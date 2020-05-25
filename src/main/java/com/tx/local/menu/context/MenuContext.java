/*
 * 描          述:  <描述>
 * 修  改   人:  PengQingyang
 * 修改时间:  2013-8-22
 * <修改描述:>
 */
package com.tx.local.menu.context;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.tx.core.exceptions.util.AssertUtils;
import com.tx.local.menu.model.Menu;
import com.tx.local.menu.model.MenuCatalogItem;
import com.tx.local.menu.model.MenuNode;

/**
 * 菜单容器<br/>
 * <功能详细描述>
 * 
 * @author  PengQingyang
 * @version  [版本号, 2013-8-22]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public class MenuContext extends MenuContextBuilder {
    
    protected static MenuContext context;
    
    /** <默认构造函数> */
    protected MenuContext() {
    }
    
    /**
     * @throws Exception
     */
    @Override
    protected void doInitContext() throws Exception {
    }
    
    /**
     * 获取基础数据容器实例<br/>
     * <功能详细描述>
     * @return [参数说明]
     * 
     * @return BasicDataContext [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    public static MenuContext getContext() {
        if (MenuContext.context != null) {
            return MenuContext.context;
        }
        synchronized (MenuContext.class) {
            MenuContext.context = applicationContext.getBean(beanName,
                    MenuContext.class);
        }
        AssertUtils.notNull(MenuContext.context, "context is null.");
        return MenuContext.context;
    }
    
    /**
     * 重新加载菜单配置<br/>
     * <功能详细描述> [参数说明]
     * 
     * @return void [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    public void reload() {
        doParseMenuConfig();
    }
    
    /**
     * 根据当前的登录人员的权限项加载菜单树
     * <功能详细描述>
     * @return [参数说明]
     * 
     * @return List<MenuItem> [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
    */
    public List<MenuCatalogItem> getMenuCatalogItemList() {
        //根据权限及菜单配置生成最终权限列表
        List<MenuCatalogItem> catalogList = new ArrayList<>(
                this.catalogMap.values());
        
        return catalogList;
    }
    
    /**
     * 根据当前的登录人员的权限项加载菜单树
     * <功能详细描述>
     * @return [参数说明]
     * 
     * @return List<MenuItem> [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
    */
    public List<Menu> getMenuListByCatalog(String catalog) {
        AssertUtils.notEmpty(catalog, "catalog is empty.");
        
        //根据权限及菜单配置生成最终权限列表
        List<Menu> menuList = new ArrayList<>();//(menuType);
        if (this.catalog2menusMap.containsKey(catalog.toUpperCase())) {
            menuList.addAll(this.catalog2menusMap.get(catalog.toUpperCase()));
        }
        
        return menuList;
    }
    
    /**
     * 根据当前的登录人员的权限项加载菜单树
     * <功能详细描述>
     * @return [参数说明]
     * 
     * @return List<MenuItem> [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
    */
    public List<MenuNode> getMenuNodeListByCatalog(String catalog) {
        AssertUtils.notEmpty(catalog, "catalog is empty.");
        
        //根据权限及菜单配置生成最终权限列表
        List<MenuNode> menuNodeList = new ArrayList<>();//(menuType);
        if (this.catalog2nodesMap.containsKey(catalog.toUpperCase())) {
            menuNodeList
                    .addAll(this.catalog2nodesMap.get(catalog.toUpperCase()));
        }
        
        return menuNodeList;
    }
    
    /**
     * 
     * <功能详细描述>
     * @return [参数说明]
     * 
     * @return Map<String,List<MenuNode>> [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    public Map<String, List<MenuNode>> getMenuNodeMap() {
        return this.catalog2nodesMap;
    }
    
}
