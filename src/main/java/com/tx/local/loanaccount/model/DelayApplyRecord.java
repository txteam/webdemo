///*
// * 描述:  <描述>
// * 修改人:  Rain
// * 修改时间:  2016年01月11日
// * <修改描述:>
// */
//package com.tx.local.loanaccount.model;
//
//import java.math.BigDecimal;
//import java.util.Date;
//import java.util.List;
//
//import javax.persistence.Entity;
//import javax.persistence.Id;
//import javax.persistence.Table;
//import javax.persistence.Transient;
//
//import org.springframework.format.annotation.DateTimeFormat;
//import com.tx.component.operator.model.Operator;
//import com.tx.core.jdbc.sqlsource.annotation.UpdateAble;
//
///**
// * 展期申请记录
// * 
// * @author Rain
// * @version [版本号, 2016年01月11日]
// * @see [相关类/方法]
// * @since [产品/模块版本]
// */
//@Entity
//@Table(name = "la_delay_apply")
//public class DelayApplyRecord {
//    
//    /** 展期申请记录id */
//    @Id
//    private String id;
//    
//    /** 交易记录id */
//    private String tradingRecordId;
//    
//    /** 客户id */
//    private String clientId;
//    
//    /** 客户名 */
//    private String clientName;
//    
//    /** 客户账户id */
//    private String clientAccountId;
//    
//    /** 贷款账户id */
//    private String loanAccountId;
//    
//    /** 当前期数 */
//    private String currentPeriod;
//    
//    /** 原还款日 */
//    @DateTimeFormat(pattern = "yyyy-MM-dd")
//    private Date sourceRepayDate;
//    
//    /** 展期后还款日期 */
//    @DateTimeFormat(pattern = "yyyy-MM-dd")
//    private Date targetRepayDate;
//    
//    /** 延期原因 */
//    private String delayReason;
//    
//    
//    
//    /** 创建日期 */
//    private Date createDate;
//    
//    /** 申请人id */
//    private String applyOperatorId;
//    
//    /** 申请日 */
//    private Date applyDate;
//    
//    /** 审批人id */
//    @UpdateAble
//    private String approveOperatorId;
//    
//    /** 审批日 */
//    private Date approveDate;
//    
//    /** 备注 */
//    private String remark;
//    
//    /** 虚中心id */
//    private String vcid;;
//    
//    
//    
//    
//    
//    /** 流程实例id:保留状态 */
//    private String processInstanceId;
//    
//    /** 展期申请状态 */
//    private String delayApplyStatus;
//    
//    
//    
//    /** 还款金额信息xml表达式 */
//    private String repayAmountInfoXml;
//    
//    /** 银行账户id */
//    private String bankAccountId;
//    
//    /** 还款日 */
//    @DateTimeFormat(pattern = "yyyy-MM-dd")
//    private Date RepayDate;
//    
//    /** 展期原因 */
//    @Transient
//    private String delayReasonName;
//    
//    /** 对应的贷款账户 */
//    @Transient
//    private LoanAccount loanAccount;
//    
//    /** 对应的银行账户 */
//    @Transient
//    private BankAccount bankAccount;
//    
//    /** 申请人 */
//    @Transient
//    private Operator applyOperator;
//    
//    /** 还款金额信息 */
//    @Transient
//    private List<RepayAmountInfo> repayAmountInfos;
//    
//    /** 申请日 */
//    public Date getApplyDate() {
//        return applyDate;
//    }
//    
//    /** 申请人 */
//    public Operator getApplyOperator() {
//        return applyOperator;
//    }
//    
//    /** 申请人id */
//    public String getApplyOperatorId() {
//        return applyOperatorId;
//    }
//    
//    /** 审批日 */
//    public Date getApproveDate() {
//        return approveDate;
//    }
//    
//    /** 审批人id */
//    public String getApproveOperatorId() {
//        return approveOperatorId;
//    }
//    
//    /** 对应的银行账户 */
//    public BankAccount getBankAccount() {
//        return bankAccount;
//    }
//    
//    /** 银行账户id */
//    public String getBankAccountId() {
//        return bankAccountId;
//    }
//    
//    /** 创建日期 */
//    public Date getCreateDate() {
//        return createDate;
//    }
//    
//    /** 当前期数 */
//    public String getCurrentPeriod() {
//        return currentPeriod;
//    }
//    
//    /** 展期申请状态 */
//    public String getDelayApplyStatus() {
//        return delayApplyStatus;
//    }
//    
//    /** 展期天数 */
//    public int getDelayDays() {
//        return delayDays;
//    }
//    
//    /** 展期金额 */
//    public BigDecimal getDelayFeeAmount() {
//        return delayFeeAmount;
//    }
//    
//    /** 延期原因 */
//    public String getDelayReason() {
//        return delayReason;
//    }
//    
//    /** 展期原因 */
//    public String getDelayReasonName() {
//        return delayReasonName;
//    }
//    
//    public String getId() {
//        return id;
//    }
//    
//    /** 对应的贷款账户 */
//    public LoanAccount getLoanAccount() {
//        return loanAccount;
//    }
//    
//    /** 贷款账户id */
//    public String getLoanAccountId() {
//        return loanAccountId;
//    }
//    
//    /** 流程实例id:保留状态 */
//    public String getProcessInstanceId() {
//        return processInstanceId;
//    }
//    
//    /** 备注 */
//    public String getRemark() {
//        return remark;
//    }
//    
//    /** 还款金额信息 */
//    public List<RepayAmountInfo> getRepayAmountInfos() {
//        return repayAmountInfos;
//    }
//    
//    /** 还款金额信息xml表达式 */
//    public String getRepayAmountInfoXml() {
//        return repayAmountInfoXml;
//    }
//    
//    /** 还款日 */
//    public Date getRepayDate() {
//        return RepayDate;
//    }
//    
//    /** 原还款日 */
//    public Date getSourceRepayDate() {
//        return sourceRepayDate;
//    }
//    
//    /** 展期后还款日期 */
//    public Date getTargetRepayDate() {
//        return targetRepayDate;
//    }
//    
//    /** 交易记录id */
//    public String getTradingRecordId() {
//        return tradingRecordId;
//    }
//    
//    /** 虚中心id */
//    public String getVcid() {
//        return vcid;
//    }
//    
//    /** 申请日 */
//    public void setApplyDate(Date applyDate) {
//        this.applyDate = applyDate;
//    }
//    
//    /** 申请人 */
//    public void setApplyOperator(Operator applyOperator) {
//        this.applyOperator = applyOperator;
//    }
//    
//    /** 申请人id */
//    public void setApplyOperatorId(String applyOperatorId) {
//        this.applyOperatorId = applyOperatorId;
//    }
//    
//    /** 审批日 */
//    public void setApproveDate(Date approveDate) {
//        this.approveDate = approveDate;
//    }
//    
//    /** 审批人id */
//    public void setApproveOperatorId(String approveOperatorId) {
//        this.approveOperatorId = approveOperatorId;
//    }
//    
//    /** 对应的银行账户 */
//    public void setBankAccount(BankAccount bankAccount) {
//        this.bankAccount = bankAccount;
//    }
//    
//    /** 银行账户id */
//    public void setBankAccountId(String bankAccountId) {
//        this.bankAccountId = bankAccountId;
//    }
//    
//    /** 创建日期 */
//    public void setCreateDate(Date createDate) {
//        this.createDate = createDate;
//    }
//    
//    /** 当前期数 */
//    public void setCurrentPeriod(String currentPeriod) {
//        this.currentPeriod = currentPeriod;
//    }
//    
//    /** 展期申请状态 */
//    public void setDelayApplyStatus(String delayApplyStatus) {
//        this.delayApplyStatus = delayApplyStatus;
//    }
//    
//    /** 展期天数 */
//    public void setDelayDays(int delayDays) {
//        this.delayDays = delayDays;
//    }
//    
//    /** 展期金额 */
//    public void setDelayFeeAmount(BigDecimal delayFeeAmount) {
//        this.delayFeeAmount = delayFeeAmount;
//    }
//    
//    /** 延期原因 */
//    public void setDelayReason(String delayReason) {
//        this.delayReason = delayReason;
//    }
//    
//    /** 展期原因 */
//    public void setDelayReasonName(String delayReasonName) {
//        this.delayReasonName = delayReasonName;
//    }
//    
//    public void setId(String id) {
//        this.id = id;
//    }
//    
//    /** 对应的贷款账户 */
//    public void setLoanAccount(LoanAccount loanAccount) {
//        this.loanAccount = loanAccount;
//    }
//    
//    /** 贷款账户id */
//    public void setLoanAccountId(String loanAccountId) {
//        this.loanAccountId = loanAccountId;
//    }
//    
//    /** 流程实例id:保留状态 */
//    public void setProcessInstanceId(String processInstanceId) {
//        this.processInstanceId = processInstanceId;
//    }
//    
//    /** 备注 */
//    public void setRemark(String remark) {
//        this.remark = remark;
//    }
//    
//    /** 还款金额信息 */
//    public void setRepayAmountInfos(List<RepayAmountInfo> repayAmountInfos) {
//        this.repayAmountInfos = repayAmountInfos;
//    }
//    
//    /** 还款金额信息xml表达式 */
//    public void setRepayAmountInfoXml(String repayAmountInfoXml) {
//        this.repayAmountInfoXml = repayAmountInfoXml;
//    }
//    
//    /** 还款日 */
//    public void setRepayDate(Date repayDate) {
//        RepayDate = repayDate;
//    }
//    
//    /** 原还款日 */
//    public void setSourceRepayDate(Date sourceRepayDate) {
//        this.sourceRepayDate = sourceRepayDate;
//    }
//    
//    /** 展期后还款日期 */
//    public void setTargetRepayDate(Date targetRepayDate) {
//        this.targetRepayDate = targetRepayDate;
//    }
//    
//    /** 交易记录id */
//    public void setTradingRecordId(String tradingRecordId) {
//        this.tradingRecordId = tradingRecordId;
//    }
//    
//    /** 虚中心id */
//    public void setVcid(String vcid) {
//        this.vcid = vcid;
//    }
//}
