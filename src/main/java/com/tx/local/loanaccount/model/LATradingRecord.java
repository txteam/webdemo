/*
 * 描          述:  <描述>
 * 修  改   人:  Administrator
 * 修改时间:  2014年4月23日
 * <修改描述:>
 */
package com.tx.local.loanaccount.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.apache.commons.collections4.CollectionUtils;
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.tx.core.TxConstants;
import com.tx.core.util.ObjectUtils;
import com.tx.local.basicdata.model.FeeItemEnum;
import com.tx.local.loanaccount.helper.paymentrecord.PaymentRecordHelper;

/**
 * 交易记录<br/>
 * 
 * @author   Tim.peng
 * @version  [版本号, 2014年4月23日]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
@Entity
@Table(name = "la_trading_record")
public class LATradingRecord implements Serializable {
    
    /** 注释内容 */
    private static final long serialVersionUID = -6031308364401303434L;
    
    /** 主键 */
    @Id
    private String id;
    
    /** 关联贷款单账户id */
    private String loanAccountId;
    
    /** 操作流水 */
    private String requestId;
    
    /** 关联交易主键: 撤销交易->被撤销的交易    调账记录->被调账的记录  */
    private String relatedTradingRecordId;
    
    /** 生成方式/交易记录来源类型 */
    private RequestSourceTypeEnum sourceType;
    
    /** 交易类型:收款  放款 撤销 */
    private LATradingCategoryEnum category;
    
    /** 交易类型：用以区分操作交易，撤销交易，以及自动交易:必填 */
    private LATradingRecordTypeEnum type;
    
    /** 银行账户id */
    private String bankAccountId;
    
    /** 还款日:指定的还款日期 */
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date repayDate;
    
    /** 还款期数 */
    private String repayDatePeriod;
    
    /** 交易当前期数 */
    private String currentPeriod;
    
    /** 还款到期日期:记录当前交易的还款到期日 */
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date expireDate;
    
    /** 还款金额 */
    private BigDecimal repaySum = BigDecimal.ZERO;
    
    /** 交易金额 */
    private BigDecimal sum = BigDecimal.ZERO;
    
    /** 交易摘要: */
    private String summary;
    
    /** 是否可见 */
    private boolean viewAble;
    
    /** 是否为调账交易 */
    private boolean adjust = false;
    
    /** 是否已到账 */
    private boolean received = true;
    
    /** 到账时间 */
    private Date receiveDate;
    
    /** 是否可撤销 */
    private boolean revokeAble;
    
    /** 是被否退回/撤销 */
    private boolean revoked;
    
    /** 撤销原因  */
    private String revokeResean;
    
    /** 撤销操作员 */
    private String revokeOperatorId;
    
    /** 撤销时间  */
    private Date revokeDate;
    
    /** 是否是已注销贷款账户发生的交易 */
    private boolean writeOff;
    
    /** 到账时间 */
    private Date writeOffDate;
    
    /** 交易发生的那一刻对应的委外分配记录id */
    private String outsourceAssignRecordId;
    
    /** 是否委外期间发生的交易：如果为false则不会记收外包佣金:revoke时，这个应该根据被撤销交易的信息进行填入 */
    private boolean outsourceRepay;
    
    /** 本金结余： */
    private BigDecimal principalBalance;
    
    /** 本金结余递减：*/
    private BigDecimal principalBalanceIrr;
    
    /** 交易前贷款账户状态 */
    private AccountStatusEnum beforeAccountStatus;
    
    /** 交易前催收状态 */
    private CollectionStatusEnum beforeCollectionStatus;
    
    /** 交易前结息状态状态 */
    private SettleInterestStatusEnum beforeSettleInterestStatus;
    
    /** 交易前贷款账户是否死亡状态 */
    private boolean beforeIsDie;
    
    /** 交易前贷款账户状态 */
    private boolean beforeIsLegalProcedure;
    
    /** 交易前贷款账户是否关闭状态 */
    private boolean beforeIsClose;
    
    /** 交易前本金结余： */
    private BigDecimal beforePrincipalBalance;
    
    /** 交易前本金结余递减：*/
    private BigDecimal beforePrincipalBalanceIrr;
    
    /** 交易前是否逾期 */
    private boolean beforeIsOverdue;
    
    /** 交易前逾期日期 */
    private Date beforeOverdueDate;
    
    /** 交易前逾期金额 */
    private BigDecimal beforeOverdueAmount;
    
    /** 交易前逾期总额 */
    private BigDecimal beforeOverdueSum;
    
    /** 交易后贷款账户状态 */
    private AccountStatusEnum afterAccountStatus;
    
    /** 交易后结息账户状态 */
    private CollectionStatusEnum afterCollectionStatus;
    
    //    /** 交易后贷款账户状态 */
    private SettleInterestStatusEnum afterSettleInterestStatus;
    
    /** 交易后贷款账户是否死亡状态 */
    private boolean afterIsDie;
    
    //    /** 交易后贷款账户状态 */
    private boolean afterIsLegalProcedure;
    
    /** 交易后贷款账户是否关闭状态 */
    private boolean afterIsClose;
    
    /** 交易后本金结余： */
    private BigDecimal afterPrincipalBalance;
    
    /** 交易后本金结余递减：*/
    private BigDecimal afterPrincipalBalanceIrr;
    
    /** 交易后是否逾期 */
    private boolean afterIsOverdue;
    
    /** 交易后逾期日期 */
    private Date afterOverdueDate;
    
    /** 交易后逾期金额 */
    private BigDecimal afterOverdueAmount;
    
    /** 交易后逾期总额 */
    private BigDecimal afterOverdueSum;
    
    /** 处理虚中心 */
    private String vcid;
    
    /** 处理组织 */
    private String organizationId;
    
    /** 处理人 */
    private String operatorId;
    
    /** 更新人 */
    private String lastUpdateOperatorId;
    
    /**更新时间 */
    private Date lastUpdateDate;
    
    /** 创建时间 */
    private Date createDate;
    
    /** 其他参数：存放json数据 */
    private String attributes;
    
    /**备注 */
    private String remark;
    
    /** 交易记录分项列表 */
    @OneToMany
    private List<LATradingRecordEntry> tradingRecordEntryList = new ArrayList<>(
            TxConstants.INITIAL_CONLLECTION_SIZE);
    
    /** 计费记录 */
    @OneToMany
    private List<ChargeRecord> chargeRecordList = new ArrayList<>(
            TxConstants.INITIAL_CONLLECTION_SIZE);
    
    /** 豁免记录 */
    @OneToMany
    private List<ExemptRecord> exemptRecordList = new ArrayList<>(
            TxConstants.INITIAL_CONLLECTION_SIZE);
    
    /** 实收记录 */
    @OneToMany
    private List<PaymentRecord> paymentRecordList = new ArrayList<>(
            TxConstants.INITIAL_CONLLECTION_SIZE);
    
    /** 实收记录分项 */
    @OneToMany
    private List<PaymentRecordEntry> paymentRecordEntryList = new ArrayList<>(
            TxConstants.INITIAL_CONLLECTION_SIZE);
    
    /** 计费记录分项 */
    @OneToMany
    private List<ChargeRecordEntry> chargeRecordEntryList = new ArrayList<>(
            TxConstants.INITIAL_CONLLECTION_SIZE);
    
    /** 豁免记录分项 */
    @OneToMany
    private List<ExemptRecordEntry> exemptRecordEntryList = new ArrayList<>(
            TxConstants.INITIAL_CONLLECTION_SIZE);
    
    /** 计费记录分项集合 */
    @Transient
    @JsonIgnore
    private final ScheduleAssociateMap<ChargeRecord> chargeRecordMap = new ScheduleAssociateMap<ChargeRecord>();
    
    /** 计费记录分项集合 */
    @Transient
    @JsonIgnore
    private final ScheduleAssociateMap<ExemptRecord> exemptRecordMap = new ScheduleAssociateMap<ExemptRecord>();
    
    /** 支付记录分项集合 */
    @Transient
    @JsonIgnore
    private final ScheduleAssociateMap<PaymentRecord> paymentRecordMap = new ScheduleAssociateMap<PaymentRecord>();
    
    /** 计费记录分项集合 */
    @Transient
    @JsonIgnore
    private final ScheduleEntryAssociateMap<ChargeRecordEntry> chargeRecordEntryMap = new ScheduleEntryAssociateMap<ChargeRecordEntry>();
    
    /** 计费记录分项集合 */
    @Transient
    @JsonIgnore
    private final ScheduleEntryAssociateMap<ExemptRecordEntry> exemptRecordEntryMap = new ScheduleEntryAssociateMap<ExemptRecordEntry>();
    
    /** 支付记录分项集合 */
    @Transient
    @JsonIgnore
    private final ScheduleEntryAssociateMap<PaymentRecordEntry> paymentRecordEntryMap = new ScheduleEntryAssociateMap<PaymentRecordEntry>();
    
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
     * @return 返回 loanAccountId
     */
    public String getLoanAccountId() {
        return loanAccountId;
    }
    
    /**
     * @param 对loanAccountId进行赋值
     */
    public void setLoanAccountId(String loanAccountId) {
        this.loanAccountId = loanAccountId;
    }
    
    /**
     * @return 返回 requestId
     */
    public String getRequestId() {
        return requestId;
    }
    
    /**
     * @param 对requestId进行赋值
     */
    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }
    
    /**
     * @return 返回 relatedTradingRecordId
     */
    public String getRelatedTradingRecordId() {
        return relatedTradingRecordId;
    }
    
    /**
     * @param 对relatedTradingRecordId进行赋值
     */
    public void setRelatedTradingRecordId(String relatedTradingRecordId) {
        this.relatedTradingRecordId = relatedTradingRecordId;
    }
    
    /**
     * @return 返回 sourceType
     */
    public RequestSourceTypeEnum getSourceType() {
        return sourceType;
    }
    
    /**
     * @param 对sourceType进行赋值
     */
    public void setSourceType(RequestSourceTypeEnum sourceType) {
        this.sourceType = sourceType;
    }
    
    /**
     * @return 返回 category
     */
    public LATradingCategoryEnum getCategory() {
        return category;
    }
    
    /**
     * @param 对category进行赋值
     */
    public void setCategory(LATradingCategoryEnum category) {
        this.category = category;
    }
    
    /**
     * @return 返回 type
     */
    public LATradingRecordTypeEnum getType() {
        return type;
    }
    
    /**
     * @param 对type进行赋值
     */
    public void setType(LATradingRecordTypeEnum type) {
        this.type = type;
    }
    
    /**
     * @return 返回 sum
     */
    public BigDecimal getSum() {
        return sum;
    }
    
    /**
     * @param 对sum进行赋值
     */
    public void setSum(BigDecimal sum) {
        this.sum = sum;
    }
    
    /**
     * @return 返回 repaySum
     */
    public BigDecimal getRepaySum() {
        return repaySum;
    }
    
    /**
     * @param 对repaySum进行赋值
     */
    public void setRepaySum(BigDecimal repaySum) {
        this.repaySum = repaySum;
    }
    
    /**
     * @return 返回 summary
     */
    public String getSummary() {
        return summary;
    }
    
    /**
     * @param 对summary进行赋值
     */
    public void setSummary(String summary) {
        this.summary = summary;
    }
    
    /**
     * @return 返回 viewAble
     */
    public boolean isViewAble() {
        return viewAble;
    }
    
    /**
     * @param 对viewAble进行赋值
     */
    public void setViewAble(boolean viewAble) {
        this.viewAble = viewAble;
    }
    
    /**
     * @return 返回 revokeAble
     */
    public boolean isRevokeAble() {
        return revokeAble;
    }
    
    /**
     * @param 对revokeAble进行赋值
     */
    public void setRevokeAble(boolean revokeAble) {
        this.revokeAble = revokeAble;
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
     * @return 返回 revokeResean
     */
    public String getRevokeResean() {
        return revokeResean;
    }
    
    /**
     * @param 对revokeResean进行赋值
     */
    public void setRevokeResean(String revokeResean) {
        this.revokeResean = revokeResean;
    }
    
    /**
     * @return 返回 revokeOperatorId
     */
    public String getRevokeOperatorId() {
        return revokeOperatorId;
    }
    
    /**
     * @param 对revokeOperatorId进行赋值
     */
    public void setRevokeOperatorId(String revokeOperatorId) {
        this.revokeOperatorId = revokeOperatorId;
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
     * @return 返回 adjust
     */
    public boolean isAdjust() {
        return adjust;
    }
    
    /**
     * @param 对adjust进行赋值
     */
    public void setAdjust(boolean adjust) {
        this.adjust = adjust;
    }
    
    /**
     * @return 返回 received
     */
    public boolean isReceived() {
        return received;
    }
    
    /**
     * @param 对received进行赋值
     */
    public void setReceived(boolean received) {
        this.received = received;
    }
    
    /**
     * @return 返回 receiveDate
     */
    public Date getReceiveDate() {
        return receiveDate;
    }
    
    /**
     * @param 对receiveDate进行赋值
     */
    public void setReceiveDate(Date receiveDate) {
        this.receiveDate = receiveDate;
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
     * @return 返回 outsourceRepay
     */
    public boolean isOutsourceRepay() {
        return outsourceRepay;
    }
    
    /**
     * @param 对outsourceRepay进行赋值
     */
    public void setOutsourceRepay(boolean outsourceRepay) {
        this.outsourceRepay = outsourceRepay;
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
     * @return 返回 expireDate
     */
    public Date getExpireDate() {
        return expireDate;
    }
    
    /**
     * @param 对expireDate进行赋值
     */
    public void setExpireDate(Date expireDate) {
        this.expireDate = expireDate;
    }
    
    /**
     * @return 返回 repayDate
     */
    public Date getRepayDate() {
        return repayDate;
    }
    
    /**
     * @param 对repayDate进行赋值
     */
    public void setRepayDate(Date repayDate) {
        this.repayDate = repayDate;
    }
    
    /**
     * @return 返回 bankAccountId
     */
    public String getBankAccountId() {
        return bankAccountId;
    }
    
    /**
     * @param 对bankAccountId进行赋值
     */
    public void setBankAccountId(String bankAccountId) {
        this.bankAccountId = bankAccountId;
    }
    
    /**
     * @return 返回 repayDatePeriod
     */
    public String getRepayDatePeriod() {
        return repayDatePeriod;
    }
    
    /**
     * @param 对repayDatePeriod进行赋值
     */
    public void setRepayDatePeriod(String repayDatePeriod) {
        this.repayDatePeriod = repayDatePeriod;
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
     * @return 返回 beforeAccountStatus
     */
    public AccountStatusEnum getBeforeAccountStatus() {
        return beforeAccountStatus;
    }
    
    /**
     * @param 对beforeAccountStatus进行赋值
     */
    public void setBeforeAccountStatus(AccountStatusEnum beforeAccountStatus) {
        this.beforeAccountStatus = beforeAccountStatus;
    }
    
    /**
     * @return 返回 beforeCollectionStatus
     */
    public CollectionStatusEnum getBeforeCollectionStatus() {
        return beforeCollectionStatus;
    }
    
    /**
     * @param 对beforeCollectionStatus进行赋值
     */
    public void setBeforeCollectionStatus(
            CollectionStatusEnum beforeCollectionStatus) {
        this.beforeCollectionStatus = beforeCollectionStatus;
    }
    
    /**
     * @return 返回 beforeSettleInterestStatus
     */
    public SettleInterestStatusEnum getBeforeSettleInterestStatus() {
        return beforeSettleInterestStatus;
    }
    
    /**
     * @param 对beforeSettleInterestStatus进行赋值
     */
    public void setBeforeSettleInterestStatus(
            SettleInterestStatusEnum beforeSettleInterestStatus) {
        this.beforeSettleInterestStatus = beforeSettleInterestStatus;
    }
    
    /**
     * @return 返回 beforeIsDie
     */
    public boolean isBeforeIsDie() {
        return beforeIsDie;
    }
    
    /**
     * @param 对beforeIsDie进行赋值
     */
    public void setBeforeIsDie(boolean beforeIsDie) {
        this.beforeIsDie = beforeIsDie;
    }
    
    /**
     * @return 返回 beforeIsLegalProcedure
     */
    public boolean isBeforeIsLegalProcedure() {
        return beforeIsLegalProcedure;
    }
    
    /**
     * @param 对beforeIsLegalProcedure进行赋值
     */
    public void setBeforeIsLegalProcedure(boolean beforeIsLegalProcedure) {
        this.beforeIsLegalProcedure = beforeIsLegalProcedure;
    }
    
    /**
     * @return 返回 beforeIsClose
     */
    public boolean isBeforeIsClose() {
        return beforeIsClose;
    }
    
    /**
     * @param 对beforeIsClose进行赋值
     */
    public void setBeforeIsClose(boolean beforeIsClose) {
        this.beforeIsClose = beforeIsClose;
    }
    
    /**
     * @return 返回 beforePrincipalBalance
     */
    public BigDecimal getBeforePrincipalBalance() {
        return beforePrincipalBalance;
    }
    
    /**
     * @param 对beforePrincipalBalance进行赋值
     */
    public void setBeforePrincipalBalance(BigDecimal beforePrincipalBalance) {
        this.beforePrincipalBalance = beforePrincipalBalance;
    }
    
    /**
     * @return 返回 beforePrincipalBalanceIrr
     */
    public BigDecimal getBeforePrincipalBalanceIrr() {
        return beforePrincipalBalanceIrr;
    }
    
    /**
     * @param 对beforePrincipalBalanceIrr进行赋值
     */
    public void setBeforePrincipalBalanceIrr(
            BigDecimal beforePrincipalBalanceIrr) {
        this.beforePrincipalBalanceIrr = beforePrincipalBalanceIrr;
    }
    
    /**
     * @return 返回 beforeIsOverdue
     */
    public boolean isBeforeIsOverdue() {
        return beforeIsOverdue;
    }
    
    /**
     * @param 对beforeIsOverdue进行赋值
     */
    public void setBeforeIsOverdue(boolean beforeIsOverdue) {
        this.beforeIsOverdue = beforeIsOverdue;
    }
    
    /**
     * @return 返回 beforeOverdueDate
     */
    public Date getBeforeOverdueDate() {
        return beforeOverdueDate;
    }
    
    /**
     * @param 对beforeOverdueDate进行赋值
     */
    public void setBeforeOverdueDate(Date beforeOverdueDate) {
        this.beforeOverdueDate = beforeOverdueDate;
    }
    
    /**
     * @return 返回 beforeOverdueAmount
     */
    public BigDecimal getBeforeOverdueAmount() {
        return beforeOverdueAmount;
    }
    
    /**
     * @param 对beforeOverdueAmount进行赋值
     */
    public void setBeforeOverdueAmount(BigDecimal beforeOverdueAmount) {
        this.beforeOverdueAmount = beforeOverdueAmount;
    }
    
    /**
     * @return 返回 beforeOverdueSum
     */
    public BigDecimal getBeforeOverdueSum() {
        return beforeOverdueSum;
    }
    
    /**
     * @param 对beforeOverdueSum进行赋值
     */
    public void setBeforeOverdueSum(BigDecimal beforeOverdueSum) {
        this.beforeOverdueSum = beforeOverdueSum;
    }
    
    /**
     * @return 返回 afterAccountStatus
     */
    public AccountStatusEnum getAfterAccountStatus() {
        return afterAccountStatus;
    }
    
    /**
     * @param 对afterAccountStatus进行赋值
     */
    public void setAfterAccountStatus(AccountStatusEnum afterAccountStatus) {
        this.afterAccountStatus = afterAccountStatus;
    }
    
    /**
     * @return 返回 afterCollectionStatus
     */
    public CollectionStatusEnum getAfterCollectionStatus() {
        return afterCollectionStatus;
    }
    
    /**
     * @param 对afterCollectionStatus进行赋值
     */
    public void setAfterCollectionStatus(
            CollectionStatusEnum afterCollectionStatus) {
        this.afterCollectionStatus = afterCollectionStatus;
    }
    
    /**
     * @return 返回 afterSettleInterestStatus
     */
    public SettleInterestStatusEnum getAfterSettleInterestStatus() {
        return afterSettleInterestStatus;
    }
    
    /**
     * @param 对afterSettleInterestStatus进行赋值
     */
    public void setAfterSettleInterestStatus(
            SettleInterestStatusEnum afterSettleInterestStatus) {
        this.afterSettleInterestStatus = afterSettleInterestStatus;
    }
    
    /**
     * @return 返回 afterIsDie
     */
    public boolean isAfterIsDie() {
        return afterIsDie;
    }
    
    /**
     * @param 对afterIsDie进行赋值
     */
    public void setAfterIsDie(boolean afterIsDie) {
        this.afterIsDie = afterIsDie;
    }
    
    /**
     * @return 返回 afterIsLegalProcedure
     */
    public boolean isAfterIsLegalProcedure() {
        return afterIsLegalProcedure;
    }
    
    /**
     * @param 对afterIsLegalProcedure进行赋值
     */
    public void setAfterIsLegalProcedure(boolean afterIsLegalProcedure) {
        this.afterIsLegalProcedure = afterIsLegalProcedure;
    }
    
    /**
     * @return 返回 afterIsClose
     */
    public boolean isAfterIsClose() {
        return afterIsClose;
    }
    
    /**
     * @param 对afterIsClose进行赋值
     */
    public void setAfterIsClose(boolean afterIsClose) {
        this.afterIsClose = afterIsClose;
    }
    
    /**
     * @return 返回 afterPrincipalBalance
     */
    public BigDecimal getAfterPrincipalBalance() {
        return afterPrincipalBalance;
    }
    
    /**
     * @param 对afterPrincipalBalance进行赋值
     */
    public void setAfterPrincipalBalance(BigDecimal afterPrincipalBalance) {
        this.afterPrincipalBalance = afterPrincipalBalance;
    }
    
    /**
     * @return 返回 afterPrincipalBalanceIrr
     */
    public BigDecimal getAfterPrincipalBalanceIrr() {
        return afterPrincipalBalanceIrr;
    }
    
    /**
     * @param 对afterPrincipalBalanceIrr进行赋值
     */
    public void setAfterPrincipalBalanceIrr(
            BigDecimal afterPrincipalBalanceIrr) {
        this.afterPrincipalBalanceIrr = afterPrincipalBalanceIrr;
    }
    
    /**
     * @return 返回 afterIsOverdue
     */
    public boolean isAfterIsOverdue() {
        return afterIsOverdue;
    }
    
    /**
     * @param 对afterIsOverdue进行赋值
     */
    public void setAfterIsOverdue(boolean afterIsOverdue) {
        this.afterIsOverdue = afterIsOverdue;
    }
    
    /**
     * @return 返回 afterOverdueDate
     */
    public Date getAfterOverdueDate() {
        return afterOverdueDate;
    }
    
    /**
     * @param 对afterOverdueDate进行赋值
     */
    public void setAfterOverdueDate(Date afterOverdueDate) {
        this.afterOverdueDate = afterOverdueDate;
    }
    
    /**
     * @return 返回 afterOverdueAmount
     */
    public BigDecimal getAfterOverdueAmount() {
        return afterOverdueAmount;
    }
    
    /**
     * @param 对afterOverdueAmount进行赋值
     */
    public void setAfterOverdueAmount(BigDecimal afterOverdueAmount) {
        this.afterOverdueAmount = afterOverdueAmount;
    }
    
    /**
     * @return 返回 afterOverdueSum
     */
    public BigDecimal getAfterOverdueSum() {
        return afterOverdueSum;
    }
    
    /**
     * @param 对afterOverdueSum进行赋值
     */
    public void setAfterOverdueSum(BigDecimal afterOverdueSum) {
        this.afterOverdueSum = afterOverdueSum;
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
     * @return 返回 operatorId
     */
    public String getOperatorId() {
        return operatorId;
    }
    
    /**
     * @param 对operatorId进行赋值
     */
    public void setOperatorId(String operatorId) {
        this.operatorId = operatorId;
    }
    
    /**
     * @return 返回 lastUpdateOperatorId
     */
    public String getLastUpdateOperatorId() {
        return lastUpdateOperatorId;
    }
    
    /**
     * @param 对lastUpdateOperatorId进行赋值
     */
    public void setLastUpdateOperatorId(String lastUpdateOperatorId) {
        this.lastUpdateOperatorId = lastUpdateOperatorId;
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
     * @return 返回 attributes
     */
    public String getAttributes() {
        return attributes;
    }
    
    /**
     * @param 对attributes进行赋值
     */
    public void setAttributes(String attributes) {
        this.attributes = attributes;
    }
    
    /**
     * @return 返回 remark
     */
    public String getRemark() {
        return remark;
    }
    
    /**
     * @param 对remark进行赋值
     */
    public void setRemark(String remark) {
        this.remark = remark;
    }
    
    /**
     * @return 返回 tradingRecordEntryList
     */
    public List<LATradingRecordEntry> getTradingRecordEntryList() {
        return tradingRecordEntryList;
    }
    
    /**
     * @param 对tradingRecordEntryList进行赋值
     */
    public void setTradingRecordEntryList(
            List<LATradingRecordEntry> tradingRecordEntryList) {
        this.tradingRecordEntryList = tradingRecordEntryList;
    }
    
    /**
     * @return 返回 chargeRecordList
     */
    public List<ChargeRecord> getChargeRecordList() {
        return chargeRecordList;
    }
    
    /**
     * @param 对chargeRecordList进行赋值
     */
    public void setChargeRecordList(List<ChargeRecord> chargeRecordList) {
        this.chargeRecordList = chargeRecordList;
        
        this.chargeRecordMap.clear();
        this.chargeRecordMap.addAll(chargeRecordList);
    }
    
    /**
     * @return 返回 chargeRecordEntryList
     */
    public List<ChargeRecordEntry> getChargeRecordEntryList() {
        return chargeRecordEntryList;
    }
    
    /**
     * @param 对chargeRecordEntryList进行赋值
     */
    public void setChargeRecordEntryList(
            List<ChargeRecordEntry> chargeRecordEntryList) {
        this.chargeRecordEntryList = chargeRecordEntryList;
        
        this.chargeRecordEntryMap.clear();
        this.chargeRecordEntryMap.addAll(chargeRecordEntryList);
    }
    
    /**
     * @return 返回 paymentRecordList
     */
    public List<PaymentRecord> getPaymentRecordList() {
        return paymentRecordList;
    }
    
    /**
     * @param 对paymentRecordList进行赋值
     */
    public void setPaymentRecordList(List<PaymentRecord> paymentRecordList) {
        if (paymentRecordList != null) {
            Collections.sort(paymentRecordList,
                    PaymentRecordHelper.PERIOD_COMPARATOR);
        }
        this.paymentRecordList = paymentRecordList;
        
        this.paymentRecordMap.clear();
        this.paymentRecordMap.addAll(paymentRecordList);
    }
    
    /**
     * @return 返回 paymentRecordEntryList
     */
    public List<PaymentRecordEntry> getPaymentRecordEntryList() {
        return paymentRecordEntryList;
    }
    
    /**
     * @param 对paymentRecordEntryList进行赋值
     */
    public void setPaymentRecordEntryList(
            List<PaymentRecordEntry> paymentRecordEntryList) {
        this.paymentRecordEntryList = paymentRecordEntryList;
        
        this.paymentRecordEntryMap.clear();
        this.paymentRecordEntryMap.addAll(paymentRecordEntryList);
    }
    
    /**
     * @return 返回 exemptRecordList
     */
    public List<ExemptRecord> getExemptRecordList() {
        return exemptRecordList;
    }
    
    /**
     * @param 对exemptRecordList进行赋值
     */
    public void setExemptRecordList(List<ExemptRecord> exemptRecordList) {
        this.exemptRecordList = exemptRecordList;
        
        this.exemptRecordMap.clear();
        this.exemptRecordMap.addAll(exemptRecordList);
    }
    
    /**
     * @return 返回 exemptRecordEntryList
     */
    public List<ExemptRecordEntry> getExemptRecordEntryList() {
        return exemptRecordEntryList;
    }
    
    /**
     * @param 对exemptRecordEntryList进行赋值
     */
    public void setExemptRecordEntryList(
            List<ExemptRecordEntry> exemptRecordEntryList) {
        this.exemptRecordEntryList = exemptRecordEntryList;
        
        this.exemptRecordEntryMap.clear();
        this.exemptRecordEntryMap.addAll(exemptRecordEntryList);
    }
    
    /**
     * @return 返回 chargeRecordMap
     */
    public ScheduleAssociateMap<ChargeRecord> getChargeRecordMap() {
        return chargeRecordMap;
    }
    
    /**
     * @return 返回 exemptRecordMap
     */
    public ScheduleAssociateMap<ExemptRecord> getExemptRecordMap() {
        return exemptRecordMap;
    }
    
    /**
     * @return 返回 paymentRecordMap
     */
    public ScheduleAssociateMap<PaymentRecord> getPaymentRecordMap() {
        return paymentRecordMap;
    }
    
    /**
     * @return 返回 chargeRecordEntryMap
     */
    public ScheduleEntryAssociateMap<ChargeRecordEntry> getChargeRecordEntryMap() {
        return chargeRecordEntryMap;
    }
    
    /**
     * @return 返回 exemptRecordEntryMap
     */
    public ScheduleEntryAssociateMap<ExemptRecordEntry> getExemptRecordEntryMap() {
        return exemptRecordEntryMap;
    }
    
    /**
     * @return 返回 paymentRecordEntryMap
     */
    public ScheduleEntryAssociateMap<PaymentRecordEntry> getPaymentRecordEntryMap() {
        return paymentRecordEntryMap;
    }
    
    /**
      * 获取各费用项实收金额映射<br/>
      * <功能详细描述>
      * @return [参数说明]
      * 
      * @return Map<FeeItemEnum,BigDecimal> [返回类型说明]
      * @exception throws [异常类型] [异常说明]
      * @see [类、类#方法、类#成员]
     */
    public Map<FeeItemEnum, BigDecimal> getFeeItem2PaymentAmountMap() {
        Map<FeeItemEnum, BigDecimal> resMap = new HashMap<>();
        if (CollectionUtils.isEmpty(this.paymentRecordEntryList)) {
            return resMap;
        }
        
        for (PaymentRecordEntry preTemp : this.paymentRecordEntryList) {
            if (!resMap.containsKey(preTemp.getFeeItem())) {
                resMap.put(preTemp.getFeeItem(), BigDecimal.ZERO);
            }
            resMap.put(preTemp.getFeeItem(),
                    resMap.get(preTemp.getFeeItem()).add(preTemp.getAmount()));
        }
        return resMap;
    }
    
    /**
     * 获取各费用项实收金额映射<br/>
     * <功能详细描述>
     * @return [参数说明]
     * 
     * @return Map<FeeItemEnum,BigDecimal> [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
    */
    public Map<FeeItemEnum, BigDecimal> getFeeItem2ChargeAmountMap() {
        Map<FeeItemEnum, BigDecimal> resMap = new HashMap<>();
        if (CollectionUtils.isEmpty(this.chargeRecordEntryList)) {
            return resMap;
        }
        
        for (ChargeRecordEntry creTemp : this.chargeRecordEntryList) {
            if (!resMap.containsKey(creTemp.getFeeItem())) {
                resMap.put(creTemp.getFeeItem(), BigDecimal.ZERO);
            }
            resMap.put(creTemp.getFeeItem(),
                    resMap.get(creTemp.getFeeItem()).add(creTemp.getAmount()));
        }
        return resMap;
    }
    
    /**
    * 获取各费用项实收金额映射<br/>
    * <功能详细描述>
    * @return [参数说明]
    * 
    * @return Map<FeeItemEnum,BigDecimal> [返回类型说明]
    * @exception throws [异常类型] [异常说明]
    * @see [类、类#方法、类#成员]
    */
    public Map<FeeItemEnum, BigDecimal> getFeeItem2ExemptAmountMap() {
        Map<FeeItemEnum, BigDecimal> resMap = new HashMap<>();
        if (CollectionUtils.isEmpty(this.exemptRecordEntryList)) {
            return resMap;
        }
        
        for (ExemptRecordEntry ereTemp : this.exemptRecordEntryList) {
            if (!resMap.containsKey(ereTemp.getFeeItem())) {
                resMap.put(ereTemp.getFeeItem(), BigDecimal.ZERO);
            }
            resMap.put(ereTemp.getFeeItem(),
                    resMap.get(ereTemp.getFeeItem()).add(ereTemp.getAmount()));
        }
        return resMap;
    }
    
    /**
     * 贷款账户的hashCode值<br/>
     * @return
     */
    @Override
    public int hashCode() {
        return ObjectUtils.generateHashCode(super.hashCode(), this, "id");
    }
    
    /**
     * 判断两个贷款账户是否是同一个<br/>
     * @param obj
     * @return
     */
    @Override
    public boolean equals(Object obj) {
        return ObjectUtils.equals(this, obj, "id");
    }
    
}
