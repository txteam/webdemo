/*
 * 描          述:  <描述>
 * 修  改   人:  Administrator
 * 修改时间:  2019年12月20日
 * <修改描述:>
 */
package com.tx.plugin.payment;

import com.tx.component.configuration.annotation.ConfigCatalog;
import com.tx.component.plugin.context.PluginConfig;

/**
 * 支付插件配置<br/>
 * <功能详细描述>
 * 
 * @author  Administrator
 * @version  [版本号, 2019年12月20日]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
@ConfigCatalog(code = "plugin.payment", name = "支付插件配置")
public abstract class PaymentPluginConfig extends PluginConfig {
    
    /** 登陆备注 */
    private String remark;
    
    /**
     * @return 返回 remark
     */
    public String getRemark() {
        return remark;
    }
    
    /**
     * @param 对remark进行赋值
     */
    public void setRemark(String remark) {
        this.remark = remark;
    }
}
