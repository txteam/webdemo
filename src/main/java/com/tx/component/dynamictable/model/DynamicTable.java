/*
 * 描          述:  <描述>
 * 修  改   人:  PengQY
 * 修改时间:  2020-01-17
 * <修改描述:>
 */
package com.tx.component.dynamictable.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

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
    @Column(name = "serviceTypeId")
    private DynamicTableServiceType serviceType;
    
    /** 模板表类型：主表，备份表，历史表... */
    @Column(name = "typeId")
    private DynamicTableType type;
    
    /** 实体类型 */
    private Class<?> entityType;
    
    /** 与id一起同为模板表的唯一键 */
    @Column(nullable = false, updatable = true, length = 64)
    private String tableName;
    
    /** 是否可编辑 */
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
     * @return
     */
    @Override
    public String getTableName() {
        // TODO Auto-generated method stub
        return null;
    }

    /**
     * @return
     */
    @Override
    public String getComment() {
        // TODO Auto-generated method stub
        return null;
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
