/*
 * 描          述:  <描述>
 * 修  改   人:  Administrator
 * 修改时间:  2017年12月6日
 * <修改描述:>
 */
package com.tx.local.taxsettle.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Table;

import org.apache.commons.lang3.StringUtils;

import com.tx.core.util.ObjectUtils;
import com.tx.local.basicdata.model.FeeItemEnum;
import com.tx.local.loanaccount.model.PaymentScheduleEntry;

/**
 * 结息计划表<br/>
 * <功能详细描述>
 * 结息计划
 * @author  Administrator
 * @version  [版本号, 2017年12月6日]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
@Table(name = "ts_settle_schedule")
public class LATaxSettleSchedule implements Serializable {
    
    /** 注释内容 */
    private static final long serialVersionUID = 3688391930181122389L;
    
    /** ID */
    private String id;
    
    /** Account ID */
    private String loanAccountId;
    
    /** 结息计划类型 */
    private LATaxSettleScheduleTypeEnum type;
    
    /** 还款期数 */
    private String period;
    
    /** 还款日 */
    private Date repaymentDate;
    
    /** 起始日 */
    private Date startDate;
    
    /** 结束日 */
    private Date endDate;
    
    /** 月份：结息计划核心字段：根据month与还款计划进行映射： 存储内容为 yyyyMM */
    private String month;
    
    /** 费用项 */
    private FeeItemEnum feeItem;
    
    /** 前应收金额: 和 paymentSchedule.receivableAmount对应 */
    private BigDecimal receivableAmount = BigDecimal.ZERO;
    
    /** 前豁免金额 */
    private BigDecimal exemptAmount = BigDecimal.ZERO;
    
    /** 前实收金额 */
    private BigDecimal actualReceivedAmount = BigDecimal.ZERO;
    
    /** 税前金额 */
    private BigDecimal beforeSettleAmount = BigDecimal.ZERO;
    
    /** 应交税金额 */
    private BigDecimal shouldSettleAmount = BigDecimal.ZERO;
    
    /** 实际交税金额 */
    private BigDecimal actualSettleAmount = BigDecimal.ZERO;
    
    /** 税前金额 */
    private BigDecimal afterSettleAmount = BigDecimal.ZERO;
    
    /**创建日期*/
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
     * @return 返回 type
     */
    public LATaxSettleScheduleTypeEnum getType() {
        return type;
    }
    
    /**
     * @param 对type进行赋值
     */
    public void setType(LATaxSettleScheduleTypeEnum type) {
        this.type = type;
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
     * @return 返回 repaymentDate
     */
    public Date getRepaymentDate() {
        return repaymentDate;
    }
    
    /**
     * @param 对repaymentDate进行赋值
     */
    public void setRepaymentDate(Date repaymentDate) {
        this.repaymentDate = repaymentDate;
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
    
    /**
     * @return 返回 endDate
     */
    public Date getEndDate() {
        return endDate;
    }
    
    /**
     * @param 对endDate进行赋值
     */
    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }
    
    /**
     * @return 返回 month
     */
    public String getMonth() {
        return month;
    }
    
    /**
     * @param 对month进行赋值
     */
    public void setMonth(String month) {
        this.month = month;
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
     * @return 返回 receivableAmount
     */
    public BigDecimal getReceivableAmount() {
        return receivableAmount;
    }
    
    /**
     * @param 对receivableAmount进行赋值
     */
    public void setReceivableAmount(BigDecimal receivableAmount) {
        this.receivableAmount = receivableAmount;
    }
    
    /**
     * @return 返回 exemptAmount
     */
    public BigDecimal getExemptAmount() {
        return exemptAmount;
    }
    
    /**
     * @param 对exemptAmount进行赋值
     */
    public void setExemptAmount(BigDecimal exemptAmount) {
        this.exemptAmount = exemptAmount;
    }
    
    /**
     * @return 返回 actualReceivedAmount
     */
    public BigDecimal getActualReceivedAmount() {
        return actualReceivedAmount;
    }
    
    /**
     * @param 对actualReceivedAmount进行赋值
     */
    public void setActualReceivedAmount(BigDecimal actualReceivedAmount) {
        this.actualReceivedAmount = actualReceivedAmount;
    }
    
    /**
     * @return 返回 beforeSettleAmount
     */
    public BigDecimal getBeforeSettleAmount() {
        return beforeSettleAmount;
    }
    
    /**
     * @param 对beforeSettleAmount进行赋值
     */
    public void setBeforeSettleAmount(BigDecimal beforeSettleAmount) {
        this.beforeSettleAmount = beforeSettleAmount;
    }
    
    /**
     * @return 返回 shouldSettleAmount
     */
    public BigDecimal getShouldSettleAmount() {
        return shouldSettleAmount;
    }
    
    /**
     * @param 对shouldSettleAmount进行赋值
     */
    public void setShouldSettleAmount(BigDecimal shouldSettleAmount) {
        this.shouldSettleAmount = shouldSettleAmount;
    }
    
    /**
     * @return 返回 actualSettleAmount
     */
    public BigDecimal getActualSettleAmount() {
        return actualSettleAmount;
    }
    
    /**
     * @param 对actualSettleAmount进行赋值
     */
    public void setActualSettleAmount(BigDecimal actualSettleAmount) {
        this.actualSettleAmount = actualSettleAmount;
    }
    
    /**
     * @return 返回 afterSettleAmount
     */
    public BigDecimal getAfterSettleAmount() {
        return afterSettleAmount;
    }
    
    /**
     * @param 对afterSettleAmount进行赋值
     */
    public void setAfterSettleAmount(BigDecimal afterSettleAmount) {
        this.afterSettleAmount = afterSettleAmount;
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
     * @return
     */
    @Override
    public int hashCode() {
        if (!StringUtils.isEmpty(this.id)) {
            return ObjectUtils.generateHashCode(super.hashCode(), this, "id");
        } else {
            return ObjectUtils.generateHashCode(super.hashCode(),
                    this,
                    "id",
                    "type",
                    "period",
                    "feeItem",
                    "month");
        }
    }
    
    /**
     * @param obj
     * @return
     */
    @Override
    public boolean equals(Object obj) {
        if (obj == null || !(obj instanceof PaymentScheduleEntry)) {
            return false;
        }
        PaymentScheduleEntry other = (PaymentScheduleEntry) obj;
        if (!StringUtils.isEmpty(this.id)
                && !StringUtils.isEmpty(other.getId())) {
            //两者均有id的情形
            return ObjectUtils.equals(this, other, "id");
        } else {
            return ObjectUtils.equals(this, other, "id", "month", "feeItem");
        }
    }
}
