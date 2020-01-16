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

import com.tx.core.support.json.JSONAttributesSupport;

import io.swagger.annotations.ApiModel;

/**
 * 行事历对应事件<br/>
 * <功能详细描述>
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
    
    /** 事件类型：用于区分事件类型，可以存储多种日历事件 */
    @Column(nullable = false, length = 64)
    private CalendarEventTypeEnum type;
    
    /** 主题类型 */
    @Column(nullable = false, length = 64)
    private CalendarEventTopicTypeEnum topicType;
    
    /** 主题ID，可以为用户id，也可以为其他 */
    @Column(nullable = true, length = 64)
    private String topicId;
    
    /** 必须，事件在日历上显示的title */
    @Column(nullable = false, updatable = true, length = 100)
    private String title;
    
    /** 备注 */
    @Column(nullable = true, updatable = true, length = 500)
    private String remark;
    
    /** linkUrl */
    @Column(nullable = true, updatable = true, length = 128)
    private String linkUrl;
    
    /** 样式值 */
    @Column(nullable = true, updatable = true, length = 64)
    private String classValue;
    
    /** style值 */
    @Column(nullable = true, updatable = true, length = 64)
    private String styleValue;
    
    /** 额外的参数 */
    @Column(nullable = true, updatable = true, length = 4000)
    private String attributes;
    
    @Column(nullable = false)
    private Date start;
    
    @Column(nullable = false)
    private Date end;
    
    /** 可选，true or false，是否是全天事件 */
    private boolean allDay;
    
    /** 是否可编辑 */
    private boolean editable;
    
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
     * @return 返回 linkUrl
     */
    public String getLinkUrl() {
        return linkUrl;
    }
    
    /**
     * @param 对linkUrl进行赋值
     */
    public void setLinkUrl(String linkUrl) {
        this.linkUrl = linkUrl;
    }
    
    /**
     * @return 返回 classValue
     */
    public String getClassValue() {
        return classValue;
    }
    
    /**
     * @param 对classValue进行赋值
     */
    public void setClassValue(String classValue) {
        this.classValue = classValue;
    }
    
    /**
     * @return 返回 styleValue
     */
    public String getStyleValue() {
        return styleValue;
    }
    
    /**
     * @param 对styleValue进行赋值
     */
    public void setStyleValue(String styleValue) {
        this.styleValue = styleValue;
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
