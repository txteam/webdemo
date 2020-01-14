/*
 * 描          述:  <描述>
 * 修  改   人:  Administrator
 * 修改时间:  2016年10月1日
 * <修改描述:>
 */
package com.tx.local.personal.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

import com.tx.local.basicdata.model.District;
import com.tx.local.basicdata.model.SexEnum;
import com.tx.local.creditinfo.annotation.SingleCreditInfo;
import com.tx.local.creditinfo.context.AbstractCreditInfo;
import com.tx.local.creditinfo.model.Education;
import com.tx.local.creditinfo.model.IdCardDeadline;
import com.tx.local.creditinfo.model.IdentityState;
import com.tx.local.creditinfo.model.LiveStatus;
import com.tx.local.creditinfo.model.MaritalStatus;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

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
@SingleCreditInfo
@Entity
@Table(name = "ci_personal_summary")
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
public class PersonalSummary extends AbstractCreditInfo {
    
    /** 注释内容 */
    private static final long serialVersionUID = -7016793184048963410L;
    
    /** 姓 */
    private String fristName;
    
    /** 名 */
    private String lastName;
    
    /** 详细地址 */
    private String addressDetial;
    
    /** 出生日期 */
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date birthday;
    
    /** 性别 */
    private SexEnum sex;
    
    /** 身份证正面文件id */
    private String frontOfIDCardFileId;
    
    /**身份证正面地址*/
    private String frontOfIDCardUrl;
    
    /** 身份证反面id */
    private String reverseOfIDCardFileId;
    
    /** 身份证反面url */
    private String reverseOfIDCardUrl;
    
    /** 学历ID */
    @Column(name = "educationId")
    private Education education;
    
    /*** 身份状态 **/
    @Column(name = "identityStateId")
    private IdentityState identityState;
    
    /*** 居住状况 **/
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
    
    /** 身份证地址_省_ID */
    @Column(name = "idCardProvinceId")
    private District idCardProvince;
    
    /** 身份证地址_市_ID */
    @Column(name = "idCardCityId")
    private District idCardCity;
    
    /** 身份证地址_区/县_ID */
    @Column(name = "idCardCountyId")
    private District idCardCounty;
    
    /** 父亲姓名 */
    private String fathName;
    
    /** 父亲是否过父亲是否过世 */
    private Boolean fathStatus = Boolean.FALSE;
    
    /** 母亲姓名 */
    private String mothName;
    
    /** 母亲是否过世 */
    private Boolean mothStatus = Boolean.FALSE;
    
    /** 家庭是否知悉此项贷款 */
    private Boolean famKno = Boolean.FALSE;
    
    /** 家庭是否知悉此项贷款_姓名 */
    private String famName;
    
    /** 家庭是否知悉此项贷款_关系 */
    private String famReal;
    
    /** 是否与本公司员有亲属关系_姓名 */
    private String compName;
    
    /** 是否与本公司员工有亲属关系 */
    private Boolean compRelation = Boolean.FALSE;
    
    /** 阻止推广信息 */
    private String exteninfo;
    
    /** 通讯地址类型ID */
    private String addressTypeId;
    
    /** 通讯地址类型id */
    private String addressTypeHiiden;
    
    /** 电子邮件地址 */
    private String email;
    
    /** 通讯地址 */
    private String messAddr;
    
    /** 籍贯ID */
    private String nativeId;
    
    /*** 居住开始时间 **/
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date liveStartDate;
    
    /*** 居住结束时间 **/
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date liveEndDate;
}
