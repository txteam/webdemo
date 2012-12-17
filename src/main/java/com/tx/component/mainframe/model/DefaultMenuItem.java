/*
 * 描          述:  <描述>
 * 修  改   人:  PengQingyang
 * 修改时间:  2012-12-17
 * <修改描述:>
 */
package com.tx.component.mainframe.model;

import java.util.List;

/**
 * 菜单项默认实现
 * <功能详细描述>
 * 
 * @author  PengQingyang
 * @version  [版本号, 2012-12-17]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public class DefaultMenuItem implements MenuItem {
    
    /** 菜单唯一id */
    private String id;
    
    /** 父菜单id */
    private String parentId;
    
    /** 父菜单类型 */
    private String type;
    
    /** 菜单名 */
    private String text;
    
    /** 菜单鼠标悬停提示信息 */
    private String tips;
    
    /** 菜单href值 */
    private String href = "javascript:void(0);return false;";
    
    /** 菜单图标 */
    private String icon;
    
    /** 是否有效 */
    private boolean isValid;
    
    /** 是否可见 */
    private boolean isVisible;
    
    /** 菜单对应权限 */
    private List<String> authKeyList;
    
    /** 子菜单集合 */
    private List<MenuItem> childs;
    
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
     * @return 返回 isValid
     */
    public boolean isValid() {
        return isValid;
    }
    
    /**
     * @param 对isValid进行赋值
     */
    public void setValid(boolean isValid) {
        this.isValid = isValid;
    }
    
    /**
     * @return 返回 isVisible
     */
    public boolean isVisible() {
        return isVisible;
    }
    
    /**
     * @param 对isVisible进行赋值
     */
    public void setVisible(boolean isVisible) {
        this.isVisible = isVisible;
    }
    
    /**
     * @return 返回 authKeyList
     */
    public List<String> getAuthKeyList() {
        return authKeyList;
    }
    
    /**
     * @param 对authKeyList进行赋值
     */
    public void setAuthKeyList(List<String> authKeyList) {
        this.authKeyList = authKeyList;
    }
    
    /**
     * @return 返回 childs
     */
    public List<MenuItem> getChilds() {
        return childs;
    }
    
    /**
     * @param 对childs进行赋值
     */
    public void setChilds(List<MenuItem> childs) {
        this.childs = childs;
    }
    
    /**
     * @param obj
     * @return
     */
    @Override
    public boolean equals(Object obj) {
        if (obj == null || !(obj instanceof MenuItem)) {
            return false;
        }
        else {
            MenuItem other = (MenuItem) obj;
            if (this.id == null) {
                //这里认为authType或id为空时，不能按照authItem的设置判断相等，仅仅能根据两者是否是同一个引用判断相等
                //仅以两者是否是同一个对象的链接进行判断
                return this == other;
            }
            else {
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
    
}
