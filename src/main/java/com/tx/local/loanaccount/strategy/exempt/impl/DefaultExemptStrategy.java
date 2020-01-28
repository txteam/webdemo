/*
 * 描          述:  <描述>
 * 修  改   人:  Administrator
 * 修改时间:  2017年7月5日
 * <修改描述:>
 */
package com.tx.local.loanaccount.strategy.exempt.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.springframework.stereotype.Component;

import com.tx.component.command.context.CommandContext;
import com.tx.component.command.context.CommandResponse;
import com.tx.core.exceptions.util.AssertUtils;
import com.tx.local.basicdata.model.FeeItemEnum;
import com.tx.local.loanaccount.LoanAccountConstants;
import com.tx.local.loanaccount.context.request.trading.exempt.ExemptRequest;
import com.tx.local.loanaccount.handle.PaymentScheduleHandler;
import com.tx.local.loanaccount.helper.exemptallocator.ExemptAllocator;
import com.tx.local.loanaccount.helper.exemptallocator.repay.DefaultRepayExemptAllocator;
import com.tx.local.loanaccount.helper.exemptrecord.ExemptRecordEntryHelper;
import com.tx.local.loanaccount.model.ExemptIntention;
import com.tx.local.loanaccount.model.ExemptRecordEntry;
import com.tx.local.loanaccount.model.LATradingRecord;
import com.tx.local.loanaccount.model.LoanAccount;
import com.tx.local.loanaccount.model.ScheduleTypeEnum;
import com.tx.local.loanaccount.strategy.exempt.ExemptStrategy;

/**
 * 豁免策略<br/>
 * <功能详细描述>
 * 
 * @author  Administrator
 * @version  [版本号, 2017年7月5日]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
@Component("defaultExemptStrategy")
public class DefaultExemptStrategy implements ExemptStrategy {
    
    /**
     * 豁免<br/>
     * @param loanAccount
     * @param exemptIntention
     * @return
     */
    @Override
    public LATradingRecord exempt(LoanAccount loanAccount,
            ExemptIntention exemptIntention) {
        AssertUtils.notNull(loanAccount, "loanAccount is null.");
        AssertUtils.notEmpty(loanAccount.getId(), "loanAccount.id is empty.");
        AssertUtils.notNull(exemptIntention, "exemptIntention is null.");
        AssertUtils.notEmpty(exemptIntention.getLoanAccountId(),
                "exemptIntention.loanAccountId is empty.");
        
        AssertUtils.notNull(exemptIntention.getRepayDate(),
                "exemptIntention.repayDate is null.");
        AssertUtils.notNull(exemptIntention.getAmount(),
                "exemptIntention.amount is null.");
        
        String loanAccountId = loanAccount.getId();
        AssertUtils.isTrue(
                loanAccountId.equals(exemptIntention.getLoanAccountId()),
                "loanAccountId is not matches.");
        
        ExemptRequest rRequest = new ExemptRequest(loanAccountId,
                exemptIntention);
        CommandResponse response = CommandContext.getContext().post(rRequest);
        
        LATradingRecord tr = (LATradingRecord) response.getAttribute(
                LoanAccountConstants.RESPONSE_KEY_REVOKED_TRADINGRECORD);
        return tr;
    }
    
    /**
     * @param loanAccount
     * @param handler
     * @param exemptIntention
     * @return
     */
    @Override
    public ExemptAllocator assignOfExempt(LoanAccount loanAccount,
            PaymentScheduleHandler handler, ExemptIntention exemptIntention) {
        AssertUtils.notNull(loanAccount, "loanAccount is null.");
        AssertUtils.notNull(handler, "handler is null.");
        AssertUtils.notNull(exemptIntention, "exemptIntention is null.");
        AssertUtils.notNull(exemptIntention.getRepayDate(),
                "exemptIntention.repayDate is null.");
        AssertUtils.notNull(exemptIntention.getAmount(),
                "exemptIntention.amount is null.");
        AssertUtils.isTrue(
                exemptIntention.getAmount().compareTo(BigDecimal.ZERO) > 0,
                "repayIntention.amount should > 0.");
        
        ExemptAllocator assignAllocator = new DefaultRepayExemptAllocator(
                loanAccount, handler, exemptIntention);
        assignAllocator.assign();
        
        return assignAllocator;
    }
    
    /**
     * @param loanAccount
     * @param handler
     * @param exemptIntention
     * @return
     */
    @Override
    public Map<FeeItemEnum, BigDecimal> mainAssignOfExempt(
            LoanAccount loanAccount, PaymentScheduleHandler handler,
            ExemptIntention exemptIntention) {
        //获取到还款结果
        ExemptAllocator assignAllocator = assignOfExempt(loanAccount,
                handler,
                exemptIntention);
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
        
        return feeItem2amountMap;
    }
    
    /**
     * @param tradingRecord
     * @param loanAccount
     * @param handler
     * @param exemptIntention
     * @return
     */
    @Override
    public List<ExemptRecordEntry> assignOfExempt(LATradingRecord tradingRecord,
            LoanAccount loanAccount, PaymentScheduleHandler handler,
            ExemptAllocator exemptAllocator) {
        //获取到还款结果
        Map<ScheduleTypeEnum, Map<String, Map<FeeItemEnum, BigDecimal>>> assignMap = exemptAllocator
                .getAssignAmountMap();
        BigDecimal assignSum = exemptAllocator.getAmount();
        List<ExemptRecordEntry> resList = new ArrayList<>();
        for (Entry<ScheduleTypeEnum, Map<String, Map<FeeItemEnum, BigDecimal>>> scheduleTypeEntry : assignMap
                .entrySet()) {
            ScheduleTypeEnum scheduleTypeTemp = scheduleTypeEntry.getKey();
            resList.addAll(ExemptRecordEntryHelper.buildExemptRecordEntryList(
                    tradingRecord,
                    handler,
                    scheduleTypeTemp,
                    assignSum,
                    scheduleTypeEntry.getValue()));
        }
        return resList;
    }
    
}
