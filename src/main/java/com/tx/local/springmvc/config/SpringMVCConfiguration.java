/*
 * 描          述:  <描述>
 * 修  改   人:  zhangwei
 * 修改时间:  2018年7月20日
 * <修改描述:>
 */
package com.tx.local.springmvc.config;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.boot.autoconfigure.thymeleaf.ThymeleafProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.format.FormatterRegistry;
import org.springframework.http.MediaType;
import org.springframework.http.converter.BufferedImageHttpMessageConverter;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.validation.Validator;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.thymeleaf.IEngineConfiguration;
import org.thymeleaf.spring5.templateresolver.SpringResourceTemplateResolver;

import com.tx.core.springmvc.support.StringToDateConverter;

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
        implements WebMvcConfigurer, InitializingBean, ApplicationContextAware {
    
    /** 本地化验证器 */
    private LocalValidatorFactoryBean validator;
    
    /** spring容器句柄 */
    private ApplicationContext applicationContext;
    
    /** 获取thymeleaf属性 */
    private final ThymeleafProperties properties;
    
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
     * 客户端包支撑<br/>
     * <功能详细描述>
     * @return [参数说明]
     * 
     * @return SpringResourceTemplateResolver [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @Bean("client.springResourceTemplateResolver")
    public SpringResourceTemplateResolver clientSpringResourceTemplateResolver() {
        SpringResourceTemplateResolver resolver = new SpringResourceTemplateResolver(){
            /**
             * @param configuration
             * @param ownerTemplate
             * @param template
             * @param templateResolutionAttributes
             * @return
             */
            @Override
            protected boolean computeResolvable(
                    IEngineConfiguration configuration, String ownerTemplate,
                    String template,
                    Map<String, Object> templateResolutionAttributes) {
                RequestAttributes requestAttributes = RequestContextHolder
                        .getRequestAttributes();
                if (requestAttributes == null || !ServletRequestAttributes.class.isInstance(requestAttributes)) {
                    //如果为空，或不是ServletRequestAttributes的实例，都不进行适配
                    return false;
                }
                HttpServletRequest request = ((ServletRequestAttributes) requestAttributes).getRequest();
                if (request == null || !request.getServletPath().startsWith("/client/")) {
                    //如果为空，或servletPath不是以"/client/"开始的不进行匹配
                    return false;
                }
                return super.computeResolvable(configuration,
                        ownerTemplate,
                        template,
                        templateResolutionAttributes);
            }
        };
        resolver.setApplicationContext(this.applicationContext);
        resolver.setPrefix("classpath:/templates4client/");
        resolver.setSuffix(this.properties.getSuffix());
        resolver.setTemplateMode(this.properties.getMode());
        if (this.properties.getEncoding() != null) {
            resolver.setCharacterEncoding(this.properties.getEncoding().name());
        }
        resolver.setCacheable(this.properties.isCache());
        resolver.setOrder(Ordered.HIGHEST_PRECEDENCE);//直接设置为最高优先级
        resolver.setCheckExistence(this.properties.isCheckTemplate());
        return resolver;
    }
    
    /**
     * 客户端包支撑<br/>
     * <功能详细描述>
     * @return [参数说明]
     * 
     * @return SpringResourceTemplateResolver [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @Bean("wapclient.springResourceTemplateResolver")
    public SpringResourceTemplateResolver wapclientSpringResourceTemplateResolver() {
        SpringResourceTemplateResolver resolver = new SpringResourceTemplateResolver(){
            /**
             * @param configuration
             * @param ownerTemplate
             * @param template
             * @param templateResolutionAttributes
             * @return
             */
            @Override
            protected boolean computeResolvable(
                    IEngineConfiguration configuration, String ownerTemplate,
                    String template,
                    Map<String, Object> templateResolutionAttributes) {
                RequestAttributes requestAttributes = RequestContextHolder
                        .getRequestAttributes();
                if (requestAttributes == null || !ServletRequestAttributes.class.isInstance(requestAttributes)) {
                    //如果为空，或不是ServletRequestAttributes的实例，都不进行适配
                    return false;
                }
                HttpServletRequest request = ((ServletRequestAttributes) requestAttributes).getRequest();
                if (request == null || !request.getServletPath().startsWith("/wap/client/")) {
                    //如果为空，或servletPath不是以"/client/"开始的不进行匹配
                    return false;
                }
                return super.computeResolvable(configuration,
                        ownerTemplate,
                        template,
                        templateResolutionAttributes);
            }
        };
        resolver.setApplicationContext(this.applicationContext);
        resolver.setPrefix("classpath:/templates4wapclient/");
        resolver.setSuffix(this.properties.getSuffix());
        resolver.setTemplateMode(this.properties.getMode());
        if (this.properties.getEncoding() != null) {
            resolver.setCharacterEncoding(this.properties.getEncoding().name());
        }
        resolver.setCacheable(this.properties.isCache());
        resolver.setOrder(Ordered.HIGHEST_PRECEDENCE);//直接设置为最高优先级
        resolver.setCheckExistence(this.properties.isCheckTemplate());
        return resolver;
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
