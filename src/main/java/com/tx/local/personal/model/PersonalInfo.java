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

import com.tx.local.clientinfo.model.ClientInfo;
import com.tx.local.institution.model.Institution;
import com.tx.local.institution.model.InstitutionInfo;
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

    /** 联系电话(默认将使用该联系电话去创建该机构的管理员) */
    @Column(length = 64, nullable = true)
    private String linkMobileNumber;

    /** 身份证号码 */
    private String idCardNumber;
    
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

    /** 客户信息 */
    @Transient
    private ClientInfo clientInfo;

    /** 机构信息 */
    @Transient
    private InstitutionInfo institutionInfo;

    /** 信用信息 */
    @Transient
    private CreditInfo creditInfo;

    /** 客户详情信息 */
    @Transient
    private PersonalSummary personalSummary;
    /**
     * 封装
     * @return
     */
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getVcid() {
        return vcid;
    }

    public void setVcid(String vcid) {
        this.vcid = vcid;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public boolean isCreditInfoBinding() {
        return creditInfoBinding;
    }

    public void setCreditInfoBinding(boolean creditInfoBinding) {
        this.creditInfoBinding = creditInfoBinding;
    }

    public String getCreditInfoId() {
        return creditInfoId;
    }

    public void setCreditInfoId(String creditInfoId) {
        this.creditInfoId = creditInfoId;
    }

    public PersonalTypeEnum getType() {
        return type;
    }

    public void setType(PersonalTypeEnum type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFristName() {
        return fristName;
    }

    public void setFristName(String fristName) {
        this.fristName = fristName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getLinkMobileNumber() {
        return linkMobileNumber;
    }

    public void setLinkMobileNumber(String linkMobileNumber) {
        this.linkMobileNumber = linkMobileNumber;
    }

    public String getIdCardNumber() {
        return idCardNumber;
    }

    public void setIdCardNumber(String idCardNumber) {
        this.idCardNumber = idCardNumber;
    }

    public District getProvince() {
        return province;
    }

    public void setProvince(District province) {
        this.province = province;
    }

    public District getCity() {
        return city;
    }

    public void setCity(District city) {
        this.city = city;
    }

    public District getCounty() {
        return county;
    }

    public void setCounty(District county) {
        this.county = county;
    }

    public District getDistrict() {
        return district;
    }

    public void setDistrict(District district) {
        this.district = district;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getFullAddress() {
        return fullAddress;
    }

    public void setFullAddress(String fullAddress) {
        this.fullAddress = fullAddress;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public SexEnum getSex() {
        return sex;
    }

    public void setSex(SexEnum sex) {
        this.sex = sex;
    }

    public boolean isModifyAble() {
        return modifyAble;
    }

    public void setModifyAble(boolean modifyAble) {
        this.modifyAble = modifyAble;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Date getLastUpdateDate() {
        return lastUpdateDate;
    }

    public void setLastUpdateDate(Date lastUpdateDate) {
        this.lastUpdateDate = lastUpdateDate;
    }

    public String getLastUpdateUserId() {
        return lastUpdateUserId;
    }

    public void setLastUpdateUserId(String lastUpdateUserId) {
        this.lastUpdateUserId = lastUpdateUserId;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getCreateUserId() {
        return createUserId;
    }

    public void setCreateUserId(String createUserId) {
        this.createUserId = createUserId;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public ClientInfo getClientInfo() {
        return clientInfo;
    }

    public void setClientInfo(ClientInfo clientInfo) {
        this.clientInfo = clientInfo;
    }

    public InstitutionInfo getInstitutionInfo() {
        return institutionInfo;
    }

    public void setInstitutionInfo(InstitutionInfo institutionInfo) {
        this.institutionInfo = institutionInfo;
    }

    public CreditInfo getCreditInfo() {
        return creditInfo;
    }

    public void setCreditInfo(CreditInfo creditInfo) {
        this.creditInfo = creditInfo;
    }

    public PersonalSummary getPersonalSummary() {
        return personalSummary;
    }

    public void setPersonalSummary(PersonalSummary personalSummary) {
        this.personalSummary = personalSummary;
    }
}

