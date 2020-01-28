/*
 * 描          述:  <描述>
 * 修  改   人:  Administrator
 * 修改时间:  2017年6月25日
 * <修改描述:>
 */
package com.tx.local.loanaccount.strategy.settle;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.tx.core.exceptions.util.AssertUtils;
import com.tx.local.basicdata.model.FeeItemCfg;
import com.tx.local.basicdata.model.FeeItemEnum;
import com.tx.local.loanaccount.handle.PaymentScheduleHandler;
import com.tx.local.loanaccount.helper.exemptallocator.ExemptAllocator;
import com.tx.local.loanaccount.helper.exemptallocator.settle.DefaultSettleExemptAllocator;
import com.tx.local.loanaccount.helper.exemptrecord.ExemptRecordEntryHelper;
import com.tx.local.loanaccount.helper.paymentrecord.PaymentRecordEntryHelper;
import com.tx.local.loanaccount.helper.repayallocator.RepayAllocator;
import com.tx.local.loanaccount.helper.repayallocator.settle.DefaultSettleAllocator;
import com.tx.local.loanaccount.model.ChargeRecordEntry;
import com.tx.local.loanaccount.model.ExemptIntention;
import com.tx.local.loanaccount.model.ExemptRecordEntry;
import com.tx.local.loanaccount.model.LATradingRecord;
import com.tx.local.loanaccount.model.LoanAccount;
import com.tx.local.loanaccount.model.LoanAccountFeeItem;
import com.tx.local.loanaccount.model.LoanAccountSettleDetail;
import com.tx.local.loanaccount.model.PaymentRecordEntry;
import com.tx.local.loanaccount.model.RepayIntention;
import com.tx.local.loanaccount.model.ScheduleTypeEnum;
import com.tx.local.loanaccount.model.SettleIntention;
import com.tx.local.loanaccount.model.settledetail.DefaultLoanBillLoanAccountSettleDetail;

/**
  * 结清策略实现<br/>
  * <功能详细描述>
  * 
  * @author  Administrator
  * @version  [版本号, 2017年6月25日]
  * @see  [相关类/方法]
  * @since  [产品/模块版本]
  */
public abstract class AbstractSettleStrategy implements SettleStrategy {
    
    /**
     * @param loanAccount
     * @param handler
     * @param repayDate
     * @return
     */
    @Override
    public LoanAccountSettleDetail buildLoanAccountSettleDetail(LoanAccount loanAccount,
            Map<FeeItemEnum, LoanAccountFeeItem> feeItemMap, Map<FeeItemEnum, FeeItemCfg> feeItemCfgMap,
            PaymentScheduleHandler handler, Date repayDate) {
        //提前结清冲销
        List<ChargeRecordEntry> creList = buildSettleChargeEntryList(loanAccount,
                feeItemMap,
                feeItemCfgMap,
                handler,
                null,
                repayDate);
        handler.chargeByEntry(creList);
        
        //结清详情
        LoanAccountSettleDetail lasd = new DefaultLoanBillLoanAccountSettleDetail(loanAccount, handler, repayDate);
        return lasd;
    }
    
    /**
     * @param loanAccount
     * @param handler
     * @param settleIntention
     * @return
     */
    @Override
    public Map<FeeItemEnum, BigDecimal> autoAssignOfSettle(LoanAccount loanAccount,
            Map<FeeItemEnum, LoanAccountFeeItem> feeItemMap, Map<FeeItemEnum, FeeItemCfg> feeItemCfgMap,
            PaymentScheduleHandler handler, SettleIntention settleIntention) {
        //提前结清冲销
        List<ChargeRecordEntry> creList = buildSettleChargeEntryList(loanAccount,
                feeItemMap,
                feeItemCfgMap,
                handler,
                null,
                settleIntention.getRepayDate());
        handler.chargeByEntry(creList);
        
        //豁免分配器
        ExemptAllocator exemptAllocator = assignOfSettleExempt(loanAccount, handler, settleIntention);
        if (exemptAllocator != null) {
            List<ExemptRecordEntry> ereList = ExemptRecordEntryHelper.buildExemptRecordEntryList(null,
                    handler,
                    ScheduleTypeEnum.MAIN,
                    exemptAllocator.getAmount(),
                    exemptAllocator.getMainAssignAmountMap());
            handler.exemptByEntry(ereList);
        }
        
        //获取到还款结果
        Map<FeeItemEnum, BigDecimal> feeItem2amountMap = new HashMap<>();
        RepayAllocator assignAllocator = assignOfSettle(loanAccount, handler, settleIntention);
        Map<ScheduleTypeEnum, Map<String, Map<FeeItemEnum, BigDecimal>>> assignMap = assignAllocator
                .getAssignAmountMap();
        Map<String, Map<FeeItemEnum, BigDecimal>> mainAssignMap = assignMap.get(ScheduleTypeEnum.MAIN);
        
        for (Map<FeeItemEnum, BigDecimal> feeItemMapTemp : mainAssignMap.values()) {
            for (Entry<FeeItemEnum, BigDecimal> feeItemEntryTemp : feeItemMapTemp.entrySet()) {
                BigDecimal assignAmountTemp = BigDecimal.ZERO;
                if (!feeItem2amountMap.containsKey(feeItemEntryTemp.getKey())) {
                    feeItem2amountMap.put(feeItemEntryTemp.getKey(), BigDecimal.ZERO);
                } else {
                    assignAmountTemp = feeItem2amountMap.get(feeItemEntryTemp.getKey());
                }
                feeItem2amountMap.put(feeItemEntryTemp.getKey(), assignAmountTemp.add(feeItemEntryTemp.getValue()));
            }
        }
        //FIXME: 这里后面需要调整，临时解决
        if (assignAllocator.getOverRepayAmount().compareTo(BigDecimal.ZERO) > 0) {
            feeItem2amountMap.put(FeeItemEnum.DK_CEHK, assignAllocator.getOverRepayAmount());
        }
        
        return feeItem2amountMap;
    }
    
    /**
     * @param loanAccount
     * @param handler
     * @param repayIntention
     * @return
     */
    @Override
    public RepayAllocator assignOfSettle(LoanAccount loanAccount, PaymentScheduleHandler handler,
            SettleIntention settleIntention) {
        AssertUtils.notNull(loanAccount, "loanAccount is null.");
        AssertUtils.notNull(handler, "handler is null.");
        AssertUtils.notNull(settleIntention, "settleIntention is null.");
        AssertUtils.notNull(settleIntention.getRepayDate(), "settleIntention.repayDate is null.");
        AssertUtils.notNull(settleIntention.getRepayAmount(), "settleIntention.repayAmount is null.");
        AssertUtils.isTrue(settleIntention.getRepayAmount().compareTo(BigDecimal.ZERO) > 0,
                "repayIntention.repayAmount should > 0.");
        
        RepayIntention repayIntention = new RepayIntention();
        repayIntention.setRepayDate(settleIntention.getRepayDate());
        repayIntention.setAmount(settleIntention.getRepayAmount());
        
        RepayAllocator assignAllocator = new DefaultSettleAllocator(loanAccount, handler, repayIntention);
        assignAllocator.assign();
        
        return assignAllocator;
    }
    
    /**
     * @param tradingRecord
     * @param loanAccount
     * @param feeItemCfgMap
     * @param handler
     * @param assignAllocator
     * @param received
     * @return
     */
    @Override
    public List<PaymentRecordEntry> assignOfSettle(LATradingRecord tradingRecord, LoanAccount loanAccount,
            PaymentScheduleHandler handler, RepayAllocator assignAllocator, boolean isReceived) {
        //获取到还款结果
        Map<ScheduleTypeEnum, Map<String, Map<FeeItemEnum, BigDecimal>>> assignMap = assignAllocator
                .getAssignAmountMap();
        
        BigDecimal assignSum = assignAllocator.getAmount().subtract(assignAllocator.getOverRepayAmount());
        List<PaymentRecordEntry> resList = new ArrayList<>();
        for (Entry<ScheduleTypeEnum, Map<String, Map<FeeItemEnum, BigDecimal>>> scheduleTypeEntry : assignMap
                .entrySet()) {
            ScheduleTypeEnum scheduleTypeTemp = scheduleTypeEntry.getKey();
            resList.addAll(PaymentRecordEntryHelper.buildPaymentRecordEntryList(tradingRecord,
                    loanAccount,
                    handler,
                    scheduleTypeTemp,
                    assignSum,
                    assignMap.get(scheduleTypeTemp),
                    isReceived));
        }
        return resList;
    }
    
    /**
     * @param loanAccount
     * @param handler
     * @param exemptIntention
     * @return
     */
    @Override
    public ExemptAllocator assignOfSettleExempt(LoanAccount loanAccount, PaymentScheduleHandler handler,
            SettleIntention settleIntention) {
        AssertUtils.notNull(loanAccount, "loanAccount is null.");
        AssertUtils.notNull(handler, "handler is null.");
        AssertUtils.notNull(settleIntention, "settleIntention is null.");
        AssertUtils.notNull(settleIntention.getRepayDate(), "settleIntention.repayDate is null.");
        AssertUtils.notNull(settleIntention.getExemptAmount(), "settleIntention.amount is null.");
        AssertUtils.isTrue(settleIntention.getExemptAmount().compareTo(BigDecimal.ZERO) >= 0,
                "repayIntention.amount should >= 0.");
        
        if (settleIntention.getExemptAmount().compareTo(BigDecimal.ZERO) == 0) {
            return null;
        }
        
        ExemptIntention exemptIntention = new ExemptIntention();
        exemptIntention.setAmount(settleIntention.getExemptAmount());
        exemptIntention.setFeeItem2AmountMap(settleIntention.getExemptFeeItem2AmountMap());
        
        ExemptAllocator assignAllocator = new DefaultSettleExemptAllocator(loanAccount, handler, exemptIntention);
        assignAllocator.assign();
        
        return assignAllocator;
    }
    
    /**
     * @param tradingRecord
     * @param loanAccount
     * @param handler
     * @param exemptAllocator
     * @return
     */
    @Override
    public List<ExemptRecordEntry> assignOfSettleExempt(LATradingRecord tradingRecord, LoanAccount loanAccount,
            PaymentScheduleHandler handler, ExemptAllocator exemptAllocator) {
        List<ExemptRecordEntry> resList = new ArrayList<>();
        if (exemptAllocator == null) {
            return resList;
        }
        
        //获取到还款结果
        Map<ScheduleTypeEnum, Map<String, Map<FeeItemEnum, BigDecimal>>> assignMap = exemptAllocator
                .getAssignAmountMap();
        BigDecimal assignSum = exemptAllocator.getAmount();
        
        for (Entry<ScheduleTypeEnum, Map<String, Map<FeeItemEnum, BigDecimal>>> scheduleTypeEntry : assignMap
                .entrySet()) {
            ScheduleTypeEnum scheduleTypeTemp = scheduleTypeEntry.getKey();
            resList.addAll(ExemptRecordEntryHelper.buildExemptRecordEntryList(tradingRecord,
                    handler,
                    scheduleTypeTemp,
                    assignSum,
                    scheduleTypeEntry.getValue()));
        }
        return resList;
    }
}
