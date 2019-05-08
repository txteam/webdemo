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

import com.tx.core.jdbc.sqlsource.annotation.QueryConditionEqual;
import com.tx.core.jdbc.sqlsource.annotation.QueryConditionGreaterOrEqual;
import com.tx.core.jdbc.sqlsource.annotation.QueryConditionLess;
import com.tx.core.jdbc.sqlsource.annotation.UpdateAble;

/**
 * 客户通知消息明细<br/>
 * <功能详细描述>
 * 
 * @author  Administrator
 * @version  [版本号, 2016年9月5日]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
@Entity
public class NoticeMessage2ClientDetail {
    
    /** 站内消息id */
    @Id
    private String id;
    
    /** 站内消息优先级 */
    @UpdateAble
    private NoticeMessagePriorityEnum priority;
    
    /** 站内消息类型 */
    private String noticeMessageTypeId;
    
    /** 站内消息类型 */
    private String noticeMessageTypeCode;
    
    /** 站内消息类型 */
    private String noticeMessageTypeName;
    
    /** 站内消息标题 */
    @UpdateAble
    @QueryConditionEqual
    private String title;
    
    /** 站内消息内容 */
    @UpdateAble
    private String content;
    
    /** 发布时间 */
    @QueryConditionGreaterOrEqual(key = "minPublishDate")
    @QueryConditionLess(key = "maxPublishDate")
    private Date publishDate = new Date();;
    
    /** 是否撤销 */
    @UpdateAble
    @QueryConditionEqual
    private boolean revokeFlag = false;
    
    /** 撤销时间 */
    @UpdateAble
    private Date revokeDate;
    
    /** 创建时间 */
    @QueryConditionGreaterOrEqual(key = "minCreateDate")
    @QueryConditionLess(key = "maxCreateDate")
    private Date createDate = new Date();
    
    /** 最后更新时间 */
    @UpdateAble
    private Date lastUpdateDate = new Date();
    
    /** 站内消息id */
    private String noticeMessageId;
    
    /** 客户类型 */
    @UpdateAble
    @QueryConditionEqual
    private NoticeMessageClientTypeEnum clientType;
    
    /** 客户id */
    @QueryConditionEqual
    private String clientId;
    
    /** 客户电话号码 */
    @QueryConditionEqual
    private String clientPhoneNumber;
    
    /** 客户登录名 */
    @QueryConditionEqual
    private String clientLoginName;
    
    /** 客户用户名 */
    @QueryConditionEqual
    private String clientUserName;
    
    /** 接收消息时间 */
    private Date receiveDate;
    
    /** 是否阅读 */
    @UpdateAble
    @QueryConditionEqual
    private boolean readFlag;
    
    /** 阅读时间 */
    @UpdateAble
    private Date readDate;
    
    /** 是否删除 */
    @UpdateAble
    @QueryConditionEqual
    private boolean deleteFlag;
    
    /** 删除时间 */
    @UpdateAble
    private Date deleteDate;
    
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
     * @return 返回 noticeMessageTypeId
     */
    public String getNoticeMessageTypeId() {
        return noticeMessageTypeId;
    }
    
    /**
     * @param 对noticeMessageTypeId进行赋值
     */
    public void setNoticeMessageTypeId(String noticeMessageTypeId) {
        this.noticeMessageTypeId = noticeMessageTypeId;
    }
    
    /**
     * @return 返回 noticeMessageTypeCode
     */
    public String getNoticeMessageTypeCode() {
        return noticeMessageTypeCode;
    }
    
    /**
     * @param 对noticeMessageTypeCode进行赋值
     */
    public void setNoticeMessageTypeCode(String noticeMessageTypeCode) {
        this.noticeMessageTypeCode = noticeMessageTypeCode;
    }
    
    /**
     * @return 返回 noticeMessageTypeName
     */
    public String getNoticeMessageTypeName() {
        return noticeMessageTypeName;
    }
    
    /**
     * @param 对noticeMessageTypeName进行赋值
     */
    public void setNoticeMessageTypeName(String noticeMessageTypeName) {
        this.noticeMessageTypeName = noticeMessageTypeName;
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
     * @return 返回 clientId
     */
    public String getClientId() {
        return clientId;
    }
    
    /**
     * @param 对clientId进行赋值
     */
    public void setClientId(String clientId) {
        this.clientId = clientId;
    }
    
    /**
     * @return 返回 clientPhoneNumber
     */
    public String getClientPhoneNumber() {
        return clientPhoneNumber;
    }
    
    /**
     * @param 对clientPhoneNumber进行赋值
     */
    public void setClientPhoneNumber(String clientPhoneNumber) {
        this.clientPhoneNumber = clientPhoneNumber;
    }
    
    /**
     * @return 返回 clientLoginName
     */
    public String getClientLoginName() {
        return clientLoginName;
    }
    
    /**
     * @param 对clientLoginName进行赋值
     */
    public void setClientLoginName(String clientLoginName) {
        this.clientLoginName = clientLoginName;
    }
    
    /**
     * @return 返回 clientUserName
     */
    public String getClientUserName() {
        return clientUserName;
    }
    
    /**
     * @param 对clientUserName进行赋值
     */
    public void setClientUserName(String clientUserName) {
        this.clientUserName = clientUserName;
    }
    
    /**
     * @return 返回 receiveDate
     */
    public Date getReceiveDate() {
        return receiveDate;
    }
    
    /**
     * @param 对receiveDate进行赋值
     */
    public void setReceiveDate(Date receiveDate) {
        this.receiveDate = receiveDate;
    }
    
    /**
     * @return 返回 readFlag
     */
    public boolean isReadFlag() {
        return readFlag;
    }
    
    /**
     * @param 对readFlag进行赋值
     */
    public void setReadFlag(boolean readFlag) {
        this.readFlag = readFlag;
    }
    
    /**
     * @return 返回 readDate
     */
    public Date getReadDate() {
        return readDate;
    }
    
    /**
     * @param 对readDate进行赋值
     */
    public void setReadDate(Date readDate) {
        this.readDate = readDate;
    }
    
    /**
     * @return 返回 deleteFlag
     */
    public boolean isDeleteFlag() {
        return deleteFlag;
    }
    
    /**
     * @param 对deleteFlag进行赋值
     */
    public void setDeleteFlag(boolean deleteFlag) {
        this.deleteFlag = deleteFlag;
    }
    
    /**
     * @return 返回 deleteDate
     */
    public Date getDeleteDate() {
        return deleteDate;
    }
    
    /**
     * @param 对deleteDate进行赋值
     */
    public void setDeleteDate(Date deleteDate) {
        this.deleteDate = deleteDate;
    }

    /**
     * @return 返回 noticeMessageId
     */
    public String getNoticeMessageId() {
        return noticeMessageId;
    }

    /**
     * @param 对noticeMessageId进行赋值
     */
    public void setNoticeMessageId(String noticeMessageId) {
        this.noticeMessageId = noticeMessageId;
    }
}
