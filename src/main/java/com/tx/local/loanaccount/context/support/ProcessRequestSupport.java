/*
c * 描          述:  <描述>
 * 修  改   人:  Administrator
 * 修改时间:  2014年4月25日
 * <修改描述:>
 */
package com.tx.local.loanaccount.context.support;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.tx.component.command.context.CommandResponse;
import com.tx.local.loanaccount.context.receiver.process.AbstractProcessReceiver;
import com.tx.local.loanaccount.context.request.process.AbstractProcessRequest;
import com.tx.local.loanaccount.model.LoanAccount;

/**
 * 操作请求支持类<br/>
 *     提供类似command模式中的Command对象的封装
 *     以支持context的策略模式的功能实现<br/>
 * 
 * @author  Administrator
 * @version  [版本号, 2014年4月25日]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
@Scope("prototype")
@Component("processRequestSupport")
public class ProcessRequestSupport
        extends
        AbstractLoanAccountRequestSupport<AbstractProcessRequest, AbstractProcessReceiver<AbstractProcessRequest>> {
    
    /** <默认构造函数> */
    public ProcessRequestSupport() {
        super();
    }
    
    /** <默认构造函数> */
    public ProcessRequestSupport(
            AbstractProcessReceiver<AbstractProcessRequest> receiver) {
        super(receiver);
    }
    
    /**
     * @param request
     * @param response
     * @return
     */
    @Override
    protected boolean isLockWhenHandle(AbstractProcessRequest request,
            CommandResponse response) {
        return false;
    }
    
    /** 
     * 实际贷款账户操作处理逻辑
     * <功能详细描述>
     * @param request
     * @param loanAccountId
     * @param loanAccount [参数说明]
     * 
     * @return void [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    protected void doHandle(AbstractProcessRequest request,
            CommandResponse response) {
        String loanAccountId = request.getLoanAccountId();
        LoanAccount loanAccount = this.loanAccountService.findById(loanAccountId);
        
        this.receiver.validateRequest(request, response, loanAccount);
        
        this.receiver.handle(request, response, loanAccount);
    }
}
