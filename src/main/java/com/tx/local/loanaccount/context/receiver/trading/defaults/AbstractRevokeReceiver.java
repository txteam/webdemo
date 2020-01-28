/*
 * 描          述:  <描述>
 * 修  改   人:  Administrator
 * 修改时间:  2014年6月9日
 * <修改描述:>
 */
package com.tx.local.loanaccount.context.receiver.trading.defaults;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;

import com.tx.component.command.context.CommandResponse;
import com.tx.core.TxConstants;
import com.tx.local.loanaccount.LoanAccountConstants;
import com.tx.local.loanaccount.context.request.trading.AbstractRevokeTradingRequest;
import com.tx.local.loanaccount.handle.PaymentScheduleHandler;
import com.tx.local.loanaccount.helper.chargerecord.ChargeRecordEntryHelper;
import com.tx.local.loanaccount.helper.exemptrecord.ExemptRecordEntryHelper;
import com.tx.local.loanaccount.helper.paymentrecord.PaymentRecordEntryHelper;
import com.tx.local.loanaccount.model.ChargeRecord;
import com.tx.local.loanaccount.model.ChargeRecordEntry;
import com.tx.local.loanaccount.model.ExemptRecord;
import com.tx.local.loanaccount.model.ExemptRecordEntry;
import com.tx.local.loanaccount.model.LATradingRecord;
import com.tx.local.loanaccount.model.LATradingRecordEntry;
import com.tx.local.loanaccount.model.LoanAccount;
import com.tx.local.loanaccount.model.PaymentRecord;
import com.tx.local.loanaccount.model.PaymentRecordEntry;

/**
 * 撤销基础类<br/>
 * 
 * @author  Administrator
 * @version  [版本号, 2014年6月9日]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public abstract class AbstractRevokeReceiver<PR extends AbstractRevokeTradingRequest>
        extends AbstractDefaultTradingReceiver<PR> {
    
    /** 交易记录线程变量key  */
    protected static final String REVOKE_TRADINGRECORD_KEY = "REVOKE_TRADINGRECORD_KEY";
    
    /** 获取需要进行撤销的交易记录 */
    protected LATradingRecord getRevokedTradingRecord(PR request) {
        //将交易记录存入线程变量
        //CommandSessionContext session = CommandSessionContext.getSession();
        LATradingRecord tradingRecord = null;
        //        if (session != null && session.getAttribute(REVOKE_TRADINGRECORD_KEY) != null) {
        //            tradingRecord = (LATradingRecord) session.getAttribute(REVOKE_TRADINGRECORD_KEY);
        //        } else {
        //            String revokeTradingId = request.getRevokeTradingRecordId();
        //            //如果在线程变量中不存在 则取出放入
        //            tradingRecord = tradingRecordService.findTradingRecordById(revokeTradingId);
        //            AssertUtils.notNull(tradingRecord, "tradingRecord is null.");
        //            if (session != null) {
        //                session.setAttribute(REVOKE_TRADINGRECORD_KEY, tradingRecord);
        //            }
        //        }
        tradingRecord = request.getRevokeTradingRecord();
        
        return tradingRecord;
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
            LoanAccount loanAccount, PaymentScheduleHandler paymentScheduleHandler, LATradingRecord tradingRecord) {
        List<ChargeRecordEntry> resList = new ArrayList<>(TxConstants.INITIAL_CONLLECTION_SIZE);
        String getRevokeTradingRecordId = getRevokedTradingRecord(request).getId();
        
        List<ChargeRecordEntry> sourceChargeRecordEntryList = this.chargeRecordEntryService
                .queryListByTradingRecordId(getRevokeTradingRecordId);
        if (CollectionUtils.isEmpty(sourceChargeRecordEntryList)) {
            return resList;
        }
        
        //根据原还款记录分项构建新撤销的还款记录分项<br/>
        resList.addAll(ChargeRecordEntryHelper.buildRevokeEntryList(tradingRecord,
                paymentScheduleHandler,
                sourceChargeRecordEntryList));
        return resList;
    }
    
    /**
     * @param request
     * @param loanAccount
     * @param paymentScheduleMap
     * @param tradingRecord
     * @return
     */
    @Override
    public List<ExemptRecordEntry> createExemptRecordEntryList(PR request, CommandResponse response,
            LoanAccount loanAccount, PaymentScheduleHandler paymentScheduleHandler, LATradingRecord tradingRecord) {
        List<ExemptRecordEntry> resList = new ArrayList<>();
        String getRevokeTradingRecordId = getRevokedTradingRecord(request).getId();
        List<ExemptRecordEntry> sourceExemptRecordEntry = this.exemptRecordEntryService
                .queryListByTradingRecordId(getRevokeTradingRecordId);
        resList.addAll(ExemptRecordEntryHelper.buildRevokeExemptRecordEntry(tradingRecord,
                paymentScheduleHandler,
                sourceExemptRecordEntry));
        
        return resList;
    }
    
    /**
     * @param request
     * @param loanAccount
     * @param paymentScheduleMap
     * @param tradingRecord
     * @return
     */
    @Override
    public List<PaymentRecordEntry> createPaymentRecordEntryList(PR request, CommandResponse response,
            LoanAccount loanAccount, PaymentScheduleHandler paymentScheduleHandler, LATradingRecord tradingRecord) {
        List<PaymentRecordEntry> resList = new ArrayList<>(TxConstants.INITIAL_CONLLECTION_SIZE);
        String getRevokeTradingRecordId = getRevokedTradingRecord(request).getId();
        List<PaymentRecordEntry> sourcePaymentRecordEntryList = this.paymentRecordEntryService
                .queryListByTradingRecordId(getRevokeTradingRecordId);
        if (CollectionUtils.isEmpty(sourcePaymentRecordEntryList)) {
            return resList;
        }
        //根据原还款记录分项构建新撤销的还款记录分项<br/>
        resList.addAll(PaymentRecordEntryHelper.buildRevokePaymentRecordEntry(tradingRecord,
                paymentScheduleHandler,
                sourcePaymentRecordEntryList));
        return resList;
    }
    
    /**
     * @param request
     * @param loanAccount
     * @param paymentScheduleMap
     * @param tradingRecord
     * @param delayRecord
     * @param chargeRecords
     * @param paymentRecords
     * @return
     */
    @Override
    public List<LATradingRecordEntry> createTradingRecordEntryList(PR request, CommandResponse response,
            LoanAccount loanAccount, PaymentScheduleHandler paymentScheduleHandler, LATradingRecord tradingRecord,
            List<PaymentRecord> paymentRecords) {
        List<LATradingRecordEntry> resList = new ArrayList<>(TxConstants.INITIAL_CONLLECTION_SIZE);
        //        String getRevokeTradingRecordId = getRevokeTradingRecord(request).getId();
        //        List<TradingRecordEntry> sourceTradingRecordEntryList = this.tradingRecordEntryService.queryTradingRecordEntryListByTradingRecordId(getRevokeTradingRecordId);
        //        if (CollectionUtils.isEmpty(sourceTradingRecordEntryList)) {
        //            return resList;
        //        }
        //        
        //        //根据原还款记录分项构建新撤销的还款记录分项<br/>
        //        resList.addAll(this.tradingRecordEntryHelper.buildRevokeTradingRecordEntryListBySourceTradingRecordEntryList(loanAccount,
        //                tradingRecord,
        //                sourceTradingRecordEntryList));
        return resList;
    }
    
    /**
     * @param request
     * @param loanAccount
     * @param paymentScheduleMap
     * @param tradingRecord
     * @param delayRecord
     * @param chargeRecords
     * @param paymentRecords
     */
    @Override
    public void postHandle(PR request, CommandResponse response, LoanAccount loanAccount,
            PaymentScheduleHandler paymentScheduleHandler, LATradingRecord tradingRecord,
            List<ChargeRecord> chargeRecords, List<ExemptRecord> exemptRecords, List<PaymentRecord> paymentRecords) {
        super.postHandle(request,
                response,
                loanAccount,
                paymentScheduleHandler,
                tradingRecord,
                chargeRecords,
                exemptRecords,
                paymentRecords);
        LATradingRecord revokeTradingRecord = getRevokedTradingRecord(request);
        String revokedTradingRecordId = revokeTradingRecord.getId();//修改对应交易为已撤销
        response.setAttribute(LoanAccountConstants.RESPONSE_KEY_REVOKED_TRADINGRECORDID, revokedTradingRecordId);//被撤销的交易id
        response.setAttribute(LoanAccountConstants.RESPONSE_KEY_REVOKED_TRADINGRECORD, revokeTradingRecord);//返回当前交易
        
        String revokeReason = request.getRevokeReason();
        String revokeRemark = request.getRemark();
        revokeTradingRecord.setRevokeAble(false);
        revokeTradingRecord.setRevoked(true);
        revokeTradingRecord.setRevokeResean(revokeReason);
        revokeTradingRecord.setRemark(revokeRemark);
        revokeTradingRecord.setViewAble(request.isViewAbleOfRevokedTradingRecord());
        revokeTradingRecord.setRevokeOperatorId(request.getOperatorId());
        //将交易转变为撤销
        this.tradingRecordService.updateWhenRevoke(revokeTradingRecord);
    }
}
