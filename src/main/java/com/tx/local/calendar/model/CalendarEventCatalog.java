/*
 * 描          述:  <描述>
 * 修  改   人:  Administrator
 * 修改时间:  2020年2月5日
 * <修改描述:>
 */
package com.tx.local.calendar.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.tx.component.basicdata.model.BasicData;

import io.swagger.annotations.ApiModel;

/**
 * 行事历分类<br/>
 * <功能详细描述>
 * 
 * @author  Administrator
 * @version  [版本号, 2020年2月5日]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
@Entity
@Table(name = "cal_event_catalog")
@ApiModel("日程分类")
public class CalendarEventCatalog implements BasicData {
    
    /** 注释内容 */
    private static final long serialVersionUID = -6051543988330268097L;
    
    /** 记事本分类唯一键 */
    @Id
    @Column(nullable = false, length = 64)
    private String id;
    
    /** 虚中心ID */
    @Column(nullable = false, length = 64)
    private String vcid;
    
    /** 事件类型：用于区分事件类型，可以存储多种日历事件 */
    @Column(nullable = false, length = 64)
    private CalendarEventTypeEnum type;
    
    /** 主题类型 */
    @Column(nullable = false, length = 64)
    private CalendarEventTopicTypeEnum topicType;
    
    /** 行事历编码 */
    @Column(nullable = false, updatable = false)
    private String code;
    
    /** 记事本分类名称 */
    @Column(nullable = false, updatable = true, length = 100)
    private String name;
    
    /** 是否有效 */
    private boolean valid;
    
    /** 是否可编辑 */
    private boolean modifyAble;
    
    /** 记事本分类备注 */
    @Column(nullable = true, updatable = true, length = 500)
    private String remark;
    
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
     * @return 返回 code
     */
    public String getCode() {
        return code;
    }
    
    /**
     * @param 对code进行赋值
     */
    public void setCode(String code) {
        this.code = code;
    }
    
    /**
     * @return 返回 name
     */
    public String getName() {
        return name;
    }
    
    /**
     * @param 对name进行赋值
     */
    public void setName(String name) {
        this.name = name;
    }
    
    /**
     * @return 返回 valid
     */
    public boolean isValid() {
        return valid;
    }
    
    /**
     * @param 对valid进行赋值
     */
    public void setValid(boolean valid) {
        this.valid = valid;
    }
    
    /**
     * @return 返回 modifyAble
     */
    public boolean isModifyAble() {
        return modifyAble;
    }
    
    /**
     * @param 对modifyAble进行赋值
     */
    public void setModifyAble(boolean modifyAble) {
        this.modifyAble = modifyAble;
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
