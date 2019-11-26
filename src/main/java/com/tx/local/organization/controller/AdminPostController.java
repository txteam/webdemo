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

import com.tx.local.organization.model.Organization;
import com.tx.local.organization.model.Post;
import com.tx.local.organization.service.OrganizationService;
import com.tx.local.organization.service.PostService;
import com.tx.local.security.util.WebContextUtils;
import com.tx.local.vitualcenter.facade.VirtualCenterFacade;
import com.tx.core.paged.model.PagedList;

/**
 * 职位控制层<br/>
 * 
 * @author []
 * @version [版本号]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
@Controller
@RequestMapping("/admin/post")
public class AdminPostController {
    
    //职位业务层
    @Resource(name = "postService")
    private PostService postService;
    
    //操作员所属组织业务层
    @Resource
    private OrganizationService organizationService;
    
    //虚中心业务层
    @Resource
    private VirtualCenterFacade virtualCenterFacade;
    
    /**
     * 跳转到查询职位列表页面<br/>
     * <功能详细描述>
     * @return [参数说明]
     * 
     * @return String [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @RequestMapping("/toQueryList")
    public String toQueryList(ModelMap response) {
        response.put("vcid", WebContextUtils.getVcidBySecurity());
        
        return "organization/admin/queryPostList";
    }
    
    /**
     * 跳转到新增职位页面<br/>
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
            @RequestParam(value = "organizationId", required = false) String organizationId,
            ModelMap response) {
        Post op = new Post();
        
        op.setParentId(parentId);
        if (!StringUtils.isEmpty(organizationId)) {
            Organization org = this.organizationService
                    .findById(organizationId);
            op.setOrganizationId(organizationId);
            op.setVcid(org.getVcid());
            response.put("organization", org);
        }
        if (!StringUtils.isEmpty(parentId)) {
            Post parent = this.postService.findById(parentId);
            response.put("parent", parent);
        }
        response.put("post", op);
        
        return "organization/admin/addPost";
    }
    
    /**
     * 跳转到编辑职位页面
     * <功能详细描述>
     * @return [参数说明]
     * 
     * @return String [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @RequestMapping("/toUpdate")
    public String toUpdate(@RequestParam("id") String id, ModelMap response) {
        Post post = this.postService.findById(id);
        response.put("post", post);
        
        return "organization/admin/updatePost";
    }
    
    /**
     * 查询职位实例列表<br/>
     * <功能详细描述>
     * @return [参数说明]
     * 
     * @return List<Post> [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @ResponseBody
    @RequestMapping("/queryList")
    public List<Post> queryList(
            @RequestParam(value = "valid", required = false) Boolean valid,
            @RequestParam MultiValueMap<String, String> request) {
        Map<String, Object> params = new HashMap<>();
        params.put("vcid", WebContextUtils.getVcidBySecurity());
        
        List<Post> resList = this.postService.queryList(valid, params);
        
        return resList;
    }
    
    /**
     * 查询职位实例分页列表<br/>
     * <功能详细描述>
     * @return [参数说明]
     * 
     * @return List<Post> [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @ResponseBody
    @RequestMapping("/queryPagedList")
    public PagedList<Post> queryPagedList(
            @RequestParam(value = "valid", required = false) Boolean valid,
            @RequestParam(value = "pageNumber", required = false, defaultValue = "1") int pageIndex,
            @RequestParam(value = "pageSize", required = false, defaultValue = "10") int pageSize,
            @RequestParam MultiValueMap<String, String> request) {
        Map<String, Object> params = new HashMap<>();
        params.put("vcid", WebContextUtils.getVcidBySecurity());
        
        PagedList<Post> resPagedList = this.postService.queryPagedList(valid,
                params,
                pageIndex,
                pageSize);
        return resPagedList;
    }
    
    /**
     * 新增职位实例
     * <功能详细描述>
     * @param post [参数说明]
     * 
     * @return void [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @ResponseBody
    @RequestMapping("/add")
    public boolean add(Post post) {
        Organization org = this.organizationService
                .findById(post.getOrganizationId());
        post.setVcid(org.getVcid());
        
        this.postService.insert(post);
        return true;
    }
    
    /**
     * 更新职位实例<br/>
     * <功能详细描述>
     * @param post
     * @return [参数说明]
     * 
     * @return boolean [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @ResponseBody
    @RequestMapping("/update")
    public boolean update(Post post) {
        boolean flag = this.postService.updateById(post);
        return flag;
    }
    
}