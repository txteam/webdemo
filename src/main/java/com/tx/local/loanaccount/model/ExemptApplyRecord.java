///*
// * 描          述:  <描述>
// * 修  改   人:  Administrator
// * 修改时间:  2014年6月7日
// * <修改描述:>
// */
//package com.tx.local.loanaccount.model;
//
//import java.io.Serializable;
//import java.math.BigDecimal;
//import java.util.Date;
//
//import javax.persistence.Table;
//
//import com.tx.component.basicdata.model.FeeItemEnum;
//import com.tx.core.jdbc.sqlsource.annotation.UpdateAble;
//import com.tx.core.util.ObjectUtils;
//
///**
// * 豁免设置<br/>
// * 
// * @author  Administrator
// * @version  [版本号, 2014年6月7日]
// * @see  [相关类/方法]
// * @since  [产品/模块版本]
// */
//@Table(name = "la_exempt_apply")
//public class ExemptApplyRecord implements Serializable {
//    
//    /** 注释内容 */
//    private static final long serialVersionUID = -6031308364401303434L;
//    
//    /** 主键 */
//    private String id;
//    
//    /** 创建人所在分公司 */
//    private String vcid;
//    
//    /** 创建人所在组织 */
//    private String organizationId;
//    
//    /** 创建人id */
//    private String operatorId;
//    
//    /** 创建人姓名 */
//    private String operatorName;
//    
//    /** 贷款单账户唯一键:  */
//    private String loanAccountId;
//    
//    /** 合同编号  非数据库映射字段 */
//    private String contractNumber;
//    
//    /** 客户姓名 非数据库映射字段 */
//    private String clientName;
//    
//    /** 豁免类型：还款豁免，提前结清豁免,续贷结清豁免 */
//    private ExemptSettingTypeEnum exemptType;
//    
//    /** 豁免申请状态：待审批，审批通过待入账，关联自动转账还款待入账，已入账 */
//    private ExemptSettingStatusEnum status;
//    
//    /** 豁免设置对应期数 */
//    private String period;
//    
//    /** 豁免设置对应费用项 */
//    @UpdateAble
//    private FeeItemEnum feeItem;
//    
//    /** 豁免费用项目及期数对应的还款记录分项 */
//    private String paymentScheduleEntryId;
//    
//    /** 豁免前金额 sourceAmount */
//    private BigDecimal sourceAmount;
//    
//    /** 申请金额 applyAmount */
//    private BigDecimal applyAmount;
//    
//    /** 审批同意金额：批核可豁免金额 */
//    private BigDecimal approveAgreeAmount;
//    
//    /** 豁免生效金额 */
//    private BigDecimal effictiveAmount;
//    
//    /** 豁免后金额 */
//    private BigDecimal targetAmount;
//    
//    /** 创建人ID */
//    private String createOperatorId;
//    
//    /** 首次使用人ID */
//    private String applyOperatorId;
//    
//    /** 最后使用人ID  */
//    private String lastUpdateOperatorId;
//    
//    /** 是否已入账 */
//    private boolean effective;
//    
//    /** 豁免设置关联引用类型 */
//    private ExemptSettingRelateRefTypeEnum relateRefType;
//    
//    /** 关联交易主键: 交易id,或自动转账设置 */
//    private String relateRefId;
//    
//    /** 创建时间 */
//    private Date createDate;
//    
//    /** 豁免 费用项目及期数对应还款日期 */
//    private Date repayDate;
//    
//    /** 关联生效时间 */
//    private Date processDate;
//    
//    /** 豁免设置的审批时间：（审批通过时间或审批驳回时间 ）*/
//    private Date approveDate;
//    
//    /** 豁免生效时间 */
//    private Date effectiveDate;
//    
//    @Override
//    public boolean equals(Object obj) {
//        return ObjectUtils.equals(this, obj, "id");
//    }
//    
//    /**
//     * @return 返回 applyAmount
//     */
//    public BigDecimal getApplyAmount() {
//        return applyAmount;
//    }
//    
//    /**
//     * @return 返回 applyOperatorId
//     */
//    public String getApplyOperatorId() {
//        return applyOperatorId;
//    }
//    
//    /**
//     * @return 返回 approveAgreeAmount
//     */
//    public BigDecimal getApproveAgreeAmount() {
//        return approveAgreeAmount;
//    }
//    
//    /**
//     * @return 返回 approveDate
//     */
//    public Date getApproveDate() {
//        return approveDate;
//    }
//    
//    /**
//     * @return 返回 clientName
//     */
//    public String getClientName() {
//        return clientName;
//    }
//    
//    /**
//     * @return 返回 contractNumber
//     */
//    public String getContractNumber() {
//        return contractNumber;
//    }
//    
//    /**
//     * @return 返回 createDate
//     */
//    public Date getCreateDate() {
//        return createDate;
//    }
//    
//    /**
//     * @return 返回 createOperatorId
//     */
//    public String getCreateOperatorId() {
//        return createOperatorId;
//    }
//    
//    /**
//     * @return 返回 effectiveDate
//     */
//    public Date getEffectiveDate() {
//        return effectiveDate;
//    }
//    
//    /**
//     * @return 返回 effictiveAmount
//     */
//    public BigDecimal getEffictiveAmount() {
//        return effictiveAmount;
//    }
//    
//    /**
//     * @return 返回 exemptType
//     */
//    public ExemptSettingTypeEnum getExemptType() {
//        return exemptType;
//    }
//    
//    /**
//     * @return 返回 feeItem
//     */
//    public FeeItemEnum getFeeItem() {
//        return feeItem;
//    }
//    
//    /**
//     * @return 返回 id
//     */
//    public String getId() {
//        return id;
//    }
//    
//    /**
//     * @return 返回 lastUpdateOperatorId
//     */
//    public String getLastUpdateOperatorId() {
//        return lastUpdateOperatorId;
//    }
//    
//    /**
//     * @return 返回 loanAccountId
//     */
//    public String getLoanAccountId() {
//        return loanAccountId;
//    }
//    
//    /**
//     * @return 返回 operatorId
//     */
//    public String getOperatorId() {
//        return operatorId;
//    }
//    
//    /**
//     * @return 返回 operatorName
//     */
//    public String getOperatorName() {
//        return operatorName;
//    }
//    
//    /**
//     * @return 返回 organizationId
//     */
//    public String getOrganizationId() {
//        return organizationId;
//    }
//    
//    /**
//     * @return 返回 paymentScheduleEntryId
//     */
//    public String getPaymentScheduleEntryId() {
//        return paymentScheduleEntryId;
//    }
//    
//   
//    /**
//     * @return 返回 period
//     */
//    public String getPeriod() {
//        return period;
//    }
//    
//    /**
//     * @return 返回 processDate
//     */
//    public Date getProcessDate() {
//        return processDate;
//    }
//    
//    /**
//     * @return 返回 relateRefId
//     */
//    public String getRelateRefId() {
//        return relateRefId;
//    }
//    
//    /**
//     * @return 返回 relateRefType
//     */
//    public ExemptSettingRelateRefTypeEnum getRelateRefType() {
//        return relateRefType;
//    }
//    
//    /**
//     * @return 返回 repayDate
//     */
//    public Date getRepayDate() {
//        return repayDate;
//    }
//
//    /**
//     * @return 返回 sourceAmount
//     */
//    public BigDecimal getSourceAmount() {
//        return sourceAmount;
//    }
//
//    /**
//     * @return 返回 status
//     */
//    public ExemptSettingStatusEnum getStatus() {
//        return status;
//    }
//
//    /**
//     * @return 返回 targetAmount
//     */
//    public BigDecimal getTargetAmount() {
//        return targetAmount;
//    }
//
//    /**
//     * @return 返回 vcid
//     */
//    public String getVcid() {
//        return vcid;
//    }
//    
//    @Override
//    public int hashCode() {
//        return ObjectUtils.generateHashCode(super.hashCode(), this, "id");
//    }
//    
//    /**
//     * @return 返回 effective
//     */
//    public boolean isEffective() {
//        return effective;
//    }
//
//    /**
//     * @param 对applyAmount进行赋值
//     */
//    public void setApplyAmount(BigDecimal applyAmount) {
//        this.applyAmount = applyAmount;
//    }
//    
//    /**
//     * @param 对applyOperatorId进行赋值
//     */
//    public void setApplyOperatorId(String applyOperatorId) {
//        this.applyOperatorId = applyOperatorId;
//    }
//
//    /**
//     * @param 对approveAgreeAmount进行赋值
//     */
//    public void setApproveAgreeAmount(BigDecimal approveAgreeAmount) {
//        this.approveAgreeAmount = approveAgreeAmount;
//    }
//
//    /**
//     * @param 对approveDate进行赋值
//     */
//    public void setApproveDate(Date approveDate) {
//        this.approveDate = approveDate;
//    }
//
//    /**
//     * @param 对clientName进行赋值
//     */
//    public void setClientName(String clientName) {
//        this.clientName = clientName;
//    }
//
//    /**
//     * @param 对contractNumber进行赋值
//     */
//    public void setContractNumber(String contractNumber) {
//        this.contractNumber = contractNumber;
//    }
//
//    /**
//     * @param 对createDate进行赋值
//     */
//    public void setCreateDate(Date createDate) {
//        this.createDate = createDate;
//    }
//    
//    /**
//     * @param 对createOperatorId进行赋值
//     */
//    public void setCreateOperatorId(String createOperatorId) {
//        this.createOperatorId = createOperatorId;
//    }
//    
//    /**
//     * @param 对effective进行赋值
//     */
//    public void setEffective(boolean effective) {
//        this.effective = effective;
//    }
//    
//    /**
//     * @param 对effectiveDate进行赋值
//     */
//    public void setEffectiveDate(Date effectiveDate) {
//        this.effectiveDate = effectiveDate;
//    }
//    
//    /**
//     * @param 对effictiveAmount进行赋值
//     */
//    public void setEffictiveAmount(BigDecimal effictiveAmount) {
//        this.effictiveAmount = effictiveAmount;
//    }
//    
//    /**
//     * @param 对exemptType进行赋值
//     */
//    public void setExemptType(ExemptSettingTypeEnum exemptType) {
//        this.exemptType = exemptType;
//    }
//    
//    /**
//     * @param 对feeItem进行赋值
//     */
//    public void setFeeItem(FeeItemEnum feeItem) {
//        this.feeItem = feeItem;
//    }
//    
//    /**
//     * @param 对id进行赋值
//     */
//    public void setId(String id) {
//        this.id = id;
//    }
//    
//    /**
//     * @param 对lastUpdateOperatorId进行赋值
//     */
//    public void setLastUpdateOperatorId(String lastUpdateOperatorId) {
//        this.lastUpdateOperatorId = lastUpdateOperatorId;
//    }
//
//    /**
//     * @param 对loanAccountId进行赋值
//     */
//    public void setLoanAccountId(String loanAccountId) {
//        this.loanAccountId = loanAccountId;
//    }
//
//    /**
//     * @param 对operatorId进行赋值
//     */
//    public void setOperatorId(String operatorId) {
//        this.operatorId = operatorId;
//    }
//
//    /**
//     * @param 对operatorName进行赋值
//     */
//    public void setOperatorName(String operatorName) {
//        this.operatorName = operatorName;
//    }
//
//    /**
//     * @param 对organizationId进行赋值
//     */
//    public void setOrganizationId(String organizationId) {
//        this.organizationId = organizationId;
//    }
//
//    /**
//     * @param 对paymentScheduleEntryId进行赋值
//     */
//    public void setPaymentScheduleEntryId(String paymentScheduleEntryId) {
//        this.paymentScheduleEntryId = paymentScheduleEntryId;
//    }
//
//    /**
//     * @param 对period进行赋值
//     */
//    public void setPeriod(String period) {
//        this.period = period;
//    }
//
//    /**
//     * @param 对processDate进行赋值
//     */
//    public void setProcessDate(Date processDate) {
//        this.processDate = processDate;
//    }
//
//    /**
//     * @param 对relateRefId进行赋值
//     */
//    public void setRelateRefId(String relateRefId) {
//        this.relateRefId = relateRefId;
//    }
//
//    /**
//     * @param 对relateRefType进行赋值
//     */
//    public void setRelateRefType(ExemptSettingRelateRefTypeEnum relateRefType) {
//        this.relateRefType = relateRefType;
//    }
//
//    /**
//     * @param 对repayDate进行赋值
//     */
//    public void setRepayDate(Date repayDate) {
//        this.repayDate = repayDate;
//    }
//
//    /**
//     * @param 对sourceAmount进行赋值
//     */
//    public void setSourceAmount(BigDecimal sourceAmount) {
//        this.sourceAmount = sourceAmount;
//    }
//
//    /**
//     * @param 对status进行赋值
//     */
//    public void setStatus(ExemptSettingStatusEnum status) {
//        this.status = status;
//    }
//
//    /**
//     * @param 对targetAmount进行赋值
//     */
//    public void setTargetAmount(BigDecimal targetAmount) {
//        this.targetAmount = targetAmount;
//    }
//
//    /**
//     * @param 对vcid进行赋值
//     */
//    public void setVcid(String vcid) {
//        this.vcid = vcid;
//    }
//}
