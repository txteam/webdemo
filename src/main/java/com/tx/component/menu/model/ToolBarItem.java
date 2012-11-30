package com.tx.component.menu.model;

/**
 * 工具栏菜单项
 * 
 * @author Uniman
 * 
 */
public class ToolBarItem extends MenuItemRef implements Cloneable
{
    /**序列化索引*/
    private static final long serialVersionUID = 2027982634921272406L;

    /**ID*/
    private long id;

    /**本层次菜单中的排序索引*/
    private int index;

    /**
     * @return ID
     */
    public long getId()
    {
        return id;
    }

    /**
     * @param id
     *            ID
     */
    public void setId(long id)
    {
        this.id = id;
    }

    /**
     * @return 本层次菜单中的排序索引
     */
    public int getIndex()
    {
        return index;
    }

    /**
     * @param index
     *            本层次菜单中的排序索引
     */
    public void setIndex(int index)
    {
        this.index = index;
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#clone()
     */
    @Override
    public ToolBarItem clone() throws CloneNotSupportedException
    {
        return (ToolBarItem) super.clone();
    }

}
