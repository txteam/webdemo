package com.tx.local.loanaccount.event.change;

import com.tx.local.loanaccount.event.LoanAccountChangeEvent;
import com.tx.local.loanaccount.model.LoanAccount;

/**
 * 
 * 账户还款到不逾期（包括AC和结清的情况）时触发本事件
 * 
 * @author  kashima
 * @version  [版本号, 2014-5-25]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public class OverdueOverEvent extends LoanAccountChangeEvent {
    
    /** <默认构造函数> */
    public OverdueOverEvent(LoanAccount loanAccount) {
        super(loanAccount);
    }
}
