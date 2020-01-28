/*
 * 描          述:  <描述>
 * 修  改   人:  Administrator
 * 修改时间:  2017年7月4日
 * <修改描述:>
 */
package com.tx.local.loanaccount.strategy.repay.cehk;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Component;

import com.tx.core.exceptions.util.AssertUtils;
import com.tx.local.basicdata.model.FeeItemEnum;
import com.tx.local.loanaccount.LoanAccountConstants;
import com.tx.local.loanaccount.handle.PaymentScheduleHandler;
import com.tx.local.loanaccount.model.LATradingRecord;
import com.tx.local.loanaccount.model.LoanAccount;
import com.tx.local.loanaccount.model.OverRepayRecord;
import com.tx.local.loanaccount.model.RepayIntention;
import com.tx.local.loanaccount.strategy.repay.CEHKStrategy;

/**
 * 默认的超额还款策略<br/>
 * <功能详细描述>
 * 
 * @author  Administrator
 * @version  [版本号, 2017年7月4日]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
@Component("defaultCEHKStrategy")
public class DefaultCEHKStrategy implements CEHKStrategy {
    
    /**
     * @param loanAccount
     * @param handler
     * @param tradingRecord
     * @param overRepayAmount
     * @return
     */
    @Override
    public List<OverRepayRecord> buildOverRepayRecords(LoanAccount loanAccount,
            PaymentScheduleHandler handler, LATradingRecord tradingRecord,
            RepayIntention repayIntention) {
        AssertUtils.notNull(loanAccount, "loanAccount is null.");
        AssertUtils.notEmpty(loanAccount.getId(), "loanAccount.id is empty.");
        AssertUtils.notNull(handler, "handler is null.");
        
        AssertUtils.notNull(repayIntention, "repayIntention is null.");
        AssertUtils.notNull(repayIntention.getAmount(),
                "repayIntention.amount is null.");
        AssertUtils.isTrue(
                repayIntention.getAmount().compareTo(BigDecimal.ZERO) >= 0,
                "repayIntention.amount >= 0.");
        
        List<OverRepayRecord> resList = new ArrayList<>();
        
        OverRepayRecord orr = new OverRepayRecord();
        Date now = new Date();
        String loanAccountId = loanAccount.getId();
        orr.setCreateDate(now);
        orr.setLoanAccountId(loanAccountId);
        orr.setPeriod(LoanAccountConstants.PERIOD_NA);
        orr.setFeeItem(FeeItemEnum.DK_CEHK);
        orr.setAmount(repayIntention.getAmount());
        orr.setReceived(true);
        orr.setReceiveDate(now);
        orr.setRevoked(false);
        orr.setRevokeDate(null);
        orr.setTradingRecordId(
                tradingRecord == null ? null : tradingRecord.getId());
        resList.add(orr);
        
        return resList;
    }
    
}
