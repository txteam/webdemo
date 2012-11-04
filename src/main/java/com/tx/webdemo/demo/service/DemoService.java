/*
 * 描          述:  <描述>
 * 修  改   人:  PengQingyang
 * 修改时间:  2012-10-14
 * <修改描述:>
 */
package com.tx.webdemo.demo.service;

import javax.annotation.Resource;

import org.apache.cxf.common.util.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.tx.core.exceptions.parameter.ParameterIsEmptyException;
import com.tx.webdemo.demo.annotation.DemoAnotation;
import com.tx.webdemo.demo.dao.DemoDao;
import com.tx.webdemo.demo.model.Demo;

/**
 * <业务层>
 * <功能详细描述>
 * 
 * @author  PengQingyang
 * @version  [版本号, 2012-10-14]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
@Component("demoService")
public class DemoService {
    
    /* 是否需要引入日志记录，根据具体业务定，这里是为了打印启动加载情况才加入的  */
    private Logger logger = LoggerFactory.getLogger(DemoService.class);
    
    @Resource(name="demoDao")
    private DemoDao demoDao;
    
    public DemoService(){
        logger.info("Instance DemoService............................");
    }
    
    @Transactional
    @DemoAnotation
    public void insertDemo(Demo demo) {
        //验证参数: demo.name  password 不能为空
        if (demo == null || StringUtils.isEmpty(demo.getName())
                || StringUtils.isEmpty(demo.getPassowrd())) {
            throw new ParameterIsEmptyException(
                    "demo.name or password is not empty.");
        }
        
        this.demoDao.insertDemo(demo);
    }
}
