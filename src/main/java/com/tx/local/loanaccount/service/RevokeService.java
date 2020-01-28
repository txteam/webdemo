package com.tx.local.loanaccount.service;

import java.util.List;

import com.tx.local.loanaccount.model.LATradingRecord;

/**
  * 撤销业务层接口<br/>
  * <功能详细描述>
  * 
  * @author  Administrator
  * @version  [版本号, 2015年2月12日]
  * @see  [相关类/方法]
  * @since  [产品/模块版本]
 */
public interface RevokeService {
    
    /**
      * 获取可撤销的交易集合<br/>
      * <功能详细描述>
      * @param loanAccountId
      * @return [参数说明]
      * 
      * @return List<TradingRecord> [返回类型说明]
      * @exception throws [异常类型] [异常说明]
      * @see [类、类#方法、类#成员]
     */
    public abstract List<LATradingRecord> getRevokeAbleTradingRecords(
            String loanAccountId);
    
    /**
     * 退回逻辑<br/>
     *    支撑：撤销|归回业务类型<br/>
     * <功能详细描述>
     * @param tradingRecord [参数说明]
     * 
     * @return void [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
    */
    public abstract void revoke(String loanAccountId,
            String revokeTradingRecordId, String revokeReason);
    
    /**
      * 归还款项<br/>
      * <功能详细描述>
      * @param bankAccountId
      * @param loanAccountId
      * @param revokeTradingRecordId
      * @param revokeReason [参数说明]
      * 
      * @return void [返回类型说明]
      * @exception throws [异常类型] [异常说明]
      * @see [类、类#方法、类#成员]
     */
    public void refund(String bankAccountId, String loanAccountId,
            String revokeTradingRecordId, String revokeReason);
}