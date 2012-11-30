package com.tx.component.menu.model;

import java.io.Serializable;

/**
  * <主菜单引用>
  * <功能详细描述>
  * 
  * @author  brady
  * @version  [版本号, 2012-11-30]
  * @see  [相关类/方法]
  * @since  [产品/模块版本]
 */
public abstract class MenuItemRef implements Serializable {
    
    /** 序列化索引 */
    private static final long serialVersionUID = 556111111339082225L;
    
    /** 关联的主菜单ID */
    private String menuId;
    
    /** 菜单文本 */
    private String text;
    
    /** 图标文件名 */
    private String icon;
    
    /** 上一次编辑者的工号 */
    private int modifier;
    
    /** 上一次编辑的时间 */
    private String modifyTime;
    
    /** 对应的主菜单项 */
    private MenuItem indexMenuItem;
    
    /**
     * @return 关联的主菜单ID
     */
    public String getMenuId() {
        return menuId;
    }
    
    /**
     * @param menuId
     *            关联的主菜单ID
     */
    public void setMenuId(String menuId) {
        this.menuId = menuId;
    }
    
    /**
     * @return 菜单文本
     */
    public String getText() {
        return text;
    }
    
    /**
     * @param text
     *            菜单文本
     */
    public void setText(String text) {
        this.text = text;
    }
    
    /**
     * @return 图标文件名
     */
    public String getIcon() {
        return icon;
    }
    
    /**
     * @param icon
     *            图标文件名
     */
    public void setIcon(String icon) {
        this.icon = icon;
    }
    
    /**
     * @return 上一次编辑者的工号
     */
    public int getModifier() {
        return modifier;
    }
    
    /**
     * @param modifier
     *            上一次编辑者的工号
     */
    public void setModifier(int modifier) {
        this.modifier = modifier;
    }
    
    /**
     * @return 上一次编辑的时间
     */
    public String getModifyTime() {
        return modifyTime;
    }
    
    /**
     * @param modifyTime
     *            上一次编辑的时间
     */
    public void setModifyTime(String modifyTime) {
        this.modifyTime = modifyTime;
    }
    
    /**
     * @return 对应的主菜单项
     */
    public MenuItem getIndexMenuItem() {
        return indexMenuItem;
    }
    
    /**
     * @param indexMenuItem
     *            对应的主菜单项
     */
    public void setIndexMenuItem(MenuItem indexMenuItem) {
        this.indexMenuItem = indexMenuItem;
    }
    
}
