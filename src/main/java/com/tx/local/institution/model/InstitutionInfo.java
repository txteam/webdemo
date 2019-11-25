/*
 * 描          述:  <描述>
 * 修  改   人:  Administrator
 * 修改时间:  2019年11月25日
 * <修改描述:>
 */
package com.tx.local.institution.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

/**
 * 组织机构<br/>
 *    包含：个体户，企业等机构信息
 * <功能详细描述>
 * 
 * @author  Administrator
 * @version  [版本号, 2019年11月25日]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
@Entity
@Table(name = "cl_institution")
public class InstitutionInfo implements Serializable {
    
    /** 注释内容 */
    private static final long serialVersionUID = 3297464751315326650L;
    
    /** 主键:唯一键 */
    @Id
    private String id;
    
    /** 机构类型 */
    private InstitutionTypeEnum type;
    
    /** 客户id */
    private String clientId;
    
    /** 机构名 */
    private String name;
    
    /** 法人 */
    private String legalName;
    
    /** 法人身份证号 */
    private String legalIdCardNumber;
    
    /** 代理人 */
    private String agentName;
    
    /** 代理人身份证号 */
    private String agentIdCardNumber;
    
    /** 组织机构代码 */
    private String institutionNumber;
    
    /** 税务号 */
    private String taxNumber;
    
    /** 营业执照号 */
    private String businessLicenseNumber;
    
    /** 机构号段范围 */
    private String sectionNoStart;
    
    /** 机构号段范围 */
    private String sectionNoEnd;
    
    /** 营业执照省 */
    private String districtId;
    
    /** 地址 */
    private String address;
    
    /** 邮政编码 */
    private String postcode;
    
    /** 公司联系电话(座机) */
    private String phoneNumber;
    
    /** 经营期限 */
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date expiryDate;
    
    /** 开户许可证附件 */
    private String openAccountLicenseFileId;
    
    /** 营业执照文件id */
    private String businessLicenseFileId;
    
    /** 带公章营业执照 */
    private String businessLicenseWithSealFileId;
    
    /** 企业经办人授权委托书 */
    private String letterOfAttorneyFileId;
    
    /**备注*/
    public String remark;
    
    /** 创建日期 */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createDate;
    
    /** 最后更新时间 */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
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
     * @return 返回 type
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
     * @return 返回 legalName
     */
    public String getLegalName() {
        return legalName;
    }
    
    /**
     * @param 对legalName进行赋值
     */
    public void setLegalName(String legalName) {
        this.legalName = legalName;
    }
    
    /**
     * @return 返回 legalIdCardNumber
     */
    public String getLegalIdCardNumber() {
        return legalIdCardNumber;
    }
    
    /**
     * @param 对legalIdCardNumber进行赋值
     */
    public void setLegalIdCardNumber(String legalIdCardNumber) {
        this.legalIdCardNumber = legalIdCardNumber;
    }
    
    /**
     * @return 返回 agentName
     */
    public String getAgentName() {
        return agentName;
    }
    
    /**
     * @param 对agentName进行赋值
     */
    public void setAgentName(String agentName) {
        this.agentName = agentName;
    }
    
    /**
     * @return 返回 agentIdCardNumber
     */
    public String getAgentIdCardNumber() {
        return agentIdCardNumber;
    }
    
    /**
     * @param 对agentIdCardNumber进行赋值
     */
    public void setAgentIdCardNumber(String agentIdCardNumber) {
        this.agentIdCardNumber = agentIdCardNumber;
    }
    
    /**
     * @return 返回 institutionNumber
     */
    public String getInstitutionNumber() {
        return institutionNumber;
    }
    
    /**
     * @param 对institutionNumber进行赋值
     */
    public void setInstitutionNumber(String institutionNumber) {
        this.institutionNumber = institutionNumber;
    }
    
    /**
     * @return 返回 taxNumber
     */
    public String getTaxNumber() {
        return taxNumber;
    }
    
    /**
     * @param 对taxNumber进行赋值
     */
    public void setTaxNumber(String taxNumber) {
        this.taxNumber = taxNumber;
    }
    
    /**
     * @return 返回 businessLicenseNumber
     */
    public String getBusinessLicenseNumber() {
        return businessLicenseNumber;
    }
    
    /**
     * @param 对businessLicenseNumber进行赋值
     */
    public void setBusinessLicenseNumber(String businessLicenseNumber) {
        this.businessLicenseNumber = businessLicenseNumber;
    }
    
    /**
     * @return 返回 sectionNoStart
     */
    public String getSectionNoStart() {
        return sectionNoStart;
    }
    
    /**
     * @param 对sectionNoStart进行赋值
     */
    public void setSectionNoStart(String sectionNoStart) {
        this.sectionNoStart = sectionNoStart;
    }
    
    /**
     * @return 返回 sectionNoEnd
     */
    public String getSectionNoEnd() {
        return sectionNoEnd;
    }
    
    /**
     * @param 对sectionNoEnd进行赋值
     */
    public void setSectionNoEnd(String sectionNoEnd) {
        this.sectionNoEnd = sectionNoEnd;
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
     * @return 返回 postcode
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
     * @return 返回 phoneNumber
     */
    public String getPhoneNumber() {
        return phoneNumber;
    }
    
    /**
     * @param 对phoneNumber进行赋值
     */
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
    
    /**
     * @return 返回 expiryDate
     */
    public Date getExpiryDate() {
        return expiryDate;
    }
    
    /**
     * @param 对expiryDate进行赋值
     */
    public void setExpiryDate(Date expiryDate) {
        this.expiryDate = expiryDate;
    }
    
    /**
     * @return 返回 openAccountLicenseFileId
     */
    public String getOpenAccountLicenseFileId() {
        return openAccountLicenseFileId;
    }
    
    /**
     * @param 对openAccountLicenseFileId进行赋值
     */
    public void setOpenAccountLicenseFileId(String openAccountLicenseFileId) {
        this.openAccountLicenseFileId = openAccountLicenseFileId;
    }
    
    /**
     * @return 返回 businessLicenseFileId
     */
    public String getBusinessLicenseFileId() {
        return businessLicenseFileId;
    }
    
    /**
     * @param 对businessLicenseFileId进行赋值
     */
    public void setBusinessLicenseFileId(String businessLicenseFileId) {
        this.businessLicenseFileId = businessLicenseFileId;
    }
    
    /**
     * @return 返回 businessLicenseWithSealFileId
     */
    public String getBusinessLicenseWithSealFileId() {
        return businessLicenseWithSealFileId;
    }
    
    /**
     * @param 对businessLicenseWithSealFileId进行赋值
     */
    public void setBusinessLicenseWithSealFileId(
            String businessLicenseWithSealFileId) {
        this.businessLicenseWithSealFileId = businessLicenseWithSealFileId;
    }
    
    /**
     * @return 返回 letterOfAttorneyFileId
     */
    public String getLetterOfAttorneyFileId() {
        return letterOfAttorneyFileId;
    }
    
    /**
     * @param 对letterOfAttorneyFileId进行赋值
     */
    public void setLetterOfAttorneyFileId(String letterOfAttorneyFileId) {
        this.letterOfAttorneyFileId = letterOfAttorneyFileId;
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
}
