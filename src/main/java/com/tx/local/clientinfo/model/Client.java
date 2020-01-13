/*
 * 描          述:  <描述>
 * 修  改   人:  Administrator
 * 修改时间:  2015年10月20日
 * <修改描述:>
 */
package com.tx.local.clientinfo.model;

/**
 * 客户信息接口<br/>
 * 用以持有客户信息对象<br/>
 * 
 * @author  Administrator
 * @version  [版本号, 2015年10月20日]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public interface Client {
    
    /**
     * 获取客户id<br/>
     * <功能详细描述>
     * @return [参数说明]
     * 
     * @return String [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    public String getId();
    
    /**
     * 获取客户类型<br/>
     * <功能详细描述>
     * @return [参数说明]
     * 
     * @return ClientInfoTypeEnum [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    public ClientTypeEnum getType();
    
    /**
     * 获取用户姓名<br/>
     * <功能详细描述>
     * @return [参数说明]
     * 
     * @return String [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    public String getUsername();
}
