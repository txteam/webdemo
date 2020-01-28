/*
 * 描          述:  <描述>
 * 修  改   人:  Administrator
 * 修改时间:  2015年2月7日
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
 * 扣款失败记录<br/>
 * <功能详细描述>
 * 
 * @author  Administrator
 * @version  [版本号, 2015年2月7日]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
@Entity
@Table(name = "LA_DEDUCT_FAIL_RECORD")
public class DeductFailChargeRecord implements Serializable {
    
    /** 注释内容 */
    private static final long serialVersionUID = 2005292780191016141L;
    
    /** 记录id唯一键 */
    @Id
    private String id;
    
    /** 对应贷款账户id */
    private String loanAccountId;
    
    /** 对应的扣款任务id */
    private String duductRecordId;
    
    /** 对应的交易记录id */
    private String tradingRecordId;
    
    /** 所属扣款渠道 */
    private LABankAccountTypeEnum bankAccountType;
    
    /** 贷款金额 */
    private BigDecimal loanAmount;
    
    /** 扣款失败金额 */
    private BigDecimal deductFailAmount;
    
    /** 期数 */
    private String period;
    
    /** 计费的费用项 */
    private FeeItemEnum feeItem;
    
    /** 计费金额 */
    private BigDecimal amount;
    
    /** 计费时间 */
    private Date recordDate;
    
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
     * @return 返回 duductRecordId
     */
    public String getDuductRecordId() {
        return duductRecordId;
    }
    
    /**
     * @param 对duductRecordId进行赋值
     */
    public void setDuductRecordId(String duductRecordId) {
        this.duductRecordId = duductRecordId;
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
     * @return 返回 loanAmount
     */
    public BigDecimal getLoanAmount() {
        return loanAmount;
    }
    
    /**
     * @param 对loanAmount进行赋值
     */
    public void setLoanAmount(BigDecimal loanAmount) {
        this.loanAmount = loanAmount;
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
     * @return 返回 deductFailAmount
     */
    public BigDecimal getDeductFailAmount() {
        return deductFailAmount;
    }
    
    /**
     * @param 对deductFailAmount进行赋值
     */
    public void setDeductFailAmount(BigDecimal deductFailAmount) {
        this.deductFailAmount = deductFailAmount;
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
}
