package com.tx.local.personal.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

import com.tx.core.jdbc.sqlsource.annotation.UpdateAble;
import com.tx.local.basicdata.model.District;
import com.tx.local.creditinfo.model.Education;
import com.tx.local.creditinfo.model.IdentityState;
import com.tx.local.creditinfo.model.LiveStatus;
import com.tx.local.creditinfo.model.MaritalStatus;

/**
 * 农户信息管理<br/> --即将废弃
 * 证件类型和证件号码为唯一键<br/>
 * <功能详细描述>
 *
 * @author Administrator
 * @version [版本号, 2020-3-13]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
@Entity
@Table(name = "p_peasant_manage")
public class PeasantInfo {

    @Id
    private String id;

    /*** 社团ID */
    private String cooperativeId;

    /**入社时间**/
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date joinCooperativeDate;

    /**是否退出**/
    private boolean quitAgencyType;

    /**退出社时间**/
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date quitCooperativeDate;

    /**
     * 身份证号码
     */
    private String idCardNumber;

    /**
     * 姓名
     */
    @UpdateAble
    private String name;

    /**
     * 姓
     */
    @UpdateAble
    private String fristName;

    /**
     * 名
     */
    @UpdateAble
    private String lastName;

    /**
     * 现居住地址
     */
    @UpdateAble
    private String liveAddress;

    /**
     * 出生日期
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @UpdateAble
    private Date birthday;


    /**
     * 年龄
     */
    @UpdateAble
    private Integer age;

    /**
     * 性别
     */
    @UpdateAble
    private boolean gender;

    /**
     * 是否领取低保
     */
    @UpdateAble
    private boolean subsistenceAllowance;

    /**
     * 电话号码
     */
    @UpdateAble
    private String telephoneNumber;

    /**
     * 学历ID
     */
    @Column(name = "educationId")
    @UpdateAble
    private Education education;

    /*** 身份状态 **/
    @Column(name = "identityStateId")
    @UpdateAble
    private IdentityState identityState;

    /*** 居住状况 **/
    @Column(name = "liveStatusId")
    @UpdateAble
    private LiveStatus liveStatus;

    /**
     * 身份证地址_省_ID
     */
    @Column(name = "idCardProvinceId")
    @UpdateAble
    private District idCardProvince;

    /**
     * 身份证地址_市_ID
     */
    @Column(name = "idCardCityId")
    @UpdateAble
    private District idCardCity;

    /**
     * 身份证地址_区/县_ID
     */
    @Column(name = "idCardCountyId")
    @UpdateAble
    private District idCardCounty;

    /**
     * 婚姻状况ID
     */
    @Column(name = "maritalStatusId")
    @UpdateAble
    private MaritalStatus maritalStatus;

    /**
     * 籍贯ID
     */
    @UpdateAble
    private String nativeId;

    /**居住人数**/
    @UpdateAble
    private Integer familyCount;

    /** 审核状态:0-未审核 1-审核通过 2-审核不通过 */
    private Integer auditStatus;

    private String createOperatorId;

    private String lastUpdateOperatorId;

    private Date createDate;

    private Date lastUpdateDate;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCooperativeId() {
        return cooperativeId;
    }

    public void setCooperativeId(String cooperativeId) {
        this.cooperativeId = cooperativeId;
    }

    public Date getJoinCooperativeDate() {
        return joinCooperativeDate;
    }

    public void setJoinCooperativeDate(Date joinCooperativeDate) {
        this.joinCooperativeDate = joinCooperativeDate;
    }

    public boolean isQuitAgencyType() {
        return quitAgencyType;
    }

    public void setQuitAgencyType(boolean quitAgencyType) {
        this.quitAgencyType = quitAgencyType;
    }

    public Date getQuitCooperativeDate() {
        return quitCooperativeDate;
    }

    public void setQuitCooperativeDate(Date quitCooperativeDate) {
        this.quitCooperativeDate = quitCooperativeDate;
    }

    public String getIdCardNumber() {
        return idCardNumber;
    }

    public void setIdCardNumber(String idCardNumber) {
        this.idCardNumber = idCardNumber;
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

    public String getLiveAddress() {
        return liveAddress;
    }

    public void setLiveAddress(String liveAddress) {
        this.liveAddress = liveAddress;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public boolean isGender() {
        return gender;
    }

    public void setGender(boolean gender) {
        this.gender = gender;
    }

    public boolean isSubsistenceAllowance() {
        return subsistenceAllowance;
    }

    public void setSubsistenceAllowance(boolean subsistenceAllowance) {
        this.subsistenceAllowance = subsistenceAllowance;
    }

    public String getTelephoneNumber() {
        return telephoneNumber;
    }

    public void setTelephoneNumber(String telephoneNumber) {
        this.telephoneNumber = telephoneNumber;
    }

    public Education getEducation() {
        return education;
    }

    public void setEducation(Education education) {
        this.education = education;
    }

    public IdentityState getIdentityState() {
        return identityState;
    }

    public void setIdentityState(IdentityState identityState) {
        this.identityState = identityState;
    }

    public LiveStatus getLiveStatus() {
        return liveStatus;
    }

    public void setLiveStatus(LiveStatus liveStatus) {
        this.liveStatus = liveStatus;
    }

    public District getIdCardProvince() {
        return idCardProvince;
    }

    public void setIdCardProvince(District idCardProvince) {
        this.idCardProvince = idCardProvince;
    }

    public District getIdCardCity() {
        return idCardCity;
    }

    public void setIdCardCity(District idCardCity) {
        this.idCardCity = idCardCity;
    }

    public District getIdCardCounty() {
        return idCardCounty;
    }

    public void setIdCardCounty(District idCardCounty) {
        this.idCardCounty = idCardCounty;
    }

    public MaritalStatus getMaritalStatus() {
        return maritalStatus;
    }

    public void setMaritalStatus(MaritalStatus maritalStatus) {
        this.maritalStatus = maritalStatus;
    }

    public String getNativeId() {
        return nativeId;
    }

    public void setNativeId(String nativeId) {
        this.nativeId = nativeId;
    }

    public Integer getFamilyCount() {
        return familyCount;
    }

    public void setFamilyCount(Integer familyCount) {
        this.familyCount = familyCount;
    }

    public Integer getAuditStatus() {
        return auditStatus;
    }

    public void setAuditStatus(Integer auditStatus) {
        this.auditStatus = auditStatus;
    }

    public String getCreateOperatorId() {
        return createOperatorId;
    }

    public void setCreateOperatorId(String createOperatorId) {
        this.createOperatorId = createOperatorId;
    }

    public String getLastUpdateOperatorId() {
        return lastUpdateOperatorId;
    }

    public void setLastUpdateOperatorId(String lastUpdateOperatorId) {
        this.lastUpdateOperatorId = lastUpdateOperatorId;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getLastUpdateDate() {
        return lastUpdateDate;
    }

    public void setLastUpdateDate(Date lastUpdateDate) {
        this.lastUpdateDate = lastUpdateDate;
    }
}
