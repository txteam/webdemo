/*
 * 描          述:  <描述>
 * 修  改   人:  lenovo
 * 修改时间:  2014年6月18日
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
 * 逾期利息计收记录<br/>
 * la_overdule_interest_record
 * 
 * @author  lenovo
 * @version  [版本号, 2014年6月18日]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
@Entity
@Table(name = "LA_OVERDUE_INTEREST_RECORD")
public class OverdueInterestChargeRecord implements Serializable {
    
    /** 注释内容 */
    private static final long serialVersionUID = -2660937637980263832L;
    
    /** 唯一键 */
    @Id
    private String id;
    
    /** 贷款账户id */
    private String loanAccountId;
    
    /** 逾期利息记录对应的交易记录id */
    private String tradingRecordId;
    
    /** 当前期数 */
    private String currentPeriod;
    
    /** 逾期金额 */
    private BigDecimal overdueAmount;
    
    /** 逾期总金额 */
    private BigDecimal overdueSum;
    
    /** 本金结余： */
    private BigDecimal principalBalance;
    
    /** 逾期利率 */
    private BigDecimal overdueInterestRate;
    
    /** 逾期天数：一般用于，当豁免日期刚过次日，就或产生一条多天的逾期利息计费 */
    private int dayCount;
    
    /** 逾期利息金额 */
    private BigDecimal amount;
    
    /** 期数 */
    private String period;
    
    /** 费用项 */
    private FeeItemEnum feeItem;
    
    /** 逾期时间 */
    private Date overdueDate;
    
    /** 开始时间 */
    private Date startDate;
    
    /** 计息日 */
    private Date recordDate;
    
    /** 记录日期对应期数 */
    private String recordDatePeriod;
    
    /** 撤销交易记录id */
    private String revokeTradingRecordId;
    
    /** 是否撤销 */
    private boolean revoked;
    
    /** 撤销时间 */
    private Date revokeDate;
    
    /** 备注 */
    private String remark;
    
    /** 创建时间 */
    private Date createDate;
    
    /** 最后更新时间 */
    private Date lastUpdateDate;
    
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
     * @return 返回 currentPeriod
     */
    public String getCurrentPeriod() {
        return currentPeriod;
    }
    
    /**
     * @param 对currentPeriod进行赋值
     */
    public void setCurrentPeriod(String currentPeriod) {
        this.currentPeriod = currentPeriod;
    }
    
    /**
     * @return 返回 recordDatePeriod
     */
    public String getRecordDatePeriod() {
        return recordDatePeriod;
    }
    
    /**
     * @param 对recordDatePeriod进行赋值
     */
    public void setRecordDatePeriod(String recordDatePeriod) {
        this.recordDatePeriod = recordDatePeriod;
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
     * @return 返回 overdueInterestRate
     */
    public BigDecimal getOverdueInterestRate() {
        return overdueInterestRate;
    }
    
    /**
     * @param 对overdueInterestRate进行赋值
     */
    public void setOverdueInterestRate(BigDecimal overdueInterestRate) {
        this.overdueInterestRate = overdueInterestRate;
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
    
    /**
     * @return 返回 remark
     */
    public String getRemark() {
        return remark;
    }
    
    /**
     * @param 对remark进行赋值
     */
    public void setRemark(String remark) {
        this.remark = remark;
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
     * @return 返回 dayCount
     */
    public int getDayCount() {
        return dayCount;
    }
    
    /**
     * @param 对dayCount进行赋值
     */
    public void setDayCount(int dayCount) {
        this.dayCount = dayCount;
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
     * @return 返回 startDate
     */
    public Date getStartDate() {
        return startDate;
    }

    /**
     * @param 对startDate进行赋值
     */
    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }
}
