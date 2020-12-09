/*

S * 描          述:  <描述>
 * 修  改   人:  Administrator
 * 修改时间:  2018年4月25日
 * <修改描述:>
 */
package com.tx.local.boot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.CacheManager;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;
import org.springframework.cache.transaction.TransactionAwareCacheManagerProxy;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * 客户信息模块启动器<br/>
 * <功能详细描述>
 * 
 * @author  Administrator
 * @version  [版本号, 2018年4月25日]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
//@EnableEurekaClient
//@EntityScan(basePackages = { "com.tx.local" })
//@EnableJpaRepositories(basePackages = { "com.tx.local" })
@EnableAspectJAutoProxy()
@EnableFeignClients(basePackages = { "com.tx.local" })
@SpringBootApplication(scanBasePackages = { "com.tx.security", "com.tx.local",
        "com.tx.plugin", "com.tx.component.dynamictable",
        "com.tx.component.lock", "com.tx.client" })
@EnableSwagger2
public class WebdemoBootApplication {
    
    /**
     * 项目启动<br/>
     * <功能详细描述>
     * @param args [参数说明]
     * 
     * @return void [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    public static void main(String[] args) {
        SpringApplication.run(WebdemoBootApplication.class, args);
    }
    
    /**
     * 创建openAPI的Docket实例<br/>
     * <功能详细描述>
     * @return [参数说明]
     * 
     * @return Docket [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @Bean
    public Docket createOpenAPIDocket() {
        return new Docket(DocumentationType.SWAGGER_2).apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.tx"))
                .apis(RequestHandlerSelectors.withClassAnnotation(Api.class))
                .apis(RequestHandlerSelectors
                        .withMethodAnnotation(ApiOperation.class))
                .paths(PathSelectors.any())
                .build();
    }
    
    /**
     * 展示的API信息<br/>
     * <功能详细描述>
     * @return [参数说明]
     * 
     * @return ApiInfo [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder().title("WEBDEMO接口文档(WEBDEMO-API)")
                .description("API文档")
                .termsOfServiceUrl("http://webdemo.cqtianxin.com/")
                .contact(new Contact("xxxxxx", "xxxxxx@xx.xxx", ""))
                .version("9.0")
                .build();
    }
    
    /**
     * 用内存缓存代替redis
     * <功能详细描述>
     * @return [参数说明]
     * 
     * @return CacheManager [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @Bean("cacheManager")
    public CacheManager cacheManager() {
        CacheManager cm = new ConcurrentMapCacheManager();
        CacheManager res = new TransactionAwareCacheManagerProxy(cm);
        return res;
    }
}
