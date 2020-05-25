/*
 * 描          述:  <描述>
 * 修  改   人:  PengQingyang
 * 修改时间:  2020年5月22日
 * <修改描述:>
 */
package com.tx.local.clientinfo.model;

/**
 * <功能简述>
 * <功能详细描述>
 * 
 * @author  PengQingyang
 * @version  [版本号, 2020年5月22日]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public interface ClientAware {
    
    /**
     * 获取客户
     * <功能详细描述>
     * @return [参数说明]
     * 
     * @return String [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    public String getClientId();
    
    /**
     * 获取客户信息<br/>
     * <功能详细描述>
     * @return [参数说明]
     * 
     * @return Client [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    public Client getClient();
    
    /**
     * 设置客户信息<br/>
     * <功能详细描述>
     * @param client
     * @return [参数说明]
     * 
     * @return Client [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    public Client setClient(Client client);
    
}
