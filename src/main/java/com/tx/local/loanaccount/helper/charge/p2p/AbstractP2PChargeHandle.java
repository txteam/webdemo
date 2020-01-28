///*
// * 描          述:  <描述>
// * 修  改   人:  Administrator
// * 修改时间:  2015年2月15日
// * <修改描述:>
// */
//package com.tx.local.loanaccount.helper.charge.p2p;
//
//import java.math.BigDecimal;
//import java.util.Arrays;
//import java.util.Date;
//import java.util.List;
//
//import com.tx.component.command.context.HelperFactory;
//import com.tx.local.loanaccount.context.receiver.trading.AbstractTradingReceiver;
//import com.tx.local.loanaccount.context.request.trading.AbstractTradingRequest;
//import com.tx.local.loanaccount.handle.PaymentScheduleHandler;
//import com.tx.local.loanaccount.helper.charge.AbstractChargeHandle;
//import com.tx.local.loanaccount.helper.chargerecord.OverdueInterestRecordHelper;
//import com.tx.local.loanaccount.model.LoanAccount;
//import com.tx.local.loanaccount.model.LoanAccountTypeEnum;
//import com.tx.local.loanaccount.model.OverdueInterestChargeRecord;
//import com.tx.local.loanaccount.model.RepayChannelTypeEnum;
//
///**
// * 入账计费处理器<br/>
// * <功能详细描述>
// * 
// * @author  Administrator
// * @version  [版本号, 2015年2月15日]
// * @see  [相关类/方法]
// * @since  [产品/模块版本]
// */
//public abstract class AbstractP2PChargeHandle<PR extends AbstractTradingRequest> extends
//        AbstractChargeHandle<PR> {
//    
//    /** <默认构造函数> */
//    public AbstractP2PChargeHandle(LoanAccount loanAccount,
//            AbstractTradingReceiver<?> receiver) {
//        super(loanAccount, receiver);
//    }
//    
//    /** <默认构造函数> */
//    public AbstractP2PChargeHandle(PaymentScheduleHandler handler) {
//        super(handler);
//    }
//    
//    /** <默认构造函数> */
//    public AbstractP2PChargeHandle() {
//        super();
//    }
//    
//    /**
//     * @return
//     */
//    @Override
//    public List<LoanAccountTypeEnum> getSupportLoanAccountTypes() {
//        return Arrays.asList(LoanAccountTypeEnum.P2P);
//    }
//    
//    /**
//      * 如果必要构建逾期利息记录
//      * <功能详细描述>
//      * @param tradingRecord
//      * @param repayDate
//      * @return [参数说明]
//      * 
//      * @return List<OverdueInterestRecord> [返回类型说明]
//      * @exception throws [异常类型] [异常说明]
//      * @see [类、类#方法、类#成员]
//     */
//    protected List<OverdueInterestChargeRecord> buildOverdueInterestRecordsIfNecessary(
//            Date repayDate) {
//        //如果贷款账户逾期，则需要将尚未入账的逾期金额计算进去
//        OverdueInterestRecordHelper oirHelper = HelperFactory.getHelper(OverdueInterestRecordHelper.class,
//                this.loanAccount.getLoanAccountType());
//        List<OverdueInterestChargeRecord> oirList = oirHelper.buildOverdueInterestRecords(RepayChannelTypeEnum.DK_ZX,
//                loanAccount,
//                this.feeItem2FeeCfgItemMap,
//                this.period2paymentScheduleMap,
//                repayDate,
//                null);
//        return oirList;
//    }
//    
//    /**
//     * 是否需要逾期利息计费<br/> 
//     * <功能详细描述>
//     * @param loanAccount
//     * @param repayDate
//     * @return [参数说明]
//     * 
//     * @return boolean [返回类型说明]
//     * @exception throws [异常类型] [异常说明]
//     * @see [类、类#方法、类#成员]
//    */
//    protected boolean isNeedOutsourceCommissionCharge(LoanAccount loanAccount) {
//        if (loanAccount.isOutsource()
//                && loanAccount.getOutsourcePercentage() != null
//                && loanAccount.getOutsourcePercentage()
//                        .compareTo(BigDecimal.ZERO) > 0) {
//            return true;
//        }
//        return false;
//    }
//    
//}
