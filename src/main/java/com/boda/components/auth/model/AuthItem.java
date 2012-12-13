/*
 * 描          述:  <描述>
 * 修  改   人:  brady
 * 修改时间:  2012-11-30
 * <修改描述:>
 */
package com.boda.components.auth.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.apache.cxf.common.util.StringUtils;
import org.apache.ibatis.type.Alias;

import com.boda.components.auth.AuthConstant;
import com.tx.core.tree.model.TreeAble;

/**
 * <权限项>
 * <功能详细描述>
 * 
 * @author  PengQingyang
 * @version  [版本号, 2012-11-30]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
@Alias("authItem")
public class AuthItem implements Serializable,
        TreeAble<List<AuthItem>, AuthItem> {
    
    /** 注释内容 */
    private static final long serialVersionUID = -5205952448154970380L;
    
    /** 
     * 权限项唯一键key 
     * 约定权限项目分割符为"_"
     * 如权限为"wd_"
     */
    private String id;
    
    /** 父级权限id */
    private String parentId;
    
    /** 权限项名 */
    private String name;
    
    /** 权限项目描述 */
    private String description;
    
    /** 
     * 权限类型
     * 后续根据需要可以扩展相应的权限大类
     * 比如 
     *      产品权限
     *      流程环节权限
     *      通过多个纬度的权限交叉可以达到多纬度的授权体系
     */
    private String authType = "";
    
    /** 子权限列表 */
    private List<AuthItem> childs = new ArrayList<AuthItem>();
    
    /** 是否为抽象权限：默认为非抽象权限，抽象权限不建立权限列表映射 */
    private boolean isAbstract = false;
    
    /**
     * @return 返回 id
     */
    public String getId() {
        return id;
    }
    
    /**
     * @param 对id进行赋值
     * 
     */
    public void setId(String id) {
        this.id = id;
    }
    
    /**
     * @return 返回 parentId
     */
    public String getParentId() {
        return parentId;
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
     * @return 返回 description
     */
    public String getDescription() {
        return description;
    }
    
    /**
     * @param 对description进行赋值
     */
    public void setDescription(String description) {
        this.description = description;
    }
    
    /**
     * @return 返回 childs
     */
    public List<AuthItem> getChilds() {
        return childs;
    }
    
    /**
     * @param 对childs进行赋值
     */
    public void setChilds(List<AuthItem> childs) {
        this.childs = childs;
    }
    
    /**
     * @return 返回 authType
     */
    public String getAuthType() {
        return authType;
    }
    
    /**
     * @param 对authType进行赋值
     */
    public void setAuthType(String authType) {
        this.authType = authType;
    }
    
    /**
     * @param 对parentId进行赋值
     */
    public void setParentId(String parentId) {
        this.parentId = parentId;
    }
    
    /**
     * @return 返回 isAbstract
     */
    public boolean isAbstract() {
        return isAbstract;
    }
    
    /**
     * @param 对isAbstract进行赋值
     */
    public void setAbstract(boolean isAbstract) {
        this.isAbstract = isAbstract;
    }
    
    /**
     * @param obj
     * @return
     */
    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        else if (obj instanceof AuthItem) {
            AuthItem other = (AuthItem) obj;
            if (this.id == null && other.getId() == null) {
                //这里认为两者ID均为空时无法进行详细判断
                //仅以两者是否是同一个对象的链接进行判断
                return this == other;
            }
            else if (this.getId() == null) {
                return false;
            }
            else {
                return this.getId().equals(other.getId());
            }
        }
        else {
            return false;
        }
    }
    
    /**
     * @return
     */
    @Override
    public int hashCode() {
        if (this.getId() == null) {
            return super.hashCode();
        }
        return this.getId().hashCode() + this.getClass().hashCode();
    }
    
    /**
      * 创建权限列表
      * 1、根据父级权限创建子权限
      * 2、如果父权限为抽象权限，子权限如果没有指定权限类型，则可根据父权限设定权限类型
      * @param key
      * @param authType
      * @param name
      * @param description
      * @return [参数说明]
      * 
      * @return List<AuthItem> [返回类型说明]
      * @exception throws [异常类型] [异常说明]
      * @see [类、类#方法、类#成员]
     */
    public AuthItem createChildAuthItem(String id, String authType,
            String name, boolean isAbstract, String description) {
        
        AuthItem authItem = new AuthItem();
        authItem.setId(id);
        authItem.setDescription(description);
        authItem.setParentId(this.id);
        
        if (StringUtils.isEmpty(authType)) {
            //如果没有指定的权限类型，判断当前权限是否为抽象权限
            if (this.isAbstract
                    && this.authType.endsWith(AuthConstant.ABSTRACT_AUTH_END)) {
                //如果为抽象权限，则设定权限类型为对应抽象权限的子类型
                authItem.setAuthType(this.authType.substring(0,
                        this.authType.length()
                                - AuthConstant.ABSTRACT_AUTH_END.length()));
            }
            else {
                //如果不为抽象权限，则子权限默认相同于父权限
                authItem.setAuthType(this.authType);
            }
        }
        else {
            //如果指定的权限类型，则该权限权限类型为指定的权限类型
            authItem.setAuthType(authType);
        }
        
        return authItem;
    }
}
