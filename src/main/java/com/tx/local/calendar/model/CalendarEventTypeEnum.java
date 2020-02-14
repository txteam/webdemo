/*
 * 描          述:  <描述>
 * 修  改   人:  Administrator
 * 修改时间:  2020年1月16日
 * <修改描述:>
 */
package com.tx.local.calendar.model;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.tx.core.support.json.BaseEnum;
import com.tx.core.support.json.BaseEnumJsonSerializer;

/**
 * 行事历事件类型枚举<br/>
 * <功能详细描述>
 * 
 * @author  Administrator
 * @version  [版本号, 2020年1月16日]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
@JsonSerialize(using = BaseEnumJsonSerializer.class)
public enum CalendarEventTypeEnum implements BaseEnum {
    
    /** 操作人员行事历事件 */
    OPERATOR_CALENDAR_EVENT("OPERATOR_CALENDAR_EVENT", "个人事件"),
    
    /** 操作人员行事历事件 */
    OPERATOR_PUBLIC_CALENDAR_EVENT("OPERATOR_PUBLIC_CALENDAR_EVENT", "公共事件"),
    
    /** 客户行事历事件 */
    CLIENT_CALENDAR_EVENT("CLIENT_CALENDAR_EVENT", "个人事件"),
    
    /** 客户行事历事件 */
    CLIENT_PUBLIC_CALENDAR_EVENT("CLIENT_PUBLIC_CALENDAR_EVENT", "公共事件");
    
    /** 编码 */
    private final String code;
    
    /** 名称 */
    private final String name;
    
    /** <默认构造函数> */
    private CalendarEventTypeEnum(String code, String name) {
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
