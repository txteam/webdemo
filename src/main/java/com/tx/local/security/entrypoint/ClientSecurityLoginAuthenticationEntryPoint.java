/*
 * 描          述:  <描述>
 * 修  改   人:  Administrator
 * 修改时间:  2019年7月8日
 * <修改描述:>
 */
package com.tx.local.security.entrypoint;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.LoginUrlAuthenticationEntryPoint;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.PathMatcher;

import com.tx.core.util.WebUtils;

/**
 * 自定义认证进入节点：定义后台管理员以及客户进入不同的登陆页面<br/>
 * <功能详细描述>
 * 
 * @author  Administrator
 * @version  [版本号, 2019年7月8日]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public class ClientSecurityLoginAuthenticationEntryPoint
        extends LoginUrlAuthenticationEntryPoint {
    
    private Map<String, String> authEntryPointMap = new HashMap<String, String>();
    
    /** 路径匹配器 */
    private PathMatcher pathMatcher = new AntPathMatcher();
    
    /** <默认构造函数> */
    public ClientSecurityLoginAuthenticationEntryPoint(String loginFormUrl) {
        super(loginFormUrl);
    }
    
    /**
     * @param request
     * @param response
     * @param authException
     * @throws IOException
     * @throws ServletException
     */
    @Override
    public void commence(HttpServletRequest request,
            HttpServletResponse response, AuthenticationException authException)
            throws IOException, ServletException {
        if (WebUtils.isAjaxRequest(request)) {
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED,
                    authException.getMessage());
        } else {
            super.commence(request, response, authException);
        }
    }
    
    /**
     * @return 返回 authEntryPointMap
     */
    public Map<String, String> getAuthEntryPointMap() {
        return authEntryPointMap;
    }
    
    /**
     * @param 对authEntryPointMap进行赋值
     */
    public void setAuthEntryPointMap(Map<String, String> authEntryPointMap) {
        this.authEntryPointMap = authEntryPointMap;
    }
    
    /**
     * @return 返回 pathMatcher
     */
    public PathMatcher getPathMatcher() {
        return pathMatcher;
    }
    
    /**
     * @param 对pathMatcher进行赋值
     */
    public void setPathMatcher(PathMatcher pathMatcher) {
        this.pathMatcher = pathMatcher;
    }
}
