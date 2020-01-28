package com.tx.local.loanaccount.model;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.apache.commons.lang3.StringUtils;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.tx.core.util.ObjectUtils;
import com.tx.local.basicdata.model.FeeItemEnum;

/**
 * 还款计划分项 <br/>
 * <功能详细描述>
 * 
 * @author  Administrator
 * @version  [版本号, 2014年5月17日]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
@Entity
@Table(name = "LA_PAYMENT_SCHEDULE_ENTRY")
public class PaymentScheduleEntry
        implements Serializable, ScheduleEntryAssociate, AssignEntryComparable {
    
    /** 注释内容 */
    private static final long serialVersionUID = -7788594151248857483L;
    
    //主键：
    @Id
    @Column(nullable = false, length = 64)
    private String id;
    
    /** 贷款单账户唯一键:  */
    @Column(nullable = false, length = 64)
    private String loanAccountId;
    
    /** 还款类型 */
    @Column(nullable = false, length = 64)
    private ScheduleTypeEnum scheduleType;
    
    /** 主还款计划 */
    @ManyToOne
    @JoinColumn(name = "paymentScheduleId")
    @JsonIgnore
    @Column(nullable = false, length = 64)
    private PaymentSchedule paymentSchedule;
    
    /** 前一期期数 */
    @Column(nullable = true, length = 64)
    private String prePeriod;
    
    /** 期数 */
    @Column(nullable = false, length = 64)
    private String period;
    
    /** 下一期 */
    @Column(nullable = true, length = 64)
    private String nextPeriod;
    
    /** 费用项 */
    @Column(nullable = false, length = 64)
    private FeeItemEnum feeItem;
    
    /** 应收金额： */
    @Column(nullable = false, precision = 20, scale = 6)
    private BigDecimal receivableAmount = BigDecimal.ZERO;
    
    /** 实收金额： */
    @Column(nullable = false, precision = 20, scale = 6)
    private BigDecimal actualReceivedAmount = BigDecimal.ZERO;
    
    /** 豁免金额： */
    @Column(nullable = false, precision = 20, scale = 6)
    private BigDecimal exemptAmount = BigDecimal.ZERO;
    
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
     * @return 返回 scheduleType
     */
    public ScheduleTypeEnum getScheduleType() {
        return scheduleType;
    }
    
    /**
     * @param 对scheduleType进行赋值
     */
    public void setScheduleType(ScheduleTypeEnum scheduleType) {
        this.scheduleType = scheduleType;
    }
    
    /**
     * @return 返回 paymentSchedule
     */
    public PaymentSchedule getPaymentSchedule() {
        return paymentSchedule;
    }
    
    /**
     * @param 对paymentSchedule进行赋值
     */
    public void setPaymentSchedule(PaymentSchedule paymentSchedule) {
        this.paymentSchedule = paymentSchedule;
    }
    
    /**
     * @return 返回 prePeriod
     */
    public String getPrePeriod() {
        return prePeriod;
    }
    
    /**
     * @param 对prePeriod进行赋值
     */
    public void setPrePeriod(String prePeriod) {
        this.prePeriod = prePeriod;
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
     * @return 返回 nextPeriod
     */
    public String getNextPeriod() {
        return nextPeriod;
    }
    
    /**
     * @param 对nextPeriod进行赋值
     */
    public void setNextPeriod(String nextPeriod) {
        this.nextPeriod = nextPeriod;
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
     * @return
     */
    @Override
    public int hashCode() {
        if (!StringUtils.isEmpty(this.id)) {
            return ObjectUtils.generateHashCode(super.hashCode(), this, "id");
        } else {
            return ObjectUtils.generateHashCode(super.hashCode(),
                    this,
                    "scheduleType",
                    "period",
                    "feeItem");
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
            return ObjectUtils.equals(this,
                    other,
                    "scheduleType",
                    "period",
                    "feeItem");
        }
    }
}