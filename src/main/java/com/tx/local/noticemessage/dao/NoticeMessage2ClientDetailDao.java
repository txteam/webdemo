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
import com.tx.local.noticemessage.model.NoticeMessage2ClientDetail;

/**
 * NoticeMessage2ClientDetail持久层
 * <功能详细描述>
 * 
 * @author  
 * @version  [版本号, ]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public interface NoticeMessage2ClientDetailDao {
    
    /**
      * 查询NoticeMessage2ClientDetail实体
      * auto generate
      * <功能详细描述>
      * @param condition
      * @return [参数说明]
      * 
      * @return NoticeMessage2ClientDetail [返回类型说明]
      * @exception throws [异常类型] [异常说明]
      * @see [类、类#方法、类#成员]
     */
    //auto generate
    public NoticeMessage2ClientDetail find(NoticeMessage2ClientDetail condition);
    
    /**
      * 根据条件查询NoticeMessage2ClientDetail列表
      * auto generate
      * <功能详细描述>
      * @param params
      * @return [参数说明]
      * 
      * @return List<NoticeMessage2ClientDetail> [返回类型说明]
      * @exception throws [异常类型] [异常说明]
      * @see [类、类#方法、类#成员]
     */
    //auto generate
    public List<NoticeMessage2ClientDetail> queryList(Map<String, Object> params);
    
    /**
      * 根据条件查询NoticeMessage2ClientDetail列表总数
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
      * 分页查询NoticeMessage2ClientDetail列表
      * auto generate
      * <功能详细描述>
      * @param params
      * @param pageIndex
      * @param pageSize
      * @return [参数说明]
      * 
      * @return PagedList<NoticeMessage2ClientDetail> [返回类型说明]
      * @exception throws [异常类型] [异常说明]
      * @see [类、类#方法、类#成员]
     */
    //auto generate
    public PagedList<NoticeMessage2ClientDetail> queryPagedList(
            Map<String, Object> params, int pageIndex, int pageSize);
}
