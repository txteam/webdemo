/*
 * 描          述:  <描述>
 * 修  改   人:  Administrator
 * 修改时间:  2017年6月16日
 * <修改描述:>
 */
package com.tx.local.loanaccount.strategy.revoke;

import java.util.List;

import com.tx.component.strategy.context.Strategy;
import com.tx.local.loanaccount.model.LATradingRecord;
import com.tx.local.loanaccount.model.LoanAccount;
import com.tx.local.loanaccount.model.RevokeTypeEnum;

/**
  * 撤销策略<br/>
  * <功能详细描述>
  * 
  * @author  Administrator
  * @version  [版本号, 2017年6月16日]
  * @see  [相关类/方法]
  * @since  [产品/模块版本]
  */
public interface RevokeStrategy extends Strategy {
    
    /**
     * 获取可撤销的交易记录<br/>
     * <功能详细描述>
     * @param loanAccount
     * @return [参数说明]
     * 
     * @return List<LATradingRecord> [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
    */
    public List<LATradingRecord> getRevokeAbleTradingRecords(LoanAccount loanAccount);
    
    /**
     * 获取科撤销的还款交易记录<br/>
     * <功能详细描述>
     * @param loanAccount
     * @return [参数说明]
     * 
     * @return List<LATradingRecord> [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
    */
    public List<LATradingRecord> getRevokeAbleRepayTradingRecords(LoanAccount loanAccount);
    
    /**
      * 是否可撤销<br/>
      * <功能详细描述>
      * @param loanAccount
      * @param tradingRecord
      * @return [参数说明]
      * 
      * @return boolean [返回类型说明]
      * @exception throws [异常类型] [异常说明]
      * @see [类、类#方法、类#成员]
     */
    public boolean isRevokeAble(LoanAccount loanAccount,LATradingRecord tradingRecord);
    
    /**
      * 是否可进行归还款项处理<br/>
      * <功能详细描述>
      * @param loanAccount
      * @param tradingRecord
      * @return [参数说明]
      * 
      * @return boolean [返回类型说明]
      * @exception throws [异常类型] [异常说明]
      * @see [类、类#方法、类#成员]
     */
    public boolean isRefundAble(LoanAccount loanAccount,LATradingRecord tradingRecord);
    
    /**
      * 是否能撤销到待入账<br/>
      * <功能详细描述>
      * @param loanAccount
      * @param tradingRecord
      * @return [参数说明]
      * 
      * @return boolean [返回类型说明]
      * @exception throws [异常类型] [异常说明]
      * @see [类、类#方法、类#成员]
     */
    public boolean isRevokeToWaitAccountAble(LoanAccount loanAccount,LATradingRecord tradingRecord);
    
    /**
      * 撤销交易<br/>
      * <功能详细描述>
      * @param revokeType
      * @param loanAccount
      * @param revokeTradingRecordId
      * @param revokeResean
      * @return [参数说明]
      * 
      * @return boolean [返回类型说明]
      * @exception throws [异常类型] [异常说明]
      * @see [类、类#方法、类#成员]
     */
    public boolean revoke(RevokeTypeEnum revokeType, LoanAccount loanAccount, String revokeTradingRecordId,
            String revokeResean);
    
    /**
      * 撤销至待入账<br/>
      * <功能详细描述>
      * @param revokeType
      * @param loanAccount
      * @param revokeTradingRecordId
      * @param revokeResean
      * @return [参数说明]
      * 
      * @return boolean [返回类型说明]
      * @exception throws [异常类型] [异常说明]
      * @see [类、类#方法、类#成员]
     */
    public boolean revokeToWaitAccount(RevokeTypeEnum revokeType, LoanAccount loanAccount, String revokeTradingRecordId,
            String revokeResean);
    
    /**
      * 归还款项<br/>
      * <功能详细描述>
      * @param revokeType
      * @param loanAccount
      * @param revokeTradingRecordId
      * @param revokeResean
      * @param bankAccountId
      * @return [参数说明]
      * 
      * @return boolean [返回类型说明]
      * @exception throws [异常类型] [异常说明]
      * @see [类、类#方法、类#成员]
     */
    public boolean refund(RevokeTypeEnum revokeType, LoanAccount loanAccount, String revokeTradingRecordId,
            String revokeResean, String bankAccountId);
}
