/*
 * 描          述:  <描述>
 * 修  改   人:  lenovo
 * 修改时间:  2014年7月22日
 * <修改描述:>
 */
package com.tx.local.loanaccount.context.receiver.trading.defaults.revokeloan;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;

import com.tx.component.command.context.CommandResponse;
import com.tx.core.TxConstants;
import com.tx.core.exceptions.util.AssertUtils;
import com.tx.core.util.DateUtils;
import com.tx.local.loanaccount.context.receiver.trading.defaults.AbstractRevokeReceiver;
import com.tx.local.loanaccount.context.request.trading.AbstractRevokeTradingRequest;
import com.tx.local.loanaccount.handle.PaymentScheduleHandler;
import com.tx.local.loanaccount.helper.chargerecord.ChargeRecordEntryHelper;
import com.tx.local.loanaccount.model.AccountStatusEnum;
import com.tx.local.loanaccount.model.ChargeRecordEntry;
import com.tx.local.loanaccount.model.LATradingRecord;
import com.tx.local.loanaccount.model.LoanAccount;
import com.tx.local.loanaccount.model.PaymentScheduleEntry;

/**
 * 基础撤销还款基类<br/>
 * <功能详细描述>
 * 
 * @author  lenovo
 * @version  [版本号, 2014年7月22日]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public abstract class AbstractRevokeLoanReceiver<PR extends AbstractRevokeTradingRequest>
        extends AbstractRevokeReceiver<PR> {
    
    /**
     * @param request
     * @param loanAccount
     * @param paymentScheduleMap
     */
    @Override
    public void validateRequest(PR request, CommandResponse response, LoanAccount loanAccount,
            PaymentScheduleHandler handler) {
        //贷款账户状态为AC
        AssertUtils.isTrue(AccountStatusEnum.AC.equals(loanAccount.getAccountStatus()), "账户状态不为AC.");
        
        AssertUtils.isTrue(!loanAccount.isRevoked(), "账户已被撤销.");
        AssertUtils.isTrue(!loanAccount.isClosed(), "账户已经关闭");
        AssertUtils.isTrue(!loanAccount.isOverdue(), "账户已逾期.");
        AssertUtils.isTrue(!loanAccount.isLocked(), "账户已锁定.");
        
        //还未到第一次还款日账户不逾期
        Date nowDate = new Date();
        AssertUtils.isTrue(DateUtils.compareByDay(nowDate, loanAccount.getFirstRepayDate()) < 0, "账户已经超过首次还款日");// 不是同一天
        
        //交易有且仅有一条
        List<LATradingRecord> tradingRecords = this.tradingRecordService.queryListByLoanAccountId(loanAccount.getId(),
                null,
                null,
                null);
        //交易有且只有一笔，并且为发放贷款 // 交易记录只有一笔
        AssertUtils.isTrue(CollectionUtils.isNotEmpty(tradingRecords) // 交易记录不为空
                && tradingRecords.size() == 1, "账户不是有且仅有一条为发放贷款的交易记录.");
    }
    
    /**
    * @param request
    * @param loanAccount
    * @param paymentScheduleMap
    * @param tradingRecord
    * @return
    */
    @Override
    public List<ChargeRecordEntry> createChargeRecordEntryList(PR request, CommandResponse response,
            LoanAccount loanAccount, PaymentScheduleHandler handler, LATradingRecord tradingRecord) {
        List<ChargeRecordEntry> resList = new ArrayList<>(TxConstants.INITIAL_CONLLECTION_SIZE);
        for (PaymentScheduleEntry entry : handler.getPaymentScheduleEntryMap().valueList()) {
            //生成所有应收冲0的记录
            resList.add(ChargeRecordEntryHelper.buildBalanceEntryList(tradingRecord, entry));
        }
        return resList;
    }
}
