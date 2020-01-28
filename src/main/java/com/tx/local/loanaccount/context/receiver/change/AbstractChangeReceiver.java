/*
 * 描          述:  <描述>
 * 修  改   人:  Administrator
 * 修改时间:  2014年4月10日
 * <修改描述:>
 */
package com.tx.local.loanaccount.context.receiver.change;

import java.math.BigDecimal;
import java.util.Date;

import com.tx.component.command.context.CommandResponse;
import com.tx.local.loanaccount.model.LoanAccount;
import com.tx.local.loanaccount.context.receiver.AbstractLoanAccountReceiver;
import com.tx.local.loanaccount.context.request.change.AbstractChangeRequest;
import com.tx.local.loanaccount.model.LATradingRecord;

/**
 * 贷款账户操作支持接口<br/>
 * <功能详细描述>
 * 
 * @author  Administrator
 * @version  [版本号, 2014年4月10日]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public abstract class AbstractChangeReceiver<PR extends AbstractChangeRequest> extends AbstractLoanAccountReceiver<PR> {
    
    /**
     * @param request
     * @return
     */
    @Override
    protected boolean isMatch(PR request) {
        return true;
    }
    
    /**
      * 校验请求合法性<br/>
      * <功能详细描述>
      * @param request
      * @param response
      * @param loanAccount [参数说明]
      * 
      * @return void [返回类型说明]
      * @exception throws [异常类型] [异常说明]
      * @see [类、类#方法、类#成员]
     */
    public void validateRequest(PR request, CommandResponse response, LoanAccount loanAccount) {
    }
    
    /**
     * 
     * 创建交易记录
     *
     * @param request 交易请求器
     * @param response 操作返回对象
     * @param loanAccount 账户
     * 
     * @return TradingRecord 交易记录
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    public LATradingRecord createTradingRecord(PR request, CommandResponse response, LoanAccount loanAccount) {
        Date now = new Date();
        LATradingRecord tradingRecord = new LATradingRecord();
        
        tradingRecord.setRepayDate(now);//还款日期为贷款生效日
        tradingRecord.setExpireDate(loanAccount.getCurrentPeriodExpireDate());//到期日为当前期数到期日
        tradingRecord.setRemark(request.getRemark());
        
        //账户交易是否到账
        tradingRecord.setReceived(true);
        tradingRecord.setReceiveDate(now);
        tradingRecord.setViewAble(false);
        tradingRecord.setRevokeAble(false);
        tradingRecord.setBankAccountId(null);
        tradingRecord.setSum(BigDecimal.ZERO);
        
        return tradingRecord;
    }
    
    /**
      * 账户变更的操作实现类<br/>
      * @param request [参数说明]
      * 
      * @return void [返回类型说明]
      * @exception throws [异常类型] [异常说明]
      * @see [类、类#方法、类#成员]
      */
    public abstract void posthandle(PR request, CommandResponse response, LoanAccount loanAccount);
    
    /**
      * 处理后置逻辑该逻辑将在贷款账户及还款计划完成持久后被调用，一般用于发送事件等<br/>
      *<功能详细描述>
      * @param request
      * @param response
      * @param loanAccount [参数说明]
      * 
      * @return void [返回类型说明]
      * @exception throws [异常类型] [异常说明]
      * @see [类、类#方法、类#成员]
     */
    public void afterHandle(PR request, CommandResponse response, LoanAccount loanAccount) {
    }
}
