/*
 * 描          述:  <描述>
 * 修  改   人:  Administrator
 * 修改时间:  2020年1月5日
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
 * 公告(通知)消息<br/>
 * <功能详细描述>
 * 
 * @author  Administrator
 * @version  [版本号, 2020年1月5日]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
@Entity
@Table(name = "msg_notice_message")
@ApiModel("公告消息")
public class NoticeMessage implements Serializable {
    
    /** 注释内容 */
    private static final long serialVersionUID = 3841369791135814499L;
    
    /** 站内消息id */
    @Id
    private String id;
    
    /** 所属虚中心：一版来说公告消息只能针对同一虚中心内部 */
    //该字段经过几天思考，暂定如下：不能为空，如果一条消息需要让多个虚中心看见，最好考虑多个虚中心都发送一份。
    //如果加入vcid is null时，则对所有都可见，未来为了兼容该设计可能会出现更多复杂的场景
    @Column(nullable = false, length = 64, updatable = false)
    private String vcid;
    
    /** 站内消息类型 */
    @Column(name = "catalogId", nullable = false, length = 64, updatable = false)
    private NoticeCatalog noticeCatalog;
    
    /** 站内消息优先级 */
    @Column(name = "priorityId", nullable = true, length = 64, updatable = true)
    private NoticePriority priority;
    
    /** 站内消息标题 */
    @Column(nullable = false, updatable = true, length = 100)
    private String title;
    
    /** 站内消息内容 */
    @Column(length = 4000)
    private String content;
    
    /** 是否发布 */
    private boolean published = false;
    
    /** 发布时间 */
    private Date publishDate = new Date();
    
    /** 是否有效 */
    private boolean valid;
    
    /** 无效时间 */
    private Date invalidDate;
    
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
    public NoticePriority getPriority() {
        return priority;
    }

    /**
     * @param 对priority进行赋值
     */
    public void setPriority(NoticePriority priority) {
        this.priority = priority;
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
