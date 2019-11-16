/*
 * 描          述:  <描述>
 * 修  改   人:  Administrator
 * 修改时间:  2015年11月8日
 * <修改描述:>
 */
package com.tx.local.operator.model;

import java.io.Serializable;

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
public enum OperatorRoleEnum implements Serializable {
    
    /** 系统管理员 */
    SUPER_ADMIN("SUPER_ADMIN", "超级管理员", VirtualCenterEnum.JT.getCode()),
    
    /** 系统管理员 */
    ADMIN("ADMIN", "系统管理员", VirtualCenterEnum.JT.getCode());
    
    /** 数据库中主键，系统启动后会进行写入 */
    private final String id;
    
    /** 名称 */
    private final String name;
    
    /** 归属虚中心编码 */
    private final String virtualCenterCode;
    
    /** <默认构造函数> */
    private OperatorRoleEnum(String id, String name, String virtualCenterCode) {
        this.id = id;
        this.name = name;
        this.virtualCenterCode = virtualCenterCode;
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
     * @return 返回 virtualCenterCode
     */
    public String getVirtualCenterCode() {
        return virtualCenterCode;
    }
}
