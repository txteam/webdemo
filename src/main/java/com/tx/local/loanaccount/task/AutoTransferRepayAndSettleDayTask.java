//package com.tx.local.loanaccount.task;
//
//import java.math.BigDecimal;
//import java.util.Calendar;
//import java.util.Date;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//import javax.annotation.Resource;
//
//import org.apache.commons.collections.CollectionUtils;
//import org.apache.commons.lang3.time.DateUtils;
//import org.joda.time.DateTime;
//import org.springframework.stereotype.Component;
//
//import com.tx.component.command.context.CommandContext;
//import com.tx.component.task.dailytask.task.SimpleTask;
//import com.tx.local.loanaccount.context.request.process.AutoTransferRepayAndSettleDispatcherRequest;
//import com.tx.local.loanaccount.model.AccountStatusEnum;
//import com.tx.local.loanaccount.model.LoanAccount;
//import com.tx.local.loanaccount.model.PaymentSchedule;
//import com.tx.local.loanaccount.model.RepayIntention;
//import com.tx.local.loanaccount.model.RequestSourceTypeEnum;
//import com.tx.local.loanaccount.service.LoanAccountService;
//import com.tx.local.loanaccount.service.PaymentScheduleService;
//
//@Component("transferRepayAndSettleDayTask")
//public class AutoTransferRepayAndSettleDayTask extends SimpleTask {
//    
//    @Resource(name = "loanAccountService")
//    private LoanAccountService accountService;
//    
//    @Resource(name = "paymentScheduleService")
//    private PaymentScheduleService paymentScheduleService;
//    
//    @Override
//    public String getCode() {
//        return "DAY_START_LA_TRANSFER_REPAY_SETTLE_TASK";
//    }
//    
//    @Override
//    public String getName() {
//        return "融资客户自动还款|结算投资客户收益";
//    }
//    
//    @Override
//    public Date execute(Date executeDate) {
//        Date exeDate = DateUtils.truncate(executeDate, Calendar.DATE);
//        DateTime executeDateTime = new DateTime(executeDate);
//        executeDateTime = new DateTime(executeDateTime.getYear(),
//                executeDateTime.getMonthOfYear(),
//                executeDateTime.getDayOfMonth(),
//                0, 0, 0);
//        DateTime maxCurrentPeriodExpireDateTime = executeDateTime.plusDays(1);
//        
//        DateTime nextExecuteDateTime = executeDateTime.plusDays(1);
//        nextExecuteDateTime = new DateTime(nextExecuteDateTime.getYear(),
//                nextExecuteDateTime.getMonthOfYear(),
//                nextExecuteDateTime.getDayOfMonth(), 15, 0, 0);
//        
//        Map<String, Object> params = new HashMap<String, Object>();
//        //params.put("maxNextRepayDate", maxNextRepayDate.toDate());
//        params.put("accountStatus", AccountStatusEnum.AC); // 状态为AC
//        params.put("closed", false); // 未关闭
//        params.put("locked", false); // 未锁定
//        params.put("maxCurrentPeriodExpireDate",
//                maxCurrentPeriodExpireDateTime.toDate());
//        
//        List<LoanAccount> loanAccountList = accountService.queryList(params);
//        if (CollectionUtils.isEmpty(loanAccountList)) {
//            //返回下次执行时间
//            return nextExecuteDateTime.toDate();
//        }
//        
//        for (LoanAccount tempLoanAccount : loanAccountList) {
//            //还款计划
//            List<PaymentSchedule> psList = paymentScheduleService.queryPaymentScheduleListByLoanAccountId(tempLoanAccount.getId());
//            
//            BigDecimal amount = BigDecimal.ZERO;
//            for (PaymentSchedule tempPaymentSchedule : psList) {
//                Date shouldRepayDate = tempPaymentSchedule.getRepaymentDate();
//                if (shouldRepayDate != null
//                        && maxCurrentPeriodExpireDateTime.toDate()
//                                .compareTo(DateUtils.truncate(shouldRepayDate,
//                                        5)) > 0) {
//                    //本次还款金额
//                    amount = tempPaymentSchedule.getReceivableSum()
//                            .add(tempPaymentSchedule.getExemptSum())
//                            .subtract(tempPaymentSchedule.getActualReceivedSum())
//                            .add(amount);
//                }
//            }
//            if (BigDecimal.ZERO.compareTo(amount) == 0) {
//                //返回下次执行时间
//                return executeDateTime.plusDays(1).toDate();
//            }
//            
//            RepayIntention repayIntention = new RepayIntention();
//            repayIntention.setLoanAccountId(tempLoanAccount.getId());
//            repayIntention.setAmount(amount);
//            AutoTransferRepayAndSettleDispatcherRequest atrRquest = new AutoTransferRepayAndSettleDispatcherRequest(
//                    tempLoanAccount.getId(), repayIntention,
//                    RequestSourceTypeEnum.夜间事务);
//            CommandContext.getContext().post(atrRquest);
//        }
//        
//        //返回下次执行时间
//        return nextExecuteDateTime.toDate();
//    }
//    
//}
