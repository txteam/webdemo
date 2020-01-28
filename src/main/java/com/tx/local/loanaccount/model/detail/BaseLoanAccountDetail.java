/*
 * 描          述:  <描述>
 * 修  改   人:  Administrator
 * 修改时间:  2017年5月1日
 * <修改描述:>
 */
package com.tx.local.loanaccount.model.detail;

import java.beans.PropertyDescriptor;
import java.io.Serializable;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import javax.persistence.Id;

import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.PropertyAccessorFactory;

import com.tx.core.exceptions.util.AssertUtils;
import com.tx.core.util.DateUtils;
import com.tx.local.basicdata.model.FeeItemEnum;
import com.tx.local.basicdata.model.IDCardTypeEnum;
import com.tx.local.basicdata.model.RepayWayEnum;
import com.tx.local.basicdata.model.TimeUnitTypeEnum;
import com.tx.local.loanaccount.handle.PaymentScheduleHandler;
import com.tx.local.loanaccount.helper.LoanAccountHelper;
import com.tx.local.loanaccount.helper.paymentschedule.PaymentScheduleHelper;
import com.tx.local.loanaccount.model.AccountStatusEnum;
import com.tx.local.loanaccount.model.CollectionStatusEnum;
import com.tx.local.loanaccount.model.LoanAccount;
import com.tx.local.loanaccount.model.LoanAccountCategoryEnum;
import com.tx.local.loanaccount.model.LoanAccountDetail;
import com.tx.local.loanaccount.model.LoanAccountFeeItem;
import com.tx.local.loanaccount.model.LoanAccountTypeEnum;
import com.tx.local.loanaccount.model.PaymentSchedule;
import com.tx.local.loanaccount.model.PaymentScheduleEntry;
import com.tx.local.loanaccount.model.ScheduleTypeEnum;
import com.tx.local.loanaccount.model.SettleInterestStatusEnum;

/**
 * 抽象贷款账户详情<br/>
 * <功能详细描述>
 * 
 * @author  Administrator
 * @version  [版本号, 2017年5月1日]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public class BaseLoanAccountDetail implements LoanAccountDetail, Serializable {
    
    /** 注释内容 */
    private static final long serialVersionUID = 3176258761900148045L;
    
    /** 主键： */
    @Id
    private String id;
    
    /** 原贷贷款账户id(续贷，再贷关联的原贷款账户) */
    private String sourceLoanAccountId;
    
    /** 原贷款账户最后一笔交易记录id */
    private String sourceLastTradingRecordId;
    
    /** 分类 */
    private LoanAccountCategoryEnum category;
    
    /** 贷款账户类型:  common */
    private LoanAccountTypeEnum loanAccountType;
    
    /** 客户id */
    private String clientId;
    
    /** 贷款账户对应客户账户 */
    private String clientAccountId;
    
    /** 客户姓名：找到客户实例后填入极少情况发生变化不变 */
    private String clientName;
    
    /** 证 件类型：根据客户实例填入极少情况发生变化clientIdCardType[IDCardType?放名还是code还是id?]*/
    private IDCardTypeEnum clientIdCardType;
    
    /** 证件号码:找到客户实例后填入 不变 */
    private String clientIdCardNumber;
    
    /** 客户信用信息id */
    private String creditInfoId;
    
    /** 信用信息Tag版本 */
    private String creditInfoVersion;
    
    /** 系统生成 创建时间：客户账户创建的时间 不变*/
    private Date createDate;
    
    /** 最后更新时间：首次填入客户账户创建时间，后面每次更新都需要跟新该字段 更次更新都需要变更 */
    private Date lastUpdateDate;
    
    /** 贷前延期天数 */
    private int beforeDelayDays;
    
    /** 贷后延期天数 */
    private int afterDelayDays;
    
    /** 生成后主动调用贷前客户快照功能 不变  贷款合同主键：不变 贷款合同编号：*/
    private String contractId;
    
    /** 留空：贷前暂为规划合同实体 从贷款 不变*/
    private String contractNumber;
    
    /** 生效时间/合同起始日期： 获取到合同实例，将合同的合同起始日期填入 不变 */
    private Date effectiveDate;
    
    /** 、到期时间/合同结束日期：获取到合同实例，将合同的结束日期填入 不变 */
    private Date expiryDate;
    
    /** 首期还款日期:还款计划中第一次还款日期，根据合同填入，后面不再更新 */
    private Date firstRepayDate;
    
    /** 还款方式 */
    private RepayWayEnum repayWay;
    
    /** 时间单位 */
    private TimeUnitTypeEnum timeUnitType;
    
    /** 时间 */
    private int time;
    
    /** 总期数：不变 根据贷款合同填入 */
    private int totalPeriod;
    
    /** 每月还款日:填入每月还款日  */
    private int monthlyRepayDay;
    
    /** 贷款金额：不变  */
    private BigDecimal loanAmount;
    
    /** 实际放款金额 */
    private BigDecimal factOutLoanAmount;
    
    /** 起息日 */
    private Date interestDate;
    
    /** 实际放款时间 */
    private Date factLoanDate;
    
    /** 每月还款金额 */
    private BigDecimal monthlyRepayAmount;
    
    /** 已付期数: */
    private BigDecimal paidPeriod;
    
    /** 当前期数: */
    private String currentPeriod;
    
    /** 是否退回：*/
    private boolean revoked;
    
    /** 退回时间： */
    private Date revokeDate;
    
    /** 退回原因：*/
    private String revokeReason;
    
    /** 是否注销:  账户注销前，该字段为空 */
    private boolean writeOff;
    
    /** 注销日期: 账户注销前，该字段为空 */
    private Date writeOffDate;
    
    /** 注销金额 */
    private BigDecimal writeOffAmount;
    
    /** 注销后还款金额 */
    private BigDecimal writeOffRepayAmount;
    
    /** 注销原因: 填入账户注销原因 */
    private String writeOffReason;
    
    /** 是否锁定： 默认值为false,如果锁定则填入true:保留字段 */
    private boolean locked;
    
    /** 最后锁定时间：账户锁定时，需要更新该字段 */
    private Date lastLockDate;
    
    /** 结息状态:调用状态机填入，初始化时为AC//SettleInterestStatus 正常 停息 完成 */
    private SettleInterestStatusEnum settleInterestStatus;
    
    /** 下次结息日 */
    private Date nextSettleInterestDate;
    
    /** 帐户状态:调用状态机填入，初始化时为AC//AccountStatusEnum//AC//RS*/
    private AccountStatusEnum accountStatus;
    
    /** 催收状态: 调用状态机填入，初始化时为AC//AccountStatusEnum  NA(空) DQ */
    private CollectionStatusEnum collectionStatus;
    
    /** 是否死亡：DA */
    private boolean died;
    
    /** 是否关闭 XX */
    private boolean closed;
    
    /** 是否法律程序：lp:?  LP AC/DQ/DA/LP/XX */
    private boolean legalProcedure;
    
    /** 上次还款日: 填入下次还款日期 */
    private Date lastRepayDate;
    
    /** 下次还款日期: 应还款日，以及逾期时处理，及延期的业务逻辑，需要一并进行考虑  */
    private Date nextRepayDate;
    
    /** 宽限天数:会对逾期利息产生影响 */
    private int graceDays;
    
    /** 是否逾期: 账户发生逾期后更新该日期 账户逾期的变化仅在夜间事务中进行设置 为了平账白天调整过程中可能发生逾期的变更 */
    private boolean overdue;
    
    /** 逾期日期: 账户发生逾期后更新该日期 */
    private Date overdueDate;
    
    /** 逾期金额总额:所有逾期金额之和与其他应收金额之和  逾期总额：每日夜间事务处理 */
    private BigDecimal overdueSum = BigDecimal.ZERO;
    
    /** 逾期金额: 所有费用类型为逾期依赖的金额之和 （在现有业务中实际是为：逾期的 本金+利息+管理费 之和）*/
    private BigDecimal overdueAmount = BigDecimal.ZERO;
    
    /** 超额还款金额 */
    private BigDecimal overRepayAmount;
    
    /** 本金结余： */
    private BigDecimal principalBalance;
    
    /** 本金结余递减：*/
    private BigDecimal principalBalanceIrr;
    
    /** 最近一次还款金额 */
    private BigDecimal lastRepayAmount;
    
    /** 委外是否发生过 */
    private boolean outsourceHappend = false;
    
    /** 是否正在发生委外 */
    private boolean outsource = false;
    
    /** 对应委外分配记录id */
    private String outsourceAssignRecordId;
    
    /** 委外外包佣金比率 */
    private BigDecimal outsourcePercentage;
    
    /** 委外委托金额 */
    private BigDecimal outsourceAmount;
    
    /** 结清时对应的当前期数 */
    private String settleCurrentPeriod;
    
    /** 结清还款日对应的期数 */
    private String settleRepayDatePeriod;
    
    /** 是否结清 */
    private boolean settle;
    
    /** 结清交易发生日期 */
    private Date settleDate;
    
    /** 结清交易发生的还款日期 */
    private Date settleRepayDate;
    
    /** 是否SL结清 */
    private boolean settleSL;
    
    /** SL结清交易发生的还款日期 */
    private Date settleSLRepayDate;
    
    /** SL结清还款日对应的期数 */
    private String settleSLRepayDatePeriod;
    
    /** SL结清交易发生日期,SL结清时 */
    private Date settleSLDate;
    
    /** 当前期数到期日  */
    private Date currentPeriodExpireDate;
    
    /** 最后逾期检测日 [保留字段] */
    private Date nextOverdueCheckDate;
    
    /** 是否存在未到账交易 */
    private boolean existNotReceivedTrading;
    
    /** 是否历史账户：贷款账户迁移至历史表后，该标记为true */
    private boolean inHisTable;
    
    /** 贷款账户所属虚中心 */
    private String vcid;
    
    /** 贷款账户所属组织id:保留字段 */
    private String organizationId;
    
    /** 还款日 */
    private Date repayDate;
    
    /** 还款日对应期数 */
    private String repayDatePeriod;
    
    /** 下次还款金额 */
    private BigDecimal nextRepayAmount;
    
    /** loanAccountStatus */
    private String status;
    
    /** 逾期天数 */
    private int overdueDays;
    
    /** 逾期月数 */
    private BigDecimal overdueMonthes;
    
    /** 费用项配置值映射 */
    private Map<FeeItemEnum, String> feeItemValueMap;
    
    /** 应还款日映射 */
    private Map<String, Date> repaymentDateMap;
    
    /** 逾期日期映射 */
    private Map<String, Integer> overdueDaysMap;
    
    /** 所有费用的 应 + 豁 - 实 与费用的映射 */
    private Map<ScheduleTypeEnum, Map<String, Map<FeeItemEnum, BigDecimal>>> sumMap;
    
    /** 各费用项的应收金额映射 */
    private Map<ScheduleTypeEnum, Map<String, Map<FeeItemEnum, BigDecimal>>> receivableSumMap;
    
    /** 豁免金额映射 */
    private Map<ScheduleTypeEnum, Map<String, Map<FeeItemEnum, BigDecimal>>> exemptSumMap;
    
    /** 应收金额映射 */
    private Map<ScheduleTypeEnum, Map<String, Map<FeeItemEnum, BigDecimal>>> actualReceivedSumMap;
    
    /** 所有费用的 应 + 豁 - 实 与费用的映射 */
    private Map<ScheduleTypeEnum, Map<String, Map<FeeItemEnum, BigDecimal>>> sumMapOfMature;
    
    /** 各费用项的应收金额映射 */
    private Map<ScheduleTypeEnum, Map<String, Map<FeeItemEnum, BigDecimal>>> receivableSumMapOfMature;
    
    /** 豁免金额映射 */
    private Map<ScheduleTypeEnum, Map<String, Map<FeeItemEnum, BigDecimal>>> exemptSumMapOfMature;
    
    /** 应收金额映射 */
    private Map<ScheduleTypeEnum, Map<String, Map<FeeItemEnum, BigDecimal>>> actualReceivedSumMapOfMature;
    
    /** <默认构造函数> */
    public BaseLoanAccountDetail() {
        super();
    }
    
    /** <默认构造函数> */
    public BaseLoanAccountDetail(LoanAccount loanAccount,
            PaymentScheduleHandler handler, Date repayDate) {
        super();
        init(loanAccount, handler, repayDate);
    }
    
    /**
     * 初始化<br/>
     * <功能详细描述>
     * @param loanAccount
     * @param handler [参数说明]
     * 
     * @return void [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
    */
    protected void init(LoanAccount loanAccount, PaymentScheduleHandler handler,
            Date repayDate) {
        AssertUtils.notNull(loanAccount, "loanAccount is null.");
        AssertUtils.notNull(handler, "handler is null.");
        AssertUtils.notNull(repayDate, "repayDate is null.");
        
        this.repayDate = repayDate;
        this.repayDatePeriod = PaymentScheduleHelper.getReceivablePeriod(
                handler.getPaymentScheduleMap(),
                ScheduleTypeEnum.MAIN,
                repayDate);
        this.nextRepayAmount = handler.caculateNextRepayAmount();
        this.status = LoanAccountHelper.getLoanAccountStatus(loanAccount);
        Date overdueDate = this.overdueDate == null ? handler.getOverdueDate()
                : this.overdueDate;
        this.overdueDays = loanAccount.isOverdue() ? DateUtils
                .calculateNumberOfDaysBetween(this.repayDate, overdueDate) : 0;
        this.overdueMonthes = (new BigDecimal(this.overdueDays))
                .divide(new BigDecimal("30"), 2, RoundingMode.UP);
        
        //写入贷款账户信息
        BeanWrapper sourceBW = PropertyAccessorFactory
                .forBeanPropertyAccess(loanAccount);
        BeanWrapper thisBW = PropertyAccessorFactory
                .forBeanPropertyAccess(this);
        for (PropertyDescriptor pd : sourceBW.getPropertyDescriptors()) {
            String propertyName = pd.getName();
            if (!sourceBW.isReadableProperty(propertyName)
                    || !thisBW.isWritableProperty(propertyName)) {
                continue;
            }
            thisBW.setPropertyValue(propertyName,
                    sourceBW.getPropertyValue(propertyName));
        }
        
        //费用项映射
        this.feeItemValueMap = new HashMap<>();
        for (Entry<FeeItemEnum, LoanAccountFeeItem> entryTemp : handler
                .getFeeItemMap().entrySet()) {
            this.feeItemValueMap.put(entryTemp.getKey(),
                    entryTemp.getValue().getValue());
        }
        
        //遍历还款计划
        this.sumMap = new HashMap<>();
        this.receivableSumMap = new HashMap<>();
        this.exemptSumMap = new HashMap<>();
        this.actualReceivedSumMap = new HashMap<>();
        
        this.sumMapOfMature = new HashMap<>();
        this.receivableSumMapOfMature = new HashMap<>();
        this.exemptSumMapOfMature = new HashMap<>();
        this.actualReceivedSumMapOfMature = new HashMap<>();
        
        this.repaymentDateMap = new HashMap<>();
        this.overdueDaysMap = new HashMap<>();
        for (Entry<ScheduleTypeEnum, Map<String, PaymentSchedule>> entryTemp : handler
                .getPaymentScheduleMap().entrySet()) {
            ScheduleTypeEnum scheduleType = entryTemp.getKey();
            if (!this.sumMap.containsKey(scheduleType)) {
                this.sumMap.put(scheduleType, new HashMap<>());
                this.receivableSumMap.put(scheduleType, new HashMap<>());
                this.exemptSumMap.put(scheduleType, new HashMap<>());
                this.actualReceivedSumMap.put(scheduleType, new HashMap<>());
                
                this.sumMapOfMature.put(scheduleType, new HashMap<>());
                this.receivableSumMapOfMature.put(scheduleType,
                        new HashMap<>());
                this.exemptSumMapOfMature.put(scheduleType, new HashMap<>());
                this.actualReceivedSumMapOfMature.put(scheduleType,
                        new HashMap<>());
            }
            for (Entry<String, PaymentSchedule> valueEntry : entryTemp
                    .getValue().entrySet()) {
                String period = valueEntry.getKey();
                PaymentSchedule ps = valueEntry.getValue();
                if (!this.sumMap.containsKey(period)) {
                    this.sumMap.get(scheduleType).put(period, new HashMap<>());
                    this.receivableSumMap.get(scheduleType).put(period,
                            new HashMap<>());
                    this.exemptSumMap.get(scheduleType).put(period,
                            new HashMap<>());
                    this.actualReceivedSumMap.get(scheduleType).put(period,
                            new HashMap<>());
                    
                    this.sumMapOfMature.get(scheduleType).put(period,
                            new HashMap<>());
                    this.receivableSumMapOfMature.get(scheduleType).put(period,
                            new HashMap<>());
                    this.exemptSumMapOfMature.get(scheduleType).put(period,
                            new HashMap<>());
                    this.actualReceivedSumMapOfMature.get(scheduleType)
                            .put(period, new HashMap<>());
                }
                //boolean isOverdue = false;
                boolean isMature = false;
                if (NumberUtils.isDigits(period)
                        && ScheduleTypeEnum.MAIN.equals(scheduleType)) {
                    this.repaymentDateMap.put(period, ps.getRepaymentDate());
                    if (DateUtils.compareByDay(repayDate,
                            ps.getRepaymentDate()) > 0) {
                        if (!ps.isSettle()) {
                            this.overdueDaysMap.put(period,
                                    DateUtils.calculateNumberOfDaysBetween(
                                            repayDate, ps.getRepaymentDate()));
                        } else if (ps.isSettle()
                                && DateUtils.compareByDay(ps.getLastRepayDate(),
                                        ps.getRepaymentDate()) > 0) {
                            this.overdueDaysMap.put(period,
                                    DateUtils.calculateNumberOfDaysBetween(
                                            ps.getLastRepayDate(),
                                            ps.getRepaymentDate()));
                        }
                    }
                }
                if (!NumberUtils.isDigits(period) || DateUtils
                        .compareByDay(repayDate, ps.getRepaymentDate()) > 0) {
                    //NA期和按时还款的计划算作到期
                    isMature = true;
                }
                
                for (PaymentScheduleEntry pse : ps
                        .getPaymentScheduleEntryList()) {
                    this.sumMap.get(scheduleType).get(period).put(
                            pse.getFeeItem(),
                            pse.getReceivableAmount()
                                    .add(pse.getExemptAmount())
                                    .subtract(pse.getActualReceivedAmount()));
                    this.receivableSumMap.get(scheduleType).get(period).put(
                            pse.getFeeItem(), pse.getReceivableAmount());
                    this.exemptSumMap.get(scheduleType).get(period).put(
                            pse.getFeeItem(), pse.getExemptAmount());
                    this.actualReceivedSumMap.get(scheduleType).get(period).put(
                            pse.getFeeItem(), pse.getActualReceivedAmount());
                    
                    if (isMature) {
                        this.sumMapOfMature.get(scheduleType).get(period).put(
                                pse.getFeeItem(),
                                pse.getReceivableAmount()
                                        .add(pse.getExemptAmount())
                                        .subtract(
                                                pse.getActualReceivedAmount()));
                        this.receivableSumMapOfMature.get(scheduleType)
                                .get(period)
                                .put(pse.getFeeItem(),
                                        pse.getReceivableAmount());
                        this.exemptSumMapOfMature.get(scheduleType)
                                .get(period)
                                .put(pse.getFeeItem(), pse.getExemptAmount());
                        this.actualReceivedSumMapOfMature.get(scheduleType)
                                .get(period)
                                .put(pse.getFeeItem(),
                                        pse.getActualReceivedAmount());
                    }
                }
            }
        }
    }
    
    /**
     * @return
     */
    @Override
    public String getStatus() {
        return this.status;
    }
    
    /**
     * @return
     */
    @Override
    public int getOverdueDays() {
        return this.overdueDays;
    }
    
    /**
     * @return
     */
    @Override
    public BigDecimal getOverdueMonthes() {
        return this.overdueMonthes;
    }
    
    /**
     * @return 返回 repayDate
     */
    @Override
    public Date getRepayDate() {
        return this.repayDate;
    }
    
    /**
     * @return 返回 repayDatePeriod
     */
    @Override
    public String getRepayDatePeriod() {
        return this.repayDatePeriod;
    }
    
    /**
     * @return
     */
    @Override
    public BigDecimal getNextRepayAmount() {
        return this.nextRepayAmount;
    }
    
    /**
     * @return
     */
    @Override
    public int getC0() {
        if (!this.overdueDaysMap.containsKey(this.repayDatePeriod)) {
            return 0;
        } else {
            return this.overdueDaysMap.get(repayDatePeriod);
        }
    }
    
    /**
     * @return
     */
    @Override
    public int getC1() {
        int repayDatePeriod = NumberUtils.toInt(this.repayDatePeriod);
        int c1Period = repayDatePeriod;
        if (repayDatePeriod > 1) {
            c1Period = repayDatePeriod - 1;
        } else {
            return getC0();
        }
        String c1 = "" + c1Period;
        if (!this.overdueDaysMap.containsKey(c1)) {
            return 0;
        } else {
            return this.overdueDaysMap.get(c1);
        }
    }
    
    /**
     * @return
     */
    @Override
    public int getC2() {
        int repayDatePeriod = NumberUtils.toInt(this.repayDatePeriod);
        int c2Period = repayDatePeriod;
        if (repayDatePeriod > 2) {
            c2Period = repayDatePeriod - 2;
        } else {
            return getC1();
        }
        String c2 = "" + c2Period;
        if (!this.overdueDaysMap.containsKey(c2)) {
            return 0;
        } else {
            return this.overdueDaysMap.get(c2);
        }
    }
    
    /**
     * @return
     */
    @Override
    public String getC0C1C2() {
        return getC0() + "/" + getC1() + "/" + getC2();
    }
    
    /**
     * @return
     */
    @Override
    public int getLod() {
        int max = 0;
        for (Entry<String, Integer> entryTemp : this.overdueDaysMap
                .entrySet()) {
            max += NumberUtils.max(max, entryTemp.getValue());
        }
        return max;
    }
    
    /**
     * @return
     */
    @Override
    public int getAod() {
        int total = 0;
        for (Entry<String, Integer> entryTemp : this.overdueDaysMap
                .entrySet()) {
            total += entryTemp.getValue();
        }
        if (this.repaymentDateMap.size() == 0) {
            return 0;
        } else {
            return total / this.repaymentDateMap.size();
        }
    }
    
    /**
     * @return
     */
    @Override
    public int getTod() {
        int total = 0;
        for (Entry<String, Integer> entryTemp : this.overdueDaysMap
                .entrySet()) {
            total += entryTemp.getValue();
        }
        return total;
    }
    
    /**
     * @return
     */
    @Override
    public String getLodAodTod() {
        return getLod() + "/" + getAod() + "/" + getTod();
    }
    
    /**
     * @return
     */
    @Override
    public Map<String, Date> getRepaymentDateMap() {
        return this.repaymentDateMap;
    }
    
    /**
    * @return
    */
    @Override
    public Map<ScheduleTypeEnum, Map<String, Map<FeeItemEnum, BigDecimal>>> getSumMapOfMature() {
        return this.sumMapOfMature;
    }
    
    /**
    * @param scheuleType
    * @param feeItem
    * @return
    */
    @Override
    public BigDecimal getSumOfMature(ScheduleTypeEnum scheuleType,
            FeeItemEnum feeItem) {
        AssertUtils.notNull(scheuleType, "scheuleType is null.");
        AssertUtils.notNull(feeItem, "feeItem is null.");
        
        AssertUtils.notNull(this.sumMapOfMature, "sumMapOfMature is null.");
        AssertUtils.notNull(this.sumMapOfMature.get(scheuleType),
                "sumOfMatureMap.scheduleType:{} is null.",
                new Object[] { scheuleType });
        
        BigDecimal totalSum = BigDecimal.ZERO;
        for (Map<FeeItemEnum, BigDecimal> periodMap : this.sumMapOfMature
                .get(scheuleType).values()) {
            if (!periodMap.containsKey(feeItem)) {
                continue;
            }
            totalSum = totalSum.add(periodMap.get(feeItem));
        }
        
        return totalSum;
    }
    
    /**
    * @return
    */
    @Override
    public BigDecimal getTotalSumOfMature(ScheduleTypeEnum scheuleType) {
        AssertUtils.notNull(scheuleType, "scheuleType is null.");
        
        AssertUtils.notNull(this.sumMapOfMature, "sumOfMatureMap is null.");
        AssertUtils.notNull(this.sumMapOfMature.get(scheuleType),
                "sumOfMatureMap.scheduleType:{} is null.",
                new Object[] { scheuleType });
        
        BigDecimal totalSum = BigDecimal.ZERO;
        for (Map<FeeItemEnum, BigDecimal> periodMap : this.sumMapOfMature
                .get(scheuleType).values()) {
            for (BigDecimal amountTemp : periodMap.values()) {
                totalSum = totalSum.add(amountTemp);
            }
        }
        
        return totalSum;
    }
    
    /**
    * @return
    */
    @Override
    public Map<ScheduleTypeEnum, Map<String, Map<FeeItemEnum, BigDecimal>>> getReceivableSumMapOfMature() {
        return this.receivableSumMapOfMature;
    }
    
    /**
    * @param scheuleType
    * @param feeItem
    * @return
    */
    @Override
    public BigDecimal getReceivableSumOfMature(ScheduleTypeEnum scheuleType,
            FeeItemEnum feeItem) {
        AssertUtils.notNull(scheuleType, "scheuleType is null.");
        AssertUtils.notNull(feeItem, "feeItem is null.");
        
        AssertUtils.notNull(this.receivableSumMapOfMature,
                "receivableSumMapOfMature is null.");
        AssertUtils.notNull(this.receivableSumMapOfMature.get(scheuleType),
                "receivableSumMapOfMature.scheduleType:{} is null.",
                new Object[] { scheuleType });
        
        BigDecimal totalSum = BigDecimal.ZERO;
        for (Map<FeeItemEnum, BigDecimal> periodMap : this.receivableSumMapOfMature
                .get(scheuleType).values()) {
            if (!periodMap.containsKey(feeItem)) {
                continue;
            }
            totalSum = totalSum.add(periodMap.get(feeItem));
        }
        
        return totalSum;
    }
    
    /**
    * @return
    */
    @Override
    public BigDecimal getReceivableTotalSumOfMature(
            ScheduleTypeEnum scheuleType) {
        AssertUtils.notNull(scheuleType, "scheuleType is null.");
        
        AssertUtils.notNull(this.receivableSumMapOfMature,
                "receivableSumMapOfMature is null.");
        AssertUtils.notNull(this.receivableSumMapOfMature.get(scheuleType),
                "receivableSumMapOfMature.scheduleType:{} is null.",
                new Object[] { scheuleType });
        
        BigDecimal totalSum = BigDecimal.ZERO;
        for (Map<FeeItemEnum, BigDecimal> periodMap : this.receivableSumMapOfMature
                .get(scheuleType).values()) {
            for (BigDecimal amountTemp : periodMap.values()) {
                totalSum = totalSum.add(amountTemp);
            }
        }
        
        return totalSum;
    }
    
    /**
    * @return
    */
    @Override
    public Map<ScheduleTypeEnum, Map<String, Map<FeeItemEnum, BigDecimal>>> getExemptSumMapOfMature() {
        return this.exemptSumMapOfMature;
    }
    
    /**
    * @param scheuleType
    * @param feeItem
    * @return
    */
    @Override
    public BigDecimal getExemptSumOfMature(ScheduleTypeEnum scheuleType,
            FeeItemEnum feeItem) {
        AssertUtils.notNull(scheuleType, "scheuleType is null.");
        AssertUtils.notNull(feeItem, "feeItem is null.");
        
        AssertUtils.notNull(this.exemptSumMapOfMature,
                "exemptSumMapOfMature is null.");
        AssertUtils.notNull(this.exemptSumMapOfMature.get(scheuleType),
                "exemptSumMapOfMature.scheduleType:{} is null.",
                new Object[] { scheuleType });
        
        BigDecimal totalSum = BigDecimal.ZERO;
        for (Map<FeeItemEnum, BigDecimal> periodMap : this.exemptSumMapOfMature
                .get(scheuleType).values()) {
            if (!periodMap.containsKey(feeItem)) {
                continue;
            }
            totalSum = totalSum.add(periodMap.get(feeItem));
        }
        
        return totalSum;
    }
    
    /**
    * @return
    */
    @Override
    public BigDecimal getExemptTotalSumOfMature(ScheduleTypeEnum scheuleType) {
        AssertUtils.notNull(scheuleType, "scheuleType is null.");
        
        AssertUtils.notNull(this.exemptSumMapOfMature,
                "exemptSumMapOfMature is null.");
        AssertUtils.notNull(this.exemptSumMapOfMature.get(scheuleType),
                "exemptSumMapOfMature.scheduleType:{} is null.",
                new Object[] { scheuleType });
        
        BigDecimal totalSum = BigDecimal.ZERO;
        for (Map<FeeItemEnum, BigDecimal> periodMap : this.exemptSumMapOfMature
                .get(scheuleType).values()) {
            for (BigDecimal amountTemp : periodMap.values()) {
                totalSum = totalSum.add(amountTemp);
            }
        }
        
        return totalSum;
    }
    
    /**
    * @return
    */
    @Override
    public Map<ScheduleTypeEnum, Map<String, Map<FeeItemEnum, BigDecimal>>> getActualReceivedSumMapOfMature() {
        return this.actualReceivedSumMapOfMature;
    }
    
    /**
    * @param scheuleType
    * @param feeItem
    * @return
    */
    @Override
    public BigDecimal getActualReceivedSumOfMature(ScheduleTypeEnum scheuleType,
            FeeItemEnum feeItem) {
        AssertUtils.notNull(scheuleType, "scheuleType is null.");
        AssertUtils.notNull(feeItem, "feeItem is null.");
        
        AssertUtils.notNull(this.actualReceivedSumMapOfMature,
                "actualReceivedSumMapOfMature is null.");
        AssertUtils.notNull(this.actualReceivedSumMapOfMature.get(scheuleType),
                "actualReceivedSumOfMatureMap.scheduleType:{} is null.",
                new Object[] { scheuleType });
        
        BigDecimal totalSum = BigDecimal.ZERO;
        for (Map<FeeItemEnum, BigDecimal> periodMap : this.actualReceivedSumMapOfMature
                .get(scheuleType).values()) {
            if (!periodMap.containsKey(feeItem)) {
                continue;
            }
            totalSum = totalSum.add(periodMap.get(feeItem));
        }
        
        return totalSum;
    }
    
    /**
    * @return
    */
    @Override
    public BigDecimal getActualReceivedTotalSumOfMature(
            ScheduleTypeEnum scheuleType) {
        AssertUtils.notNull(scheuleType, "scheuleType is null.");
        
        AssertUtils.notNull(this.actualReceivedSumMapOfMature,
                "actualReceivedSumMapOfMature is null.");
        AssertUtils.notNull(this.actualReceivedSumMapOfMature.get(scheuleType),
                "actualReceivedSumMapOfMature.scheduleType:{} is null.",
                new Object[] { scheuleType });
        
        BigDecimal totalSum = BigDecimal.ZERO;
        for (Map<FeeItemEnum, BigDecimal> periodMap : this.actualReceivedSumMapOfMature
                .get(scheuleType).values()) {
            for (BigDecimal amountTemp : periodMap.values()) {
                totalSum = totalSum.add(amountTemp);
            }
        }
        
        return totalSum;
    }
    
    /**
     * @return
     */
    @Override
    public Map<ScheduleTypeEnum, Map<String, Map<FeeItemEnum, BigDecimal>>> getSumMap() {
        return this.sumMap;
    }
    
    /**
     * @param scheuleType
     * @param feeItem
     * @return
     */
    @Override
    public BigDecimal getSum(ScheduleTypeEnum scheuleType,
            FeeItemEnum feeItem) {
        AssertUtils.notNull(scheuleType, "scheuleType is null.");
        AssertUtils.notNull(feeItem, "feeItem is null.");
        
        AssertUtils.notNull(this.sumMap, "sumMap is null.");
        AssertUtils.notNull(this.sumMap.get(scheuleType),
                "sumMap.scheduleType:{} is null.",
                new Object[] { scheuleType });
        
        BigDecimal totalSum = BigDecimal.ZERO;
        for (Map<FeeItemEnum, BigDecimal> periodMap : this.sumMap
                .get(scheuleType).values()) {
            if (!periodMap.containsKey(feeItem)) {
                continue;
            }
            totalSum = totalSum.add(periodMap.get(feeItem));
        }
        
        return totalSum;
    }
    
    /**
     * @return
     */
    @Override
    public BigDecimal getTotalSum(ScheduleTypeEnum scheuleType) {
        AssertUtils.notNull(scheuleType, "scheuleType is null.");
        
        AssertUtils.notNull(this.sumMap, "sumMap is null.");
        AssertUtils.notNull(this.sumMap.get(scheuleType),
                "sumMap.scheduleType:{} is null.",
                new Object[] { scheuleType });
        
        BigDecimal totalSum = BigDecimal.ZERO;
        for (Map<FeeItemEnum, BigDecimal> periodMap : this.sumMap
                .get(scheuleType).values()) {
            for (BigDecimal amountTemp : periodMap.values()) {
                totalSum = totalSum.add(amountTemp);
            }
        }
        
        return totalSum;
    }
    
    /**
     * @return
     */
    @Override
    public Map<ScheduleTypeEnum, Map<String, Map<FeeItemEnum, BigDecimal>>> getReceivableSumMap() {
        return this.receivableSumMap;
    }
    
    /**
     * @param scheuleType
     * @param feeItem
     * @return
     */
    @Override
    public BigDecimal getReceivableSum(ScheduleTypeEnum scheuleType,
            FeeItemEnum feeItem) {
        AssertUtils.notNull(scheuleType, "scheuleType is null.");
        AssertUtils.notNull(feeItem, "feeItem is null.");
        
        AssertUtils.notNull(this.receivableSumMap, "receivableSumMap is null.");
        AssertUtils.notNull(this.receivableSumMap.get(scheuleType),
                "receivableSumMap.scheduleType:{} is null.",
                new Object[] { scheuleType });
        
        BigDecimal totalSum = BigDecimal.ZERO;
        for (Map<FeeItemEnum, BigDecimal> periodMap : this.receivableSumMap
                .get(scheuleType).values()) {
            if (!periodMap.containsKey(feeItem)) {
                continue;
            }
            totalSum = totalSum.add(periodMap.get(feeItem));
        }
        
        return totalSum;
    }
    
    /**
     * @return
     */
    @Override
    public BigDecimal getReceivableTotalSum(ScheduleTypeEnum scheuleType) {
        AssertUtils.notNull(scheuleType, "scheuleType is null.");
        
        AssertUtils.notNull(this.receivableSumMap, "receivableSumMap is null.");
        AssertUtils.notNull(this.receivableSumMap.get(scheuleType),
                "receivableSumMap.scheduleType:{} is null.",
                new Object[] { scheuleType });
        
        BigDecimal totalSum = BigDecimal.ZERO;
        for (Map<FeeItemEnum, BigDecimal> periodMap : this.receivableSumMap
                .get(scheuleType).values()) {
            for (BigDecimal amountTemp : periodMap.values()) {
                totalSum = totalSum.add(amountTemp);
            }
        }
        
        return totalSum;
    }
    
    /**
     * @return
     */
    @Override
    public Map<ScheduleTypeEnum, Map<String, Map<FeeItemEnum, BigDecimal>>> getExemptSumMap() {
        return this.exemptSumMap;
    }
    
    /**
     * @param scheuleType
     * @param feeItem
     * @return
     */
    @Override
    public BigDecimal getExemptSum(ScheduleTypeEnum scheuleType,
            FeeItemEnum feeItem) {
        AssertUtils.notNull(scheuleType, "scheuleType is null.");
        AssertUtils.notNull(feeItem, "feeItem is null.");
        
        AssertUtils.notNull(this.exemptSumMap, "exemptSumMap is null.");
        AssertUtils.notNull(this.exemptSumMap.get(scheuleType),
                "exemptSumMap.scheduleType:{} is null.",
                new Object[] { scheuleType });
        
        BigDecimal totalSum = BigDecimal.ZERO;
        for (Map<FeeItemEnum, BigDecimal> periodMap : this.exemptSumMap
                .get(scheuleType).values()) {
            if (!periodMap.containsKey(feeItem)) {
                continue;
            }
            totalSum = totalSum.add(periodMap.get(feeItem));
        }
        
        return totalSum;
    }
    
    /**
     * @return
     */
    @Override
    public BigDecimal getExemptTotalSum(ScheduleTypeEnum scheuleType) {
        AssertUtils.notNull(scheuleType, "scheuleType is null.");
        
        AssertUtils.notNull(this.exemptSumMap, "exemptSumMap is null.");
        AssertUtils.notNull(this.exemptSumMap.get(scheuleType),
                "exemptSumMap.scheduleType:{} is null.",
                new Object[] { scheuleType });
        
        BigDecimal totalSum = BigDecimal.ZERO;
        for (Map<FeeItemEnum, BigDecimal> periodMap : this.exemptSumMap
                .get(scheuleType).values()) {
            for (BigDecimal amountTemp : periodMap.values()) {
                totalSum = totalSum.add(amountTemp);
            }
        }
        
        return totalSum;
    }
    
    /**
     * @return
     */
    @Override
    public Map<ScheduleTypeEnum, Map<String, Map<FeeItemEnum, BigDecimal>>> getActualReceivedSumMap() {
        return this.actualReceivedSumMap;
    }
    
    /**
     * @param scheuleType
     * @param feeItem
     * @return
     */
    @Override
    public BigDecimal getActualReceivedSum(ScheduleTypeEnum scheuleType,
            FeeItemEnum feeItem) {
        AssertUtils.notNull(scheuleType, "scheuleType is null.");
        AssertUtils.notNull(feeItem, "feeItem is null.");
        
        AssertUtils.notNull(this.actualReceivedSumMap,
                "actualReceivedSumMap is null.");
        AssertUtils.notNull(this.actualReceivedSumMap.get(scheuleType),
                "actualReceivedSumMap.scheduleType:{} is null.",
                new Object[] { scheuleType });
        
        BigDecimal totalSum = BigDecimal.ZERO;
        for (Map<FeeItemEnum, BigDecimal> periodMap : this.actualReceivedSumMap
                .get(scheuleType).values()) {
            if (!periodMap.containsKey(feeItem)) {
                continue;
            }
            totalSum = totalSum.add(periodMap.get(feeItem));
        }
        
        return totalSum;
    }
    
    /**
     * @return
     */
    @Override
    public BigDecimal getActualReceivedTotalSum(ScheduleTypeEnum scheuleType) {
        AssertUtils.notNull(scheuleType, "scheuleType is null.");
        
        AssertUtils.notNull(this.actualReceivedSumMap,
                "actualReceivedSumMap is null.");
        AssertUtils.notNull(this.actualReceivedSumMap.get(scheuleType),
                "actualReceivedSumMap.scheduleType:{} is null.",
                new Object[] { scheuleType });
        
        BigDecimal totalSum = BigDecimal.ZERO;
        for (Map<FeeItemEnum, BigDecimal> periodMap : this.actualReceivedSumMap
                .get(scheuleType).values()) {
            for (BigDecimal amountTemp : periodMap.values()) {
                totalSum = totalSum.add(amountTemp);
            }
        }
        
        return totalSum;
    }
    
    /**
     * @return
     */
    @Override
    public Map<FeeItemEnum, String> getFeeItemValueMap() {
        return this.feeItemValueMap;
    }
    
    /**
     * @param feeItem
     * @return
     */
    @Override
    public final String getFeeItemValue(FeeItemEnum feeItem) {
        AssertUtils.notNull(feeItem, "feeItem is null.");
        
        String value = getFeeItemValueMap().get(feeItem);
        return value;
    }
    
    /**
     * @param feeItem
     * @return
     */
    @Override
    public final BigDecimal getFeeItemRate(FeeItemEnum feeItem) {
        AssertUtils.notNull(feeItem, "feeItem is null.");
        
        if (getFeeItemValue(feeItem) == null) {
            return BigDecimal.ZERO;
        }
        BigDecimal value = new BigDecimal(getFeeItemValue(feeItem));
        return value;
    }
    
    /**
     * @param feeItem
     * @return
     */
    @Override
    public final BigDecimal getFeeItemRatePercent(FeeItemEnum feeItem) {
        AssertUtils.notNull(feeItem, "feeItem is null.");
        
        BigDecimal value = getFeeItemRate(feeItem)
                .multiply(new BigDecimal("100"));
        return value;
    }
    
    /**
     * @return 返回 id
     */
    public String getId() {
        return id;
    }
    
    /**
     * @param 对id进行赋值
     */
    public void setId(String id) {
        this.id = id;
    }
    
    /**
     * @return 返回 repayWay
     */
    public RepayWayEnum getRepayWay() {
        return repayWay;
    }
    
    /**
     * @param 对repayWay进行赋值
     */
    public void setRepayWay(RepayWayEnum repayWay) {
        this.repayWay = repayWay;
    }
    
    /**
     * @return 返回 timeUnitType
     */
    public TimeUnitTypeEnum getTimeUnitType() {
        return timeUnitType;
    }
    
    /**
     * @param 对timeUnitType进行赋值
     */
    public void setTimeUnitType(TimeUnitTypeEnum timeUnitType) {
        this.timeUnitType = timeUnitType;
    }
    
    /**
     * @return 返回 time
     */
    public int getTime() {
        return time;
    }
    
    /**
     * @param 对time进行赋值
     */
    public void setTime(int time) {
        this.time = time;
    }
    
    /**
     * @return 返回 category
     */
    public LoanAccountCategoryEnum getCategory() {
        return category;
    }
    
    /**
     * @param 对category进行赋值
     */
    public void setCategory(LoanAccountCategoryEnum category) {
        this.category = category;
    }
    
    /**
     * @return 返回 loanAccountType
     */
    public LoanAccountTypeEnum getLoanAccountType() {
        return loanAccountType;
    }
    
    /**
     * @param 对loanAccountType进行赋值
     */
    public void setLoanAccountType(LoanAccountTypeEnum loanAccountType) {
        this.loanAccountType = loanAccountType;
    }
    
    /**
     * @return 返回 creditInfoId
     */
    public String getCreditInfoId() {
        return creditInfoId;
    }
    
    /**
     * @param 对creditInfoId进行赋值
     */
    public void setCreditInfoId(String creditInfoId) {
        this.creditInfoId = creditInfoId;
    }
    
    /**
     * @return 返回 creditInfoVersion
     */
    public String getCreditInfoVersion() {
        return creditInfoVersion;
    }
    
    /**
     * @param 对creditInfoVersion进行赋值
     */
    public void setCreditInfoVersion(String creditInfoVersion) {
        this.creditInfoVersion = creditInfoVersion;
    }
    
    /**
     * @return 返回 clientId
     */
    public String getClientId() {
        return clientId;
    }
    
    /**
     * @param 对clientId进行赋值
     */
    public void setClientId(String clientId) {
        this.clientId = clientId;
    }
    
    /**
     * @return 返回 clientAccountId
     */
    public String getClientAccountId() {
        return clientAccountId;
    }
    
    /**
     * @param 对clientAccountId进行赋值
     */
    public void setClientAccountId(String clientAccountId) {
        this.clientAccountId = clientAccountId;
    }
    
    /**
     * @return 返回 clientName
     */
    public String getClientName() {
        return clientName;
    }
    
    /**
     * @param 对clientName进行赋值
     */
    public void setClientName(String clientName) {
        this.clientName = clientName;
    }
    
    /**
     * @return 返回 clientIdCardType
     */
    public IDCardTypeEnum getClientIdCardType() {
        return clientIdCardType;
    }
    
    /**
     * @param 对clientIdCardType进行赋值
     */
    public void setClientIdCardType(IDCardTypeEnum clientIdCardType) {
        this.clientIdCardType = clientIdCardType;
    }
    
    /**
     * @return 返回 clientIdCardNumber
     */
    public String getClientIdCardNumber() {
        return clientIdCardNumber;
    }
    
    /**
     * @param 对clientIdCardNumber进行赋值
     */
    public void setClientIdCardNumber(String clientIdCardNumber) {
        this.clientIdCardNumber = clientIdCardNumber;
    }
    
    /**
     * @return 返回 beforeDelayDays
     */
    public int getBeforeDelayDays() {
        return beforeDelayDays;
    }
    
    /**
     * @param 对beforeDelayDays进行赋值
     */
    public void setBeforeDelayDays(int beforeDelayDays) {
        this.beforeDelayDays = beforeDelayDays;
    }
    
    /**
     * @return 返回 afterDelayDays
     */
    public int getAfterDelayDays() {
        return afterDelayDays;
    }
    
    /**
     * @param 对afterDelayDays进行赋值
     */
    public void setAfterDelayDays(int afterDelayDays) {
        this.afterDelayDays = afterDelayDays;
    }
    
    /**
     * @return 返回 contractId
     */
    public String getContractId() {
        return contractId;
    }
    
    /**
     * @param 对contractId进行赋值
     */
    public void setContractId(String contractId) {
        this.contractId = contractId;
    }
    
    /**
     * @return 返回 contractNumber
     */
    public String getContractNumber() {
        return contractNumber;
    }
    
    /**
     * @param 对contractNumber进行赋值
     */
    public void setContractNumber(String contractNumber) {
        this.contractNumber = contractNumber;
    }
    
    /**
     * @return 返回 effectiveDate
     */
    public Date getEffectiveDate() {
        return effectiveDate;
    }
    
    /**
     * @param 对effectiveDate进行赋值
     */
    public void setEffectiveDate(Date effectiveDate) {
        this.effectiveDate = effectiveDate;
    }
    
    /**
     * @return 返回 expiryDate
     */
    public Date getExpiryDate() {
        return expiryDate;
    }
    
    /**
     * @param 对expiryDate进行赋值
     */
    public void setExpiryDate(Date expiryDate) {
        this.expiryDate = expiryDate;
    }
    
    /**
     * @return 返回 firstRepayDate
     */
    public Date getFirstRepayDate() {
        return firstRepayDate;
    }
    
    /**
     * @param 对firstRepayDate进行赋值
     */
    public void setFirstRepayDate(Date firstRepayDate) {
        this.firstRepayDate = firstRepayDate;
    }
    
    /**
     * @return 返回 totalPeriod
     */
    public int getTotalPeriod() {
        return totalPeriod;
    }
    
    /**
     * @param 对totalPeriod进行赋值
     */
    public void setTotalPeriod(int totalPeriod) {
        this.totalPeriod = totalPeriod;
    }
    
    /**
     * @return 返回 paidPeriod
     */
    public BigDecimal getPaidPeriod() {
        return paidPeriod;
    }
    
    /**
     * @param 对paidPeriod进行赋值
     */
    public void setPaidPeriod(BigDecimal paidPeriod) {
        this.paidPeriod = paidPeriod;
    }
    
    /**
     * @return 返回 currentPeriod
     */
    public String getCurrentPeriod() {
        return currentPeriod;
    }
    
    /**
     * @param 对currentPeriod进行赋值
     */
    public void setCurrentPeriod(String currentPeriod) {
        this.currentPeriod = currentPeriod;
    }
    
    /**
     * @return 返回 revoked
     */
    public boolean isRevoked() {
        return revoked;
    }
    
    /**
     * @param 对revoked进行赋值
     */
    public void setRevoked(boolean revoked) {
        this.revoked = revoked;
    }
    
    /**
     * @return 返回 revokeDate
     */
    public Date getRevokeDate() {
        return revokeDate;
    }
    
    /**
     * @param 对revokeDate进行赋值
     */
    public void setRevokeDate(Date revokeDate) {
        this.revokeDate = revokeDate;
    }
    
    /**
     * @return 返回 revokeReason
     */
    public String getRevokeReason() {
        return revokeReason;
    }
    
    /**
     * @param 对revokeReason进行赋值
     */
    public void setRevokeReason(String revokeReason) {
        this.revokeReason = revokeReason;
    }
    
    /**
     * @return 返回 writeOff
     */
    public boolean isWriteOff() {
        return writeOff;
    }
    
    /**
     * @param 对writeOff进行赋值
     */
    public void setWriteOff(boolean writeOff) {
        this.writeOff = writeOff;
    }
    
    /**
     * @return 返回 writeOffDate
     */
    public Date getWriteOffDate() {
        return writeOffDate;
    }
    
    /**
     * @param 对writeOffDate进行赋值
     */
    public void setWriteOffDate(Date writeOffDate) {
        this.writeOffDate = writeOffDate;
    }
    
    /**
     * @return 返回 writeOffAmount
     */
    public BigDecimal getWriteOffAmount() {
        return writeOffAmount;
    }
    
    /**
     * @param 对writeOffAmount进行赋值
     */
    public void setWriteOffAmount(BigDecimal writeOffAmount) {
        this.writeOffAmount = writeOffAmount;
    }
    
    /**
     * @return 返回 writeOffRepayAmount
     */
    public BigDecimal getWriteOffRepayAmount() {
        return writeOffRepayAmount;
    }
    
    /**
     * @param 对writeOffRepayAmount进行赋值
     */
    public void setWriteOffRepayAmount(BigDecimal writeOffRepayAmount) {
        this.writeOffRepayAmount = writeOffRepayAmount;
    }
    
    /**
     * @return 返回 writeOffReason
     */
    public String getWriteOffReason() {
        return writeOffReason;
    }
    
    /**
     * @param 对writeOffReason进行赋值
     */
    public void setWriteOffReason(String writeOffReason) {
        this.writeOffReason = writeOffReason;
    }
    
    /**
     * @return 返回 locked
     */
    public boolean isLocked() {
        return locked;
    }
    
    /**
     * @param 对locked进行赋值
     */
    public void setLocked(boolean locked) {
        this.locked = locked;
    }
    
    /**
     * @return 返回 lastLockDate
     */
    public Date getLastLockDate() {
        return lastLockDate;
    }
    
    /**
     * @param 对lastLockDate进行赋值
     */
    public void setLastLockDate(Date lastLockDate) {
        this.lastLockDate = lastLockDate;
    }
    
    /**
     * @return 返回 settleInterestStatus
     */
    public SettleInterestStatusEnum getSettleInterestStatus() {
        return settleInterestStatus;
    }
    
    /**
     * @param 对settleInterestStatus进行赋值
     */
    public void setSettleInterestStatus(
            SettleInterestStatusEnum settleInterestStatus) {
        this.settleInterestStatus = settleInterestStatus;
    }
    
    /**
     * @return 返回 nextSettleInterestDate
     */
    public Date getNextSettleInterestDate() {
        return nextSettleInterestDate;
    }
    
    /**
     * @param 对nextSettleInterestDate进行赋值
     */
    public void setNextSettleInterestDate(Date nextSettleInterestDate) {
        this.nextSettleInterestDate = nextSettleInterestDate;
    }
    
    /**
     * @return 返回 accountStatus
     */
    public AccountStatusEnum getAccountStatus() {
        return accountStatus;
    }
    
    /**
     * @param 对accountStatus进行赋值
     */
    public void setAccountStatus(AccountStatusEnum accountStatus) {
        this.accountStatus = accountStatus;
    }
    
    /**
     * @return 返回 collectionStatus
     */
    public CollectionStatusEnum getCollectionStatus() {
        return collectionStatus;
    }
    
    /**
     * @param 对collectionStatus进行赋值
     */
    public void setCollectionStatus(CollectionStatusEnum collectionStatus) {
        this.collectionStatus = collectionStatus;
    }
    
    /**
     * @return 返回 died
     */
    public boolean isDied() {
        return died;
    }
    
    /**
     * @param 对died进行赋值
     */
    public void setDied(boolean died) {
        this.died = died;
    }
    
    /**
     * @return 返回 closed
     */
    public boolean isClosed() {
        return closed;
    }
    
    /**
     * @param 对closed进行赋值
     */
    public void setClosed(boolean closed) {
        this.closed = closed;
    }
    
    /**
     * @return 返回 legalProcedure
     */
    public boolean isLegalProcedure() {
        return legalProcedure;
    }
    
    /**
     * @param 对legalProcedure进行赋值
     */
    public void setLegalProcedure(boolean legalProcedure) {
        this.legalProcedure = legalProcedure;
    }
    
    /**
     * @return 返回 monthlyRepayDay
     */
    public int getMonthlyRepayDay() {
        return monthlyRepayDay;
    }
    
    /**
     * @param 对monthlyRepayDay进行赋值
     */
    public void setMonthlyRepayDay(int monthlyRepayDay) {
        this.monthlyRepayDay = monthlyRepayDay;
    }
    
    /**
     * @return 返回 monthlyRepayAmount
     */
    public BigDecimal getMonthlyRepayAmount() {
        return monthlyRepayAmount;
    }
    
    /**
     * @param 对monthlyRepayAmount进行赋值
     */
    public void setMonthlyRepayAmount(BigDecimal monthlyRepayAmount) {
        this.monthlyRepayAmount = monthlyRepayAmount;
    }
    
    /**
     * @return 返回 lastRepayDate
     */
    public Date getLastRepayDate() {
        return lastRepayDate;
    }
    
    /**
     * @param 对lastRepayDate进行赋值
     */
    public void setLastRepayDate(Date lastRepayDate) {
        this.lastRepayDate = lastRepayDate;
    }
    
    /**
     * @return 返回 nextRepayDate
     */
    public Date getNextRepayDate() {
        return nextRepayDate;
    }
    
    /**
     * @param 对nextRepayDate进行赋值
     */
    public void setNextRepayDate(Date nextRepayDate) {
        this.nextRepayDate = nextRepayDate;
    }
    
    /**
     * @return 返回 graceDays
     */
    public int getGraceDays() {
        return graceDays;
    }
    
    /**
     * @param 对graceDays进行赋值
     */
    public void setGraceDays(int graceDays) {
        this.graceDays = graceDays;
    }
    
    /**
     * @return 返回 overdue
     */
    public boolean isOverdue() {
        return overdue;
    }
    
    /**
     * @param 对overdue进行赋值
     */
    public void setOverdue(boolean overdue) {
        this.overdue = overdue;
    }
    
    /**
     * @return 返回 overdueDate
     */
    public Date getOverdueDate() {
        return overdueDate;
    }
    
    /**
     * @param 对overdueDate进行赋值
     */
    public void setOverdueDate(Date overdueDate) {
        this.overdueDate = overdueDate;
    }
    
    /**
     * @return 返回 overdueSum
     */
    public BigDecimal getOverdueSum() {
        return overdueSum;
    }
    
    /**
     * @param 对overdueSum进行赋值
     */
    public void setOverdueSum(BigDecimal overdueSum) {
        this.overdueSum = overdueSum;
    }
    
    /**
     * @return 返回 overdueAmount
     */
    public BigDecimal getOverdueAmount() {
        return overdueAmount;
    }
    
    /**
     * @param 对overdueAmount进行赋值
     */
    public void setOverdueAmount(BigDecimal overdueAmount) {
        this.overdueAmount = overdueAmount;
    }
    
    /**
     * @return 返回 overRepayAmount
     */
    public BigDecimal getOverRepayAmount() {
        return overRepayAmount;
    }
    
    /**
     * @param 对overRepayAmount进行赋值
     */
    public void setOverRepayAmount(BigDecimal overRepayAmount) {
        this.overRepayAmount = overRepayAmount;
    }
    
    /**
     * @return 返回 loanAmount
     */
    public BigDecimal getLoanAmount() {
        return loanAmount;
    }
    
    /**
     * @param 对loanAmount进行赋值
     */
    public void setLoanAmount(BigDecimal loanAmount) {
        this.loanAmount = loanAmount;
    }
    
    /**
     * @return 返回 factOutLoanAmount
     */
    public BigDecimal getFactOutLoanAmount() {
        return factOutLoanAmount;
    }
    
    /**
     * @param 对factOutLoanAmount进行赋值
     */
    public void setFactOutLoanAmount(BigDecimal factOutLoanAmount) {
        this.factOutLoanAmount = factOutLoanAmount;
    }
    
    /**
     * @return 返回 factLoanDate
     */
    public Date getFactLoanDate() {
        return factLoanDate;
    }
    
    /**
     * @param 对factLoanDate进行赋值
     */
    public void setFactLoanDate(Date factLoanDate) {
        this.factLoanDate = factLoanDate;
    }
    
    /**
     * @return 返回 interestDate
     */
    public Date getInterestDate() {
        return interestDate;
    }
    
    /**
     * @param 对interestDate进行赋值
     */
    public void setInterestDate(Date interestDate) {
        this.interestDate = interestDate;
    }
    
    /**
     * @return 返回 principalBalance
     */
    public BigDecimal getPrincipalBalance() {
        return principalBalance;
    }
    
    /**
     * @param 对principalBalance进行赋值
     */
    public void setPrincipalBalance(BigDecimal principalBalance) {
        this.principalBalance = principalBalance;
    }
    
    /**
     * @return 返回 principalBalanceIrr
     */
    public BigDecimal getPrincipalBalanceIrr() {
        return principalBalanceIrr;
    }
    
    /**
     * @param 对principalBalanceIrr进行赋值
     */
    public void setPrincipalBalanceIrr(BigDecimal principalBalanceIrr) {
        this.principalBalanceIrr = principalBalanceIrr;
    }
    
    /**
     * @return 返回 lastRepayAmount
     */
    public BigDecimal getLastRepayAmount() {
        return lastRepayAmount;
    }
    
    /**
     * @param 对lastRepayAmount进行赋值
     */
    public void setLastRepayAmount(BigDecimal lastRepayAmount) {
        this.lastRepayAmount = lastRepayAmount;
    }
    
    /**
     * @return 返回 outsourceHappend
     */
    public boolean isOutsourceHappend() {
        return outsourceHappend;
    }
    
    /**
     * @param 对outsourceHappend进行赋值
     */
    public void setOutsourceHappend(boolean outsourceHappend) {
        this.outsourceHappend = outsourceHappend;
    }
    
    /**
     * @return 返回 outsource
     */
    public boolean isOutsource() {
        return outsource;
    }
    
    /**
     * @param 对outsource进行赋值
     */
    public void setOutsource(boolean outsource) {
        this.outsource = outsource;
    }
    
    /**
     * @return 返回 outsourceAssignRecordId
     */
    public String getOutsourceAssignRecordId() {
        return outsourceAssignRecordId;
    }
    
    /**
     * @param 对outsourceAssignRecordId进行赋值
     */
    public void setOutsourceAssignRecordId(String outsourceAssignRecordId) {
        this.outsourceAssignRecordId = outsourceAssignRecordId;
    }
    
    /**
     * @return 返回 outsourcePercentage
     */
    public BigDecimal getOutsourcePercentage() {
        return outsourcePercentage;
    }
    
    /**
     * @param 对outsourcePercentage进行赋值
     */
    public void setOutsourcePercentage(BigDecimal outsourcePercentage) {
        this.outsourcePercentage = outsourcePercentage;
    }
    
    /**
     * @return 返回 outsourceAmount
     */
    public BigDecimal getOutsourceAmount() {
        return outsourceAmount;
    }
    
    /**
     * @param 对outsourceAmount进行赋值
     */
    public void setOutsourceAmount(BigDecimal outsourceAmount) {
        this.outsourceAmount = outsourceAmount;
    }
    
    /**
     * @return 返回 settleCurrentPeriod
     */
    public String getSettleCurrentPeriod() {
        return settleCurrentPeriod;
    }
    
    /**
     * @param 对settleCurrentPeriod进行赋值
     */
    public void setSettleCurrentPeriod(String settleCurrentPeriod) {
        this.settleCurrentPeriod = settleCurrentPeriod;
    }
    
    /**
     * @return 返回 settleRepayDatePeriod
     */
    public String getSettleRepayDatePeriod() {
        return settleRepayDatePeriod;
    }
    
    /**
     * @param 对settleRepayDatePeriod进行赋值
     */
    public void setSettleRepayDatePeriod(String settleRepayDatePeriod) {
        this.settleRepayDatePeriod = settleRepayDatePeriod;
    }
    
    /**
     * @return 返回 settle
     */
    public boolean isSettle() {
        return settle;
    }
    
    /**
     * @param 对settle进行赋值
     */
    public void setSettle(boolean settle) {
        this.settle = settle;
    }
    
    /**
     * @return 返回 settleDate
     */
    public Date getSettleDate() {
        return settleDate;
    }
    
    /**
     * @param 对settleDate进行赋值
     */
    public void setSettleDate(Date settleDate) {
        this.settleDate = settleDate;
    }
    
    /**
     * @return 返回 settleRepayDate
     */
    public Date getSettleRepayDate() {
        return settleRepayDate;
    }
    
    /**
     * @param 对settleRepayDate进行赋值
     */
    public void setSettleRepayDate(Date settleRepayDate) {
        this.settleRepayDate = settleRepayDate;
    }
    
    /**
     * @return 返回 settleSL
     */
    public boolean isSettleSL() {
        return settleSL;
    }
    
    /**
     * @param 对settleSL进行赋值
     */
    public void setSettleSL(boolean settleSL) {
        this.settleSL = settleSL;
    }
    
    /**
     * @return 返回 settleSLRepayDate
     */
    public Date getSettleSLRepayDate() {
        return settleSLRepayDate;
    }
    
    /**
     * @param 对settleSLRepayDate进行赋值
     */
    public void setSettleSLRepayDate(Date settleSLRepayDate) {
        this.settleSLRepayDate = settleSLRepayDate;
    }
    
    /**
     * @return 返回 settleSLDate
     */
    public Date getSettleSLDate() {
        return settleSLDate;
    }
    
    /**
     * @param 对settleSLDate进行赋值
     */
    public void setSettleSLDate(Date settleSLDate) {
        this.settleSLDate = settleSLDate;
    }
    
    /**
     * @return 返回 sourceLoanAccountId
     */
    public String getSourceLoanAccountId() {
        return sourceLoanAccountId;
    }
    
    /**
     * @param 对sourceLoanAccountId进行赋值
     */
    public void setSourceLoanAccountId(String sourceLoanAccountId) {
        this.sourceLoanAccountId = sourceLoanAccountId;
    }
    
    /**
     * @return 返回 sourceLastTradingRecordId
     */
    public String getSourceLastTradingRecordId() {
        return sourceLastTradingRecordId;
    }
    
    /**
     * @param 对sourceLastTradingRecordId进行赋值
     */
    public void setSourceLastTradingRecordId(String sourceLastTradingRecordId) {
        this.sourceLastTradingRecordId = sourceLastTradingRecordId;
    }
    
    /**
     * @return 返回 createDate
     */
    public Date getCreateDate() {
        return createDate;
    }
    
    /**
     * @param 对createDate进行赋值
     */
    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }
    
    /**
     * @return 返回 lastUpdateDate
     */
    public Date getLastUpdateDate() {
        return lastUpdateDate;
    }
    
    /**
     * @param 对lastUpdateDate进行赋值
     */
    public void setLastUpdateDate(Date lastUpdateDate) {
        this.lastUpdateDate = lastUpdateDate;
    }
    
    /**
     * @return 返回 settleSLRepayDatePeriod
     */
    public String getSettleSLRepayDatePeriod() {
        return settleSLRepayDatePeriod;
    }
    
    /**
     * @param 对settleSLRepayDatePeriod进行赋值
     */
    public void setSettleSLRepayDatePeriod(String settleSLRepayDatePeriod) {
        this.settleSLRepayDatePeriod = settleSLRepayDatePeriod;
    }
    
    /**
     * @return 返回 currentPeriodExpireDate
     */
    public Date getCurrentPeriodExpireDate() {
        return currentPeriodExpireDate;
    }
    
    /**
     * @param 对currentPeriodExpireDate进行赋值
     */
    public void setCurrentPeriodExpireDate(Date currentPeriodExpireDate) {
        this.currentPeriodExpireDate = currentPeriodExpireDate;
    }
    
    /**
     * @return 返回 nextOverdueCheckDate
     */
    public Date getNextOverdueCheckDate() {
        return nextOverdueCheckDate;
    }
    
    /**
     * @param 对nextOverdueCheckDate进行赋值
     */
    public void setNextOverdueCheckDate(Date nextOverdueCheckDate) {
        this.nextOverdueCheckDate = nextOverdueCheckDate;
    }
    
    /**
     * @return 返回 existNotReceivedTrading
     */
    public boolean isExistNotReceivedTrading() {
        return existNotReceivedTrading;
    }
    
    /**
     * @param 对existNotReceivedTrading进行赋值
     */
    public void setExistNotReceivedTrading(boolean existNotReceivedTrading) {
        this.existNotReceivedTrading = existNotReceivedTrading;
    }
    
    /**
     * @return 返回 inHisTable
     */
    public boolean isInHisTable() {
        return inHisTable;
    }
    
    /**
     * @param 对inHisTable进行赋值
     */
    public void setInHisTable(boolean inHisTable) {
        this.inHisTable = inHisTable;
    }
    
    /**
     * @return 返回 vcid
     */
    public String getVcid() {
        return vcid;
    }
    
    /**
     * @param 对vcid进行赋值
     */
    public void setVcid(String vcid) {
        this.vcid = vcid;
    }
    
    /**
     * @return 返回 organizationId
     */
    public String getOrganizationId() {
        return organizationId;
    }
    
    /**
     * @param 对organizationId进行赋值
     */
    public void setOrganizationId(String organizationId) {
        this.organizationId = organizationId;
    }
    
    /**
     * @return 返回 serialversionuid
     */
    public static long getSerialversionuid() {
        return serialVersionUID;
    }
}
