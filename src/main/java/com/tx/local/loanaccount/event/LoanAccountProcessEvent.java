/*
 * 描          述:  <描述>
 * 修  改   人:  Administrator
 * 修改时间:  2014年4月26日
 * <修改描述:>
 */
package com.tx.local.loanaccount.event;

import com.tx.component.command.context.CommandRequest;
import com.tx.component.event.event.impl.EventImpl;
import com.tx.local.loanaccount.model.LoanAccount;

/**
 * 贷款单账户操作事件基类<br/>
 * 
 * @author  Administrator
 * @version  [版本号, 2014年4月26日]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public abstract class LoanAccountProcessEvent extends EventImpl {
    
    /** 贷款账户 */
    private final LoanAccount loanAccount;
    
    /** 操作请求 */
    private final CommandRequest request;
    
    /**
     * 
     * 贷款单账户操作事件基类
     * 
     * @param loanAccount 贷款账户
     * @param processRequest 操作请求
     * 
     * @version [版本号, 2015年12月21日]
     * @see [相关类/方法]
     * @since [产品/模块版本]
     * @author rain
     */
    public LoanAccountProcessEvent(LoanAccount loanAccount, CommandRequest processRequest) {
        super();
        this.loanAccount = loanAccount;
        this.request = processRequest;
    }
    
    /** 贷款账户 */
    public LoanAccount getLoanAccount() {
        return loanAccount;
    }
    
    /** 操作请求 */
    public CommandRequest getRequest() {
        return request;
    }
}
