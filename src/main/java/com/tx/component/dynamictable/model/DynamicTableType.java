/*
 * 描          述:  <描述>
 * 修  改   人:  brady
 * 修改时间:  2013-10-12
 * <修改描述:>
 */
package com.tx.component.dynamictable.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.tx.core.support.initable.model.ConfigInitAble;

/**
 * 模板表类型枚举<br/>
 * <功能详细描述>
 * 
 * @author  brady
 * @version  [版本号, 2013-10-12]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
@XmlRootElement(name = "type")
@XmlAccessorType(XmlAccessType.FIELD)
@Entity
@Table(name = "dt_table_type")
public class DynamicTableType implements ConfigInitAble, Serializable {
    
    /** 注释内容 */
    private static final long serialVersionUID = -6676531255782869781L;
    
    /** 模板表类型唯一键 */
    @Id
    @Column(nullable = false, length = 64)
    private String id;
    
    /** 编码 */
    @Column(nullable = false, length = 64)
    private String code;
    
    /** 模板表类型：所属业务类型 */
    @Column(nullable = false, name = "serviceTypeId")
    private DynamicTableServiceType serviceType;
    
    /** 模板表类型，唯一键，名字不能重复 */
    @XmlAttribute
    @Column(nullable = false)
    private String name;
    
    /** 模板表类型是否有效 */
    @Column(nullable = false)
    private boolean valid;
    
    /** 是否可编辑 */
    @Column(nullable = false)
    private boolean modifyAble;
    
    /** 模板表类型后缀名 */
    @XmlAttribute
    private String suffix;
    
    /** 模板表类型名描述 */
    @XmlElement
    private String remark;
    
    /** 最后更新时间 */
    @Column(nullable = false, updatable = true)
    private Date lastUpdateDate;
    
    /** 创建时间 */
    @Column(nullable = false, updatable = true)
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
     * @return 返回 suffix
     */
    public String getSuffix() {
        return suffix;
    }
    
    /**
     * @param 对suffix进行赋值
     */
    public void setSuffix(String suffix) {
        this.suffix = suffix;
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
}
