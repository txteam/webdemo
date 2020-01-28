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
import com.tx.local.loanaccount.model.ChargeRecord;
import com.tx.local.loanaccount.model.ChargeRecordEntry;
import com.tx.local.loanaccount.model.LATradingRecord;
import com.tx.local.loanaccount.model.LoanAccount;
import com.tx.local.loanaccount.model.ScheduleAssociateMap;
import com.tx.local.loanaccount.model.ScheduleEntryAssociateMap;

/**
 * 计费事件<br/> 
 * <功能详细描述>
 * 
 * @author  Administrator
 * @version  [版本号, 2014年5月20日]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public class ChargeEvent extends LoanAccountTradingEvent {
    
    /** 计费记录 */
    private List<ChargeRecord> chargeRecordList;
    
    /** 计费记录 */
    private List<ChargeRecordEntry> chargeRecordEntryList;
    
    /** 计费记录映射 */
    private final ScheduleAssociateMap<ChargeRecord> chargeRecordMap;
    
    /** 计费记录映射 */
    private final ScheduleEntryAssociateMap<ChargeRecordEntry> chargeRecordEntryMap;
    
    /** <默认构造函数> */
    public ChargeEvent(LoanAccount loanAccount, LATradingRecord tradingRecord, PaymentScheduleHandler handler,
            CommandRequest processRequest,
            List<ChargeRecord> chargeRecordList) {
        super(loanAccount, tradingRecord, handler, processRequest);
        
        AssertUtils.notEmpty(chargeRecordList, "chargeRecordList is empty.");
        this.chargeRecordList = chargeRecordList;
        this.chargeRecordEntryList = new ArrayList<>();
        for(ChargeRecord crTemp : this.chargeRecordList){
            this.chargeRecordEntryList.addAll(crTemp.getChargeRecordEntryList());
        }
        this.chargeRecordMap = new ScheduleAssociateMap<ChargeRecord>(this.chargeRecordList);
        this.chargeRecordEntryMap = new ScheduleEntryAssociateMap<>(this.chargeRecordEntryList);
    }
    
    /**
     * @return 返回 chargeRecordList
     */
    public List<ChargeRecord> getChargeRecordList() {
        return chargeRecordList;
    }
    
    /**
     * @param 对chargeRecordList进行赋值
     */
    public void setChargeRecordList(List<ChargeRecord> chargeRecordList) {
        this.chargeRecordList = chargeRecordList;
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
}
