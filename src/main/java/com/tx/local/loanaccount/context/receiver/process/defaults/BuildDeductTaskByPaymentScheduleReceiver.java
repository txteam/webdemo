///*
// * 描          述:  <描述>
// * 修  改   人:  Administrator
// * 修改时间:  2015年2月10日
// * <修改描述:>
// */
//package com.tx.local.loanaccount.context.receiver.process.defaults;
//
//import java.util.ArrayList;
//import java.util.Date;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//import javax.annotation.Resource;
//
//import org.springframework.stereotype.Component;
//
//import com.tx.component.command.context.CommandContext;
//import com.tx.component.command.context.CommandResponse;
//import com.tx.core.exceptions.util.AssertUtils;
//import com.tx.local.loanaccount.LoanAccountConstants;
//import com.tx.local.loanaccount.context.receiver.process.AbstractProcessReceiver;
//import com.tx.local.loanaccount.context.request.process.BuildDeductTaskByPaymentScheduleRequest;
//import com.tx.local.loanaccount.context.request.trading.suspend.SuspendRepayRequest;
//import com.tx.local.loanaccount.context.request.trading.suspend.SuspendRequest;
//import com.tx.local.loanaccount.helper.deducttask.DeductTaskHelper;
//import com.tx.local.loanaccount.model.DeductTask;
//import com.tx.local.loanaccount.model.LABankAccount;
//import com.tx.local.loanaccount.model.LATradingRecord;
//import com.tx.local.loanaccount.model.LoanAccount;
//import com.tx.local.loanaccount.model.LoanAccount2ClientBankAccount;
//import com.tx.local.loanaccount.model.LoanAccountTypeEnum;
//import com.tx.local.loanaccount.model.PaymentSchedule;
//import com.tx.local.loanaccount.model.RepayIntention;
//import com.tx.local.loanaccount.model.RepayIntentionTypeEnum;
//import com.tx.local.loanaccount.service.DeductTaskService;
//import com.tx.local.loanaccount.service.LABankAccountService;
//import com.tx.local.loanaccount.service.LoanAccount2ClientBankAccountService;
//
///**
// * 
//  * 根据还款计划生成交易记录、扣款任务的处理器<br/>
//  * <功能详细描述>
//  * 
//  * @author  huangdonggang
//  * @version  [版本号, 2017年12月15日]
//  * @see  [相关类/方法]
//  * @since  [产品/模块版本]
// */
//@Component("buildDeductTaskByPaymentScheduleReceiver")
//public class BuildDeductTaskByPaymentScheduleReceiver extends
//        AbstractProcessReceiver<BuildDeductTaskByPaymentScheduleRequest> {
//    
//    /** 扣款任务业务层 */
//    @Resource(name = "deductTaskService")
//    private DeductTaskService deductTaskService;
//    
//    /** 贷款账户对公银行账户业务层 */
//    @Resource(name = "laBankAccountService")
//    private LABankAccountService laBankAccountService;
//    
//    /** 客户扣、退款银行账户信息 */
//    @Resource(name = "loanAccount2ClientBankAccountService")
//    private LoanAccount2ClientBankAccountService loanAccount2ClientBankAccountService;
//    
//    /**
//     * @param request
//     * @param response
//     */
//    @Override
//    public void handle(BuildDeductTaskByPaymentScheduleRequest request,
//            CommandResponse response, LoanAccount loanAccount) {
//        String loanAccountId = request.getLoanAccountId();
//        List<PaymentSchedule> receivablePaymentScheduleList = request
//                .getReceivablePaymentScheduleList();
//        AssertUtils.notTrue(receivablePaymentScheduleList.isEmpty(),
//                "receivablePaymentScheduleList is empty");
//        
//        //交易记录
//        List<LATradingRecord> laTradingRecords = new ArrayList<LATradingRecord>();
//        for (PaymentSchedule paymentSchedule : receivablePaymentScheduleList) {
//            RepayIntention repayIntention = buildRepayIntentionByPaymentSchedule(
//                    paymentSchedule, request.getExecuteDate(), loanAccount);
//            
//            CommandResponse tradingResponse = null;
//            if (loanAccount.isOverdue() || loanAccount.isLocked()) {
//                //发起暂缓交易: 当发生逾期或锁定期间，发起暂缓交易
//                tradingResponse = CommandContext.getContext()
//                        .post(new SuspendRequest(loanAccountId, repayIntention,
//                                paymentSchedule));
//            } else {
//                //发起未到账自动转账还款《未到账还款》
//                tradingResponse = CommandContext.getContext()
//                        .post(new SuspendRepayRequest(loanAccountId,
//                                repayIntention, paymentSchedule));
//            }
//            LATradingRecord tradingRecord = (LATradingRecord) tradingResponse
//                    .getAttribute(
//                            LoanAccountConstants.RESPONSE_KEY_TRADINGRECORD);
//            
//            laTradingRecords.add(tradingRecord);
//        }
//        
//        if (!laTradingRecords.isEmpty()) {
////            //生成交易记录，锁定贷款账户
////            loanAccountService.lockByLoanAccountId(loanAccount.getId());
//            //构建扣款任务
//            buildDeductTask(loanAccount, laTradingRecords);
//        }
//        
//    }
//    
//    /**
//     * 
//      *获取银行卡信息
//      *<功能详细描述>
//      * @param loanAccount
//      * @return [参数说明]
//      * 
//      * @return List<LoanAccount2ClientBankAccount> [返回类型说明]
//      * @exception throws [异常类型] [异常说明]
//      * @see [类、类#方法、类#成员]
//     */
//    private List<LoanAccount2ClientBankAccount> getLoanAccount2ClientBankAccounts(
//            LoanAccount loanAccount) {
//        Map<String, Object> params = new HashMap<String, Object>();
//        params.put("loanAccountId", loanAccount.getId());
//        params.put("valid", true);
//        List<LoanAccount2ClientBankAccount> loanAccount2ClientBankAccounts = loanAccount2ClientBankAccountService
//                .queryList(params);
//        return loanAccount2ClientBankAccounts;
//    }
//    
//    /**
//      * 根据还款计划生成还款意愿
//      * <功能详细描述>
//      * @param paymentSchedule
//      * @return [参数说明]
//      * 
//      * @return RepayIntention [返回类型说明]
//      * @exception throws [异常类型] [异常说明]
//      * @see [类、类#方法、类#成员]
//     */
//    private RepayIntention buildRepayIntentionByPaymentSchedule(
//            PaymentSchedule paymentSchedule, Date repayDate,
//            LoanAccount loanAccount) {
//        RepayIntention repayIntention = new RepayIntention();
//        repayIntention.setLoanAccountId(paymentSchedule.getLoanAccountId());
//        repayIntention.setRepayDate(repayDate);
//        repayIntention.setType(RepayIntentionTypeEnum.TRANSFER_REPAY);
//        repayIntention.setBankAccountId(
//                getLABankAccount(loanAccount.getLoanAccountType()).getId());
//        repayIntention.setAmount(paymentSchedule.getReceivableSum()
//                .add(paymentSchedule.getExemptSum())
//                .subtract(paymentSchedule.getActualReceivedSum()));
//        return repayIntention;
//    }
//    
//    /**
//     * 
//      * 构建扣款任务
//      *<功能详细描述>
//      * @param deductTasks
//      * @param loanAccount
//      * @param laTradingRecords [参数说明]
//      * 
//      * @return void [返回类型说明]
//      * @exception throws [异常类型] [异常说明]
//      * @see [类、类#方法、类#成员]
//     */
//    private void buildDeductTask(LoanAccount loanAccount,
//            List<LATradingRecord> laTradingRecords) {
//        
//        //客户银行卡信息
//        List<LoanAccount2ClientBankAccount> loanAccount2ClientBankAccounts = getLoanAccount2ClientBankAccounts(
//                loanAccount);
//        
//        //构建扣款任务
//        List<DeductTask> deductTasks = DeductTaskHelper
//                .buildDeductTaskByTradingRecord(loanAccount,
//                        laTradingRecords,
//                        loanAccount2ClientBankAccounts,
//                        getLABankAccount(loanAccount.getLoanAccountType()));
//        
//        //保存扣款任务
//        if (!deductTasks.isEmpty()) {
//            deductTaskService.batchInsert(deductTasks);
//        }
//    }
//    
//    /**
//     * 
//      *获取银行卡信息
//      *<功能详细描述>
//      * @return [参数说明]
//      * 
//      * @return LABankAccount [返回类型说明]
//      * @exception throws [异常类型] [异常说明]
//      * @see [类、类#方法、类#成员]
//     */
//    private LABankAccount getLABankAccount(
//            LoanAccountTypeEnum loanAccountType) {
//        //FIXME 
//        String bankAccountId = "ZZ_HK-" + loanAccountType.getKey();
//        LABankAccount laBankAccount = laBankAccountService
//                .findById(bankAccountId);
//        return laBankAccount;
//    }
//    
//    @Override
//    protected boolean isMatch(BuildDeductTaskByPaymentScheduleRequest request) {
//        return true;
//    }
//}
