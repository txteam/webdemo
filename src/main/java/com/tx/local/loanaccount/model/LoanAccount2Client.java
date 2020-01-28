/*
 * 描          述:  <描述>
 * 修  改   人:  Administrator
 * 修改时间:  2014年5月18日
 * <修改描述:>
 */
package com.tx.local.loanaccount.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.tx.local.basicdata.model.IDCardTypeEnum;

/**
 * 贷款账户与客户之间关联关系<br/>
 * 
 * @author  Administrator
 * @version  [版本号, 2014年5月18日]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
@Entity
@Table(name = "LA_ACCOUNT_2_CLIENT")
public class LoanAccount2Client implements Serializable {
    
    /** 注释内容 */
    private static final long serialVersionUID = -6031308364401303434L;
    
    /** 主键 */
    @Id
    private String id;
    
    /** 贷款账户主键 */
    @ManyToOne
    @JoinColumn(name = "loanAccountId")
    private LoanAccount loanAccount;
    
    /** 客户主键 */
    private String clientId;
    
    /** 客户账户主键 */
    private String clientAccountId;
    
    /** 证件类型 */
    private IDCardTypeEnum idCardType;
    
    /** 客户证件号码 */
    private String idCardNumber;
    
    /** 信用信息id */
    private String creditInfoId;
    
    /** 客户信用信息版本 */
    private String creditInfoVersion;
    
    /** 客户姓名 */
    private String clientName;
    
    /** 客户引用类型 */
    private LAClientTypeEnum type;
    
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
     * @return 返回 loanAccount
     */
    public LoanAccount getLoanAccount() {
        return loanAccount;
    }
    
    /**
     * @param 对loanAccount进行赋值
     */
    public void setLoanAccount(LoanAccount loanAccount) {
        this.loanAccount = loanAccount;
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
     * @return 返回 clientAccountId
     */
    public String getClientAccountId() {
        return clientAccountId;
    }
    
    /**
     * @param 对clientAccountId进行赋值
     */
    public void setClientAccountId(String clientAccountId) {
        this.clientAccountId = clientAccountId;
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
     * @return 返回 clientName
     */
    public String getClientName() {
        return clientName;
    }
    
    /**
     * @param 对clientName进行赋值
     */
    public void setClientName(String clientName) {
        this.clientName = clientName;
    }
    
    /**
     * @return 返回 type
     */
    public LAClientTypeEnum getType() {
        return type;
    }
    
    /**
     * @param 对type进行赋值
     */
    public void setType(LAClientTypeEnum type) {
        this.type = type;
    }
    
    /**
     * @return 返回 creditInfoVersion
     */
    public String getCreditInfoVersion() {
        return creditInfoVersion;
    }
    
    /**
     * @param 对creditInfoVersion进行赋值
     */
    public void setCreditInfoVersion(String creditInfoVersion) {
        this.creditInfoVersion = creditInfoVersion;
    }
}
