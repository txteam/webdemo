/*
 * 描          述:  <描述>
 * 修  改   人:  Administrator
 * 修改时间:  2019年5月6日
 * <修改描述:>
 */
package com.tx.local.boot;

import java.io.IOException;

import org.apache.commons.lang3.ArrayUtils;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;

import com.thoughtworks.xstream.XStream;
import com.tx.component.configuration.config.ConfigPropertyParser;
import com.tx.core.util.XstreamUtils;

/**
 * <功能简述>
 * <功能详细描述>
 * 
 * @author  Administrator
 * @version  [版本号, 2019年5月6日]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public class Test {
    
    /** resourceResolver */
    private static final ResourcePatternResolver resourceResolver = new PathMatchingResourcePatternResolver();
    
    //配置解析句柄
    private static XStream configXstream = XstreamUtils
            .getXstream(ConfigPropertyParser.class);
    
    /** 配置文件所在路径 */
    private static String configLocation = "classpath:config/*.xml";
    
    public static void main(String[] args) throws IOException {
        org.springframework.core.io.Resource[] configResources = resourceResolver
                .getResources(configLocation);
        
        if (ArrayUtils.isEmpty(configResources)) {
            return;
        }
        
        for (org.springframework.core.io.Resource configResource : configResources) {
            if (!configResource.exists()) {
                continue;
            }
            
            //解析配置文件
            ConfigPropertyParser parser = (ConfigPropertyParser) configXstream
                    .fromXML(configResource.getInputStream());
            //初始化配置属性
            
            System.out.println(parser.getConfigs().size());
        }
    }
}
