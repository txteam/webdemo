/*
 * 描          述:  <描述>
 * 修  改   人:  Administrator
 * 修改时间:  2014年5月21日
 * <修改描述:>
 */
package com.tx.local.loanaccount.context.request.build;

import com.tx.core.util.UUIDUtils;
import com.tx.local.loanaccount.model.LATradingRecordTypeEnum;
import com.tx.local.loanaccount.model.LoanAccountTypeEnum;
import com.tx.local.loanbill.model.LoanTypeEnum;

/**
 * 贷款账户构建请求<br/>
 * 
 * @author  Tim.peng
 * @version  [版本号, 2017年5月5日]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public class LoanBillLoanAccountBuildRequest extends AbstractBuildRequest {
    
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
    
    private int freeInterestDays = 0;
    
    /** <默认构造函数> */
    public LoanBillLoanAccountBuildRequest() {
        super();
        //setLoanAccountId(UUIDUtils.generateUUID());
    }
    
    /** <默认构造函数> */
    public LoanBillLoanAccountBuildRequest(
            LoanAccountTypeEnum loanAccountType) {
        super(UUIDUtils.generateUUID(), loanAccountType);
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
    
    /**
     * @return 返回 freeInterestDays
     */
    public int getFreeInterestDays() {
        return freeInterestDays;
    }
    
    /**
     * @param 对freeInterestDays进行赋值
     */
    public void setFreeInterestDays(int freeInterestDays) {
        this.freeInterestDays = freeInterestDays;
    }
    
    /**
     * @return
     */
    @Override
    public LATradingRecordTypeEnum getTradingRecordType() {
        return LATradingRecordTypeEnum.BUILD_LOAN_BILL_LOAN_ACCOUNT;
    }
}
