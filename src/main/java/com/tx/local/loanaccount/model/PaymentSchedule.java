package com.tx.local.loanaccount.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.ListUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.format.annotation.DateTimeFormat;

import com.tx.core.TxConstants;
import com.tx.core.exceptions.util.AssertUtils;
import com.tx.core.util.ObjectUtils;
import com.tx.local.basicdata.model.FeeItemEnum;

/**
  * 还款计划实体接口<br/>
  * <功能详细描述>
  * 
  * @author  Administrator
  * @version  [版本号, 2014年5月15日]
  * @see  [相关类/方法]
  * @since  [产品/模块版本]
 */
@Table(name = "LA_PAYMENT_SCHEDULE", indexes = {
        @Index(name = "IDX_PAYMENT_SCHEDULE_00", columnList = "loanAccountId,scheduleType,period", unique = true) })
public class PaymentSchedule implements Serializable, ScheduleAssociate {
    
    /** 注释内容 */
    private static final long serialVersionUID = 2924023656543345618L;
    
    /** 唯一键：*/
    @Id
    @Column(nullable = false, length = 64)
    private String id;
    
    /** 贷款单账户唯一键:  */
    @Column(nullable = false, length = 64)
    private String loanAccountId;
    
    /** 还款类型 */
    @Column(nullable = false, length = 64)
    private ScheduleTypeEnum scheduleType;
    
    /** 前一期 */
    @Column(nullable = true, length = 64)
    private String prePeriod;
    
    /** 期数 */
    @Column(nullable = false, length = 64)
    private String period;
    
    /** 下一期 */
    @Column(nullable = true, length = 64)
    private String nextPeriod;
    
    /** 到期还款日： */
    //需要考虑月末，以及周末，以及假期，和这几个的组合的情况的处理情况
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date repaymentDate;
    
    /** 还款应收账款总额： */
    @Column(nullable = false, precision = 20, scale = 6)
    private BigDecimal receivableSum;
    
    /** 同一期的各费用项应收之和
    //还款实收账款总额：
    //同一期的各费用项实收之和  */
    @Column(nullable = false, precision = 20, scale = 6)
    private BigDecimal actualReceivedSum;
    
    /** 同一期的各递减费用项实收之和
    //豁免账款总额： */
    @Column(nullable = false, precision = 20, scale = 6)
    private BigDecimal exemptSum;
    
    /** 同一期的各费用项豁免之和
    //当前期数是否逾期： */
    private boolean overdue = false;
    
    /** 逾期金额 */
    private BigDecimal overdueAmount = BigDecimal.ZERO;
    
    /** 逾期本金 */
    private BigDecimal overduePrincipal = BigDecimal.ZERO;
    
    /** 当期中存在的费用仅有（本金、管理费、利息）参与是否逾期的计算，其他金额并不参与是否逾期的计算，逻辑需要考虑到
    /** 逾期金额： */
    private BigDecimal overdueSum = BigDecimal.ZERO;
    
    /** 是否结清 */
    private boolean settle = false;
    
    /** 结清还款日期 */
    private Date settleRepayDate;
    
    /** 结清日期 */
    private Date settleProcessDate;
    
    /** 最后一次还款日 */
    private Date lastRepayDate;
    
    /** 本金结余： */
    private BigDecimal principalBalance = null;
    
    /** 还款计划分项 */
    @OneToMany
    private List<PaymentScheduleEntry> paymentScheduleEntryList = new ArrayList<>(
            TxConstants.INITIAL_CONLLECTION_SIZE);
    
    /** 还款计划分项 */
    @OneToMany
    private final Map<FeeItemEnum, PaymentScheduleEntry> paymentScheduleEntryMap = new HashMap<FeeItemEnum, PaymentScheduleEntry>();
    
    //  /** 还款计划分项 */
    //    @OneToMany
    //    private final ScheduleEntryAssociateMap<PaymentScheduleEntry> paymentScheduleEntryMap = new ScheduleEntryAssociateMap<PaymentScheduleEntry>();
    
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
     * @return 返回 receivableSum
     */
    public BigDecimal getReceivableSum() {
        return receivableSum;
    }
    
    /**
     * @param 对receivableSum进行赋值
     */
    public void setReceivableSum(BigDecimal receivableSum) {
        this.receivableSum = receivableSum;
    }
    
    /**
     * @return 返回 actualReceivedSum
     */
    public BigDecimal getActualReceivedSum() {
        return actualReceivedSum;
    }
    
    /**
     * @param 对actualReceivedSum进行赋值
     */
    public void setActualReceivedSum(BigDecimal actualReceivedSum) {
        this.actualReceivedSum = actualReceivedSum;
    }
    
    /**
     * @return 返回 exemptSum
     */
    public BigDecimal getExemptSum() {
        return exemptSum;
    }
    
    /**
     * @param 对exemptSum进行赋值
     */
    public void setExemptSum(BigDecimal exemptSum) {
        this.exemptSum = exemptSum;
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
     * @return 返回 paymentScheduleEntryList
     */
    public List<PaymentScheduleEntry> getPaymentScheduleEntryList() {
        @SuppressWarnings("unchecked")
        List<PaymentScheduleEntry> resList = ListUtils
                .unmodifiableList(paymentScheduleEntryList);
        return resList;
    }
    
    //    /**
    //     * @param 对paymentScheduleEntryList进行赋值
    //     */
    //    public void setPaymentScheduleEntryList(List<PaymentScheduleEntry> paymentScheduleEntryList) {
    //        AssertUtils.notNull(paymentScheduleEntryList, "paymentScheduleEntryList is null.");
    //        
    //        this.paymentScheduleEntryMap.clear();
    //        this.paymentScheduleEntryList = paymentScheduleEntryList;
    //        
    //        if (CollectionUtils.isEmpty(paymentScheduleEntryList)) {
    //            return;
    //        }
    //        for (PaymentScheduleEntry paymentScheduleEntry : paymentScheduleEntryList) {
    //            AssertUtils.isTrue(
    //                    this.scheduleType != null && this.scheduleType.equals(paymentScheduleEntry.getScheduleType()),
    //                    "paymentScheduleEntry scheduleType not matched.ScheduleType:{}",
    //                    new Object[] { paymentScheduleEntry.getScheduleType() });
    //            AssertUtils.isTrue(this.period != null && this.period.equals(paymentScheduleEntry.getPeriod()),
    //                    "paymentScheduleEntry period not matched.period:{}",
    //                    paymentScheduleEntry.getPeriod());
    //            
    //            paymentScheduleEntry.setPaymentSchedule(this);
    //        }
    //        this.paymentScheduleEntryMap.addAll(paymentScheduleEntryList);
    //    }
    //    
    //    /**
    //     * 压入还款计划分项
    //     *<功能详细描述>
    //     * @param paymentScheduleEntry [参数说明]
    //     * 
    //     * @return void [返回类型说明]
    //     * @exception throws [异常类型] [异常说明]
    //     * @see [类、类#方法、类#成员]
    //    */
    //    public void addPaymentScheduleEntry(PaymentScheduleEntry paymentScheduleEntry) {
    //        AssertUtils.notNull(paymentScheduleEntry, "paymentScheduleEntry is null.");
    //        AssertUtils.isTrue(
    //                this.scheduleType != null && this.scheduleType.equals(paymentScheduleEntry.getScheduleType()),
    //                "paymentScheduleEntry scheduleType not matched.ScheduleType:{}",
    //                new Object[] { paymentScheduleEntry.getScheduleType() });
    //        AssertUtils.isTrue(this.period != null && this.period.equals(paymentScheduleEntry.getPeriod()),
    //                "paymentScheduleEntry period not matched.period:{}",
    //                paymentScheduleEntry.getPeriod());
    //        AssertUtils.isTrue(!this.paymentScheduleEntryMap.containsKey(paymentScheduleEntry.getFeeItem()),
    //                "feeItem is exist.feeItem:{}",
    //                new Object[] { paymentScheduleEntry.getFeeItem() });
    //        
    //        paymentScheduleEntry.setPaymentSchedule(this);
    //        this.paymentScheduleEntryList.add(paymentScheduleEntry);
    //        this.paymentScheduleEntryMap.add(paymentScheduleEntry);
    //    }
    //    
    //    /**
    //     * @return 返回 paymentScheduleEntryMap
    //     */
    //    public ScheduleEntryAssociateMap<PaymentScheduleEntry> getPaymentScheduleEntryMap() {
    //        return this.paymentScheduleEntryMap;
    //    }
    
    /**
     * @param 对paymentScheduleEntryList进行赋值
     */
    public void setPaymentScheduleEntryList(
            List<PaymentScheduleEntry> paymentScheduleEntryList) {
        AssertUtils.notNull(paymentScheduleEntryList,
                "paymentScheduleEntryList is null.");
        
        this.paymentScheduleEntryMap.clear();
        this.paymentScheduleEntryList = paymentScheduleEntryList;
        
        if (CollectionUtils.isEmpty(paymentScheduleEntryList)) {
            return;
        }
        for (PaymentScheduleEntry paymentScheduleEntry : paymentScheduleEntryList) {
            AssertUtils.isTrue(
                    this.scheduleType != null && this.scheduleType
                            .equals(paymentScheduleEntry.getScheduleType()),
                    "paymentScheduleEntry scheduleType not matched.ScheduleType:{}",
                    new Object[] { paymentScheduleEntry.getScheduleType() });
            AssertUtils.isTrue(
                    this.period != null && this.period
                            .equals(paymentScheduleEntry.getPeriod()),
                    "paymentScheduleEntry period not matched.period:{}",
                    paymentScheduleEntry.getPeriod());
            
            paymentScheduleEntry.setPaymentSchedule(this);
            this.paymentScheduleEntryMap.put(paymentScheduleEntry.getFeeItem(),
                    paymentScheduleEntry);
        }
    }
    
    /**
     * 压入还款计划分项
     *<功能详细描述>
     * @param paymentScheduleEntry [参数说明]
     * 
     * @return void [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
    */
    public void addPaymentScheduleEntry(
            PaymentScheduleEntry paymentScheduleEntry) {
        AssertUtils.notNull(paymentScheduleEntry,
                "paymentScheduleEntry is null.");
        AssertUtils.isTrue(
                this.scheduleType != null && this.scheduleType
                        .equals(paymentScheduleEntry.getScheduleType()),
                "paymentScheduleEntry scheduleType not matched.ScheduleType:{}",
                new Object[] { paymentScheduleEntry.getScheduleType() });
        AssertUtils.isTrue(
                this.period != null
                        && this.period.equals(paymentScheduleEntry.getPeriod()),
                "paymentScheduleEntry period not matched.period:{}",
                paymentScheduleEntry.getPeriod());
        AssertUtils.isTrue(
                !this.paymentScheduleEntryMap
                        .containsKey(paymentScheduleEntry.getFeeItem()),
                "feeItem is exist.feeItem:{}",
                new Object[] { paymentScheduleEntry.getFeeItem() });
        
        paymentScheduleEntry.setPaymentSchedule(this);
        this.paymentScheduleEntryList.add(paymentScheduleEntry);
        this.paymentScheduleEntryMap.put(paymentScheduleEntry.getFeeItem(),
                paymentScheduleEntry);
    }
    
    /**
     * @return 返回 paymentScheduleEntryMap
     */
    public Map<FeeItemEnum, PaymentScheduleEntry> getPaymentScheduleEntryMap() {
        return paymentScheduleEntryMap;
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
     * @return 返回 settle
     */
    public boolean isSettle() {
        return settle;
    }
    
    /**
     * @param 对settle进行赋值
     */
    public void setSettle(boolean settle) {
        this.settle = settle;
    }
    
    /**
     * @return 返回 lastRepayDate
     */
    public Date getLastRepayDate() {
        return lastRepayDate;
    }
    
    /**
     * @param 对lastRepayDate进行赋值
     */
    public void setLastRepayDate(Date lastRepayDate) {
        this.lastRepayDate = lastRepayDate;
    }
    
    /**
     * @return 返回 settleRepayDate
     */
    public Date getSettleRepayDate() {
        return settleRepayDate;
    }
    
    /**
     * @param 对settleRepayDate进行赋值
     */
    public void setSettleRepayDate(Date settleRepayDate) {
        this.settleRepayDate = settleRepayDate;
    }
    
    /**
     * @return 返回 settleProcessDate
     */
    public Date getSettleProcessDate() {
        return settleProcessDate;
    }
    
    /**
     * @param 对settleProcessDate进行赋值
     */
    public void setSettleProcessDate(Date settleProcessDate) {
        this.settleProcessDate = settleProcessDate;
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
     * @return 返回 overduePrincipal
     */
    public BigDecimal getOverduePrincipal() {
        return overduePrincipal;
    }
    
    /**
     * @param 对overduePrincipal进行赋值
     */
    public void setOverduePrincipal(BigDecimal overduePrincipal) {
        this.overduePrincipal = overduePrincipal;
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
                    "period");
        }
    }
    
    /**
     * @param obj
     * @return
     */
    @Override
    public boolean equals(Object obj) {
        if (obj == null || !(obj instanceof PaymentSchedule)) {
            return false;
        }
        PaymentSchedule other = (PaymentSchedule) obj;
        if (!StringUtils.isEmpty(this.id)
                && !StringUtils.isEmpty(other.getId())) {
            //两者均有id的情形
            return ObjectUtils.equals(this, other, "id");
        } else {
            return ObjectUtils.equals(this, other, "scheduleType", "period");
        }
    }
}