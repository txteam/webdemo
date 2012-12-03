/*
 * 描          述:  <描述>
 * 修  改   人:  brady
 * 修改时间:  2012-12-3
 * <修改描述:>
 */
package com.tx.component.auth.context;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 回话权限容器
 * <功能详细描述>
 * 
 * @author  brady
 * @version  [版本号, 2012-12-3]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public class SessionAuthContext {
    
    /** request请求的引用 */
    private HttpServletRequest request;
    
    /** response的引用 */
    private HttpServletResponse response;
    
    /** handler */
    private Object handler;
    
    /**
     * @return 返回 request
     */
    public HttpServletRequest getRequest() {
        return request;
    }
    
    /**
     * @param 对request进行赋值
     */
    public void setRequest(HttpServletRequest request) {
        this.request = request;
    }
    
    /**
     * @return 返回 response
     */
    public HttpServletResponse getResponse() {
        return response;
    }
    
    /**
     * @param 对response进行赋值
     */
    public void setResponse(HttpServletResponse response) {
        this.response = response;
    }
    
    /**
     * @return 返回 handler
     */
    public Object getHandler() {
        return handler;
    }
    
    /**
     * @param 对handler进行赋值
     */
    public void setHandler(Object handler) {
        this.handler = handler;
    }
}
