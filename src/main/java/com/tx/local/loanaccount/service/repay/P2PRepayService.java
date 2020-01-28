///*
// * 描          述:  <描述>
// * 修  改   人:  Administrator
// * 修改时间:  2014年8月21日
// * <修改描述:>
// */
//package com.tx.local.loanaccount.service.repay;
//
//import java.math.BigDecimal;
//import java.util.Arrays;
//import java.util.Collections;
//import java.util.Date;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//import java.util.Map.Entry;
//
//import javax.annotation.Resource;
//
//import org.apache.commons.collections.CollectionUtils;
//import org.apache.commons.collections.MapUtils;
//import org.springframework.core.OrderComparator;
//import org.springframework.stereotype.Component;
//import org.springframework.transaction.annotation.Transactional;
//
//import com.tx.component.basicdata.model.FeeItemEnum;
//import com.tx.component.command.context.CommandContext;
//import com.tx.core.exceptions.util.AssertUtils;
//import com.tx.core.util.UUIDUtils;
//import com.tx.local.loanaccount.context.request.process.AutoTransferRepayAndSettleDispatcherRequest;
//import com.tx.local.loanaccount.context.request.trading.charge.RepayChargeRequest;
//import com.tx.local.loanaccount.handle.PaymentScheduleHandler;
//import com.tx.local.loanaccount.handle.PaymentScheduleHandlerHelper;
//import com.tx.local.loanaccount.helper.charge.ChargeHandle;
//import com.tx.local.loanaccount.helper.charge.ChargeHandleFactory;
//import com.tx.local.loanaccount.helper.charge.ChargeHandleTypeEnum;
//import com.tx.local.loanaccount.helper.repay.earlysettleallocator.P2PEarlySettleAllocator;
//import com.tx.local.loanaccount.helper.repay.repayallocator.P2PRepayAllocator;
//import com.tx.local.loanaccount.model.ChargeRecordEntry;
//import com.tx.local.loanaccount.model.LATradingCategoryEnum;
//import com.tx.local.loanaccount.model.LoanAccount;
//import com.tx.local.loanaccount.model.LoanAccountFeeItem;
//import com.tx.local.loanaccount.model.RepayChannelTypeEnum;
//import com.tx.local.loanaccount.model.RepayIntention;
//import com.tx.local.loanaccount.model.RequestSourceTypeEnum;
//import com.tx.local.loanaccount.model.LATradingRecord;
//import com.tx.local.loanaccount.service.LoanAccountService;
//import com.tx.local.loanaccount.service.RepayService;
//import com.tx.local.loanaccount.service.LATradingRecordService;
//
///**
// * 还款业务层<br/>
// * <功能详细描述>
// * 
// * @author  Administrator
// * @version  [版本号, 2014年8月21日]
// * @see  [相关类/方法]
// * @since  [产品/模块版本]
// */
//@Component("p2pRepayService")
//public class P2PRepayService implements RepayService {
//    
//    @Resource(name = "loanAccountService")
//    private LoanAccountService loanAccountService;
//    
//    @Resource(name = "tradingRecordService")
//    private LATradingRecordService tradingRecordService;
//    
//    /**
//     * @param repayIntention
//     * @return
//     */
//    @Override
//    public Map<String, BigDecimal> autoAssign(RepayIntention repayIntention) {
//        AssertUtils.notNull(repayIntention, "repayIntention is null");
//        AssertUtils.notEmpty(repayIntention.getLoanAccountId(),
//                "repayIntention.loanAccountId is null");
//        if (repayIntention.getRepayDate() == null) {
//            repayIntention.setRepayDate(new Date());
//        }
//        
//        String loanAccountId = repayIntention.getLoanAccountId();
//        //得到每个金额在平息中的分配情况
//        Map<String, BigDecimal> assignSumMap = new HashMap<>();
//        switch (repayIntention.getRepayType()) {
//            case CASH_REPAY:
//            case AUTO_TRANSFER_REPAY:
//                repayAutoAssign(repayIntention, loanAccountId, assignSumMap);
//                break;
//            case CASH_EARLY_SETTLE:
//                earlySettleAutoAssign(repayIntention,
//                        loanAccountId,
//                        assignSumMap);
//                break;
//            default:
//                AssertUtils.isTrue(false,
//                        "错误的还款类型：{}",
//                        new Object[] { repayIntention.getRepayType() });
//                break;
//        }
//        return assignSumMap;
//    }
//    
//    /** 
//     * 提前结清自动分配<br/>
//     * <功能详细描述>
//     * @param repayIntention
//     * @param loanAccountId [参数说明]
//     * 
//     * @return void [返回类型说明]
//     * @exception throws [异常类型] [异常说明]
//     * @see [类、类#方法、类#成员]
//     */
//    private PaymentScheduleHandler earlySettleAutoAssign(
//            RepayIntention repayIntention, String loanAccountId,
//            Map<String, BigDecimal> assignSumMap) {
//        PaymentScheduleHandler handler = PaymentScheduleHandlerHelper.buildEarlySettlePaymentScheduleHandlerByLoanAccountId(loanAccountId,
//                repayIntention);
//        LoanAccount loanAccount = handler.getLoanAccount();
//        //进行平息分配
//        P2PEarlySettleAllocator zsEarlySettleAllocator = new P2PEarlySettleAllocator(
//                handler.getLoanAccount(), loanAccount.getFeeCfgItemList(),
//                handler.getPeriod2PaymentScheduleMap(), repayIntention);
//        zsEarlySettleAllocator.assign();
//        Map<String, Map<FeeItemEnum, BigDecimal>> repayAssignMap = zsEarlySettleAllocator.getCalmAssignAmountMap();
//        
//        //得到每个金额在平息中的分配情况
//        for (Entry<String, Map<FeeItemEnum, BigDecimal>> entryTemp : repayAssignMap.entrySet()) {
//            for (Entry<FeeItemEnum, BigDecimal> amountEntry : entryTemp.getValue()
//                    .entrySet()) {
//                FeeItemEnum feeItemTemp = amountEntry.getKey();
//                if (!assignSumMap.containsKey(feeItemTemp.toString())) {
//                    assignSumMap.put(feeItemTemp.toString(), BigDecimal.ZERO);
//                }
//                assignSumMap.put(feeItemTemp.toString(),
//                        assignSumMap.get(feeItemTemp.toString())
//                                .add(amountEntry.getValue()));
//            }
//        }
//        Map<RepayChannelTypeEnum, BigDecimal> overRepayAmountMap = zsEarlySettleAllocator.getOverRepayAmountMap();
//        if (!MapUtils.isEmpty(overRepayAmountMap)) {
//            BigDecimal overRepayAmountSum = BigDecimal.ZERO;
//            for (Entry<RepayChannelTypeEnum, BigDecimal> amountEntry : overRepayAmountMap.entrySet()) {
//                overRepayAmountSum = overRepayAmountSum.add(amountEntry.getValue());
//            }
//            assignSumMap.put(FeeItemEnum.CEHK.toString(), overRepayAmountSum);
//        }
//        
//        BigDecimal assignSumTemp = BigDecimal.ZERO;
//        for (BigDecimal amountTemp : assignSumMap.values()) {
//            assignSumTemp = assignSumTemp.add(amountTemp);
//        }
//        AssertUtils.isTrue(assignSumTemp.compareTo(repayIntention.getAmount()) == 0,
//                "assignSum:{} should equals amount:{} ",
//                new Object[] { assignSumTemp, repayIntention.getAmount() });
//        
//        assignSumMap.put("earlySettleAmount", handler.getSum());
//        return handler;
//    }
//    
//    /**
//      * 还款自动分配<br/>
//      * <功能详细描述>
//      * @param repayIntention
//      * @param loanAccountId
//      * @return [参数说明]
//      * 
//      * @return Map<String,BigDecimal> [返回类型说明]
//      * @exception throws [异常类型] [异常说明]
//      * @see [类、类#方法、类#成员]
//     */
//    private PaymentScheduleHandler repayAutoAssign(
//            RepayIntention repayIntention, String loanAccountId,
//            Map<String, BigDecimal> assignSumMap) {
//        PaymentScheduleHandler handler = PaymentScheduleHandlerHelper.buildRepayPaymentScheduleHandlerByLoanAccountId(loanAccountId,
//                repayIntention);
//        LoanAccount loanAccount = handler.getLoanAccount();
//        
//        //进行平息分配
//        P2PRepayAllocator zsRepayAllocator = new P2PRepayAllocator(
//                handler.getLoanAccount(), loanAccount.getFeeCfgItemList(),
//                handler.getPeriod2PaymentScheduleMap(), repayIntention);
//        zsRepayAllocator.assign();
//        Map<String, Map<FeeItemEnum, BigDecimal>> repayAssignMap = zsRepayAllocator.getCalmAssignAmountMap();
//        
//        for (Entry<String, Map<FeeItemEnum, BigDecimal>> entryTemp : repayAssignMap.entrySet()) {
//            for (Entry<FeeItemEnum, BigDecimal> amountEntry : entryTemp.getValue()
//                    .entrySet()) {
//                FeeItemEnum feeItemTemp = amountEntry.getKey();
//                if (!assignSumMap.containsKey(feeItemTemp.toString())) {
//                    assignSumMap.put(feeItemTemp.toString(), BigDecimal.ZERO);
//                }
//                assignSumMap.put(feeItemTemp.toString(),
//                        assignSumMap.get(feeItemTemp.toString())
//                                .add(amountEntry.getValue()));
//            }
//        }
//        for (Entry<RepayChannelTypeEnum, BigDecimal> amountEntry : zsRepayAllocator.getOverRepayAmountMap()
//                .entrySet()) {
//            //FeeItemEnum feeItemTemp = amountEntry.getKey();
//            if (!assignSumMap.containsKey(FeeItemEnum.CEHK.toString())) {
//                assignSumMap.put(FeeItemEnum.CEHK.toString(), BigDecimal.ZERO);
//            }
//            assignSumMap.put(FeeItemEnum.CEHK.toString(),
//                    assignSumMap.get(FeeItemEnum.CEHK.toString())
//                            .add(amountEntry.getValue()));
//        }
//        BigDecimal assignSumTemp = BigDecimal.ZERO;
//        for (BigDecimal amountTemp : assignSumMap.values()) {
//            assignSumTemp = assignSumTemp.add(amountTemp);
//        }
//        
//        AssertUtils.isTrue(assignSumTemp.compareTo(repayIntention.getAmount()) == 0,
//                "assignSum:{} should equals amount:{} ",
//                new Object[] { assignSumTemp, repayIntention.getAmount() });
//        return handler;
//    }
//    
//    /**
//     * 豁免自动分配<br/>
//     * <功能详细描述>
//     * @param repayIntention
//     * @param loanAccountId
//     * @return [参数说明]
//     * 
//     * @return Map<String,BigDecimal> [返回类型说明]
//     * @exception throws [异常类型] [异常说明]
//     * @see [类、类#方法、类#成员]
//    */
//    private PaymentScheduleHandler exemptAutoAssign(
//            RepayIntention repayIntention, String loanAccountId,
//            Map<String, BigDecimal> assignSumMap) {
//        PaymentScheduleHandler handler = PaymentScheduleHandlerHelper.buildPaymentScheduleHandler(loanAccountId);
//        //还款计费
//        LATradingRecord chargeTradingRecord = new LATradingRecord();
//        ChargeHandle<RepayChargeRequest> chargeHandle = ChargeHandleFactory.buildChargeHandle(ChargeHandleTypeEnum.REPAY_CHARGE,
//                handler);
//        RepayChargeRequest rcRequest = new RepayChargeRequest(
//                UUIDUtils.generateUUID(), true, repayIntention, loanAccountId,
//                RequestSourceTypeEnum.操作请求);
//        if (chargeHandle.isNeedChargeTrading(rcRequest)) {
//            List<ChargeRecordEntry> crEntryList = chargeHandle.charge(rcRequest,
//                    chargeTradingRecord);
//            handler.chargeByEntry(crEntryList);
//        }
//        //还款豁免
//        //        ExemptAllocator exemptAllocator = ExemptAllocatorFactory.buildExemptAllocator(ExemptAllocatorTypeEnum.REPAY_EXEMPT,
//        //                handler,
//        //                repayIntention);
//        //        exemptAllocator.calmAssign();
//        //        handler.exempt(exemptAllocator.getCalmAssignAmountMap());
//        
//        for (LoanAccountFeeItem feeCfgItemTemp : handler.getFeeCfgItemList()) {
//            FeeItemEnum feeItemTemp = feeCfgItemTemp.getFeeItem();
//            BigDecimal sumTemp = handler.getSum(feeItemTemp);
//            //各费用项应还
//            assignSumMap.put(feeItemTemp.toString(), sumTemp);
//        }
//        Map<FeeItemEnum, BigDecimal> assignAmountMap = new HashMap<FeeItemEnum, BigDecimal>();
//        //        for(Map<FeeItemEnum, BigDecimal> mapEntry : exemptAllocator.getCalmAssignAmountMap().values()){
//        //            for(Entry<FeeItemEnum, BigDecimal> eTemp : mapEntry.entrySet()){
//        //                if(!assignAmountMap.containsKey(eTemp.getKey())){
//        //                    assignAmountMap.put(eTemp.getKey(), BigDecimal.ZERO);
//        //                }
//        //                assignAmountMap.put(eTemp.getKey(), assignAmountMap.get(eTemp.getKey()).add(eTemp.getValue()));
//        //            }
//        //        }
//        for (Entry<FeeItemEnum, BigDecimal> assignEntry : assignAmountMap.entrySet()) {
//            FeeItemEnum feeItemTemp = assignEntry.getKey();
//            BigDecimal assignAmount = assignEntry.getValue();
//            //各费用项应还
//            assignSumMap.put(feeItemTemp.toString() + "_effictive",
//                    assignAmount);
//        }
//        return handler;
//    }
//    
//    @Override
//    @Transactional
//    public boolean repay(RepayIntention repayIntention) {
//        String loanAccountId = repayIntention.getLoanAccountId();
//        AssertUtils.notEmpty(loanAccountId, "loanAccountId is empty.");
//        AssertUtils.notNull(repayIntention, "repayIntention is null.");
//        
//        switch (repayIntention.getRepayType()) {
//            case AUTO_TRANSFER_REPAY:
//                AutoTransferRepayAndSettleDispatcherRequest atrr = new AutoTransferRepayAndSettleDispatcherRequest(
//                        loanAccountId, repayIntention,
//                        RequestSourceTypeEnum.操作请求);
//                CommandContext.getContext().post(atrr);
//                break;
//            case CASH_REPAY:
//            case CASH_EARLY_SETTLE:
//            default:
//                AssertUtils.isTrue(false, "错误的还款类型");
//                break;
//        }
//        
//        return true;
//    }
//    
//    @Override
//    public LATradingRecord findLastRevokeAbleRepayTradingRecord(
//            String loanAccountId) {
//        List<LATradingRecord> revokeAbleTradingList = this.tradingRecordService.queryRevokeAbleByLoanAccountId(loanAccountId,
//                Arrays.asList(LATradingCategoryEnum.还款),
//                null,
//                null);
//        LATradingRecord lastTradingRecord = null;
//        if (!CollectionUtils.isEmpty(revokeAbleTradingList)) {
//            Collections.sort(revokeAbleTradingList, OrderComparator.INSTANCE);
//            lastTradingRecord = revokeAbleTradingList.get(revokeAbleTradingList.size() - 1);
//            return lastTradingRecord;
//        } else {
//            return null;
//        }
//    }
//}
