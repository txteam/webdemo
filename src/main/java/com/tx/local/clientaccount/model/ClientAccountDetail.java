package com.tx.local.clientaccount.model;

import com.tx.local.basicdata.model.IDCardTypeEnum;
import com.tx.local.clientinfo.model.ClientTypeEnum;

/**
 * 客户账户详情<br/>
 * <功能简述>
 * 
 * @author bobby
 * @version [版本号, 2016年1月27日]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
public class ClientAccountDetail extends ClientAccount {
    
    /** 客户类型 */
    private ClientTypeEnum clientType;
    
    /** 移动电话号码 */
    private String mobilePhoneNumber;
    
    /** 邮件 */
    private String email;
    
    /** 名称 */
    private String userName;
    
    /** 证件类型 */
    private IDCardTypeEnum idCardType;
    
    /** 证件号码 */
    private String idCardNumber;
    
    /** 登录名 */
    private String loginName;

    /**
     * @return 返回 clientType
     */
    public ClientTypeEnum getClientType() {
        return clientType;
    }

    /**
     * @param 对clientType进行赋值
     */
    public void setClientType(ClientTypeEnum clientType) {
        this.clientType = clientType;
    }

    /**
     * @return 返回 mobilePhoneNumber
     */
    public String getMobilePhoneNumber() {
        return mobilePhoneNumber;
    }

    /**
     * @param 对mobilePhoneNumber进行赋值
     */
    public void setMobilePhoneNumber(String mobilePhoneNumber) {
        this.mobilePhoneNumber = mobilePhoneNumber;
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
     * @return 返回 userName
     */
    public String getUserName() {
        return userName;
    }

    /**
     * @param 对userName进行赋值
     */
    public void setUserName(String userName) {
        this.userName = userName;
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
     * @return 返回 loginName
     */
    public String getLoginName() {
        return loginName;
    }

    /**
     * @param 对loginName进行赋值
     */
    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }
}
