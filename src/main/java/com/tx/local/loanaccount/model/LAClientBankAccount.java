/*
 * 描          述:  <描述>
 * 修  改   人:  huangdonggang
 * 修改时间:  2017年12月9日
 * <修改描述:>
 */
package com.tx.local.loanaccount.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.tx.local.basicdata.model.BankCardTypeEnum;
import com.tx.local.basicdata.model.BankInfo;
import com.tx.local.basicdata.model.IDCardTypeEnum;

/**
 *  客户扣款、退款 银行卡信息</br>
 * <功能详细描述>
 * 
 * @author  huangdonggang
 * @version  [版本号, 2017年12月9日]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
@Entity
@Table(name = "LA_CLIENT_BANK_ACCOUNT")
public class LAClientBankAccount implements Serializable {
    
    /** 注释内容 */
    private static final long serialVersionUID = 2818087061845182300L;
    
    /** 主键 */
    @Id
    private String id;
    
    /** 贷款账户主键 */
    @ManyToOne
    @Column(name = "loanAccountId", nullable = false, length = 64, updatable = false)
    private LoanAccount loanAccount;
    
    /** 证件类型 */
    @Column(nullable = false, length = 64, updatable = false)
    private IDCardTypeEnum idCardType;
    
    /** 客户证件号码 */
    @Column(nullable = false, length = 64, updatable = false)
    private String idCardNumber;
    
    /** 客户姓名 */
    @Column(nullable = false, length = 32, updatable = false)
    private String clientName;
    
    /**银行卡信息*/
    @Column(name = "bankInfoId", nullable = false, length = 64, updatable = false)
    private BankInfo bankInfo;
    
    /**银行卡类型*/
    @Column(nullable = false, length = 64, updatable = true)
    private BankCardTypeEnum bankCardType;
    
    /**银行卡用途，扣款、退款 */
    @Column(nullable = false, length = 64, updatable = true)
    private LAClientBankAccountTypeEnum type;
    
    /**银行卡卡号*/
    @Column(nullable = false, length = 64, updatable = false)
    private String number;
    
    /** 是否 默认账户,放，扣款,有且仅有一个账户 */
    @Column(nullable = false, updatable = true)
    private boolean defaultAccount;
    
    /** 是否可用 */
    @Column(nullable = false, updatable = true)
    private boolean valid;
    
    /** 最后修改时间 */
    @Column(nullable = false, updatable = true)
    private Date lastUpdateDate;
    
    /** 创建时间 */
    @Column(nullable = false, updatable = false)
    private Date createDate;
    
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
     * @return 返回 bankInfo
     */
    public BankInfo getBankInfo() {
        return bankInfo;
    }
    
    /**
     * @param 对bankInfo进行赋值
     */
    public void setBankInfo(BankInfo bankInfo) {
        this.bankInfo = bankInfo;
    }
    
    /**
     * @return 返回 bankCardType
     */
    public BankCardTypeEnum getBankCardType() {
        return bankCardType;
    }
    
    /**
     * @param 对bankCardType进行赋值
     */
    public void setBankCardType(BankCardTypeEnum bankCardType) {
        this.bankCardType = bankCardType;
    }
    
    /**
     * @return 返回 type
     */
    public LAClientBankAccountTypeEnum getType() {
        return type;
    }
    
    /**
     * @param 对type进行赋值
     */
    public void setType(LAClientBankAccountTypeEnum type) {
        this.type = type;
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
     * @return 返回 defaultAccount
     */
    public boolean isDefaultAccount() {
        return defaultAccount;
    }
    
    /**
     * @param 对defaultAccount进行赋值
     */
    public void setDefaultAccount(boolean defaultAccount) {
        this.defaultAccount = defaultAccount;
    }
    
    /**
     * @return 返回 valid
     */
    public boolean isValid() {
        return valid;
    }
    
    /**
     * @param 对valid进行赋值
     */
    public void setValid(boolean valid) {
        this.valid = valid;
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
    
}
