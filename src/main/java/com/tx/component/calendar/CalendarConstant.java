/*
 * 描          述:  <描述>
 * 修  改   人:  Administrator
 * 修改时间:  2014年2月19日
 * <修改描述:>
 */
package com.tx.component.calendar;


/**
 * 行事历常量类<br/>
 * <功能详细描述>
 * 
 * @author  Administrator
 * @version  [版本号, 2014年2月19日]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public interface CalendarConstant {
    
    /** 个人事件 */
    int EVENT_TYPE_PRIVATE = 0;
    
    /** 公共事件 */
    int EVENT_TYPE_PUBLIC = 1;
    
    /** 是否提醒， 1-提醒 */
    int NOTIFY_TYPE_YES = 1;
    
    /** 是否提醒， 0-不提醒 */
    int NOTIFY_TYPE_NO = 0;
    
    /** 行事历 事件 是否是全天 是-1  */
    int ALLDAY_YES = 1;
    
    /** 行事历 事件 是否是全天 否-0  */
    int ALLDAY_NO = 0;
}
