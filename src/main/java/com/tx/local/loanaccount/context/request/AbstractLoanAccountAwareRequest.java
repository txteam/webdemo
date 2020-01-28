/*
 * 描          述:  <描述>
 * 修  改   人:  Administrator
 * 修改时间:  2014年6月9日
 * <修改描述:>
 */
package com.tx.local.loanaccount.context.request;

import com.tx.core.exceptions.util.AssertUtils;
import com.tx.local.loanaccount.model.LoanAccount;
import com.tx.local.loanaccount.model.RequestSourceTypeEnum;

/**
 * 账户请求基础类<br/>
 * 
 * @author  Tim.peng
 * @version  [版本号, 2014年6月9日]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public abstract class AbstractLoanAccountAwareRequest extends AbstractLoanAccountRequest implements LoanAccountAware {
    
    private transient LoanAccount loanAccount;
    
    /** <默认构造函数> */
    public AbstractLoanAccountAwareRequest() {
        super();
    }
    
    /** 根据"账户ID"、"交易来源"和"贷款账户操作类型"构建'账户'请求 */
    public AbstractLoanAccountAwareRequest(String loanAccountId, RequestSourceTypeEnum sourceType) {
        super(loanAccountId, sourceType);
    }
    
    /**
     * @param loanAccount
     */
    @Override
    public void setLoanAccount(LoanAccount loanAccount) {
        AssertUtils.notNull(loanAccount, "loanAccount is null.");
        
        this.loanAccount = loanAccount;
        setLoanAccountType(loanAccount.getLoanAccountType());
    }
    
    /**
     * @return 返回 loanAccount
     */
    public LoanAccount getLoanAccount() {
        return loanAccount;
    }
}
