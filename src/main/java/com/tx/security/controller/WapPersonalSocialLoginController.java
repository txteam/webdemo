package com.tx.security.controller;
///*
// * 描          述:  <描述>
// * 修  改   人:  Administrator
// * 修改时间:  2019年12月25日
// * <修改描述:>
// */
//package com.tx.plugin.login.controller;
//
//import javax.annotation.Resource;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import javax.servlet.http.HttpSession;
//
//import com.tx.local.clientinfo.model.ClientInfo;
//import com.tx.local.clientinfo.model.ClientSocialAccount;
//import com.tx.local.clientinfo.model.ClientSocialAccountTypeEnum;
//import com.tx.local.clientinfo.service.ClientInfoService;
//import com.tx.local.clientinfo.service.ClientSocialAccountService;
//import com.tx.local.report.model.ReportCompanyTypeEnum;
//import com.tx.local.report.model.ReportPersonal;
//import com.tx.local.report.service.ReportPersonalService;
//import org.apache.commons.lang.RandomStringUtils;
//import org.apache.commons.lang3.StringUtils;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.security.web.WebAttributes;
//import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
//import org.springframework.security.web.savedrequest.RequestCache;
//import org.springframework.security.web.savedrequest.SavedRequest;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.ModelMap;
//import org.springframework.util.Base64Utils;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.servlet.ModelAndView;
//
//import com.tx.core.util.MessageUtils;
//import com.tx.core.util.WebUtils;
//import com.tx.local.operator.service.OperSocialAccountService;
//import com.tx.plugin.login.LoginPlugin;
//import com.tx.plugin.login.LoginPluginConstants;
//import com.tx.plugin.login.LoginPluginUtils;
//import com.tx.plugin.login.exception.SocialAuthorizeException;
//
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
///**
// * 操作人员第三方用户登陆
// * <功能详细描述>
// * 
// * @author  Administrator
// * @version  [版本号, 2019年12月25日]
// * @see  [相关类/方法]
// * @since  [产品/模块版本]
// */
//@Controller
//@RequestMapping("/wap/personal")
//public class WapPersonalSocialLoginController {
//    
//    /** 日志记录句柄 */
//    protected final Logger logger = LoggerFactory.getLogger(getClass());
//    
//    /** 第三方用户账户业务层 */
//    @Resource
//    private OperSocialAccountService operSocialAccountService;
//
//    //填报人业务层
//    @Resource(name = "reportPersonalService")
//    private ReportPersonalService reportPersonalService;
//
//    @Resource
//    private ClientSocialAccountService clientSocialAccountService;
//
//    @Resource
//    private ClientInfoService clientInfoService;
//    
//    /** 请求缓存 */
//    private RequestCache requestCache = new HttpSessionRequestCache();
//    
//    /**
//     * 请求编码forbind<br/>
//     * <功能详细描述>
//     * @param request
//     * @param response
//     * @return [参数说明]
//     * 
//     * @return ModelAndView [返回类型说明]
//     * @exception throws [异常类型] [异常说明]
//     * @see [类、类#方法、类#成员]
//     */
//    @RequestMapping("/social/wxlogin")
//    public ModelAndView wxlogin(HttpServletRequest request, HttpServletResponse response) {
//        String id = request.getParameter("id");
//        String type =  request.getParameter("type");
//        if(StringUtils.isNotBlank(id) && StringUtils.isNotBlank(type) ){
//            request.getSession().setAttribute("InstitutionId", id);
//            request.getSession().setAttribute("InstitutionType", type);
//        }
//
//        LoginPlugin<?> loginPlugin = LoginPluginUtils.getLoginPlugin("WX");
//        if (loginPlugin == null || !loginPlugin.isInstalled()) {
//            HttpSession session = request.getSession(true);
//            SocialAuthorizeException e = new SocialAuthorizeException(
//                    MessageUtils.format("微信登陆插件不存在或未安装."));
//            session.setAttribute(WebAttributes.AUTHENTICATION_EXCEPTION, e);
//            ModelAndView mv = new ModelAndView();
//            mv.setViewName("redirect:/personal/social/loginerror");
//            return mv;
//        }
//        
//        //如果会话中不含uniqueId:
//        SavedRequest req = requestCache.getRequest(request, response);
//        String redirectUrl = req.getRedirectUrl();
//        //在会话中以特殊的字段存储
//        String sessionUniqueId = (String) request.getSession(true)
//                .getAttribute("uniqueId");
//        if (StringUtils.isEmpty(sessionUniqueId)) {
//            
//            if(StringUtils.isEmpty(req.getRedirectUrl())){
//                //....
//            }
//            //WebUtils.getBaseUrl(request)+ "/wap/personal/fillreport/xxxx";
//            String state = Base64Utils.encodeToString(
//                    RandomStringUtils.randomAlphabetic(30).getBytes());
//            String scope = "snsapi_base";//
//            ModelAndView mv = loginPlugin.getCodeHandle("loginplugin/signIn",
//                    redirectUrl,
//                    state,
//                    scope,
//                    request);
//            return mv;
//        }
//        //如果含有则跳转到注册页面
//        //保存code,保存state到会话
//        request.getSession().setAttribute("code",  request.getParameter("code"));
//        request.getSession().setAttribute("state",  request.getParameter("state"));
//
//        return new ModelAndView("report/wapview/home");
//    }
//    
//    /**
//     * 请求编码forbind<br/>
//     * <功能详细描述>
//     * @param request
//     * @param response
//     * @return [参数说明]
//     * 
//     * @return ModelAndView [返回类型说明]
//     * @exception throws [异常类型] [异常说明]
//     * @see [类、类#方法、类#成员]
//     */
//    @RequestMapping("/social/registe")
//    public String registe(HttpServletRequest request,
//                          HttpServletResponse response,
//                            ReportPersonal reportPersonal) {
//        //如果用户完成注册，则跳转到登陆页
//
//        String id = (String)request.getSession().getAttribute("InstitutionId");
//        String type =  (String)request.getSession().getAttribute("InstitutionType");
//
//        String uniqueId =  (String)request.getSession().getAttribute("uniqueId");
//        if(type.equals(ReportCompanyTypeEnum.ZCB.getName())){
//            reportPersonal.setInstitutionId(id);
//        }
//        if(type.equals(ReportCompanyTypeEnum.HZS.getName())){
//            reportPersonal.setVillageOfficeId(id);
//        }
//        ClientInfo clientInfo = new ClientInfo();
//        clientInfo.setUsername(reportPersonal.getPhoneNumber());
//        clientInfo.setName(reportPersonal.getName());
//        clientInfo.setPassword(reportPersonal.getPhoneNumber());
//        clientInfoService.insert(clientInfo);
//
//        ClientSocialAccount clientSocialAccount =new ClientSocialAccount();
//        clientSocialAccount.setClientId(clientInfo.getId());
//        clientSocialAccount.setUniqueId(uniqueId);
//        clientSocialAccount.setType(ClientSocialAccountTypeEnum.valueOf("WX"));
//        clientSocialAccountService.insert(clientSocialAccount);
//
//        HashMap<String, Object> params =  new HashMap<String, Object>();
//        params.put("name",reportPersonal.getName());
//        params.put("phoneNumber",reportPersonal.getPhoneNumber());
//        List<ReportPersonal> resList = reportPersonalService.queryList(params);
//        if(resList != null && resList.size() > 0){
//            request.getSession().setAttribute("ReportPersonalId", resList.get(0).getId());
//        }else{
//            reportPersonal.setWxUniqueId(uniqueId);
//            reportPersonalService.insert(reportPersonal);
//            request.getSession().setAttribute("ReportPersonalId", reportPersonal.getId());
//        }
//
//        String code = (String)request.getSession().getAttribute("code");
//        String state =  (String)request.getSession().getAttribute("state");
//        return "redirect:/wap/personal/fillreport/toLogin?code=" + code + "&state=" + state;
//    }
//
//
//    /**
//     * 请求编码forbind<br/>
//     * <功能详细描述>
//     * @param request
//     * @return [参数说明]
//     *
//     * @return ModelAndView [返回类型说明]
//     * @exception throws [异常类型] [异常说明]
//     * @see [类、类#方法、类#成员]
//     */
//    @RequestMapping("/fillreport/toLogin")
//    public String toLogin(HttpServletRequest request,
//                          HttpServletResponse response,
//                          @RequestParam String id,
//                          @RequestParam String type) {
//        SavedRequest req = requestCache.getRequest(request, response);
//        String redirectUrl = req.getRedirectUrl();
//        request.getSession().setAttribute("InstitutionId", id);
//        request.getSession().setAttribute("InstitutionType", type);
//        //如果用户完成注册，则跳转到登陆页
//        return "report/wapview/index";
//    }
//}
