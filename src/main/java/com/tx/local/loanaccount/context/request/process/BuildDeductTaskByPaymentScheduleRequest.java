package com.tx.local.loanaccount.context.request.process;

import java.util.Date;
import java.util.List;

import com.tx.local.loanaccount.model.PaymentSchedule;
import com.tx.local.loanaccount.model.RequestSourceTypeEnum;

/**
  * 根据扣款日期对应的需还款的还款计划项请求构建扣款交易记录<br/>
  *    1、需要确保以下几个场景正常：当期已经扣款（需要生成0条）
  *    2、当期扣款：生成1条
  *    3、多期扣款：已经逾期的情况 
  * <功能详细描述>
  * 
  * @author  Administrator
  * @version  [版本号, 2015年1月22日]
  * @see  [相关类/方法]
  * @since  [产品/模块版本]
 */
public class BuildDeductTaskByPaymentScheduleRequest extends
        AbstractProcessRequest {
    
    /** 执行日期 */
    private final Date executeDate;
    
    /** 应收还款计划列表 */
    private final List<PaymentSchedule> receivablePaymentScheduleList;
    
    /** 根据还款计划进行扣款 */
    public BuildDeductTaskByPaymentScheduleRequest(String loanAccountId,
            Date executeDate,
            List<PaymentSchedule> receivablePaymentScheduleList) {
        super(loanAccountId, RequestSourceTypeEnum.AUTO_SCHEDULE_REQUEST);
        
        this.executeDate = executeDate;
        this.receivablePaymentScheduleList = receivablePaymentScheduleList;
    }
    
    /**
     * @return 返回 executeDate
     */
    public Date getExecuteDate() {
        return executeDate;
    }
    
    /**
     * @return 返回 receivablePaymentScheduleList
     */
    public List<PaymentSchedule> getReceivablePaymentScheduleList() {
        return receivablePaymentScheduleList;
    }
    
}
