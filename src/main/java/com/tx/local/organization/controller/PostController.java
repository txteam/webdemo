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
@RequestMapping("/post")
public class PostController {
    
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
     * 跳转到选择职位页面<br/>
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
        
        return "/organization/selectPost";
    }
    
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
    public String toQueryList(
            @RequestParam(value = "organizationId", required = false) String organizationId,
            ModelMap response) {
        response.put("vcid", WebContextUtils.getVcid());
        response.put("organizationId", organizationId);
        
        return "organization/queryPostList";
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
        Organization org = null;
        if (!StringUtils.isEmpty(organizationId)) {
            org = this.organizationService.findById(organizationId);
            op.setOrganizationId(organizationId);
            op.setVcid(org.getVcid());
        }
        if (!StringUtils.isEmpty(parentId)) {
            Post parent = this.postService.findById(parentId);
            response.put("parent", parent);
            
            if (parent != null && org == null) {
                org = this.organizationService
                        .findById(parent.getOrganizationId());
            }
        }
        
        response.put("organization", org);
        response.put("post", op);
        
        return "organization/addPost";
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
        
        if (post != null && !StringUtils.isEmpty(post.getOrganizationId())) {
            Organization org = this.organizationService
                    .findById(post.getOrganizationId());
            response.put("organization", org);
        }
        if (post != null && !StringUtils.isEmpty(post.getParentId())) {
            Post parent = this.postService.findById(post.getParentId());
            response.put("parent", parent);
        }
        
        response.put("post", post);
        
        return "organization/updatePost";
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
            @RequestParam(value = "organizationId", required = false) String organizationId,
            @RequestParam(value = "valid", required = false) Boolean valid,
            @RequestParam MultiValueMap<String, String> request) {
        Map<String, Object> params = new HashMap<>();
        params.put("vcid", WebContextUtils.getVcid());
        params.put("organizationId", organizationId);
        
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
            @RequestParam(value = "organizationId", required = false) String organizationId,
            @RequestParam(value = "valid", required = false) Boolean valid,
            @RequestParam(value = "pageNumber", required = false, defaultValue = "1") int pageIndex,
            @RequestParam(value = "pageSize", required = false, defaultValue = "10") int pageSize,
            @RequestParam MultiValueMap<String, String> request) {
        Map<String, Object> params = new HashMap<>();
        params.put("vcid", WebContextUtils.getVcid());
        params.put("organizationId", organizationId);
        
        PagedList<Post> resPagedList = this.postService.queryPagedList(valid,
                params,
                pageIndex,
                pageSize);
        return resPagedList;
    }
    
    /**
     * 新增职位实例<br/>
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
        post.setVcid(WebContextUtils.getVcid());
        
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
    
    /**
     * 根据主键查询职位实例<br/> 
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
    public Post findById(@RequestParam(value = "id") String id) {
        Post post = this.postService.findById(id);
        return post;
    }
    
    /**
     * 根据编码查询职位实例<br/> 
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
    public Post findByCode(@RequestParam(value = "code") String code) {
        Post post = this.postService.findByCode(code);
        return post;
    }
    
    /**
     * 删除职位实例<br/> 
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
        boolean flag = this.postService.deleteById(id);
        return flag;
    }
    
    /**
     * 禁用职位实例
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
        boolean flag = this.postService.disableById(id);
        return flag;
    }
    
    /**
     * 启用职位实例<br/>
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
        boolean flag = this.postService.enableById(id);
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
        params.remove("id");
        boolean flag = this.postService.exists(params, excludeId);
        
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
        boolean flag = this.postService.modifyAble(id);
        return flag;
    }
    
    /**
     * 根据条件查询职位子级列表<br/>
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
    public List<Post> queryChildren(
            @RequestParam(value = "organizationId", required = false) String organizationId,
            @RequestParam(value = "parentId", required = true) String parentId,
            @RequestParam(value = "valid", required = false) Boolean valid,
            @RequestParam MultiValueMap<String, String> request) {
        Map<String, Object> params = new HashMap<>();
        params.put("vcid", WebContextUtils.getVcid());
        params.put("organizationId", organizationId);
        
        List<Post> resList = this.postService.queryChildrenByParentId(parentId,
                valid,
                params);
        
        return resList;
    }
    
    /**
     * 根据条件查询职位子、孙级列表<br/>
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
    public List<Post> queryDescendants(
            @RequestParam(value = "organizationId", required = false) String organizationId,
            @RequestParam(value = "parentId", required = true) String parentId,
            @RequestParam(value = "valid", required = false) Boolean valid,
            @RequestParam MultiValueMap<String, String> request) {
        Map<String, Object> params = new HashMap<>();
        params.put("vcid", WebContextUtils.getVcid());
        params.put("organizationId", organizationId);
        
        List<Post> resList = this.postService
                .queryDescendantsByParentId(parentId, valid, params);
        
        return resList;
    }
    
}