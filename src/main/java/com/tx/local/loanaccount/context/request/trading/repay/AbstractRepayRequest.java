/*
 * 描          述:  <描述>
 * 修  改   人:  Administrator
 * 修改时间:  2014年7月1日
 * <修改描述:>
 */
package com.tx.local.loanaccount.context.request.trading.repay;

import java.math.BigDecimal;
import java.util.Date;

import com.tx.core.exceptions.util.AssertUtils;
import com.tx.local.loanaccount.context.request.LABankAccountAware;
import com.tx.local.loanaccount.context.request.trading.AbstractTradingRequest;
import com.tx.local.loanaccount.model.LABankAccount;
import com.tx.local.loanaccount.model.LABankAccountTypeEnum;
import com.tx.local.loanaccount.model.LoanAccount;
import com.tx.local.loanaccount.model.RepayIntention;
import com.tx.local.loanaccount.model.RequestSourceTypeEnum;

/**
 * 还款请求基础类<br/>
 * 
 * @author Tim.peng
 * @version [版本号, 2014年7月1日]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
public abstract class AbstractRepayRequest extends AbstractTradingRequest implements LABankAccountAware {
    
    /** 银行账户 */
    protected transient LABankAccount bankAccount;
    
    /** 还款意愿 */
    protected RepayIntention repayIntention;
    
    /** <默认构造函数> */
    public AbstractRepayRequest() {
        super();
    }
    
    /** 根据"账户ID"、"交易来源"和"贷款账户操作类型"构建交易请求 */
    public AbstractRepayRequest(String loanAccountId, RepayIntention repayIntention, RequestSourceTypeEnum sourceType) {
        super(loanAccountId, sourceType);
        
        AssertUtils.notNull(repayIntention, "repayIntention is null.");
        AssertUtils.isTrue(loanAccountId.equals(repayIntention.getLoanAccountId()),
                "loanAccountId is not match repayIntention.loanAccountId.");
        AssertUtils.isTrue(repayIntention.getAmount().compareTo(BigDecimal.ZERO) > 0,
                "repayIntention.amount should > 0.");
        AssertUtils.notNull(repayIntention.getRepayDate(), "repayIntention.repayDate is null.");
        
        this.repayIntention = repayIntention;
    }
    
    /**
     * 保存贷款账户信息
     */
    public AbstractRepayRequest(LoanAccount loanAccount, RequestSourceTypeEnum sourceType) {
        super(loanAccount.getId(), sourceType);
        setLoanAccount(loanAccount);
    }
    
    /** 还款意愿 */
    public RepayIntention getRepayIntention() {
        return repayIntention;
    }
    
    /**
     * @param 对repayIntention进行赋值
     */
    public void setRepayIntention(RepayIntention repayIntention) {
        this.repayIntention = repayIntention;
    }
    
    /**
     * @param bankAccount
     */
    @Override
    public final void setBankAccount(LABankAccount bankAccount) {
        this.bankAccount = bankAccount;
    }
    
    /**
     * 获取银行账户
     * @return
     */
    @Override
    public final LABankAccount getBankAccount() {
        return this.bankAccount;
    }
    
    /**
     * 获取银行账户类型<br/>
     * <功能详细描述>
     * @return [参数说明]
     * 
     * @return Date [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    public final LABankAccountTypeEnum getBankAccountType() {
        AssertUtils.notNull(this.bankAccount, "bankAccount is null.");
        return this.bankAccount.getBankAccountType();
    }
    
    /**
     * @return
     */
    @Override
    public final String getBankAccountId() {
        AssertUtils.notNull(this.repayIntention, "repayIntention is null.");
        return this.repayIntention.getBankAccountId();
    }
    
    /**
     * 获取还款日期<br/>
     * <功能详细描述>
     * @return [参数说明]
     * 
     * @return Date [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    public final Date getRepayDate() {
        AssertUtils.notNull(this.repayIntention, "repayIntention is null.");
        return this.repayIntention.getRepayDate();
    }
    
    /**
      * 获取还款金额<br/>
      * <功能详细描述>
      * @return [参数说明]
      * 
      * @return BigDecimal [返回类型说明]
      * @exception throws [异常类型] [异常说明]
      * @see [类、类#方法、类#成员]
     */
    public final BigDecimal getAmount() {
        AssertUtils.notNull(this.repayIntention, "repayIntention is null.");
        return this.repayIntention.getAmount();
    }
}
