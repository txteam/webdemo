/*
 * 描          述:  <描述>
 * 修  改   人:  Administrator
 * 修改时间:  2017年5月16日
 * <修改描述:>
 */
package com.tx.local.loanaccount.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 扣款记录<br/>
 * <功能详细描述>
 * 
 * @author  Administrator
 * @version  [版本号, 2017年5月16日]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
@Entity
@Table(name = "LA_DEDUCT_RECORD")
public class LADeductRecord implements Serializable {
    
    /** 注释内容 */
    private static final long serialVersionUID = 6870097289221884887L;
    
    /** 扣款交易记录主键 */
    @Id
    private String id;
    
    /** 贷款账户主键 */
    private String loanAccountId;
    
    /** 交易记录主键 */
    private String tradingRecordId;
    
    /** 扣款银行账户类型 */
    private LABankAccountTypeEnum bankAccountType;
    
    /** 扣款银行账户 */
    @Column(name = "bankAccountId")
    private LABankAccount bankAccount;
    
    /** 扣款类型 */
    private LADeductRecordTypeEnum type;
    
    /** 扣款记录状态 */
    private LADeductRecordStatusEnum status;
    
    /** 所有扣款金额 */
    private BigDecimal sum;
    
    /** 待确认的扣款任务数目，如果一个扣款意愿被拆分 为3条扣款任务，则数目初始 为三，每确认一条扣款 则减一 */
    private Integer count;
    
    /** 已经完成的条目数 */
    private Integer completeCount;
    
    /** 已经完成的金额 */
    private BigDecimal completeSum;
    
    /** 完成时间 */
    private Date completeDate;
    
    /** 扣取成功的金额 */
    private BigDecimal successSum = BigDecimal.ZERO;
    
    /** 扣取失败的金额 */
    private BigDecimal failSum = BigDecimal.ZERO;
    
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
     * @return 返回 type
     */
    public LADeductRecordTypeEnum getType() {
        return type;
    }
    
    /**
     * @param 对type进行赋值
     */
    public void setType(LADeductRecordTypeEnum type) {
        this.type = type;
    }
    
    /**
     * @return 返回 status
     */
    public LADeductRecordStatusEnum getStatus() {
        return status;
    }
    
    /**
     * @param 对status进行赋值
     */
    public void setStatus(LADeductRecordStatusEnum status) {
        this.status = status;
    }
    
    /**
     * @return 返回 bankAccountType
     */
    public LABankAccountTypeEnum getBankAccountType() {
        return bankAccountType;
    }
    
    /**
     * @param 对bankAccountType进行赋值
     */
    public void setBankAccountType(LABankAccountTypeEnum bankAccountType) {
        this.bankAccountType = bankAccountType;
    }
    
    /**
     * @return 返回 bankAccount
     */
    public LABankAccount getBankAccount() {
        return bankAccount;
    }
    
    /**
     * @param 对bankAccount进行赋值
     */
    public void setBankAccount(LABankAccount bankAccount) {
        this.bankAccount = bankAccount;
    }
    
    /**
     * @return 返回 count
     */
    public Integer getCount() {
        return count;
    }
    
    /**
     * @param 对count进行赋值
     */
    public void setCount(Integer count) {
        this.count = count;
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
     * @return 返回 completeCount
     */
    public Integer getCompleteCount() {
        return completeCount;
    }
    
    /**
     * @param 对completeCount进行赋值
     */
    public void setCompleteCount(Integer completeCount) {
        this.completeCount = completeCount;
    }
    
    /**
     * @return 返回 completeSum
     */
    public BigDecimal getCompleteSum() {
        return completeSum;
    }
    
    /**
     * @param 对completeSum进行赋值
     */
    public void setCompleteSum(BigDecimal completeSum) {
        this.completeSum = completeSum;
    }
    
    /**
     * @return 返回 completeDate
     */
    public Date getCompleteDate() {
        return completeDate;
    }
    
    /**
     * @param 对completeDate进行赋值
     */
    public void setCompleteDate(Date completeDate) {
        this.completeDate = completeDate;
    }
    
    /**
     * @return 返回 successSum
     */
    public BigDecimal getSuccessSum() {
        return successSum;
    }
    
    /**
     * @param 对successSum进行赋值
     */
    public void setSuccessSum(BigDecimal successSum) {
        this.successSum = successSum;
    }
    
    /**
     * @return 返回 failSum
     */
    public BigDecimal getFailSum() {
        return failSum;
    }
    
    /**
     * @param 对failSum进行赋值
     */
    public void setFailSum(BigDecimal failSum) {
        this.failSum = failSum;
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
}
