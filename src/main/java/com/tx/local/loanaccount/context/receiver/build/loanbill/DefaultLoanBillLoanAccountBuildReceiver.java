/*
 * 描 述: <描述> 修 改 人: Administrator 修改时间: 2014年5月21日 <修改描述:>
 */
package com.tx.local.loanaccount.context.receiver.build.loanbill;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.tx.local.loanaccount.context.request.build.LoanBillLoanAccountBuildRequest;
import com.tx.local.loanaccount.service.LoanAccount2LoanBillService;

/**
 * 构建贷款账户请求处理器<br/>
 * 
 * @author Administrator
 * @version [版本号, 2014年5月21日]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
@Component("defaultLoanBillLoanAccountBuildReceiver")
public class DefaultLoanBillLoanAccountBuildReceiver extends
        AbstractLoanBillLoanAccountBuildReceiver<LoanBillLoanAccountBuildRequest> {
    
    @Resource(name = "loanAccount2LoanBillService")
    private LoanAccount2LoanBillService loanAccount2LoanBillService;
    
    /**
     * @param request
     * @return
     */
    @Override
    protected boolean isMatch(LoanBillLoanAccountBuildRequest request) {
        return true;
    }
    
}
