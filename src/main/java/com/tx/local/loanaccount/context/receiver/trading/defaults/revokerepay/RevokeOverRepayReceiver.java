/*
 * 描          述:  <描述>
 * 修  改   人:  Administrator
 * 修改时间:  2014年6月27日
 * <修改描述:>
 */
package com.tx.local.loanaccount.context.receiver.trading.defaults.revokerepay;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.tx.component.command.context.CommandResponse;
import com.tx.core.exceptions.util.AssertUtils;
import com.tx.local.loanaccount.context.request.trading.revokerepay.RevokeOverRepayRequest;
import com.tx.local.loanaccount.handle.PaymentScheduleHandler;
import com.tx.local.loanaccount.model.ChargeRecord;
import com.tx.local.loanaccount.model.ExemptRecord;
import com.tx.local.loanaccount.model.LATradingRecord;
import com.tx.local.loanaccount.model.LoanAccount;
import com.tx.local.loanaccount.model.OverRepayRecord;
import com.tx.local.loanaccount.model.PaymentRecord;
import com.tx.local.loanaccount.service.OverRepayRecordService;

/**
 * 撤销超额还款实现<br/>
 * <功能详细描述>
 * 
 * @author  Administrator
 * @version  [版本号, 2014年6月27日]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
@Component("revokeOverRepayReceiver")
public class RevokeOverRepayReceiver extends AbstractRevokeRepayReceiver<RevokeOverRepayRequest> {
    
    /** 超额还款记录业务层 */
    @Resource(name = "overRepayRecordService")
    private OverRepayRecordService overRepayRecordService;
    
    /**
     * @param request
     * @param loanAccount
     * @param paymentScheduleMap
     */
    @Override
    public void validateRequest(RevokeOverRepayRequest request, CommandResponse response, LoanAccount loanAccount,
            PaymentScheduleHandler handler) {
        super.validateRequest(request, response, loanAccount, handler);
    }
    
    /**
     * 创建交易记录<br/>
     * @param request
     * @param response
     * @param loanAccount
     * @param handler
     * @return
     */
    @Override
    public LATradingRecord createTradingRecord(RevokeOverRepayRequest request, CommandResponse response,
            LoanAccount loanAccount, PaymentScheduleHandler handler) {
        LATradingRecord revokedTradingRecord = getRevokedTradingRecord(request);
        
        Date now = new Date();
        LATradingRecord tradingRecord = new LATradingRecord();
        
        //账户交易是否到账
        tradingRecord.setReceived(revokedTradingRecord.isReceived());
        tradingRecord.setReceiveDate(now);
        
        //撤销提前结清计费交易可见，不可被撤销
        tradingRecord.setViewAble(true);
        tradingRecord.setRevokeAble(false);
        
        //设置交易的关联交易
        tradingRecord.setRelatedTradingRecordId(request.getRevokeTradingRecordId());//关联交易
        
        //银行账户id、交易金额、还款金额
        tradingRecord.setBankAccountId(revokedTradingRecord.getBankAccountId());
        tradingRecord.setSum(revokedTradingRecord.getSum().negate());
        tradingRecord.setRepaySum(revokedTradingRecord.getRepaySum().negate());
        
        tradingRecord.setExpireDate(revokedTradingRecord.getExpireDate());//到期日为空
        tradingRecord.setRepayDate(revokedTradingRecord.getRepayDate());//还款日期为贷款生效日
        
        tradingRecord.setRevokeResean(request.getRevokeReason());
        tradingRecord.setRemark(request.getRemark());
        
        //FIXME: 委外分派记录id,是否委外还款
        //tradingRecord.setOutsourceAssignRecordId();
        //tradingRecord.setOutsourceRepay();
        
        return tradingRecord;
    }
    
    /**
     * @param request
     * @param response
     * @param loanAccount
     * @param handler
     * @param tradingRecord
     * @param chargeRecords
     * @param exemptRecords
     * @param paymentRecords
     */
    @Override
    public void postHandle(RevokeOverRepayRequest request, CommandResponse response, LoanAccount loanAccount,
            PaymentScheduleHandler handler, LATradingRecord tradingRecord, List<ChargeRecord> chargeRecords,
            List<ExemptRecord> exemptRecords, List<PaymentRecord> paymentRecords) {
        super.postHandle(request,
                response,
                loanAccount,
                handler,
                tradingRecord,
                chargeRecords,
                exemptRecords,
                paymentRecords);
        //获取被撤销的交易
        LATradingRecord revokeTradingRecord = getRevokedTradingRecord(request);
        String revokeTradingRecordId = request.getRevokeTradingRecordId();
        
        //被撤销的交易金额
        BigDecimal amount = revokeTradingRecord.getSum();
        BigDecimal sourceAmount = loanAccount.getOverRepayAmount();
        BigDecimal targetAmount = sourceAmount.subtract(amount);
        AssertUtils.isTrue(amount.compareTo(BigDecimal.ZERO) > 0, "超额还款金额应大于0");
        AssertUtils.isTrue(targetAmount.compareTo(BigDecimal.ZERO) >= 0, "超额还款金额应大于或等于0");
        loanAccount.setOverRepayAmount(targetAmount);//修改超额还款金额
        
        Date now = new Date();
        List<OverRepayRecord> orrList = this.overRepayRecordService
                .queryListByTradingRecordId(revokeTradingRecordId, null);
        AssertUtils.notEmpty(orrList, "orrList is empty.");
        for(OverRepayRecord orrTemp : orrList){
            orrTemp.setRevoked(true);
            orrTemp.setRevokeDate(now);
            orrTemp.setRevokeTradingRecordId(tradingRecord.getId());
        }
        this.overRepayRecordService.batchInsertToHis(orrList);
        this.overRepayRecordService.deleteByTradingRecordId(revokeTradingRecordId);
    }
}
