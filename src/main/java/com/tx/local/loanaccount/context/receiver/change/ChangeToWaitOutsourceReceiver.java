/*
 * 描          述:  <描述>
 * 修  改   人:  Administrator
 * 修改时间:  2015年2月4日
 * <修改描述:>
 */
package com.tx.local.loanaccount.context.receiver.change;

import org.springframework.stereotype.Component;

import com.tx.component.command.context.CommandResponse;
import com.tx.local.loanaccount.context.request.change.ChangeToWaitOutsourceRequest;
import com.tx.local.loanaccount.model.CollectionStatusEnum;
import com.tx.local.loanaccount.model.LoanAccount;

/**
 * 修改贷款账户为待委外<br/>
 * <功能详细描述>
 * 
 * @author  Administrator
 * @version  [版本号, 2015年2月4日]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
@Component("changeToWaitOutsourceReceiver")
public class ChangeToWaitOutsourceReceiver extends
        AbstractChangeReceiver<ChangeToWaitOutsourceRequest> {
    
    /**
     * @param request
     * @param response
     * @param loanAccount
     */
    @Override
    public void posthandle(ChangeToWaitOutsourceRequest request,
            CommandResponse response, LoanAccount loanAccount) {
        
        loanAccount.setCollectionStatus(CollectionStatusEnum.CO);
        //loanAccount.setRepayType(RepayTypeEnum.CP);
    }
}
