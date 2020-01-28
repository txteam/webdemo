/*
 * 描          述:  <描述>
 * 修  改   人:  Administrator
 * 修改时间:  2017年6月23日
 * <修改描述:>
 */
package com.tx.local.loanaccount.strategy.detail;

import java.util.Date;

import com.tx.component.strategy.context.Strategy;
import com.tx.local.loanaccount.handle.PaymentScheduleHandler;
import com.tx.local.loanaccount.model.LoanAccount;
import com.tx.local.loanaccount.model.LoanAccountDetail;

/**
  * 详情策略<br/>
  * <功能详细描述>
  * 
  * @author  Administrator
  * @version  [版本号, 2017年6月23日]
  * @see  [相关类/方法]
  * @since  [产品/模块版本]
  */
public interface DetailStrategy extends Strategy {
    
    /**
     * 构建贷款账户详情<br/>
     * <功能详细描述>
     * @param loanAccount
     * @param handler
     * @return [参数说明]
     * 
     * @return LoanAccountDetail [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
    */
    public LoanAccountDetail buildLoanAccountDetail(LoanAccount loanAccount, PaymentScheduleHandler handler,
            Date repayDate);
    
}
