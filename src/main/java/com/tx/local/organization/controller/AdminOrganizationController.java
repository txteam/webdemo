/*
 * 描       述:  <描述>
 * 修  改 人:  
 * 修改时间:
 * <修改描述:>
 */
package com.tx.local.organization.controller;

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
import com.tx.local.vitualcenter.model.VirtualCenter;

/**
 * 组织控制层<br/>
 * 
 * @author []
 * @version [版本号]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
@Controller
@RequestMapping("/admin/organization")
public class AdminOrganizationController {
    
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
        response.put("vcid", WebContextUtils.getVcidBySecurity());
        
        return "/organization/admin/queryOrganizationList";
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
        
        response.put("types", OrganizationTypeEnum.values());
        response.put("virtualCenterList",
                this.virtualCenterFacade.queryList(true, null));
        
        if (!StringUtils.isEmpty(parentId)) {
            Organization parent = this.organizationService.findById(parentId);
            response.put("parent", parent);
            org.setVcid(parent.getVcid());
        } else {
            org.setVcid(WebContextUtils.getVcidBySecurity());
        }
        response.put("organization", org);
        
        return "/organization/admin/addOrganization";
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
        response.put("types", OrganizationTypeEnum.values());
        response.put("vcid", WebContextUtils.getVcidBySecurity());
        
        if (!StringUtils.isEmpty(organization.getParentId())) {
            Organization parent = this.organizationService
                    .findById(organization.getParentId());
            response.put("parent", parent);
        }
        if (!StringUtils.isEmpty(organization.getVcid())) {
            VirtualCenter vc = this.virtualCenterFacade
                    .findById(organization.getVcid());
            response.put("vc", vc);
        }
        
        return "/organization/admin/updateOrganization";
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
            @RequestParam(value = "vcid", required = false) String vcid,
            @RequestParam(value = "valid", required = false) Boolean valid,
            @RequestParam MultiValueMap<String, String> request) {
        Map<String, Object> params = new HashMap<>();
        params.put("vcid", vcid);
        
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
            @RequestParam(value = "vcid", required = false) String vcid,
            @RequestParam(value = "valid", required = false) Boolean valid,
            @RequestParam(value = "pageNumber", required = false, defaultValue = "1") int pageIndex,
            @RequestParam(value = "pageSize", required = false, defaultValue = "10") int pageSize,
            @RequestParam MultiValueMap<String, String> request) {
        Map<String, Object> params = new HashMap<>();
        params.put("vcid", vcid);
        
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
        if (StringUtils.isEmpty(organization.getVcid())) {
            organization.setVcid(WebContextUtils.getVcidBySecurity());
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
}