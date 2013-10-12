/*
 * 描          述:  <描述>
 * 修  改   人:  brady
 * 修改时间:  2013-10-12
 * <修改描述:>
 */
package com.tx.component.template.model;

import java.io.Serializable;

import javax.persistence.Table;

/**
 * 模板表字段<br/>
 * <功能详细描述>
 * 
 * @author  brady
 * @version  [版本号, 2013-10-12]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
@Table(name = "TEMPLATE_COLUMN")
public class TemplateColumn implements Serializable, Cloneable {
    
    /** 注释内容 */
    private static final long serialVersionUID = -5052803226029030696L;
    
    /** 模板字段唯一id */
    private String id;
    
    /** 模板表所属模板表id */
    private String templateTableId;
    
    /** 
     * 模板表字段类型 
     * 由该类型决定对应数据的表中结构
     */
    private TemplateColumnTypeEnum columnType;
    
    
}
