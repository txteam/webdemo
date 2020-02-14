/*
 * 描          述:  <描述>
 * 修  改   人:  Administrator
 * 修改时间:  2020年1月14日
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
 * 信息附件<br/>
 * <功能详细描述>
 * 
 * @author  Administrator
 * @version  [版本号, 2020年1月14日]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
@Entity
@Table(name = "msg_message_attachment")
@ApiModel("消息附件")
public class MessageAttachment implements Serializable {
    
    /** 注释内容 */
    private static final long serialVersionUID = -846811523136172528L;
    
    /** 唯一键id */
    @Id
    @Column(nullable = false, updatable = false)
    private String id;
    
    /** 所属虚中心id */
    //在该类中允许虚中心为null，当虚中心为空时，则为多虚中心公用
    @Column(nullable = false, updatable = false)
    private String vcid;
    
    /** 消息类型 */
    @Column(nullable = false, updatable = false)
    private MessageTypeEnum messageType;
    
    /** 消息id */
    @Column(nullable = false, updatable = false)
    private String messageId;
    
    /** 附件id */
    @Column(nullable = false)
    private String attachmentId;
    
    /** 附件id */
    @Column(nullable = false)
    private String attachmentUrl;
    
    /** 最后修改时间 */
    @Column(nullable = false, updatable = true)
    private Date lastUpdateDate;
    
    /** 创建时间 */
    @Column(nullable = false, updatable = false)
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
     * @return 返回 messageType
     */
    public MessageTypeEnum getMessageType() {
        return messageType;
    }
    
    /**
     * @param 对messageType进行赋值
     */
    public void setMessageType(MessageTypeEnum messageType) {
        this.messageType = messageType;
    }
    
    /**
     * @return 返回 messageId
     */
    public String getMessageId() {
        return messageId;
    }
    
    /**
     * @param 对messageId进行赋值
     */
    public void setMessageId(String messageId) {
        this.messageId = messageId;
    }
    
    /**
     * @return 返回 attachmentId
     */
    public String getAttachmentId() {
        return attachmentId;
    }
    
    /**
     * @param 对attachmentId进行赋值
     */
    public void setAttachmentId(String attachmentId) {
        this.attachmentId = attachmentId;
    }
    
    /**
     * @return 返回 attachmentUrl
     */
    public String getAttachmentUrl() {
        return attachmentUrl;
    }
    
    /**
     * @param 对attachmentUrl进行赋值
     */
    public void setAttachmentUrl(String attachmentUrl) {
        this.attachmentUrl = attachmentUrl;
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
