/*
 * 描          述:  <描述>
 * 修  改   人:  Administrator
 * 修改时间:  2014年5月18日
 * <修改描述:>
 */
package com.tx.local.loanaccount.handle;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.ListUtils;
import org.apache.commons.collections4.MapUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.joda.time.DateTime;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import com.tx.component.command.context.ChangeListener;
import com.tx.component.command.context.ChangeListenerUtils;
import com.tx.core.TxConstants;
import com.tx.core.exceptions.util.AssertUtils;
import com.tx.core.util.DateUtils;
import com.tx.core.util.ObjectUtils;
import com.tx.local.basicdata.model.FeeItemCfg;
import com.tx.local.basicdata.model.FeeItemEnum;
import com.tx.local.loanaccount.LoanAccountConstants;
import com.tx.local.loanaccount.model.ChargeRecord;
import com.tx.local.loanaccount.model.ChargeRecordEntry;
import com.tx.local.loanaccount.model.ExemptRecord;
import com.tx.local.loanaccount.model.ExemptRecordEntry;
import com.tx.local.loanaccount.model.LATradingRecord;
import com.tx.local.loanaccount.model.LoanAccount;
import com.tx.local.loanaccount.model.LoanAccountFeeItem;
import com.tx.local.loanaccount.model.PaymentRecord;
import com.tx.local.loanaccount.model.PaymentRecordEntry;
import com.tx.local.loanaccount.model.PaymentSchedule;
import com.tx.local.loanaccount.model.PaymentScheduleEntry;
import com.tx.local.loanaccount.model.ScheduleAssociateMap;
import com.tx.local.loanaccount.model.ScheduleEntryAssociateMap;
import com.tx.local.loanaccount.model.ScheduleTypeEnum;

/**
 * 还款计划处理器<br/>
 * 接受还款计划构造而出<br/>
 * <功能详细描述>
 * 
 * @author Administrator
 * @version [版本号, 2014年5月18日]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
public class PaymentScheduleHandler implements Cloneable, Serializable {
    
    /** 注释内容 */
    private static final long serialVersionUID = 543555800419842131L;
    
    /** 还款计划映射 */
    private ScheduleAssociateMap<PaymentSchedule> paymentScheduleMap;
    
    /** 还款计划分项映射 */
    private ScheduleEntryAssociateMap<PaymentScheduleEntry> paymentScheduleEntryMap;
    
    /** 发生了变化的还款计划 key 为period */
    private List<ChangeListener<PaymentSchedule>> paymentScheduleChangeListeners;
    
    /** 发生了变化的还款计划分项 */
    private List<ChangeListener<PaymentScheduleEntry>> paymentScheduleEntryChangeListeners;
    
    /** 增加的还款计划映射 */
    private final Set<PaymentSchedule> addPaymentScheduleSet = new HashSet<>(
            TxConstants.INITIAL_MAP_SIZE);
    
    /** 新增的还款计划分项 */
    private final Set<PaymentScheduleEntry> addPaymentScheduleEntrySet = new HashSet<PaymentScheduleEntry>(
            TxConstants.INITIAL_MAP_SIZE);
    
    /* ************* 入参初始化 start******************* */
    
    /** 对应的贷款账户id */
    private final String loanAccountId;
    
    /** 贷款账户 */
    private final LoanAccount loanAccount;
    
    /** 费用项对费用配置项之间的映射 */
    private final Map<FeeItemEnum, LoanAccountFeeItem> feeItemMap;
    
    /** 费用项对费用配置项之间的映射 */
    private final Map<FeeItemEnum, FeeItemCfg> feeItemCfgMap;
    
    /** 构建handler时传入的还款计划列表的引用 */
    private final List<PaymentSchedule> paymentScheduleList;
    
    /** 还款计划分项集合 */
    private final List<PaymentScheduleEntry> paymentScheduleEntryList;
    
    /* ************* 入参初始化 end ******************* */
    
    public PaymentScheduleHandler(LoanAccount loanAccount,
            Map<FeeItemEnum, FeeItemCfg> feeItemCfgMap,
            Map<FeeItemEnum, LoanAccountFeeItem> feeItemMap,
            List<PaymentSchedule> paymentScheduleList,
            List<PaymentScheduleEntry> paymentScheduleEntryList) {
        AssertUtils.notEmpty(loanAccount.getId(), "loanAccount.id is empty.");
        AssertUtils.notNull(loanAccount, "loanAccount is null.");
        AssertUtils.notEmpty(feeItemCfgMap, "feeItemCfgMap is null.");
        
        AssertUtils.notNull(feeItemMap, "feeItemMap is null.");//这里判断not null是因为需要允许lazyMap存在
        AssertUtils.notEmpty(paymentScheduleList,
                "paymentScheduleList is null.");
        AssertUtils.notEmpty(paymentScheduleEntryList,
                "paymentScheduleEntryList is null.");
        
        this.loanAccountId = loanAccount.getId();
        this.loanAccount = loanAccount;
        this.feeItemMap = feeItemMap;
        this.feeItemCfgMap = feeItemCfgMap;
        this.paymentScheduleList = paymentScheduleList;
        this.paymentScheduleEntryList = paymentScheduleEntryList;
        
        init();
    }
    
    /**
     * 重置 <功能详细描述> [参数说明]
     * 
     * @return void [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    private void init() {
        AssertUtils.notEmpty(this.loanAccountId, "loanAccountId is empty.");
        AssertUtils.notNull(this.loanAccount, "loanAccount is null.");
        AssertUtils.notNull(this.feeItemMap, "feeItemMap is null.");
        AssertUtils.notEmpty(this.feeItemCfgMap, "feeItemCfgMap is empty.");
        
        //处理费用项集合
        this.loanAccount.setFeeItemMap(feeItemMap);
        this.loanAccount.setFeeItemCfgMap(feeItemCfgMap);
        
        //构建还款计划映射
        ScheduleEntryAssociateMap<PaymentScheduleEntry> pseMap = new ScheduleEntryAssociateMap<>(
                this.paymentScheduleEntryList);
        //ScheduleAssociateMap<PaymentSchedule> psMap = new ScheduleAssociateMap<>(this.paymentScheduleList);
        for (PaymentSchedule ps : this.paymentScheduleList) {
            //建立关联：在此处已经将Entry中的Entity设置进入了
            ps.setPaymentScheduleEntryList(new ArrayList<>(
                    pseMap.valueList(ps.getScheduleType(), ps.getPeriod())));
        }
        
        this.paymentScheduleChangeListeners = ChangeListenerUtils
                .buildListenerList(this.paymentScheduleList,
                        PaymentSchedule.class,
                        Arrays.asList("setPaymentScheduleEntryList"));
        this.paymentScheduleEntryChangeListeners = ChangeListenerUtils
                .buildListenerList(this.paymentScheduleEntryList,
                        PaymentScheduleEntry.class,
                        Arrays.asList("setPaymentSchedule"));
        
        this.paymentScheduleMap = new ScheduleAssociateMap<>(ChangeListenerUtils
                .newProxyList(this.paymentScheduleChangeListeners));
        this.paymentScheduleEntryMap = new ScheduleEntryAssociateMap<>(
                ChangeListenerUtils.newProxyList(
                        this.paymentScheduleEntryChangeListeners));
    }
    
    /**
     * 校验计划中本金结余是否与实际的本金结余金额一致<br/>
     * @param scheduleType
     * @param principalBalance [参数说明]
     *            
     * @return void [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    public void validatePrincipalBalance(ScheduleTypeEnum scheduleType,
            BigDecimal principalBalance) {
        AssertUtils.notNull(scheduleType, "scheduleType is null.");
        AssertUtils.notNull(principalBalance, "principalBalance is null.");
        if (!this.paymentScheduleMap.containsKey(scheduleType)) {
            return;
        }
        
        //计划中的本金结余
        BigDecimal schedulePrincipalBalance = BigDecimal.ZERO;
        
        for (PaymentSchedule paymentSchedule : this.paymentScheduleMap
                .get(scheduleType).values()) {
            String period = paymentSchedule.getPeriod();
            for (PaymentScheduleEntry entry : paymentSchedule
                    .getPaymentScheduleEntryList()) {
                AssertUtils.isTrue(period.equals(entry.getPeriod()),
                        "entry period not match.");
                
                FeeItemCfg feeItemCfg = loanAccount.getFeeItemCfgMap()
                        .get(entry.getFeeItem());
                AssertUtils.notNull(feeItemCfg,
                        "feeItemCfg is null.feeItem:{}",
                        new Object[] { entry.getFeeItem() });
                //如果是本金
                if (feeItemCfg.isPrincipal()) {
                    //本金结余等于：计划中本金分项中的，应收 + 豁免  - 实收之和
                    schedulePrincipalBalance = schedulePrincipalBalance
                            .add(entry.getReceivableAmount()
                                    .add(entry.getExemptAmount())
                                    .subtract(entry.getActualReceivedAmount()));
                }
            }
        }
        AssertUtils.isTrue(
                principalBalance.compareTo(schedulePrincipalBalance) == 0,
                "本金结余不等：贷款账户：{} 计划中:{}",
                new Object[] { principalBalance, schedulePrincipalBalance });
    }
    
    /**
     * 校验还款计划中每一期 平息应收 = 各分项应收之和 平息豁免 = 各分项目豁免之和 平息实收 = 各分项实收之和
     * 递减应收 = 各分项递减应收之和 递减豁免 = 各分项目递减之和 递减实收 = 各分项递减之和 校验计划中 校验还款计划中 每期中的 计划 与 计划分项目 是否匹配 校验还款计划中 平息应收之和 + 平息豁免之和 = 递减应收 + 递减豁免 平息实收之和 == 递减实收之和 校验逾期总额 计划中的逾期总额是否与贷款账户中的逾期总额一致 校验还款计划 是否当前期数之前的贷款账户的平息是否均已结清 是否存在前一期未结清，下一期已经结清的情况 <功能详细描述> [参数说明]
     * 
     * @return void [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    public void validate() {
        //平息应收、豁免、实收
        BigDecimal receivableSum = BigDecimal.ZERO;
        BigDecimal exemptSum = BigDecimal.ZERO;
        BigDecimal actualReceivedSum = BigDecimal.ZERO;
        
        for (PaymentSchedule paymentSchedule : this.paymentScheduleMap
                .valueList(ScheduleTypeEnum.MAIN)) {
            String period = paymentSchedule.getPeriod();
            
            BigDecimal entryReceivableSum = BigDecimal.ZERO;
            BigDecimal entryExemptSum = BigDecimal.ZERO;
            BigDecimal entryActualReceivedSum = BigDecimal.ZERO;
            
            for (PaymentScheduleEntry entry : paymentSchedule
                    .getPaymentScheduleEntryList()) {
                AssertUtils.isTrue(period.equals(entry.getPeriod()),
                        "entry period not match.");
                FeeItemEnum feeItem = entry.getFeeItem();
                
                entryReceivableSum = entryReceivableSum
                        .add(entry.getReceivableAmount());
                entryExemptSum = entryExemptSum.add(entry.getExemptAmount());
                entryActualReceivedSum = entryActualReceivedSum
                        .add(entry.getActualReceivedAmount());
                
                AssertUtils.isTrue(
                        entry.getReceivableAmount()
                                .add(entry.getExemptAmount())
                                .compareTo(BigDecimal.ZERO) >= 0,
                        "scheduleType:{} period:{} 应收加豁免值应大于0.应收:{} 豁免:{}",
                        new Object[] { ScheduleTypeEnum.MAIN, period,
                                entry.getReceivableAmount(),
                                entry.getExemptAmount() });
                if (feeItem.isNeedValidate()) {
                    //超额还款就会出现<0的情况
                    AssertUtils.isTrue(
                            entry.getReceivableAmount()
                                    .add(entry.getExemptAmount())
                                    .subtract(entry.getActualReceivedAmount())
                                    .compareTo(BigDecimal.ZERO) >= 0,
                            "scheduleType:{} period:{} 应收加豁免减实收之和应大于0.应收:{} 豁免:{}:实收:{} period:{} feeItem:{}",
                            new Object[] { ScheduleTypeEnum.MAIN, period,
                                    entry.getReceivableAmount(),
                                    entry.getExemptAmount(),
                                    entry.getActualReceivedAmount(),
                                    entry.getPeriod(), entry.getFeeItem() });
                }
            }
            AssertUtils.isTrue(
                    paymentSchedule.getReceivableSum()
                            .compareTo(entryReceivableSum) == 0,
                    "还款计划:应收不等于应收分项之和  scheduleType:{} period:{} receivable:{} receivableEntrySum:{}",
                    new Object[] { ScheduleTypeEnum.MAIN,
                            paymentSchedule.getPeriod(),
                            paymentSchedule.getReceivableSum(),
                            entryReceivableSum });
            AssertUtils.isTrue(
                    paymentSchedule.getExemptSum()
                            .compareTo(entryExemptSum) == 0,
                    "还款计划:豁免不等于豁免分项之和  scheduleType:{} period:{} exempt:{} exemptEntrySum:{}",
                    new Object[] { ScheduleTypeEnum.MAIN,
                            paymentSchedule.getPeriod(),
                            paymentSchedule.getExemptSum(), entryExemptSum });
            AssertUtils.isTrue(
                    paymentSchedule.getActualReceivedSum()
                            .compareTo(entryActualReceivedSum) == 0,
                    "还款计划:实收不等于 实收分项之和 scheduleType:{} period:{} actualReceived:{} entryActualReceivedSum:{}",
                    new Object[] { ScheduleTypeEnum.MAIN,
                            paymentSchedule.getPeriod(),
                            paymentSchedule.getActualReceivedSum(),
                            entryActualReceivedSum });
            
            receivableSum = receivableSum
                    .add(paymentSchedule.getReceivableSum());
            exemptSum = exemptSum.add(paymentSchedule.getExemptSum());
            actualReceivedSum = actualReceivedSum
                    .add(paymentSchedule.getActualReceivedSum());
        }
        
        //验证其他类型计划的合法性与一致性
        for (ScheduleTypeEnum scheduleType : this.paymentScheduleMap.keySet()) {
            if (ScheduleTypeEnum.MAIN.equals(scheduleType)) {
                continue;
            }
            validate(scheduleType, receivableSum, exemptSum, actualReceivedSum);
        }
    }
    
    /**
      * 验证计划类型的合法性<br/>
      * <功能详细描述>
      * @param scheduleType
      * @param receivableSum
      * @param exemptSum
      * @param actualReceivedSum [参数说明]
      * 
      * @return void [返回类型说明]
      * @exception throws [异常类型] [异常说明]
      * @see [类、类#方法、类#成员]
     */
    private void validate(ScheduleTypeEnum scheduleType,
            BigDecimal receivableSum, BigDecimal exemptSum,
            BigDecimal actualReceivedSum) {
        //平息应收、豁免、实收
        BigDecimal receivableSumIrr = BigDecimal.ZERO;
        BigDecimal exemptSumIrr = BigDecimal.ZERO;
        BigDecimal actualReceivedSumIrr = BigDecimal.ZERO;
        for (PaymentSchedule paymentSchedule : this.paymentScheduleMap
                .valueList(scheduleType)) {
            String period = paymentSchedule.getPeriod();
            
            BigDecimal entryReceivableSum = BigDecimal.ZERO;
            BigDecimal entryExemptSum = BigDecimal.ZERO;
            BigDecimal entryActualReceivedSum = BigDecimal.ZERO;
            
            for (PaymentScheduleEntry entry : paymentSchedule
                    .getPaymentScheduleEntryList()) {
                AssertUtils.isTrue(period.equals(entry.getPeriod()),
                        "entry period not match.");
                FeeItemEnum feeItem = entry.getFeeItem();
                
                entryReceivableSum = entryReceivableSum
                        .add(entry.getReceivableAmount());
                entryExemptSum = entryExemptSum.add(entry.getExemptAmount());
                entryActualReceivedSum = entryActualReceivedSum
                        .add(entry.getActualReceivedAmount());
                
                AssertUtils.isTrue(
                        entry.getReceivableAmount()
                                .add(entry.getExemptAmount())
                                .compareTo(BigDecimal.ZERO) >= 0,
                        "scheduleType:{} period:{} 应收加豁免值应大于0.应收:{} 豁免:{}",
                        new Object[] { scheduleType, period,
                                entry.getReceivableAmount(),
                                entry.getExemptAmount() });
                if (feeItem.isNeedValidate()) {
                    //超额还款就会出现<0的情况
                    AssertUtils.isTrue(
                            entry.getReceivableAmount()
                                    .add(entry.getExemptAmount())
                                    .subtract(entry.getActualReceivedAmount())
                                    .compareTo(BigDecimal.ZERO) >= 0,
                            "scheduleType:{} period:{} 应收加豁免减实收之和应大于0.应收:{} 豁免:{}:实收:{} period:{} feeItem:{}",
                            new Object[] { scheduleType, period,
                                    entry.getReceivableAmount(),
                                    entry.getExemptAmount(),
                                    entry.getActualReceivedAmount(),
                                    entry.getPeriod(), entry.getFeeItem() });
                }
            }
            AssertUtils.isTrue(
                    paymentSchedule.getReceivableSum()
                            .compareTo(entryReceivableSum) == 0,
                    "还款计划:应收不等于应收分项之和 scheduleType:{} period:{} receivable:{} receivableEntrySum:{}",
                    new Object[] { scheduleType, paymentSchedule.getPeriod(),
                            paymentSchedule.getReceivableSum(),
                            entryReceivableSum });
            AssertUtils.isTrue(
                    paymentSchedule.getExemptSum()
                            .compareTo(entryExemptSum) == 0,
                    "还款计划:豁免不等于豁免分项之和 scheduleType:{} period:{} exempt:{} exemptEntrySum:{}",
                    new Object[] { scheduleType, paymentSchedule.getPeriod(),
                            paymentSchedule.getExemptSum(), entryExemptSum });
            AssertUtils.isTrue(
                    paymentSchedule.getActualReceivedSum()
                            .compareTo(entryActualReceivedSum) == 0,
                    "还款计划:实收不等于 实收分项之和 scheduleType:{} period:{} actualReceived:{} entryActualReceivedSum:{}",
                    new Object[] { scheduleType, paymentSchedule.getPeriod(),
                            paymentSchedule.getActualReceivedSum(),
                            entryActualReceivedSum });
            
            receivableSumIrr = receivableSumIrr
                    .add(paymentSchedule.getReceivableSum());
            exemptSumIrr = exemptSumIrr.add(paymentSchedule.getExemptSum());
            actualReceivedSumIrr = actualReceivedSumIrr
                    .add(paymentSchedule.getActualReceivedSum());
        }
        
        //平息的 应收 + 豁免 = 递减的应收 + 豁免
        AssertUtils.isTrue(
                receivableSum.add(exemptSum)
                        .compareTo(receivableSumIrr.add(exemptSumIrr)) == 0,
                "MAIN:平息应收 + 豁免 不等于 {}的应收 + 豁免. 平息应收：{} 豁免：{}  {}应收：{} 豁免:{}",
                new Object[] { scheduleType, receivableSum, exemptSum,
                        scheduleType, receivableSumIrr, exemptSumIrr });
        AssertUtils.isTrue(
                actualReceivedSum.compareTo(actualReceivedSumIrr) == 0,
                "MAIN: 实收不等于{}实收. MAIN:实收:{} {}实收:{}",
                new Object[] { scheduleType, actualReceivedSum, scheduleType,
                        actualReceivedSumIrr });
    }
    
    /**
     * 构建一个还款计划项<br/>
     * <功能详细描述>
     * 
     * @param period [参数说明]
     *            
     * @return void [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    private PaymentSchedule buildPaymentSchedule(ScheduleTypeEnum scheduleType,
            String period) {
        AssertUtils.notNull(scheduleType, "scheduleType is null.");
        AssertUtils.notEmpty(period, "period is empty.");
        AssertUtils.isTrue(!NumberUtils.isDigits(period),
                "period:{} is digists.",
                period);
        
        PaymentSchedule paymentSchedule = new PaymentSchedule();
        paymentSchedule.setReceivableSum(BigDecimal.ZERO);
        paymentSchedule.setActualReceivedSum(BigDecimal.ZERO);
        paymentSchedule.setExemptSum(BigDecimal.ZERO);
        
        paymentSchedule.setLoanAccountId(loanAccount.getId());
        paymentSchedule.setScheduleType(scheduleType);
        paymentSchedule.setPeriod(period);
        
        paymentSchedule.setOverdue(false);
        paymentSchedule.setOverdueSum(BigDecimal.ZERO);
        paymentSchedule.setSettle(false);
        
        paymentSchedule.setRepaymentDate(null);
        paymentSchedule.setPrePeriod(null);
        paymentSchedule.setNextPeriod(null);
        
        return paymentSchedule;
    }
    
    /**
     * 构建还款计划分项 <功能详细描述>
     * 
     * @param period
     * @param feeItem
     * @return [参数说明]
     *         
     * @return PaymentScheduleEntry [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    private PaymentScheduleEntry buildPaymentScheduleEntry(
            PaymentSchedule paymentSchedule, ScheduleTypeEnum scheduleType,
            String period, FeeItemEnum feeItem) {
        AssertUtils.notNull(paymentSchedule, "paymentSchedule is null");
        AssertUtils.notNull(scheduleType, "scheduleType is null.");
        AssertUtils.notEmpty(period, "period is empty.");
        //AssertUtils.isTrue(!NumberUtils.isDigits(period), "period:{} is digists.", period);
        AssertUtils.notNull(feeItem, "feeItem is null.");
        
        PaymentScheduleEntry scheduleEntry = new PaymentScheduleEntry();
        scheduleEntry.setReceivableAmount(BigDecimal.ZERO);
        scheduleEntry.setActualReceivedAmount(BigDecimal.ZERO);
        scheduleEntry.setExemptAmount(BigDecimal.ZERO);
        
        scheduleEntry.setLoanAccountId(loanAccount.getId());
        scheduleEntry.setPaymentSchedule(paymentSchedule);
        scheduleEntry.setScheduleType(scheduleType);
        scheduleEntry.setPeriod(period);
        scheduleEntry.setFeeItem(feeItem);
        
        scheduleEntry.setPrePeriod(paymentSchedule.getPrePeriod());
        scheduleEntry.setNextPeriod(paymentSchedule.getNextPeriod());
        
        return scheduleEntry;
    }
    
    /**
     * 持久化还款计划 如果存在，则添加至已经修改的还款计划中，如果不存在，则生成一条新的还款计划并添加至原有引用中 <功能详细描述>
     * 
     * @param period
     * @return [参数说明]
     *         
     * @return PaymentSchedule [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    public PaymentSchedule getPaymentSchedule(ScheduleTypeEnum scheduleType,
            String period) {
        AssertUtils.notNull(scheduleType, "scheduleType is null.");
        AssertUtils.notEmpty(period, "period is empty.");
        
        PaymentSchedule paymentSchedule = null;
        if (this.paymentScheduleMap.contains(scheduleType, period)) {
            paymentSchedule = paymentScheduleMap.get(scheduleType, period);
        } else {
            paymentSchedule = buildPaymentSchedule(scheduleType, period);
            
            //添加至新增的还款计划中
            AssertUtils.isTrue(
                    !this.addPaymentScheduleSet.contains(paymentSchedule),
                    "paymentSchedule is exists.scheduleType:{} period:{}",
                    new Object[] { paymentSchedule.getScheduleType(),
                            paymentSchedule.getPeriod() });
            this.addPaymentScheduleSet.add(paymentSchedule);
            this.paymentScheduleMap.add(paymentSchedule);
        }
        return paymentSchedule;
    }
    
    /**
     * 根据期数以及费用项获取对应还款计划分项,如果指定分项不存在，则进行创建<br/>
     * <功能详细描述>
     * 
     * @return [参数说明]
     *         
     * @return PaymentScheduleEntry [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    public PaymentScheduleEntry getPaymentScheduleEntry(
            ScheduleTypeEnum scheduleType, String period, FeeItemEnum feeItem) {
        AssertUtils.notNull(scheduleType, "scheduleType is null.");
        AssertUtils.notEmpty(period, "period is empty.");
        AssertUtils.notNull(feeItem, "feeItem is null.");
        
        //获取对应期的分项映射
        PaymentScheduleEntry scheduleEntry = null;
        if (this.paymentScheduleEntryMap.contains(scheduleType,
                period,
                feeItem)) {
            scheduleEntry = this.paymentScheduleEntryMap.get(scheduleType,
                    period,
                    feeItem);
        } else {
            PaymentSchedule paymentSchedule = getPaymentSchedule(scheduleType,
                    period);
            scheduleEntry = buildPaymentScheduleEntry(paymentSchedule,
                    scheduleType,
                    period,
                    feeItem);
            paymentSchedule.addPaymentScheduleEntry(scheduleEntry);
            
            //添加至新增的还款计划中
            AssertUtils.isTrue(
                    !this.addPaymentScheduleEntrySet.contains(scheduleEntry),
                    "paymentSchedule is exists.scheduleType:{} period:{} feeItem:{}",
                    new Object[] { scheduleEntry.getScheduleType(),
                            scheduleEntry.getPeriod(),
                            scheduleEntry.getFeeItem() });
            this.addPaymentScheduleEntrySet.add(scheduleEntry);
            this.paymentScheduleEntryMap.add(scheduleEntry);
        }
        
        return scheduleEntry;
    }
    
    /**
     * 构建计费集合 <功能详细描述>
     * 
     * @param chargeRecordEntryList
     * @param tradingRecord
     * @return [参数说明]
     *         
     * @return List<ChargeRecord> [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    public List<ChargeRecord> buildChargeRecords(
            List<ChargeRecordEntry> chargeRecordEntryList,
            LATradingRecord tradingRecord) {
        if (CollectionUtils.isEmpty(chargeRecordEntryList)) {
            return null;
        }
        
        //校验并压入multiValueMap
        ScheduleEntryAssociateMap<ChargeRecordEntry> chargeRecordEntryMap = new ScheduleEntryAssociateMap<ChargeRecordEntry>(
                chargeRecordEntryList);
        ScheduleAssociateMap<ChargeRecord> chargeRecordMap = new ScheduleAssociateMap<>();
        //迭代写入计费记录分项
        for (Entry<ScheduleTypeEnum, Map<String, Map<FeeItemEnum, ChargeRecordEntry>>> entryTemp : chargeRecordEntryMap
                .entrySet()) {
            if (MapUtils.isEmpty(entryTemp.getValue())) {
                continue;
            }
            ScheduleTypeEnum scheduleType = entryTemp.getKey();
            for (Entry<String, Map<FeeItemEnum, ChargeRecordEntry>> et : entryTemp
                    .getValue().entrySet()) {
                String period = et.getKey();
                
                PaymentSchedule paymentSchedule = getPaymentSchedule(
                        scheduleType, period);
                ChargeRecord chargeRecord = new ChargeRecord();
                
                chargeRecord.setPeriod(period);
                chargeRecord.setLoanAccountId(this.loanAccount.getId());
                chargeRecord.setTradingRecord(tradingRecord);
                chargeRecord.setPaymentSchedule(paymentSchedule);
                chargeRecord.setScheduleType(scheduleType);
                chargeRecord.setPeriod(period);
                Date now = new Date();
                chargeRecord.setCreateDate(now);
                chargeRecord.setLastUpdateDate(now);
                
                //从计划中获取source值
                chargeRecord.setSourceSum(paymentSchedule.getReceivableSum());
                //计算增量值
                BigDecimal sum = BigDecimal.ZERO; // 增值和
                for (ChargeRecordEntry chargeRecordEntry : et.getValue()
                        .values()) {
                    sum = sum.add(chargeRecordEntry.getAmount());
                }
                chargeRecord.setSum(sum);
                //设置target值
                BigDecimal targetSum = chargeRecord.getSourceSum().add(sum);
                chargeRecord.setTargetSum(targetSum);
                
                //将关联的ChargeRecordEntry设入
                chargeRecord.setChargeRecordEntryList(
                        new ArrayList<>(et.getValue().values()));
                chargeRecordMap.add(chargeRecord);
            }
        }
        
        //返回的计费记录
        List<ChargeRecord> chargeRecords = chargeRecordMap.valueList();
        return chargeRecords;
    }
    
    /**
     * 重新计算还款计划的本金结余<br/>
     * <功能详细描述> [参数说明]
     * 
     * @return void [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    private void calculatePrincipalBalance() {
        for (ScheduleTypeEnum scheduleType : this.paymentScheduleMap.keySet()) {
            calculatePrincipalBalance(scheduleType);
        }
    }
    
    /**
     * 重新计算还款计划的本金结余<br/>
     * <功能详细描述> [参数说明]
     * 
     * @return void [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    private void calculatePrincipalBalance(ScheduleTypeEnum scheduleType) {
        AssertUtils.notNull(scheduleType, "scheduleType is null.");
        //如果无计费信息
        BigDecimal principalBalance = loanAccount.getLoanAmount();
        
        String nextPeriod = "1";
        while (!StringUtils.isBlank(nextPeriod)) {
            //校验合法性
            AssertUtils.isTrue(
                    this.paymentScheduleMap.contains(scheduleType, nextPeriod),
                    "paymentScheduleMap not conatains scheduleType:{} period:{}",
                    new Object[] { scheduleType, nextPeriod });
            //获取对应期数的还款计划
            PaymentSchedule ps = this.paymentScheduleMap.get(scheduleType,
                    nextPeriod);
            //计算本金结余
            List<PaymentScheduleEntry> entryList = this.paymentScheduleEntryMap
                    .valueList(scheduleType, nextPeriod);
            for (PaymentScheduleEntry entryTemp : entryList) {
                FeeItemEnum feeItem = entryTemp.getFeeItem();
                if (feeItemCfgMap.get(feeItem).isPrincipal()) {
                    principalBalance = principalBalance
                            .subtract(entryTemp.getReceivableAmount());
                }
            }
            //设置计划中的本金结余
            if (ps.getPrincipalBalance() == null || ps.getPrincipalBalance()
                    .compareTo(principalBalance) != 0) {
                //设置计划中的本金结余
                ps.setPrincipalBalance(principalBalance);
            }
            //下一期
            nextPeriod = ps.getNextPeriod();//下一期的期数
        }
    }
    
    /**
     * 对还款计划进行计费变化<br/>
     * 根据计费项目变更对应的还款计划项 根据计费项分项变更对应的还款计划分项 <功能详细描述>
     * 
     * @param chargeRecords [参数说明]
     *            
     * @return void [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    public void charge(List<ChargeRecord> chargeRecords) {
        //如果无计费信息
        if (CollectionUtils.isEmpty(chargeRecords)) {
            return;
        }
        //如果存在
        for (ChargeRecord chargeRecord : chargeRecords) {
            //改变其对应期数的应收
            ScheduleTypeEnum scheduleType = chargeRecord.getScheduleType();
            String period = chargeRecord.getPeriod();
            //获取对应的还款计划
            PaymentSchedule ps = getPaymentSchedule(scheduleType, period);
            
            ps.setReceivableSum(
                    ps.getReceivableSum().add(chargeRecord.getSum())); //平息应收//设置还款计划值变化
            chargeRecord.setPaymentSchedule(ps);//计费记录关联还款记录
            
            //将计费记录分项记录入对应的还款计划项中
            List<ChargeRecordEntry> chargeRecordEntries = chargeRecord
                    .getChargeRecordEntryList();
            for (ChargeRecordEntry chargeRecordEntry : chargeRecordEntries) {
                //根据费用项更新其对应的计划中的数据
                FeeItemEnum feeItem = chargeRecordEntry.getFeeItem();
                PaymentScheduleEntry pse = getPaymentScheduleEntry(scheduleType,
                        period,
                        feeItem);
                
                //改变计划分项金额
                pse.setReceivableAmount(pse.getReceivableAmount()
                        .add(chargeRecordEntry.getAmount()));
                chargeRecordEntry.setPaymentScheduleEntry(pse);//关联计划分项
            }
        }
        calculatePrincipalBalance();
    }
    
    /**
     * 对还款计划进行计费变化<br/>
     * 根据计费项目变更对应的还款计划项 根据计费项分项变更对应的还款计划分项 该功能不能适用于，平息递减计费值不同的情形。 <功能详细描述>
     * 
     * @param chargeRecordMap [参数说明]
     *            
     * @return void [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    public void charge(ScheduleAssociateMap<ChargeRecord> chargeRecordMap) {
        //如果无计费信息
        if (MapUtils.isEmpty(chargeRecordMap)) {
            return;
        }
        //如果存在
        charge(chargeRecordMap.valueList());
    }
    
    /**
     * 对还款计划进行计费变化<br/>
     * 根据计费项目变更对应的还款计划项 根据计费项分项变更对应的还款计划分项 该功能不能适用于，平息递减计费值不同的情形。 <功能详细描述>
     * 
     * @param chargeRecords [参数说明]
     *            
     * @return void [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    public void chargeByEntry(List<ChargeRecordEntry> chargeRecordEntrys) {
        //如果无计费信息
        if (CollectionUtils.isEmpty(chargeRecordEntrys)) {
            return;
        }
        //如果存在
        for (ChargeRecordEntry chargeRecordEntry : chargeRecordEntrys) {
            //改变其对应期数的应收
            ScheduleTypeEnum scheduleType = chargeRecordEntry.getScheduleType();
            String period = chargeRecordEntry.getPeriod();
            
            PaymentSchedule ps = getPaymentSchedule(scheduleType, period);//获取对应的还款计划
            FeeItemEnum feeItem = chargeRecordEntry.getFeeItem();//根据费用项更新其对应的计划中的数据
            PaymentScheduleEntry pse = getPaymentScheduleEntry(scheduleType,
                    period,
                    feeItem);
            BigDecimal chargeEntryValue = chargeRecordEntry.getAmount();
            
            pse.setReceivableAmount(
                    pse.getReceivableAmount().add(chargeEntryValue));//改变计划分项金额
            ps.setReceivableSum(ps.getReceivableSum().add(chargeEntryValue)); //平息应收//设置还款计划值变化
        }
        calculatePrincipalBalance();
    }
    
    /**
     * 对还款计划进行计费变化<br/>
     * 根据计费项目变更对应的还款计划项 根据计费项分项变更对应的还款计划分项 该功能不能适用于，平息递减计费值不同的情形。 <功能详细描述>
     * 
     * @param chargeRecords [参数说明]
     *            
     * @return void [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    public void chargeByEntry(
            ScheduleEntryAssociateMap<ChargeRecordEntry> chargeRecordEntryMap) {
        //如果无计费信息
        if (MapUtils.isEmpty(chargeRecordEntryMap)) {
            return;
        }
        chargeByEntry(chargeRecordEntryMap.valueList());
    }
    
    /**
     * 构建豁免集合<br/>
     * 
     * @param exemptRecordEntryList 豁免分项集合
     * @param tradingRecord 交易记录
     * @param earlySettle 是否提前结清豁免
     * @return [参数说明]
     *         
     * @return List<ExemptRecord> [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    public List<ExemptRecord> buildExemptRecords(
            List<ExemptRecordEntry> exemptRecordEntryList,
            LATradingRecord tradingRecord) {
        if (CollectionUtils.isEmpty(exemptRecordEntryList)) {
            return null;
        }
        
        //校验并压入multiValueMap
        ScheduleEntryAssociateMap<ExemptRecordEntry> exemptRecordEntryMap = new ScheduleEntryAssociateMap<ExemptRecordEntry>(
                exemptRecordEntryList);
        ScheduleAssociateMap<ExemptRecord> exemptRecordMap = new ScheduleAssociateMap<>();
        
        //迭代写入计费记录分项
        for (Entry<ScheduleTypeEnum, Map<String, Map<FeeItemEnum, ExemptRecordEntry>>> entryTemp : exemptRecordEntryMap
                .entrySet()) {
            if (MapUtils.isEmpty(entryTemp.getValue())) {
                continue;
            }
            ScheduleTypeEnum scheduleType = entryTemp.getKey();
            for (Entry<String, Map<FeeItemEnum, ExemptRecordEntry>> et : entryTemp
                    .getValue().entrySet()) {
                String period = et.getKey();
                
                PaymentSchedule paymentSchedule = getPaymentSchedule(
                        scheduleType, period);
                ExemptRecord exemptRecord = new ExemptRecord();
                
                exemptRecord.setPeriod(period);
                exemptRecord.setLoanAccountId(this.loanAccount.getId());
                exemptRecord.setTradingRecord(tradingRecord);
                exemptRecord.setPaymentSchedule(paymentSchedule);
                exemptRecord.setScheduleType(scheduleType);
                exemptRecord.setPeriod(period);
                Date now = new Date();
                exemptRecord.setCreateDate(now);
                exemptRecord.setLastUpdateDate(now);
                
                //从计划中获取source值
                exemptRecord.setSourceSum(paymentSchedule.getExemptSum());
                //计算增量值
                BigDecimal sum = BigDecimal.ZERO; // 增值和
                for (ExemptRecordEntry exemptRecordEntry : et.getValue()
                        .values()) {
                    sum = sum.add(exemptRecordEntry.getAmount());
                }
                exemptRecord.setSum(sum);
                //设置target值
                BigDecimal targetSum = exemptRecord.getSourceSum().add(sum);
                exemptRecord.setTargetSum(targetSum);
                
                //将关联的ChargeRecordEntry设入
                exemptRecord.setExemptRecordEntryList(
                        new ArrayList<>(et.getValue().values()));
                exemptRecordMap.add(exemptRecord);
            }
        }
        
        //返回的豁免记录
        List<ExemptRecord> exemptRecords = exemptRecordMap.valueList();
        return exemptRecords;
    }
    
    /**
     * 根据豁免记录修改还款计划<br/>
     * <功能详细描述>
     * 
     * @param exemptRecords [参数说明]
     *            
     * @return void [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    public void exempt(List<ExemptRecord> exemptRecords) {
        if (CollectionUtils.isEmpty(exemptRecords)) {
            return;
        }
        for (ExemptRecord exemptRecord : exemptRecords) {
            ScheduleTypeEnum scheduleType = exemptRecord.getScheduleType();
            String period = exemptRecord.getPeriod();
            
            PaymentSchedule ps = getPaymentSchedule(scheduleType, period);
            
            ps.setExemptSum(ps.getExemptSum().add(exemptRecord.getSum()));// 改变对应的应收
            exemptRecord.setPaymentSchedule(ps);//豁免记录关联还款记录
            
            //获取分项
            List<ExemptRecordEntry> entryList = exemptRecord
                    .getExemptRecordEntryList();
            for (ExemptRecordEntry entry : entryList) {
                //根据费用项更新其对应的计划中的数据
                FeeItemEnum feeItem = entry.getFeeItem();
                PaymentScheduleEntry pse = getPaymentScheduleEntry(scheduleType,
                        period,
                        feeItem);
                pse.setExemptAmount(
                        pse.getExemptAmount().add(entry.getAmount()));
                entry.setPaymentScheduleEntry(pse);//豁免记录分项关联还款分项
                
                ////计划中豁免金额 应 == entry的TargetAmount
                //AssertUtils.isTrue(paymentScheduleEntry.getExemptAmount().equals(entry.getTargetAmount()),
                //        "scheduleEntry,exemptAmount:{} != chargeRecordEntry.targetAmount:{}",
                //        new Object[] { paymentScheduleEntry.getExemptAmount(), entry.getTargetAmount() });
            }
        }
    }
    
    /**
     * 根据豁免记录修改还款计划<br/>
     * 该功能不能适用于平息递减值不同的情形<br/>
     * <功能详细描述>
     * 
     * @param exemptRecords [参数说明]
     *            
     * @return void [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    public void exempt(ScheduleAssociateMap<ExemptRecord> exemptRecordMap) {
        if (MapUtils.isEmpty(exemptRecordMap)) {
            return;
        }
        exempt(exemptRecordMap.valueList());
    }
    
    /**
     * 对还款计划进行计费变化<br/>
     * 根据计费项目变更对应的还款计划项 根据计费项分项变更对应的还款计划分项 该功能不能适用于，平息递减计费值不同的情形。 <功能详细描述>
     * 
     * @param chargeRecords [参数说明]
     *            
     * @return void [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    public void exemptByEntry(List<ExemptRecordEntry> exemptRecordEntrys) {
        //如果无计费信息
        if (CollectionUtils.isEmpty(exemptRecordEntrys)) {
            return;
        }
        //如果存在
        for (ExemptRecordEntry exemptRecordEntry : exemptRecordEntrys) {
            //改变其对应期数的应收
            ScheduleTypeEnum scheduleType = exemptRecordEntry.getScheduleType();
            String period = exemptRecordEntry.getPeriod();
            
            PaymentSchedule ps = getPaymentSchedule(scheduleType, period);//获取对应的还款计划
            FeeItemEnum feeItem = exemptRecordEntry.getFeeItem();//根据费用项更新其对应的计划中的数据
            PaymentScheduleEntry pse = getPaymentScheduleEntry(scheduleType,
                    period,
                    feeItem);
            BigDecimal exemptEntryValue = exemptRecordEntry.getAmount();
            
            pse.setExemptAmount(pse.getExemptAmount().add(exemptEntryValue));//改变计划分项金额
            ps.setExemptSum(ps.getExemptSum().add(exemptEntryValue)); //平息应收//设置还款计划值变化
        }
        calculatePrincipalBalance();
    }
    
    /**
     * 对还款计划进行计费变化<br/>
     * 根据计费项目变更对应的还款计划项 根据计费项分项变更对应的还款计划分项 该功能不能适用于，平息递减计费值不同的情形。 <功能详细描述>
     * 
     * @param chargeRecords [参数说明]
     *            
     * @return void [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    public void exemptByEntry(
            ScheduleEntryAssociateMap<ExemptRecordEntry> exemptRecordEntryMap) {
        //如果无计费信息
        if (MapUtils.isEmpty(exemptRecordEntryMap)) {
            return;
        }
        exemptByEntry(exemptRecordEntryMap.valueList());
    }
    
    /**
     * 构建还款计划集合<br/>
     * <功能详细描述>
     * 
     * @param paymentRecordEntryList 还款计划分项
     * @param tradingRecord 交易记录
     * @return [参数说明]
     *         
     * @return List<PaymentRecord> [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    public List<PaymentRecord> buildPaymentRecords(
            List<PaymentRecordEntry> paymentRecordEntryList,
            LATradingRecord buildTradingRecord) {
        if (CollectionUtils.isEmpty(paymentRecordEntryList)) {
            return null;
        }
        
        MultiValueMap<LATradingRecord, PaymentRecordEntry> receivedPreMMap = new LinkedMultiValueMap<>();
        MultiValueMap<LATradingRecord, PaymentRecordEntry> notReceivedPreMMap = new LinkedMultiValueMap<>();
        for (PaymentRecordEntry pre : paymentRecordEntryList) {
            if (pre.isReceived()) {
                receivedPreMMap.add(pre.getTradingRecord(), pre);
            } else {
                notReceivedPreMMap.add(pre.getTradingRecord(), pre);
            }
        }
        
        List<PaymentRecord> paymentRecords = new ArrayList<>();
        for (Entry<LATradingRecord, List<PaymentRecordEntry>> preMMapEntry : receivedPreMMap
                .entrySet()) {
            LATradingRecord tradingRecordTemp = preMMapEntry.getKey();
            //校验并压入multiValueMap
            ScheduleEntryAssociateMap<PaymentRecordEntry> paymentRecordEntryMap = new ScheduleEntryAssociateMap<PaymentRecordEntry>(
                    preMMapEntry.getValue());
            ScheduleAssociateMap<PaymentRecord> paymentRecordMap = new ScheduleAssociateMap<>();
            //迭代写入计费记录分项
            for (Entry<ScheduleTypeEnum, Map<String, Map<FeeItemEnum, PaymentRecordEntry>>> entryTemp : paymentRecordEntryMap
                    .entrySet()) {
                if (MapUtils.isEmpty(entryTemp.getValue())) {
                    continue;
                }
                ScheduleTypeEnum scheduleType = entryTemp.getKey();
                for (Entry<String, Map<FeeItemEnum, PaymentRecordEntry>> et : entryTemp
                        .getValue().entrySet()) {
                    String period = et.getKey();
                    
                    PaymentSchedule paymentSchedule = getPaymentSchedule(
                            scheduleType, period);
                    
                    PaymentRecord paymentRecord = new PaymentRecord();
                    paymentRecord.setLoanAccountId(this.loanAccount.getId());
                    paymentRecord.setTradingRecord(tradingRecordTemp);
                    paymentRecord.setBuildTradingRecord(buildTradingRecord);
                    paymentRecord.setPaymentSchedule(paymentSchedule);
                    paymentRecord.setScheduleType(scheduleType);
                    paymentRecord.setPeriod(period);
                    paymentRecord
                            .setExpireDate(paymentSchedule.getRepaymentDate());
                    
                    Date now = new Date();
                    paymentRecord.setCreateDate(now);
                    paymentRecord.setLastUpdateDate(now);
                    paymentRecord.setRepayDate(buildTradingRecord == null
                            ? new Date() : buildTradingRecord.getRepayDate());
                    
                    paymentRecord.setReceived(true);
                    paymentRecord.setReceiveDate(now);
                    
                    //从计划中获取source值
                    paymentRecord.setSourceSum(
                            paymentSchedule.getActualReceivedSum());
                    //计算增量值
                    BigDecimal sum = BigDecimal.ZERO; // 增值和
                    for (PaymentRecordEntry paymentRecordEntry : et.getValue()
                            .values()) {
                        sum = sum.add(paymentRecordEntry.getAmount());
                    }
                    paymentRecord.setSum(sum);
                    BigDecimal targetSum = paymentRecord.getSourceSum()
                            .add(sum);//设置target值
                    paymentRecord.setTargetSum(targetSum);
                    
                    //将关联的ChargeRecordEntry设入
                    paymentRecord.setPaymentRecordEntryList(
                            new ArrayList<>(et.getValue().values()));
                    paymentRecordMap.add(paymentRecord);
                }
            }
            paymentRecords.addAll(paymentRecordMap.valueList());
        }
        
        for (Entry<LATradingRecord, List<PaymentRecordEntry>> preMMapEntry : notReceivedPreMMap
                .entrySet()) {
            LATradingRecord tradingRecordTemp = preMMapEntry.getKey();
            //校验并压入multiValueMap
            ScheduleEntryAssociateMap<PaymentRecordEntry> paymentRecordEntryMap = new ScheduleEntryAssociateMap<PaymentRecordEntry>(
                    preMMapEntry.getValue());
            ScheduleAssociateMap<PaymentRecord> paymentRecordMap = new ScheduleAssociateMap<>();
            //迭代写入计费记录分项
            for (Entry<ScheduleTypeEnum, Map<String, Map<FeeItemEnum, PaymentRecordEntry>>> entryTemp : paymentRecordEntryMap
                    .entrySet()) {
                if (MapUtils.isEmpty(entryTemp.getValue())) {
                    continue;
                }
                ScheduleTypeEnum scheduleType = entryTemp.getKey();
                for (Entry<String, Map<FeeItemEnum, PaymentRecordEntry>> et : entryTemp
                        .getValue().entrySet()) {
                    String period = et.getKey();
                    
                    PaymentSchedule paymentSchedule = getPaymentSchedule(
                            scheduleType, period);
                    
                    PaymentRecord paymentRecord = new PaymentRecord();
                    paymentRecord.setPeriod(period);
                    paymentRecord.setLoanAccountId(this.loanAccount.getId());
                    paymentRecord.setTradingRecord(tradingRecordTemp);
                    paymentRecord.setBuildTradingRecord(buildTradingRecord);
                    paymentRecord.setPaymentSchedule(paymentSchedule);
                    paymentRecord.setScheduleType(scheduleType);
                    paymentRecord.setPeriod(period);
                    
                    Date now = new Date();
                    paymentRecord.setCreateDate(now);
                    paymentRecord.setLastUpdateDate(now);
                    paymentRecord.setRepayDate(buildTradingRecord == null
                            ? new Date() : buildTradingRecord.getRepayDate());
                    
                    paymentRecord.setReceived(false);
                    paymentRecord.setReceiveDate(null);
                    
                    //从计划中获取source值
                    paymentRecord.setSourceSum(
                            paymentSchedule.getActualReceivedSum());
                    //计算增量值
                    BigDecimal sum = BigDecimal.ZERO; // 增值和
                    for (PaymentRecordEntry paymentRecordEntry : et.getValue()
                            .values()) {
                        sum = sum.add(paymentRecordEntry.getAmount());
                    }
                    paymentRecord.setSum(sum);
                    //设置target值
                    BigDecimal targetSum = paymentRecord.getSourceSum()
                            .add(sum);
                    paymentRecord.setTargetSum(targetSum);
                    
                    //将关联的ChargeRecordEntry设入
                    paymentRecord.setPaymentRecordEntryList(
                            new ArrayList<>(et.getValue().values()));
                    paymentRecordMap.add(paymentRecord);
                }
            }
            paymentRecords.addAll(paymentRecordMap.valueList());
        }
        return paymentRecords;
    }
    
    /**
     * 根据paymentRecord记录修改还款计划 <功能详细描述>
     * 
     * @param paymentRecords [参数说明]
     *            
     * @return void [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    public void payment(List<PaymentRecord> paymentRecords) {
        //无计费
        if (CollectionUtils.isEmpty(paymentRecords)) {
            return;
        }
        // 存在计费
        for (PaymentRecord paymentRecord : paymentRecords) {
            if (!paymentRecord.isReceived()) {
                continue;
            }
            //改变其对应期数的应收
            ScheduleTypeEnum scheduleType = paymentRecord.getScheduleType();
            String period = paymentRecord.getPeriod();
            
            PaymentSchedule ps = getPaymentSchedule(scheduleType, period);//获取对应的还款计划
            
            //改变对应的实收
            ps.setLastRepayDate(paymentRecord.getRepayDate());
            ps.setActualReceivedSum(
                    ps.getActualReceivedSum().add(paymentRecord.getSum())); //平息实收
            paymentRecord.setPaymentSchedule(ps);//关联计划项
            
            //获取分项
            List<PaymentRecordEntry> entryList = paymentRecord
                    .getPaymentRecordEntryList();
            for (PaymentRecordEntry entry : entryList) {
                //根据费用项更新其对应的计划中的数据
                FeeItemEnum feeItem = entry.getFeeItem();
                PaymentScheduleEntry pse = getPaymentScheduleEntry(scheduleType,
                        period,
                        feeItem);
                
                pse.setActualReceivedAmount(
                        pse.getActualReceivedAmount().add(entry.getAmount()));
                entry.setPaymentScheduleEntry(pse);//关联计划分项
            }
        }
    }
    
    /**
     * 校验贷款账户是否逾期 <功能详细描述>
     * 
     * @return [参数说明]
     *         
     * @return boolean [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    public boolean isOverdue() {
        boolean isOverdue = false;
        
        Date nowDate = new Date();
        isOverdue = isOverdue(nowDate);
        return isOverdue;
    }
    
    /**
     * 校验贷款账户是否逾期 <功能详细描述>
     * @return [参数说明]
     *         
     * @return boolean [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    public boolean isOverdue(Date processDate) {
        AssertUtils.notNull(processDate, "processDate is null.");
        
        boolean isOverdue = false;
        String nextPeriod = "1";
        while (!StringUtils.isBlank(nextPeriod)) {
            PaymentSchedule paymentSchedule = this.paymentScheduleMap
                    .get(ScheduleTypeEnum.MAIN, nextPeriod);
            
            //如果nowDate <= RepaymentDate 即对应期数还没有到，所以计算逾期不会计算到对应还款计划
            if (DateUtils.compareByDay(processDate,
                    paymentSchedule.getRepaymentDate()) > 0) {
                //是否逾期仅看平息账中逾期依赖费用项
                for (PaymentScheduleEntry entryTemp : paymentSchedule
                        .getPaymentScheduleEntryList()) {
                    FeeItemEnum feeItem = entryTemp.getFeeItem();
                    if (!this.feeItemCfgMap.get(feeItem).isOverdueDepend()) {
                        //非逾期依赖费用项直接跳过
                        continue;
                    }
                    BigDecimal check = entryTemp.getReceivableAmount()
                            .add(entryTemp.getExemptAmount())
                            .subtract(entryTemp.getActualReceivedAmount());
                    if (check.compareTo(BigDecimal.ZERO) > 0) {
                        isOverdue = true;
                        break;
                    }
                }
            } else {
                break;
            }
            nextPeriod = paymentSchedule.getNextPeriod();
        }
        return isOverdue;
    }
    
    /**
     * 获取逾期日期<br/>
     * <功能详细描述>
     * 
     * @return [参数说明]
     *         
     * @return Date [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    public Date getOverdueDate() {
        Date nowDate = new Date();
        Date overdueDate = getOverdueDate(nowDate);
        
        return overdueDate;
    }
    
    /**
     * 获取逾期日期<br/>
     * <功能详细描述>
     * 
     * @return [参数说明]
     *         
     * @return Date [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    public Date getOverdueDate(Date processDate) {
        AssertUtils.notNull(processDate, "processDate is null.");
        
        Date overdueDate = null;
        String nextPeriod = "1";
        while (!StringUtils.isBlank(nextPeriod)) {
            PaymentSchedule paymentSchedule = this.paymentScheduleMap
                    .get(ScheduleTypeEnum.MAIN, nextPeriod);
            
            if (DateUtils.compareByDay(processDate,
                    paymentSchedule.getRepaymentDate()) > 0) {
                //是否逾期仅看平息账
                BigDecimal check = paymentSchedule.getReceivableSum()
                        .add(paymentSchedule.getExemptSum())
                        .subtract(paymentSchedule.getActualReceivedSum());
                if (check.compareTo(BigDecimal.ZERO) > 0) {
                    overdueDate = paymentSchedule.getRepaymentDate();
                    break;
                }
            } else {
                break;
            }
            nextPeriod = paymentSchedule.getNextPeriod();
        }
        if (overdueDate == null) {
            return null;
        }
        //逾期日期为还款日当日的00:00:00
        DateTime repaymentDateTimeTemp = new DateTime(overdueDate);
        DateTime overdueDateTimeTemp = new DateTime(
                repaymentDateTimeTemp.getYear(),
                repaymentDateTimeTemp.getMonthOfYear(),
                repaymentDateTimeTemp.getDayOfMonth(), 0, 0, 0);
        //获取逾期日期
        return overdueDateTimeTemp.toDate();
    }
    
    /**
     * 获取逾期金额<br/>
     * <功能详细描述>
     * @param processDate
     * @return [参数说明]
     * 
     * @return BigDecimal [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    public BigDecimal getOverdueAmount(Date processDate,
            ScheduleTypeEnum scheduleType) {
        BigDecimal overdueAmount = getOverdueAmount(processDate,
                scheduleType,
                null);
        return overdueAmount;
    }
    
    /**
     * 获取逾期日期<br/>
     * <功能详细描述>
     * 
     * @return [参数说明]
     *         
     * @return Date [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    public BigDecimal getOverdueAmount(Date processDate,
            ScheduleTypeEnum scheduleType, Collection<FeeItemEnum> feeItems) {
        AssertUtils.notNull(processDate, "processDate is null.");
        
        BigDecimal overdueAmount = BigDecimal.ZERO;
        String nextPeriod = "1";
        while (!StringUtils.isBlank(nextPeriod)) {
            PaymentSchedule paymentSchedule = this.paymentScheduleMap
                    .get(scheduleType, nextPeriod);
            
            if (DateUtils.compareByDay(processDate,
                    paymentSchedule.getRepaymentDate()) > 0) {
                for (PaymentScheduleEntry pse : paymentSchedule
                        .getPaymentScheduleEntryList()) {
                    if (!CollectionUtils.isEmpty(feeItems)
                            && !feeItems.contains(pse.getFeeItem())) {
                        continue;
                    }
                    //是否逾期仅看平息账
                    BigDecimal overdueAmountTemp = pse.getReceivableAmount()
                            .add(pse.getExemptAmount())
                            .subtract(pse.getActualReceivedAmount());
                    overdueAmount = overdueAmount.add(overdueAmountTemp);
                }
            } else {
                break;
            }
            nextPeriod = paymentSchedule.getNextPeriod();
        }
        return overdueAmount;
    }
    
    /**
     * 获取逾期金额<br/>
     * <功能详细描述>
     * @param processDate
     * @return [参数说明]
     * 
     * @return BigDecimal [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    public BigDecimal getOverdueSum(Date processDate,
            ScheduleTypeEnum scheduleType) {
        BigDecimal overdueAmount = getOverdueSum(processDate,
                scheduleType,
                null);
        return overdueAmount;
    }
    
    /**
     * 获取逾期日期<br/>
     * <功能详细描述>
     * 
     * @return [参数说明]
     *         
     * @return Date [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    public BigDecimal getOverdueSum(Date processDate,
            ScheduleTypeEnum scheduleType, Collection<FeeItemEnum> feeItems) {
        AssertUtils.notNull(processDate, "processDate is null.");
        
        BigDecimal overdueAmount = BigDecimal.ZERO;
        for (PaymentSchedule paymentSchedule : this.paymentScheduleMap
                .valueList(scheduleType)) {
            if (!paymentSchedule.getPeriod().equals("NA")
                    && DateUtils.compareByDay(processDate,
                            paymentSchedule.getRepaymentDate()) > 0) {
                for (PaymentScheduleEntry pse : paymentSchedule
                        .getPaymentScheduleEntryList()) {
                    if (!CollectionUtils.isEmpty(feeItems)
                            && !feeItems.contains(pse.getFeeItem())) {
                        continue;
                    }
                    //是否逾期仅看平息账
                    BigDecimal overdueAmountTemp = pse.getReceivableAmount()
                            .add(pse.getExemptAmount())
                            .subtract(pse.getActualReceivedAmount());
                    overdueAmount = overdueAmount.add(overdueAmountTemp);
                }
            }
        }
        return overdueAmount;
    }
    
    /**
     * 获取逾期日期<br/>
     * <功能详细描述>
     * 
     * @return [参数说明]
     *         
     * @return Date [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    public BigDecimal getPrincipalBalance(ScheduleTypeEnum scheduleType) {
        AssertUtils.notNull(scheduleType, "scheduleType is null.");
        
        BigDecimal principalBalance = BigDecimal.ZERO;
        for (PaymentScheduleEntry pse : this.paymentScheduleEntryMap
                .valueList(scheduleType)) {
            FeeItemEnum feeItem = pse.getFeeItem();
            if (!this.feeItemCfgMap.get(feeItem).isPrincipal()) {
                continue;
            }
            
            //是否逾期仅看平息账
            BigDecimal amount = pse.getReceivableAmount()
                    .add(pse.getExemptAmount())
                    .subtract(pse.getActualReceivedAmount());
            principalBalance = principalBalance.add(amount);
        }
        return principalBalance;
    }
    
    /**
     * 获取当前期数<br/>
     * <功能详细描述>
     * 
     * @return [参数说明]
     *         
     * @return String [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    public String getCurrentPeriod() {
        String currentPeriod = "1";
        String nextPeriod = "1";
        while (!StringUtils.isBlank(nextPeriod)) {
            PaymentSchedule paymentSchedule = this.paymentScheduleMap
                    .get(ScheduleTypeEnum.MAIN, nextPeriod);
            
            //是否逾期仅看平息账
            BigDecimal check = paymentSchedule.getReceivableSum()
                    .add(paymentSchedule.getExemptSum())
                    .subtract(paymentSchedule.getActualReceivedSum());
            //当前期数
            currentPeriod = nextPeriod;
            if (check.compareTo(BigDecimal.ZERO) > 0) {
                break;
            }
            nextPeriod = paymentSchedule.getNextPeriod();
        }
        return currentPeriod;
    }
    
    /**
     * 计算当前期数到期日<br/>
     * <功能详细描述>
     * 
     * @return [参数说明]
     *         
     * @return Date [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    public Date caculateCurrentPeriodExpireDate() {
        String currentPeriod = getCurrentPeriod();
        PaymentSchedule paymentSchedule = this.paymentScheduleMap
                .get(ScheduleTypeEnum.MAIN, currentPeriod);
        
        Date currentPeriodExpireDate = paymentSchedule.getRepaymentDate();
        return currentPeriodExpireDate;
    }
    
    /**
     * 根据还款计划获取首期还款日<br/>
     * <功能详细描述>
     * 
     * @return [参数说明]
     *         
     * @return Date [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    public Date getFirstMonthRepayDateByPaymentSchedule() {
        PaymentSchedule paymentSchedule = this.paymentScheduleMap
                .get(ScheduleTypeEnum.MAIN, "1");
        
        Date firstMonthRepayDate = paymentSchedule.getRepaymentDate();
        return firstMonthRepayDate;
    }
    
    /**
     * 根据还款计划重新计算下次还款日<br/>
     * <功能详细描述>
     * 
     * @return [参数说明]
     *         
     * @return Date [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    public Date getNextRepayDate() {
        String nextPeriod = "1";
        Date nextRepayDate = null;
        while (!StringUtils.isBlank(nextPeriod)) {
            PaymentSchedule paymentSchedule = this.paymentScheduleMap
                    .get(ScheduleTypeEnum.MAIN, nextPeriod);
            
            //当前期数
            if (paymentSchedule.getReceivableSum()
                    .add(paymentSchedule.getExemptSum())
                    .subtract(paymentSchedule.getActualReceivedSum())
                    .compareTo(BigDecimal.ZERO) > 0) {
                nextRepayDate = paymentSchedule.getRepaymentDate();
                break;
            }
            nextRepayDate = paymentSchedule.getRepaymentDate();
            nextPeriod = paymentSchedule.getNextPeriod();
        }
        
        return nextRepayDate;
    }
    
    //    /**
    //     * 根据还款计划重新计算下次还款日<br/>
    //     * <功能详细描述>
    //     * 
    //     * @return [参数说明]
    //     *         
    //     * @return Date [返回类型说明]
    //     * @exception throws [异常类型] [异常说明]
    //     * @see [类、类#方法、类#成员]
    //     */
    //    public Date getNextDeductDate() {
    //        DateTime now = DateTime.now();
    //        
    //        //String currentPeriod = "1";
    //        String nextPeriod = "1";
    //        Date nextRepayDate = null;
    //        while (!StringUtils.isBlank(nextPeriod)) {
    //            PaymentSchedule paymentSchedule = this.paymentScheduleMap
    //                    .get(ScheduleTypeEnum.MAIN, nextPeriod);
    //            
    //            //当前期数
    //            if (DateUtils.compareByDay(paymentSchedule.getRepaymentDate(),
    //                    now.toDate()) > 0) {
    //                nextRepayDate = paymentSchedule.getRepaymentDate();
    //                break;
    //            }
    //            nextPeriod = paymentSchedule.getNextPeriod();
    //        }
    //        
    //        //当所有还款计划均已到期的情况下这个值可能为空
    //        if (nextRepayDate == null) {
    //            DateTime nextRepayDateTime = null;
    //            
    //            if (now.getDayOfMonth() < this.loanAccount.getMonthlyRepayDay()) {
    //                //如果当天尚未到当月的每月还款日，则构建当月的还款日期
    //                Calendar cale = Calendar.getInstance();
    //                cale.setTime(now.toDate());
    //                if (this.loanAccount.getMonthlyRepayDay() <= cale
    //                        .getActualMaximum(Calendar.DAY_OF_MONTH)) {
    //                    nextRepayDateTime = new DateTime(now.getYear(),
    //                            now.getMonthOfYear(),
    //                            this.loanAccount.getMonthlyRepayDay(), 23, 59, 59);
    //                } else {
    //                    nextRepayDateTime = new DateTime(now.getYear(),
    //                            now.getMonthOfYear(),
    //                            cale.getActualMaximum(Calendar.DAY_OF_MONTH), 23,
    //                            59, 59);
    //                }
    //            } else {
    //                DateTime nextMonthDay = now.plusMonths(1);
    //                //如果当天尚未到当月的每月还款日，则构建当月的还款日期
    //                Calendar cale = Calendar.getInstance();
    //                cale.setTime(nextMonthDay.toDate());
    //                
    //                if (this.loanAccount.getMonthlyRepayDay() <= cale
    //                        .getActualMaximum(Calendar.DAY_OF_MONTH)) {
    //                    nextRepayDateTime = new DateTime(nextMonthDay.getYear(),
    //                            nextMonthDay.getMonthOfYear(),
    //                            this.loanAccount.getMonthlyRepayDay(), 23, 59, 59);
    //                } else {
    //                    nextRepayDateTime = new DateTime(nextMonthDay.getYear(),
    //                            nextMonthDay.getMonthOfYear(),
    //                            cale.getActualMaximum(Calendar.DAY_OF_MONTH), 23,
    //                            59, 59);
    //                }
    //            }
    //            nextRepayDate = nextRepayDateTime.toDate();
    //        }
    //        
    //        return nextRepayDate;
    //    }
    
    /**
     * 根据还款计划重新计算下次还款日<br/>
     * <功能详细描述>
     * 
     * @return [参数说明]
     *         
     * @return Date [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    public BigDecimal caculateNextRepayAmount() {
        Date nextRepayDate = this.getNextRepayDate();
        BigDecimal nextRepayAmount = BigDecimal.ZERO;
        String nextPeriod = "1";
        while (!StringUtils.isBlank(nextPeriod)) {
            PaymentSchedule paymentSchedule = this.paymentScheduleMap
                    .get(ScheduleTypeEnum.MAIN, nextPeriod);
            
            //当前期数
            if (DateUtils.compareByDay(paymentSchedule.getRepaymentDate(),
                    nextRepayDate) <= 0) {
                nextRepayAmount = nextRepayAmount.add(paymentSchedule
                        .getReceivableSum()
                        .add(paymentSchedule.getExemptSum())
                        .subtract(paymentSchedule.getActualReceivedSum()));
                break;
            }
            nextPeriod = paymentSchedule.getNextPeriod();
        }
        //将非Digist的期数添加进去
        for (PaymentSchedule paymentSchedule : this.paymentScheduleMap
                .valueList(ScheduleTypeEnum.MAIN)) {
            if (NumberUtils.isDigits(paymentSchedule.getPeriod())) {
                continue;
            }
            nextRepayAmount = nextRepayAmount
                    .add(paymentSchedule.getReceivableSum()
                            .add(paymentSchedule.getExemptSum())
                            .subtract(paymentSchedule.getActualReceivedSum()));
        }
        
        return nextRepayAmount;
    }
    
    /**
     * 获取已付期数 <功能详细描述>
     * 
     * @return [参数说明]
     *         
     * @return BigDecimal [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    public BigDecimal getPaidPeriod() {
        BigDecimal paidPeriod = BigDecimal.ZERO;
        
        String nextPeriod = "1";
        while (!StringUtils.isBlank(nextPeriod)) {
            PaymentSchedule paymentSchedule = this.paymentScheduleMap
                    .get(ScheduleTypeEnum.MAIN, nextPeriod);
            
            //是否逾期仅看平息账
            BigDecimal check = paymentSchedule.getReceivableSum()
                    .add(paymentSchedule.getExemptSum())
                    .subtract(paymentSchedule.getActualReceivedSum());
            //一直计算到未结清的一期
            if (check.compareTo(BigDecimal.ZERO) > 0) {
                paidPeriod = paidPeriod
                        .add(paymentSchedule.getActualReceivedSum()
                                .divide(paymentSchedule.getReceivableSum()
                                        .add(paymentSchedule.getExemptSum()),
                                        2,
                                        BigDecimal.ROUND_DOWN));
                break;
            } else {
                paidPeriod = paidPeriod.add(BigDecimal.ONE);
            }
            nextPeriod = paymentSchedule.getNextPeriod();
        }
        return paidPeriod;
    }
    
    /**
     * 是否正常结清 <功能详细描述>
     * 
     * @return [参数说明]
     *         
     * @return boolean [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    public boolean isFPSettle() {
        for (PaymentScheduleEntry entry : this.paymentScheduleEntryMap
                .valueList(ScheduleTypeEnum.MAIN)) {
            BigDecimal receivableAmount = entry.getReceivableAmount();
            BigDecimal exemptAmount = entry.getExemptAmount();
            BigDecimal actualReceivedAmount = entry.getActualReceivedAmount();
            BigDecimal check = receivableAmount.add(exemptAmount)
                    .subtract(actualReceivedAmount);
            
            if (check.compareTo(LoanAccountConstants.ONE_CENT) >= 0) {
                //校验金额小于1分
                return false;
            }
        }
        return true;
    }
    
    /**
     * 是否已将逾期依赖结清 <功能详细描述>
     * 
     * @return [参数说明]
     *         
     * @return boolean [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    public boolean isSLSettle() {
        for (PaymentScheduleEntry entry : this.paymentScheduleEntryMap
                .valueList(ScheduleTypeEnum.MAIN)) {
            if (!this.feeItemCfgMap.get(entry.getFeeItem()).isOverdueDepend()) {
                continue;
            }
            BigDecimal receivableAmount = entry.getReceivableAmount();
            BigDecimal exemptAmount = entry.getExemptAmount();
            BigDecimal actualReceivedAmount = entry.getActualReceivedAmount();
            BigDecimal check = receivableAmount.add(exemptAmount)
                    .subtract(actualReceivedAmount);
            if (check.compareTo(LoanAccountConstants.ONE_CENT) >= 0) {
                //校验金额小于1分
                return false;
            }
        }
        return true;
    }
    
    /**
     * @return
     * @throws CloneNotSupportedException
     */
    @Override
    public Object clone() {
        PaymentScheduleHandler newHandler = new PaymentScheduleHandler(
                ObjectUtils.deepClone(loanAccount), this.feeItemCfgMap,
                this.feeItemMap,
                ObjectUtils.deepClone(this.paymentScheduleList),
                ObjectUtils.deepClone(this.paymentScheduleEntryList));
        newHandler.init();
        return newHandler;
    }
    
    /**
     * @param feeItem
     * @return
     */
    public Map<FeeItemEnum, BigDecimal> getReceivableFeeItem2SumMap(
            ScheduleTypeEnum scheduleType) {
        AssertUtils.notNull(scheduleType, "scheduleType is null.");
        
        Map<FeeItemEnum, BigDecimal> resMap = new HashMap<>();
        for (PaymentScheduleEntry entry : this.paymentScheduleEntryMap
                .valueList(scheduleType)) {
            FeeItemEnum fiTemp = entry.getFeeItem();
            if (!resMap.containsKey(fiTemp)) {
                resMap.put(fiTemp, BigDecimal.ZERO);
            }
            resMap.put(fiTemp,
                    resMap.get(fiTemp).add(entry.getReceivableAmount()));
        }
        return resMap;
    }
    
    /**
     * @param feeItem
     * @return
     */
    public BigDecimal getReceivableSum(ScheduleTypeEnum scheduleType,
            FeeItemEnum feeItem) {
        AssertUtils.notNull(scheduleType, "scheduleType is null.");
        AssertUtils.notNull(feeItem, "feeItem is null.");
        
        BigDecimal sum = BigDecimal.ZERO;
        for (PaymentScheduleEntry entry : this.paymentScheduleEntryMap
                .valueList(scheduleType, feeItem)) {
            sum = sum.add(entry.getReceivableAmount());
        }
        return sum;
    }
    
    /**
     * @param feeItem
     * @return
     */
    public BigDecimal getMainReceivableSum(FeeItemEnum feeItem) {
        AssertUtils.notNull(feeItem, "feeItem is null.");
        
        BigDecimal sum = getReceivableSum(ScheduleTypeEnum.MAIN, feeItem);
        return sum;
    }
    
    /**
     * @param feeItem
     * @return
     */
    public BigDecimal getActualReceivedSum(ScheduleTypeEnum scheduleType,
            FeeItemEnum feeItem) {
        AssertUtils.notNull(scheduleType, "scheduleType is null.");
        AssertUtils.notNull(feeItem, "feeItem is null.");
        
        BigDecimal sum = BigDecimal.ZERO;
        for (PaymentScheduleEntry entry : this.paymentScheduleEntryMap
                .valueList(scheduleType, feeItem)) {
            sum = sum.add(entry.getActualReceivedAmount());
        }
        return sum;
    }
    
    /**
     * @param feeItem
     * @return
     */
    public BigDecimal getMainActualReceivedSum(FeeItemEnum feeItem) {
        AssertUtils.notNull(feeItem, "feeItem is null.");
        
        BigDecimal sum = getActualReceivedSum(ScheduleTypeEnum.MAIN, feeItem);
        return sum;
    }
    
    /**
     * @param feeItem
     * @return
     */
    public BigDecimal getExemptSum(ScheduleTypeEnum scheduleType,
            FeeItemEnum feeItem) {
        AssertUtils.notNull(scheduleType, "scheduleType is null.");
        AssertUtils.notNull(feeItem, "feeItem is null.");
        
        BigDecimal sum = BigDecimal.ZERO;
        for (PaymentScheduleEntry entry : this.paymentScheduleEntryMap
                .valueList(scheduleType, feeItem)) {
            sum = sum.add(entry.getExemptAmount());
        }
        return sum;
    }
    
    /**
     * @param feeItem
     * @return
     */
    public BigDecimal getMainExemptSum(FeeItemEnum feeItem) {
        AssertUtils.notNull(feeItem, "feeItem is null.");
        
        BigDecimal sum = getExemptSum(ScheduleTypeEnum.MAIN, feeItem);
        return sum;
    }
    
    /**
     * @param feeItem
     * @return
     */
    public BigDecimal getSum(ScheduleTypeEnum scheduleType,
            FeeItemEnum feeItem) {
        AssertUtils.notNull(scheduleType, "scheduleType is null.");
        AssertUtils.notNull(feeItem, "feeItem is null.");
        
        BigDecimal sum = BigDecimal.ZERO;
        for (PaymentScheduleEntry entry : this.paymentScheduleEntryMap
                .valueList(scheduleType, feeItem)) {
            sum = sum.add(entry.getReceivableAmount())
                    .add(entry.getExemptAmount())
                    .subtract(entry.getActualReceivedAmount());
        }
        return sum;
    }
    
    /**
     * @param feeItem
     * @return
     */
    public BigDecimal getMainSum(FeeItemEnum feeItem) {
        AssertUtils.notNull(feeItem, "feeItem is null.");
        
        BigDecimal sum = getSum(ScheduleTypeEnum.MAIN, feeItem);
        return sum;
    }
    
    /**
     * @param feeItem
     * @return
     */
    public BigDecimal getSumByScheduleType(ScheduleTypeEnum scheduleType) {
        AssertUtils.notNull(scheduleType, "scheduleType is null.");
        
        BigDecimal sum = BigDecimal.ZERO;
        for (PaymentScheduleEntry entry : this.paymentScheduleEntryMap
                .valueList(scheduleType)) {
            sum = sum.add(entry.getReceivableAmount())
                    .add(entry.getExemptAmount())
                    .subtract(entry.getActualReceivedAmount());
        }
        return sum;
    }
    
    /**
     * @param feeItem
     * @return
     */
    public BigDecimal getSum() {
        BigDecimal sum = getSumByScheduleType(ScheduleTypeEnum.MAIN);
        return sum;
    }
    
    /**
     * @param feeItem
     * @return
     */
    public BigDecimal getSumByFeeItems(ScheduleTypeEnum scheduleType,
            Collection<FeeItemEnum> feeItemCollection) {
        AssertUtils.notNull(scheduleType, "scheduleType is null.");
        AssertUtils.notEmpty(feeItemCollection, "feeItemCollection is empty.");
        
        BigDecimal sum = BigDecimal.ZERO;
        for (PaymentScheduleEntry entry : this.paymentScheduleEntryMap
                .valueList(scheduleType)) {
            if (!feeItemCollection.contains(entry.getFeeItem())) {
                continue;
            }
            sum = sum.add(entry.getReceivableAmount())
                    .add(entry.getExemptAmount())
                    .subtract(entry.getActualReceivedAmount());
        }
        return sum;
    }
    
    /**
     * @param feeItem
     * @return
     */
    public BigDecimal getMainSumByFeeItems(
            Collection<FeeItemEnum> feeItemCollection) {
        AssertUtils.notEmpty(feeItemCollection, "feeItemCollection is empty.");
        
        BigDecimal sum = getSumByFeeItems(ScheduleTypeEnum.MAIN,
                feeItemCollection);
        return sum;
    }
    
    /**
     * 根据期数获取还款计划分项列表<br/>
     * <功能详细描述>
     * 
     * @param period
     * @return [参数说明]
     *         
     * @return List<PaymentScheduleEntry> [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    public PaymentSchedule getPaymentScheduleByPeriod(
            ScheduleTypeEnum scheduleType, String period) {
        AssertUtils.notNull(scheduleType, "scheduleType is null.");
        AssertUtils.notEmpty(period, "period is null.");
        
        PaymentSchedule res = this.paymentScheduleMap.get(scheduleType)
                .get(period);
        return res;
    }
    
    /**
     * @return 返回 sourceMultiPaymentScheduleEntryMap
     */
    public ScheduleEntryAssociateMap<PaymentScheduleEntry> getPaymentScheduleEntryMap() {
        return this.paymentScheduleEntryMap;
    }
    
    /**
     * 返回一个已经完成组装的还款计划的Map <功能详细描述>
     * 
     * @return [参数说明]
     *         
     * @return Map<String,PaymentSchedule> [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    public ScheduleAssociateMap<PaymentSchedule> getPaymentScheduleMap() {
        return this.paymentScheduleMap;
    }
    
    /**
     * 获取所有新增的还款计划列表<br/>
     * <功能简述> <功能详细描述>
     * 
     * @return [参数说明]
     *         
     * @return List<PaymentSchedule> [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    public List<PaymentSchedule> getAddPaymentScheduleList() {
        List<PaymentSchedule> resList = ListUtils
                .unmodifiableList(new ArrayList<>(this.addPaymentScheduleSet));
        return resList;
    }
    
    /**
     * 获取所有更新过的还款计划项<br/>
     * <功能详细描述>
     * 
     * @return [参数说明]
     *         
     * @return List<PaymentSchedule> [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    public List<PaymentSchedule> getUpdatePaymentScheduleList() {
        List<PaymentSchedule> resList = ListUtils
                .unmodifiableList(new ArrayList<>(ChangeListenerUtils
                        .getChangedList(this.paymentScheduleChangeListeners)));
        return resList;
    }
    
    /**
     * 获取所有新增的还款计划分项 <功能详细描述>
     * 
     * @return [参数说明]
     *         
     * @return List<PaymentScheduleEntry> [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    public List<PaymentScheduleEntry> getAddPaymentScheduleEntryList() {
        List<PaymentScheduleEntry> resList = ListUtils.unmodifiableList(
                new ArrayList<>(this.addPaymentScheduleEntrySet));
        return resList;
    }
    
    /**
     * 获取所有更新过的还款计划分项 <功能详细描述>
     * 
     * @return [参数说明]
     *         
     * @return List<PaymentScheduleEntry> [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    public List<PaymentScheduleEntry> getUpdatePaymentScheduleEntryList() {
        List<PaymentScheduleEntry> resList = ListUtils.unmodifiableList(
                new ArrayList<>(ChangeListenerUtils.getChangedList(
                        this.paymentScheduleEntryChangeListeners)));
        return resList;
    }
    
    /**
     * @return 返回 loanAccountId
     */
    public String getLoanAccountId() {
        return loanAccountId;
    }
    
    /**
     * @return 返回 loanAccount
     */
    public LoanAccount getLoanAccount() {
        return this.loanAccount;
    }
    
    /**
     * @return 返回 feeCfgItemMap
     */
    public Map<FeeItemEnum, LoanAccountFeeItem> getFeeItemMap() {
        return this.feeItemMap;
    }
    
    /**
     * @return 返回 feeItemCfgMap
     */
    public Map<FeeItemEnum, FeeItemCfg> getFeeItemCfgMap() {
        return feeItemCfgMap;
    }
}
