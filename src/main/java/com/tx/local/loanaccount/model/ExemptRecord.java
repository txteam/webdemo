/*
 * 描          述:  <描述>
 * 修  改   人:  Administrator
 * 修改时间:  2014年5月18日
 * <修改描述:>
 */
package com.tx.local.loanaccount.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.apache.commons.collections4.CollectionUtils;

import com.tx.core.exceptions.util.AssertUtils;
import com.tx.local.basicdata.model.FeeItemEnum;

/**
 * 豁免记录<br/>
 * <功能详细描述>
 * 
 * @author  Administrator
 * @version  [版本号, 2014年5月18日]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
@Entity
@Table(name = "la_exempt_record")
public class ExemptRecord implements Serializable, ScheduleAssociate {
    
    /** 注释内容 */
    private static final long serialVersionUID = -6031308364401303434L;
    
    /** 主键 */
    @Id
    private String id;
    
    /** 对应交易 */
    @ManyToOne
    @Column(name = "tradingRecordId")
    private LATradingRecord tradingRecord;
    
    /** 关联还款计划分项主键 */
    @ManyToOne
    @Column(name = "paymentScheduleId")
    private PaymentSchedule paymentSchedule;
    
    /** 贷款单账户唯一键 */
    private String loanAccountId;
    
    /** 期数 */
    private String period;
    
    /** 还款类型 */
    private ScheduleTypeEnum scheduleType;
    
    /** 原金额 */
    private BigDecimal sourceSum;
    
    /** 金额 */
    private BigDecimal sum;
    
    /** 目标金额 */
    private BigDecimal targetSum;
    
    /** 是否撤销 */
    private boolean revoked;
    
    /** 撤销时间 */
    private Date revokeDate;
    
    /** 最后更新时间 */
    private Date lastUpdateDate;
    
    /** 创建时间 */
    private Date createDate;
    
    /** 豁免记录分项 */
    @OneToMany
    private List<ExemptRecordEntry> exemptRecordEntryList;
    
    /** 豁免记录分项映射 */
    @OneToMany
    private final Map<FeeItemEnum, ExemptRecordEntry> exemptRecordEntryMap = new HashMap<FeeItemEnum, ExemptRecordEntry>();
    
    /** <默认构造函数> */
    public ExemptRecord(String id) {
        super();
        this.id = id;
    }
    
    /** <默认构造函数> */
    public ExemptRecord() {
        super();
    }
    
    /**
     * @return 返回 exemptRecordEntryList
     */
    public List<ExemptRecordEntry> getExemptRecordEntryList() {
        return exemptRecordEntryList;
    }
    
    /**
     * @param 对exemptRecordEntryList进行赋值
     */
    public void setExemptRecordEntryList(List<ExemptRecordEntry> exemptRecordEntryList) {
        AssertUtils.notNull(exemptRecordEntryList, "exemptRecordEntryList is null.");
        
        this.exemptRecordEntryMap.clear();
        this.exemptRecordEntryList = exemptRecordEntryList;
        
        if (CollectionUtils.isEmpty(exemptRecordEntryList)) {
            return;
        }
        for (ExemptRecordEntry exemptRecordEntry : exemptRecordEntryList) {
            AssertUtils.isTrue(
                    this.scheduleType != null && this.scheduleType.equals(exemptRecordEntry.getScheduleType()),
                    "exemptRecordEntry scheduleType not matched.ScheduleType:{}",
                    new Object[] { exemptRecordEntry.getScheduleType() });
            AssertUtils.isTrue(this.period != null && this.period.equals(exemptRecordEntry.getPeriod()),
                    "exemptRecordEntry period not matched.period:{}",
                    exemptRecordEntry.getPeriod());
            
            exemptRecordEntry.setExemptRecord(this);
            this.exemptRecordEntryMap.put(exemptRecordEntry.getFeeItem(), exemptRecordEntry);
        }
        //this.exemptRecordEntryMap.addAll(exemptRecordEntryList);
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
    public void addExemptRecordEntry(ExemptRecordEntry exemptRecordEntry) {
        AssertUtils.notNull(exemptRecordEntry, "exemptRecordEntry is null.");
        AssertUtils.isTrue(this.scheduleType != null && this.scheduleType.equals(exemptRecordEntry.getScheduleType()),
                "exemptRecordEntry scheduleType not matched.ScheduleType:{}",
                new Object[] { exemptRecordEntry.getScheduleType() });
        AssertUtils.isTrue(this.period != null && this.period.equals(exemptRecordEntry.getPeriod()),
                "exemptRecordEntry period not matched.period:{}",
                exemptRecordEntry.getPeriod());
        AssertUtils.isTrue(!this.exemptRecordEntryMap.containsKey(exemptRecordEntry.getFeeItem()),
                "feeItem is exist.feeItem:{}",
                new Object[] { exemptRecordEntry.getFeeItem() });
        
        exemptRecordEntry.setExemptRecord(this);
        this.exemptRecordEntryList.add(exemptRecordEntry);
        //this.exemptRecordEntryMap.add(exemptRecordEntry);
        this.exemptRecordEntryMap.put(exemptRecordEntry.getFeeItem(), exemptRecordEntry);
    }
    
    //    /**
    //     * @return 返回 exemptRecordEntryMap
    //     */
    //    public ScheduleEntryAssociateMap<ExemptRecordEntry> getExemptRecordEntryMap() {
    //        return exemptRecordEntryMap;
    //    }
    
    /**
     * @return 返回 exemptRecordEntryMap
     */
    public Map<FeeItemEnum, ExemptRecordEntry> getExemptRecordEntryMap() {
        return exemptRecordEntryMap;
    }
    
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
     * @return 返回 sourceSum
     */
    public BigDecimal getSourceSum() {
        return sourceSum;
    }
    
    /**
     * @param 对sourceSum进行赋值
     */
    public void setSourceSum(BigDecimal sourceSum) {
        this.sourceSum = sourceSum;
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
     * @return 返回 targetSum
     */
    public BigDecimal getTargetSum() {
        return targetSum;
    }
    
    /**
     * @param 对targetSum进行赋值
     */
    public void setTargetSum(BigDecimal targetSum) {
        this.targetSum = targetSum;
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
    
    //  /** 是否注销:  账户注销前，该字段为空 */
    //    private boolean writeOff;
    //    
    //    /** 是否是提前结清豁免： */
    //    private boolean earlySettle;
    //
    //    /** 豁免总金额(为豁免分项之和)： */
    //    private BigDecimal exemptSumIrr;
    //    
    //    /** 豁免原金额 */
    //    private BigDecimal sourceExemptSumIrr;
    //    
    //    /** 调整后的豁免金额，要看exempt中记录的是增量还是目标值，如果为目标值，则与该值一致 */
    //    private BigDecimal targetExemptSumIrr;
    //    
    //    /** 备注:  */
    //    private String summary;
}
