/*
 * 描          述:  <描述>
 * 修  改   人:  Administrator
 * 修改时间:  2019年4月19日
 * <修改描述:>
 */
package com.tx.local.menu;

/**
 * 菜单常量<br/>
 * <功能详细描述>
 * 
 * @author  Administrator
 * @version  [版本号, 2019年4月19日]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public interface MenuConstants {
    
    /** 主菜单:如果没有值则默认为主菜单 */
    String CATALOG_NAV = "nav_catalog";
    
    /** 主菜单:如果没有值则默认为主菜单 */
    String CATALOG_MAIN = "main_catalog";
    
    /** 工具菜单 */
    String CATALOG_EXAMPLE = "example_catalog";
    
    /** 工具菜单 */
    String CATALOG_CONFIG = "config_catalog";
    
    /** 菜单目标: tab */
    String MENU_TYPE_NAV = "nav";
    
    /** 菜单目标: tab */
    String MENU_TYPE_TAB = "tab";
    
    /** 菜单目标： target */
    String MENU_TARGET_DIALOG = "dialog";
    
    /** 菜单目标: event */
    String MENU_TARGET_EVENT = "event";
    
    /** tab的属性：selectRefresh,点选时刷新tab内容 */
    String ATTRIBUTE_TAB_SELECTREFRESH = "selectRefresh";
    
    /** tab的属性：openNewEveryTime:点击时每次都打开一个新的tab */
    String ATTRIBUTE_TAB_OPENNEWEVERYTIME = "openNewEveryTime";
    
    /** 打开dialog的宽度 */
    String ATTRIBUTE_DIALOG_WIDTH = "width";
    
    /** 打开dialog的高度 */
    String ATTRIBUTE_DIALOG_HEIGHT = "height";
    
    /** dialog是否为模态 */
    String ATTRIBUTE_DIALOG_MODAL = "modal";
    
    /** 触发的事件类型 */
    String ATTRIBUTE_EVENT_EVENTTYPE = "eventType";
}
