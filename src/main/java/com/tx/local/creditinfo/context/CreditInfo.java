/*
 * 描          述:  <描述>
 * 修  改   人:  Administrator
 * 修改时间:  2016年10月3日
 * <修改描述:>
 */
package com.tx.local.creditinfo.context;

import java.io.Serializable;
import java.util.Date;

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
     * 设置信用信息唯一键<br/>
     * <功能详细描述>
     * @param creditInfoId [参数说明]
     * 
     * @return void [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    void setId(String id);
    
    /**
     * 获取客户id
     * <功能详细描述>
     * @return [参数说明]
     * 
     * @return String [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    String getCreditInfoId();
    
    /**
     * 设置客户信用信息<br/>
     * <功能详细描述>
     * @param creditInfoId [参数说明]
     * 
     * @return void [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    void setCreditInfoId(String creditInfoId);
    
    /**
     * 获取版本类型<br/>
     * <功能详细描述>
     * @return [参数说明]
     * 
     * @return ClientExtendInfoVersionTypeEnum [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    CreditInfoVersionTypeEnum getVersionType();
    
    /**
     * 设置信息版本类型<br/>
     * <功能详细描述>
     * @param versionType [参数说明]
     * 
     * @return void [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    void setVersionType(CreditInfoVersionTypeEnum versionType);
    
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
    
    /**
     * 设置版本号<br/>
     * <功能详细描述>
     * @param version [参数说明]
     * 
     * @return void [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    void setVersion(int version);
    
    /**
     * 获取创建时间
     * <功能详细描述>
     * @return [参数说明]
     * 
     * @return Date [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    Date getCreateDate();
    
    /**
     * 设置创建时间<br/>
     * <功能详细描述>
     * @param createDate [参数说明]
     * 
     * @return void [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    void setCreateDate(Date createDate);
    
    /**
     * 获取最后更新时间<br/>
     * <功能详细描述>
     * @return [参数说明]
     * 
     * @return Date [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    Date getLastUpdateDate();
    
    /**
     * 设置最后更新时间<br/>
     * <功能详细描述>
     * @param lastUpdateDate [参数说明]
     * 
     * @return void [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    void setLastUpdateDate(Date lastUpdateDate);
    
    /**
     * 获取创建人id<br/>
     * <功能详细描述>
     * @return [参数说明]
     * 
     * @return String [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    String getCreateUserId();
    
    /**
     * 设置最后更新时间<br/>
     * <功能详细描述>
     * @param createUserId [参数说明]
     * 
     * @return void [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    void setCreateUserId(String createUserId);
    
    /**
     * 获取最后更新人id<br/>
     * <功能详细描述>
     * @return [参数说明]
     * 
     * @return String [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    String getLastUpdateUserId();
    
    /**
     * 设置最后更新人id<br/>
     * <功能详细描述>
     * @param lastUpdateUserId [参数说明]
     * 
     * @return void [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    void setLastUpdateUserId(String lastUpdateUserId);
}
