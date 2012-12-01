/*
 * 描          述:  <描述>
 * 修  改   人:  brady
 * 修改时间:  2012-11-30
 * <修改描述:>
 */
package com.tx.component.auth.model;

import java.io.Serializable;
import java.util.Set;

import com.tx.core.tree.model.TreeAble;

/**
 * <权限项>
 * <功能详细描述>
 * 
 * @author  brady
 * @version  [版本号, 2012-11-30]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public class AuthItem implements Serializable,
        TreeAble<Set<AuthItem>, AuthItem> {
    
    /** 注释内容 */
    private static final long serialVersionUID = -5205952448154970380L;
    
    /** 权限类型：操作权限父节类型，抽象权限，不是真是的权限  */
    public final static String TYPE_ABS_OPERATE = "TYPE_ABS_OPERATE";
    
    /** 权限类型：操作权限 */
    public final static String TYPE_OPERATE = "TYPE_OPERATE";
    
    /** 权限类型：数据权限 */
    public final static String TYPE_ABS_DATA = "TYPE_ABS_DATA";
    
    /** 权限类型：数据列权限父节类型，抽象权限，不是真是的权限 */
    public final static String TYPE_ABS_DATA_COLUMN = "TYPE_ABS_DATA_COLUMN";
    
    /** 权限类型：数据列权限 */
    public final static String TYPE_DATA_COLUMN = "TYPE_DATA_COLUMN";
    
    /** 权限类型: 数据行权限父节类型，抽象权限，不是真是的权限 */
    public final static String TYPE_ABS_DATA_ROW = "TYPE_ABS_DATA_ROW";
    
    /** 权限类型: 数据行权限 */
    public final static String TYPE_DATA_ROW = "TYPE_DATA_ROW";
    
    /** 权限版本：用以支持权限升级 */
    private String version;
    
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
    private String authType;
    
    /**
     * 无权限的处理方式：提供集中默认方式
     * showEmpty : 显示为空
     * maskInfo ：遮罩信息   方式通过注入权限容器中可自定义添加
     */
    private String noAuthDeal;
    
    /** 子权限列表 */
    private Set<AuthItem> childs;
    
    /**
     * @return 返回 version
     */
    public String getVersion() {
        return version;
    }
    
    /**
     * @param 对version进行赋值
     */
    public void setVersion(String version) {
        this.version = version;
    }
    
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
    public Set<AuthItem> getChilds() {
        return childs;
    }
    
    /**
     * @param 对childs进行赋值
     */
    public void setChilds(Set<AuthItem> childs) {
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
     * @return 返回 noAuthDeal
     */
    public String getNoAuthDeal() {
        return noAuthDeal;
    }
    
    /**
     * @param 对noAuthDeal进行赋值
     */
    public void setNoAuthDeal(String noAuthDeal) {
        this.noAuthDeal = noAuthDeal;
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
}
