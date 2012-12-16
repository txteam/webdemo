/*
 * 描          述:  <描述>
 * 修  改   人:  PengQingyang
 * 修改时间:  2012-12-16
 * <修改描述:>
 */
package com.tx.components.mainframe.xmlmodel;

import com.thoughtworks.xstream.annotations.XStreamAlias;


 /**
  * 菜单配置文件读取器
  * <功能详细描述>
  * 
  * @author  PengQingyang
  * @version  [版本号, 2012-12-16]
  * @see  [相关类/方法]
  * @since  [产品/模块版本]
  */
@XStreamAlias("menu_config")
public class MenuConfig {
    
    @XStreamAlias("main_menus_config")
    private MainMenusConfig mainMenusConfig;
    
    @XStreamAlias("tool_menus_config")
    private ToolMenusConfig toolMenusConfig;

    /**
     * @return 返回 mainMenusConfig
     */
    public MainMenusConfig getMainMenusConfig() {
        return mainMenusConfig;
    }

    /**
     * @param 对mainMenusConfig进行赋值
     */
    public void setMainMenusConfig(MainMenusConfig mainMenusConfig) {
        this.mainMenusConfig = mainMenusConfig;
    }

    /**
     * @return 返回 toolMenusConfig
     */
    public ToolMenusConfig getToolMenusConfig() {
        return toolMenusConfig;
    }

    /**
     * @param 对toolMenusConfig进行赋值
     */
    public void setToolMenusConfig(ToolMenusConfig toolMenusConfig) {
        this.toolMenusConfig = toolMenusConfig;
    }
}
