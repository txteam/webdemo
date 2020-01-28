/*
 * 描          述:  <描述>
 * 修  改   人:  Administrator
 * 修改时间:  2017年6月25日
 * <修改描述:>
 */
package com.tx.local.loanaccount.context.receiver.trading.defaults.charge;

import com.tx.component.command.context.CommandResponse;
import com.tx.core.exceptions.util.AssertUtils;
import com.tx.core.util.MessageUtils;
import com.tx.local.loanaccount.context.receiver.trading.defaults.AbstractDefaultTradingReceiver;
import com.tx.local.loanaccount.context.request.trading.charge.AbstractChargeRequest;
import com.tx.local.loanaccount.handle.PaymentScheduleHandler;
import com.tx.local.loanaccount.model.AccountStatusEnum;
import com.tx.local.loanaccount.model.LoanAccount;

/**
  * 抽象豁免处理器<br/>
  * <功能详细描述>
  * 
  * @author  Administrator
  * @version  [版本号, 2017年6月25日]
  * @see  [相关类/方法]
  * @since  [产品/模块版本]
  */
public class AbstractChargeReceiver<PR extends AbstractChargeRequest> extends AbstractDefaultTradingReceiver<PR> {
    
    /**
     * @param request
     * @param response
     * @return
     */
    @Override
    public boolean preHandle(PR request, CommandResponse response) {
        String loanAccountId = request.getLoanAccountId();
        AssertUtils.notEmpty(loanAccountId, "loanAccountId is empty.");
        LoanAccount loanAccount = request.getLoanAccount();
        AssertUtils.notNull(loanAccount, "loanAccount is null.");
        
        switch (loanAccount.getAccountStatus()) {
            case AC:
            case SL:
            case WO:
                break;
            case ACCN:
            case RSL:
            case ESL:
            case WSL:
            case WFP:
            case ES:
            case RS:
            case FP:
            default:
                throw new IllegalArgumentException(
                        MessageUtils.format("错误的贷款账户状态:{}", new Object[] { loanAccount.getAccountStatus() }));
        }
        return true;
    }
    
    /**
     * 验证请求合法性<br/>
     * @param request
     * @param response
     * @param loanAccount
     * @param paymentScheduleHandler
     */
    @Override
    public void validateRequest(PR request, CommandResponse response, LoanAccount loanAccount,
            PaymentScheduleHandler paymentScheduleHandler) {
        //贷后取消的账户不能进行还款
        AssertUtils.notTrue(AccountStatusEnum.ACCN.equals(loanAccount.getAccountStatus()), "loanAccount is ACCN.");
    }
}
