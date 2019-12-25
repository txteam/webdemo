/*
 * 描          述:  <描述>
 * 修  改   人:  Administrator
 * 修改时间:  2019年12月20日
 * <修改描述:>
 */
package com.tx.plugin.payment;

import com.tx.component.plugin.context.Plugin;

/**
 * 支付插件父类<br/>
 * <功能详细描述>
 * 
 * @author  Administrator
 * @version  [版本号, 2019年12月20日]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public abstract class PaymentPlugin<CONFIG extends PaymentPluginConfig>
        extends Plugin<CONFIG> {
    
    /**
    * @return
    */
    @Override
    public String getCatalog() {
        return "payment";
    }
}
