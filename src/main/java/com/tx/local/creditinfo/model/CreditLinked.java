/*
 * 描          述:  <描述>
 * 修  改   人:  PengQingyang
 * 修改时间:  2020年5月5日
 * <修改描述:>
 */
package com.tx.local.creditinfo.model;

import java.io.Serializable;

/**
 * 1：n的信用信息<br/>
 * <功能详细描述>
 * 
 * @author  PengQingyang
 * @version  [版本号, 2020年5月5日]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public interface CreditLinked extends Serializable {
    
    /**
     * 获取信用信息唯一键<br/>
     * <功能详细描述>
     * @return [参数说明]
     * 
     * @return String [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    String getId();
    
    /**
     * 设置信息ID<br/>
     * <功能详细描述>
     * @param creditInfoId [参数说明]
     * 
     * @return void [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    void setId(String id);
    
    /**
     * 获取客户ID<br/>
     * <功能详细描述>
     * @return [参数说明]
     * 
     * @return String [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    String getClientId();
    
    /**
     * 设置客户信用ID<br/>
     * <功能详细描述>
     * @param clientId [参数说明]
     * 
     * @return void [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    void setClientId(String clientId);
    
    /**
     * 获取客户信用id
     * <功能详细描述>
     * @return [参数说明]
     * 
     * @return String [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    String getCreditInfoId();
    
    /**
     * 设置客户信用信息ID<br/>
     * <功能详细描述>
     * @param creditInfoId [参数说明]
     * 
     * @return void [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    void setCreditInfoId(String creditInfoId);
}
