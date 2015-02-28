/*
 * 描          述:  <描述>
 * 修  改   人:  
 * 修改时间:  
 * <修改描述:>
 */
package com.tx.component.attachment.dao.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.tx.component.attachment.dao.AttachmentRefDao;
import com.tx.component.attachment.model.AttachmentRef;
import com.tx.core.mybatis.model.Order;
import com.tx.core.mybatis.support.MyBatisDaoSupport;
import com.tx.core.paged.model.PagedList;

/**
 * AttachmentRef持久层
 * <功能详细描述>
 * 
 * @author  
 * @version  [版本号, 2012-12-11]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
@Component("attachmentRefDao")
public class AttachmentRefDaoImpl implements AttachmentRefDao {
    
    @Resource(name = "myBatisDaoSupport")
    private MyBatisDaoSupport myBatisDaoSupport;
    
    /**
     * @param condition
     */
    @Override
    public void batchInsertAttachmentRef(List<AttachmentRef> condition){
        this.myBatisDaoSupport.batchInsertUseUUID("attachmentRef.insertAttachmentRef", condition, "id",true);
    }
    
    /**
     * @param condition
     */
    @Override
    public void batchUpdateAttachmentRef(List<Map<String,Object>> updateRowMapList){
        this.myBatisDaoSupport.batchUpdate("attachmentRef.updateAttachmentRef", updateRowMapList,true);
    }
    
    /**
     * @param condition
     */
    @Override
    public void insertAttachmentRef(AttachmentRef condition) {
        this.myBatisDaoSupport.insertUseUUID("attachmentRef.insertAttachmentRef", condition, "id");
    }
    
    /**
     * @param condition
     * @return
     */
    @Override
    public int deleteAttachmentRef(AttachmentRef condition) {
        return this.myBatisDaoSupport.delete("attachmentRef.deleteAttachmentRef", condition);
    }
    
    /**
     * @param condition
     * @return
     */
    @Override
    public AttachmentRef findAttachmentRef(AttachmentRef condition) {
        return this.myBatisDaoSupport.<AttachmentRef> find("attachmentRef.findAttachmentRef", condition);
    }
    
    /**
     * @param params
     * @return
     */
    @Override
    public List<AttachmentRef> queryAttachmentRefList(Map<String, Object> params) {
        return this.myBatisDaoSupport.<AttachmentRef> queryList("attachmentRef.queryAttachmentRef",
                params);
    }
    
    /**
     * @param params
     * @param orderList
     * @return
     */
    @Override
    public List<AttachmentRef> queryAttachmentRefList(Map<String, Object> params,
            List<Order> orderList) {
        return this.myBatisDaoSupport.<AttachmentRef> queryList("attachmentRef.queryAttachmentRef",
                params,
                orderList);
    }
    
    /**
     * @param params
     * @return
     */
    @Override
    public int countAttachmentRef(Map<String, Object> params) {
        return this.myBatisDaoSupport.<Integer> find("attachmentRef.queryAttachmentRefCount",
                params);
    }
    
    /**
     * @param params
     * @param pageIndex
     * @param pageSize
     * @return
     */
    @Override
    public PagedList<AttachmentRef> queryAttachmentRefPagedList(Map<String, Object> params,
            int pageIndex, int pageSize) {
        return this.myBatisDaoSupport.<AttachmentRef> queryPagedList("attachmentRef.queryAttachmentRef",
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
    public PagedList<AttachmentRef> queryAttachmentRefPagedList(Map<String, Object> params,
            int pageIndex, int pageSize, List<Order> orderList) {
        return this.myBatisDaoSupport.<AttachmentRef> queryPagedList("attachmentRef.queryAttachmentRef",
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
    public int updateAttachmentRef(Map<String, Object> updateRowMap) {
        return this.myBatisDaoSupport.update("attachmentRef.updateAttachmentRef", updateRowMap);
    }
}
