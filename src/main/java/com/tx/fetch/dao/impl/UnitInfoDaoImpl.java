/*
 * 描          述:  <描述>
 * 修  改   人:  
 * 修改时间:  
 * <修改描述:>
 */
package com.tx.fetch.dao.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.tx.fetch.dao.UnitInfoDao;
import com.tx.fetch.model.UnitInfo;
import com.tx.core.mybatis.model.Order;
import com.tx.core.mybatis.support.MyBatisDaoSupport;
import com.tx.core.paged.model.PagedList;

/**
 * UnitInfo持久层
 * <功能详细描述>
 * 
 * @author  
 * @version  [版本号, 2012-12-11]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
@Component("unitInfoDao")
public class UnitInfoDaoImpl implements UnitInfoDao {
    
    @Resource(name = "myBatisDaoSupport")
    private MyBatisDaoSupport myBatisDaoSupport;
    
    /**
     * @param condition
     */
    @Override
    public void batchInsertUnitInfo(List<UnitInfo> condition) {
        this.myBatisDaoSupport.batchInsertUseUUID("unitInfo.insertUnitInfo",
                condition,
                "id",
                true);
    }
    
    /**
     * @param condition
     */
    @Override
    public void batchUpdateUnitInfo(List<Map<String, Object>> updateRowMapList) {
        this.myBatisDaoSupport.batchUpdate("unitInfo.updateUnitInfo",
                updateRowMapList,
                true);
    }
    
    /**
     * @param condition
     */
    @Override
    public void insertUnitInfo(UnitInfo condition) {
        this.myBatisDaoSupport.insertUseUUID("unitInfo.insertUnitInfo",
                condition,
                "id");
    }
    
    /**
     * @param condition
     * @return
     */
    @Override
    public int deleteUnitInfo(UnitInfo condition) {
        return this.myBatisDaoSupport.delete("unitInfo.deleteUnitInfo",
                condition);
    }
    
    /**
     * @param condition
     * @return
     */
    @Override
    public UnitInfo findUnitInfo(UnitInfo condition) {
        return this.myBatisDaoSupport.<UnitInfo> find("unitInfo.findUnitInfo",
                condition);
    }
    
    /**
     * @param params
     * @return
     */
    @Override
    public List<UnitInfo> queryUnitInfoList(Map<String, Object> params) {
        return this.myBatisDaoSupport.<UnitInfo> queryList("unitInfo.queryUnitInfo",
                params);
    }
    
    /**
     * @param params
     * @param orderList
     * @return
     */
    @Override
    public List<UnitInfo> queryUnitInfoList(Map<String, Object> params,
            List<Order> orderList) {
        return this.myBatisDaoSupport.<UnitInfo> queryList("unitInfo.queryUnitInfo",
                params,
                orderList);
    }
    
    /**
     * @param params
     * @return
     */
    @Override
    public int countUnitInfo(Map<String, Object> params) {
        return this.myBatisDaoSupport.<Integer> find("unitInfo.queryUnitInfoCount",
                params);
    }
    
    /**
     * @param params
     * @param pageIndex
     * @param pageSize
     * @return
     */
    @Override
    public PagedList<UnitInfo> queryUnitInfoPagedList(
            Map<String, Object> params, int pageIndex, int pageSize) {
        return this.myBatisDaoSupport.<UnitInfo> queryPagedList("unitInfo.queryUnitInfo",
                params,
                pageIndex,
                pageSize);
    }
    
    /**
     * @param params
     * @param pageIndex
     * @param pageSize
     * @return
     */
    @Override
    public PagedList<UnitInfo> queryDistinctUnitInfoPagedList(
            Map<String, Object> params, int pageIndex, int pageSize) {
        return this.myBatisDaoSupport.<UnitInfo> queryPagedList("unitInfo.queryDistinctUnitInfo",
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
    public PagedList<UnitInfo> queryUnitInfoPagedList(
            Map<String, Object> params, int pageIndex, int pageSize,
            List<Order> orderList) {
        return this.myBatisDaoSupport.<UnitInfo> queryPagedList("unitInfo.queryUnitInfo",
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
    public int updateUnitInfo(Map<String, Object> updateRowMap) {
        return this.myBatisDaoSupport.update("unitInfo.updateUnitInfo",
                updateRowMap);
    }
    
    /**
     * @return 返回 myBatisDaoSupport
     */
    public MyBatisDaoSupport getMyBatisDaoSupport() {
        return myBatisDaoSupport;
    }
    
    /**
     * @param 对myBatisDaoSupport进行赋值
     */
    public void setMyBatisDaoSupport(MyBatisDaoSupport myBatisDaoSupport) {
        this.myBatisDaoSupport = myBatisDaoSupport;
    }
}
