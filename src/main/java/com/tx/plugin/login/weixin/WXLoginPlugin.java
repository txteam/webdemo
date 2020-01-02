///*
// * 描          述:  <描述>
// * 修  改   人:  Administrator
// * 修改时间:  2019年12月11日
// * <修改描述:>
// */
//package com.tx.plugin.login.weixin;
//
//import java.util.HashMap;
//import java.util.LinkedHashMap;
//import java.util.Map;
//
//import javax.servlet.http.HttpServletRequest;
//
//import org.apache.commons.lang3.StringUtils;
//import org.apache.oltu.oauth2.client.OAuthClient;
//import org.apache.oltu.oauth2.client.URLConnectionClient;
//import org.apache.oltu.oauth2.client.request.OAuthClientRequest;
//import org.apache.oltu.oauth2.client.response.OAuthJSONAccessTokenResponse;
//import org.apache.oltu.oauth2.common.OAuth;
//import org.apache.oltu.oauth2.common.exception.OAuthProblemException;
//import org.apache.oltu.oauth2.common.exception.OAuthSystemException;
//import org.apache.oltu.oauth2.common.message.types.GrantType;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.stereotype.Component;
//import org.springframework.web.servlet.ModelAndView;
//
//import com.alibaba.fastjson.JSONObject;
//import com.tx.core.exceptions.util.AssertUtils;
//import com.tx.core.util.HttpClientUtils;
//import com.tx.core.util.JsonUtils;
//import com.tx.local.basicdata.model.SexEnum;
//import com.tx.plugin.login.LoginPlugin;
//import com.tx.plugin.login.exception.AuthorizeException;
//import com.tx.plugin.login.model.LoginAccessToken;
//import com.tx.plugin.login.model.LoginUserInfo;
//
///**
// * 微信登陆插件<br/>
// *  1、网站服务器向微信API传入带有 回调url 的参数
// *  2、手机微信通过摄像头扫二维码，从 光学原理 上完成数据的传递
// *  3、PC浏览器上查询扫码状态的 长连接 收到返回的状态值，并更新提示
// *  4、PC浏览器上查询手机客户端 点击确认按钮的状态值，并更新提示，然后 重定向 到 过程1 中传递url地址上
// *  5、网站服务器在授权成功后，完成本系统的用户注册或者登录的业务逻辑
// *  6、网站服务器重定向到用户登录成功的界面中（如果对于新注册用户不需要额外的审核的话）
// *  注：
// *  微信平台的各种API接口请参考：微信开放平台提供的官方文档
// *  微信扫码登录的开发权限需要在微信开放平台中进行企业资质认证（个人用户无法获得）
// *  回调url 的域必需在微信开放平台中进行填写备案，本地开发时传递的 回调url 参数必须和备案一致
// * 
// * @author  Administrator
// * @version  [版本号, 2019年12月11日]
// * @see  [相关类/方法]
// * @since  [产品/模块版本]
// */
//@Component("wxLoginPlugin")
//public class WXLoginPlugin extends LoginPlugin<WXLoginPluginConfig> {
//    
//    /** 日志记录器 */
//    private Logger logger = LoggerFactory.getLogger(WXLoginPlugin.class);
//    
//    public static final String CODE_REQUEST_QR_URL = "https://open.weixin.qq.com/connect/qrconnect#wechat_redirect";
//    
//    /** code请求URL */
//    public static final String CODE_REQUEST_URL = "https://open.weixin.qq.com/connect/oauth2/authorize#wechat_redirect";
//    
//    /** openId请求URL */
//    public static final String OPEN_ID_REQUEST_URL = "https://api.weixin.qq.com/sns/oauth2/access_token";
//    
//    /** 用戶信息請求URL */
//    public static final String USER_INFO_REQUEST_URL = "https://api.weixin.qq.com/sns/userinfo";
//    
//    /** key province */
//    public static final String KEY_PROVINCE = "province";
//    
//    /** key country */
//    public static final String KEY_COUNTRY = "country";
//    
//    /** key headimgurl */
//    public static final String KEY_HEAD_IMG_URL = "headimgurl";
//    
//    /** key privilege */
//    public static final String KEY_PRIVILEGE = "privilege";
//    
//    /** key unionid */
//    public static final String KEY_UNION_ID = "unionid";
//    
//    /**
//     * @return
//     */
//    @Override
//    public String getName() {
//        return "微信登陆插件";
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
//        return "plugin.login.wx";
//    }
//    
//    //https://open.weixin.qq.com/connect/qrconnect?appid=wx356a9316d6bf588a&redirect_uri=https%3A%2F%2Flocalhost:8090%2webdemo%2FoperSocialAccount%2Fbind&response_type=code&scope=snsapi_login&state=3d6be0a4035d839573b04816624a415e#wechat_redirect
//    /**
//     * 登录处理[signInHandle]<br/>
//     * 让系统进入页面，然后完成自动提交，进入第三方以便获取code值<br/>
//     * 
//     * <功能详细描述>
//     * @param viewName
//     * @param redirectUri
//     * @param request
//     * @return [参数说明]
//     * 
//     * @return ModelAndView [返回类型说明]
//     * @exception throws [异常类型] [异常说明]
//     * @see [类、类#方法、类#成员]
//     */
//    @Override
//    public ModelAndView getCodeHandle(String viewName, String redirectUri,
//            String state, String scope, HttpServletRequest request) {
//        AssertUtils.notEmpty(viewName, "viewName is empty.");
//        
//        WXLoginPluginConfig config = getConfig();
//        
//        ModelAndView mview = new ModelAndView(viewName);
//        mview.addObject("requestUrl", CODE_REQUEST_QR_URL);
//        
//        Map<String, String> parameterMap = new LinkedHashMap<>();
//        //应用唯一标识，在微信开放平台提交应用审核通过后获得
//        parameterMap.put("appid", config.getAppId());
//        //redirect_uri  是   重定向地址，需要进行UrlEncode
//        parameterMap.put("redirect_uri", redirectUri);
//        //response_type 是   填code
//        parameterMap.put("response_type", "code");
//        //scope 是   应用授权作用域，拥有多个作用域用逗号（,）分隔，网页应用目前仅填写snsapi_login即可
//        //snsapi_base,snsapi_userinfo
//        if (StringUtils.isEmpty(scope)) {
//            parameterMap.put("scope", "snsapi_base");
//        } else {
//            parameterMap.put("scope", scope);
//        }
//        
//        mview.addObject("parameterMap", parameterMap);
//        return mview;
//    }
//    
//    /**
//     * 判断是否登录成功<br/>
//     * <功能详细描述>
//     * @param code
//     * @param request
//     * @return [参数说明]
//     * 
//     * @return boolean [返回类型说明]
//     * @exception throws [异常类型] [异常说明]
//     * @see [类、类#方法、类#成员]
//     */
//    @Override
//    public LoginUserInfo getUserInfo(String code, String state,
//            HttpServletRequest request) throws AuthorizeException {
//        LoginAccessToken accessToken = getAccessToken(code, request);
//        if (accessToken == null
//                || StringUtils.isEmpty(accessToken.getUniqueId())
//                || StringUtils.isEmpty(accessToken.getAccessToken())) {
//            throw new AuthorizeException("获取AccessToken失败.");
//        }
//        //获取用户信息
//        LoginUserInfo userInfo = getUserInfo(accessToken, request);
//        return userInfo;
//    }
//    
//    /**
//     * 获取用户唯一键<br/>
//     * <功能详细描述>
//     * @param code
//     * @param request
//     * @return [参数说明]
//     * 
//     * @return String [返回类型说明]
//     * @exception throws [异常类型] [异常说明]
//     * @see [类、类#方法、类#成员]
//     */
//    @Override
//    public String getUniqueId(String code, String state,
//            HttpServletRequest request) throws AuthorizeException {
//        LoginAccessToken accessToken = getAccessToken(code, request);
//        if (accessToken == null
//                || StringUtils.isEmpty(accessToken.getUniqueId())
//                || StringUtils.isEmpty(accessToken.getAccessToken())) {
//            throw new AuthorizeException("获取AccessToken失败.");
//        }
//        //获取用户信息
//        String uniqueId = accessToken.getUniqueId();
//        return uniqueId;
//    }
//    
//    /**
//     * 获取AccessToken<br/>
//     * <功能详细描述>
//     * @param code
//     * @param request
//     * @return
//     * @throws AuthorizeException [参数说明]
//     * 
//     * @return LoginAccessToken [返回类型说明]
//     * @exception throws [异常类型] [异常说明]
//     * @see [类、类#方法、类#成员]
//     */
//    public LoginAccessToken getAccessToken(String code,
//            HttpServletRequest request) throws AuthorizeException {
//        WXLoginPluginConfig config = getConfig();
//        try {
//            //从源代码分析，oauthClient是否重复使用，对性能无影响，URLConnectionClient的构造函数并无多余动作
//            //疑问： OauthClient以及URLConnectionClient中源代码显示，并无显式关闭连接的情况.系统是如何保证连接关闭的，需要查询资资料
//            OAuthClient oAuthClient = new OAuthClient(
//                    new URLConnectionClient());
//            OAuthClientRequest.TokenRequestBuilder builder = OAuthClientRequest
//                    .tokenLocation(OPEN_ID_REQUEST_URL);
//            String appid = config.getAppId();
//            String secret = config.getAppSecret();
//            builder.setParameter("appid", appid);
//            builder.setParameter("secret", secret);
//            builder.setCode(code);
//            builder.setGrantType(GrantType.AUTHORIZATION_CODE);
//            OAuthClientRequest accessTokenRequest = builder.buildQueryMessage();
//            OAuthJSONAccessTokenResponse response = oAuthClient
//                    .accessToken(accessTokenRequest, OAuth.HttpMethod.GET);
//            
//            LoginAccessToken token = new LoginAccessToken();
//            token.setUniqueId(response.getParam("openid"));
//            token.setAccessToken(response.getAccessToken());
//            token.setExpiresIn(response.getExpiresIn());
//            token.setRefreshToken(response.getRefreshToken());
//            token.setTokenType(response.getTokenType());
//            token.setScope(response.getScope());
//            
//            logger.info(
//                    "------ getAccessToken:{appid:{},secret:{},uniqueId:{},accessToken:{},refreshToken:{},expiresIn:{}",
//                    appid,
//                    secret,
//                    token.getUniqueId(),
//                    token.getAccessToken(),
//                    token.getRefreshToken(),
//                    token.getExpiresIn());
//            if (StringUtils.isEmpty(token.getUniqueId())) {
//                throw new AuthorizeException("获取微信用户openid失败.");
//            }
//            return token;
//        } catch (OAuthSystemException | OAuthProblemException e) {
//            throw new AuthorizeException(
//                    "获取微信用户openid失败.发生异常:" + e.getMessage(), e);
//        }
//    }
//    
//    /**
//     * 获取用户信息<br/>
//     * <功能详细描述>
//     * @param uniqueId
//     * @param accessToken
//     * @return [参数说明]
//     * 
//     * @return WeixinUserInfo [返回类型说明]
//     * @exception throws [异常类型] [异常说明]
//     * @see [类、类#方法、类#成员]
//     */
//    public LoginUserInfo getUserInfo(LoginAccessToken accessToken,
//            HttpServletRequest request) {
//        AssertUtils.notNull(accessToken, "accessToken is null.");
//        AssertUtils.notEmpty(accessToken.getUniqueId(),
//                "accessToken.uniqueId is empty.");
//        AssertUtils.notEmpty(accessToken.getAccessToken(),
//                "accessToken.accessToken is empty.");
//        
//        Map<String, String> params = new HashMap<>();
//        params.put("access_token", accessToken.getAccessToken());
//        params.put("openid", accessToken.getUniqueId());
//        params.put("lang", "zh_CN");
//        String result = HttpClientUtils.get(USER_INFO_REQUEST_URL, params);
//        logger.info("------ getUserInfo:{access_token:{},openid:{},result:{}}",
//                accessToken.getAccessToken(),
//                accessToken.getUniqueId(),
//                result);
//        @SuppressWarnings("unchecked")
//        Map<String, String> res = JsonUtils.toObject(result, Map.class);
//        
//        LoginUserInfo user = new LoginUserInfo();
//        user.setUsername(res.get("nickname"));
//        user.setSex(res.get("sex") == null ? SexEnum.MALE : SexEnum.FEMALE);
//        JSONObject json = user.getAttributeJSONObject();
//        json.put(KEY_PROVINCE, res.get("province"));
//        json.put(KEY_COUNTRY, res.get("country"));
//        json.put(KEY_HEAD_IMG_URL, res.get("headimgurl"));
//        json.put(KEY_PRIVILEGE, res.get("privilege"));
//        json.put(KEY_UNION_ID, res.get("unionid"));
//        
//        return user;
//    }
//    
//}
