/*
 * 描          述:  <描述>
 * 修  改   人:  PengQingyang
 * 修改时间:  2012-12-16
 * <修改描述:>
 */
package com.tx.component.mainframe.xmlmodel;

import java.util.List;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamImplicit;


 /**
  * 菜单项配置
  * <功能详细描述>
  * 
  * @author  PengQingyang
  * @version  [版本号, 2012-12-16]
  * @see  [相关类/方法]
  * @since  [产品/模块版本]
  */
@XStreamAlias("main_menu_config")
public class MainMenusConfig {
    
    @XStreamImplicit(itemFieldName="menu")
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
    
}
