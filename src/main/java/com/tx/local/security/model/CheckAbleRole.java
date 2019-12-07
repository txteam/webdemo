/*
 * 描          述:  <描述>
 * 修  改   人:  Administrator
 * 修改时间:  2019年11月26日
 * <修改描述:>
 */
package com.tx.local.security.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.tx.component.role.model.Role;

/**
 * 角色是否选中<br/>
 * <功能详细描述>
 * 
 * @author  Administrator
 * @version  [版本号, 2019年11月26日]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public class CheckAbleRole implements Role {
    
    /** 注释内容 */
    private static final long serialVersionUID = 5633939436937881122L;
    
    /** 权限项 */
    protected Role role;
    
    /** 是否选中 */
    private boolean checked = false;
    
    /** <默认构造函数> */
    public CheckAbleRole() {
        super();
    }
    
    /** <默认构造函数> */
    public CheckAbleRole(Role role) {
        super();
        this.role = role;
    }
    
    /**
     * @return
     */
    @Override
    public String getId() {
        return this.role.getId();
    }
    
    /**
     * @return
     */
    @Override
    public String getParentId() {
        return this.role.getParentId();
    }
    
    /**
     * @return
     */
    @Override
    public String getRoleTypeId() {
        return this.role.getRoleTypeId();
    }
    
    /**
     * @return
     */
    @Override
    public String getName() {
        return this.role.getName();
    }
    
    /**
     * @return
     */
    @Override
    public String getRemark() {
        return this.role.getRemark();
    }
    
    /**
     * @return 返回 checked
     */
    public boolean isChecked() {
        return checked;
    }
    
    /**
     * @param 对checked进行赋值
     */
    public void setChecked(boolean checked) {
        this.checked = checked;
    }
    
    /**
     * @return 返回 auth
     */
    @JsonIgnore
    public Role getRole() {
        return role;
    }
    
    /**
     * @param 对auth进行赋值
     */
    public void setRole(Role auth) {
        this.role = auth;
    }
    
}
