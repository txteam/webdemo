/*
 * 描          述:  <描述>
 * 修  改   人:  Administrator
 * 修改时间:  2017年6月17日
 * <修改描述:>
 */
package com.tx.local.loanaccount.model;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Map;

import com.tx.local.basicdata.model.FeeItemEnum;

/**
  * 贷款账户结清详情<br/>
  * <功能详细描述>
  * 
  * @author  Administrator
  * @version  [版本号, 2017年6月17日]
  * @see  [相关类/方法]
  * @since  [产品/模块版本]
  */
public interface LoanAccountSettleDetail {
    
    /** 获取结清日期 */
    public Date getSettleDate();
    
    /** 贷款金额 */
    public BigDecimal getLoanAmount();
    
    /** 获取本金结余 */
    public BigDecimal getPrincipalBalance(ScheduleTypeEnum scheuleType);
    
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
    
    /** 获取结清金额 */
    public BigDecimal getSettleSum();
    
    /** 获取外包佣金 */
    public BigDecimal getWBYJSum();
    
    /** 获取注销后还款 */
    public BigDecimal getZXHHKSum();
    
    /** 获取应付合计 */
    public BigDecimal getTotal();
    
    /** */
    public Map<ScheduleTypeEnum, BigDecimal> getExemptAbleSumMap();
    
    /** 获取可豁免总额 */
    public BigDecimal getExemptAbleSum(ScheduleTypeEnum scheuleType);
    
    /** 获取可豁免总额 */
    public BigDecimal getMainExemptAbleSum();
}
