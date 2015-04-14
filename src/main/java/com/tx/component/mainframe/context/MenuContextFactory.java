/*
 * 描          述:  <描述>
 * 修  改   人:  PengQingyang
 * 修改时间:  2013-8-22
 * <修改描述:>
 */
package com.tx.component.mainframe.context;

import org.springframework.beans.factory.FactoryBean;
import org.springframework.stereotype.Component;

/**
 * 菜单容器工厂<br/>
 * <功能详细描述>
 * 
 * @author PengQingyang
 * @version [版本号, 2013-8-22]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
@Component("menuContext")
public class MenuContextFactory extends MenuContext implements
        FactoryBean<MenuContext> {
    
    /**
     * @return
     * @throws Exception
     */
    @Override
    public MenuContext getObject() throws Exception {
        return MenuContext.getContext();
    }
    
    /**
     * @return
     */
    @Override
    public Class<?> getObjectType() {
        return MenuContext.class;
    }
    
    /**
     * @return
     */
    @Override
    public boolean isSingleton() {
        return true;
    }
}
