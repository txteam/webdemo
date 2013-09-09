/*
 * 描          述:  <描述>
 * 修  改   人:  PengQingyang
 * 修改时间:  2013-8-26
 * <修改描述:>
 */
package com.tx.component.mainframe.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.tx.component.mainframe.basicdata.DistrictTypeEnum;
import com.tx.core.tree.model.TreeAble;


 /**
  * 区域；地方；行政区
  * <功能详细描述>
  * 
  * @author  PengQingyang
  * @version  [版本号, 2013-8-26]
  * @see  [相关类/方法]
  * @since  [产品/模块版本]
  */
@Entity
@Table(name = "BASIC_DISTRICT")
public class District implements TreeAble<List<District>, District>,Serializable{
    
    /** 注释内容 */
    private static final long serialVersionUID = -3959052246810066341L;

    /** 区域编码  */
    @Id
    private String id;
    
    /** 区域父级id */
    private String parentId;
    
    /** 区域名 */
    private String name;
    
    /** 区域全名 */
    private String fullName;
    
    /** 区域编码 */
    private String code;
    
    /** 区域对应身份证编码 */
    private String idCardCode;
    
    /**邮政编码*/    
    private String postalCode;
    
    /** 区域描述 */
    private String remark;
    
    /** 区域类型 */
    private DistrictTypeEnum type;
    
    @Transient
    private List<District> childs;

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
     * @return 返回 idCardCode
     */
    public String getIdCardCode() {
        return idCardCode;
    }

    /**
     * @param 对idCardCode进行赋值
     */
    public void setIdCardCode(String idCardCode) {
        this.idCardCode = idCardCode;
    }

    /**
     * @return 返回 postalCode
     */
    public String getPostalCode() {
        return postalCode;
    }

    /**
     * @param 对postalCode进行赋值
     */
    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
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
     * @return 返回 type
     */
    public DistrictTypeEnum getType() {
        return type;
    }

    /**
     * @param 对type进行赋值
     */
    public void setType(DistrictTypeEnum type) {
        this.type = type;
    }

    /**
     * @return 返回 childs
     */
    public List<District> getChilds() {
        return childs;
    }

    /**
     * @param 对childs进行赋值
     */
    public void setChilds(List<District> childs) {
        this.childs = childs;
    }
}
