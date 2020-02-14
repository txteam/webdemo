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
public class ContentInfo {
    
    /** 主键id */
    @Id
    private String id;
    
    /** 内容类型：一般内容类型于内容所在分类的类型一致 */
    private ContentInfoTypeEnum type;
    
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
    
    
}
