/*
 * 描          述:  <描述>
 * 修  改   人:  Administrator
 * 修改时间:  2019年7月18日
 * <修改描述:>
 */
package com.tx.front4client.security.util;

import org.springframework.security.core.Authentication;

import com.tx.front4client.security.model.ClientUserDetails;
import com.tx.local.clientinfo.model.Client;
import com.tx.local.clientinfo.model.ClientTypeEnum;
import com.tx.local.security.util.AbstractWebContextUtils;

/**
 * WebContext容器工具类<br/>
 * <功能详细描述>
 * 
 * @author  Administrator
 * @version  [版本号, 2019年7月18日]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public class ClientWebContextUtils extends AbstractWebContextUtils {
    
    /**
             * 获取OperatorUserDetails实例<br/>
     * <功能详细描述>
     * @return [参数说明]
     *
     * @return OperatorUserDetails [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    public static ClientUserDetails getClientUserDetails() {
        Authentication authentication = getAuthentication();
        if (authentication != null && authentication.getPrincipal() != null
                && authentication.getPrincipal() instanceof ClientUserDetails) {
            ClientUserDetails details = (ClientUserDetails) authentication
                    .getPrincipal();
            return details;
        } else {
            return null;
        }
    }
    
    /**
     *  从会话中获取当前登录人员
     * <功能详细描述>
     * @return [参数说明]
     *
     * @return Operator [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    public static Client getClient() {
        ClientUserDetails details = getClientUserDetails();
        if (null == details) {
            return null;
        }
        
        Client client = details.getClient();
        return client;
    }
    
    /**
     * 获取当前操作人员的id
     * <功能详细描述>
     * @return [参数说明]
     *
     * @return String [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    public static String getClientId() {
        Client client = getClient();
        String clientId = client == null ? null : client.getId();
        return clientId;
    }
    
    /**
     * 获取当前操作人员的id
     * <功能详细描述>
     * @return [参数说明]
     * 
     * @return String [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    public static String getClientUsername() {
        Client client = getClient();
        String clientUsername = client == null ? null : client.getUsername();
        return clientUsername;
    }
    
    /**
     * 获取当前操作人员的类型
     * <功能详细描述>
     * @return [参数说明]
     *
     * @return String [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    public static ClientTypeEnum getClientType() {
        Client client = getClient();
        ClientTypeEnum clientType = client == null ? null : client.getType();
        return clientType;
    }
    
}
