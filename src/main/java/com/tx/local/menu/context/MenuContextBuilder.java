/*
 * 描          述:  <描述>
 * 修  改   人:  brady
 * 修改时间:  2013-8-22
 * <修改描述:>
 */
package com.tx.local.menu.context;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanNameAware;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.core.io.Resource;

import com.thoughtworks.xstream.XStream;
import com.tx.core.exceptions.util.ExceptionWrapperUtils;
import com.tx.core.util.MessageUtils;
import com.tx.core.util.XstreamUtils;
import com.tx.local.menu.config.MenuCatalogConfig;
import com.tx.local.menu.config.MenuConfig;
import com.tx.local.menu.config.MenuItemConfig;
import com.tx.local.menu.exception.MenuContextInitException;
import com.tx.local.menu.model.Menu;
import com.tx.local.menu.model.MenuCatalogItem;
import com.tx.local.menu.model.MenuItem;
import com.tx.local.menu.model.MenuNode;

/**
 * 菜单容器配置器<br/>
 * <功能详细描述>
 * 
 * @author  brady
 * @version  [版本号, 2013-8-22]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public abstract class MenuContextBuilder extends MenuContextConfigurator
        implements ApplicationContextAware, BeanNameAware {
    
    /** 日志记录句柄 */
    protected static final Logger logger = LoggerFactory
            .getLogger(MenuContextBuilder.class);
    
    /** menuConfig的读取器 */
    protected static final XStream menuConfigXstream = XstreamUtils
            .getXstream(MenuConfig.class);
    
    /** spring容器句柄 */
    protected static ApplicationContext applicationContext;
    
    /** spring容器句柄 */
    protected static String beanName;
    
    /** 菜单配置所在目录 */
    protected String menuConfigLocation = "classpath:context/menu_config.xml";
    
    /** 菜单类型到菜单项目列表映射 */
    protected Map<String, MenuCatalogItem> catalogMap = new HashMap<>();
    
    /** 菜单类型到菜单项目列表映射 */
    protected Map<String, List<Menu>> catalog2menusMap = new HashMap<>();
    
    /** 菜单类型到菜单项目列表映射 */
    protected Map<String, List<MenuNode>> catalog2nodesMap = new HashMap<>();
    
    /**
     * @param applicationContext
     * @throws BeansException
     */
    @Override
    public void setApplicationContext(ApplicationContext applicationContext)
            throws BeansException {
        MenuContextBuilder.applicationContext = applicationContext;
    }
    
    /**
     * @param name
     */
    @Override
    public void setBeanName(String name) {
        MenuContextBuilder.beanName = name;
    }
    
    /**
     * @throws Exception
     */
    @Override
    protected void doBuild() throws Exception {
        doParseMenuConfig();
    }
    
    /**
     * 解析菜单配置<br/>
     * <功能详细描述> [参数说明]
     * 
     * @return void [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    protected void doParseMenuConfig() {
        //解析配置文件
        MenuConfig menuConfig = parseMenuConfig();
        if (menuConfig == null
                || CollectionUtils.isEmpty(menuConfig.getCatalogList())) {
            logger.warn("菜单配置无内容.");
            return;
        }
        
        logger.info("开始加载菜单配置：");
        //读取并生成最终的菜单项目
        Map<String, MenuCatalogItem> catalogMap = new HashMap<String, MenuCatalogItem>();
        Map<String, List<Menu>> catalog2menuListMap = new HashMap<String, List<Menu>>();
        Map<String, List<MenuNode>> catalog2menuNodeListMap = new HashMap<String, List<MenuNode>>();
        for (MenuCatalogConfig catalogTemp : menuConfig.getCatalogList()) {
            String catalogUpperCase = catalogTemp.getId().toUpperCase();
            
            logger.debug("   ...加载菜单分类: catalog: [{}]", catalogTemp.getId());
            //构建菜单目录项目
            MenuCatalogItem catalog = doBuildMenuCatalogItem(catalogTemp);
            List<Menu> menuList = new ArrayList<>();
            List<MenuNode> childrenMenuNodeList = doBuildMenuNodeList(menuList,
                    catalog,
                    null,
                    catalogTemp.getChildren());
            
            catalog.setDescendants(menuList);
            catalog.setChildren(childrenMenuNodeList);
            
            catalogMap.put(catalogUpperCase, catalog);
            catalog2menuListMap.put(catalogUpperCase, menuList);
            catalog2menuNodeListMap.put(catalogUpperCase, childrenMenuNodeList);
        }
        
        this.catalogMap = catalogMap;
        this.catalog2menusMap = catalog2menuListMap;
        this.catalog2nodesMap = catalog2menuNodeListMap;
        logger.info("开始加载菜单配置：");
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
    private MenuConfig parseMenuConfig() {
        if (StringUtils.isEmpty(this.menuConfigLocation)) {
            logger.warn("菜单配置文件地址位空.");
            return null;
        }
        
        //取得菜单配置资源
        Resource menuConfigResource = MenuContextBuilder.applicationContext
                .getResource(this.menuConfigLocation);
        if (!menuConfigResource.exists()) {
            logger.warn("菜单配置文件不存在.");
            return null;
        }
        
        //读取菜单配置文件生成解析后对象
        logger.warn("菜单配置文件加载成功:{}", this.menuConfigLocation);
        try (InputStream in = menuConfigResource.getInputStream()) {
            MenuConfig res = (MenuConfig) menuConfigXstream.fromXML(in);
            return res;
        } catch (IOException e) {
            throw ExceptionWrapperUtils.wrapperIOException(e, "解析菜单配置异常.");
        }
    }
    
    /**
     * 构建菜单节点项<br/>
     * <功能详细描述>
     * @param menuList
     * @param catalog
     * @param parent
     * @param menuConfigs
     * @return [参数说明]
     * 
     * @return List<MenuNode> [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    private List<MenuNode> doBuildMenuNodeList(List<Menu> menuList,
            MenuCatalogItem catalog, Menu parent,
            List<MenuItemConfig> menuConfigs) {
        List<MenuNode> resList = new ArrayList<>();
        if (CollectionUtils.isEmpty(menuConfigs)) {
            return resList;
        }
        
        for (MenuItemConfig micTemp : menuConfigs) {
            MenuNode menunode = doBuildMenuNode(menuList,
                    catalog,
                    parent,
                    micTemp);
            resList.add(menunode);
        }
        return resList;
    }
    
    /**
     * 迭代创建菜单项
     * <功能详细描述>
     * @param parentMenuItem
     * @param menuConfig
     * @param mainMenuItemList
     * @param menuItemType [参数说明]
     * 
     * @return void [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
    */
    private MenuNode doBuildMenuNode(List<Menu> menuList,
            MenuCatalogItem catalog, Menu parent, MenuItemConfig menuConfig) {
        MenuItem menu = new MenuItem();
        
        menu.setCatalog(catalog);
        menu.setId(menuConfig.getId());
        menu.setText(menuConfig.getText());
        menu.setHref(menuConfig.getHref());
        menu.setIcon(menuConfig.getIcon());
        menu.setTips(menuConfig.getTips());
        menu.setType(menuConfig.getType());
        menu.setAttributes(menuConfig.getAttributes());
        
        if (!StringUtils.isEmpty(menuConfig.getAuths())) {
            String[] auths = StringUtils.splitByWholeSeparatorPreserveAllTokens(
                    menuConfig.getAuths(), ",");
            if (!ArrayUtils.isEmpty(auths)) {
                menu.setAuths(new HashSet<>(Arrays.asList(auths)));
            }
        }
        if (!StringUtils.isEmpty(menuConfig.getRoles())) {
            String[] rolse = StringUtils.splitByWholeSeparatorPreserveAllTokens(
                    menuConfig.getRoles(), ",");
            
            if (!ArrayUtils.isEmpty(rolse)) {
                menu.setRoles(new HashSet<>(Arrays.asList(rolse)));
            }
        }
        if (!StringUtils.isEmpty(menuConfig.getAccess())) {
            String[] access = StringUtils
                    .splitByWholeSeparatorPreserveAllTokens(
                            menuConfig.getAccess(), ",");
            if (!ArrayUtils.isEmpty(access)) {
                menu.setAccess(new HashSet<>(Arrays.asList(access)));
            }
        }
        if (parent != null) {
            menu.setParentId(parent.getId());
            
            //当存在父级菜单时，父级菜单需要的权限以及角色，都需要附给子级菜单
            menu.getAuths().addAll(parent.getAuths());
            menu.getRoles().addAll(parent.getRoles());
            menu.getAccess().addAll(parent.getAccess());
        }
        
        logger.debug("   ......加载菜单项: catalog:[{}] | id:[{}] text:[{}]",
                catalog.getId(),
                menu.getId(),
                menu.getText());
        
        if (menuList.contains(menu)) {
            String errorMessage = MessageUtils.format(
                    "   ......加载菜单项错误: 重复菜单项: 菜单分类:{} 菜单ID:{} 菜单名:{}",
                    new Object[] { menu.getCatalog().getId(), menu.getId(),
                            menu.getText() });
            logger.error(errorMessage);
            throw new MenuContextInitException(errorMessage);
        } else {
            menuList.add(menu);
        }
        
        MenuNode mn = new MenuNode(menu);
        List<MenuNode> childs = doBuildMenuNodeList(menuList,
                catalog,
                menu,
                menuConfig.getChildren());
        mn.setChildren(childs);
        
        return mn;
    }
    
    /** 
     * 构建菜单目录项<br/>
     * <功能详细描述>
     * @param catalogConfigTemp
     * @return [参数说明]
     * 
     * @return MenuCatalogItem [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    private MenuCatalogItem doBuildMenuCatalogItem(
            MenuCatalogConfig catalogConfigTemp) {
        MenuCatalogItem catalog = new MenuCatalogItem();
        catalog.setId(catalogConfigTemp.getId());
        catalog.setText(catalogConfigTemp.getText());
        catalog.setType(catalogConfigTemp.getType());
        catalog.setAttributes(catalogConfigTemp.getAttributes());
        
        if (!StringUtils.isEmpty(catalogConfigTemp.getAuths())) {
            String[] authorities = StringUtils
                    .splitByWholeSeparatorPreserveAllTokens(
                            catalogConfigTemp.getAuths(), ",");
            if (!ArrayUtils.isEmpty(authorities)) {
                catalog.setAuths(new HashSet<>(Arrays.asList(authorities)));
            }
        }
        if (!StringUtils.isEmpty(catalogConfigTemp.getRoles())) {
            String[] roles = StringUtils.splitByWholeSeparatorPreserveAllTokens(
                    catalogConfigTemp.getRoles(), ",");
            if (!ArrayUtils.isEmpty(roles)) {
                catalog.setRoles(new HashSet<>(Arrays.asList(roles)));
            }
        }
        if (!StringUtils.isEmpty(catalogConfigTemp.getAccess())) {
            String[] access = StringUtils
                    .splitByWholeSeparatorPreserveAllTokens(
                            catalogConfigTemp.getAccess(), ",");
            if (!ArrayUtils.isEmpty(access)) {
                catalog.setAccess(new HashSet<>(Arrays.asList(access)));
            }
        }
        return catalog;
    }
}
