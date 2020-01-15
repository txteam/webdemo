/*
 * 描          述:  <描述>
 * 修  改   人:  Administrator
 * 修改时间:  2016年10月22日
 * <修改描述:>
 */
package com.tx.component.dynamictable.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.tx.core.jdbc.sqlsource.annotation.QueryConditionEqual;
import com.tx.core.jdbc.sqlsource.annotation.UpdateAble;
import com.tx.core.support.initable.model.ConfigInitAble;

/**
 * 模板数据业务类型<br/>
 *     报表业务类型为表征量，很多时候仅仅在创建动态表时填入值使用。
 *     暂不考虑，则增加表类型后，将所有同一业务类型下的表都进行升级的逻辑<br/>
 * <功能详细描述>
 * 
 * @author  Administrator
 * @version  [版本号, 2016年10月22日]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
@XmlRootElement(name = "service_type")
@XmlAccessorType(XmlAccessType.FIELD)
@Entity
@Table(name = "dt_table_service_type")
public class DynamicTableServiceType implements Serializable, ConfigInitAble{
    
    /** 注释内容 */
    private static final long serialVersionUID = -3950355297040789703L;
    
    /** 模板表类型唯一键 */
    @Id
    private String id;
    
    /** 模板表类型编码 */
    @XmlAttribute
    private String code;
    
    /** 模板表类型是否有效 */
    private boolean valid;
    
    /** 是否可编辑 */
    private boolean modifyAble;
    
    /** 模板表类型，唯一键，名字不能重复 */
    @XmlAttribute
    private String name;
    
    /** 模板表类型前缀名 */
    @XmlAttribute
    private String tablePrefix;
    
    /** 模板表类型名描述 */
    @XmlElement
    private String remark;
    
    /** 最后更新时间 */
    private Date lastUpdateDate;
    
    /** 创建时间 */
    private Date createDate;
    
    /** 关联的动态表类型 */
    @OneToMany
    @Transient
    @XmlElement(name = "type")
    private List<DynamicTableType> typeList;
    
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
     * @return 返回 valid
     */
    public boolean isValid() {
        return valid;
    }
    
    /**
     * @param 对valid进行赋值
     */
    public void setValid(boolean valid) {
        this.valid = valid;
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
     * @return 返回 name
     */
    public String getName() {
        return name;
    }
    
    /**
     * @param 对name进行赋值
     */
    public void setName(String name) {
        this.name = name;
    }
    
    /**
     * @return 返回 tablePrefix
     */
    public String getTablePrefix() {
        return tablePrefix;
    }
    
    /**
     * @param 对tablePrefix进行赋值
     */
    public void setTablePrefix(String tablePrefix) {
        this.tablePrefix = tablePrefix;
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
     * @return 返回 typeList
     */
    public List<DynamicTableType> getTypeList() {
        return typeList;
    }
    
    /**
     * @param 对typeList进行赋值
     */
    public void setTypeList(List<DynamicTableType> typeList) {
        this.typeList = typeList;
    }
}
