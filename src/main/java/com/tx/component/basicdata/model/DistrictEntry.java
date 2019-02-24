/*
 * 描          述:  <描述>
 * 修  改   人:  Administrator
 * 修改时间:  2016年8月7日
 * <修改描述:>
 */
package com.tx.component.basicdata.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.tx.core.jdbc.sqlsource.annotation.QueryConditionEqual;
import com.tx.core.jdbc.sqlsource.annotation.UpdateAble;

/**
 * 区域分项属性<br/>
 * <功能详细描述>
 * 
 * @author  Administrator
 * @version  [版本号, 2016年8月7日]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
@Entity
@Table(name = "bd_district_entry")
public class DistrictEntry implements Serializable{
    
    /** 注释内容 */
    private static final long serialVersionUID = -5573047180451998624L;

    /** id */
    @Id
    private String id;
    
    /** 区域id */
    @QueryConditionEqual
    private String entityId;
    
    /** 分项key值 */
    @QueryConditionEqual
    private String entryKey;
    
    /** 分项value值 */
    @UpdateAble
    private String entryValue;

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
     * @return 返回 entityId
     */
    public String getEntityId() {
        return entityId;
    }

    /**
     * @param 对entityId进行赋值
     */
    public void setEntityId(String entityId) {
        this.entityId = entityId;
    }

    /**
     * @return 返回 entryKey
     */
    public String getEntryKey() {
        return entryKey;
    }

    /**
     * @param 对entryKey进行赋值
     */
    public void setEntryKey(String entryKey) {
        this.entryKey = entryKey;
    }

    /**
     * @return 返回 entryValue
     */
    public String getEntryValue() {
        return entryValue;
    }

    /**
     * @param 对entryValue进行赋值
     */
    public void setEntryValue(String entryValue) {
        this.entryValue = entryValue;
    }
}
