/*
 * 描          述:  <描述>
 * 修  改   人:  Administrator
 * 修改时间:  2017年1月11日
 * <修改描述:>
 */
package com.tx.local.clientinfo.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.tx.component.role.model.Role;
import com.tx.component.role.model.RoleType;
import com.tx.core.support.initable.model.ConfigInitAble;

import io.swagger.annotations.ApiModel;

/**
 * 客户角色<br/>
 * <功能详细描述>
 * 
 * @author  Administrator
 * @version  [版本号, 2017年1月11日]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
@Entity
@Table(name = "cli_client_role")
@ApiModel("客户角色")
public class ClientRole implements ConfigInitAble,Role ,  Serializable {
    
    /** 注释内容 */
    private static final long serialVersionUID = -4686404756099827980L;
    
    /** id */
    @Id
    private String id;
    
    @Column(nullable = false, updatable = false, unique = true)
    private String code;
    
    /** 名称 */
    @Column(nullable = false)
    private String name;
    
    /** 是否有效 */
    @Column(nullable = false)
    private boolean valid = true;
    
    /** 是否可编辑 */
    @Column(nullable = false)
    private boolean modifyAble = true;
    
    /** 最后更新时间 */
    @Column(nullable = false)
    private Date lastUpdateDate;
    
    /** 创造时间 */
    @Column(nullable = false)
    private Date createDate;
    
    /** 备注 */
    private String remark;
    
    /**
     * @return 返回 id
     */
    public String getId() {
        return id;
    }
    
    /**
     * @param 对id进行赋值
     */
    public void setId(String id) {
        this.id = id;
    }
    
    /**
     * @return 返回 code
     */
    public String getCode() {
        return code;
    }
    
    /**
     * @param 对code进行赋值
     */
    public void setCode(String code) {
        this.code = code;
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
     * @return 返回 valid
     */
    public boolean isValid() {
        return valid;
    }
    
    /**
     * @param 对valid进行赋值
     */
    public void setValid(boolean valid) {
        this.valid = valid;
    }
    
    /**
     * @return 返回 modifyAble
     */
    public boolean isModifyAble() {
        return modifyAble;
    }
    
    /**
     * @param 对modifyAble进行赋值
     */
    public void setModifyAble(boolean modifyAble) {
        this.modifyAble = modifyAble;
    }
    
    /**
     * @return 返回 lastUpdateDate
     */
    public Date getLastUpdateDate() {
        return lastUpdateDate;
    }
    
    /**
     * @param 对lastUpdateDate进行赋值
     */
    public void setLastUpdateDate(Date lastUpdateDate) {
        this.lastUpdateDate = lastUpdateDate;
    }
    
    /**
     * @return 返回 createDate
     */
    public Date getCreateDate() {
        return createDate;
    }
    
    /**
     * @param 对createDate进行赋值
     */
    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }
    
    /**
     * @return 返回 remark
     */
    public String getRemark() {
        return remark;
    }

    @Override
    public String getRoleTypeId() {
        return null;
    }

    @Override
    public String getParentId() {
        return null;
    }

    @Override
    public RoleType getParent() {
        return null;
    }

    @Override
    public RoleType getRoleType() {
        return null;
    }

    /**
     * @param 对remark进行赋值
     */
    public void setRemark(String remark) {
        this.remark = remark;
    }
}
