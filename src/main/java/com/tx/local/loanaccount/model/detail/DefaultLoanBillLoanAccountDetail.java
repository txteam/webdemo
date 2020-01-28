/*
 * 描          述:  <描述>
 * 修  改   人:  lenovo
 * 修改时间:  2014年5月30日
 * <修改描述:>
 */
package com.tx.local.loanaccount.model.detail;

import java.util.Date;

import com.tx.local.loanaccount.handle.PaymentScheduleHandler;
import com.tx.local.loanaccount.model.LoanAccount;
import com.tx.local.loanaccount.model.LoanAccount2LoanBill;
import com.tx.local.loanaccount.model.LoanBillLoanAccountDetail;
import com.tx.local.loanbill.model.LoanTypeEnum;

/**
 * 贷款账户详情实现<br/>
 * 
 * @author lenovo
 * @version [版本号, 2014年5月30日]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
public class DefaultLoanBillLoanAccountDetail extends BaseLoanAccountDetail
        implements LoanBillLoanAccountDetail {
    
    /** 注释内容 */
    private static final long serialVersionUID = 5628644077973741786L;
    
    /** 贷款类别:新贷、再贷、续贷 */
    private LoanTypeEnum loanType;
    
    /** 客户贷款账户id */
    private String clientLoanAccountId;
    
    /** 贷款单id */
    private String loanBillId;
    
    /** 贷款单编号 */
    private String loanBillNumber;
    
    /** 贷款产品id */
    private String loanProductId;
    
    /** 贷款产品名 */
    private String loanProductName;
    
    /** <默认构造函数> */
    public DefaultLoanBillLoanAccountDetail() {
        super();
    }
    
    /** <默认构造函数> */
    public DefaultLoanBillLoanAccountDetail(
            LoanAccount2LoanBill loanAccount2LoanBill, LoanAccount loanAccount,
            PaymentScheduleHandler handler, Date repayDate) {
        super(loanAccount, handler, repayDate);
        
        this.loanType = loanAccount2LoanBill == null ? null
                : loanAccount2LoanBill.getLoanType();
        this.loanBillId = loanAccount2LoanBill == null ? null
                : loanAccount2LoanBill.getLoanBillId();
        this.loanBillNumber = loanAccount2LoanBill == null ? null
                : loanAccount2LoanBill.getLoanBillNumber();
        this.loanProductId = loanAccount2LoanBill == null ? null
                : loanAccount2LoanBill.getLoanProductId();
        this.loanProductName = loanAccount2LoanBill == null ? null
                : loanAccount2LoanBill.getLoanProductName();
    }
    
    /**
     * @return 返回 loanType
     */
    public LoanTypeEnum getLoanType() {
        return loanType;
    }
    
    /**
     * @param 对loanType进行赋值
     */
    public void setLoanType(LoanTypeEnum loanType) {
        this.loanType = loanType;
    }
    
    /**
     * @return 返回 clientLoanAccountId
     */
    public String getClientLoanAccountId() {
        return clientLoanAccountId;
    }
    
    /**
     * @param 对clientLoanAccountId进行赋值
     */
    public void setClientLoanAccountId(String clientLoanAccountId) {
        this.clientLoanAccountId = clientLoanAccountId;
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
}
