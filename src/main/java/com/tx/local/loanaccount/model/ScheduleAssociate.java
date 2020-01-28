/*
 * 描          述:  <描述>
 * 修  改   人:  Administrator
 * 修改时间:  2017年5月9日
 * <修改描述:>
 */
package com.tx.local.loanaccount.model;

/**
 * 计划关联项<br/>
 * <功能详细描述>
 * 
 * @author  Administrator
 * @version  [版本号, 2017年5月9日]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public interface ScheduleAssociate {
    
    /**
     * 获取计划类型<br/>
     * <功能详细描述>
     * @return [参数说明]
     * 
     * @return ScheduleTypeEnum [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    public ScheduleTypeEnum getScheduleType();
    
    /**
     * 获取期数<br/>
     * <功能详细描述>
     * @return [参数说明]
     * 
     * @return String [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    public String getPeriod();
}
