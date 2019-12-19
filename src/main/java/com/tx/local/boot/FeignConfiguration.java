/*
 * 描          述:  <描述>
 * 修  改   人:  Administrator
 * 修改时间:  2019年6月12日
 * <修改描述:>
 */
package com.tx.local.boot;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import feign.Request;
import feign.Retryer;

/**
 * <功能简述>
 * <功能详细描述>
 * 
 * @author  Administrator
 * @version  [版本号, 2019年6月12日]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
@Configuration
public class FeignConfiguration {
    
    public static int connectTimeOutMillis = 12000;//超时时间
    
    public static int readTimeOutMillis = 12000;
    
    @Bean
    public Request.Options options() {
        return new Request.Options(connectTimeOutMillis, readTimeOutMillis);
    }
    
    @Bean
    public Retryer feignRetryer() {
        //Retryer retryer = new Retryer.Default(100, 1000, 4);
        return new Retryer.Default();
    }
    
    //    public PoolingHttpClientConnectionManager poolingHttpClientConnectionManager() {
    //        PoolingHttpClientConnectionManager connectionManager = new PoolingHttpClientConnectionManager();
    //        connectionManager.setMaxTotal(200);
    //        connectionManager.setDefaultMaxPerRoute(100);
    //        return connectionManager;
    //    }
    //    @Bean
    //    public Contract feignContract() {
    //        //这将SpringMvc Contract 替换为feign.Contract.Default
    //        return new feign.Contract.Default();
    //    }
}
