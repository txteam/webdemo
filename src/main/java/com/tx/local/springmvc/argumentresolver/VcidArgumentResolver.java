/*
 * 描          述:  <描述>
 * 修  改   人:  Administrator
 * 修改时间:  2019年11月18日
 * <修改描述:>
 */
package com.tx.local.springmvc.argumentresolver;

import org.springframework.core.MethodParameter;
import org.springframework.web.bind.annotation.ValueConstants;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.annotation.AbstractNamedValueMethodArgumentResolver;

import com.tx.component.security.context.SecurityContext;
import com.tx.local.operator.model.OperatorRoleEnum;
import com.tx.local.security.model.OperatorUserDetails;
import com.tx.local.security.util.WebContextUtils;

/**
 * 虚中心参数解析器<br/>
 * <功能详细描述>
 * 
 * @author  Administrator
 * @version  [版本号, 2019年11月18日]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public class VcidArgumentResolver extends AbstractNamedValueMethodArgumentResolver {
    
    /**
     * @param parameter
     * @return
     */
    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        //非简单对象，并且RequestModelParam.value,name 不为空
        return (parameter.hasParameterAnnotation(VcidRequestParam.class)
                && String.class.equals(parameter.getParameterType()));
    }

    /**
     * @param parameter
     * @return
     */
    @Override
    protected NamedValueInfo createNamedValueInfo(MethodParameter parameter) {
        return new NamedValueInfo("vcid", false, ValueConstants.DEFAULT_NONE);
    }

    /**
     * @param name
     * @param parameter
     * @param request
     * @return
     * @throws Exception
     */
    @Override
    protected Object resolveName(String name, MethodParameter parameter,
            NativeWebRequest request) throws Exception {
        Object arg = null;
        Object principal = SecurityContext.getContext().getPrincipal();
        if (principal instanceof OperatorUserDetails) {
            if (!SecurityContext.getContext()
                    .hasRole(OperatorRoleEnum.SUPER_ADMIN.getId())) {
                //如果不为超级管理员，则参数vcid直接获取操作员所在虚中心
                arg = WebContextUtils.getCurrentVcid();
            }else{
                arg = request.getParameter("vcid");
            }
        }else{
            arg = request.getParameter("vcid");
        }
        
        return arg;
    }
}
