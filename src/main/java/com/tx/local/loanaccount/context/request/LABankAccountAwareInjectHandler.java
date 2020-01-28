/*
 * 描          述:  <描述>
 * 修  改   人:  Administrator
 * 修改时间:  2017年6月2日
 * <修改描述:>
 */
package com.tx.local.loanaccount.context.request;

import javax.annotation.Resource;

import com.tx.core.util.StringUtils;
import org.springframework.stereotype.Component;

import com.tx.component.command.context.injecthandler.AbstractInjectHandler;
import com.tx.core.exceptions.util.AssertUtils;
import com.tx.local.loanaccount.model.LABankAccount;
import com.tx.local.loanaccount.service.LABankAccountService;

/**
  * 贷款账户注入处理器<br/>
  * <功能详细描述>
  * 
  * @author  Administrator
  * @version  [版本号, 2017年6月2日]
  * @see  [相关类/方法]
  * @since  [产品/模块版本]
  */
@Component("laBankAccountAwareInjectHandler")
public class LABankAccountAwareInjectHandler extends AbstractInjectHandler<LABankAccountAware> {
    
    /** 银行账户业务层 */
    @Resource(name = "laBankAccountService")
    private LABankAccountService laBankAccountService;
    
    /**
     * @param request
     */
    @Override
    protected void doInject(LABankAccountAware request) {
        String bankAccountId = request.getBankAccountId();
//        AssertUtils.notEmpty(bankAccountId, "bankAccountId is empty.");
        
        if (request.getBankAccount() == null && StringUtils.isNotEmpty(bankAccountId)) {
            LABankAccount bankAccount = this.laBankAccountService.findById(bankAccountId);
            AssertUtils.notNull(bankAccount, "bankAccount:{} is not exist.", bankAccountId);
            
            request.setBankAccount(bankAccount);
        }
        
    }
}
