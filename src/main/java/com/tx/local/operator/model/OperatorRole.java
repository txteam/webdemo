/*
 * 描          述:  <描述>
 * 修  改   人:  Administrator
 * 修改时间:  2014年11月10日
 * <修改描述:>
 */
package com.tx.local.operator.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.tx.component.role.model.Role;
import com.tx.local.security.model.RoleTypeEnum;

import io.swagger.annotations.ApiModel;

/**
 * 角色
 *     角色在系统中主要为一个权限集合的整合
 *     角色下挂在分公司下
 *     每个分公司可以有一套独立的角色体系
 * 角色不同于职位
 *     相对于职位更加灵活
 *     一个人允许多个角色
 * <功能详细描述>
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
    
    /** 角色编码 */
    private String code;
    
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
     * @return
     */
    @Override
    @JsonIgnore
    public String getRoleTypeId() {
        return RoleTypeEnum.ROLE_TYPE_OPERATOR.getId();
    }

    /**
     * @return
     */
    @Override
    @JsonIgnore
    public String getParentId() {
        return Role.super.getParentId();
    }
}
