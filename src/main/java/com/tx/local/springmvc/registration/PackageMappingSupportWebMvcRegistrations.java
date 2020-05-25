///*
// * 描          述:  <描述>
// * 修  改   人:  Administrator
// * 修改时间:  2020年4月6日
// * <修改描述:>
// */
//package com.tx.local.springmvc.registration;
//
//import java.lang.reflect.Method;
//
//import org.springframework.beans.factory.InitializingBean;
//import org.springframework.boot.autoconfigure.web.servlet.WebMvcRegistrations;
//import org.springframework.util.StringUtils;
//import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
//import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;
//
///**
// * 自定义WebMVCRegistrations
// *    支持不同的包有不同的前缀实现<br/>
// * <功能详细描述>
// * 
// * @author  Administrator
// * @version  [版本号, 2020年4月6日]
// * @see  [相关类/方法]
// * @since  [产品/模块版本]
// */
//public class PackageMappingSupportWebMvcRegistrations
//        implements WebMvcRegistrations, InitializingBean {
//    
//    //${tx.url.prefix.client}
//    //@Value("${tx.url.prefix.client}")
//    private String clientUrlPrefix = "client";
//    
//    /**
//     * @throws Exception
//     */
//    @Override
//    public void afterPropertiesSet() throws Exception {
//    }
//    
//    /**
//     * @return
//     */
//    @Override
//    public RequestMappingHandlerMapping getRequestMappingHandlerMapping() {
//        RequestMappingHandlerMapping mapping = new RequestMappingHandlerMapping() {
//            
//            /**
//             * @param method
//             * @param handlerType
//             * @return
//             */
//            @Override
//            protected RequestMappingInfo getMappingForMethod(Method method,
//                    Class<?> handlerType) {
//                RequestMappingInfo info = super.getMappingForMethod(method,
//                        handlerType);
//                
//                if (StringUtils.endsWithIgnoreCase(handlerType.getName(),
//                        "APIController")) {
//                    //如果为APIController直接返回，不进行处理
//                    return info;
//                }
//                if (StringUtils.startsWithIgnoreCase(
//                        handlerType.getPackage().getName(), "com.tx.client")
//                        && !StringUtils.isEmpty(clientUrlPrefix)) {
//                    //如果为client时,并且包名不为APIController,在对应的url请求前追加前缀
//                    RequestMappingInfo packageInfo = RequestMappingInfo
//                            .paths(clientUrlPrefix).build();
//                    info = packageInfo.combine(info);
//                    return info;
//                } else {
//                    //如果不匹配时使用默认
//                    return info;
//                }
//            }
//        };
//        return mapping;
//    }
//}
