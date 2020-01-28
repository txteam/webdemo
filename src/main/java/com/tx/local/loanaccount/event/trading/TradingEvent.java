/*
 * 描          述:  <描述>
 * 修  改   人:  Administrator
 * 修改时间:  2014年4月26日
 * <修改描述:>
 */
package com.tx.local.loanaccount.event.trading;

import java.util.ArrayList;
import java.util.List;

import com.tx.component.command.context.CommandRequest;
import com.tx.local.loanaccount.event.LoanAccountTradingEvent;
import com.tx.local.loanaccount.handle.PaymentScheduleHandler;
import com.tx.local.loanaccount.model.ChargeRecord;
import com.tx.local.loanaccount.model.ChargeRecordEntry;
import com.tx.local.loanaccount.model.ExemptRecord;
import com.tx.local.loanaccount.model.ExemptRecordEntry;
import com.tx.local.loanaccount.model.LATradingRecord;
import com.tx.local.loanaccount.model.LoanAccount;
import com.tx.local.loanaccount.model.PaymentRecord;
import com.tx.local.loanaccount.model.PaymentRecordEntry;
import com.tx.local.loanaccount.model.PaymentSchedule;
import com.tx.local.loanaccount.model.PaymentScheduleEntry;
import com.tx.local.loanaccount.model.ScheduleAssociateMap;
import com.tx.local.loanaccount.model.ScheduleEntryAssociateMap;

/**
 * 贷款单账户交易事件<br/>
 * <功能详细描述>
 * 
 * @author  Administrator
 * @version  [版本号, 2014年4月26日]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public class TradingEvent extends LoanAccountTradingEvent {
    
    /** 计费记录 */
    private final List<ChargeRecord> chargeRecordList;
    
    /** 计费记录 */
    private final List<ChargeRecordEntry> chargeRecordEntryList;
    
    /** 计费记录映射 */
    private final ScheduleAssociateMap<ChargeRecord> chargeRecordMap;
    
    /** 计费记录映射 */
    private final ScheduleEntryAssociateMap<ChargeRecordEntry> chargeRecordEntryMap;
    
    /** 豁免记录 */
    private final List<ExemptRecord> exemptRecordList;
    
    /** 计费记录 */
    private final List<ExemptRecordEntry> exemptRecordEntryList;
    
    /** 计费记录映射 */
    private final ScheduleAssociateMap<ExemptRecord> exemptRecordMap;
    
    /** 计费记录映射 */
    private final ScheduleEntryAssociateMap<ExemptRecordEntry> exemptRecordEntryMap;
    
    /** 还款记录 */
    private final List<PaymentRecord> paymentRecordList;
    
    /** 实收记录分项列表 */
    private final List<PaymentRecordEntry> paymentRecordEntryList;
    
    /** 计费记录映射 */
    private final ScheduleAssociateMap<PaymentRecord> paymentRecordMap;
    
    /** 计费记录映射 */
    private final ScheduleEntryAssociateMap<PaymentRecordEntry> paymentRecordEntryMap;
    
    /** 还款记录 */
    private final List<PaymentSchedule> changedPaymentScheduleList;
    
    /** 实收记录分项列表 */
    private final List<PaymentScheduleEntry> changedPaymentScheduleEntryList;
    
    /** 计费记录映射 */
    private final ScheduleAssociateMap<PaymentSchedule> changedPaymentScheduleMap;
    
    /** 计费记录映射 */
    private final ScheduleEntryAssociateMap<PaymentScheduleEntry> changedPaymentScheduleEntryMap;
    
    /** <默认构造函数> */
    public TradingEvent(LoanAccount loanAccount, LATradingRecord tradingRecord,
            PaymentScheduleHandler handler, CommandRequest request,
            List<ChargeRecord> chargeRecordList,
            List<ExemptRecord> exemptRecordList,
            List<PaymentRecord> paymentRecordList) {
        super(loanAccount, tradingRecord, handler, request);
        
        this.chargeRecordList = chargeRecordList == null ? new ArrayList<>()
                : chargeRecordList;
        this.chargeRecordEntryList = new ArrayList<>();
        for (ChargeRecord crTemp : this.chargeRecordList) {
            this.chargeRecordEntryList
                    .addAll(crTemp.getChargeRecordEntryList());
        }
        this.chargeRecordMap = new ScheduleAssociateMap<ChargeRecord>(
                this.chargeRecordList);
        this.chargeRecordEntryMap = new ScheduleEntryAssociateMap<>(
                this.chargeRecordEntryList);
        
        this.exemptRecordList = exemptRecordList == null ? new ArrayList<>()
                : exemptRecordList;
        this.exemptRecordEntryList = new ArrayList<>();
        for (ExemptRecord erTemp : this.exemptRecordList) {
            this.exemptRecordEntryList
                    .addAll(erTemp.getExemptRecordEntryList());
        }
        this.exemptRecordMap = new ScheduleAssociateMap<ExemptRecord>(
                this.exemptRecordList);
        this.exemptRecordEntryMap = new ScheduleEntryAssociateMap<ExemptRecordEntry>(
                this.exemptRecordEntryList);
        
        this.paymentRecordList = paymentRecordList == null ? new ArrayList<>()
                : paymentRecordList;
        this.paymentRecordEntryList = new ArrayList<>();
        for (PaymentRecord prTemp : this.paymentRecordList) {
            this.paymentRecordEntryList
                    .addAll(prTemp.getPaymentRecordEntryList());
        }
        this.paymentRecordMap = new ScheduleAssociateMap<PaymentRecord>(
                this.paymentRecordList);
        this.paymentRecordEntryMap = new ScheduleEntryAssociateMap<PaymentRecordEntry>(
                this.paymentRecordEntryList);
        
        this.changedPaymentScheduleList = new ArrayList<>();
        this.changedPaymentScheduleEntryList = new ArrayList<>();
        this.changedPaymentScheduleList
                .addAll(handler.getAddPaymentScheduleList());
        this.changedPaymentScheduleList
                .addAll(handler.getUpdatePaymentScheduleList());
        this.changedPaymentScheduleEntryList
                .addAll(handler.getAddPaymentScheduleEntryList());
        this.changedPaymentScheduleEntryList
                .addAll(handler.getUpdatePaymentScheduleEntryList());
        
        this.changedPaymentScheduleMap = new ScheduleAssociateMap<PaymentSchedule>(
                this.changedPaymentScheduleList);
        this.changedPaymentScheduleEntryMap = new ScheduleEntryAssociateMap<PaymentScheduleEntry>(
                this.changedPaymentScheduleEntryList);
    }
    
    /**
     * @return 返回 chargeRecordList
     */
    public List<ChargeRecord> getChargeRecordList() {
        return chargeRecordList;
    }
    
    /**
     * @return 返回 chargeRecordEntryList
     */
    public List<ChargeRecordEntry> getChargeRecordEntryList() {
        return chargeRecordEntryList;
    }
    
    /**
     * @return 返回 chargeRecordMap
     */
    public ScheduleAssociateMap<ChargeRecord> getChargeRecordMap() {
        return chargeRecordMap;
    }
    
    /**
     * @return 返回 chargeRecordEntryMap
     */
    public ScheduleEntryAssociateMap<ChargeRecordEntry> getChargeRecordEntryMap() {
        return chargeRecordEntryMap;
    }
    
    /**
     * @return 返回 exemptRecordList
     */
    public List<ExemptRecord> getExemptRecordList() {
        return exemptRecordList;
    }
    
    /**
     * @return 返回 exemptRecordEntryList
     */
    public List<ExemptRecordEntry> getExemptRecordEntryList() {
        return exemptRecordEntryList;
    }
    
    /**
     * @return 返回 exemptRecordMap
     */
    public ScheduleAssociateMap<ExemptRecord> getExemptRecordMap() {
        return exemptRecordMap;
    }
    
    /**
     * @return 返回 exemptRecordEntryMap
     */
    public ScheduleEntryAssociateMap<ExemptRecordEntry> getExemptRecordEntryMap() {
        return exemptRecordEntryMap;
    }
    
    /**
     * @return 返回 paymentRecordList
     */
    public List<PaymentRecord> getPaymentRecordList() {
        return paymentRecordList;
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
    
    /**
     * @return 返回 changedPaymentScheduleList
     */
    public List<PaymentSchedule> getChangedPaymentScheduleList() {
        return changedPaymentScheduleList;
    }
    
    /**
     * @return 返回 changedPaymentScheduleEntryList
     */
    public List<PaymentScheduleEntry> getChangedPaymentScheduleEntryList() {
        return changedPaymentScheduleEntryList;
    }
    
    /**
     * @return 返回 changedPaymentScheduleMap
     */
    public ScheduleAssociateMap<PaymentSchedule> getChangedPaymentScheduleMap() {
        return changedPaymentScheduleMap;
    }
    
    /**
     * @return 返回 changedPaymentScheduleEntryMap
     */
    public ScheduleEntryAssociateMap<PaymentScheduleEntry> getChangedPaymentScheduleEntryMap() {
        return changedPaymentScheduleEntryMap;
    }
}
