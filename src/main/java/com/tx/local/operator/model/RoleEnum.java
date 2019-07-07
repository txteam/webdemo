/*
 * 描          述:  <描述>
 * 修  改   人:  Administrator
 * 修改时间:  2015年11月8日
 * <修改描述:>
 */
package com.tx.local.operator.model;

import java.util.ArrayList;
import java.util.List;

import com.tx.local.vitualcenter.model.VirtualCenterEnum;

/**
 * 角色枚举<br/>
 * <功能详细描述>
 * 
 * @author  Administrator
 * @version  [版本号, 2015年11月8日]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public enum RoleEnum {
    
    ADMIN(RoleTypeEnum.JT_ADMIN_ROLE, "系统管理员", VirtualCenterEnum.JT);
    
    private RoleTypeEnum roleType;
    
    private String name;
    
    private VirtualCenterEnum virtualCenterKey;
    
    /** <默认构造函数> */
    private RoleEnum(RoleTypeEnum roleType, String name,
            VirtualCenterEnum virtualCenterKey) {
        this.roleType = roleType;
        this.name = name;
        this.virtualCenterKey = virtualCenterKey;
    }
    
    /**
     * @return 返回 roleType
     */
    public RoleTypeEnum getRoleType() {
        return roleType;
    }
    
    /**
     * @param 对roleType进行赋值
     */
    public void setRoleType(RoleTypeEnum roleType) {
        this.roleType = roleType;
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
    
    /**
     * @return 返回 virtualCenterKey
     */
    public VirtualCenterEnum getVirtualCenterKey() {
        return virtualCenterKey;
    }
    
    /**
     * @param 对virtualCenterKey进行赋值
     */
    public void setVirtualCenterKey(VirtualCenterEnum virtualCenterKey) {
        this.virtualCenterKey = virtualCenterKey;
    }
    
    /**
     * 
      *<功能简述>
      *<功能详细描述>
      * @param roleTypeEnum
      * @return [参数说明]
      * 
      * @return List<RoleEnum> [返回类型说明]
      * @exception throws [异常类型] [异常说明]
      * @see [类、类#方法、类#成员]
     */
    public static List<RoleEnum> roleEnums(RoleTypeEnum roleTypeEnum) {
        List<RoleEnum> enums = new ArrayList<RoleEnum>();
        for (RoleEnum roleEnum : RoleEnum.values()) {
            if (roleEnum.getRoleType().equals(roleTypeEnum)) {
                enums.add(roleEnum);
            }
        }
        return enums;
    }
}
