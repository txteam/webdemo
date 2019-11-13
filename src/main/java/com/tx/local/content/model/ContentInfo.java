/*
 * 描          述:  <描述>
 * 修  改   人:  Administrator
 * 修改时间:  2016年8月3日
 * <修改描述:>
 */
package com.tx.local.content.model;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.tx.core.support.entrysupport.model.EntityEntry;
import com.tx.core.support.entrysupport.model.EntryAble;

import io.swagger.annotations.ApiModel;

/**
 * 内容信息管理<br/>
 * <功能详细描述>
 * 
 * @author  Administrator
 * @version  [版本号, 2016年8月3日]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
@Entity
@Table(name = "ci_content_info")
@ApiModel("内容信息")
public class ContentInfo implements EntryAble<EntityEntry> {
    
    /** 主键id */
    @Id
    private String id;
    
    /** 内容类型：一般内容类型于内容所在分类的类型一致 */
    @Column(name = "typeCode")
    private ContentInfoType type;
    
    /** 内容分类 */
    @Column(name = "categoryCode")
    private ContentInfoCategory category;
    
    /** 信息级别： */
    @Column(name = "levelCode")
    private ContentInfoLevel level;
    
    /** 名称 */
    private String name;
    
    /** 标题 */
    private String title;
    
    /** 内容字段：内容 */
    private String content;
    
    /** 内容文件id：存储文件id */
    private String fileId;
    
    /** 文件相对路径：存储相对路径 */
    private String fileUrl;
    
    /** 链接的url */
    private String linkUrl;
    
    /** 关键字：便于信息检索 */
    private String keywords;
    
    /** 备注 */
    private String remark;
    
    /** 是否有效 */
    private boolean valid = true;
    
    /** 排序值 */
    private int orderIndex = 0;
    
    /** 最后更新人 */
    private String lastUpdateOperatorId;
    
    /** 最后更新时间 */
    private Date lastUpdateDate;
    
    /** 创建人 */
    private String createOperatorId;
    
    /** 创建时间 */
    private Date createDate;
    
    /** 分项属性列表 */
    @Transient
    private List<EntityEntry> entryList;
    
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
     * @return 返回 type
     */
    public ContentInfoType getType() {
        return type;
    }

    /**
     * @param 对type进行赋值
     */
    public void setType(ContentInfoType type) {
        this.type = type;
    }

    /**
     * @return 返回 category
     */
    public ContentInfoCategory getCategory() {
        return category;
    }
    
    /**
     * @param 对category进行赋值
     */
    public void setCategory(ContentInfoCategory category) {
        this.category = category;
    }
    
    /**
     * @return 返回 level
     */
    public ContentInfoLevel getLevel() {
        return level;
    }
    
    /**
     * @param 对level进行赋值
     */
    public void setLevel(ContentInfoLevel level) {
        this.level = level;
    }
    
    /**
     * @return 返回 name
     */
    public String getName() {
        return name;
    }
    
    /**
     * @param 对name进行赋值
     */
    public void setName(String name) {
        this.name = name;
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
     * @return 返回 fileId
     */
    public String getFileId() {
        return fileId;
    }

    /**
     * @param 对fileId进行赋值
     */
    public void setFileId(String fileId) {
        this.fileId = fileId;
    }

    /**
     * @return 返回 fileUrl
     */
    public String getFileUrl() {
        return fileUrl;
    }

    /**
     * @param 对fileUrl进行赋值
     */
    public void setFileUrl(String fileUrl) {
        this.fileUrl = fileUrl;
    }

    /**
     * @return 返回 linkUrl
     */
    public String getLinkUrl() {
        return linkUrl;
    }
    
    /**
     * @param 对linkUrl进行赋值
     */
    public void setLinkUrl(String linkUrl) {
        this.linkUrl = linkUrl;
    }
    
    /**
     * @return 返回 keywords
     */
    public String getKeywords() {
        return keywords;
    }
    
    /**
     * @param 对keywords进行赋值
     */
    public void setKeywords(String keywords) {
        this.keywords = keywords;
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
     * @return 返回 orderIndex
     */
    public int getOrderIndex() {
        return orderIndex;
    }
    
    /**
     * @param 对orderIndex进行赋值
     */
    public void setOrderIndex(int orderIndex) {
        this.orderIndex = orderIndex;
    }
    
    /**
     * @return 返回 lastUpdateOperatorId
     */
    public String getLastUpdateOperatorId() {
        return lastUpdateOperatorId;
    }
    
    /**
     * @param 对lastUpdateOperatorId进行赋值
     */
    public void setLastUpdateOperatorId(String lastUpdateOperatorId) {
        this.lastUpdateOperatorId = lastUpdateOperatorId;
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
     * @return 返回 createOperatorId
     */
    public String getCreateOperatorId() {
        return createOperatorId;
    }
    
    /**
     * @param 对createOperatorId进行赋值
     */
    public void setCreateOperatorId(String createOperatorId) {
        this.createOperatorId = createOperatorId;
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
     * @return 返回 entryList
     */
    public List<EntityEntry> getEntryList() {
        return entryList;
    }
    
    /**
     * @param 对entryList进行赋值
     */
    public void setEntryList(List<EntityEntry> entryList) {
        this.entryList = entryList;
    }
}
