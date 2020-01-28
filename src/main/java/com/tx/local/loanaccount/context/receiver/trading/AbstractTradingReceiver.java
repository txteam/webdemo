/*
 * 描          述:  <描述>
 * 修  改   人:  Administrator
 * 修改时间:  2014年4月10日
 * <修改描述:>
 */
package com.tx.local.loanaccount.context.receiver.trading;

import java.util.List;

import org.springframework.core.Ordered;
import org.springframework.core.PriorityOrdered;

import com.tx.component.command.context.CommandResponse;
import com.tx.local.loanaccount.LoanAccountConstants;
import com.tx.local.loanaccount.context.receiver.AbstractLoanAccountReceiver;
import com.tx.local.loanaccount.context.request.trading.AbstractTradingRequest;
import com.tx.local.loanaccount.handle.PaymentScheduleHandler;
import com.tx.local.loanaccount.model.ChargeRecord;
import com.tx.local.loanaccount.model.ChargeRecordEntry;
import com.tx.local.loanaccount.model.ExemptRecord;
import com.tx.local.loanaccount.model.ExemptRecordEntry;
import com.tx.local.loanaccount.model.LATradingRecord;
import com.tx.local.loanaccount.model.LATradingRecordEntry;
import com.tx.local.loanaccount.model.LoanAccount;
import com.tx.local.loanaccount.model.PaymentRecord;
import com.tx.local.loanaccount.model.PaymentRecordEntry;

/**
 * 贷款账户交易基础接收器
 * 
 * @author  Tim.peng
 * @version  [版本号, 2014年4月10日]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public abstract class AbstractTradingReceiver<PR extends AbstractTradingRequest> extends AbstractLoanAccountReceiver<PR>
        implements PriorityOrdered {
    
    /**
     * @return
     */
    @Override
    public int getOrder() {
        return Ordered.LOWEST_PRECEDENCE;
    }
    
    /**
     * 判断是否匹配<br/>
     * <功能详细描述>
     * @param request
     * @return [参数说明]
     * 
     * @return boolean [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
    */
    protected abstract boolean isMatch(PR request);
    
    /**
     * 校验请求合法性<br/>
     *
     * @param request 交易请求器
     * @param response 操作返回对象
     * @param loanAccount 账户
     * @param handler 对应账户中期数和还款计划的映射
     * 
     * @return void [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    public void validateRequest(PR request, CommandResponse response, LoanAccount loanAccount,
            PaymentScheduleHandler handler) {
    }
    
    /**
     * 创建交易记录<br/>
     * @param request 交易请求器
     * @param response 操作返回对象
     * @param loanAccount 账户
     * @param handler 对应账户中期数和还款计划的映射
     * 
     * @return TradingRecord 交易记录
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    public LATradingRecord createTradingRecord(PR request, CommandResponse response, LoanAccount loanAccount,
            PaymentScheduleHandler handler) {
        return null;
    }
    
    /**
     * 创建当前交易产生的计费记录分项列表<br/>
     * 如提前结清手续费等<br/>
     *
     * @param request 交易请求器
     * @param response 操作返回对象
     * @param loanAccount 账户
     * @param handler 对应账户中期数和还款计划的映射
     * @param tradingRecord 交易记录
     * 
     * @return List<ChargeRecordEntry> 交易对应的计费记录分项列表
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    public List<ChargeRecordEntry> createChargeRecordEntryList(PR request, CommandResponse response,
            LoanAccount loanAccount, PaymentScheduleHandler handler, LATradingRecord tradingRecord) {
        return null;
    }
    
    /**
     * 重置当前交易产生的计费记录分项列表<br/>
     * 如提前结清手续费等<br/>
     *
     * @param request 交易请求器
     * @param response 操作返回对象
     * @param loanAccount 账户
     * @param handler 对应账户中期数和还款计划的映射
     * @param tradingRecord 交易记录
     * 
     * @return List<ChargeRecordEntry> 交易对应的计费记录分项列表
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    public List<ChargeRecordEntry> resetChargeRecordEntryList(PR request, CommandResponse response,
            LoanAccount loanAccount,PaymentScheduleHandler handler,List<ChargeRecordEntry> chargeRecordEntryList) {
        return null;
    }
    
    /**
     * 创建豁免记录<br/>
     *
     * @param request 交易请求器
     * @param response 操作返回对象
     * @param loanAccount 账户
     * @param handler 对应账户中期数和还款计划的映射
     * @param tradingRecord 交易记录
     * 
     * @return List<ExemptRecordEntry> 交易对应的豁免记录列表
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    public List<ExemptRecordEntry> createExemptRecordEntryList(PR request, CommandResponse response,
            LoanAccount loanAccount, PaymentScheduleHandler handler, LATradingRecord tradingRecord) {
        return null;
    }
    
    /**
     * 创建当前交易产生的还款记录分项列表<br/>
     *
     * @param request 交易请求器
     * @param response 操作返回对象
     * @param loanAccount 账户
     * @param handler 对应账户中期数和还款计划的映射
     * @param tradingRecord 交易记录
     * 
     * @return List<PaymentRecordEntry> 交易对应的还款记录分项列表
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    public List<PaymentRecordEntry> createPaymentRecordEntryList(PR request, CommandResponse response,
            LoanAccount loanAccount, PaymentScheduleHandler handler, LATradingRecord tradingRecord) {
        return null;
    }
    
    /**
     * 创建交易分项列表<br/>
     *
     * @param request 交易请求器
     * @param response 操作返回对象
     * @param loanAccount 账户
     * @param handler 对应账户中期数和还款计划的映射
     * @param tradingRecord 交易记录
     * @param chargeRecords 计费记录列表
     * @param paymentRecords 还款记录
     * 
     * @return List<TradingRecordEntry> 交易记录分录列表
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    public List<LATradingRecordEntry> createTradingRecordEntryList(PR request, CommandResponse response,
            LoanAccount loanAccount, PaymentScheduleHandler handler, LATradingRecord tradingRecord,
            List<PaymentRecord> paymentRecords) {
        return null;
    }
    
    /**
     * <li>在操作执行前的前置操作
     * <ul>一般写入,根据操作生成还款计划,生成计费记录,并修改还款计划</ul></li>
     * <li>在操作执行期间的处理
     * <ul>一般，根据还款计划，以及传入的操作记录，生成交易记录</ul></li>
     * <li>操作后置执行处理
     * <ul>一般在此触发贷款单操作事件,对贷款的状态根据操作进行修改</ul></li>
     * 
     * @param request 交易请求器
     * @param response 操作返回对象
     * @param loanAccount 账户
     * @param handler 还款计划处理器
     * @param tradingRecord 交易记录
     * @param chargeRecords 计费记录列表
     * @param exemptRecords 豁免记录列表
     * @param paymentRecords 还款记录
     * 
     * @return void [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    public void postHandle(PR request, CommandResponse response, LoanAccount loanAccount,
            PaymentScheduleHandler handler, LATradingRecord tradingRecord, List<ChargeRecord> chargeRecords,
            List<ExemptRecord> exemptRecords, List<PaymentRecord> paymentRecords) {
        response.setAttribute(LoanAccountConstants.RESPONSE_KEY_LOANACCOUNT, loanAccount);//贷款账户
        response.setAttribute(LoanAccountConstants.RESPONSE_KEY_TRADINGRECORD, tradingRecord);//交易记录
        response.setAttribute(LoanAccountConstants.RESPONSE_KEY_TRADINGRECORDID, tradingRecord.getId());//交易记录id
        response.setAttribute(LoanAccountConstants.RESPONSE_KEY_PAYMENTSCHEDULEHANDLE, handler);//还款计划处理器
        response.setAttribute(LoanAccountConstants.RESPONSE_KEY_PAYMENTRECORDS, paymentRecords);//还款实收记录
    }
    
    /**
     * 处理后置逻辑<br/>
     * 该逻辑将在贷款账户及还款计划完成持久后被调用，一般用于发送事件等<br/>
     *
     * @param request 交易请求器
     * @param response 操作返回对象
     * @param loanAccount 账户
     * @param handler 还款计划处理器
     * @param tradingRecord 交易记录
     * @param chargeRecords 计费记录列表
     * @param exemptRecords 豁免记录列表
     * @param paymentRecords 还款记录
     * 
     * @return void [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    public void afterHandle(PR request, CommandResponse response, LoanAccount loanAccount,
            PaymentScheduleHandler handler, LATradingRecord tradingRecord, List<ChargeRecord> chargeRecords,
            List<ExemptRecord> exemptRecords, List<PaymentRecord> paymentRecords) {
    }
    
}
