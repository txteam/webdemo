/*
 * 描          述:  <描述>
 * 修  改   人:  Administrator
 * 修改时间:  2014年6月5日
 * <修改描述:>
 */
package com.tx.local.loanaccount.helper.paymentrecord;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.apache.commons.collections.CollectionUtils;

import com.tx.core.TxConstants;
import com.tx.core.exceptions.util.AssertUtils;
import com.tx.local.basicdata.model.FeeItemEnum;
import com.tx.local.loanaccount.handle.PaymentScheduleHandler;
import com.tx.local.loanaccount.model.LATradingRecord;
import com.tx.local.loanaccount.model.LoanAccount;
import com.tx.local.loanaccount.model.PaymentRecordEntry;
import com.tx.local.loanaccount.model.PaymentScheduleEntry;
import com.tx.local.loanaccount.model.ScheduleTypeEnum;

/**
 * 还款计划帮助类<br/>
 * <功能详细描述>
 * 
 * @author  Administrator
 * @version  [版本号, 2014年6月5日]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public abstract class PaymentRecordEntryHelper {
    
    /** 
     * 辅助生成还款记录分项<br/>
     * <功能详细描述>
     * @param managerPaymentScheduleEntry [参数说明]
     * 
     * @return void [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    public static PaymentRecordEntry buildPaymentRecordEntry(LATradingRecord buildTradingRecord,
            LATradingRecord tradingRecord, PaymentScheduleHandler handler, ScheduleTypeEnum scheduleType, String period,
            FeeItemEnum feeItem, BigDecimal amount, boolean isReceived) {
        AssertUtils.notNull(buildTradingRecord, "buildTradingRecord is null.");
        AssertUtils.notNull(tradingRecord, "tradingRecord is null.");
        AssertUtils.notNull(handler, "handler is empty.");
        AssertUtils.notNull(scheduleType, "scheduleType is null.");
        AssertUtils.notEmpty(period, "period is empty.");
        AssertUtils.notNull(feeItem, "feeItem is null.");
        AssertUtils.notNull(amount, "amount is null.");
        
        PaymentScheduleEntry paymentScheduleEntry = handler.getPaymentScheduleEntry(scheduleType, period, feeItem);
        
        //构建对应的还款记录分项
        PaymentRecordEntry entry = new PaymentRecordEntry();
        entry.setLoanAccountId(tradingRecord.getLoanAccountId());
        //设置分项对应的交易记录
        entry.setBuildTradingRecord(buildTradingRecord);
        entry.setTradingRecord(tradingRecord);
        // 根据费用项计算费用
        entry.setScheduleType(scheduleType);
        entry.setPeriod(period);
        entry.setPaymentScheduleEntry(paymentScheduleEntry);
        entry.setFeeItem(feeItem);
        
        entry.setSourceAmount(paymentScheduleEntry.getActualReceivedAmount());
        entry.setAmount(amount);
        entry.setTargetAmount(paymentScheduleEntry.getActualReceivedAmount().add(amount));
        
        //设置默认值
        entry.setRevoked(false);
        entry.setRevokeDate(null);
        Date now = new Date();
        entry.setCreateDate(now);
        entry.setLastUpdateDate(now);
        
        entry.setReceived(isReceived);
        entry.setReceiveDate(isReceived ? now : null);
        
        return entry;
    }
    
    /** 
     * 辅助生成还款记录分项<br/>
     * <功能详细描述>
     * @param managerPaymentScheduleEntry [参数说明]
     * 
     * @return void [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    public static PaymentRecordEntry buildPaymentRecordEntry(LATradingRecord tradingRecord,
            PaymentScheduleHandler handler, ScheduleTypeEnum scheduleType, String period, FeeItemEnum feeItem,
            BigDecimal amount, boolean isReceived) {
        AssertUtils.notNull(tradingRecord, "tradingRecord is null.");
        AssertUtils.notNull(handler, "handler is empty.");
        AssertUtils.notNull(scheduleType, "scheduleType is null.");
        AssertUtils.notEmpty(period, "period is empty.");
        AssertUtils.notNull(feeItem, "feeItem is null.");
        AssertUtils.notNull(amount, "amount is null.");
        
        PaymentRecordEntry entry = buildPaymentRecordEntry(tradingRecord,
                tradingRecord,
                handler,
                scheduleType,
                period,
                feeItem,
                amount,
                isReceived);
        
        return entry;
    }
    
    /**
     * 根据原还款记录生成新的还款记录
     * <功能详细描述>
     * @param paymentScheduleMap
     * @param sourcePaymentRecordEntryList
     * @return [参数说明]
     * 
     * @return List<PaymentRecordEntry> [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
    */
    public static List<PaymentRecordEntry> buildRevokePaymentRecordEntry(LATradingRecord tradingRecord,
            PaymentScheduleHandler handler, List<PaymentRecordEntry> sourcePaymentRecordEntryList) {
        List<PaymentRecordEntry> paymentRecordEntryList = new ArrayList<>(TxConstants.INITIAL_CONLLECTION_SIZE);
        if (CollectionUtils.isEmpty(sourcePaymentRecordEntryList)) {
            return paymentRecordEntryList;
        }
        for (PaymentRecordEntry entry : sourcePaymentRecordEntryList) {
            paymentRecordEntryList.add(buildPaymentRecordEntry(tradingRecord,
                    handler,
                    entry.getScheduleType(),
                    entry.getPeriod(),
                    entry.getFeeItem(),
                    entry.getAmount().negate(),
                    entry.isReceived()));
        }
        return paymentRecordEntryList;
    }
    
    /**
     * 根据平息分配映射，以及递减分配映射，生成对应的还款记录分项
     * <功能详细描述>
     * @param loanAccount
     * @param paymentScheduleMap
     * @param assignMap
     * @param assignMapIrr
     * @return [参数说明]
     * 
     * @return List<PaymentRecordEntry> [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
    */
    public static List<PaymentRecordEntry> buildPaymentRecordEntryList(LATradingRecord tradingRecord,
            LoanAccount loanAccount, PaymentScheduleHandler handler,
            ScheduleTypeEnum scheduleType, BigDecimal assignSum, Map<String, Map<FeeItemEnum, BigDecimal>> assignMap,
            boolean isReceived) {
        //验证还款记录分配的合法性
        validate(assignSum, assignMap);
        
        Set<String> periodSet = new HashSet<>(TxConstants.INITIAL_CONLLECTION_SIZE);
        
        //获取期数并集
        periodSet.addAll(assignMap.keySet());
        
        List<PaymentRecordEntry> paymentRecordEntryList = new ArrayList<>(TxConstants.INITIAL_CONLLECTION_SIZE);
        //迭代期数生成还款记录分项
        for (String period : periodSet) {
            //获取档期费用项并集
            Set<FeeItemEnum> feeItemSet = new HashSet<>(TxConstants.INITIAL_CONLLECTION_SIZE);
            if (assignMap.containsKey(period)) {
                feeItemSet.addAll(assignMap.get(period).keySet());
            }
            //迭代费用项生成还款计划分项
            for (FeeItemEnum feeItem : feeItemSet) {
                BigDecimal amount = assignMap.containsKey(period) ? assignMap.get(period).get(feeItem)
                        : BigDecimal.ZERO;
                PaymentRecordEntry entry = buildPaymentRecordEntry(tradingRecord,
                        handler,
                        scheduleType,
                        period,
                        feeItem,
                        amount == null ? BigDecimal.ZERO : amount,
                        isReceived);
                paymentRecordEntryList.add(entry);
            }
        }
        
        List<PaymentRecordEntry> resList = new ArrayList<>(TxConstants.INITIAL_CONLLECTION_SIZE);
        //将还款逻辑中ammount 以及 amountIrr均为0的记录抛弃掉
        for (PaymentRecordEntry recordEntry : paymentRecordEntryList) {
            if (BigDecimal.ZERO.compareTo(recordEntry.getAmount()) == 0) {
                continue;
            } else {
                resList.add(recordEntry);
            }
        }
        return resList;
    }
    
    /**
     * 校验平息递减分配金额是否相等<br/>
     * <功能详细描述>
     * @param assignMap
     * @param assignMapIrr [参数说明]
     * 
     * @return void [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
    */
    private static void validate(BigDecimal assignSum, Map<String, Map<FeeItemEnum, BigDecimal>> assignMap) {
        AssertUtils.notEmpty(assignSum.compareTo(BigDecimal.ZERO) > 0, "assignSum should >= 0.");
        AssertUtils.notEmpty(assignMap, "assignMap is empty.");
        
        BigDecimal sum = BigDecimal.ZERO;
        for (Map<FeeItemEnum, BigDecimal> entryMap : assignMap.values()) {
            for (Entry<FeeItemEnum, BigDecimal> amountEntry : entryMap.entrySet()) {
                sum = sum.add(amountEntry.getValue());
            }
        }
        AssertUtils.isTrue(sum.compareTo(assignSum) == 0,
                "平息分配总金额与还款总金额不等。分项值和:{} 总金额:{}",
                new Object[] { sum, assignSum });
    }
    
}
