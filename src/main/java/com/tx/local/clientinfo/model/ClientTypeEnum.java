/*
 * 描 述: <描述> 修 改 人: Administrator 修改时间: 2015年10月21日 <修改描述:>
 */
package com.tx.local.clientinfo.model;

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
    
    /** 行政机构 */
    ADM_INS("ADM_INS", "行政机构"),
    
    /** 社属机构 */
    COO_INS("COO_INS", "社属机构"),
    
    /** 机构 */
    INSTITUTION("INSTITUTION", "机构"),
    
    /** 机构成员 */
    INSTITUTION_MEMBER("INSTITUTION_MEMBER", "机构成员"),
    
    /** 个体工商户(个体户) */
    SELF_EMPLOYED("SELF_EMPLOYED", "个体工商户"),
    
    /** 个人用户 */
    PERSONAL("PERSONAL", "个人");
    
    /** 编码 */
    private final String code;
    
    /** 名称 */
    private final String name;
    
    /** <默认构造函数> */
    private ClientTypeEnum(String code, String name) {
        this.code = code;
        this.name = name;
    }
    
    /**
     * @return 返回 code
     */
    public String getCode() {
        return code;
    }
    
    /**
     * @return 返回 name
     */
    public String getName() {
        return name;
    }
    
}
