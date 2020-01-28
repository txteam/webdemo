package com.tx.local.loanaccount.context.receiver.change;

import java.util.Date;

import org.springframework.stereotype.Component;

import com.tx.component.command.context.CommandResponse;
import com.tx.component.event.context.EventContext;
import com.tx.core.exceptions.util.AssertUtils;
import com.tx.local.loanaccount.context.request.change.ChangeToOverdueRequest;
import com.tx.local.loanaccount.event.change.OverdueEvent;
import com.tx.local.loanaccount.model.CollectionStatusEnum;
import com.tx.local.loanaccount.model.LoanAccount;

/**
  * 将账户状态改变为已逾期<br/>
  * <功能详细描述>
  * 
  * @author  Administrator
  * @version  [版本号, 2014年6月21日]
  * @see  [相关类/方法]
  * @since  [产品/模块版本]
 */
@Component
public class ChangeToOverdueReceiver extends
        AbstractChangeReceiver<ChangeToOverdueRequest> {
    
    /**
     * @param request
     * @param response
     * @param loanAccount
     */
    @Override
    public void posthandle(ChangeToOverdueRequest request,
            CommandResponse response, LoanAccount loanAccount) {
        Date overdueDate = request.getOverdueDate();
        
        AssertUtils.notNull(overdueDate, "overdueDate is null.");
        loanAccount.setOverdue(true);
        loanAccount.setOverdueDate(overdueDate);
        
        //看看，逾期金额，逾期中各逻辑是否需要重新处理<br/>
        CollectionStatusEnum newCollectionStatus = loanAccount.getCollectionStatus();
        //如果账户状态为逾期
        switch (loanAccount.getCollectionStatus()) {
            case NA:
                newCollectionStatus = CollectionStatusEnum.DQ;
                break;
            default:
                break;
        }
        loanAccount.setCollectionStatus(newCollectionStatus);
        
        EventContext.getContext().trigger(new OverdueEvent(loanAccount,
                overdueDate));
    }
    
}
