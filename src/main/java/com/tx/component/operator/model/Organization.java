/*
 * 描          述:  <描述>
 * 修  改   人:  PengQingyang
 * 修改时间:  2013-8-26
 * <修改描述:>
 */
package com.tx.component.operator.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.tx.component.operator.basicdata.ChiefTypeEnum;
import com.tx.component.operator.basicdata.OrganizationTypeEnum;
import com.tx.core.tree.model.TreeAble;
import com.tx.core.util.ObjectUtils;

/**
 * 组织<br/>
 * <功能详细描述>
 * 
 * @author  PengQingyang
 * @version  [版本号, 2013-8-26]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
@Entity
@Table(name = "OPER_ORGANIZATION")
public class Organization implements TreeAble<List<Organization>, Organization>,Serializable {
    
    /** 注释内容 */
    private static final long serialVersionUID = -6369964838562412893L;

    /** 主管类型：人员 */
    public static final int CHIEFTYPE_OPERATOR = 0;
    
    /** 主管类型：职位 */
    public static final int CHIEFTYPE_POST = 1;
    
    /** 组织唯一键 */
    @Id
    private String id;
    
    /** 父组织id */
    private String parentId;
    
    /** 编码 */
    private String code;
    
    /** 名称 */
    private String name;
    
    /** 全称 */
    private String fullName;
    
    /** 别名 */
    private String alias;
    
    /** 全地址名 */
    private String fullAddress;
    
    /** 地址 */
    private String address;
    
    /** 是否可用 */
    private boolean valid;
    
    /** 组织类型 */
    private OrganizationTypeEnum type;
    
    /**省ID*/
    private String districtId;
    
    /** 描述 */
    private String remark;
    
    /** 
     * 主管类型 人员 职位
     */
    private ChiefTypeEnum chiefType = ChiefTypeEnum.人员;
    
    /**
     * 与主管类型对应的主键：
     *     可能为人员id
     *     可能为职位id
     *     ....
     */
    private String chiefId;
    
    /** 子级组织集合 */
    private List<Organization> childs;
    
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
     * @return 返回 chiefType
     */
    public ChiefTypeEnum getChiefType() {
        return chiefType;
    }

    /**
     * @param 对chiefType进行赋值
     */
    public void setChiefType(ChiefTypeEnum chiefType) {
        this.chiefType = chiefType;
    }

    /**
     * @return 返回 chiefId
     */
    public String getChiefId() {
        return chiefId;
    }

    /**
     * @param 对chiefId进行赋值
     */
    public void setChiefId(String chiefId) {
        this.chiefId = chiefId;
    }

    /**
     * @return 返回 childs
     */
    public List<Organization> getChilds() {
        return childs;
    }
    
    /**
     * @param 对childs进行赋值
     */
    public void setChilds(List<Organization> childs) {
        this.childs = childs;
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
