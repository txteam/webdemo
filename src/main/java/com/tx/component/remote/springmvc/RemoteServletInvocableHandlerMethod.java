///*
// * 描          述:  <描述>
// * 修  改   人:  Administrator
// * 修改时间:  2017年2月13日
// * <修改描述:>
// */
//package com.tx.component.remote.springmvc;
//
//import java.io.IOException;
//import java.lang.annotation.Annotation;
//import java.lang.reflect.InvocationTargetException;
//import java.lang.reflect.Method;
//import java.util.concurrent.Callable;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.http.HttpStatus;
//import org.springframework.util.ClassUtils;
//import org.springframework.util.ReflectionUtils;
//import org.springframework.util.StringUtils;
//import org.springframework.web.bind.annotation.ResponseStatus;
//import org.springframework.web.context.request.ServletWebRequest;
//import org.springframework.web.method.HandlerMethod;
//import org.springframework.web.method.support.HandlerMethodReturnValueHandler;
//import org.springframework.web.method.support.HandlerMethodReturnValueHandlerComposite;
//import org.springframework.web.method.support.InvocableHandlerMethod;
//import org.springframework.web.method.support.ModelAndViewContainer;
//import org.springframework.web.servlet.View;
//import org.springframework.web.util.NestedServletException;
//
//import com.tx.component.remote.model.RemoteResult;
//import com.tx.core.exceptions.SILException;
//
///**
// * 远程调用ServletInvaleHandleMethod
// * <功能详细描述>
// * 
// * @author  Administrator
// * @version  [版本号, 2017年2月13日]
// * @see  [相关类/方法]
// * @since  [产品/模块版本]
// */
//public class RemoteServletInvocableHandlerMethod extends InvocableHandlerMethod {
//    
//    //日志记录器
//    private static final Logger logger = LoggerFactory.getLogger(RemoteServletInvocableHandlerMethod.class);
//    
//    private HttpStatus responseStatus;
//    
//    private String responseReason;
//    
//    private HandlerMethodReturnValueHandlerComposite returnValueHandlers;
//    
//    /**
//     * Creates an instance from the given handler and method.
//     */
//    public RemoteServletInvocableHandlerMethod(Object handler, Method method) {
//        super(handler, method);
//        initResponseStatus();
//    }
//    
//    /**
//     * Create an instance from a {@code HandlerMethod}.
//     */
//    public RemoteServletInvocableHandlerMethod(HandlerMethod handlerMethod) {
//        super(handlerMethod);
//        initResponseStatus();
//    }
//    
//    /**
//     * @param args
//     * @return
//     * @throws Exception
//     */
//    @Override
//    protected Object doInvoke(Object... args) throws Exception {
//        ReflectionUtils.makeAccessible(getBridgedMethod());
//        RemoteResult remoteResult = null;
//        try {
//            Object returnValue = getBridgedMethod().invoke(getBean(), args);
//            if (returnValue != null && returnValue instanceof RemoteResult) {
//                remoteResult = (RemoteResult) returnValue;
//            } else {
//                remoteResult = new RemoteResult(returnValue);
//            }
//        } catch (IllegalArgumentException ex) {
//            logger.error(ex.getMessage(), ex);
//            //logger.warn(ex.getMessage());
//            
//            remoteResult = new RemoteResult(999, ex.getMessage());
//        } catch (InvocationTargetException ex) {
//            logger.error(ex.getMessage(), ex);
//            //logger.warn(ex.getMessage());
//            
//            if (ex.getTargetException() instanceof SILException) {
//                SILException sie = (SILException) ex.getTargetException();
//                remoteResult = new RemoteResult(sie.getErrorCode(),
//                        sie.getErrorMessage());
//            } else {
//                remoteResult = new RemoteResult(999, ex.getMessage());
//            }
//        } catch (SILException e) {
//            logger.error(e.getMessage(), e);
//            //logger.warn(e.getMessage());
//            
//            remoteResult = new RemoteResult(e.getErrorCode(),
//                    e.getErrorMessage());
//        } catch (Exception e) {
//            logger.debug(e.getMessage(), e);
//            logger.warn(e.getMessage());
//            
//            remoteResult = new RemoteResult(999, e.getMessage());
//        } catch (Throwable e) {
//            logger.debug(e.getMessage(), e);
//            logger.warn(e.getMessage());
//            
//            remoteResult = new RemoteResult(999, e.getMessage());
//        }
//        return remoteResult;
//    }
//    
//    private void initResponseStatus() {
//        ResponseStatus annot = getMethodAnnotation(ResponseStatus.class);
//        if (annot != null) {
//            this.responseStatus = annot.value();
//            this.responseReason = annot.reason();
//        }
//    }
//    
//    /**
//     * Register {@link HandlerMethodReturnValueHandler} instances to use to
//     * handle return values.
//     */
//    public void setHandlerMethodReturnValueHandlers(
//            HandlerMethodReturnValueHandlerComposite returnValueHandlers) {
//        this.returnValueHandlers = returnValueHandlers;
//    }
//    
//    /**
//     * Invokes the method and handles the return value through a registered
//     * {@link HandlerMethodReturnValueHandler}.
//     *
//     * @param webRequest the current request
//     * @param mavContainer the ModelAndViewContainer for this request
//     * @param providedArgs "given" arguments matched by type, not resolved
//     */
//    public final void invokeAndHandle(ServletWebRequest webRequest,
//            ModelAndViewContainer mavContainer, Object... providedArgs)
//            throws Exception {
//        
//        Object returnValue = invokeForRequest(webRequest,
//                mavContainer,
//                providedArgs);
//        
//        setResponseStatus(webRequest);
//        
//        if (returnValue == null) {
//            if (isRequestNotModified(webRequest) || hasResponseStatus()
//                    || mavContainer.isRequestHandled()) {
//                mavContainer.setRequestHandled(true);
//                return;
//            }
//        } else if (StringUtils.hasText(this.responseReason)) {
//            mavContainer.setRequestHandled(true);
//            return;
//        }
//        
//        mavContainer.setRequestHandled(false);
//        
//        try {
//            this.returnValueHandlers.handleReturnValue(returnValue,
//                    getReturnValueType(returnValue),
//                    mavContainer,
//                    webRequest);
//        } catch (Exception ex) {
//            if (logger.isTraceEnabled()) {
//                logger.trace(getReturnValueHandlingErrorMessage("Error handling return value",
//                        returnValue),
//                        ex);
//            }
//            throw ex;
//        }
//    }
//    
//    /**
//     * Set the response status according to the {@link ResponseStatus} annotation.
//     */
//    private void setResponseStatus(ServletWebRequest webRequest)
//            throws IOException {
//        if (this.responseStatus == null) {
//            return;
//        }
//        
//        if (StringUtils.hasText(this.responseReason)) {
//            webRequest.getResponse().sendError(this.responseStatus.value(),
//                    this.responseReason);
//        } else {
//            webRequest.getResponse().setStatus(this.responseStatus.value());
//        }
//        
//        // to be picked up by the RedirectView
//        webRequest.getRequest().setAttribute(View.RESPONSE_STATUS_ATTRIBUTE,
//                this.responseStatus);
//    }
//    
//    /**
//     * Does the given request qualify as "not modified"?
//     * @see ServletWebRequest#checkNotModified(long)
//     * @see ServletWebRequest#checkNotModified(String)
//     */
//    private boolean isRequestNotModified(ServletWebRequest webRequest) {
//        return webRequest.isNotModified();
//    }
//    
//    /**
//     * Does this method have the response status instruction?
//     */
//    private boolean hasResponseStatus() {
//        return responseStatus != null;
//    }
//    
//    private String getReturnValueHandlingErrorMessage(String message,
//            Object returnValue) {
//        StringBuilder sb = new StringBuilder(message);
//        if (returnValue != null) {
//            sb.append(" [type=" + returnValue.getClass().getName() + "] ");
//        }
//        sb.append("[value=" + returnValue + "]");
//        return getDetailedErrorMessage(sb.toString());
//    }
//    
//    /**
//     * Return a ServletInvocableHandlerMethod that will process the value returned
//     * from an async operation essentially either applying return value handling or
//     * raising an exception if the end result is an Exception.
//     */
//    RemoteServletInvocableHandlerMethod wrapConcurrentResult(final Object result) {
//        
//        return new RemoteCallableHandlerMethod(new Callable<Object>() {
//            
//            public Object call() throws Exception {
//                if (result instanceof Exception) {
//                    throw (Exception) result;
//                } else if (result instanceof Throwable) {
//                    throw new NestedServletException("Async processing failed",
//                            (Throwable) result);
//                }
//                return result;
//            }
//        });
//    }
//    
//    /**
//     * A ServletInvocableHandlerMethod sub-class that invokes a given
//     * {@link Callable} and "inherits" the annotations of the containing class
//     * instance, useful for invoking a Callable returned from a HandlerMethod.
//     */
//    private class RemoteCallableHandlerMethod extends
//            RemoteServletInvocableHandlerMethod {
//        
//        public RemoteCallableHandlerMethod(Callable<?> callable) {
//            super(callable, ClassUtils.getMethod(callable.getClass(), "call"));
//            this.setHandlerMethodReturnValueHandlers(RemoteServletInvocableHandlerMethod.this.returnValueHandlers);
//        }
//        
//        @Override
//        public <A extends Annotation> A getMethodAnnotation(
//                Class<A> annotationType) {
//            return RemoteServletInvocableHandlerMethod.this.getMethodAnnotation(annotationType);
//        }
//    }
//    
//}
