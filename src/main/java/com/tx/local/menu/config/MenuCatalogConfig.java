/*
 * 描          述:  <描述>
 * 修  改   人:  PengQingyang
 * 修改时间:  2012-12-16
 * <修改描述:>
 */
package com.tx.local.menu.config;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;
import com.thoughtworks.xstream.annotations.XStreamConverter;
import com.thoughtworks.xstream.annotations.XStreamImplicit;

/**
 * 菜单项配置
 * <功能详细描述>
 * 
 * @author  PengQingyang
 * @version  [版本号, 2012-12-16]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
@XStreamConverter(value = MenuCatalogConfigConverter.class)
@XStreamAlias("catalog")
public class MenuCatalogConfig {
    
    /** 编码  */
    @XStreamAsAttribute
    private String id;
    
    /** 文字  */
    @XStreamAsAttribute
    private String text;
    
    /** 打开类型 ： nav,dialog,tab,event */
    @XStreamAsAttribute
    private String type;
    
    /** 菜单对应权限 */
    private Map<String, String> attributes;
    
    /** 菜单对应权限 */
    @XStreamAsAttribute
    private String auths;
    
    /** 菜单对应权限 */
    @XStreamAsAttribute
    private String roles;
    
    /** 目录是否可访问 */
    @XStreamAsAttribute
    private String access;
    
    @XStreamImplicit(itemFieldName = "menu")
    private List<MenuItemConfig> children;
    
    /** <默认构造函数> */
    public MenuCatalogConfig() {
        super();
        this.attributes = new HashMap<String, String>();
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
     * @return 返回 attributes
     */
    public Map<String, String> getAttributes() {
        return attributes;
    }
    
    /**
     * @param 对attributes进行赋值
     */
    public void setAttributes(Map<String, String> attributes) {
        this.attributes = attributes;
    }
    
    /**
     * @return 返回 access
     */
    public String getAccess() {
        return access;
    }
    
    /**
     * @param 对access进行赋值
     */
    public void setAccess(String access) {
        this.access = access;
    }
    
    /**
     * @return 返回 roles
     */
    public String getRoles() {
        return roles;
    }
    
    /**
     * @param 对roles进行赋值
     */
    public void setRoles(String roles) {
        this.roles = roles;
    }
    
    /**
     * @return 返回 auths
     */
    public String getAuths() {
        return auths;
    }
    
    /**
     * @param 对auths进行赋值
     */
    public void setAuths(String auths) {
        this.auths = auths;
    }
    
    /**
     * @return 返回 children
     */
    public List<MenuItemConfig> getChildren() {
        return children;
    }
    
    /**
     * @param 对children进行赋值
     */
    public void setChildren(List<MenuItemConfig> children) {
        this.children = children;
    }
}
