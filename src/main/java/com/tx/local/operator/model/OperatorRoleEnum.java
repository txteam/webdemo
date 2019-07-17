/*
 * 描          述:  <描述>
 * 修  改   人:  Administrator
 * 修改时间:  2015年11月8日
 * <修改描述:>
 */
package com.tx.local.operator.model;

/**
 * 角色枚举<br/>
 * <功能详细描述>
 * 
 * @author  Administrator
 * @version  [版本号, 2015年11月8日]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public enum OperatorRoleEnum {
    
    ADMIN("ADMIN", "系统管理员");
    
    private final String id;
    
    private final String name;
    
    /** <默认构造函数> */
    private OperatorRoleEnum(String id, String name) {
        this.id = id;
        this.name = name;
    }
    
    /**
     * @return 返回 id
     */
    public String getId() {
        return id;
    }
    
    /**
     * @return 返回 name
     */
    public String getName() {
        return name;
    }
}
