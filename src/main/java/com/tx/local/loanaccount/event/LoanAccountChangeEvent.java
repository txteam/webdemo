/*
 * 描          述:  <描述>
 * 修  改   人:  Administrator
 * 修改时间:  2014年4月26日
 * <修改描述:>
 */
package com.tx.local.loanaccount.event;

import com.tx.component.event.event.impl.EventImpl;
import com.tx.local.loanaccount.model.LoanAccount;

/**
 * 贷款单账户事件<br/>
 * <功能详细描述>
 * 
 * @author  Administrator
 * @version  [版本号, 2014年4月26日]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public abstract class LoanAccountChangeEvent extends EventImpl {
    
    /** 贷款账户实例 */
    private final LoanAccount loanAccount;

    /** <默认构造函数> */
    public LoanAccountChangeEvent(LoanAccount loanAccount) {
        super();
        this.loanAccount = loanAccount;
    }

    /**
     * @return 返回 loanAccount
     */
    public LoanAccount getLoanAccount() {
        return loanAccount;
    }
}
