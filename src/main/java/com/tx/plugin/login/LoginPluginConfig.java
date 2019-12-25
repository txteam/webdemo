/*
 * 描          述:  <描述>
 * 修  改   人:  Administrator
 * 修改时间:  2019年12月4日
 * <修改描述:>
 */
package com.tx.plugin.login;

import com.tx.component.configuration.annotation.ConfigCatalog;
import com.tx.component.plugin.context.PluginConfig;

/**
 * 登录插件配置<br/>
 * <功能详细描述>
 * 
 * @author  Administrator
 * @version  [版本号, 2019年12月4日]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
@ConfigCatalog(code = "plugin.login", name = "登陆插件配置")
public abstract class LoginPluginConfig extends PluginConfig {
    
    //  /** 登陆url:这里只是用以验证参数不可编辑的场景，其实可以在filter中直接写死链接即可 */
    //    @ApiModelProperty(accessMode = AccessMode.READ_ONLY, value = "登陆Url")
    //    private String processesUrl = "/operator/wx/login";
    
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
