package com.tx.local.security.model;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.tx.component.auth.model.AuthType;
import com.tx.core.support.json.BaseEnum;
import com.tx.core.support.json.BaseEnumJsonSerializer;

/**
 * 权限类型枚举<br/>
 * <功能详细描述>
 * 
 * @author bobby
 * @version [版本号, 2015年11月7日]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
@JsonSerialize(using = BaseEnumJsonSerializer.class)
public enum OperatorAuthTypeEnum implements AuthType, BaseEnum {
    
    /** 系统管理员 */
    AUTH_TYPE_OPERATOR_OPERATE("AUTH_TYPE_OPERATOR_OPERATE", "系统操作员操作权限"),
    
    /** 系统管理员 */
    AUTH_TYPE_OPERATOR_DATA("AUTH_TYPE_OPERATOR_DATA", "系统操作员数据权限");
    
    /** KEY值 */
    private final String id;
    
    /** NAME值 */
    private final String name;
    
    /** 备注 */
    private final String remark;
    
    /** <默认构造函数> */
    private OperatorAuthTypeEnum(String id, String name) {
        this.id = id;
        this.name = name;
        this.remark = "";
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
