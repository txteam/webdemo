/*
 * 描          述:  <描述>
 * 修  改   人:  Administrator
 * 修改时间:  2016年1月7日
 * <修改描述:>
 */
package com.tx.local.loanaccount.model;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 贷款账户每日记录<br/>
 *    在夜间事务前，以及事务后判断贷款账户的状态是否发生变更，如果发生变更则，进行数据记录<br/>
 * <功能详细描述>
 * 
 * @author  Administrator
 * @version  [版本号, 2016年1月7日]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
@Entity
@Table(name = "la_daily_record")
public class LoanAccountDailyRecord {
    
    /** 记录的唯一键 */
    @Id
    private String id;
    
    /** 贷款账户id:非空 */
    private String loanAccountId;
    
    /** 客户id */
    private String clientId;
    
    /** 是否逾期:非空 */
    private boolean overdue;
    
    /** 逾期日期 */
    private Date overdueDate;
    
    /** 逾期金额 */
    private BigDecimal overdueAmount;
    
    /** 逾期金额 */
    private BigDecimal overdueSum;
    
    /** 账户状态:非空 */
    private AccountStatusEnum accountStatus;
    
    /** 催收状态:非空 */
    private CollectionStatusEnum collectionStatus;
    
    /** 结息状态:非空 */
    private SettleInterestStatusEnum settleInterestStatus;
    
    /** 是否法律程序中:非空 */
    private boolean legalProcedure;
    
    /** 是否关闭:非空 */
    private boolean closed;
    
    /** 是否死亡：非空 */
    private boolean died;
    
    /** 本金结余:非空 */
    private BigDecimal principalBalance;
    
    /** 本金结余递减:非空 */
    private BigDecimal principalBalanceIrr;
    
    /** 逾期信息记录类型:非空 */
    private LoanAccountDailyRecordTypeEnum type;
    
    /** 记录日期 */
    private Date recordDate;
    
    /** 创建时间:非空 */
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
     * @return 返回 overdue
     */
    public boolean isOverdue() {
        return overdue;
    }
    
    /**
     * @param 对overdue进行赋值
     */
    public void setOverdue(boolean overdue) {
        this.overdue = overdue;
    }
    
    /**
     * @return 返回 overdueDate
     */
    public Date getOverdueDate() {
        return overdueDate;
    }
    
    /**
     * @param 对overdueDate进行赋值
     */
    public void setOverdueDate(Date overdueDate) {
        this.overdueDate = overdueDate;
    }
    
    /**
     * @return 返回 overdueAmount
     */
    public BigDecimal getOverdueAmount() {
        return overdueAmount;
    }
    
    /**
     * @param 对overdueAmount进行赋值
     */
    public void setOverdueAmount(BigDecimal overdueAmount) {
        this.overdueAmount = overdueAmount;
    }
    
    /**
     * @return 返回 overdueSum
     */
    public BigDecimal getOverdueSum() {
        return overdueSum;
    }
    
    /**
     * @param 对overdueSum进行赋值
     */
    public void setOverdueSum(BigDecimal overdueSum) {
        this.overdueSum = overdueSum;
    }
    
    /**
     * @return 返回 accountStatus
     */
    public AccountStatusEnum getAccountStatus() {
        return accountStatus;
    }
    
    /**
     * @param 对accountStatus进行赋值
     */
    public void setAccountStatus(AccountStatusEnum accountStatus) {
        this.accountStatus = accountStatus;
    }
    
    /**
     * @return 返回 collectionStatus
     */
    public CollectionStatusEnum getCollectionStatus() {
        return collectionStatus;
    }
    
    /**
     * @param 对collectionStatus进行赋值
     */
    public void setCollectionStatus(CollectionStatusEnum collectionStatus) {
        this.collectionStatus = collectionStatus;
    }
    
    /**
     * @return 返回 settleInterestStatus
     */
    public SettleInterestStatusEnum getSettleInterestStatus() {
        return settleInterestStatus;
    }
    
    /**
     * @param 对settleInterestStatus进行赋值
     */
    public void setSettleInterestStatus(
            SettleInterestStatusEnum settleInterestStatus) {
        this.settleInterestStatus = settleInterestStatus;
    }
    
    /**
     * @return 返回 legalProcedure
     */
    public boolean isLegalProcedure() {
        return legalProcedure;
    }
    
    /**
     * @param 对legalProcedure进行赋值
     */
    public void setLegalProcedure(boolean legalProcedure) {
        this.legalProcedure = legalProcedure;
    }
    
    /**
     * @return 返回 closed
     */
    public boolean isClosed() {
        return closed;
    }
    
    /**
     * @param 对closed进行赋值
     */
    public void setClosed(boolean closed) {
        this.closed = closed;
    }
    
    /**
     * @return 返回 died
     */
    public boolean isDied() {
        return died;
    }
    
    /**
     * @param 对died进行赋值
     */
    public void setDied(boolean died) {
        this.died = died;
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
     * @return 返回 type
     */
    public LoanAccountDailyRecordTypeEnum getType() {
        return type;
    }
    
    /**
     * @param 对type进行赋值
     */
    public void setType(LoanAccountDailyRecordTypeEnum type) {
        this.type = type;
    }
    
    /**
     * @return 返回 recordDate
     */
    public Date getRecordDate() {
        return recordDate;
    }
    
    /**
     * @param 对recordDate进行赋值
     */
    public void setRecordDate(Date recordDate) {
        this.recordDate = recordDate;
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
