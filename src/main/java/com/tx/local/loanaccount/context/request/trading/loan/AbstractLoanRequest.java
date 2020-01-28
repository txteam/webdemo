/*
 * 描          述:  <描述>
 * 修  改   人:  Administrator
 * 修改时间:  2014年8月29日
 * <修改描述:>
 */
package com.tx.local.loanaccount.context.request.trading.loan;

import java.math.BigDecimal;
import java.util.Map;

import com.tx.local.basicdata.model.FeeItemEnum;
import com.tx.local.loanaccount.context.request.LABankAccountAware;
import com.tx.local.loanaccount.context.request.trading.AbstractTradingRequest;
import com.tx.local.loanaccount.model.LABankAccount;
import com.tx.local.loanaccount.model.LoanAccount;
import com.tx.local.loanaccount.model.RequestSourceTypeEnum;

/**
 * 放款请求器<br/>
 * 
 * @author   Tim.peng
 * @version  [版本号, 2014年8月29日]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public abstract class AbstractLoanRequest extends AbstractTradingRequest
        implements LABankAccountAware {
    
    /** 银行账户id */
    private String bankAccountId;
    
    /** 放款期间各费用项 */
    private Map<FeeItemEnum, BigDecimal> feeItem2AmountMap;
    
    /** 银行账户 */
    private transient LABankAccount bankAccount;
    
    /** <默认构造函数> */
    public AbstractLoanRequest() {
        super();
    }
    
    /** 抽象：发放贷款请求 */
    public AbstractLoanRequest(LoanAccount loanAccount,
            Map<FeeItemEnum, BigDecimal> feeItem2AmountMap) {
        super(loanAccount.getId(), RequestSourceTypeEnum.RELATE_SCHEDULE);
        
        this.feeItem2AmountMap = feeItem2AmountMap;
    }
    
    /**
     * @return 返回 bankAccountId
     */
    public String getBankAccountId() {
        return bankAccountId;
    }
    
    /**
     * @param 对bankAccountId进行赋值
     */
    public void setBankAccountId(String bankAccountId) {
        this.bankAccountId = bankAccountId;
    }
    
    /**
     * @return 返回 feeItem2AmountMap
     */
    public Map<FeeItemEnum, BigDecimal> getFeeItem2AmountMap() {
        return feeItem2AmountMap;
    }
    
    /**
     * @param 对feeItem2AmountMap进行赋值
     */
    public void setFeeItem2AmountMap(
            Map<FeeItemEnum, BigDecimal> feeItem2AmountMap) {
        this.feeItem2AmountMap = feeItem2AmountMap;
    }
    
    /**
     * @return 返回 bankAccount
     */
    public LABankAccount getBankAccount() {
        return bankAccount;
    }
    
    /**
     * @param 对bankAccount进行赋值
     */
    public void setBankAccount(LABankAccount bankAccount) {
        this.bankAccount = bankAccount;
    }
}
