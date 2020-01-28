/*
 * 描          述:  <描述>
 * 修  改   人:  Administrator
 * 修改时间:  2017年6月25日
 * <修改描述:>
 */
package com.tx.local.loanaccount.model.settledetail;

import java.beans.PropertyDescriptor;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import javax.persistence.Id;

import org.springframework.beans.BeanWrapper;
import org.springframework.beans.PropertyAccessorFactory;

import com.tx.core.exceptions.util.AssertUtils;
import com.tx.local.basicdata.model.FeeItemCfg;
import com.tx.local.basicdata.model.FeeItemEnum;
import com.tx.local.basicdata.model.IDCardTypeEnum;
import com.tx.local.loanaccount.handle.PaymentScheduleHandler;
import com.tx.local.loanaccount.model.LoanAccount;
import com.tx.local.loanaccount.model.LoanAccountCategoryEnum;
import com.tx.local.loanaccount.model.LoanAccountSettleDetail;
import com.tx.local.loanaccount.model.LoanAccountTypeEnum;
import com.tx.local.loanaccount.model.PaymentSchedule;
import com.tx.local.loanaccount.model.PaymentScheduleEntry;
import com.tx.local.loanaccount.model.ScheduleTypeEnum;

/**
  * 本金结余详情<br/>
  * <功能详细描述>
  * 
  * @author  Administrator
  * @version  [版本号, 2017年6月25日]
  * @see  [相关类/方法]
  * @since  [产品/模块版本]
  */
public class BaseLoanAccountSettleDetail implements LoanAccountSettleDetail {
    
    /** 主键： */
    @Id
    protected String id;
    
    /** 分类 */
    protected LoanAccountCategoryEnum category;
    
    /** 贷款账户类型:  common */
    protected LoanAccountTypeEnum loanAccountType;
    
    /** 贷款金额：不变  */
    protected BigDecimal loanAmount;
    
    /** 客户id */
    protected String clientId;
    
    /** 贷款账户对应客户账户 */
    protected String clientAccountId;
    
    /** 结清日期 */
    protected Date settleDate;
    
    /** 客户姓名：找到客户实例后填入极少情况发生变化不变 */
    protected String clientName;
    
    /** 证 件类型：根据客户实例填入极少情况发生变化clientIdCardType[IDCardType?放名还是code还是id?]*/
    protected IDCardTypeEnum clientIdCardType;
    
    /** 证件号码:找到客户实例后填入 不变 */
    protected String clientIdCardNumber;
    
    /** 客户信用信息id */
    protected String creditInfoId;
    
    /** 信用信息Tag版本 */
    protected String creditInfoVersion;
    
    /** 是否正在发生委外 */
    protected boolean outsource = false;
    
    /** 对应委外分配记录id */
    protected String outsourceAssignRecordId;
    
    /** 委外外包佣金比率 */
    protected BigDecimal outsourcePercentage;
    
    /** 本金结余映射 */
    protected Map<ScheduleTypeEnum, BigDecimal> principalBalanceMap;
    
    /** 本金结余映射 */
    protected Map<ScheduleTypeEnum, BigDecimal> exemptAbleSumMap;
    
    /** 所有费用的 应 + 豁 - 实 与费用的映射 */
    protected Map<ScheduleTypeEnum, Map<String, Map<FeeItemEnum, BigDecimal>>> sumMap;
    
    /** 各费用项的应收金额映射 */
    protected Map<ScheduleTypeEnum, Map<String, Map<FeeItemEnum, BigDecimal>>> receivableSumMap;
    
    /** 豁免金额映射 */
    protected Map<ScheduleTypeEnum, Map<String, Map<FeeItemEnum, BigDecimal>>> exemptSumMap;
    
    /** 应收金额映射 */
    protected Map<ScheduleTypeEnum, Map<String, Map<FeeItemEnum, BigDecimal>>> actualReceivedSumMap;
    
    /** <默认构造函数> */
    public BaseLoanAccountSettleDetail() {
        super();
    }
    
    /** <默认构造函数> */
    public BaseLoanAccountSettleDetail(LoanAccount loanAccount,
            PaymentScheduleHandler handler, Date settleDate) {
        super();
        init(loanAccount, handler, settleDate);
    }
    
    /**
     * 初始化贷款账户<br/>
     * <功能详细描述>
     * @param loanAccount
     * @param handler
     * @param repayDate [参数说明]
     * 
     * @return void [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    protected void init(LoanAccount loanAccount, PaymentScheduleHandler handler,
            Date settleDate) {
        AssertUtils.notNull(loanAccount, "loanAccount is null.");
        AssertUtils.notNull(handler, "handler is null.");
        AssertUtils.notNull(settleDate, "settleDate is null.");
        
        //写入贷款账户信息
        this.settleDate = settleDate;
        BeanWrapper sourceBW = PropertyAccessorFactory
                .forBeanPropertyAccess(loanAccount);
        BeanWrapper thisBW = PropertyAccessorFactory
                .forBeanPropertyAccess(this);
        for (PropertyDescriptor pd : sourceBW.getPropertyDescriptors()) {
            String propertyName = pd.getName();
            if (!sourceBW.isReadableProperty(propertyName)
                    || !thisBW.isWritableProperty(propertyName)) {
                continue;
            }
            thisBW.setPropertyValue(propertyName,
                    sourceBW.getPropertyValue(propertyName));
        }
        
        this.principalBalanceMap = new HashMap<>();
        Map<FeeItemEnum, FeeItemCfg> feeItemCfgMap = handler.getFeeItemCfgMap();
        
        //遍历还款计划
        this.sumMap = new HashMap<>();
        this.receivableSumMap = new HashMap<>();
        this.exemptSumMap = new HashMap<>();
        this.actualReceivedSumMap = new HashMap<>();
        this.exemptAbleSumMap = new HashMap<>();
        
        for (Entry<ScheduleTypeEnum, Map<String, PaymentSchedule>> entryTemp : handler
                .getPaymentScheduleMap().entrySet()) {
            ScheduleTypeEnum scheduleType = entryTemp.getKey();
            if (!this.sumMap.containsKey(scheduleType)) {
                this.sumMap.put(scheduleType, new HashMap<>());
                this.receivableSumMap.put(scheduleType, new HashMap<>());
                this.exemptSumMap.put(scheduleType, new HashMap<>());
                this.actualReceivedSumMap.put(scheduleType, new HashMap<>());
                this.exemptAbleSumMap.put(scheduleType, BigDecimal.ZERO);
            }
            
            BigDecimal principalBalance = BigDecimal.ZERO;
            for (Entry<String, PaymentSchedule> valueEntry : entryTemp
                    .getValue().entrySet()) {
                String period = valueEntry.getKey();
                PaymentSchedule ps = valueEntry.getValue();
                if (!this.sumMap.containsKey(period)) {
                    this.sumMap.get(scheduleType).put(period, new HashMap<>());
                    this.receivableSumMap.get(scheduleType).put(period,
                            new HashMap<>());
                    this.exemptSumMap.get(scheduleType).put(period,
                            new HashMap<>());
                    this.actualReceivedSumMap.get(scheduleType).put(period,
                            new HashMap<>());
                }
                
                for (PaymentScheduleEntry pse : ps
                        .getPaymentScheduleEntryList()) {
                    if (pse.getReceivableAmount()
                            .add(pse.getExemptAmount())
                            .compareTo(BigDecimal.ZERO) < 0) {
                        this.sumMap.get(scheduleType).get(period).put(
                                pse.getFeeItem(), BigDecimal.ZERO);
                    } else {
                        this.sumMap.get(scheduleType).get(period).put(
                                pse.getFeeItem(),
                                pse.getReceivableAmount()
                                        .add(pse.getExemptAmount())
                                        .subtract(
                                                pse.getActualReceivedAmount()));
                    }
                    
                    this.receivableSumMap.get(scheduleType).get(period).put(
                            pse.getFeeItem(), pse.getReceivableAmount());
                    this.exemptSumMap.get(scheduleType).get(period).put(
                            pse.getFeeItem(), pse.getExemptAmount());
                    this.actualReceivedSumMap.get(scheduleType).get(period).put(
                            pse.getFeeItem(), pse.getActualReceivedAmount());
                    
                    if (feeItemCfgMap.get(pse.getFeeItem()).isPrincipal()) {
                        principalBalance = principalBalance
                                .add(pse.getReceivableAmount()
                                        .add(pse.getExemptAmount())
                                        .subtract(
                                                pse.getActualReceivedAmount()));
                    }
                    if (feeItemCfgMap.get(pse.getFeeItem()).isExemptAble()) {
                        this.exemptAbleSumMap.put(scheduleType,
                                this.exemptAbleSumMap.get(scheduleType).add(pse
                                        .getReceivableAmount()
                                        .add(pse.getExemptAmount())
                                        .subtract(pse
                                                .getActualReceivedAmount())));
                    }
                }
            }
            this.principalBalanceMap.put(scheduleType, principalBalance);
        }
        
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
     * @return 返回 category
     */
    public LoanAccountCategoryEnum getCategory() {
        return category;
    }
    
    /**
     * @param 对category进行赋值
     */
    public void setCategory(LoanAccountCategoryEnum category) {
        this.category = category;
    }
    
    /**
     * @return 返回 loanAccountType
     */
    public LoanAccountTypeEnum getLoanAccountType() {
        return loanAccountType;
    }
    
    /**
     * @param 对loanAccountType进行赋值
     */
    public void setLoanAccountType(LoanAccountTypeEnum loanAccountType) {
        this.loanAccountType = loanAccountType;
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
     * @return 返回 clientAccountId
     */
    public String getClientAccountId() {
        return clientAccountId;
    }
    
    /**
     * @param 对clientAccountId进行赋值
     */
    public void setClientAccountId(String clientAccountId) {
        this.clientAccountId = clientAccountId;
    }
    
    /**
     * @return 返回 clientName
     */
    public String getClientName() {
        return clientName;
    }
    
    /**
     * @param 对clientName进行赋值
     */
    public void setClientName(String clientName) {
        this.clientName = clientName;
    }
    
    /**
     * @return 返回 clientIdCardType
     */
    public IDCardTypeEnum getClientIdCardType() {
        return clientIdCardType;
    }
    
    /**
     * @param 对clientIdCardType进行赋值
     */
    public void setClientIdCardType(IDCardTypeEnum clientIdCardType) {
        this.clientIdCardType = clientIdCardType;
    }
    
    /**
     * @return 返回 clientIdCardNumber
     */
    public String getClientIdCardNumber() {
        return clientIdCardNumber;
    }
    
    /**
     * @param 对clientIdCardNumber进行赋值
     */
    public void setClientIdCardNumber(String clientIdCardNumber) {
        this.clientIdCardNumber = clientIdCardNumber;
    }
    
    /**
     * @return 返回 creditInfoId
     */
    public String getCreditInfoId() {
        return creditInfoId;
    }
    
    /**
     * @param 对creditInfoId进行赋值
     */
    public void setCreditInfoId(String creditInfoId) {
        this.creditInfoId = creditInfoId;
    }
    
    /**
     * @return 返回 creditInfoVersion
     */
    public String getCreditInfoVersion() {
        return creditInfoVersion;
    }
    
    /**
     * @param 对creditInfoVersion进行赋值
     */
    public void setCreditInfoVersion(String creditInfoVersion) {
        this.creditInfoVersion = creditInfoVersion;
    }
    
    /**
     * @param 对loanAmount进行赋值
     */
    public void setLoanAmount(BigDecimal loanAmount) {
        this.loanAmount = loanAmount;
    }
    
    /**
     * @return 返回 outsource
     */
    public boolean isOutsource() {
        return outsource;
    }
    
    /**
     * @param 对outsource进行赋值
     */
    public void setOutsource(boolean outsource) {
        this.outsource = outsource;
    }
    
    /**
     * @return 返回 outsourceAssignRecordId
     */
    public String getOutsourceAssignRecordId() {
        return outsourceAssignRecordId;
    }
    
    /**
     * @param 对outsourceAssignRecordId进行赋值
     */
    public void setOutsourceAssignRecordId(String outsourceAssignRecordId) {
        this.outsourceAssignRecordId = outsourceAssignRecordId;
    }
    
    /**
     * @return 返回 outsourcePercentage
     */
    public BigDecimal getOutsourcePercentage() {
        return outsourcePercentage;
    }
    
    /**
     * @param 对outsourcePercentage进行赋值
     */
    public void setOutsourcePercentage(BigDecimal outsourcePercentage) {
        this.outsourcePercentage = outsourcePercentage;
    }
    
    /**
     * @param 对principalBalanceMap进行赋值
     */
    public void setPrincipalBalanceMap(
            Map<ScheduleTypeEnum, BigDecimal> principalBalanceMap) {
        this.principalBalanceMap = principalBalanceMap;
    }
    
    /**
     * @param 对sumMap进行赋值
     */
    public void setSumMap(
            Map<ScheduleTypeEnum, Map<String, Map<FeeItemEnum, BigDecimal>>> sumMap) {
        this.sumMap = sumMap;
    }
    
    /**
     * @param 对receivableSumMap进行赋值
     */
    public void setReceivableSumMap(
            Map<ScheduleTypeEnum, Map<String, Map<FeeItemEnum, BigDecimal>>> receivableSumMap) {
        this.receivableSumMap = receivableSumMap;
    }
    
    /**
     * @param 对exemptSumMap进行赋值
     */
    public void setExemptSumMap(
            Map<ScheduleTypeEnum, Map<String, Map<FeeItemEnum, BigDecimal>>> exemptSumMap) {
        this.exemptSumMap = exemptSumMap;
    }
    
    /**
     * @param 对actualReceivedSumMap进行赋值
     */
    public void setActualReceivedSumMap(
            Map<ScheduleTypeEnum, Map<String, Map<FeeItemEnum, BigDecimal>>> actualReceivedSumMap) {
        this.actualReceivedSumMap = actualReceivedSumMap;
    }
    
    /**
     * @return
     */
    @Override
    public BigDecimal getLoanAmount() {
        return this.loanAmount;
    }
    
    public Map<ScheduleTypeEnum, BigDecimal> getPrincipalBalanceMap() {
        return this.principalBalanceMap;
    }
    
    /**
     * @param scheuleType
     * @return
     */
    @Override
    public BigDecimal getPrincipalBalance(ScheduleTypeEnum scheuleType) {
        AssertUtils.notNull(scheuleType, "scheuleType is null.");
        
        AssertUtils.notNull(this.principalBalanceMap,
                "principalBalanceMap is null.");
        AssertUtils.notNull(this.principalBalanceMap.get(scheuleType),
                "principalBalanceMap.scheduleType:{} is null.",
                new Object[] { scheuleType });
        
        BigDecimal principalBalance = this.principalBalanceMap.get(scheuleType);
        
        return principalBalance;
    }
    
    /**
     * @return
     */
    @Override
    public Map<ScheduleTypeEnum, Map<String, Map<FeeItemEnum, BigDecimal>>> getSumMap() {
        return this.sumMap;
    }
    
    /**
     * @param scheuleType
     * @param feeItem
     * @return
     */
    @Override
    public BigDecimal getSum(ScheduleTypeEnum scheuleType,
            FeeItemEnum feeItem) {
        AssertUtils.notNull(scheuleType, "scheuleType is null.");
        AssertUtils.notNull(feeItem, "feeItem is null.");
        
        AssertUtils.notNull(this.sumMap, "sumMap is null.");
        AssertUtils.notNull(this.sumMap.get(scheuleType),
                "sumMap.scheduleType:{} is null.",
                new Object[] { scheuleType });
        
        BigDecimal totalSum = BigDecimal.ZERO;
        for (Map<FeeItemEnum, BigDecimal> periodMap : this.sumMap
                .get(scheuleType).values()) {
            if (!periodMap.containsKey(feeItem)) {
                continue;
            }
            totalSum = totalSum.add(periodMap.get(feeItem));
        }
        
        return totalSum;
    }
    
    /**
     * @param scheuleType
     * @return
     */
    @Override
    public BigDecimal getTotalSum(ScheduleTypeEnum scheuleType) {
        AssertUtils.notNull(scheuleType, "scheuleType is null.");
        
        AssertUtils.notNull(this.sumMap, "sumMap is null.");
        AssertUtils.notNull(this.sumMap.get(scheuleType),
                "sumMap.scheduleType:{} is null.",
                new Object[] { scheuleType });
        
        BigDecimal totalSum = BigDecimal.ZERO;
        for (Map<FeeItemEnum, BigDecimal> periodMap : this.sumMap
                .get(scheuleType).values()) {
            for (BigDecimal amountTemp : periodMap.values()) {
                if (amountTemp.compareTo(BigDecimal.ZERO) < 0) {
                    //跳过：超额还款、注销后回款.  等可能出现实收 > 应收的场景
                    continue;
                }
                totalSum = totalSum.add(amountTemp);
            }
        }
        
        return totalSum;
    }
    
    /**
     * @return
     */
    @Override
    public Map<ScheduleTypeEnum, Map<String, Map<FeeItemEnum, BigDecimal>>> getReceivableSumMap() {
        return this.receivableSumMap;
    }
    
    /**
     * @param scheuleType
     * @param feeItem
     * @return
     */
    @Override
    public BigDecimal getReceivableSum(ScheduleTypeEnum scheuleType,
            FeeItemEnum feeItem) {
        AssertUtils.notNull(scheuleType, "scheuleType is null.");
        AssertUtils.notNull(feeItem, "feeItem is null.");
        
        AssertUtils.notNull(this.receivableSumMap, "receivableSumMap is null.");
        AssertUtils.notNull(this.receivableSumMap.get(scheuleType),
                "receivableSumMap.scheduleType:{} is null.",
                new Object[] { scheuleType });
        
        BigDecimal totalSum = BigDecimal.ZERO;
        for (Map<FeeItemEnum, BigDecimal> periodMap : this.receivableSumMap
                .get(scheuleType).values()) {
            if (!periodMap.containsKey(feeItem)) {
                continue;
            }
            totalSum = totalSum.add(periodMap.get(feeItem));
        }
        
        return totalSum;
    }
    
    /**
     * @param scheuleType
     * @return
     */
    @Override
    public BigDecimal getReceivableTotalSum(ScheduleTypeEnum scheuleType) {
        AssertUtils.notNull(scheuleType, "scheuleType is null.");
        
        AssertUtils.notNull(this.receivableSumMap, "receivableSumMap is null.");
        AssertUtils.notNull(this.receivableSumMap.get(scheuleType),
                "receivableSumMap.scheduleType:{} is null.",
                new Object[] { scheuleType });
        
        BigDecimal totalSum = BigDecimal.ZERO;
        for (Map<FeeItemEnum, BigDecimal> periodMap : this.receivableSumMap
                .get(scheuleType).values()) {
            for (BigDecimal amountTemp : periodMap.values()) {
                totalSum = totalSum.add(amountTemp);
            }
        }
        
        return totalSum;
    }
    
    /**
     * @return
     */
    @Override
    public Map<ScheduleTypeEnum, Map<String, Map<FeeItemEnum, BigDecimal>>> getExemptSumMap() {
        return this.exemptSumMap;
    }
    
    /**
     * @param scheuleType
     * @param feeItem
     * @return
     */
    @Override
    public BigDecimal getExemptSum(ScheduleTypeEnum scheuleType,
            FeeItemEnum feeItem) {
        AssertUtils.notNull(scheuleType, "scheuleType is null.");
        AssertUtils.notNull(feeItem, "feeItem is null.");
        
        AssertUtils.notNull(this.exemptSumMap, "exemptSumMap is null.");
        AssertUtils.notNull(this.exemptSumMap.get(scheuleType),
                "exemptSumMap.scheduleType:{} is null.",
                new Object[] { scheuleType });
        
        BigDecimal totalSum = BigDecimal.ZERO;
        for (Map<FeeItemEnum, BigDecimal> periodMap : this.exemptSumMap
                .get(scheuleType).values()) {
            if (!periodMap.containsKey(feeItem)) {
                continue;
            }
            totalSum = totalSum.add(periodMap.get(feeItem));
        }
        
        return totalSum;
    }
    
    /**
     * @param scheuleType
     * @return
     */
    @Override
    public BigDecimal getExemptTotalSum(ScheduleTypeEnum scheuleType) {
        AssertUtils.notNull(scheuleType, "scheuleType is null.");
        
        AssertUtils.notNull(this.exemptSumMap, "exemptSumMap is null.");
        AssertUtils.notNull(this.exemptSumMap.get(scheuleType),
                "exemptSumMap.scheduleType:{} is null.",
                new Object[] { scheuleType });
        
        BigDecimal totalSum = BigDecimal.ZERO;
        for (Map<FeeItemEnum, BigDecimal> periodMap : this.exemptSumMap
                .get(scheuleType).values()) {
            for (BigDecimal amountTemp : periodMap.values()) {
                totalSum = totalSum.add(amountTemp);
            }
        }
        
        return totalSum;
    }
    
    /**
     * @return
     */
    @Override
    public Map<ScheduleTypeEnum, Map<String, Map<FeeItemEnum, BigDecimal>>> getActualReceivedSumMap() {
        return this.actualReceivedSumMap;
    }
    
    /**
     * @param scheuleType
     * @param feeItem
     * @return
     */
    @Override
    public BigDecimal getActualReceivedSum(ScheduleTypeEnum scheuleType,
            FeeItemEnum feeItem) {
        AssertUtils.notNull(scheuleType, "scheuleType is null.");
        AssertUtils.notNull(feeItem, "feeItem is null.");
        
        AssertUtils.notNull(this.actualReceivedSumMap,
                "actualReceivedSumMap is null.");
        AssertUtils.notNull(this.actualReceivedSumMap.get(scheuleType),
                "actualReceivedSumMap.scheduleType:{} is null.",
                new Object[] { scheuleType });
        
        BigDecimal totalSum = BigDecimal.ZERO;
        for (Map<FeeItemEnum, BigDecimal> periodMap : this.actualReceivedSumMap
                .get(scheuleType).values()) {
            if (!periodMap.containsKey(feeItem)) {
                continue;
            }
            totalSum = totalSum.add(periodMap.get(feeItem));
        }
        
        return totalSum;
    }
    
    /**
     * @param scheuleType
     * @return
     */
    @Override
    public BigDecimal getActualReceivedTotalSum(ScheduleTypeEnum scheuleType) {
        AssertUtils.notNull(scheuleType, "scheuleType is null.");
        
        AssertUtils.notNull(this.actualReceivedSumMap,
                "actualReceivedSumMap is null.");
        AssertUtils.notNull(this.actualReceivedSumMap.get(scheuleType),
                "actualReceivedSumMap.scheduleType:{} is null.",
                new Object[] { scheuleType });
        
        BigDecimal totalSum = BigDecimal.ZERO;
        for (Map<FeeItemEnum, BigDecimal> periodMap : this.actualReceivedSumMap
                .get(scheuleType).values()) {
            for (BigDecimal amountTemp : periodMap.values()) {
                totalSum = totalSum.add(amountTemp);
            }
        }
        
        return totalSum;
    }
    
    /**
     * @return
     */
    @Override
    public BigDecimal getSettleSum() {
        BigDecimal settleSum = getTotalSum(ScheduleTypeEnum.MAIN);
        return settleSum;
    }
    
    /**
     * @return
     */
    @Override
    public BigDecimal getWBYJSum() {
        BigDecimal settleSum = getSettleSum();
        if (settleSum.compareTo(BigDecimal.ZERO) > 0 && this.outsource
                && this.outsourcePercentage.compareTo(BigDecimal.ZERO) > 0) {
            BigDecimal wbyjSum = settleSum.multiply(this.outsourcePercentage)
                    .setScale(2, RoundingMode.UP);
            return wbyjSum;
        }
        return BigDecimal.ZERO;
    }
    
    /**
     * @return
     */
    @Override
    public BigDecimal getZXHHKSum() {
        BigDecimal sum = BigDecimal.ZERO;
        sum = sum.add(getActualReceivedSum(ScheduleTypeEnum.MAIN,
                FeeItemEnum.DK_ZXHHK));
        sum = sum.add(getActualReceivedSum(ScheduleTypeEnum.MAIN,
                FeeItemEnum.ZX_ZXHHK));
        return sum;
    }
    
    /**
     * @return
     */
    @Override
    public BigDecimal getTotal() {
        BigDecimal total = getSettleSum().add(getWBYJSum())
                .subtract(getZXHHKSum());
        return total;
    }
    
    /**
     * @return
     */
    @Override
    public Map<ScheduleTypeEnum, BigDecimal> getExemptAbleSumMap() {
        return this.exemptAbleSumMap;
    }
    
    /**
     * @param scheuleType
     * @return
     */
    @Override
    public BigDecimal getExemptAbleSum(ScheduleTypeEnum scheuleType) {
        AssertUtils.notNull(scheuleType, "scheuleType is null.");
        BigDecimal exemptAbleSum = this.exemptAbleSumMap.get(scheuleType);
        return exemptAbleSum;
    }
    
    /**
     * @param scheuleType
     * @return
     */
    @Override
    public BigDecimal getMainExemptAbleSum() {
        BigDecimal exemptAbleSum = this.exemptAbleSumMap
                .get(ScheduleTypeEnum.MAIN);
        return exemptAbleSum == null ? BigDecimal.ZERO : exemptAbleSum;
    }
    
    /**
     * @return
     */
    @Override
    public Date getSettleDate() {
        return this.settleDate;
    }
}
