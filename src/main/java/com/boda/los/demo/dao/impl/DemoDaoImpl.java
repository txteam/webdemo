/*
 * 描          述:  <描述>
 * 修  改   人:  
 * 修改时间:  
 * <修改描述:>
 */
package com.boda.los.demo.dao.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.boda.los.demo.dao.DemoDao;
import com.boda.los.demo.model.Demo;
import com.tx.core.mybatis.model.Order;
import com.tx.core.mybatis.support.MyBatisDaoSupport;
import com.tx.core.paged.model.PagedList;

/**
 * Demo持久层
 * <功能详细描述>
 * 
 * @author  
 * @version  [版本号, 2012-12-11]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
@Component("demoDao")
public class DemoDaoImpl implements DemoDao {
    
    @Resource(name = "myBatisDaoSupport")
    private MyBatisDaoSupport myBatisDaoSupport;
    
    /**
     * @param condition
     */
    @Override
    public void insertDemo(Demo condition) {
        this.myBatisDaoSupport.insertUseUUID("demo.insertDemo", condition, "id");
    }
    
    /**
     * @param condition
     * @return
     */
    @Override
    public int deleteDemo(Demo condition) {
        return this.myBatisDaoSupport.delete("demo.deleteDemo", condition);
    }
    
    /**
     * @param condition
     * @return
     */
    @Override
    public Demo findDemo(Demo condition) {
        return this.myBatisDaoSupport.<Demo> find("demo.findDemo", condition);
    }
    
    /**
     * @param params
     * @return
     */
    @Override
    public List<Demo> queryDemoList(Map<String, Object> params) {
        return this.myBatisDaoSupport.<Demo> queryList("demo.queryDemo",
                params);
    }
    
    /**
     * @param params
     * @param orderList
     * @return
     */
    @Override
    public List<Demo> queryDemoList(Map<String, Object> params,
            List<Order> orderList) {
        return this.myBatisDaoSupport.<Demo> queryList("demo.queryDemo",
                params,
                orderList);
    }
    
    /**
     * @param params
     * @return
     */
    @Override
    public int countDemo(Map<String, Object> params) {
        return this.myBatisDaoSupport.<Integer> find("demo.queryDemoCount",
                params);
    }
    
    /**
     * @param params
     * @param pageIndex
     * @param pageSize
     * @return
     */
    @Override
    public PagedList<Demo> queryDemoPagedList(Map<String, Object> params,
            int pageIndex, int pageSize) {
        return this.myBatisDaoSupport.<Demo> queryPagedList("demo.queryDemo",
                params,
                pageIndex,
                pageSize);
    }
    
    /**
     * @param params
     * @param pageIndex
     * @param pageSize
     * @param orderList
     * @return
     */
    @Override
    public PagedList<Demo> queryDemoPagedList(Map<String, Object> params,
            int pageIndex, int pageSize, List<Order> orderList) {
        return this.myBatisDaoSupport.<Demo> queryPagedList("demo.queryDemo",
                params,
                pageIndex,
                pageSize,
                orderList);
    }
    
    /**
     * @param updateRowMap
     * @return
     */
    @Override
    public int updateDemo(Map<String, Object> updateRowMap) {
        return this.myBatisDaoSupport.update("demo.updateDemo", updateRowMap);
    }
}
