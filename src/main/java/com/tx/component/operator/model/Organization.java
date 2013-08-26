/*
 * 描          述:  <描述>
 * 修  改   人:  PengQingyang
 * 修改时间:  2013-8-26
 * <修改描述:>
 */
package com.tx.component.operator.model;

import com.tx.component.mainframe.model.District;


 /**
  * 组织<br/>
  * <功能详细描述>
  * 
  * @author  PengQingyang
  * @version  [版本号, 2013-8-26]
  * @see  [相关类/方法]
  * @since  [产品/模块版本]
  */
public class Organization {
    
    /** 组织唯一键 */
    private String id;
    
    /** 父组织id */
    private String parentId;
    
    /** 编码 */
    private String code;
    
    /** 虚中心id */
    private String vcid;
    
    /** 名称 */
    private String name;
    
    /** 全称 */
    private String fullName;
    
    /** 地址 */
    private String address;
    
    /** 是否可用 */
    private boolean valid;
    
    /** 
     * 合同起始日期在16点后就可选择接下来的时间，此时间为可配置时间 
     */
    private int dayBegin;
    
    /**是否是分行*/
    private Boolean isBranch;
    
    /**省ID*/
    private District province;
    
    /**市里ID*/
    private District city;
    
    /**所属地区*/
    private District area;
    
    /**丙方名称*/
    private String otherName;
    
    /**丙方地址*/
    private String otherAddress;
    
    /**修正名称*/
    private String realName;
}
