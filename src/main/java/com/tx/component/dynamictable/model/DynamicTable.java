///*
// * 描          述:  <描述>
// * 修  改   人:  brady
// * 修改时间:  2013-10-12
// * <修改描述:>
// */
//package com.tx.component.dynamictable.model;
//
//import java.io.Serializable;
//import java.util.ArrayList;
//import java.util.Date;
//import java.util.List;
//
//import javax.persistence.Column;
//import javax.persistence.Entity;
//import javax.persistence.Id;
//import javax.persistence.OneToMany;
//import javax.persistence.Table;
//import javax.persistence.Transient;
//
//import com.tx.core.ddlutil.model.TableDef;
//
///**
// * 模板表<br/>
// * <功能详细描述>
// * 
// * @author  brady
// * @version  [版本号, 2013-10-12]
// * @see  [相关类/方法]
// * @since  [产品/模块版本]
// */
//@Entity
//@Table(name = "dt_table")
//public class DynamicTable implements Serializable, TableDef {
//    
//    /** 注释内容 */
//    private static final long serialVersionUID = 8275499359664274226L;
//    
//    /** 模板表id:具体的一张模板表，id唯一对应唯一的tableName */
//    @Id
//    @Column(nullable = false, length = 64)
//    private String id;
//    
//    /** 模板表：编码 */
//    @Column(nullable = false, length = 64)
//    private String code;
//    
//    /** 模板表业务类型：填报表、记录信息、商品等情况... */
//    @Column(name = "serviceTypeId")
//    private DynamicTableServiceType serviceType;
//    
//    /** 模板表类型：主表，备份表，历史表... */
//    @Column(name = "typeId")
//    private DynamicTableType type;
//    
//    
//    
//    /** 实体类型 */
//    private Class<?> entityType;
//    
//    /** 与id一起同为模板表的唯一键 */
//    private String tableName;
//    
//    /** 模板表状态 */
//    private DynamicTableStatusEnum status = DynamicTableStatusEnum.WAIT_CREATE;
//    
//    /** 模板表版本<br/> V + _YYYYMMDD_ + x x首次为0，当表从运营态切换时，将自动+1，原0态表自动，重命名后作为备份存在，表明切换为tp_bak_... */
//    private int version;
//    
//    /** 表注释内容 */
//    @Column(nullable = true, updatable = true, length = 64)
//    private String comment;
//    
//    /** 模板表备注描述 */
//    @Column(nullable = true, updatable = true, length = 500)
//    private String remark;
//    
//    /** 是否可编辑 */
//    private boolean modifyAble = true;
//    
//    /** 最后更新时间 */
//    @Column(nullable = false, updatable = true)
//    private Date lastUpdateDate;
//    
//    /** 创建时间 */
//    @Column(nullable = false, updatable = false)
//    private Date createDate;
//    
//    /** 动态表对应的字段 */
//    @Transient
//    @OneToMany
//    private List<DynamicTableColumn> columns = new ArrayList<>();
//    
//    /** 动态表对应的索引 */
//    @Transient
//    @OneToMany
//    private List<DynamicTableColumn> indexes = new ArrayList<>();
//    
//    /**
//     * @return 返回 id
//     */
//    public String getId() {
//        return id;
//    }
//    
//    /**
//     * @param 对id进行赋值
//     */
//    public void setId(String id) {
//        this.id = id;
//    }
//    
//    /**
//     * @return 返回 modifyAble
//     */
//    public boolean isModifyAble() {
//        return modifyAble;
//    }
//    
//    /**
//     * @param 对modifyAble进行赋值
//     */
//    public void setModifyAble(boolean modifyAble) {
//        this.modifyAble = modifyAble;
//    }
//    
//    /**
//     * @return 返回 tableType
//     */
//    public DynamicTableType getTableType() {
//        return tableType;
//    }
//    
//    /**
//     * @param 对tableType进行赋值
//     */
//    public void setTableType(DynamicTableType tableType) {
//        this.tableType = tableType;
//    }
//    
//    /**
//     * @return 返回 tableServiceType
//     */
//    public DynamicTableServiceType getTableServiceType() {
//        return tableServiceType;
//    }
//    
//    /**
//     * @param 对tableServiceType进行赋值
//     */
//    public void setTableServiceType(DynamicTableServiceType tableServiceType) {
//        this.tableServiceType = tableServiceType;
//    }
//    
//    /**
//     * @return 返回 code
//     */
//    public String getCode() {
//        return code;
//    }
//    
//    /**
//     * @param 对code进行赋值
//     */
//    public void setCode(String code) {
//        this.code = code;
//    }
//    
//    /**
//     * @return 返回 entityType
//     */
//    public Class<?> getEntityType() {
//        return entityType;
//    }
//    
//    /**
//     * @param 对entityType进行赋值
//     */
//    public void setEntityType(Class<?> entityType) {
//        this.entityType = entityType;
//    }
//    
//    /**
//     * @return 返回 tableName
//     */
//    public String getTableName() {
//        return tableName;
//    }
//    
//    /**
//     * @param 对tableName进行赋值
//     */
//    public void setTableName(String tableName) {
//        this.tableName = tableName;
//    }
//    
//    /**
//     * @return 返回 status
//     */
//    public DynamicTableStatusEnum getStatus() {
//        return status;
//    }
//    
//    /**
//     * @param 对status进行赋值
//     */
//    public void setStatus(DynamicTableStatusEnum status) {
//        this.status = status;
//    }
//    
//    /**
//     * @return 返回 version
//     */
//    public int getVersion() {
//        return version;
//    }
//    
//    /**
//     * @param 对version进行赋值
//     */
//    public void setVersion(int version) {
//        this.version = version;
//    }
//    
//    /**
//     * @return 返回 comment
//     */
//    public String getComment() {
//        return comment;
//    }
//    
//    /**
//     * @param 对comment进行赋值
//     */
//    public void setComment(String comment) {
//        this.comment = comment;
//    }
//    
//    /**
//     * @return 返回 remark
//     */
//    public String getRemark() {
//        return remark;
//    }
//    
//    /**
//     * @param 对remark进行赋值
//     */
//    public void setRemark(String remark) {
//        this.remark = remark;
//    }
//    
//    /**
//     * @return 返回 lastUpdateDate
//     */
//    public Date getLastUpdateDate() {
//        return lastUpdateDate;
//    }
//    
//    /**
//     * @param 对lastUpdateDate进行赋值
//     */
//    public void setLastUpdateDate(Date lastUpdateDate) {
//        this.lastUpdateDate = lastUpdateDate;
//    }
//    
//    /**
//     * @return 返回 createDate
//     */
//    public Date getCreateDate() {
//        return createDate;
//    }
//    
//    /**
//     * @param 对createDate进行赋值
//     */
//    public void setCreateDate(Date createDate) {
//        this.createDate = createDate;
//    }
//    
//    /**
//     * @return 返回 columns
//     */
//    public List<DynamicTableColumn> getColumns() {
//        return columns;
//    }
//    
//    /**
//     * @param 对columns进行赋值
//     */
//    public void setColumns(List<DynamicTableColumn> columns) {
//        this.columns = columns;
//    }
//    
//    /**
//     * @return 返回 indexes
//     */
//    public List<DynamicTableColumn> getIndexes() {
//        return indexes;
//    }
//    
//    /**
//     * @param 对indexes进行赋值
//     */
//    public void setIndexes(List<DynamicTableColumn> indexes) {
//        this.indexes = indexes;
//    }
//}
