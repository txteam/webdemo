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

import com.tx.core.jdbc.sqlsource.annotation.QueryConditionEqual;
import com.tx.core.jdbc.sqlsource.annotation.UpdateAble;

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
@Table(name = "OPER_ROLE")
public class Role implements Serializable {
    
    /** 注释内容 */
    private static final long serialVersionUID = 1416028282623462740L;
    
    /** id */
    @Id
    private String id;
    
    @UpdateAble
    @QueryConditionEqual
    private RoleEnum roleKey;
    
    @UpdateAble
    @QueryConditionEqual
    private String code;
    
    /** 虚中心id */
    @QueryConditionEqual
    private String vcid;
    
    /** 角色类型 */
    @UpdateAble
    @QueryConditionEqual
    private RoleTypeEnum roleType;
    
    /** 名称 */
    @UpdateAble
    @QueryConditionEqual
    private String name;
    
    /** 是否有效 */
    @UpdateAble
    @QueryConditionEqual
    private boolean valid = true;
    
    /** 是否可编辑 */
    @UpdateAble
    @QueryConditionEqual
    private boolean editAble = true;
    
    /** 备注 */
    @UpdateAble
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
     * @return 返回 roleKey
     */
    public RoleEnum getRoleKey() {
        return roleKey;
    }

    /**
     * @param 对roleKey进行赋值
     */
    public void setRoleKey(RoleEnum roleKey) {
        this.roleKey = roleKey;
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
     * @return 返回 roleType
     */
    public RoleTypeEnum getRoleType() {
        return roleType;
    }

    /**
     * @param 对roleType进行赋值
     */
    public void setRoleType(RoleTypeEnum roleType) {
        this.roleType = roleType;
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
     * @return 返回 editAble
     */
    public boolean isEditAble() {
        return editAble;
    }

    /**
     * @param 对editAble进行赋值
     */
    public void setEditAble(boolean editAble) {
        this.editAble = editAble;
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
}
