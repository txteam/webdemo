/*
 * 描          述:  <描述>
 * 修  改   人:  Administrator
 * 修改时间:  2017年6月22日
 * <修改描述:>
 */
package com.tx.local.loanaccount.model;

import com.tx.local.basicdata.model.FeeItemEnum;

/**
 * 分配项可排序分项<br/>
 * <功能详细描述>
 * 
 * @author  Administrator
 * @version  [版本号, 2017年6月22日]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public interface AssignEntryComparable {
    
    /**
     * 获取对应的期数<br/>
     * <功能详细描述>
     * @return [参数说明]
     * 
     * @return String [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    public String getPeriod();
    
    /**
     * 获取费用项<br/>
     * <功能详细描述>
     * @return [参数说明]
     * 
     * @return FeeItemEnum [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    public FeeItemEnum getFeeItem();
}
