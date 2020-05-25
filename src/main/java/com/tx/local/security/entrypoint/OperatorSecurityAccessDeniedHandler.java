/*
 * 描          述:  <描述>
 * 修  改   人:  Administrator
 * 修改时间:  2019年8月6日
 * <修改描述:>
 */
package com.tx.local.security.entrypoint;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.WebAttributes;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.PathMatcher;

import com.tx.core.util.WebUtils;

/**
 * 访问控制处理<br/>
 * <功能详细描述>
 * 
 * @author  Administrator
 * @version  [版本号, 2019年8月6日]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public class OperatorSecurityAccessDeniedHandler
        implements AccessDeniedHandler {
    
    /** 错误页映射 */
    private Map<String, String> errorPageMap = new HashMap<String, String>();
    
    /** 路径匹配器 */
    private PathMatcher pathMatcher = new AntPathMatcher();
    
    /** 错误页 */
    private String errorPage;
    
    /** <默认构造函数> */
    public OperatorSecurityAccessDeniedHandler(String errorPage) {
        super();
        
        errorPageMap.put("/mainframe/**", "/admin/login");
        errorPageMap.put("/admin/mainframe/**", "/admin/login");
        errorPageMap.put("/background/mainframe/**", "/admin/login");
    }
    
    /**
     * @param request
     * @param response
     * @param accessDeniedException
     * @throws IOException
     * @throws ServletException
     */
    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response,
            AccessDeniedException accessDeniedException)
            throws IOException, ServletException {
        if (response.isCommitted()) {
            return;
        }
        if (WebUtils.isAjaxRequest(request)) {
            //AJAX请求,使用response发送403
            response.sendError(403);
        } else {
            String requestURI = request.getRequestURI()
                    .replace(request.getContextPath(), "");
            for (String url : this.errorPageMap.keySet()) {
                if (this.pathMatcher.match(url, requestURI)) {
                    String errorPageTemp = this.errorPageMap.get(url);
                    
                    // Put exception into request scope (perhaps of use to a view)
                    request.setAttribute(WebAttributes.ACCESS_DENIED_403,
                            accessDeniedException);
                    
                    // Set the 403 status code.
                    response.setStatus(HttpStatus.FORBIDDEN.value());
                    
                    // forward to error page.
                    RequestDispatcher dispatcher = request
                            .getRequestDispatcher(errorPageTemp);
                    dispatcher.forward(request, response);
                }
            }
            if (errorPage != null) {
                // Put exception into request scope (perhaps of use to a view)
                request.setAttribute(WebAttributes.ACCESS_DENIED_403,
                        accessDeniedException);
                
                // Set the 403 status code.
                response.setStatus(HttpStatus.FORBIDDEN.value());
                
                // forward to error page.
                RequestDispatcher dispatcher = request
                        .getRequestDispatcher(errorPage);
                dispatcher.forward(request, response);
            } else {
                response.sendError(HttpStatus.FORBIDDEN.value(),
                        HttpStatus.FORBIDDEN.getReasonPhrase());
            }
            //非AJAX请求，跳转系统默认的403错误界面，在web.xml中配置
            response.sendError(HttpServletResponse.SC_FORBIDDEN,
                    accessDeniedException.getMessage());
        }
    }
    
    /**
     * The error page to use. Must begin with a "/" and is interpreted relative to the
     * current context root.
     *
     * @param errorPage the dispatcher path to display
     *
     * @throws IllegalArgumentException if the argument doesn't comply with the above
     * limitations
     */
    public void setErrorPage(String errorPage) {
        if ((errorPage != null) && !errorPage.startsWith("/")) {
            throw new IllegalArgumentException("errorPage must begin with '/'");
        }
        
        this.errorPage = errorPage;
    }
    
}
