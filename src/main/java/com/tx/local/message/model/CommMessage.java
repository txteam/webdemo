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

import com.tx.core.support.json.JSONAttributesSupport;

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
 * 
 * <功能详细描述>
 * 
 * @author  Administrator
 * @version  [版本号, 2020年1月4日]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
@Entity
@Table(name = "msg_comm_message")
@ApiModel("会话消息")
public class CommMessage implements Serializable, JSONAttributesSupport {
    
    /** 注释内容 */
    private static final long serialVersionUID = 760874841288229239L;
    
    /** 主键  */
    @Id
    @Column(name = "id", nullable = false)
    private String id;
    
    /** 回复信息时，填入被回复的信息 */
    @Column(name = "parentId", nullable = true)
    private String parentId;
    
    /** 所属虚中心id */
    @Column(name = "vcid", nullable = false)
    private String vcid;
    
    /** 客户类型 */
    @Column(name = "userType", nullable = false)
    private MsgUserTypeEnum userType;
    
    /** 用户id */
    @Column(name = "userId", nullable = false)
    private String userId;
    
    /** 分类 */
    @Column(name = "catalogId", nullable = false)
    private CommMessageCatalog catalog;
    
    /** 状态 */
    @Column(name = "statusId", nullable = true)
    private CommMessageStatus status;
    
    /** 关联类型 */
    @Column(name = "refType", nullable = true)
    private MsgRefTypeEnum refType;
    
    /** 关联id */
    @Column(name = "refId", nullable = true)
    private String refId;
    
    /** 建议类容 */
    @Column(name = "content", length = 4000)
    private String content;
    
    /** 额外的其他属性 */
    @Column(name = "attributes", length = 4000)
    private String attributes;
    
    /** 最后修改时间 */
    @Column(name = "lastUpdateDate", nullable = false, updatable = true)
    private Date lastUpdateDate;
    
    /** 创建时间 */
    @Column(name = "createDate", nullable = false, updatable = false)
    private Date createDate;
    
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
     * @return 返回 catalog
     */
    public CommMessageCatalog getCatalog() {
        return catalog;
    }
    
    /**
     * @param 对catalog进行赋值
     */
    public void setCatalog(CommMessageCatalog catalog) {
        this.catalog = catalog;
    }
    
    /**
     * @return 返回 status
     */
    public CommMessageStatus getStatus() {
        return status;
    }
    
    /**
     * @param 对status进行赋值
     */
    public void setStatus(CommMessageStatus status) {
        this.status = status;
    }
    
    /**
     * @return 返回 refType
     */
    public MsgRefTypeEnum getRefType() {
        return refType;
    }
    
    /**
     * @param 对refType进行赋值
     */
    public void setRefType(MsgRefTypeEnum refType) {
        this.refType = refType;
    }
    
    /**
     * @return 返回 refId
     */
    public String getRefId() {
        return refId;
    }
    
    /**
     * @param 对refId进行赋值
     */
    public void setRefId(String refId) {
        this.refId = refId;
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
}
