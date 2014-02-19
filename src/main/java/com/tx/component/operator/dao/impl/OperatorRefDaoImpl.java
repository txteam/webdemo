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

import com.tx.component.operator.dao.OperatorRefDao;
import com.tx.component.operator.model.OperatorRef;
import com.tx.core.mybatis.model.Order;
import com.tx.core.mybatis.support.MyBatisDaoSupport;
import com.tx.core.paged.model.PagedList;

/**
 * OperatorRef持久层
 * <功能详细描述>
 * 
 * @author  
 * @version  [版本号, 2012-12-11]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
@Component("operatorRefDao")
public class OperatorRefDaoImpl implements OperatorRefDao {
    
    @Resource(name = "myBatisDaoSupport")
    private MyBatisDaoSupport myBatisDaoSupport;
    
    /**
     * @param condition
     */
    @Override
    public void insertOperatorRefHis(OperatorRef condition) {
        this.myBatisDaoSupport.insert("operatorRef.insertOperatorRefHis",
                condition);
    }
    
    /**
     * @param operatorRefList
     */
    @Override
    public void batchInsertOperatorRefHis(List<OperatorRef> operatorRefList) {
        this.myBatisDaoSupport.batchInsert("operatorRef.insertOperatorRefHis",
                operatorRefList,
                true);
    }
    
    /**
     * @param operatorRefList
     */
    @Override
    public void batchInsertOperatorRef(List<OperatorRef> operatorRefList) {
        this.myBatisDaoSupport.batchInsert("operatorRef.insertOperatorRef",
                operatorRefList,
                true);
    }
    
    /**
     * @param condition
     */
    @Override
    public void insertOperatorRef(OperatorRef condition) {
        this.myBatisDaoSupport.insert("operatorRef.insertOperatorRef",
                condition);
    }
    
    /**
     * @param condition
     * @return
     */
    @Override
    public int deleteOperatorRef(OperatorRef condition) {
        return this.myBatisDaoSupport.delete("operatorRef.deleteOperatorRef",
                condition);
    }
    
    /**
     * @param operatorRefList
     */
    @Override
    public void batchDeleteOperatorRef(List<OperatorRef> operatorRefList) {
        this.myBatisDaoSupport.batchDelete("operatorRef.deleteOperatorRef",
                operatorRefList,
                true);
    }
    
    /**
     * @param condition
     * @return
     */
    @Override
    public OperatorRef findOperatorRef(OperatorRef condition) {
        return this.myBatisDaoSupport.<OperatorRef> find("operatorRef.findOperatorRef",
                condition);
    }
    
    /**
     * @param params
     * @return
     */
    @Override
    public List<OperatorRef> queryOperatorRefList(Map<String, Object> params) {
        return this.myBatisDaoSupport.<OperatorRef> queryList("operatorRef.queryOperatorRef",
                params);
    }
    
    /**
     * @param params
     * @param orderList
     * @return
     */
    @Override
    public List<OperatorRef> queryOperatorRefList(Map<String, Object> params,
            List<Order> orderList) {
        return this.myBatisDaoSupport.<OperatorRef> queryList("operatorRef.queryOperatorRef",
                params,
                orderList);
    }
    
    /**
     * @param params
     * @return
     */
    @Override
    public int countOperatorRef(Map<String, Object> params) {
        return this.myBatisDaoSupport.<Integer> find("operatorRef.queryOperatorRefCount",
                params);
    }
    
    /**
     * @param params
     * @param pageIndex
     * @param pageSize
     * @return
     */
    @Override
    public PagedList<OperatorRef> queryOperatorRefPagedList(
            Map<String, Object> params, int pageIndex, int pageSize) {
        return this.myBatisDaoSupport.<OperatorRef> queryPagedList("operatorRef.queryOperatorRef",
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
    public PagedList<OperatorRef> queryOperatorRefPagedList(
            Map<String, Object> params, int pageIndex, int pageSize,
            List<Order> orderList) {
        return this.myBatisDaoSupport.<OperatorRef> queryPagedList("operatorRef.queryOperatorRef",
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
    public int updateOperatorRef(Map<String, Object> updateRowMap) {
        return this.myBatisDaoSupport.update("operatorRef.updateOperatorRef",
                updateRowMap);
    }
}
