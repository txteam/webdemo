/*
 * 描          述:  <描述>
 * 修  改   人:  Administrator
 * 修改时间:  2017年3月5日
 * <修改描述:>
 */
package com.tx.local.content.model;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.tx.component.basicdata.model.BasicDataEnum;
import com.tx.component.basicdata.model.BasicDataEnumJsonSerializer;

/**
 * 类容类型<br/>
 * <功能详细描述>
 * 
 * @author  Administrator
 * @version  [版本号, 2017年3月5日]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
@JsonSerialize(using = BasicDataEnumJsonSerializer.class)
public enum ContentInfoTypeEnum implements BasicDataEnum {
    
    LINK("LINK", "链接"),
    
    IMAGE("IMAGE", "图片"),
    
    TEXT("TEXT", "文章");
    
    /** 对应枚举关键字：该字段可以为空 */
    private final String code;
    
    /** 内容信息类型名 */
    private final String name;
    
    /** <默认构造函数> */
    private ContentInfoTypeEnum(String code, String name) {
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
