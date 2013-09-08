/*
 * 描          述:  <描述>
 * 修  改   人:  PengQingyang
 * 修改时间:  2012-12-17
 * <修改描述:>
 */
package com.tx.component.mainframe.model;

import java.util.List;
import java.util.Map;

import com.tx.core.tree.model.TreeAble;

/**
 * 菜单项目接口
 * <功能详细描述>
 * 
 * @author  PengQingyang
 * @version  [版本号, 2012-12-17]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public interface MenuItem extends TreeAble<List<MenuItem>, MenuItem>,Cloneable {
    
    /** 主菜单 */
    public static final String TYPE_MAIN_MENU = "MAINMENU";
    
    /** 工具菜单 */
    public static final String TYPE_TOOL_MENU = "TOOLMENU";
    
    /**
      * 唯一键
      * <功能详细描述>
      * @return [参数说明]
      * 
      * @return String [返回类型说明]
      * @exception throws [异常类型] [异常说明]
      * @see [类、类#方法、类#成员]
     */
    public String getId();
    
    /**
      * 父菜单id
      * <功能详细描述>
      * @return [参数说明]
      * 
      * @return String [返回类型说明]
      * @exception throws [异常类型] [异常说明]
      * @see [类、类#方法、类#成员]
     */
    public String getParentId();
    
    /**
     * 菜单类型
     * @return 返回 type
     */
    public String getType();
    
    /**
     * 菜单显示文本
     * @return 返回 text
     */
    public String getText();
    
    /**
     * 菜单提示信息
     * @return 返回 tips
     */
    public String getTips();
    
    /**
     * 踩点href值
     * @return 返回 href
     */
    public String getHref();
    
    /**
     * 菜单对应的权限，权限与权限之间是或的关系
     * 只有任意拥有其中之一的权限即可拥有该菜单
     * @return 返回 authKey
     */
    public List<String> getAuthKeyList();
    
    /**
     * 菜单对应图标
     * @return 返回 icon
     */
    public String getIcon();
    
    /**
     * 是否有效
     * @return 返回 isValid
     */
    public boolean isValid();
    
    /**
     * 是否可见
     * @return 返回 isVisible
     */
    public boolean isVisible();
    
    /**
     * 打开类型 ： mainTabs,openDialog,triggerGlobalEvent
     * @return 返回 target
     */
    public String getTarget();

    /**
     * mainTabs时：选中后是否需要刷新
     * @return 返回 selectRefresh
     */
    public boolean isSelectRefresh();

    /**
     * mainTabs时：是否每次都打开新tab
     * @return 返回 openNewEveryTime
     */
    public boolean isOpenNewEveryTime();

    /**
     * openDialog: 宽
     * @return 返回 width
     */
    public int getWidth();

    /**
     * openDialog: 高
     * @return 返回 height
     */
    public int getHeight();

    /**
     * openDialog: 是否模态
     * @return 返回 isModal
     */
    public boolean isModal();

    /**
     * triggerGlobalEvent: 事件类型
     * @return 返回 eventType
     */
    public String getEventType();

    /**
     * triggerGlobalEvent: getData
     * @return 返回 params
     */
    public Map<String, String> getData();
    
    /**
      *<功能简述>
      *<功能详细描述>
      * @return [参数说明]
      * 
      * @return String [返回类型说明]
      * @exception throws [异常类型] [异常说明]
      * @see [类、类#方法、类#成员]
     */
    public String getParam();
    
    /**
      * 拷贝复制菜单项
      *<功能详细描述>
      * @return
      * @throws CloneNotSupportedException [参数说明]
      * 
      * @return Object [返回类型说明]
      * @exception throws [异常类型] [异常说明]
      * @see [类、类#方法、类#成员]
     */
    public Object clone() throws CloneNotSupportedException;
}
