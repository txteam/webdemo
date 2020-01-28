/*
 * 描          述:  <描述>
 * 修  改   人:  Administrator
 * 修改时间:  2017年5月7日
 * <修改描述:>
 */
package com.tx.local.loanaccount.strategy.loanaccount;

import org.springframework.core.Ordered;

import com.tx.local.loanaccount.model.LoanAccount;
import com.tx.local.loanaccount.model.LoanAccountTypeEnum;
import com.tx.local.loanaccount.strategy.build.PaymentScheduleEntryBuilder;
import com.tx.local.loanaccount.strategy.charge.YQLXChargeStrategy;
import com.tx.local.loanaccount.strategy.detail.DetailStrategy;
import com.tx.local.loanaccount.strategy.exempt.ExemptStrategy;
import com.tx.local.loanaccount.strategy.feeitem.FeeItemStrategy;
import com.tx.local.loanaccount.strategy.repay.RepayStrategy;
import com.tx.local.loanaccount.strategy.revoke.RevokeStrategy;
import com.tx.local.loanaccount.strategy.settle.SettleStrategy;

/**
  * 贷款账户策略<br/>
  * <功能详细描述>
  * 
  * @author  Administrator
  * @version  [版本号, 2017年5月7日]
  * @see  [相关类/方法]
  * @since  [产品/模块版本]
  */
public interface LoanAccountStrategy extends Ordered, PaymentScheduleEntryBuilder, FeeItemStrategy, DetailStrategy,
        RevokeStrategy, RepayStrategy, SettleStrategy, ExemptStrategy,YQLXChargeStrategy {
    
    /**
      * 根据贷款账户判断是否与当前策略匹配<br/>
      * <功能详细描述>
      * @param loanAccount
      * @return [参数说明]
      * 
      * @return boolean [返回类型说明]
      * @exception throws [异常类型] [异常说明]
      * @see [类、类#方法、类#成员]
     */
    public boolean support(LoanAccount loanAccount);
    
    /**
      * 支持的贷款账户类型<br/>
      * <功能详细描述>
      * @return [参数说明]
      * 
      * @return LoanAccountTypeEnum [返回类型说明]
      * @exception throws [异常类型] [异常说明]
      * @see [类、类#方法、类#成员]
     */
    public LoanAccountTypeEnum supportLoanAccountType();
    
    /**
      * 根据传入的视图类型获取对应的页面名称<br/>
      * <功能详细描述>
      * @param viewType
      * @return [参数说明]
      * 
      * @return String [返回类型说明]
      * @exception throws [异常类型] [异常说明]
      * @see [类、类#方法、类#成员]
     */
    public String getPageName(String viewType, String viewName);
    
}
