/*
 * 描          述:  <描述>
 * 修  改   人:  Administrator
 * 修改时间:  2019年12月5日
 * <修改描述:>
 */
package com.tx.plugin.wxlogin;

import com.tx.component.configuration.annotation.ConfigCatalog;
import com.tx.plugin.login.LoginPluginConfig;

import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiModelProperty.AccessMode;

/**
 * 微信登陆插件配置
 * <功能详细描述>
 * 
 * @author  Administrator
 * @version  [版本号, 2019年12月5日]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
@ConfigCatalog(code = "plugin.login.wx", name = "操作员微信登陆插件配置")
public class OperatorWXLoginPluginConfig extends LoginPluginConfig {
    
    /** 登陆url:这里只是用以验证参数不可编辑的场景，其实可以在filter中直接写死链接即可 */
    @ApiModelProperty(accessMode = AccessMode.READ_ONLY, value = "登陆Url")
    private String processesUrl = "/operator/wx/login";
    
    //wx356a9316d6bf588a
    private String appId;
    
    //f6ab3ad6c700352cd2f9af8c3c1c923a
    private String appSecret;
    
    /**
     * @return 返回 appId
     */
    public String getAppId() {
        return appId;
    }
    
    /**
     * @param 对appId进行赋值
     */
    public void setAppId(String appId) {
        this.appId = appId;
    }
    
    /**
     * @return 返回 appSecret
     */
    public String getAppSecret() {
        return appSecret;
    }
    
    /**
     * @param 对appSecret进行赋值
     */
    public void setAppSecret(String appSecret) {
        this.appSecret = appSecret;
    }

    /**
     * @return 返回 processesUrl
     */
    public String getProcessesUrl() {
        return processesUrl;
    }

    /**
     * @param 对processesUrl进行赋值
     */
    public void setProcessesUrl(String processesUrl) {
        this.processesUrl = processesUrl;
    }
}
