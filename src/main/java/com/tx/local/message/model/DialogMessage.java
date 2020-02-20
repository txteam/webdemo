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
 * 会话消息：包含请求、意见、建议、提问<br/>
 *      CommunicationMessage
 * 包含请求、意见、建议、提问、回答、回复、等内容<br/>
 * 通过catalog进行分类，可以存在状态
 * 状态归属为具体的分类
 * 可通过配置进行初始化
 *    ：信息发布分为两种，允许html的内容以及不允许的
 *    客户发送的信息需要经不允许的接口填入，防止持久性注入攻击
 * <功能详细描述>
 * 
 * @author  Administrator
 * @version  [版本号, 2020年1月4日]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
@Entity
@Table(name = "msg_dialog_message")
@ApiModel("会话消息")
public class DialogMessage implements Serializable {
    
    /** 注释内容 */
    private static final long serialVersionUID = 760874841288229239L;
    
    /** 主键  */
    @Id
    @Column(nullable = false)
    private String id;
    
    /** 回复信息时，填入被回复的信息 */
    @Column(nullable = true)
    private String parentId;
    
    /** 所属虚中心id */
    @Column(nullable = false)
    private String vcid;
    
    /** 会话消息类型 */
    @Column(nullable = false)
    private DialogMessageTypeEnum type;
    
    /** 关联类型 */
    @Column(nullable = true)
    private DialogTopicTypeEnum topicType;
    
    /** 关联id */
    @Column(nullable = true)
    private String topicId;
    
    /** 客户类型 */
    @Column(nullable = true)
    private MessageUserTypeEnum userType;
    
    /** 用户id */
    @Column(nullable = true)
    private String userId;
    
    /** 站内消息标题 */
    @Column(nullable = false, updatable = true, length = 100)
    private String title;
    
    /** 站内消息内容 */
    @Column(length = 4000)
    private String content;
    
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
     * @return 返回 parentId
     */
    public String getParentId() {
        return parentId;
    }
    
    /**
     * @param 对parentId进行赋值
     */
    public void setParentId(String parentId) {
        this.parentId = parentId;
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
    public DialogMessageTypeEnum getType() {
        return type;
    }
    
    /**
     * @param 对type进行赋值
     */
    public void setType(DialogMessageTypeEnum type) {
        this.type = type;
    }
    
    /**
     * @return 返回 topicType
     */
    public DialogTopicTypeEnum getTopicType() {
        return topicType;
    }
    
    /**
     * @param 对topicType进行赋值
     */
    public void setTopicType(DialogTopicTypeEnum topicType) {
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
