package com.tx.local.loanaccount.helper.repayallocator;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Map;

import com.tx.local.basicdata.model.FeeItemEnum;
import com.tx.local.loanaccount.model.ScheduleTypeEnum;

/**
 * 还款分配器<br/>
 * <功能详细描述>
 * 
 * @author  Administrator
 * @version  [版本号, 2017年6月20日]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public interface RepayAllocator {
    
    /**
      * 获取还款日期<br/>
      * <功能详细描述>
      * @return [参数说明]
      * 
      * @return Date [返回类型说明]
      * @exception throws [异常类型] [异常说明]
      * @see [类、类#方法、类#成员]
     */
    Date getRepayDate();
    
    /**
      * 获取分配总金额<br/>
      * <功能详细描述>
      * @return [参数说明]
      * 
      * @return BigDecimal [返回类型说明]
      * @exception throws [异常类型] [异常说明]
      * @see [类、类#方法、类#成员]
     */
    BigDecimal getAmount();
    
    /**
     * 对还款进行平息账分配<br/>
     * <功能详细描述> [参数说明]
     * 
     * @return void [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
    */
    void assign();
    
    /** 
     * 当还款渠道映射为空时的还款分配<br/>
     * <功能详细描述> [参数说明]
     * 
     * @return void [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    void mainAssign();
    
    /**
     * 根据主计划分配进行其他类型的计划的金额分配<br/>
     * <功能详细描述> [参数说明]
     * 
     * @return void [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    void assignByScheduleTypes();
    
    /**
     * 对还款进行递减账分配<br/>
     * <功能详细描述> [参数说明]
     * 
     * @return void [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    void assignByScheduleType(ScheduleTypeEnum scheduleType);
    
    /**
     * 获取主计划还款费用分配<br/>
     * <功能详细描述>
     * @return [参数说明]
     * 
     * @return Map<String,Map<FeeItemEnum,BigDecimal>> [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    Map<String, Map<FeeItemEnum, BigDecimal>> getMainAssignAmountMap();
    
    /**
      * 获取分配金额映射（不含主计划）
      * <功能详细描述>
      * @return [参数说明]
      * 
      * @return Map<ScheduleTypeEnum,Map<String,Map<FeeItemEnum,BigDecimal>>> [返回类型说明]
      * @exception throws [异常类型] [异常说明]
      * @see [类、类#方法、类#成员]
     */
    Map<ScheduleTypeEnum, Map<String, Map<FeeItemEnum, BigDecimal>>> getAssignAmountMap();
    
    /**
      * 获取超额还款金额<br/>
      * <功能详细描述>
      * @return [参数说明]
      * 
      * @return BigDecimal [返回类型说明]
      * @exception throws [异常类型] [异常说明]
      * @see [类、类#方法、类#成员]
     */
    BigDecimal getOverRepayAmount();
    
}