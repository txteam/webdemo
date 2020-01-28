package com.tx.local.loanaccount.service;

import java.math.BigDecimal;
import java.util.Map;

import com.tx.local.loanaccount.model.RepayIntention;
import com.tx.local.loanaccount.model.LATradingRecord;

/**
 * 还款业务层接口<br/>
 * <功能详细描述>
 * 
 * @author  Administrator
 * @version  [版本号, 2015年2月10日]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public interface RepayService {
    
    /**
     * 将金额进行自动分配<br/>
     * <功能详细描述>
     * @param repayIntention
     * @return [参数说明]
     * 
     * @return Map<String,BigDecimal> [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    public Map<String, BigDecimal> autoAssign(RepayIntention repayIntention);
    
    /** 
     * 还款<br/>
     * <功能详细描述>
     * @param repayModel
     * @param sourceId
     * @return [参数说明]
     * 
     * @return boolean [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    public boolean repay(RepayIntention repayIntention);
    
    /**
     * 查询最后可关联的交易<br/>
     * <功能详细描述>
     * @return [参数说明]
     * 
     * @return List<TradingRecord> [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    public LATradingRecord findLastRevokeAbleRepayTradingRecord(
            String loanAccountId);
}