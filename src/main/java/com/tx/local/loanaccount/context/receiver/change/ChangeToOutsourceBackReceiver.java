/*
  * 描          述:  <描述>
 * 修  改   人:  Administrator
 * 修改时间:  2014年12月14日
 * <修改描述:>
 */
package com.tx.local.loanaccount.context.receiver.change;

import java.math.BigDecimal;

import org.springframework.stereotype.Component;

import com.tx.component.command.context.CommandResponse;
import com.tx.local.loanaccount.context.request.change.ChangeToOutsourceBackRequest;
import com.tx.local.loanaccount.model.CollectionStatusEnum;
import com.tx.local.loanaccount.model.LoanAccount;

/**
 * 委外退回代码实现类<br/>
 * <功能详细描述>
 * 
 * @author  Administrator
 * @version  [版本号, 2014年12月14日]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
@Component("changeToOutsourceBackReceiver")
public class ChangeToOutsourceBackReceiver extends
        AbstractChangeReceiver<ChangeToOutsourceBackRequest> {
    
    /**
     * @param request
     * @param response
     * @param loanAccount
     */
    @Override
    public void posthandle(ChangeToOutsourceBackRequest request,
            CommandResponse response, LoanAccount loanAccount) {
        
        //委外退回保留与贷款账户关联关系，是为了方便委外退回账户列表的已还金额查询显示
        //updateRowMap.put("outsourceAssignRecordId", "");
        loanAccount.setOutsource(false);
        loanAccount.setOutsourceAmount(BigDecimal.ZERO);
        loanAccount.setOutsourcePercentage(BigDecimal.ZERO);
        
        loanAccount.setCollectionStatus(CollectionStatusEnum.RA);
    }
}
