/*
 * 描          述:  <描述>
 * 修  改   人:  Administrator
 * 修改时间:  2016年11月15日
 * <修改描述:>
 */
package com.tx.local.creditinfo.model;

import com.tx.local.basicdata.model.IDCardTypeEnum;

/**
 * 支持关联客户<br/>
 * <功能详细描述>
 * 
 * @author  Administrator
 * @version  [版本号, 2016年11月15日]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public interface CreditInfoLinkAble {
    
    /**
     * 获取关联客户信用信息id<br/>
     * <功能详细描述>
     * @return [参数说明]
     * 
     * @return String [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    public String getCreditInfoId();
    
    /**
     * 获取关联客户信用信息的证件类型<br/>
     * <功能详细描述>
     * @return [参数说明]
     * 
     * @return IDCardTypeEnum [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    public IDCardTypeEnum getIdCardType();
    
    /**
     * 获取关联的客户信用信息证件号码<br/>
     * <功能详细描述>
     * @return [参数说明]
     * 
     * @return String [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    public String getIdCardNumber();
    
    /**
     * 
     *<名称>
     *<功能详细描述>
     * @return [参数说明]
     * 
     * @return String [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    public String getName();
    
}
