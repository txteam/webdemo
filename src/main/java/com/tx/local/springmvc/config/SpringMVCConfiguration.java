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
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

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
public class SpringMVCConfiguration
        implements WebMvcConfigurer, InitializingBean {
    
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
     * @param converters
     */
    @Override
    public void extendMessageConverters(
            List<HttpMessageConverter<?>> converters) {
        //bufferedImageConverter
        converters.add(new BufferedImageHttpMessageConverter());
    }
    
    /**
     * @param resolvers
     */
    @Override
    public void addArgumentResolvers(
            List<HandlerMethodArgumentResolver> resolvers) {
        WebMvcConfigurer.super.addArgumentResolvers(resolvers);
        
        //resolvers.add(new VcidArgumentResolver());
    }
    
    /**
     * @param exceptionResolvers
     */
    @Override
    public void extendHandlerExceptionResolvers(
            List<HandlerExceptionResolver> exceptionResolvers) {
        //super.extendHandlerExceptionResolvers(exceptionResolvers);
        //添加自定义异常解析器
        //exceptionResolvers.add(customizedHandlerExceptionResolver());
    }
    
    /**
     * 资源访问处理器<br/>
     * @param registry
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        //super.addResourceHandlers(registry);
        registry.addResourceHandler("/favicon.ico")
                .addResourceLocations("classpath:/static/favicon.ico")
                .setCachePeriod(86400);
        registry.addResourceHandler("/robots.txt")
                .addResourceLocations("classpath:/static/robots.txt")
                .setCachePeriod(86400);
        
        //WebMvcAutoConfiguration中覆写了webjars的的读取方法需要覆写回来
        //FIXME:临时将缓存时间调整为0
        Integer cachePeriod = 0;//86400;
        registry.addResourceHandler("/webjars/**")
                .addResourceLocations("classpath:/static/webjars/")
                .addResourceLocations("classpath:/META-INF/resources/webjars/")
                .setCachePeriod(cachePeriod);
        
    }
    
    /**
     * 注册自定义的拦截器
     * @param registry
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //registry.addInterceptor(new VirtualCenterInjectInterceptor());
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
        return this.validator;
    }
}
