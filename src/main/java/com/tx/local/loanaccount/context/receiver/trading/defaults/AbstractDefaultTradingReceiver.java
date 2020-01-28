/*
 * 描          述:  <描述>
 * 修  改   人:  Administrator
 * 修改时间:  2014年4月10日
 * <修改描述:>
 */
package com.tx.local.loanaccount.context.receiver.trading.defaults;

import com.tx.local.loanaccount.context.receiver.trading.AbstractTradingReceiver;
import com.tx.local.loanaccount.context.request.trading.AbstractTradingRequest;

/**
 * 贷款账户交易基础接收器
 * 
 * @author  Tim.peng
 * @version  [版本号, 2014年4月10日]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public abstract class AbstractDefaultTradingReceiver<PR extends AbstractTradingRequest>
        extends AbstractTradingReceiver<PR> {
    
    /**
     * 判断是否匹配<br/>
     * <功能详细描述>
     * @param request
     * @return [参数说明]
     * 
     * @return boolean [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
    */
    protected boolean isMatch(PR request) {
        return true;
    }
}
