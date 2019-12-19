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

import com.tx.local.basicdata.model.IDCardTypeEnum;
import com.tx.local.basicdata.model.SexEnum;

import io.swagger.annotations.ApiModel;

/**
 * 员工信息<br/>
 * <功能详细描述>
 * 
 * @author  PengQingyang
 * @version  [版本号, 2013-8-26]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
@Entity
@Table(name = "oper_employee_info")
@ApiModel("员工信息")
public class EmployeeInfo implements Serializable {
    
    /** 注释内容 */
    private static final long serialVersionUID = 8805323377968943458L;
    
    @Id
    private String id;
    
    /** id */
    @Column(name = "operatorId")
    private Operator operator;
    
    /** 编号(工号) */
    private String number;
    
    /** 真实姓名 */
    private String realName;
    
    /** 证件类型 */
    private IDCardTypeEnum idCardType;
    
    /** 身份证号码 */
    private String idCardNumber;
    
    /** 生日 */
    private Date birthday;
    
    /** 性别0男  1女 */
    private SexEnum sex;
    
    /** 入职时间 */
    private Date entryDate;
    
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
    private String mobileNumber;
    
    /** 创建时间 */
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
     * @return 返回 operator
     */
    public Operator getOperator() {
        return operator;
    }

    /**
     * @param 对operator进行赋值
     */
    public void setOperator(Operator operator) {
        this.operator = operator;
    }

    /**
     * @return 返回 number
     */
    public String getNumber() {
        return number;
    }

    /**
     * @param 对number进行赋值
     */
    public void setNumber(String number) {
        this.number = number;
    }

    /**
     * @return 返回 realName
     */
    public String getRealName() {
        return realName;
    }

    /**
     * @param 对realName进行赋值
     */
    public void setRealName(String realName) {
        this.realName = realName;
    }

    /**
     * @return 返回 idCardType
     */
    public IDCardTypeEnum getIdCardType() {
        return idCardType;
    }

    /**
     * @param 对idCardType进行赋值
     */
    public void setIdCardType(IDCardTypeEnum idCardType) {
        this.idCardType = idCardType;
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
     * @return 返回 mobileNumber
     */
    public String getMobileNumber() {
        return mobileNumber;
    }

    /**
     * @param 对mobileNumber进行赋值
     */
    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
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
