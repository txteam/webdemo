package com.tx.front4client.security.model;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.tx.component.role.model.RoleType;
import com.tx.core.support.json.BaseEnum;
import com.tx.core.support.json.BaseEnumJsonSerializer;

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
@JsonSerialize(using = BaseEnumJsonSerializer.class)
public enum ClientRoleTypeEnum implements RoleType, BaseEnum {
    
    /** 系统管理员 */
    ROLE_TYPE_CLIENT_ENUM("ROLE_TYPE_CLIENT_ENUM", "预定义客户角色"),
    
    /** 系统管理员 */
    ROLE_TYPE_CLIENT("ROLE_TYPE_CLIENT", "客户角色");
    
    /** KEY值 */
    private final String id;
    
    /** NAME值 */
    private final String name;
    
    /** 备注 */
    private final String remark;
    
    /** <默认构造函数> */
    private ClientRoleTypeEnum(String id, String name) {
        this.id = id;
        this.name = name;
        this.remark = name;
    }
    
    /**
     * @return 返回 id
     */
    @Override
    public String getId() {
        return id;
    }
    
    /**
     * @return 返回 name
     */
    @Override
    public String getName() {
        return name;
    }
    
    /**
     * @return 返回 remark
     */
    @Override
    public String getRemark() {
        return remark;
    }
}
