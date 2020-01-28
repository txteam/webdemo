/*
 * 描          述:  <描述>
 * 修  改   人:  Administrator
 * 修改时间:  2017年6月20日
 * <修改描述:>
 */
package com.tx.local.loanaccount.helper.repayallocator.settle;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import com.tx.core.exceptions.util.AssertUtils;
import com.tx.local.basicdata.model.FeeItemCfg;
import com.tx.local.basicdata.model.FeeItemEnum;
import com.tx.local.loanaccount.handle.PaymentScheduleHandler;
import com.tx.local.loanaccount.helper.paymentschedule.PaymentScheduleHelper;
import com.tx.local.loanaccount.model.AssignEntryComparable;
import com.tx.local.loanaccount.model.LoanAccount;
import com.tx.local.loanaccount.model.PaymentSchedule;
import com.tx.local.loanaccount.model.PaymentScheduleEntry;
import com.tx.local.loanaccount.model.ScheduleAssociateMap;
import com.tx.local.loanaccount.model.ScheduleEntryAssociateMap;
import com.tx.local.loanaccount.model.ScheduleTypeEnum;
import com.tx.local.loanaccount.model.SettleInterestStatusEnum;

/**
 * 还款分配调制器<br/>
 * <功能详细描述>
 * 
 * @author  Administrator
 * @version  [版本号, 2017年6月20日]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public class SettleAssignAdjustor {
    
    //贷款账户
    private LoanAccount loanAccount;
    
    //费用项配置
    private Map<FeeItemEnum, FeeItemCfg> feeItemCfgMap;
    
    //还款计划处理器
    private PaymentScheduleHandler paymentScheduleHandler;
    
    //是否递减分配
    private final ScheduleTypeEnum scheduleType;
    
    //还款日期
    private final Date repayDate;
    
    //应收期数
    private final int receivablePeriod;
    
    //比较器
    private final Comparator<AssignEntryComparable> comparator;
    
    //参与分配的费用集合
    private final Collection<FeeItemEnum> feeItemCollection;
    
    //分配映射
    private final Map<String, Map<FeeItemEnum, BigDecimal>> assignMap;
    
    //分配项分组列表
    private final List<AssignItemGroup> assignItemGroupList;
    
    /** <默认构造函数> */
    public SettleAssignAdjustor(LoanAccount loanAccount,
            Map<FeeItemEnum, FeeItemCfg> feeItemCfgMap,
            PaymentScheduleHandler paymentScheduleHandler,
            ScheduleTypeEnum scheduleType, Date repayDate,
            Comparator<AssignEntryComparable> comparator,
            Collection<FeeItemEnum> feeItemCollection,
            Map<String, Map<FeeItemEnum, BigDecimal>> assignMap) {
        super();
        AssertUtils.notNull(loanAccount, "loanAccount is null.");
        AssertUtils.notEmpty(feeItemCfgMap, "feeItemCfgMap is empty.");
        AssertUtils.notNull(paymentScheduleHandler,
                "paymentScheduleHandler is null.");
        AssertUtils.notNull(scheduleType, "scheduleType is null.");
        AssertUtils.notNull(repayDate, "repayDate is null.");
        AssertUtils.notNull(comparator, "comparator is null.");
        
        AssertUtils.notEmpty(feeItemCollection, "feeItemCollection is empty.");
        AssertUtils.notNull(assignMap, "assignMap is null.");
        
        this.loanAccount = loanAccount;
        this.repayDate = repayDate;
        this.paymentScheduleHandler = paymentScheduleHandler;
        this.scheduleType = scheduleType;
        this.assignMap = assignMap;
        this.comparator = comparator;
        this.feeItemCollection = feeItemCollection;
        
        ScheduleAssociateMap<PaymentSchedule> paymentScheduleMap = this.paymentScheduleHandler
                .getPaymentScheduleMap();
        String waitReceivePeriodString = PaymentScheduleHelper
                .getWaitReceivePeriod(paymentScheduleMap, this.scheduleType);
        String receivablePeriodString = PaymentScheduleHelper
                .getReceivablePeriod(paymentScheduleMap,
                        this.scheduleType,
                        this.repayDate);
        AssertUtils.notNull(waitReceivePeriodString, "待收期数不能为空.");
        AssertUtils.notNull(receivablePeriodString, "应收期数不能为空.");
        this.receivablePeriod = NumberUtils.toInt(receivablePeriodString);
        
        //可调整还款计划分项集合
        List<PaymentScheduleEntry> adjustAblePaymentScheduleEntryList = new ArrayList<>();
        for (PaymentScheduleEntry pse : this.paymentScheduleHandler
                .getPaymentScheduleEntryMap().valueList(scheduleType)) {
            String period = pse.getPeriod();
            if (!NumberUtils.isDigits(period)) {
                continue;
            }
            //本金不参与调整
            if (!feeItemCollection.contains(pse.getFeeItem())
                    || this.feeItemCfgMap.get(pse.getFeeItem()).isPrincipal()) {
                continue;
            }
            adjustAblePaymentScheduleEntryList.add(pse);
        }
        Collections.sort(adjustAblePaymentScheduleEntryList, comparator);
        
        //初始化分配项，以及分配项目分组
        for (PaymentScheduleEntry pseTemp : adjustAblePaymentScheduleEntryList) {
            if (feeItemCollection.contains(pseTemp.getFeeItem())) {
                continue;
            }
            if (!assignMap.containsKey(pseTemp.getPeriod())) {
                assignMap.put(pseTemp.getPeriod(), new HashMap<>());
            }
            if (!assignMap.get(pseTemp.getPeriod())
                    .containsKey(pseTemp.getFeeItem())) {
                assignMap.get(pseTemp.getPeriod()).put(pseTemp.getFeeItem(),
                        BigDecimal.ZERO);
            }
        }
        
        //还款计划分项
        ScheduleEntryAssociateMap<PaymentScheduleEntry> pseMap = new ScheduleEntryAssociateMap<>(
                adjustAblePaymentScheduleEntryList);
        Comparator<AssignEntryComparable> assignItemComparator = this.comparator;
        
        //获取与期数的映射
        MultiValueMap<Integer, AssignItem> assignItemMMap = new LinkedMultiValueMap<>();
        for (Entry<String, Map<FeeItemEnum, BigDecimal>> entryTemp : assignMap
                .entrySet()) {
            String periodStringTemp = entryTemp.getKey();
            if (!NumberUtils.isDigits(periodStringTemp)) {
                continue;
            }
            
            int periodWeightTemp = getPeriodWeight(periodStringTemp);
            for (Entry<FeeItemEnum, BigDecimal> subEntryTemp : entryTemp
                    .getValue().entrySet()) {
                //本金不参与调整
                if (!this.feeItemCollection.contains(subEntryTemp.getKey())
                        || this.feeItemCfgMap.get(subEntryTemp.getKey())
                                .isPrincipal()) {
                    continue;
                }
                PaymentScheduleEntry pse = pseMap.get(scheduleType,
                        periodStringTemp,
                        subEntryTemp.getKey());
                AssertUtils.notNull(pse,
                        "pse not exist.scheduleType:{} period:{} feeItem:{}",
                        new Object[] { scheduleType, periodStringTemp,
                                subEntryTemp.getKey() });
                //仅将integer类型的值进行压堆
                assignItemMMap.add(periodWeightTemp,
                        new AssignItem(periodStringTemp, periodWeightTemp, pse,
                                subEntryTemp));
            }
        }
        
        //构建分配项目分组
        this.assignItemGroupList = new ArrayList<>();
        for (Entry<Integer, List<AssignItem>> entryTemp : assignItemMMap
                .entrySet()) {
            List<AssignItem> aiList = entryTemp.getValue();
            if (CollectionUtils.isEmpty(aiList)) {
                continue;
            }
            
            Collections.sort(aiList, assignItemComparator);
            AssignItemGroup aigTemp = new AssignItemGroup(entryTemp.getKey(),
                    aiList);
            this.assignItemGroupList.add(aigTemp);
        }
        //对分配项分组进行排序
        Collections.sort(this.assignItemGroupList, assignItemGroupComparator);
    }
    
    /**
     * 调整分配<br/>
     * <功能详细描述> [参数说明]
     * 
     * @return void [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    public void adjust() {
        if (this.paymentScheduleHandler.isSLSettle()) {
            //当逾期依赖项均已结清的时候已经不用进行分配调整了
            return;
        }
        if (this.assignItemGroupList.size() <= 1) {
            //如果分配项分组<=1 不需要进行调整分配了.
            return;
        }
        //分配项分组列表
        for (int i = 0; i < this.assignItemGroupList.size() - 1; i++) {
            AssignItemGroup aigTemp = this.assignItemGroupList.get(i);
            List<AssignItemGroup> subAigList = this.assignItemGroupList
                    .subList(i, assignItemGroupList.size());
            
            BigDecimal lendAbleSum = lendAbleSum(subAigList);
            AssertUtils.isTrue(lendAbleSum.compareTo(BigDecimal.ZERO) >= 0,
                    "lendAbleSum:{} should >= 0.",
                    new Object[] { lendAbleSum });
            if (lendAbleSum.compareTo(BigDecimal.ZERO) == 0) {
                break;
            }
            
            for (AssignItem aiTemp : aigTemp.getAssignItemList()) {
                BigDecimal assignAbleAmount = aiTemp.getAssignAbleAmount();
                AssertUtils.isTrue(
                        assignAbleAmount.compareTo(BigDecimal.ZERO) >= 0,
                        "assignAbleAmount:{} should >= 0.",
                        new Object[] { assignAbleAmount });
                if (assignAbleAmount.compareTo(BigDecimal.ZERO) == 0) {
                    continue;
                }
                lendAbleSum = lendAbleSum(subAigList);
                AssertUtils.isTrue(lendAbleSum.compareTo(BigDecimal.ZERO) >= 0,
                        "lendAbleSum:{} should >= 0.",
                        new Object[] { lendAbleSum });
                if (lendAbleSum.compareTo(BigDecimal.ZERO) == 0) {
                    break;
                }
                
                //当子组可借出金额 > 0时
                doAdjust(assignAbleAmount, subAigList);
            }
            lendAbleSum = lendAbleSum(subAigList);
            AssertUtils.isTrue(lendAbleSum.compareTo(BigDecimal.ZERO) >= 0,
                    "lendAbleSum:{} should >= 0.",
                    new Object[] { lendAbleSum });
            if (lendAbleSum.compareTo(BigDecimal.ZERO) == 0) {
                break;
            }
        }
    }
    
    /**
     * 调整分配<br/>
     * <功能详细描述>
     * @param assignAbleAmount
     * @param subAigList [参数说明]
     * 
     * @return void [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    private void doAdjust(BigDecimal assignAbleAmount,
            List<AssignItemGroup> subAigList) {
        BigDecimal assignAbleAmountBalance = assignAbleAmount;
        for (int i = subAigList.size() - 1; i >= 0; i--) {
            AssignItemGroup aigTemp = subAigList.get(i);
            if (aigTemp.getLendAbleSum().compareTo(BigDecimal.ZERO) <= 0) {
                continue;
            }
            
            for (int j = aigTemp.getAssignItemList().size() - 1; j >= 0; j--) {
                AssignItem aiTemp = aigTemp.getAssignItemList().get(j);
                
                if (aiTemp.getLendAbleAmount()
                        .compareTo(BigDecimal.ZERO) <= 0) {
                    continue;
                }
                BigDecimal lendAmount = BigDecimal.ZERO;
                if (assignAbleAmountBalance
                        .compareTo(aiTemp.getLendAbleAmount()) > 0) {
                    lendAmount = aiTemp.getLendAbleAmount();
                } else {
                    lendAmount = assignAbleAmountBalance;
                }
                
                assignAbleAmountBalance = assignAbleAmountBalance
                        .subtract(lendAmount);
                aiTemp.lendAmount(lendAmount);
                
                if (assignAbleAmountBalance.compareTo(BigDecimal.ZERO) <= 0) {
                    break;
                }
            }
            if (assignAbleAmountBalance.compareTo(BigDecimal.ZERO) <= 0) {
                break;
            }
        }
    }
    
    /**
     * 计算子分组可出借金额<br/>
     * <功能详细描述>
     * @param subAigList
     * @return [参数说明]
     * 
     * @return BigDecimal [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    private BigDecimal lendAbleSum(List<AssignItemGroup> subAigList) {
        BigDecimal lendAbleSum = BigDecimal.ZERO;
        if (CollectionUtils.isEmpty(subAigList)) {
            return lendAbleSum;
        }
        for (AssignItemGroup aigTemp : subAigList) {
            lendAbleSum = lendAbleSum.add(aigTemp.getLendAbleSum());
        }
        return lendAbleSum;
    }
    
    /**
     * @return 返回 assignMap
     */
    public Map<String, Map<FeeItemEnum, BigDecimal>> getAssignMap() {
        return assignMap;
    }
    
    /**
     * @return 返回 loanAccount
     */
    public LoanAccount getLoanAccount() {
        return loanAccount;
    }
    
    /**
     * @return 返回 feeItemCfgMap
     */
    public Map<FeeItemEnum, FeeItemCfg> getFeeItemCfgMap() {
        return feeItemCfgMap;
    }
    
    /**
     * @return 返回 paymentScheduleHandler
     */
    public PaymentScheduleHandler getPaymentScheduleHandler() {
        return paymentScheduleHandler;
    }
    
    /**
     * 期数权重<br/>
     * <功能详细描述>
     * @param period
     * @return [参数说明]
     * 
     * @return int [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
    */
    private int getPeriodWeight(String period) {
        AssertUtils.notEmpty(period, "period is empty.");
        AssertUtils.isTrue(NumberUtils.isDigits(period),
                "period should be disgists.period:",
                period);
        
        int periodWeight = 0;
        if (SettleInterestStatusEnum.SSI
                .equals(loanAccount.getSettleInterestStatus())) {
            if (NumberUtils.toInt(period) <= this.receivablePeriod) {
                periodWeight = this.receivablePeriod;
            } else {
                periodWeight = NumberUtils.toInt(period);
            }
        } else {
            periodWeight = NumberUtils.toInt(period);
        }
        return periodWeight;
    }
    
    /**
      * 存储分配项<br/>
      * <功能详细描述>
      * 
      * @author  Administrator
      * @version  [版本号, 2017年6月20日]
      * @see  [相关类/方法]
      * @since  [产品/模块版本]
     */
    public static class AssignItem implements AssignEntryComparable {
        
        /** 对应期数 */
        private final String period;
        
        /** 期数权重 */
        private final int periodWeight;
        
        /** 对应的费用项 */
        private final FeeItemEnum feeItem;
        
        /** 还款计划分项 */
        private final PaymentScheduleEntry paymentScheduleEntry;
        
        /** 分配映射 */
        private final Entry<FeeItemEnum, BigDecimal> assignEntry;
        
        /** <默认构造函数> */
        public AssignItem(String period, int periodWeight,
                PaymentScheduleEntry paymentScheduleEntry,
                Entry<FeeItemEnum, BigDecimal> assignEntry) {
            super();
            AssertUtils.notEmpty(period, "period is null.");
            AssertUtils.notNull(assignEntry, "assignEntry is null.");
            AssertUtils.notNull(assignEntry.getKey(),
                    "assignEntry.key is null.");
            AssertUtils.notNull(assignEntry.getValue(),
                    "assignEntry.value is null.");
            
            this.period = period;
            this.feeItem = assignEntry.getKey();
            this.assignEntry = assignEntry;
            this.paymentScheduleEntry = paymentScheduleEntry;
            this.periodWeight = periodWeight;
        }
        
        /**
         * @return 返回 paymentScheduleEntry
         */
        public PaymentScheduleEntry getPaymentScheduleEntry() {
            return paymentScheduleEntry;
        }
        
        /**
         * @return 返回 periodWeight
         */
        public int getPeriodWeight() {
            return periodWeight;
        }
        
        /**
         * @return 返回 period
         */
        public String getPeriod() {
            return period;
        }
        
        /**
         * @return 返回 feeItem
         */
        public FeeItemEnum getFeeItem() {
            return feeItem;
        }
        
        /**
         * @return 返回 assignMap
         */
        public Entry<FeeItemEnum, BigDecimal> getAssignEntry() {
            return assignEntry;
        }
        
        /**
          * 借出金额<br/>
          * <功能详细描述>
          * @param amount
          * @return [参数说明]
          * 
          * @return BigDecimal [返回类型说明]
          * @exception throws [异常类型] [异常说明]
          * @see [类、类#方法、类#成员]
         */
        public BigDecimal lendAmount(BigDecimal amount) {
            AssertUtils.notNull(amount, "amount is null.");
            AssertUtils.isTrue(
                    amount.compareTo(this.assignEntry.getValue()) <= 0,
                    "amount:{} should <= lendAbleAmount:{}",
                    new Object[] { amount, this.assignEntry.getValue() });
            
            return this.assignEntry.getValue();
        }
        
        /**
          * 借入金额<br/>
          * <功能详细描述>
          * @param amount [参数说明]
          * 
          * @return void [返回类型说明]
          * @exception throws [异常类型] [异常说明]
          * @see [类、类#方法、类#成员]
         */
        public void borrow(BigDecimal amount) {
            AssertUtils.notNull(amount, "amount is null.");
            AssertUtils.isTrue(amount.compareTo(BigDecimal.ZERO) > 0,
                    "amount should > 0.");
            BigDecimal assignAmount = this.assignEntry.getValue().add(amount);
            
            this.assignEntry.setValue(assignAmount);
        }
        
        /**
          * 获取对应的分配金额<br/>
          * <功能详细描述>
          * @return [参数说明]
          * 
          * @return BigDecimal [返回类型说明]
          * @exception throws [异常类型] [异常说明]
          * @see [类、类#方法、类#成员]
         */
        public BigDecimal getLendAbleAmount() {
            return this.assignEntry.getValue();
        }
        
        /**
          * 获取可分配金额
          * <功能详细描述>
          * @return [参数说明]
          * 
          * @return BigDecimal [返回类型说明]
          * @exception throws [异常类型] [异常说明]
          * @see [类、类#方法、类#成员]
         */
        public BigDecimal getAssignAbleAmount() {
            BigDecimal assignAbleAmount = this.paymentScheduleEntry
                    .getReceivableAmount()
                    .add(paymentScheduleEntry.getExemptAmount())
                    .subtract(paymentScheduleEntry.getActualReceivedAmount())
                    .subtract(this.assignEntry.getValue());
            AssertUtils.isTrue(assignAbleAmount.compareTo(BigDecimal.ZERO) >= 0,
                    "checkAmount should >= 0,checkAmount:{}",
                    new Object[] { assignAbleAmount });
            return assignAbleAmount;
        }
    }
    
    /**
     * 可分配项分组<br/>
     * <功能详细描述>
     * 
     * @author  Administrator
     * @version  [版本号, 2017年6月20日]
     * @see  [相关类/方法]
     * @since  [产品/模块版本]
    */
    public static class AssignItemGroup {
        
        /** 期数权重 */
        private final int periodWeight;
        
        /** 可分配项列表 */
        private final List<AssignItem> assignItemList;
        
        /** <默认构造函数> */
        public AssignItemGroup(int periodWeight,
                List<AssignItem> assignItemList) {
            super();
            
            AssertUtils.notEmpty(assignItemList, "assignItemList is null.");
            
            this.periodWeight = periodWeight;
            this.assignItemList = assignItemList;
        }
        
        /**
        * @return 返回 periodWeight
        */
        public int getPeriodWeight() {
            return periodWeight;
        }
        
        /**
        * @return 返回 assignItemList
        */
        public List<AssignItem> getAssignItemList() {
            return assignItemList;
        }
        
        /**
         * 可被借出金额<br/>
         * <功能详细描述>
         * @return [参数说明]
         * 
         * @return BigDecimal [返回类型说明]
         * @exception throws [异常类型] [异常说明]
         * @see [类、类#方法、类#成员]
        */
        public BigDecimal getLendAbleSum() {
            BigDecimal sum = BigDecimal.ZERO;
            for (AssignItem ai : assignItemList) {
                sum = sum.add(ai.getLendAbleAmount());
            }
            return sum;
        }
        
        /**
         * 借出金额<br/>
         *    当超出可出借金额抛出异常<br/>
         * <功能详细描述>
         * @param expectAmount
         * @return [参数说明]
         * 
         * @return BigDecimal [返回类型说明]
         * @exception throws [异常类型] [异常说明]
         * @see [类、类#方法、类#成员]
        */
        public void lend(BigDecimal expectAmount) {
            AssertUtils.notNull(expectAmount, "expectAmount is null.");
            
            BigDecimal lendAbleSum = getLendAbleSum();
            AssertUtils.isTrue(expectAmount.compareTo(lendAbleSum) <= 0,
                    "expectAmount:{} should <= lendAbleSum:{}",
                    new Object[] { expectAmount, lendAbleSum });
            
            BigDecimal expectAmountBalance = expectAmount;
            for (AssignItem aiTemp : assignItemList) {
                BigDecimal lendAmountTemp = BigDecimal.ZERO;
                if (expectAmountBalance
                        .compareTo(aiTemp.getLendAbleAmount()) > 0) {
                    lendAmountTemp = aiTemp.getLendAbleAmount();
                } else {
                    lendAmountTemp = expectAmountBalance;
                }
                expectAmountBalance = expectAmountBalance
                        .subtract(lendAmountTemp);
                aiTemp.lendAmount(lendAmountTemp);
            }
        }
    }
    
    /**
     * 分配制比较器：用于对分配项组进行排序<br/>
     */
    private static Comparator<AssignItemGroup> assignItemGroupComparator = new Comparator<SettleAssignAdjustor.AssignItemGroup>() {
        @Override
        public int compare(AssignItemGroup o1, AssignItemGroup o2) {
            Integer o1PeriodWeight = o1.getPeriodWeight();
            Integer o2PeriodWeight = o2.getPeriodWeight();
            return o1PeriodWeight.compareTo(o2PeriodWeight);
        }
    };
    
}
