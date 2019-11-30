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
import javax.persistence.Table;
import javax.persistence.Transient;

import com.tx.core.tree.model.TreeAble;
import com.tx.core.util.ObjectUtils;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 组织<br/>
 *     组织在创建时需要指定对应虚中心
 *     组织的虚拟中心不允许更改，
 *     如果更改会增加系统很大的复杂度
 *     如果组织虚中心调整会影响相当多的历史数据记录
 *     设计认为：现阶段无需要为组织虚中心调整考虑太多的业务逻辑
 *     如果有这样的情况的话，最好通过禁用现有虚中心，新建虚中心的方式进行解决
 *     组织一旦初始化后，虚中心不允许进行更改的设计的话，业务数据仅需要根据组织纬度进行描述即可
 *     虚中心相关数据抽取，以及处理就可以通过关联的组织纬度抽取对应的虚中心。
 *     现在看来这样的设计是最佳的
 *     对组织现提供了删除，提供了禁用，删除的权限尽量不要为业务人员开放，
 *     组织关联过多不系统在系统中根据外键对组织删除做完整性约束。
 * <功能详细描述>
 * 
 * @author  PengQingyang
 * @version  [版本号, 2013-8-26]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
@Entity
@Table(name = "org_organization")
@ApiModel("组织")
public class Organization
        implements TreeAble<List<Organization>, Organization>, Serializable {
    
    /** 注释内容 */
    private static final long serialVersionUID = -6369964838562412893L;
    
    /** 组织唯一键 */
    @Id
    @Column(nullable = false, length = 64)
    private String id;
    
    /** 父组织id */
    @Column(nullable = false, length = 64)
    private String parentId;
    
    /** */
    @ApiModelProperty("虚中心id")
    @Column(nullable = false, length = 64)
    private String vcid;
    
    /**公司id*/
    @ApiModelProperty("公司")
    @Column(nullable = true, length = 64, name = "companyId")
    private Organization company;
    
    /** 编码 */
    @Column(nullable = false, length = 64)
    private String code;
    
    /** 组织类型 */
    @ApiModelProperty("组织类型")
    @Column(nullable = false, length = 64)
    private OrganizationTypeEnum type;
    
    /** 名称 */
    @Column(nullable = false, length = 64)
    private String name;
    
    /** 全称 */
    @Column(nullable = true, length = 256)
    private String fullName;
    
    /** 别名 */
    @Column(nullable = true, length = 64)
    private String alias;
    
    /**所在区域ID*/
    @Column(nullable = true, length = 64)
    private String districtId;
    
    /** 地址 */
    @Column(nullable = true, length = 64)
    private String address;
    
    /** 全地址名 */
    @Column(nullable = true, length = 256)
    private String fullAddress;
    
    /** 是否有效 */
    @Column(nullable = false)
    private boolean valid;
    
    /** 描述 */
    @Column(nullable = true, length = 256)
    private String remark;
    
    /** 创建时间 */
    @Column(nullable = false)
    private Date createDate;
    
    /** 创建时间 */
    @Column(nullable = false)
    private Date lastUpdateDate;
    
    /** 子级组织集合 */
    @Transient
    private List<Organization> children;
    
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
     * @return 返回 parentId
     */
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
     * @return 返回 address
     */
    public String getAddress() {
        return address;
    }
    
    /**
     * @param 对address进行赋值
     */
    public void setAddress(String address) {
        this.address = address;
    }
    
    /**
     * @return 返回 type
     */
    public OrganizationTypeEnum getType() {
        return type;
    }
    
    /**
     * @param 对type进行赋值
     */
    public void setType(OrganizationTypeEnum type) {
        this.type = type;
    }
    
    /**
     * @return 返回 districtId
     */
    public String getDistrictId() {
        return districtId;
    }
    
    /**
     * @param 对districtId进行赋值
     */
    public void setDistrictId(String districtId) {
        this.districtId = districtId;
    }
    
    /**
     * @return 返回 alias
     */
    public String getAlias() {
        return alias;
    }
    
    /**
     * @param 对alias进行赋值
     */
    public void setAlias(String alias) {
        this.alias = alias;
    }
    
    /**
     * @return 返回 fullAddress
     */
    public String getFullAddress() {
        return fullAddress;
    }
    
    /**
     * @param 对fullAddress进行赋值
     */
    public void setFullAddress(String fullAddress) {
        this.fullAddress = fullAddress;
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
     * @return 返回 children
     */
    public List<Organization> getChildren() {
        return children;
    }
    
    /**
     * @param 对children进行赋值
     */
    public void setChildren(List<Organization> children) {
        this.children = children;
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
     * @return 返回 company
     */
    public Organization getCompany() {
        return company;
    }

    /**
     * @param 对company进行赋值
     */
    public void setCompany(Organization company) {
        this.company = company;
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

    /**
     * @param obj
     * @return
     */
    @Override
    public boolean equals(Object obj) {
        return ObjectUtils.equals(this, obj, "id");
    }
    
    /**
     * @return
     */
    @Override
    public int hashCode() {
        return ObjectUtils.generateHashCode(super.hashCode(), this, "id");
    }
}
