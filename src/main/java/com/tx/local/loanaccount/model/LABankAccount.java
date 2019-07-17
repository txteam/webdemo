/*
 * 描          述:  <描述>
 * 修  改   人:  Administrator
 * 修改时间:  2017年5月8日
 * <修改描述:>
 */
package com.tx.local.loanaccount.model;

import java.io.Serializable;
import java.util.Collections;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.tx.component.basicdata.model.BasicData;
import com.tx.local.basicdata.model.PaymentChannelEnum;

/**
  * 贷款账户相关银行账户信息<br/>
  * <功能详细描述>
  * 
  * @author  Administrator
  * @version  [版本号, 2017年5月8日]
  * @see  [相关类/方法]
  * @since  [产品/模块版本]
  */
@Entity
@Table(name = "la_bank_account")
public class LABankAccount implements Serializable, BasicData {
    /** 注释内容 */
    private static final long serialVersionUID = 7579843529891564794L;
    
    /** 主键 */
    @Id
    private String id;
    
    /** 编码 */
    private String code;
    
    /** 账户名称 */
    private String name;
    
    /** 状态 */
    private boolean valid;
    
    /** 是否可编辑 */
    private boolean modifyAble;
    
    /** 贷款账户类型 */
    private LoanAccountTypeEnum loanAccountType;
    
    /** 银行账户类型 */
    private LABankAccountTypeEnum bankAccountType;
    
    /** 银行渠道 */
    private LABankAccountChannelEnum bankAccountChannel;
    
    /** 支付渠道 */
    private PaymentChannelEnum paymentChannel;
    
    /** 账户号 */
    private String accountNumber;
    
    /** 财务科目编码 */
    private String accountTitleCode;
    
    /** 参数配置 */
    private String attributes;
    
    /** 优先级 */
    private int priority;
    
    /** 备注信息 */
    private String remark;
    
    /** 创建时间 */
    private Date createDate;
    
    /** 最后更新时间 */
    private Date lastUpdateDate;
    
    /** 费用项集合 */
    private transient Collections feeItems;
    
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
     * @return 返回 modifyAble
     */
    public boolean isModifyAble() {
        return modifyAble;
    }
    
    /**
     * @param 对modifyAble进行赋值
     */
    public void setModifyAble(boolean modifyAble) {
        this.modifyAble = modifyAble;
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
     * @return 返回 accountNumber
     */
    public String getAccountNumber() {
        return accountNumber;
    }
    
    /**
     * @return 返回 priority
     */
    public int getPriority() {
        return priority;
    }
    
    /**
     * @param 对priority进行赋值
     */
    public void setPriority(int priority) {
        this.priority = priority;
    }
    
    /**
     * @return 返回 bankAccountType
     */
    public LABankAccountTypeEnum getBankAccountType() {
        return bankAccountType;
    }
    
    /**
     * @param 对bankAccountType进行赋值
     */
    public void setBankAccountType(LABankAccountTypeEnum bankAccountType) {
        this.bankAccountType = bankAccountType;
    }
    
    /**
     * @return 返回 remark
     */
    public String getRemark() {
        return remark;
    }
    
    /**
     * @param 对remark进行赋值
     */
    public void setRemark(String remark) {
        this.remark = remark;
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

    /**
     * @return 返回 loanAccountType
     */
    public LoanAccountTypeEnum getLoanAccountType() {
        return loanAccountType;
    }

    /**
     * @param 对loanAccountType进行赋值
     */
    public void setLoanAccountType(LoanAccountTypeEnum loanAccountType) {
        this.loanAccountType = loanAccountType;
    }

    /**
     * @return 返回 paymentChannel
     */
    public PaymentChannelEnum getPaymentChannel() {
        return paymentChannel;
    }

    /**
     * @param 对paymentChannel进行赋值
     */
    public void setPaymentChannel(PaymentChannelEnum paymentChannel) {
        this.paymentChannel = paymentChannel;
    }

    /**
     * @return 返回 accountTitleCode
     */
    public String getAccountTitleCode() {
        return accountTitleCode;
    }

    /**
     * @param 对accountTitleCode进行赋值
     */
    public void setAccountTitleCode(String accountTitleCode) {
        this.accountTitleCode = accountTitleCode;
    }

    /**
     * @return 返回 attributes
     */
    public String getAttributes() {
        return attributes;
    }

    /**
     * @param 对attributes进行赋值
     */
    public void setAttributes(String attributes) {
        this.attributes = attributes;
    }

    /**
     * @param 对accountNumber进行赋值
     */
    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    /**
     * @return 返回 feeItems
     */
    public Collections getFeeItems() {
        return feeItems;
    }

    /**
     * @param 对feeItems进行赋值
     */
    public void setFeeItems(Collections feeItems) {
        this.feeItems = feeItems;
    }

    /**
     * @return 返回 bankAccountChannel
     */
    public LABankAccountChannelEnum getBankAccountChannel() {
        return bankAccountChannel;
    }

    /**
     * @param 对bankAccountChannel进行赋值
     */
    public void setBankAccountChannel(LABankAccountChannelEnum bankAccountChannel) {
        this.bankAccountChannel = bankAccountChannel;
    }
}
