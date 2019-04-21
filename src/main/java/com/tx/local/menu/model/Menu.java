/*
 * 描          述:  <描述>
 * 修  改   人:  PengQingyang
 * 修改时间:  2012-12-17
 * <修改描述:>
 */
package com.tx.local.menu.model;

import java.util.Map;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * 菜单项目接口<br/>
 * <功能详细描述>
 * 
 * @author  PengQingyang
 * @version  [版本号, 2012-12-17]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public interface Menu {
    
    /**
     * 唯一键<br/>
     * @return
     */
    public String getId();
    
    /**
     * 父菜单id<br/>
     * @return
     */
    public String getParentId();
    
    /**
     * 获取菜单所属类目<br/>
     * <功能详细描述>
     * @return [参数说明]
     * 
     * @return MenuCatalogItem [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @JsonIgnore
    public MenuCatalogItem getCatalog();
    
    /**
     * 菜单显示文本<br/>
     * @return 返回 text
     */
    public String getText();
    
    /**
     * 菜单对应图标<br/>
     * @return 返回 icon
     */
    public String getIcon();
    
    /**
     * 菜单提示信息<br/>
     * @return 返回 tips
     */
    public String getTips();
    
    /**
     * 踩点href值<br/>
     * @return 返回 href
     */
    public String getHref();
    
    /**
     * 打开类型 ： tabs,dialog,event
     * @return 返回 target
     */
    public String getType();
    
    /**
     * 是否有效<br/>
     * <功能详细描述>
     * @return [参数说明]
     * 
     * @return boolean [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
    */
    public boolean isValid();
    
    /**
     * 是否可编辑<br/>
     * <功能详细描述>
     * @return [参数说明]
     * 
     * @return boolean [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
    */
    public boolean isModifyAble();
    
    /**
     * 获取菜单额外属性<br/>
     * <功能详细描述>
     * @return [参数说明]
     * 
     * @return Map<String,String> [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    public Map<String, String> getAttributes();
    
    /**
     * 菜单对应的权限，权限与权限之间是或的关系
     * 只有任意拥有其中之一的权限即可拥有该菜单
     * <功能详细描述>
     * @return [参数说明]
     * 
     * @return String [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    public Set<String> getAuthorities();
    
    /**
     * 拥有的角色<br/>
     * <功能详细描述>
     * @return [参数说明]
     * 
     * @return String[] [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    public Set<String> getRoles();
}
