/*
 * 描          述:  <描述>
 * 修  改   人:  Administrator
 * 修改时间:  2020年1月10日
 * <修改描述:>
 */
package com.tx.local.servicelog.model;

import org.springframework.core.Ordered;

/**
 * 业务日志属性<br/>
 * <功能详细描述>
 * 
 * @author  Administrator
 * @version  [版本号, 2020年1月10日]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public class ServiceLogProperty implements Ordered {
    
    /** 字段 */
    private String field;
    
    /** 名称 */
    private String title;
    
    /** 宽度 */
    private int width = 100;
    
    /** 是否影藏 */
    private boolean hidden = false;
    
    /** 排序值 */
    private int order = 0;
    
    /** <默认构造函数> */
    public ServiceLogProperty() {
        super();
    }
    
    /** <默认构造函数> */
    public ServiceLogProperty(String field, String title, boolean hidden,
            int order) {
        super();
        this.field = field;
        this.title = title;
        this.hidden = hidden;
        this.order = order;
    }
    
    /**
     * @return 返回 field
     */
    public String getField() {
        return field;
    }
    
    /**
     * @param 对field进行赋值
     */
    public void setField(String field) {
        this.field = field;
    }
    
    /**
     * @return 返回 title
     */
    public String getTitle() {
        return title;
    }
    
    /**
     * @param 对title进行赋值
     */
    public void setTitle(String title) {
        this.title = title;
    }
    
    /**
     * @return 返回 width
     */
    public int getWidth() {
        return width;
    }
    
    /**
     * @param 对width进行赋值
     */
    public void setWidth(int width) {
        this.width = width;
    }
    
    /**
     * @return 返回 hidden
     */
    public boolean isHidden() {
        return hidden;
    }
    
    /**
     * @param 对hidden进行赋值
     */
    public void setHidden(boolean hidden) {
        this.hidden = hidden;
    }
    
    /**
     * @return 返回 order
     */
    public int getOrder() {
        return order;
    }
    
    /**
     * @param 对order进行赋值
     */
    public void setOrder(int order) {
        this.order = order;
    }
    
}
