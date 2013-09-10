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

import com.tx.component.operator.model.Post;
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
@Controller("newPostController")
@RequestMapping("/post")
public class PostController {
    
    @Resource(name = "newPostService")
    private PostService postService;
    
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
      * 跳转到添加职位页面<br/>
      *<功能详细描述>
      * @return [参数说明]
      * 
      * @return String [返回类型说明]
      * @exception throws [异常类型] [异常说明]
      * @see [类、类#方法、类#成员]
     */
    @RequestMapping("/toAddPost")
    public String toAddPost(ModelMap response) {
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
            @RequestParam(value = "code", required = false) String excludePostId) {
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
    @RequestMapping("/queryPostList")
    public List<Post> queryPostList(
            @RequestParam(value = "parentPostId", required = false) String parentPostId,
            @RequestParam(value = "organizationId", required = false) String organizationId) {
        List<Post> resList = null;
        if (StringUtils.isEmpty(parentPostId)
                && StringUtils.isEmpty(organizationId)) {
            resList = this.postService.queryPostListByAuth();
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
    @RequestMapping("/addPost")
    @ResponseBody
    public boolean addPost(Post post) {
        this.postService.insertPost(post);
        return true;
    }
    
}
