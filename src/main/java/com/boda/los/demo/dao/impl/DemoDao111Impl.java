/*
 * 描          述:  <描述>
 * 修  改   人:  PengQingyang
 * 修改时间:  2012-10-14
 * <修改描述:>
 */
package com.boda.los.demo.dao.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.boda.los.demo.dao.DemoDao111;
import com.boda.los.demo.model.Demo;
import com.tx.core.mybatis.model.BatchResult;
import com.tx.core.mybatis.model.Order;
import com.tx.core.mybatis.support.MyBatisDaoSupport;
import com.tx.core.paged.model.PagedList;

/**
 * <demo对象持久层>
 * <功能详细描述>
 * 
 * @author  PengQingyang
 * @version  [版本号, 2012-10-14]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
@Component("demoDao111")
public class DemoDao111Impl implements DemoDao111 {
    
    /* 是否需要引入日志记录，根据具体业务定，这里是为了打印启动加载情况才加入的  */
    private static Logger logger = LoggerFactory.getLogger(DemoDao111Impl.class);
    
    @Resource(name = "myBatisDaoSupport")
    private MyBatisDaoSupport mybatisDaoSupport;
    
    /**
     * 
     * @param demo
     */
    @Override
    public void insertDemo(Demo demo) {
        this.mybatisDaoSupport.insertUseUUID("demo.insertDemo", demo, "id");
    }
    
    /**
     * @param demoList
     * @param isStopWhenException
     * @return
     */
    @Override
    public BatchResult batchInsertDemo(List<Demo> demoList,
            boolean isStopWhenException) {
        return this.mybatisDaoSupport.batchInsert("demo.insertDemo",
                demoList,
                isStopWhenException);
    }
    
    /**
     * @param paraMap
     * @return
     */
    @Override
    public PagedList<Demo> queryDemoPagedList(Map<String, Object> paraMap) {
        // TODO Auto-generated method stub
        return null;
    }
    
    /**
     * @param paraMap
     * @param orders
     * @return
     */
    @Override
    public PagedList<Demo> queryDemoPagedList(Map<String, Object> paraMap,
            List<Order> orders) {
        // TODO Auto-generated method stub
        return null;
    }
    
    /**
     * @param paraMap
     * @return
     */
    public PagedList<Demo> queryDemoPagedList(Map<String, Object> paraMap,
            int pageIndex, int pageSize) {
        return this.mybatisDaoSupport.<Demo> queryPagedList("demo.queryList",
                paraMap,
                pageIndex,
                pageSize);
    }
    
    /**
     * @param findCondition
     * @return
     */
    @Override
    public int deleteDemo(Demo condition) {
        return this.mybatisDaoSupport.delete("demo.deleteDemo", condition);
    }
    
    /**
     * @param demoMap
     * @return
     */
    @Override
    public int updateDemo(Map<String, Object> demoMap) {
        return this.mybatisDaoSupport.update("demo.updateDemo", demoMap);
    }
    
    /**
     * @param findCondition
     * @return
     */
    @Override
    public Demo findDemo(Demo findCondition) {
        return (Demo) this.mybatisDaoSupport.find("demo.findDemo",
                findCondition);
    }
    
    /**
     * @param paraMap
     * @param orders
     * @return
     */
    @Override
    public List<Demo> queryDemoList(Map<String, Object> paraMap,
            List<Order> orders) {
        // TODO Auto-generated method stub
        return null;
    }
    
    @Override
    public List<Demo> queryDemoList(Map<String, Object> paraMap) {
        // TODO Auto-generated method stub
        return null;
    }
    
}
