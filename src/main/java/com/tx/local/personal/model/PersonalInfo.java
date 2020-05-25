/*
 * 描          述:  <描述>
 * 修  改   人:  Administrator
 * 修改时间:  2016年10月1日
 * <修改描述:>
 */
package com.tx.local.personal.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.springframework.format.annotation.DateTimeFormat;

import com.tx.local.basicdata.model.District;
import com.tx.local.basicdata.model.SexEnum;
import com.tx.local.clientinfo.model.Client;
import com.tx.local.creditinfo.model.CreditInfo;

/**
 * 客户概要信息<br/>
 *    证件类型和证件号码为唯一键<br/>
 * <功能详细描述>
 * 
 * @author  Administrator
 * @version  [版本号, 2016年10月1日]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
@Entity
@Table(name = "per_personal_info")
public class PersonalInfo implements Serializable {
    
    /** 注释内容 */
    private static final long serialVersionUID = 230269497747644146L;
    
    /** 主键:唯一键 */
    @Id
    @Column(length = 64, updatable = false, nullable = false)
    private String id;
    
    /** 虚中心id： 将会使用社属机构id当做vcid进行插入数据 */
    @Column(length = 64, updatable = false, nullable = false)
    private String vcid;
    
    /** 客户id */
    @Column(length = 64, updatable = true, nullable = true)
    private String clientId;
    
    /** 是否完成信用信息绑定 */
    @Column(nullable = false, updatable = true)
    private boolean creditInfoBinding;
    
    /** 之所以要将设计过程中的机构信用信息与机构信息进行隔离是因为，可能先存在机构信息，再存在机构信用信息，机构信用信息是在机构完成实名认证后绑定的信息，如果需要 */
    @Column(length = 64, updatable = true, nullable = true)
    private String creditInfoId;
    
    /** 类型 */
    @Column(length = 64, updatable = true, nullable = false)
    private PersonalTypeEnum type;
    
    /** 姓名 */
    @Column(length = 32, updatable = true, nullable = false)
    private String name;
    
    /** 姓 */
    @Column(length = 32, updatable = false, nullable = false)
    private String fristName;
    
    /** 名 */
    @Column(length = 32, updatable = false, nullable = false)
    private String lastName;
    
    /** 省_ID */
    @Column(name = "provinceId", length = 64, nullable = true)
    private District province;
    
    /** 市_ID */
    @Column(name = "cityId", length = 64, nullable = true)
    private District city;
    
    /** 区/县_ID */
    @Column(name = "countyId", length = 64, nullable = true)
    private District county;
    
    /** 区/县_ID */
    @Column(name = "districtId", length = 64, nullable = true)
    private District district;
    
    /** 行政区域:详细地址 */
    @Column(length = 256, nullable = true)
    private String address;
    
    /** 地址全称 */
    @Column(length = 256, nullable = true)
    private String fullAddress;
    
    /** 出生日期 */
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(nullable = true, updatable = true)
    private Date birthday;
    
    /** 性别 */
    @Column(nullable = true, updatable = true)
    private SexEnum sex;
    
    /** 是否可编辑，部分数据可能是关联到具体的其他数据一并进行维护的，可能被设置为不可编辑 */
    @Column(nullable = false, updatable = true)
    private boolean modifyAble;
    
    /**备注*/
    @Column(length = 500, nullable = true)
    public String remark;
    
    /** 最后更新时间 */
    @Column(nullable = false, updatable = true)
    private Date lastUpdateDate;
    
    /** 最后更新用户 */
    @Column(nullable = true, updatable = true)
    private String lastUpdateUserId;
    
    /** 创建时间 */
    @Column(nullable = false, updatable = false)
    private Date createDate;
    
    /** 创建用户ID */
    @Column(nullable = true, updatable = false)
    private String createUserId;
    
    /** 客户信息 */
    @Transient
    private Client client;
    
    /** 信用信息 */
    @Transient
    private CreditInfo creditInfo;
    
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
     * @return 返回 creditInfoBinding
     */
    public boolean isCreditInfoBinding() {
        return creditInfoBinding;
    }
    
    /**
     * @param 对creditInfoBinding进行赋值
     */
    public void setCreditInfoBinding(boolean creditInfoBinding) {
        this.creditInfoBinding = creditInfoBinding;
    }
    
    /**
     * @return 返回 creditInfoId
     */
    public String getCreditInfoId() {
        return creditInfoId;
    }
    
    /**
     * @param 对creditInfoId进行赋值
     */
    public void setCreditInfoId(String creditInfoId) {
        this.creditInfoId = creditInfoId;
    }
    
    /**
     * @return 返回 type
     */
    public PersonalTypeEnum getType() {
        return type;
    }
    
    /**
     * @param 对type进行赋值
     */
    public void setType(PersonalTypeEnum type) {
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
     * @return 返回 fristName
     */
    public String getFristName() {
        return fristName;
    }
    
    /**
     * @param 对fristName进行赋值
     */
    public void setFristName(String fristName) {
        this.fristName = fristName;
    }
    
    /**
     * @return 返回 lastName
     */
    public String getLastName() {
        return lastName;
    }
    
    /**
     * @param 对lastName进行赋值
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
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
     * @return 返回 county
     */
    public District getCounty() {
        return county;
    }
    
    /**
     * @param 对county进行赋值
     */
    public void setCounty(District county) {
        this.county = county;
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
     * @return 返回 birthday
     */
    public Date getBirthday() {
        return birthday;
    }
    
    /**
     * @param 对birthday进行赋值
     */
    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }
    
    /**
     * @return 返回 sex
     */
    public SexEnum getSex() {
        return sex;
    }
    
    /**
     * @param 对sex进行赋值
     */
    public void setSex(SexEnum sex) {
        this.sex = sex;
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
     * @return 返回 lastUpdateUserId
     */
    public String getLastUpdateUserId() {
        return lastUpdateUserId;
    }
    
    /**
     * @param 对lastUpdateUserId进行赋值
     */
    public void setLastUpdateUserId(String lastUpdateUserId) {
        this.lastUpdateUserId = lastUpdateUserId;
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
     * @return 返回 createUserId
     */
    public String getCreateUserId() {
        return createUserId;
    }
    
    /**
     * @param 对createUserId进行赋值
     */
    public void setCreateUserId(String createUserId) {
        this.createUserId = createUserId;
    }
    
    /**
     * @return 返回 client
     */
    public Client getClient() {
        return client;
    }
    
    /**
     * @param 对client进行赋值
     */
    public void setClient(Client client) {
        this.client = client;
    }
    
    /**
     * @return 返回 creditInfo
     */
    public CreditInfo getCreditInfo() {
        return creditInfo;
    }
    
    /**
     * @param 对creditInfo进行赋值
     */
    public void setCreditInfo(CreditInfo creditInfo) {
        this.creditInfo = creditInfo;
    }
}
