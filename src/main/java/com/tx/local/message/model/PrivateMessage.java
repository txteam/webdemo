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
public class PrivateMessage implements Serializable {
    
    /** 注释内容 */
    private static final long serialVersionUID = 3395690271963157384L;
    
    /** 私信id */
    @Id
    @Column(nullable = false)
    private String id;
    
    /** 所属虚中心id */
    @Column(nullable = false)
    private String vcid;
    
    /** 私信类型： 发送，接收 */
    @Column(nullable = false, updatable = false)
    private PrivateMessageTypeEnum type;
    
    /** 分类：私信，提醒等 */
    @Column(nullable = false, updatable = false)
    private PrivateMessageCatalogEnum catalog;
    
    /** 客户类型 */
    @Column(nullable = false)
    private MsgUserTypeEnum userType;
    
    /** 用户id */
    @Column(nullable = false)
    private String userId;
    
    /** 发送和接收的私信会被复制一份，生成一条发送，一条接收信息，接收方的sourceId为发送方的消息id */
    @Column(nullable = true, updatable = false, length = 64)
    private String sourceId;
    
    /** 站内消息标题 */
    @Column(nullable = false, updatable = true, length = 100)
    private String title;
    
    /** 站内消息内容 */
    @Column(length = 4000)
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
     * @return 返回 type
     */
    public PrivateMessageTypeEnum getType() {
        return type;
    }
    
    /**
     * @param 对type进行赋值
     */
    public void setType(PrivateMessageTypeEnum type) {
        this.type = type;
    }
    
    /**
     * @return 返回 catalog
     */
    public PrivateMessageCatalogEnum getCatalog() {
        return catalog;
    }
    
    /**
     * @param 对catalog进行赋值
     */
    public void setCatalog(PrivateMessageCatalogEnum catalog) {
        this.catalog = catalog;
    }
    
    /**
     * @return 返回 userType
     */
    public MsgUserTypeEnum getUserType() {
        return userType;
    }
    
    /**
     * @param 对userType进行赋值
     */
    public void setUserType(MsgUserTypeEnum userType) {
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
     * @return 返回 sourceId
     */
    public String getSourceId() {
        return sourceId;
    }
    
    /**
     * @param 对sourceId进行赋值
     */
    public void setSourceId(String sourceId) {
        this.sourceId = sourceId;
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
