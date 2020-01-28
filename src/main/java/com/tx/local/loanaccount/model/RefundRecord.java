///*
// * 描          述:  <描述>
// * 修  改   人:  lenovo
// * 修改时间:  2014年7月22日
// * <修改描述:>
// */
//package com.tx.local.loanaccount.model;
//
//import java.io.Serializable;
//import java.math.BigDecimal;
//import java.util.Date;
//
//import javax.persistence.Entity;
//import javax.persistence.Id;
//import javax.persistence.Table;
//
//import com.tx.core.jdbc.sqlsource.annotation.UpdateAble;
//
///**
// * 退款记录<br/>
// * <功能详细描述>
// * 
// * @author  lenovo
// * @version  [版本号, 2014年7月22日]
// * @see  [相关类/方法]
// * @since  [产品/模块版本]
// */
//@Entity
//@Table(name = "la_refund_record")
//public class RefundRecord implements Serializable {
//    
//    /** 注释内容 */
//    private static final long serialVersionUID = -6031308364401303434L;
//    
//    /** 归还款项主键 */
//    @Id
//    private String id;
//    
//    /** 关联贷款单账户ID */
//    private String loanAccountId;
//    
//    /** 退款银行账户 */
//    private LABankAccount bankAccount;
//    
//    /** 客户id */
//    private String clientId;
//    
//    /** 客户账户id */
//    private String clientAccountId;
//    
//    /** 客户姓名 */
//    private String clientName;
//    
//    /** 退款金额 */
//    private BigDecimal amount;
//    
//    /** 归还状态状态  */
//    private RefundStatusEnum status;
//    
//    /** 关联交易ID */
//    private String tradingRecordId;
//    
//    /** 身份证号 */
//    private String clientIDCardNumber;
//    
//    
//    
//    
//    
//    /** 最后一次操作人 */
//    private String lastUpdateOperatorId;
//    
//    /** 创建人ID */
//    private String createOperatorId;
//    
//    /** 最后修改日期 */
//    @UpdateAble
//    private Date lastUpdateDate;
//    
//    /** 创建时间 */
//    private Date createDate;
//    
//    /** <默认构造函数> */
//    public RefundRecord() {
//        super();
//    }
//    
//    /**
//     * @return 返回 amount
//     */
//    public BigDecimal getAmount() {
//        return amount;
//    }
//    
//    /**
//     * @return 返回 bankAccountId
//     */
//    public String getBankAccountId() {
//        return bankAccountId;
//    }
//    
//    /**
//     * @return 返回 clientIDCardNumber
//     */
//    public String getClientIDCardNumber() {
//        return clientIDCardNumber;
//    }
//
//	/**
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
//     * @return 返回 createOperatorName
//     */
//    public String getCreateOperatorName() {
//        return createOperatorName;
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
//     * @return 返回 lastUpdateDate
//     */
//    public Date getLastUpdateDate() {
//        return lastUpdateDate;
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
//     * @return 返回 refundStatus
//     */
//    public RefundStatusEnum getRefundStatus() {
//        return refundStatus;
//    }
//    
//    /**
//     * @return 返回 repayIntention
//     */
//    public String getRepayIntention() {
//        return repayIntention;
//    }
//    
//    /**
//     * @return 返回 sourceBankAccountId
//     */
//    public String getSourceBankAccountId() {
//        return sourceBankAccountId;
//    }
//    
//    /**
//     * @return 返回 tradingRecordId
//     */
//    public String getTradingRecordId() {
//        return tradingRecordId;
//    }
//    
//    /**
//     * @return 返回 vcid
//     */
//    public String getVcid() {
//        return vcid;
//    }
//    
//    /**
//     * @param 对amount进行赋值
//     */
//    public void setAmount(BigDecimal amount) {
//        this.amount = amount;
//    }
//    
//    /**
//     * @param 对bankAccountId进行赋值
//     */
//    public void setBankAccountId(String bankAccountId) {
//        this.bankAccountId = bankAccountId;
//    }
//    
//    /**
//     * @param 对clientIDCardNumber进行赋值
//     */
//    public void setClientIDCardNumber(String clientIDCardNumber) {
//        this.clientIDCardNumber = clientIDCardNumber;
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
//     * @param 对createOperatorName进行赋值
//     */
//    public void setCreateOperatorName(String createOperatorName) {
//        this.createOperatorName = createOperatorName;
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
//     * @param 对lastUpdateDate进行赋值
//     */
//    public void setLastUpdateDate(Date lastUpdateDate) {
//        this.lastUpdateDate = lastUpdateDate;
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
//     * @param 对refundStatus进行赋值
//     */
//    public void setRefundStatus(RefundStatusEnum refundStatus) {
//        this.refundStatus = refundStatus;
//    }
//
//    /**
//     * @param 对repayIntention进行赋值
//     */
//    public void setRepayIntention(String repayIntention) {
//        this.repayIntention = repayIntention;
//    }
//
//    /**
//     * @param 对sourceBankAccountId进行赋值
//     */
//    public void setSourceBankAccountId(String sourceBankAccountId) {
//        this.sourceBankAccountId = sourceBankAccountId;
//    }
//
//    /**
//     * @param 对tradingRecordId进行赋值
//     */
//    public void setTradingRecordId(String tradingRecordId) {
//        this.tradingRecordId = tradingRecordId;
//    }
//
//    /**
//     * @param 对vcid进行赋值
//     */
//    public void setVcid(String vcid) {
//        this.vcid = vcid;
//    }
//    
//}
