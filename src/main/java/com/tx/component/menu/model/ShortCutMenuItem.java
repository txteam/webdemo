package com.tx.component.menu.model;

import java.util.ArrayList;
import java.util.List;

/**
 * 快捷菜单的菜单项
 * 
 * @author Uniman
 * 
 */
public class ShortCutMenuItem extends ToolTipIndexMenuItemRef implements Cloneable
{

    /**
     * 序列化索引
     */
    private static final long serialVersionUID = 5722245465017143938L;

    /**
     * ID
     */
    private long id;

    /**
     * 菜单层级
     */
    private int level;

    /**
     * 上级菜单ID
     */
    private long parentId;

    /**
     * 本层次菜单中的排序索引
     */
    private int index;

    /**
     * 子菜单
     */
    private List<ShortCutMenuItem> children = new ArrayList<ShortCutMenuItem>();

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
     * @return 菜单层级
     */
    public int getLevel()
    {
        return level;
    }

    /**
     * @param level
     *            菜单层级
     */
    public void setLevel(int level)
    {
        this.level = level;
    }

    /**
     * @return 上级菜单ID
     */
    public long getParentId()
    {
        return parentId;
    }

    /**
     * @param parentId
     *            上级菜单ID
     */
    public void setParentId(long parentId)
    {
        this.parentId = parentId;
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

    /**
     * @return 子菜单
     */
    public List<ShortCutMenuItem> getChildren()
    {
        return children;
    }

    /**
     * @param children
     *            子菜单
     */
    public void setChildren(List<ShortCutMenuItem> children)
    {
        this.children = children;
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#clone()
     */
    @Override
    public ShortCutMenuItem clone() throws CloneNotSupportedException
    {
        ShortCutMenuItem cloned = (ShortCutMenuItem) super.clone();

        cloned.children = new ArrayList<ShortCutMenuItem>(children.size());
        for (ShortCutMenuItem item : children)
        {
            cloned.children.add(item.clone());
        }

        return cloned;
    }

}
