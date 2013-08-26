/*
 * 描          述:  <描述>
 * 修  改   人:  
 * 修改时间:  
 * <修改描述:>
 */
package com.tx.component.mainframe.dao.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.tx.component.mainframe.dao.DistrictDao;
import com.tx.component.mainframe.model.District;
import com.tx.core.mybatis.model.Order;
import com.tx.core.mybatis.support.MyBatisDaoSupport;
import com.tx.core.paged.model.PagedList;

/**
 * District持久层
 * <功能详细描述>
 * 
 * @author  
 * @version  [版本号, 2012-12-11]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
@Component("districtDao")
public class DistrictDaoImpl implements DistrictDao {
    
    @Resource(name = "myBatisDaoSupport")
    private MyBatisDaoSupport myBatisDaoSupport;
    
    /**
     * @param condition
     */
    @Override
    public void insertDistrict(District condition) {
        this.myBatisDaoSupport.insertUseUUID("district.insertDistrict", condition, "id");
    }
    
    /**
     * @param condition
     * @return
     */
    @Override
    public int deleteDistrict(District condition) {
        return this.myBatisDaoSupport.delete("district.deleteDistrict", condition);
    }
    
    /**
     * @param condition
     * @return
     */
    @Override
    public District findDistrict(District condition) {
        return this.myBatisDaoSupport.<District> find("district.findDistrict", condition);
    }
    
    /**
     * @param params
     * @return
     */
    @Override
    public List<District> queryDistrictList(Map<String, Object> params) {
        return this.myBatisDaoSupport.<District> queryList("district.queryDistrict",
                params);
    }
    
    /**
     * @param params
     * @param orderList
     * @return
     */
    @Override
    public List<District> queryDistrictList(Map<String, Object> params,
            List<Order> orderList) {
        return this.myBatisDaoSupport.<District> queryList("district.queryDistrict",
                params,
                orderList);
    }
    
    /**
     * @param params
     * @return
     */
    @Override
    public int countDistrict(Map<String, Object> params) {
        return this.myBatisDaoSupport.<Integer> find("district.queryDistrictCount",
                params);
    }
    
    /**
     * @param params
     * @param pageIndex
     * @param pageSize
     * @return
     */
    @Override
    public PagedList<District> queryDistrictPagedList(Map<String, Object> params,
            int pageIndex, int pageSize) {
        return this.myBatisDaoSupport.<District> queryPagedList("district.queryDistrict",
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
    public PagedList<District> queryDistrictPagedList(Map<String, Object> params,
            int pageIndex, int pageSize, List<Order> orderList) {
        return this.myBatisDaoSupport.<District> queryPagedList("district.queryDistrict",
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
    public int updateDistrict(Map<String, Object> updateRowMap) {
        return this.myBatisDaoSupport.update("district.updateDistrict", updateRowMap);
    }
}
