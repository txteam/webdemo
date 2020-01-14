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

import com.tx.local.basicdata.model.UserTypeEnum;

import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

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
@Data
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Notepad implements Serializable {
    
    /** 注释内容 */
    private static final long serialVersionUID = -7961496141263121358L;
    
    /** 主键ID */
    @Id
    @Column(nullable = false, length = 64)
    private String id;
    
    /** 记事本类型 */
    @Column(name = "type", nullable = false, length = 64)
    private NotepadTypeEnum type;
    
    /** 记事本分类ID;客户为空 */
    @Column(name = "catalogId", nullable = true, length = 64)
    private NotepadCatalog catalog;
    
    /** 记事本主题类型 */
    @Column(nullable = true, length = 64)
    private NotepadTopicTypeEnum topicType;
    
    /** 记事本主题ID */
    @Column(nullable = true, length = 64)
    private String topicId;
    
    /** 用户类型 */
    @Column(nullable = false, length = 64)
    private UserTypeEnum userType;
    
    /** 用户ID */
    @Column(nullable = false, length = 64)
    private String userId;
    
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
    
}
