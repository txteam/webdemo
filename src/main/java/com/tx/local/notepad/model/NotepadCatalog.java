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

import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * 记事本目录<br/>
 * <功能详细描述>
 * 
 * @author  Administrator
 * @version  [版本号, 2020年1月14日]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
@Entity
@Table(name = "note_notepad_catalog")
@ApiModel("记事本目录")
@Data
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class NotepadCatalog implements Serializable {
    
    /** 注释内容 */
    private static final long serialVersionUID = -6051543988330268097L;
    
    /** 记事本分类唯一键 */
    @Id
    @Column(nullable = false, length = 64)
    private String id;
    
    /** 虚中心ID */
    @Column(nullable = false, length = 64)
    private String vcid;
    
    /** 上级记事本分类ID */
    @Column(name = "parentId", nullable = true, length = 64)
    private String parentId;
    
    /** 记事本类型 */
    @Column(name = "type", nullable = false, length = 64)
    private NotepadTypeEnum type;
    
    /** 记事本主题类型 */
    @Column(nullable = false, length = 64)
    private NotepadTopicTypeEnum topicType;
    
    /** 记事本主题ID */
    @Column(nullable = false, length = 64)
    private String topicId;
    
    /** 记事本分类名称 */
    @Column(nullable = false, updatable = true, length = 100)
    private String name;
    
    /** 记事本分类备注 */
    @Column(nullable = true, updatable = true, length = 500)
    private String remark;
    
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
    
}
