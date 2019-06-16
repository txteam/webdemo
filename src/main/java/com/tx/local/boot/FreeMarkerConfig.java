/*
 * 描          述:  <描述>
 * 修  改   人:  Administrator
 * 修改时间:  2018年12月17日
 * <修改描述:>
 */
package com.tx.local.boot;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.ServletContextAware;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;

import javax.servlet.ServletContext;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * 门户程序启动器<br/>
 * <功能详细描述>
 * 
 * @author  Administrator
 * @version  [版本号, 2018年12月17日]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
@Configuration
public class FreeMarkerConfig implements ServletContextAware {
    
    /** servlet容器 */
    private ServletContext servletContext;
    
    /**
     * @param servletContext
     */
    @Override
    public void setServletContext(ServletContext servletContext) {
        this.servletContext = servletContext;
    }
    
    @Bean
    public FreeMarkerConfigurer freeMarkerConfigurer() {
        FreeMarkerConfigurer freeMarkerConfigurer = new FreeMarkerConfigurer();
        
        freeMarkerConfigurer.setTemplateLoaderPaths("classpath:/",
                "classpath:/templates/");
        
        Map<String, Object> variables = new HashMap<>();
        variables.put("base", servletContext.getContextPath());
        variables.put("contextPath", servletContext.getContextPath());
        variables.put("showPowered", true);
        freeMarkerConfigurer.setFreemarkerVariables(variables);
        
        Properties prop = new Properties();
        prop.put("default_encoding", "UTF-8");
        prop.put("url_escaping_charset", "UTF-8");
        prop.put("output_format", "HTMLOutputFormat");
        prop.put("template_update_delay", "0");
        prop.put("tag_syntax", "auto_detect"); // [# 和 <# 都可以使用,二选一；[# 优先级高于 <#
        prop.put("classic_compatible", "true");
        prop.put("number_format", "0.######");
        prop.put("boolean_format", "true,false");
        prop.put("datetime_format", "yyyy-MM-dd");
        prop.put("date_format", "yyyy-MM-dd");
        prop.put("time_format", "HH:mm:ss");
        prop.put("object_wrapper", "freemarker.ext.beans.BeansWrapper");
        freeMarkerConfigurer.setFreemarkerSettings(prop);
        
        //加载tld
        //        List<String> tlds = new ArrayList<String>();
        //        tlds.add("/static/tags/security.tld");
        //        freeMarkerConfigurer.getTaglibFactory().setClasspathTlds(tlds);
        
        return freeMarkerConfigurer;
    }
    
    //    @Bean
    //    public PropertiesFactoryBean configProperties() throws Exception{
    //        PropertiesFactoryBean propertiesFactoryBean = new PropertiesFactoryBean();
    //        PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
    //        propertiesFactoryBean.setLocations(resolver.getResources("classpath*:application.properties"));
    //        return propertiesFactoryBean;
    //    }
    
    //    @Bean
    //    @ConditionalOnMissingBean(ClassPathTldsLoader.class)
    //    public ClassPathTldsLoader classPathTldsLoader() {
    //        return new ClassPathTldsLoader();
    //    }
}
