package com.tx.local.institution.model;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.tx.component.basicdata.model.BasicDataEnum;
import com.tx.component.basicdata.model.BasicDataEnumJsonSerializer;

/**
  * 机构类型
  * @author  huangdonggang
  * @version  [版本号, 2016年12月13日]
  * @see  [相关类/方法]
  * @since  [产品/模块版本]
 */
@JsonSerialize(using = BasicDataEnumJsonSerializer.class)
public enum InstitutionTypeEnum implements BasicDataEnum {
    
    JG("JG", "企业"),
    
    SJ("SJ", "商家"),
    
    GTH("GTH", "个体工商户");
    
    private final String key;
    
    private final String name;
    
    /** <默认构造函数> */
    private InstitutionTypeEnum(String key, String name) {
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
