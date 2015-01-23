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

import com.tx.component.attachment.dao.AttachmentDao;
import com.tx.component.attachment.model.Attachment;
import com.tx.core.mybatis.model.Order;
import com.tx.core.mybatis.support.MyBatisDaoSupport;
import com.tx.core.paged.model.PagedList;

/**
 * Attachment持久层
 * <功能详细描述>
 * 
 * @author  
 * @version  [版本号, 2012-12-11]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
@Component("attachmentDao")
public class AttachmentDaoImpl implements AttachmentDao {
    
    @Resource(name = "myBatisDaoSupport")
    private MyBatisDaoSupport myBatisDaoSupport;
    
    /**
     * @param condition
     */
    @Override
    public void batchInsertAttachment(List<Attachment> condition){
        this.myBatisDaoSupport.batchInsertUseUUID("attachment.insertAttachment", condition, "id",true);
    }
    
    /**
     * @param condition
     */
    @Override
    public void batchUpdateAttachment(List<Map<String,Object>> updateRowMapList){
        this.myBatisDaoSupport.batchUpdate("attachment.updateAttachment", updateRowMapList,true);
    }
    
    /**
     * @param condition
     */
    @Override
    public void insertAttachment(Attachment condition) {
        this.myBatisDaoSupport.insertUseUUID("attachment.insertAttachment", condition, "id");
    }
    
    /**
     * @param condition
     * @return
     */
    @Override
    public int deleteAttachment(Attachment condition) {
        return this.myBatisDaoSupport.delete("attachment.deleteAttachment", condition);
    }
    
    /**
     * @param condition
     * @return
     */
    @Override
    public Attachment findAttachment(Attachment condition) {
        return this.myBatisDaoSupport.<Attachment> find("attachment.findAttachment", condition);
    }
    
    /**
     * @param params
     * @return
     */
    @Override
    public List<Attachment> queryAttachmentList(Map<String, Object> params) {
        return this.myBatisDaoSupport.<Attachment> queryList("attachment.queryAttachment",
                params);
    }
    
    /**
     * @param params
     * @param orderList
     * @return
     */
    @Override
    public List<Attachment> queryAttachmentList(Map<String, Object> params,
            List<Order> orderList) {
        return this.myBatisDaoSupport.<Attachment> queryList("attachment.queryAttachment",
                params,
                orderList);
    }
    
    /**
     * @param params
     * @return
     */
    @Override
    public int countAttachment(Map<String, Object> params) {
        return this.myBatisDaoSupport.<Integer> find("attachment.queryAttachmentCount",
                params);
    }
    
    /**
     * @param params
     * @param pageIndex
     * @param pageSize
     * @return
     */
    @Override
    public PagedList<Attachment> queryAttachmentPagedList(Map<String, Object> params,
            int pageIndex, int pageSize) {
        return this.myBatisDaoSupport.<Attachment> queryPagedList("attachment.queryAttachment",
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
    public PagedList<Attachment> queryAttachmentPagedList(Map<String, Object> params,
            int pageIndex, int pageSize, List<Order> orderList) {
        return this.myBatisDaoSupport.<Attachment> queryPagedList("attachment.queryAttachment",
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
    public int updateAttachment(Map<String, Object> updateRowMap) {
        return this.myBatisDaoSupport.update("attachment.updateAttachment", updateRowMap);
    }
}
