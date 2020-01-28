//package com.tx.local.loanaccount.task;
//
//import java.util.Date;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//import javax.annotation.Resource;
//
//import org.joda.time.DateTime;
//import org.springframework.stereotype.Component;
//import org.springframework.transaction.annotation.Propagation;
//import org.springframework.transaction.annotation.Transactional;
//
//import com.tx.component.command.context.CommandContext;
//import com.tx.component.task.timedtask.task.ConcurrentBatchTimedTask;
//import com.tx.core.exceptions.util.AssertUtils;
//import com.tx.local.loanaccount.LoanAccountConstants;
//import com.tx.local.loanaccount.context.request.change.ChangeNextOverdueCheckDateRequest;
//import com.tx.local.loanaccount.context.request.change.ChangeToOverdueRequest;
//import com.tx.local.loanaccount.handle.PaymentScheduleHandler;
//import com.tx.local.loanaccount.handle.PaymentScheduleHandlerHelper;
//import com.tx.local.loanaccount.model.AccountStatusEnum;
//import com.tx.local.loanaccount.model.LoanAccount;
//import com.tx.local.loanaccount.model.RequestSourceTypeEnum;
//import com.tx.local.loanaccount.service.LoanAccountService;
//
///**
// * 计算是否逾期定时器<br/>
// * 
// * @author  lenovo
// * @version  [版本号, 2014年6月11日]
// * @see  [相关类/方法]
// * @since  [产品/模块版本]
// */
//@Component("task.loanAccountOverdueCheckTask")
//public class LoanAccountOverdueCheckTask
//        extends ConcurrentBatchTimedTask<LoanAccount> {
//    
//    /** 贷款单账户业务  */
//    @Resource(name = "loanAccountService")
//    private LoanAccountService loanAccountService;
//    
//    /** 还款计划业务 */
//    @Resource(name = "paymentScheduleHandlerHelper")
//    private PaymentScheduleHandlerHelper paymentScheduleHandlerHelper;
//    
//    /**
//     * 父级任务编码<br/>
//     * @return
//     */
//    @Override
//    public String getParentCode() {
//        return LoanAccountConstants.TASK_PARENT_CODE;
//    }
//    
//    /**
//     * 任务编码<br/>
//     * @return
//     */
//    @Override
//    public String getCode() {
//        return "LOANACCOUNT_OVERDUE_CHECK_TASK";
//    }
//    
//    /**
//     * 任务名称<br/>
//     * @return
//     */
//    @Override
//    public String getName() {
//        return "贷款账户逾期检测";
//    }
//    
//    /**
//     * @return
//     */
//    @Override
//    public int getOrder() {
//        return 101;
//    }
//    
//    /**
//     * @param executeDate
//     * @return
//     */
//    @Override
//    public List<LoanAccount> getList(Date executeDate) {
//        // 验证合法性
//        AssertUtils.notNull(executeDate, "executeDate is null .");
//        
//        DateTime executeDateTime = new DateTime(executeDate);
//        executeDateTime = new DateTime(executeDateTime.getYear(),
//                executeDateTime.getMonthOfYear(),
//                executeDateTime.getDayOfMonth(), 0, 0, 0);
//        
//        Map<String, Object> params = new HashMap<String, Object>();
//        params.put("accountStatus", AccountStatusEnum.AC); // 账户状态为AC
//        params.put("overdue", false); // 未标志逾期
//        params.put("closed", false); //未关闭
//        params.put("locked", false); // 未锁定
//        params.put("maxNextOverdueCheckDate", executeDateTime.toDate()); //未关闭
//        
//        List<LoanAccount> resList = loanAccountService.queryList(params);
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
//        String loanAccountId = loanAccount.getId();
//        
//        //更新贷款账户的下次逾期检查日为第二日
//        DateTime executeDateTime = new DateTime(executeDate);
//        DateTime nextOverdueCheckDateTime = (new DateTime(
//                executeDateTime.getYear(), executeDateTime.getMonthOfYear(),
//                executeDateTime.getDayOfMonth(), 0, 0, 0)).plusDays(1);
//        ChangeNextOverdueCheckDateRequest changeNOCDRequest = new ChangeNextOverdueCheckDateRequest(
//                loanAccountId, nextOverdueCheckDateTime.toDate());
//        CommandContext.getContext().post(changeNOCDRequest);
//        
//        PaymentScheduleHandler handler = paymentScheduleHandlerHelper
//                .buildPaymentScheduleHandler(loanAccount);
//        boolean isOverdue = handler.isOverdue(executeDateTime.toDate());
//        
//        if (!isOverdue) {
//            //如果未逾期
//            return;
//        }
//        
//        // 如果发生逾期
//        Date overdueDate = executeDateTime.toDate();
//        AssertUtils.notNull(overdueDate, "overdueDate is null.");
//        ChangeToOverdueRequest changeToOverdueRequest = new ChangeToOverdueRequest(
//                loanAccountId, overdueDate,
//                RequestSourceTypeEnum.AUTO_SCHEDULE_REQUEST);
//        CommandContext.getContext().post(changeToOverdueRequest);
//    }
//}
