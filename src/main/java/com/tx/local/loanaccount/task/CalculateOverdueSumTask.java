//package com.tx.local.loanaccount.task;
//
//import java.math.BigDecimal;
//import java.util.Date;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//import javax.annotation.Resource;
//
//import org.joda.time.DateTime;
//import org.springframework.transaction.annotation.Propagation;
//import org.springframework.transaction.annotation.Transactional;
//
//import com.tx.component.event.context.EventContext;
//import com.tx.component.task.context.task.PagedBatchTask;
//import com.tx.core.exceptions.util.AssertUtils;
//import com.tx.local.loanaccount.event.change.OverdueAmountChangeEvent;
//import com.tx.local.loanaccount.handle.PaymentScheduleHandler;
//import com.tx.local.loanaccount.handle.PaymentScheduleHandlerFactory;
//import com.tx.local.loanaccount.helper.caculator.AmountCaculatorFacotry;
//import com.tx.local.loanaccount.helper.caculator.AmountCaculatorTypeEnum;
//import com.tx.local.loanaccount.model.AccountStatusEnum;
//import com.tx.local.loanaccount.model.LoanAccount;
//import com.tx.local.loanaccount.service.LoanAccountFeeCfgItemService;
//import com.tx.local.loanaccount.service.LoanAccountService;
//import com.tx.local.loanaccount.service.PaymentScheduleEntryService;
//import com.tx.local.loanaccount.service.PaymentScheduleService;
//
///**
// * 计算逾期贷款账户逾期金额的定时任务<br/>
// *    算法描述：每日夜，分页查询贷款账户
// *    将逾期的贷款账户，逐个进行核算，计算其逾期金额以及逾期总额<br/>
// * 
// * @author  lenovo
// * @version  [版本号, 2014年6月11日]
// * @see  [相关类/方法]
// * @since  [产品/模块版本]
// */
//public class CalculateOverdueSumTask extends PagedBatchTask<LoanAccount> {
//    
//    /** 贷款单账户业务  */
//    @Resource(name = "loanAccountService")
//    private LoanAccountService loanAccountService;
//    
//    /** 还款计划业务 */
//    @Resource(name = "paymentScheduleService")
//    private PaymentScheduleService paymentScheduleService;
//    
//    /** 贷款单账户费用配置项目业务  */
//    @Resource(name = "loanAccountFeeCfgItemService")
//    private LoanAccountFeeCfgItemService loanAccountFeeCfgItemService;
//    
//    /** 还款计划分项 */
//    @Resource(name = "paymentScheduleEntryService")
//    private PaymentScheduleEntryService paymentScheduleEntryService;
//    
//    @Override
//    public String getKey() {
//        return "CALCULATE_OVERDUESUM_TASK";
//    }
//    
//    @Override
//    public String getName() {
//        return "设置贷款账户逾期总额";
//    }
//    
//    /**
//     * @param executeDate
//     * @return
//     */
//    @Override
//    public int getCount(Date executeDate) {
//        Map<String, Object> params = new HashMap<String, Object>();
//        params.put("accountStatus", AccountStatusEnum.AC); // 账户状态为AC
//        params.put("overdue", true); //未标志逾期
//        params.put("closed", false); //未关闭
//        
//        int count = loanAccountService.countLoanAccount(params);
//        
//        return count;
//    }
//    
//    /**
//     * @param executeDate
//     * @param pageIndex
//     * @param pageSize
//     * @return
//     */
//    @Override
//    public List<LoanAccount> getDatas(Date executeDate, int pageIndex,
//            int pageSize) {
//        Map<String, Object> params = new HashMap<String, Object>();
//        params.put("accountStatus", AccountStatusEnum.AC); // 账户状态为AC
//        params.put("overdue", true); // 未标志逾期
//        params.put("closed", false); //未关闭
//        
//        List<LoanAccount> resList = loanAccountService.queryLoanAccountPagedList(params,
//                pageIndex,
//                pageSize)
//                .getList();
//        
//        return resList;
//    }
//    
//    /**
//     * 返回下次调度时间<br/>
//     * @param executeDate
//     * @return
//     */
//    @Override
//    public Date getNextDate(Date executeDate) {
//        AssertUtils.notNull(executeDate, "executeDate is null .");
//        
//        DateTime executeDateTime = new DateTime(executeDate);
//        executeDateTime = new DateTime(executeDateTime.getYear(),
//                executeDateTime.getMonthOfYear(),
//                executeDateTime.getDayOfMonth(), 0, 0, 0);
//        return executeDateTime.plusDays(1).toDate();
//    }
//    
//    /**
//     * 根据调度时间判断贷款账户是否逾期<br/>
//     * @param loanAccount
//     * @param executeDate
//     */
//    @Override
//    @Transactional(propagation = Propagation.REQUIRED)
//    public void execute(LoanAccount loanAccount, Date executeDate) {
//        AssertUtils.notNull(loanAccount, "loanAccount is null.");
//        AssertUtils.notNull(executeDate, "executeDate is null.");
//        
//        PaymentScheduleHandler handler = PaymentScheduleHandlerFactory.buildPaymentScheduleHandlerByLoanAccountId(loanAccount.getId());
//        BigDecimal overdueAmount = AmountCaculatorFacotry.buildAmountCaculator(loanAccount.getLoanAccountType(),
//                AmountCaculatorTypeEnum.OVERDUE_AMOUNT,
//                handler)
//                .caculate(executeDate);
//        BigDecimal overdueSum = AmountCaculatorFacotry.buildAmountCaculator(loanAccount.getLoanAccountType(),
//                AmountCaculatorTypeEnum.OVERDUE_SUM,
//                handler)
//                .caculate(executeDate);
//        
//        
//        this.loanAccountService.updateOverdueAmountAndSum(loanAccount.getId(),
//                overdueAmount,
//                overdueSum);
//        
//        if (loanAccount.getOverdueAmount().compareTo(overdueAmount) != 0) {
//            EventContext.getContext()
//            .trigger(new OverdueAmountChangeEvent(loanAccount,
//                    loanAccount.getOverdueAmount(), overdueAmount));
//        }
//    }
//}
