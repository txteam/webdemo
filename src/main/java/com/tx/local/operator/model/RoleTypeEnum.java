package com.tx.local.operator.model;

/**
 * 
 * 角色类型枚举<br/>
 * <功能详细描述>
 * 
 * @author bobby
 * @version [版本号, 2015年11月7日]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
public enum RoleTypeEnum {
    
    /** 系统管理员 */
    JT_ADMIN_ROLE("JT_ADMIN_ROLE", "系统管理员角色"),
    
    /** 系统管理员 */
    JT_OPERATOR_ROLE("JT_OPERATOR_ROLE","系统用户角色");
    
    /** KEY值 */
    private String key;
    
    /** NAME值 */
    private String name;
    
    /** <默认构造函数> */
    private RoleTypeEnum(String key, String name) {
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
     * @param 对key进行赋值
     */
    public void setKey(String key) {
        this.key = key;
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
