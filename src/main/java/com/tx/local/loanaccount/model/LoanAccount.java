/*
 * 描          述:  <描述>
 * 修  改   人:  Administrator
 * 修改时间:  2014年4月10日
 * <修改描述:>
 */
package com.tx.local.loanaccount.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Map;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.tx.local.basicdata.model.District;
import com.tx.local.basicdata.model.FeeItemCfg;
import com.tx.local.basicdata.model.FeeItemEnum;
import com.tx.local.basicdata.model.IDCardTypeEnum;
import com.tx.local.basicdata.model.RepayWayEnum;
import com.tx.local.basicdata.model.TimeUnitTypeEnum;

/**
 * 贷款账户|应收账款<br/>
 * <功能详细描述>
 * 
 * @author  Administrator
 * @version  [版本号, 2014年4月10日]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
@Entity
@Table(name = "la_loan_account")
public class LoanAccount implements Serializable {
    
    /** 注释内容 */
    private static final long serialVersionUID = -6031308364401303434L;
    
    /** 主键： */
    @Id
    private String id;
    
    /** 贷款账户所属虚中心 */
    @Column(nullable = false, updatable = false, length = 64)
    private String vcid;
    
    /** 原贷贷款账户id(续贷，再贷关联的原贷款账户) */
    @Column(nullable = true, updatable = false, length = 64)
    private String sourceLoanAccountId;
    
    /** 原贷款账户最后一笔交易记录id */
    @Column(nullable = true, updatable = false, length = 64)
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
    
    /** 到期时间/合同结束日期：获取到合同实例，将合同的结束日期填入 不变 expiredDate */
    private Date expiryDate;
    
    /** 计息日 */
    private Date interestDate;
    
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
    
    /** 下次还款日期: 应还款日，以及逾期时处理，及延期的业务逻辑，需要一并进行考虑  */
    private Date nextDeductDate;
    
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
    
    /** 是否历史账户：贷款账户迁移至历史表后，该标记为true */
    private boolean inHisTable;
    
    /** 贷款账户所属组织id:保留字段 */
    private String organizationId;
    
    /** 区ID */
    @Column(name = "districtId")
    private District district;
    
    /** 费用配置项映射 */
    @Transient
    private Map<FeeItemEnum, LoanAccountFeeItem> feeItemMap;
    
    /** 费用项配置映射 */
    @Transient
    private Map<FeeItemEnum, FeeItemCfg> feeItemCfgMap;
    
    /**
     * @return 返回 feeItemCfgMap
     */
    public Map<FeeItemEnum, FeeItemCfg> getFeeItemCfgMap() {
        return feeItemCfgMap;
    }
    
    /**
     * @param 对feeItemCfgMap进行赋值
     */
    public void setFeeItemCfgMap(Map<FeeItemEnum, FeeItemCfg> feeItemCfgMap) {
        this.feeItemCfgMap = feeItemCfgMap;
    }
    
    /**
     * @return 返回 feeCfgItemMap
     */
    public Map<FeeItemEnum, LoanAccountFeeItem> getFeeItemSettingMap() {
        return feeItemMap;
    }
    
    /**
     * @return 返回 feeItemMap
     */
    public Map<FeeItemEnum, LoanAccountFeeItem> getFeeItemMap() {
        return feeItemMap;
    }
    
    /**
     * @param 对feeItemMap进行赋值
     */
    public void setFeeItemMap(Map<FeeItemEnum, LoanAccountFeeItem> feeItemMap) {
        this.feeItemMap = feeItemMap;
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
    
    /** 贷款账户类型:  common */
    public LoanAccountTypeEnum getLoanAccountType() {
        return loanAccountType;
    }
    
    /** 贷款账户类型:  common */
    public void setLoanAccountType(LoanAccountTypeEnum loanAccountType) {
        this.loanAccountType = loanAccountType;
    }
    
    /** 贷款账户对应客户账户 */
    public String getClientAccountId() {
        return clientAccountId;
    }
    
    /** 贷款账户对应客户账户 */
    public void setClientAccountId(String clientAccountId) {
        this.clientAccountId = clientAccountId;
    }
    
    /** 系统生成 创建时间：客户账户创建的时间 不变*/
    public Date getCreateDate() {
        return createDate;
    }
    
    /** 系统生成 创建时间：客户账户创建的时间 不变*/
    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }
    
    /** 最后更新时间：首次填入客户账户创建时间，后面每次更新都需要跟新该字段 更次更新都需要变更 */
    public Date getLastUpdateDate() {
        return lastUpdateDate;
    }
    
    /** 最后更新时间：首次填入客户账户创建时间，后面每次更新都需要跟新该字段 更次更新都需要变更 */
    public void setLastUpdateDate(Date lastUpdateDate) {
        this.lastUpdateDate = lastUpdateDate;
    }
    
    /** 贷前延期天数 */
    public int getBeforeDelayDays() {
        return beforeDelayDays;
    }
    
    /** 贷前延期天数 */
    public void setBeforeDelayDays(int beforeDelayDays) {
        this.beforeDelayDays = beforeDelayDays;
    }
    
    /** 贷后延期天数 */
    public int getAfterDelayDays() {
        return afterDelayDays;
    }
    
    /** 贷后延期天数 */
    public void setAfterDelayDays(int afterDelayDays) {
        this.afterDelayDays = afterDelayDays;
    }
    
    /** 生成后主动调用贷前客户快照功能 不变  贷款合同主键：不变 贷款合同编号：*/
    public String getContractId() {
        return contractId;
    }
    
    /** 生成后主动调用贷前客户快照功能 不变  贷款合同主键：不变 贷款合同编号：*/
    public void setContractId(String contractId) {
        this.contractId = contractId;
    }
    
    /** 留空：贷前暂为规划合同实体 从贷款 不变*/
    public String getContractNumber() {
        return contractNumber;
    }
    
    /** 留空：贷前暂为规划合同实体 从贷款 不变*/
    public void setContractNumber(String contractNumber) {
        this.contractNumber = contractNumber;
    }
    
    /**
     * 生效时间/合同起始日期： 
     * 获取到合同实例，将合同的合同起始日期填入
     * 不变
     */
    public Date getEffectiveDate() {
        return effectiveDate;
    }
    
    /**
     * 生效时间/合同起始日期： 
     * 获取到合同实例，将合同的合同起始日期填入
     * 不变
     */
    public void setEffectiveDate(Date effectiveDate) {
        this.effectiveDate = effectiveDate;
    }
    
    /** 
     * 到期时间/合同结束日期：获取到合同实例，
     * 将合同的结束日期填入 
     * 不变 
     */
    public Date getExpiryDate() {
        return expiryDate;
    }
    
    /** 
     * 到期时间/合同结束日期：获取到合同实例，
     * 将合同的结束日期填入 
     * 不变 
     */
    public void setExpiryDate(Date expiryDate) {
        this.expiryDate = expiryDate;
    }
    
    /** 
     * 客户id:参数传入
     * 不变 
     */
    public String getClientId() {
        return clientId;
    }
    
    /** 
     * 客户id:参数传入
     * 不变 
     */
    public void setClientId(String clientId) {
        this.clientId = clientId;
    }
    
    /** 
     * 客户姓名： 
     * 找到客户实例后填入
     * 极少情况发生变化不变
     */
    public String getClientName() {
        return clientName;
    }
    
    /** 
     * 客户姓名： 
     * 找到客户实例后填入
     * 极少情况发生变化不变
     */
    public void setClientName(String clientName) {
        this.clientName = clientName;
    }
    
    /** 
     * 证 件类型：
     * 根据客户实例填入
     * 极少情况发生变化
     * clientIdCardType[IDCardType?放名还是code还是id?]
     */
    public IDCardTypeEnum getClientIdCardType() {
        return clientIdCardType;
    }
    
    /** 
     * 证 件类型：
     * 根据客户实例填入
     * 极少情况发生变化
     * clientIdCardType[IDCardType?放名还是code还是id?]
     */
    public void setClientIdCardType(IDCardTypeEnum clientIdCardType) {
        this.clientIdCardType = clientIdCardType;
    }
    
    /**
     * 证件号码:
     * 找到客户实例后填入
     * 不变 
     */
    public String getClientIdCardNumber() {
        return clientIdCardNumber;
    }
    
    /**
     * 证件号码:
     * 找到客户实例后填入
     * 不变 
     */
    public void setClientIdCardNumber(String clientIdCardNumber) {
        this.clientIdCardNumber = clientIdCardNumber;
    }
    
    /** 首期还款日期:还款计划中第一次还款日期，根据合同填入，后面不再更新 */
    public Date getFirstRepayDate() {
        return firstRepayDate;
    }
    
    /** 首期还款日期:还款计划中第一次还款日期，根据合同填入，后面不再更新 */
    public void setFirstRepayDate(Date firstRepayDate) {
        this.firstRepayDate = firstRepayDate;
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
    
    /** 总期数：不变 根据贷款合同填入 */
    public int getTotalPeriod() {
        return totalPeriod;
    }
    
    /** 总期数：不变 根据贷款合同填入 */
    public void setTotalPeriod(int totalPeriod) {
        this.totalPeriod = totalPeriod;
    }
    
    /** 已付期数: */
    public BigDecimal getPaidPeriod() {
        return paidPeriod;
    }
    
    /** 已付期数: */
    public void setPaidPeriod(BigDecimal paidPeriod) {
        this.paidPeriod = paidPeriod;
    }
    
    /** 当前期数: */
    public String getCurrentPeriod() {
        return currentPeriod;
    }
    
    /** 当前期数: */
    public void setCurrentPeriod(String currentPeriod) {
        this.currentPeriod = currentPeriod;
    }
    
    /** 是否退回：*/
    public boolean isRevoked() {
        return revoked;
    }
    
    /** 是否退回：*/
    public void setRevoked(boolean revoked) {
        this.revoked = revoked;
    }
    
    /** 退回时间： */
    public Date getRevokeDate() {
        return revokeDate;
    }
    
    /** 退回时间： */
    public void setRevokeDate(Date revokeDate) {
        this.revokeDate = revokeDate;
    }
    
    /** 退回原因：*/
    public String getRevokeReason() {
        return revokeReason;
    }
    
    /** 退回原因：*/
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
    
    /** 帐户状态 */
    public AccountStatusEnum getAccountStatus() {
        return accountStatus;
    }
    
    /** 帐户状态 */
    public void setAccountStatus(AccountStatusEnum accountStatus) {
        this.accountStatus = accountStatus;
    }
    
    /** 催收状态 */
    public CollectionStatusEnum getCollectionStatus() {
        return collectionStatus;
    }
    
    /** 催收状态 */
    public void setCollectionStatus(CollectionStatusEnum collectionStatus) {
        this.collectionStatus = collectionStatus;
    }
    
    /** 是否死亡 */
    public boolean isDied() {
        return died;
    }
    
    /** 是否死亡 */
    public void setDied(boolean died) {
        this.died = died;
    }
    
    /** 是否关闭 */
    public boolean isClosed() {
        return closed;
    }
    
    /** 是否关闭 */
    public void setClosed(boolean closed) {
        this.closed = closed;
    }
    
    /** 是否法律程序 */
    public boolean isLegalProcedure() {
        return legalProcedure;
    }
    
    /** 是否法律程序 */
    public void setLegalProcedure(boolean legalProcedure) {
        this.legalProcedure = legalProcedure;
    }
    
    /** 每月还款日:填入每月还款日 */
    public int getMonthlyRepayDay() {
        return monthlyRepayDay;
    }
    
    /** 每月还款日:填入每月还款日 */
    public void setMonthlyRepayDay(int monthlyRepayDay) {
        this.monthlyRepayDay = monthlyRepayDay;
    }
    
    /** 每月还款金额 */
    public BigDecimal getMonthlyRepayAmount() {
        return monthlyRepayAmount;
    }
    
    /** 每月还款金额 */
    public void setMonthlyRepayAmount(BigDecimal monthlyRepayAmount) {
        this.monthlyRepayAmount = monthlyRepayAmount;
    }
    
    /** 上次还款日: 填入下次还款日期 */
    public Date getLastRepayDate() {
        return lastRepayDate;
    }
    
    /** 上次还款日: 填入下次还款日期 */
    public void setLastRepayDate(Date lastRepayDate) {
        this.lastRepayDate = lastRepayDate;
    }
    
    /** 下次还款日期: 应还款日，以及逾期时处理，及延期的业务逻辑，需要一并进行考虑  */
    public Date getNextRepayDate() {
        return nextRepayDate;
    }
    
    /** 下次还款日期: 应还款日，以及逾期时处理，及延期的业务逻辑，需要一并进行考虑  */
    public void setNextRepayDate(Date nextRepayDate) {
        this.nextRepayDate = nextRepayDate;
    }
    
    /**
     * @return 返回 nextDeductDate
     */
    public Date getNextDeductDate() {
        return nextDeductDate;
    }
    
    /**
     * @param 对nextDeductDate进行赋值
     */
    public void setNextDeductDate(Date nextDeductDate) {
        this.nextDeductDate = nextDeductDate;
    }
    
    /** 是否逾期: 账户发生逾期后更新该日期 账户逾期的变化仅在夜间事务中进行设置 为了平账白天调整过程中可能发生逾期的变更 */
    public boolean isOverdue() {
        return overdue;
    }
    
    /** 是否逾期: 账户发生逾期后更新该日期 账户逾期的变化仅在夜间事务中进行设置 为了平账白天调整过程中可能发生逾期的变更 */
    public void setOverdue(boolean overdue) {
        this.overdue = overdue;
    }
    
    /** 逾期日期: 账户发生逾期后更新该日期 */
    public Date getOverdueDate() {
        return overdueDate;
    }
    
    /** 逾期日期: 账户发生逾期后更新该日期 */
    public void setOverdueDate(Date overdueDate) {
        this.overdueDate = overdueDate;
    }
    
    /** 逾期金额总额:所有逾期金额之和与其他应收金额之和  逾期总额：每日夜间事务处理 */
    public BigDecimal getOverdueSum() {
        return overdueSum;
    }
    
    /** 逾期金额总额:所有逾期金额之和与其他应收金额之和  逾期总额：每日夜间事务处理 */
    public void setOverdueSum(BigDecimal overdueSum) {
        this.overdueSum = overdueSum;
    }
    
    /** 逾期金额: 所有费用类型为逾期依赖的金额之和 （在现有业务中实际是为：逾期的 本金+利息+管理费 之和）*/
    public BigDecimal getOverdueAmount() {
        return overdueAmount;
    }
    
    /** 逾期金额: 所有费用类型为逾期依赖的金额之和 （在现有业务中实际是为：逾期的 本金+利息+管理费 之和）*/
    public void setOverdueAmount(BigDecimal overdueAmount) {
        this.overdueAmount = overdueAmount;
    }
    
    /** 贷款金额：不变 */
    public BigDecimal getLoanAmount() {
        return loanAmount;
    }
    
    /** 贷款金额：不变 */
    public void setLoanAmount(BigDecimal loanAmount) {
        this.loanAmount = loanAmount;
    }
    
    /** 本金结余 */
    public BigDecimal getPrincipalBalance() {
        return principalBalance;
    }
    
    /** 本金结余 */
    public void setPrincipalBalance(BigDecimal principalBalance) {
        this.principalBalance = principalBalance;
    }
    
    /** 本金结余递减 */
    public BigDecimal getPrincipalBalanceIrr() {
        return principalBalanceIrr;
    }
    
    /** 本金结余递减 */
    public void setPrincipalBalanceIrr(BigDecimal principalBalanceIrr) {
        this.principalBalanceIrr = principalBalanceIrr;
    }
    
    /** 贷款账户所属虚中心 */
    public String getVcid() {
        return vcid;
    }
    
    /** 贷款账户所属虚中心 */
    public void setVcid(String vcid) {
        this.vcid = vcid;
    }
    
    /** 贷款账户所属组织id:保留字段 */
    public String getOrganizationId() {
        return organizationId;
    }
    
    /** 贷款账户所属组织id:保留字段 */
    public void setOrganizationId(String organizationId) {
        this.organizationId = organizationId;
    }
    
    /** 宽限天数:会对逾期利息产生影响 */
    public int getGraceDays() {
        return graceDays;
    }
    
    /** 宽限天数:会对逾期利息产生影响 */
    public void setGraceDays(int graceDays) {
        this.graceDays = graceDays;
    }
    
    /** 最后逾期检测日 [保留字段] */
    public Date getNextOverdueCheckDate() {
        return nextOverdueCheckDate;
    }
    
    /** 最后逾期检测日 [保留字段] */
    public void setNextOverdueCheckDate(Date nextOverdueCheckDate) {
        this.nextOverdueCheckDate = nextOverdueCheckDate;
    }
    
    /** 实际放款金额 */
    public BigDecimal getFactOutLoanAmount() {
        return factOutLoanAmount;
    }
    
    /** 实际放款金额 */
    public void setFactOutLoanAmount(BigDecimal factOutLoanAmount) {
        this.factOutLoanAmount = factOutLoanAmount;
    }
    
    /** 超额还款金额 */
    public BigDecimal getOverRepayAmount() {
        return overRepayAmount;
    }
    
    /** 超额还款金额 */
    public void setOverRepayAmount(BigDecimal overRepayAmount) {
        this.overRepayAmount = overRepayAmount;
    }
    
    /** 是否历史账户：贷款账户迁移至历史表后，该标记为true */
    public boolean isInHisTable() {
        return inHisTable;
    }
    
    /** 是否历史账户：贷款账户迁移至历史表后，该标记为true */
    public void setInHisTable(boolean inHisTable) {
        this.inHisTable = inHisTable;
    }
    
    /** 当前期数到期日  */
    public Date getCurrentPeriodExpireDate() {
        return currentPeriodExpireDate;
    }
    
    /** 当前期数到期日  */
    public void setCurrentPeriodExpireDate(Date currentPeriodExpireDate) {
        this.currentPeriodExpireDate = currentPeriodExpireDate;
    }
    
    /** 最近一次还款金额 */
    public BigDecimal getLastRepayAmount() {
        return lastRepayAmount;
    }
    
    /** 最近一次还款金额 */
    public void setLastRepayAmount(BigDecimal lastRepayAmount) {
        this.lastRepayAmount = lastRepayAmount;
    }
    
    /** 注销金额 */
    public BigDecimal getWriteOffAmount() {
        return writeOffAmount;
    }
    
    /** 注销金额 */
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
    
    /** 下次结息日 */
    public Date getNextSettleInterestDate() {
        return nextSettleInterestDate;
    }
    
    /** 下次结息日 */
    public void setNextSettleInterestDate(Date nextSettleInterestDate) {
        this.nextSettleInterestDate = nextSettleInterestDate;
    }
    
    /** 原贷贷款账户id(续贷，再贷关联的原贷款账户) */
    public String getSourceLoanAccountId() {
        return sourceLoanAccountId;
    }
    
    /** 原贷贷款账户id(续贷，再贷关联的原贷款账户) */
    public void setSourceLoanAccountId(String sourceLoanAccountId) {
        this.sourceLoanAccountId = sourceLoanAccountId;
    }
    
    /** 原贷款账户最后一笔交易记录id */
    public String getSourceLastTradingRecordId() {
        return sourceLastTradingRecordId;
    }
    
    /** 原贷款账户最后一笔交易记录id */
    public void setSourceLastTradingRecordId(String sourceLastTradingRecordId) {
        this.sourceLastTradingRecordId = sourceLastTradingRecordId;
    }
    
    /** 实际放款时间 */
    public Date getFactLoanDate() {
        return factLoanDate;
    }
    
    /** 实际放款时间 */
    public void setFactLoanDate(Date factLoanDate) {
        this.factLoanDate = factLoanDate;
    }
    
    /** 委外是否发生过 */
    public boolean isOutsourceHappend() {
        return outsourceHappend;
    }
    
    /** 委外是否发生过 */
    public void setOutsourceHappend(boolean outsourceHappend) {
        this.outsourceHappend = outsourceHappend;
    }
    
    /** 是否正在发生委外 */
    public boolean isOutsource() {
        return outsource;
    }
    
    /** 是否正在发生委外 */
    public void setOutsource(boolean outsource) {
        this.outsource = outsource;
    }
    
    /** 对应委外分配记录id */
    public String getOutsourceAssignRecordId() {
        return outsourceAssignRecordId;
    }
    
    /** 对应委外分配记录id */
    public void setOutsourceAssignRecordId(String outsourceAssignRecordId) {
        this.outsourceAssignRecordId = outsourceAssignRecordId;
    }
    
    /** 委外外包佣金比率 */
    public BigDecimal getOutsourcePercentage() {
        return outsourcePercentage;
    }
    
    /** 委外外包佣金比率 */
    public void setOutsourcePercentage(BigDecimal outsourcePercentage) {
        this.outsourcePercentage = outsourcePercentage;
    }
    
    /** 委外委托金额 */
    public BigDecimal getOutsourceAmount() {
        return outsourceAmount;
    }
    
    /** 委外委托金额 */
    public void setOutsourceAmount(BigDecimal outsourceAmount) {
        this.outsourceAmount = outsourceAmount;
    }
    
    /** 结清时对应的当前期数 */
    public String getSettleCurrentPeriod() {
        return settleCurrentPeriod;
    }
    
    /** 结清时对应的当前期数 */
    public void setSettleCurrentPeriod(String settleCurrentPeriod) {
        this.settleCurrentPeriod = settleCurrentPeriod;
    }
    
    /** 结清还款日对应的期数 */
    public String getSettleRepayDatePeriod() {
        return settleRepayDatePeriod;
    }
    
    /** 结清还款日对应的期数 */
    public void setSettleRepayDatePeriod(String settleRepayDatePeriod) {
        this.settleRepayDatePeriod = settleRepayDatePeriod;
    }
    
    /** 结清交易发生日期 */
    public Date getSettleDate() {
        return settleDate;
    }
    
    /** 结清交易发生日期 */
    public void setSettleDate(Date settleDate) {
        this.settleDate = settleDate;
    }
    
    /** 结清交易发生的还款日期 */
    public Date getSettleRepayDate() {
        return settleRepayDate;
    }
    
    /** 结清交易发生的还款日期 */
    public void setSettleRepayDate(Date settleRepayDate) {
        this.settleRepayDate = settleRepayDate;
    }
    
    /** 是否结清 */
    public boolean isSettle() {
        return settle;
    }
    
    /** 是否结清 */
    public void setSettle(boolean settle) {
        this.settle = settle;
    }
    
    /** 是否SL结清 */
    public boolean isSettleSL() {
        return settleSL;
    }
    
    /** 是否SL结清 */
    public void setSettleSL(boolean settleSL) {
        this.settleSL = settleSL;
    }
    
    /** SL结清交易发生日期,SL结清时 */
    public Date getSettleSLDate() {
        return settleSLDate;
    }
    
    /** SL结清交易发生日期,SL结清时 */
    public void setSettleSLDate(Date settleSLDate) {
        this.settleSLDate = settleSLDate;
    }
    
    /** SL结清交易发生的还款日期 */
    public Date getSettleSLRepayDate() {
        return this.settleSLRepayDate;
    }
    
    /** SL结清交易发生的还款日期 */
    public void setSettleSLRepayDate(Date settleSLRepayDate) {
        this.settleSLRepayDate = settleSLRepayDate;
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
     * @return 返回 district
     */
    public District getDistrict() {
        return district;
    }
    
    /**
     * @param 对district进行赋值
     */
    public void setDistrict(District district) {
        this.district = district;
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
    
}
