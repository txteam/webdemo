/*
 * 描          述:  <描述>
 * 修  改   人:  PengQingyang
 * 修改时间:  2012-12-16
 * <修改描述:>
 */
package com.tx.component.mainframe.config;

import java.util.List;

import org.apache.cxf.common.util.StringUtils;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;
import com.thoughtworks.xstream.annotations.XStreamImplicit;
import com.tx.component.mainframe.model.MenuItem;

/**
 * 菜单项配置
 * <功能详细描述>
 * 
 * @author  PengQingyang
 * @version  [版本号, 2012-12-16]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
@XStreamAlias("menus_config")
public class MenusItemConfig {
    
    @XStreamAlias("type")
    @XStreamAsAttribute
    private String type;
    
    @XStreamImplicit(itemFieldName = "menu")
    private List<MenuItemConfig> menuConfigList;
    
    /**
     * @return 返回 menuConfigList
     */
    public List<MenuItemConfig> getMenuConfigList() {
        return menuConfigList;
    }
    
    /**
     * @param 对menuConfigList进行赋值
     */
    public void setMenuConfigList(List<MenuItemConfig> menuConfigList) {
        this.menuConfigList = menuConfigList;
    }
    
    /**
     * @return 返回 type
     */
    public String getType() {
        return StringUtils.isEmpty(type) ? MenuItem.TYPE_MAIN_MENU
                : type.toUpperCase();
    }
    
    /**
     * @param 对type进行赋值
     */
    public void setType(String type) {
        if(StringUtils.isEmpty(type)){
            this.type = MenuItem.TYPE_MAIN_MENU;
        }else{
            this.type = type.toUpperCase();
        }
    }
}
