/*
 * 描          述:  <描述>
 * 修  改   人:  PengQingyang
 * 修改时间:  2013-9-9
 * <修改描述:>
 */
package com.tx.component.operator.model;

/**
 * 主管类型<br/>
 * <功能详细描述>
 * 
 * @author  PengQingyang
 * @version  [版本号, 2013-9-9]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public enum ChiefTypeEnum {
    /** */
    POST("POST","职位"),
    
    /** */
    OPERATOR("OPERATOR","人员");
    
    /** key值 */
    private final String key;
    
    /** name值 */
    private final String name;

    /** <默认构造函数> */
    private ChiefTypeEnum(String key, String name) {
        this.key = key;
        this.name = name;
    }

    /**
     * @return 返回 key
     */
    public String getKey() {
        return key;
    }

    /**
     * @return 返回 name
     */
    public String getName() {
        return name;
    }
}
