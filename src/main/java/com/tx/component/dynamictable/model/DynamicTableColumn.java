/*
 * 描          述:  <描述>
 * 修  改   人:  brady
 * 修改时间:  2013-10-12
 * <修改描述:>
 */
package com.tx.component.dynamictable.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

import com.tx.core.ddlutil.dialect.Dialect4DDL;
import com.tx.core.ddlutil.model.JdbcTypeEnum;
import com.tx.core.ddlutil.model.TableColumnDef;
import com.tx.core.ddlutil.model.TableIndexDef;
import com.tx.core.exceptions.util.AssertUtils;

/**
 * 模板表字段<br/>
 * <功能详细描述>
 * 
 * @author  brady
 * @version  [版本号, 2013-10-12]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
@Table(name = "dt_table_column")
public class DynamicTableColumn implements Serializable, TableColumnDef,
        TableIndexDef {
    
    /** 注释内容 */
    private static final long serialVersionUID = -3031694155181561970L;
    
    /** 模板字段唯一id */
    @Id
    private String id;
    
    /** 动态表id */
    @Column(name = "dynamicTableId")
    private DynamicTable dynamicTable;
    
    /** java类型 */
    private Class<?> javaType;
    
    /** 对应的属性名称 */
    private String propertyName;
    
    /** 数据库类型 */
    private JdbcTypeEnum jdbcType;
    
    /** 字段类型字符串 */
    private String columnType;
    
    private int size;
    
    private int scale;
    
    /** 默认值 */
    private String defaultValue;
    
    /** 是否必填：默认非必填 */
    private boolean required = false;
    
    /** 是否唯一：默认非唯一*/
    private boolean primaryKey = false;
    
    /** 字段对应的实际字段名：实际的数据库字段名 */
    private String columnName;
    
    /* ---- 可修改属性start ---- */
    
    /** 排序优先级 */
    private int orderPriority;
    
    /** 字段标签（用于显示，一般为中文） */
    private String label;
    
    /** 字段注释 */
    private String comment;
    
    /** 是否是唯一键 */
    private boolean uniqueKey = false;
    
    /** 是否索引字段：默认非索引*/
    private boolean indexColumn = false;
    
    /** 索引字段名称：如果两个字段为联合索引，则索引名称相同 */
    private String indexName;
    
    /** <默认构造函数> */
    public DynamicTableColumn() {
    }
    
    /** <默认构造函数> */
    public DynamicTableColumn(Class<?> javaType, String propertyName,
            String columnName, JdbcTypeEnum jdbcType, String columnType,
            int size, int scale) {
        super();
        AssertUtils.notNull(javaType, "jdbcType is null.");
        AssertUtils.notEmpty(propertyName, "propertyName is empty.");
        AssertUtils.notEmpty(columnName, "columnName is empty.");
        AssertUtils.notNull(jdbcType, "jdbcType is null.");
        AssertUtils.notEmpty(columnType, "columnType is empty.");
        
        this.jdbcType = jdbcType;
        this.propertyName = propertyName;
        this.javaType = javaType;
        this.columnName = columnName;
        this.columnType = columnType;
        this.size = size;
        this.scale = scale;
    }
    
    
    
    /**
     * @return
     */
    @Override
    public String getColumnNames() {
        // TODO Auto-generated method stub
        return null;
    }

    /**
     * @param ddlDialect
     * @return
     */
    @Override
    public String getColumnType(Dialect4DDL ddlDialect) {
        // TODO Auto-generated method stub
        return null;
    }

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
     * @return 返回 dynamicTable
     */
    public DynamicTable getDynamicTable() {
        return dynamicTable;
    }
    
    /**
     * @param 对dynamicTable进行赋值
     */
    public void setDynamicTable(DynamicTable dynamicTable) {
        this.dynamicTable = dynamicTable;
    }
    
    /**
     * @return 返回 javaType
     */
    public Class<?> getJavaType() {
        return javaType;
    }
    
    /**
     * @param 对javaType进行赋值
     */
    public void setJavaType(Class<?> javaType) {
        this.javaType = javaType;
    }
    
    /**
     * @return 返回 jdbcType
     */
    public JdbcTypeEnum getJdbcType() {
        return jdbcType;
    }
    
    /**
     * @param 对jdbcType进行赋值
     */
    public void setJdbcType(JdbcTypeEnum jdbcType) {
        this.jdbcType = jdbcType;
    }
    
    /**
     * @return 返回 propertyName
     */
    public String getPropertyName() {
        return propertyName;
    }
    
    /**
     * @param 对propertyName进行赋值
     */
    public void setPropertyName(String propertyName) {
        this.propertyName = propertyName;
    }
    
    /**
     * @return 返回 columnName
     */
    public String getColumnName() {
        return columnName;
    }
    
    /**
     * @param 对columnName进行赋值
     */
    public void setColumnName(String columnName) {
        this.columnName = columnName;
    }
    
    /**
     * @return 返回 label
     */
    public String getLabel() {
        return label;
    }
    
    /**
     * @param 对label进行赋值
     */
    public void setLabel(String label) {
        this.label = label;
    }
    
    /**
     * @return 返回 comment
     */
    public String getComment() {
        return comment;
    }
    
    /**
     * @param 对comment进行赋值
     */
    public void setComment(String comment) {
        this.comment = comment;
    }
    
    /**
     * @return 返回 columnType
     */
    public String getColumnType() {
        return columnType;
    }
    
    /**
     * @param 对columnType进行赋值
     */
    public void setColumnType(String columnType) {
        this.columnType = columnType;
    }
    
    /**
     * @return 返回 size
     */
    public int getSize() {
        return size;
    }
    
    /**
     * @param 对size进行赋值
     */
    public void setSize(int size) {
        this.size = size;
    }
    
    /**
     * @return 返回 scale
     */
    public int getScale() {
        return scale;
    }
    
    /**
     * @param 对scale进行赋值
     */
    public void setScale(int scale) {
        this.scale = scale;
    }
    
    /**
     * @return 返回 required
     */
    public boolean isRequired() {
        return required;
    }
    
    /**
     * @param 对required进行赋值
     */
    public void setRequired(boolean required) {
        this.required = required;
    }
    
    /**
     * @return 返回 primaryKey
     */
    public boolean isPrimaryKey() {
        return primaryKey;
    }
    
    /**
     * @param 对primaryKey进行赋值
     */
    public void setPrimaryKey(boolean primaryKey) {
        this.primaryKey = primaryKey;
    }
    
    /**
     * @return 返回 uniqueKey
     */
    public boolean isUniqueKey() {
        return uniqueKey;
    }
    
    /**
     * @param 对uniqueKey进行赋值
     */
    public void setUniqueKey(boolean uniqueKey) {
        this.uniqueKey = uniqueKey;
    }
    
    /**
     * @return 返回 defaultValue
     */
    public String getDefaultValue() {
        return defaultValue;
    }
    
    /**
     * @param 对defaultValue进行赋值
     */
    public void setDefaultValue(String defaultValue) {
        this.defaultValue = defaultValue;
    }
    
    /**
     * @return 返回 indexColumn
     */
    public boolean isIndexColumn() {
        return indexColumn;
    }
    
    /**
     * @param 对indexColumn进行赋值
     */
    public void setIndexColumn(boolean indexColumn) {
        this.indexColumn = indexColumn;
    }
    
    /**
     * @return 返回 indexName
     */
    public String getIndexName() {
        return indexName;
    }
    
    /**
     * @param 对indexName进行赋值
     */
    public void setIndexName(String indexName) {
        this.indexName = indexName;
    }
    
    /**
     * @return 返回 orderPriority
     */
    public int getOrderPriority() {
        return orderPriority;
    }
    
    /**
     * @param 对orderPriority进行赋值
     */
    public void setOrderPriority(int orderPriority) {
        this.orderPriority = orderPriority;
    }
}
