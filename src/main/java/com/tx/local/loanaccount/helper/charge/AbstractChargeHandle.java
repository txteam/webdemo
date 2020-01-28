///*
// * 描          述:  <描述>
// * 修  改   人:  Administrator
// * 修改时间:  2015年2月15日
// * <修改描述:>
// */
//package com.tx.local.loanaccount.helper.charge;
//
//import java.util.List;
//import java.util.Map;
//
//import javax.annotation.Resource;
//
//import com.tx.component.basicdata.model.FeeItemEnum;
//import com.tx.core.exceptions.util.AssertUtils;
//import com.tx.local.loanaccount.context.receiver.trading.AbstractTradingReceiver;
//import com.tx.local.loanaccount.context.request.trading.AbstractTradingRequest;
//import com.tx.local.loanaccount.handle.PaymentScheduleHandler;
//import com.tx.local.loanaccount.model.LoanAccount;
//import com.tx.local.loanaccount.model.LoanAccountFeeItem;
//import com.tx.local.loanaccount.model.PaymentSchedule;
//import com.tx.local.loanaccount.model.LATradingRecord;
//import com.tx.local.loanaccount.service.OverdueInterestChargeRecordService;
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
//public abstract class AbstractChargeHandle<PR extends AbstractTradingRequest> implements
//        ChargeHandle<PR> {
//    
//    @Resource(name = "overdueInterestRecordService")
//    protected OverdueInterestChargeRecordService overdueInterestRecordService;
//    
//    protected final LoanAccount loanAccount;
//    
//    protected final List<LoanAccountFeeItem> feeCfgItemList;
//    
//    protected final Map<FeeItemEnum, LoanAccountFeeItem> feeItem2FeeCfgItemMap;
//    
//    protected final Map<String, PaymentSchedule> period2paymentScheduleMap;
//    
//    /** <默认构造函数> */
//    public AbstractChargeHandle() {
//        super();
//        
//        this.loanAccount = null;
//        this.feeCfgItemList = null;
//        this.feeItem2FeeCfgItemMap = null;
//        this.period2paymentScheduleMap = null;
//    }
//
//    /** <默认构造函数> */
//    public AbstractChargeHandle(LoanAccount loanAccount, AbstractTradingReceiver<?> receiver) {
//        AssertUtils.notNull(loanAccount, "loanAccount is null.");
//        AssertUtils.notEmpty(receiver, "receiver is null.");
//        
//        this.loanAccount = loanAccount;
//        String loanAccountId = loanAccount.getId();
//        
//        this.feeCfgItemList = receiver.getFeeCfgItemListByLoanAccountId(loanAccountId);
//        if (this.loanAccount.getFeeCfgItemList() == null) {
//            this.loanAccount.setFeeCfgItemList(feeCfgItemList);
//        }
//        this.feeItem2FeeCfgItemMap = this.loanAccount.getFeeCfgItemMapping();
//        this.period2paymentScheduleMap = receiver.getPeriod2PaymentScheduleMapByLoanAccountId(loanAccountId);
//    }
//    
//    /** <默认构造函数> */
//    public AbstractChargeHandle(PaymentScheduleHandler handler) {
//        AssertUtils.notEmpty(handler, "handler is null.");
//        
//        this.loanAccount = handler.getLoanAccount();
//        
//        this.feeCfgItemList = handler.getFeeCfgItemList();
//        if (this.loanAccount.getFeeCfgItemList() == null) {
//            this.loanAccount.setFeeCfgItemList(feeCfgItemList);
//        }
//        this.feeItem2FeeCfgItemMap = this.loanAccount.getFeeCfgItemMapping();
//        this.period2paymentScheduleMap = handler.getPeriod2PaymentScheduleMap();
//    }
//    
//    /**
//      * 是否需要进行计费<br/>
//      * <功能详细描述>
//      * @return [参数说明]
//      * 
//      * @return boolean [返回类型说明]
//      * @exception throws [异常类型] [异常说明]
//      * @see [类、类#方法、类#成员]
//     */
//    public boolean isNeedChargeTrading(PR request) {
//        return true;
//    }
//    
//    
//    
//    /**
//      * 持久化计费记录<br/> 
//      * <功能详细描述>
//      * @param request
//      * @param response
//      * @param loanAccount
//      * @param feeCfgItemList
//      * @param paymentScheduleMap
//      * @param tradingRecord [参数说明]
//      * 
//      * @return void [返回类型说明]
//      * @exception throws [异常类型] [异常说明]
//      * @see [类、类#方法、类#成员]
//     */
//    public void persist(PR request, LATradingRecord tradingRecord) {
//        throw new UnsupportedOperationException("不支撑的操作：persist");
//    }
//    
//    /**
//      * 撤销计费的持久化逻辑<br/>
//      * <功能详细描述>
//      * @param request
//      * @param tradingRecord
//      * @param revokedTrading [参数说明]
//      * 
//      * @return void [返回类型说明]
//      * @exception throws [异常类型] [异常说明]
//      * @see [类、类#方法、类#成员]
//     */
//    public void revokePersist(PR request, LATradingRecord tradingRecord,
//            LATradingRecord revokedTrading) {
//        throw new UnsupportedOperationException(
//                "不支撑的操作：persistRevokeChargeWhenRevoke");
//    }
//}
