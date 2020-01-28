/*
 * 描          述:  <描述>
 * 修  改   人:  Administrator
 * 修改时间:  2014年6月9日
 * <修改描述:>
 */
package com.tx.local.loanaccount.context.receiver.change;

import org.springframework.stereotype.Component;

import com.tx.component.command.context.CommandResponse;
import com.tx.core.exceptions.util.AssertUtils;
import com.tx.local.loanaccount.context.request.change.ChangeCollectionStatusRequest;
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
@Component("changeCollectionStatusReceiver")
public class ChangeCollectionStatusReceiver extends
        AbstractChangeReceiver<ChangeCollectionStatusRequest> {
    
    /**
     * @param request
     * @param loanAccount
     */
    @Override
    public void validateRequest(ChangeCollectionStatusRequest request,
            CommandResponse response, LoanAccount loanAccount) {
        AssertUtils.notNull(loanAccount, "loanAccount is null.");
        loanAccount.getCollectionStatus();
    }
    
    /**
     * @param request
     * @param loanAccount
     */
    @Override
    public void posthandle(ChangeCollectionStatusRequest request,
            CommandResponse response, LoanAccount loanAccount) {
        
        //如果传入了催收状态
        if (request.getCollectionStatus() != null) {
            loanAccount.setCollectionStatus(request.getCollectionStatus());
        }
        //如果传入了是否法律程序，更新是否法律程序
        if (request.getIsDied() != null) {
            loanAccount.setDied(request.getIsDied());
        }
        //如果传入了是否死亡，进行更新是否死亡
        if (request.getIsLegalProcedure() != null) {
            loanAccount.setLegalProcedure(request.getIsLegalProcedure());
        }
        //如果传入了是否死亡，进行更新是否死亡
        if (request.getIsClosed() != null) {
            loanAccount.setClosed(request.getIsClosed());
        }
    }
}
