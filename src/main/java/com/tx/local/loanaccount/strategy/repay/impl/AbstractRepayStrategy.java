/*
 * 描          述:  <描述>
 * 修  改   人:  Administrator
 * 修改时间:  2017年6月25日
 * <修改描述:>
 */
package com.tx.local.loanaccount.strategy.repay.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.transaction.Transactional;

import org.apache.commons.collections4.CollectionUtils;

import com.tx.component.command.context.CommandContext;
import com.tx.component.command.context.CommandResponse;
import com.tx.core.exceptions.util.AssertUtils;
import com.tx.local.basicdata.model.FeeItemEnum;
import com.tx.local.loanaccount.LoanAccountConstants;
import com.tx.local.loanaccount.context.request.trading.repay.RepayRequest;
import com.tx.local.loanaccount.handle.PaymentScheduleHandler;
import com.tx.local.loanaccount.helper.paymentrecord.PaymentRecordEntryHelper;
import com.tx.local.loanaccount.helper.repayallocator.RepayAllocator;
import com.tx.local.loanaccount.helper.repayallocator.repay.DDDRepayAllocator;
import com.tx.local.loanaccount.model.ChargeRecordEntry;
import com.tx.local.loanaccount.model.LATradingRecord;
import com.tx.local.loanaccount.model.LoanAccount;
import com.tx.local.loanaccount.model.OverRepayRecord;
import com.tx.local.loanaccount.model.PaymentRecordEntry;
import com.tx.local.loanaccount.model.RepayIntention;
import com.tx.local.loanaccount.model.ScheduleTypeEnum;
import com.tx.local.loanaccount.strategy.repay.RepayStrategy;

/**
 * 默认的还款策略实现<br/>
 * <功能详细描述>
 * 
 * @author  Administrator
 * @version  [版本号, 2017年6月25日]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public abstract class AbstractRepayStrategy implements RepayStrategy {
    
    /**
     * @param loanAccount
     * @param repayIntention
     * @return
     */
    @Override
    @Transactional
    public LATradingRecord repay(LoanAccount loanAccount,
            RepayIntention repayIntention) {
        AssertUtils.notNull(loanAccount, "loanAccount is null.");
        AssertUtils.notEmpty(loanAccount.getId(), "loanAccount.id is empty.");
        AssertUtils.notNull(repayIntention, "repayIntention is null.");
        AssertUtils.notEmpty(repayIntention.getLoanAccountId(),
                "repayIntention.loanAccountId is empty.");
        AssertUtils.notEmpty(repayIntention.getBankAccountId(),
                "repayIntention.bankAccountId is empty.");
        
        AssertUtils.notNull(repayIntention.getRepayDate(),
                "repayIntention.repayDate is null.");
        AssertUtils.notNull(repayIntention.getAmount(),
                "repayIntention.amount is null.");
        
        String loanAccountId = loanAccount.getId();
        AssertUtils.isTrue(
                loanAccountId.equals(repayIntention.getLoanAccountId()),
                "loanAccountId is not matches.");
        
        RepayRequest rRequest = new RepayRequest(loanAccountId, repayIntention);
        CommandResponse response = CommandContext.getContext().post(rRequest);
        
        LATradingRecord tr = (LATradingRecord) response
                .getAttribute(LoanAccountConstants.RESPONSE_KEY_TRADINGRECORD);
        return tr;
    }
    
    /**
     * 还款分配<br/>
     * @param loanAccount
     * @param paymentScheduleHandler
     * @param repayIntention
     * @return
     */
    @Override
    public RepayAllocator assignOfRepay(LoanAccount loanAccount,
            PaymentScheduleHandler paymentScheduleHandler,
            RepayIntention repayIntention) {
        AssertUtils.notNull(loanAccount, "loanAccount is null.");
        AssertUtils.notNull(paymentScheduleHandler,
                "paymentScheduleHandler is null.");
        AssertUtils.notNull(repayIntention, "repayIntention is null.");
        AssertUtils.notNull(repayIntention.getRepayDate(),
                "repayIntention.repayDate is null.");
        AssertUtils.notNull(repayIntention.getAmount(),
                "repayIntention.amount is null.");
        AssertUtils.isTrue(
                repayIntention.getAmount().compareTo(BigDecimal.ZERO) > 0,
                "repayIntention.amount should > 0.");
        
        RepayAllocator assignAllocator = new DDDRepayAllocator(loanAccount,
                paymentScheduleHandler, repayIntention);
        assignAllocator.assign();
        
        return assignAllocator;
    }
    
    /**
     * @param loanAccount
     * @param repayIntention
     * @return
     */
    @Override
    public Map<FeeItemEnum, BigDecimal> autoAssignOfRepay(
            LoanAccount loanAccount, PaymentScheduleHandler handler,
            RepayIntention repayIntention) {
        List<ChargeRecordEntry> creList = buildRepayChargeEntryList(loanAccount,
                handler.getFeeItemMap(),
                handler.getFeeItemCfgMap(),
                handler,
                null,
                repayIntention.getRepayDate());
        handler.chargeByEntry(creList);
        //获取到还款结果
        RepayAllocator assignAllocator = assignOfRepay(loanAccount,
                handler,
                repayIntention);
        Map<ScheduleTypeEnum, Map<String, Map<FeeItemEnum, BigDecimal>>> assignMap = assignAllocator
                .getAssignAmountMap();
        Map<String, Map<FeeItemEnum, BigDecimal>> mainAssignMap = assignMap
                .get(ScheduleTypeEnum.MAIN);
        
        Map<FeeItemEnum, BigDecimal> feeItem2amountMap = new HashMap<>();
        for (Map<FeeItemEnum, BigDecimal> feeItemMapTemp : mainAssignMap
                .values()) {
            for (Entry<FeeItemEnum, BigDecimal> feeItemEntryTemp : feeItemMapTemp
                    .entrySet()) {
                BigDecimal assignAmountTemp = BigDecimal.ZERO;
                if (!feeItem2amountMap.containsKey(feeItemEntryTemp.getKey())) {
                    feeItem2amountMap.put(feeItemEntryTemp.getKey(),
                            BigDecimal.ZERO);
                } else {
                    assignAmountTemp = feeItem2amountMap
                            .get(feeItemEntryTemp.getKey());
                }
                feeItem2amountMap.put(feeItemEntryTemp.getKey(),
                        assignAmountTemp.add(feeItemEntryTemp.getValue()));
            }
        }
        //FIXME: 这里后面需要调整，临时解决
        if (assignAllocator.getOverRepayAmount()
                .compareTo(BigDecimal.ZERO) > 0) {
            feeItem2amountMap.put(FeeItemEnum.DK_CEHK,
                    assignAllocator.getOverRepayAmount());
        }
        
        return feeItem2amountMap;
    }
    
    /**
     * 还款分配<br/>
     * @param tradingRecord
     * @param loanAccount
     * @param handler
     * @param assignAllocator
     * @param isReceived
     * @return
     */
    @Override
    public List<PaymentRecordEntry> assignOfRepay(LATradingRecord tradingRecord,
            LoanAccount loanAccount, PaymentScheduleHandler handler,
            RepayAllocator assignAllocator, boolean isReceived) {
        //获取到还款结果
        Map<ScheduleTypeEnum, Map<String, Map<FeeItemEnum, BigDecimal>>> assignMap = assignAllocator
                .getAssignAmountMap();
        BigDecimal assignSum = assignAllocator.getAmount()
                .subtract(assignAllocator.getOverRepayAmount());
        List<PaymentRecordEntry> resList = new ArrayList<>();
        for (Entry<ScheduleTypeEnum, Map<String, Map<FeeItemEnum, BigDecimal>>> scheduleTypeEntry : assignMap
                .entrySet()) {
            ScheduleTypeEnum scheduleTypeTemp = scheduleTypeEntry.getKey();
            resList.addAll(PaymentRecordEntryHelper.buildPaymentRecordEntryList(
                    tradingRecord,
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
     * 构建超额还款分项列表<br/>
     * @param loanAccount
     * @param handler
     * @param tradingRecord
     * @param overRepayRecords
     * @param isReceived
     * @return
     */
    @Override
    public List<PaymentRecordEntry> buildCEHKEntryList(LoanAccount loanAccount,
            PaymentScheduleHandler handler, LATradingRecord tradingRecord,
            List<OverRepayRecord> overRepayRecords, boolean isReceived) {
        List<PaymentRecordEntry> creList = new ArrayList<>();
        if (CollectionUtils.isEmpty(overRepayRecords)) {
            return creList;
        }
        
        for (OverRepayRecord overRepayRecord : overRepayRecords) {
            for (ScheduleTypeEnum scheduleTypeTemp : handler
                    .getPaymentScheduleMap().keySet()) {
                creList.add(PaymentRecordEntryHelper.buildPaymentRecordEntry(
                        tradingRecord,
                        handler,
                        scheduleTypeTemp,
                        overRepayRecord.getPeriod(),
                        overRepayRecord.getFeeItem(),
                        overRepayRecord.getAmount(),
                        isReceived));
            }
        }
        return creList;
    }
    
}
