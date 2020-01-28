/*
 * 描          述:  <描述>
 * 修  改   人:  Administrator
 * 修改时间:  2014年6月14日
 * <修改描述:>
 */
package com.tx.local.loanaccount.context.receiver.change;

import org.springframework.stereotype.Component;

import com.tx.component.command.context.CommandResponse;
import com.tx.core.exceptions.util.AssertUtils;
import com.tx.local.loanaccount.context.request.change.ChangeSettleInterestStatusRequest;
import com.tx.local.loanaccount.model.LoanAccount;

/**
 * 改变解析状态接收器
 * <功能详细描述>
 * 
 * @author  Administrator
 * @version  [版本号, 2014年6月14日]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
@Component("changeSettleInterestStatusReceiver")
public class ChangeSettleInterestStatusReceiver extends
        AbstractChangeReceiver<ChangeSettleInterestStatusRequest> {
    
    /**
     * @param request
     * @param loanAccount
     */
    @Override
    public void validateRequest(ChangeSettleInterestStatusRequest request,
            CommandResponse response, LoanAccount loanAccount) {
        AssertUtils.notNull(loanAccount, "loanAccount is null.");
        AssertUtils.notNull(request.getSettleInterestStatus(),
                "settleInterestStatus is null.");
    }
    
    /**
     * @param request
     * @param loanAccount
     */
    @Override
    public void posthandle(ChangeSettleInterestStatusRequest request,
            CommandResponse response, LoanAccount loanAccount) {
        //修改贷款账户结息状态
        loanAccount.setSettleInterestStatus(request.getSettleInterestStatus());
    }
    
}
