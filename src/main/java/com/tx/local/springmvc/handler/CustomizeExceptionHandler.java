/*
 * 描          述:  <描述>
 * 修  改   人:  Administrator
 * 修改时间:  2019年1月28日
 * <修改描述:>
 */
package com.tx.local.springmvc.handler;

import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.boot.autoconfigure.web.ErrorAttributes;
import org.springframework.boot.autoconfigure.web.ErrorViewResolver;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.tx.core.exceptions.SILException;

/**
 * 自定义处理异常解析器<br/>
 * <功能详细描述>
 * 
 * @author  Administrator
 * @version  [版本号, 2019年1月28日]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
//@Controller
public class CustomizeExceptionHandler{
    
    /** 日志记录器 */
    private static Logger logger = LoggerFactory
            .getLogger(CustomizeExceptionHandler.class);
    
    /** 错误属性处理类 */
    private ErrorAttributes errorAttributes;
    
    /** 错误视图解析器 */
    private List<ErrorViewResolver> errorViewResolvers;
    
    /** <默认构造函数> */
    public CustomizeExceptionHandler(ErrorAttributes errorAttributes,
            ObjectProvider<List<ErrorViewResolver>> errorViewResolversProvider) {
        super();
        this.errorAttributes = errorAttributes;
        this.errorViewResolvers = errorViewResolversProvider.getIfAvailable();
    }
    
    /**
     * @param request
     * @param response
     * @param handler
     * @param ex
     * @return
     */
    @ExceptionHandler
    public Object errorHandler(HttpServletRequest request,
            HttpServletResponse response, Exception ex) {
        if (!(ex instanceof SILException) || ex.getCause() == null) {
            //仅记录非自定义异常，获自定义异常无错误来源的情况
            logger.error(ex.getMessage(), ex);
        }
        
        if (isAjaxRequest(request)) {
            Map<String, Object> body = getErrorAttributes(request,
                    isIncludeStackTrace(request, MediaType.ALL));
            HttpStatus status = getStatus(request);
            ResponseEntity<Map<String, Object>> responseEntiry = new ResponseEntity<Map<String, Object>>(
                    body, status);
            
            return responseEntiry;
        } else {
            HttpStatus status = getStatus(request);
            Map<String, Object> model = Collections
                    .unmodifiableMap(getErrorAttributes(request,
                            isIncludeStackTrace(request, MediaType.TEXT_HTML)));
            response.setStatus(status.value());
            ModelAndView modelAndView = resolveErrorView(request,
                    response,
                    status,
                    model);
            return (modelAndView != null ? modelAndView
                    : new ModelAndView("error", model));
        }
    }
    
    /**
     * 获取错误属性信息<br/>
     * <功能详细描述>
     * @param request
     * @param includeStackTrace
     * @return [参数说明]
     * 
     * @return Map<String,Object> [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    protected Map<String, Object> getErrorAttributes(HttpServletRequest request,
            boolean includeStackTrace) {
        RequestAttributes requestAttributes = new ServletRequestAttributes(
                request);
        return this.errorAttributes.getErrorAttributes(requestAttributes,
                includeStackTrace);
    }
    
    /**
     * 是否包含异常堆栈信息<br/>
     * <功能详细描述>
     * @param request
     * @param produces
     * @return [参数说明]
     * 
     * @return boolean [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    protected boolean isIncludeStackTrace(HttpServletRequest request,
            MediaType produces) {
        //IncludeStacktrace include = getErrorProperties().getIncludeStacktrace();
        //            if (include == IncludeStacktrace.ALWAYS) {
        //                return true;
        //            }
        //            if (include == IncludeStacktrace.ON_TRACE_PARAM) {
        //                return getTraceParameter(request);
        //            }
        return false;
    }
    
    /**
     * 判断请求中是否包含堆栈信息参数<br/>
     * <功能详细描述>
     * @param request
     * @return [参数说明]
     * 
     * @return boolean [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    protected boolean getTraceParameter(HttpServletRequest request) {
        String parameter = request.getParameter("trace");
        if (parameter == null) {
            return false;
        }
        return !"false".equals(parameter.toLowerCase(Locale.ENGLISH));
    }
    
    /**
     * 获取错误视图<br/>
     * <功能详细描述>
     * @param request
     * @param response
     * @param status
     * @param model
     * @return [参数说明]
     * 
     * @return ModelAndView [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    protected ModelAndView resolveErrorView(HttpServletRequest request,
            HttpServletResponse response, HttpStatus status,
            Map<String, Object> model) {
        if (CollectionUtils.isEmpty(this.errorViewResolvers)) {
            return null;
        }
        for (ErrorViewResolver resolver : this.errorViewResolvers) {
            ModelAndView modelAndView = resolver.resolveErrorView(request,
                    status,
                    model);
            if (modelAndView != null) {
                return modelAndView;
            }
        }
        return null;
    }
    
    /**
     * 获取请求相应状态<br/>
     * <功能详细描述>
     * @param request
     * @return [参数说明]
     * 
     * @return HttpStatus [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    protected HttpStatus getStatus(HttpServletRequest request) {
        Integer statusCode = (Integer) request
                .getAttribute("javax.servlet.error.status_code");
        if (statusCode == null) {
            return HttpStatus.INTERNAL_SERVER_ERROR;
        }
        try {
            return HttpStatus.valueOf(statusCode);
        } catch (Exception ex) {
            return HttpStatus.INTERNAL_SERVER_ERROR;
        }
    }
    
    /**
     * 是否是ajax请求<br/>
     * <功能详细描述>
     * @param request
     * @return [参数说明]
     * 
     * @return boolean [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    public static boolean isAjaxRequest(HttpServletRequest request) {
        //Header
        String requestType = request.getHeader("X-Requested-With");
        if (StringUtils.equalsIgnoreCase("XMLHttpRequest", requestType)) {
            return true;
        }
        
        //accept
        String accept = request.getHeader(HttpHeaders.ACCEPT);
        if (StringUtils.isEmpty(accept)) {
            accept = request.getHeader("accept");
        }
        if (!StringUtils.isEmpty(accept) && StringUtils
                .indexOfIgnoreCase(accept, "application/json") != -1) {
            return true;
        }
        
        //contentType
        String contentType = request.getHeader(HttpHeaders.CONTENT_TYPE);
        if (StringUtils.isEmpty(contentType)) {
            contentType = request.getHeader("content-type");
        }
        if (!StringUtils.isEmpty(contentType) && StringUtils
                .indexOfIgnoreCase(contentType, "application/json") != -1) {
            return true;
        }
        
        return false;
    }
}
