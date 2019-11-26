package com.tx.local.security.model;

import com.tx.component.role.model.RoleType;

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
public enum RoleTypeEnum implements RoleType {
    
    /** 系统管理员 */
    ROLE_TYPE_ADMIN("ROLE_TYPE_ADMIN", "系统操作员"),
    
    /** 系统管理员 */
    ROLE_TYPE_OPERATOR("ROLE_TYPE_OPERATOR", "系统操作员"),
    
    /** 系统管理员 */
    ROLE_TYPE_CLIENT("ROLE_TYPE_CLIENT", "客户");
    
    /** KEY值 */
    private final String id;
    
    /** NAME值 */
    private final String name;
    
    /** 备注 */
    private final String remark;
    
    /** <默认构造函数> */
    private RoleTypeEnum(String id, String name) {
        this.id = id;
        this.name = name;
        this.remark = "";
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
    
    /**
     * @return 返回 remark
     */
    public String getRemark() {
        return remark;
    }
}
