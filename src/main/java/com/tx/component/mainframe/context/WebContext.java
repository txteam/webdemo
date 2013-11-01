/*
 * 描          述:  <描述>
 * 修  改   人:  brady
 * 修改时间:  2013-9-10
 * <修改描述:>
 */
package com.tx.component.mainframe.context;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.InitializingBean;

import com.tx.component.operator.service.OrganizationService;
import com.tx.core.exceptions.SILException;

/**
 * Web容器<br/>
 * <功能详细描述>
 * 
 * @author  brady
 * @version  [版本号, 2013-9-10]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public class WebContext implements InitializingBean {
    
    private static WebContext webContext;
    
    @Resource(name = "organizationService")
    private OrganizationService organizationService;
    
    /**
      * 获取web容器实例<br/>
      *<功能详细描述>
      * @return [参数说明]
      * 
      * @return WebContext [返回类型说明]
      * @exception throws [异常类型] [异常说明]
      * @see [类、类#方法、类#成员]
     */
    public static WebContext getContext() {
        assertIsInit();
        
        return webContext;
    }
    
    /**
     * @throws Exception
     */
    @Override
    public void afterPropertiesSet() throws Exception {
        
        //初始化后将webContext设置为当前初始化的实例
        WebContext.webContext = this;
    }
    
    /** 私有化容器 */
    protected WebContext() {
    }
    
    /**
      * 断言容器已经初始化<br/>
      * 检查容器是否初始化<br/>
      *<功能详细描述> [参数说明]
      * 
      * @return void [返回类型说明]
      * @exception throws [异常类型] [异常说明]
      * @see [类、类#方法、类#成员]
     */
    private static void assertIsInit() {
        if (webContext == null) {
            throw new SILException("容器尚未初始化");
        }
    }
    
    /**
     * 根据当前人员id作为父组织id
     * 迭代查询当前组织及其子组织的列表<br/>
     *<功能详细描述>
     * @param parentOrganizationId
     * @return [参数说明]
     * 
     * @return List<Organization> [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
    */
    public List<String> queryOrganizationIdList() {
        List<String> resList = this.organizationService.queryOrganizationIdList();
        
        return resList;
    }
}
