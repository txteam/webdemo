/*
 * 描          述:  <描述>
 * 修  改   人:  PengQingyang
 * 修改时间:  2012-12-17
 * <修改描述:>
 */
package com.tx.local.menu.model;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * 菜单项默认实现<br/>
 * <功能详细描述>
 * 
 * @author  PengQingyang
 * @version  [版本号, 2012-12-17]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public class MenuItem
        implements Menu, Serializable {
    
    /** 注释内容 */
    private static final long serialVersionUID = -6052627407144634479L;
    
    /** 菜单唯一id */
    private String id;
    
    /** 父菜单id */
    private String parentId;
    
    /** 父菜单类型 */
    private MenuCatalogItem catalog;
    
    /** 菜单名 */
    private String text;
    
    /** 菜单鼠标悬停提示信息 */
    private String tips;
    
    /** 菜单href值 */
    private String href = "javascript:void(0);";
    
    /** 菜单图标 */
    private String icon;
    
    /** 是否有效 */
    private boolean valid = true;
    
    /** 是否可见 */
    private boolean modifyAble = false;
    
    /** 参数 */
    private final Map<String, String> attributes = new HashMap<String, String>();
    
    /** 菜单对应权限 */
    private String[] authorities;
    
    /** 菜单对应权限 */
    private String[] roles;
    
    /** 打开类型 ： tab,nav,event,dialog */
    private String type = "tab";
    
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
     * @return 返回 roles
     */
    public String[] getRoles() {
        return roles;
    }
    
    /**
     * @param 对roles进行赋值
     */
    public void setRoles(String[] roles) {
        this.roles = roles;
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
     * @return 返回 catalog
     */
    public MenuCatalogItem getCatalog() {
        return catalog;
    }
    
    /**
     * @param 对catalog进行赋值
     */
    public void setCatalog(MenuCatalogItem catalog) {
        this.catalog = catalog;
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
     * @return 返回 tips
     */
    public String getTips() {
        return tips;
    }
    
    /**
     * @param 对tips进行赋值
     */
    public void setTips(String tips) {
        this.tips = tips;
    }
    
    /**
     * @return 返回 href
     */
    public String getHref() {
        return href;
    }
    
    /**
     * @param 对href进行赋值
     */
    public void setHref(String href) {
        this.href = href;
    }
    
    /**
     * @return 返回 icon
     */
    public String getIcon() {
        return icon;
    }
    
    /**
     * @param 对icon进行赋值
     */
    public void setIcon(String icon) {
        this.icon = icon;
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
     * @return 返回 authorities
     */
    public String[] getAuthorities() {
        return authorities;
    }
    
    /**
     * @param 对authorities进行赋值
     */
    public void setAuthorities(String[] authorities) {
        this.authorities = authorities;
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
     * @return 返回 attributes
     */
    public Map<String, String> getAttributes() {
        Map<String, String> resMap = new HashMap<>();
        resMap.putAll(this.catalog.getAttributes());
        resMap.putAll(this.attributes);
        return resMap;
    }
    
    /**
     * @param obj
     * @return
     */
    @Override
    public boolean equals(Object obj) {
        if (obj == null || !(obj instanceof Menu)) {
            return false;
        } else {
            Menu other = (Menu) obj;
            if (this.id == null) {
                //这里认为authType或id为空时，不能按照authItem的设置判断相等，仅仅能根据两者是否是同一个引用判断相等
                //仅以两者是否是同一个对象的链接进行判断
                return this == other;
            } else {
                return this.getId().equals(other.getId());
            }
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
