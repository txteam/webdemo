/*
 * 描          述:  <描述>
 * 修  改   人:  Administrator
 * 修改时间:  2020年1月10日
 * <修改描述:>
 */
package com.tx.local.calendar.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.tx.core.support.json.JSONAttributesSupport;

import io.swagger.annotations.ApiModel;

/**
 * 行事历对应事件<br/>
 * 参考：  https://fullcalendar.io/docs/event-parsing
 *      https://www.helloweba.net/javascript/454.html#fc-EventSourceObject
 *      https://fullcalendar.io/docs/event-parsing
 *      https://fullcalendar.io/docs/event-source-object
 * 
 * @author  Administrator
 * @version  [版本号, 2020年1月10日]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
@Entity
@Table(name = "cal_calendar_event")
@ApiModel("日程")
public class CalendarEvent implements Serializable, JSONAttributesSupport {
    
    /** 注释内容 */
    private static final long serialVersionUID = 4459440658885155691L;
    
    /** 可选，事件唯一标识，重复的事件具有相同的id */
    @Id
    @Column(nullable = false)
    private String id;
    
    /** 虚中心id */
    @Column(nullable = false, length = 64)
    private String vcid;
    
    /** 事件类型：用于区分事件类型，可以存储多种日历事件,注意：暂利用type代替 */
    @Column(nullable = false, length = 64)
    private CalendarEventTypeEnum type;
    
    /** 行事历事件分类可以为空，预留扩展性 */
    @Column(name = "catalogId", nullable = true, length = 64)
    private CalendarEventCatalog catalog;
    
    /** 主题类型 */
    @Column(nullable = false, length = 64)
    private CalendarEventTopicTypeEnum topicType;
    
    /** 主题ID，可以为用户id，也可以为其他 */
    @Column(nullable = true, length = 64)
    private String topicId;
    
    /** 必须，事件在日历上显示的title */
    @Column(nullable = false, updatable = true, length = 100)
    private String title;
    
    /** linkUrl */
    @JsonAlias(value = "url")
    @Column(nullable = true, updatable = true, length = 128)
    private String url;
    
    /** 每周的哪几天 */
    @Column(nullable = true)
    //Array. (For defining a simple recurring event). The days of the week this event repeats. 
    //An array of integers representing days e.g. [0, 1] for an event that repeats on Sundays and Mondays.
    private String daysOfWeek;
    
    //事件开始日期/时间，必选。格式为ISO8601字符串或UNIX时间戳
    /** 起始时间 */
    @Column(nullable = false)
    private Date start;
    
    //事件结束日期/时间，可选。格式为ISO8601字符串或UNIX时间戳
    /** 结束时间 */
    @Column(nullable = false)
    private Date end;
    
    //创建简单的重复事件将会使用到这些属性
    //Something duration-parseable. (For defining a simple recurring event). The time of day the event starts.
    //@Column(nullable = false)
    //private String startTime;
    //Something duration-parseable. (For defining a simple recurring event). The time of day the event ends.
    //@Column(nullable = false)
    //private String endTime;
    
    //tring 或者 Array 类型，可选。一个css类（或者一组），附加到事件的 DOM 元素上。
    /** 样式值 */
    @Column(nullable = true, updatable = true, length = 64)
    private String className;
    
    /** 可选，true or false，是否是全天事件 */
    private boolean allDay;
    
    //true或false，可选。只针对当前的单个事件，其他事件不受影响。
    /** 是否可编辑 */
    private boolean editable;
    
    //Boolean (true or false). Overrides the master eventOverlap option for this single event. 
    //If false, prevents this event from being dragged/resized over other events.
    //Also prevents other events from being dragged/resized over this event.
    private boolean overlap = true;
    
    /** 行事历事件策略 */
    @Column(nullable = true, updatable = true, length = 256)
    private String rrule;
    
    /** 时间间隔: for specifying the end time of each instance */
    @Column(nullable = true, updatable = false, length = 32)
    private String duration;
    
    /** 备注 */
    @Column(nullable = true, updatable = true, length = 100)
    private String remark;
    
    /** 额外的参数 */
    //extendedProps,color,backgroundColor,borderColor,textColor
    @Column(nullable = true, updatable = true, length = 2000)
    private String attributes;
    
    /** 最后更新时间 */
    @Column(nullable = false, updatable = false)
    private String creator;
    
    /** 最后更新时间 */
    @Column(nullable = false, updatable = true)
    private Date lastUpdateDate;
    
    /** 最后更新用户 */
    @Column(nullable = true, updatable = true)
    private String lastUpdateUserId;
    
    /** 创建时间 */
    @Column(nullable = false, updatable = false)
    private Date createDate;
    
    /** 创建用户ID */
    @Column(nullable = true, updatable = false)
    private String createUserId;
    
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
     * @return 返回 vcid
     */
    public String getVcid() {
        return vcid;
    }
    
    /**
     * @param 对vcid进行赋值
     */
    public void setVcid(String vcid) {
        this.vcid = vcid;
    }
    
    /**
     * @return 返回 type
     */
    public CalendarEventTypeEnum getType() {
        return type;
    }
    
    /**
     * @param 对type进行赋值
     */
    public void setType(CalendarEventTypeEnum type) {
        this.type = type;
    }
    
    /**
     * @return 返回 catalog
     */
    public CalendarEventCatalog getCatalog() {
        return catalog;
    }
    
    /**
     * @param 对catalog进行赋值
     */
    public void setCatalog(CalendarEventCatalog catalog) {
        this.catalog = catalog;
    }
    
    /**
     * @return 返回 topicType
     */
    public CalendarEventTopicTypeEnum getTopicType() {
        return topicType;
    }
    
    /**
     * @param 对topicType进行赋值
     */
    public void setTopicType(CalendarEventTopicTypeEnum topicType) {
        this.topicType = topicType;
    }
    
    /**
     * @return 返回 topicId
     */
    public String getTopicId() {
        return topicId;
    }
    
    /**
     * @param 对topicId进行赋值
     */
    public void setTopicId(String topicId) {
        this.topicId = topicId;
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
    
    /**
     * @return 返回 url
     */
    public String getUrl() {
        return url;
    }
    
    /**
     * @param 对url进行赋值
     */
    public void setUrl(String url) {
        this.url = url;
    }
    
    /**
     * @return 返回 daysOfWeek
     */
    public String getDaysOfWeek() {
        return daysOfWeek;
    }
    
    /**
     * @param 对daysOfWeek进行赋值
     */
    public void setDaysOfWeek(String daysOfWeek) {
        this.daysOfWeek = daysOfWeek;
    }
    
    /**
     * @return 返回 start
     */
    public Date getStart() {
        return start;
    }
    
    /**
     * @param 对start进行赋值
     */
    public void setStart(Date start) {
        this.start = start;
    }
    
    /**
     * @return 返回 end
     */
    public Date getEnd() {
        return end;
    }
    
    /**
     * @param 对end进行赋值
     */
    public void setEnd(Date end) {
        this.end = end;
    }
    
    /**
     * @return 返回 className
     */
    public String getClassName() {
        return className;
    }
    
    /**
     * @param 对className进行赋值
     */
    public void setClassName(String className) {
        this.className = className;
    }
    
    /**
     * @return 返回 allDay
     */
    public boolean isAllDay() {
        return allDay;
    }
    
    /**
     * @param 对allDay进行赋值
     */
    public void setAllDay(boolean allDay) {
        this.allDay = allDay;
    }
    
    /**
     * @return 返回 editable
     */
    public boolean isEditable() {
        return editable;
    }
    
    /**
     * @param 对editable进行赋值
     */
    public void setEditable(boolean editable) {
        this.editable = editable;
    }
    
    /**
     * @return 返回 overlap
     */
    public boolean isOverlap() {
        return overlap;
    }
    
    /**
     * @param 对overlap进行赋值
     */
    public void setOverlap(boolean overlap) {
        this.overlap = overlap;
    }
    
    /**
     * @return 返回 rrule
     */
    public String getRrule() {
        return rrule;
    }
    
    /**
     * @param 对rrule进行赋值
     */
    public void setRrule(String rrule) {
        this.rrule = rrule;
    }
    
    /**
     * @return 返回 duration
     */
    public String getDuration() {
        return duration;
    }
    
    /**
     * @param 对duration进行赋值
     */
    public void setDuration(String duration) {
        this.duration = duration;
    }
    
    /**
     * @return 返回 remark
     */
    public String getRemark() {
        return remark;
    }
    
    /**
     * @param 对remark进行赋值
     */
    public void setRemark(String remark) {
        this.remark = remark;
    }
    
    /**
     * @return 返回 attributes
     */
    public String getAttributes() {
        return attributes;
    }
    
    /**
     * @param 对attributes进行赋值
     */
    public void setAttributes(String attributes) {
        this.attributes = attributes;
    }
    
    /**
     * @return 返回 creator
     */
    public String getCreator() {
        return creator;
    }
    
    /**
     * @param 对creator进行赋值
     */
    public void setCreator(String creator) {
        this.creator = creator;
    }
    
    /**
     * @return 返回 lastUpdateDate
     */
    public Date getLastUpdateDate() {
        return lastUpdateDate;
    }
    
    /**
     * @param 对lastUpdateDate进行赋值
     */
    public void setLastUpdateDate(Date lastUpdateDate) {
        this.lastUpdateDate = lastUpdateDate;
    }
    
    /**
     * @return 返回 lastUpdateUserId
     */
    public String getLastUpdateUserId() {
        return lastUpdateUserId;
    }
    
    /**
     * @param 对lastUpdateUserId进行赋值
     */
    public void setLastUpdateUserId(String lastUpdateUserId) {
        this.lastUpdateUserId = lastUpdateUserId;
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
     * @return 返回 createUserId
     */
    public String getCreateUserId() {
        return createUserId;
    }
    
    /**
     * @param 对createUserId进行赋值
     */
    public void setCreateUserId(String createUserId) {
        this.createUserId = createUserId;
    }
}
