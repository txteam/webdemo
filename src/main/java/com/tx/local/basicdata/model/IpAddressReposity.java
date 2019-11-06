package com.tx.local.basicdata.model;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.tx.component.basicdata.annotation.BasicDataEntity;
import com.tx.component.basicdata.model.BasicData;
import com.tx.component.basicdata.model.BasicDataViewTypeEnum;

/**
 * Ip地址数据库<br/>
 * <功能详细描述>
 * 
 * @author  Administrator
 * @version  [版本号, 2016年11月22日]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
@Entity
@Table(name = "bd_ipaddress_reposity")
@BasicDataEntity(name = "Ip地址数据库", viewType = BasicDataViewTypeEnum.PAGEDLIST)
public class IpAddressReposity implements BasicData {
    
    /** 注释内容 */
    private static final long serialVersionUID = -3660117634692886902L;
    
    /** id */
    @Id
    private String id;
    
    /** 行政区划id */
    @Column(name = "districtId")
    private District district;
    
    //    /** 运营商id*/
    //    @UpdateAble
    //    @QueryConditionEqual
    //    @Column(name = "telecomOperatorId")
    //    private TelecomOperator telecomOperator;
    
    /** 编码 */
    private String code;
    
    /** ip地址名：存放>ip段 */
    private String name;
    
    /** 洲id */
    private String continentName;
    
    /** 国家id */
    private String nationName;
    
    /** 省id */
    private String provinceName;
    
    /** 市id */
    private String cityName;
    
    /** 县id */
    private String countyName;
    
    /** 国家英文名称*/
    private String enName;
    
    /** 简拼*/
    private String en;
    
    /** 是否编辑 */
    private boolean modifyAble = true;
    
    /** 是否有效 */
    private boolean valid = true;
    
    /** 备注 */
    private String remark;
    
    /** 纬度 */
    private BigDecimal latitude;
    
    /** 经度 */
    private BigDecimal longitude;
    
    /** 创建日期 */
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
     * @return 返回 district
     */
    public District getDistrict() {
        return district;
    }
    
    /**
     * @param 对district进行赋值
     */
    public void setDistrict(District district) {
        this.district = district;
    }
    
    //    /**
    //     * @return 返回 telecomOperator
    //     */
    //    public TelecomOperator getTelecomOperator() {
    //        return telecomOperator;
    //    }
    //    
    //    /**
    //     * @param 对telecomOperator进行赋值
    //     */
    //    public void setTelecomOperator(TelecomOperator telecomOperator) {
    //        this.telecomOperator = telecomOperator;
    //    }
    
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
     * @return 返回 continentName
     */
    public String getContinentName() {
        return continentName;
    }
    
    /**
     * @param 对continentName进行赋值
     */
    public void setContinentName(String continentName) {
        this.continentName = continentName;
    }
    
    /**
     * @return 返回 nationName
     */
    public String getNationName() {
        return nationName;
    }
    
    /**
     * @param 对nationName进行赋值
     */
    public void setNationName(String nationName) {
        this.nationName = nationName;
    }
    
    /**
     * @return 返回 provinceName
     */
    public String getProvinceName() {
        return provinceName;
    }
    
    /**
     * @param 对provinceName进行赋值
     */
    public void setProvinceName(String provinceName) {
        this.provinceName = provinceName;
    }
    
    /**
     * @return 返回 cityName
     */
    public String getCityName() {
        return cityName;
    }
    
    /**
     * @param 对cityName进行赋值
     */
    public void setCityName(String cityName) {
        this.cityName = cityName;
    }
    
    /**
     * @return 返回 countyName
     */
    public String getCountyName() {
        return countyName;
    }
    
    /**
     * @param 对countyName进行赋值
     */
    public void setCountyName(String countyName) {
        this.countyName = countyName;
    }
    
    /**
     * @return 返回 enName
     */
    public String getEnName() {
        return enName;
    }
    
    /**
     * @param 对enName进行赋值
     */
    public void setEnName(String enName) {
        this.enName = enName;
    }
    
    /**
     * @return 返回 en
     */
    public String getEn() {
        return en;
    }
    
    /**
     * @param 对en进行赋值
     */
    public void setEn(String en) {
        this.en = en;
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
     * @return 返回 latitude
     */
    public BigDecimal getLatitude() {
        return latitude;
    }
    
    /**
     * @param 对latitude进行赋值
     */
    public void setLatitude(BigDecimal latitude) {
        this.latitude = latitude;
    }
    
    /**
     * @return 返回 longitude
     */
    public BigDecimal getLongitude() {
        return longitude;
    }
    
    /**
     * @param 对longitude进行赋值
     */
    public void setLongitude(BigDecimal longitude) {
        this.longitude = longitude;
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