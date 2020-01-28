/*
 * 描          述:  <描述>
 * 修  改   人:  Administrator
 * 修改时间:  2014年7月2日
 * <修改描述:>
 */
package com.tx.local.loanaccount.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Map;

import org.springframework.format.annotation.DateTimeFormat;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.tx.core.exceptions.util.AssertUtils;
import com.tx.core.util.ObjectUtils;
import com.tx.local.basicdata.model.FeeItemEnum;

/**
 * 还款款意愿<br/>
 * 
 * @author Tim.peng
 * @version [版本号, 2014年7月2日]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
@XStreamAlias("settleIntention")
public class SettleIntention implements Serializable {
    
    /** 注释内容 */
    private static final long serialVersionUID = -6031308364401303434L;
    
    /** 贷款账户id */
    private String loanAccountId;
    
    /** 还款模型类型：现金还款、现金提前结清、自动转账还款、自动转账提前结清、POS机还款、POS机还款提前结清 */
    private SettleIntentionTypeEnum type = SettleIntentionTypeEnum.EARLY_SETTLE;
    
    /** 银行账户id */
    private String bankAccountId;
    
    /** 现金还款指定的还款日期 */
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date repayDate = new Date();
    
    /** 参与分配金额,现金还款金额 */
    private BigDecimal repayAmount = BigDecimal.ZERO;
    
    /** 参与分配金额,现金还款金额 */
    private BigDecimal exemptAmount = BigDecimal.ZERO;
    
    /** 还款费用项分配金额 */
    private Map<FeeItemEnum, BigDecimal> exemptFeeItem2AmountMap;
    
    /** 所属委外分派记录 id */
    private String outsourceAssignRecordId;
    
    /**
     * 校验还款意愿合法性
     * 
     * @return void [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    public void validate() {
        AssertUtils.notNull(this.repayDate, "repayDate is null.");
        
        AssertUtils.isTrue(repayAmount.compareTo(BigDecimal.ZERO) >= 0,
                "repayAmount must >= 0.exemptAmount:{}",
                new Object[] {});
        
        BigDecimal exemptAmountSum = BigDecimal.ZERO;
        if (!ObjectUtils.isEmpty(exemptFeeItem2AmountMap)) {
            for (BigDecimal amountTemp : exemptFeeItem2AmountMap.values()) {
                exemptAmountSum = exemptAmountSum.add(amountTemp);
            }
        }
        AssertUtils.isTrue(exemptAmount.compareTo(exemptAmountSum) >= 0,
                "exemptAmount must >= feeItem2AmountMapSum. exemptAmount:{} exemptAmountSum:{}",
                new Object[] { exemptAmount, exemptAmountSum });
        
        AssertUtils.notNull(this.type, "type is null.");
    }
    
    /**
     * @return 返回 type
     */
    public SettleIntentionTypeEnum getType() {
        return type;
    }
    
    /**
     * @param 对type进行赋值
     */
    public void setType(SettleIntentionTypeEnum type) {
        this.type = type;
    }
    
    /** 参与分配金额,现金还款金额 */
    public BigDecimal getRepayAmount() {
        return this.repayAmount;
    }
    
    /** 参与分配金额,现金还款金额 */
    public BigDecimal getExemptAmount() {
        return this.exemptAmount;
    }
    
    /** 银行账户id */
    public String getBankAccountId() {
        return bankAccountId;
    }
    
    /** 还款费用项分配金额 */
    public Map<FeeItemEnum, BigDecimal> getExemptFeeItem2AmountMap() {
        return exemptFeeItem2AmountMap;
    }
    
    /** 贷款账户id */
    public String getLoanAccountId() {
        return loanAccountId;
    }
    
    /** 所属委外分派记录 id */
    public String getOutsourceAssignRecordId() {
        return outsourceAssignRecordId;
    }
    
    /** 现金还款指定的还款日期(yyyy-MM-dd) */
    public Date getRepayDate() {
        return repayDate;
    }
    
    /** @param 对repayAmount进行赋值 */
    public void setRepayAmount(BigDecimal repayAmount) {
        this.repayAmount = repayAmount;
    }
    
    /**
     * @param 对exemptAmount进行赋值
     */
    public void setExemptAmount(BigDecimal exemptAmount) {
        this.exemptAmount = exemptAmount;
    }
    
    /** 银行账户id */
    public void setBankAccountId(String bankAccountId) {
        this.bankAccountId = bankAccountId;
    }
    
    /** 还款费用项分配金额 */
    public void setFeeItem2AmountMap(Map<FeeItemEnum, BigDecimal> exemptFeeItem2AmountMap) {
        this.exemptFeeItem2AmountMap = exemptFeeItem2AmountMap;
    }
    
    /** 贷款账户id */
    public void setLoanAccountId(String loanAccountId) {
        this.loanAccountId = loanAccountId;
    }
    
    /** 所属委外分派记录 id */
    public void setOutsourceAssignRecordId(String outsourceAssignRecordId) {
        this.outsourceAssignRecordId = outsourceAssignRecordId;
    }
    
    /** 现金还款指定的还款日期(yyyy-MM-dd) */
    public void setRepayDate(Date repayDate) {
        this.repayDate = repayDate;
    }
}
