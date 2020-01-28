/*
 * 描          述:  <描述>
 * 修  改   人:  Administrator
 * 修改时间:  2014年8月29日
 * <修改描述:>
 */
package com.tx.local.loanaccount.context.request.trading.settle;

import java.math.BigDecimal;
import java.util.Date;

import com.tx.core.exceptions.util.AssertUtils;
import com.tx.local.loanaccount.context.request.LABankAccountAware;
import com.tx.local.loanaccount.context.request.trading.AbstractTradingRequest;
import com.tx.local.loanaccount.model.LABankAccount;
import com.tx.local.loanaccount.model.LABankAccountTypeEnum;
import com.tx.local.loanaccount.model.RequestSourceTypeEnum;
import com.tx.local.loanaccount.model.SettleIntention;

/**
 * 基础的提前结清抽象类实现<br/>
 * 
 * @author  Tim.peng
 * @version  [版本号, 2014年8月29日]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public abstract class AbstractSettleRequest extends AbstractTradingRequest implements LABankAccountAware {
    
    /** 银行账户 */
    protected transient LABankAccount bankAccount;
    
    /** 还款意愿 */
    protected SettleIntention settleIntention;
    
    /** <默认构造函数> */
    public AbstractSettleRequest() {
        super();
    }
    
    /** 根据"账户ID"、"交易来源"和"贷款账户操作类型"构建交易请求 */
    public AbstractSettleRequest(String loanAccountId, SettleIntention settleIntention) {
        super(loanAccountId, RequestSourceTypeEnum.OPER_REQUEST);
        
        AssertUtils.notNull(settleIntention, "settleIntention is null.");
        AssertUtils.isTrue(loanAccountId.equals(settleIntention.getLoanAccountId()),
                "loanAccountId is not match settleIntention.loanAccountId.");
        AssertUtils.isTrue(settleIntention.getRepayAmount().compareTo(BigDecimal.ZERO) > 0,
                "settleIntention.amount should > 0.");
        AssertUtils.notNull(settleIntention.getRepayDate(), "settleIntention.repayDate is null.");
        
        this.settleIntention = settleIntention;
    }
    
    /** 还款意愿 */
    public SettleIntention getSettleIntention() {
        return settleIntention;
    }
    
    /**
     * @param 对repayIntention进行赋值
     */
    public void setSettleIntention(SettleIntention settleIntention) {
        this.settleIntention = settleIntention;
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
     * 获取还款日期<br/>
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
        AssertUtils.notNull(this.settleIntention, "settleIntention is null.");
        return this.settleIntention.getBankAccountId();
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
        AssertUtils.notNull(this.settleIntention, "settleIntention is null.");
        return this.settleIntention.getRepayDate();
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
    public final BigDecimal getRepayAmount() {
        AssertUtils.notNull(this.settleIntention, "settleIntention is null.");
        return this.settleIntention.getRepayAmount();
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
    public final BigDecimal getExemptAmount() {
        AssertUtils.notNull(this.settleIntention, "settleIntention is null.");
        return this.settleIntention.getExemptAmount();
    }
}
