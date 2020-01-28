/*
 * 描          述:  <描述>
 * 修  改   人:  Administrator
 * 修改时间:  2014年4月24日
 * <修改描述:>
 */
package com.tx.local.financeaccount.model;

import com.tx.core.support.json.BaseEnum;

/**
 * 财务总账GL对应的核算项类型<br/>
 * <功能详细描述>
 * 
 * @author  Administrator
 * @version  [版本号, 2014年4月24日]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public enum GLAccountingItemTypeEnum implements BaseEnum {
    
    /** 核算项(省) */
    DISTRICT_PROVINCE("DISTRICT_PROVINCE", "省", "province"),
    
    /** 核算项(市) */
    DISTRICT_CITY("DISTRICT_CITY", "市", "city"),
    
    /** 核算项(地区) */
    DISTRICT_AREA("DISTRICT_AREA", "区", "area"),
    
    /** 核算项(分行) */
    ORGANIZATION_BRANCH("ORGANIZATION_BRANCH", "分行", "branch"),
    
    /** 核算项(公司) */
    ORGANIZATION_COMPANY("ORGANIZATION_COMPANY", "公司", "company"),
    
    /** 核算项(公司) */
    ORGANIZATION_COMPANY_ENTRY("ORGANIZATION_COMPANY_ENTRY", "分公司",
            "companyEntry"),
    
    /** 核算项(贷款产品) */
    CREDITPRODUCT("CREDITPRODUCT", "贷款产品", "creditProduct");
    
    /** 关键字key */
    private final String key;
    
    /** 核算项大类名称 */
    private final String name;
    
    /** 核算项类型对应的字段名称  */
    /* 对应的id字段的key为:field + Id
     *  name字段的key为:field + Name
     *  code字段的key为:field + Code
     */
    private final String field;
    
    /** <默认构造函数> */
    private GLAccountingItemTypeEnum(String key, String name, String field) {
        this.key = key;
        this.name = name;
        this.field = field;
    }
    
    /**
     * @return 返回 key
     */
    public String getKey() {
        return key;
    }
    
    /**
     * @return 返回 name
     */
    public String getName() {
        return name;
    }
    
    /**
     * @return 返回 field
     */
    public String getField() {
        return field;
    }
}
