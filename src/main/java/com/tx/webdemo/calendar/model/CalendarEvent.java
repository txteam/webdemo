/*
 * 描          述:  <描述>
 * 修  改   人:  PengQingyang
 * 修改时间:  2012-11-4
 * <修改描述:>
 */
package com.tx.webdemo.calendar.model;

import java.util.Date;

import org.apache.ibatis.type.Alias;
import org.codehaus.jackson.annotate.JsonAutoDetect;
import org.codehaus.jackson.annotate.JsonIgnore;

/**
 * <日历事件实体>
 * <功能详细描述>
 * http://arshaw.com/fullcalendar/docs
 * 
 * @author  PengQingyang
 * @version  [版本号, 2012-11-4]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
@Alias("calendarEvent")
@JsonAutoDetect
public class CalendarEvent {
    
    /** 个人事件 */
    @JsonIgnore
    public static final int EVENT_TYPE_PRIVATE = 0;
    
    /** 公共事件 */
    @JsonIgnore
    public static final int EVENT_TYPE_PUBLIC = 1;
    
    /** 是否提醒， 1-提醒 */
    @JsonIgnore
    public static final int NOTIFY_TYPE_YES = 1;
    
    /** 是否提醒， 0-不提醒 */
    @JsonIgnore
    public static final int NOTIFY_TYPE_NO = 0;
    
    /** 行事历 事件 是否是全天 是-1  */
    @JsonIgnore
    public static final int ALLDAY_YES = 1;
    
    /** 行事历 事件 是否是全天 否-0  */
    @JsonIgnore
    public static final int ALLDAY_NO = 0;
    
    /** 事件唯一id */
    private String id;
    
    /** 事件标题 */
    private String title;
    
    /**0-个人事件 1-公共事件*/
    private int eventType = EVENT_TYPE_PRIVATE;
    
    /**结束时间*/
    private Date endDate;
    
    /** 开始时间 */
    private Date startDate;
    
    /** 创建人id */
    private String createOper;
    
    /** 创建时间 */
    private Date createDate;
    
    /** 最后修改人 */
    private String updateOper;
    
    /** 最后修改时间 */
    private Date updateDate;
    
    /** 是否提醒， 0-不提醒 1-提醒 */
    private int notifyType = NOTIFY_TYPE_NO;
    
    /** 提醒事件详细信息 */
    private String description;
    
    /** 是否是全天 0-否，1-是*/
    private int allDay;
    
    /**
     * 0100000每周的哪几天 从周日开始
     */
    private String days;

    /**
     * @return 返回 id
     */
    public String getId() {
        return id;
    }

    /**
     * @param 对id进行赋值
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * @return 返回 eventType
     */
    public int getEventType() {
        return eventType;
    }

    /**
     * @param 对eventType进行赋值
     */
    public void setEventType(int eventType) {
        this.eventType = eventType;
    }

    /**
     * @return 返回 endDate
     */
    public Date getEndDate() {
        return endDate;
    }

    /**
     * @param 对endDate进行赋值
     */
    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    /**
     * @return 返回 startDate
     */
    public Date getStartDate() {
        return startDate;
    }

    /**
     * @param 对startDate进行赋值
     */
    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    /**
     * @return 返回 createOper
     */
    public String getCreateOper() {
        return createOper;
    }

    /**
     * @param 对createOper进行赋值
     */
    public void setCreateOper(String createOper) {
        this.createOper = createOper;
    }

    /**
     * @return 返回 createDate
     */
    public Date getCreateDate() {
        return createDate;
    }

    /**
     * @param 对createDate进行赋值
     */
    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    /**
     * @return 返回 updateOper
     */
    public String getUpdateOper() {
        return updateOper;
    }

    /**
     * @param 对updateOper进行赋值
     */
    public void setUpdateOper(String updateOper) {
        this.updateOper = updateOper;
    }

    /**
     * @return 返回 updateDate
     */
    public Date getUpdateDate() {
        return updateDate;
    }

    /**
     * @param 对updateDate进行赋值
     */
    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    /**
     * @return 返回 notifyType
     */
    public int getNotifyType() {
        return notifyType;
    }

    /**
     * @param 对notifyType进行赋值
     */
    public void setNotifyType(int notifyType) {
        this.notifyType = notifyType;
    }

    /**
     * @return 返回 description
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param 对description进行赋值
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * @return 返回 allDay
     */
    public int getAllDay() {
        return allDay;
    }

    /**
     * @param 对allDay进行赋值
     */
    public void setAllDay(int allDay) {
        this.allDay = allDay;
    }

    /**
     * @return 返回 days
     */
    public String getDays() {
        return days;
    }

    /**
     * @param 对days进行赋值
     */
    public void setDays(String days) {
        this.days = days;
    }

    /**
     * @return 返回 title
     */
    public String getTitle() {
        return title;
    }

    /**
     * @param 对title进行赋值
     */
    public void setTitle(String title) {
        this.title = title;
    }
}
