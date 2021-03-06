/*
 * 描       述:  <描述>
 * 修  改 人:  
 * 修改时间:
 * <修改描述:>
 */
package com.tx.local.organization.controller;

import java.util.Arrays;
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
import com.tx.local.organization.model.Organization;
import com.tx.local.organization.model.OrganizationTypeEnum;
import com.tx.local.organization.service.OrganizationService;
import com.tx.local.security.util.WebContextUtils;
import com.tx.local.vitualcenter.facade.VirtualCenterFacade;

/**
 * 组织控制层<br/>
 * 
 * @author []
 * @version [版本号]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
@Controller
@RequestMapping("/organization")
public class OrganizationController {
    
    //组织业务层
    @Resource(name = "organizationService")
    private OrganizationService organizationService;
    
    //虚中心业务层
    @Resource
    private VirtualCenterFacade virtualCenterFacade;
    
    /**
     * 跳转到查询组织列表页面<br/>
     * <功能详细描述>
     * @return [参数说明]
     * 
     * @return String [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @RequestMapping("/toQueryList")
    public String toQueryList(ModelMap response) {
        response.put("types", OrganizationTypeEnum.values());
        response.put("vcid", WebContextUtils.getVcid());
        
        return "organization/queryOrganizationList";
    }
    
    /**
     * 跳转到新增组织页面<br/>
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
        Organization org = new Organization();
        org.setVcid(WebContextUtils.getVcid());
        
        response.put("types", OrganizationTypeEnum.values());
        if (!StringUtils.isEmpty(parentId)) {
            Organization parent = this.organizationService.findById(parentId);
            response.put("parent", parent);
            
            //避免在操作期间出现VCID异常变动的情况
            if (parent != null
                    && !parent.getVcid().equals(WebContextUtils.getVcid())) {
                org.setParentId(null);
            }
        }
        response.put("organization", org);
        
        return "organization/addOrganization";
    }
    
    /**
     * 跳转到编辑组织页面
     * <功能详细描述>
     * @return [参数说明]
     * 
     * @return String [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @RequestMapping("/toUpdate")
    public String toUpdate(@RequestParam("id") String id, ModelMap response) {
        Organization organization = this.organizationService.findById(id);
        response.put("organization", organization);
        
        response.put("types",
                Arrays.asList(OrganizationTypeEnum.DEPARTMENT,
                        OrganizationTypeEnum.BRANCH_DEPARTMENT,
                        OrganizationTypeEnum.GROUP));
        if (!StringUtils.isEmpty(organization.getParentId())) {
            Organization parent = this.organizationService
                    .findById(organization.getParentId());
            response.put("parent", parent);
        }
        
        return "organization/updateOrganization";
    }
    
    /**
     * 跳转到选择组织页面
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
        
        return "organization/selectOrganization";
    }
    
    /**
     * 查询组织实例列表<br/>
     * <功能详细描述>
     * @return [参数说明]
     * 
     * @return List<Organization> [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @ResponseBody
    @RequestMapping("/queryList")
    public List<Organization> queryList(
            @RequestParam(value = "valid", required = false) Boolean valid,
            @RequestParam MultiValueMap<String, String> request) {
        Map<String, Object> params = new HashMap<>();
        params.put("vcid", WebContextUtils.getVcid());
        
        List<Organization> resList = this.organizationService.queryList(valid,
                params);
        return resList;
    }
    
    /**
     * 查询组织实例分页列表<br/>
     * <功能详细描述>
     * @return [参数说明]
     * 
     * @return List<Organization> [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @ResponseBody
    @RequestMapping("/queryPagedList")
    public PagedList<Organization> queryPagedList(
            @RequestParam(value = "valid", required = false) Boolean valid,
            @RequestParam(value = "pageNumber", required = false, defaultValue = "1") int pageIndex,
            @RequestParam(value = "pageSize", required = false, defaultValue = "10") int pageSize,
            @RequestParam MultiValueMap<String, String> request) {
        Map<String, Object> params = new HashMap<>();
        params.put("vcid", WebContextUtils.getVcid());
        
        PagedList<Organization> resPagedList = this.organizationService
                .queryPagedList(valid, params, pageIndex, pageSize);
        return resPagedList;
    }
    
    /**
     * 新增组织实例
     * <功能详细描述>
     * @param organization [参数说明]
     * 
     * @return void [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @ResponseBody
    @RequestMapping("/add")
    public boolean add(Organization organization) {
        organization.setVcid(WebContextUtils.getVcid());
        if (!StringUtils.isEmpty(organization.getParentId())) {
            Organization parent = this.organizationService
                    .findById(organization.getParentId());
            //避免在操作期间出现VCID异常变动的情况
            if (!parent.getVcid().equals(WebContextUtils.getVcid())) {
                organization.setParentId(null);
            }
        }
        this.organizationService.insert(organization);
        return true;
    }
    
    /**
     * 更新组织实例<br/>
     * <功能详细描述>
     * @param organization
     * @return [参数说明]
     * 
     * @return boolean [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @ResponseBody
    @RequestMapping("/update")
    public boolean update(Organization organization) {
        boolean flag = this.organizationService.updateById(organization);
        return flag;
    }
    
    /**
     * 根据主键查询组织实例<br/> 
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
    public Organization findById(@RequestParam(value = "id") String id) {
        Organization organization = this.organizationService.findById(id);
        return organization;
    }
    
    /**
     * 根据编码查询组织实例<br/> 
     * <功能详细描述>
     * @param code
     * @return [参数说明]
     * 
     * @return boolean [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @ResponseBody
    @RequestMapping("/findByCode")
    public Organization findByCode(@RequestParam(value = "code") String code) {
        Organization organization = this.organizationService.findByCode(code);
        return organization;
    }
    
    /**
     * 删除组织实例<br/> 
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
        boolean flag = this.organizationService.deleteById(id);
        return flag;
    }
    
    /**
     * 禁用组织实例
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
        boolean flag = this.organizationService.disableById(id);
        return flag;
    }
    
    /**
     * 启用组织实例<br/>
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
        boolean flag = this.organizationService.enableById(id);
        return flag;
    }
    
    /**
     * 校验参数对应实例是否重复
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
            @RequestParam(value = "id", required = false) String id,
            @RequestParam Map<String, String> params) {
        params.remove("id");
        boolean flag = this.organizationService.exists(params, id);
        
        Map<String, String> resMap = new HashMap<String, String>();
        if (!flag) {
            resMap.put("ok", "");
        } else {
            resMap.put("error", "重复值");
        }
        return resMap;
    }
    
    /**
     * 是否可编辑<br/>
     * <功能详细描述>
     * @param id
     * @return [参数说明]
     * 
     * @return boolean [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @ResponseBody
    @RequestMapping("/modifyAble")
    public boolean modifyAble(@RequestParam(value = "id") String id) {
        boolean flag = this.organizationService.modifyAble(id);
        return flag;
    }
    
    /**
     * 根据条件查询组织结构子级列表<br/>
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
    public List<Organization> queryChildren(
            @RequestParam(value = "parentId", required = true) String parentId,
            @RequestParam(value = "valid", required = false) Boolean valid,
            @RequestParam MultiValueMap<String, String> request) {
        Map<String, Object> params = new HashMap<>();
        params.put("vcid", WebContextUtils.getVcid());
        
        List<Organization> resList = this.organizationService
                .queryChildrenByParentId(parentId, valid, params);
        
        return resList;
    }
    
    /**
     * 根据条件查询组织结构子、孙级列表<br/>
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
    public List<Organization> queryDescendants(
            @RequestParam(value = "parentId", required = true) String parentId,
            @RequestParam(value = "valid", required = false) Boolean valid,
            @RequestParam MultiValueMap<String, String> request) {
        Map<String, Object> params = new HashMap<>();
        params.put("vcid", WebContextUtils.getVcid());
        
        List<Organization> resList = this.organizationService
                .queryDescendantsByParentId(parentId, valid, params);
        
        return resList;
    }
    
}