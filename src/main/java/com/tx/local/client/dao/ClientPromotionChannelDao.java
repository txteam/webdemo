/*
 * 描          述:  <描述>
 * 修  改   人:  
 * 修改时间:  
 * <修改描述:>
 */
package com.tx.local.client.dao;

import java.util.List;
import java.util.Map;

import com.tx.core.paged.model.PagedList;
import com.tx.local.client.model.ClientPromotionChannel;

/**
 * ClientPromotionChannel持久层
 * <功能详细描述>
 * 
 * @author  
 * @version  [版本号, ]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public interface ClientPromotionChannelDao {

    /**
     * 插入ClientPromotionChannel对象实体
     * 1、auto generate
     * <功能详细描述>
     * @param condition [参数说明]
     * 
     * @return void [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    //auto generate
    public void insert(ClientPromotionChannel clientPromotionChannel);
    
    /**
     * 批量插入ClientPromotionChannel对象实体
     * 1、auto generate
     * <功能详细描述>
     * @param condition [参数说明]
     * 
     * @return void [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    //auto generate
    public void batchInsert(List<ClientPromotionChannel> clientPromotionChannelList); 
    
    /**
     * 删除ClientPromotionChannel对象
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
    public int delete(ClientPromotionChannel clientPromotionChannel);
    
    /**
     * 更新ClientPromotionChannel实体，
     * auto generate
     * 1、传入ClientPromotionChannel中主键不能为空
     * <功能详细描述>
     * @param updateClientPromotionChannelRowMap
     * @return [参数说明]
     * 
     * @return int [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    //auto generate
    public int update(Map<String, Object> updateRowMap);
    
    /**
     * 批量更新ClientPromotionChannel实体，
     * auto generate
     * 1、传入ClientPromotionChannel中主键不能为空
     * <功能详细描述>
     * @param updateClientPromotionChannelRowMap
     * @return [参数说明]
     * 
     * @return int [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    //auto generate
    public void batchUpdate(List<Map<String,Object>> updateRowMapList);
    
    /**
     * 查询ClientPromotionChannel实体
     * auto generate
     * <功能详细描述>
     * @param condition
     * @return [参数说明]
     * 
     * @return ClientPromotionChannel [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    //auto generate
    public ClientPromotionChannel find(ClientPromotionChannel clientPromotionChannel);
    
    /**
     * 根据条件查询ClientPromotionChannel列表
     * auto generate
     * <功能详细描述>
     * @param params
     * @return [参数说明]
     * 
     * @return List<ClientPromotionChannel> [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    //auto generate
    public List<ClientPromotionChannel> queryList(Map<String, Object> params);
    
    /**
     * 根据条件查询ClientPromotionChannel列表总数
     * auto generated
     * <功能详细描述>
     * @param params
     * @return [参数说明]
     * 
     * @return int [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    //auto generate
    public int count(Map<String, Object> params);
    
    /**
     * 分页查询ClientPromotionChannel列表
     * auto generate
     * <功能详细描述>
     * @param params
     * @param pageIndex
     * @param pageSize
     * @return [参数说明]
     * 
     * @return PagedList<ClientPromotionChannel> [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    //auto generate
    public PagedList<ClientPromotionChannel> queryPagedList(Map<String, Object> params,
            int pageIndex, int pageSize);
    
}
