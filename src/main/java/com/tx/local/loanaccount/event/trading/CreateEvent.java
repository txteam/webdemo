/*
 * 描          述:  <描述>
 * 修  改   人:  Administrator
 * 修改时间:  2014年5月20日
 * <修改描述:>
 */
package com.tx.local.loanaccount.event.trading;

import java.util.List;

import com.tx.component.command.context.CommandRequest;
import com.tx.local.loanaccount.event.LoanAccountBuildEvent;
import com.tx.local.loanaccount.model.LoanAccount;
import com.tx.local.loanaccount.model.PaymentSchedule;
import com.tx.local.loanaccount.model.PaymentScheduleEntry;

/**
 * 创建贷款账户事件<br/>
 *     事件发生于
 * 
 * @author  Administrator
 * @version  [版本号, 2014年5月20日]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public class CreateEvent extends LoanAccountBuildEvent {
    
    /** <默认构造函数> */
    public CreateEvent(LoanAccount loanAccount,
            List<PaymentSchedule> paymentScheduleList,
            List<PaymentScheduleEntry> paymentScheduleEntryList,
            CommandRequest buildRequest) {
        super(loanAccount, paymentScheduleList, paymentScheduleEntryList,
                buildRequest);
    }
    
}
