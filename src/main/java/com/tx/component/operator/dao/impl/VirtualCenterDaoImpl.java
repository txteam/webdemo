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

import com.tx.component.operator.dao.VirtualCenterDao;
import com.tx.component.operator.model.VirtualCenter;
import com.tx.core.mybatis.model.Order;
import com.tx.core.mybatis.support.MyBatisDaoSupport;
import com.tx.core.paged.model.PagedList;

/**
 * VirtualCenter持久层
 * <功能详细描述>
 * 
 * @author  
 * @version  [版本号, 2012-12-11]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
@Component("virtualCenterDao")
public class VirtualCenterDaoImpl implements VirtualCenterDao {
    
    @Resource(name = "myBatisDaoSupport")
    private MyBatisDaoSupport myBatisDaoSupport;
    
    /**
     * @param condition
     */
    @Override
    public void insertVirtualCenter(VirtualCenter condition) {
        this.myBatisDaoSupport.insertUseUUID("virtualCenter.insertVirtualCenter", condition, "id");
    }
    
    /**
     * @param condition
     * @return
     */
    @Override
    public int deleteVirtualCenter(VirtualCenter condition) {
        return this.myBatisDaoSupport.delete("virtualCenter.deleteVirtualCenter", condition);
    }
    
    /**
     * @param condition
     * @return
     */
    @Override
    public VirtualCenter findVirtualCenter(VirtualCenter condition) {
        return this.myBatisDaoSupport.<VirtualCenter> find("virtualCenter.findVirtualCenter", condition);
    }
    
    /**
     * @param params
     * @return
     */
    @Override
    public List<VirtualCenter> queryVirtualCenterList(Map<String, Object> params) {
        return this.myBatisDaoSupport.<VirtualCenter> queryList("virtualCenter.queryVirtualCenter",
                params);
    }
    
    /**
     * @param params
     * @param orderList
     * @return
     */
    @Override
    public List<VirtualCenter> queryVirtualCenterList(Map<String, Object> params,
            List<Order> orderList) {
        return this.myBatisDaoSupport.<VirtualCenter> queryList("virtualCenter.queryVirtualCenter",
                params,
                orderList);
    }
    
    /**
     * @param params
     * @return
     */
    @Override
    public int countVirtualCenter(Map<String, Object> params) {
        return this.myBatisDaoSupport.<Integer> find("virtualCenter.queryVirtualCenterCount",
                params);
    }
    
    /**
     * @param params
     * @param pageIndex
     * @param pageSize
     * @return
     */
    @Override
    public PagedList<VirtualCenter> queryVirtualCenterPagedList(Map<String, Object> params,
            int pageIndex, int pageSize) {
        return this.myBatisDaoSupport.<VirtualCenter> queryPagedList("virtualCenter.queryVirtualCenter",
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
    public PagedList<VirtualCenter> queryVirtualCenterPagedList(Map<String, Object> params,
            int pageIndex, int pageSize, List<Order> orderList) {
        return this.myBatisDaoSupport.<VirtualCenter> queryPagedList("virtualCenter.queryVirtualCenter",
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
    public int updateVirtualCenter(Map<String, Object> updateRowMap) {
        return this.myBatisDaoSupport.update("virtualCenter.updateVirtualCenter", updateRowMap);
    }
}
