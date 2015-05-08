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

import com.tx.component.operator.model.EmployeeInfo;
import com.tx.component.operator.service.EmployeeInfoService;
import com.tx.core.paged.model.PagedList;
import com.tx.core.util.UUIDUtils;


 /**
  * EmployeeInfo业务层单元测试类
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
        "classpath:spring/beans-cache.xml",
        "classpath:spring/beans-ds.xml",
        "classpath:spring/beans-i18n.xml",
        "classpath:spring/beans-tx.xml",
        "classpath:spring/beans.xml" })
@ActiveProfiles("dev")
public class EmployeeInfoServiceTest {
    
    /** 设置jndi */
    @BeforeClass
    public static void setUp() {
        //bindJNDI
    }
    
    @Resource(name="employeeInfoService")
    private EmployeeInfoService employeeInfoService;
    
    /**
      * 生成employeeInfo实例
      * <功能详细描述>
      * @return [参数说明]
      * 
      * @return EmployeeInfo [返回类型说明]
      * @exception throws [异常类型] [异常说明]
      * @see [类、类#方法、类#成员]
     */
    protected EmployeeInfo getEmployeeInfo(){
        EmployeeInfo res = new EmployeeInfo();
        res.setOperatorId(UUIDUtils.generateUUID());
        res.setName("admin");
        
        
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
            EmployeeInfo employeeInfo = getEmployeeInfo();
            
            this.employeeInfoService.insertEmployeeInfo(employeeInfo);
            
            Assert.assertNotNull(employeeInfo.getOperatorId());
            
            String pk = employeeInfo.getOperatorId();
            
            EmployeeInfo res = this.employeeInfoService.findEmployeeInfoByOperatorId(pk);
            
            Assert.assertNotNull(res);
            
            int count = this.employeeInfoService.deleteByOperatorId(res.getOperatorId());
            
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
            int count = this.employeeInfoService.countEmployeeInfo();
            
            Assert.assertTrue(count >= 0);
            
            List<EmployeeInfo> employeeInfoList = this.employeeInfoService.queryEmployeeInfoList();
            
            Assert.assertNotNull(employeeInfoList);
            
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
    @Test
    public void testQueryPageList(){
        try {
            PagedList<EmployeeInfo> employeeInfoPageList = this.employeeInfoService.queryEmployeeInfoPagedList(1, 10);
            
            Assert.assertNotNull(employeeInfoPageList);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.assertTrue(false);
        }
    }
    
}
