/*
 * 描          述:  <描述>
 * 修  改   人:  Administrator
 * 修改时间:  2020年1月14日
 * <修改描述:>
 */
package com.tx.local.notepad.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.tx.core.support.json.JSONAttributesSupport;

import io.swagger.annotations.ApiModel;

/**
 * 记事本<br/>
 * <功能详细描述>
 * 
 * @author  Administrator
 * @version  [版本号, 2020年1月14日]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
@Entity
@Table(name = "note_notepad")
@ApiModel("记事本")
public class Notepad implements Serializable, JSONAttributesSupport {
    
    /** 注释内容 */
    private static final long serialVersionUID = -7961496141263121358L;
    
    /** 主键ID */
    @Id
    @Column(nullable = false, length = 64)
    private String id;
    
    /** 虚中心ID */
    @Column(nullable = false, length = 64)
    private String vcid;
    
    /** 记事本类型 */
    @Column(nullable = false, length = 64)
    private NotepadTypeEnum type;
    
    /** 记事本目录id */
    @Column(name = "catalogId", nullable = true, length = 64)
    private NotepadCatalog catalog;
    
    /** 记事本主题类型 */
    @Column(nullable = false, length = 64)
    private NotepadTopicTypeEnum topicType;
    
    /** 记事本主题ID */
    @Column(nullable = false, length = 64)
    private String topicId;
    
    /** 标题 */
    @Column(nullable = false, updatable = true, length = 100)
    private String title;
    
    /** 内容 */
    @Column(nullable = true, updatable = true, length = 4000)
    private String content;
    
    /** 备注 */
    @Column(nullable = true, updatable = true, length = 500)
    private String remark;
    
    /** 额外的参数 */
    @Column(nullable = true, updatable = true, length = 4000)
    private String attributes;
    
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
    public NotepadTypeEnum getType() {
        return type;
    }

    /**
     * @param 对type进行赋值
     */
    public void setType(NotepadTypeEnum type) {
        this.type = type;
    }

    /**
     * @return 返回 catalog
     */
    public NotepadCatalog getCatalog() {
        return catalog;
    }

    /**
     * @param 对catalog进行赋值
     */
    public void setCatalog(NotepadCatalog catalog) {
        this.catalog = catalog;
    }

    /**
     * @return 返回 topicType
     */
    public NotepadTopicTypeEnum getTopicType() {
        return topicType;
    }

    /**
     * @param 对topicType进行赋值
     */
    public void setTopicType(NotepadTopicTypeEnum topicType) {
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
