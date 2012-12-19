/*
 * 描          述:  <描述>
 * 修  改   人:  brady
 * 修改时间:  2012-12-11
 * <修改描述:>
 */
package com.tx.webdemo.demo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import junit.framework.Assert;

import org.apache.commons.lang.time.DateFormatUtils;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.tx.core.mybatis.model.BatchResult;
import com.tx.core.paged.model.PagedList;
import com.tx.webdemo.TestBase;
import com.tx.webdemo.demo.model.Demo;
import com.tx.webdemo.demo.service.DemoService;


 /**
  * Demo业务层单元测试类
  * <功能详细描述>
  * 
  * @author  brady
  * @version  [版本号, 2012-12-11]
  * @see  [相关类/方法]
  * @since  [产品/模块版本]
  */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { 
        "classpath:spring/beans-ds.xml",
        "classpath:spring/beans-tx.xml", 
        "classpath:spring/beans-mybatis.xml",
        "classpath:spring/beans.xml" })
public class DemoServiceTest {
    
    /** 设置jndi */
    @BeforeClass
    public static void setUp() {
        TestBase.bindDsToJNDI();
    }
    
    @Resource(name="demoService")
    private DemoService demoService;
    
    /**
      * 生成demo实例
      * <功能详细描述>
      * @return [参数说明]
      * 
      * @return Demo [返回类型说明]
      * @exception throws [异常类型] [异常说明]
      * @see [类、类#方法、类#成员]
     */
    protected Demo getDemo(){
        String loginName = "testInsertDemo"
                + DateFormatUtils.format(new Date(), "yyyyMMddHHmmssSSS");
        Demo newDemo = new Demo();
        newDemo.setName("testName");
        newDemo.setLoginName(loginName);
        newDemo.setPassword("test");
        newDemo.setCreateDate(new Date());
        
        return newDemo;
    }
    
    /**
      * 贯通测试，增加，查询，删除
      * <功能详细描述> [参数说明]
      * 
      * @return void [返回类型说明]
      * @exception throws [异常类型] [异常说明]
      * @see [类、类#方法、类#成员]
     */
    //@Test
    public void testInsertAndFindAndDelete(){
        //
        try {
            Demo demo = getDemo();
            
            this.demoService.insertDemo(demo);
            
            Assert.assertNotNull(demo.getId());
            
            String pk = demo.getId();
            
            Demo res = this.demoService.findDemoById(pk);
            
            Assert.assertNotNull(res);
            
            int count = this.demoService.deleteById(res.getId());
            
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
            int count = this.demoService.countDemo();
            
            Assert.assertTrue(count >= 0);
            
            List<Demo> demoList = this.demoService.queryDemoList();
            
            Assert.assertNotNull(demoList);
            
        } catch (Exception e) {
            e.printStackTrace();
            Assert.assertTrue(false);
        }
    }
    
    /**
      * 测试分页查询功能
      * <功能详细描述> [参数说明]
      * 
      * @return void [返回类型说明]
      * @exception throws [异常类型] [异常说明]
      * @see [类、类#方法、类#成员]
     */
    //@Test
    public void testQueryPageList(){
        try {
            PagedList<Demo> demoPageList = this.demoService.queryDemoPagedList(1, 10);
            
            demoPageList = this.demoService.queryDemoPagedList(2, 10);
            
            Assert.assertNotNull(demoPageList);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.assertTrue(false);
        }
    }
    
    //@Test
    public void testBatchInserDemoNonStop() {
        List<Demo> demoList = new ArrayList<Demo>();
        String loginName = "testBatchInserDemoNonStop"
                + DateFormatUtils.format(new Date(), "yyyyMMddHHmmssSSS");
        
        Demo newDemo1 = new Demo();
        newDemo1.setName("testName");
        newDemo1.setLoginName(loginName);
        newDemo1.setPassword("test");
        demoList.add(newDemo1);
        Demo newDemo2 = new Demo();
        newDemo2.setName("testName");
        newDemo2.setLoginName(loginName);
        newDemo2.setPassword("test");
        demoList.add(newDemo2);
        for (int i = 0; i < 200; i++) {
            Demo newDemo = new Demo();
            newDemo.setName("testName");
            newDemo.setLoginName(loginName + (int) (Math.random() * 10000000));
            newDemo.setPassword("test");
            demoList.add(newDemo);
        }
        Demo newDemo3 = new Demo();
        newDemo3.setName("testName");
        newDemo3.setLoginName(loginName);
        newDemo3.setPassword("test");
        demoList.add(newDemo3);
        
        try {
            BatchResult result = this.demoService.batchInsertDemoNotStopWhenException(demoList,false);
            System.out.println(result.isSuccessAll());
            System.out.println(result.getErrorNum());
            System.out.println(result.getErrorRownumIndexList().size());
            for(int indexNum : result.getErrorRownumIndexList()){
                System.out.println(indexNum + ":" + ((Demo)result.getErrorRownumParameterMapping().get(indexNum)).getLoginName());
            }
            Assert.assertTrue(true);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.assertTrue(false);
        }
    }
    
}
