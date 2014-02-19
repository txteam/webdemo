/*
 * 描          述:  <描述>
 * 修  改   人:  PengQingyang
 * 修改时间:  2014-2-19
 * <修改描述:>
 */
package com.tx.component.calendar.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonAutoDetect;
import org.hibernate.annotations.GenericGenerator;

import com.tx.component.calendar.CalendarConstant;

/**
 * 日历事件实体
 * <功能详细描述>
 * http://arshaw.com/fullcalendar/docs
 * 
 * @author  PengQingyang
 * @version  [版本号, 2012-11-4]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
@JsonAutoDetect
@Entity
@Table(name = "t_calendar_event")
public class CalendarEvent {
    
    /** 事件唯一id */
    private String id;
    
    /** 事件标题 */
    private String title;
    
    /**0-个人事件 1-公共事件*/
    private int eventType = CalendarConstant.EVENT_TYPE_PRIVATE;
    
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
    //private int notifyType = NOTIFY_TYPE_NO;
    
    /** 提醒事件详细信息 */
    private String description;
    
    /** 是否是全天 0-否，1-是*/
    private int allDay = CalendarConstant.NOTIFY_TYPE_NO;
    
    /**
     * 0100000每周的哪几天 从周日开始
     */
    private String days = "0000000";

    /**
     * @return 返回 id
     */
    @Id
    @Column(name = "ID")
    @GeneratedValue(generator = "sys_uuid")
    @GenericGenerator(name = "sys_uuid", strategy = "uuid")
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
    
//    @Transient
//    public String getShowMSG(){
//        StringBuilder sb = new StringBuilder();
//        sb.append(this.getTitle());
//        if(this.getAllDay()==1){
//            sb.append("(全天)");
//        }else{
//            sb.append("(");
//            sb.append(DateUtil.dateToStr(this.getStartDate(), "HH:mm"));
//            sb.append("至");
//            sb.append(DateUtil.dateToStr(this.getEndDate(), "HH:mm"));
//            sb.append(")");
//        }
////        StringBuilder returnSb =new  StringBuilder(); 
////        if (!StringUtil.isEmpty(sb)) {
////            int length = sb.length();
////            if (length > 40) {
////                returnSb.append(sb.substring(0, 40));
////                sb.append("...");
////            } else {
////                returnSb.append(sb);
////            }
////        }
//        return sb.toString();
//    }
}
