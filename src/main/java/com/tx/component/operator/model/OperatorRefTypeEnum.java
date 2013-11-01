/*
 * 描          述:  <描述>
 * 修  改   人:  brady
 * 修改时间:  2013-9-16
 * <修改描述:>
 */
package com.tx.component.operator.model;

/**
 * 人员引用类型枚举<br/>
 * <功能详细描述>
 * 
 * @author  brady
 * @version  [版本号, 2013-9-16]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public enum OperatorRefTypeEnum {
    /**
     * 人员职位
     */
    POST("post"), 
    /**
     * 人员组织
     */
    ORGANIZATION("organization");
    
    //销售分组等以后在此进行扩展
    
    /** <默认构造函数> */
    private OperatorRefTypeEnum(String code) {
        this.code = code;
    }

    /** 操作员引用类型编码 */
    private String code;
    
    /** 操作员引用类型名 */
    private String name;

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
}
