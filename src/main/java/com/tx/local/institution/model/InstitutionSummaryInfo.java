/*
 * 描          述:  <描述>
 * 修  改   人:  Administrator
 * 修改时间:  2020年3月28日
 * <修改描述:>
 */
package com.tx.local.institution.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

import com.tx.local.basicdata.model.IDCardTypeEnum;
import com.tx.local.creditinfo.model.CreditSingleLinked;

/**
 * 企业信息摘要<br/>
 * <功能详细描述>
 * 
 * @author  Administrator
 * @version  [版本号, 2020年3月28日]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
@Entity
@Table(name = "ins_summary_info")
public class InstitutionSummaryInfo implements CreditSingleLinked{
    
    /** 注释内容 */
    private static final long serialVersionUID = -7050744330670846633L;

    /** 企业信息摘要id */
    @Id
    @Column(length = 64, updatable = false, nullable = false)
    private String id;
    
    /** 信用信息id */
    @Column(length = 64, updatable = false, nullable = false)
    private String creditInfoId;
    
    /** 法人 */
    @Column(length = 32, updatable = true, nullable = true)
    private String legalName;
    
    /** 法人身份证号 */
    @Column(length = 32, updatable = true, nullable = true)
    private IDCardTypeEnum legalIdCardType = IDCardTypeEnum.SFZ;
    
    /** 法人身份证号 */
    @Column(length = 32, updatable = true, nullable = true)
    private String legalIdCardNumber;
    
    /** 代理人 */
    @Column(length = 32, updatable = true, nullable = true)
    private String agentName;
    
    /** 法人身份证号 */
    @Column(length = 32, updatable = true, nullable = true)
    private IDCardTypeEnum agentIdCardType = IDCardTypeEnum.SFZ;
    
    /** 代理人身份证号 */
    @Column(length = 32, updatable = true, nullable = true)
    private String agentIdCardNumber;
    
    /** 组织机构代码 */
    @Column(length = 32, updatable = true, nullable = true)
    private String institutionNumber;
    
    /** 税务号 */
    @Column(length = 32, updatable = true, nullable = true)
    private String taxNumber;
    
    /** 营业执照号 */
    @Column(length = 32, updatable = true, nullable = true)
    private String businessLicenseNumber;
    
    /** 公司联系电话(座机) */
    @Column(length = 16, updatable = true, nullable = true)
    private String phoneNumber;
    
    /** 经营期限 */
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(updatable = true, nullable = true)
    private Date expiryDate;
    
    /** 开户许可证附件 */
    private String openAccountLicenseUrl;
    
    /** 营业执照文件id */
    private String businessLicenseUrl;
    
    /** 带公章营业执照 */
    private String businessLicenseWithSealUrl;
    
    /** 企业经办人授权委托书 */
    private String authorizationUrl;

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
     * @return 返回 legalIdCardType
     */
    public IDCardTypeEnum getLegalIdCardType() {
        return legalIdCardType;
    }

    /**
     * @param 对legalIdCardType进行赋值
     */
    public void setLegalIdCardType(IDCardTypeEnum legalIdCardType) {
        this.legalIdCardType = legalIdCardType;
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
     * @return 返回 agentIdCardType
     */
    public IDCardTypeEnum getAgentIdCardType() {
        return agentIdCardType;
    }

    /**
     * @param 对agentIdCardType进行赋值
     */
    public void setAgentIdCardType(IDCardTypeEnum agentIdCardType) {
        this.agentIdCardType = agentIdCardType;
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
     * @return 返回 openAccountLicenseUrl
     */
    public String getOpenAccountLicenseUrl() {
        return openAccountLicenseUrl;
    }

    /**
     * @param 对openAccountLicenseUrl进行赋值
     */
    public void setOpenAccountLicenseUrl(String openAccountLicenseUrl) {
        this.openAccountLicenseUrl = openAccountLicenseUrl;
    }

    /**
     * @return 返回 businessLicenseUrl
     */
    public String getBusinessLicenseUrl() {
        return businessLicenseUrl;
    }

    /**
     * @param 对businessLicenseUrl进行赋值
     */
    public void setBusinessLicenseUrl(String businessLicenseUrl) {
        this.businessLicenseUrl = businessLicenseUrl;
    }

    /**
     * @return 返回 businessLicenseWithSealUrl
     */
    public String getBusinessLicenseWithSealUrl() {
        return businessLicenseWithSealUrl;
    }

    /**
     * @param 对businessLicenseWithSealUrl进行赋值
     */
    public void setBusinessLicenseWithSealUrl(String businessLicenseWithSealUrl) {
        this.businessLicenseWithSealUrl = businessLicenseWithSealUrl;
    }

    /**
     * @return 返回 authorizationUrl
     */
    public String getAuthorizationUrl() {
        return authorizationUrl;
    }

    /**
     * @param 对authorizationUrl进行赋值
     */
    public void setAuthorizationUrl(String authorizationUrl) {
        this.authorizationUrl = authorizationUrl;
    }
}
