/*
 * 描          述:  <描述>
 * 修  改   人:  Administrator
 * 修改时间:  2017年6月2日
 * <修改描述:>
 */
package com.tx.local.loanaccount.context.receiver;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.tx.component.command.context.CommandReceiver;
import com.tx.component.command.context.CommandRequest;
import com.tx.component.command.context.ReceiverSelector;
import com.tx.core.exceptions.util.AssertUtils;
import com.tx.local.loanaccount.context.request.trading.AbstractTradingRequest;
import com.tx.local.loanaccount.model.LoanAccount;
import com.tx.local.loanaccount.strategy.loanaccount.LoanAccountStrategyHelper;

/**
  * 贷款账户处理器的选择器<br/>
  * <功能详细描述>
  * 
  * @author  Administrator
  * @version  [版本号, 2017年6月2日]
  * @see  [相关类/方法]
  * @since  [产品/模块版本]
  */
@Component("loanAccountReceiverSelector")
public class LoanAccountTradingReceiverSelector implements ReceiverSelector {
    
    /** 贷款账户策略辅助器 */
    @Resource(name = "loanAccountStrategyHelper")
    private LoanAccountStrategyHelper LoanAccountStrategyHelper;
    
    /**
     * @return
     */
    @Override
    public int getOrder() {
        return 0;
    }
    
    /**
     * @return
     */
    @Override
    public Class<? extends CommandRequest> getRequestType() {
        return AbstractTradingRequest.class;
    }
    
    /**
     * @param request
     * @param receivers
     * @return
     */
    @SuppressWarnings("unchecked")
    @Override
    public <PR extends CommandRequest> CommandReceiver<PR> select(CommandRequest request,
            List<CommandReceiver<? extends CommandRequest>> receivers) {
        AssertUtils.notNull(request, "request is null.");
        AssertUtils.notEmpty(receivers, "receivers is empty.requestType:{}",new Object[]{request.getClass().getName()});
        
        AssertUtils.isTrue(request instanceof AbstractTradingRequest, "request not AbstractTradingRequest instantce.");
        AbstractTradingRequest tr = (AbstractTradingRequest) request;
        
        String loanAccountId = tr.getLoanAccountId();
        LoanAccount loanAccount = tr.getLoanAccount();
        AssertUtils.notEmpty(loanAccountId, "loanAccountId is empty.");
        AssertUtils.notNull(loanAccount, "loanAccount is null.");
        
        @SuppressWarnings("rawtypes")
        CommandReceiver receiver = receivers.get(0);
        return receiver;
    }
}
