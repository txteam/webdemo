/*
 * 描          述:  <描述>
 * 修  改   人:  Administrator
 * 修改时间:  2014年11月20日
 * <修改描述:>
 */
package com.tx.component.file.context.impl;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.tx.component.file.context.SessionPlugin;

/**
 * 会话插件实现ForSpringMVC
 * <功能详细描述>
 * 
 * @author  Administrator
 * @version  [版本号, 2014年11月20日]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public class SessionPluginImplForSpringMVC implements SessionPlugin {
    
    /**
     * @return
     */
    @Override
    public Map<String, Object> getSessionMap() {
        Map<String, Object> resMap = new HashMap<String, Object>();
        HttpSession session = getSession(false);
        
        if (session != null) {
            @SuppressWarnings("unchecked")
            Enumeration<Object> attNames = session.getAttributeNames();
            while (attNames.hasMoreElements()) {
                Object attNameTempObj = attNames.nextElement();
                if (attNameTempObj instanceof String) {
                    Object objTemp = session.getAttribute((String) attNameTempObj);
                    
                    resMap.put((String) attNameTempObj, objTemp);
                }
            }
        }
        return resMap;
    }
    
    /**
     * 获取当前会话
     * <功能详细描述>
     * @param flag
     * @return [参数说明]
     * 
     * @return HttpSession [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
    */
    public static HttpSession getSession(boolean flag) {
        if (getRequest() == null) {
            return null;
        }
        return getRequest().getSession(flag);
    }
    
    /**
     * 获取当前请求RequestAttributes
     * <功能详细描述>
     * @return [参数说明]
     * 
     * @return HttpServletRequest [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
    */
    public static RequestAttributes getRequestAttributes() {
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        
        return requestAttributes;
    }
    
    /**
     * 获取当前请求request
     * <功能详细描述>
     * @return [参数说明]
     * 
     * @return HttpServletRequest [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
    */
    public static HttpServletRequest getRequest() {
        if (getRequestAttributes() != null) {
            return ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        } else {
            return null;
        }
    }
    
}
