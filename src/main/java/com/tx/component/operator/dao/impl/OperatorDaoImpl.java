/*
 * 描          述:  <描述>
 * 修  改   人:  
 * 修改时间:  
 * <修改描述:>
 */
package com.tx.component.operator.dao.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.tx.component.operator.dao.OperatorDao;
import com.tx.component.operator.model.Operator;
import com.tx.core.mybatis.model.Order;
import com.tx.core.mybatis.support.MyBatisDaoSupport;
import com.tx.core.paged.model.PagedList;

/**
 * Operator持久层
 * <功能详细描述>
 * 
 * @author  
 * @version  [版本号, 2012-12-11]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
@Component("operatorDao")
public class OperatorDaoImpl implements OperatorDao {
    
    @Resource(name = "myBatisDaoSupport")
    private MyBatisDaoSupport myBatisDaoSupport;
    
    /**
     * @param condition
     */
    @Override
    public void insertOperator(Operator condition) {
        this.myBatisDaoSupport.insertUseUUID("operator.insertOperator", condition, "id");
    }
    
    /**
     * @param condition
     * @return
     */
    @Override
    public int deleteOperator(Operator condition) {
        return this.myBatisDaoSupport.delete("operator.deleteOperator", condition);
    }
    
    /**
     * @param condition
     * @return
     */
    @Override
    public Operator findOperator(Operator condition) {
        return this.myBatisDaoSupport.<Operator> find("operator.findOperator", condition);
    }
    
    /**
     * @param params
     * @return
     */
    @Override
    public List<Operator> queryOperatorList(Map<String, Object> params) {
        return this.myBatisDaoSupport.<Operator> queryList("operator.queryOperator",
                params);
    }
    
    /**
     * @param params
     * @param orderList
     * @return
     */
    @Override
    public List<Operator> queryOperatorList(Map<String, Object> params,
            List<Order> orderList) {
        return this.myBatisDaoSupport.<Operator> queryList("operator.queryOperator",
                params,
                orderList);
    }
    
    /**
     * @param params
     * @return
     */
    @Override
    public int countOperator(Map<String, Object> params) {
        return this.myBatisDaoSupport.<Integer> find("operator.queryOperatorCount",
                params);
    }
    
    /**
     * @param params
     * @param pageIndex
     * @param pageSize
     * @return
     */
    @Override
    public PagedList<Operator> queryOperatorPagedList(Map<String, Object> params,
            int pageIndex, int pageSize) {
        return this.myBatisDaoSupport.<Operator> queryPagedList("operator.queryOperator",
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
    public PagedList<Operator> queryOperatorPagedList(Map<String, Object> params,
            int pageIndex, int pageSize, List<Order> orderList) {
        return this.myBatisDaoSupport.<Operator> queryPagedList("operator.queryOperator",
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
    public int updateOperator(Map<String, Object> updateRowMap) {
        return this.myBatisDaoSupport.update("operator.updateOperator", updateRowMap);
    }
}
