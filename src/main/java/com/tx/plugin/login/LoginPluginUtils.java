/*
 * 描          述:  <描述>
 * 修  改   人:  Administrator
 * 修改时间:  2019年12月25日
 * <修改描述:>
 */
package com.tx.plugin.login;

import java.util.Map;

import org.apache.commons.lang3.EnumUtils;

import com.tx.component.plugin.context.PluginContext;
import com.tx.core.exceptions.util.AssertUtils;
import com.tx.local.operator.model.OperSocialAccountTypeEnum;
import com.tx.local.security.util.WebContextUtils;
import com.tx.plugin.login.weibo.WBLoginPlugin;

/**
 * 登陆插件常用工具类<br/>
 * <功能详细描述>
 * 
 * @author  Administrator
 * @version  [版本号, 2019年12月25日]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public abstract class LoginPluginUtils {
    
    /** 账户类型映射 */
    private final static Map<String, OperSocialAccountTypeEnum> ACCOUNT_TYPE_MAP = EnumUtils
            .getEnumMap(OperSocialAccountTypeEnum.class);
    
    /**
     * 根据插件寻找到对应的账户类型<br/>
     * <功能详细描述>
     * @param plugin
     * @return [参数说明]
     * 
     * @return OperSocialAccountTypeEnum [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    public static OperSocialAccountTypeEnum getTypeByPlugin(String plugin) {
        OperSocialAccountTypeEnum type = ACCOUNT_TYPE_MAP.get(plugin);
        return type;
    }
    
    /**
     * 将值进行压入会话
     * <功能详细描述>
     * @param userId [参数说明]
     * 
     * @return void [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    public static void pushAttribute(String key, String value) {
        AssertUtils.notEmpty(key, "key is empty.");
        AssertUtils.notEmpty(value, "value is empty.");
        
        WebContextUtils.getSession().setAttribute(key, value);
    }
    
    /**
     * 从会话中获取对应的值
     * <功能详细描述>
     * @return [参数说明]
     * 
     * @return String [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    public static String popAttribute(String key) {
        AssertUtils.notEmpty(key, "key is empty.");
        
        String value = (String) WebContextUtils.getSession().getAttribute(key);
        WebContextUtils.getSession().removeAttribute(key);
        return value;
    }
    
    /**
     * 将userId进行压入会话
     * <功能详细描述>
     * @param userId [参数说明]
     * 
     * @return void [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    public static void pushUserId(String userId) {
        AssertUtils.notEmpty(userId, "userId is empty.");
        
        WebContextUtils.getSession().setAttribute(
                LoginPluginConstants.USER_ID_ATTRIBUTE_NAME, userId);
    }
    
    /**
     * 从会话中获取对应的userId
     * <功能详细描述>
     * @return [参数说明]
     * 
     * @return String [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    public static String popUserId() {
        String userId = (String) WebContextUtils.getSession()
                .getAttribute(LoginPluginConstants.USER_ID_ATTRIBUTE_NAME);
        
        WebContextUtils.getSession()
                .removeAttribute(LoginPluginConstants.USER_ID_ATTRIBUTE_NAME);
        return userId;
    }
    
    /**
     * 将targetName进行压入会话
     * <功能详细描述>
     * @param userId [参数说明]
     * 
     * @return void [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    public static void pushTargetName(String targetName) {
        AssertUtils.notEmpty(targetName, "targetName is empty.");
        
        WebContextUtils.getSession().setAttribute(
                LoginPluginConstants.TARGET_NAME_ATTRIBUTE_NAME, targetName);
    }
    
    /**
     * 从会话中获取对应的targetName.
     * <功能详细描述>
     * @return [参数说明]
     * 
     * @return String [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    public static String popTargetName() {
        String targetName = (String) WebContextUtils.getSession()
                .getAttribute(LoginPluginConstants.TARGET_NAME_ATTRIBUTE_NAME);
        
        WebContextUtils.getSession().removeAttribute(
                LoginPluginConstants.TARGET_NAME_ATTRIBUTE_NAME);
        return targetName;
    }
    
    /**
     * 根据传入插件简称获取对应插件实例<br/>
     * <功能详细描述>
     * @param plugin
     * @return [参数说明]
     * 
     * @return LoginPlugin<?> [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    public static LoginPlugin<?> getLoginPlugin(String plugin) {
        LoginPlugin<?> loginPlugin = null;
        switch (plugin) {
//            case "WX":
//                loginPlugin = PluginContext.getContext()
//                        .getPlugin(WXLoginPlugin.class);
//                break;
//            case "QQ":
//                loginPlugin = PluginContext.getContext()
//                        .getPlugin(QQLoginPlugin.class);
//                break;
            case "WB":
                loginPlugin = PluginContext.getContext()
                        .getPlugin(WBLoginPlugin.class);
                break;
            case "BD":
                loginPlugin = null;
                break;
            case "GH":
                loginPlugin = null;
                break;
        }
        return loginPlugin;
    }
}
