/*
 * 描          述:  <描述>
 * 修  改   人:  PengQingyang
 * 修改时间:  2012-10-14
 * <修改描述:>
 */
package com.tx.webdemo.demo.dao.impl;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.tx.core.mybatis.support.MyBatisDaoSupport;
import com.tx.webdemo.demo.dao.DemoDao;
import com.tx.webdemo.demo.model.Demo;


 /**
  * <demo对象持久层>
  * <功能详细描述>
  * 
  * @author  PengQingyang
  * @version  [版本号, 2012-10-14]
  * @see  [相关类/方法]
  * @since  [产品/模块版本]
  */
@Component("demoDao")
public class DemoDaoImpl implements DemoDao {
    
    /* 是否需要引入日志记录，根据具体业务定，这里是为了打印启动加载情况才加入的  */
    private static Logger logger = LoggerFactory.getLogger(DemoDaoImpl.class);
    
    @Resource(name="myBatisDaoSupport")
    private MyBatisDaoSupport mybatisDaoSupport;
    
    /** <默认构造函数> */
    public DemoDaoImpl() {
        logger.info("Instance DemoDaoImpl............................");
    }
    
    /**
     * @param demo
     */
    @Override
    public void insertDemo(Demo demo) {
        this.mybatisDaoSupport.insert("demo.insertDemo",demo);
    }
    
}
