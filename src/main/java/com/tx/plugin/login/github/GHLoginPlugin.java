/*
 * 描          述:  <描述>
 * 修  改   人:  Administrator
 * 修改时间:  2019年12月11日
 * <修改描述:>
 */
package com.tx.plugin.login.github;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.Base64Utils;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.databind.JsonNode;
import com.tx.core.exceptions.util.AssertUtils;
import com.tx.core.util.HttpClientUtils;
import com.tx.core.util.JsonUtils;
import com.tx.core.util.MessageUtils;
import com.tx.core.util.WebUtils;
import com.tx.local.basicdata.model.SexEnum;
import com.tx.plugin.login.LoginPlugin;
import com.tx.plugin.login.LoginPluginUtils;
import com.tx.plugin.login.exception.AuthorizeException;
import com.tx.plugin.login.model.LoginAccessToken;
import com.tx.plugin.login.model.LoginUserInfo;

/**
 * GITHUB登陆插件<br/>
 * 
 * @author  Administrator
 * @version  [版本号, 2019年12月11日]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
@Component("ghLoginPlugin")
public class GHLoginPlugin extends LoginPlugin<GHLoginPluginConfig> {
    
    /** 日志记录器 */
    private Logger logger = LoggerFactory.getLogger(GHLoginPlugin.class);
    
    /** code请求URL */
    private static final String CODE_REQUEST_URL = "https://github.com/login/oauth/authorize";
    
    /** uid请求URL */
    private static final String UID_REQUEST_URL = "https://github.com/login/oauth/access_token";
    
    /** userInfo请求URL */
    private static final String USER_INFO_REQUEST_URL = "https://api.github.com/user";
    
    /** "状态"属性名称 */
    private static final String STATE_ATTRIBUTE_NAME = GHLoginPlugin.class
            .getName() + ".STATE";
    
    /** "状态"属性名称 */
    private static final String REDIRECT_URL_ATTRIBUTE_NAME = GHLoginPlugin.class
            .getName() + ".REDIRECT_URL";
    
    /**
     * @return
     */
    @Override
    public String getName() {
        return "微博登陆插件";
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
        return "plugin.login.gh";
    }
    
    /**
     * @param operatorId
     * @param request
     * @return
     */
    @Override
    public ModelAndView bindHandle(String operatorId,
            HttpServletRequest request) {
        //将当前操作人员写入会话中
        LoginPluginUtils.pushUserId(operatorId);
        
        //state
        String state = Base64Utils.encodeToString(
                RandomStringUtils.randomAlphabetic(30).getBytes());
        //scope信息
        String scope = "snsapi_userinfo";
        //redirectUri
        String redirectUri = WebUtils.getBaseUrl(request)
                + "loginplugin/callback/GH";
        ModelAndView mv = getCodeHandle("loginplugin/signIn",
                redirectUri,
                state,
                scope,
                request);
        return mv;
    }
    
    /**
     * @param request
     * @return
     */
    @Override
    public ModelAndView loginHandle(HttpServletRequest request) {
        //state
        String state = Base64Utils.encodeToString(
                RandomStringUtils.randomAlphabetic(30).getBytes());
        //scope信息
        String scope = "snsapi_userinfo";
        //redirectUri
        String redirectUri = WebUtils.getBaseUrl(request)
                + "loginplugin/callback/GH";
        ModelAndView mv = getCodeHandle("loginplugin/signIn",
                redirectUri,
                state,
                scope,
                request);
        return mv;
    }
    
    /**
     * 获取code的handle<br/>
     * @param viewName
     * @param redirectUri
     * @param scope
     * @param request
     * @return
     */
    @Override
    public ModelAndView getCodeHandle(String viewName, String redirectUri,
            String state, String scope, HttpServletRequest request) {
        AssertUtils.notEmpty(viewName, "viewName is empty.");
        AssertUtils.notEmpty(redirectUri, "redirectUri is empty.");
        
        //写入state值
        LoginPluginUtils.pushAttribute(STATE_ATTRIBUTE_NAME, state);
        LoginPluginUtils.pushAttribute(REDIRECT_URL_ATTRIBUTE_NAME,
                redirectUri);
        
        GHLoginPluginConfig config = getConfig();
        ModelAndView mview = new ModelAndView();
        
        Map<String, Object> parameterMap = new LinkedHashMap<>();
        parameterMap.put("response_type", "code");
        parameterMap.put("client_id", config.getClientId());
        parameterMap.put("redirect_uri", redirectUri);
        parameterMap.put("state", state);
        
        mview.addObject("requestUrl", CODE_REQUEST_URL);
        mview.addObject("parameterMap", parameterMap);
        mview.setViewName(viewName);
        return mview;
    }
    
    /**
     * 获取第三方用户信息<br/>
     * @param code
     * @param request
     * @return
     * @throws AuthorizeException
     */
    @Override
    public LoginUserInfo getUserInfo(String code, String state,
            HttpServletRequest request) throws AuthorizeException {
        LoginAccessToken accessToken = getAccessToken(code, state, request);
        if (accessToken == null
                || StringUtils.isEmpty(accessToken.getUniqueId())
                || StringUtils.isEmpty(accessToken.getAccessToken())) {
            throw new AuthorizeException("获取AccessToken失败.");
        }
        //获取用户信息
        LoginUserInfo userInfo = getUserInfo(accessToken, request);
        return userInfo;
    }
    
    /**
     * 获取登陆用户的第三方唯一键<br/>
     * @param code
     * @param state
     * @param request
     * @return
     * @throws AuthorizeException
     */
    @Override
    public String getUniqueId(String code, String state,
            HttpServletRequest request) throws AuthorizeException {
        LoginAccessToken token = getAccessToken(code, state, request);
        String uniqueId = token.getUniqueId();
        return uniqueId;
    }
    
    /**
     * 获取AccessToken<br/>
     *      https://open.weibo.com/wiki/Oauth/access_token
     * <功能详细描述>
     * @param request
     * @param response
     * @return
     * @throws Exception [参数说明]
     * 
     * @return boolean [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    public LoginAccessToken getAccessToken(String code, String state,
            HttpServletRequest request) throws AuthorizeException {
        String sessionState = LoginPluginUtils
                .popAttribute(STATE_ATTRIBUTE_NAME);
        if (!StringUtils.equals(state, sessionState)) {
            throw new AuthorizeException(MessageUtils.format(
                    "state value is invalid.request_state:{}  session_state:{}",
                    state,
                    sessionState));
        }
        //从会话中获取redirectUri
        String redirectUri = LoginPluginUtils
                .popAttribute(REDIRECT_URL_ATTRIBUTE_NAME);
        
        GHLoginPluginConfig config = getConfig();
        Map<String, String> parameterMap = new HashMap<>();
        parameterMap.put("grant_type", "authorization_code");
        String appKey = config.getClientId();
        String appSecret = config.getClientSecret();
        parameterMap.put("client_id", appKey);
        parameterMap.put("client_secret", appSecret);
        parameterMap.put("code", code);
        parameterMap.put("redirect_uri", redirectUri);
        String result = HttpClientUtils.post(UID_REQUEST_URL, parameterMap);
        logger.info(
                "--- getAccessToken:{client_id:{},client_secret:{},code:{},result:{}}",
                appKey,
                appSecret,
                code,
                result);
        //{"access_token":"2.00zleWPB01Gkhc5cc927db85Da_qIB","remind_in":"157679999","expires_in":157679999,"uid":"1145561103","isRealName":"true"}
        
        JsonNode node = JsonUtils.toTree(result);
        String accessToken = node.get("access_token").textValue();
        String uid = node.get("uid").textValue();
        LoginAccessToken token = new LoginAccessToken();
        token.setUniqueId(uid);
        token.setAccessToken(accessToken);
        token.setExpiresIn(node.get("expires_in").longValue());
        token.getAttributeJSONObject().put("remind_in",
                node.get("remind_in").longValue());
        token.getAttributeJSONObject().put("isRealName",
                node.get("isRealName").booleanValue());
        if (StringUtils.isEmpty(token.getUniqueId())) {
            throw new AuthorizeException("获取UniqueId失败.");
        }
        if (StringUtils.isEmpty(token.getAccessToken())) {
            throw new AuthorizeException("获取AccessToken失败.");
        }
        return token;
    }
    
    /**
     * 获取用户信息<br/>
     * <功能详细描述>
     * @param accessToken
     * @param request
     * @return [参数说明]
     * 
     * @return LoginUserInfo [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    public LoginUserInfo getUserInfo(LoginAccessToken accessToken,
            HttpServletRequest request) {
        AssertUtils.notNull(accessToken, "accessToken is null.");
        AssertUtils.notEmpty(accessToken.getUniqueId(),
                "accessToken.uniqueId is empty.");
        AssertUtils.notEmpty(accessToken.getAccessToken(),
                "accessToken.accessToken is empty.");
        
        Map<String, String> params = new HashMap<String, String>();
        params.put("access_token", accessToken.getAccessToken());
        params.put("uid", accessToken.getUniqueId());
        String result = HttpClientUtils.get(USER_INFO_REQUEST_URL, params);
        logger.info("--- getUserInfo:{access_token:{},openid:{},result:{}}",
                accessToken.getAccessToken(),
                accessToken.getUniqueId(),
                result);
        
        JsonNode node = JsonUtils.toTree(result);
        LoginUserInfo user = new LoginUserInfo();
        user.setId(node.get("id").asText());
        user.setUsername(node.get("name").asText());
        user.setSex(StringUtils.equalsAnyIgnoreCase("m",
                node.get("gender").asText()) ? SexEnum.MALE : SexEnum.FEMALE);
        user.setUniqueId(accessToken.getUniqueId());
        JSONObject json = user.getAttributeJSONObject();
        json.put("screen_name", node.get("screen_name").asText());
        json.put("domain", node.get("domain").asText());
        json.put("province", node.get("province").asText());
        json.put("city", node.get("city").asText());
        json.put("location", node.get("location").asText());
        json.put("description", node.get("description").asText());
        json.put("url", node.get("url").asText());
        json.put("profile_image_url", node.get("profile_image_url").asText());
        return user;
    }
}
