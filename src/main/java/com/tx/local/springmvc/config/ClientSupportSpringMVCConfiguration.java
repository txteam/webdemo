///*
// * 描          述:  <描述>
// * 修  改   人:  zhangwei
// * 修改时间:  2018年7月20日
// * <修改描述:>
// */
//package com.tx.local.springmvc.config;
//
//import java.util.Collection;
//import java.util.Collections;
//import java.util.LinkedHashMap;
//import java.util.Locale;
//import java.util.Map;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
//import org.apache.commons.lang3.StringUtils;
//import org.springframework.beans.BeansException;
//import org.springframework.beans.factory.InitializingBean;
//import org.springframework.beans.factory.ObjectProvider;
//import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
//import org.springframework.boot.autoconfigure.thymeleaf.ThymeleafProperties;
//import org.springframework.context.ApplicationContext;
//import org.springframework.context.ApplicationContextAware;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.core.Ordered;
//import org.springframework.util.MimeType;
//import org.springframework.web.context.request.RequestAttributes;
//import org.springframework.web.context.request.RequestContextHolder;
//import org.springframework.web.servlet.View;
//import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
//import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
//import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
//import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
//import org.thymeleaf.context.IExpressionContext;
//import org.thymeleaf.dialect.IDialect;
//import org.thymeleaf.linkbuilder.StandardLinkBuilder;
//import org.thymeleaf.spring5.SpringTemplateEngine;
//import org.thymeleaf.spring5.templateresolver.SpringResourceTemplateResolver;
//import org.thymeleaf.spring5.view.ThymeleafViewResolver;
//
///**
// * 支持client访问的web配置器<br/>
// * <功能详细描述>
// * 
// * @author  zhangwei
// * @version  [版本号, 2018年7月20日]
// * @see  [相关类/方法]
// * @since  [产品/模块版本]
// */
//@Configuration
//public class ClientSupportSpringMVCConfiguration
//        implements WebMvcConfigurer, InitializingBean, ApplicationContextAware {
//    
//    public static final String VIEW_CONTEXT_ATTR = "view.context.attribute";
//    
//    public static final String VIEW_CONTEXT_CLIENT = "client";
//    
//    /** spring容器句柄 */
//    private ApplicationContext applicationContext;
//    
//    /** 获取thymeleaf属性 */
//    private final ThymeleafProperties properties;
//    
//    /** 获取注册的IDialect实现 */
//    private final Collection<IDialect> dialects;
//    
//    /** <默认构造函数> */
//    public ClientSupportSpringMVCConfiguration(ThymeleafProperties properties,
//            ObjectProvider<Collection<IDialect>> dialectsProvider) {
//        this.properties = properties;
//        this.dialects = dialectsProvider.getIfAvailable(Collections::emptyList);
//    }
//    
//    /**
//     * @param applicationContext
//     * @throws BeansException
//     */
//    @Override
//    public void setApplicationContext(ApplicationContext applicationContext)
//            throws BeansException {
//        this.applicationContext = applicationContext;
//    }
//    
//    /**
//     * @throws Exception
//     */
//    @Override
//    public void afterPropertiesSet() throws Exception {
//    }
//    
//    /**
//     * 资源访问处理器<br/>
//     * @param registry
//     */
//    @Override
//    public void addResourceHandlers(ResourceHandlerRegistry registry) {
//        //FIXME:临时将缓存时间调整为0
//        Integer cachePeriod = 0;//86400;
//        //增加client的资源解析
//        registry.addResourceHandler("/client/webjars/**")
//                .addResourceLocations("classpath:/static/webjars/")
//                .addResourceLocations("classpath:/META-INF/resources/webjars/")
//                .setCachePeriod(cachePeriod);
//        registry.addResourceHandler("/client/**")
//                .addResourceLocations("classpath:/static/")
//                .setCachePeriod(cachePeriod);
//        
//    }
//    
//    /**
//     * @param registry
//     */
//    @Override
//    public void addInterceptors(InterceptorRegistry registry) {
//        registry.addInterceptor(new HandlerInterceptorAdapter() {
//            
//            /**
//             * @param request
//             * @param response
//             * @param handler
//             * @return
//             * @throws Exception
//             */
//            @Override
//            public boolean preHandle(HttpServletRequest request,
//                    HttpServletResponse response, Object handler)
//                    throws Exception {
//                String servletPath = request.getServletPath();
//                if (servletPath.startsWith("/client/")) {
//                    RequestContextHolder.getRequestAttributes().setAttribute(
//                            VIEW_CONTEXT_ATTR,
//                            VIEW_CONTEXT_CLIENT,
//                            RequestAttributes.SCOPE_REQUEST);
//                }
//                return super.preHandle(request, response, handler);
//            }
//        });
//    }
//    
//    /**
//     * 原默认实现中viewResolver是使用的命名判断是否存在<br/>
//     * <功能详细描述>
//     * @return [参数说明]
//     * 
//     * @return ThymeleafViewResolver [返回类型说明]
//     * @exception throws [异常类型] [异常说明]
//     * @see [类、类#方法、类#成员]
//     */
//    @Bean
//    @ConditionalOnMissingBean(name = "client.thymeleafViewResolver")
//    public ThymeleafViewResolver clientThymeleafViewResolver() {
//        ThymeleafViewResolver resolver = new ThymeleafViewResolver() {
//            
//            /**
//             * @param viewName
//             * @param locale
//             * @return
//             * @throws Exception
//             */
//            @Override
//            protected View createView(String viewName, Locale locale)
//                    throws Exception {
//                RequestAttributes requestAttributes = RequestContextHolder
//                        .getRequestAttributes();
//                String attrValue = (String)requestAttributes.getAttribute(VIEW_CONTEXT_ATTR,
//                        RequestAttributes.SCOPE_REQUEST);
//                if(!StringUtils.equals(VIEW_CONTEXT_CLIENT, attrValue)){
//                    return null;
//                }
//                return super.createView(viewName, locale);
//            }
//        };
//        resolver.setTemplateEngine(templateEngine());
//        resolver.setCharacterEncoding(this.properties.getEncoding().name());
//        resolver.setContentType(
//                appendCharset(this.properties.getServlet().getContentType(),
//                        resolver.getCharacterEncoding()));
//        //resolver.setExcludedViewNames(this.properties.getExcludedViewNames());
//        //resolver.setViewNames(this.properties.getViewNames());
//        // This resolver acts as a fallback resolver (e.g. like a
//        // InternalResourceViewResolver) so it needs to have low precedence
//        resolver.setOrder(Ordered.LOWEST_PRECEDENCE - 10);
//        resolver.setCache(this.properties.isCache());
//        return resolver;
//    }
//    
//    /**
//     * 不能注册为@Bean
//     * 否则会覆盖掉，ThymeleafAutoConfiguration中的默认的SpringTemplateEngine<br/>
//     * <功能详细描述>
//     * @return [参数说明]
//     * 
//     * @return SpringTemplateEngine [返回类型说明]
//     * @exception throws [异常类型] [异常说明]
//     * @see [类、类#方法、类#成员]
//     */
//    private SpringTemplateEngine templateEngine() {
//        SpringTemplateEngine engine = new SpringTemplateEngine();
//        engine.setEnableSpringELCompiler(
//                this.properties.isEnableSpringElCompiler());
//        //仅使用独立的客户资源解析器，之所以不复用是因为其中LinkBuilder实现存在差异
//        engine.addTemplateResolver(clientSpringResourceTemplateResolver());
//        this.dialects.forEach(engine::addDialect);
//        engine.setLinkBuilder(new StandardLinkBuilder() {
//            /**
//             * @param context
//             * @param base
//             * @param parameters
//             * @return
//             */
//            @Override
//            protected String computeContextPath(IExpressionContext context,
//                    String base, Map<String, Object> parameters) {
//                String contextPath = super.computeContextPath(context,
//                        base,
//                        parameters);
//                contextPath = contextPath + "/client";
//                return contextPath;
//            }
//        });
//        return engine;
//    }
//    
//    /**
//     * 客户端包支撑<br/>
//     * <功能详细描述>
//     * @return [参数说明]
//     * 
//     * @return SpringResourceTemplateResolver [返回类型说明]
//     * @exception throws [异常类型] [异常说明]
//     * @see [类、类#方法、类#成员]
//     */
//    private SpringResourceTemplateResolver clientSpringResourceTemplateResolver() {
//        SpringResourceTemplateResolver resolver = new SpringResourceTemplateResolver();
//        resolver.setApplicationContext(this.applicationContext);
//        resolver.setPrefix("classpath:/templates4client/");
//        resolver.setSuffix(this.properties.getSuffix());
//        resolver.setTemplateMode(this.properties.getMode());
//        if (this.properties.getEncoding() != null) {
//            resolver.setCharacterEncoding(this.properties.getEncoding().name());
//        }
//        resolver.setCacheable(this.properties.isCache());
//        resolver.setOrder(Ordered.HIGHEST_PRECEDENCE);//直接设置为最高优先级
//        resolver.setCheckExistence(this.properties.isCheckTemplate());
//        return resolver;
//    }
//    
//    /**
//     * appendCharset<br/>
//     * <功能详细描述>
//     * @param type
//     * @param charset
//     * @return [参数说明]
//     * 
//     * @return String [返回类型说明]
//     * @exception throws [异常类型] [异常说明]
//     * @see [类、类#方法、类#成员]
//     */
//    private String appendCharset(MimeType type, String charset) {
//        if (type.getCharset() != null) {
//            return type.toString();
//        }
//        LinkedHashMap<String, String> parameters = new LinkedHashMap<>();
//        parameters.put("charset", charset);
//        parameters.putAll(type.getParameters());
//        return new MimeType(type, parameters).toString();
//    }
//    
//}
