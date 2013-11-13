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

import com.tx.component.operator.dao.OrganizationDao;
import com.tx.component.operator.model.Organization;
import com.tx.core.mybatis.model.Order;
import com.tx.core.mybatis.support.MyBatisDaoSupport;
import com.tx.core.paged.model.PagedList;

/**
 * Organization持久层
 * <功能详细描述>
 * 
 * @author  
 * @version  [版本号, 2012-12-11]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
@Component("organizationDao")
public class OrganizationDaoImpl implements OrganizationDao {
    
    @Resource(name = "myBatisDaoSupport")
    private MyBatisDaoSupport myBatisDaoSupport;
    
    /**
     * @param condition
     */
    @Override
    public void insertOrganization(Organization organization) {
        this.myBatisDaoSupport.insert("organization.insertOrganization",
                organization);
    }
    
    /**
     * @param condition
     */
    @Override
    public void insertOrganizationToHis(Organization organization) {
        this.myBatisDaoSupport.insert("organization.insertOrganizationToHis",
                organization);
    }
    
    /**
     * @param condition
     * @return
     */
    @Override
    public int deleteOrganization(Organization condition) {
        return this.myBatisDaoSupport.delete("organization.deleteOrganization",
                condition);
    }
    
    /**
     * @param condition
     * @return
     */
    @Override
    public Organization findOrganization(Organization condition) {
        return this.myBatisDaoSupport.<Organization> find("organization.findOrganization",
                condition);
    }
    
    /**
     * @param params
     * @return
     */
    @Override
    public List<Organization> queryOrganizationList(Map<String, Object> params) {
        return this.myBatisDaoSupport.<Organization> queryList("organization.queryOrganization",
                params);
    }
    
    /**
     * @param params
     * @param orderList
     * @return
     */
    @Override
    public List<Organization> queryOrganizationList(Map<String, Object> params,
            List<Order> orderList) {
        return this.myBatisDaoSupport.<Organization> queryList("organization.queryOrganization",
                params,
                orderList);
    }
    
    /**
     * @param params
     * @return
     */
    @Override
    public int countOrganization(Map<String, Object> params) {
        return this.myBatisDaoSupport.<Integer> find("organization.queryOrganizationCount",
                params);
    }
    
    /**
     * @param params
     * @param pageIndex
     * @param pageSize
     * @return
     */
    @Override
    public PagedList<Organization> queryOrganizationPagedList(
            Map<String, Object> params, int pageIndex, int pageSize) {
        return this.myBatisDaoSupport.<Organization> queryPagedList("organization.queryOrganization",
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
    public PagedList<Organization> queryOrganizationPagedList(
            Map<String, Object> params, int pageIndex, int pageSize,
            List<Order> orderList) {
        return this.myBatisDaoSupport.<Organization> queryPagedList("organization.queryOrganization",
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
    public int updateOrganization(Map<String, Object> updateRowMap) {
        return this.myBatisDaoSupport.update("organization.updateOrganization",
                updateRowMap);
    }
}
