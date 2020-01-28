///*
// * 描          述:  <描述>
// * 修  改   人:  lenovo
// * 修改时间:  2014年7月22日
// * <修改描述:>
// */
//package com.tx.local.loanaccount.service.revoke;
//
//import java.util.ArrayList;
//import java.util.Arrays;
//import java.util.Collections;
//import java.util.Date;
//import java.util.List;
//
//import javax.annotation.Resource;
//
//import org.apache.commons.collections.CollectionUtils;
//import org.springframework.core.OrderComparator;
//import org.springframework.stereotype.Component;
//import org.springframework.transaction.annotation.Transactional;
//
//import com.tx.component.command.context.CommandContext;
//import com.tx.core.exceptions.util.AssertUtils;
//import com.tx.core.util.DateUtils;
//import com.tx.local.loanaccount.context.request.process.RevokeTradingDispatchRequest;
//import com.tx.local.loanaccount.context.request.trading.revoke.revokerepay.RefundRequest;
//import com.tx.local.loanaccount.model.LoanAccount;
//import com.tx.local.loanaccount.model.RequestSourceTypeEnum;
//import com.tx.local.loanaccount.model.LATradingCategoryEnum;
//import com.tx.local.loanaccount.model.LATradingRecord;
//import com.tx.local.loanaccount.model.LATradingRecordTypeEnum;
//import com.tx.local.loanaccount.service.LoanAccountService;
//import com.tx.local.loanaccount.service.RevokeService;
//import com.tx.local.loanaccount.service.LATradingRecordService;
//
///**
// * 退回|撤销业务支撑层实现逻辑<br/>
// * 
// * @author lenovo
// * @version [版本号, 2014年7月22日]
// * @see [相关类/方法]
// * @since [产品/模块版本]
// */
//@Component("p2pRevokeService")
//public class P2PRevokeService implements RevokeService {
//    
//    @Resource(name = "tradingRecordService")
//    private LATradingRecordService tradingRecordService;
//    
//    @Resource(name = "loanAccountService")
//    private LoanAccountService loanAccountService;
//    
//    /**
//     * @param loanAccountId
//     * @return
//     */
//    @Override
//    public List<LATradingRecord> getRevokeAbleTradingRecords(String loanAccountId) {
//        List<LATradingRecord> resList = new ArrayList<>();
//        List<LATradingRecord> revokeAbleTradingList = this.tradingRecordService.queryRevokeAbleByLoanAccountId(loanAccountId,
//                Arrays.asList(LATradingCategoryEnum.放款,
//                        LATradingCategoryEnum.还款,
//                        LATradingCategoryEnum.豁免),
//                Arrays.asList(LATradingRecordTypeEnum.超额还款),
//                null);
//        LATradingRecord lastTradingRecord = null;
//        if (!CollectionUtils.isEmpty(revokeAbleTradingList)) {
//            Collections.sort(revokeAbleTradingList, OrderComparator.INSTANCE);
//            lastTradingRecord = revokeAbleTradingList.get(revokeAbleTradingList.size() - 1);
//        } else {
//            return resList;
//        }
//        
//        //如果最后一笔为放款，并满足放款撤销条件，则该交易可撤销
//        if (LATradingCategoryEnum.放款.equals(lastTradingRecord.getTradingType())) {
//            //贷款账户不逾期,并且当日小于首次还款日
//            LoanAccount loanAccount = this.loanAccountService.findById(loanAccountId);
//            if (loanAccount.isOverdue()
//                    || DateUtils.compareByDay(new Date(),
//                            loanAccount.getFirstRepayDate()) > 0) {
//                return resList;
//            }
//            //贷款账户存在多条交易
//            List<LATradingRecord> tradingRecords = this.tradingRecordService.queryTradingRecordListByLoanAccountIdAndTradingTypes(loanAccountId,
//                    new LATradingCategoryEnum[] { LATradingCategoryEnum.还款,
//                            LATradingCategoryEnum.豁免 });
//            if (CollectionUtils.isEmpty(tradingRecords)) {
//                resList.add(lastTradingRecord);
//                return resList;
//            }
//        } else {
//            //如果最后一笔不为放款，查询所有超额还款
//            List<LATradingRecord> orTradingRecords = this.tradingRecordService.queryRevokeAbleByLoanAccountId(loanAccountId,
//                    Arrays.asList(LATradingRecordTypeEnum.超额还款),
//                    null);
//            //如果超额还款为空
//            if (CollectionUtils.isEmpty(orTradingRecords)) {
//                resList.add(lastTradingRecord);
//            } else {
//                resList.addAll(orTradingRecords);
//            }
//        }
//        return resList;
//    }
//    
//    /**
//     * @param loanAccountId
//     * @param revokeTradingRecordId
//     * @param revokeReason
//     */
//    @Override
//    @Transactional
//    public void revoke(String loanAccountId, String revokeTradingRecordId,
//            String revokeReason) {
//        AssertUtils.notEmpty(loanAccountId, "loanAccountId is empty.");
//        AssertUtils.notEmpty(revokeTradingRecordId,
//                "revokeTradingRecordId is empty.");
//        
//        RevokeTradingDispatchRequest rtdRequest = new RevokeTradingDispatchRequest(
//                loanAccountId, revokeTradingRecordId, revokeReason,
//                RequestSourceTypeEnum.操作请求);
//        CommandContext.getContext().post(rtdRequest);
//    }
//    
//    /**
//     * 归还款项请求<br/>
//     * <功能详细描述>
//     * 
//     * @param bankAccountId
//     * @param loanAccountId
//     * @param revokeTradingRecordId
//     * @param revokeReason [参数说明]
//     *            
//     * @return void [返回类型说明]
//     * @exception throws [异常类型] [异常说明]
//     * @see [类、类#方法、类#成员]
//     */
//    @Transactional
//    @Override
//    public void refund(String bankAccountId, String loanAccountId,
//            String revokeTradingRecordId, String revokeReason) {
//        AssertUtils.notEmpty(bankAccountId, "bankAccountId is empty.");
//        AssertUtils.notEmpty(loanAccountId, "loanAccountId is empty.");
//        AssertUtils.notEmpty(revokeTradingRecordId,
//                "revokeTradingRecordId is empty.");
//        
//        RefundRequest refundRequest = new RefundRequest(bankAccountId,
//                loanAccountId, revokeTradingRecordId, revokeReason);
//        CommandContext.getContext().post(refundRequest);
//    }
//    
//}
