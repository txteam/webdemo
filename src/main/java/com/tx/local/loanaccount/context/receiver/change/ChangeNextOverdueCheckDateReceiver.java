/*
 * 描          述:  <描述>
 * 修  改   人:  Administrator
 * 修改时间:  2014年6月9日
 * <修改描述:>
 */
package com.tx.local.loanaccount.context.receiver.change;

import org.springframework.stereotype.Component;

import com.tx.component.command.context.CommandResponse;
import com.tx.local.loanaccount.context.request.change.ChangeNextOverdueCheckDateRequest;
import com.tx.local.loanaccount.model.LoanAccount;

/**
 * 改变贷款账户催收状态处理器
 * <功能详细描述>
 * 
 * @author  Administrator
 * @version  [版本号, 2014年6月9日]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
@Component("changeNextOverdueCheckDateReceiver")
public class ChangeNextOverdueCheckDateReceiver
        extends AbstractChangeReceiver<ChangeNextOverdueCheckDateRequest> {
    
    /**
     * @param request
     * @param loanAccount
     */
    @Override
    public void posthandle(ChangeNextOverdueCheckDateRequest request,
            CommandResponse response, LoanAccount loanAccount) {
        loanAccount.setNextOverdueCheckDate(request.getNextOverdueCheckDate());
    }
}
