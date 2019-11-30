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
import java.util.stream.Collectors;

import javax.annotation.Resource;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tx.component.security.context.SecurityContext;
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
        return "background/queryMenuTreeList";
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
            @RequestParam(name = "catalog", required = false) String catalog) {
        //根据权限及菜单配置生成最终权限列表
        List<Menu> resList = new ArrayList<>();
        if (!StringUtils.isEmpty(catalog)) {
            resList = menuContext.getMenuListByCatalog(catalog);
        } else {
            List<MenuCatalogItem> mciList = menuContext
                    .getMenuCatalogItemList();
            for (MenuCatalogItem mci : mciList) {
                resList.addAll(menuContext.getMenuListByCatalog(mci.getId()));
            }
        }
        return resList;
    }
    
    /**
     * 根据当前的登录人员的权限项加载菜单树<br/>
     * <功能详细描述>
     * @return [参数说明]
     * 
     * @return List<MenuItem> [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @ResponseBody
    @RequestMapping("/queryMenuListBySecurity")
    public List<Menu> queryMenuListBySecurity(
            @RequestParam(name = "catalog") String catalog) {
        AssertUtils.notEmpty(catalog, "catalog is empty.");
        
        //根据权限及菜单配置生成最终权限列表
        List<Menu> resList = menuContext.getMenuListByCatalog(catalog)
                .stream()
                .filter(menu -> access(menu))
                .collect(Collectors.toList());
        return resList;
    }
    
    /**
     * 查询菜单映射列表<br/>
     * <功能详细描述>
     * @param catalog
     * @return [参数说明]
     * 
     * @return List<Map<String,Object>> [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @ResponseBody
    @RequestMapping("/queryMenuMapListBySecurity")
    public List<Map<String, Object>> queryMenuMapListBySecurity(
            @RequestParam(name = "catalog") String catalog) {
        AssertUtils.notEmpty(catalog, "catalog is empty.");
        
        //根据权限及菜单配置生成最终权限列表
        List<Map<String, Object>> resList = new ArrayList<Map<String, Object>>();
        for (MenuNode menuItemTemp : menuContext
                .getMenuNodeListByCatalog(catalog)) {
            if (!access(menuItemTemp.getMenu())) {
                continue;
            }
            
            Map<String, Object> menuMap = new HashMap<String, Object>();
            menuMap.put("menu", menuItemTemp.getMenu());
            menuMap.put("menuList",
                    menuItemTemp.getDescendants()
                            .stream()
                            .filter(menu -> access(menu))
                            .collect(Collectors.toList()));
            resList.add(menuMap);
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
    @ResponseBody
    @RequestMapping("/queryMenuNodeListBySecurity")
    public List<MenuNode> queryMenuNodeListBySecurity(
            @RequestParam(name = "catalog") String catalog) {
        AssertUtils.notEmpty(catalog, "catalog is empty.");
        
        //根据权限及菜单配置生成最终权限列表
        List<MenuNode> resList = menuContext.getMenuNodeListByCatalog(catalog)
                .stream()
                .filter(menuNode -> access(menuNode.getMenu()))
                .collect(Collectors.toList());
        return resList;
    }
    
    /**
     * 重新加载菜单<br/>
     * <功能详细描述>
     * @return [参数说明]
     * 
     * @return boolean [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @ResponseBody
    @RequestMapping("/reload")
    public boolean reload() {
        menuContext.reload();
        return true;
    }
    
    /**
     * 过滤方法<br/>
     * <功能详细描述>
     * @param menu
     * @return [参数说明]
     * 
     * @return boolean [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    private boolean access(Menu menu) {
        boolean flag = false;
        if (CollectionUtils.isEmpty(menu.getAuths())
                && CollectionUtils.isEmpty(menu.getRoles())
                && CollectionUtils.isEmpty(menu.getAccess())) {
            flag = true;
        } else {
            //根据权限判断是否需要鉴权
            boolean check = true;
            if (!CollectionUtils.isEmpty(menu.getAuths()) && check) {
                if (!SecurityContext.getContext()
                        .hasAuth(StringUtils.join(menu.getAuths(), ","))) {
                    check = false;
                }
            }
            if (!CollectionUtils.isEmpty(menu.getRoles()) && check) {
                if (!SecurityContext.getContext()
                        .hasRole(StringUtils.join(menu.getRoles(), ","))) {
                    check = false;
                }
            }
            // && check
            if (!CollectionUtils.isEmpty(menu.getAccess())) {
                if (!SecurityContext.getContext()
                        .access(StringUtils.join(menu.getAccess(), ","))) {
                    check = false;
                }
            }
            flag = check;
        }
        return flag;
    }
}
