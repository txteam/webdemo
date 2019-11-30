/*
 * 描          述:  <描述>
 * 修  改   人:  Administrator
 * 修改时间:  2019年11月18日
 * <修改描述:>
 */
package com.tx.local.security.model;

import com.tx.component.role.model.Role;
import com.tx.component.security.model.RoleAuthority;
import com.tx.core.exceptions.util.AssertUtils;

/**
 * 操作人员角色权限<br/>
 * <功能详细描述>
 * 
 * @author  Administrator
 * @version  [版本号, 2019年11月18日]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public class OperatorRoleAuthority implements RoleAuthority {
    
    /** 注释内容 */
    private static final long serialVersionUID = -7769850368661901465L;
    
    /** 操作人员角色 */
    private Role role;
    
    /** <默认构造函数> */
    public OperatorRoleAuthority() {
        super();
    }
    
    /** <默认构造函数> */
    public OperatorRoleAuthority(Role role) {
        super();
        this.role = role;
    }
    
    /**
     * @return
     */
    @Override
    public String getAuthority() {
        AssertUtils.notNull(this.role, "role is null.");
        AssertUtils.notEmpty(this.role.getId(), "role.id is empty.");
        
        String authority = this.role.getId();
        return authority;
    }
    
    /**
     * @return 返回 role
     */
    public Role getRole() {
        return role;
    }
    
    /**
     * @param 对role进行赋值
     */
    public void setRole(Role role) {
        this.role = role;
    }
}
