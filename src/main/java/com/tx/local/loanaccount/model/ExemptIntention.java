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
@XStreamAlias("exemptIntention")
public class ExemptIntention implements Serializable {
    
    /** 注释内容 */
    private static final long serialVersionUID = -6031308364401303434L;
    
    /** 贷款账户id */
    private String loanAccountId;
    
    /** 现金还款指定的还款日期 */
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date repayDate = new Date();
    
    /** 费用分组类型 */
    private FeeItemGroupTypeEnum groupType = FeeItemGroupTypeEnum.MONTHLY_REPAY_SUM;
    
    /** 参与分配金额,现金还款金额 */
    private BigDecimal amount = BigDecimal.ZERO;
    
    /** 还款费用项分配金额 */
    private Map<FeeItemEnum, BigDecimal> feeItem2AmountMap;
    
    /** 还款渠道金额映射 */
    private Map<String, BigDecimal> groupCode2AmountMap;
    
    /**
     * 校验还款意愿合法性
     * 
     * @return void [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    public void validate() {
        AssertUtils.notNull(this.repayDate, "repayDate is null.");
        
        AssertUtils.isTrue(amount.compareTo(BigDecimal.ZERO) >= 0,
                "repayAmount must >= 0.repayAmount:{}",
                new Object[] {});
        BigDecimal amountSum = BigDecimal.ZERO;
        
        if (!ObjectUtils.isEmpty(feeItem2AmountMap)) {
            for (BigDecimal amountTemp : feeItem2AmountMap.values()) {
                amountSum = amountSum.add(amountTemp);
            }
        }
        AssertUtils.isTrue(amount.compareTo(amountSum) >= 0,
                "repayAmount must >= feeItem2AmountMapSum. repayAmount:{} repayAmountMappingSum:{}",
                new Object[] { amount, amountSum });
    }
    
    /** 参与分配金额,现金还款金额 */
    public BigDecimal getAmount() {
        return amount;
    }
    
    /** 还款费用项分配金额 */
    public Map<FeeItemEnum, BigDecimal> getFeeItem2AmountMap() {
        return feeItem2AmountMap;
    }
    
    /** 贷款账户id */
    public String getLoanAccountId() {
        return loanAccountId;
    }
    
    /** 现金还款指定的还款日期(yyyy-MM-dd) */
    public Date getRepayDate() {
        return repayDate;
    }
    
    /** 参与分配金额,现金还款金额 */
    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }
    
    /** 还款费用项分配金额 */
    public void setFeeItem2AmountMap(Map<FeeItemEnum, BigDecimal> feeItem2AmountMap) {
        this.feeItem2AmountMap = feeItem2AmountMap;
    }
    
    /** 贷款账户id */
    public void setLoanAccountId(String loanAccountId) {
        this.loanAccountId = loanAccountId;
    }
    
    /** 现金还款指定的还款日期(yyyy-MM-dd) */
    public void setRepayDate(Date repayDate) {
        this.repayDate = repayDate;
    }
    
    /**
     * @return 返回 groupCode2AmountMap
     */
    public Map<String, BigDecimal> getGroupCode2AmountMap() {
        return groupCode2AmountMap;
    }
    
    /**
     * @param 对groupCode2AmountMap进行赋值
     */
    public void setGroupCode2AmountMap(Map<String, BigDecimal> groupCode2AmountMap) {
        this.groupCode2AmountMap = groupCode2AmountMap;
    }
    
    /** 还款金额类型 */
    public FeeItemGroupTypeEnum getGroupType() {
        return groupType;
    }
}
