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
//import java.util.ArrayList;
//import java.util.List;
//
//import javax.annotation.Resource;
//
//import org.joda.time.DateTime;
//import org.springframework.context.annotation.Scope;
//import org.springframework.stereotype.Component;
//
//import com.tx.component.basicdata.model.DeductChannelTypeEnum;
//import com.tx.component.basicdata.model.FeeItemEnum;
//import com.tx.lms.loanaccount.model.DeductTradingRecord;
//import com.tx.lms.loanaccount.request.trading.charge.DeductFailChargeRequest;
//import com.tx.lms.loanaccount.service.DeductFailChargeRecordService;
//import com.tx.lms.support.deduct.model.DeductTaskSourceTypeEnum;
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
//@Component("zsDeductFailChargeHandle")
//public class P2PDeductFailChargeHandle extends
//        AbstractP2PChargeHandle<DeductFailChargeRequest> {
//    
//    @Resource(name = "deductFailChargeRecordService")
//    private DeductFailChargeRecordService deductFailChargeRecordService;
//    
//    /** 是否进行计费 */
//    private Boolean isCharge;
//    
//    /** 计费金额 */
//    private BigDecimal chargeAmount = BigDecimal.ZERO;
//    
//    /** <默认构造函数> */
//    public P2PDeductFailChargeHandle(LoanAccount loanAccount,
//            AbstractTradingReceiver<?> receiver) {
//        super(loanAccount, receiver);
//    }
//    
//    /** <默认构造函数> */
//    public P2PDeductFailChargeHandle(PaymentScheduleHandler handler) {
//        super(handler);
//    }
//    
//    /** <默认构造函数> */
//    public P2PDeductFailChargeHandle() {
//        super();
//    }
//    
//    /**
//     * @return
//     */
//    @Override
//    public ChargeHandleTypeEnum getChargeHandleType() {
//        return ChargeHandleTypeEnum.DEDUCT_FAIL_CHARGE;
//    }
//    
//    /**
//     * 判断是否计收当前的扣款失败手续费<br>
//     *    如果当前同一渠道，同一贷款账户已经进行过计费，则不再重复计费<br>
//     *    逾期30天试扣款则不进行计费<br>
//     */
//    @Override
//    public boolean isNeedChargeTrading(DeductFailChargeRequest request) {
//        DeductTradingRecord deductTradingRecord = request.getDeductTradingRecord();
//        if(DeductTaskSourceTypeEnum.逾期30天试扣款.equals(deductTradingRecord.getDeductTaskSourceType())){
//            return false;
//        }
//        String loanAccountId = request.getLoanAccountId();
//        DateTime now = DateTime.now();
//        DateTime minChargeDateTime = new DateTime(now.getYear(),
//                now.getMonthOfYear(), now.getDayOfMonth(), 0, 0, 0);
//        DateTime maxChargeDateTime = minChargeDateTime.plusDays(1);
//        int count = this.deductFailChargeRecordService.countDeductFailChargeReord(loanAccountId,
//                request.getRepayChannelType(),
//                request.getDeductChannelType(),
//                minChargeDateTime.toDate(),
//                maxChargeDateTime.toDate());
//        if (count > 0) {
//            this.isCharge = false;
//        } else {
//            this.isCharge = true;
//        }
//        return this.isCharge;
//    }
//    
//    /**
//     * @return
//     */
//    @Override
//    public List<ChargeRecordEntry> charge(DeductFailChargeRequest request,
//            TradingRecord tradingRecord) {
//        List<ChargeRecordEntry> chargeRecordEntries = new ArrayList<ChargeRecordEntry>();
//        
//        BigDecimal amount = null;
//        //计算计费金额<br/>
//        amount = caculateChargeAmount();
//        chargeAmount = amount;
//        
//        //计算逾期利息
//        ChargeRecordEntry entry = ChargeRecordEntryHelper.buildChargeRecordEntry(tradingRecord,
//                this.period2paymentScheduleMap,
//                LoanAccountConstants.PERIOD_NA,
//                FeeItemEnum.扣款失败手续费,
//                amount,
//                amount);
//        chargeRecordEntries.add(entry);
//        
//        tradingRecord.setSum(tradingRecord.getSum().add(amount));
//        return chargeRecordEntries;
//    }
//    
//    /**
//     * @param tradingRecord
//     */
//    @Override
//    public void persist(DeductFailChargeRequest request,
//            TradingRecord tradingRecord) {
//        DeductTradingRecord deductTradingRecord = request.getDeductTradingRecord();
//        String remark = request.getRemark();
//        this.deductFailChargeRecordService.addDeductFailCharge(this.isCharge,
//                request.getRepayChannelType(),
//                request.getDeductChannelType(),
//                chargeAmount,
//                FeeItemEnum.扣款失败手续费,
//                this.loanAccount,
//                deductTradingRecord,
//                remark);
//    }
//    
//    /** 
//     * 计算计费金额<br/>
//     * <功能详细描述>
//     * @return [参数说明]
//     * 
//     * @return BigDecimal [返回类型说明]
//     * @exception throws [异常类型] [异常说明]
//     * @see [类、类#方法、类#成员]
//     */
//    private BigDecimal caculateChargeAmount() {
//        BigDecimal amount;
//        //获取贷款账户贷款金额
//        BigDecimal loanAmount = loanAccount.getLoanAmount();
//        if (loanAmount.compareTo(new BigDecimal("100000")) < 0) {
//            amount = new BigDecimal("10");
//        } else if (loanAmount.compareTo(new BigDecimal("300000")) < 0) {
//            amount = new BigDecimal("30");
//        } else {
//            amount = new BigDecimal("50");
//        }
//        return amount;
//    }
//    
//}
