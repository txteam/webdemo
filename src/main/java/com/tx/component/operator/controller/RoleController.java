/*
 * 描 述: <描述> 修 改 人: 修改时间: <修改描述:>
 */
package com.tx.component.operator.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.EnumUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tx.component.auth.annotation.CheckOperateAuth;
import com.tx.component.mainframe.AuthConstants;
import com.tx.component.operator.model.Role;
import com.tx.component.operator.model.RoleTypeEnum;
import com.tx.component.operator.service.RoleService;
import com.tx.component.operator.service.VirtualCenterService;

/**
 * Role显示层逻辑<br/>
 * <功能详细描述>
 * 
 * @author PengQingyang
 * @version [版本号, 2013-8-27]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
@CheckOperateAuth(key = "role_manage", name = "role管理")
@Controller("roleController")
@RequestMapping("/role")
public class RoleController {
    
    @Resource(name = "roleService")
    private RoleService roleService;
    
    @Resource(name = "virtualCenterService")
    private VirtualCenterService virtualCenterService;
    
    /**
     * 跳转到查询Role列表页面<br/>
     * <功能详细描述>
     * 
     * @return [参数说明]
     * 
     * @return String [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @RequestMapping("/toQueryRoleList")
    public String toQueryRoleList() {
        return "/operator/queryRoleList";
    }
    
    /**
     * 跳转到添加Role页面<br/>
     * <功能详细描述>
     * 
     * @return [参数说明]
     * 
     * @return String [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @RequestMapping("/toAddRole")
    public String toAddRole(ModelMap response) {
        response.put("role", new Role());
        response.put("virtualCenterList", this.virtualCenterService.queryVirtualCenterByAuth(AuthConstants.ROLE_VC_DATA_AUTH));
        response.put("roleTypeList", EnumUtils.getEnumList(RoleTypeEnum.class));
        
        return "/operator/addRole";
    }
    
    /**
     * 跳转到编辑Role页面 <功能详细描述>
     * 
     * @return [参数说明]
     * 
     * @return String [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @RequestMapping("/toUpdateRole")
    public String toUpdateRole(@RequestParam("roleId") String roleId, ModelMap modelMap) {
        Role resRole = this.roleService.findById(roleId);
        modelMap.put("role", resRole);
        modelMap.put("virtualCenterList", this.virtualCenterService.queryVirtualCenterByAuth(AuthConstants.ROLE_VC_DATA_AUTH));
        modelMap.put("roleTypeList", EnumUtils.getEnumList(RoleTypeEnum.class));
        
        return "/operator/updateRole";
    }
    
    /**
     * 判断Role:vcid,name是否已经被使用 <功能详细描述>
     * 
     * @param uniqueGetterName
     * @param uniqueGetterName
     * @param code
     * @param excludeRoleId
     * @return [参数说明]
     * 
     * @return boolean [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @ResponseBody
    @RequestMapping("/validateVcidAndNameIsExist")
    public Map<String, String> validateVcidAndNameIsExist(@RequestParam MultiValueMap<String, String> request,
            @RequestParam("vcid") String vcid, @RequestParam("name") String name,
            @RequestParam(value = "id", required = false) String excludeRoleId) {
        Map<String, String> key2valueMap = new HashMap<String, String>();
        key2valueMap.put("vcid", vcid);
        key2valueMap.put("name", name);
        
        boolean flag = this.roleService.isExist(key2valueMap, excludeRoleId);
        
        Map<String, String> resMap = new HashMap<String, String>();
        if (!flag) {
            resMap.put("ok", "可用的role vcid,name");
        } else {
            resMap.put("error", "已经存在的role vcid,name");
        }
        return resMap;
    }
    
    /**
     * 校验vcid code是否已经被占用 <功能详细描述>
     * 
     * @param request
     * @param vcid
     * @param code
     * @param excludeRoleId
     * @return [参数说明]
     * 
     * @return Map<String,String> [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @ResponseBody
    @RequestMapping("/validateVcidAndCodeIsExist")
    public Map<String, String> validateVcidAndCodeIsExist(@RequestParam MultiValueMap<String, String> request,
            @RequestParam("vcid") String vcid, @RequestParam("code") String code,
            @RequestParam(value = "id", required = false) String excludeRoleId) {
        Map<String, String> key2valueMap = new HashMap<String, String>();
        key2valueMap.put("vcid", vcid);
        key2valueMap.put("code", code);
        
        boolean flag = this.roleService.isExist(key2valueMap, excludeRoleId);
        
        Map<String, String> resMap = new HashMap<String, String>();
        if (!flag) {
            resMap.put("ok", "可用的role vcid,name");
        } else {
            resMap.put("error", "已经存在的role vcid,name");
        }
        return resMap;
    }
    
    /**
     * 获取角色列表,包含无效的<br/>
     * <功能详细描述>
     * 
     * @return [参数说明]
     * 
     * @return List<Role> [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @ResponseBody
    @RequestMapping("/queryRoleListIncludeInvalid")
    public List<Role> queryRoleListIncludeInvalid(@RequestParam(value = "vcid", required = false) String vcid,
            @RequestParam(value = "name", required = false) String name, @RequestParam(value = "code", required = false) String code,
            @RequestParam(value = "roleType", required = false) RoleTypeEnum roleType,
            @RequestParam(value = "excludeRoleId", required = false) String excludeRoleId) {
        List<Role> resList = this.roleService.queryRoleListByAuth(AuthConstants.ROLE_VC_DATA_AUTH,
                vcid,
                name,
                code,
                null,
                roleType,
                excludeRoleId);
        return resList;
    }
    
    /**
     * 跳转到客户的人员角色界面 <功能简述> <功能详细描述>
     * 
     * @param clientinfoId
     * @param response
     * @return [参数说明]
     * 
     * @return String [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @RequestMapping("/toConfigClientinfoRole")
    public String toConfigClientinfoRole(@RequestParam("clientinfoId") String clientinfoId,
            @RequestParam("roleType") RoleTypeEnum roleType, ModelMap response) {
        response.put("clientinfoId", clientinfoId);
        response.put("roleType", roleType);
        return "/clientinfo/configClientinfoRole";
    }
    
    /**
     * 获取角色列表,包含有效的<br/>
     * <功能详细描述>
     * 
     * @param type
     * @return [参数说明]
     * 
     * @return List<Role> [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @ResponseBody
    @RequestMapping("/queryRoleList")
    public List<Role> queryRoleList(@RequestParam(value = "vcid", required = false) String vcid,
            @RequestParam(value = "name", required = false) String name, @RequestParam(value = "code", required = false) String code,
            @RequestParam(value = "roleType", required = false) RoleTypeEnum roleType,
            @RequestParam(value = "excludeRoleId", required = false) String excludeRoleId) {
        List<Role> resList = this.roleService.queryList(vcid, name, code, true, roleType, excludeRoleId);
        return resList;
    }
    
    /**
     * 添加组织结构页面 <功能详细描述>
     * 
     * @param organization [参数说明]
     * 
     * @return void [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @CheckOperateAuth(key = "add_role", name = "增加角色")
    @RequestMapping("/addRole")
    @ResponseBody
    public boolean addRole(Role role) {
        this.roleService.insert(role);
        return true;
    }
    
    /**
     * 更新组织<br/>
     * <功能详细描述>
     * 
     * @param role
     * @return [参数说明]
     * 
     * @return boolean [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @CheckOperateAuth(key = "update_role", name = "编辑角色")
    @RequestMapping("/updateRole")
    @ResponseBody
    public boolean updateRole(Role role) {
        this.roleService.updateById(role);
        return true;
    }
    
    /**
     * 删除指定Role<br/>
     * <功能详细描述>
     * 
     * @param roleId
     * @return [参数说明]
     * 
     * @return boolean [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @CheckOperateAuth(key = "delete_role", name = "删除角色")
    @ResponseBody
    @RequestMapping("/deleteRoleById")
    public boolean deleteRoleById(@RequestParam(value = "roleId") String roleId) {
        boolean resFlag = this.roleService.deleteById(roleId);
        return resFlag;
    }
    
    /**
     * 禁用Role
     * 
     * @param roleId
     * @return [参数说明]
     * 
     * @return boolean [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @CheckOperateAuth(key = "disable_role", name = "禁用角色")
    @ResponseBody
    @RequestMapping("/disableRoleById")
    public boolean disableRoleById(@RequestParam(value = "roleId") String roleId) {
        boolean resFlag = this.roleService.disableById(roleId);
        return resFlag;
    }
    
    /**
     * 启用Role<br/>
     * <功能详细描述>
     * 
     * @param roleId
     * @return [参数说明]
     * 
     * @return boolean [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @CheckOperateAuth(key = "enable_role", name = "启用角色")
    @ResponseBody
    @RequestMapping("/enableRoleById")
    public boolean enableRoleById(@RequestParam(value = "roleId") String roleId) {
        boolean resFlag = this.roleService.enableById(roleId);
        return resFlag;
    }
    
    /**
     * 启用Role<br/>
     * <功能详细描述>
     * 
     * @param roleId
     * @return [参数说明]
     * 
     * @return boolean [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @ResponseBody
    @RequestMapping("/isDeleteOrDisableAble")
    public boolean isDeleteOrDisableAble(@RequestParam(value = "roleId") String roleId) {
        boolean resFlag = this.roleService.isDeleteOrDisableAble(roleId);
        return resFlag;
    }
}
