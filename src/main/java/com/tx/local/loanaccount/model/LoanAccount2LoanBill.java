/*
 * 描          述:  <描述>
 * 修  改   人:  Administrator
 * 修改时间:  2017年5月1日
 * <修改描述:>
 */
package com.tx.local.loanaccount.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.tx.local.loanbill.model.LoanTypeEnum;

/**
 * 贷款单|贷款账户详情<br/>
 * <功能详细描述>
 * 
 * @author  Administrator
 * @version  [版本号, 2017年5月1日]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
@Entity
@Table(name = "LA_ACCOUNT_2_LOAN_BILL")
public class LoanAccount2LoanBill {
    
    /** 贷款账户与贷款单映射的主键 */
    @Id
    @Column(nullable = false)
    private String id;
    
    /** 贷款账户id */
    @Column(name = "loanAccountId", nullable = false, length = 64)
    private LoanAccount loanAccount;
    
    /** 贷款类别:新贷、再贷、续贷 */
    @Column(nullable = false, length = 64)
    private LoanTypeEnum loanType;
    
    /** 贷款单id */
    private String loanBillId;
    
    /** 贷款单编号 */
    private String loanBillNumber;
    
    /** 贷款产品id */
    private String loanProductId;
    
    /** 贷款产品名 */
    private String loanProductName;
    
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
     * @return 返回 loanBillId
     */
    public String getLoanBillId() {
        return loanBillId;
    }
    
    /**
     * @param 对loanBillId进行赋值
     */
    public void setLoanBillId(String loanBillId) {
        this.loanBillId = loanBillId;
    }
    
    /**
     * @return 返回 loanBillNumber
     */
    public String getLoanBillNumber() {
        return loanBillNumber;
    }
    
    /**
     * @param 对loanBillNumber进行赋值
     */
    public void setLoanBillNumber(String loanBillNumber) {
        this.loanBillNumber = loanBillNumber;
    }
    
    /**
     * @return 返回 loanProductId
     */
    public String getLoanProductId() {
        return loanProductId;
    }
    
    /**
     * @param 对loanProductId进行赋值
     */
    public void setLoanProductId(String loanProductId) {
        this.loanProductId = loanProductId;
    }
    
    /**
     * @return 返回 loanProductName
     */
    public String getLoanProductName() {
        return loanProductName;
    }
    
    /**
     * @param 对loanProductName进行赋值
     */
    public void setLoanProductName(String loanProductName) {
        this.loanProductName = loanProductName;
    }
    
    /** 贷款类别: 不变\新贷\续贷\再贷 */
    public LoanTypeEnum getLoanType() {
        return loanType;
    }
    
    /** 贷款类别: 不变\新贷\续贷\再贷 */
    public void setLoanType(LoanTypeEnum loanType) {
        this.loanType = loanType;
    }
}
