/*
 * 描          述:  <描述>
 * 修  改   人:  Administrator
 * 修改时间:  2020年1月13日
 * <修改描述:>
 */
package com.tx.local.clientinfo.model;

import com.tx.local.basicdata.model.IDCardTypeEnum;

/**
 * 客户安全账户<br/>
 * <功能详细描述>
 * 
 * @author  Administrator
 * @version  [版本号, 2020年1月13日]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public interface ClientSecurityAccount {
    
    /**
     * 获取证件类型<br/>
     * <功能详细描述>
     * @return [参数说明]
     * 
     * @return IDCardTypeEnum [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    public IDCardTypeEnum getIdCardType();
    
    /**
     * 获取证件号码<br/>
     * <功能详细描述>
     * @return [参数说明]
     * 
     * @return String [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    public String getIdCardNumber();
    
    /**
     * 获取移动电话号码<br/>
     * <功能详细描述>
     * @return [参数说明]
     * 
     * @return String [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    public String getMobileNumber();
    
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
