/*
 * 描          述:  <描述>
 * 修  改   人:  Administrator
 * 修改时间:  2018年12月27日
 * <修改描述:>
 */
package com.tx.local.basicdata.model;

/**
 * 客户端类型枚举<br/>
 * <功能详细描述>
 * 
 * @author  Administrator
 * @version  [版本号, 2018年12月27日]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public enum AgentTypeEnum {
    
    UNKNOW("UNKNOW", "UNKNOW"),
    
    WX("WX", "WX"),
    
    ANDROID("ANDROID", "ANDROID"),
    
    IPHONE("IPHONE", "IPHONE"),
    
    IPAD("IPAD", "IPAD");
    
    private final String code;
    
    private final String name;
    
    /** <默认构造函数> */
    private AgentTypeEnum(String code, String name) {
        this.code = code;
        this.name = name;
    }
    
    /**
     * @return 返回 code
     */
    public String getCode() {
        return code;
    }
    
    /**
     * @return 返回 name
     */
    public String getName() {
        return name;
    }
}
