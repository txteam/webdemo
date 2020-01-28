/*
 * 描          述:  <描述>
 * 修  改   人:  Administrator
 * 修改时间:  2014年5月20日
 * <修改描述:>
 */
package com.tx.local.loanaccount.event.trading;

import java.util.ArrayList;
import java.util.List;

import com.tx.component.command.context.CommandRequest;
import com.tx.core.exceptions.util.AssertUtils;
import com.tx.local.loanaccount.event.LoanAccountTradingEvent;
import com.tx.local.loanaccount.handle.PaymentScheduleHandler;
import com.tx.local.loanaccount.model.LATradingRecord;
import com.tx.local.loanaccount.model.LoanAccount;
import com.tx.local.loanaccount.model.PaymentRecord;
import com.tx.local.loanaccount.model.PaymentRecordEntry;
import com.tx.local.loanaccount.model.ScheduleAssociateMap;
import com.tx.local.loanaccount.model.ScheduleEntryAssociateMap;

/**
 * 还款事件<br/>
 * <功能详细描述>
 * 
 * @author  Administrator
 * @version  [版本号, 2014年5月20日]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public class PaymentEvent extends LoanAccountTradingEvent {
    
    /** 还款记录 */
    private List<PaymentRecord> paymentRecordList;
    
    /** 实收记录分项列表 */
    private final List<PaymentRecordEntry> paymentRecordEntryList;
    
    /** 计费记录映射 */
    private final ScheduleAssociateMap<PaymentRecord> paymentRecordMap;
    
    /** 计费记录映射 */
    private final ScheduleEntryAssociateMap<PaymentRecordEntry> paymentRecordEntryMap;
    
    /** <默认构造函数> */
    public PaymentEvent(LoanAccount loanAccount, LATradingRecord tradingRecord,
            PaymentScheduleHandler handler, CommandRequest processRequest,
            List<PaymentRecord> paymentRecordList) {
        super(loanAccount, tradingRecord, handler, processRequest);
        
        AssertUtils.notEmpty(paymentRecordList, "paymentRecordList is empty.");
        this.paymentRecordList = paymentRecordList;
        this.paymentRecordEntryList = new ArrayList<>();
        for (PaymentRecord prTemp : this.paymentRecordList) {
            this.paymentRecordEntryList
                    .addAll(prTemp.getPaymentRecordEntryList());
        }
        this.paymentRecordMap = new ScheduleAssociateMap<PaymentRecord>(
                this.paymentRecordList);
        this.paymentRecordEntryMap = new ScheduleEntryAssociateMap<PaymentRecordEntry>(
                this.paymentRecordEntryList);
    }
    
    /**
     * @return 返回 paymentRecordList
     */
    public List<PaymentRecord> getPaymentRecordList() {
        return paymentRecordList;
    }
    
    /**
     * @param 对paymentRecordList进行赋值
     */
    public void setPaymentRecordList(List<PaymentRecord> paymentRecordList) {
        this.paymentRecordList = paymentRecordList;
    }
    
    /**
     * @return 返回 paymentRecordEntryList
     */
    public List<PaymentRecordEntry> getPaymentRecordEntryList() {
        return paymentRecordEntryList;
    }
    
    /**
     * @return 返回 paymentRecordMap
     */
    public ScheduleAssociateMap<PaymentRecord> getPaymentRecordMap() {
        return paymentRecordMap;
    }
    
    /**
     * @return 返回 paymentRecordEntryMap
     */
    public ScheduleEntryAssociateMap<PaymentRecordEntry> getPaymentRecordEntryMap() {
        return paymentRecordEntryMap;
    }
}
