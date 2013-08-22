/*
 * 描          述:  <描述>
 * 修  改   人:  PengQingyang
 * 修改时间:  2013-8-22
 * <修改描述:>
 */
package com.tx.component.mainframe.context;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.util.MultiValueMap;

import com.tx.component.auth.context.AuthSessionContext;
import com.tx.component.auth.model.AuthItemRef;
import com.tx.component.mainframe.model.MenuItem;
import com.tx.core.tree.util.TreeUtils;

/**
 * 权限容器<br/>
 * <功能详细描述>
 * 
 * @author  PengQingyang
 * @version  [版本号, 2013-8-22]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public class MenuContext implements InitializingBean{
    
    private MenuContextConfigurator menuContextConfigurator;
    
    private static MenuContext menuContext;
    
    /**
     * @throws Exception
     */
    @Override
    public void afterPropertiesSet() throws Exception {
        // TODO Auto-generated method stub
        
    }

    /**
      * 获取菜单容器<br/>
      *<功能详细描述>
      * @return [参数说明]
      * 
      * @return MenuContext [返回类型说明]
      * @exception throws [异常类型] [异常说明]
      * @see [类、类#方法、类#成员]
     */
    public static MenuContext getContext() {
        return menuContext;
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
    public List<MenuItem> getMenuItemTreeListFromCurrentSession(String menuType) {
        //获取到当前权限引用
        MultiValueMap<String, AuthItemRef> authItemRefMap = AuthSessionContext.getAuthRefMultiValueMapFromSession();
        
        //根据权限及菜单配置生成最终权限列表
        List<MenuItem> resList = new ArrayList<MenuItem>();
        for (MenuItem menuItemTemp : getMenuType2MenuItemListMap().get(menuType)) {
            if (menuItemTemp.getAuthKeyList() == null
                    || menuItemTemp.getAuthKeyList().size() == 0) {
                //如果对应菜单未配置权限，无需鉴权
                resList.add(menuItemTemp);
            }
            else {
                //根据权限判断是否需要鉴权
                for (String authKeyTemp : menuItemTemp.getAuthKeyList()) {
                    //一旦拥有其中任一权限即可拥有对应菜单
                    if (authItemRefMap.containsKey(authKeyTemp)) {
                        resList.add(menuItemTemp);
                    }
                }
            }
        }
        
        List<MenuItem> menuTreeList = TreeUtils.changToTree(resList);
        return menuTreeList;
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
    public List<MenuItem> getMenuItemListFromCurrentSession(String menuType) {
        //获取到当前权限引用
        MultiValueMap<String, AuthItemRef> authItemRefMap = AuthSessionContext.getAuthRefMultiValueMapFromSession();
        
        //根据权限及菜单配置生成最终权限列表
        List<MenuItem> resList = new ArrayList<MenuItem>();
        for (MenuItem menuItemTemp : getMenuType2MenuItemListMap().get(menuType)) {
            if (menuItemTemp.getAuthKeyList() == null
                    || menuItemTemp.getAuthKeyList().size() == 0) {
                //如果对应菜单未配置权限，无需鉴权
                resList.add(menuItemTemp);
            }
            else {
                //根据权限判断是否需要鉴权
                for (String authKeyTemp : menuItemTemp.getAuthKeyList()) {
                    //一旦拥有其中任一权限即可拥有对应菜单
                    if (authItemRefMap.containsKey(authKeyTemp)) {
                        resList.add(menuItemTemp);
                    }
                }
            }
        }
        return resList;
    }
}
