package com.tx.component.menu.model;

/**
 * 具有ToolTip的主菜单引用项
 * 
 * @author Uniman
 * 
 */
public class ToolTipIndexMenuItemRef extends MenuItemRef
{

    /**
     * 序列化索引 
     */
    private static final long serialVersionUID = -1517723911286685225L;
    
    /**
     * ToolTip内容
     */
    private String toolTip;

    /**
     * @return ToolTip内容
     */
    public String getToolTip()
    {
        return toolTip;
    }

    /**
     * @param toolTip
     *            ToolTip内容
     */
    public void setToolTip(String toolTip)
    {
        this.toolTip = toolTip;
    }

}
