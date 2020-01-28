package com.tx.local.loanaccount.context.receiver.trading.defaults.repay;

import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Component;

import com.tx.component.command.context.CommandResponse;
import com.tx.local.loanaccount.context.request.trading.repay.RepayRequest;
import com.tx.local.loanaccount.handle.PaymentScheduleHandler;
import com.tx.local.loanaccount.model.ChargeRecordEntry;
import com.tx.local.loanaccount.model.LATradingRecord;
import com.tx.local.loanaccount.model.LoanAccount;
import com.tx.local.loanaccount.strategy.loanaccount.LoanAccountStrategy;
import com.tx.local.loanaccount.strategy.loanaccount.LoanAccountStrategyHelper;

/**
  * 现金还款/已到账自动转账还款<br/>
  * <功能详细描述>
  * 
  * @author  Administrator
  * @version  [版本号, 2014年6月7日]
  * @see  [相关类/方法]
  * @since  [产品/模块版本]
 */
@Component("repayReceiver")
public class RepayReceiver extends AbstractRepayReceiver<RepayRequest> {
    
    /**
     * @param request
     * @param response
     * @param loanAccount
     * @param paymentScheduleHandler
     * @return
     */
    @Override
    public LATradingRecord createTradingRecord(RepayRequest request,
            CommandResponse response, LoanAccount loanAccount,
            PaymentScheduleHandler handler) {
        Date now = new Date();
        LATradingRecord tradingRecord = new LATradingRecord();
        
        //账户交易是否到账
        tradingRecord.setReceived(true);
        tradingRecord.setReceiveDate(now);
        
        //设置是否可见，是否可撤销
        tradingRecord.setViewAble(true);
        tradingRecord.setRevokeAble(true);
        
        //设置交易的关联交易
        tradingRecord.setRelatedTradingRecordId(null);
        
        //获取还款分配器
//        RepayAllocator repayAllocator = getRepayAllocator(loanAccount,
//                handler,
//                request.getRepayIntention(),
//                response);
        
        //银行存款金额、超额还款金额
        tradingRecord.setBankAccountId(request.getRepayIntention()
                .getBankAccountId());
        tradingRecord.setSum(request.getRepayIntention().getAmount());
        tradingRecord.setRepaySum(request.getRepayIntention().getAmount());
        
        tradingRecord.setExpireDate(loanAccount.getCurrentPeriodExpireDate());//到期日为空
        tradingRecord.setRepayDate(request.getRepayIntention().getRepayDate());//还款日期为贷款生效日
        
        //FIXME:是否外包期间还款
        //tradingRecord.setOutsourceAssignRecordId(request。 );
        //tradingRecord.setOutsourceRepay(outsourceRepay);
        
        tradingRecord.setRemark(request.getRemark());
        
        return tradingRecord;
    }
    
    @Override
    public List<ChargeRecordEntry> createChargeRecordEntryList(RepayRequest request, CommandResponse response,
            LoanAccount loanAccount, PaymentScheduleHandler handler, LATradingRecord tradingRecord) {
        //贷款账户处理策略
        LoanAccountStrategy strategy = LoanAccountStrategyHelper.getStrategy(loanAccount);
        //计费记录分项
        List<ChargeRecordEntry> creList = strategy.buildRepayChargeEntryList(loanAccount,
                handler.getFeeItemMap(),
                loanAccount.getFeeItemCfgMap(),
                handler,
                tradingRecord,
                request.getRepayDate());
        return creList;
    }

    /**
     * 
     * @param request
     * @param response
     * @param loanAccount
     * @param handler
     * @param chargeRecordEntryList
     * @return
     */
    @Override
    public List<ChargeRecordEntry> resetChargeRecordEntryList(RepayRequest request, CommandResponse response,
            LoanAccount loanAccount, PaymentScheduleHandler handler, List<ChargeRecordEntry> chargeRecordEntryList) {
        //贷款账户处理策略
        LoanAccountStrategy strategy = LoanAccountStrategyHelper.getStrategy(loanAccount);
        List<ChargeRecordEntry> creList = strategy.resetChargeRecordEntryList(loanAccount, handler, chargeRecordEntryList);
        return creList;
    }
    
}
