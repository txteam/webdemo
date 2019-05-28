/*
 * 描          述:  <描述>
 * 修  改   人:  
 * 修改时间:  
 * <修改描述:>
 */
package com.tx.local.noticemessage.dao;

import java.util.List;
import java.util.Map;

import com.tx.core.paged.model.PagedList;
import com.tx.local.noticemessage.model.NoticeMessage2Client;

/**
 * NoticeMessage2Client持久层
 * <功能详细描述>
 * 
 * @author  
 * @version  [版本号, ]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public interface NoticeMessage2ClientDao {
    
    /**
      * 批量插入NoticeMessage2Client对象实体
      * 1、auto generate
      * <功能详细描述>
      * @param condition [参数说明]
      * 
      * @return void [返回类型说明]
      * @exception throws [异常类型] [异常说明]
      * @see [类、类#方法、类#成员]
     */
    //auto generate
    public void batchInsert(List<NoticeMessage2Client> condition);
    
    /**
      * 批量更新NoticeMessage2Client实体，
      * auto generate
      * 1、传入NoticeMessage2Client中主键不能为空
      * <功能详细描述>
      * @param updateNoticeMessage2ClientRowMap
      * @return [参数说明]
      * 
      * @return int [返回类型说明]
      * @exception throws [异常类型] [异常说明]
      * @see [类、类#方法、类#成员]
     */
    public void batchUpdate(List<Map<String, Object>> updateRowMapList);
    
    /**
      * 插入NoticeMessage2Client对象实体
      * 1、auto generate
      * <功能详细描述>
      * @param condition [参数说明]
      * 
      * @return void [返回类型说明]
      * @exception throws [异常类型] [异常说明]
      * @see [类、类#方法、类#成员]
     */
    //auto generate
    public void insert(NoticeMessage2Client condition);
    
    /**
      * 删除NoticeMessage2Client对象
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
    public int delete(NoticeMessage2Client condition);
    
    /**
      * 查询NoticeMessage2Client实体
      * auto generate
      * <功能详细描述>
      * @param condition
      * @return [参数说明]
      * 
      * @return NoticeMessage2Client [返回类型说明]
      * @exception throws [异常类型] [异常说明]
      * @see [类、类#方法、类#成员]
     */
    //auto generate
    public NoticeMessage2Client find(NoticeMessage2Client condition);
    
    /**
      * 根据条件查询NoticeMessage2Client列表
      * auto generate
      * <功能详细描述>
      * @param params
      * @return [参数说明]
      * 
      * @return List<NoticeMessage2Client> [返回类型说明]
      * @exception throws [异常类型] [异常说明]
      * @see [类、类#方法、类#成员]
     */
    //auto generate
    public List<NoticeMessage2Client> queryList(Map<String, Object> params);
    
    /**
      * 根据条件查询NoticeMessage2Client列表总数
      * auto generated
      * <功能详细描述>
      * @param params
      * @return [参数说明]
      * 
      * @return int [返回类型说明]
      * @exception throws [异常类型] [异常说明]
      * @see [类、类#方法、类#成员]
     */
    public int count(Map<String, Object> params);
    
    /**
      * 分页查询NoticeMessage2Client列表
      * auto generate
      * <功能详细描述>
      * @param params
      * @param pageIndex
      * @param pageSize
      * @return [参数说明]
      * 
      * @return PagedList<NoticeMessage2Client> [返回类型说明]
      * @exception throws [异常类型] [异常说明]
      * @see [类、类#方法、类#成员]
     */
    //auto generate
    public PagedList<NoticeMessage2Client> queryPagedList(
            Map<String, Object> params, int pageIndex, int pageSize);
    
    /**
      * 更新NoticeMessage2Client实体，
      * auto generate
      * 1、传入NoticeMessage2Client中主键不能为空
      * <功能详细描述>
      * @param updateNoticeMessage2ClientRowMap
      * @return [参数说明]
      * 
      * @return int [返回类型说明]
      * @exception throws [异常类型] [异常说明]
      * @see [类、类#方法、类#成员]
     */
    public int update(Map<String, Object> updateRowMap);
}