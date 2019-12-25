/*
 * 描          述:  <描述>
 * 修  改   人:  Administrator
 * 修改时间:  2019年12月5日
 * <修改描述:>
 */
package com.tx.plugin.login.qq;

import com.tx.component.configuration.annotation.ConfigCatalog;
import com.tx.plugin.login.LoginPluginConfig;

/**
 * 操作人员QQ登陆插件配置<br/>
 * <功能详细描述>
 * 
 * @author  Administrator
 * @version  [版本号, 2019年12月5日]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
@ConfigCatalog(code = "plugin.login.qq", name = "QQ登陆插件配置")
public class QQLoginPluginConfig extends LoginPluginConfig {
    
    /** clientId */
    private String clientId;
    
    /** clientSecret */
    private String clientSecret;

    /**
     * @return 返回 clientId
     */
    public String getClientId() {
        return clientId;
    }

    /**
     * @param 对clientId进行赋值
     */
    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    /**
     * @return 返回 clientSecret
     */
    public String getClientSecret() {
        return clientSecret;
    }

    /**
     * @param 对clientSecret进行赋值
     */
    public void setClientSecret(String clientSecret) {
        this.clientSecret = clientSecret;
    }
}
