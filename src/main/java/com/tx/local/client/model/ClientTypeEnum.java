/*
 * 描 述: <描述> 修 改 人: Administrator 修改时间: 2015年10月21日 <修改描述:>
 */
package com.tx.local.client.model;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.tx.component.basicdata.model.BasicDataEnum;
import com.tx.component.basicdata.model.BasicDataEnumJsonSerializer;

/**
 * 客户类型（个人用户，机构用功能详细描述>
 * 
 * @author Administrator
 * @version [版本号, 2015年10月21日]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
@JsonSerialize(using = BasicDataEnumJsonSerializer.class)
public enum ClientTypeEnum implements BasicDataEnum {
    
    /** 机构用户 */
    EMPLOYEE("EMPLOYEE", "雇员"),
    
    /** 机构用户 */
    ENTERPRISE("ENTERPRISE", "企业"),
    
    /** 个体工商户(个体户) */
    SELF_EMPLOYED("SELF_EMPLOYED", "个体工商户"),
    
    /** 个人用户 */
    PERSONAL("PERSONAL", "个人");
    
    private final String name;
    
    private final String key;
    
    private ClientTypeEnum(String key, String name) {
        this.key = key;
        this.name = name;
    }
    
    /**
     * @return 返回 name
     */
    public String getName() {
        return name;
    }
    
    /**
     * @return 返回 key
     */
    public String getKey() {
        return key;
    }
    
}
