/*
 * 描          述:  <描述>
 * 修  改   人:  PengQingyang
 * 修改时间:  2012-10-14
 * <修改描述:>
 */
package com.tx.webdemo.demo.dao.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.tx.core.mybatis.model.Order;
import com.tx.core.mybatis.support.MyBatisDaoSupport;
import com.tx.core.paged.model.PagedList;
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
    
    @Resource(name="myBatisDaoSupport")
    private MyBatisDaoSupport mybatisDaoSupport;

    /**
     * @param demo
     */
    @Override
    public void insertDemo(Demo demo) {
        
    }

    /**
     * @param condition
     * @return
     */
    @Override
    public int deleteDemo(Demo condition) {
        // TODO Auto-generated method stub
        return 0;
    }

    /**
     * @param condition
     * @return
     */
    @Override
    public Demo findDemo(Demo condition) {
        // TODO Auto-generated method stub
        return null;
    }

    /**
     * @param params
     * @return
     */
    @Override
    public List<Demo> queryDemoList(Map<String, Object> params) {
        // TODO Auto-generated method stub
        return null;
    }

    /**
     * @param params
     * @param orderList
     * @return
     */
    @Override
    public List<Demo> queryDemoList(Map<String, Object> params,
            List<Order> orderList) {
        // TODO Auto-generated method stub
        return null;
    }

    /**
     * @param params
     * @return
     */
    @Override
    public int countDemo(Map<String, Object> params) {
        // TODO Auto-generated method stub
        return 0;
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
        // TODO Auto-generated method stub
        return null;
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
        // TODO Auto-generated method stub
        return null;
    }

    /**
     * @param updateDemoRowMap
     * @return
     */
    @Override
    public int updateDemo(Map<String, Object> updateDemoRowMap) {
        return this.mybatisDaoSupport.update("demo.test", updateDemoRowMap);
    }
}
