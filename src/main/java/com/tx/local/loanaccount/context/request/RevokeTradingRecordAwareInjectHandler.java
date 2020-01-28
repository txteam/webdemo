/*
 * 描          述:  <描述>
 * 修  改   人:  Administrator
 * 修改时间:  2017年6月2日
 * <修改描述:>
 */
package com.tx.local.loanaccount.context.request;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.tx.component.command.context.injecthandler.AbstractInjectHandler;
import com.tx.core.exceptions.util.AssertUtils;
import com.tx.local.loanaccount.model.LATradingRecord;
import com.tx.local.loanaccount.service.LATradingRecordService;

/**
  * 贷款账户注入处理器<br/>
  * <功能详细描述>
  * 
  * @author  Administrator
  * @version  [版本号, 2017年6月2日]
  * @see  [相关类/方法]
  * @since  [产品/模块版本]
  */
@Component("revokeTradingRecordAwareInjectHandler")
public class RevokeTradingRecordAwareInjectHandler extends AbstractInjectHandler<RevokeTradingRecordAware> {
    
    @Resource(name = "laTradingRecordService")
    private LATradingRecordService tradingRecordService;
    
    /**
     * @param request
     */
    @Override
    protected void doInject(RevokeTradingRecordAware request) {
        String revokeTradingRecordId = request.getRevokeTradingRecordId();
        AssertUtils.notEmpty(revokeTradingRecordId, "revokeTradingRecordId is empty.");
        
        if (request.getRevokeTradingRecord() == null) {
            LATradingRecord tradingRecord = this.tradingRecordService.findById(revokeTradingRecordId);
            AssertUtils.notNull(tradingRecord, "tradingRecord:{} is not exist.", revokeTradingRecordId);
            AssertUtils.isTrue(tradingRecord.isRevokeAble(),
                    "tradingRecord:{} is not revokeAble.",
                    revokeTradingRecordId);
            AssertUtils.isTrue(!tradingRecord.isRevoked(), "tradingRecord:{} is revoked.", revokeTradingRecordId);
            
            request.setRevokeTradingRecord(tradingRecord);
        }
    }
}
