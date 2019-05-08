/*
 * 描          述:  <描述>
 * 修  改   人:  Administrator
 * 修改时间:  2016年8月6日
 * <修改描述:>
 */
package com.tx.local.content.config;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;
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
@XStreamAlias("level")
public class LevelConfig {
    
    /** 分类编码 */
    @XStreamOmitField
    private String categoryCode;
    
    /** 对应枚举关键字：该字段可以为空 */
    @XStreamAsAttribute
    private String code;
    
    /** 内容信息类型名 */
    @XStreamAsAttribute
    private String name;
    
    /** 内容信息类型备注 */
    @XStreamAsAttribute
    private String remark;
    
    /**
     * @return 返回 categoryCode
     */
    public String getCategoryCode() {
        return categoryCode;
    }
    
    /**
     * @param 对categoryCode进行赋值
     */
    public void setCategoryCode(String categoryCode) {
        this.categoryCode = categoryCode;
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
    
}
