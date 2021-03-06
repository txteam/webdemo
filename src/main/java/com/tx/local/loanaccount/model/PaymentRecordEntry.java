/*
 * 描          述:  <描述>
 * 修  改   人:  Administrator
 * 修改时间:  2014年4月17日
 * <修改描述:>
 */
package com.tx.local.loanaccount.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.tx.local.basicdata.model.FeeItemEnum;

/**
 * 还款记录分项
 * 
 * @author  Administrator
 * @version  [版本号, 2014年4月17日]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
@JsonIgnoreProperties({ "buildTradingRecord", "tradingRecord", "paymentRecord",
        "paymentScheduleEntry" })
@Entity
@Table(name = "la_payment_record_entry")
public class PaymentRecordEntry
        implements Serializable, ScheduleEntryAssociate {
    
    /** 注释内容 */
    private static final long serialVersionUID = -6031308364401303434L;
    
    /** 主键 */
    private String id;
    
    /** 贷款账户id */
    private String loanAccountId;
    
    /** 期数:  */
    private String period;
    
    /** 还款类型 */
    private ScheduleTypeEnum scheduleType;
    
    /** 费用项 */
    private FeeItemEnum feeItem;
    
    /** 构建记录对应的交易记录：服务于调账，交易中一般来说buildTrading = trading. 如果记录被调账过，则会出现buildTraindg != trading的情形 */
    @JsonIgnore
    @ManyToOne
    @Column(name = "buildTradingRecordId")
    private LATradingRecord buildTradingRecord;
    
    /** 对应交易 */
    @JsonIgnore
    @ManyToOne
    @Column(name = "tradingRecordId")
    private LATradingRecord tradingRecord;
    
    /** 还款记录 */
    @ManyToOne
    @JoinColumn(name = "paymentRecordId")
    private PaymentRecord paymentRecord;
    
    /**　还款计划表主键：*/
    @JsonIgnore
    @ManyToOne
    @Column(name = "paymentScheduleEntryId")
    private PaymentScheduleEntry paymentScheduleEntry;
    
    /**　计费金额(增量值，本次计费的值)：*/
    private BigDecimal amount;
    
    /**　原计费金额: */
    private BigDecimal sourceAmount;
    
    /**　修改后的计费金额: */
    private BigDecimal targetAmount;
    
    /** 是否被撤销 */
    private boolean revoked;
    
    /** 撤销时间 */
    private Date revokeDate;
    
    /** 是否到帐 */
    private boolean received;
    
    /** 到账时间 */
    private Date receiveDate;
    
    /** 最后更新时间 */
    private Date lastUpdateDate;
    
    /** 创建时间 */
    private Date createDate;
    
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
     * @return 返回 period
     */
    public String getPeriod() {
        return period;
    }
    
    /**
     * @param 对period进行赋值
     */
    public void setPeriod(String period) {
        this.period = period;
    }
    
    /**
     * @return 返回 scheduleType
     */
    public ScheduleTypeEnum getScheduleType() {
        return scheduleType;
    }
    
    /**
     * @param 对scheduleType进行赋值
     */
    public void setScheduleType(ScheduleTypeEnum scheduleType) {
        this.scheduleType = scheduleType;
    }
    
    /**
     * @return 返回 feeItem
     */
    public FeeItemEnum getFeeItem() {
        return feeItem;
    }
    
    /**
     * @param 对feeItem进行赋值
     */
    public void setFeeItem(FeeItemEnum feeItem) {
        this.feeItem = feeItem;
    }
    
    /**
     * @return 返回 tradingRecord
     */
    public LATradingRecord getTradingRecord() {
        return tradingRecord;
    }
    
    /**
     * @param 对tradingRecord进行赋值
     */
    public void setTradingRecord(LATradingRecord tradingRecord) {
        this.tradingRecord = tradingRecord;
    }
    
    /**
     * @return 返回 paymentRecord
     */
    public PaymentRecord getPaymentRecord() {
        return paymentRecord;
    }
    
    /**
     * @param 对paymentRecord进行赋值
     */
    public void setPaymentRecord(PaymentRecord paymentRecord) {
        this.paymentRecord = paymentRecord;
    }
    
    /**
     * @return 返回 paymentScheduleEntry
     */
    public PaymentScheduleEntry getPaymentScheduleEntry() {
        return paymentScheduleEntry;
    }
    
    /**
     * @param 对paymentScheduleEntry进行赋值
     */
    public void setPaymentScheduleEntry(
            PaymentScheduleEntry paymentScheduleEntry) {
        this.paymentScheduleEntry = paymentScheduleEntry;
    }
    
    /**
     * @return 返回 amount
     */
    public BigDecimal getAmount() {
        return amount;
    }
    
    /**
     * @param 对amount进行赋值
     */
    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }
    
    /**
     * @return 返回 sourceAmount
     */
    public BigDecimal getSourceAmount() {
        return sourceAmount;
    }
    
    /**
     * @param 对sourceAmount进行赋值
     */
    public void setSourceAmount(BigDecimal sourceAmount) {
        this.sourceAmount = sourceAmount;
    }
    
    /**
     * @return 返回 targetAmount
     */
    public BigDecimal getTargetAmount() {
        return targetAmount;
    }
    
    /**
     * @param 对targetAmount进行赋值
     */
    public void setTargetAmount(BigDecimal targetAmount) {
        this.targetAmount = targetAmount;
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
     * @return 返回 buildTradingRecord
     */
    public LATradingRecord getBuildTradingRecord() {
        return buildTradingRecord;
    }
    
    /**
     * @param 对buildTradingRecord进行赋值
     */
    public void setBuildTradingRecord(LATradingRecord buildTradingRecord) {
        this.buildTradingRecord = buildTradingRecord;
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
}
