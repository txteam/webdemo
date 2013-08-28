/*
 * 描          述:  <描述>
 * 修  改   人:  PengQingyang
 * 修改时间:  2013-8-27
 * <修改描述:>
 */
package com.tx.component.operator.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
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
@Controller
@RequestMapping("/post")
public class PostController {
    
    @Resource(name = "postService")
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
    public String toQueryPostList(){
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
    public String toAddPost(){
        return "/operator/addPost";
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
    public List<Post> queryPostList(){
        List<Post> resList = this.postService.queryPostList();
        return resList;
    }
    
}
