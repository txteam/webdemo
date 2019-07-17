/*
 * 描 述: <描述> 修 改 人: Administrator 修改时间: 2015年11月28日 <修改描述:>
 */
package com.tx.local.clientaccount.model;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 客户账户<br/>
 * <功能详细描述>
 * 
 * @author Administrator
 * @version [版本号, 2015年11月28日]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
@Entity
@Table(name = "cla_client_account")
public class ClientAccount {
    
    /** 客户账户id */
    @Id
    private String id;
    
    /** 客户信息 */
    private String clientId;
    
    /* -------- 累计金额区 start --------- */
    
    /** 最后一次更新当日金额时所参考的时间：如果不是同一条，则将对应金额置0后进行处理 */
    private Date currentDayDate = new Date();
    
    /** 当日累计充值: 在充值时增加，在投资时减少 */
    private BigDecimal currentDayRechargeSum = BigDecimal.ZERO;
    
    /** 当日累计提现金额: 在充值时增加，在投资时减少 */
    private BigDecimal currentDayCashoutSum = BigDecimal.ZERO;
    
    /** 累计:充值总额： 用户充值时累加 */
    private BigDecimal rechargeSum = BigDecimal.ZERO;
    
    /** 累计：提现总额 */
    private BigDecimal cashoutSum = BigDecimal.ZERO;
    
    /** 累计：转入金额 */
    private BigDecimal transferInSum = BigDecimal.ZERO;
    
    /** 累计：转出金额 */
    private BigDecimal transferOutSum = BigDecimal.ZERO;
    
    /* -------- 累计金额区 end  --------- */
    
    /** 客户账户总金额：客户总金额 */
    private BigDecimal sum = BigDecimal.ZERO;
    
    /** 待结算金额：客户充值后可能已经从第三方获取到充值成功，但可能存在该客户的金额还处于待结算的状态 */
    private BigDecimal waitSettleSum = BigDecimal.ZERO;
    
    /** 客户账户总冻结资金: 冻结总金额 */
    private BigDecimal frozenSum = BigDecimal.ZERO;
    
    /** 可使用金额：该金额>=0，可使用金额 >= 当前金额时，账户可进行购买交易 */
    private BigDecimal availableSum = BigDecimal.ZERO;
    
    /* -------- 资金相关 end   ---------- */
    
    /** 创建时间 */
    private Date createDate = new Date();
    
    /** 最后更新时间 */
    private Date lastUpdateDate = new Date();
    
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
     * @return 返回 currentDayRechargeSum
     */
    public BigDecimal getCurrentDayRechargeSum() {
        return currentDayRechargeSum;
    }
    
    /**
     * @param 对currentDayRechargeSum进行赋值
     */
    public void setCurrentDayRechargeSum(BigDecimal currentDayRechargeSum) {
        this.currentDayRechargeSum = currentDayRechargeSum;
    }
    
    /**
     * @return 返回 currentDayCashoutSum
     */
    public BigDecimal getCurrentDayCashoutSum() {
        return currentDayCashoutSum;
    }
    
    /**
     * @param 对currentDayCashoutSum进行赋值
     */
    public void setCurrentDayCashoutSum(BigDecimal currentDayCashoutSum) {
        this.currentDayCashoutSum = currentDayCashoutSum;
    }
    
    /**
     * @return 返回 rechargeSum
     */
    public BigDecimal getRechargeSum() {
        return rechargeSum;
    }
    
    /**
     * @param 对rechargeSum进行赋值
     */
    public void setRechargeSum(BigDecimal rechargeSum) {
        this.rechargeSum = rechargeSum;
    }
    
    /**
     * @return 返回 cashoutSum
     */
    public BigDecimal getCashoutSum() {
        return cashoutSum;
    }
    
    /**
     * @param 对cashoutSum进行赋值
     */
    public void setCashoutSum(BigDecimal cashoutSum) {
        this.cashoutSum = cashoutSum;
    }
    
    /**
     * @return 返回 transferInSum
     */
    public BigDecimal getTransferInSum() {
        return transferInSum;
    }
    
    /**
     * @param 对transferInSum进行赋值
     */
    public void setTransferInSum(BigDecimal transferInSum) {
        this.transferInSum = transferInSum;
    }
    
    /**
     * @return 返回 transferOutSum
     */
    public BigDecimal getTransferOutSum() {
        return transferOutSum;
    }
    
    /**
     * @param 对transferOutSum进行赋值
     */
    public void setTransferOutSum(BigDecimal transferOutSum) {
        this.transferOutSum = transferOutSum;
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
     * @return 返回 waitSettleSum
     */
    public BigDecimal getWaitSettleSum() {
        return waitSettleSum;
    }
    
    /**
     * @param 对waitSettleSum进行赋值
     */
    public void setWaitSettleSum(BigDecimal waitSettleSum) {
        this.waitSettleSum = waitSettleSum;
    }
    
    /**
     * @return 返回 frozenSum
     */
    public BigDecimal getFrozenSum() {
        return frozenSum;
    }
    
    /**
     * @param 对frozenSum进行赋值
     */
    public void setFrozenSum(BigDecimal frozenSum) {
        this.frozenSum = frozenSum;
    }
    
    /**
     * @return 返回 availableSum
     */
    public BigDecimal getAvailableSum() {
        return availableSum;
    }
    
    /**
     * @param 对availableSum进行赋值
     */
    public void setAvailableSum(BigDecimal availableSum) {
        this.availableSum = availableSum;
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
     * @return 返回 currentDayDate
     */
    public Date getCurrentDayDate() {
        return currentDayDate;
    }
    
    /**
     * @param 对currentDayDate进行赋值
     */
    public void setCurrentDayDate(Date currentDayDate) {
        this.currentDayDate = currentDayDate;
    }
}
