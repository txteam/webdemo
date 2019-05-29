///*
// * 描          述:  <描述>
// * 修  改   人:  zhangwei
// * 修改时间:  2018年12月20日
// * <修改描述:>
// */
//package com.tx.common.config;
//
//import java.util.Arrays;
//import java.util.List;
//
//import javax.annotation.PostConstruct;
//
//import org.apache.commons.lang3.ArrayUtils;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;
//
///**
// * <功能简述>
// * <功能详细描述>
// *
// * @author  zhangwei
// * @version  [版本号, 2018年12月20日]
// * @see  [相关类/方法]
// * @since  [产品/模块版本]
// */
//public class ClassPathTldsLoader {
//    /**
//     * 指定路径
//     */
//    private static final String SECURITY_TLD = "/static/tags/security.tld";
//
//    final private List<String> classPathTlds;
//
//    public ClassPathTldsLoader(String... classPathTlds) {
//        super();
//        if (ArrayUtils.isEmpty(classPathTlds)) {
//            this.classPathTlds = Arrays.asList(SECURITY_TLD);
//        } else {
//            this.classPathTlds = Arrays.asList(classPathTlds);
//        }
//    }
//
//    @Autowired
//    private FreeMarkerConfigurer freeMarkerConfigurer;
//
//    @PostConstruct
//    public void loadClassPathTlds() {
//        freeMarkerConfigurer.getTaglibFactory().setClasspathTlds(classPathTlds);
//    }
//}
