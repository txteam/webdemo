/*
 * 描          述:  <描述>
 * 修  改   人:  Administrator
 * 修改时间:  2016年10月1日
 * <修改描述:>
 */
package com.tx.local.personal.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

import com.tx.local.basicdata.model.District;
import com.tx.local.creditinfo.model.Education;
import com.tx.local.creditinfo.model.IdCardDeadline;
import com.tx.local.creditinfo.model.IdentityState;
import com.tx.local.creditinfo.model.LiveStatus;
import com.tx.local.creditinfo.model.MaritalStatus;

/**
 * 个人概要信息<br/>
 *    证件类型和证件号码为唯一键<br/>
 * <功能详细描述>
 * 
 * @author  Administrator
 * @version  [版本号, 2016年10月1日]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
@Entity
@Table(name = "per_personal_summary")
public class PersonalSummary implements Serializable {
    
    /** 注释内容 */
    private static final long serialVersionUID = 7167290592273943800L;

    /** 主键:唯一键 */
    @Id
    @Column(length = 64, updatable = false, nullable = false)
    private String id;

    /** 虚中心id： 将会使用社属机构id当做vcid进行插入数据 */
    @Column(length = 64, updatable = false, nullable = false)
    private String vcid;
    
    /** 客户id */
    @Column(length = 64, updatable = true, nullable = true)
    private String personalId;
    
    /** 学历ID */
    @Column(name = "educationId")
    private Education education;
    
    /** 身份状态 **/
    @Column(name = "identityStateId")
    private IdentityState identityState;
    
    /** 居住状况 **/
    @Column(name = "liveStatusId")
    private LiveStatus liveStatus;
    
    /** 婚姻状况ID */
    @Column(name = "maritalStatusId")
    private MaritalStatus maritalStatus;
    
    /** 婚姻日期 */
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date marriageDate;
    
    /** 身份证有效期 */
    @Column(name = "idCardDeadlineId")
    private IdCardDeadline idCardDeadline;
    
    /** 身份证到期日 */
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date idCardExpiredDate;
    
    /** 身份证地址_区/县_ID */
    @Column(name = "idCardDistrictId")
    private District idCardDistrict;

    /** 家庭人口 */
    private Integer familyCount;

    /** 劳动力人口 */
    private Integer laborCount;

    /** 土地面积 */
    private BigDecimal landArea;
    
    /** 籍贯 */
    @Column(name = "nativePlaceId")
    private District nativePlace;
    
    /** 父亲姓名 */
    private String fatherName;
    
    /** 父亲是否健在 */
    private Boolean fatherAlive = Boolean.TRUE;
    
    /** 父亲移动电话号码 */
    private String fatherMobileNumber;
    
    /** 母亲姓名 */
    private String motherName;
    
    /** 母亲是否健在 */
    private Boolean motherAlive = Boolean.TRUE;
    
    /** 父亲移动电话号码 */
    private String motherMobileNumber;
    
    /**身份证正面地址*/
    private String frontOfIDCardUrl;
    
    /** 身份证反面url */
    private String reverseOfIDCardUrl;

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
     * @return 返回 personalId
     */
    public String getPersonalId() {
        return personalId;
    }

    /**
     * @param 对personalId进行赋值
     */
    public void setPersonalId(String personalId) {
        this.personalId = personalId;
    }

    /**
     * @return 返回 education
     */
    public Education getEducation() {
        return education;
    }

    /**
     * @param 对education进行赋值
     */
    public void setEducation(Education education) {
        this.education = education;
    }

    /**
     * @return 返回 identityState
     */
    public IdentityState getIdentityState() {
        return identityState;
    }

    /**
     * @param 对identityState进行赋值
     */
    public void setIdentityState(IdentityState identityState) {
        this.identityState = identityState;
    }

    /**
     * @return 返回 liveStatus
     */
    public LiveStatus getLiveStatus() {
        return liveStatus;
    }

    /**
     * @param 对liveStatus进行赋值
     */
    public void setLiveStatus(LiveStatus liveStatus) {
        this.liveStatus = liveStatus;
    }

    /**
     * @return 返回 maritalStatus
     */
    public MaritalStatus getMaritalStatus() {
        return maritalStatus;
    }

    /**
     * @param 对maritalStatus进行赋值
     */
    public void setMaritalStatus(MaritalStatus maritalStatus) {
        this.maritalStatus = maritalStatus;
    }

    /**
     * @return 返回 marriageDate
     */
    public Date getMarriageDate() {
        return marriageDate;
    }

    /**
     * @param 对marriageDate进行赋值
     */
    public void setMarriageDate(Date marriageDate) {
        this.marriageDate = marriageDate;
    }

    /**
     * @return 返回 idCardDeadline
     */
    public IdCardDeadline getIdCardDeadline() {
        return idCardDeadline;
    }

    /**
     * @param 对idCardDeadline进行赋值
     */
    public void setIdCardDeadline(IdCardDeadline idCardDeadline) {
        this.idCardDeadline = idCardDeadline;
    }

    /**
     * @return 返回 idCardExpiredDate
     */
    public Date getIdCardExpiredDate() {
        return idCardExpiredDate;
    }

    /**
     * @param 对idCardExpiredDate进行赋值
     */
    public void setIdCardExpiredDate(Date idCardExpiredDate) {
        this.idCardExpiredDate = idCardExpiredDate;
    }

    /**
     * @return 返回 idCardDistrict
     */
    public District getIdCardDistrict() {
        return idCardDistrict;
    }

    /**
     * @param 对idCardDistrict进行赋值
     */
    public void setIdCardDistrict(District idCardDistrict) {
        this.idCardDistrict = idCardDistrict;
    }

    /**
     * @return 返回 nativePlace
     */
    public District getNativePlace() {
        return nativePlace;
    }

    /**
     * @param 对nativePlace进行赋值
     */
    public void setNativePlace(District nativePlace) {
        this.nativePlace = nativePlace;
    }

    /**
     * @return 返回 fatherName
     */
    public String getFatherName() {
        return fatherName;
    }

    /**
     * @param 对fatherName进行赋值
     */
    public void setFatherName(String fatherName) {
        this.fatherName = fatherName;
    }

    /**
     * @return 返回 fatherAlive
     */
    public Boolean getFatherAlive() {
        return fatherAlive;
    }

    /**
     * @param 对fatherAlive进行赋值
     */
    public void setFatherAlive(Boolean fatherAlive) {
        this.fatherAlive = fatherAlive;
    }

    /**
     * @return 返回 fatherMobileNumber
     */
    public String getFatherMobileNumber() {
        return fatherMobileNumber;
    }

    /**
     * @param 对fatherMobileNumber进行赋值
     */
    public void setFatherMobileNumber(String fatherMobileNumber) {
        this.fatherMobileNumber = fatherMobileNumber;
    }

    /**
     * @return 返回 motherName
     */
    public String getMotherName() {
        return motherName;
    }

    /**
     * @param 对motherName进行赋值
     */
    public void setMotherName(String motherName) {
        this.motherName = motherName;
    }

    /**
     * @return 返回 motherAlive
     */
    public Boolean getMotherAlive() {
        return motherAlive;
    }

    /**
     * @param 对motherAlive进行赋值
     */
    public void setMotherAlive(Boolean motherAlive) {
        this.motherAlive = motherAlive;
    }

    /**
     * @return 返回 motherMobileNumber
     */
    public String getMotherMobileNumber() {
        return motherMobileNumber;
    }

    /**
     * @param 对motherMobileNumber进行赋值
     */
    public void setMotherMobileNumber(String motherMobileNumber) {
        this.motherMobileNumber = motherMobileNumber;
    }

    public Integer getFamilyCount() {
        return familyCount;
    }

    public void setFamilyCount(Integer familyCount) {
        this.familyCount = familyCount;
    }

    public Integer getLaborCount() {
        return laborCount;
    }

    public void setLaborCount(Integer laborCount) {
        this.laborCount = laborCount;
    }

    public BigDecimal getLandArea() {
        return landArea;
    }

    public void setLandArea(BigDecimal landArea) {
        this.landArea = landArea;
    }

    public String getFrontOfIDCardUrl() {
        return frontOfIDCardUrl;
    }

    public void setFrontOfIDCardUrl(String frontOfIDCardUrl) {
        this.frontOfIDCardUrl = frontOfIDCardUrl;
    }

    public String getReverseOfIDCardUrl() {
        return reverseOfIDCardUrl;
    }

    public void setReverseOfIDCardUrl(String reverseOfIDCardUrl) {
        this.reverseOfIDCardUrl = reverseOfIDCardUrl;
    }

    public String getVcid() {
        return vcid;
    }

    public void setVcid(String vcid) {
        this.vcid = vcid;
    }
}
