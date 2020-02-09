/*
 * 描          述:  <描述>
 * 修  改   人:  Administrator
 * 修改时间:  2020年1月16日
 * <修改描述:>
 */
package com.tx.local.calendar.model;

/**
 * 行事历事件类型枚举<br/>
 * <功能详细描述>
 * 
 * @author  Administrator
 * @version  [版本号, 2020年1月16日]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public enum CalendarEventTypeEnum {
    
    /** 操作人员行事历事件 */
    OPERATOR_CALENDAR_EVENT,
    
    /** 操作人员行事历事件 */
    OPERATOR_PUBLIC_CALENDAR_EVENT,
    
    /** 客户行事历事件 */
    CLIENT_CALENDAR_EVENT,
    
    /** 客户行事历事件 */
    CLIENT_PUBLIC_CALENDAR_EVENT;
}
