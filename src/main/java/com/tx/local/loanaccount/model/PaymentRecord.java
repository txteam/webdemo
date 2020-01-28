/*
 * 描          述:  <描述>
 * 修  改   人:  Administrator
 * 修改时间:  2014年4月16日
 * <修改描述:>
 */
package com.tx.local.loanaccount.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.apache.commons.collections4.CollectionUtils;
import org.springframework.format.annotation.DateTimeFormat;

import com.tx.core.exceptions.util.AssertUtils;
import com.tx.local.basicdata.model.FeeItemEnum;

/**
 * 还款记录<br/>
 *    针对自动转账还款而言，即产生未到帐实收<br/>
 *    在还款计划处理方面，未到帐实收，可根据实际情况选择是否进行入账<br/>
 * @author  Administrator
 * @version  [版本号, 2014年4月16日]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
@Entity
@Table(name = "LA_PAYMENT_RECORD")
public class PaymentRecord implements Serializable, ScheduleAssociate {
    
    /** 注释内容 */
    private static final long serialVersionUID = -6031308364401303434L;
    
    /** 主键 */
    @Id
    private String id;
    
    /** 构建记录对应的交易记录：服务于调账，交易中一般来说buildTrading = trading. 如果记录被调账过，则会出现buildTraindg != trading的情形 */
    @ManyToOne
    @Column(name = "buildTradingRecordId")
    private LATradingRecord buildTradingRecord;
    
    /** 对应交易 */
    @ManyToOne
    @Column(name = "tradingRecordId")
    private LATradingRecord tradingRecord;
    
    /** 关联还款计划分项主键 */
    @ManyToOne
    @Column(name = "paymentScheduleId")
    private PaymentSchedule paymentSchedule;
    
    /** 贷款单账户唯一键 */
    private String loanAccountId;
    
    /** 还款类型 */
    private ScheduleTypeEnum scheduleType;
    
    /** 期数 */
    private String period;
    
    /** 还款到期日期 */
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date expireDate;
    
    /** 原金额 */
    private BigDecimal sourceSum;
    
    /** 金额 */
    private BigDecimal sum;
    
    /** 目标金额 */
    private BigDecimal targetSum;
    
    /** 本金结余 */
    private BigDecimal principalBalance;
    
    /** 还款日期 */
    private Date repayDate;
    
    /** 是否撤销 */
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
    
    /** 一对多的映射 */
    @OneToMany
    private List<PaymentRecordEntry> paymentRecordEntryList;
    
    /** 支付分项记录 */
    @OneToMany
    private final Map<FeeItemEnum, PaymentRecordEntry> paymentRecordEntryMap = new HashMap<FeeItemEnum, PaymentRecordEntry>();
    
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
     * @return 返回 paymentSchedule
     */
    public PaymentSchedule getPaymentSchedule() {
        return paymentSchedule;
    }
    
    /**
     * @param 对paymentSchedule进行赋值
     */
    public void setPaymentSchedule(PaymentSchedule paymentSchedule) {
        this.paymentSchedule = paymentSchedule;
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
     * @return 返回 sourceSum
     */
    public BigDecimal getSourceSum() {
        return sourceSum;
    }
    
    /**
     * @param 对sourceSum进行赋值
     */
    public void setSourceSum(BigDecimal sourceSum) {
        this.sourceSum = sourceSum;
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
     * @return 返回 targetSum
     */
    public BigDecimal getTargetSum() {
        return targetSum;
    }
    
    /**
     * @param 对targetSum进行赋值
     */
    public void setTargetSum(BigDecimal targetSum) {
        this.targetSum = targetSum;
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
        AssertUtils.notNull(paymentRecordEntryList,
                "paymentRecordEntryList is null.");
        
        this.paymentRecordEntryMap.clear();
        this.paymentRecordEntryList = paymentRecordEntryList;
        
        if (CollectionUtils.isEmpty(paymentRecordEntryList)) {
            return;
        }
        for (PaymentRecordEntry paymentRecordEntry : paymentRecordEntryList) {
            AssertUtils.isTrue(
                    this.scheduleType != null && this.scheduleType
                            .equals(paymentRecordEntry.getScheduleType()),
                    "paymentRecordEntry scheduleType not matched.ScheduleType:{}",
                    new Object[] { paymentRecordEntry.getScheduleType() });
            AssertUtils.isTrue(
                    this.period != null && this.period
                            .equals(paymentRecordEntry.getPeriod()),
                    "paymentRecordEntry period not matched.period:{}",
                    paymentRecordEntry.getPeriod());
            
            paymentRecordEntry.setPaymentRecord(this);
            this.paymentRecordEntryMap.put(paymentRecordEntry.getFeeItem(),
                    paymentRecordEntry);
        }
    }
    
    /**
     * 压入还款计划分项
     *<功能详细描述>
     * @param paymentScheduleEntry [参数说明]
     * 
     * @return void [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
    */
    public void addPaymentRecordEntry(PaymentRecordEntry paymentRecordEntry) {
        AssertUtils.notNull(paymentRecordEntry, "paymentRecordEntry is null.");
        AssertUtils.isTrue(
                this.scheduleType != null && this.scheduleType
                        .equals(paymentRecordEntry.getScheduleType()),
                "paymentRecordEntry scheduleType not matched.ScheduleType:{}",
                new Object[] { paymentRecordEntry.getScheduleType() });
        AssertUtils.isTrue(
                this.period != null
                        && this.period.equals(paymentRecordEntry.getPeriod()),
                "paymentRecordEntry period not matched.period:{}",
                paymentRecordEntry.getPeriod());
        AssertUtils.isTrue(
                !this.paymentRecordEntryMap
                        .containsKey(paymentRecordEntry.getFeeItem()),
                "feeItem is exist.feeItem:{}",
                new Object[] { paymentRecordEntry.getFeeItem() });
        
        paymentRecordEntry.setPaymentRecord(this);
        this.paymentRecordEntryList.add(paymentRecordEntry);
        this.paymentRecordEntryMap.put(paymentRecordEntry.getFeeItem(),
                paymentRecordEntry);
        //this.paymentRecordEntryMap.add(paymentRecordEntry);
    }
    
    //  /**
    //  * @return 返回 paymentRecordEntryMap
    //  */
    // public ScheduleEntryAssociateMap<PaymentRecordEntry> getPaymentRecordEntryMap() {
    //     return paymentRecordEntryMap;
    // }
    
    /**
     * @return 返回 lastUpdateDate
     */
    public Date getLastUpdateDate() {
        return lastUpdateDate;
    }
    
    /**
     * @return 返回 paymentRecordEntryMap
     */
    public Map<FeeItemEnum, PaymentRecordEntry> getPaymentRecordEntryMap() {
        return paymentRecordEntryMap;
    }
    
    /**
     * @param 对lastUpdateDate进行赋值
     */
    public void setLastUpdateDate(Date lastUpdateDate) {
        this.lastUpdateDate = lastUpdateDate;
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
}
