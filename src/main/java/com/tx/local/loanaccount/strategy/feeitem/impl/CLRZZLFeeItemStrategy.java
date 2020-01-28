/*
 * 描          述:  <描述>
 * 修  改   人:  Administrator
 * 修改时间:  2017年6月23日
 * <修改描述:>
 */
package com.tx.local.loanaccount.strategy.feeitem.impl;

import org.springframework.stereotype.Component;

import com.tx.local.basicdata.model.FeeItemCfg;
import com.tx.local.basicdata.model.FeeItemEnum;
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
@Component("clrzzlFeeItemStrategy")
public class CLRZZLFeeItemStrategy extends AbstractFeeItemStrategy
        implements FeeItemStrategy {
    
    /**
     * 
     */
    @Override
    protected void initFeeItemCfgMap() {
        super.initFeeItemCfgMap();
        
        this.feeItemCfgMap.put(FeeItemEnum.DK_GZK,
                new FeeItemCfg(FeeItemEnum.DK_GZK, false, 13, 13, 13));//购置款
        
        this.feeItemCfgMap.put(FeeItemEnum.DK_BZJ,
                new FeeItemCfg(FeeItemEnum.DK_BZJ, false, 0, 0, 0));//保证金
        this.feeItemCfgMap.put(FeeItemEnum.ZX_BZJ,
                new FeeItemCfg(FeeItemEnum.ZX_BZJ, false, 0, 0, 0));//保证金
    }
    
}
