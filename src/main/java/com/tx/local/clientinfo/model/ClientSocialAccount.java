/*
 * 描          述:  <描述>
 * 修  改   人:  Administrator
 * 修改时间:  2019年12月17日
 * <修改描述:>
 */
package com.tx.local.clientinfo.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.tx.core.support.json.JSONAttributesSupport;

import io.swagger.annotations.ApiModel;

/**
 * 操作人员第三方账户
 * <功能详细描述>
 * 
 * @author  Administrator
 * @version  [版本号, 2019年12月17日]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
@Entity
@Table(name = "cl_social_account")
@ApiModel("客户第三方账户")
public class ClientSocialAccount
        implements JSONAttributesSupport, Serializable {
    
    /** 注释内容 */
    private static final long serialVersionUID = -3077737209528564538L;
    
    /** 主键 */
    private String id;
    
    /** 操作人员第三方账户类型枚举 */
    private ClientSocialAccountTypeEnum type;
    
    /** 操作人员id */
    @Column(nullable = false, updatable = false)
    private String clientId;
    
    /** 客户唯一键 */
    @Column(nullable = false, updatable = false)
    private String uniqueId;
    
    /** 第三方的用户名，或昵称等 */
    private String username;
    
    /** 第三方用户头像url */
    private String headImgUrl;
    
    /** 昵称 */
    private String attributes;
    
    /** 创建时间 */
    private Date createDate;
    
    /** 最后更新时间 */
    private Date lastUpdateDate;
    
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
     * @return 返回 type
     */
    public ClientSocialAccountTypeEnum getType() {
        return type;
    }
    
    /**
     * @param 对type进行赋值
     */
    public void setType(ClientSocialAccountTypeEnum type) {
        this.type = type;
    }
    
    /**
     * @return 返回 clientId
     */
    public String getClientId() {
        return clientId;
    }
    
    /**
     * @param 对clientId进行赋值
     */
    public void setClientId(String clientId) {
        this.clientId = clientId;
    }
    
    /**
     * @return 返回 uniqueId
     */
    public String getUniqueId() {
        return uniqueId;
    }
    
    /**
     * @param 对uniqueId进行赋值
     */
    public void setUniqueId(String uniqueId) {
        this.uniqueId = uniqueId;
    }
    
    /**
     * @return 返回 username
     */
    public String getUsername() {
        return username;
    }
    
    /**
     * @param 对username进行赋值
     */
    public void setUsername(String username) {
        this.username = username;
    }
    
    /**
     * @return 返回 attributes
     */
    public String getAttributes() {
        return attributes;
    }
    
    /**
     * @param 对attributes进行赋值
     */
    public void setAttributes(String attributes) {
        this.attributes = attributes;
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
     * @return 返回 headImgUrl
     */
    public String getHeadImgUrl() {
        return headImgUrl;
    }
    
    /**
     * @param 对headImgUrl进行赋值
     */
    public void setHeadImgUrl(String headImgUrl) {
        this.headImgUrl = headImgUrl;
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
}
