/*
 * 描          述:  <描述>
 * 修  改   人:  Administrator
 * 修改时间:  2017年6月19日
 * <修改描述:>
 */
package com.tx.local.loanaccount.strategy.revoke.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.transaction.Transactional;

import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Component;

import com.tx.component.command.context.CommandContext;
import com.tx.core.exceptions.SILException;
import com.tx.core.exceptions.util.AssertUtils;
import com.tx.core.util.DateUtils;
import com.tx.core.util.MessageUtils;
import com.tx.local.loanaccount.context.request.trading.revokeexempt.RevokeExemptRequest;
import com.tx.local.loanaccount.context.request.trading.revokeloan.RevokeNewLoanRequest;
import com.tx.local.loanaccount.context.request.trading.revokerepay.RevokeOverRepayRequest;
import com.tx.local.loanaccount.context.request.trading.revokerepay.RevokeRepayRequest;
import com.tx.local.loanaccount.context.request.trading.revokesettle.RevokeSettleRequest;
import com.tx.local.loanaccount.helper.tradingrecord.LATradingRecordHelper;
import com.tx.local.loanaccount.model.LATradingCategoryEnum;
import com.tx.local.loanaccount.model.LATradingRecord;
import com.tx.local.loanaccount.model.LoanAccount;
import com.tx.local.loanaccount.model.RequestSourceTypeEnum;
import com.tx.local.loanaccount.model.RevokeTypeEnum;
import com.tx.local.loanaccount.service.LATradingRecordService;
import com.tx.local.loanaccount.strategy.revoke.RevokeStrategy;

/**
 * 默认的撤销策略实现<br/>
 * <功能详细描述>
 * 
 * @author  Administrator
 * @version  [版本号, 2017年6月19日]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
@Component("defaultRevokeStrategy")
public class DefaultRevokeStrategy implements RevokeStrategy {
    
    /** 交易记录业务层 */
    @Resource(name = "laTradingRecordService")
    private LATradingRecordService laTradingRecordService;
    
    /**
     * @param loanAccountId
     * @return
     */
    @Override
    public List<LATradingRecord> getRevokeAbleTradingRecords(
            LoanAccount loanAccount) {
        AssertUtils.notNull(loanAccount, "loanAccount is null.");
        AssertUtils.notEmpty(loanAccount.getId(), "loanAccount.id is empty.");
        
        String loanAccountId = loanAccount.getId();
        //查询贷款账户
        LATradingCategoryEnum[] categories = { LATradingCategoryEnum.LOAN,
                LATradingCategoryEnum.REPAY, LATradingCategoryEnum.SUSPEND,
                LATradingCategoryEnum.SETTLE, LATradingCategoryEnum.CHARGE,
                LATradingCategoryEnum.EXEMPT, LATradingCategoryEnum.REBUILD };
        
        //查询交易列表
        Map<String, Object> params = new HashMap<>();
        params.put("categories", categories);
        List<LATradingRecord> tradingRecords = this.laTradingRecordService
                .queryListByLoanAccountId(loanAccountId, true, true, params);
        
        List<LATradingRecord> revokeAbleTradingRecords = new ArrayList<>();
        if (CollectionUtils.isEmpty(tradingRecords)) {
            return revokeAbleTradingRecords;
        }
        
        Collections.sort(tradingRecords, LATradingRecordHelper.ID_COMPARATOR);
        LATradingRecord lastTradingRecord = tradingRecords
                .get(tradingRecords.size() - 1);
        if (isRevokeAble(loanAccount, lastTradingRecord)) {
            revokeAbleTradingRecords.add(lastTradingRecord);
        }
        
        return revokeAbleTradingRecords;
    }
    
    /**
     * @param loanAccountId
     * @return
     */
    @Override
    public List<LATradingRecord> getRevokeAbleRepayTradingRecords(
            LoanAccount loanAccount) {
        AssertUtils.notNull(loanAccount, "loanAccount is null.");
        AssertUtils.notEmpty(loanAccount.getId(), "loanAccount.id is empty.");
        
        String loanAccountId = loanAccount.getId();
        
        //查询贷款账户
        LATradingCategoryEnum[] categories = { LATradingCategoryEnum.LOAN,
                LATradingCategoryEnum.REPAY, LATradingCategoryEnum.SUSPEND,
                LATradingCategoryEnum.SETTLE, LATradingCategoryEnum.REBUILD };
        
        //查询交易列表
        Map<String, Object> params = new HashMap<>();
        params.put("categories", categories);
        List<LATradingRecord> tradingRecords = this.laTradingRecordService
                .queryListByLoanAccountId(loanAccountId, true, true, params);
        
        List<LATradingRecord> revokeAbleTradingRecords = new ArrayList<>();
        if (CollectionUtils.isEmpty(tradingRecords)) {
            return revokeAbleTradingRecords;
        }
        Collections.sort(tradingRecords, LATradingRecordHelper.ID_COMPARATOR);
        LATradingRecord lastTradingRecord = tradingRecords
                .get(tradingRecords.size() - 1);
        if (isRevokeAble(loanAccount, lastTradingRecord)) {
            revokeAbleTradingRecords.add(lastTradingRecord);
        }
        
        return revokeAbleTradingRecords;
    }
    
    /**
     * @param tradingRecord
     * @return
     */
    @Override
    public boolean isRevokeAble(LoanAccount loanAccount,
            LATradingRecord tradingRecord) {
        AssertUtils.notNull(loanAccount, "loanAccount is null.");
        AssertUtils.notNull(tradingRecord, "tradingRecord is null.");
        AssertUtils.notNull(tradingRecord.getType(),
                "tradingRecord.type is null.");
        
        AssertUtils.notEmpty(loanAccount.getId(), "loanAccount.id is empty.");
        AssertUtils.notEmpty(tradingRecord.getId(),
                "tradingRecord.id is empty.");
        
        String loanAccountId = loanAccount.getId();
        Date now = new Date();
        switch (tradingRecord.getCategory()) {
            case LOAN:
                //账户未到达首次还款日，未逾期
                if (loanAccount.isOverdue() || DateUtils.compareByDay(now,
                        loanAccount.getFirstRepayDate()) >= 0) {
                    return false;
                }
                //账户有且仅有一条交易记录
                List<LATradingRecord> tradingRecords = this.laTradingRecordService
                        .queryListByLoanAccountId(loanAccountId,
                                null,
                                null,
                                null);
                if (tradingRecords.size() > 1) {
                    return false;
                }
                break;
            default:
                if (tradingRecord.isRevokeAble()) {
                    return true;
                } else {
                    return false;
                }
        }
        return true;
    }
    
    /**
     * @param tradingRecord
     * @return
     */
    @Override
    public boolean isRefundAble(LoanAccount loanAccount,
            LATradingRecord tradingRecord) {
        AssertUtils.notNull(tradingRecord, "tradingRecord is null.");
        if (!isRevokeAble(loanAccount, tradingRecord)) {
            return false;
        }
        
        return false;
    }
    
    /**
     * @param tradingRecord
     * @return
     */
    @Override
    public boolean isRevokeToWaitAccountAble(LoanAccount loanAccount,
            LATradingRecord tradingRecord) {
        AssertUtils.notNull(tradingRecord, "tradingRecord is null.");
        
        if (LATradingCategoryEnum.LOAN.equals(tradingRecord.getCategory())
                || !tradingRecord.isReceived()) {
            return false;
        } else {
            LATradingCategoryEnum category = tradingRecord.getCategory();
            if (LATradingCategoryEnum.REPAY.equals(category)
                    || LATradingCategoryEnum.SETTLE.equals(category)) {
                return true;
            } else {
                return false;
            }
        }
    }
    
    /**
     * @param revokeType
     * @param loanAccount
     * @param revokeTradingRecordId
     * @param revokeResean
     * @return
     */
    @Override
    @Transactional
    public boolean revoke(RevokeTypeEnum revokeType, LoanAccount loanAccount,
            String revokeTradingRecordId, String revokeReason) {
        AssertUtils.notNull(revokeType, "revokeType is null.");
        AssertUtils.notNull(loanAccount, "loanAccount is null.");
        AssertUtils.notEmpty(loanAccount.getId(), "loanAccount.id is null.");
        AssertUtils.notEmpty(revokeTradingRecordId,
                "revokeTradingRecordId is null.");
        
        LATradingRecord revokeTradingRecord = this.laTradingRecordService
                .findById(revokeTradingRecordId);
        AssertUtils.notNull(revokeTradingRecord,
                "revokeTradingRecord is not exist.revokeTradingRecord:{}",
                revokeTradingRecordId);
        AssertUtils.isTrue(
                revokeTradingRecord.isRevokeAble()
                        && !revokeTradingRecord.isRevoked(),
                "交易应为可撤销并且尚未被撤销.revokeTradingRecord:{}",
                revokeTradingRecordId);
        AssertUtils.isTrue(
                loanAccount.getId()
                        .equals(revokeTradingRecord.getLoanAccountId()),
                "被撤销交易贷款账户id应等于传入的贷款账户id",
                revokeTradingRecordId);
        AssertUtils.notNull(
                revokeTradingRecord.getType().getRevokeTradingRecordType(),
                "revokeTradingRecord.type.revokeTradingRecordType is null.");
        
        switch (revokeTradingRecord.getType().getRevokeTradingRecordType()) {
            case REVOKE_NEW_LOAN:
                RevokeNewLoanRequest rnlRequest = new RevokeNewLoanRequest(
                        loanAccount.getId(), revokeTradingRecordId,
                        revokeReason);
                CommandContext.getContext().post(rnlRequest);
                break;
            case REVOKE_CASH_REPAY:
            case REVOKE_TRANSFER_REPAY:
                RevokeRepayRequest rrRequest = new RevokeRepayRequest(
                        loanAccount.getId(), revokeTradingRecordId,
                        revokeReason, RequestSourceTypeEnum.OPER_REQUEST);
                CommandContext.getContext().post(rrRequest);
                break;
            case REVOKE_OVER_REPAY:
                RevokeOverRepayRequest rorRequest = new RevokeOverRepayRequest(
                        loanAccount.getId(), revokeTradingRecordId,
                        revokeReason);
                CommandContext.getContext().post(rorRequest);
                break;
            case REVOKE_EXEMPT:
                RevokeExemptRequest reRequest = new RevokeExemptRequest(
                        loanAccount.getId(), revokeTradingRecordId,
                        revokeReason);
                CommandContext.getContext().post(reRequest);
                break;
            case REVOKE_EARLY_SETTLE:
            case REVOKE_ONCE_SETTLE:
                RevokeSettleRequest rsRequest = new RevokeSettleRequest(
                        loanAccount.getId(), revokeTradingRecordId,
                        revokeReason, RequestSourceTypeEnum.OPER_REQUEST);
                CommandContext.getContext().post(rsRequest);
                break;
            default:
                throw new SILException(
                        MessageUtils.format("尚不支持的撤销交易类型.type:{}",
                                new Object[] { revokeTradingRecord.getType()
                                        .getRevokeTradingRecordType() }));
        }
        return true;
    }
    
    /**
     * @param revokeType
     * @param loanAccount
     * @param revokeTradingRecordId
     * @param revokeResean
     * @return
     */
    @Override
    @Transactional
    public boolean revokeToWaitAccount(RevokeTypeEnum revokeType,
            LoanAccount loanAccount, String revokeTradingRecordId,
            String revokeResean) {
        // TODO Auto-generated method stub
        return false;
    }
    
    /**
     * @param revokeType
     * @param loanAccount
     * @param revokeTradingRecordId
     * @param revokeResean
     * @param bankAccountId
     * @return
     */
    @Override
    @Transactional
    public boolean refund(RevokeTypeEnum revokeType, LoanAccount loanAccount,
            String revokeTradingRecordId, String revokeResean,
            String bankAccountId) {
        // TODO Auto-generated method stub
        return false;
    }
}
