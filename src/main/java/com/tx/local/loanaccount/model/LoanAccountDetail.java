/*
 * 描          述:  <描述>
 * 修  改   人:  Administrator
 * 修改时间:  2014年5月19日
 * <修改描述:>
 */
package com.tx.local.loanaccount.model;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Map;

import org.springframework.format.annotation.DateTimeFormat;

import com.tx.local.basicdata.model.FeeItemEnum;
import com.tx.local.basicdata.model.IDCardTypeEnum;
import com.tx.local.basicdata.model.RepayWayEnum;
import com.tx.local.basicdata.model.TimeUnitTypeEnum;

/**
 * 放款账户详情<br/>
 * <功能详细描述>
 * 
 * @author  Administrator
 * @version  [版本号, 2014年5月19日]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public interface LoanAccountDetail {
    
    /* *************************** 贷款账户  相关信息 start *************************** */
    
    /** 获取贷款账户主键 */
    public String getId();
    
    /** 还款方式 */
    public RepayWayEnum getRepayWay();
    
    /** 时间单位 */
    public TimeUnitTypeEnum getTimeUnitType();
    
    /** 时间 */
    public int getTime();
    
    /** 获取对应合同主键 */
    public String getContractId();
    
    /** 获取合同编号 */
    public String getContractNumber();
    
    /** 获取贷款账户分类 */
    public LoanAccountCategoryEnum getCategory();
    
    /** 贷款账户类型 */
    public LoanAccountTypeEnum getLoanAccountType();
    
    /** 客户信用信息id */
    public String getCreditInfoId();
    
    /** 信用信息Tag版本 */
    public String getCreditInfoVersion();
    
    /** 客户id */
    public String getClientId();
    
    /** 贷款账户对应客户账户 */
    public String getClientAccountId();
    
    /** 客户姓名：找到客户实例后填入极少情况发生变化不变 */
    public String getClientName();
    
    /** 证 件类型：根据客户实例填入极少情况发生变化clientIdCardType[IDCardType?放名还是code还是id?]*/
    public IDCardTypeEnum getClientIdCardType();
    
    /** 证件号码:找到客户实例后填入 不变 */
    public String getClientIdCardNumber();
    
    /** 贷款生效日期 */
    @DateTimeFormat(pattern = "yyyy/MM/dd")
    public Date getEffectiveDate();
    
    /** 贷款失效日期,到期日 */
    @DateTimeFormat(pattern = "yyyy/MM/dd")
    public Date getExpiryDate();
    
    /** 贷前延期天数 */
    public int getBeforeDelayDays();
    
    /** 贷后延期天数 */
    public int getAfterDelayDays();
    
    /** 首期还款日期 */
    @DateTimeFormat(pattern = "yyyy/MM/dd")
    public Date getInterestDate();
    
    /** 首期还款日期 */
    @DateTimeFormat(pattern = "yyyy/MM/dd")
    public Date getFirstRepayDate();
    
    /** 每月还款日  */
    public int getMonthlyRepayDay();
    
    /** 每月还款金额 */
    public BigDecimal getMonthlyRepayAmount();
    
    /** 已付期数 */
    public BigDecimal getPaidPeriod();
    
    /** 总期数 */
    public int getTotalPeriod();
    
    /** 账户状态 */
    public AccountStatusEnum getAccountStatus();
    
    /** 下次还款日期 */
    @DateTimeFormat(pattern = "yyyy/MM/dd")
    public Date getNextRepayDate();
    
    /** 注销日期 */
    public Date getWriteOffDate();
    
    /** 注销金额 */
    public BigDecimal getWriteOffAmount();
    
    /** 注销原因 */
    public String getWriteOffReason();
    
    /** 注销回款 */
    public BigDecimal getWriteOffRepayAmount();
    
    /** 本金结余 */
    public BigDecimal getPrincipalBalance();
    
    /** 递减本金结余 */
    public BigDecimal getPrincipalBalanceIrr();
    
    /** 贷款金额 */
    public BigDecimal getLoanAmount();
    
    /** 获取超额还款金额 */
    public BigDecimal getOverRepayAmount();
    
    /** 获取外包佣金比率 */
    public BigDecimal getOutsourcePercentage();
    
    /** 是否正在委外中 */
    public boolean isOutsource();
    
    /**  获取逾期总额 逾期依赖以及非逾期依赖的金额之和 */
    public BigDecimal getOverdueSum();
    
    /** 获取逾期金额 */
    public BigDecimal getOverdueAmount();
    
    /** 是否存在未到账交易 */
    public boolean isExistNotReceivedTrading();
    
    /* *************************** 贷款账户  相关信息 end *************************** */
    
    /** 获取状态信息 */
    public String getStatus();
    
    /** 还款日期 */
    public Date getRepayDate();
    
    /** 还款日期对应的期数 */
    public String getRepayDatePeriod();
    
    /** 逾期天数 */
    public int getOverdueDays();
    
    /** 逾期月数 */
    public BigDecimal getOverdueMonthes();
    
    /* *************************** 贷款账户  费用项设置 start *************************** */
    
    /** 根据费用项类型获取对应的费率 */
    public Map<FeeItemEnum, String> getFeeItemValueMap();
    
    /** 根据费用项类型获取对应的费率 */
    public String getFeeItemValue(FeeItemEnum feeItem);
    
    /** 根据费用项类型获取对应的费率 */
    public BigDecimal getFeeItemRate(FeeItemEnum feeItem);
    
    /** 根据费用项类型获取百分比  */
    public BigDecimal getFeeItemRatePercent(FeeItemEnum feeItem);
    
    /** 下次还款金额 */
    public BigDecimal getNextRepayAmount();
    
    //  /** 获取贷款账户提前结清金额 */
    //    public BigDecimal getEarlySettleAmount();
    
    //    /** 根据费用项获取费用《this.loanAccount.getLoanAmount * fee.value 》 */
    //    public BigDecimal getFeeItemAmount(FeeItemEnum feeItem);
    
    /* *************************** 贷款账户  费用项设置 end *************************** */
    
    /* *************************** 贷款账户  根据已到期的还款计划 计算出的 信息 start *************************** */
    
    /** 当前期(或最后一期)逾期天数/前期(或倒数第2期)逾期天数/前前期(或倒数第3期)逾期天数 */
    public String getC0C1C2();
    
    /** 当前期逾期天数 */
    public int getC0();
    
    /** 上一期逾期天数 */
    public int getC1();
    
    /** 上上一期逾期天数 */
    public int getC2();
    
    /** 最长逾期天数/平均逾期天数/总逾期天数 */
    public String getLodAodTod();
    
    /** 最长逾期天数 */
    public int getLod();
    
    /** 平均逾期天数 */
    public int getAod();
    
    /** 总逾期天数 */
    public int getTod();
    
    /* *************************** 贷款账户  根据已到期的还款计划 计算出的 信息 end   *************************** */
    
    /** 获取还款日期映射 */
    public Map<String, Date> getRepaymentDateMap();
    
    /** 获取指定费用的 应收 + 豁免 -实收之和 Map */
    public Map<ScheduleTypeEnum, Map<String, Map<FeeItemEnum, BigDecimal>>> getSumMap();
    
    /** 获取指定费用的 应收 + 豁免 -实收之和 */
    public BigDecimal getSum(ScheduleTypeEnum scheuleType, FeeItemEnum feeItem);
    
    /** 获取指定费用的应收总额映射 */
    public Map<ScheduleTypeEnum, Map<String, Map<FeeItemEnum, BigDecimal>>> getReceivableSumMap();
    
    /** 获取指定费用的应收总额  */
    public BigDecimal getReceivableSum(ScheduleTypeEnum scheuleType, FeeItemEnum feeItem);
    
    /** 获取指定费用的豁免总额映射 */
    public Map<ScheduleTypeEnum, Map<String, Map<FeeItemEnum, BigDecimal>>> getExemptSumMap();
    
    /** 获取指定费用的豁免总额 */
    public BigDecimal getExemptSum(ScheduleTypeEnum scheuleType, FeeItemEnum feeItem);
    
    /** 获取指定费用的实收金额映射 */
    public Map<ScheduleTypeEnum, Map<String, Map<FeeItemEnum, BigDecimal>>> getActualReceivedSumMap();
    
    /** 获取指定费用的实收金额 */
    public BigDecimal getActualReceivedSum(ScheduleTypeEnum scheuleType, FeeItemEnum feeItem);
    
    /** 获取应付合计 */
    public BigDecimal getTotalSum(ScheduleTypeEnum scheuleType);
    
    /** 获取应付合计 */
    public BigDecimal getReceivableTotalSum(ScheduleTypeEnum scheuleType);
    
    /** 获取应付合计 */
    public BigDecimal getExemptTotalSum(ScheduleTypeEnum scheuleType);
    
    /** 获取实收金额总额 */
    public BigDecimal getActualReceivedTotalSum(ScheduleTypeEnum scheuleType);
    
    /* *************************** 与还款日相关的数据 start *************************** */
    /** 获取到期的各费用总额 */
    public Map<ScheduleTypeEnum, Map<String, Map<FeeItemEnum, BigDecimal>>> getSumMapOfMature();
    
    /** 获取指定费用的到期 应收 + 豁免 -实收之和 */
    public BigDecimal getSumOfMature(ScheduleTypeEnum scheuleType, FeeItemEnum feeItem);
    
    /** 获取到期的应收金额之和 */
    public Map<ScheduleTypeEnum, Map<String, Map<FeeItemEnum, BigDecimal>>> getReceivableSumMapOfMature();
    
    /** 获取指定费用的到期应收总额  */
    public BigDecimal getReceivableSumOfMature(ScheduleTypeEnum scheuleType, FeeItemEnum feeItem);
    
    /** 获取费用项和已经到期的计划中豁免金额总和 */
    public Map<ScheduleTypeEnum, Map<String, Map<FeeItemEnum, BigDecimal>>> getExemptSumMapOfMature();
    
    /** 获取指定费用的到期豁免总额 */
    public BigDecimal getExemptSumOfMature(ScheduleTypeEnum scheuleType, FeeItemEnum feeItem);
    
    /** 获取指定费用的到期实收总额 */
    public Map<ScheduleTypeEnum, Map<String, Map<FeeItemEnum, BigDecimal>>> getActualReceivedSumMapOfMature();
    
    /** 获取指定费用的到期实收总额  */
    public BigDecimal getActualReceivedSumOfMature(ScheduleTypeEnum scheuleType, FeeItemEnum feeItem);
    
    /** 获取应付合计 */
    public BigDecimal getTotalSumOfMature(ScheduleTypeEnum scheuleType);
    
    /** 获取应付合计 */
    public BigDecimal getReceivableTotalSumOfMature(ScheduleTypeEnum scheuleType);
    
    /** 获取应付合计 */
    public BigDecimal getExemptTotalSumOfMature(ScheduleTypeEnum scheuleType);
    
    /** 获取实收金额总额 */
    public BigDecimal getActualReceivedTotalSumOfMature(ScheduleTypeEnum scheuleType);
    
    /* *************************** 与还款日相关的数据  end *************************** */
    
    //    /**
    //      * 获取贷款账户逾期资料
    //      * <功能详细描述>
    //      * @return [参数说明]
    //      * 
    //      * @return List<PaymentSchedule> [返回类型说明]
    //      * @exception throws [异常类型] [异常说明]
    //      * @see [类、类#方法、类#成员]
    //     */
    //    public List<PaymentSchedule> getOverdueInfo();
    //    
    //    /**
    //      * 获取逾期利息小计：逾期利息 - 豁免逾期利息 - 已收逾期利息
    //      * <功能详细描述>
    //      * @return [参数说明]
    //      * 
    //      * @return BigDecimal [返回类型说明]
    //      * @exception throws [异常类型] [异常说明]
    //      * @see [类、类#方法、类#成员]
    //     */
    //    public BigDecimal getSubTotalOverdueInterest();
    //    
    //    /**
    //      * 是否计收提前结清违约金
    //      *     如果需要：则管理费利息需要提取到期应还
    //      *     如果不需要：则管理费利息需要提取剩余应还
    //      * <功能详细描述>
    //      * @return [参数说明]
    //      * 
    //      * @return boolean [返回类型说明]
    //      * @exception throws [异常类型] [异常说明]
    //      * @see [类、类#方法、类#成员]
    //     */
    //    public boolean isNeedEarlySettleDamageAmountCharge();
    //    /**
    //      * 获取提前结清最大可豁免金额<br/> : 该金额 = 提前结清金额 - 本金结余
    //      * <功能详细描述>
    //      * @return [参数说明]
    //      * 
    //      * @return BigDecimal [返回类型说明]
    //      * @exception throws [异常类型] [异常说明]
    //      * @see [类、类#方法、类#成员]
    //     */
    //    public BigDecimal getEarlySettleMaxExemptAbleAmountAbs();
    //    /** 
    //     * 获取逾期记录
    //     * <功能详细描述>
    //     * @return [参数说明]
    //     * 
    //     * @return List<OverdueInterestRecord> [返回类型说明]
    //     * @exception throws [异常类型] [异常说明]
    //     * @see [类、类#方法、类#成员]
    //     */
    //    public List<OverdueInterestRecord> getOverdueInterestRecordList();
    //    
    //    /**
    //      * 根据期数获取还款计划实例<br/>
    //      * <功能详细描述>
    //      * @param period
    //      * @return [参数说明]
    //      * 
    //      * @return PaymentSchedule [返回类型说明]
    //      * @exception throws [异常类型] [异常说明]
    //      * @see [类、类#方法、类#成员]
    //     */
    //    public PaymentSchedule getPaymentScheduleByPeriod(String period);
    //    
    //    /**
    //      * 根据期数获取还款计划分项
    //      * <功能详细描述>
    //      * @param period
    //      * @return [参数说明]
    //      * 
    //      * @return List<PaymentScheduleEntry> [返回类型说明]
    //      * @exception throws [异常类型] [异常说明]
    //      * @see [类、类#方法、类#成员]
    //     */
    //    public List<PaymentScheduleEntry> getPaymentScheduleEntryByPeriod(String period);
    //    
    //    /**
    //      * 获取还款计划映射<br/>
    //      *<功能详细描述>
    //      * @return [参数说明]
    //      * 
    //      * @return Map<String,PaymentSchedule> [返回类型说明]
    //      * @exception throws [异常类型] [异常说明]
    //      * @see [类、类#方法、类#成员]
    //     */
    //    public Map<String, PaymentSchedule> getPeriod2PaymentScheduleMap();
    //    
    //    /**
    //      * 获取贷款账户费用项设置列表<br/> 
    //      *<功能详细描述>
    //      * @return [参数说明]
    //      * 
    //      * @return List<LoanAccountFeeCfgItem> [返回类型说明]
    //      * @exception throws [异常类型] [异常说明]
    //      * @see [类、类#方法、类#成员]
    //     */
    //    public List<LoanAccountFeeCfgItem> getLoanAccountFeeCfgItemList();
    //    
    //    /**
    //      * 获取贷款账户对应的所有还款计划分项集合<br/>
    //      *<功能详细描述>
    //      * @return [参数说明]
    //      * 
    //      * @return List<PaymentScheduleEntry> [返回类型说明]
    //      * @exception throws [异常类型] [异常说明]
    //      * @see [类、类#方法、类#成员]
    //     */
    //    public List<PaymentScheduleEntry> getPaymentScheduleEntryList();
    //    
    //    /**
    //      * 获取指定费用项还款计划分项中第一个<br/>
    //      *     一般服务于获取NA期中的还款计划分项<br/>
    //      *<功能详细描述>
    //      * @param feeItem
    //      * @return [参数说明]
    //      * 
    //      * @return PaymentScheduleEntry [返回类型说明]
    //      * @exception throws [异常类型] [异常说明]
    //      * @see [类、类#方法、类#成员]
    //     */
    //    public PaymentScheduleEntry getFirstPaymentScheduleEntry(FeeItemEnum feeItem);
    //    /** 
    //     * 获取逾期记录
    //     *<功能详细描述>
    //     * @return [参数说明]
    //     * 
    //     * @return List<Map<String,Object>> [返回类型说明]
    //     * @exception throws [异常类型] [异常说明]
    //     * @see [类、类#方法、类#成员]
    //     */
    //    public List<Map<String, Object>> getOverdueInfoMapList();
    //
    //    /** 
    //     * 根据期数获取到期还款日
    //     *<功能详细描述>
    //     * @param period
    //     * @return [参数说明]
    //     * 
    //     * @return Date [返回类型说明]
    //     * @exception throws [异常类型] [异常说明]
    //     * @see [类、类#方法、类#成员]
    //     */
    //    public Date getPeriod2RepaymentDate(String period);
    //    
    //    /**
    //      * 获取外包佣金
    //      * <功能详细描述>
    //      * @return [参数说明]
    //      * 
    //      * @return BigDecimal [返回类型说明]
    //      * @exception throws [异常类型] [异常说明]
    //      * @see [类、类#方法、类#成员]
    //     */
    //    public BigDecimal getOutsourceCommissionAmount();
    //    
    //    /** 
    //     * 获取传入参数内逾期天数总和<br/>
    //     * <功能详细描述>
    //     * @return [参数说明]
    //     * 
    //     * @return int [返回类型说明]
    //     * @exception throws [异常类型] [异常说明]
    //     * @see [类、类#方法、类#成员]
    //     */
    //    public int getSumOverdueDaysWithNearest(int countPeriod);
    //    
    //    /** 
    //     * 获取传入期数内最高逾期天数<br/>
    //     * <功能详细描述>
    //     * @return [参数说明]
    //     * 
    //     * @return int [返回类型说明]
    //     * @exception throws [异常类型] [异常说明]
    //     * @see [类、类#方法、类#成员]
    //     */
    //    public int getMaxOverdueDaysWithNearest(int countPeriod);
    //    
    //    /**
    //      * 获取最近的逾期天数
    //      * <功能详细描述>
    //      * @return [参数说明]
    //      * 
    //      * @return int [返回类型说明]
    //      * @exception throws [异常类型] [异常说明]
    //      * @see [类、类#方法、类#成员]
    //     */
    //    public int getRecentOverdueDays();
}
