///*
// * 描          述:  <描述>
// * 修  改   人:  Administrator
// * 修改时间:  2019年12月11日
// * <修改描述:>
// */
//package com.tx.plugin.login.qq;
//
//import java.util.HashMap;
//import java.util.Map;
//import java.util.TreeMap;
//import java.util.UUID;
//import java.util.regex.Matcher;
//import java.util.regex.Pattern;
//
//import javax.servlet.http.HttpServletRequest;
//
//import org.apache.commons.codec.digest.DigestUtils;
//import org.apache.commons.lang.StringUtils;
//import org.apache.commons.lang3.RandomStringUtils;
//import org.springframework.stereotype.Component;
//import org.springframework.web.servlet.ModelAndView;
//
//import com.tx.core.exceptions.util.AssertUtils;
//import com.tx.core.util.HttpClientUtils;
//import com.tx.core.util.WebUtils;
//import com.tx.local.security.util.WebContextUtils;
//import com.tx.plugin.login.LoginPlugin;
//import com.tx.plugin.login.exception.AuthorizeException;
//import com.tx.plugin.login.model.LoginAccessToken;
//import com.tx.plugin.login.model.LoginUserInfo;
//
///**
// * QQ登陆插件<br/>
// * https://wiki.open.qq.com/wiki/%E3%80%90QQ%E7%99%BB%E5%BD%95%E3%80%91OAuth2.0%E5%BC%80%E5%8F%91%E6%96%87%E6%A1%A3
// * 腾讯开放平台  http://op.open.qq.com/index.php
// * 
// * <功能详细描述>
// * 
// * @author  Administrator
// * @version  [版本号, 2019年12月11日]
// * @see  [相关类/方法]
// * @since  [产品/模块版本]
// */
//@Component("qqLoginPlugin")
//public class QQLoginPlugin extends LoginPlugin<QQLoginPluginConfig> {
//    
//    /** code请求URL */
//    private static final String CODE_REQUEST_URL = "https://graph.qq.com/oauth2.0/authorize";
//    
//    /** accessToken请求URL */
//    private static final String ACCESS_TOKEN_REQUEST_URL = "https://graph.qq.com/oauth2.0/token";
//    
//    /** openId请求URL */
//    private static final String OPEN_ID_REQUEST_URL = "https://graph.qq.com/oauth2.0/me";
//    
//    /** "openId"提取正则表达式 */
//    private static final Pattern OPEN_ID_PATTERN = Pattern
//            .compile("\"openid\"\\s*:\\s*\"(\\S*?)\"");
//    
//    /** "状态"属性名称 */
//    private static final String STATE_ATTRIBUTE_NAME = QQLoginPlugin.class
//            .getName() + ".STATE";
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
//    /**
//     * @return
//     */
//    @Override
//    public String getPrefix() {
//        return "plugin.login.qq";
//    }
//    
//    /**
//     * @param viewName
//     * @param redirectUrl
//     * @param request
//     * @return
//     */
//    @Override
//    public ModelAndView getCodeHandle(String viewName, String redirectUri,
//            String state, String scope, HttpServletRequest request) {
//        AssertUtils.notEmpty(viewName, "viewName is empty.");
//        AssertUtils.notEmpty(redirectUri, "redirectUri is empty.");
//        
//        ModelAndView mview = new ModelAndView(viewName);
//        mview.addObject("requestUrl", redirectUri);
//        Map<String, String> parameterMap = buildRequestCodeParams(redirectUri);
//        mview.addObject("parameterMap", parameterMap);
//        
//        //写入请求状态码<br/>
//        request.getSession().setAttribute(STATE_ATTRIBUTE_NAME, state);
//        
//        return mview;
//    }
//    
//    /**
//     * @param code
//     * @param request
//     * @return
//     */
//    @Override
//    public LoginUserInfo getUserInfo(String code, String state,
//            HttpServletRequest request) throws AuthorizeException {
//        throw new AuthorizeException("暂无实现.");
//    }
//    
//    /**
//     * @param code
//     * @param request
//     * @return
//     */
//    @Override
//    public String getUniqueId(String code, String state,
//            HttpServletRequest request) throws AuthorizeException {
//        // TODO Auto-generated method stub
//        return null;
//    }
//    
//    /**
//     * 获取跳转到code获取的链接url
//     * <功能详细描述>
//     * @return [参数说明]
//     * 
//     * @return String [返回类型说明]
//     * @exception throws [异常类型] [异常说明]
//     * @see [类、类#方法、类#成员]
//     */
//    public String getRquestCodeUrl() {
//        String requestUrl = CODE_REQUEST_URL;
//        return requestUrl;
//    }
//    
//    /**
//     * 构建请求code的参数<br/>
//     * <功能详细描述>
//     * @return [参数说明]
//     * 
//     * @return Map<String,String> [返回类型说明]
//     * @exception throws [异常类型] [异常说明]
//     * @see [类、类#方法、类#成员]
//     */
//    public Map<String, String> buildRequestCodeParams(String redirectUrl) {
//        AssertUtils.notEmpty(redirectUrl, "redirectUrl is empty.");
//        QQLoginPluginConfig config = getConfig();
//        
//        //生成state，并写入当前session中
//        String state = DigestUtils.md5Hex(
//                UUID.randomUUID() + RandomStringUtils.randomAlphabetic(30));
//        WebContextUtils.getSession().setAttribute(STATE_ATTRIBUTE_NAME, state);
//        
//        Map<String, String> parameterMap = new TreeMap<>();
//        //应用唯一标识，在微信开放平台提交应用审核通过后获得
//        parameterMap.put("client_id", config.getClientId());
//        //redirect_uri  是   重定向地址，需要进行UrlEncode
//        parameterMap.put("redirect_uri", redirectUrl);
//        //response_type 是   填code
//        parameterMap.put("response_type", "code");
//        //scope 是   应用授权作用域，拥有多个作用域用逗号（,）分隔，网页应用目前仅填写snsapi_login即可
//        //parameterMap.put("scope", );
//        parameterMap.put("state", state);
//        return parameterMap;
//    }
//    
//    /**
//     * 获取AccessToken《》
//     * <功能详细描述>
//     * @param request
//     * @param response
//     * @return
//     * @throws Exception [参数说明]
//     * 
//     * @return boolean [返回类型说明]
//     * @exception throws [异常类型] [异常说明]
//     * @see [类、类#方法、类#成员]
//     */
//    public LoginAccessToken getAccessToken(String code, String redirectUri)
//            throws AuthorizeException {
//        AssertUtils.notEmpty(code, "code is empty.");
//        
//        //获取配置
//        QQLoginPluginConfig config = getConfig();
//        
//        Map<String, String> parameterMap = new HashMap<>();
//        parameterMap.put("grant_type", "authorization_code");
//        parameterMap.put("client_id", config.getClientId());
//        parameterMap.put("client_secret", config.getClientSecret());
//        
//        parameterMap.put("redirect_uri", redirectUri);
//        parameterMap.put("code", code);
//        String content = HttpClientUtils.get(ACCESS_TOKEN_REQUEST_URL,
//                parameterMap);
//        
//        LoginAccessToken token = new LoginAccessToken();
//        String accessToken = WebUtils.parse(content).get("access_token");
//        if (StringUtils.isNotEmpty(accessToken)) {
//            token.setAccessToken(accessToken);
//        } else {
//            throw new AuthorizeException("access token is empty.");
//        }
//        return token;
//    }
//    
//    /**
//     * 获取唯一键id<br/>
//     * <功能详细描述>
//     * @param accessToken
//     * @return [参数说明]
//     * 
//     * @return String [返回类型说明]
//     * @exception throws [异常类型] [异常说明]
//     * @see [类、类#方法、类#成员]
//     */
//    public String getUniqueId(String accessToken) {
//        Map<String, String> parameterMap = new HashMap<>();
//        parameterMap.put("access_token", accessToken);
//        String content = HttpClientUtils.get(OPEN_ID_REQUEST_URL, parameterMap);
//        Matcher matcher = OPEN_ID_PATTERN.matcher(content);
//        if (matcher.find()) {
//            String openId = matcher.group(1);
//            return openId;
//        }
//        return null;
//    }
//    
//    //    /**
//    //     * 获取用户信息<br/>
//    //     * <功能详细描述>
//    //     * @param uniqueId
//    //     * @param accessToken
//    //     * @return [参数说明]
//    //     * 
//    //     * @return WeixinUserInfo [返回类型说明]
//    //     * @exception throws [异常类型] [异常说明]
//    //     * @see [类、类#方法、类#成员]
//    //     */
//    //    public WeixinUserInfo getUserInfo(String uniqueId, String accessToken) {
//    //        AssertUtils.notEmpty(uniqueId, "uniqueId is empty.");
//    //        AssertUtils.notEmpty(accessToken, "accessToken is empty.");
//    //        
//    //        Map<String, String> params = new HashMap<>();
//    //        params.put("access_token", accessToken);
//    //        params.put("openid", uniqueId);
//    //        params.put("lang", "zh_CN");
//    //        String result = HttpClientUtils.get(USER_INFO_REQUEST_URL, params);
//    //        //        try {
//    //        //            responseStr = new String(responseStr.getBytes("ISO-8859-1"),
//    //        //                    "utf8");
//    //        //        } catch (Exception e) {
//    //        //            throw new SILException("返回值字符集转码异常.ISO-8859-1 > UTF8.", e);
//    //        //        }
//    //        logger.info("------ getUserInfo:{access_token:{},openid:{},result:{}}",
//    //                accessToken,
//    //                uniqueId,
//    //                result);
//    //        @SuppressWarnings("unchecked")
//    //        Map<String, String> res = JsonUtils.toObject(result, Map.class);
//    //        
//    //        WeixinUserInfo user = new WeixinUserInfo();
//    //        user.setNickname(res.get("nickname"));
//    //        user.setSex(res.get("sex"));
//    //        user.setProvince(res.get("province"));
//    //        user.setCountry(res.get("country"));
//    //        user.setHeadImgUrl(res.get("headimgurl"));
//    //        user.setPrivilege(res.get("privilege"));
//    //        user.setUnionId(res.get("unionid"));
//    //        return user;
//    //    }
//    
//}
