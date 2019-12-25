/*
 q* 描          述:  <描述>
 * 修  改   人:  Administrator
 * 修改时间:  2019年12月17日
 * <修改描述:>
 */
package com.tx.local.operator.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

import com.tx.local.basicdata.model.IDCardTypeEnum;

import io.swagger.annotations.ApiModel;

/**
 * 操作员密保账户<br/>
 * <功能详细描述>
 * 
 * @author  Administrator
 * @version  [版本号, 2019年12月17日]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
@Entity
@Table(name = "oper_security_account")
@ApiModel("操作人员账户安全设置")
public class OperSecurityAccount implements Serializable {
    
    /** 注释内容 */
    private static final long serialVersionUID = -192107970248948939L;
    
    /** 主键 */
    private String id;
    
    /** 操作人员id */
    private String operatorId;
    
    /** 电话 */
    private String mobileNumber;
    
    /** 是否绑定电话号码 */
    private boolean mobileBinding = false;
    
    /** 是否可以手机登陆：特指手机接收短信验证码实现登陆 */
    private boolean mobileLoginEnable = false;
    
    /** 电子邮件 */
    private String email;
    
    /** 是否绑定email */
    private boolean emailBinding = false;
    
    /** 证件类型 */
    private IDCardTypeEnum idCardType;
    
    /** 身份证号码 */
    private String idCardNumber;
    
    /** 身份证到期日 */
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date idCardExpiryDate;
    
    /** 是否实名认证 */
    private boolean realNameAuthenticated = false;
    
    /** 实名认证错误次数:由于实名认证存在成本，所以增加该字段以便控制在一段时间内控制认证次数 */
    private int realNameErrCount;
    
    /** 实名认证错误时间 */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date realNameLastErrDate;
    
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
     * @return 返回 mobileBinding
     */
    public boolean isMobileBinding() {
        return mobileBinding;
    }
    
    /**
     * @param 对mobileBinding进行赋值
     */
    public void setMobileBinding(boolean mobileBinding) {
        this.mobileBinding = mobileBinding;
    }
    
    /**
     * @return 返回 mobileLoginEnable
     */
    public boolean isMobileLoginEnable() {
        return mobileLoginEnable;
    }
    
    /**
     * @param 对mobileLoginEnable进行赋值
     */
    public void setMobileLoginEnable(boolean mobileLoginEnable) {
        this.mobileLoginEnable = mobileLoginEnable;
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
     * @return 返回 emailBinding
     */
    public boolean isEmailBinding() {
        return emailBinding;
    }
    
    /**
     * @param 对emailBinding进行赋值
     */
    public void setEmailBinding(boolean emailBinding) {
        this.emailBinding = emailBinding;
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
     * @return 返回 idCardExpiryDate
     */
    public Date getIdCardExpiryDate() {
        return idCardExpiryDate;
    }
    
    /**
     * @param 对idCardExpiryDate进行赋值
     */
    public void setIdCardExpiryDate(Date idCardExpiryDate) {
        this.idCardExpiryDate = idCardExpiryDate;
    }
    
    /**
     * @return 返回 realNameAuthenticated
     */
    public boolean isRealNameAuthenticated() {
        return realNameAuthenticated;
    }
    
    /**
     * @param 对realNameAuthenticated进行赋值
     */
    public void setRealNameAuthenticated(boolean realNameAuthenticated) {
        this.realNameAuthenticated = realNameAuthenticated;
    }
    
    /**
     * @return 返回 realNameErrCount
     */
    public int getRealNameErrCount() {
        return realNameErrCount;
    }
    
    /**
     * @param 对realNameErrCount进行赋值
     */
    public void setRealNameErrCount(int realNameErrCount) {
        this.realNameErrCount = realNameErrCount;
    }
    
    /**
     * @return 返回 realNameLastErrDate
     */
    public Date getRealNameLastErrDate() {
        return realNameLastErrDate;
    }
    
    /**
     * @param 对realNameLastErrDate进行赋值
     */
    public void setRealNameLastErrDate(Date realNameLastErrDate) {
        this.realNameLastErrDate = realNameLastErrDate;
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
