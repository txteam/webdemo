/*
 * 描          述:  <描述>
 * 修  改   人:  Administrator
 * 修改时间:  2014年6月11日
 * <修改描述:>
 */
package com.tx.local.loanaccount.helper.exemptrecord;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections4.MapUtils;

import com.tx.core.TxConstants;
import com.tx.core.exceptions.util.AssertUtils;
import com.tx.local.basicdata.model.FeeItemEnum;
import com.tx.local.loanaccount.handle.PaymentScheduleHandler;
import com.tx.local.loanaccount.model.ExemptRecordEntry;
import com.tx.local.loanaccount.model.LATradingRecord;
import com.tx.local.loanaccount.model.PaymentScheduleEntry;
import com.tx.local.loanaccount.model.ScheduleTypeEnum;

/**
 * 豁免记录自动分配帮助类<br/>
 * 
 * @author  Administrator
 * @version  [版本号, 2014年6月11日]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public class ExemptRecordEntryHelper {
    
    /**
     * 根据传入的豁免金额构建豁免计划分项<br/>
     * <功能详细描述>
     * @param tradingRecord
     * @param handler
     * @param scheduleType
     * @param period
     * @param feeItem
     * @param amount
     * @return [参数说明]
     * 
     * @return ExemptRecordEntry [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    private static ExemptRecordEntry buildExemptRecordEntry(
            LATradingRecord tradingRecord, PaymentScheduleHandler handler,
            ScheduleTypeEnum scheduleType, String period, FeeItemEnum feeItem,
            BigDecimal amount) {
        AssertUtils.notNull(handler, "handler is empty.");
        AssertUtils.notNull(scheduleType, "scheduleType is null.");
        AssertUtils.notEmpty(period, "period is empty.");
        AssertUtils.notNull(feeItem, "feeItem is null.");
        AssertUtils.notNull(amount, "amount is null.");
        
        PaymentScheduleEntry paymentScheduleEntry = handler
                .getPaymentScheduleEntry(scheduleType, period, feeItem);
        
        ExemptRecordEntry entry = new ExemptRecordEntry();
        entry.setLoanAccountId(
                tradingRecord == null ? "" : tradingRecord.getLoanAccountId());
        entry.setTradingRecord(tradingRecord);//设置分项对应的交易记录
        entry.setScheduleType(scheduleType);// 根据费用项计算费用
        entry.setPaymentScheduleEntry(paymentScheduleEntry);
        entry.setPeriod(period);
        entry.setFeeItem(feeItem);
        
        entry.setSourceAmount(paymentScheduleEntry.getExemptAmount());
        entry.setAmount(amount);
        entry.setTargetAmount(
                paymentScheduleEntry.getExemptAmount().add(amount));
        
        //设置默认值
        entry.setRevoked(false);
        entry.setRevokeDate(null);
        Date now = new Date();
        entry.setCreateDate(now);
        entry.setLastUpdateDate(now);
        
        return entry;
    }
    
    /**
      * 根据平息以及递减分配构建豁免记录分项集合
      * <功能详细描述>
      * @param tradingRecord
      * @param paymentScheduleMap
      * @param exemptAssignMap
      * @param exemptAssignMapIrr
      * @return [参数说明]
      * 
      * @return List<ExemptRecordEntry> [返回类型说明]
      * @exception throws [异常类型] [异常说明]
      * @see [类、类#方法、类#成员]
     */
    public static List<ExemptRecordEntry> buildExemptRecordEntryList(
            LATradingRecord tradingRecord, PaymentScheduleHandler handler,
            ScheduleTypeEnum scheduleType, BigDecimal assignSum,
            Map<String, Map<FeeItemEnum, BigDecimal>> exemptAssignMap) {
        AssertUtils.notNull(handler, "handler is empty.");
        AssertUtils.notNull(scheduleType, "scheduleType is null.");
        
        //验证还款记录分配的合法性
        validate(assignSum, exemptAssignMap);
        
        List<ExemptRecordEntry> resList = new ArrayList<>(
                TxConstants.INITIAL_CONLLECTION_SIZE);
        if (MapUtils.isEmpty(exemptAssignMap)) {
            return resList;
        }
        //迭代期数生成还款记录分项
        for (Entry<String, Map<FeeItemEnum, BigDecimal>> exemptAssignEntry : exemptAssignMap
                .entrySet()) {
            String period = exemptAssignEntry.getKey();
            AssertUtils.notEmpty(period, "period is empty.");
            
            for (Entry<FeeItemEnum, BigDecimal> exemptAssignEntryTemp : exemptAssignEntry
                    .getValue().entrySet()) {
                FeeItemEnum feeItem = exemptAssignEntryTemp.getKey();
                BigDecimal amount = exemptAssignEntryTemp.getValue();
                AssertUtils.notNull(feeItem, "feeItem is null.");
                AssertUtils.notNull(amount, "amount is null.");
                
                ExemptRecordEntry entry = buildExemptRecordEntry(tradingRecord,
                        handler,
                        scheduleType,
                        period,
                        feeItem,
                        amount.negate());
                resList.add(entry);
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
    private static void validate(BigDecimal assignSum,
            Map<String, Map<FeeItemEnum, BigDecimal>> assignMap) {
        AssertUtils.notEmpty(assignSum.compareTo(BigDecimal.ZERO) > 0,
                "assignSum should >= 0.");
        AssertUtils.notEmpty(assignMap, "assignMap is empty.");
        
        BigDecimal sum = BigDecimal.ZERO;
        for (Map<FeeItemEnum, BigDecimal> entryMap : assignMap.values()) {
            for (Entry<FeeItemEnum, BigDecimal> amountEntry : entryMap
                    .entrySet()) {
                sum = sum.add(amountEntry.getValue());
            }
        }
        AssertUtils.isTrue(sum.compareTo(assignSum) == 0,
                "平息分配总金额与还款总金额不等。分项值和:{} 总金额:{}",
                new Object[] { sum, assignSum });
    }
    
    /**
     * 构建撤销豁免的分项<br/> 
     * <功能详细描述>
     * @param tradingRecord
     * @param paymentScheduleMap
     * @param sourceExemptRecordEntryList
     * @return [参数说明]
     * 
     * @return List<ExemptRecordEntry> [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    public static List<ExemptRecordEntry> buildRevokeExemptRecordEntry(
            LATradingRecord tradingRecord, PaymentScheduleHandler handler,
            List<ExemptRecordEntry> sourceExemptRecordEntryList) {
        List<ExemptRecordEntry> exemptRecordEntryList = new ArrayList<>(
                TxConstants.INITIAL_CONLLECTION_SIZE);
        if (CollectionUtils.isEmpty(sourceExemptRecordEntryList)) {
            return exemptRecordEntryList;
        }
        for (ExemptRecordEntry entry : sourceExemptRecordEntryList) {
            exemptRecordEntryList.add(buildExemptRecordEntry(tradingRecord,
                    handler,
                    entry.getScheduleType(),
                    entry.getPeriod(),
                    entry.getFeeItem(),
                    entry.getAmount().negate()));
        }
        return exemptRecordEntryList;
    }
    
    //    /**
    //     * 计算提前结清平息递减差值金额<br/>
    //     * <功能详细描述>
    //     * @param loanAccount
    //     * @param paymentSchedule
    //     * @return [参数说明]
    //     * 
    //     * @return ChargeRecordEntry [返回类型说明]
    //     * @exception throws [异常类型] [异常说明]
    //     * @see [类、类#方法、类#成员]
    //    */
    //    public static BigDecimal getEarlySettleCalmSubDecreasingAmount(LoanAccount loanAccount,
    //            PaymentScheduleHandler handler,String unExpiredPeriod) {
    //        AssertUtils.notNull(loanAccount, "loanAccount is null.");
    //        AssertUtils.notNull(handler, "handler is null.");
    //        AssertUtils.notEmpty(unExpiredPeriod, "unExpiredPeriod is empty.");
    //        
    //        BigDecimal earlySettleAmount = BigDecimal.ZERO;
    //        //如果无提前结清手续费，则不需要将后面及其的应收冲0，则不会产生平息递减差值
    //        PaymentScheduleHelper paymentScheduleHelper = HelperFactory.getHelper(PaymentScheduleHelper.class,
    //                loanAccount.getLoanAccountType());
    //        if (!paymentScheduleHelper.isNeedSetUpZeroWhenEarlySettleExempt(loanAccount,
    //                feeCfgItemMap,
    //                period2PaymentScheduleMap,
    //                repayDate)) {
    //            return earlySettleAmount;
    //        }
    //        String receivablePeriodOfRepayDate = PaymentScheduleHelper
    //                .getReceivablePeriodOfRepayDate(period2PaymentScheduleMap, repayDate);
    //        
    //        AssertUtils.isTrue(period2PaymentScheduleMap.containsKey(receivablePeriodOfRepayDate),
    //                "period2paymentScheduleMap not contains period:{}",
    //                receivablePeriodOfRepayDate);
    //        String nextPeriod = period2PaymentScheduleMap.get(receivablePeriodOfRepayDate).getNextPeriod();
    //        while (!StringUtils.isEmpty(nextPeriod)) {
    //            PaymentSchedule ps = period2PaymentScheduleMap.get(nextPeriod);
    //            for (PaymentScheduleEntry paymentScheduleEntry : ps.getPaymentScheduleEntryList()) {
    //                FeeItemEnum feeItem = paymentScheduleEntry.getFeeItem();
    //                if (feeCfgItemMap.get(feeItem).isPrincipal()) {
    //                    continue;
    //                }
    //                //增量
    //                BigDecimal amount = paymentScheduleEntry.getReceivableAmount()
    //                        .add(paymentScheduleEntry.getExemptAmount())
    //                        .subtract(paymentScheduleEntry.getActualReceivedAmount());
    //                BigDecimal amountIrr = paymentScheduleEntry.getReceivableAmountIrr()
    //                        .add(paymentScheduleEntry.getExemptAmountIrr())
    //                        .subtract(paymentScheduleEntry.getActualReceivedAmountIrr());
    //                
    //                earlySettleAmount = earlySettleAmount.add(amount.subtract(amountIrr));
    //            }
    //            nextPeriod = ps.getNextPeriod();
    //        }
    //        return earlySettleAmount;
    //    }
    //    
    //    /**
    //      * 提前结清可豁免金额<br/>
    //      * <功能详细描述>
    //      * @param loanAccount
    //      * @param feeCfgItemMap
    //      * @param period2PaymentScheduleMap
    //      * @param repayDate
    //      * @param isIrr
    //      * @return [参数说明]
    //      * 
    //      * @return BigDecimal [返回类型说明]
    //      * @exception throws [异常类型] [异常说明]
    //      * @see [类、类#方法、类#成员]
    //     */
    //    public static BigDecimal getEarlySettleExemptAbleSumAbs(LoanAccount loanAccount,
    //            Map<FeeItemEnum, LoanAccountFeeItem> feeCfgItemMap, Map<String, PaymentSchedule> period2PaymentScheduleMap,
    //            Date repayDate, boolean isIrr) {
    //        List<PaymentScheduleEntry> entryList = getEarlySettleExemptAblePaymentScheduleEntryList(feeCfgItemMap,
    //                period2PaymentScheduleMap,
    //                repayDate,
    //                isIrr,
    //                null);
    //        BigDecimal sum = BigDecimal.ZERO;
    //        for (PaymentScheduleEntry psEntry : entryList) {
    //            if (isIrr) {
    //                sum = sum.add(psEntry.getReceivableAmountIrr()
    //                        .add(psEntry.getExemptAmountIrr())
    //                        .subtract(psEntry.getActualReceivedAmountIrr()));
    //            } else {
    //                sum = sum.add(psEntry.getReceivableAmount()
    //                        .add(psEntry.getExemptAmount())
    //                        .subtract(psEntry.getActualReceivedAmount()));
    //            }
    //        }
    //        return sum;
    //    }
    //    
    //    /** 
    //     * 根据传入的期数获取在此期数之前的所有还款计划包括NA期<br/>
    //     * @param list
    //     * @param period
    //     * @return [参数说明]
    //     * 
    //     * @return List<PaymentSchedule> [返回类型说明]
    //     * @exception throws [异常类型] [异常说明]
    //     * @see [类、类#方法、类#成员]
    //     */
    //    public static List<PaymentScheduleEntry> getEarlySettleExemptAblePaymentScheduleEntryList(
    //            Map<FeeItemEnum, LoanAccountFeeItem> feeCfgItemMap, Map<String, PaymentSchedule> period2PaymentScheduleMap,
    //            Date repayDate, boolean isIrr, Collection<FeeItemEnum> feeItemCollections) {
    //        AssertUtils.notEmpty(period2PaymentScheduleMap, "paymentScheduleMap is empty.");
    //        AssertUtils.notEmpty(feeCfgItemMap, "feeCfgItemMap is empty.");
    //        AssertUtils.notEmpty(repayDate, "repayDate is empty.");
    //        
    //        //获取还款日对应的应收期数
    //        String period = PaymentScheduleHelper.getReceivablePeriodOfRepayDate(period2PaymentScheduleMap, repayDate);
    //        // 过滤期数 获取传入期数之前的的期数、包括NA 期
    //        List<PaymentSchedule> paymentScheduleListForExempt = new ArrayList<>();
    //        if (NumberUtils.isDigits(period)) {
    //            String prePeriod = period;
    //            while (!StringUtils.isBlank(prePeriod)) {
    //                PaymentSchedule psTemp = period2PaymentScheduleMap.get(prePeriod);
    //                paymentScheduleListForExempt.add(psTemp);
    //                prePeriod = psTemp.getPrePeriod();
    //            }
    //        }
    //        paymentScheduleListForExempt.add(period2PaymentScheduleMap.get(LoanAccountConstants.PERIOD_NA));
    //        
    //        //用于封装所有可分配的分项
    //        List<PaymentScheduleEntry> resList = new ArrayList<>(TxConstants.INITIAL_CONLLECTION_SIZE);
    //        for (PaymentSchedule paymentSchedule : paymentScheduleListForExempt) {
    //            // 取出当前期的entryList 
    //            List<PaymentScheduleEntry> entryList = paymentSchedule.getPaymentScheduleEntryList();
    //            //根据是否逾期依赖取出所有可分配的费用项
    //            for (PaymentScheduleEntry entryTemp : entryList) {
    //                FeeItemEnum feeItemTemp = entryTemp.getFeeItem();
    //                LoanAccountFeeItem cfgItem = feeCfgItemMap.get(feeItemTemp);
    //                if (!cfgItem.isExemptAble() || cfgItem.isPrincipal()) {
    //                    //如果不可以被豁免或为本金则跳过，不参与豁免分配
    //                    continue;
    //                }
    //                if (feeItemCollections != null && !feeItemCollections.contains(feeItemTemp)) {
    //                    continue;
    //                }
    //                BigDecimal checkAmount = BigDecimal.ZERO;
    //                if (isIrr) {
    //                    checkAmount = checkAmount.add(entryTemp.getReceivableAmountIrr())
    //                            .add(entryTemp.getExemptAmountIrr())
    //                            .subtract(entryTemp.getActualReceivedAmountIrr());
    //                } else {
    //                    checkAmount = checkAmount.add(entryTemp.getReceivableAmount())
    //                            .add(entryTemp.getExemptAmount())
    //                            .subtract(entryTemp.getActualReceivedAmount());
    //                }
    //                //检查金额，可分配金额大于0的压入列表
    //                if (checkAmount.compareTo(BigDecimal.ZERO) > 0) {
    //                    resList.add(entryTemp);
    //                }
    //            }
    //        }
    //        return resList;
    //    }
}
