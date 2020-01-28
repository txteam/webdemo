/*
 * 描          述:  <描述>
 * 修  改   人:  Administrator
 * 修改时间:  2014年5月20日
 * <修改描述:>
 */
package com.tx.local.loanaccount.model;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.tx.component.basicdata.model.BasicDataEnum;
import com.tx.component.basicdata.model.BasicDataEnumJsonSerializer;

/**
 * 催收状态枚举
 * 
 * @author Administrator
 * @version [版本号, 2014年5月20日]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
@JsonSerialize(using = BasicDataEnumJsonSerializer.class)
public enum CollectionStatusEnum implements BasicDataEnum {
    
    /** 空 */
    NA("NA", "正常"),
    
    /** 待委外 */
    CO("CO", "待委外"),
    
    /** 逾期 */
    DQ("DQ", "逾期"),
    
    /** 委外 */
    OA("OA", "委外"),
    
    /** 委外退回 */
    RA("RA", "委外退回");
    
    /** 编码 */
    private final String code;
    
    /** 名称 */
    private final String name;
    
    /** 催收状态枚举 */
    private CollectionStatusEnum(String code, String name) {
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
