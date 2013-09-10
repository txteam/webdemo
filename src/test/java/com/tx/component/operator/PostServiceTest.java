/*
 * 描          述:  <描述>
 * 修  改   人:  brady
 * 修改时间:  2012-12-11
 * <修改描述:>
 */
package com.tx.component.operator;

import java.util.List;

import javax.annotation.Resource;

import junit.framework.Assert;

import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.tx.component.operator.model.Post;
import com.tx.component.operator.service.PostService;


 /**
  * Post业务层单元测试类
  * <功能详细描述>
  * 
  * @author  brady
  * @version  [版本号, 2012-12-11]
  * @see  [相关类/方法]
  * @since  [产品/模块版本]
  */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { 
        "classpath:spring/beans-aop.xml",
        "classpath:spring/beans-auth.xml",
        "classpath:spring/beans-cache.xml",
        "classpath:spring/beans-ds.xml",
        "classpath:spring/beans-i18n.xml",
        "classpath:spring/beans-tx.xml",
        "classpath:spring/beans.xml" })
@ActiveProfiles("dev")
public class PostServiceTest {
    
    /** 设置jndi */
    @BeforeClass
    public static void setUp() {
        //bindJNDI
    }
    
    @Resource(name="postService")
    private PostService postService;
    
    /**
      * 生成post实例
      * <功能详细描述>
      * @return [参数说明]
      * 
      * @return Post [返回类型说明]
      * @exception throws [异常类型] [异常说明]
      * @see [类、类#方法、类#成员]
     */
    protected Post getPost(){
        Post res = new Post();
        
        
        
        return res;
    }
    
    /**
      * 贯通测试，增加，查询，删除
      * <功能详细描述> [参数说明]
      * 
      * @return void [返回类型说明]
      * @exception throws [异常类型] [异常说明]
      * @see [类、类#方法、类#成员]
     */
    @Test
    public void testInsertAndFindAndDelete(){
        //
        try {
            Post post = getPost();
            
            this.postService.insertPost(post);
            
            Assert.assertNotNull(post.getId());
            
            String pk = post.getId();
            
            Post res = this.postService.findPostById(pk);
            
            Assert.assertNotNull(res);
            
            int count = this.postService.deleteById(res.getId());
            
            Assert.assertTrue(count > 0);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.assertTrue(false);
        }
    }
    
    /**
      * 测试查询列表
      * <功能详细描述> [参数说明]
      * 
      * @return void [返回类型说明]
      * @exception throws [异常类型] [异常说明]
      * @see [类、类#方法、类#成员]
     */
    @Test
    public void testQueryList(){
        try {
//            int count = this.postService.countPost();
//            
//            Assert.assertTrue(count >= 0);
            
            List<Post> postList = this.postService.queryAllPostList();
            
            Assert.assertNotNull(postList);
            
        } catch (Exception e) {
            e.printStackTrace();
            Assert.assertTrue(false);
        }
    }
    
//    /**
//      * 测试分页查询功能
//      * <功能详细描述> [参数说明]
//      * 
//      * @return void [返回类型说明]
//      * @exception throws [异常类型] [异常说明]
//      * @see [类、类#方法、类#成员]
//     */
//    @Test
//    public void testQueryPageList(){
//        try {
//            PagedList<Post> postPageList = this.postService.queryPostPagedList(1, 10);
//            
//            Assert.assertNotNull(postPageList);
//        } catch (Exception e) {
//            e.printStackTrace();
//            Assert.assertTrue(false);
//        }
//    }
    
}
