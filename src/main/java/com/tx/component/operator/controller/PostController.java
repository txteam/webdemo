/*
 * 描          述:  <描述>
 * 修  改   人:  PengQingyang
 * 修改时间:  2013-8-27
 * <修改描述:>
 */
package com.tx.component.operator.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tx.component.auth.annotation.CheckOperateAuth;
import com.tx.component.operator.model.Organization;
import com.tx.component.operator.model.Post;
import com.tx.component.operator.service.OrganizationService;
import com.tx.component.operator.service.PostService;

/**
 * 职位显示层逻辑<br/>
 * <功能详细描述>
 * 
 * @author  PengQingyang
 * @version  [版本号, 2013-8-27]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
@CheckOperateAuth(key = "post_manage", parentKey = "operator_config_center", name = "职位管理")
@Controller("newPostController")
@RequestMapping("/post")
public class PostController {
    
    @Resource(name = "postService")
    private PostService postService;
    
    @Resource(name = "organizationService")
    private OrganizationService organizationService;
    
    /**
      * 跳转到查询职位列表<br/>
      *<功能详细描述>
      * @return [参数说明]
      * 
      * @return String [返回类型说明]
      * @exception throws [异常类型] [异常说明]
      * @see [类、类#方法、类#成员]
     */
    @RequestMapping("/toQueryPostList")
    public String toQueryPostList() {
        return "/operator/queryPostList";
    }
    
    /**
     * 跳转到查询职位列表<br/>
     *<功能详细描述>
     * @return [参数说明]
     * 
     * @return String [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
    */
    @RequestMapping("/toChoosePost")
    public String toChoosePost(
            @RequestParam(value = "eventName", required = false) String chooseEventName,
            ModelMap responseMap) {
        responseMap.put("eventName", chooseEventName);
        return "/operator/choosePost";
    }
    
    /**
      * 跳转到添加职位页面<br/>
      *<功能详细描述>
      * @return [参数说明]
      * 
      * @return String [返回类型说明]
      * @exception throws [异常类型] [异常说明]
      * @see [类、类#方法、类#成员]
     */
    @RequestMapping("/toAddPost")
    public String toAddPost(
            @RequestParam(value = "organizationId", required = false) String organizationId,
            @RequestParam(value = "parentPostId", required = false) String parentPostId,
            ModelMap response) {
        if (!StringUtils.isEmpty(parentPostId)) {
            Post parentPost = this.postService.findPostById(parentPostId);
            response.put("parentPost", parentPost);
            response.put("organization", parentPost.getOrganization());
        } else if (!StringUtils.isEmpty(organizationId)) {
            Organization organization = this.organizationService.findOrganizationById(organizationId);
            response.put("organization", organization);
        }
        response.put("post", new Post());
        return "/operator/addPost";
    }
    
    /**
     * 跳转到更新组织页面
     *<功能详细描述>
     * @return [参数说明]
     * 
     * @return String [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
    */
    @RequestMapping("/toUpdatePost")
    public String toUpdatePost(@RequestParam("postId") String postId,
            ModelMap modelMap) {
        Post resPost = this.postService.findPostById(postId);
        if (!StringUtils.isEmpty(resPost.getParentId())) {
            Post parentPost = this.postService.findPostById(resPost.getParentId());
            modelMap.put("parentPost", parentPost);
        }
        
        modelMap.put("post", resPost);
        return "/operator/updatePost";
    }
    
    /**
      * 判断职位编码是否已经被使用
      *<功能详细描述>
      * @param code
      * @param excludePostId
      * @return [参数说明]
      * 
      * @return boolean [返回类型说明]
      * @exception throws [异常类型] [异常说明]
      * @see [类、类#方法、类#成员]
     */
    @ResponseBody
    @RequestMapping("/postCodeIsExist")
    public Map<String, String> postCodeIsExist(
            @RequestParam("code") String code,
            @RequestParam(value = "id", required = false) String excludePostId) {
        boolean flag = this.postService.postCodeIsExist(code, excludePostId);
        Map<String, String> resMap = new HashMap<String, String>();
        if (!flag) {
            resMap.put("ok", "可用的组织码号");
        } else {
            resMap.put("error", "已经存在的组织编号");
        }
        return resMap;
    }
    
    /**
      * 查询职位列表<br/>
      *<功能详细描述>
      * @return [参数说明]
      * 
      * @return List<Post> [返回类型说明]
      * @exception throws [异常类型] [异常说明]
      * @see [类、类#方法、类#成员]
     */
    @ResponseBody
    @RequestMapping("/queryPostListIncludeInvalid")
    public List<Post> queryPostListIncludeInvalid(
            @RequestParam(value = "parentPostId", required = false) String parentPostId,
            @RequestParam(value = "organizationId", required = false) String organizationId) {
        List<Post> resList = this.postService.queryPostListIncludeInvalid(parentPostId,
                organizationId);
        return resList;
    }
    
    /**
     * 查询职位列表<br/>
     *<功能详细描述>
     * @return [参数说明]
     * 
     * @return List<Post> [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
    */
    @ResponseBody
    @RequestMapping("/queryPostList")
    public List<Post> queryPostList(
            @RequestParam(value = "parentPostId", required = false) String parentPostId,
            @RequestParam(value = "organizationId", required = false) String organizationId) {
        List<Post> resList = null;
        
        if (StringUtils.isEmpty(parentPostId)
                && StringUtils.isEmpty(organizationId)) {
            resList = this.postService.queryPostList();
        } else if (!StringUtils.isEmpty(organizationId)) {
            resList = this.postService.queryPostListByOrganizationId(organizationId);
        } else {
            resList = this.postService.queryPostListByParentId(parentPostId);
        }
        return resList;
    }
    
    /**
     * 添加组织结构页面
     *<功能详细描述>
     * @param organization [参数说明]
     * 
     * @return void [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
    */
    @CheckOperateAuth(key = "add_post", name = "增加职位")
    @RequestMapping("/addPost")
    @ResponseBody
    public boolean addPost(Post post) {
        this.postService.insertPost(post);
        return true;
    }
    
    /**
      * 更新组织<br/>
      *<功能详细描述>
      * @param post
      * @return [参数说明]
      * 
      * @return boolean [返回类型说明]
      * @exception throws [异常类型] [异常说明]
      * @see [类、类#方法、类#成员]
     */
    @CheckOperateAuth(key = "update_post", name = "编辑职位")
    @RequestMapping("/updatePost")
    @ResponseBody
    public boolean updatePost(Post post) {
        this.postService.updateById(post);
        
        return true;
    }
    
    /**
      * 校验指定职位是否能被删除
      *<功能详细描述>
      * @param postId
      * @return [参数说明]
      * 
      * @return boolean [返回类型说明]
      * @exception throws [异常类型] [异常说明]
      * @see [类、类#方法、类#成员]
     */
    @ResponseBody
    @RequestMapping("/isDeleteAble")
    public boolean isDeleteAble(@RequestParam(value = "postId") String postId) {
        boolean flag = this.postService.isDeleteAble(postId);
        
        return flag;
    }
    
    /**
      * 删除指定职位<br/> 
      *<功能详细描述>
      * @param postId
      * @return [参数说明]
      * 
      * @return boolean [返回类型说明]
      * @exception throws [异常类型] [异常说明]
      * @see [类、类#方法、类#成员]
     */
    @CheckOperateAuth(key = "delete_post", name = "删除职位", configAble = false)
    @ResponseBody
    @RequestMapping("/deletePostById")
    public boolean deletePostById(@RequestParam(value = "postId") String postId) {
        boolean resFlag = this.postService.deleteById(postId);
        return resFlag;
    }
    
    /**
      * 判断职位是否能被禁用
      *<功能详细描述>
      * @param postId
      * @return [参数说明]
      * 
      * @return boolean [返回类型说明]
      * @exception throws [异常类型] [异常说明]
      * @see [类、类#方法、类#成员]
     */
    @ResponseBody
    @RequestMapping("/isDisableAble")
    public boolean isDisableAble(@RequestParam(value = "postId") String postId) {
        boolean flag = this.postService.isDisableAble(postId);
        
        return flag;
    }
    
    /**
      * 禁用职位
      * @param postId
      * @return [参数说明]
      * 
      * @return boolean [返回类型说明]
      * @exception throws [异常类型] [异常说明]
      * @see [类、类#方法、类#成员]
     */
    @CheckOperateAuth(key = "disable_post", name = "禁用职位")
    @ResponseBody
    @RequestMapping("/disablePostById")
    public boolean disablePostById(@RequestParam(value = "postId") String postId) {
        boolean resFlag = this.postService.disableById(postId);
        return resFlag;
    }
    
    /**
      * 启用职位<br/>
      *<功能详细描述>
      * @param postId
      * @return [参数说明]
      * 
      * @return boolean [返回类型说明]
      * @exception throws [异常类型] [异常说明]
      * @see [类、类#方法、类#成员]
     */
    @CheckOperateAuth(key = "enable_post", name = "启用职位")
    @ResponseBody
    @RequestMapping("/enablePostById")
    public boolean enablePostById(@RequestParam(value = "postId") String postId) {
        boolean resFlag = this.postService.enableById(postId);
        return resFlag;
    }
    
}
