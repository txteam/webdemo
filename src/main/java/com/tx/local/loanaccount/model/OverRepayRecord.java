/*
 * 描          述:  <描述>
 * 修  改   人:  lenovo
 * 修改时间:  2015年1月3日
 * <修改描述:>
 */
package com.tx.local.loanaccount.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.tx.local.basicdata.model.FeeItemEnum;

/**
 * 超额还款记录<br/>
 * <功能详细描述>
 * 
 * @author  lenovo
 * @version  [版本号, 2015年1月3日]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
@Entity
@Table(name = "LA_OVER_REPAY_RECORD")
public class OverRepayRecord implements Serializable {
    
    /** 注释内容 */
    private static final long serialVersionUID = -6031308364401303434L;
    
    @Id
    private String id;
    
    /** 对应的贷款账户id */
    private String loanAccountId;
    
    /** 对应的交易记录id */
    private String tradingRecordId;
    
    /** 对应的期数 */
    private String period;
    
    /** 超额还款对应的费用项 */
    private FeeItemEnum feeItem;
    
    /** 超额还款金额 */
    private BigDecimal amount;
    
    /** 撤销交易记录id */
    private String revokeTradingRecordId;
    
    /** 是否被撤销或退回  */
    private boolean revoked;
    
    /** 撤销或退回时间 */
    private Date revokeDate;
    
    /** 是否到帐 */
    private boolean received;
    
    /** 到账时间 */
    private Date receiveDate;
    
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
     * @return 返回 tradingRecordId
     */
    public String getTradingRecordId() {
        return tradingRecordId;
    }
    
    /**
     * @param 对tradingRecordId进行赋值
     */
    public void setTradingRecordId(String tradingRecordId) {
        this.tradingRecordId = tradingRecordId;
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
     * @return 返回 revokeTradingRecordId
     */
    public String getRevokeTradingRecordId() {
        return revokeTradingRecordId;
    }
    
    /**
     * @param 对revokeTradingRecordId进行赋值
     */
    public void setRevokeTradingRecordId(String revokeTradingRecordId) {
        this.revokeTradingRecordId = revokeTradingRecordId;
    }
}
