/*
 * 描          述:  <描述>
 * 修  改   人:  Administrator
 * 修改时间:  2014年11月10日
 * <修改描述:>
 */
package com.tx.local.operator.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.tx.component.role.model.Role;
import com.tx.local.security.model.OperatorRoleTypeEnum;

import io.swagger.annotations.ApiModel;

/**
 * 角色
 *     角色在系统中主要为一个权限集合的整合
 *     角色下挂在分公司下
 *     每个分公司可以有一套独立的角色体系
 * 角色不同于职位
 *     相对于职位更加灵活
 *     一个人允许多个角色
 * 权限角色是系统功能权限设置的基础，相当于用户分组，所有用户对应到相应权限角色，便具有该权限角色所赋予的所有功能权限。
 * 岗位(职位)是在组织架构下的精细岗位划分，是业务流程控制、业绩考核、预警体系的基础，不同的机构、部门下的同一职务是作为不同的岗位的。
 * 并没有把岗位直接作为系统功能权限设置的对象而是引入了权限角色概念，是因为岗位非常多，而很多不同的机构、部门下的同一职务拥有同样的功能权限，如果直接用岗位来设置将极大增加重复工作量。
 * 权限角色实际上有些相当于岗位权限分类的概念，即具有同样功能权限的岗位集合在一起，这样可以减少权限设置的工作量。
 * 举例说明：
 * 杂货部门饮料组主管和杂货部门粮油组主管是两个岗位，在业务流程控制、业绩考核、预警体系中是不同的，但其在系统中的功能模块权限是相同的，故都属于门店柜组主管这样一个权限角色。
 * 
 * @author  Administrator
 * @version  [版本号, 2014年11月10日]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
@Entity
@Table(name = "oper_role")
@ApiModel("角色")
public class OperatorRole implements Serializable, Role {
    
    /** 注释内容 */
    private static final long serialVersionUID = 1416028282623462740L;
    
    /** id */
    @Id
    private String id;
    
    /** 类目id */
    private String catalogId;
    
    /** 父级角色id */
    private String parentId;
    
    /** 虚中心id */
    private String vcid;
    
    /** 名称 */
    private String name;
    
    /** 是否有效 */
    private boolean valid = true;
    
    /** 是否可编辑 */
    private boolean modifyAble = true;
    
    /** 备注 */
    private String remark;
    
    /** 创建时间 */
    private Date createDate;
    
    /** 最后更新时间 */
    private Date lastUpdateDate;
    
    /** <默认构造函数> */
    public OperatorRole() {
        super();
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
     * @return 返回 catalogId
     */
    public String getCatalogId() {
        return catalogId;
    }
    
    /**
     * @param 对catalogId进行赋值
     */
    public void setCatalogId(String catalogId) {
        this.catalogId = catalogId;
    }
    
    /**
     * @return 返回 vcid
     */
    public String getVcid() {
        return vcid;
    }
    
    /**
     * @param 对vcid进行赋值
     */
    public void setVcid(String vcid) {
        this.vcid = vcid;
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
     * @return 返回 roleTypeId
     */
    @JsonIgnore
    public String getRoleTypeId() {
        return OperatorRoleTypeEnum.ROLE_TYPE_OPERATOR.getId();
    }
}
