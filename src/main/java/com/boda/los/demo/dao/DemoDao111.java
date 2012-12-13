/*
 * 描          述:  <描述>
 * 修  改   人:  PengQingyang
 * 修改时间:  2012-10-14
 * <修改描述:>
 */
package com.boda.los.demo.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Insert;

import com.boda.los.demo.model.Demo;
import com.tx.core.mybatis.model.BatchResult;
import com.tx.core.mybatis.model.Order;
import com.tx.core.paged.model.PagedList;


 /**
  * <demo持久层>
  * <功能详细描述>
  * 
  * @author  PengQingyang
  * @version  [版本号, 2012-10-14]
  * @see  [相关类/方法]
  * @since  [产品/模块版本]
  */
public interface DemoDao111 {
    
    /**
      *<功能简述>添加demo对象
      *<功能详细描述>传入一个demo对象，将它加入到数据库中，然后返回一个整形
      * @param demo 
      * @return [参数说明] 返回为-1，表示操作失败，为1，表示操作成功
      * 
      * @return int [返回类型说明]
     */
    public void insertDemo(Demo demo);
    
    /**
      * 批量插入demo对象列表，其中错误的插入不会造成插入停止
      * <功能详细描述>
      * @param demoList [参数说明]
      * 
      * @return void [返回类型说明]
      * @exception throws [异常类型] [异常说明]
      * @see [类、类#方法、类#成员]
     */
    public BatchResult batchInsertDemo(List<Demo> demoList,boolean isStopWhenException);
    
    /**
      * 查询demo实例
      *<功能详细描述>
      * @param findCondition
      * @return [参数说明]
      * 
      * @return Demo [返回类型说明]
      * @exception throws [异常类型] [异常说明]
      * @see [类、类#方法、类#成员]
     */
    public Demo findDemo(Demo findCondition);
    
	/**
	 * 查询demoList
	 * @param paraMap
	 * @return
	 */
	public List<Demo> queryDemoList(Map<String, Object> paraMap);
	
	/**
	  * 查询demolist并根据指定排序
	  * <功能详细描述>
	  * @param paraMap
	  * @param orders
	  * @return [参数说明]
	  * 
	  * @return List<Demo> [返回类型说明]
	  * @exception throws [异常类型] [异常说明]
	  * @see [类、类#方法、类#成员]
	 */
	public List<Demo> queryDemoList(Map<String, Object> paraMap,List<Order> orders);
	
	/**
	  * 查询分页列表
	  *<功能详细描述>
	  * @param paraMap
	  * @param orders
	  * @return [参数说明]
	  * 
	  * @return PagedList<Demo> [返回类型说明]
	  * @exception throws [异常类型] [异常说明]
	  * @see [类、类#方法、类#成员]
	 */
	public PagedList<Demo> queryDemoPagedList(Map<String, Object> paraMap,List<Order> orders);
	
	/**
	  * 查询分页列表
	  *<功能详细描述>
	  * @param paraMap
	  * @return [参数说明]
	  * 
	  * @return PagedList<Demo> [返回类型说明]
	  * @exception throws [异常类型] [异常说明]
	  * @see [类、类#方法、类#成员]
	 */
	public PagedList<Demo> queryDemoPagedList(Map<String, Object> paraMap);
	
	/**
	 * 
	  *<功能简述>删除demo对象
	  *<功能详细描述>根据传进来的demoId删除对应的数据行
	  * @param demoId
	  * @return [参数说明] 返回为-1，表示操作失败，为1，表示操作成功
	  * 
	  * @return int [返回类型说明]
	 */
	public int deleteDemo(Demo findCondition);
	
	/**
	  *<功能简述>更新demo对象
	  *<功能详细描述>根据传进来的demo对象的id找到对应的数据行，然后更新它
	  * @param demo
	  * @return [参数说明] 返回为-1，表示操作失败，为1，表示操作成功
	  * 
	  * @return int [返回类型说明]
	 */
	public int updateDemo(Map<String, Object> demoMap);
}
