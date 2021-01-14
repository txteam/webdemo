/*
 * 描          述:  <描述>
 * 修  改   人:  zhangwei
 * 修改时间:  2018年7月20日
 * <修改描述:>
 */
package com.tx.local.springmvc.config;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.boot.autoconfigure.thymeleaf.ThymeleafProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.http.MediaType;
import org.springframework.http.converter.BufferedImageHttpMessageConverter;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.validation.Validator;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.config.annotation.PathMatchConfigurer;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.tx.core.springmvc.support.StringToDateConverter;
import com.tx.local.springmvc.path.LocalPathReplaceUtils;

/**
 * web配置器<br/>
 * nginx contextPath解决方案
 * https://www.cnblogs.com/codestory/p/7716914.html
 * http://www.360doc.com/content/18/0210/13/16915_729134219.shtml  nginx  contextPath解决方案
 * <功能详细描述>
 * 
 * @author  zhangwei
 * @version  [版本号, 2018年7月20日]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
@Configuration
public class SpringMVCConfiguration
        implements WebMvcConfigurer, InitializingBean, ApplicationContextAware {
    
    /** 本地化验证器 */
    private LocalValidatorFactoryBean validator;
    
    /** spring容器句柄 */
    @SuppressWarnings("unused")
    private ApplicationContext applicationContext;
    
    /** 获取thymeleaf属性 */
    @SuppressWarnings("unused")
    private final ThymeleafProperties properties;
    
    @Resource
    private LocalPathReplaceUtils localPathReplaceUtils;
    
    /** <默认构造函数> */
    public SpringMVCConfiguration(ThymeleafProperties properties) {
        this.properties = properties;
    }
    
    /**
    * @param applicationContext
    * @throws BeansException
    */
    @Override
    public void setApplicationContext(ApplicationContext applicationContext)
            throws BeansException {
        this.applicationContext = applicationContext;
    }
    
    /**
     * @throws Exception
     */
    @Override
    public void afterPropertiesSet() throws Exception {
        this.validator = new LocalValidatorFactoryBean();
    }
    
    @Override
    public void configurePathMatch(PathMatchConfigurer configurer) {
        if (this.localPathReplaceUtils.isEnable()) {
            configurer.addPathPrefix(this.localPathReplaceUtils.getPrefix(),
                    c -> c.getPackage().getName().startsWith("com.tx.local"));
        }
    }
    
    /**
     * ApplicationConversionService中已存在
     * DefaultConversionService.addDefaultConverters(registry);
     * DefaultFormattingConversionService.addDefaultFormatters(registry);
     * addApplicationConverters(registry);
     * @param registry
     */
    @Override
    public void addFormatters(FormatterRegistry registry) {
        registry.addConverter(new StringToDateConverter());
    }
    
    /**
     * @param converters
     */
    @Override
    public void extendMessageConverters(
            List<HttpMessageConverter<?>> converters) {
        //bufferedImageConverter
        converters.add(new BufferedImageHttpMessageConverter());
        
        //jackson2converter
        MappingJackson2HttpMessageConverter jackson2converter = new MappingJackson2HttpMessageConverter();
        List<MediaType> mediaTypeList = new ArrayList<>();
        mediaTypeList.add(MediaType.APPLICATION_JSON_UTF8);
        jackson2converter.setSupportedMediaTypes(mediaTypeList);
        converters.add(jackson2converter);
    }
    
    /**
     * @param resolvers
     */
    @Override
    public void addArgumentResolvers(
            List<HandlerMethodArgumentResolver> resolvers) {
        WebMvcConfigurer.super.addArgumentResolvers(resolvers);
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
        registry.addResourceHandler(
                this.localPathReplaceUtils.transform("/favicon.ico"))
                .addResourceLocations("classpath:/static/favicon.ico")
                .setCachePeriod(86400);
        registry.addResourceHandler(
                this.localPathReplaceUtils.transform("/robots.txt"))
                .addResourceLocations("classpath:/static/robots.txt")
                .setCachePeriod(86400);
        
        //WebMvcAutoConfiguration中覆写了webjars的的读取方法需要覆写回来
        //FIXME:临时将缓存时间调整为0
        Integer cachePeriod = 0;//86400;
        registry.addResourceHandler(
                this.localPathReplaceUtils.transform("/webjars/**"))
                .addResourceLocations("classpath:/static/webjars/")
                .addResourceLocations("classpath:/META-INF/resources/webjars/")
                .setCachePeriod(cachePeriod);
        
        //其他
        registry.addResourceHandler(
                this.localPathReplaceUtils.transform("/css/**"))
                .addResourceLocations("classpath:/static/css/")
                .setCachePeriod(cachePeriod);
        registry.addResourceHandler(
                this.localPathReplaceUtils.transform("/images/**"))
                .addResourceLocations("classpath:/static/css/")
                .setCachePeriod(cachePeriod);
        registry.addResourceHandler(
                this.localPathReplaceUtils.transform("/js/**"))
                .addResourceLocations("classpath:/static/css/")
                .setCachePeriod(cachePeriod);
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
