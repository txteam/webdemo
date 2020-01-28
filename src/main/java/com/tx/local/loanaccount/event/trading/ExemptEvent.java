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
import com.tx.local.loanaccount.model.ExemptRecord;
import com.tx.local.loanaccount.model.ExemptRecordEntry;
import com.tx.local.loanaccount.model.LATradingRecord;
import com.tx.local.loanaccount.model.LoanAccount;
import com.tx.local.loanaccount.model.ScheduleAssociateMap;
import com.tx.local.loanaccount.model.ScheduleEntryAssociateMap;

/**
 * 豁免事件实例<br/>
 * 
 * @author  Administrator
 * @version  [版本号, 2014年5月20日]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public class ExemptEvent extends LoanAccountTradingEvent {
    
    /** 豁免记录 */
    private List<ExemptRecord> exemptRecordList;
    
    /** 计费记录 */
    private List<ExemptRecordEntry> exemptRecordEntryList;
    
    /** 计费记录映射 */
    private final ScheduleAssociateMap<ExemptRecord> exemptRecordMap;
    
    /** 计费记录映射 */
    private final ScheduleEntryAssociateMap<ExemptRecordEntry> exemptRecordEntryMap;
    
    /** <默认构造函数> */
    public ExemptEvent(LoanAccount loanAccount, LATradingRecord tradingRecord, PaymentScheduleHandler handler,
            CommandRequest processRequest, List<ExemptRecord> exemptRecordList) {
        super(loanAccount, tradingRecord, handler, processRequest);
        
        AssertUtils.notEmpty(exemptRecordList, "exemptRecordList is empty.");
        this.exemptRecordList = exemptRecordList;
        this.exemptRecordEntryList = new ArrayList<>();
        for(ExemptRecord erTemp : this.exemptRecordList){
            this.exemptRecordEntryList.addAll(erTemp.getExemptRecordEntryList());
        }
        this.exemptRecordMap = new ScheduleAssociateMap<ExemptRecord>(this.exemptRecordList);
        this.exemptRecordEntryMap = new ScheduleEntryAssociateMap<ExemptRecordEntry>(this.exemptRecordEntryList);
    }
    
    /**
     * @return 返回 exemptRecordList
     */
    public List<ExemptRecord> getExemptRecordList() {
        return exemptRecordList;
    }
    
    /**
     * @param 对exemptRecordList进行赋值
     */
    public void setExemptRecordList(List<ExemptRecord> exemptRecordList) {
        this.exemptRecordList = exemptRecordList;
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
}
