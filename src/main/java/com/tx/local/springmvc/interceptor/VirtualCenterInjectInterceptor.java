/*
 * 描          述:  <描述>
 * 修  改   人:  PengQingyang
 * 修改时间:  2012-10-19
 * <修改描述:>
 */
package com.tx.local.springmvc.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.tx.local.security.util.WebContextUtils;

/**
 * 支持利用虚中心嵌套机制查询虚中心<br/>
 *    如果利用拦截器机制，需要调整request继承，使request.getParameterMap能够进行修改<br/>
 *    担心该操作会对后续不理解该机制的研发人员构成困扰，仔细考虑再三后该类不启用
 * 
 * @author  Administrator
 * @version  [版本号, 2019年11月20日]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public class VirtualCenterInjectInterceptor implements HandlerInterceptor {
    
    //private static Logger logger = LoggerFactory.getLogger(VirtualCenterInjectInterceptor.class);
    
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
        if(WebContextUtils.getUserDetails() != null){
            if(!WebContextUtils.isSuperAdmin()){
                //request.getParameterMap().put("vcid", new String[]{WebContextUtils.getVcid()});
                //AssertUtils
            }
        }
        
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
    }
}