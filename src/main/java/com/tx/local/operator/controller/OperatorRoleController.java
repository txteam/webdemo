/*
 * 描       述:  <描述>
 * 修  改 人:  
 * 修改时间:
 * <修改描述:>
 */
package com.tx.local.operator.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tx.core.paged.model.PagedList;
import com.tx.local.operator.model.OperatorRole;
import com.tx.local.operator.service.OperatorRoleService;
import com.tx.local.security.util.WebContextUtils;

/**
 * 角色控制层<br/>
 * 
 * @author []
 * @version [版本号]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
@Controller
@RequestMapping("/operatorRole")
public class OperatorRoleController {
    
    //角色业务层
    @Resource(name = "operatorRoleService")
    private OperatorRoleService operatorRoleService;
    
    /**
     * 跳转到选择角色页面<br/>
     * <功能详细描述>
     * @return [参数说明]
     * 
     * @return String [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @RequestMapping("/toSelect")
    public String toSelect(
            @RequestParam(value = "eventName", required = true) String eventName,
            ModelMap responseMap) {
        responseMap.put("vcid", WebContextUtils.getVcid());
        responseMap.put("eventName", eventName);
        
        return "/operator/selectOperatorRole";
    }
    
    /**
     * 跳转到查询角色列表页面<br/>
     * <功能详细描述>
     * @return [参数说明]
     * 
     * @return String [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @RequestMapping("/toQueryList")
    public String toQueryList(ModelMap response) {
        response.put("vcid", WebContextUtils.getVcid());
        
        return "/operator/queryOperatorRoleList";
    }
    
    /**
     * 跳转到新增角色页面<br/>
     * <功能详细描述>
     * @return [参数说明]
     * 
     * @return String [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @RequestMapping("/toAdd")
    public String toAdd(
            @RequestParam(value = "parentId", required = false) String parentId,
            ModelMap response) {
        OperatorRole role = new OperatorRole();
        response.put("role", role);
        String vcid = WebContextUtils.getVcid();
        response.put("vcid", vcid);
        
        if (!StringUtils.isEmpty(parentId)) {
            OperatorRole parent = this.operatorRoleService.findById(parentId);
            if (parent != null && vcid.equals(parent.getVcid())) {
                //操作员虚中心id等于父级角色的虚中心id时才写入parent
                response.put("parent", parent);
            }
        }
        return "/operator/addOperatorRole";
    }
    
    /**
     * 跳转到编辑角色页面
     * <功能详细描述>
     * @return [参数说明]
     * 
     * @return String [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @RequestMapping("/toUpdate")
    public String toUpdate(@RequestParam("id") String id, ModelMap response) {
        OperatorRole operatorRole = this.operatorRoleService.findById(id);
        response.put("operatorRole", operatorRole);
        response.put("vcid", WebContextUtils.getVcid());
        
        if (operatorRole != null
                && !StringUtils.isEmpty(operatorRole.getParentId())) {
            OperatorRole parent = this.operatorRoleService
                    .findById(operatorRole.getParentId());
            response.put("parent", parent);
        }
        
        return "/operator/updateOperatorRole";
    }
    
    /**
     * 查询角色实例列表<br/>
     * <功能详细描述>
     * @return [参数说明]
     * 
     * @return List<OperatorRole> [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @ResponseBody
    @RequestMapping("/queryList")
    public List<OperatorRole> queryList(
            @RequestParam(value = "valid", required = false) Boolean valid,
            @RequestParam MultiValueMap<String, String> request) {
        Map<String, Object> params = new HashMap<>();
        params.put("vcid", WebContextUtils.getVcid());
        params.put("code", request.getFirst("code"));
        params.put("name", request.getFirst("name"));
        
        List<OperatorRole> resList = this.operatorRoleService.queryList(valid,
                params);
        
        return resList;
    }
    
    /**
     * 查询角色实例分页列表<br/>
     * <功能详细描述>
     * @return [参数说明]
     * 
     * @return List<OperatorRole> [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @ResponseBody
    @RequestMapping("/queryPagedList")
    public PagedList<OperatorRole> queryPagedList(
            @RequestParam(value = "valid", required = false) Boolean valid,
            @RequestParam(value = "pageNumber", required = false, defaultValue = "1") int pageIndex,
            @RequestParam(value = "pageSize", required = false, defaultValue = "10") int pageSize,
            @RequestParam MultiValueMap<String, String> request) {
        Map<String, Object> params = new HashMap<>();
        params.put("vcid", WebContextUtils.getVcid());
        params.put("code", request.getFirst("code"));
        params.put("name", request.getFirst("name"));
        
        PagedList<OperatorRole> resPagedList = this.operatorRoleService
                .queryPagedList(valid, params, pageIndex, pageSize);
        return resPagedList;
    }
    
    /**
     * 新增角色实例
     * <功能详细描述>
     * @param operatorRole [参数说明]
     * 
     * @return void [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @ResponseBody
    @RequestMapping("/add")
    public boolean add(OperatorRole operatorRole) {
        operatorRole.setVcid(WebContextUtils.getVcid());
        this.operatorRoleService.insert(operatorRole);
        return true;
    }
    
    /**
     * 更新角色实例<br/>
     * <功能详细描述>
     * @param operatorRole
     * @return [参数说明]
     * 
     * @return boolean [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @ResponseBody
    @RequestMapping("/update")
    public boolean update(OperatorRole operatorRole) {
        boolean flag = this.operatorRoleService.updateById(operatorRole);
        return flag;
    }
    
    /**
     * 根据主键查询角色实例<br/> 
     * <功能详细描述>
     * @param id
     * @return [参数说明]
     * 
     * @return boolean [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @ResponseBody
    @RequestMapping("/findById")
    public OperatorRole findById(@RequestParam(value = "id") String id) {
        OperatorRole operatorRole = this.operatorRoleService.findById(id);
        return operatorRole;
    }
    
    /**
     * 删除角色实例<br/> 
     * <功能详细描述>
     * @param id
     * @return [参数说明]
     * 
     * @return boolean [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @ResponseBody
    @RequestMapping("/deleteById")
    public boolean deleteById(@RequestParam(value = "id") String id) {
        boolean flag = this.operatorRoleService.deleteById(id);
        return flag;
    }
    
    /**
     * 禁用角色实例
     * @param id
     * @return [参数说明]
     * 
     * @return boolean [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @ResponseBody
    @RequestMapping("/disableById")
    public boolean disableById(@RequestParam(value = "id") String id) {
        boolean flag = this.operatorRoleService.disableById(id);
        return flag;
    }
    
    /**
     * 启用角色实例<br/>
     * <功能详细描述>
     * @param id
     * @return [参数说明]
     * 
     * @return boolean [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @ResponseBody
    @RequestMapping("/enableById")
    public boolean enableById(@RequestParam(value = "id") String id) {
        boolean flag = this.operatorRoleService.enableById(id);
        return flag;
    }
    
    /**
     * 校验是否重复<br/>
     * @param excludeId
     * @param params
     * @return [参数说明]
     * 
     * @return boolean [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @ResponseBody
    @RequestMapping("/validate")
    public Map<String, String> validate(
            @RequestParam(value = "id", required = false) String excludeId,
            @RequestParam Map<String, String> params) {
        ////角色的编码其实就是其ID，所有这里不用加入excludeId逻辑
        boolean flag = this.operatorRoleService.exists(params, null);
        
        Map<String, String> resMap = new HashMap<String, String>();
        if (!flag) {
            resMap.put("ok", "");
        } else {
            resMap.put("error", "重复值");
        }
        return resMap;
    }
    
    /**
     * 根据条件查询角色子级列表<br/>
     * <功能详细描述>
     * @param parentId
     * @param valid
     * @param request
     * @return [参数说明]
     * 
     * @return PagedList<T> [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @ResponseBody
    @RequestMapping("/queryChildren")
    public List<OperatorRole> queryChildren(
            @RequestParam(value = "parentId", required = true) String parentId,
            @RequestParam(value = "valid", required = false) Boolean valid,
            @RequestParam MultiValueMap<String, String> request) {
        Map<String, Object> params = new HashMap<>();
        params.put("vcid", WebContextUtils.getVcid());
        
        List<OperatorRole> resList = this.operatorRoleService
                .queryChildrenByParentId(parentId, valid, params);
        
        return resList;
    }
    
    /**
     * 根据条件查询角色子、孙级列表<br/>
     * <功能详细描述>
     * @param parentId
     * @param valid
     * @param request
     * @return [参数说明]
     * 
     * @return PagedList<T> [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @ResponseBody
    @RequestMapping("/queryDescendants")
    public List<OperatorRole> queryDescendants(
            @RequestParam(value = "parentId", required = true) String parentId,
            @RequestParam(value = "valid", required = false) Boolean valid,
            @RequestParam MultiValueMap<String, String> request) {
        Map<String, Object> params = new HashMap<>();
        params.put("vcid", WebContextUtils.getVcid());
        
        List<OperatorRole> resList = this.operatorRoleService
                .queryDescendantsByParentId(parentId, valid, params);
        
        return resList;
    }
    
}