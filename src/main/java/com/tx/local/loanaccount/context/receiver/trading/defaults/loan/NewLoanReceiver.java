/*
 * 描          述:  <描述>
 * 修  改   人:  Administrator
 * 修改时间:  2014年4月29日
 * <修改描述:>
 */
package com.tx.local.loanaccount.context.receiver.trading.defaults.loan;

import java.math.BigDecimal;
import java.util.Date;

import org.springframework.stereotype.Component;

import com.tx.component.command.context.CommandResponse;
import com.tx.local.loanaccount.context.request.trading.loan.NewLoanRequest;
import com.tx.local.loanaccount.handle.PaymentScheduleHandler;
import com.tx.local.loanaccount.model.LATradingRecord;
import com.tx.local.loanaccount.model.LoanAccount;

/**
 * 新贷的处理逻辑<br/>
 * 前置
 *     判断贷款单状态为非锁定状态
 *     创建贷款单账户的还款计划
 * 处理
 *     对贷款单账户收取费用（一次性手续费，管理费）ChargeRecord
 *         根据ChargeRecord修改PaymentSchedule中的应收
 *     根据请求中的，贷款金额，以及放款银行账户实例以及应收，生成对应的tradingRecord(本金，银行存款，一次性手续费，延期费)
 *         将生成的TradingRecord传入PaymentSchedule中修改实收
 * 后置
 *     修改贷款单状态<可不要，在生成贷款单账户时，就可以将AC的状态写入即可...???>
 *     修改贷款单中相关金额。。。（???）
 * 
 * @author  Administrator
 * @version  [版本号, 2014年4月29日]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
@Component("newLoanReceiver")
public class NewLoanReceiver extends AbstractLoanReceiver<NewLoanRequest> {
    
    /**
     * @param request
     * @param loanAccount
     * @return
     */
    @Override
    public LATradingRecord createTradingRecord(NewLoanRequest request, CommandResponse response,
            LoanAccount loanAccount, PaymentScheduleHandler handler) {
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
        
        //银行存款金额、超额还款金额
        tradingRecord.setBankAccountId(request.getBankAccountId());
        BigDecimal sum = BigDecimal.ZERO;
        for (BigDecimal amountTemp : request.getFeeItem2AmountMap().values()) {
            if (amountTemp == null || amountTemp.compareTo(BigDecimal.ZERO) <= 0) {
                continue;
            }
            sum = sum.add(amountTemp);
        }
        tradingRecord.setRepaySum(sum);
        tradingRecord.setSum(loanAccount.getLoanAmount());
        
        tradingRecord.setExpireDate(null);//到期日为空
        tradingRecord.setRepayDate(loanAccount.getEffectiveDate());//还款日期为贷款生效日
        
        tradingRecord.setRemark(request.getRemark());
        
        return tradingRecord;
    }
}
