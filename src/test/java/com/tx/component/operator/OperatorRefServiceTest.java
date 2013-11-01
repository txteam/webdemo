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
import org.junit.runner.RunWith;
import org.junit.Test;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.tx.component.operator.model.OperatorRef;
import com.tx.component.operator.service.impl.OperatorRefService;
import com.tx.core.paged.model.PagedList;


 /**
  * OperatorRef业务层单元测试类
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
public class OperatorRefServiceTest {
    
    /** 设置jndi */
    @BeforeClass
    public static void setUp() {
        //bindJNDI
    }
    
    @Resource(name="operatorRefService")
    private OperatorRefService operatorRefService;
    
    /**
      * 生成operatorRef实例
      * <功能详细描述>
      * @return [参数说明]
      * 
      * @return OperatorRef [返回类型说明]
      * @exception throws [异常类型] [异常说明]
      * @see [类、类#方法、类#成员]
     */
    protected OperatorRef getOperatorRef(){
        OperatorRef res = new OperatorRef();
        
        
        
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
            OperatorRef operatorRef = getOperatorRef();
            
            this.operatorRefService.insertOperatorRef(operatorRef);
            
            Assert.assertNotNull(operatorRef.getOperatorId());
            
            String pk = operatorRef.getOperatorId();
            
            OperatorRef res = this.operatorRefService.findOperatorRefByOperatorId(pk);
            
            Assert.assertNotNull(res);
            
            int count = this.operatorRefService.deleteByOperatorId(res.getOperatorId());
            
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
            int count = this.operatorRefService.countOperatorRef();
            
            Assert.assertTrue(count >= 0);
            
            List<OperatorRef> operatorRefList = this.operatorRefService.queryOperatorRefList();
            
            Assert.assertNotNull(operatorRefList);
            
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
            PagedList<OperatorRef> operatorRefPageList = this.operatorRefService.queryOperatorRefPagedList(1, 10);
            
            Assert.assertNotNull(operatorRefPageList);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.assertTrue(false);
        }
    }
    
}
