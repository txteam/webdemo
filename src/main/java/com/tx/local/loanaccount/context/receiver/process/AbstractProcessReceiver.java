/*
 * 描          述:  <描述>
 * 修  改   人:  Administrator
 * 修改时间:  2014年4月10日
 * <修改描述:>
 */
package com.tx.local.loanaccount.context.receiver.process;

import com.tx.component.command.context.CommandResponse;
import com.tx.local.loanaccount.context.receiver.AbstractLoanAccountReceiver;
import com.tx.local.loanaccount.context.request.process.AbstractProcessRequest;
import com.tx.local.loanaccount.model.LoanAccount;

/**
 * 贷款账户操作支持接口
 * 
 * @author Administrator
 * @version [版本号, 2014年4月10日]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
public abstract class AbstractProcessReceiver<PR extends AbstractProcessRequest>
        extends AbstractLoanAccountReceiver<PR> {
    
    /**
     * @param request
     * @return
     */
    @Override
    protected boolean isMatch(PR request) {
        return true;
    }
    
    /**
     * 校验请求合法性
     * 
     * @param request 操作请求器
     * @param response 操作响应器
     * @param loanAccount 贷款账户
     *            
     * @return void [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    public void validateRequest(PR request, CommandResponse response,
            LoanAccount loanAccount) {
    }
    
    /**
     * 负责进行调度的逻辑
     * 
     * @param request 操作请求器
     * @param response 操作响应器
     * @param loanAccount 贷款账户
     *            
     * @return void [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    public void handle(PR request, CommandResponse response,
            LoanAccount loanAccount) {
    }
    
}
