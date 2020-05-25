/*
 * 描          述:  <描述>
 * 修  改   人:  Administrator
 * 修改时间:  2019年11月26日
 * <修改描述:>
 */
package com.tx.local.security.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import javax.annotation.Resource;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tx.component.role.RoleConstants;
import com.tx.component.role.context.RoleRegistry;
import com.tx.component.role.model.Role;
import com.tx.component.role.service.RoleRefService;
import com.tx.component.security.context.SecurityContext;
import com.tx.core.querier.model.QuerierBuilder;
import com.tx.local.operator.model.OperatorRoleEnum;
import com.tx.local.operator.service.OperatorService;
import com.tx.local.organization.facade.OrganizationFacade;
import com.tx.local.organization.facade.PostFacade;
import com.tx.local.security.model.CheckAbleRole;
import com.tx.local.security.model.RoleTypeEnum;
import com.tx.local.security.util.WebContextUtils;

/**
 * 人员对角色控制层<br/>
 * <功能详细描述>
 * 
 * @author  Administrator
 * @version  [版本号, 2019年11月26日]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
@Controller("operator2RoleController")
@RequestMapping({ "/operator/role" })
public class Operator2RoleController implements InitializingBean {
    
    @Resource
    private OperatorService operatorService;
    
    @Resource
    private OrganizationFacade organizationFacade;
    
    @Resource
    private PostFacade postFacade;
    
    @Resource
    private SecurityContext securityContext;
    
    private RoleRegistry roleRegistry;
    
    private RoleRefService roleRefService;
    
    /**
     * @throws Exception
     */
    @Override
    public void afterPropertiesSet() throws Exception {
        this.roleRegistry = securityContext.getRoleRegistry();
        this.roleRefService = securityContext.getRoleRefService();
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
    @RequestMapping("/queryRoleListBySecurity")
    public List<Role> queryRoleListBySecurity(
            @RequestParam(value = "roleTypeId", required = false) String roleTypeId) {
        List<Role> resList = WebContextUtils.getCurrentRoles()
                .stream()
                .collect(Collectors.toList());
        if (!StringUtils.isEmpty(roleTypeId)) {
            resList = resList.stream()
                    .filter(a -> roleTypeId.equals(a.getRoleTypeId()))
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
    @RequestMapping("/toConfigOperatorRole")
    public String toConfigOperatorRole(
            @RequestParam("operatorId") String operatorId, ModelMap modelMap) {
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
    public List<CheckAbleRole> queryOperator2RoleList(
            @RequestParam("operatorId") String operatorId,
            @RequestParam() MultiValueMap<String, String> request) {
        List<CheckAbleRole> resList = roleRegistry
                .queryList(RoleTypeEnum.ROLE_TYPE_OPERATOR.getId())
                .stream()
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
        resList.stream().forEach(caRole -> caRole
                .setChecked(hasRoleIdSet.contains(caRole.getId())));
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
    
    /**
     * 跳转到配置操作员权限列表页面<br/>
     * <功能详细描述>
     * @param response
     * @return [参数说明]
     * 
     * @return String [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @RequestMapping("/toConfigRoleOperator")
    public String toConfigRoleOperator(@RequestParam("roleId") String roleId,
            ModelMap response) {
        response.put("roleId", roleId);
        
        String vcid = WebContextUtils.getVcid();
        response.put("organizations",
                this.organizationFacade.queryList(true,
                        QuerierBuilder.newInstance()
                                .searchProperty("vcid", vcid)
                                .querier()));
        response.put("posts",
                this.postFacade.queryList(true,
                        QuerierBuilder.newInstance()
                                .searchProperty("vcid", vcid)
                                .querier()));
        return "security/configRoleOperator";
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
    @RequestMapping("/queryRole2OperatorIds")
    public Set<String> queryRole2OperatorIdList(
            @RequestParam("roleId") String roleId,
            @RequestParam(value = "operatorId[]", required = false) String[] operatorIds,
            @RequestParam() MultiValueMap<String, String> request) {
        if (ArrayUtils.isEmpty(operatorIds)) {
            return new HashSet<>();
        }
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("refIds", operatorIds);
        
        //让拥有的权限被选中
        Set<String> operatorIdSet = this.roleRefService
                .queryListByRoleId(true,
                        roleId,
                        RoleConstants.ROLEREFTYPE_OPERATOR,
                        params)
                .stream()
                .map(roleRef -> roleRef.getRefId())
                .collect(Collectors.toSet());
        return operatorIdSet;
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
    @RequestMapping("/saveRole2Operator")
    public boolean saveRole2Operator(@RequestParam("roleId") String roleId,
            @RequestParam(value = "operatorId[]", required = false) String[] operatorIds,
            @RequestParam(value = "filterOperatorId[]", required = false) String[] filterOperatorIds,
            @RequestParam() MultiValueMap<String, String> request) {
        List<String> refIds = ArrayUtils.isEmpty(operatorIds)
                ? new ArrayList<>() : Arrays.asList(operatorIds);
        List<String> filterRefIds = ArrayUtils.isEmpty(filterOperatorIds)
                ? new ArrayList<>() : Arrays.asList(filterOperatorIds);
        this.roleRefService.saveForRefIds(roleId,
                RoleConstants.ROLEREFTYPE_OPERATOR,
                refIds,
                filterRefIds);
        return true;
    }
    
    /**
     * 设置为系统管理员<br/>
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
    @RequestMapping("/setToAdmin")
    public boolean setToAdmin(
            @RequestParam(value = "operatorId", required = false) String operatorId,
            @RequestParam() MultiValueMap<String, String> request) {
        List<String> roleIdList = Arrays.asList(OperatorRoleEnum.ADMIN.getId());
        List<String> filterRoleIds = Arrays
                .asList(OperatorRoleEnum.ADMIN.getId());
        this.roleRefService.saveForRoleIds(RoleConstants.ROLEREFTYPE_OPERATOR,
                operatorId,
                roleIdList,
                filterRoleIds);
        this.operatorService.updateIsAdminById(operatorId, true);
        return true;
    }
    
    /**
     * 设置为非系统管理员<br/>
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
    @RequestMapping("/setToNotAdmin")
    public boolean setToNotAdmin(
            @RequestParam(value = "operatorId", required = false) String operatorId,
            @RequestParam() MultiValueMap<String, String> request) {
        List<String> roleIdList = new ArrayList<>();
        List<String> filterRoleIds = Arrays
                .asList(OperatorRoleEnum.ADMIN.getId());
        this.roleRefService.saveForRoleIds(RoleConstants.ROLEREFTYPE_OPERATOR,
                operatorId,
                roleIdList,
                filterRoleIds);
        this.operatorService.updateIsAdminById(operatorId, false);
        return true;
    }
}
