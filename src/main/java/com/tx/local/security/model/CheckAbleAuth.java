/*
 * 描          述:  <描述>
 * 修  改   人:  Administrator
 * 修改时间:  2019年11月26日
 * <修改描述:>
 */
package com.tx.local.security.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.tx.component.auth.model.Auth;

/**
 * 权限是否选中<br/>
 * <功能详细描述>
 * 
 * @author  Administrator
 * @version  [版本号, 2019年11月26日]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public class CheckAbleAuth implements Auth {
    
    /** 注释内容 */
    private static final long serialVersionUID = 5633939436937881122L;
    
    /** 权限项 */
    private Auth auth;
    
    /** 是否选中 */
    private boolean checked = false;
    
    /** <默认构造函数> */
    public CheckAbleAuth() {
        super();
    }
    
    /** <默认构造函数> */
    public CheckAbleAuth(Auth auth) {
        super();
        this.auth = auth;
    }
    
    /**
     * @return
     */
    @Override
    public String getAttributes() {
        return this.auth.getAttributes();
    }
    
    /**
     * @param attributes
     */
    @Override
    public void setAttributes(String attributes) {
        this.auth.setAttributes(attributes);
    }
    
    /**
     * @return
     */
    @Override
    public String getId() {
        return this.auth.getId();
    }
    
    /**
     * @return
     */
    @Override
    public String getParentId() {
        return this.auth.getParentId();
    }
    
    /**
     * @return
     */
    @Override
    public String getAuthTypeId() {
        return this.auth.getAuthTypeId();
    }
    
    /**
     * @return
     */
    @Override
    public String getName() {
        return this.auth.getName();
    }
    
    /**
     * @return
     */
    @Override
    public String getRemark() {
        return this.auth.getRemark();
    }
    
    /**
     * @return
     */
    @Override
    public boolean isConfigAble() {
        return this.auth.isConfigAble();
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
    public Auth getAuth() {
        return auth;
    }
    
    /**
     * @param 对auth进行赋值
     */
    public void setAuth(Auth auth) {
        this.auth = auth;
    }
    
    /**
     * @return
     */
    @JsonIgnore
    @Override
    public List<Auth> getChildren() {
        return this.auth.getChildren();
    }
    
    /**
     * @param childs
     */
    @Override
    public void setChildren(List<Auth> children) {
        this.auth.setChildren(children);
    }
    
}
