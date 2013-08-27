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

import com.tx.component.operator.dao.EmployeeInfoDao;
import com.tx.component.operator.model.EmployeeInfo;
import com.tx.core.mybatis.model.Order;
import com.tx.core.mybatis.support.MyBatisDaoSupport;
import com.tx.core.paged.model.PagedList;

/**
 * EmployeeInfo持久层
 * <功能详细描述>
 * 
 * @author  
 * @version  [版本号, 2012-12-11]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
@Component("employeeInfoDao")
public class EmployeeInfoDaoImpl implements EmployeeInfoDao {
    
    @Resource(name = "myBatisDaoSupport")
    private MyBatisDaoSupport myBatisDaoSupport;
    
    /**
     * @param condition
     */
    @Override
    public void insertEmployeeInfo(EmployeeInfo condition) {
        this.myBatisDaoSupport.insertUseUUID("employeeInfo.insertEmployeeInfo", condition, "id");
    }
    
    /**
     * @param condition
     * @return
     */
    @Override
    public int deleteEmployeeInfo(EmployeeInfo condition) {
        return this.myBatisDaoSupport.delete("employeeInfo.deleteEmployeeInfo", condition);
    }
    
    /**
     * @param condition
     * @return
     */
    @Override
    public EmployeeInfo findEmployeeInfo(EmployeeInfo condition) {
        return this.myBatisDaoSupport.<EmployeeInfo> find("employeeInfo.findEmployeeInfo", condition);
    }
    
    /**
     * @param params
     * @return
     */
    @Override
    public List<EmployeeInfo> queryEmployeeInfoList(Map<String, Object> params) {
        return this.myBatisDaoSupport.<EmployeeInfo> queryList("employeeInfo.queryEmployeeInfo",
                params);
    }
    
    /**
     * @param params
     * @param orderList
     * @return
     */
    @Override
    public List<EmployeeInfo> queryEmployeeInfoList(Map<String, Object> params,
            List<Order> orderList) {
        return this.myBatisDaoSupport.<EmployeeInfo> queryList("employeeInfo.queryEmployeeInfo",
                params,
                orderList);
    }
    
    /**
     * @param params
     * @return
     */
    @Override
    public int countEmployeeInfo(Map<String, Object> params) {
        return this.myBatisDaoSupport.<Integer> find("employeeInfo.queryEmployeeInfoCount",
                params);
    }
    
    /**
     * @param params
     * @param pageIndex
     * @param pageSize
     * @return
     */
    @Override
    public PagedList<EmployeeInfo> queryEmployeeInfoPagedList(Map<String, Object> params,
            int pageIndex, int pageSize) {
        return this.myBatisDaoSupport.<EmployeeInfo> queryPagedList("employeeInfo.queryEmployeeInfo",
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
    public PagedList<EmployeeInfo> queryEmployeeInfoPagedList(Map<String, Object> params,
            int pageIndex, int pageSize, List<Order> orderList) {
        return this.myBatisDaoSupport.<EmployeeInfo> queryPagedList("employeeInfo.queryEmployeeInfo",
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
    public int updateEmployeeInfo(Map<String, Object> updateRowMap) {
        return this.myBatisDaoSupport.update("employeeInfo.updateEmployeeInfo", updateRowMap);
    }
}
