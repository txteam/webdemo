/*
 * 描          述:  <描述>
 * 修  改   人:  Administrator
 * 修改时间:  2016年9月5日
 * <修改描述:>
 */
package com.tx.local.noticemessage.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * 站内消息<br/>
 * <功能详细描述>
 * 
 * @author  Administrator
 * @version  [版本号, 2016年9月5日]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
@Entity
@Table(name = "nm_notice_message")
public class NoticeMessage {
    
    /** 站内消息id */
    @Id
    private String id;
    
    /** 客户类型 */
    private NoticeMessageClientTypeEnum clientType;
    
    /** 站内消息优先级 */
    private NoticeMessagePriorityEnum priority;
    
    /** 站内消息类型 */
    @ManyToOne
    @JoinColumn(name = "noticeMessageTypeId")
    private NoticeMessageType noticeMessageType;
    
    /** 站内消息标题 */
    private String title;
    
    /** 站内消息内容 */
    private String content;
    
    /** 发布时间 */
    private Date publishDate = new Date();
    
    /** 是否撤销 */
    private boolean revokeFlag = false;
    
    /** 撤销时间 */
    private Date revokeDate;
    
    /** 创建时间 */
    private Date createDate = new Date();
    
    /** 最后更新时间 */
    private Date lastUpdateDate = new Date();
    
    /** <默认构造函数> */
    public NoticeMessage() {
        super();
    }
    
    /** <默认构造函数> */
    public NoticeMessage(String title, String content) {
        super();
        this.title = title;
        this.content = content;
    }
    
    /** <默认构造函数> */
    public NoticeMessage(NoticeMessageType noticeMessageType, String title,
            String content) {
        super();
        this.noticeMessageType = noticeMessageType;
        this.title = title;
        this.content = content;
    }
    
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
     * @return 返回 clientType
     */
    public NoticeMessageClientTypeEnum getClientType() {
        return clientType;
    }
    
    /**
     * @param 对clientType进行赋值
     */
    public void setClientType(NoticeMessageClientTypeEnum clientType) {
        this.clientType = clientType;
    }
    
    /**
     * @return 返回 priority
     */
    public NoticeMessagePriorityEnum getPriority() {
        return priority;
    }
    
    /**
     * @param 对priority进行赋值
     */
    public void setPriority(NoticeMessagePriorityEnum priority) {
        this.priority = priority;
    }
    
    /**
     * @return 返回 noticeMessageType
     */
    public NoticeMessageType getNoticeMessageType() {
        return noticeMessageType;
    }
    
    /**
     * @param 对noticeMessageType进行赋值
     */
    public void setNoticeMessageType(NoticeMessageType noticeMessageType) {
        this.noticeMessageType = noticeMessageType;
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
     * @return 返回 content
     */
    public String getContent() {
        return content;
    }
    
    /**
     * @param 对content进行赋值
     */
    public void setContent(String content) {
        this.content = content;
    }
    
    /**
     * @return 返回 publishDate
     */
    public Date getPublishDate() {
        return publishDate;
    }
    
    /**
     * @param 对publishDate进行赋值
     */
    public void setPublishDate(Date publishDate) {
        this.publishDate = publishDate;
    }
    
    /**
     * @return 返回 revokeFlag
     */
    public boolean isRevokeFlag() {
        return revokeFlag;
    }
    
    /**
     * @param 对revokeFlag进行赋值
     */
    public void setRevokeFlag(boolean revokeFlag) {
        this.revokeFlag = revokeFlag;
    }
    
    /**
     * @return 返回 revokeDate
     */
    public Date getRevokeDate() {
        return revokeDate;
    }
    
    /**
     * @param 对revokeDate进行赋值
     */
    public void setRevokeDate(Date revokeDate) {
        this.revokeDate = revokeDate;
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
    
}
