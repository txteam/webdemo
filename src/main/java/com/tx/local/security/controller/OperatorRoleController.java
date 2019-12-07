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

import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tx.component.role.RoleConstants;
import com.tx.component.role.context.RoleRegistry;
import com.tx.component.role.context.RoleTypeRegistry;
import com.tx.component.role.model.Role;
import com.tx.component.role.model.RoleType;
import com.tx.component.role.service.RoleRefService;
import com.tx.component.security.context.SecurityContext;
import com.tx.local.security.model.CheckAbleRole;
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
@Controller("roleController")
@RequestMapping({ "/role", "/operator/role" })
public class OperatorRoleController implements InitializingBean {
    
    @Resource
    private SecurityContext securityContext;
    
    private RoleTypeRegistry roleTypeRegistry;
    
    private RoleRegistry roleRegistry;
    
    private RoleRefService roleRefService;
    
    /**
     * @throws Exception
     */
    @Override
    public void afterPropertiesSet() throws Exception {
        this.roleRegistry = securityContext.getRoleRegistry();
        this.roleTypeRegistry = securityContext.getRoleTypeRegistry();
        this.roleRefService = securityContext.getRoleRefService();
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
    @RequestMapping("/toQueryRoleMain")
    public String toQueryRoleMain(ModelMap modelMap) {
        return "mainframe/queryRoleMain";
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
    @RequestMapping("/queryRoleTypeList")
    public List<RoleType> queryAuthTypeListBySecurity(
            @RequestParam(value = "roleTypeId", required = true) String roleTypeId) {
        List<RoleType> resList = roleTypeRegistry.queryList();
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
    @RequestMapping("/queryRoleList")
    public List<Role> queryRoleList(
            @RequestParam(value = "roleTypeId", required = true) String roleTypeId) {
        List<Role> resList = roleRegistry.queryList(roleTypeId);
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
    public List<Role> queryAuthListBySecurity(
            @RequestParam(value = "roleTypeId", required = true) String roleTypeId) {
        List<Role> resList = WebContextUtils.getCurrentRoles()
                .stream()
                .filter(a -> roleTypeId.equals(a.getRoleTypeId()))
                .collect(Collectors.toList());
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
    @RequestMapping("/toConfigOperatorRole")
    public String toConfigOperatorAuth(
            @RequestParam("roleTypeId") String roleTypeId,
            @RequestParam("operatorId") String operatorId, ModelMap modelMap) {
        modelMap.put("roleTypeId", roleTypeId);
        modelMap.put("operatorId", operatorId);
        
        return "security/configOperatorRole";
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
    @RequestMapping("/queryOperator2RoleList")
    public List<CheckAbleRole> queryOperator2AuthList(
            @RequestParam("roleTypeId") String roleTypeId,
            @RequestParam("operatorId") String operatorId,
            @RequestParam() MultiValueMap<String, String> request) {
        
        //获取可分配的权限，如果为超级管理员，则所有权限都可以进行分配，否则仅能分配自己拥有的权限
        List<String> assignableRoleIds = getAssignableRoleIds(roleTypeId);
        List<CheckAbleRole> resList = roleRegistry.queryList(roleTypeId)
                .stream()
                .filter(role -> assignableRoleIds.contains(role.getId()))
                .map(role -> new CheckAbleRole(role))
                .collect(Collectors.toList());
        
        //让拥有的权限被选中
        Set<String> hasRoleIdSet = this.roleRefService
                .queryListByRef(true,
                        RoleConstants.ROLEREFTYPE_OPERATOR,
                        operatorId,
                        null)
                .stream()
                .map(roleRef -> roleRef.getRoleId())
                .collect(Collectors.toSet());
        resList.stream().forEach(caAuth -> caAuth
                .setChecked(hasRoleIdSet.contains(caAuth.getId())));
        
        return resList;
    }
    
    /**
     * 保存角色权限<br/>
     * <功能详细描述>
     * @param roleTypeId
     * @param operatorId
     * @param roleIds
     * @return [参数说明]
     * 
     * @return boolean [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
    */
    @ResponseBody
    @RequestMapping("/saveOperator2Role")
    public boolean saveOperator2Role(
            @RequestParam("roleTypeId") String roleTypeId,
            @RequestParam("operatorId") String operatorId,
            @RequestParam(value = "roleId[]", required = false) String[] roleIds,
            @RequestParam() MultiValueMap<String, String> request) {
        List<String> roleIdList = null;
        if (roleIds == null) {
            roleIdList = new ArrayList<String>();
        } else {
            roleIdList = Arrays.asList(roleIds);
        }
        
        List<String> filterRoleIds = getAssignableRoleIds(roleTypeId);
        this.roleRefService.saveForRoleIds(RoleConstants.ROLEREFTYPE_OPERATOR,
                operatorId,
                roleIdList,
                filterRoleIds);
        return true;
    }
    
    /**
     * 获取可分配的权限，如果为超级管理员，则所有权限都可以进行分配，否则仅能分配自己拥有的权限
     * <功能详细描述>
     * @param roleTypeId
     * @return [参数说明]
     * 
     * @return List<String> [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    private List<String> getAssignableRoleIds(String roleTypeId) {
        List<String> resList = WebContextUtils.getCurrentRoles()
                .stream()
                .filter(role -> roleTypeId.equals(role.getRoleTypeId()))
                .map(role -> role.getId())
                .collect(Collectors.toList());
        return resList;
    }
}
