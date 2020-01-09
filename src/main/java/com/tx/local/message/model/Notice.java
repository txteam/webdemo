/*
 * 描          述:  <描述>
 * 修  改   人:  Administrator
 * 修改时间:  2020年1月5日
 * <修改描述:>
 */
package com.tx.local.message.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * 站内公告消息<br/>
 * <功能详细描述>
 * 
 * @author  Administrator
 * @version  [版本号, 2020年1月5日]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
@Entity
@Table(name = "msg_notice")
public class Notice {
    
    /** 站内消息id */
    @Id
    private String id;
    
    /** 站内消息类型 */
    @ManyToOne
    @JoinColumn(name = "noticeCatalogId")
    private NoticeCatalog noticeCatalog;
    
    /** 站内消息优先级 */
    private NoticePriorityEnum priority = NoticePriorityEnum.PT;
    
    /** 客户类型 */
    private MsgUserTypeEnum userType;
    
    /** 站内消息标题 */
    private String title;
    
    /** 站内消息内容 */
    private String content;
    
    /** 是否撤销 */
    private boolean published = false;
    
    /** 发布时间 */
    private Date publishDate = new Date();
    
    /** 是否有效 */
    private boolean valid;
    
    /** 无效时间 */
    private Date invalidDate;
    
    /** 创建时间 */
    private Date createDate = new Date();
    
    /** 最后更新时间 */
    private Date lastUpdateDate = new Date();
    
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
     * @return 返回 noticeCatalog
     */
    public NoticeCatalog getNoticeCatalog() {
        return noticeCatalog;
    }
    
    /**
     * @param 对noticeCatalog进行赋值
     */
    public void setNoticeCatalog(NoticeCatalog noticeCatalog) {
        this.noticeCatalog = noticeCatalog;
    }
    
    /**
     * @return 返回 priority
     */
    public NoticePriorityEnum getPriority() {
        return priority;
    }
    
    /**
     * @param 对priority进行赋值
     */
    public void setPriority(NoticePriorityEnum priority) {
        this.priority = priority;
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
     * @return 返回 published
     */
    public boolean isPublished() {
        return published;
    }
    
    /**
     * @param 对published进行赋值
     */
    public void setPublished(boolean published) {
        this.published = published;
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
     * @return 返回 invalidDate
     */
    public Date getInvalidDate() {
        return invalidDate;
    }
    
    /**
     * @param 对invalidDate进行赋值
     */
    public void setInvalidDate(Date invalidDate) {
        this.invalidDate = invalidDate;
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
