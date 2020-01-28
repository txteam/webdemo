/*
 * 描          述:  <描述>
 * 修  改   人:  Administrator
 * 修改时间:  2017年6月25日
 * <修改描述:>
 */
package com.tx.local.loanaccount.model.settledetail;

import java.util.Date;

import com.tx.local.loanaccount.handle.PaymentScheduleHandler;
import com.tx.local.loanaccount.model.LoanAccount;

/**
 * 贷款单贷款账户结清详情<br/>
 * <功能详细描述>
 * 
 * @author  Administrator
 * @version  [版本号, 2017年6月25日]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public class DefaultLoanBillLoanAccountSettleDetail extends BaseLoanAccountSettleDetail {
    
    /** <默认构造函数> */
    public DefaultLoanBillLoanAccountSettleDetail() {
        super();
    }
    
    /** <默认构造函数> */
    public DefaultLoanBillLoanAccountSettleDetail(LoanAccount loanAccount, PaymentScheduleHandler handler,
            Date repayDate) {
        super(loanAccount, handler, repayDate);
    }
    
}
