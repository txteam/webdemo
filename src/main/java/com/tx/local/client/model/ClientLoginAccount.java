/*
 * 描          述:  <描述>
 * 修  改   人:  xuqishun
 * 修改时间:  2016年10月17日
 * <修改描述:>
 */
package com.tx.local.client.model;

/**
 * 客户登陆账号信息<br/>
 *    一个登录账户手机号码以及登录名至少其中一个不能为空<br/>
 * 
 * @author  xuqishun
 * @version  [版本号, 2016年10月17日]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public interface ClientLoginAccount {
    
    /**
      * 获取登录的客户id<br/>
      *     登录账户id可能不等于clientId<br/>
      *     例：企业成员<br/>
      * <功能详细描述>
      * @return [参数说明]
      * 
      * @return String [返回类型说明]
      * @exception throws [异常类型] [异常说明]
      * @see [类、类#方法、类#成员]
     */
    public String getLoginClientId();
    
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
}
