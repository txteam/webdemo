/*
 * 描          述:  <描述>
 * 修  改   人:  PengQingyang
 * 修改时间:  2013-8-26
 * <修改描述:>
 */
package com.tx.component.operator.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;



 /**
  * 职位<br/>
  * <功能详细描述>
  * 
  * @author  PengQingyang
  * @version  [版本号, 2013-8-26]
  * @see  [相关类/方法]
  * @since  [产品/模块版本]
  */
@Entity
@Table(name = "OPER_POST")
public class Post implements Serializable{
    
    /** 注释内容 */
    private static final long serialVersionUID = -2514159781332417436L;

    /** id */
    @Id
    private String id;
    
    /** 上级职位id */
    private String parentId;
    
    /** 职位编码 */
    private String code;
    
    /** 职位名称 */
    private String name;
    
    /** 职位名称全名 */
    private String fullName;
    
    /** 是否有效 */
    private boolean valid;
    
    /** 备注 */
    private String remark;
    
    /** 组织 */
    @ManyToOne
    @JoinColumn(name = "organizationId")
    private Organization organization;

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
     * @return 返回 organization
     */
    public Organization getOrganization() {
        return organization;
    }

    /**
     * @param 对organization进行赋值
     */
    public void setOrganization(Organization organization) {
        this.organization = organization;
    }

    /**
     * @return 返回 parentId
     */
    public String getParentId() {
        return parentId;
    }

    /**
     * @param 对parentId进行赋值
     */
    public void setParentId(String parentId) {
        this.parentId = parentId;
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
     * @return 返回 fullName
     */
    public String getFullName() {
        return fullName;
    }

    /**
     * @param 对fullName进行赋值
     */
    public void setFullName(String fullName) {
        this.fullName = fullName;
    }
}
