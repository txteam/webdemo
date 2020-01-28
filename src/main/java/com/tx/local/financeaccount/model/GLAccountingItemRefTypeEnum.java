/*
 * 描          述:  <描述>
 * 修  改   人:  Administrator
 * 修改时间:  2014年6月7日
 * <修改描述:>
 */
package com.tx.local.financeaccount.model;

import com.tx.core.support.json.BaseEnum;

/**
 * 引用类型
 * <功能详细描述>
 * 
 * @author  Administrator
 * @version  [版本号, 2014年6月7日]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public enum GLAccountingItemRefTypeEnum implements BaseEnum {
    
    DISTRICT("DISTRICT", "区域"),
    
    CREDIT_PRODUCT("CREDIT_PRODUCT", "贷款产品"),
    
    ORGANIZATION("ORGANIZATION", "组织"),;
    
    private final String key;
    
    private final String name;
    
    /** <默认构造函数> */
    private GLAccountingItemRefTypeEnum(String key, String name) {
        this.key = key;
        this.name = name;
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
}
