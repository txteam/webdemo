/*
 * 描          述:  <描述>
 * 修  改   人:  PengQingyang
 * 修改时间:  2013-8-26
 * <修改描述:>
 */
package com.tx.component.operator.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.tx.component.mainframe.model.District;
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
public class Organization implements TreeAble<List<Organization>, Organization>{
    
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
    
    /** 是否是分行 */
    private boolean isBranch;
    
    /**省ID*/
    private String provinceId;
    
    /**市里ID*/
    private String cityId;
    
    /** 所属地区 */
    private String areaId;
    
    /** 描述 */
    private String description;
    
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
     * @return 返回 isBranch
     */
    public boolean isBranch() {
        return isBranch;
    }

    /**
     * @param 对isBranch进行赋值
     */
    public void setBranch(boolean isBranch) {
        this.isBranch = isBranch;
    }

    /**
     * @return 返回 provinceId
     */
    public String getProvinceId() {
        return provinceId;
    }

    /**
     * @param 对provinceId进行赋值
     */
    public void setProvinceId(String provinceId) {
        this.provinceId = provinceId;
    }

    /**
     * @return 返回 cityId
     */
    public String getCityId() {
        return cityId;
    }

    /**
     * @param 对cityId进行赋值
     */
    public void setCityId(String cityId) {
        this.cityId = cityId;
    }

    /**
     * @return 返回 areaId
     */
    public String getAreaId() {
        return areaId;
    }

    /**
     * @param 对areaId进行赋值
     */
    public void setAreaId(String areaId) {
        this.areaId = areaId;
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
     * @return 返回 description
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param 对description进行赋值
     */
    public void setDescription(String description) {
        this.description = description;
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
        return ObjectUtils.generateHashCode(this, "id");
    }
    
    public static void main(String[] args) {
        //System.out.println(hash);
        
        Organization a1 = new Organization();
        //a1.setId("abc");
        System.out.println(a1.hashCode());
        
        Organization a2 = new Organization();
        
        System.out.println(a2.hashCode());
        
        System.out.println(a1.equals(a2));
    }
    
    
}
