/*
 * 描          述:  <描述>
 * 修  改   人:  PengQingyang
 * 修改时间:  2012-12-17
 * <修改描述:>
 */
package com.tx.local.menu.model;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
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
public class MenuCatalogItem implements Serializable {
    
    /** 注释内容 */
    private static final long serialVersionUID = -6052627407144634479L;
    
    /** 菜单唯一id */
    private String id;
    
    /** 菜单名 */
    private String text;
    
    /** 默认配置 */
    private final Map<String, String> attributes = new HashMap<String, String>();
    
    /** 子菜单集合 */
    private List<Menu> menuList;
    
    /** 菜单节点清单(不包含子节点的子节点，以树结构存放数据) */
    private List<MenuNode> menuNodeList;
    
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
     * @return 返回 attributes
     */
    public Map<String, String> getAttributes() {
        return attributes;
    }
    
    /**
     * @return 返回 menuList
     */
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
     * @return 返回 menuNodeList
     */
    public List<MenuNode> getMenuNodeList() {
        return menuNodeList;
    }
    
    /**
     * @param 对menuNodeList进行赋值
     */
    public void setMenuNodeList(List<MenuNode> menuNodeList) {
        this.menuNodeList = menuNodeList;
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
