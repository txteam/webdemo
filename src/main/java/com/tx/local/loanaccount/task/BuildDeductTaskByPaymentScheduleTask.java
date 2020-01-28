//package com.tx.local.loanaccount.task;
//
//import java.math.BigDecimal;
//import java.util.ArrayList;
//import java.util.Calendar;
//import java.util.Date;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//import javax.annotation.Resource;
//
//import org.apache.commons.lang3.StringUtils;
//import org.apache.commons.lang3.math.NumberUtils;
//import org.joda.time.DateTime;
//import org.springframework.stereotype.Component;
//
//import com.alibaba.dubbo.common.utils.CollectionUtils;
//import com.tx.component.command.context.CommandContext;
//import com.tx.component.task.timedtask.task.ConcurrentBatchTimedTask;
//import com.tx.core.TxConstants;
//import com.tx.core.exceptions.SILException;
//import com.tx.core.exceptions.util.AssertUtils;
//import com.tx.core.util.DateUtils;
//import com.tx.local.loanaccount.LoanAccountConstants;
//import com.tx.local.loanaccount.context.request.change.ChangeNextDeductDateRequest;
//import com.tx.local.loanaccount.context.request.process.BuildDeductTaskByPaymentScheduleRequest;
//import com.tx.local.loanaccount.model.AccountStatusEnum;
//import com.tx.local.loanaccount.model.LoanAccount;
//import com.tx.local.loanaccount.model.PaymentSchedule;
//import com.tx.local.loanaccount.model.ScheduleAssociateMap;
//import com.tx.local.loanaccount.model.ScheduleTypeEnum;
//import com.tx.local.loanaccount.service.LoanAccountService;
//import com.tx.local.loanaccount.service.PaymentScheduleService;
//
///**
// * 扣款任务定时扫描器<br/>
// * 根据还款计划构建扣交易记录、款任务<br/>
// * 扫描贷款账户
// * 
// * @author lenovo
// * @version [版本号, 2014年6月9日]
// * @see [相关类/方法]
// * @since [产品/模块版本]
// */
//@Component("buildDeductTaskByPaymentScheduleTask")
//public class BuildDeductTaskByPaymentScheduleTask
//        extends ConcurrentBatchTimedTask<LoanAccount> {
//    
//    /** 贷款单账户业务 */
//    @Resource(name = "loanAccountService")
//    private LoanAccountService loanAccountService;
//    
//    /** 还款计划业务 */
//    @Resource(name = "paymentScheduleService")
//    private PaymentScheduleService paymentScheduleService;
//    
//    /**
//     * @return
//     */
//    @Override
//    public String getCode() {
//        return "BUILD_DEDUCT_TASK_BY_PAYMENT_SCHEDULE_TASK";
//    }
//    
//    /**
//     * @return
//     */
//    @Override
//    public String getName() {
//        return "根据还款计划构建扣款任务";
//    }
//    
//    /**
//     * 父级任务编码
//     * @return
//     */
//    @Override
//    public String getParentCode() {
//        return LoanAccountConstants.TASK_PARENT_CODE;
//    }
//    
//    /**
//     * 执行先后顺序
//     * @return
//     */
//    @Override
//    public int getOrder() {
//        return 201;
//    }
//    
//    /**
//     * @param executeDate
//     * @return
//     */
//    @Override
//    public List<LoanAccount> getList(Date executeDate) {
//        // 生成查询条件
//        DateTime executeDateTime = new DateTime(executeDate);
//        // 生成查询条件
//        DateTime maxNextDeductDate = (new DateTime(executeDateTime.getYear(),
//                executeDateTime.getMonthOfYear(),
//                executeDateTime.getDayOfMonth(), 0, 0, 0)).plusDays(1);
//        
//        Map<String, Object> params = new HashMap<String, Object>(
//                TxConstants.INITIAL_MAP_SIZE);
//        params.put("accountStatus", AccountStatusEnum.AC); // 状态为AC
//        params.put("closed", false); // 未关闭
//        params.put("maxNextDeductDate", maxNextDeductDate.toDate()); // 自动还款日小于当天的最后时间
//        
//        List<LoanAccount> loanAccountList = this.loanAccountService
//                .queryList(params);
//        return loanAccountList;
//    }
//    
//    /**
//     * @param executeDate
//     * @return
//     */
//    @Override
//    public Date getNextDate(Date executeDate) {
//        // 生成查询条件
//        DateTime executeDateTime = new DateTime(executeDate);
//        
//        // 每日执行一次
//        DateTime nextDxecuteDateTime = (new DateTime(executeDateTime.getYear(),
//                executeDateTime.getMonthOfYear(),
//                executeDateTime.getDayOfMonth(), 0, 0, 0)).plusDays(1);
//        
//        return nextDxecuteDateTime.toDate();
//    }
//    
//    /**
//     * @param loanAccount
//     * @param executeDate
//     */
//    @Override
//    public void execute(LoanAccount loanAccount, Date executeDate) {
//        // 合法性验证
//        AssertUtils.notNull(loanAccount, "loanAccount is null .");
//        AssertUtils.notEmpty(loanAccount.getId(), "loanAccount.id is empty .");
//        AssertUtils.notEmpty(executeDate, "executeDate is empty .");
//        
//        //贷款账户id
//        String loanAccountId = loanAccount.getId();
//        List<PaymentSchedule> paymentScheduleList = this.paymentScheduleService
//                .queryListByLoanAccountId(loanAccountId, null);
//        ScheduleAssociateMap<PaymentSchedule> paymentScheduleMap = new ScheduleAssociateMap<>(
//                paymentScheduleList);
//        
//        //更新贷款账户的下次扣款日
//        Date nextDeductDate = getNextDeductDate(loanAccount,
//                paymentScheduleMap,
//                executeDate);
//        ChangeNextDeductDateRequest changeNDDRequest = new ChangeNextDeductDateRequest(
//                loanAccountId, nextDeductDate);
//        CommandContext.getContext().post(changeNDDRequest);
//        
//        //需构建扣款任务的还款计划列表（日期小于传入时间，或等于传入时间的当日）
//        List<PaymentSchedule> receivablePaymentScheduleList = getNeedBuildDeductTaskPaymentScheduleList(
//                paymentScheduleList, executeDate);
//        if (CollectionUtils.isEmpty(receivablePaymentScheduleList)) {
//            //如果需要生成扣款任务的还款计划列表为空则直接返回
//            return;
//        }
//        //构建交易记录、扣款计划
//        CommandContext.getContext()
//                .post(new BuildDeductTaskByPaymentScheduleRequest(
//                        loanAccount.getId(), executeDate,
//                        receivablePaymentScheduleList));
//        
//    }
//    
//    /** 
//     * 获取需要生成扣款任务的还款计划集合<br/>
//     * <功能详细描述>
//     * @param executeDate
//     * @param paymentScheduleList
//     * @param temPaymentScheduleList [参数说明]
//     * 
//     * @return void [返回类型说明]
//     * @exception throws [异常类型] [异常说明]
//     * @see [类、类#方法、类#成员]
//     */
//    private List<PaymentSchedule> getNeedBuildDeductTaskPaymentScheduleList(
//            List<PaymentSchedule> paymentScheduleList, Date executeDate) {
//        List<PaymentSchedule> needBuildDeductTaskPaymentScheduleList = new ArrayList<>(
//                TxConstants.INITIAL_CONLLECTION_SIZE);
//        for (PaymentSchedule paymentSchedule : paymentScheduleList) {
//            if (!NumberUtils.isParsable(paymentSchedule.getPeriod())) {
//                continue;
//            }
//            if (!ScheduleTypeEnum.MAIN
//                    .equals(paymentSchedule.getScheduleType())) {
//                //只获取主计划中需要构建扣款任务的还款计划项
//                continue;
//            }
//            //到期还款日大于传入日期当天的23:59:59的时间<=的都会生成扣款任务<br/>
//            if (DateUtils.compareByDay(paymentSchedule.getRepaymentDate(),
//                    executeDate) > 0) {
//                continue;
//            }
//            
//            BigDecimal receivable = paymentSchedule.getReceivableSum()
//                    .add(paymentSchedule.getExemptSum())
//                    .subtract(paymentSchedule.getActualReceivedSum());
//            // 如果应收加豁免减实收小于等于0
//            if (receivable.compareTo(BigDecimal.ZERO) <= 0) {
//                continue;
//            }
//            
//            needBuildDeductTaskPaymentScheduleList.add(paymentSchedule);
//        }
//        return needBuildDeductTaskPaymentScheduleList;
//    }
//    
//    /**
//     * 根据还款计划重新计算下次还款日<br/>
//     * <功能详细描述>
//     * 
//     * @return [参数说明]
//     *         
//     * @return Date [返回类型说明]
//     * @exception throws [异常类型] [异常说明]
//     * @see [类、类#方法、类#成员]
//     */
//    //TODO:下次扣款日在不同的场景可能不同，较长逾期的客户，可能需要，每日都进行试扣，需要衡量试扣账户是独立定时任务，还是并入该逻辑
//    public Date getNextDeductDate(LoanAccount loanAccount,
//            ScheduleAssociateMap<PaymentSchedule> paymentScheduleMap,
//            Date executeDate) {
//        DateTime executeDateTime = new DateTime(executeDate);
//        
//        String nextPeriod = "1";
//        Date nextRepayDate = null;
//        while (!StringUtils.isBlank(nextPeriod)) {
//            PaymentSchedule paymentSchedule = paymentScheduleMap
//                    .get(ScheduleTypeEnum.MAIN, nextPeriod);
//            
//            nextRepayDate = paymentSchedule.getRepaymentDate();
//            //当前期数
//            if (DateUtils.compareByDay(paymentSchedule.getRepaymentDate(),
//                    executeDate) > 0) {
//                break;
//            }
//            nextPeriod = paymentSchedule.getNextPeriod();
//        }
//        
//        //当所有还款计划均已到期的情况下这个值可能为空
//        if (DateUtils.compareByDay(nextRepayDate, executeDate) < 0) {
//            switch (loanAccount.getTimeUnitType()) {
//                case MONTH:
//                    DateTime nextRepayDateTime = null;
//                    if (executeDateTime.getDayOfMonth() < loanAccount.getMonthlyRepayDay()) {
//                        //如果当天尚未到当月的每月还款日，则构建当月的还款日期
//                        Calendar cale = Calendar.getInstance();
//                        cale.setTime(executeDateTime.toDate());
//                        if (loanAccount.getMonthlyRepayDay() <= cale
//                                .getActualMaximum(Calendar.DAY_OF_MONTH)) {
//                            nextRepayDateTime = new DateTime(executeDateTime.getYear(),
//                                    executeDateTime.getMonthOfYear(),
//                                    loanAccount.getMonthlyRepayDay(), 23, 59, 59);
//                        } else {
//                            nextRepayDateTime = new DateTime(executeDateTime.getYear(),
//                                    executeDateTime.getMonthOfYear(),
//                                    cale.getActualMaximum(Calendar.DAY_OF_MONTH), 23,
//                                    59, 59);
//                        }
//                    } else {
//                        DateTime nextMonthDay = executeDateTime.plusMonths(1);
//                        //如果当天尚未到当月的每月还款日，则构建当月的还款日期
//                        Calendar cale = Calendar.getInstance();
//                        cale.setTime(nextMonthDay.toDate());
//                        
//                        if (loanAccount.getMonthlyRepayDay() <= cale
//                                .getActualMaximum(Calendar.DAY_OF_MONTH)) {
//                            nextRepayDateTime = new DateTime(nextMonthDay.getYear(),
//                                    nextMonthDay.getMonthOfYear(),
//                                    loanAccount.getMonthlyRepayDay(), 23, 59, 59);
//                        } else {
//                            nextRepayDateTime = new DateTime(nextMonthDay.getYear(),
//                                    nextMonthDay.getMonthOfYear(),
//                                    cale.getActualMaximum(Calendar.DAY_OF_MONTH), 23,
//                                    59, 59);
//                        }
//                    }
//                    nextRepayDate = nextRepayDateTime.toDate();
//                    break;
//                default:
//                    throw new SILException("暂不支持的时间类型.timeUnitType:"
//                            + loanAccount.getTimeUnitType());
//            }
//        }
//        
//        return nextRepayDate;
//    }
//}
