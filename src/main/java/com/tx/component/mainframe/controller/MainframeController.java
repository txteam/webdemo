/*
 * 描          述:  <描述>
 * 修  改   人:  PengQingyang
 * 修改时间:  2012-12-16
 * <修改描述:>
 */
package com.tx.component.mainframe.controller;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tx.component.auth.AuthConstant;
import com.tx.component.auth.context.AuthContext;
import com.tx.component.auth.context.AuthSessionContext;
import com.tx.component.mainframe.context.MenuContext;
import com.tx.component.mainframe.context.WebContextUtils;
import com.tx.component.mainframe.model.MenuItem;
import com.tx.component.mainframe.servicelog.LoginLog;
import com.tx.component.operator.OperatorConstants;
import com.tx.component.operator.model.Operator;
import com.tx.component.operator.model.Organization;
import com.tx.component.operator.service.OperatorRefService;
import com.tx.component.operator.service.OperatorService;
import com.tx.component.operator.service.OrganizationService;
import com.tx.component.operator.service.PostService;
import com.tx.component.operator.service.RoleService;
import com.tx.component.remote.model.RemoteResult;
import com.tx.component.servicelog.context.ServiceLoggerContext;
import com.tx.component.servicelog.context.logger.ServiceLogger;
import com.tx.core.util.MD5Utils;

/**
 * 登录功能入口
 * <功能详细描述>
 * 
 * @author  PengQingyang
 * @version  [版本号, 2012-12-16]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
@Controller("mainframeController")
@RequestMapping("/mainframe")
public class MainframeController {
    
    /** 日志记录器 */
    @SuppressWarnings("unused")
    private Logger logger = LoggerFactory.getLogger(MainframeController.class);
    
    /** 组织业务层 */
    @Resource(name = "organizationService")
    private OrganizationService organizationService;
    
    /** 操作人员业务层 */
    @Resource(name = "operatorService")
    private OperatorService operatorService;
    
    /** 角色业务层 */
    @Resource(name = "roleService")
    private RoleService roleService;
    
    /** 职位业务层 */
    @Resource(name = "postService")
    private PostService postService;
    
    /** 人员引用业务层 */
    @Resource(name = "operatorRefService")
    private OperatorRefService operatorRefService;
    
    /** 权限容器 */
    @Resource(name = "authContext")
    private AuthContext authContext;
    
    /**
      * 跳转到登录页面<br/>
      * <功能详细描述>
      * @return [参数说明]
      * 
      * @return String [返回类型说明]
      * @exception throws [异常类型] [异常说明]
      * @see [类、类#方法、类#成员]
     */
    @RequestMapping("/toLogin")
    public String toLogin() {
        return "/mainframe/login";
    }
    
    /**
      * 跳转到会话丢失错误页面<br/>
      * <功能详细描述>
      * @return [参数说明]
      * 
      * @return String [返回类型说明]
      * @exception throws [异常类型] [异常说明]
      * @see [类、类#方法、类#成员]
     */
    @RequestMapping("/toSessionLostError")
    public String toSessionLostError() {
        return "/error/sessionLostError";
    }
    
    /**
      * 跳转到主页面中<br/> 
      * <功能详细描述>
      * @param model
      * @return [参数说明]
      * 
      * @return String [返回类型说明]
      * @exception throws [异常类型] [异常说明]
      * @see [类、类#方法、类#成员]
     */
    @RequestMapping("/toMainframe")
    public String toMainframe(Model model) {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        model.addAttribute("now", df.format(new Date()));
        return "/mainframe/mainframe";
    }
    
    /**
     * 跳转到主页面中<br/> 
     *<功能详细描述>
     * @param model
     * @return [参数说明]
     * 
     * @return String [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
    */
    @RequestMapping("/toModifyPassword")
    public String toModifyPassword(Model model) {
        return "/mainframe/modifyPassword";
    }
    
    /**
      * 登录<br/>
      * 1、检查输入的用户名和密码是否正确<br/>
      * 2、如果正确，加载用户权限<br/>
      * 3、查询页面菜单<br/>
      * 3、页面的用户菜单 tab等，等待页面加载完成后再进行ajax加载
      * 
      * @param loginName
      * @param password
      * @param style 登录界面
      * @return [参数说明]
      * 
      * @return String [返回类型说明]
      * @exception throws [异常类型] [异常说明]
      * @see [类、类#方法、类#成员]
     */
    @ResponseBody
    @RequestMapping("/login")
    public RemoteResult login(@RequestParam("loginName") String loginName,
            @RequestParam("password") String password,
            @RequestParam Map<String, String> request) {
        RemoteResult result = new RemoteResult();
        //登录人员
        Operator oper = this.operatorService.findByLoginName(loginName);
        if (oper == null) {
            result.setResult(false);
            result.setErrorMessage("指定用户不存在.");
            return result;
        }
        //用户是否被锁定
        if (!oper.isValid() || oper.isLocked()) {
            result.setResult(false);
            result.setErrorMessage("指定用户已被锁定，请联系管理员进行解锁.");
            return result;
        }
        //如果登录不成功继续返回登录页面
        if (!this.operatorService.checkPasswordForLogin(loginName, password)) {
            result.setResult(false);
            result.setErrorMessage("用户密码错误.请重新输入.");
            return result;
        }
        
        //调用权限容器登录句柄
        authContextLoginHandler(oper);
        webContextLoginHandler(oper);
        //这个时候记录日志中信息没有写进需要手动写入
        ServiceLogger<LoginLog> serviceLogger = ServiceLoggerContext.getLogger(LoginLog.class);
        serviceLogger.setAttribute("operatorId", oper.getId());
        serviceLogger.setAttribute("operatorName", oper.getUserName());
        serviceLogger.setAttribute("operatorLoginName", oper.getLoginName());
        serviceLogger.log(new LoginLog("jhms", LoginLog.LOGINTYPE_LOGIN,
                "操作员{}登录系统", new Object[] { loginName }));
        //如果密码没有修改过或者修改时间超过三个月
        if (oper.getPwdUpdateDate() == null
                || checkPwdUpdateDateIsAfterThirdMonth(oper.getPwdUpdateDate())) {
            result.setResult(false);
            result.setErrorMessage("指定用户已被锁定，请联系管理员进行解锁.");
            result.setData("mainframe/toModifyPassword.action");
            return result;
        }
        result.setResult(true);
        result.setData("mainframe/toMainframe.action");
        return result;
    }
    
    /**
      * 检查密码修改时间是否超过三个月<br/>
      *<功能详细描述>
      * @param pwdUpdateDate
      * @return [参数说明]
      * 
      * @return boolean [返回类型说明]
      * @exception throws [异常类型] [异常说明]
      * @see [类、类#方法、类#成员]
     */
    private boolean checkPwdUpdateDateIsAfterThirdMonth(Date pwdUpdateDate) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(pwdUpdateDate);
        cal.add(Calendar.MONTH, 3);
        Date afterThirdMonthDate = cal.getTime();
        return new Date().after(afterThirdMonthDate);
    }
    
    /** 
     * 在登录期间需要通过WebContextUtils写入的相关操作
     *<功能详细描述>
     * @param oper [参数说明]
     * 
     * @return void [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    private void webContextLoginHandler(Operator oper) {
        //将当前登录人员写入会话中
        WebContextUtils.putOperatorInSession(oper);
        //将当前组织写入会话中
        Organization currentOrg = this.organizationService.findById(oper.getOrganization()
                .getId());
        if (currentOrg != null) {
            WebContextUtils.putOganizationInSession(currentOrg);
        }
    }
    
    /** 
     * 权限容器登录句柄
     *<功能详细描述>
     * @param oper [参数说明]
     * 
     * @return void [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    private void authContextLoginHandler(Operator oper) {
        //初始化用户权限到当前会话中
        MultiValueMap<String, String> refType2RefIdMapping = new LinkedMultiValueMap<String, String>();
        refType2RefIdMapping.add(AuthConstant.AUTHREFTYPE_OPERATOR,
                oper.getId());
        refType2RefIdMapping.add(AuthConstant.AUTHREFTYPE_OPERATOR_TEMPORARY,
                oper.getId());
        
        /** 获取人员的职位集合 */
        Set<String> postIdSet = this.operatorRefService.queryRefIdSetByOperatorId(OperatorConstants.OPERATORREF_TYPE_POST,
                oper.getId());
        if (!CollectionUtils.isEmpty(postIdSet)) {
            for (String postIdTemp : postIdSet) {
                refType2RefIdMapping.add(AuthConstant.AUTHREFTYPE_POST,
                        postIdTemp);
            }
        }
        Set<String> roleIdSet = this.operatorRefService.queryRefIdSetByOperatorId(OperatorConstants.OPERATORREF_TYPE_ROLE,
                oper.getId());
        if (!CollectionUtils.isEmpty(roleIdSet)) {
            for (String roleIdTemp : roleIdSet) {
                refType2RefIdMapping.add(AuthConstant.AUTHREFTYPE_ROLE,
                        roleIdTemp);
            }
        }
        AuthSessionContext.putOperatorIdToSession(oper.getId());
        authContext.login(refType2RefIdMapping);
    }
    
    /**
     * 修改密码 <功能详细描述>
     * 
     * @param password
     * @param newPassword
     * @param confirmPassword
     * @return [参数说明]
     * 
     * @return boolean [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @ResponseBody
    @RequestMapping("modifyPassword")
    public RemoteResult modifyPassword(
            @RequestParam(value = "password") String password,
            @RequestParam(value = "newPassword") String newPassword,
            @RequestParam(value = "confirmPassword") String confirmPassword) {
        RemoteResult result = new RemoteResult();
        Operator currentOper = WebContextUtils.getCurrentOperator();
        if (currentOper == null) {
            result.setResult(false);
            result.setErrorMessage("用户未登录.");
            result.setData("mainframe/toLogin.action");
            return result;
        }
        if (!newPassword.equals(confirmPassword)) {
            result.setResult(false);
            result.setErrorMessage("两次输入的密码不一致.");
            return result;
        }
        
        String encryptPassword = MD5Utils.encode(password);
        Operator oper = this.operatorService.findByLoginName(currentOper.getLoginName());
        if (!oper.getPassword().equals(encryptPassword)) {
            result.setResult(false);
            result.setErrorMessage("原密码错误.");
            return result;
        }
        String newEncryptPassword = MD5Utils.encode(newPassword);
        this.operatorService.updatePasswordById(oper.getId(), newEncryptPassword);
        result.setResult(true);
        result.setData("mainframe/toMainframe.action");
        return result;
    }
    
    /**
      * 退出系统<br/>
      *<功能详细描述>
      * @return [参数说明]
      * 
      * @return String [返回类型说明]
      * @exception throws [异常类型] [异常说明]
      * @see [类、类#方法、类#成员]
     */
    @RequestMapping("/logout")
    public String logout() {
        HttpSession session = WebContextUtils.getSession(true);
        //使session失效
        session.invalidate();
        //退出到登录页面
        return "/mainframe/logout";
    }
    
    /**
      * 查询有权限的菜单<br/>
      * <功能详细描述>
      * @param menuItemId
      * @return [参数说明]
      * 
      * @return List<MenuItem> [返回类型说明]
      * @exception throws [异常类型] [异常说明]
      * @see [类、类#方法、类#成员]
     */
    @ResponseBody
    @RequestMapping("/queryMenuItemTreeListDependAuthority")
    public List<MenuItem> queryMenuItemTreeListDependAuthority(
            @RequestParam(value = "menuItemId", required = false) String menuItemId) {
        List<MenuItem> mainMenuItemTreeList = null;
        if (StringUtils.isEmpty(menuItemId)) {
            mainMenuItemTreeList = MenuContext.getContext()
                    .getMenuItemTreeListFromCurrentSession(MenuItem.TYPE_MAIN_MENU);
        } else {
            mainMenuItemTreeList = MenuContext.getContext()
                    .getMenuItemTreeListFromCurrentSession(MenuItem.TYPE_MAIN_MENU,
                            menuItemId)
                    .getChilds();
        }
        
        return mainMenuItemTreeList;
    }
    
    /**
      * 跳转到查询菜单列表页面
      *<功能详细描述>
      * @return [参数说明]
      * 
      * @return String [返回类型说明]
      * @exception throws [异常类型] [异常说明]
      * @see [类、类#方法、类#成员]
     */
    @RequestMapping("/toQueryAllMenuItemTreeList")
    public String toQueryAllMenuItemTreeList() {
        return "mainframe/queryAllMenuItemTreeList";
    }
    
    /**
      * 查询所有菜单列表<br/>
      *<功能详细描述>
      * @param menuItemId
      * @return [参数说明]
      * 
      * @return List<MenuItem> [返回类型说明]
      * @exception throws [异常类型] [异常说明]
      * @see [类、类#方法、类#成员]
     */
    @ResponseBody
    @RequestMapping("/getAllMenuItemList")
    public List<MenuItem> getAllMenuItemList() {
        List<MenuItem> mainMenuItemTreeList = null;
        mainMenuItemTreeList = MenuContext.getContext()
                .getAllMenuItemList(MenuItem.TYPE_MAIN_MENU);
        
        //mainMenuItemTreeList = TreeUtils.changToTree(mainMenuItemTreeList);
        return mainMenuItemTreeList;
    }
    
    /**
      * 获取系统当前时间
      *<功能详细描述>
      * @return [参数说明]
      * 
      * @return String [返回类型说明]
      * @exception throws [异常类型] [异常说明]
      * @see [类、类#方法、类#成员]
     */
    @ResponseBody
    @RequestMapping("/getDateTime")
    public String getDateTime() {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return df.format(new Date());
    }
    
}
