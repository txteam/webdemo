/*
 * 描          述:  <描述>
 * 修  改   人:  Administrator
 * 修改时间:  2019年4月20日
 * <修改描述:>
 */
package com.tx.local.loginaccount.model;


/**
 * 登陆账户<br/>
 * <功能详细描述>
 * 
 * @author  Administrator
 * @version  [版本号, 2019年4月20日]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public interface LoginAccount {
    
    /**
     * 登录账户唯一键<br/>
     * <功能详细描述>
     * @return [参数说明]
     * 
     * @return String [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    public String getId();
    
    /**
     * 登陆账户关联类型<br/>
     *  可以为操作人员，也可以为客户<br/>
     *  client,operator...
     * <功能详细描述>
     * @return [参数说明]
     * 
     * @return String [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    public String getRefType();
    
    /**
     * 获取登录的客户id<br/>
     *     登录账户id可能不等于ClientId<br/>
     *     例：企业成员<br/>
     * <功能详细描述>
     * @return [参数说明]
     * 
     * @return String [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
    */
    public String getRefId();
    
    /**
     * 获取登录账户的登录名<br/>
     * <功能详细描述>
     * @return [参数说明]
     * 
     * @return String [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
    */
    public String getLoginName();
    
    /**
     * 获取移动电话号码<br/>
     * <功能详细描述>
     * @return [参数说明]
     * 
     * @return String [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
    */
    public String getMobilePhoneNumber();
    
    /**
    * 获取客户邮件<br/>s
    * <功能详细描述>
    * @return [参数说明]
    * 
    * @return String [返回类型说明]
    * @exception throws [异常类型] [异常说明]
    * @see [类、类#方法、类#成员]
    */
    public String getEmail();
    
    /**
     * 获取微信编码<br/>
     * <功能详细描述>
     * @return [参数说明]
     * 
     * @return String [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    public String getWeixinAccountCode();
    
    /**
     * 获取微信账户名<br/>
     * <功能详细描述>
     * @return [参数说明]
     * 
     * @return String [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    public String getWeixinAccountName();
    
    /**
     * 获取QQ账户编码<br/>
     * <功能详细描述>
     * @return [参数说明]
     * 
     * @return String [返回类型说明]
     * @see [类、类#方法、类#成员]
     */
    public String getQQAccountCode();
    
    /**
     * 获取QQ账户名<br/>
     * <功能详细描述>
     * @return [参数说明]
     * 
     * @return String [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    public String getQQAccountName();
}
