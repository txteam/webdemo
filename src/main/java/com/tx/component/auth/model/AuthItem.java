/*
 * 描          述:  <描述>
 * 修  改   人:  brady
 * 修改时间:  2012-11-30
 * <修改描述:>
 */
package com.tx.component.auth.model;

import java.io.Serializable;
import java.util.List;
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
public class AuthItem implements Serializable,TreeAble<Set<AuthItem>,AuthItem>{
    
    /** 注释内容 */
    private static final long serialVersionUID = -5205952448154970380L;
    
    /** 权限类型：操作权限 */
    public final static String AUTH_TYPE_OPERATE = "operate";
    
    /** 权限类型：数据权限 */
    public final static String AUTH_TYPE_DATA = "data";
    
    /** 权限类型：数据列权限 */
    public final static String AUTH_TYPE_DATA_COLUMN = "data_column";
    
    /** 权限类型行权限 */
    public final static String AUTH_TYPE_DATA_ROW = "data_row";
    
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
    
    /** 权限列表 */
    private List<String> dimensionList;
    
    /** 子权限列表 */
    private Set<AuthItem> childs;
    
    private String authType;
    
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
     * @return 返回 dimensionList
     */
    public List<String> getDimensionList() {
        return dimensionList;
    }

    /**
     * @param 对dimensionList进行赋值
     */
    public void setDimensionList(List<String> dimensionList) {
        this.dimensionList = dimensionList;
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
}
