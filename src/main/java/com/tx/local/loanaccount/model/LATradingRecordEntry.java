/*
 * 描          述:  <描述>
 * 修  改   人:  Administrator
 * 修改时间:  2020年1月26日
 * <修改描述:>
 */
package com.tx.local.loanaccount.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.tx.local.basicdata.model.DebitCreditDirectionEnum;

/**
 * <功能简述>
 * <功能详细描述>
 * 
 * @author  Administrator
 * @version  [版本号, 2020年1月26日]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
@Entity
@Table(name = "la_trading_record_entry")
public class LATradingRecordEntry implements Serializable {
    
    /** 注释内容 */
    private static final long serialVersionUID = -5301854616234280805L;
    
    /** 主键：*/
    private String id;
    
    /** 贷款账户id */
    @JsonIgnore
    @Column(name = "tradingRecordId", nullable = false, length = 64)
    private String loanAccountId;
    
    /** 贷款账户交易记录: */
    @JsonIgnore
    @Column(name = "tradingRecordId", nullable = false, length = 64)
    private LATradingRecord tradingRecord;
    
    /** 借|贷方向 */
    @Column(nullable = false, length = 64)
    private DebitCreditDirectionEnum direction;
    
    /** 财务科目公司编码 */
    @Column(nullable = false, length = 64)
    private String accountTitleCompanyCode;
    
    /** 财务科目编码 */
    @Column(nullable = false, length = 64)
    private String accountTitleCode;
    
    /** 平息金额： */
    private BigDecimal amount;
    
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
     * @return 返回 direction
     */
    public DebitCreditDirectionEnum getDirection() {
        return direction;
    }
    
    /**
     * @param 对direction进行赋值
     */
    public void setDirection(DebitCreditDirectionEnum direction) {
        this.direction = direction;
    }
    
    /**
     * @return 返回 accountTitleCompanyCode
     */
    public String getAccountTitleCompanyCode() {
        return accountTitleCompanyCode;
    }
    
    /**
     * @param 对accountTitleCompanyCode进行赋值
     */
    public void setAccountTitleCompanyCode(String accountTitleCompanyCode) {
        this.accountTitleCompanyCode = accountTitleCompanyCode;
    }
    
    /**
     * @return 返回 accountTitleCode
     */
    public String getAccountTitleCode() {
        return accountTitleCode;
    }
    
    /**
     * @param 对accountTitleCode进行赋值
     */
    public void setAccountTitleCode(String accountTitleCode) {
        this.accountTitleCode = accountTitleCode;
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
    
}
