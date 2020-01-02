/*
 * 描          述:  <描述>
 * 修  改   人:  Administrator
 * 修改时间:  2019年11月26日
 * <修改描述:>
 */
package com.tx.local.security.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tx.component.auth.AuthConstants;
import com.tx.component.auth.context.AuthRegistry;
import com.tx.component.auth.context.AuthTypeRegistry;
import com.tx.component.auth.model.Auth;
import com.tx.component.auth.model.AuthType;
import com.tx.component.auth.service.AuthRefService;
import com.tx.component.security.context.SecurityContext;
import com.tx.local.security.model.CheckAbleAuth;
import com.tx.local.security.util.WebContextUtils;

/**
 * 权限显示层<br/>
 * <功能详细描述>
 * 
 * @author  Administrator
 * @version  [版本号, 2019年11月26日]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
@Controller("operator2AuthController")
@RequestMapping({ "/operator/auth" })
public class Operator2AuthController implements InitializingBean {
    
    @Resource
    private SecurityContext securityContext;
    
    private AuthTypeRegistry authTypeRegistry;
    
    private AuthRegistry authRegistry;
    
    private AuthRefService authRefService;
    
    /**
     * @throws Exception
     */
    @Override
    public void afterPropertiesSet() throws Exception {
        this.authRegistry = securityContext.getAuthRegistry();
        this.authTypeRegistry = securityContext.getAuthTypeRegistry();
        this.authRefService = securityContext.getAuthRefService();
    }
    
    /**
     * 跳转到查询权限视图<br/>
     * <功能详细描述>
     * @return [参数说明]
     * 
     * @return String [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @RequestMapping("/toQueryAuthMain")
    public String toQueryAuthView(ModelMap modelMap) {
        return "security/queryAuthMain";
    }
    
    /**
     * 根据当前人员权限类型与权限列表<br/> 
     * <功能详细描述>
     * @return [参数说明]
     * 
     * @return List<AuthItem> [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @ResponseBody
    @RequestMapping("/queryAuthTypeList")
    public List<AuthType> queryAuthTypeListBySecurity() {
        List<AuthType> resList = authTypeRegistry.queryList();
        return resList;
    }
    
    /**
     * 根据当前人员权限类型与权限列表<br/> 
     * <功能详细描述>
     * @return [参数说明]
     * 
     * @return List<AuthItem> [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @ResponseBody
    @RequestMapping("/queryAuthList")
    public List<Auth> queryAuthList(
            @RequestParam(value = "authTypeId", required = true) String authTypeId) {
        List<Auth> resList = authRegistry.queryList(authTypeId);
        return resList;
    }
    
    /**
     * 根据当前人员权限类型与权限列表<br/> 
     * <功能详细描述>
     * @return [参数说明]
     * 
     * @return List<AuthItem> [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @ResponseBody
    @RequestMapping("/queryAuthListBySecurity")
    public List<Auth> queryAuthListBySecurity(
            @RequestParam(value = "authTypeId", required = false) String authTypeId) {
        List<Auth> resList = WebContextUtils.getCurrentAuths()
                .stream()
                .collect(Collectors.toList());
        if (!StringUtils.isEmpty(authTypeId)) {
            resList = WebContextUtils.getCurrentAuths()
                    .stream()
                    .filter(a -> authTypeId.equals(a.getAuthTypeId()))
                    .collect(Collectors.toList());
        }
        return resList;
    }
    
    /**
     * 跳转到配置操作员权限列表页面<br/>
     *<功能详细描述>
     * @param modelMap
     * @return [参数说明]
     * 
     * @return String [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @RequestMapping("/toConfigOperatorAuth")
    public String toConfigOperatorAuth(
            @RequestParam("authTypeId") String authTypeId,
            @RequestParam("operatorId") String operatorId, ModelMap modelMap) {
        modelMap.put("authTypeId", authTypeId);
        modelMap.put("operatorId", operatorId);
        
        return "security/configOperatorAuth";
    }
    
    /**
     * 根据当前人员权限类型与权限列表<br/> 
     *<功能详细描述>
     * @return [参数说明]
     * 
     * @return List<AuthItem> [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @ResponseBody
    @RequestMapping("/queryOperator2AuthList")
    public List<CheckAbleAuth> queryOperator2AuthList(
            @RequestParam("authTypeId") String authTypeId,
            @RequestParam("operatorId") String operatorId,
            @RequestParam() MultiValueMap<String, String> request) {
        
        //获取可分配的权限，如果为超级管理员，则所有权限都可以进行分配，否则仅能分配自己拥有的权限
        List<String> assignableAuthIds = getAssignableAuthIds(authTypeId);
        List<CheckAbleAuth> resList = authRegistry.queryList(authTypeId)
                .stream()
                .filter(auth -> assignableAuthIds.contains(auth.getId()))
                .map(auth -> new CheckAbleAuth(auth))
                .collect(Collectors.toList());
        
        //让拥有的权限被选中
        Set<String> hasAuthIdSet = this.authRefService
                .queryListByRef(true,
                        AuthConstants.AUTHREFTYPE_OPERATOR,
                        operatorId,
                        null)
                .stream()
                .map(authRef -> authRef.getAuthId())
                .collect(Collectors.toSet());
        resList.stream().forEach(caAuth -> caAuth
                .setChecked(hasAuthIdSet.contains(caAuth.getId())));
        
        return resList;
    }
    
    /**
     * 保存角色权限<br/>
     *<功能详细描述>
     * @param postId
     * @param authType
     * @param authItemIds
     * @return [参数说明]
     * 
     * @return boolean [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
    */
    @ResponseBody
    @RequestMapping("/saveOperator2Auth")
    public boolean saveOperator2Auth(
            @RequestParam("authTypeId") String authTypeId,
            @RequestParam("operatorId") String operatorId,
            @RequestParam(value = "authId[]", required = false) String[] authIds,
            @RequestParam() MultiValueMap<String, String> request) {
        List<String> authIdList = null;
        if (authIds == null) {
            authIdList = new ArrayList<String>();
        } else {
            authIdList = Arrays.asList(authIds);
        }
        
        List<String> filterAuthIds = getAssignableAuthIds(authTypeId);
        this.authRefService.saveForAuthIds(AuthConstants.AUTHREFTYPE_OPERATOR,
                operatorId,
                authIdList,
                filterAuthIds);
        return true;
    }
    
    /**
     * 跳转到配置操作员权限列表页面<br/>
     *<功能详细描述>
     * @param modelMap
     * @return [参数说明]
     * 
     * @return String [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @RequestMapping("/toConfigPostAuth")
    public String toConfigPostAuth(
            @RequestParam("authTypeId") String authTypeId,
            @RequestParam("postId") String postId, ModelMap modelMap) {
        modelMap.put("authTypeId", authTypeId);
        modelMap.put("postId", postId);
        
        return "security/configPostAuth";
    }
    
    /**
     * 根据当前人员权限类型与权限列表<br/> 
     *<功能详细描述>
     * @return [参数说明]
     * 
     * @return List<AuthItem> [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @ResponseBody
    @RequestMapping("/queryPost2AuthList")
    public List<CheckAbleAuth> queryPost2AuthList(
            @RequestParam("authTypeId") String authTypeId,
            @RequestParam("postId") String postId,
            @RequestParam() MultiValueMap<String, String> request) {
        
        //获取可分配的权限，如果为超级管理员，则所有权限都可以进行分配，否则仅能分配自己拥有的权限
        List<String> assignableAuthIds = getAssignableAuthIds(authTypeId);
        List<CheckAbleAuth> resList = authRegistry.queryList(authTypeId)
                .stream()
                .filter(auth -> assignableAuthIds.contains(auth.getId()))
                .map(auth -> new CheckAbleAuth(auth))
                .collect(Collectors.toList());
        
        //让拥有的权限被选中
        Set<String> hasAuthIdSet = this.authRefService
                .queryListByRef(true,
                        AuthConstants.AUTHREFTYPE_POST,
                        postId,
                        null)
                .stream()
                .map(authRef -> authRef.getAuthId())
                .collect(Collectors.toSet());
        resList.stream().forEach(caAuth -> caAuth
                .setChecked(hasAuthIdSet.contains(caAuth.getId())));
        
        return resList;
    }
    
    /**
     * 保存角色权限<br/>
     *<功能详细描述>
     * @param postId
     * @param authType
     * @param authItemIds
     * @return [参数说明]
     * 
     * @return boolean [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
    */
    @ResponseBody
    @RequestMapping("/savePost2Auth")
    public boolean savePost2Auth(@RequestParam("authTypeId") String authTypeId,
            @RequestParam("postId") String postId,
            @RequestParam(value = "authId[]", required = false) String[] authIds,
            @RequestParam() MultiValueMap<String, String> request) {
        List<String> authIdList = null;
        if (authIds == null) {
            authIdList = new ArrayList<String>();
        } else {
            authIdList = Arrays.asList(authIds);
        }
        
        List<String> filterAuthIds = getAssignableAuthIds(authTypeId);
        this.authRefService.saveForAuthIds(AuthConstants.AUTHREFTYPE_POST,
                postId,
                authIdList,
                filterAuthIds);
        return true;
    }
    
    /**
     * 跳转到配置操作员权限列表页面<br/>
     *<功能详细描述>
     * @param modelMap
     * @return [参数说明]
     * 
     * @return String [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @RequestMapping("/toConfigRoleAuth")
    public String toConfigRoleAuth(
            @RequestParam("authTypeId") String authTypeId,
            @RequestParam("roleId") String roleId, ModelMap modelMap) {
        modelMap.put("authTypeId", authTypeId);
        modelMap.put("roleId", roleId);
        
        return "security/configRoleAuth";
    }
    
    /**
     * 根据当前人员权限类型与权限列表<br/> 
     *<功能详细描述>
     * @return [参数说明]
     * 
     * @return List<AuthItem> [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @ResponseBody
    @RequestMapping("/queryRole2AuthList")
    public List<CheckAbleAuth> queryRole2AuthList(
            @RequestParam("authTypeId") String authTypeId,
            @RequestParam("roleId") String roleId,
            @RequestParam() MultiValueMap<String, String> request) {
        
        //获取可分配的权限，如果为超级管理员，则所有权限都可以进行分配，否则仅能分配自己拥有的权限
        List<String> assignableAuthIds = getAssignableAuthIds(authTypeId);
        List<CheckAbleAuth> resList = authRegistry.queryList(authTypeId)
                .stream()
                .filter(auth -> assignableAuthIds.contains(auth.getId()))
                .map(auth -> new CheckAbleAuth(auth))
                .collect(Collectors.toList());
        
        //让拥有的权限被选中
        Set<String> hasAuthIdSet = this.authRefService
                .queryListByRef(true,
                        AuthConstants.AUTHREFTYPE_ROLE,
                        roleId,
                        null)
                .stream()
                .map(authRef -> authRef.getAuthId())
                .collect(Collectors.toSet());
        resList.stream().forEach(caAuth -> caAuth
                .setChecked(hasAuthIdSet.contains(caAuth.getId())));
        
        return resList;
    }
    
    /**
     * 保存角色权限<br/>
     *<功能详细描述>
     * @param postId
     * @param authType
     * @param authItemIds
     * @return [参数说明]
     * 
     * @return boolean [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
    */
    @ResponseBody
    @RequestMapping("/saveRole2Auth")
    public boolean saveRole2Auth(@RequestParam("authTypeId") String authTypeId,
            @RequestParam("roleId") String roleId,
            @RequestParam(value = "authId[]", required = false) String[] authIds,
            @RequestParam() MultiValueMap<String, String> request) {
        List<String> authIdList = null;
        if (authIds == null) {
            authIdList = new ArrayList<String>();
        } else {
            authIdList = Arrays.asList(authIds);
        }
        
        List<String> filterAuthIds = getAssignableAuthIds(authTypeId);
        this.authRefService.saveForAuthIds(AuthConstants.AUTHREFTYPE_ROLE,
                roleId,
                authIdList,
                filterAuthIds);
        return true;
    }
    
    /**
     * 获取可分配的权限，如果为超级管理员，则所有权限都可以进行分配，否则仅能分配自己拥有的权限
     * <功能详细描述>
     * @param authTypeId
     * @return [参数说明]
     * 
     * @return List<String> [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    private List<String> getAssignableAuthIds(String authTypeId) {
        List<String> resList = WebContextUtils.getCurrentAuths()
                .stream()
                .filter(auth -> authTypeId.equals(auth.getAuthTypeId()))
                .map(auth -> auth.getId())
                .collect(Collectors.toList());
        return resList;
    }
}
