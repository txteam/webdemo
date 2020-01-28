/*
 * 描 述: <描述> 修 改 人: Administrator 修改时间: 2014年5月21日 <修改描述:>
 */
package com.tx.local.loanaccount.context.receiver.build.loanbill;

import java.util.List;

import javax.annotation.Resource;

import com.tx.component.command.context.CommandResponse;
import com.tx.local.loanaccount.context.receiver.build.AbstractLoanAccountBuildReceiver;
import com.tx.local.loanaccount.context.request.build.LoanBillLoanAccountBuildRequest;
import com.tx.local.loanaccount.model.LoanAccount;
import com.tx.local.loanaccount.model.LoanAccount2LoanBill;
import com.tx.local.loanaccount.model.LoanAccountFeeItem;
import com.tx.local.loanaccount.model.PaymentSchedule;
import com.tx.local.loanaccount.model.PaymentScheduleEntry;
import com.tx.local.loanaccount.service.LoanAccount2LoanBillService;

/**
 * 构建贷款账户请求处理器<br/>
 * 
 * @author Administrator
 * @version [版本号, 2014年5月21日]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */

public abstract class AbstractLoanBillLoanAccountBuildReceiver<BT extends LoanBillLoanAccountBuildRequest>
        extends AbstractLoanAccountBuildReceiver<BT> {
    
    @Resource(name = "loanAccount2LoanBillService")
    protected LoanAccount2LoanBillService loanAccount2LoanBillService;
    
    /**
     * @param request
     * @param response
     * @param loanAccount
     * @param feeCfgItems
     * @param paymentSchedules
     */
    @Override
    public void postHandle(BT request, CommandResponse response,
            LoanAccount loanAccount, List<LoanAccountFeeItem> feeCfgItems,
            List<PaymentSchedule> paymentScheduleList,
            List<PaymentScheduleEntry> paymentScheduleEntryList) {
        super.postHandle(request,
                response,
                loanAccount,
                feeCfgItems,
                paymentScheduleList,
                paymentScheduleEntryList);
        
        //LoanAccount2LoanBill 持久化
        LoanAccount2LoanBill la2lb = new LoanAccount2LoanBill();
        la2lb.setLoanAccount(loanAccount);
        la2lb.setLoanType(request.getLoanType());
        la2lb.setLoanBillId(request.getLoanBillId());
        la2lb.setLoanBillNumber(request.getLoanBillNumber());
        la2lb.setLoanProductId(request.getLoanProductName());
        la2lb.setLoanProductName(request.getLoanProductName());
        this.loanAccount2LoanBillService.insert(la2lb);
    }
    
}
