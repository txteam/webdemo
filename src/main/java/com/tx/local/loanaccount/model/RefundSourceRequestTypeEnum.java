/*
 * 描          述:  <描述>
 * 修  改   人:  lenovo
 * 修改时间:  2014年7月24日
 * <修改描述:>
 */
package com.tx.local.loanaccount.model;

/**
 * 归还款项来源
 * <功能详细描述>
 * 
 * @author  lenovo
 * @version  [版本号, 2014年7月24日]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public enum RefundSourceRequestTypeEnum {
    
    /**自动转账还款  */
    自动转账还款,
    
    /** 现金还款  */
    现金还款,
    
    /** 超额还款 */
    超额还款;
    
}
