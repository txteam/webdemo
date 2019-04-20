/*
 * 描          述:  <描述>
 * 修  改   人:  PengQingyang
 * 修改时间:  2013-8-22
 * <修改描述:>
 */
package com.tx.local.menu.context;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.ArrayUtils;

import com.tx.core.exceptions.util.AssertUtils;
import com.tx.local.menu.model.Menu;
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
        
        return menuNodeList;
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
    public List<Menu> getMenuListBySecurity(String catalog) {
        AssertUtils.notEmpty(catalog, "catalog is empty.");
        
        //根据权限及菜单配置生成最终权限列表
        List<Menu> resList = new ArrayList<Menu>();
        for (Menu menuItemTemp : getMenuListByCatalog(catalog)) {
            if (ArrayUtils.isEmpty(menuItemTemp.getAuthorities())
                    && ArrayUtils.isEmpty(menuItemTemp.getRoles())) {
                //如果对应菜单未配置权限，无需鉴权
                resList.add(menuItemTemp);
            } else {
                //根据权限判断是否需要鉴权
                for (String authorityTemp : menuItemTemp.getAuthorities()) {
                    //一旦拥有其中任一权限即可拥有对应菜单
                    //SecurityContextHolder.getContext().getAuthentication().
                    //if (authItemRefMap.containsKey(authKeyTemp)) {
                    resList.add(menuItemTemp);
                    //}
                }
            }
        }
        return resList;
    }
}
