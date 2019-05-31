/*
 * 描          述:  <描述>
 * 修  改   人:  
 * 修改时间:  
 * <修改描述:>
 */
package com.tx.local.clientinfo.dao.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.tx.local.clientinfo.dao.ClientPromotionChannelDao;
import com.tx.local.clientinfo.model.ClientPromotionChannel;
import com.tx.core.mybatis.support.MyBatisDaoSupport;
import com.tx.core.paged.model.PagedList;

/**
 * ClientPromotionChannel持久层
 * <功能详细描述>
 * 
 * @author  
 * @version  [版本号, 2012-12-11]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
@Component("clientPromotionChannelDao")
public class ClientPromotionChannelDaoImpl implements ClientPromotionChannelDao {
    
    @Resource(name = "myBatisDaoSupport")
    private MyBatisDaoSupport myBatisDaoSupport;
    
    /**
     * @param condition
     */
    @Override
    public void insert(ClientPromotionChannel clientPromotionChannel) {
        this.myBatisDaoSupport.insertUseUUID("clientPromotionChannel.insert", clientPromotionChannel, "id");
    }
    
    /**
     * @param condition
     */
    @Override
    public void batchInsert(List<ClientPromotionChannel> clientPromotionChannel){
        this.myBatisDaoSupport.batchInsertUseUUID("clientPromotionChannel.insert", clientPromotionChannel, "id",true);
    }
    
    /**
     * @param condition
     * @return
     */
    @Override
    public int delete(ClientPromotionChannel clientPromotionChannel) {
        return this.myBatisDaoSupport.delete("clientPromotionChannel.delete", clientPromotionChannel);
    }
    
    /**
     * @param updateRowMap
     * @return
     */
    @Override
    public int update(Map<String, Object> updateRowMap) {
        return this.myBatisDaoSupport.update("clientPromotionChannel.update", updateRowMap);
    }
    
    /**
     * @param condition
     */
    @Override
    public void batchUpdate(List<Map<String,Object>> updateRowMapList){
        this.myBatisDaoSupport.batchUpdate("clientPromotionChannel.update", updateRowMapList,true);
    }
    
    /**
     * @param condition
     * @return
     */
    @Override
    public ClientPromotionChannel find(ClientPromotionChannel clientPromotionChannel) {
        return this.myBatisDaoSupport.<ClientPromotionChannel> find("clientPromotionChannel.find", clientPromotionChannel);
    }
    
    /**
     * @param params
     * @return
     */
    @Override
    public List<ClientPromotionChannel> queryList(Map<String, Object> params) {
        return this.myBatisDaoSupport.<ClientPromotionChannel> queryList("clientPromotionChannel.query",
                params);
    }
    
    /**
     * @param params
     * @return
     */
    @Override
    public int count(Map<String, Object> params) {
        return this.myBatisDaoSupport.<Integer> find("clientPromotionChannel.queryCount",
                params);
    }
    
    /**
     * @param params
     * @param pageIndex
     * @param pageSize
     * @return
     */
    @Override
    public PagedList<ClientPromotionChannel> queryPagedList(Map<String, Object> params,
            int pageIndex, int pageSize) {
        return this.myBatisDaoSupport.<ClientPromotionChannel> queryPagedList("clientPromotionChannel.query",
                params,
                pageIndex,
                pageSize);
    }
}
