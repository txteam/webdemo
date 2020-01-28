/*
 * 描          述:  <描述>
 * 修  改   人:  Administrator
 * 修改时间:  2017年6月23日
 * <修改描述:>
 */
package com.tx.local.loanaccount.strategy.detail.impl;

import java.util.Date;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.tx.local.loanaccount.handle.PaymentScheduleHandler;
import com.tx.local.loanaccount.model.LoanAccount;
import com.tx.local.loanaccount.model.LoanAccount2LoanBill;
import com.tx.local.loanaccount.model.LoanAccountDetail;
import com.tx.local.loanaccount.model.detail.DefaultLoanBillLoanAccountDetail;
import com.tx.local.loanaccount.service.LoanAccount2LoanBillService;
import com.tx.local.loanaccount.strategy.detail.DetailStrategy;

/**
  * 撤销业务层<br/>
  * <功能详细描述>
  * 
  * @author  Administrator
  * @version  [版本号, 2017年6月23日]
  * @see  [相关类/方法]
  * @since  [产品/模块版本]
  */
@Component("defaultLoanBillDetailStrategy")
public class DefaultLoanBillDetailStrategy implements DetailStrategy {
    
    /** 贷款账户2LoanBill的业务层 */
    @Resource(name = "loanAccount2LoanBillService")
    private LoanAccount2LoanBillService loanAccount2LoanBillService;
    
    /**
     * @param loanAccount
     * @param handler
     * @param repayDate
     * @return
     */
    @Override
    public LoanAccountDetail buildLoanAccountDetail(LoanAccount loanAccount,
            PaymentScheduleHandler handler, Date repayDate) {
        String loanAccountId = loanAccount.getId();
        LoanAccount2LoanBill la2lb = this.loanAccount2LoanBillService
                .findByLoanAccountId(loanAccountId);
        LoanAccountDetail lad = new DefaultLoanBillLoanAccountDetail(la2lb,
                loanAccount, handler, repayDate);
        return lad;
    }
}
