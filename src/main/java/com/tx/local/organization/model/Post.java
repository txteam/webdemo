/*
 * 描          述:  <描述>
 * 修  改   人:  PengQingyang
 * 修改时间:  2013-8-26
 * <修改描述:>
 */
package com.tx.local.organization.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.tx.core.tree.model.TreeAble;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 岗位(职位)<br/>
 *     职位和组织的关系<br/>
 *     不同的组织结构下面可能存在相同名字的职位
 *     或职责都相同的职位
 *     但在不同的组织结构下应该被认定为不同的职位。
 * 岗位包括：
 *    岗位描述
 *    岗位职责
 *    工作关系
 *    任职资格
 *    任职能力
 *    考核标准
 *    工作条件
 *    流程指引
 *    工作方式
 *    ......    
 *     
 *     在本类中，如果存在parent的职位，
 *     则默认当前的职位的organizationId与父级职位相同<br/>
 *     这个理念应该在界面上进行体现<br/>
 * <功能详细描述>
 * 
 * @author  PengQingyang
 * @version  [版本号, 2013-8-26]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
@Entity
@Table(name = "org_post")
@ApiModel("职位")
public class Post implements TreeAble<List<Post>, Post>, Serializable {
    
    /** 注释内容 */
    private static final long serialVersionUID = -2514159781332417436L;
    
    /** id */
    @Id
    @Column(nullable = false, length = 64)
    private String id;
    
    /** vcid */
    @ApiModelProperty("虚中心id")
    @Column(nullable = false, length = 64)
    private String vcid;
    
    /** 上级职位id */
    @ApiModelProperty("上级职位id")
    @Column(nullable = false, length = 64)
    private String parentId;
    
    /** 职位编码 */
    @Column(nullable = false, length = 64)
    private String code;
    
    /** 职位名称 */
    @Column(nullable = false, length = 64)
    private String name;
    
    /** 职位名称全名 */
    @Column(nullable = true, length = 256)
    private String fullName;
    
    /** 职位是否有效 */
    @Column(nullable = false)
    private boolean valid;
    
    /** 备注 */
    @Column(nullable = true, length = 256)
    private String remark;
    
    /** 组织 */
    @ManyToOne
    private String organizationId;
    
    /** 创建时间 */
    private Date createDate;
    
    /** 最后更新时间 */
    private Date lastUpdateDate;
    
    /** 子职位 */
    @Transient
    private List<Post> children;
    
    /**
     * @return 返回 childs
     */
    @Override
    public List<Post> getChildren() {
        return children;
    }
    
    /**
     * @param 对childs进行赋值
     */
    @Override
    public void setChildren(List<Post> children) {
        this.children = children;
    }
    
    /**
     * @return 返回 id
     */
    @Override
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
     * @return 返回 organizationId
     */
    public String getOrganizationId() {
        return organizationId;
    }
    
    /**
     * @param 对organizationId进行赋值
     */
    public void setOrganizationId(String organizationId) {
        this.organizationId = organizationId;
    }
    
    /**
     * @return 返回 parentId
     */
    @Override
    public String getParentId() {
        return parentId;
    }
    
    /**
     * @param 对parentId进行赋值
     */
    public void setParentId(String parentId) {
        this.parentId = parentId;
    }
    
    /**
     * @return 返回 remark
     */
    public String getRemark() {
        return remark;
    }
    
    /**
     * @param 对remark进行赋值
     */
    public void setRemark(String remark) {
        this.remark = remark;
    }
    
    /**
     * @return 返回 fullName
     */
    public String getFullName() {
        return fullName;
    }
    
    /**
     * @param 对fullName进行赋值
     */
    public void setFullName(String fullName) {
        this.fullName = fullName;
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
     * @return 返回 vcid
     */
    public String getVcid() {
        return vcid;
    }
    
    /**
     * @param 对vcid进行赋值
     */
    public void setVcid(String vcid) {
        this.vcid = vcid;
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
