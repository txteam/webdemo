/*

 * 描          述:  <描述>
 * 修  改   人:  PengQY
 * 修改时间:  2020-01-17
 * <修改描述:>
 */
package com.tx.component.dynamictable.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.tx.core.ddlutil.model.TableColumnDef;
import com.tx.core.ddlutil.model.TableDef;
import com.tx.core.ddlutil.model.TableIndexDef;

/**
 * 模板表<br/>
 * <功能详细描述>
 * 
 * @author  brady
 * @version  [版本号, 2013-10-12]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
@Entity
@Table(name = "dt_table")
public class DynamicTable implements Serializable, TableDef {
    
    /** 注释内容 */
    private static final long serialVersionUID = 8275499359664274226L;
    
    /** 模板表id:具体的一张模板表，id唯一对应唯一的tableName */
    @Id
    @Column(nullable = false, length = 64)
    private String id;
    
    /** 模板表版本<br/> V + _YYYYMMDD_ + x x首次为0，当表从运营态切换时，将自动+1，原0态表自动，重命名后作为备份存在，表明切换为tp_bak_... */
    @Column(nullable = false, updatable = true, length = 64)
    private int version;
    
    /** 模板表：编码 */
    @Column(nullable = false, length = 64)
    private String code;
    
    /** 模板表业务类型：填报表、记录信息、商品等情况... */
    @Column(name = "serviceTypeId", nullable = false, length = 64)
    private DynamicTableServiceType serviceType;
    
    /** 模板表类型：主表，备份表，历史表... */
    @Column(name = "typeId", nullable = false, length = 64)
    private DynamicTableType type;
    
    /** 实体类: 数据输入以后存储数据的对象：可以为具体bean，也可以为dynaBean，也可以为map */
    @Column(nullable = true, length = 128)
    private Class<?> entityType;
    
    /** 与id一起同为模板表的唯一键 */
    @Column(nullable = false, updatable = false, length = 64)
    private String tableName;
    
    /** 是否可编辑 */
    @Column(nullable = false, updatable = true)
    private boolean modifyAble = true;
    
    /** 模板表状态 */
    @Column(nullable = false, updatable = true, length = 64)
    private DynamicTableStatusEnum status = DynamicTableStatusEnum.WAIT_CREATE;
    
    /** 表注释内容 */
    @Column(nullable = true, updatable = true, length = 100)
    private String comment;
    
    /** 模板表备注描述 */
    @Column(nullable = true, updatable = true, length = 500)
    private String remark;
    
    /** 最后更新时间 */
    @Column(nullable = false, updatable = true)
    private Date lastUpdateDate;
    
    /** 创建时间 */
    @Column(nullable = false, updatable = false)
    private Date createDate;
    
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
     * @return 返回 version
     */
    public int getVersion() {
        return version;
    }
    
    /**
     * @param 对version进行赋值
     */
    public void setVersion(int version) {
        this.version = version;
    }
    
    /**
     * @return 返回 code
     */
    public String getCode() {
        return code;
    }
    
    /**
     * @param 对code进行赋值
     */
    public void setCode(String code) {
        this.code = code;
    }
    
    /**
     * @return 返回 serviceType
     */
    public DynamicTableServiceType getServiceType() {
        return serviceType;
    }
    
    /**
     * @param 对serviceType进行赋值
     */
    public void setServiceType(DynamicTableServiceType serviceType) {
        this.serviceType = serviceType;
    }
    
    /**
     * @return 返回 type
     */
    public DynamicTableType getType() {
        return type;
    }
    
    /**
     * @param 对type进行赋值
     */
    public void setType(DynamicTableType type) {
        this.type = type;
    }
    
    /**
     * @return 返回 entityType
     */
    public Class<?> getEntityType() {
        return entityType;
    }
    
    /**
     * @param 对entityType进行赋值
     */
    public void setEntityType(Class<?> entityType) {
        this.entityType = entityType;
    }
    
    /**
     * @return 返回 tableName
     */
    public String getTableName() {
        return tableName;
    }
    
    /**
     * @param 对tableName进行赋值
     */
    public void setTableName(String tableName) {
        this.tableName = tableName;
    }
    
    /**
     * @return 返回 modifyAble
     */
    public boolean isModifyAble() {
        return modifyAble;
    }
    
    /**
     * @param 对modifyAble进行赋值
     */
    public void setModifyAble(boolean modifyAble) {
        this.modifyAble = modifyAble;
    }
    
    /**
     * @return 返回 status
     */
    public DynamicTableStatusEnum getStatus() {
        return status;
    }
    
    /**
     * @param 对status进行赋值
     */
    public void setStatus(DynamicTableStatusEnum status) {
        this.status = status;
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
     * @return
     */
    @Override
    public List<? extends TableColumnDef> getColumns() {
        // TODO Auto-generated method stub
        return null;
    }
    
    /**
     * @return
     */
    @Override
    public List<? extends TableIndexDef> getIndexes() {
        // TODO Auto-generated method stub
        return null;
    }
}
