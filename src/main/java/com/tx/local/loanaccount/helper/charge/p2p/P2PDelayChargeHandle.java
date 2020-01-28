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
//import java.util.List;
//
//import org.springframework.context.annotation.Scope;
//import org.springframework.stereotype.Component;
//
//import com.tx.component.basicdata.model.FeeItemEnum;
//import com.tx.core.util.DateUtils;
//import com.tx.lms.loanaccount.request.trading.delay.BaseDelayRequest;
//import com.tx.local.loanaccount.LoanAccountConstants;
//import com.tx.local.loanaccount.context.receiver.AbstractTradingReceiver;
//import com.tx.local.loanaccount.handle.PaymentScheduleHandler;
//import com.tx.local.loanaccount.helper.charge.ChargeHandleTypeEnum;
//import com.tx.local.loanaccount.helper.chargerecord.ChargeRecordEntryHelper;
//import com.tx.local.loanaccount.model.ChargeRecordEntry;
//import com.tx.local.loanaccount.model.LoanAccount;
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
//@Component("zsDelayChargeHandle")
//public class P2PDelayChargeHandle extends
//        AbstractP2PChargeHandle<BaseDelayRequest> {
//    
//    /** <默认构造函数> */
//    public P2PDelayChargeHandle(LoanAccount loanAccount,
//            AbstractTradingReceiver<?> receiver) {
//        super(loanAccount, receiver);
//    }
//    
//    /** <默认构造函数> */
//    public P2PDelayChargeHandle(PaymentScheduleHandler handler) {
//        super(handler);
//    }
//    
//    /** <默认构造函数> */
//    public P2PDelayChargeHandle() {
//        super();
//    }
//    
//    /**
//     * @return
//     */
//    @Override
//    public ChargeHandleTypeEnum getChargeHandleType() {
//        return ChargeHandleTypeEnum.DELAY_CHARGE;
//    }
//    
//    /**
//     * 判断是否计收当前的扣款失败手续费
//     *    如果当前同一渠道，同一贷款账户已经进行过计费，则不再重复计费<br/>
//     * @return
//     */
//    @Override
//    public boolean isNeedChargeTrading(BaseDelayRequest request) {
//        return true;
//    }
//    
//    /**
//     * @return
//     */
//    @Override
//    public List<ChargeRecordEntry> charge(BaseDelayRequest request,
//            TradingRecord tradingRecord) {
//        List<ChargeRecordEntry> chargeRecordEntries = new ArrayList<ChargeRecordEntry>();
//        
//        int count = DateUtils.calculateNumberOfDaysBetween(request.getTargetDate(),
//                request.getSourceDate());
//        //计算延期费
//        BigDecimal feeAmountRate1 = new BigDecimal(
//                this.loanAccount.getFeeCfgItemMapping()
//                        .get(FeeItemEnum.贷后延期手续费_咨询公司)
//                        .getValue());
//        BigDecimal amount1 = this.loanAccount.getLoanAmount()
//                .multiply(feeAmountRate1)
//                .multiply(new BigDecimal(count))
//                .setScale(0, RoundingMode.UP);
//        ChargeRecordEntry entry1 = ChargeRecordEntryHelper.buildChargeRecordEntry(tradingRecord,
//                this.period2paymentScheduleMap,
//                LoanAccountConstants.PERIOD_NA,
//                FeeItemEnum.贷后延期手续费_咨询公司,
//                amount1,
//                amount1);
//        chargeRecordEntries.add(entry1);
//        
//        BigDecimal feeAmountRate2 = new BigDecimal(
//                this.loanAccount.getFeeCfgItemMapping()
//                        .get(FeeItemEnum.贷后延期手续费_贷款公司)
//                        .getValue());
//        BigDecimal amount2 = this.loanAccount.getLoanAmount()
//                .multiply(feeAmountRate2)
//                .multiply(new BigDecimal(count))
//                .setScale(0, RoundingMode.UP);
//        ChargeRecordEntry entry2 = ChargeRecordEntryHelper.buildChargeRecordEntry(tradingRecord,
//                this.period2paymentScheduleMap,
//                LoanAccountConstants.PERIOD_NA,
//                FeeItemEnum.贷后延期手续费_贷款公司,
//                amount2,
//                amount2);
//        chargeRecordEntries.add(entry2);
//        
//        //tradingRecord.setSum(tradingRecord.getSum().add(amount));
//        return chargeRecordEntries;
//    }
//    
//    /**
//     * @param tradingRecord
//     */
//    @Override
//    public void persist(BaseDelayRequest request, TradingRecord tradingRecord) {
//    }
//}
