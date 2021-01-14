/*
 * 描          述:  <描述>
 * 修  改   人:  PengQingyang
 * 修改时间:  2020年12月27日
 * <修改描述:>
 */
package com.tx.local.springmvc.path;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * contextPath可替换配置<br/>
 * <功能详细描述>
 * 
 * @author  PengQingyang
 * @version  [版本号, 2020年12月27日]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
@Configuration
@EnableConfigurationProperties(LocalPathConfigProperties.class)
public class LocalPathReplaceConfigAutoConfiguration {
    
    /** 本地路径配置属性 */
    private LocalPathConfigProperties properties;
    
    /** <默认构造函数> */
    public LocalPathReplaceConfigAutoConfiguration(
            LocalPathConfigProperties properties) {
        super();
        this.properties = properties;
    }
    
    /**
     * 本地路径替换工具<br/>
     * <功能详细描述>
     * @return [参数说明]
     * 
     * @return LocalPathReplaceUtils [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @Bean(name = "localPathReplaceUtils")
    public LocalPathReplaceUtils localPathReplaceUtils() {
        LocalPathReplaceUtils pathUtils = new LocalPathReplaceUtils(
                this.properties);
        return pathUtils;
    }
}
