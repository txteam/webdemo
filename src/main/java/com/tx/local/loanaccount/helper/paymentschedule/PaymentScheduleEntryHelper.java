/*
 * 描          述:  <描述>
 * 修  改   人:  Administrator
 * 修改时间:  2014年6月11日
 * <修改描述:>
 */
package com.tx.local.loanaccount.helper.paymentschedule;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.util.MultiValueMap;

import com.tx.core.exceptions.util.AssertUtils;
import com.tx.local.basicdata.model.FeeItemCfg;
import com.tx.local.basicdata.model.FeeItemEnum;
import com.tx.local.loanaccount.model.PaymentSchedule;
import com.tx.local.loanaccount.model.PaymentScheduleEntry;
import com.tx.local.loanaccount.model.ScheduleAssociateMap;
import com.tx.local.loanaccount.model.ScheduleEntryAssociateMap;
import com.tx.local.loanaccount.model.ScheduleTypeEnum;

/**
 * 还款计划分项帮助类<br/>
 * 
 * @author  Administrator
 * @version  [版本号, 2014年6月11日]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public class PaymentScheduleEntryHelper {
    
    /**
     * 计算逾期总额<br/>
     * <功能详细描述>
     * @param paymentScheduleMap
     * @param paymentScheduleEntryMap
     * @param scheduleType
     * @param repayDate
     * @return [参数说明]
     * 
     * @return BigDecimal [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    public static BigDecimal calculateOverdueSum(ScheduleAssociateMap<PaymentSchedule> paymentScheduleMap,
            ScheduleEntryAssociateMap<PaymentScheduleEntry> paymentScheduleEntryMap, ScheduleTypeEnum scheduleType,
            Date repayDate) {
        BigDecimal overdueSum = calculateOverdueSum(paymentScheduleMap,
                paymentScheduleEntryMap,
                scheduleType,
                repayDate,
                null);
        return overdueSum;
    }
    
    /**
     * 计算逾期总额<br/>
     * <功能详细描述>
     * @param paymentScheduleMap
     * @param paymentScheduleEntryMap
     * @param scheduleType
     * @param repayDate
     * @param feeItems
     * @return [参数说明]
     * 
     * @return BigDecimal [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    public static BigDecimal calculateOverdueSum(ScheduleAssociateMap<PaymentSchedule> paymentScheduleMap,
            ScheduleEntryAssociateMap<PaymentScheduleEntry> paymentScheduleEntryMap, ScheduleTypeEnum scheduleType,
            Date repayDate, Collection<FeeItemEnum> feeItems) {
        AssertUtils.notNull(paymentScheduleEntryMap, "paymentScheduleEntryMap is null.");
        AssertUtils.notNull(scheduleType, "scheduleType is null.");
        AssertUtils.notNull(paymentScheduleEntryMap.get(scheduleType),
                "paymentScheduleEntryMap[scheduleType] is null.");
        AssertUtils.notNull(repayDate, "repayDate is null.");
        
        List<PaymentScheduleEntry> expiredPSEs = new ArrayList<>();
        Map<String, PaymentSchedule> psMap = paymentScheduleMap.get(scheduleType);
        Map<String, Map<FeeItemEnum, PaymentScheduleEntry>> pseMap = paymentScheduleEntryMap.get(scheduleType);
        for (PaymentSchedule psTemp : psMap.values()) {
            if (!PaymentScheduleHelper.isExpired(psTemp, repayDate)) {
                continue;
            }
            expiredPSEs.addAll(pseMap.get(psTemp.getPeriod()).values());
        }
        
        BigDecimal total = BigDecimal.ZERO;
        for (PaymentScheduleEntry paymentScheduleEntry : expiredPSEs) {
            if (!CollectionUtils.isEmpty(feeItems) && !feeItems.contains(paymentScheduleEntry.getFeeItem())) {
                continue;
            }
            BigDecimal receivableAmount = paymentScheduleEntry.getReceivableAmount();
            BigDecimal exemptAmount = paymentScheduleEntry.getExemptAmount();
            BigDecimal actualReceivedAmount = paymentScheduleEntry.getActualReceivedAmount();
            BigDecimal subTotal = receivableAmount.add(exemptAmount).subtract(actualReceivedAmount);
            total = total.add(subTotal);
        }
        
        return total;
    }
    
    /**
     * 计算逾期利息<br/>
     * <功能详细描述>
     * @param feeItemCfgMap
     * @param paymentScheduleMap
     * @param paymentScheduleEntryMap
     * @param scheduleType
     * @param repayDate
     * @return [参数说明]
     * 
     * @return BigDecimal [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    public static BigDecimal calculateOverdueAmount(Map<FeeItemEnum, FeeItemCfg> feeItemCfgMap,
            ScheduleAssociateMap<PaymentSchedule> paymentScheduleMap,
            ScheduleEntryAssociateMap<PaymentScheduleEntry> paymentScheduleEntryMap, ScheduleTypeEnum scheduleType,
            Date repayDate) {
        BigDecimal overdueAmount = calculateOverdueAmount(feeItemCfgMap,
                paymentScheduleMap,
                paymentScheduleEntryMap,
                scheduleType,
                repayDate,
                null);
        
        return overdueAmount;
    }
    
    /**
      * 计算逾期金额<br/>
      * <功能详细描述>
      * @param feeItemCfgMap
      * @param paymentScheduleMap
      * @param paymentScheduleEntryMap
      * @param scheduleType
      * @param repayDate
      * @param feeItems
      * @return [参数说明]
      * 
      * @return BigDecimal [返回类型说明]
      * @exception throws [异常类型] [异常说明]
      * @see [类、类#方法、类#成员]
     */
    public static BigDecimal calculateOverdueAmount(Map<FeeItemEnum, FeeItemCfg> feeItemCfgMap,
            ScheduleAssociateMap<PaymentSchedule> paymentScheduleMap,
            ScheduleEntryAssociateMap<PaymentScheduleEntry> paymentScheduleEntryMap, ScheduleTypeEnum scheduleType,
            Date repayDate, Collection<FeeItemEnum> feeItems) {
        AssertUtils.notNull(paymentScheduleEntryMap, "paymentScheduleEntryMap is null.");
        AssertUtils.notNull(scheduleType, "scheduleType is null.");
        AssertUtils.notNull(paymentScheduleEntryMap.get(scheduleType),
                "paymentScheduleEntryMap[scheduleType] is null.");
        AssertUtils.notNull(repayDate, "repayDate is null.");
        
        List<PaymentScheduleEntry> expiredPSEs = new ArrayList<>();
        Map<String, PaymentSchedule> psMap = paymentScheduleMap.get(scheduleType);
        Map<String, Map<FeeItemEnum, PaymentScheduleEntry>> pseMap = paymentScheduleEntryMap.get(scheduleType);
        for (PaymentSchedule psTemp : psMap.values()) {
            if (!PaymentScheduleHelper.isExpired(psTemp, repayDate)) {
                continue;
            }
            expiredPSEs.addAll(pseMap.get(psTemp.getPeriod()).values());
        }
        
        BigDecimal total = BigDecimal.ZERO;
        for (PaymentScheduleEntry paymentScheduleEntry : expiredPSEs) {
            FeeItemEnum feeItem = paymentScheduleEntry.getFeeItem();
            AssertUtils.isTrue(feeItemCfgMap.containsKey(feeItem),
                    "feeItemCfgMap not contains feeItem:[{}]",
                    new Object[] { feeItem });
            FeeItemCfg feeItemCfg = feeItemCfgMap.get(feeItem);
            if (!feeItemCfg.isOverdueDepend()) {
                continue;
            }
            if (!CollectionUtils.isEmpty(feeItems) && !feeItems.contains(feeItem)) {
                continue;
            }
            
            BigDecimal receivableAmount = paymentScheduleEntry.getReceivableAmount();
            BigDecimal exemptAmount = paymentScheduleEntry.getExemptAmount();
            BigDecimal actualReceivedAmount = paymentScheduleEntry.getActualReceivedAmount();
            BigDecimal subTotal = receivableAmount.add(exemptAmount).subtract(actualReceivedAmount);
            total = total.add(subTotal);
        }
        
        return total;
    }
    
    /**
      * 是否正常结清
      * <功能详细描述>
      * @param multiValueEntryMap
      * @return [参数说明]
      * 
      * @return boolean [返回类型说明]
      * @exception throws [异常类型] [异常说明]
      * @see [类、类#方法、类#成员]
     */
    public static boolean isFP(MultiValueMap<FeeItemEnum, PaymentScheduleEntry> paymentScheduleEntryMap) {
        BigDecimal total = BigDecimal.ZERO;
        for (Entry<FeeItemEnum, List<PaymentScheduleEntry>> entry : paymentScheduleEntryMap.entrySet()) {
            for (PaymentScheduleEntry paymentScheduleEntry : entry.getValue()) {
                BigDecimal receivableAmount = paymentScheduleEntry.getReceivableAmount();
                BigDecimal exemptAmount = paymentScheduleEntry.getExemptAmount();
                BigDecimal actualReceivedAmount = paymentScheduleEntry.getActualReceivedAmount();
                BigDecimal subTotal = receivableAmount.add(exemptAmount).subtract(actualReceivedAmount);
                total = total.add(subTotal);
            }
        }
        if (total.compareTo(BigDecimal.ZERO) == 0) {
            return true;
        }
        return false;
    }
    
    /**
      * 是否结清(仅逾期依赖的费用项结清)
      * <功能详细描述>
      * @param multiValueEntryMap
      * @return [参数说明]
      * 
      * @return boolean [返回类型说明]
      * @exception throws [异常类型] [异常说明]
      * @see [类、类#方法、类#成员]
     */
    public static boolean isSL(Map<FeeItemEnum, FeeItemCfg> feeItemCfgMap,
            MultiValueMap<FeeItemEnum, PaymentScheduleEntry> paymentScheduleEntryMap) {
        BigDecimal total = BigDecimal.ZERO;
        for (Entry<FeeItemEnum, List<PaymentScheduleEntry>> entry : paymentScheduleEntryMap.entrySet()) {
            AssertUtils.isTrue(feeItemCfgMap.containsKey(entry.getKey()),
                    "feeItemCfgMap not contains feeItem:[{}]",
                    new Object[] { entry.getKey() });
            FeeItemCfg feeCfgItem = feeItemCfgMap.get(entry.getKey());
            if (!feeCfgItem.isOverdueDepend()) {
                continue;
            }
            for (PaymentScheduleEntry paymentScheduleEntry : entry.getValue()) {
                BigDecimal receivableAmount = paymentScheduleEntry.getReceivableAmount();
                BigDecimal exemptAmount = paymentScheduleEntry.getExemptAmount();
                BigDecimal actualReceivedAmount = paymentScheduleEntry.getActualReceivedAmount();
                BigDecimal subTotal = receivableAmount.add(exemptAmount).subtract(actualReceivedAmount);
                total = total.add(subTotal);
            }
        }
        if (total.compareTo(BigDecimal.ZERO) == 0) {
            return true;
        }
        return false;
    }
    
    //    /**
    //      * 根据还款计划获取最大还款金额
    //      * <功能详细描述>
    //      * @param paymentScheduleMap
    //      * @return [参数说明]
    //      * 
    //      * @return BigDecimal [返回类型说明]
    //      * @exception throws [异常类型] [异常说明]
    //      * @see [类、类#方法、类#成员]
    //     */
    //    public static BigDecimal getMaxRepaySum(
    //            Map<String, PaymentSchedule> paymentScheduleMap) {
    //        BigDecimal sum = BigDecimal.ZERO;
    //        for (PaymentSchedule ps : paymentScheduleMap.values()) {
    //            sum = sum.add(ps.getReceivableSum()
    //                    .add(ps.getExemptSum())
    //                    .subtract(ps.getActualReceivedSum()));
    //        }
    //        return sum;
    //    }
    
    //    /**
    //      * 获取最大每月还款金额总额<br/>
    //      *<功能详细描述>
    //      * @param paymentScheduleMap
    //      * @return [参数说明]
    //      * 
    //      * @return BigDecimal [返回类型说明]
    //      * @exception throws [异常类型] [异常说明]
    //      * @see [类、类#方法、类#成员]
    //     */
    //    public static BigDecimal getMonthlyRepaySum(
    //            Map<FeeItemEnum, LoanAccountFeeCfgItem> feeCfgItemMap,
    //            Map<String, PaymentSchedule> paymentScheduleMap) {
    //        BigDecimal sum = BigDecimal.ZERO;
    //        for (PaymentSchedule ps : paymentScheduleMap.values()) {
    //            for (PaymentScheduleEntry entry : ps.getPaymentScheduleEntryList()) {
    //                if (!feeCfgItemMap.get(entry.getFeeItem()).isOverdueDepend()) {
    //                    continue;
    //                }
    //                sum = sum.add(entry.getReceivableAmount()
    //                        .add(entry.getExemptAmount())
    //                        .subtract(entry.getActualReceivedAmount()));
    //            }
    //        }
    //        return sum;
    //    }
    //    /**
    //     * 根据还款计划获取最大还款金额
    //     * <功能详细描述>
    //     * @param paymentScheduleMap
    //     * @return [参数说明]
    //     * 
    //     * @return BigDecimal [返回类型说明]
    //     * @exception throws [异常类型] [异常说明]
    //     * @see [类、类#方法、类#成员]
    //    */
    //    public static Set<FeeItemEnum> getMonthlyOverdueDependFeeItemSet(
    //            LoanAccount loanAccount,
    //            Map<String, PaymentSchedule> paymentScheduleMap) {
    //        AssertUtils.notNull(loanAccount, "loanAccount is null.");
    //        AssertUtils.notEmpty(loanAccount.getFeeCfgItemList(),
    //                "loanAccount.feeCfgItemList is empty.");
    //        Set<FeeItemEnum> feeItemSet = new HashSet<>(
    //                TxConstants.INITIAL_CONLLECTION_SIZE);
    //        //可能参与分配的还款计划
    //        String nextPeriod = "1";
    //        while (StringUtils.isNotBlank(nextPeriod)) {
    //            PaymentSchedule ps = paymentScheduleMap.get(nextPeriod);
    //            //地阿呆获取存在于每期的费用项集合
    //            for (PaymentScheduleEntry entry : ps.getPaymentScheduleEntryList()) {
    //                FeeItemEnum feeItem = entry.getFeeItem();
    //                if (!loanAccount.getFeeCfgItemMapping()
    //                        .get(feeItem)
    //                        .isPrincipal()) {
    //                    continue;
    //                }
    //                feeItemSet.add(entry.getFeeItem());
    //            }
    //            nextPeriod = ps.getNextPeriod();
    //        }
    //        return feeItemSet;
    //    }
    //    /**
    //      * 过滤取出每月的还款尚未结清的分项<br/>
    //      * <功能详细描述>
    //      * @param paymentScheduleList
    //      * @return [参数说明]
    //      * 
    //      * @return List<PaymentSchedule> [返回类型说明]
    //      * @exception throws [异常类型] [异常说明]
    //      * @see [类、类#方法、类#成员]
    //     */
    //    public static List<PaymentSchedule> getMonthlyPamentScheduleList(
    //            Map<String, PaymentSchedule> paymentScheduleMap) {
    //        String nextPeriod = "1";
    //        //可能参与分配的还款计划
    //        List<PaymentSchedule> monthlyPaymentScheduleList = new ArrayList<>(
    //                TxConstants.INITIAL_CONLLECTION_SIZE);
    //        while (StringUtils.isNotBlank(nextPeriod)) {
    //            PaymentSchedule ps = paymentScheduleMap.get(nextPeriod);
    //            
    //            monthlyPaymentScheduleList.add(ps);
    //            nextPeriod = ps.getNextPeriod();
    //        }
    //        return monthlyPaymentScheduleList;
    //    }
    //    /**
    //      * 根据传入条件获取可分配金额的费用分项列表
    //      * <功能详细描述>
    //      * @param paymentScheduleMap
    //      * @param isIrr
    //      * @param feeItemCollection
    //      * @return [参数说明]
    //      * 
    //      * @return List<PaymentScheduleEntry> [返回类型说明]
    //      * @exception throws [异常类型] [异常说明]
    //      * @see [类、类#方法、类#成员]
    //     */
    //    public static List<PaymentScheduleEntry> getAssignAblePamentScheduleEntryList(
    //            Map<String, PaymentSchedule> paymentScheduleMap, boolean isIrr,
    //            Collection<FeeItemEnum> feeItemCollection) {
    //        //还款计划分项列表
    //        List<PaymentScheduleEntry> resList = new ArrayList<>(
    //                TxConstants.INITIAL_CONLLECTION_SIZE);
    //        //还款计划项
    //        for (PaymentSchedule ps : paymentScheduleMap.values()) {
    //            for (PaymentScheduleEntry entry : ps.getPaymentScheduleEntryList()) {
    //                BigDecimal checkAmount = BigDecimal.ZERO;
    //                FeeItemEnum feeItemTemp = entry.getFeeItem();
    //                //如果传入了指定费用项，则将非指定费用项目的还款计划分项跳过
    //                if (!CollectionUtils.isEmpty(feeItemCollection)
    //                        && !feeItemCollection.contains(feeItemTemp)) {
    //                    continue;
    //                }
    //                if (isIrr) {
    //                    checkAmount = checkAmount.add(entry.getReceivableAmountIrr())
    //                            .add(entry.getExemptAmountIrr())
    //                            .subtract(entry.getActualReceivedAmountIrr());
    //                } else {
    //                    checkAmount = checkAmount.add(entry.getReceivableAmount())
    //                            .add(entry.getExemptAmount())
    //                            .subtract(entry.getActualReceivedAmount());
    //                }
    //                //检查金额，可分配金额大于0的压入列表
    //                if (checkAmount.compareTo(BigDecimal.ZERO) > 0) {
    //                    resList.add(entry);
    //                }
    //            }
    //        }
    //        return resList;
    //    }
    //    /**
    //      * 获取未结清的还款计划分项
    //      *<功能详细描述>
    //      * @param paymentScheduleMap
    //      * @return [参数说明]
    //      * 
    //      * @return List<PaymentScheduleEntry> [返回类型说明]
    //      * @exception throws [异常类型] [异常说明]
    //      * @see [类、类#方法、类#成员]
    //     */
    //    public static List<PaymentScheduleEntry> getAssignAbleMonthlyPaymentScheduleEntryMap(
    //            Map<String, PaymentSchedule> paymentScheduleMap, boolean isIrr,
    //            Collection<FeeItemEnum> feeItemCollection) {
    //        //获取月还款计划项
    //        List<PaymentSchedule> paymentScheduleList = getMonthlyPamentScheduleList(paymentScheduleMap);
    //        //还款计划分项列表
    //        List<PaymentScheduleEntry> resList = new ArrayList<>(
    //                TxConstants.INITIAL_CONLLECTION_SIZE);
    //        for (PaymentSchedule ps : paymentScheduleList) {
    //            for (PaymentScheduleEntry entry : ps.getPaymentScheduleEntryList()) {
    //                BigDecimal checkAmount = BigDecimal.ZERO;
    //                FeeItemEnum feeItemTemp = entry.getFeeItem();
    //                //如果传入了指定费用项，则将非指定费用项目的还款计划分项跳过
    //                if (!CollectionUtils.isEmpty(feeItemCollection)
    //                        && feeItemCollection.contains(feeItemTemp)) {
    //                    continue;
    //                }
    //                
    //                if (isIrr) {
    //                    checkAmount = checkAmount.add(entry.getReceivableAmountIrr())
    //                            .add(entry.getExemptAmountIrr())
    //                            .subtract(entry.getActualReceivedAmountIrr());
    //                } else {
    //                    checkAmount = checkAmount.add(entry.getReceivableAmount())
    //                            .add(entry.getExemptAmount())
    //                            .subtract(entry.getActualReceivedAmount());
    //                }
    //                //检查金额
    //                if (checkAmount.compareTo(BigDecimal.ZERO) > 0) {
    //                    resList.add(entry);
    //                }
    //            }
    //        }
    //        return resList;
    //    }
    //    /**
    //     * 构建费用项对期数的还款计划分项的映射
    //     *<功能详细描述>
    //     * @param paymentScheduleMap
    //     * @return [参数说明]
    //     * 
    //     * @return Map<FeeItemEnum,Map<String,PaymentScheduleEntry>> [返回类型说明]
    //     * @exception throws [异常类型] [异常说明]
    //     * @see [类、类#方法、类#成员]
    //    */
    //    public static Map<FeeItemEnum, Map<String, PaymentScheduleEntry>> getPaymentScheduleFeeItem2PeriodMap(
    //            Map<String, PaymentSchedule> paymentScheduleMap) {
    //        AssertUtils.notEmpty(paymentScheduleMap, "paymentScheduleMap is empty.");
    //        
    //        Map<FeeItemEnum, Map<String, PaymentScheduleEntry>> resMap = new HashMap<>(
    //                TxConstants.INITIAL_MAP_SIZE);
    //        for (PaymentSchedule ps : paymentScheduleMap.values()) {
    //            if (CollectionUtils.isEmpty(ps.getPaymentScheduleEntryList())) {
    //                continue;
    //            }
    //            for (PaymentScheduleEntry entry : ps.getPaymentScheduleEntryList()) {
    //                if (!resMap.containsKey(entry.getFeeItem())) {
    //                    resMap.put(entry.getFeeItem(),
    //                            new HashMap<String, PaymentScheduleEntry>(
    //                                    TxConstants.INITIAL_MAP_SIZE));
    //                }
    //                resMap.get(entry.getFeeItem()).put(entry.getPeriod(), entry);
    //            }
    //        }
    //        return resMap;
    //    }
    
    public static final Comparator<PaymentScheduleEntry> periodComparator = new Comparator<PaymentScheduleEntry>() {
        @Override
        public int compare(PaymentScheduleEntry o1, PaymentScheduleEntry o2) {
            if (NumberUtils.isDigits(o1.getPeriod()) && NumberUtils.isDigits(o2.getPeriod())) {
                Integer o1Period = new Integer(o1.getPeriod());
                Integer o2Period = new Integer(o2.getPeriod());
                return o1Period.compareTo(o2Period);
            } else if (!NumberUtils.isDigits(o1.getPeriod()) && !NumberUtils.isDigits(o2.getPeriod())) {
                return o1.getPeriod().compareTo(o2.getPeriod());
            } else if (NumberUtils.isDigits(o1.getPeriod())) {
                return -1;
            } else {
                return 1;
            }
        }
    };
    
    public static void main(String[] args) {
        Map<String, Integer> test = new HashMap<String, Integer>();
        test.put("1", 1);
        test.put("2", 2);
        test.put("3", 3);
        
        List<Entry<String, Integer>> entryList = new ArrayList<>(test.entrySet());
        
        entryList.get(0).setValue(10);
        
        for (Entry<String, Integer> entry : test.entrySet()) {
            System.out.println(entry.getKey() + " " + entry.getValue());
        }
    }
}
