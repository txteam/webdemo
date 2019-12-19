/*
 * 描          述:  <描述>
 * 修  改   人:  Administrator
 * 修改时间:  2019年12月11日
 * <修改描述:>
 */
package com.tx.plugin.wxlogin;

import java.util.Map;
import java.util.TreeMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;

import com.tx.component.plugin.context.Plugin;
import com.tx.core.exceptions.util.AssertUtils;
import com.tx.plugin.login.LoginPlugin;

/**
 * 微信登陆插件<br/>
 *  1、网站服务器向微信API传入带有 回调url 的参数
 *  2、手机微信通过摄像头扫二维码，从 光学原理 上完成数据的传递
 *  3、PC浏览器上查询扫码状态的 长连接 收到返回的状态值，并更新提示
 *  4、PC浏览器上查询手机客户端 点击确认按钮的状态值，并更新提示，然后 重定向 到 过程1 中传递url地址上
 *  5、网站服务器在授权成功后，完成本系统的用户注册或者登录的业务逻辑
 *  6、网站服务器重定向到用户登录成功的界面中（如果对于新注册用户不需要额外的审核的话）
 *  注：
 *  微信平台的各种API接口请参考：微信开放平台提供的官方文档
 *  微信扫码登录的开发权限需要在微信开放平台中进行企业资质认证（个人用户无法获得）
 *  回调url 的域必需在微信开放平台中进行填写备案，本地开发时传递的 回调url 参数必须和备案一致
 * 
 * @author  Administrator
 * @version  [版本号, 2019年12月11日]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
@Component("operatorWeixinLoginPlugin")
public class OperatorWXLoginPlugin extends LoginPlugin<OperatorWXLoginPluginConfig> {
    
    /** 日志记录器 */
    private Logger logger = LoggerFactory.getLogger(OperatorWXLoginPlugin.class);
    
    /** code请求URL */
    private static final String CODE_REQUEST_URL = "https://open.weixin.qq.com/connect/oauth2/authorize#wechat_redirect";
    
    /** openId请求URL */
    private static final String OPEN_ID_REQUEST_URL = "https://api.weixin.qq.com/sns/oauth2/access_token";
    
    /** 用戶信息請求URL */
    private static final String USER_INFO_REQUEST_URL = "https://api.weixin.qq.com/sns/userinfo";

    /**
     * @return
     */
    @Override
    public String getName() {
        return "微信登陆插件";
    }
    
    /**
     * @return
     */
    @Override
    public String getVersion() {
        return "1.0";
    }
    
    /**
     * @return
     */
    @Override
    public String getPrefix() {
        return "plugin.login.operator.wx";
    }
    
    /**
     * 获取跳转到code获取的链接url
     * <功能详细描述>
     * @return [参数说明]
     * 
     * @return String [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    public String getRquestCodeUrl() {
        String requestUrl = CODE_REQUEST_URL;
        return requestUrl;
    }
    
    /**
     * 构建请求code的参数<br/>
     * <功能详细描述>
     * @return [参数说明]
     * 
     * @return Map<String,String> [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    public Map<String, String> buildRequestCodeParams(String redirectUrl) {
        AssertUtils.notEmpty(redirectUrl, "redirectUrl is empty.");
        OperatorWXLoginPluginConfig config = getConfig();
        
        Map<String, String> parameterMap = new TreeMap<>();
        //应用唯一标识，在微信开放平台提交应用审核通过后获得
        parameterMap.put("appid", config.getAppId());
        //redirect_uri  是   重定向地址，需要进行UrlEncode
        parameterMap.put("redirect_uri", redirectUrl);
        //response_type 是   填code
        parameterMap.put("response_type", "code");
        //scope 是   应用授权作用域，拥有多个作用域用逗号（,）分隔，网页应用目前仅填写snsapi_login即可
        parameterMap.put("scope", "snsapi_userinfo");
        return parameterMap;
    }
    
    /**
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public boolean isSignInSuccess(HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        //        Map<String, Object> params = new HashMap<>();
        //        params.put("access_token", "");
        //        params.put("openid", "");
        //        params.put("lang", "zh_CN");
        //        String str = 
        //                
        //        //WebUtils.get(USER_INFO_REQUEST_URL, params);
        //        System.out.println(str);
        //
        //        String code = request.getParameter("code");
        //        if (StringUtils.isEmpty(code)) {
        //            return false;
        //        }
        //        try {
        //            OAuthClient oAuthClient = new OAuthClient(new URLConnectionClient());
        //            OAuthClientRequest.TokenRequestBuilder tokenRequestBuilder = OAuthClientRequest
        //                    .tokenLocation(OPEN_ID_REQUEST_URL);
        //            tokenRequestBuilder.setParameter("appid", getAppId());
        //            tokenRequestBuilder.setParameter("secret", getAppSecret());
        //            tokenRequestBuilder.setCode(code);
        //            tokenRequestBuilder.setGrantType(GrantType.AUTHORIZATION_CODE);
        //            OAuthClientRequest accessTokenRequest = tokenRequestBuilder.buildQueryMessage();
        //            OAuthJSONAccessTokenResponse authJSONAccessTokenResponse = oAuthClient.accessToken(accessTokenRequest,
        //                    OAuth.HttpMethod.GET);
        //            String openId = authJSONAccessTokenResponse.getParam("openid");
        //            String accessToken = authJSONAccessTokenResponse.getParam("access_token");
        //             System.out.println(openId);
        //             System.out.println(accessToken);
        //            if (StringUtils.isNotEmpty(openId) && StringUtils.isNotEmpty(accessToken)) {
        //                request.setAttribute("openid", openId);
        //                request.setAttribute("access_token", accessToken);
        //                return true;
        //            }
        //
        //        } catch (OAuthSystemException e) {
        //            System.out.println("登录失败");
        //            throw new RuntimeException(e.getMessage(), e);
        //        } catch (OAuthProblemException e) {
        //            System.out.println("登录失败1");
        //            throw new RuntimeException(e.getMessage(), e);
        //        }
        return false;
    }
    
    //基础支持
    //code获取支撑
    //获取access_token
    
    /**
     * @param uniqueId
     * @param accessToken
     * @param request
     * @param response
     */
    public void getUserInfo(String uniqueId, String accessToken,
            HttpServletRequest request, HttpServletResponse response) {
        // TODO Auto-generated method stub
        
    }
    
    public void signInHandle(HttpServletRequest request,
            HttpServletResponse response, ModelAndView modelAndView)
            throws Exception {
        
        //        System.out.println("登录参数："+parameterMap.toString());
        //        modelAndView.addObject("requestUrl", CODE_REQUEST_URL);
        //        modelAndView.addObject("parameterMap", parameterMap);
        //        modelAndView.setViewName(LoginPlugin.DEFAULT_PAY_VIEW_NAME);
    }
    

    
}
