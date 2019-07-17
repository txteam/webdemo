/*
 * 描          述:  <描述>
 * 修  改   人:  PengQingyang
 * 修改时间:  2013-8-26
 * <修改描述:>
 */
package com.tx.local.operator.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * <功能简述>
 * <功能详细描述>
 * 
 * @author  PengQingyang
 * @version  [版本号, 2013-8-26]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
@Entity
@Table(name = "oper_employee_info")
public class EmployeeInfo implements Serializable {
    
    /** 注释内容 */
    private static final long serialVersionUID = 8805323377968943458L;
    
    /** id */
    @Id
    @Column(name = "operatorId")
    private String operatorId;
    
    /** 编号(工号) */
    private String code;
    
    /** 姓名 */
    private String name;
    
    /** 身份证号码 */
    private String idCardNumber;
    
    /** 年龄 */
    private int age;
    
    /** 性别0男  1女 */
    private int sex;
    
    /** 入职时间 */
    private Date entryDate;
    
    /** 试用期 到期时间 */
    private Date trialPeriodEndDate;
    
    /**是否转正*/
    private boolean official = true;
    
    /** 转正时间  */
    private Date officialDate;
    
    /**是否离职*/
    private boolean leaving = false;
    
    /**离职时间*/
    private Date leavingDate;
    
    /** 邮箱 */
    private String email;
    
    /** 电话 */
    private String phoneNumber;
    
    /**
     * @return 返回 operatorId
     */
    public String getOperatorId() {
        return operatorId;
    }
    
    /**
     * @param 对operatorId进行赋值
     */
    public void setOperatorId(String operatorId) {
        this.operatorId = operatorId;
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
     * @return 返回 idCardNumber
     */
    public String getIdCardNumber() {
        return idCardNumber;
    }
    
    /**
     * @param 对idCardNumber进行赋值
     */
    public void setIdCardNumber(String idCardNumber) {
        this.idCardNumber = idCardNumber;
    }
    
    /**
     * @return 返回 age
     */
    public int getAge() {
        return age;
    }
    
    /**
     * @param 对age进行赋值
     */
    public void setAge(int age) {
        this.age = age;
    }
    
    /**
     * @return 返回 sex
     */
    public int getSex() {
        return sex;
    }
    
    /**
     * @param 对sex进行赋值
     */
    public void setSex(int sex) {
        this.sex = sex;
    }
    
    /**
     * @return 返回 entryDate
     */
    public Date getEntryDate() {
        return entryDate;
    }
    
    /**
     * @param 对entryDate进行赋值
     */
    public void setEntryDate(Date entryDate) {
        this.entryDate = entryDate;
    }
    
    /**
     * @return 返回 trialPeriodEndDate
     */
    public Date getTrialPeriodEndDate() {
        return trialPeriodEndDate;
    }
    
    /**
     * @param 对trialPeriodEndDate进行赋值
     */
    public void setTrialPeriodEndDate(Date trialPeriodEndDate) {
        this.trialPeriodEndDate = trialPeriodEndDate;
    }
    
    /**
     * @return 返回 official
     */
    public boolean isOfficial() {
        return official;
    }
    
    /**
     * @param 对official进行赋值
     */
    public void setOfficial(boolean official) {
        this.official = official;
    }
    
    /**
     * @return 返回 officialDate
     */
    public Date getOfficialDate() {
        return officialDate;
    }
    
    /**
     * @param 对officialDate进行赋值
     */
    public void setOfficialDate(Date officialDate) {
        this.officialDate = officialDate;
    }
    
    /**
     * @return 返回 leaving
     */
    public boolean isLeaving() {
        return leaving;
    }
    
    /**
     * @param 对leaving进行赋值
     */
    public void setLeaving(boolean leaving) {
        this.leaving = leaving;
    }
    
    /**
     * @return 返回 leavingDate
     */
    public Date getLeavingDate() {
        return leavingDate;
    }
    
    /**
     * @param 对leavingDate进行赋值
     */
    public void setLeavingDate(Date leavingDate) {
        this.leavingDate = leavingDate;
    }
    
    /**
     * @return 返回 email
     */
    public String getEmail() {
        return email;
    }
    
    /**
     * @param 对email进行赋值
     */
    public void setEmail(String email) {
        this.email = email;
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
}
