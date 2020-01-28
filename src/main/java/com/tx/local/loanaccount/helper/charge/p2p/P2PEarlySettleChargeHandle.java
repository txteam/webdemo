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
//import com.tx.core.exceptions.util.AssertUtils;
//import com.tx.core.util.DateUtils;
//import com.tx.lms.loanaccount.helper.SupportProcessorFactory;
//import com.tx.local.loanaccount.LoanAccountConstants;
//import com.tx.local.loanaccount.context.Request;
//import com.tx.local.loanaccount.context.receiver.AbstractTradingReceiver;
//import com.tx.local.loanaccount.context.request.trading.charge.EarlySettleChargeRequest;
//import com.tx.local.loanaccount.handle.PaymentScheduleHandler;
//import com.tx.local.loanaccount.helper.charge.ChargeHandleTypeEnum;
//import com.tx.local.loanaccount.helper.chargerecord.ChargeRecordEntryHelper;
//import com.tx.local.loanaccount.helper.paymentschedule.PaymentScheduleHelper;
//import com.tx.local.loanaccount.model.ChargeRecordEntry;
//import com.tx.local.loanaccount.model.LoanAccount;
//import com.tx.local.loanaccount.model.OverdueInterestRecord;
//import com.tx.local.loanaccount.model.PaymentSchedule;
//import com.tx.local.loanaccount.model.TradingRecord;
//
///**
// * 扣款失败计费处理器<br/>
// * 
// * @author  Administrator
// * @version  [版本号, 2015年2月20日]
// * @see  [相关类/方法]
// * @since  [产品/模块版本]
// */
//@Scope("prototype")
//@Component("zsEarlySettleChargeHandle")
//public class P2PEarlySettleChargeHandle extends
//        AbstractP2PChargeHandle<EarlySettleChargeRequest> {
//    
//    private boolean isBuildOverdueInterestRecords = false;
//    
//    private List<OverdueInterestRecord> overdueInterestRecords;
//    
//    /** <默认构造函数> */
//    public P2PEarlySettleChargeHandle(LoanAccount loanAccount,
//            AbstractTradingReceiver<?> receiver) {
//        super(loanAccount, receiver);
//    }
//    
//    /** <默认构造函数> */
//    public P2PEarlySettleChargeHandle(PaymentScheduleHandler handler) {
//        super(handler);
//    }
//    
//    /** <默认构造函数> */
//    public P2PEarlySettleChargeHandle() {
//        super();
//    }
//    
//    /**
//     * @return
//     */
//    @Override
//    public ChargeHandleTypeEnum getChargeHandleType() {
//        return ChargeHandleTypeEnum.EARLY_SETTLE_CHARGE;
//    }
//    
//    /**
//     * @return
//     */
//    @Override
//    public boolean isNeedChargeTrading(
//            EarlySettleChargeRequest repayChargeRequest) {
//        Date repayDate = repayChargeRequest.getRepayIntention().getRepayDate();
//        if (!this.isBuildOverdueInterestRecords) {
//            this.overdueInterestRecords = buildOverdueInterestRecordsIfNecessary(repayDate);
//            this.isBuildOverdueInterestRecords = true;
//        }
//        if (!CollectionUtils.isEmpty(this.overdueInterestRecords)) {
//            return true;
//        }
//        if (isNeedOutsourceCommissionCharge(loanAccount)) {
//            return true;
//        }
//        PaymentScheduleHelper paymentScheduleHelper = SupportProcessorFactory.getSupport(PaymentScheduleHelper.class,
//                loanAccount.getLoanAccountType());
//        BigDecimal earlySettleDamageAmount = paymentScheduleHelper.calculateEarlySettleDamageAmount(loanAccount,
//                this.feeItem2FeeCfgItemMap,
//                this.period2paymentScheduleMap,
//                repayDate);
//        if (earlySettleDamageAmount.compareTo(BigDecimal.ZERO) > 0) {
//            return true;
//        }
//        return false;
//    }
//    
//    /**
//     * @return
//     */
//    @Override
//    public List<ChargeRecordEntry> charge(EarlySettleChargeRequest request,
//            TradingRecord tradingRecord) {
//        List<ChargeRecordEntry> chargeRecordEntries = new ArrayList<ChargeRecordEntry>();
//        
//        Date repayDate = request.getRepayIntention().getRepayDate();
//        //根据当前还款计划计算提前结清金额：该金额中已经包含了提前结清违约金
//        PaymentScheduleHelper paymentScheduleHelper = SupportProcessorFactory.getSupport(PaymentScheduleHelper.class,
//                loanAccount.getLoanAccountType());
//        BigDecimal earlySettleAmount = paymentScheduleHelper.caculateEarlySettleAmount(loanAccount,
//                loanAccount.getFeeCfgItemMapping(),
//                this.period2paymentScheduleMap,
//                repayDate);
//        
//        processOverdueInterest(request,
//                loanAccount,
//                this.period2paymentScheduleMap,
//                tradingRecord,
//                chargeRecordEntries);
//        for (OverdueInterestRecord oirTemp : this.overdueInterestRecords) {
//            earlySettleAmount = earlySettleAmount.add(oirTemp.getOverdueInterestAmount());
//        }
//        
//        processOutsourceCommissionAmount(earlySettleAmount,
//                request,
//                loanAccount,
//                this.period2paymentScheduleMap,
//                tradingRecord,
//                chargeRecordEntries);
//        
//        //提前结清违约金
//        processEarlySettleDamageAmount(request,
//                loanAccount,
//                this.period2paymentScheduleMap,
//                tradingRecord,
//                chargeRecordEntries);
//        
//        return chargeRecordEntries;
//    }
//    
//    /**
//     * @param tradingRecord
//     */
//    @Override
//    public void persist(EarlySettleChargeRequest request,
//            TradingRecord tradingRecord) {
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
//    private void processOverdueInterest(EarlySettleChargeRequest request,
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
//    private void processOutsourceCommissionAmount(BigDecimal earlySettleAmount,
//            EarlySettleChargeRequest request, LoanAccount loanAccount,
//            Map<String, PaymentSchedule> paymentScheduleMap,
//            TradingRecord tradingRecord, List<ChargeRecordEntry> resList) {
//        if (loanAccount.getOutsourcePercentage() == null
//                || BigDecimal.ZERO.compareTo(loanAccount.getOutsourcePercentage()) == 0) {
//            return;
//        }
//        
//        BigDecimal value = loanAccount.getOutsourcePercentage();
//        BigDecimal outsourceCommissionAmount = earlySettleAmount.multiply(value)
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
//    
//    /** 
//     * 计收提前结清违约金<br/>
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
//    private void processEarlySettleDamageAmount(
//            EarlySettleChargeRequest request, LoanAccount loanAccount,
//            Map<String, PaymentSchedule> paymentScheduleMap,
//            TradingRecord tradingRecord, List<ChargeRecordEntry> resList) {
//        Date repayDate = request.getRepayIntention().getRepayDate();
//        
//        PaymentScheduleHelper paymentScheduleHelper = SupportProcessorFactory.getSupport(PaymentScheduleHelper.class,
//                loanAccount.getLoanAccountType());
//        BigDecimal earlyRepayDamageAmount = paymentScheduleHelper.calculateEarlySettleDamageAmount(loanAccount,
//                loanAccount.getFeeCfgItemMapping(),
//                paymentScheduleMap,
//                repayDate);
//        if (earlyRepayDamageAmount.compareTo(BigDecimal.ZERO) != 0) {
//            ChargeRecordEntry earlySettleDamageChargeEntry = ChargeRecordEntryHelper.buildChargeRecordEntry(tradingRecord,
//                    paymentScheduleMap,
//                    LoanAccountConstants.PERIOD_NA,
//                    FeeItemEnum.提前还款违约金,
//                    earlyRepayDamageAmount,
//                    earlyRepayDamageAmount);
//            resList.add(earlySettleDamageChargeEntry);
//        }
//    }
//}
