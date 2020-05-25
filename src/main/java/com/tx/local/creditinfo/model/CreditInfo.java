/*
 * 描          述:  <描述>
 * 修  改   人:  Administrator
 * 修改时间:  2016年10月3日
 * <修改描述:>
 */
package com.tx.local.creditinfo.model;

import java.io.Serializable;

import com.tx.local.basicdata.model.IDCardTypeEnum;
import com.tx.local.creditinfo.context.CreditInfoTypeEnum;

/**
 * 客户版本信息<br/>
 * <功能详细描述>
 * 
 * @author  Administrator
 * @version  [版本号, 2016年10月3日]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public interface CreditInfo extends Serializable {
    
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
     * 基线版本<br/>
     * <功能详细描述>
     * @return [参数说明]
     * 
     * @return int [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    int getBaseVersion();
    
    /**
     * 获取版本类型<br/>
     * <功能详细描述>
     * @return [参数说明]
     * 
     * @return ClientExtendInfoVersionTypeEnum [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    CreditInfoTypeEnum getType();
    
    /**
     * 获取版本号<br/>
     * <功能详细描述>
     * @return [参数说明]
     * 
     * @return String [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    int getVersion();
}
