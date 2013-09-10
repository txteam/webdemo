/*
 * 描          述:  <描述>
 * 修  改   人:  brady
 * 修改时间:  2013-9-10
 * <修改描述:>
 */
package com.tx.component.mainframe.context;

import org.springframework.beans.factory.FactoryBean;
import org.springframework.stereotype.Component;



 /**
  * Web容器实例<br/>
  *    主要提供在权限容器环境下根据当前线程内人员查询组织信息<br/>
  * <功能详细描述>
  * 
  * @author  brady
  * @version  [版本号, 2013-9-10]
  * @see  [相关类/方法]
  * @since  [产品/模块版本]
  */
@Component("webContext")
public class WebContextFactory extends WebContext implements FactoryBean<WebContext>{

    /**
     * @return
     * @throws Exception
     */
    @Override
    public WebContext getObject() throws Exception {
        return WebContext.getContext();
    }

    /**
     * @return
     */
    @Override
    public Class<?> getObjectType() {
        return WebContext.class;
    }

    /**
     * @return
     */
    @Override
    public boolean isSingleton() {
        return true;
    }
}
