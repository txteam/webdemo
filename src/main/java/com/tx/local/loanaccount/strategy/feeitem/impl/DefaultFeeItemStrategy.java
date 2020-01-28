/*
 * 描          述:  <描述>
 * 修  改   人:  Administrator
 * 修改时间:  2017年6月23日
 * <修改描述:>
 */
package com.tx.local.loanaccount.strategy.feeitem.impl;

import org.springframework.stereotype.Component;

import com.tx.local.loanaccount.strategy.feeitem.AbstractFeeItemStrategy;
import com.tx.local.loanaccount.strategy.feeitem.FeeItemStrategy;

/**
  * 车商贷费用策略实现<br/>
  * <功能详细描述>
  * 
  * @author  Administrator
  * @version  [版本号, 2017年6月23日]
  * @see  [相关类/方法]
  * @since  [产品/模块版本]
  */
@Component("defaultFeeItemStrategy")
public class DefaultFeeItemStrategy extends AbstractFeeItemStrategy implements FeeItemStrategy {
    
}
