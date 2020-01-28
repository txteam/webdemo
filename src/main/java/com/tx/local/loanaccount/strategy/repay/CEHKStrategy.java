/*
 * 描          述:  <描述>
 * 修  改   人:  Administrator
 * 修改时间:  2017年7月4日
 * <修改描述:>
 */
package com.tx.local.loanaccount.strategy.repay;

import java.util.List;

import com.tx.component.strategy.context.Strategy;
import com.tx.local.loanaccount.handle.PaymentScheduleHandler;
import com.tx.local.loanaccount.model.LATradingRecord;
import com.tx.local.loanaccount.model.LoanAccount;
import com.tx.local.loanaccount.model.OverRepayRecord;
import com.tx.local.loanaccount.model.RepayIntention;

/**
 * 超额还款策略<br/>
 * <功能详细描述>
 * 
 * @author  Administrator
 * @version  [版本号, 2017年7月4日]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public interface CEHKStrategy extends Strategy {
    
    /**
     * 构建超额还款记录<br/>
     * <功能详细描述>
     * @param loanAccount
     * @param handler
     * @param tradingRecord
     * @param repayIntention
     * @return [参数说明]
     * 
     * @return OverRepayRecord [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    public List<OverRepayRecord> buildOverRepayRecords(LoanAccount loanAccount,
            PaymentScheduleHandler handler, LATradingRecord tradingRecord,
            RepayIntention repayIntention);
}
