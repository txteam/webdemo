/*
 * 描          述:  <描述>
 * 修  改   人:  Administrator
 * 修改时间:  2020年1月4日
 * <修改描述:>
 */
package com.tx.local.message.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import io.swagger.annotations.ApiModel;

/**
 * 私信<br/>
 * <功能详细描述>
 * 
 * @author  Administrator
 * @version  [版本号, 2020年1月4日]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
@Entity
@Table(name = "msg_private_message")
@ApiModel("私信")
public class PrivateMessage implements Serializable, Cloneable {
    
    /** 注释内容 */
    private static final long serialVersionUID = 3395690271963157384L;
    
    /** 私信id */
    @Id
    @Column(nullable = false)
    private String id;
    
    /** 所属虚中心id */
    @Column(nullable = false)
    private String vcid;
    
    /** 分类：私信，提醒等 */
    @Column(name = "catalogId", nullable = true, updatable = false,length=64)
    private MessageCatalog catalog;
    
    /** 客户类型 */
    @Column(nullable = false)
    private MessageUserTypeEnum userType;
    
    /** 接收者：可以为空 */
    @Column(nullable = false, updatable = false, length = 64)
    private String userId;
    
    /** 接收者：可以为空 */
    @Column(nullable = true, updatable = false, length = 64)
    private String username;
    
    /** 用户id */
    @Column(nullable = true, updatable = false, length = 64)
    private String senderId;
    
    /** 发送者：可以为空 */
    @Column(nullable = true, updatable = false, length = 64)
    private String sender;
    
    /** 站内消息标题 */
    @Column(nullable = false, updatable = true, length = 100)
    private String title;
    
    /** 站内消息内容 */
    @Column(nullable = true, updatable = true, length = 4000)
    private String content;
    
    /** 是否未读 */
    //注: read的被动语态也是read,但read在创建脚本时为特殊字符需要添添加引号才能创建表成功
    @Column(updatable = true)
    private boolean unread;
    
    /** 阅读时间 */
    @Column(nullable = true, updatable = true)
    private Date readDate;
    
    /** 最后修改时间 */
    @Column(nullable = false, updatable = true)
    private Date lastUpdateDate;
    
    /** 最后更新用户 */
    @Column(nullable = true, updatable = true)
    private String lastUpdateUserId;
    
    /** 创建时间 */
    @Column(nullable = false, updatable = false)
    private Date createDate;
    
    /** 创建用户 */
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
     * @return 返回 catalog
     */
    public MessageCatalog getCatalog() {
        return catalog;
    }
    
    /**
     * @param 对catalog进行赋值
     */
    public void setCatalog(MessageCatalog catalog) {
        this.catalog = catalog;
    }
    
    /**
     * @return 返回 userType
     */
    public MessageUserTypeEnum getUserType() {
        return userType;
    }
    
    /**
     * @param 对userType进行赋值
     */
    public void setUserType(MessageUserTypeEnum userType) {
        this.userType = userType;
    }
    
    /**
     * @return 返回 userId
     */
    public String getUserId() {
        return userId;
    }
    
    /**
     * @param 对userId进行赋值
     */
    public void setUserId(String userId) {
        this.userId = userId;
    }
    
    /**
     * @return 返回 username
     */
    public String getUsername() {
        return username;
    }
    
    /**
     * @param 对username进行赋值
     */
    public void setUsername(String username) {
        this.username = username;
    }
    
    /**
     * @return 返回 senderId
     */
    public String getSenderId() {
        return senderId;
    }
    
    /**
     * @param 对senderId进行赋值
     */
    public void setSenderId(String senderId) {
        this.senderId = senderId;
    }
    
    /**
     * @return 返回 sender
     */
    public String getSender() {
        return sender;
    }
    
    /**
     * @param 对sender进行赋值
     */
    public void setSender(String sender) {
        this.sender = sender;
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
     * @return 返回 unread
     */
    public boolean isUnread() {
        return unread;
    }
    
    /**
     * @param 对unread进行赋值
     */
    public void setUnread(boolean unread) {
        this.unread = unread;
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
