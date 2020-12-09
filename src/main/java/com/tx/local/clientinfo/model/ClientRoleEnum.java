/*
 * 描          述:  <描述>
 * 修  改   人:  Administrator
 * 修改时间:  2015年11月8日
 * <修改描述:>
 */
package com.tx.local.clientinfo.model;

import java.io.Serializable;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.tx.component.role.model.Role;
import com.tx.core.support.json.BaseEnum;
import com.tx.core.support.json.BaseEnumJsonSerializer;
import com.tx.security4client.model.ClientRoleTypeEnum;

/**
 * 角色枚举<br/>
 *    比较特殊的两个角色
 *    该角色不用配置权限<br/>
 *    ：在登陆期间，当发现当前用户拥有相关角色时做特殊处理<br/>
 * <功能详细描述>
 * 
 * @author  Administrator
 * @version  [版本号, 2015年11月8日]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
@JsonSerialize(using = BaseEnumJsonSerializer.class)
public enum ClientRoleEnum implements Serializable, Role, BaseEnum {
    
    /** 系统操作人员:所有操作人员，默认拥有该角色 */
    CLIENT("CLIENT", "客户(企业工作台)",
            ClientRoleTypeEnum.ROLE_TYPE_CLIENT_ENUM.getId()),
    
    /** 系统管理员:默认拥有除超级管理员外的所有权限 */
    CLIENT_ADMIN("CLIENT_ADMIN", "客户管理员(企业工作台)",
            ClientRoleTypeEnum.ROLE_TYPE_CLIENT.getId());
    
    /** 数据库中主键，系统启动后会进行写入 */
    private final String id;
    
    /** 名称 */
    private final String name;
    
    /** 角色类型id */
    private final String roleTypeId;
    
    /** <默认构造函数> */
    private ClientRoleEnum(String id, String name, String roleTypeId) {
        this.id = id;
        this.name = name;
        this.roleTypeId = roleTypeId;
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
     * @return
     */
    @Override
    public String getRoleTypeId() {
        return this.roleTypeId;
    }
}
