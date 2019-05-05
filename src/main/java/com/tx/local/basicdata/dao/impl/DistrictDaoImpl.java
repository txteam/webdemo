/*
 * 描          述:  <描述>
 * 修  改   人:  
 * 修改时间:  
 * <修改描述:>
 */
package com.tx.local.basicdata.dao.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.tx.core.mybatis.support.MyBatisDaoSupport;
import com.tx.core.paged.model.PagedList;
import com.tx.local.basicdata.dao.DistrictDao;
import com.tx.local.basicdata.model.District;

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
    public void batchInsert(List<District> condition) {
        this.myBatisDaoSupport.batchInsertUseUUID("district.insertDistrict",
                condition,
                "id",
                true);
    }
    
    /**
     * @param condition
     */
    @Override
    public void batchUpdate(List<Map<String, Object>> updateRowMapList) {
        this.myBatisDaoSupport.batchUpdate("district.updateDistrict",
                updateRowMapList,
                true);
    }
    
    /**
     * @param condition
     */
    @Override
    public void insert(District condition) {
        this.myBatisDaoSupport.insertUseUUID("district.insertDistrict",
                condition,
                "id");
    }
    
    /**
     * @param condition
     * @return
     */
    @Override
    public int delete(District condition) {
        return this.myBatisDaoSupport.delete("district.deleteDistrict",
                condition);
    }
    
    /**
     * @param condition
     * @return
     */
    @Override
    public District find(District condition) {
        return this.myBatisDaoSupport.<District> find("district.findDistrict",
                condition);
    }
    
    /**
     * @param params
     * @return
     */
    @Override
    public List<District> queryList(Map<String, Object> params) {
        return this.myBatisDaoSupport
                .<District> queryList("district.queryDistrict", params);
    }
    
    /**
     * @param params
     * @return
     */
    @Override
    public int count(Map<String, Object> params) {
        return this.myBatisDaoSupport
                .<Integer> find("district.queryDistrictCount", params);
    }
    
    /**
     * @param params
     * @param pageIndex
     * @param pageSize
     * @return
     */
    @Override
    public PagedList<District> queryPagedList(Map<String, Object> params,
            int pageIndex, int pageSize) {
        return this.myBatisDaoSupport.<District> queryPagedList(
                "district.queryDistrict", params, pageIndex, pageSize);
    }
    
    /**
     * @param updateRowMap
     * @return
     */
    @Override
    public int update(Map<String, Object> updateRowMap) {
        return this.myBatisDaoSupport.update("district.updateDistrict",
                updateRowMap);
    }
    
    /**
     * @param 对myBatisDaoSupport进行赋值
     */
    public void setMyBatisDaoSupport(MyBatisDaoSupport myBatisDaoSupport) {
        this.myBatisDaoSupport = myBatisDaoSupport;
    }
}
