//package com.tx.local.loanaccount.task;
//
//import java.util.Date;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//import javax.annotation.Resource;
//
//import org.apache.commons.collections.CollectionUtils;
//import org.joda.time.DateTime;
//import org.joda.time.LocalDate;
//import org.springframework.stereotype.Component;
//
//import com.tx.component.task.timedtask.task.ConcurrentBatchTimedTask;
//import com.tx.local.loanaccount.model.LoanAccount;
//import com.tx.local.loanaccount.model.LoanAccountDailyRecord;
//import com.tx.local.loanaccount.model.LoanAccountDailyRecordTypeEnum;
//import com.tx.local.loanaccount.service.LoanAccountDailyRecordService;
//import com.tx.local.loanaccount.service.LoanAccountService;
//
///**
// * 计算是否逾期定时器<br/>
// * 2.10版本上线，在2016-01-11首次执行
// * @author  lenovo
// * @version  [版本号, 2014年6月11日]
// * @see  [相关类/方法]
// * @since  [产品/模块版本]
// */
//@Component("loanAccountDayEndDailyRecordTask")
//public class LoanAccountDayEndDailyRecordTask extends ConcurrentBatchTimedTask<LoanAccount> {
//    
//    /** 贷款单账户业务  */
//    @Resource(name = "loanAccountService")
//    private LoanAccountService loanAccountService;
//    
//    /** 贷款账户每日记录业务层 */
//    @Resource(name = "loanAccountDailyRecordService")
//    private LoanAccountDailyRecordService loanAccountDailyRecordService;
//    
//    @Override
//    public String getCode() {
//        return "LA_DAY_END_DAILY_RECORD_TASK";
//    }
//    
//    @Override
//    public String getName() {
//        return "贷款账户DayEnd变更记录存储";
//    }
//    
//    @Override
//    public Date execute(Date executeDate) {
//        DateTime executeDateTime = new DateTime(executeDate);
//        executeDateTime = new DateTime(executeDateTime.getYear(),
//                executeDateTime.getMonthOfYear(),
//                executeDateTime.getDayOfMonth(), 0, 0, 0);
//        Map<String, Object> params = new HashMap<String, Object>();
//        params.put("dayEndDailyRecordRecordDate", executeDateTime.toDate());
//        
//        List<LoanAccount> resList = loanAccountService.queryList(params);
//        if (CollectionUtils.isEmpty(resList)) {
//            //返回下次执行时间
//            return executeDateTime.plusDays(1).toDate();
//        }
//        
//        for (LoanAccount laTemp : resList) {
//            String loanAccountId = laTemp.getId();
//            //提取贷款账户日结记录中最后一条
//            LoanAccountDailyRecord sourceLadr = this.loanAccountDailyRecordService.findLastByLoanAccountId(loanAccountId,
//                    LoanAccountDailyRecordTypeEnum.DAY_END,
//                    executeDateTime.toDate());
//            if (sourceLadr != null
//                    && org.apache.commons.lang3.time.DateUtils.isSameDay(sourceLadr.getRecordDate(),
//                            executeDateTime.plusSeconds(-1)
//                            .toDate())) {
//                //如果记录不为空，且记录时间与执行前一天为同一天，则认为数据已经插入过了，进行跳过
//                //该业务发生于，当天事务出现问题后，重新执行时，将已经处理的数据跳过
//                continue;
//            }
//            //构建新的记录
//            LoanAccountDailyRecord newLadr = this.loanAccountDailyRecordService.build4insert(laTemp,
//                    LoanAccountDailyRecordTypeEnum.DAY_END,
//                    new LocalDate(zone)
//                    .toDate(),
//                    new Date());
//            //判断是否需要记录,如果需要则进行添加
//            if (sourceLadr == null
//                    || !this.loanAccountDailyRecordService.isEquals(sourceLadr,
//                            newLadr)) {
//                //最后一条记录不为当前记录时间，并且不等于最后一条记录
//                this.loanAccountDailyRecordService.insert(newLadr);
//            }
//        }
//        
//        //返回下次执行时间
//        return executeDateTime.plusDays(1).toDate();
//    }
//}
