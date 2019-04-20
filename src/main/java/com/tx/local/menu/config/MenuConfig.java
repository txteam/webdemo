/*
 * 描          述:  <描述>
 * 修  改   人:  PengQingyang
 * 修改时间:  2012-12-16
 * <修改描述:>
 */
package com.tx.local.menu.config;

import java.util.List;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamImplicit;

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
    
    @XStreamImplicit(itemFieldName = "catalog")
    private List<MenuCatalogConfig> catalogList;

    /**
     * @return 返回 catalogList
     */
    public List<MenuCatalogConfig> getCatalogList() {
        return catalogList;
    }

    /**
     * @param 对catalogList进行赋值
     */
    public void setCatalogList(List<MenuCatalogConfig> catalogList) {
        this.catalogList = catalogList;
    }
}
