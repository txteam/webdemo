/*
 * 描          述:  <描述>
 * 修  改   人:  PengQingyang
 * 修改时间:  2012-12-2
 * <修改描述:>
 */
package com.boda.components.auth.springmvc;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.cxf.common.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.boda.components.auth.context.AuthContext;
import com.boda.components.auth.context.SessionAuthContext;
import com.tx.core.exceptions.parameter.ParameterIsEmptyException;


 /**
  * <功能简述>
  * <功能详细描述>
  * 
  * @author  PengQingyang
  * @version  [版本号, 2012-12-2]
  * @see  [相关类/方法]
  * @since  [产品/模块版本]
  */
public class AuthContextSupportInterceptor implements HandlerInterceptor{

    /**
     * @param request
     * @param response
     * @param handler
     * @return
     * @throws Exception
     */
    @Override
    public boolean preHandle(HttpServletRequest request,
            HttpServletResponse response, Object handler) throws Exception {
        String operatorId = request.getParameter("operatorId");//得到操作员Id
        //判断操作员Id是不是为空，为空则抛出异常
        if (StringUtils.isEmpty(operatorId)) {
            throw new ParameterIsEmptyException(
                    "request.getParameter('operatorId') operatorId is empty.");
        }
        SessionAuthContext.preHandler(request, response);
    	AuthContext.initCurrentUserAuthContextWhenLogin(operatorId);
    	return true;
    }

    /**
     * @param request
     * @param response
     * @param handler
     * @param modelAndView
     * @throws Exception
     */
    @Override
    public void postHandle(HttpServletRequest request,
            HttpServletResponse response, Object handler,
            ModelAndView modelAndView) throws Exception {
        
    }

    /**
     * @param request
     * @param response
     * @param handler
     * @param ex
     * @throws Exception
     */
    @Override
    public void afterCompletion(HttpServletRequest request,
            HttpServletResponse response, Object handler, Exception ex)
            throws Exception {
    	SessionAuthContext.afterHandler();
    }
    
}
