/*
 * 描          述:  <描述>
 * 修  改   人:  brady
 * 修改时间:  2013-8-22
 * <修改描述:>
 */
package com.tx.component.mainframe.context;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
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

import com.thoughtworks.xstream.XStream;
import com.tx.component.mainframe.config.MenuConfig;
import com.tx.component.mainframe.config.MenuItemConfig;
import com.tx.component.mainframe.config.MenusItemConfig;
import com.tx.component.mainframe.exception.MenuContextInitException;
import com.tx.component.mainframe.model.DefaultMenuItem;
import com.tx.component.mainframe.model.MenuItem;
import com.tx.core.exceptions.util.AssertUtils;
import com.tx.core.exceptions.util.ExceptionWrapperUtils;
import com.tx.core.util.XstreamUtils;

/**
 * 菜单容器配置器<br/>
 * <功能详细描述>
 * 
 * @author  brady
 * @version  [版本号, 2013-8-22]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public class MenuContextConfigurator implements InitializingBean,
        ApplicationContextAware {
    
    private static final Logger logger = LoggerFactory.getLogger(MenuContextConfigurator.class);
    
    private ApplicationContext context;
    
    /** 菜单配置所在目录 */
    private String menuConfigLocation = "classpath:context/menuConfig.xml";
    
    /** menuConfig的读取器 */
    private static final XStream menuConfigXstream = XstreamUtils.getXstream(MenuConfig.class);
    
    /** 菜单类型到菜单项目列表映射 */
    private Map<String, List<MenuItem>> menuType2MenuItemListMap;
    
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
        MenuConfig menuConfig = loadMenuConfig();
        this.menuType2MenuItemListMap = loadMenusType2MenuItemListMap(menuConfig);
    }
    
    /**
      * 重新加载菜单配置<br/>
      *<功能详细描述> [参数说明]
      * 
      * @return void [返回类型说明]
      * @exception throws [异常类型] [异常说明]
      * @see [类、类#方法、类#成员]
     */
    public void reLoad() {
        MenuConfig menuConfig = loadMenuConfig();
        this.menuType2MenuItemListMap = loadMenusType2MenuItemListMap(menuConfig);
    }
    
    /**
      * 获取一个以menusType为key的menuItemList Map
      *<功能详细描述>
      * @return [参数说明]
      * 
      * @return Map<String,List<MenuItem>> [返回类型说明]
      * @exception throws [异常类型] [异常说明]
      * @see [类、类#方法、类#成员]
     */
    public Map<String, List<MenuItem>> loadMenusType2MenuItemListMap(
            MenuConfig menuConfig) {
        logger.info("开始加载菜单配置：");
        Map<String, List<MenuItem>> menuType2MenuItemListMap = new HashMap<String, List<MenuItem>>();
        if (menuConfig == null
                || CollectionUtils.isEmpty(menuConfig.getMenusConfigList())) {
            return menuType2MenuItemListMap;
        }
        
        //读取并生成最终的菜单项目
        for (MenusItemConfig menusItemConfigTemp : menuConfig.getMenusConfigList()) {
            String menusItemType = menusItemConfigTemp.getType();
            List<MenuItem> menuItemList = loadMenusItemList(menuType2MenuItemListMap,
                    menusItemConfigTemp);
            
            menuType2MenuItemListMap.put(menusItemType, menuItemList);
        }
        logger.info("开始加载菜单配置：");
        return menuType2MenuItemListMap;
    }
    
    /**
      * 加载菜单menusItemConfig如果对应类型已经存在，则在原Map中获取原Map的list进行返回<br/> 
      *<功能简述>
      *<功能详细描述>
      * @param menuType2MenuItemListMap
      * @param menusItemConfig
      * @return [参数说明]
      * 
      * @return List<MenuItem> [返回类型说明]
      * @exception throws [异常类型] [异常说明]
      * @see [类、类#方法、类#成员]
     */
    private List<MenuItem> loadMenusItemList(
            Map<String, List<MenuItem>> menuType2MenuItemListMap,
            MenusItemConfig menusItemConfig) {
        String menusItemType = menusItemConfig.getType();
        
        List<MenuItem> menuItemList = null;
        if (!menuType2MenuItemListMap.containsKey(menusItemType)) {
            menuItemList = new ArrayList<MenuItem>();
        }
        else {
            menuItemList = menuType2MenuItemListMap.get(menusItemType);
        }
        
        //迭代menusItemConfig加载对应菜单项目配置
        if (CollectionUtils.isEmpty(menusItemConfig.getMenuConfigList())) {
            return menuItemList;
        }
        for (MenuItemConfig menuItemConfigTemp : menusItemConfig.getMenuConfigList()) {
            loadMenuItemConfig(null,
                    menuItemConfigTemp,
                    menuItemList,
                    menusItemType);
        }
        return menuItemList;
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
    private void loadMenuItemConfig(MenuItem parentMenuItem,
            MenuItemConfig menuItemConfig, List<MenuItem> mainMenuItemList,
            String menuItemType) {
        DefaultMenuItem menu = new DefaultMenuItem();
        menu.setHref(menuItemConfig.getHref());
        menu.setIcon(menuItemConfig.getIcon());
        menu.setId(menuItemConfig.getId());
        menu.setText(menuItemConfig.getText());
        menu.setTips(menuItemConfig.getTips());
        
        menu.setTarget(menuItemConfig.getTarget());
        menu.setSelectRefresh(menuItemConfig.isSelectRefresh());
        menu.setOpenNewEveryTime(menuItemConfig.isOpenNewEveryTime());
        
        menu.setWidth(menuItemConfig.getWidth());
        menu.setHeight(menuItemConfig.getHeight());
        menu.setModal(menuItemConfig.isModal());
        
        menu.setEventType(menuItemConfig.getEventType());
        menu.setParam(menuItemConfig.getParam());
        menu.setData(menuItemConfig.getData());
        
        menu.setValid(menuItemConfig.isValid());
        menu.setVisible(menuItemConfig.isVisible());
        
        menu.setType(menuItemType);
        
        if (parentMenuItem != null) {
            menu.setParentId(parentMenuItem.getId());
        }
        
        if (!StringUtils.isEmpty(menuItemConfig.getAuthKey())) {
            menu.setAuthKeyList(Arrays.asList(StringUtils.splitByWholeSeparatorPreserveAllTokens(menuItemConfig.getAuthKey(),
                    ",")));
        }else{
            if(parentMenuItem != null){
                menu.setAuthKeyList(parentMenuItem.getAuthKeyList());
            }
        }
        
        //如果菜单id重复，认为是配置存在问题，这里抛出异常，启动时解决
        AssertUtils.isTrue(!mainMenuItemList.contains(menu),
                new MenuContextInitException("菜单id重复,重复id为："
                        + menuItemConfig.getId()));
        mainMenuItemList.add(menu);
        
        if (menuItemConfig.getChilds() != null
                && menuItemConfig.getChilds().size() > 0) {
            for (MenuItemConfig childMenuItemConfig : menuItemConfig.getChilds()) {
                loadMenuItemConfig(menu,
                        childMenuItemConfig,
                        mainMenuItemList,
                        menuItemType);
            }
        }
    }
    
    /**
     * 加载菜单配置<br/>
     *     解析菜单配置文件生成解析后菜单配置对象<br/>
     *<功能详细描述>
     * @return [参数说明]
     * 
     * @return MenuConfig [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
    */
    private MenuConfig loadMenuConfig() {
        AssertUtils.notEmpty(this.menuConfigLocation,
                "menuConfigLocation is empty.");
        Resource menuConfigResource = this.context.getResource(this.menuConfigLocation);
        
        AssertUtils.isExist(menuConfigResource,
                "menuConfigResource is not exist.");
        InputStream in = null;
        
        //读取菜单配置文件生成解析后对象
        try {
            in = menuConfigResource.getInputStream();
            MenuConfig res = (MenuConfig) menuConfigXstream.fromXML(in);
            return res;
        }
        catch (IOException e) {
            throw ExceptionWrapperUtils.wrapperIOException(e,
                    "read menuConfigResource exception.");
        }
        finally {
            IOUtils.closeQuietly(in);
        }
    }

    /**
     * @return 返回 menuType2MenuItemListMap
     */
    public Map<String, List<MenuItem>> getMenuType2MenuItemListMap() {
        return menuType2MenuItemListMap;
    }
}
