/*
 * 描 述: <描述> 修 改 人: Administrator 修改时间: 2015年12月19日 <修改描述:>
 */
package com.tx.local.clientaccount.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.tx.core.util.ObjectUtils;

/**
 * 子账户<br/>
 * <功能详细描述>
 * 
 * @author Administrator
 * @version [版本号, 2015年12月19日]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
@Entity
@Table(name = "cla_client_account_item")
public class ClientAccountItem implements Serializable{
    
    /** 注释内容 */
    private static final long serialVersionUID = 9192974500859482745L;

    /** 客户账户项id */
    @Id
    private String id;
    
    /** 客户id */
    private String clientId;
    
    /** 客户账户id */
    private String clientAccountId;
    
    /** 账户编码：如果为托管账户，则存放托管的第三方账号 */
    private String code;
    
    /** 账户名称：服务于存储第三方账户:xxxx_结算专用账户 */
    private String accountName;
    
    /** 客户账户项类型 */
    //private PaymentChannelEnum paymentChannel;
    
    /** 累计: 充值金额 */
    private BigDecimal rechargeSum = BigDecimal.ZERO;
    
    /** 累计：提现金额 */
    private BigDecimal cashoutSum = BigDecimal.ZERO;
    
    /** 客户账户金额 */
    private BigDecimal amount = BigDecimal.ZERO;
    
    /** 待结算金额 */
    private BigDecimal waitSettleAmount = BigDecimal.ZERO;
    
    /** 客户账户冻结金额 */
    private BigDecimal frozenAmount = BigDecimal.ZERO;
    
    /** 可使用金额 */
    private BigDecimal availableAmount = BigDecimal.ZERO;
    
    /** 是否激活 */
    private Boolean activated;
    
    /** 激活时间 */
    private Date activateDate;
    
    /** 最后更新时间 */
    private Date lastUpdateDate = new Date();
    
    /** 创建时间 */
    private Date createDate = new Date();
    
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
     * @return 返回 code
     */
    public String getCode() {
        return code;
    }
    
    /**
     * @param 对code进行赋值
     */
    public void setCode(String code) {
        this.code = code;
    }
    
    /**
     * @return 返回 accountName
     */
    public String getAccountName() {
        return accountName;
    }
    
    /**
     * @param 对accountName进行赋值
     */
    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }
    
//    /**
//     * @return 返回 paymentChannel
//     */
//    public PaymentChannelEnum getPaymentChannel() {
//        return paymentChannel;
//    }
//    
//    /**
//     * @param 对paymentChannel进行赋值
//     */
//    public void setPaymentChannel(PaymentChannelEnum paymentChannel) {
//        this.paymentChannel = paymentChannel;
//    }
    
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
     * @return 返回 waitSettleAmount
     */
    public BigDecimal getWaitSettleAmount() {
        return waitSettleAmount;
    }
    
    /**
     * @param 对waitSettleAmount进行赋值
     */
    public void setWaitSettleAmount(BigDecimal waitSettleAmount) {
        this.waitSettleAmount = waitSettleAmount;
    }
    
    /**
     * @return 返回 frozenAmount
     */
    public BigDecimal getFrozenAmount() {
        return frozenAmount;
    }
    
    /**
     * @param 对frozenAmount进行赋值
     */
    public void setFrozenAmount(BigDecimal frozenAmount) {
        this.frozenAmount = frozenAmount;
    }
    
    /**
     * @return 返回 availableAmount
     */
    public BigDecimal getAvailableAmount() {
        return availableAmount;
    }
    
    /**
     * @param 对availableAmount进行赋值
     */
    public void setAvailableAmount(BigDecimal availableAmount) {
        this.availableAmount = availableAmount;
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
     * @return 返回 activated
     */
    public Boolean isActivated() {
        return activated;
    }

    /**
     * @param 对activated进行赋值
     */
    public void setActivated(Boolean activated) {
        this.activated = activated;
    }

    /**
     * @return 返回 activateDate
     */
    public Date getActivateDate() {
        return activateDate;
    }

    /**
     * @param 对activateDate进行赋值
     */
    public void setActivateDate(Date activateDate) {
        this.activateDate = activateDate;
    }

    /**
     * @return
     */
    @Override
    public int hashCode() {
        return ObjectUtils.generateHashCode(super.hashCode(), this, "id");
    }
    
    /**
     * @param obj
     * @return
     */
    @Override
    public boolean equals(Object obj) {
        return ObjectUtils.equals(this, obj, "id");
    }
}
