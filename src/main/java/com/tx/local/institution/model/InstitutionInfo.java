/*
 * 描          述:  <描述>
 * 修  改   人:  Administrator
 * 修改时间:  2019年11月25日
 * <修改描述:>
 */
package com.tx.local.institution.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.tx.local.basicdata.model.District;
import com.tx.local.clientinfo.model.Client;
import com.tx.local.clientinfo.model.ClientAware;
import com.tx.local.clientinfo.model.ClientInfo;
import com.tx.local.creditinfo.model.CreditInfo;

/**
 * 组织机构<br/>
 *    包含：个体户，企业等机构信息
 *    Client <- 1 : 1 -> InstitutionInfo
 *    Client <- n : 1 -> InstitutionCreditInfo <- 1 : 1 1 : n -> CreditDetail...
 *    InstitutionCreditInfo在客户完成实名认证后进行绑定
 *    完成绑定后，客户可以维护其相关信用信息
 *    与信用无关的信息，都与clientId进行关联
 *    与信用有关（实名以后进行录入的），都与creditId进行关联
 *    CreditInfo <- 1 : 1 1 : n -> CreditDetail...
 *    InstitutionInfo与
 * <功能详细描述>
 * 
 * @author  Administrator
 * @version  [版本号, 2019年11月25日]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
@Entity
@Table(name = "ins_institution")
public class InstitutionInfo implements Serializable, Institution, ClientAware {
    
    /** 注释内容 */
    private static final long serialVersionUID = 3297464751315326650L;
    
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
    
    /** 机构类型 */
    @Column(length = 64, updatable = true, nullable = false)
    private InstitutionTypeEnum type;
    
    /** 合作社名 */
    @Column(length = 64, updatable = true, nullable = true)
    private String name;
    
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
    
    /** 邮政编码 */
    @Column(length = 64, nullable = true)
    private String postcode;
    
    /**备注*/
    @Column(length = 500, nullable = true)
    public String remark;
    
    /** 联系人 */
    @Column(length = 64, nullable = true)
    private String linkName;

    /** 企业统一信用码 */
    private String idCardNumber;
    
    /** 联系电话(默认将使用该联系电话去创建该机构的管理员) */
    @Column(length = 64, nullable = true)
    private String linkMobileNumber;
    
    /** 是否可编辑，部分数据可能是关联到具体的其他数据一并进行维护的，可能被设置为不可编辑 */
    @Column(nullable = false, updatable = true)
    private boolean modifyAble;
    
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
    private ClientInfo clientInfo;
    
    /** 信用信息 */
    @Transient
    private CreditInfo creditInfo;

    /** 机构信息 */
    @Transient
    private InstitutionInfo institutionInfo;

    /*机构扩展信息*/
    @Transient
    private InstitutionSummaryInfo institutionSummaryInfo;
    
    /**
     * @return
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
     * @return
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
     * @return
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
     * @return
     */
    public InstitutionTypeEnum getType() {
        return type;
    }
    
    /**
     * @param 对type进行赋值
     */
    public void setType(InstitutionTypeEnum type) {
        this.type = type;
    }
    
    /**
     * @return
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
     * @return
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
     * @return
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
     * @return
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
     * @return
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
     * @return
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
     * @return
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
     * @return
     */
    public String getPostcode() {
        return postcode;
    }
    
    /**
     * @param 对postcode进行赋值
     */
    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }
    
    /**
     * @return
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
     * @return
     */
    public String getLinkName() {
        return linkName;
    }
    
    /**
     * @param 对linkName进行赋值
     */
    public void setLinkName(String linkName) {
        this.linkName = linkName;
    }
    
    /**
     * @return
     */
    public String getLinkMobileNumber() {
        return linkMobileNumber;
    }
    
    /**
     * @param 对linkMobileNumber进行赋值
     */
    public void setLinkMobileNumber(String linkMobileNumber) {
        this.linkMobileNumber = linkMobileNumber;
    }
    
    /**
     * @return
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

    public InstitutionInfo getInstitutionInfo() {
        return institutionInfo;
    }

    public void setInstitutionInfo(InstitutionInfo institutionInfo) {
        this.institutionInfo = institutionInfo;
    }

    public String getIdCardNumber() {
        return idCardNumber;
    }

    public void setIdCardNumber(String idCardNumber) {
        this.idCardNumber = idCardNumber;
    }

    public InstitutionSummaryInfo getInstitutionSummaryInfo() {
        return institutionSummaryInfo;
    }

    public void setInstitutionSummaryInfo(InstitutionSummaryInfo institutionSummaryInfo) {
        this.institutionSummaryInfo = institutionSummaryInfo;
    }

    public ClientInfo getClientInfo() {
        return clientInfo;
    }

    public void setClientInfo(ClientInfo clientInfo) {
        this.clientInfo = clientInfo;
    }
}
