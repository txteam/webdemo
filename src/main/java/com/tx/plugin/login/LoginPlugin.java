/*
 * Copyright 2005-2017 cqtianxin.com. All rights reserved.
 * Support: http://www.cqtianxin.com
 * License: http://www.cqtianxin.com/license
 */
package com.tx.plugin.login;

import com.tx.component.plugin.context.Plugin;

/**
 * 登陆插件<br/>
 * <功能详细描述>
 * 
 * @author  Administrator
 * @version  [版本号, 2019年12月4日]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public abstract class LoginPlugin<CONFIG extends LoginPluginConfig>
        extends Plugin<CONFIG> {
    
    /**
     * @return
     */
    @Override
    public String getCatalog() {
        return "login";
    }
}