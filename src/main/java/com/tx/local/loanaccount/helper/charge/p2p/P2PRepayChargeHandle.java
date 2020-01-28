package com.tx.local.loanaccount.helper.charge.p2p;
///*
// * 描          述:  <描述>
// * 修  改   人:  Administrator
// * 修改时间:  2015年2月20日
// * <修改描述:>
// */
//package com.tx.local.loanaccount.helper.charge.p2p;
//
//import java.math.BigDecimal;
//import java.math.RoundingMode;
//import java.util.ArrayList;
//import java.util.Date;
//import java.util.List;
//import java.util.Map;
//
//import org.apache.commons.collections.CollectionUtils;
//import org.apache.commons.lang3.StringUtils;
//import org.springframework.context.annotation.Scope;
//import org.springframework.stereotype.Component;
//import org.springframework.util.MultiValueMap;
//
//import com.tx.component.basicdata.model.FeeItemEnum;
//import com.tx.core.TxConstants;
//import com.tx.core.exceptions.util.AssertUtils;
//import com.tx.core.util.DateUtils;
//import com.tx.local.loanaccount.LoanAccountConstants;
//import com.tx.local.loanaccount.context.Request;
//import com.tx.local.loanaccount.context.receiver.AbstractTradingReceiver;
//import com.tx.local.loanaccount.context.request.trading.charge.RepayChargeRequest;
//import com.tx.local.loanaccount.handle.PaymentScheduleHandler;
//import com.tx.local.loanaccount.helper.charge.ChargeHandleTypeEnum;
//import com.tx.local.loanaccount.helper.chargerecord.ChargeRecordEntryHelper;
//import com.tx.local.loanaccount.model.ChargeRecordEntry;
//import com.tx.local.loanaccount.model.LoanAccount;
//import com.tx.local.loanaccount.model.OverdueInterestRecord;
//import com.tx.local.loanaccount.model.PaymentSchedule;
//import com.tx.local.loanaccount.model.TradingRecord;
//
///**
// * 扣款失败计费处理器<br/>
// * <功能详细描述>
// * 
// * @author  Administrator
// * @version  [版本号, 2015年2月20日]
// * @see  [相关类/方法]
// * @since  [产品/模块版本]
// */
//@Scope("prototype")
//@Component("zsRepayChargeHandle")
//public class P2PRepayChargeHandle extends
//        AbstractP2PChargeHandle<RepayChargeRequest> {
//    
//    private boolean isBuildOverdueInterestRecords = false;
//    
//    private List<OverdueInterestRecord> overdueInterestRecords;
//    
//    /** <默认构造函数> */
//    public P2PRepayChargeHandle(LoanAccount loanAccount,
//            AbstractTradingReceiver<?> receiver) {
//        super(loanAccount, receiver);
//    }
//    
//    /** <默认构造函数> */
//    public P2PRepayChargeHandle(PaymentScheduleHandler handler) {
//        super(handler);
//    }
//    
//    /** <默认构造函数> */
//    public P2PRepayChargeHandle() {
//        super();
//    }
//    
//    /**
//     * @return
//     */
//    @Override
//    public ChargeHandleTypeEnum getChargeHandleType() {
//        return ChargeHandleTypeEnum.REPAY_CHARGE;
//    }
//    
//    /**
//     * @return
//     */
//    @Override
//    public boolean isNeedChargeTrading(RepayChargeRequest repayChargeRequest) {
////        Date repayDate = repayChargeRequest.getRepayIntention().getRepayDate();
////        if (!this.isBuildOverdueInterestRecords) {
////            this.overdueInterestRecords = buildOverdueInterestRecordsIfNecessary(repayDate);
////            this.isBuildOverdueInterestRecords = true;
////        }
////        if (!CollectionUtils.isEmpty(this.overdueInterestRecords)) {
////            return true;
////        }
////        
////        if (!CollectionUtils.isEmpty(this.overdueInterestRecords)) {
////            return true;
////        }
////        if (isNeedOutsourceCommissionCharge(loanAccount)) {
////            return true;
////        }
//        return false;
//    }
//    
//    /**
//     * @return
//     */
//    @Override
//    public List<ChargeRecordEntry> charge(RepayChargeRequest request,
//            TradingRecord tradingRecord) {
//        List<ChargeRecordEntry> resList = new ArrayList<>(
//                TxConstants.INITIAL_CONLLECTION_SIZE);
//        
//        //逾期利息计算
//        processOverdueInterest(request,
//                this.loanAccount,
//                this.period2paymentScheduleMap,
//                tradingRecord,
//                resList);
//        
//        //外包佣金
//        processOutsourceCommissionAmount(request,
//                this.loanAccount,
//                this.period2paymentScheduleMap,
//                tradingRecord,
//                resList);
//        
//        //填入计费交易的交易金额
//        BigDecimal tradingSum = BigDecimal.ZERO;
//        for (ChargeRecordEntry creTemp : resList) {
//            tradingSum = tradingSum.add(creTemp.getAmount());
//        }
//        tradingRecord.setSum(tradingSum);
//        return resList;
//    }
//    
//    /**
//     * @param tradingRecord
//     */
//    @Override
//    public void persist(RepayChargeRequest request, TradingRecord tradingRecord) {
//        AssertUtils.notNull(request, "request is null");
//        AssertUtils.notNull(tradingRecord, "tradingRecord is null");
//        
//        if (!CollectionUtils.isEmpty(this.overdueInterestRecords)) {
//            for (OverdueInterestRecord oirTemp : this.overdueInterestRecords) {
//                oirTemp.setProcessDate(tradingRecord.getProcessDate());
//                oirTemp.setTradingRecordId(tradingRecord.getId());
//                
//                this.overdueInterestRecordService.insertOverdueInterestRecord(oirTemp);
//                
//                //修改贷款账户最后计息日
//                Date lastInterestAccrualDate = loanAccount.getLastInterestAccrualDate(); //计息日
//                Date currentPeriodExpireDate = loanAccount.getCurrentPeriodExpireDate(); //当前期数到期日
//                Date interestAccrualStartDate = DateUtils.max(lastInterestAccrualDate,
//                        currentPeriodExpireDate);//取其中最大值
//                Date repayDate = request.getRepayIntention().getRepayDate();
//                
//                //设置贷款账户的最后一次计息日:取还款日与计息日中最大值为最后一次计息日
//                loanAccount.setLastInterestAccrualDate(DateUtils.max(interestAccrualStartDate,
//                        repayDate));
//            }
//        }
//    }
//    
//    /**
//     * @param tradingRecord
//     * @return
//     */
//    @Override
//    public void revokePersist(Request request, TradingRecord tradingRecord,
//            TradingRecord revokedTradingRecord) {
//        AssertUtils.notNull(request, "request is null");
//        AssertUtils.notNull(tradingRecord, "tradingRecord is null");
//        AssertUtils.notNull(revokedTradingRecord,
//                "revokedTradingRecord is null");
//        
//        MultiValueMap<FeeItemEnum, ChargeRecordEntry> chargeRecordEntryMultiValueMap = tradingRecord.getChargeRecordEntryMultiValueMap();
//        if (!CollectionUtils.isEmpty(chargeRecordEntryMultiValueMap.get(FeeItemEnum.逾期利息))) {
//            List<OverdueInterestRecord> overdueInterestRecordList = this.overdueInterestRecordService.queryOverdueInterestRecordListByTradingRecordId(revokedTradingRecord.getId());
//            
//            if (!CollectionUtils.isEmpty(overdueInterestRecordList)) {
//                for (OverdueInterestRecord oir : overdueInterestRecordList) {
//                    this.overdueInterestRecordService.moveToHis(oir);
//                    //恢复在计费入账前贷款账户的最后一次计息日
//                    loanAccount.setLastInterestAccrualDate(oir.getLastInterestAccrualDate());
//                }
//            }
//        }
//        if (!CollectionUtils.isEmpty(chargeRecordEntryMultiValueMap.get(FeeItemEnum.外包佣金))) {
//            //TODO:下周在贷款账户中增加外包佣金记录功能
//        }
//    }
//    
//    /** 
//     * 处理逾期利息<br/>
//     * <功能详细描述>
//     * @param request
//     * @param loanAccount
//     * @param paymentScheduleMap
//     * @param tradingRecord
//     * @param resList [参数说明]
//     * 
//     * @return void [返回类型说明]
//     * @exception throws [异常类型] [异常说明]
//     * @see [类、类#方法、类#成员]
//     */
//    private void processOverdueInterest(RepayChargeRequest request,
//            LoanAccount loanAccount,
//            Map<String, PaymentSchedule> paymentScheduleMap,
//            TradingRecord tradingRecord, List<ChargeRecordEntry> resList) {
//        Date repayDate = request.getRepayIntention().getRepayDate();
//        if (!this.isBuildOverdueInterestRecords) {
//            this.overdueInterestRecords = buildOverdueInterestRecordsIfNecessary(repayDate);
//            this.isBuildOverdueInterestRecords = true;
//        }
//        
//        if (CollectionUtils.isEmpty(this.overdueInterestRecords)) {
//            //如果不需要进行逾期利息计费计算，则直接跳过该逻辑
//            return;
//        }
//        
//        for (OverdueInterestRecord oirTemp : this.overdueInterestRecords) {
//            ChargeRecordEntry overdueChargeEntry = ChargeRecordEntryHelper.buildChargeRecordEntry(tradingRecord,
//                    paymentScheduleMap,
//                    LoanAccountConstants.PERIOD_NA,
//                    oirTemp.getFeeItem(),
//                    oirTemp.getOverdueInterestAmount(),
//                    oirTemp.getOverdueInterestAmount());
//            resList.add(overdueChargeEntry);
//        }
//    }
//    
//    /**
//     * 处理外包佣金计收<br/>
//     * <功能详细描述>
//     * @param request
//     * @param loanAccount
//     * @param paymentScheduleMap
//     * @param tradingRecord
//     * @param resList [参数说明]
//     * 
//     * @return void [返回类型说明]
//     * @exception throws [异常类型] [异常说明]
//     * @see [类、类#方法、类#成员]
//    */
//    private void processOutsourceCommissionAmount(RepayChargeRequest request,
//            LoanAccount loanAccount,
//            Map<String, PaymentSchedule> paymentScheduleMap,
//            TradingRecord tradingRecord, List<ChargeRecordEntry> resList) {
//        if (loanAccount.getOutsourcePercentage() == null
//                || BigDecimal.ZERO.compareTo(loanAccount.getOutsourcePercentage()) == 0) {
//            return;
//        }
//        
//        BigDecimal value = loanAccount.getOutsourcePercentage();
//        BigDecimal outsourceCommissionAmount = request.getRepayIntention()
//                .getAmount()
//                .multiply(value)
//                .setScale(0, RoundingMode.UP);
//        ChargeRecordEntry outsourceCommissionChargeEntry = ChargeRecordEntryHelper.buildChargeRecordEntry(tradingRecord,
//                paymentScheduleMap,
//                LoanAccountConstants.PERIOD_NA,
//                FeeItemEnum.外包佣金,
//                outsourceCommissionAmount,
//                outsourceCommissionAmount);
//        tradingRecord.setOutsourceRepay(true);
//        
//        String outsourceAssignRecordId = request.getRepayIntention().getOutsourceAssignRecordId();
//        if(StringUtils.isNotBlank(outsourceAssignRecordId)){ 
//            tradingRecord.setOutsourceAssignRecordId(outsourceAssignRecordId);
//        }
//        
//        resList.add(outsourceCommissionChargeEntry);
//    }
//}
