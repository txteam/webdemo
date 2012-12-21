/*
 * 描          述:  <描述>
 * 修  改   人:  PengQingyang
 * 修改时间:  2012-12-16
 * <修改描述:>
 */
package com.tx.component.mainframe.service;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;


import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import com.thoughtworks.xstream.XStream;
import com.tx.component.auth.context.AuthContext;
import com.tx.component.auth.model.AuthItemRef;
import com.tx.component.mainframe.model.DefaultMenuItem;
import com.tx.component.mainframe.model.MenuItem;
import com.tx.component.mainframe.xmlmodel.MenuConfig;
import com.tx.component.mainframe.xmlmodel.MenuItemConfig;
import com.tx.core.exceptions.parameter.ParameterIsEmptyException;
import com.tx.core.exceptions.parameter.ParameterIsInvalidException;
import com.tx.core.exceptions.resource.ResourceLoadException;
import com.tx.core.tree.util.TreeUtils;
import com.tx.core.util.XstreamUtils;

/**
 * 菜单业务层
 * <功能详细描述>
 * 
 * @author  PengQingyang
 * @version  [版本号, 2012-12-16]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
@Component("menuService")
public class MenuService implements InitializingBean, ApplicationContextAware {
    
    private static final Logger logger = LoggerFactory.getLogger(MenuService.class);
    
    private ApplicationContext context;
    
    /** 菜单配置所在目录 */
    private String menuConfigLocation = "classpath:context/menuConfig.xml";
    
    /** menuConfig的读取器 */
    private static final XStream xstream = XstreamUtils.getXstream(MenuConfig.class);
    
    private List<MenuItem> mainMenuItemList;
    
    private List<MenuItem> toolMenuItemList;
    
    /**
     * @param applicationContext
     * @throws BeansException
     */
    @Override
    public void setApplicationContext(ApplicationContext applicationContext)
            throws BeansException {
        this.context = applicationContext;
    }
    
    /**
     * @throws Exception
     */
    @Override
    public void afterPropertiesSet() throws Exception {
        loadMenuConfig();
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
    public List<MenuItem> getMainMenuTreeListByCurrentSession(){
        //获取到当前权限引用
        Map<String, AuthItemRef> authItemRefMap = AuthContext.getContext()
                .getCurrentSessionContext()
                .getCurrentOperatorAuthMapFromSession();
        
        //根据权限及菜单配置生成最终权限列表
        List<MenuItem> resList = new ArrayList<MenuItem>();
        for(MenuItem menuItemTemp: this.mainMenuItemList){
            if(menuItemTemp.getAuthKeyList() == null ||
                    menuItemTemp.getAuthKeyList().size() == 0){
                //如果对应菜单未配置权限，无需鉴权
                resList.add(menuItemTemp);
            }else{
                //根据权限判断是否需要鉴权
                for(String authKeyTemp : menuItemTemp.getAuthKeyList()){
                    //一旦拥有其中任一权限即可拥有对应菜单
                    if(authItemRefMap.containsKey(authKeyTemp)){
                        resList.add(menuItemTemp);
                    }
                }
            }
        }
        
        List<MenuItem> menuTreeList = TreeUtils.changToTree(resList);
        return menuTreeList;
    }
    
    /**
      * 根据权限获取当前人员的工具菜单树
      * <功能详细描述>
      * @return [参数说明]
      * 
      * @return List<MenuItem> [返回类型说明]
      * @exception throws [异常类型] [异常说明]
      * @see [类、类#方法、类#成员]
     */
    public List<MenuItem> getToolMenuTreeListByCurrentSession(){
        //获取到当前权限引用
        Map<String, AuthItemRef> authItemRefMap = AuthContext.getContext()
                .getCurrentSessionContext()
                .getCurrentOperatorAuthMapFromSession();
        
        //根据权限及菜单配置生成最终权限列表
        List<MenuItem> resList = new ArrayList<MenuItem>();
        for(MenuItem menuItemTemp: this.toolMenuItemList){
            if(menuItemTemp.getAuthKeyList() == null ||
                    menuItemTemp.getAuthKeyList().size() == 0){
                //如果对应菜单未配置权限，无需鉴权
                resList.add(menuItemTemp);
            }else{
                //根据权限判断是否需要鉴权
                for(String authKeyTemp : menuItemTemp.getAuthKeyList()){
                    //一旦拥有其中任一权限即可拥有对应菜单
                    if(authItemRefMap.containsKey(authKeyTemp)){
                        resList.add(menuItemTemp);
                    }
                }
            }
        }
        
        List<MenuItem> menuTreeList = TreeUtils.changToTree(resList);
        return menuTreeList;
    }
    
    /**
      * 重新加载菜单配置项
      * <功能详细描述> [参数说明]
      * 
      * @return void [返回类型说明]
      * @exception throws [异常类型] [异常说明]
      * @see [类、类#方法、类#成员]
     */
    public void reLoadMenuConfig(){
        loadMenuConfig();
    }
    
    /**
      * 加载菜单配置
      * <功能详细描述> [参数说明]
      * 
      * @return void [返回类型说明]
      * @exception throws [异常类型] [异常说明]
      * @see [类、类#方法、类#成员]
     */
    public void loadMenuConfig() {
        if (menuConfigLocation == null
                || !this.context.getResource(menuConfigLocation).exists()) {
            throw new ParameterIsEmptyException("菜单配置不存在");
        }
        
        MenuConfig menuConfig = generateMenuConfigByConfigResource();
        
        List<MenuItem> mainMenuItemList = parseMainMenuItem(menuConfig);
        List<MenuItem> toolMenuItemList = parseToolsMenuItem(menuConfig);
        
        this.mainMenuItemList = mainMenuItemList;
        this.toolMenuItemList = toolMenuItemList;
    }
    
    /**
     * 解析生成主菜单项
     * <功能详细描述>
     * @param menuConfig
     * @return [参数说明]
     * 
     * @return List<MenuItem> [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
    */
   private List<MenuItem> parseToolsMenuItem(MenuConfig menuConfig) {
       List<MenuItem> toolsMenuItemList = new ArrayList<MenuItem>();
       
       if (menuConfig == null
               || menuConfig.getToolMenusConfig() == null
               || CollectionUtils.isEmpty(menuConfig.getToolMenusConfig().getMenuConfigList())) {
           return toolsMenuItemList;
       }
       
       for (MenuItemConfig menuItemConfig : menuConfig.getToolMenusConfig()
               .getMenuConfigList()) {
           if (StringUtils.isEmpty(menuItemConfig.getId())) {
               continue;
           }
           createMenuItem(null,
                   menuItemConfig,
                   toolsMenuItemList,
                   MenuItem.TYPE_TOOL_MENU);
       }
       
       return toolsMenuItemList;
   }
    
    /**
      * 解析生成主菜单项
      * <功能详细描述>
      * @param menuConfig
      * @return [参数说明]
      * 
      * @return List<MenuItem> [返回类型说明]
      * @exception throws [异常类型] [异常说明]
      * @see [类、类#方法、类#成员]
     */
    private List<MenuItem> parseMainMenuItem(MenuConfig menuConfig) {
        List<MenuItem> mainMenuItemList = new ArrayList<MenuItem>();
        
        if (menuConfig == null
                || menuConfig.getMainMenusConfig() == null
                || menuConfig.getMainMenusConfig().getMenuConfigList().size() == 0) {
            return mainMenuItemList;
        }
        
        for (MenuItemConfig menuItemConfig : menuConfig.getMainMenusConfig()
                .getMenuConfigList()) {
            if (StringUtils.isEmpty(menuItemConfig.getId())) {
                continue;
            }
            createMenuItem(null,
                    menuItemConfig,
                    mainMenuItemList,
                    MenuItem.TYPE_MAIN_MENU);
        }
        
        return mainMenuItemList;
    }
    
    /**
      * 迭代创建菜单项
      * <功能详细描述>
      * @param parentMenuItem
      * @param menuItemConfig
      * @param mainMenuItemList
      * @param menuItemType [参数说明]
      * 
      * @return void [返回类型说明]
      * @exception throws [异常类型] [异常说明]
      * @see [类、类#方法、类#成员]
     */
    private void createMenuItem(MenuItem parentMenuItem,
            MenuItemConfig menuItemConfig, List<MenuItem> mainMenuItemList,
            String menuItemType) {
        DefaultMenuItem menu = new DefaultMenuItem();
        menu.setHref(menuItemConfig.getHref());
        menu.setIcon(menuItemConfig.getIcon());
        menu.setId(menuItemConfig.getId());
        menu.setText(menuItemConfig.getText());
        menu.setTips(menuItemConfig.getTips());
        
        menu.setValid(menuItemConfig.isValid());
        menu.setVisible(menuItemConfig.isVisible());
        
        menu.setType(menuItemType);
        
       
        if (parentMenuItem != null) {
            menu.setParentId(parentMenuItem.getId());
        }
        
        if (!StringUtils.isEmpty(menuItemConfig.getAuthKey())) {
            menu.setAuthKeyList(Arrays.asList(StringUtils.splitByWholeSeparatorPreserveAllTokens(menuItemConfig.getAuthKey(),
                    ",")));
        }
        
        //如果菜单id重复，认为是配置存在问题，这里抛出异常，启动时解决
        if(mainMenuItemList.contains(menu)){
            throw new ParameterIsInvalidException("菜单id重复,重复id为：" + menuItemConfig.getId());
        }
        mainMenuItemList.add(menu);
        
        if (menuItemConfig.getChilds() != null
                && menuItemConfig.getChilds().size() > 0) {
            for (MenuItemConfig childMenuItemConfig : menuItemConfig.getChilds()) {
                createMenuItem(menu,
                        childMenuItemConfig,
                        mainMenuItemList,
                        menuItemType);
            }
        }
    }
    
    /**
      * 根据菜单配置文件生成菜单配置对象
      * <功能详细描述>
      * @return [参数说明]
      * 
      * @return MenuConfig [返回类型说明]
      * @exception throws [异常类型] [异常说明]
      * @see [类、类#方法、类#成员]
     */
    private MenuConfig generateMenuConfigByConfigResource() {
        MenuConfig menuConfig = null;
        InputStream io = null;
        Resource menuConfigResource = this.context.getResource(menuConfigLocation);
        if (menuConfigResource == null || !menuConfigResource.exists()) {
            throw new ParameterIsInvalidException("菜单配置资源不存在：配置路径为{}",
                    this.menuConfigLocation);
        }
        try {
            io = menuConfigResource.getInputStream();
            menuConfig = (MenuConfig) xstream.fromXML(io);
        }
        catch (Exception e) {
            logger.info(e.toString(),e);
            throw new ResourceLoadException("加载菜单配置异常:{}", e, e.toString());
        }
        finally {
            IOUtils.closeQuietly(io);
        }
        return menuConfig;
    }

    /**
     * @return 返回 mainMenuItemList
     */
    public List<MenuItem> getMainMenuItemList() {
        return mainMenuItemList;
    }

    /**
     * @param 对mainMenuItemList进行赋值
     */
    public void setMainMenuItemList(List<MenuItem> mainMenuItemList) {
        this.mainMenuItemList = mainMenuItemList;
    }

    /**
     * @return 返回 menuConfigLocation
     */
    public String getMenuConfigLocation() {
        return menuConfigLocation;
    }

    /**
     * @param 对menuConfigLocation进行赋值
     */
    public void setMenuConfigLocation(String menuConfigLocation) {
        this.menuConfigLocation = menuConfigLocation;
    }

    /**
     * @return 返回 toolMenuItemList
     */
    public List<MenuItem> getToolMenuItemList() {
        return toolMenuItemList;
    }

    /**
     * @param 对toolMenuItemList进行赋值
     */
    public void setToolMenuItemList(List<MenuItem> toolMenuItemList) {
        this.toolMenuItemList = toolMenuItemList;
    }
    
}
