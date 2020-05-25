package com.tx.local.institution.model;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.tx.component.basicdata.model.BasicDataEnum;
import com.tx.component.basicdata.model.BasicDataEnumJsonSerializer;

/**
 * 机构类型<br/>
 * <功能详细描述>
 * 
 * @author  Administrator
 * @version  [版本号, 2020年3月28日]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
@JsonSerialize(using = BasicDataEnumJsonSerializer.class)
public enum InstitutionTypeEnum implements BasicDataEnum {
    
    /** 机构用户 */
    ADM_INS("ADM_INS", "行政机构"),
    
    /** 机构用户 */
    COO_INS("COO_INS", "社属机构"),
    
    /** 企业 */
    ENTERPRISE("ENTERPRISE", "企业"),
    
    /** 个体工商户 */
    SELF_EMPLOYED("SELF_EMPLOYED", "个体工商户");
    
    /** 编码 */
    private final String code;
    
    /** 名称 */
    private final String name;
    
    /** <默认构造函数> */
    private InstitutionTypeEnum(String code, String name) {
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
