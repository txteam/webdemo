///*
// * 描          述:  <描述>
// * 修  改   人:  Administrator
// * 修改时间:  2014年11月27日
// * <修改描述:>
// */
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
//import com.tx.component.task.context.task.PagedBatchTask;
//import com.tx.core.exceptions.util.AssertUtils;
//import com.tx.core.util.DateUtils;
//import com.tx.lms.loanaccount.context.LoanAccountContext;
//import com.tx.lms.loanaccount.model.LoanAccount;
//import com.tx.lms.loanaccount.model.LoanAccountProcessStatusEnum;
//import com.tx.lms.loanaccount.model.RequestSourceTypeEnum;
//import com.tx.lms.loanaccount.model.SettleInterestStatusEnum;
//import com.tx.lms.loanaccount.request.change.ChangeSettleInterestStatusRequest;
//import com.tx.lms.loanaccount.service.LoanAccountService;
//import com.tx.lms.tax.TaxConstants;
//import com.tx.lms.tax.settle.service.TaxSettlementScheduleService;
//
///**
// * 对逾期账户进行检测，如果对应的逾期账户已经逾期>=91天，则对该账户进行停息处理 修改贷款账户的，结息状态为停息
// * 
// * @author Administrator
// * @version [版本号, 2014年11月27日]
// * @see [相关类/方法]
// * @since [产品/模块版本]
// */
//@Component("stopSettleInterestCheckTask")
//public class StopTaxSettlementTask extends PagedBatchTask<LoanAccount> {
//    
//    @Resource(name = "taxSettlementScheduleService")
//    private TaxSettlementScheduleService taxSettlementScheduleService;
//    
//    /** 贷款单账户业务 */
//    @Resource(name = "loanAccountService")
//    private LoanAccountService loanAccountService;
//    
//    @Override
//    @Transactional(propagation = Propagation.REQUIRED)
//    public void execute(LoanAccount loanAccount, Date executeDate) {
//        if (SettleInterestStatusEnum.停息.equals(loanAccount.getSettleInterestStatus())) {
//            return;
//        }
//        if (DateUtils.calculateNumberOfDaysBetween(executeDate,
//                loanAccount.getOverdueDate()) < TaxConstants.STOP_TAX_SETTLE_DAYS) {
//            return;
//        }
//        ChangeSettleInterestStatusRequest request = new ChangeSettleInterestStatusRequest(
//                loanAccount, SettleInterestStatusEnum.停息,
//                RequestSourceTypeEnum.夜间事务);
//        LoanAccountContext.getContext().post(request);
//    }
//    
//    @Override
//    public int getCount(Date executeDate) {
//        Map<String, Object> params = new HashMap<String, Object>();
//        params.put("overdue", true); //查询逾期的账户
//        params.put("processStatus", LoanAccountProcessStatusEnum.正常);
//        
//        int count = loanAccountService.countLoanAccount(params);
//        
//        return count;
//    }
//    
//    @Override
//    public List<LoanAccount> getDatas(Date executeDate, int pageIndex,
//            int pageSize) {
//        Map<String, Object> params = new HashMap<String, Object>();
//        params.put("overdue", true); // 未标志逾期
//        params.put("processStatus", LoanAccountProcessStatusEnum.正常);
//        
//        List<LoanAccount> resList = loanAccountService.queryLoanAccountPagedList(params,
//                pageIndex,
//                pageSize)
//                .getList();
//        return resList;
//    }
//    
//    @Override
//    public String getKey() {
//        return "STOP_SETTLE_INTEREST_CHECK_TASK";
//    }
//    
//    @Override
//    public String getName() {
//        return "停息账户检查";
//    }
//    
//    /**
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
//}
