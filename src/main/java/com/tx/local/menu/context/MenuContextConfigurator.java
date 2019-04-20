/*
 * 描          述:  <描述>
 * 修  改   人:  brady
 * 修改时间:  2013-8-22
 * <修改描述:>
 */
package com.tx.local.menu.context;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;

import com.thoughtworks.xstream.XStream;
import com.tx.core.util.XstreamUtils;
import com.tx.local.menu.config.MenuConfig;

/**
 * 菜单容器配置器<br/>
 * <功能详细描述>
 * 
 * @author  brady
 * @version  [版本号, 2013-8-22]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public abstract class MenuContextConfigurator implements InitializingBean {
    
    /** 日志记录句柄 */
    protected static final Logger logger = LoggerFactory
            .getLogger(MenuContextConfigurator.class);
    
    /** menuConfig的读取器 */
    protected static final XStream menuConfigXstream = XstreamUtils
            .getXstream(MenuConfig.class);
    
    /** 菜单配置所在目录 */
    protected String menuConfigLocation = "classpath:context/menu_config.xml";
    
    /**
     * @throws Exception
     */
    @Override
    public final void afterPropertiesSet() throws Exception {
        logger.info("菜单容器初始化,start.");
        logger.info("   菜单容器初始化,构建容器.");
        doBuild();
        logger.info("   菜单容器初始化,初始化容器.");
        doInitContext();
        logger.info("菜单容器初始化,end.");
    }
    
    /**
     * 构建容器<br/>
     * <功能详细描述>
     * @throws Exception [参数说明]
     * 
     * @return void [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    protected abstract void doBuild() throws Exception;
    
    /**
     * 初始化容器<br/>
     * <功能详细描述>
     * @throws Exception [参数说明]
     * 
     * @return void [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    protected abstract void doInitContext() throws Exception;
}
