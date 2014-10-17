/*
 * 描          述:  <描述>
 * 修  改   人:  PengQingyang
 * 修改时间:  2013-8-26
 * <修改描述:>
 */
package com.tx.component.mainframe.authloader;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.MapUtils;
import org.slf4j.helpers.MessageFormatter;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;

import com.tx.component.auth.context.AuthTypeItemContext;
import com.tx.component.auth.context.loader.AuthLoader;
import com.tx.component.auth.model.AuthItem;
import com.tx.component.auth.model.AuthItemImpl;
import com.tx.component.mainframe.context.MenuContext;
import com.tx.component.mainframe.model.MenuItem;
import com.tx.core.exceptions.util.AssertUtils;
import com.tx.core.tree.util.TreeUtils;

/**
 * 菜单权限加载器<br/>
 * <功能详细描述>
 * 
 * @author  PengQingyang
 * @version  [版本号, 2013-8-26]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
@Component("menuAuthLoader")
public class MenuAuthLoader implements AuthLoader {
    
    @Resource(name = "menuContext")
    private MenuContext menuContext;
    
    /**
     * @return
     */
    @Override
    public int getOrder() {
        return Ordered.HIGHEST_PRECEDENCE + 10;
    }
    
    /**
     * @return
     */
    @Override
    public Set<AuthItem> loadAuthItems(
            Map<String, AuthItem> sourceAuthItemMapping) {
        Map<String,AuthItemImpl> authItemMap = new HashMap<String,AuthItemImpl>();
        Set<MenuItem> menuItemSet = new HashSet<MenuItem>();
        Set<String> existAuthItemId = new HashSet<String>();
        Map<String, List<MenuItem>> menuType2MenuItemListMap = menuContext.getMenuType2MenuItemListMap();
        if (MapUtils.isEmpty(menuType2MenuItemListMap)) {
            return new HashSet<>();
        }
        
        //将所有菜单去重归并并生成树形结构以便生成菜单树形结构
        for (List<MenuItem> menuItemListTemp : menuType2MenuItemListMap.values()) {
            menuItemSet.addAll(menuItemListTemp);
        }
        List<MenuItem> menuItemList = new ArrayList<MenuItem>(menuItemSet);
        List<MenuItem> menuItemTreeList = TreeUtils.changeToTree(menuItemList);
        
        for (MenuItem menuItemTemp : menuItemTreeList) {
            iterateCreateAuthItem(authItemMap,
                    existAuthItemId,
                    null,
                    menuItemTemp);
        }
        Set<AuthItem> resSet = new HashSet<>();
        for(AuthItem authItem :authItemMap.values()){
            resSet.add(authItem);
        }
        return resSet;
    }
    
    /**
      * 根据菜单创建权限项<br/>
      *<功能详细描述>
      * @param parentMenuAuthItem
      * @param menuItem
      * @return [参数说明]
      * 
      * @return AuthItem [返回类型说明]
      * @exception throws [异常类型] [异常说明]
      * @see [类、类#方法、类#成员]
     */
    private void iterateCreateAuthItem(Map<String,AuthItemImpl> authItemMap,
            Set<String> existAuthItemIdSet, AuthItem parentMenuAuthItem,
            MenuItem menuItem) {
        AssertUtils.notEmpty(menuItem, "menuItem is null");
        
        //根据菜单生成对应权限项
        AuthItemImpl authItem = null;
        List<String> authKeyList = menuItem.getAuthKeyList();
        if (authKeyList != null) {
            for (String authKeyTemp : authKeyList) {
                if (existAuthItemIdSet.contains(authKeyTemp)) {
                    authItem = authItemMap.get(authKeyTemp);
                    continue;
                }else{
                    authItem = new AuthItemImpl();
                    authItem.setAuthType(AuthTypeItemContext.getContext()
                            .registeAuthTypeItem("AUTHTYPE_OPERATE",
                                    "操作权限",
                                    "菜单操作权限",
                                    true,
                                    true)
                            .getAuthType());
                    authItem.setConfigAble(true);
                    authItem.setDescription(MessageFormatter.arrayFormat("菜单[{}]的操作权限",
                            new Object[] { menuItem.getText() })
                            .getMessage());
                    authItem.setEditAble(false);
                    authItem.setId(authKeyTemp);
                    authItem.setName(menuItem.getText());
                    authItem.setParentId(parentMenuAuthItem != null ? parentMenuAuthItem.getId()
                            : null);
                    authItem.setValid(true);
                    authItem.setViewAble(true);
                    
                    authItemMap.put(authItem.getId(),authItem);
                    existAuthItemIdSet.add(authItem.getId());
                }
            }
        }
        
        parentMenuAuthItem = authItem == null ? parentMenuAuthItem : authItem;
        if (!CollectionUtils.isEmpty(menuItem.getChilds())) {
            for (MenuItem childMenuItem : menuItem.getChilds()) {
                if (childMenuItem == null) {
                    continue;
                }
                iterateCreateAuthItem(authItemMap,
                        existAuthItemIdSet,
                        parentMenuAuthItem,
                        childMenuItem);
            }
        }
    }
}
