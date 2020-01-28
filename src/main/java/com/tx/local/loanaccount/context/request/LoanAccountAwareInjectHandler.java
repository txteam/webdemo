/*
 * 描          述:  <描述>
 * 修  改   人:  Administrator
 * 修改时间:  2017年6月2日
 * <修改描述:>
 */
package com.tx.local.loanaccount.context.request;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.tx.component.command.context.injecthandler.AbstractInjectHandler;
import com.tx.core.exceptions.util.AssertUtils;
import com.tx.local.loanaccount.model.LoanAccount;
import com.tx.local.loanaccount.service.LoanAccountService;

/**
  * 贷款账户注入处理器<br/>
  * <功能详细描述>
  * 
  * @author  Administrator
  * @version  [版本号, 2017年6月2日]
  * @see  [相关类/方法]
  * @since  [产品/模块版本]
  */
@Component("loanAccountAwareInjectHandler")
public class LoanAccountAwareInjectHandler extends AbstractInjectHandler<LoanAccountAware> {
    
    @Resource(name = "loanAccountService")
    private LoanAccountService loanAccountService;
    
    /**
     * @param request
     */
    @Override
    protected void doInject(LoanAccountAware request) {
        String loanAccountId = request.getLoanAccountId();
        AssertUtils.notEmpty(loanAccountId, "loanAccountId is empty.");
        
        if(request.getLoanAccount() == null){
            LoanAccount la = this.loanAccountService.findById(loanAccountId);
            AssertUtils.notNull(la, "loanAccount:{} is not exist.", loanAccountId);
            
            request.setLoanAccount(la);
        }
    }
}
