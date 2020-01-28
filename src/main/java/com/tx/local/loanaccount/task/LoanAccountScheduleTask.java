///*
// * 描          述:  <描述>
// * 修  改   人:  Administrator
// * 修改时间:  2017年10月20日
// * <修改描述:>
// */
//package com.tx.local.loanaccount.task;
//
//import java.util.Date;
//import java.util.List;
//
//import javax.annotation.Resource;
//
//import org.apache.commons.collections4.CollectionUtils;
//import org.apache.commons.lang3.time.DateFormatUtils;
//import org.joda.time.DateTime;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.InitializingBean;
//import org.springframework.scheduling.annotation.Scheduled;
//import org.springframework.stereotype.Component;
//import org.springframework.transaction.PlatformTransactionManager;
//import org.springframework.transaction.TransactionDefinition;
//import org.springframework.transaction.TransactionStatus;
//import org.springframework.transaction.support.DefaultTransactionDefinition;
//import org.springframework.transaction.support.TransactionCallbackWithoutResult;
//import org.springframework.transaction.support.TransactionTemplate;
//
//import com.tx.component.task.annotations.Task;
//import com.tx.component.task.context.TaskContext;
//import com.tx.component.task.context.TaskSessionContext;
//import com.tx.component.task.interceptor.TaskContextRegistry;
//import com.tx.component.task.model.TaskDef;
//import com.tx.component.task.model.TaskStatus;
//import com.tx.component.task.service.TaskStatusService;
//import com.tx.core.exceptions.SILException;
//import com.tx.local.loanaccount.LoanAccountConstants;
//
///**
// * 贷款账户调度任务<br/>
// * <功能详细描述>
// * 
// * @author  Administrator
// * @version  [版本号, 2017年10月20日]
// * @see  [相关类/方法]
// * @since  [产品/模块版本]
// */
//@Component("loanAccountScheduleTask")
//public class LoanAccountScheduleTask implements InitializingBean {
//    
//    /** 日志记录器 */
//    private Logger logger = LoggerFactory.getLogger(LoanAccountScheduleTask.class);
//    
//    /** 任务容器 */
//    @Resource(name = "taskContext")
//    private TaskContext taskContext;
//    
//    @Resource(name = "transactionManager")
//    private PlatformTransactionManager transactionManager;
//    
//    private TransactionTemplate transactionTemplate;
//    
//    private TaskContextRegistry taskContextRegistry;
//    
//    private TaskStatusService TaskStatusService;
//    
//    /**
//     * @throws Exception
//     */
//    @Override
//    public void afterPropertiesSet() throws Exception {
//        this.taskContextRegistry = taskContext.getTaskContextRegistry();
//        this.TaskStatusService = taskContext.getTaskStatusService();
//        
//        this.transactionTemplate = new TransactionTemplate(transactionManager,
//                new DefaultTransactionDefinition(TransactionDefinition.PROPAGATION_REQUIRES_NEW));
//    }
//    
//    /**
//     * 贷款账户定义任务调度<br/>
//     * <功能详细描述> [参数说明]
//     * 
//     * @return void [返回类型说明]
//     * @exception throws [异常类型] [异常说明]
//     * @see [类、类#方法、类#成员]
//     */
//    //每日凌晨20分自动调度
//    @Scheduled(cron = "0 20 0 * * ?")
////    @Scheduled(cron="0/5 * *  * * ? ") 
//    @Task(code = LoanAccountConstants.TASK_PARENT_CODE, name = "贷款账户调度任务")
//    public void schedule() {
//        Date nextFireDate = TaskSessionContext.getSession().getNextFireDate();
//        Date now = new Date();
//        if (nextFireDate != null && now.compareTo(nextFireDate) <= 0) {
//            //如果尚未到下次执行时间，则跳过执行
//            logger.info("尚未到下次执行时间，当前时间:{} 下次执行时间:{}",
//                    new Object[] { DateFormatUtils.format(now, "yyyy-MM-dd HH:mm:ss"),
//                            DateFormatUtils.format(nextFireDate, "yyyy-MM-dd HH:mm:ss") });
//            return;
//        }
//        List<TaskDef> tdList = this.taskContextRegistry.getTaskDefsByParentCode(LoanAccountConstants.TASK_PARENT_CODE);
//        if (CollectionUtils.isEmpty(tdList)) {
//            return;
//        }
//        
//        //在贷款账户这种特定情形下，需要任务一天一天地向后执行
//        DateTime nextFireDateTime = new DateTime(nextFireDate);
//        //待下次执行时间到了开始执行
//        while (now.compareTo(nextFireDateTime.toDate()) > 0) {
//            final DateTime executeDateTime = new DateTime(nextFireDateTime.getYear(), nextFireDateTime.getMonthOfYear(),
//                    nextFireDateTime.getDayOfMonth(), 0, 0, 0);
//            
//            try {
//                this.transactionTemplate.execute(new TransactionCallbackWithoutResult() {
//                    @Override
//                    protected void doInTransactionWithoutResult(TransactionStatus status) {
//                        doExecute(tdList, executeDateTime);
//                    }
//                });
//            } catch (SILException e) {
//                logger.error("事务执行异常:" + e.getMessage());
//                //任务仍然显示为成功，但是从当前循环中跳出
//                break;
//            } catch (Exception e) {
//                logger.error("事务执行异常.", e);
//                //任务仍然显示为成功，但是从当前循环中跳出
//                break;
//            }
//            nextFireDateTime = nextFireDateTime.plusDays(1);
//        }
//        
//        //设置下次执行时间
//        TaskSessionContext.getSession().setNextFireDate(nextFireDateTime.toDate());
//    }
//    
//    /** 
//     * 任务执行<br/>
//     * <功能详细描述>
//     * @param nextFireDate
//     * @param now
//     * @param tdList
//     * @param nextFireDateTime
//     * @param executeDateTime [参数说明]
//     * 
//     * @return void [返回类型说明]
//     * @exception throws [异常类型] [异常说明]
//     * @see [类、类#方法、类#成员]
//     */
//    private void doExecute(List<TaskDef> tdList, DateTime executeDateTime) {
//        Date now = new Date();
//        for (TaskDef taskDef : tdList) {
//            String taskId = taskDef.getId();
//            TaskStatus taskStatus = this.TaskStatusService.findByTaskId(taskId);
//            if (taskStatus.getNextFireDate() != null
//                    && executeDateTime.toDate().compareTo(taskStatus.getNextFireDate()) <= 0) {
//                //如果尚未到下次执行时间，则跳过执行
//                logger.info("任务:code:{} name:{} .尚未到下次执行时间，当前时间:{} 下次执行时间:{}",
//                        new Object[] { taskDef.getCode(), taskDef.getName(),
//                                DateFormatUtils.format(now, "yyyy-MM-dd HH:mm:ss"),
//                                DateFormatUtils.format(taskStatus.getNextFireDate(), "yyyy-MM-dd HH:mm:ss") });
//                continue;
//            }
//            taskContextRegistry.executeByTaskId(taskDef.getId(), executeDateTime.toDate());
//        }
//    }
//}
