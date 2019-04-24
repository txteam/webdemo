/*
 * 描          述:  <描述>
 * 修  改   人:  PengQingyang
 * 修改时间:  2012-12-17
 * <修改描述:>
 */
package com.tx.local.menu.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.collections4.CollectionUtils;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * 菜单项默认实现<br/>
 * <功能详细描述>
 * 
 * @author  PengQingyang
 * @version  [版本号, 2012-12-17]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public class MenuCatalogItem implements Serializable {
    
    /** 注释内容 */
    private static final long serialVersionUID = -6052627407144634479L;
    
    /** 菜单唯一id */
    private String id;
    
    /** 菜单名 */
    private String text;
    
    /** 图标 */
    private String type;
    
    /** 默认配置 */
    private Map<String, String> attributes;
    
    /** 菜单对应权限 */
    private Set<String> authorities;
    
    /** 菜单对应权限 */
    private Set<String> roles;
    
    /** 子菜单集合 */
    @JsonIgnore
    private List<Menu> menuList;
    
    /** 菜单节点清单(不包含子节点的子节点，以树结构存放数据):直接子节点 */
    @JsonIgnore
    private List<MenuNode> childrenMenuNodeList;
    
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
     * @return 返回 text
     */
    public String getText() {
        return text;
    }
    
    /**
     * @param 对text进行赋值
     */
    public void setText(String text) {
        this.text = text;
    }
    
    /**
     * @return 返回 type
     */
    public String getType() {
        return type;
    }
    
    /**
     * @param 对type进行赋值
     */
    public void setType(String type) {
        this.type = type;
    }
    
    /**
     * @return 返回 authorities
     */
    public Set<String> getAuthorities() {
        if (this.authorities == null) {
            this.authorities = new HashSet<>();
        }
        return authorities;
    }
    
    /**
     * @param 对authorities进行赋值
     */
    public void setAuthorities(Set<String> authorities) {
        this.authorities = authorities;
    }
    
    /**
     * @return 返回 roles
     */
    public Set<String> getRoles() {
        if (this.roles == null) {
            this.roles = new HashSet<>();
        }
        return roles;
    }
    
    /**
     * @param 对roles进行赋值
     */
    public void setRoles(Set<String> roles) {
        this.roles = roles;
    }
    
    /**
     * @return 返回 attributes
     */
    public Map<String, String> getAttributes() {
        return attributes;
    }
    
    /**
     * @param 对attributes进行赋值
     */
    public void setAttributes(Map<String, String> attributes) {
        this.attributes = attributes == null ? new HashMap<String, String>()
                : attributes;
    }
    
    /**
     * @return 返回 menuList
     */
    @JsonIgnore
    public List<Menu> getMenuList() {
        return menuList;
    }
    
    /**
     * @param 对menuList进行赋值
     */
    public void setMenuList(List<Menu> menuList) {
        this.menuList = menuList;
    }
    
    /**
     * @return 返回 childrenMenuNodeList
     */
    @JsonIgnore
    public List<MenuNode> getChildrenMenuNodeList() {
        return childrenMenuNodeList;
    }
    
    /**
     * @param 对childrenMenuNodeList进行赋值
     */
    public void setChildrenMenuNodeList(List<MenuNode> childrenMenuNodeList) {
        this.childrenMenuNodeList = childrenMenuNodeList;
    }
    
    /**
     * @return 返回 childMenuList
     */
    @JsonIgnore
    public List<Menu> getChildrenMenuList() {
        List<Menu> resList = new ArrayList<Menu>();
        if (!CollectionUtils.isEmpty(this.childrenMenuNodeList)) {
            for (MenuNode mn : this.childrenMenuNodeList) {
                resList.add(mn);
            }
        }
        return resList;
    }
    
    /**
     * @return
     */
    @Override
    public int hashCode() {
        if (this.getId() == null) {
            return super.hashCode();
        }
        return this.getId().hashCode();
    }
    
    /**
     * @return
     * @throws CloneNotSupportedException
     */
    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
