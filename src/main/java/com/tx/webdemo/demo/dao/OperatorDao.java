/*
 * 描          述:  <描述>
 * 修  改   人:  PengQingyang
 * 修改时间:  2012-12-1
 * <修改描述:>
 */
package com.tx.webdemo.demo.dao;

import java.util.List;
import java.util.Map;

import com.tx.core.paged.model.PagedList;
import com.tx.webdemo.demo.model.Operator;

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
    
    Operator findOperator(Operator condition);
    
    void addOperator(Operator operator);
    
    List<Operator> queryOperatorList(Map<String, Object> queryCondition);
    
    PagedList<Operator> queryOperatorPagedList(Map<String, Object> paraObj,
            int pageIndex, int pageSize);
    
    int delOperator(Operator condition);
    
    int updateOperator(Operator condition);
}
