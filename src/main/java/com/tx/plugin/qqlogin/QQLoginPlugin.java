///*
// * 描          述:  <描述>
// * 修  改   人:  Administrator
// * 修改时间:  2019年12月11日
// * <修改描述:>
// */
//package com.tx.plugin.qqlogin;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
//import org.springframework.stereotype.Component;
//
//import com.tx.component.plugin.loginplugin.LoginPlugin;
//
///**
// * <功能简述>
// * <功能详细描述>
// * 
// * @author  Administrator
// * @version  [版本号, 2019年12月11日]
// * @see  [相关类/方法]
// * @since  [产品/模块版本]
// */
//@Component("plugin.login.qqLoginPlugin")
//public class QQLoginPlugin extends LoginPlugin<QQLoginPluginConfig> {
//    
//    /**
//     * @return
//     */
//    @Override
//    public String getName() {
//        return "QQ登陆插件";
//    }
//    
//    /**
//     * @return
//     */
//    @Override
//    public String getVersion() {
//        return "1.0";
//    }
//    
//    
//    
//    /**
//     * @return
//     */
//    @Override
//    public String getPrefix() {
//        return "plugin.login.qq";
//    }
//    
//    /**
//     * @param request
//     * @return
//     */
//    @Override
//    public String getSocialUsername(HttpServletRequest request) {
//        // TODO Auto-generated method stub
//        return super.getSocialUsername(request);
//    }
//    
//    /**
//     * @param request
//     * @return
//     */
//    @Override
//    public String getHeadImgUrl(HttpServletRequest request) {
//        // TODO Auto-generated method stub
//        return super.getHeadImgUrl(request);
//    }
//    
//    /**
//     * @param request
//     * @return
//     */
//    @Override
//    public boolean supports(HttpServletRequest request) {
//        // TODO Auto-generated method stub
//        return super.supports(request);
//    }
//    
//    /**
//     * @param request
//     * @return
//     */
//    @Override
//    public String getUniqueId(HttpServletRequest request) {
//        // TODO Auto-generated method stub
//        return null;
//    }
//    
//    /**
//     * @param uniqueId
//     * @param accessToken
//     * @param request
//     * @param response
//     */
//    @Override
//    public void getUserInfo(String uniqueId, String accessToken,
//            HttpServletRequest request, HttpServletResponse response) {
//        // TODO Auto-generated method stub
//        
//    }
//    
//    /**
//     * @param request
//     * @param response
//     * @return
//     * @throws Exception
//     */
//    @Override
//    public boolean isSignInSuccess(HttpServletRequest request,
//            HttpServletResponse response) throws Exception {
//        // TODO Auto-generated method stub
//        return false;
//    }
//    
//}
