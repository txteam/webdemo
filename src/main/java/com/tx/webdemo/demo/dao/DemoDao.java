/*
 * 描          述:  <描述>
 * 修  改   人:  PengQingyang
 * 修改时间:  2012-10-14
 * <修改描述:>
 */
package com.tx.webdemo.demo.dao;

import java.util.List;
import java.util.Map;

import com.tx.core.mybatis.model.Order;
import com.tx.core.paged.model.PagedList;
import com.tx.webdemo.demo.model.Demo;

/**
 * demo持久层
 * <功能详细描述>
 * 
 * @author  PengQingyang
 * @version  [版本号, 2012-10-14]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public interface DemoDao {
    
    /**
      * 插入对象实体
      * 1、auto generate
      * <功能详细描述>
      * @param demo [参数说明]
      * 
      * @return void [返回类型说明]
      * @exception throws [异常类型] [异常说明]
      * @see [类、类#方法、类#成员]
     */
    //auto generate
    public void insertDemo(Demo demo);
    
    /**
      * 删除demo对象
      * 1、auto generate
      * 2、生成逻辑为根据主键进行删除
      * <功能详细描述>
      * @param condition [参数说明]
      * 
      * @return void [返回类型说明]
      * @exception throws [异常类型] [异常说明]
      * @see [类、类#方法、类#成员]
     */
    //auto generate
    public int deleteDemo(Demo condition);
    
    /**
      * 查询demo实体
      * <功能详细描述>
      * @param condition
      * @return [参数说明]
      * 
      * @return Demo [返回类型说明]
      * @exception throws [异常类型] [异常说明]
      * @see [类、类#方法、类#成员]
     */
    //auto generate
    public Demo findDemo(Demo condition);
    
    /**
      * 根据条件查询demo列表
      * <功能详细描述>
      * @param params
      * @return [参数说明]
      * 
      * @return List<Demo> [返回类型说明]
      * @exception throws [异常类型] [异常说明]
      * @see [类、类#方法、类#成员]
     */
    //auto generate
    public List<Demo> queryDemoList(Map<String, Object> params);
    
    /**
      * 根据指定查询条件以及排序列表查询demo列表
      * <功能详细描述>
      * @param params
      * @param orderList
      * @return [参数说明]
      * 
      * @return List<Demo> [返回类型说明]
      * @exception throws [异常类型] [异常说明]
      * @see [类、类#方法、类#成员]
     */
    //auto generate
    public List<Demo> queryDemoList(Map<String, Object> params,
            List<Order> orderList);
    
    /**
      * 根据条件查询demo列表数
      * <功能详细描述>
      * @param params
      * @return [参数说明]
      * 
      * @return int [返回类型说明]
      * @exception throws [异常类型] [异常说明]
      * @see [类、类#方法、类#成员]
     */
    public int countDemo(Map<String, Object> params);
    
    /**
      * 分页查询demo列表
      * <功能详细描述>
      * @param params
      * @param pageIndex
      * @param pageSize
      * @return [参数说明]
      * 
      * @return PagedList<Demo> [返回类型说明]
      * @exception throws [异常类型] [异常说明]
      * @see [类、类#方法、类#成员]
     */
    //auto generate
    public PagedList<Demo> queryDemoPagedList(Map<String, Object> params,
            int pageIndex, int pageSize);
    
    /**
      * 分页查询demo列表，传入排序字段
      *<功能详细描述>
      * @param params
      * @param pageIndex
      * @param pageSize
      * @param orderList
      * @return [参数说明]
      * 
      * @return PagedList<Demo> [返回类型说明]
      * @exception throws [异常类型] [异常说明]
      * @see [类、类#方法、类#成员]
     */
    //auto generate
    public PagedList<Demo> queryDemoPagedList(Map<String, Object> params,
            int pageIndex, int pageSize, List<Order> orderList);
    
    
    /**
      * 更新demo实体，
      * 1、传入demo中主键不能为空
      * <功能详细描述>
      * @param updateDemoRowMap
      * @return [参数说明]
      * 
      * @return int [返回类型说明]
      * @exception throws [异常类型] [异常说明]
      * @see [类、类#方法、类#成员]
     */
    public int updateDemo(Map<String, Object> updateDemoRowMap);
}
