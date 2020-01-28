///*
// * 描          述:  <描述>
// * 修  改   人:  Administrator
// * 修改时间:  2015年2月15日
// * <修改描述:>
// */
//package com.tx.local.loanaccount.helper.charge;
//
//import java.util.List;
//
//import com.tx.local.loanaccount.context.request.trading.AbstractTradingRequest;
//import com.tx.local.loanaccount.model.ChargeRecordEntry;
//import com.tx.local.loanaccount.model.LATradingRecord;
//import com.tx.local.loanaccount.model.LoanAccountTypeEnum;
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
//public interface ChargeHandle<PR extends AbstractTradingRequest> {
//    
//    /**
//     * 计算对应的贷款账户类型<br/>
//     * <功能详细描述>
//     * @return [参数说明]
//     * 
//     * @return LoanAccountTypeEnum[] [返回类型说明]
//     * @exception throws [异常类型] [异常说明]
//     * @see [类、类#方法、类#成员]
//    */
//    public List<LoanAccountTypeEnum> getSupportLoanAccountTypes();
//    
//    /**
//      * 获取金额计算器处理的金额类型<br/>
//      * <功能详细描述>
//      * @return [参数说明]
//      * 
//      * @return AmountCaculatorTypeEnum [返回类型说明]
//      * @exception throws [异常类型] [异常说明]
//      * @see [类、类#方法、类#成员]
//     */
//    public ChargeHandleTypeEnum getChargeHandleType();
//    
//    /**
//     * 是否需要进行计费<br/>
//      *<功能详细描述>
//      * @param request
//      * @return [参数说明]
//      * 
//      * @return boolean [返回类型说明]
//      * @exception throws [异常类型] [异常说明]
//      * @see [类、类#方法、类#成员]
//     */
//    public boolean isNeedChargeTrading(PR request);
//    
//    /**
//     * 为贷款单账户形成计费记录<br/>
//     * <功能详细描述>
//     * @param tradingRecord
//     * @return [参数说明]
//     * 
//     * @return List<ChargeRecordEntry> [返回类型说明]
//     * @exception throws [异常类型] [异常说明]
//     * @see [类、类#方法、类#成员]
//    */
//    public abstract List<ChargeRecordEntry> charge(PR request,
//            LATradingRecord tradingRecord);
//    
//    /**
//     * 持久化计费记录<br/> 
//     * <功能详细描述>
//     * @param request
//     * @param response
//     * @param loanAccount
//     * @param feeCfgItemList
//     * @param paymentScheduleMap
//     * @param tradingRecord [参数说明]
//     * 
//     * @return void [返回类型说明]
//     * @exception throws [异常类型] [异常说明]
//     * @see [类、类#方法、类#成员]
//    */
//    public void persist(PR request, LATradingRecord tradingRecord);
//    
//    /**
//     * 撤销计费的持久化逻辑<br/>
//     * <功能详细描述>
//     * @param request
//     * @param tradingRecord
//     * @param revokedTrading [参数说明]
//     * 
//     * @return void [返回类型说明]
//     * @exception throws [异常类型] [异常说明]
//     * @see [类、类#方法、类#成员]
//    */
//    public void revokePersist(PR request,
//            LATradingRecord tradingRecord, LATradingRecord revokedTrading);
//}
