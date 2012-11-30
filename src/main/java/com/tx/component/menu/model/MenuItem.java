package com.tx.component.menu.model;

import java.util.ArrayList;
import java.util.List;

import com.tx.core.tree.model.TreeAble;

/**
  * <主菜单项配置>
  * 
  * @author  brady
  * @version  [版本号, 2012-11-30]
  * @see  [相关类/方法]
  * @since  [产品/模块版本]
 */
public class MenuItem implements Cloneable, TreeAble<List<MenuItem>, MenuItem> {
    
    /** ID */
    private String id;
    
    /** 父级菜单id */
    private String parentId;
    
    /** 菜单项文本 */
    private String text;
    
    /** 菜单项Tips */
    private String tips;
    
    /** 显示图标使用的样式 */
    private String icon;
    
    /** 链接地址 */
    private String href;
    
    /** 点击时触发的JavaScript */
    private String onClick;
    
    /** 点击事件监听器名称 */
    private String triggerEventType;
    
    /** 是否在主菜单上显示 */
    private boolean isVisible = true;
    
    /** 
     * 权限项 ：如果指定多个权限，需同时含有多个权限才能访问该菜单
     */
    private List<String> authIds = new ArrayList<String>();
    
    /** 子菜单项 */
    private List<MenuItem> childs = new ArrayList<MenuItem>();
    
    /**
     * @return ID
     */
    public String getId() {
        return id;
    }
    
    /**
     * @param id
     *            ID
     */
    public void setId(String id) {
        this.id = id;
    }
    
    /**
     * @return 菜单项文本
     */
    public String getText() {
        return text;
    }
    
    /**
     * @param text
     *            菜单项文本
     */
    public void setText(String text) {
        this.text = text;
    }
    
    /**
     * @return 菜单项Tips
     */
    public String getTips() {
        return tips;
    }
    
    /**
     * @param tips 菜单项Tips
     */
    public void setTips(String tips) {
        this.tips = tips;
    }
    
    /**
     * @return 链接地址
     */
    public String getHref() {
        return href;
    }
    
    /**
     * @param href 链接地址
     */
    public void setHref(String href) {
        this.href = href;
    }
    
    /**
     * @return 显示图标使用的样式
     */
    public String getIcon() {
        return icon;
    }
    
    /**
     * @param icon 显示图标使用的样式
     */
    public void setIcon(String icon) {
        this.icon = icon;
    }
    
    /**
     * @return 点击时触发的JavaScript
     */
    public String getOnClick() {
        return onClick;
    }
    
    /**
     * @param onClick 点击时触发的JavaScript
     */
    public void setOnClick(String onClick) {
        this.onClick = onClick;
    }
    
    /**
     * @return 是否在主菜单上显示
     */
    public boolean isVisible() {
        return isVisible;
    }
    
    /**
     * @param isVisible 是否在主菜单上显示
     */
    public void setVisible(boolean isVisible) {
        this.isVisible = isVisible;
    }
    
    /**
     * @return 返回 parentId
     */
    public String getParentId() {
        return parentId;
    }
    
    /**
     * @param 对parentId进行赋值
     */
    public void setParentId(String parentId) {
        this.parentId = parentId;
    }
    
    /**
     * @return 返回 triggerEventType
     */
    public String getTriggerEventType() {
        return triggerEventType;
    }
    
    /**
     * @param 对triggerEventType进行赋值
     */
    public void setTriggerEventType(String triggerEventType) {
        this.triggerEventType = triggerEventType;
    }
    
    /**
     * @return 返回 childs
     */
    public List<MenuItem> getChilds() {
        return childs;
    }
    
    /**
     * @param 对childs进行赋值
     */
    public void setChilds(List<MenuItem> childs) {
        this.childs = childs;
    }

    /**
     * @return 返回 authIds
     */
    public List<String> getAuthIds() {
        return authIds;
    }

    /**
     * @param 对authIds进行赋值
     */
    public void setAuthIds(List<String> authIds) {
        this.authIds = authIds;
    }
    
    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#toString()
     */
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{id=").append(id);
        sb.append(",text=").append(text);
        sb.append(",href=").append(href);
        sb.append(",authIds=").append(authIds);
        sb.append(",icon=").append(icon);
        sb.append(",triggerEventType=").append(triggerEventType);
        sb.append(",onClick=").append(onClick);
        sb.append(",childs=").append(childs);
        sb.append("}");
        return sb.toString();
    }
    
    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#clone()
     */
    @Override
    public MenuItem clone() throws CloneNotSupportedException {
        MenuItem cloned = (MenuItem) super.clone();
        
        cloned.authIds = new ArrayList<String>(authIds);
        cloned.childs = new ArrayList<MenuItem>(childs.size());
        for (MenuItem child : childs) {
            cloned.childs.add(child.clone());
        }
        
        return cloned;
    }
}
