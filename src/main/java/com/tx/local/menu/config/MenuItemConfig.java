/*
 * 描          述:  <描述>
 * 修  改   人:  PengQingyang
 * 修改时间:  2012-12-16
 * <修改描述:>
 */
package com.tx.local.menu.config;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;
import com.thoughtworks.xstream.annotations.XStreamConverter;
import com.thoughtworks.xstream.annotations.XStreamImplicit;

/**
 * 菜单配置<br/>
 * <功能详细描述>
 * 
 * @author  PengQingyang
 * @version  [版本号, 2012-12-16]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
@XStreamConverter(value = MenuItemConfigConverter.class)
@XStreamAlias("menu")
public class MenuItemConfig {
    
    /** 菜单唯一id */
    @XStreamAsAttribute
    private String id;
    
    /** 菜单名 */
    @XStreamAsAttribute
    private String text;
    
    /** 菜单鼠标悬停提示信息 */
    @XStreamAsAttribute
    private String tips;
    
    /** 菜单href值 */
    @XStreamAsAttribute
    private String href = "javascript:void(0)";
    
    /** 菜单对应权限 */
    @XStreamAsAttribute
    private String auths;
    
    /** 菜单对应权限 */
    @XStreamAsAttribute
    private String roles;
    
    /** 访问权控 */
    @XStreamAsAttribute
    private String accesses;
    
    /** 菜单图标 */
    @XStreamAsAttribute
    private String icon;
    
    /** 打开类型 ： nav,dialog,tab,event */
    @XStreamAsAttribute
    private String type;
    
    /** 菜单配置项额外属性 */
    private Map<String, String> attributes;
    
    /** 子菜单项 */
    @XStreamImplicit(itemFieldName = "menu")
    private List<MenuItemConfig> children = new ArrayList<MenuItemConfig>();
    
    /** <默认构造函数> */
    public MenuItemConfig() {
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
     * @return 返回 accesses
     */
    public String getAccesses() {
        return accesses;
    }
    
    /**
     * @param 对accesses进行赋值
     */
    public void setAccesses(String accesses) {
        this.accesses = accesses;
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
