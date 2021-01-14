/*
 * 描          述:  <描述>
 * 修  改   人:  Administrator
 * 修改时间:  2019年11月26日
 * <修改描述:>
 */
package com.tx.local.clientinfo.client;

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
import com.tx.front4client.security.model.ClientRoleTypeEnum;
import com.tx.local.clientinfo.facade.ClientInfoFacade;
import com.tx.local.security.model.CheckAbleRole;
import com.tx.local.security.util.WebContextUtils;

/**
 * 人员对角色控制层<br/>
 *    注：在后台系统中由于/client已经被工作台鉴权拦截器拦截，如果使用/client/role则会在后台系统中造成accessDie的异常。
 *    //TODO: 需要利用该异常，定位ClientSecurity拦截器没有跳转到客户登陆页面的异常。后续解决。
 * <功能详细描述>
 * 
 * @author  Administrator
 * @version  [版本号, 2019年11月26日]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
@Controller("client2RoleController")
@RequestMapping({ "/client/role", "/client2role" })
public class Client2RoleController implements InitializingBean {
    
    @Resource
    private ClientInfoFacade clientInfoFacade;
    
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
    @RequestMapping("/toConfigClientRole")
    public String toConfigClientRole(
            @RequestParam(value = "roleTypeId", required = false) String roleTypeId,
            @RequestParam("clientId") String clientId, ModelMap modelMap) {
        modelMap.put("roleTypeId", roleTypeId);
        modelMap.put("clientId", clientId);
        
        return "security/configClientRole";
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
    @RequestMapping("/queryClient2RoleList")
    public List<CheckAbleRole> queryClient2RoleList(
            @RequestParam(value = "roleTypeId", required = false) String roleTypeId,
            @RequestParam("clientId") String clientId,
            @RequestParam() MultiValueMap<String, String> request) {
        List<CheckAbleRole> resList = roleRegistry
                .queryList(ClientRoleTypeEnum.ROLE_TYPE_CLIENT_ENUM.getId())
                .stream()
                .map(role -> new CheckAbleRole(role))
                .collect(Collectors.toList());
        //让拥有的权限被选中
        Set<String> hasRoleIdSet = this.roleRefService
                .queryListByRef(true,
                        RoleConstants.ROLEREFTYPE_CLIENT,
                        clientId,
                        null)
                .stream()
                .map(roleRef -> roleRef.getRoleId())
                .collect(Collectors.toSet());
        resList.stream()
                .forEach(caRole -> caRole
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
    @RequestMapping("/saveClient2Role")
    public boolean saveClient2Role(
            @RequestParam("roleTypeId") String roleTypeId,
            @RequestParam("clientId") String clientId,
            @RequestParam(value = "roleId[]", required = false) String[] roleIds,
            @RequestParam() MultiValueMap<String, String> request) {
        List<String> roleIdList = null;
        if (roleIds == null) {
            roleIdList = new ArrayList<String>();
        } else {
            roleIdList = Arrays.asList(roleIds);
        }
        
        List<String> filterRoleIds = getAssignableRoleIds(roleTypeId);
        this.roleRefService.saveForRoleIds(RoleConstants.ROLEREFTYPE_CLIENT,
                clientId,
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
    @RequestMapping("/toConfigRoleClient")
    public String toConfigRoleOperator(@RequestParam("roleId") String roleId,
            ModelMap response) {
        response.put("roleId", roleId);
        
        String vcid = WebContextUtils.getVcid();
        return "security/configRoleClient";
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
    @RequestMapping("/queryRole2ClientIds")
    public Set<String> queryRole2OperatorIdList(
            @RequestParam("roleId") String roleId,
            @RequestParam(value = "clientId[]", required = false) String[] clientIds,
            @RequestParam() MultiValueMap<String, String> request) {
        if (ArrayUtils.isEmpty(clientIds)) {
            return new HashSet<>();
        }
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("refIds", clientIds);
        
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
    @RequestMapping("/saveRole2Client")
    public boolean saveRole2Operator(@RequestParam("roleId") String roleId,
            @RequestParam(value = "clientId[]", required = false) String[] clientIds,
            @RequestParam(value = "filterOperatorId[]", required = false) String[] filterOperatorIds,
            @RequestParam() MultiValueMap<String, String> request) {
        List<String> refIds = ArrayUtils.isEmpty(clientIds) ? new ArrayList<>()
                : Arrays.asList(clientIds);
        List<String> filterRefIds = ArrayUtils.isEmpty(filterOperatorIds)
                ? new ArrayList<>()
                : Arrays.asList(filterOperatorIds);
        this.roleRefService.saveForRefIds(roleId,
                RoleConstants.ROLEREFTYPE_OPERATOR,
                refIds,
                filterRefIds);
        return true;
    }
}
