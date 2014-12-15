/*
 * 描          述:  <描述>
 * 修  改   人:  
 * 修改时间:  
 * <修改描述:>
 */
package com.tx.fetch.dao;

import java.util.List;
import java.util.Map;

import com.tx.core.mybatis.model.Order;
import com.tx.core.paged.model.PagedList;
import com.tx.fetch.model.PersonInfo;

/**
 * PersonInfo持久层
 * <功能详细描述>
 * 
 * @author  
 * @version  [版本号, ]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public interface PersonInfoDao {

    /**
      * 批量插入PersonInfo对象实体
      * 1、auto generate
      * <功能详细描述>
      * @param condition [参数说明]
      * 
      * @return void [返回类型说明]
      * @exception throws [异常类型] [异常说明]
      * @see [类、类#方法、类#成员]
     */
    //auto generate
    public void batchInsertPersonInfo(List<PersonInfo> condition);    
    
    /**
      * 批量更新PersonInfo实体，
      * auto generate
      * 1、传入PersonInfo中主键不能为空
      * <功能详细描述>
      * @param updatePersonInfoRowMap
      * @return [参数说明]
      * 
      * @return int [返回类型说明]
      * @exception throws [异常类型] [异常说明]
      * @see [类、类#方法、类#成员]
     */
    public void batchUpdatePersonInfo(List<Map<String,Object>> updateRowMapList);
    
    /**
      * 插入PersonInfo对象实体
      * 1、auto generate
      * <功能详细描述>
      * @param condition [参数说明]
      * 
      * @return void [返回类型说明]
      * @exception throws [异常类型] [异常说明]
      * @see [类、类#方法、类#成员]
     */
    //auto generate
    public void insertPersonInfo(PersonInfo condition);
    
    /**
      * 删除PersonInfo对象
      * 1、auto generate
      * 2、根据入参条件进行删除
      * <功能详细描述>
      * @param condition [参数说明]
      * 
      * @return void [返回类型说明]
      * @exception throws [异常类型] [异常说明]
      * @see [类、类#方法、类#成员]
     */
    //auto generate
    public int deletePersonInfo(PersonInfo condition);
    
    /**
      * 查询PersonInfo实体
      * auto generate
      * <功能详细描述>
      * @param condition
      * @return [参数说明]
      * 
      * @return PersonInfo [返回类型说明]
      * @exception throws [异常类型] [异常说明]
      * @see [类、类#方法、类#成员]
     */
    //auto generate
    public PersonInfo findPersonInfo(PersonInfo condition);
    
    /**
      * 根据条件查询PersonInfo列表
      * auto generate
      * <功能详细描述>
      * @param params
      * @return [参数说明]
      * 
      * @return List<PersonInfo> [返回类型说明]
      * @exception throws [异常类型] [异常说明]
      * @see [类、类#方法、类#成员]
     */
    //auto generate
    public List<PersonInfo> queryPersonInfoList(Map<String, Object> params);
    
    /**
      * 根据指定查询条件以及排序列查询PersonInfo列表
      * auto generate
      * <功能详细描述>
      * @param params
      * @param orderList
      * @return [参数说明]
      * 
      * @return List<PersonInfo> [返回类型说明]
      * @exception throws [异常类型] [异常说明]
      * @see [类、类#方法、类#成员]
     */
    //auto generate
    public List<PersonInfo> queryPersonInfoList(Map<String, Object> params,
            List<Order> orderList);
    
    /**
      * 根据条件查询PersonInfo列表总数
      * auto generated
      * <功能详细描述>
      * @param params
      * @return [参数说明]
      * 
      * @return int [返回类型说明]
      * @exception throws [异常类型] [异常说明]
      * @see [类、类#方法、类#成员]
     */
    public int countPersonInfo(Map<String, Object> params);
    
    /**
      * 分页查询PersonInfo列表
      * auto generate
      * <功能详细描述>
      * @param params
      * @param pageIndex
      * @param pageSize
      * @return [参数说明]
      * 
      * @return PagedList<PersonInfo> [返回类型说明]
      * @exception throws [异常类型] [异常说明]
      * @see [类、类#方法、类#成员]
     */
    //auto generate
    public PagedList<PersonInfo> queryPersonInfoPagedList(Map<String, Object> params,
            int pageIndex, int pageSize);
    
    /**
      * 分页查询PersonInfo列表，传入排序字段
      * auto generate
      * <功能详细描述>
      * @param params
      * @param pageIndex
      * @param pageSize
      * @param orderList
      * @return [参数说明]
      * 
      * @return PagedList<PersonInfo> [返回类型说明]
      * @exception throws [异常类型] [异常说明]
      * @see [类、类#方法、类#成员]
     */
    //auto generate
    public PagedList<PersonInfo> queryPersonInfoPagedList(Map<String, Object> params,
            int pageIndex, int pageSize, List<Order> orderList);
    
    
    /**
      * 更新PersonInfo实体，
      * auto generate
      * 1、传入PersonInfo中主键不能为空
      * <功能详细描述>
      * @param updatePersonInfoRowMap
      * @return [参数说明]
      * 
      * @return int [返回类型说明]
      * @exception throws [异常类型] [异常说明]
      * @see [类、类#方法、类#成员]
     */
    public int updatePersonInfo(Map<String, Object> updateRowMap);
}
