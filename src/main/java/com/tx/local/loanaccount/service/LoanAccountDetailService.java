///*
// * 描          述:  <描述>
// * 修  改   人:  Administrator
// * 修改时间:  2016年5月5日
// * <修改描述:>
// */
//package com.tx.local.loanaccount.service;
//
//import javax.annotation.Resource;
//
//import org.springframework.stereotype.Component;
//
///**
// * <功能简述>
// * <功能详细描述>
// * 
// * @author  Administrator
// * @version  [版本号, 2016年5月5日]
// * @see  [相关类/方法]
// * @since  [产品/模块版本]
// */
//@Component("loanAccountDetailService")
//public class LoanAccountDetailService {
//    
//    @Resource(name = "paymentScheduleService")
//    private PaymentScheduleService paymentScheduleService;
//    
//    @Resource(name = "paymentScheduleEntryService")
//    private PaymentScheduleEntryService paymentScheduleEntryService;
//    
//    @Resource(name = "loanAccountFeeCfgItemService")
//    private LoanAccountFeeItemService loanAccountFeeCfgItemService;
//    
//    public LoanAccountDetail bulidLoanAccountDetail(LoanAccount loanAccount,
//            Date repayDate, boolean containsOverdueInfo) {
//        
//        AssertUtils.notNull(loanAccount, "loanAccount is null.");
//        AssertUtils.notEmpty(loanAccount.getId(), "loanAccount.id is empty.");
//        
//        String loanAccountId = loanAccount.getId();
//        if (repayDate == null) {
//            repayDate = new Date();
//        }
//        
//        List<PaymentSchedule> paymentScheduleList = this.paymentScheduleService.queryListByLoanAccountId(loanAccountId);
//        List<PaymentScheduleEntry> paymentScheduleEntryList = this.paymentScheduleEntryService.queryPaymentScheduleEntryListByLoanAccountId(loanAccountId);
//        List<LoanAccountFeeItem> loanAccountFeeCfgItemList = this.loanAccountFeeCfgItemService.queryListByLoanAccountId(loanAccountId);
//        Date requestRepayDate = repayDate;
//        List<OverdueInterestChargeRecord> overdueInterestRecordList = null;
//        //        if (containsOverdueInfo) {
//        //            overdueInterestRecordList = this.overdueInterestRecordService.queryOverdueInterestRecordListByLoanAccountId(loanAccountId);
//        //        }
//        LoanAccountDetail loanAccountDetail = new CommonLoanBillLoanAccountDetail(
//                requestRepayDate, loanAccount, loanAccountFeeCfgItemList,
//                paymentScheduleList, paymentScheduleEntryList, containsOverdueInfo,
//                overdueInterestRecordList);
//        return loanAccountDetail;
//    }
//}
