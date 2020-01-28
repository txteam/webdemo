/*
 * 描          述:  <描述>
 * 修  改   人:  Administrator
 * 修改时间:  2017年6月25日
 * <修改描述:>
 */
package com.tx.local.loanaccount.strategy.repay.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.tx.component.strategy.context.StrategyContext;
import com.tx.local.basicdata.model.FeeItemCfg;
import com.tx.local.basicdata.model.FeeItemEnum;
import com.tx.local.loanaccount.handle.PaymentScheduleHandler;
import com.tx.local.loanaccount.model.ChargeRecordEntry;
import com.tx.local.loanaccount.model.LATradingRecord;
import com.tx.local.loanaccount.model.LoanAccount;
import com.tx.local.loanaccount.model.LoanAccountFeeItem;
import com.tx.local.loanaccount.model.OverRepayRecord;
import com.tx.local.loanaccount.model.RepayIntention;
import com.tx.local.loanaccount.strategy.repay.CEHKStrategy;

/**
 * 默认的还款策略实现<br/>
 * <功能详细描述>
 * 
 * @author  Administrator
 * @version  [版本号, 2017年6月25日]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
@Component("defaultRepayStrategy")
public class DefaultRepayStrategy extends AbstractRepayStrategy {
    
    /**
     * 构建超额还款记录<br/>
     * @param loanAccount
     * @param handler
     * @param tradingRecord
     * @param repayIntention
     * @return
     */
    @Override
    public List<OverRepayRecord> buildOverRepayRecords(LoanAccount loanAccount,
            PaymentScheduleHandler handler, LATradingRecord tradingRecord,
            RepayIntention repayIntention) {
        CEHKStrategy cehkStrategy = StrategyContext.getContext()
                .getStrategy(CEHKStrategy.class, "defaultCEHKStrategy");
        List<OverRepayRecord> orrList = cehkStrategy.buildOverRepayRecords(
                loanAccount, handler, tradingRecord, repayIntention);
        return orrList;
    }
    
    /**
     * @param loanAccount
     * @param feeItemMap
     * @param feeItemCfgMap
     * @param handler
     * @param tradingRecord
     * @param repayDate
     * @return
     */
    @Override
    public List<ChargeRecordEntry> buildRepayChargeEntryList(
            LoanAccount loanAccount,
            Map<FeeItemEnum, LoanAccountFeeItem> feeItemMap,
            Map<FeeItemEnum, FeeItemCfg> feeItemCfgMap,
            PaymentScheduleHandler handler, LATradingRecord tradingRecord,
            Date repayDate) {
        return null;
    }
    
    /**
     * @param loanAccount
     * @param handler
     * @param chargeRecordEntryList
     * @return
     */
    @Override
    public List<ChargeRecordEntry> resetChargeRecordEntryList(
            LoanAccount loanAccount, PaymentScheduleHandler handler,
            List<ChargeRecordEntry> chargeRecordEntryList) {
        // TODO Auto-generated method stub
        return null;
    }
}
