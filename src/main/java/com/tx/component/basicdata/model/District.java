package com.tx.component.basicdata.model;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.tx.component.basicdata.annotation.BasicDataType;
import com.tx.core.jdbc.sqlsource.annotation.QueryConditionEqual;
import com.tx.core.jdbc.sqlsource.annotation.QueryConditionGreaterOrEqual;
import com.tx.core.jdbc.sqlsource.annotation.QueryConditionLess;
import com.tx.core.jdbc.sqlsource.annotation.QueryConditionLikeAfter;
import com.tx.core.jdbc.sqlsource.annotation.UpdateAble;

/**
 * 地理信息 <br/>
 * 
 * @author Bobby
 * @version [版本号, 2014年5月18日]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
@Entity
@Table(name = "bd_district")
@BasicDataType(name = "区域", common = false)
public class District implements TreeAbleBasicData<District> {
    
    /** 注释内容 */
    private static final long serialVersionUID = 6865409939223642746L;
    
    /** id */
    @Id
    private String id;
    
    /** 区域父节点 */
    @UpdateAble
    @QueryConditionEqual
    @Column(name = "parentId")
    private District parent;
    
    /** 区域层级 */
    @UpdateAble
    @QueryConditionEqual
    private int level;
    
    /** 编码 */
    @UpdateAble
    @QueryConditionEqual
    private String code;
    
    /** 行政区划编码 */
    @UpdateAble
    @QueryConditionEqual
    private String zipCode;
    
    /** 是否有效 */
    @UpdateAble
    @QueryConditionEqual
    private boolean valid = true;
    
    /** 是否可编辑 */
    @UpdateAble
    @QueryConditionEqual
    private boolean modifyAble = true;
    
    /** 省id */
    @UpdateAble
    @QueryConditionEqual
    @Column(name = "provinceId")
    @JsonIgnore
    private District province;
    
    /** 市id */
    @UpdateAble
    @QueryConditionEqual
    @JsonIgnore
    @Column(name = "cityId")
    private District city;
    
    /** 县id */
    @UpdateAble
    @QueryConditionEqual
    @Column(name = "countyId")
    @JsonIgnore
    private District county;
    
    /** 区域类型 */
    @QueryConditionEqual
    private DistrictTypeEnum type;
    
    /** 名称 */
    @UpdateAble
    @QueryConditionEqual
    private String name;
    
    /** 区域拼音 */
    @UpdateAble
    @QueryConditionLikeAfter
    private String pinyin;
    
    /** 区域简拼 */
    @UpdateAble
    @QueryConditionEqual
    private String py;
    
    /** 区域全名 */
    @UpdateAble
    @QueryConditionEqual
    private String fullName;
    
    /** 备注 */
    @UpdateAble
    private String remark;
    
    /** 创建日期 */
    @QueryConditionGreaterOrEqual(key = "minCreateDate")
    @QueryConditionLess(key = "maxCreateDate")
    private Date createDate;
    
    /** 最后更新时间 */
    @UpdateAble
    private Date lastUpdateDate;
    
    /** 子节点 */
    @Transient
    @OneToMany
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
        if (this.parent == null) {
            return null;
        }
        return this.parent.getId();
    }
    
    /**
     * @return 返回 parent
     */
    public District getParent() {
        return parent;
    }
    
    /**
     * @param 对parent进行赋值
     */
    public void setParent(District parent) {
        this.parent = parent;
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
     * @return 返回 province
     */
    public String getProvinceId() {
        if (this.province == null) {
            return null;
        }
        return this.province.getId();
    }
    
    /**
     * @return 返回 province
     */
    public District getProvince() {
        return province;
    }
    
    /**
     * @param 对province进行赋值
     */
    public void setProvince(District province) {
        this.province = province;
    }
    
    /**
     * @return 返回 city
     */
    public String getCityId() {
        if (this.city == null) {
            return null;
        }
        return this.city.getId();
    }
    
    /**
     * @return 返回 city
     */
    public District getCity() {
        return city;
    }
    
    /**
     * @param 对city进行赋值
     */
    public void setCity(District city) {
        this.city = city;
    }
    
    /**
     * @return 返回 country
     */
    public String getCountyId() {
        if (this.county == null) {
            return null;
        }
        return this.county.getId();
    }
    
    /**
     * @return 返回 country
     */
    public District getCounty() {
        return this.county;
    }
    
    /**
     * @param 对country进行赋值
     */
    public void setCounty(District county) {
        this.county = county;
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
    
    /**
     * @return 返回 level
     */
    public int getLevel() {
        return level;
    }
    
    /**
     * @param 对level进行赋值
     */
    public void setLevel(int level) {
        this.level = level;
    }
    
    /**
     * @return 返回 zipCode
     */
    public String getZipCode() {
        return zipCode;
    }
    
    /**
     * @param 对zipCode进行赋值
     */
    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }
    
    /**
     * @return 返回 pinyin
     */
    public String getPinyin() {
        return pinyin;
    }
    
    /**
     * @param 对pinyin进行赋值
     */
    public void setPinyin(String pinyin) {
        this.pinyin = pinyin;
    }
    
    /**
     * @return 返回 py
     */
    public String getPy() {
        return py;
    }
    
    /**
     * @param 对py进行赋值
     */
    public void setPy(String py) {
        this.py = py;
    }
}
