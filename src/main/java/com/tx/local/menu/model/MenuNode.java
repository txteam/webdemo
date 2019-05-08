/*
 * 描          述:  <描述>
 * 修  改   人:  PengQingyang
 * 修改时间:  2012-12-17
 * <修改描述:>
 */
package com.tx.local.menu.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.collections4.CollectionUtils;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.tx.core.exceptions.util.AssertUtils;
import com.tx.core.tree.model.TreeAble;

/**
 * 菜单项默认实现<br/>
 * <功能详细描述>
 * 
 * @author  PengQingyang
 * @version  [版本号, 2012-12-17]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public class MenuNode
        implements Menu, TreeAble<List<MenuNode>, MenuNode>, Serializable {
    
    /** 注释内容 */
    private static final long serialVersionUID = -6052627407144634479L;
    
    /** 菜单唯一id */
    private final Menu menu;
    
    /** 子菜单集合 */
    @JsonIgnore
    private List<MenuNode> children;
    
    /** <默认构造函数> */
    public MenuNode(Menu menu) {
        super();
        AssertUtils.notNull(menu, "menu is null.");
        
        this.menu = menu;
    }
    
    /**
     * @return 返回 menu
     */
    public Menu getMenu() {
        return menu;
    }
    
    /**
     * @return 返回 id
     */
    public String getId() {
        return this.menu.getId();
    }
    
    /**
     * @return 返回 parentId
     */
    public String getParentId() {
        return this.menu.getParentId();
    }
    
    /**
     * @return 返回 type
     */
    public String getType() {
        return this.menu.getType();
    }
    
    /**
     * @return 返回 text
     */
    public String getText() {
        return this.menu.getText();
    }
    
    /**
     * @return 返回 href
     */
    public String getHref() {
        return this.menu.getHref();
    }
    
    /**
     * @return 返回 icon
     */
    public String getIcon() {
        return this.menu.getIcon();
    }
    
    /**
     * @return 返回 tips
     */
    public String getTips() {
        return this.menu.getTips();
    }
    
    /**
     * @return 返回 valid
     */
    public boolean isValid() {
        return this.menu.isValid();
    }
    
    /**
     * @return 返回 modifyAble
     */
    public boolean isModifyAble() {
        return this.menu.isModifyAble();
    }
    
    /**
     * @return 返回 attributes
     */
    public Map<String, String> getAttributes() {
        return this.menu.getAttributes();
    }
    
    /**
     * @return 返回 catalog
     */
    @JsonIgnore
    public MenuCatalogItem getCatalog() {
        return this.menu.getCatalog();
    }
    
    /**
     * @return 返回 roles
     */
    public Set<String> getRoles() {
        return this.menu.getRoles();
    }
    
    /**
     * @return 返回 authorities
     */
    public Set<String> getAuths() {
        return this.menu.getAuths();
    }
    
    /**
     * @return 返回 childs
     */
    public List<MenuNode> getChildren() {
        return children;
    }
    
    /**
     * @return 返回 childMenuList
     */
    public List<MenuNode> getDescendants() {
        List<MenuNode> resList = new ArrayList<MenuNode>();
        doGetChildren(resList, getChildren());
        return resList;
    }
    
    /**
     * 迭代获取所有子节点菜单<br/>
     * <功能详细描述> [参数说明]
     * 
     * @return void [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    private void doGetChildren(List<MenuNode> resList,
            List<MenuNode> children) {
        if (CollectionUtils.isEmpty(children)) {
            return;
        }
        for (MenuNode mn : children) {
            resList.add(mn);
            doGetChildren(resList, mn.getChildren());
        }
    }
    
    /**
     * @param 对childs进行赋值
     */
    public void setChildren(List<MenuNode> childs) {
        this.children = childs;
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
            if (getId() == null) {
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
