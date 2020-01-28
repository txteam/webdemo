/*
 * 描          述:  <描述>
 * 修  改   人:  Administrator
 * 修改时间:  2020年1月26日
 * <修改描述:>
 */
package com.tx.local.basicdata.model;

/**
 * 财务科目<br/>
 * <功能详细描述> 
 * 
 * @author  Administrator
 * @version  [版本号, 2020年1月26日]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public interface AccountTitle {
    
    /**
     * 编码<br/>
     * <功能详细描述>
     * @return [参数说明]
     * 
     * @return String [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    public String getCode();
    
    /**
     * 名称<br/>
     * <功能详细描述>
     * @return [参数说明]
     * 
     * @return String [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    public String getName();
}
