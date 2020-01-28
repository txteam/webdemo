/*
 * 描          述:  <描述>
 * 修  改   人:  Administrator
 * 修改时间:  2014年5月21日
 * <修改描述:>
 */
package com.tx.local.loanaccount.context.receiver.build;

import java.util.List;

import com.tx.component.command.context.CommandResponse;
import com.tx.component.event.context.EventContext;
import com.tx.local.loanaccount.LoanAccountConstants;
import com.tx.local.loanaccount.context.receiver.AbstractLoanAccountReceiver;
import com.tx.local.loanaccount.context.request.build.AbstractBuildRequest;
import com.tx.local.loanaccount.event.trading.CreateEvent;
import com.tx.local.loanaccount.model.LoanAccount;
import com.tx.local.loanaccount.model.LoanAccountFeeItem;
import com.tx.local.loanaccount.model.PaymentSchedule;
import com.tx.local.loanaccount.model.PaymentScheduleEntry;
import com.tx.local.loanaccount.strategy.loanaccount.LoanAccountStrategy;

/**
 * 账户构建接收器<br/>
 * <功能详细描述>
 * 
 * @author  Administrator
 * @version  [版本号, 2014年5月21日]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public abstract class AbstractBuildReceiver<PR extends AbstractBuildRequest>
        extends AbstractLoanAccountReceiver<PR> {
    
    /**
     * 校验请求合法性<br/>
     *    续贷，再贷处理逻辑中会用到<br/>
     * <功能详细描述>
     * @param request
     * @param response
     * @param loanAccount [参数说明]
     * 
     * @return void [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
    */
    public void validateRequest(PR request, CommandResponse response) {
    }
    
    /**
      * 构建贷款账户<br/>
      * <功能详细描述>
      * @param request
      * @return [参数说明]
      * 
      * @return LoanAccount [返回类型说明]
      * @exception throws [异常类型] [异常说明]
      * @see [类、类#方法、类#成员]
     */
    public abstract LoanAccount buildLoanAccount(PR request);
    
    /**
      * 构建贷款账户的费用配置项<br/>
      * <功能详细描述>
      * @param request
      * @param loanAccount
      * @return [参数说明]
      * 
      * @return List<LoanAccountFeeCfgItem> [返回类型说明]
      * @exception throws [异常类型] [异常说明]
      * @see [类、类#方法、类#成员]
     */
    public abstract List<LoanAccountFeeItem> buildLoanAccountFeeItems(
            PR request, LoanAccount loanAccount);
    
    /**
      * 构建还款计划<br/>
      *<功能详细描述>
      * @param request
      * @param loanAccount
      * @param feeCfgItems
      * @return [参数说明]
      * 
      * @return List<PaymentSchedule> [返回类型说明]
      * @exception throws [异常类型] [异常说明]
      * @see [类、类#方法、类#成员]
     */
    public abstract List<PaymentSchedule> buildPaymentSchedules(PR request,
            LoanAccount loanAccount, List<LoanAccountFeeItem> feeCfgItems,
            LoanAccountStrategy strategy);
    
    /**
     * 账户变更的操作实现类<br/>
     * @param request [参数说明]
     * 
     * @return void [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    public void postHandle(PR request, CommandResponse response,
            LoanAccount loanAccount, List<LoanAccountFeeItem> feeCfgItems,
            List<PaymentSchedule> paymentScheduleList,
            List<PaymentScheduleEntry> paymentScheduleEntryList) {
        response.setAttribute(LoanAccountConstants.RESPONSE_KEY_LOANACCOUNT,
                loanAccount);
        response.setAttribute(LoanAccountConstants.RESPONSE_KEY_PAYMENTSCHEDULE,
                paymentScheduleList);
        
        CreateEvent createEvent = new CreateEvent(loanAccount,
                paymentScheduleList, paymentScheduleEntryList, request);
        EventContext.getContext().trigger(createEvent);
    }
    
    /**
     * 账户变更的后置处理逻辑<br/>
     * @param request [参数说明]
     * 
     * @return void [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    public void afterHandle(PR request, CommandResponse response,
            LoanAccount loanAccount, List<LoanAccountFeeItem> feeCfgItems,
            List<PaymentSchedule> paymentSchedules) {
    }
}
