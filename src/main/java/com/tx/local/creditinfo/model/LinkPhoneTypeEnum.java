/*
 * 描          述:  <描述>
 * 修  改   人:  Administrator
 * 修改时间:  2016年11月15日
 * <修改描述:>
 */
package com.tx.local.creditinfo.model;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.tx.component.basicdata.model.BasicDataEnum;
import com.tx.component.basicdata.model.BasicDataEnumJsonSerializer;

/**
 * 电话类型<br/>
 * <功能详细描述>
 * 
 * @author  Administrator
 * @version  [版本号, 2016年11月15日]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
@JsonSerialize(using = BasicDataEnumJsonSerializer.class)
public enum LinkPhoneTypeEnum implements BasicDataEnum {
    
    /** 住宅电话 */
    HOUSE_PHONE("HOUSE_PHONE", "住宅电话"),
    
    /** 单位电话 */
    UNIT_PHONE("UNIT_PHONE", "单位电话"),
    
    /** 移动电话 */
    MOBILE_PHONE("MOBILE_PHONE", "移动电话");
    
    /** 编码 */
    private final String code;
    
    /** 名称 */
    private final String name;
    
    private LinkPhoneTypeEnum(String code, String name) {
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
