/*
 * 描          述:  <描述>
 * 修  改   人:  Administrator
 * 修改时间:  2014年12月14日
 * <修改描述:>
 */
package com.tx.local.loanaccount.context.receiver.change;

import org.springframework.stereotype.Component;

import com.tx.component.command.context.CommandResponse;
import com.tx.local.loanaccount.context.request.change.ChangeToOutsourceRequest;
import com.tx.local.loanaccount.model.CollectionStatusEnum;
import com.tx.local.loanaccount.model.LoanAccount;

/**
 * 委外请求响应处理器<br/>
 * <功能详细描述>
 * 
 * @author  Administrator
 * @version  [版本号, 2014年12月14日]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
@Component("changeToOutsourceReceiver")
public class ChangeToOutsourceReceiver extends
        AbstractChangeReceiver<ChangeToOutsourceRequest> {
    
    /**
     * @param request
     * @param response
     * @param loanAccount
     */
    @Override
    public void posthandle(ChangeToOutsourceRequest request,
            CommandResponse response, LoanAccount loanAccount) {
        
        loanAccount.setOutsourceHappend(true);
        loanAccount.setOutsource(true);
        loanAccount.setOutsourceAssignRecordId(request.getOutsourceAssignRecordId());
        loanAccount.setOutsourcePercentage(request.getOutsourcePercentage());
        loanAccount.setOutsourceAmount(request.getOutsourceAmount());
        loanAccount.setCollectionStatus(CollectionStatusEnum.OA);
    }
    
}
