/*
 * 描          述:  <描述>
 * 修  改   人:  Administrator
 * 修改时间:  2015年1月27日
 * <修改描述:>
 */
package com.tx.local.loanaccount.helper.exemptallocator;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.MapUtils;
import org.apache.commons.lang3.math.NumberUtils;

import com.tx.core.TxConstants;
import com.tx.core.exceptions.util.AssertUtils;
import com.tx.local.basicdata.model.FeeItemCfg;
import com.tx.local.basicdata.model.FeeItemEnum;
import com.tx.local.loanaccount.LoanAccountConstants;
import com.tx.local.loanaccount.handle.PaymentScheduleHandler;
import com.tx.local.loanaccount.helper.paymentschedule.PaymentScheduleEntryHelper;
import com.tx.local.loanaccount.model.AssignEntryComparable;
import com.tx.local.loanaccount.model.ExemptIntention;
import com.tx.local.loanaccount.model.FeeItemAssignGroup;
import com.tx.local.loanaccount.model.FeeItemGroup;
import com.tx.local.loanaccount.model.LoanAccount;
import com.tx.local.loanaccount.model.PaymentSchedule;
import com.tx.local.loanaccount.model.PaymentScheduleEntry;
import com.tx.local.loanaccount.model.ScheduleTypeEnum;
import com.tx.local.loanaccount.strategy.loanaccount.LoanAccountStrategy;
import com.tx.local.loanaccount.strategy.loanaccount.LoanAccountStrategyHelper;

/**
 * 还款分配器<br/>
 * 1.本金,利息,管理费不能跳期
 * 2.还款要按照还款优先级进行分配
 * 3.如果客户有还款意愿,必须优先按照客户意愿进行分配
 * 4.但是不能违背第一个原则,尽量保证第二个原则
 * 
 * @author  Administrator
 * @version  [版本号, 2015年1月27日]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public abstract class AbstractExemptAllocator implements ExemptAllocator {
    
    /* ------------------ 入参 ----------------- */
    
    /** 贷款账户 */
    protected final LoanAccount loanAccount;
    
    /** 还款计划列表 */
    protected final PaymentScheduleHandler handler;
    
    /** 还款日期 */
    protected final Date repayDate;
    
    /** 还款意愿中的金额 */
    protected final BigDecimal amount;
    
    /** 还款意愿中费用项目与金额之间的映射 */
    protected final Map<FeeItemEnum, BigDecimal> feeItem2AmountMap;
    
    ///** 还款意愿中费用项目与金额之间的映射 */
    //protected final Map<String, BigDecimal> groupCode2AmountMap;
    
    /* ------------------ 入参 ----------------- */
    
    /** 贷款账户对应策略 */
    protected final LoanAccountStrategy loanAccountStrategy;
    
    /** 费用配置项列表 */
    protected final Map<FeeItemEnum, FeeItemCfg> feeItemCfgMap;
    
    /** 分配分项比较器 */
    protected Comparator<AssignEntryComparable> assignEntryComparable;
    
    /** 最终的平息分配映射 */
    protected final Map<String, Map<FeeItemEnum, BigDecimal>> mainAssignAmountMap = new HashMap<>();
    
    /** 最终的平息分配映射 */
    protected final Map<ScheduleTypeEnum, Map<String, Map<FeeItemEnum, BigDecimal>>> assignAmountMap = new HashMap<>();
    
    /** 最大可豁免期数 */
    protected final int maxExemptAblePeriod;
    
    /** 参与分配的费用归属方 */
    protected Collection<FeeItemEnum> assignAbleFeeItems = new HashSet<>();
    
    /** <默认构造函数> */
    public AbstractExemptAllocator(LoanAccount loanAccount,
            PaymentScheduleHandler handler, ExemptIntention exemptIntention,
            int maxExemptAblePeriod) {
        super();
        AssertUtils.notNull(loanAccount, "loanAccount is null.");
        AssertUtils.notNull(handler, "handler is null.");
        AssertUtils.notNull(exemptIntention, "repayIntention is null.");
        AssertUtils.notNull(exemptIntention.getRepayDate(),
                "repayIntention.repayDate is null.");
        AssertUtils.notNull(exemptIntention.getAmount(),
                "repayIntention.amount is null.");
        AssertUtils.isTrue(
                exemptIntention.getAmount().compareTo(BigDecimal.ZERO) >= 0,
                "repayIntention.amount should >= 0.");
        
        this.loanAccount = loanAccount;
        this.handler = handler;
        this.repayDate = exemptIntention.getRepayDate();
        this.amount = exemptIntention.getAmount();
        this.feeItem2AmountMap = exemptIntention.getFeeItem2AmountMap() == null
                ? new HashMap<>() : exemptIntention.getFeeItem2AmountMap();
        //this.groupCode2AmountMap = exemptIntention.getGroupCode2AmountMap() == null ? new HashMap<>() : exemptIntention.getGroupCode2AmountMap();
        
        this.loanAccountStrategy = LoanAccountStrategyHelper
                .getStrategy(loanAccount);
        AssertUtils.notNull(this.loanAccountStrategy,
                "loanAccountStrategy is null.");
        this.feeItemCfgMap = this.loanAccountStrategy.getFeeItemCfgMap();
        if (this.loanAccount.getFeeItemCfgMap() == null) {
            this.loanAccount.setFeeItemCfgMap(this.feeItemCfgMap);
        }
        
        this.assignAbleFeeItems = this.loanAccountStrategy
                .getFeeItemsByExemptIntention(exemptIntention);
        
        //        String waitReceivePeriodString = PaymentScheduleHelper.getWaitReceivePeriod(handler.getPaymentScheduleMap(),
        //                ScheduleTypeEnum.MAIN);
        //        String receivablePeriodString = PaymentScheduleHelper.getReceivablePeriod(handler.getPaymentScheduleMap(),
        //                ScheduleTypeEnum.MAIN,
        //                repayDate);
        //        NumberUtils.max(NumberUtils.toInt(waitReceivePeriodString),
        //                NumberUtils.toInt(receivablePeriodString, 1));
        this.maxExemptAblePeriod = maxExemptAblePeriod;
    }
    
    /**
     * 构建费用项分配分组<br/>
     * <功能详细描述>
     * @param scheduleType
     * @return [参数说明]
     * 
     * @return Collection<FeeItemAssignGroup> [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    protected Collection<FeeItemAssignGroup> buildFeeItemAssignGroups(
            ScheduleTypeEnum scheduleType) {
        AssertUtils.notNull(scheduleType, "scheduleType is null.");
        Collection<FeeItemGroup> feeItemGroups = this.loanAccountStrategy
                .getFeeItemGroupsOfRepayExempt();
        
        ////构建费用分配分组
        if (ScheduleTypeEnum.MAIN.equals(scheduleType)) {
            Collection<FeeItemAssignGroup> res = FeeItemAssignGroup
                    .buildFeeItemAssignGroups(ScheduleTypeEnum.MAIN,
                            feeItemGroups,
                            this.feeItem2AmountMap,
                            null);
            return res;
        } else {
            Collection<FeeItemAssignGroup> res = FeeItemAssignGroup
                    .buildFeeItemAssignGroups(scheduleType,
                            feeItemGroups,
                            this.mainAssignAmountMap);
            return res;
        }
    }
    
    /**
     * 对还款进行平息账分配<br/>
     * <功能详细描述> [参数说明]
     * 
     * @return void [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @Override
    public void assign() {
        //将分配映射进行清空
        this.assignAmountMap.clear();
        
        //根据还款意愿进行主计划分配
        mainAssign();
        
        //根据主计划生成分配意愿分组
        assignByScheduleTypes();
    }
    
    /** 
     * 当还款渠道映射为空时的还款分配<br/>
     * <功能详细描述> [参数说明]
     * 
     * @return void [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @Override
    public void mainAssign() {
        this.mainAssignAmountMap.clear();
        
        //按照分组进行分配
        Collection<FeeItemAssignGroup> feeItemAssignGroups = buildFeeItemAssignGroups(
                ScheduleTypeEnum.MAIN);
        if (!CollectionUtils.isEmpty(feeItemAssignGroups)) {
            for (FeeItemAssignGroup fiagTemp : feeItemAssignGroups) {
                assignByFeeItemAssignGroup(this.mainAssignAmountMap,
                        ScheduleTypeEnum.MAIN,
                        fiagTemp);
            }
        }
        
        //在还款意愿外的费用项中进行分配
        if (!MapUtils.isEmpty(this.feeItem2AmountMap)) {
            //在还款意愿外的，按照
            Collection<FeeItemEnum> subtractFeeItemCollections = CollectionUtils
                    .subtract(this.assignAbleFeeItems,
                            this.feeItem2AmountMap.keySet());
            assignByExemptPriority(this.mainAssignAmountMap,
                    ScheduleTypeEnum.MAIN,
                    this.amount,
                    subtractFeeItemCollections);
        }
        //在指定费用项中进行分配
        assignByExemptPriority(this.mainAssignAmountMap,
                ScheduleTypeEnum.MAIN,
                this.amount,
                this.assignAbleFeeItems);
        //在所有费用项中进行分配:
        assignByExemptPriority(this.mainAssignAmountMap,
                ScheduleTypeEnum.MAIN,
                this.amount,
                null);
        
        filterAssignAmountMap(this.mainAssignAmountMap);
        validateAssign(this.mainAssignAmountMap);
        this.assignAmountMap.put(ScheduleTypeEnum.MAIN,
                this.mainAssignAmountMap);
    }
    
    /**
     * 根据主计划分配进行其他类型的计划的金额分配<br/>
     * <功能详细描述> [参数说明]
     * 
     * @return void [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @Override
    public void assignByScheduleTypes() {
        AssertUtils.notEmpty(this.mainAssignAmountMap,
                "mainAssignAmountMap is empty.");
        
        for (ScheduleTypeEnum scheduleType : this.handler
                .getPaymentScheduleMap().keySet()) {
            if (ScheduleTypeEnum.MAIN.equals(scheduleType)) {
                //跳过主计划的分配
                continue;
            }
            assignByScheduleType(scheduleType);
        }
    }
    
    /**
     * 对还款进行递减账分配<br/>
     * <功能详细描述> [参数说明]
     * 
     * @return void [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @Override
    public void assignByScheduleType(ScheduleTypeEnum scheduleType) {
        AssertUtils.notNull(scheduleType, "scheduleType is null.");
        AssertUtils.notEmpty(this.assignAmountMap, "assignAmountMap is empty.");
        AssertUtils.notEmpty(this.mainAssignAmountMap,
                "mainAssignAmountMap is empty.");
        this.assignAmountMap.remove(scheduleType);
        
        Map<String, Map<FeeItemEnum, BigDecimal>> typeAssignAmountMap = new HashMap<>();
        Collection<FeeItemAssignGroup> feeItemAssignGroups = buildFeeItemAssignGroups(
                scheduleType);
        
        FeeItemAssignGroup mainFiagTemp = new FeeItemAssignGroup(
                scheduleType.getKey(), feeItemAssignGroups);
        for (FeeItemAssignGroup fiagTemp : mainFiagTemp.getChilds()) {
            assignByFeeItemAssignGroup(typeAssignAmountMap,
                    scheduleType,
                    fiagTemp);
        }
        //在指定费用项中进行分配
        assignByExemptPriority(this.mainAssignAmountMap,
                ScheduleTypeEnum.MAIN,
                this.amount,
                this.assignAbleFeeItems);
        //在所有费用项中进行分配:
        assignByExemptPriority(this.mainAssignAmountMap,
                ScheduleTypeEnum.MAIN,
                this.amount,
                null);
        
        //计算分配结余金额
        filterAssignAmountMap(typeAssignAmountMap);
        validateAssign(typeAssignAmountMap);
        //雅茹分配结果
        this.assignAmountMap.put(scheduleType, typeAssignAmountMap);
    }
    
    /**
     * 根据还款意愿以及平息的还款，将还款在递减中进行分配<br/>
     * <功能详细描述>
     * @param assignAmountMap
     * @param mainAssignAmountMap
     * @param feeItemCollections [参数说明]
     * 
     * @return void [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    protected void assignByFeeItemAssignGroup(
            Map<String, Map<FeeItemEnum, BigDecimal>> assignMap,
            ScheduleTypeEnum scheduleType,
            FeeItemAssignGroup feeItemAssignGroup) {
        Collection<FeeItemEnum> feeItemCollection = feeItemAssignGroup
                .getFeeItemSet();//获取分配分组的费用项集合
        feeItemCollection = CollectionUtils.intersection(feeItemCollection,
                this.assignAbleFeeItems);
        
        if (feeItemAssignGroup.isLeaf()) {
            AssertUtils.isEmpty(feeItemAssignGroup.getChilds(),
                    "feeItemAssignGroup.childs is not null.");
            
            //根据leaf节点中： 费用项目对金额的映射
            if (!MapUtils.isEmpty(feeItemAssignGroup.getFeeItem2AmountMap())) {
                assignByFeeItem2AmountMap(assignMap,
                        scheduleType,
                        feeItemAssignGroup.getFeeItem2AmountMap(),
                        feeItemCollection);
            }
            //指定费用的还款意愿费用项集合
            Collection<FeeItemEnum> repayIntentionFeeItemSetTemp = feeItemAssignGroup
                    .getFeeItem2AmountMap().keySet();
            //按照客户的还款金额类型，将分配结余金额在对应的费用项（排除意愿中含有的费用项）进行分配
            Collection<FeeItemEnum> subtractFeeItemCollections = CollectionUtils
                    .subtract(feeItemCollection, repayIntentionFeeItemSetTemp);
            if (!CollectionUtils.isEmpty(subtractFeeItemCollections)) {
                assignByExemptPriority(assignMap,
                        scheduleType,
                        feeItemAssignGroup.getAssignSum(),
                        subtractFeeItemCollections);
            }
        } else {
            AssertUtils.notEmpty(feeItemAssignGroup.getChilds(),
                    "feeItemAssignGroup.childs is empty.");
            
            for (FeeItemAssignGroup aiagTemp : feeItemAssignGroup.getChilds()) {
                assignByFeeItemAssignGroup(assignMap, scheduleType, aiagTemp);
            }
            
            //指定费用的还款意愿费用项集合
            Collection<FeeItemEnum> repayIntentionFeeItemSetTemp = feeItemAssignGroup
                    .getFeeItem2AmountMap().keySet();
            //按照客户的还款金额类型，将分配结余金额在对应的费用项（排除意愿中含有的费用项）进行分配
            Collection<FeeItemEnum> subtractFeeItemCollections = CollectionUtils
                    .subtract(feeItemCollection, repayIntentionFeeItemSetTemp);
            if (!CollectionUtils.isEmpty(subtractFeeItemCollections)) {
                assignByExemptPriority(assignMap,
                        scheduleType,
                        feeItemAssignGroup.getAssignSum(),
                        subtractFeeItemCollections);
            }
        }
        //按照还款意愿将剩余金额再进行分配
        assignByExemptPriority(assignMap,
                scheduleType,
                feeItemAssignGroup.getAssignSum(),
                feeItemCollection);
    }
    
    /**
     * 根据还款意愿进行金额分配<br/>
     * <功能详细描述>
     * @param assignMap
     * @param feeItem2AmountMap
     * @param scheduleType
     * @param feeItemCollection
     * @return [参数说明]
     * 
     * @return BigDecimal [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    protected BigDecimal assignByFeeItem2AmountMap(
            Map<String, Map<FeeItemEnum, BigDecimal>> assignMap,
            ScheduleTypeEnum scheduleType,
            Map<FeeItemEnum, BigDecimal> feeItem2AmountMap,
            Collection<FeeItemEnum> feeItemCollection) {
        AssertUtils.notNull(assignMap, "assignMap is null.");
        AssertUtils.notNull(scheduleType, "scheduleType is null.");
        
        if (MapUtils.isEmpty(feeItem2AmountMap)) {
            return BigDecimal.ZERO;
        }
        BigDecimal assignBanlanceSum = BigDecimal.ZERO;
        for (Entry<FeeItemEnum, BigDecimal> feeItem2AmountEntry : feeItem2AmountMap
                .entrySet()) {
            if (!CollectionUtils.isEmpty(feeItemCollection)
                    && !feeItemCollection
                            .contains(feeItem2AmountEntry.getKey())) {
                continue;
            }
            BigDecimal assignBanlanceTemp = assignByFeeItemAmount(assignMap,
                    scheduleType,
                    feeItem2AmountEntry.getValue(),
                    feeItem2AmountEntry.getKey());
            assignBanlanceSum = assignBanlanceSum.add(assignBanlanceTemp);
        }
        return assignBanlanceSum;
    }
    
    /** 
     * 根据费用项金额进行分配<br/>
     * <功能详细描述>
     * @param assignMap
     * @param scheduleType
     * @param assignBanlanceSum
     * @param feeItem2AmountEntry
     * @return [参数说明]
     * 
     * @return BigDecimal [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    protected BigDecimal assignByFeeItemAmount(
            Map<String, Map<FeeItemEnum, BigDecimal>> assignMap,
            ScheduleTypeEnum scheduleType, BigDecimal assignAmount,
            FeeItemEnum feeItemTemp) {
        BigDecimal assignBanlance = assignAmount;
        List<PaymentScheduleEntry> entryList = getAssignAblePaymentScheduleEntryList(
                scheduleType, Arrays.asList(feeItemTemp));
        
        //如果存在多期时
        Collections.sort(entryList,
                PaymentScheduleEntryHelper.periodComparator);
        for (PaymentScheduleEntry entry : entryList) {
            String periodTemp = entry.getPeriod();
            BigDecimal assignAmountTemp = BigDecimal.ZERO;
            BigDecimal assignedAmount = BigDecimal.ZERO;//已经还入金额
            //计算已还入金额
            if (assignMap.containsKey(periodTemp)
                    && assignMap.get(periodTemp).containsKey(feeItemTemp)) {
                assignedAmount = assignMap.get(periodTemp).get(feeItemTemp);
            }
            
            BigDecimal assignAbleMaxAmount = null;
            //计算可分配入金额: 超过2位小数以外舍去
            assignAbleMaxAmount = entry.getReceivableAmount()
                    .add(entry.getExemptAmount())
                    .subtract(entry.getActualReceivedAmount())
                    .subtract(assignedAmount)
                    .setScale(2, RoundingMode.DOWN);
            if (assignAbleMaxAmount.compareTo(BigDecimal.ZERO) <= 0) {
                continue;
            }
            //获取分配金额
            if (assignBanlance.compareTo(assignAbleMaxAmount) <= 0) {
                assignAmountTemp = assignBanlance;
            } else {
                assignAmountTemp = assignAbleMaxAmount;
            }
            //将对应费用项目的值进行设定
            if (!assignMap.containsKey(periodTemp)) {
                assignMap.put(periodTemp,
                        new HashMap<FeeItemEnum, BigDecimal>(
                                TxConstants.INITIAL_MAP_SIZE));
            }
            if (assignMap.get(periodTemp).get(feeItemTemp) == null) {
                //将对应费用项,对应期数的金额写入
                assignMap.get(periodTemp).put(feeItemTemp, assignAmountTemp);
            } else {
                //将对应费用项,对应期数的金额加入
                assignMap.get(periodTemp).put(feeItemTemp,
                        assignedAmount.add(assignAmountTemp));
            }
            //计算结余
            assignBanlance = assignBanlance.subtract(assignAmountTemp);
            if (assignBanlance.compareTo(BigDecimal.ZERO) <= 0) {
                break;
            }
        }
        //分配结余应>=0
        AssertUtils.isTrue(assignBanlance.compareTo(BigDecimal.ZERO) >= 0,
                "assignBanlance should >= 0");
        return assignBanlance;
    }
    
    /**
     * 将待分配金额分配入金额分配结果映射中<br/>
     * <功能详细描述>
     * @param assignMap
     * @param waitAssignAmount
     * @param scheduleType
     * @param feeItemCollection
     * @return [参数说明]
     * 
     * @return BigDecimal [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    protected final BigDecimal assignByExemptPriority(
            Map<String, Map<FeeItemEnum, BigDecimal>> assignMap,
            ScheduleTypeEnum scheduleType, BigDecimal assignAmount,
            Collection<FeeItemEnum> feeItemCollection) {
        AssertUtils.notNull(assignMap, "assignMap is null.");
        AssertUtils.notNull(scheduleType, "scheduleType is null.");
        AssertUtils.notNull(assignAmount, "assignAmount is null.");
        AssertUtils.isTrue(assignAmount.compareTo(BigDecimal.ZERO) >= 0,
                "assignAmount:{} should >= 0.",
                new Object[] { assignAmount });
        
        if (assignAmount.compareTo(BigDecimal.ZERO) == 0) {
            return BigDecimal.ZERO;
        }
        //获取剩余待分配金额
        BigDecimal assignedSum = BigDecimal.ZERO;
        for (Map<FeeItemEnum, BigDecimal> entryValueTemp : assignMap.values()) {
            for (BigDecimal amountTemp : entryValueTemp.values()) {
                assignedSum = assignedSum.add(amountTemp);
            }
        }
        BigDecimal waitAssignAmount = assignAmount.subtract(assignedSum);
        AssertUtils.isTrue(waitAssignAmount.compareTo(BigDecimal.ZERO) >= 0,
                "waitAssignAmount:{} should >= 0.",
                new Object[] { waitAssignAmount });
        if (waitAssignAmount.compareTo(BigDecimal.ZERO) == 0) {
            return BigDecimal.ZERO;
        }
        
        //获取所有可分配的还款计划分项目
        List<PaymentScheduleEntry> entryList = getAssignAblePaymentScheduleEntryList(
                scheduleType, feeItemCollection);
        BigDecimal assignSum = BigDecimal.ZERO;//已分配总金额
        BigDecimal assignBanlance = waitAssignAmount;//分配结余金额
        for (PaymentScheduleEntry entry : entryList) {
            String periodTemp = entry.getPeriod();
            FeeItemEnum feeItemTemp = entry.getFeeItem();
            BigDecimal assignedAmount = BigDecimal.ZERO;//已经还入金额
            //计算已还入金额
            if (assignMap.containsKey(periodTemp)
                    && assignMap.get(periodTemp).containsKey(feeItemTemp)) {
                assignedAmount = assignMap.get(periodTemp).get(feeItemTemp);
            }
            BigDecimal assignAbleMaxAmount = null;
            
            //计算可分配入金额: 超过2位小数以外舍去
            assignAbleMaxAmount = entry.getReceivableAmount()
                    .add(entry.getExemptAmount())
                    .subtract(entry.getActualReceivedAmount())
                    .subtract(assignedAmount)
                    .setScale(2, RoundingMode.DOWN);
            if (assignAbleMaxAmount.compareTo(BigDecimal.ZERO) <= 0) {
                continue;
            }
            
            BigDecimal assignAmountTemp = null;
            //获取分配金额
            if (assignBanlance.compareTo(assignAbleMaxAmount) <= 0) {
                assignAmountTemp = assignBanlance;
            } else {
                assignAmountTemp = assignAbleMaxAmount;
            }
            
            if (assignAmountTemp.compareTo(BigDecimal.ZERO) <= 0) {
                continue;
            }
            //将对应费用项目的值进行设定
            if (!assignMap.containsKey(periodTemp)) {
                assignMap.put(periodTemp,
                        new HashMap<FeeItemEnum, BigDecimal>(
                                TxConstants.INITIAL_MAP_SIZE));
            }
            if (assignMap.get(periodTemp).get(feeItemTemp) == null) {
                //将对应费用项,对应期数的金额写入
                assignMap.get(periodTemp).put(feeItemTemp, assignAmountTemp);
            } else {
                //将对应费用项,对应期数的金额加入
                assignMap.get(periodTemp).put(feeItemTemp,
                        assignedAmount.add(assignAmountTemp));
            }
            //计算结余
            assignBanlance = assignBanlance.subtract(assignAmountTemp);
            assignSum = assignSum.add(assignAmountTemp);
        }
        //分配结余应>=0
        AssertUtils.isTrue(assignBanlance.compareTo(BigDecimal.ZERO) >= 0,
                "assignBanlance should >= 0");
        //判断分配结余 == 总金额 - 分配金额
        AssertUtils.isTrue(
                assignBanlance
                        .compareTo(waitAssignAmount.subtract(assignSum)) == 0,
                "判断分配结余:{} != 总金额:{} - 分配金额:{}",
                new Object[] { assignBanlance, waitAssignAmount, assignSum });
        return assignBanlance;
    }
    
    /**
     * 根据传入条件获取可分配金额的费用分项列表<br/>
     * <功能详细描述>
     * @param paymentScheduleMap
     * @param isIrr
     * @param feeItemCollection
     * @return [参数说明]
     * 
     * @return List<PaymentScheduleEntry> [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    private List<PaymentScheduleEntry> getAssignAblePaymentScheduleEntryList(
            ScheduleTypeEnum scheduleType,
            Collection<FeeItemEnum> feeItemCollection) {
        AssertUtils.notNull(scheduleType, "scheduleType is null.");
        AssertUtils.notEmpty(
                this.handler.getPaymentScheduleMap().get(scheduleType),
                "handler.paymentScheduleMap.[scheduleType] is empty.");
        
        //还款计划分项列表
        List<PaymentScheduleEntry> resList = new ArrayList<>(
                TxConstants.INITIAL_CONLLECTION_SIZE);
        for (PaymentSchedule psTemp : this.handler.getPaymentScheduleMap()
                .valueList(scheduleType)) {
            if (NumberUtils.isDigits(psTemp.getPeriod()) && NumberUtils
                    .toInt(psTemp.getPeriod()) > this.maxExemptAblePeriod) {
                continue;
            }
            for (PaymentScheduleEntry entry : psTemp
                    .getPaymentScheduleEntryList()) {
                FeeItemEnum feeItemTemp = entry.getFeeItem();
                //如果传入了指定费用项，则将非指定费用项目的还款计划分项跳过
                if (!CollectionUtils.isEmpty(feeItemCollection)
                        && !feeItemCollection.contains(feeItemTemp)) {
                    continue;
                }
                if (feeItemCfgMap.get(feeItemTemp).isPrincipal()
                        || !feeItemCfgMap.get(feeItemTemp).isExemptAble()) {
                    continue;
                }
                BigDecimal checkAmount = entry.getReceivableAmount()
                        .add(entry.getExemptAmount())
                        .subtract(entry.getActualReceivedAmount());
                //检查金额，可分配金额大于0的压入列表
                if (checkAmount.compareTo(LoanAccountConstants.ONE_CENT) >= 0) {
                    resList.add(entry);
                }
            }
        }
        //获取费用项//未来WO期，及其他情况可以考虑重构该comparator进行满足
        Comparator<AssignEntryComparable> comparator = buildAssignComparator(
                scheduleType);
        //对还款计划分项进行排序
        Collections.sort(resList, comparator);
        return resList;
    }
    
    /**
     * 根据条件构建还款分配优先级比较器<br/>
     * <功能详细描述>
     * @param scheduleType
     * @return [参数说明]
     * 
     * @return Comparator<PaymentScheduleEntry> [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    protected abstract Comparator<AssignEntryComparable> buildAssignComparator(
            ScheduleTypeEnum scheduleType);
    
    /** 
     * 验证主计划分配结果<br/>
     * <功能详细描述> [参数说明]
     * 
     * @return void [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    private void validateAssign(
            Map<String, Map<FeeItemEnum, BigDecimal>> assignAmountMap) {
        //计算分配结余金额
        BigDecimal assignedSum = BigDecimal.ZERO;//已分配金额之和
        for (Map<FeeItemEnum, BigDecimal> assignAmountMapTemp : assignAmountMap
                .values()) {
            for (BigDecimal amount : assignAmountMapTemp.values()) {
                //校验分配金额必须为2位小数以内
                AssertUtils.isTrue(
                        amount.compareTo(
                                amount.setScale(2, RoundingMode.DOWN)) == 0,
                        "amount:{} should == amount.setScale(2,UP):{}",
                        new Object[] { amount,
                                amount.setScale(2, RoundingMode.DOWN) });
                
                assignedSum = assignedSum.add(amount);
            }
        }
        
        AssertUtils.isTrue(this.amount.compareTo(assignedSum) == 0,
                "amount:{} <> assignedSum:{} ",
                new Object[] { this.amount, assignedSum });
    }
    
    /**
     * 过滤最终的分配映射去掉其中的null或0
     * <功能详细描述>
     * @param assignAmountMap [参数说明]
     * 
     * @return void [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    private void filterAssignAmountMap(
            Map<String, Map<FeeItemEnum, BigDecimal>> assignAmountMap) {
        AssertUtils.notNull(assignAmountMap, "assignAmountMap is null.");
        
        Map<String, Map<FeeItemEnum, BigDecimal>> newMap = new HashMap<>();
        for (Entry<String, Map<FeeItemEnum, BigDecimal>> entryTemp : assignAmountMap
                .entrySet()) {
            String periodTemp = entryTemp.getKey();
            Map<FeeItemEnum, BigDecimal> feeItemMapTemp = new HashMap<>();
            for (Entry<FeeItemEnum, BigDecimal> subEntryTemp : entryTemp
                    .getValue().entrySet()) {
                FeeItemEnum feeItem = subEntryTemp.getKey();
                if (subEntryTemp.getValue() == null || subEntryTemp.getValue()
                        .compareTo(BigDecimal.ZERO) == 0) {
                    continue;
                }
                
                //校验分配金额必须为2位小数以内
                AssertUtils.isTrue(
                        subEntryTemp.getValue()
                                .compareTo(subEntryTemp.getValue().setScale(2,
                                        RoundingMode.DOWN)) == 0,
                        "amount:{} should == amount.setScale(2,UP):{}",
                        new Object[] { subEntryTemp.getValue(), subEntryTemp
                                .getValue().setScale(2, RoundingMode.DOWN) });
                
                feeItemMapTemp.put(feeItem, subEntryTemp.getValue());
            }
            if (!MapUtils.isEmpty(feeItemMapTemp)) {
                newMap.put(periodTemp, feeItemMapTemp);
            }
        }
        assignAmountMap.clear();
        assignAmountMap.putAll(newMap);
    }
    
    /**
     * @return
     */
    @Override
    public Map<String, Map<FeeItemEnum, BigDecimal>> getMainAssignAmountMap() {
        return mainAssignAmountMap;
    }
    
    /**
     * @return
     */
    @Override
    public Map<ScheduleTypeEnum, Map<String, Map<FeeItemEnum, BigDecimal>>> getAssignAmountMap() {
        return assignAmountMap;
    }
    
    /**
     * @return
     */
    @Override
    public Date getRepayDate() {
        return this.repayDate;
    }
    
    /**
     * @return
     */
    @Override
    public BigDecimal getAmount() {
        return this.amount;
    }
}
