/*
 * 描          述:  <描述>
 * 修  改   人:  Administrator
 * 修改时间:  2019年4月21日
 * <修改描述:>
 */
package com.tx.local.menu.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tx.core.exceptions.util.AssertUtils;
import com.tx.local.menu.context.MenuContext;
import com.tx.local.menu.model.Menu;
import com.tx.local.menu.model.MenuCatalogItem;
import com.tx.local.menu.model.MenuNode;

/**
 * 菜单控制层业务逻辑<br/>
 * <功能详细描述>
 * 
 * @author  Administrator
 * @version  [版本号, 2019年4月21日]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
@Controller
@RequestMapping("/menu")
public class MenuController {
    
    @Resource(name = "menuContext")
    private MenuContext menuContext;
    
    /**
     * 跳转到查询菜单列表页面
     *<功能详细描述>
     * @return [参数说明]
     * 
     * @return String [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
    */
    @RequestMapping("/toQueryMenuTreeList")
    public String toQueryMenuTreeList() {
        return "mainframe/queryMenuTreeList";
    }
    
    /**
     * 查询所有菜单列表<br/>
     *<功能详细描述>
     * @param menuItemId
     * @return [参数说明]
     * 
     * @return List<MenuItem> [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
    */
    @ResponseBody
    @RequestMapping("/queryCatalogList")
    public List<MenuCatalogItem> queryMenuCatalogList() {
        List<MenuCatalogItem> resList = menuContext.getMenuCatalogItemList();
        return resList;
    }
    
    /**
     * 查询所有菜单列表<br/>
     *<功能详细描述>
     * @param menuItemId
     * @return [参数说明]
     * 
     * @return List<MenuItem> [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
    */
    @ResponseBody
    @RequestMapping("/queryList")
    public List<Menu> queryMenuList(
            @RequestParam(name = "catalog") String catalog) {
        AssertUtils.notEmpty(catalog, "catalog is empty.");
        
        //根据权限及菜单配置生成最终权限列表
        List<Menu> resList = menuContext.getMenuListByCatalog(catalog);
        return resList;
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
    @SuppressWarnings("unused") 
    @ResponseBody
    @RequestMapping("/queryMenuListBySecurity")
    public List<Menu> queryMenuListBySecurity(
            @RequestParam(name = "catalog") String catalog) {
        AssertUtils.notEmpty(catalog, "catalog is empty.");
        
        //根据权限及菜单配置生成最终权限列表
        List<Menu> resList = new ArrayList<Menu>();
        for (Menu menuItemTemp : menuContext.getMenuListByCatalog(catalog)) {
            if (CollectionUtils.isEmpty(menuItemTemp.getAuthorities())
                    && CollectionUtils.isEmpty(menuItemTemp.getRoles())) {
                //如果对应菜单未配置权限，无需鉴权
                resList.add(menuItemTemp);
            } else {
                //根据权限判断是否需要鉴权
                boolean check = true;
                if (!CollectionUtils.isEmpty(menuItemTemp.getAuthorities())
                        && check) {
                    for (String authorityTemp : menuItemTemp.getAuthorities()) {
                        //一旦拥有其中任一权限即可拥有对应菜单
                        //SecurityContextHolder.getContext().getAuthentication().
                        //if (authItemRefMap.containsKey(authKeyTemp)) {
                        //resList.add(menuItemTemp);
                        //}
                    }
                }
                if (!CollectionUtils.isEmpty(menuItemTemp.getRoles())
                        && check) {
                    for (String roleTemp : menuItemTemp.getRoles()) {
                        //一旦拥有其中任一权限即可拥有对应菜单
                        //SecurityContextHolder.getContext().getAuthentication().
                        //if (authItemRefMap.containsKey(authKeyTemp)) {
                        //resList.add(menuItemTemp);
                        //}
                    }
                }
                if (check) {
                    //一旦不匹配则无权限
                    resList.add(menuItemTemp);
                }
            }
        }
        return resList;
    }
    
    @SuppressWarnings("unused") 
    @ResponseBody
    @RequestMapping("/queryMenuMapListBySecurity")
    public List<Map<String, Object>> queryMenuMapListBySecurity(
            @RequestParam(name = "catalog") String catalog) {
        AssertUtils.notEmpty(catalog, "catalog is empty.");
        
        //根据权限及菜单配置生成最终权限列表
        List<Map<String, Object>> resList = new ArrayList<Map<String, Object>>();
        for (MenuNode menuItemTemp : menuContext.getMenuNodeListByCatalog(catalog)) {
            Map<String, Object> menuMap = new HashMap<String, Object>();
            menuMap.put("menu", menuItemTemp.getMenu());
            menuMap.put("menuList", menuItemTemp.getDescendants());
            
            if (CollectionUtils.isEmpty(menuItemTemp.getAuthorities())
                    && CollectionUtils.isEmpty(menuItemTemp.getRoles())) {
                //如果对应菜单未配置权限，无需鉴权
                
                resList.add(menuMap);
            } else {
                //根据权限判断是否需要鉴权
                boolean check = true;
                if (!CollectionUtils.isEmpty(menuItemTemp.getAuthorities())
                        && check) {
                    for (String authorityTemp : menuItemTemp.getAuthorities()) {
                        //一旦拥有其中任一权限即可拥有对应菜单
                        //SecurityContextHolder.getContext().getAuthentication().
                        //if (authItemRefMap.containsKey(authKeyTemp)) {
                        //resList.add(menuItemTemp);
                        //}
                    }
                }
                if (!CollectionUtils.isEmpty(menuItemTemp.getRoles())
                        && check) {
                    for (String roleTemp : menuItemTemp.getRoles()) {
                        //一旦拥有其中任一权限即可拥有对应菜单
                        //SecurityContextHolder.getContext().getAuthentication().
                        //if (authItemRefMap.containsKey(authKeyTemp)) {
                        //resList.add(menuItemTemp);
                        //}
                    }
                }
                if (check) {
                    //一旦不匹配则无权限
                    resList.add(menuMap);
                }
            }
        }
        return resList;
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
    @SuppressWarnings("unused") 
    @ResponseBody
    @RequestMapping("/queryMenuNodeListBySecurity")
    public List<MenuNode> queryMenuNodeListBySecurity(
            @RequestParam(name = "catalog") String catalog) {
        AssertUtils.notEmpty(catalog, "catalog is empty.");
        
        //根据权限及菜单配置生成最终权限列表
        List<MenuNode> resList = new ArrayList<MenuNode>();
        for (MenuNode menunodeTemp : menuContext
                .getMenuNodeListByCatalog(catalog)) {
            if (CollectionUtils.isEmpty(menunodeTemp.getAuthorities())
                    && CollectionUtils.isEmpty(menunodeTemp.getRoles())) {
                //如果对应菜单未配置权限，无需鉴权
                resList.add(menunodeTemp);
            } else {
                //根据权限判断是否需要鉴权
                boolean check = true;
                if (!CollectionUtils.isEmpty(menunodeTemp.getAuthorities())
                        && check) {
                    for (String authorityTemp : menunodeTemp.getAuthorities()) {
                        //一旦拥有其中任一权限即可拥有对应菜单
                        //SecurityContextHolder.getContext().getAuthentication().
                        //if (authItemRefMap.containsKey(authKeyTemp)) {
                        //resList.add(menuItemTemp);
                        //}
                    }
                }
                if (!CollectionUtils.isEmpty(menunodeTemp.getRoles())
                        && check) {
                    for (String roleTemp : menunodeTemp.getRoles()) {
                        //一旦拥有其中任一权限即可拥有对应菜单
                        //SecurityContextHolder.getContext().getAuthentication().
                        //if (authItemRefMap.containsKey(authKeyTemp)) {
                        //resList.add(menuItemTemp);
                        //}
                    }
                }
                if (check) {
                    //一旦不匹配则无权限
                    resList.add(menunodeTemp);
                }
            }
        }
        return resList;
    }
    
}
