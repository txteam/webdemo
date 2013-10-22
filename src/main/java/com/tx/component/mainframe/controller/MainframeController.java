/*
 * 描          述:  <描述>
 * 修  改   人:  PengQingyang
 * 修改时间:  2012-12-16
 * <修改描述:>
 */
package com.tx.component.mainframe.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.cxf.common.util.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
import com.tx.component.operator.model.Operator;
import com.tx.component.operator.model.Organization;
import com.tx.component.operator.service.OperatorService;
import com.tx.component.operator.service.OrganizationService;
import com.tx.component.servicelog.context.ServiceLoggerContext;
import com.tx.component.servicelog.logger.ServiceLogger;

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
    
    @SuppressWarnings("unused")
    private Logger logger = LoggerFactory.getLogger(MainframeController.class);
    
    @Resource(name = "operatorService")
    private OperatorService operatorService;
    
    @Resource(name = "organizationService")
    private OrganizationService organizationService;
    
    @Resource(name = "authContext")
    private AuthContext authContext;
    
    @RequestMapping("/toLogin")
    public String toLogin() {
        return "/mainframe/login";
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
    @RequestMapping("/login")
    public String login(@RequestParam("loginName") String loginName,
            @RequestParam("password") String password, Model model) {
        //登录人员
        Operator oper = this.operatorService.findOperatorByLoginName(loginName);
        if (oper == null) {
            model.addAttribute("errorMsg", "指定用户不存在.");
            return "/mainframe/login";
        }
        //用户是否被锁定
        if (!oper.isValid() || oper.isLocked()) {
            model.addAttribute("errorMsg", "指定用户已被锁定，请联系管理员进行解锁.");
            return "/mainframe/login";
        }
        
        //如果登录不成功继续返回登录页面
        if (!this.operatorService.checkPassword(loginName, password)) {
            model.addAttribute("errorMsg", "用户密码错误.请重新输入.");
            return "/mainframe/login";
        }
        
        model.addAttribute(WebContextUtils.SESSION_CURRENT_OPERATOR, oper);
        
        //调用权限容器登录句柄
        authContextLoginHandler(oper);
        
        //web容器登录句柄，负责向容器中写入需要的会话信息
        webContextLoginHandler(oper);
        
        //这个时候记录日志中信息没有写进需要手动写入
        ServiceLogger<LoginLog> serviceLogger =  ServiceLoggerContext.getLogger(LoginLog.class);
        serviceLogger.setAttribute("operatorId", oper.getId());
        serviceLogger.setAttribute("operatorName", oper.getUserName());
        serviceLogger.setAttribute("operatorLoginName", oper.getLoginName());
        serviceLogger.log(new LoginLog(
                "webdemo", LoginLog.LOGINTYPE_LOGIN, "操作员{}登录系统",
                new Object[] { loginName }));
        
        return "/mainframe/mainframe";
    }

    
     /** 
      *<功能简述>
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
        Organization currentOrg = this.organizationService.findOrganizationById(oper.getOrganization().getId());
        WebContextUtils.putOganizationInSession(currentOrg);
        
        
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
        //authSessionContext.initCurrentUserAuthContextWhenLogin("123456");//初始化用户权限到当前会话中 
        Map<String, String> refType2RefIdMapping = new HashMap<String, String>();
        refType2RefIdMapping.put(AuthConstant.AUTHREFTYPE_OPERATOR, "123456");
        refType2RefIdMapping.put(AuthConstant.AUTHREFTYPE_OPERATOR_TEMPORARY,
                "123456");
        //refType2RefIdMapping.put(AuthConstant.AUTHREFTYPE_POST, postId);
        //refType2RefIdMapping.put(AuthConstant.AUTHREFTYPE_ORGANIZATION, loginOrganization.getId());
        authContext.login(refType2RefIdMapping);
        //修改权限项记录日志会用到对应值
        AuthSessionContext.putOperatorIdToSession(oper.getId());
    }
    
    /**
      * 查询有权限的菜单<br/>
      *<功能详细描述>
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
}
