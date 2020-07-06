/*
 * 描          述:  <描述>
 * 修  改   人:  Administrator
 * 修改时间:  2019年7月25日
 * <修改描述:>
 */
package com.tx.local.security.handler;

import com.tx.core.util.WebUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * ajax认证成功句柄<br/>
 * <功能详细描述>
 * 
 * @author  Administrator
 * @version  [版本号, 2019年7月25日]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public class WapClientSecurityAuthenticationSuccessHandler
        extends SavedRequestAwareAuthenticationSuccessHandler {

    /** 日志记录器 */
    private Logger logger = LoggerFactory
            .getLogger(WapClientSecurityAuthenticationSuccessHandler.class);

    /** <默认构造函数> */
    public WapClientSecurityAuthenticationSuccessHandler() {
        super();
        setDefaultTargetUrl("/wap/client/mainframe");
    }
    
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request,
            HttpServletResponse response, Authentication authentication)
            throws IOException, ServletException {
        logger.info("登录成功：" + authentication.getName());

        if (WebUtils.isAjaxRequest(request)) {
            //如果为ajax请求，则返回ajax结果
            response.setContentType("application/json;charset=utf-8");
            PrintWriter out = response.getWriter();
            out.write("{" + "\"status\":\"success\"," + "\"msg\":\"登录成功\","
                    + "\"data\":\"" + authentication.getName() + "\"}");
            out.flush();
            out.close();
            return;
        }

        super.onAuthenticationSuccess(request, response, authentication);
    }
}
