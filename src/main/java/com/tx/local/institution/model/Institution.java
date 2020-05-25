package com.tx.local.institution.model;

import com.tx.local.basicdata.model.District;

/**
 * 机构信息<br/>
 * <功能详细描述>
 * 
 * @author  PengQingyang
 * @version  [版本号, 2020年5月5日]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public interface Institution {
    
    /**
     * 获取机构ID<br/>
     * <功能详细描述>
     * @return [参数说明]
     * 
     * @return String [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    String getId();
    
    /**
     * 机构所属虚中心<br/>
     * <功能详细描述>
     * @return [参数说明]
     * 
     * @return String [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    String getVcid();
    
    /**
     * 机构对应的客户ID
     * <功能详细描述>
     * @return [参数说明]
     * 
     * @return String [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    String getClientId();
    
    /**
     * 机构类型<br/>
     * <功能详细描述>
     * @return [参数说明]
     * 
     * @return InstitutionTypeEnum [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    InstitutionTypeEnum getType();
    
    /**
     * 机构名称<br/>
     * <功能详细描述>
     * @return [参数说明]
     * 
     * @return String [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    String getName();
    
    /**
     * 机构所在省<br/>
     * <功能详细描述>
     * @return [参数说明]
     * 
     * @return District [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    District getProvince();
    
    /**
     * 机构所市<br/>
     * <功能详细描述>
     * @return [参数说明]
     * 
     * @return District [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    District getCity();
    
    /**
     * 机构所在县<br/>
     * <功能详细描述>
     * @return [参数说明]
     * 
     * @return District [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    District getCounty();
    
    /**
     * 机构所在区域<br/>
     * <功能详细描述>
     * @return [参数说明]
     * 
     * @return District [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    District getDistrict();
    
    /**
     * 机构地址<br/>
     * <功能详细描述>
     * @return [参数说明]
     * 
     * @return String [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    String getAddress();
    
    /**
     * 机构全路径<br/>
     * <功能详细描述>
     * @return [参数说明]
     * 
     * @return String [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    String getFullAddress();
    
    /**
     * 机构所在区域邮政编码<br/>
     * <功能详细描述>
     * @return [参数说明]
     * 
     * @return String [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    String getPostcode();
    
    /**
     * 机构联系人<br/>
     * <功能详细描述>
     * @return [参数说明]
     * 
     * @return String [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    String getLinkName();
    
    /**
     * 机构联系电话<br/>
     * <功能详细描述>
     * @return [参数说明]
     * 
     * @return String [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    String getLinkMobileNumber();
    
    /**
     * 机构是否可编辑<br/>
     * <功能详细描述>
     * @return [参数说明]
     * 
     * @return boolean [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    boolean isModifyAble();
}