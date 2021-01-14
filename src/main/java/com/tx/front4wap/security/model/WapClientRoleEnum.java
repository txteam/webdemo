/*
 * 描          述:  <描述>
 * 修  改   人:  Administrator
 * 修改时间:  2015年11月8日
 * <修改描述:>
 */
package com.tx.front4wap.security.model;

import java.io.Serializable;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.tx.component.role.model.Role;
import com.tx.core.support.json.BaseEnum;
import com.tx.core.support.json.BaseEnumJsonSerializer;

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
public enum WapClientRoleEnum implements Serializable, Role, BaseEnum {
    
    /** 系统操作人员:所有操作人员，默认拥有该角色 */
    WAP_CLIENT("WAP_CLIENT", "WAP端客户角色",
            WapRoleTypeEnum.ROLE_TYPE_WAP_ENUM.getId());
    
    /** 数据库中主键，系统启动后会进行写入 */
    private final String id;
    
    /** 名称 */
    private final String name;
    
    /** 角色类型id */
    private final String roleTypeId;
    
    /** <默认构造函数> */
    private WapClientRoleEnum(String id, String name, String roleTypeId) {
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
