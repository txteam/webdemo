/*
 * 描          述:  <描述>
 * 修  改   人:  zhangwei
 * 修改时间:  2018年7月20日
 * <修改描述:>
 */
package com.tx.local.springmvc.config;

import java.util.List;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.BufferedImageHttpMessageConverter;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.validation.Validator;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * web配置器<br/>
 * <功能详细描述>
 * 
 * @author  zhangwei
 * @version  [版本号, 2018年7月20日]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
@Configuration
public class SpringMVCConfiguration extends WebMvcConfigurerAdapter
        implements InitializingBean {
    
    /** 本地化验证器 */
    private LocalValidatorFactoryBean validator;
    
    /**
     * @throws Exception
     */
    @Override
    public void afterPropertiesSet() throws Exception {
        this.validator = new LocalValidatorFactoryBean();
    }
    
    /**
     * 参数解析器<br/>
     * @param argumentResolvers
     */
    @Override
    public void addArgumentResolvers(
            List<HandlerMethodArgumentResolver> argumentResolvers) {
        super.addArgumentResolvers(argumentResolvers);
    }
    
    /**
     * @param converters
     */
    @Override
    public void extendMessageConverters(
            List<HttpMessageConverter<?>> converters) {
        super.extendMessageConverters(converters);
        
        //bufferedImageConverter
        converters.add(new BufferedImageHttpMessageConverter());
    }
    
    /**
     * @param exceptionResolvers
     */
    @Override
    public void extendHandlerExceptionResolvers(
            List<HandlerExceptionResolver> exceptionResolvers) {
        super.extendHandlerExceptionResolvers(exceptionResolvers);
        //添加自定义异常解析器
        //exceptionResolvers.add(customizedHandlerExceptionResolver());
    }
    
    /**
     * 资源访问处理器<br/>
     * @param registry
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        super.addResourceHandlers(registry);
        registry.addResourceHandler("/favicon.ico")
                .addResourceLocations("classpath:/static/favicon.ico")
                .setCachePeriod(86400);
        registry.addResourceHandler("/robots.txt")
                .addResourceLocations("classpath:/static/robots.txt")
                .setCachePeriod(86400);
        
        //WebMvcAutoConfiguration中覆写了webjars的的读取方法需要覆写回来
        Integer cachePeriod = 86400;
        registry.addResourceHandler("/webjars/**")
                .addResourceLocations("classpath:/static/webjars/")
                .setCachePeriod(cachePeriod);
        
    }
    
    /**
     * 注册自定义的拦截器
     * @param registry
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        
    }
    
    /**
     * validator实例<br/>
     * <功能详细描述>
     * @return [参数说明]
     * 
     * @return LocalValidatorFactoryBean [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    public LocalValidatorFactoryBean validator() {
        return this.validator;
    }
    
    /**
     * @return
     */
    @Override
    public Validator getValidator() {
        return super.getValidator();
    }
}
