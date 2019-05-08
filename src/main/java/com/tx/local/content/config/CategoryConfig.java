/*
 * 描          述:  <描述>
 * 修  改   人:  Administrator
 * 修改时间:  2016年8月6日
 * <修改描述:>
 */
package com.tx.local.content.config;

import java.util.ArrayList;
import java.util.List;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;
import com.thoughtworks.xstream.annotations.XStreamImplicit;
import com.thoughtworks.xstream.annotations.XStreamOmitField;

/**
 * 内容类型<br/>
 * <功能详细描述>
 * 
 * @author  Administrator
 * @version  [版本号, 2016年8月6日]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
@XStreamAlias("category")
public class CategoryConfig {
    
    /** 分类编码 */
    @XStreamOmitField
    private CategoryConfig parent;
    
    /** 对应枚举关键字：该字段可以为空 */
    @XStreamAsAttribute
    private String type;
    
    /** 对应枚举关键字：该字段可以为空 */
    @XStreamAsAttribute
    private String code;
    
    /** 内容信息类型名 */
    @XStreamAsAttribute
    private String name;
    
    /** 内容信息类型备注 */
    @XStreamAsAttribute
    private String remark;
    
    /** 类型类型排序号 */
    @XStreamAsAttribute
    private int orderIndex = 0;
    
    /** 子菜单项 */
    @XStreamImplicit(itemFieldName = "category")
    private List<CategoryConfig> categoryConfigList = new ArrayList<CategoryConfig>();
    
    /** 子菜单项 */
    @XStreamImplicit(itemFieldName = "level")
    private List<LevelConfig> levelConfigList = new ArrayList<LevelConfig>();
    
    /**
     * @return 返回 parent
     */
    public CategoryConfig getParent() {
        return parent;
    }
    
    /**
     * @param 对parent进行赋值
     */
    public void setParent(CategoryConfig parent) {
        this.parent = parent;
    }
    
    /**
     * @return 返回 type
     */
    public String getType() {
        return type;
    }
    
    /**
     * @param 对type进行赋值
     */
    public void setType(String type) {
        this.type = type;
    }
    
    /**
     * @return 返回 code
     */
    public String getCode() {
        return code;
    }
    
    /**
     * @param 对code进行赋值
     */
    public void setCode(String code) {
        this.code = code;
    }
    
    /**
     * @return 返回 name
     */
    public String getName() {
        return name;
    }
    
    /**
     * @param 对name进行赋值
     */
    public void setName(String name) {
        this.name = name;
    }
    
    /**
     * @return 返回 remark
     */
    public String getRemark() {
        return remark;
    }
    
    /**
     * @param 对remark进行赋值
     */
    public void setRemark(String remark) {
        this.remark = remark;
    }
    
    /**
     * @return 返回 orderIndex
     */
    public int getOrderIndex() {
        return orderIndex;
    }
    
    /**
     * @param 对orderIndex进行赋值
     */
    public void setOrderIndex(int orderIndex) {
        this.orderIndex = orderIndex;
    }
    
    /**
     * @return 返回 categoryConfigList
     */
    public List<CategoryConfig> getCategoryConfigList() {
        return categoryConfigList;
    }
    
    /**
     * @param 对categoryConfigList进行赋值
     */
    public void setCategoryConfigList(List<CategoryConfig> categoryConfigList) {
        this.categoryConfigList = categoryConfigList;
    }
    
    /**
     * @return 返回 levelConfigList
     */
    public List<LevelConfig> getLevelConfigList() {
        return levelConfigList;
    }
    
    /**
     * @param 对levelConfigList进行赋值
     */
    public void setLevelConfigList(List<LevelConfig> levelConfigList) {
        this.levelConfigList = levelConfigList;
    }
}
