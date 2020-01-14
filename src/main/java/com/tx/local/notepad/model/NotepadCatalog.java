/*
 * 描          述:  <描述>
 * 修  改   人:  Administrator
 * 修改时间:  2020年1月14日
 * <修改描述:>
 */
package com.tx.local.notepad.model;

import java.io.Serializable;

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
 * 记事本分类<br/>
 * <功能详细描述>
 * 
 * @author  Administrator
 * @version  [版本号, 2020年1月14日]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
@Entity
@Table(name = "note_notepad_catalog")
@ApiModel("记事本分类")
@Data
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class NotepadCatalog implements Serializable{
    
    /** 注释内容 */
    private static final long serialVersionUID = -6051543988330268097L;

    /** 记事本分类唯一键 */
    @Id
    @Column(nullable = false, length = 64)
    private String id;
    
    @Column(name = "parentId", nullable = false, length = 64)
    private String parentId;
    
    private String name;
    
    private String remark;
    
    
}
