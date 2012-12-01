/*
 * 描          述:  <描述>
 * 修  改   人:  PengQingyang
 * 修改时间:  2012-12-1
 * <修改描述:>
 */
package com.tx.component.auth.model;

/**
 * <角色定义>
 * 通常角色被认定为权限的集合体，
 * 所以这里将角色放入权限模块中一并进行提供
 * 
 * @author  PengQingyang
 * @version  [版本号, 2012-12-1]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public class Role {
    
    private static final long serialVersionUID = -4655371768336413345L;
    
    /** 缺省的角色 */
    public final static String DEFAULT_ROLE = "1";
    
    /** 自定义角色 */
    public final static String CUSTOM_ROLE = "0";
    
    /** 角色名 */
    private String roleName = "";
    
    /** 角色描述 */
    private String roleDesc = "";
    
    /** 该角色的创建者 */
    private String createUserId;
    
    /** 组织ID */
    private String organizeId = "";
    
    /** 是否缺省的角色 0：是 1：不是 */
    private String defaultRole;
}
