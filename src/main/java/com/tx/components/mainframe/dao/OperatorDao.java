/*
 * 描          述:  <描述>
 * 修  改   人:  PengQingyang
 * 修改时间:  2012-12-1
 * <修改描述:>
 */
package com.tx.components.mainframe.dao;

import java.util.List;
import java.util.Map;

import com.tx.components.mainframe.model.Operator;
import com.tx.core.paged.model.PagedList;

/**
 * <功能简述>
 * <功能详细描述>
 * 
 * @author  PengQingyang
 * @version  [版本号, 2012-12-1]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public interface OperatorDao {
    
    /**
      *<查找当前操作人员>
      *<功能详细描述>
      * @param condition
      * @return [参数说明]
      * 
      * @return Operator [返回类型说明]
      * @exception throws [异常类型] [异常说明]
      * @see [类、类#方法、类#成员]
     */
    Operator findOperator(Operator condition);
    
    /**
      *<增加操作人员>
      *<功能详细描述>
      * @param operator [参数说明]
      * 
      * @return void [返回类型说明]
      * @exception throws [异常类型] [异常说明]
      * @see [类、类#方法、类#成员]
     */
    void addOperator(Operator operator);
    
    /**
      *<查询操作人员列表>
      *<功能详细描述>
      * @param queryCondition
      * @return [参数说明]
      * 
      * @return List<Operator> [返回类型说明]
      * @exception throws [异常类型] [异常说明]
      * @see [类、类#方法、类#成员]
     */
    List<Operator> queryOperatorList(Map<String, Object> queryCondition);
    
    /**
      *<分页查询操作人员列表>
      *<功能详细描述>
      * @param paraObj
      * @param pageIndex
      * @param pageSize
      * @return [参数说明]
      * 
      * @return PagedList<Operator> [返回类型说明]
      * @exception throws [异常类型] [异常说明]
      * @see [类、类#方法、类#成员]
     */
    PagedList<Operator> queryOperatorPagedList(Map<String, Object> paraObj,
            int pageIndex, int pageSize);
    
    /**
      *<删除操作人员>
      *<功能详细描述>
      * @param condition
      * @return [参数说明]
      * 
      * @return int [返回类型说明]
      * @exception throws [异常类型] [异常说明]
      * @see [类、类#方法、类#成员]
     */
    int delOperator(Operator condition);
    
    /**
      *<更新操作人员>
      *<功能详细描述>
      * @param condition
      * @return [参数说明]
      * 
      * @return int [返回类型说明]
      * @exception throws [异常类型] [异常说明]
      * @see [类、类#方法、类#成员]
     */
    int updateOperator(Operator condition);
}
