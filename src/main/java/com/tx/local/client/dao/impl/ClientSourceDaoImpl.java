/*
 * 描          述:  <描述>
 * 修  改   人:  
 * 修改时间:  
 * <修改描述:>
 */
package com.tx.local.client.dao.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.tx.local.client.dao.ClientSourceDao;
import com.tx.local.client.model.ClientSource;
import com.tx.core.mybatis.support.MyBatisDaoSupport;
import com.tx.core.paged.model.PagedList;

/**
 * ClientSource持久层
 * <功能详细描述>
 * 
 * @author  
 * @version  [版本号, 2012-12-11]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
@Component("clientSourceDao")
public class ClientSourceDaoImpl implements ClientSourceDao {
    
    @Resource(name = "myBatisDaoSupport")
    private MyBatisDaoSupport myBatisDaoSupport;
    
    /**
     * @param condition
     */
    @Override
    public void insert(ClientSource clientSource) {
        this.myBatisDaoSupport.insertUseUUID("clientSource.insert", clientSource, "id");
    }
    
    /**
     * @param condition
     */
    @Override
    public void batchInsert(List<ClientSource> clientSource){
        this.myBatisDaoSupport.batchInsertUseUUID("clientSource.insert", clientSource, "id",true);
    }
    
    /**
     * @param condition
     * @return
     */
    @Override
    public int delete(ClientSource clientSource) {
        return this.myBatisDaoSupport.delete("clientSource.delete", clientSource);
    }
    
    /**
     * @param updateRowMap
     * @return
     */
    @Override
    public int update(Map<String, Object> updateRowMap) {
        return this.myBatisDaoSupport.update("clientSource.update", updateRowMap);
    }
    
    /**
     * @param condition
     */
    @Override
    public void batchUpdate(List<Map<String,Object>> updateRowMapList){
        this.myBatisDaoSupport.batchUpdate("clientSource.update", updateRowMapList,true);
    }
    
    /**
     * @param condition
     * @return
     */
    @Override
    public ClientSource find(ClientSource clientSource) {
        return this.myBatisDaoSupport.<ClientSource> find("clientSource.find", clientSource);
    }
    
    /**
     * @param params
     * @return
     */
    @Override
    public List<ClientSource> queryList(Map<String, Object> params) {
        return this.myBatisDaoSupport.<ClientSource> queryList("clientSource.query",
                params);
    }
    
    /**
     * @param params
     * @return
     */
    @Override
    public int count(Map<String, Object> params) {
        return this.myBatisDaoSupport.<Integer> find("clientSource.queryCount",
                params);
    }
    
    /**
     * @param params
     * @param pageIndex
     * @param pageSize
     * @return
     */
    @Override
    public PagedList<ClientSource> queryPagedList(Map<String, Object> params,
            int pageIndex, int pageSize) {
        return this.myBatisDaoSupport.<ClientSource> queryPagedList("clientSource.query",
                params,
                pageIndex,
                pageSize);
    }
}
