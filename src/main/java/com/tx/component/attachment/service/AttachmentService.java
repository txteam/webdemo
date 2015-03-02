/*
 * 描          述:  <描述>
 * 修  改   人:  
 * 修改时间:  
 * <修改描述:>
 */
package com.tx.component.attachment.service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.tx.component.attachment.dao.AttachmentDao;
import com.tx.component.attachment.model.Attachment;
import com.tx.core.exceptions.util.AssertUtils;
import com.tx.core.paged.model.PagedList;

/**
 * Attachment的业务层
 * <功能详细描述>
 * 
 * @author  
 * @version  [版本号, ]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
@Component("attachmentService")
public class AttachmentService {
    
    @SuppressWarnings("unused")
    private Logger logger = LoggerFactory.getLogger(AttachmentService.class);
    
    @Resource(name = "attachmentDao")
    private AttachmentDao attachmentDao;
    
    /**
      * 将attachment实例插入数据库中保存
      * 1、如果attachment为空时抛出参数为空异常
      * 2、如果attachment中部分必要参数为非法值时抛出参数不合法异常
     * <功能详细描述>
     * @param district [参数说明]
     * 
     * @return void [返回类型说明]
     * @exception throws
     * @see [类、类#方法、类#成员]
    */
    @Transactional
    public void insertAttachment(Attachment attachment) {
        //验证参数是否合法
        AssertUtils.notNull(attachment, "attachment is null.");
        
        Date now = new Date();
        attachment.setCreateDate(now);
        attachment.setLastUpdateDate(now);
        
        this.attachmentDao.insertAttachment(attachment);
    }
    
    /**
      * 批量插入附件对象<br/>
      * <功能详细描述>
      * @param attachmentList [参数说明]
      * 
      * @return void [返回类型说明]
      * @exception throws [异常类型] [异常说明]
      * @see [类、类#方法、类#成员]
     */
    @Transactional
    public void batchInsertAttachment(List<Attachment> attachmentList) {
        //验证参数是否合法
        AssertUtils.notEmpty(attachmentList, "attachmentList is null.");
        
        for (Attachment attachment : attachmentList) {
            Date now = new Date();
            attachment.setCreateDate(now);
            attachment.setLastUpdateDate(now);
        }
        
        this.attachmentDao.batchInsertAttachment(attachmentList);
    }
    
    /**
     * 根据id删除attachment实例
     * 1、如果入参数为空，则抛出异常
     * 2、执行删除后，将返回数据库中被影响的条数
     * @param id
     * @return 返回删除的数据条数，<br/>
     * 有些业务场景，如果已经被别人删除同样也可以认为是成功的
     * 这里讲通用生成的业务层代码定义为返回影响的条数
     * @return int [返回类型说明]
     * @exception throws 
     * @see [类、类#方法、类#成员]
    */
    @Transactional
    public int deleteById(String id) {
        AssertUtils.notEmpty(id, "id is empty.");
        
        Attachment condition = new Attachment();
        condition.setId(id);
        return this.attachmentDao.deleteAttachment(condition);
    }
    
    /**
      * 根据Id查询Attachment实体
      * 1、当id为empty时抛出异常
      * <功能详细描述>
      * @param id
      * @return [参数说明]
      * 
      * @return Attachment [返回类型说明]
      * @exception throws 可能存在数据库访问异常DataAccessException
      * @see [类、类#方法、类#成员]
     */
    public Attachment findAttachmentById(String id) {
        AssertUtils.notEmpty(id, "id is empty.");
        
        Attachment condition = new Attachment();
        condition.setId(id);
        
        Attachment res = this.attachmentDao.findAttachment(condition);
        return res;
    }
    
    /**
      * 根据Attachment实体列表
      * TODO:补充说明
      * 
      * <功能详细描述>
      * @return [参数说明]
      * 
      * @return List<Attachment> [返回类型说明]
      * @exception throws [异常类型] [异常说明]
      * @see [类、类#方法、类#成员]
     */
    public List<Attachment> queryAttachmentList(/*TODO:自己定义条件*/) {
        //TODO:判断条件合法性
        
        //TODO:生成查询条件
        Map<String, Object> params = new HashMap<String, Object>();
        
        //TODO:根据实际情况，填入排序字段等条件，根据是否需要排序，选择调用dao内方法
        List<Attachment> resList = this.attachmentDao.queryAttachmentList(params);
        
        return resList;
    }
    
    /**
     * 分页查询Attachment实体列表
     * TODO:补充说明
     * 
     * <功能详细描述>
     * @return [参数说明]
     * 
     * @return List<Attachment> [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
    */
    public PagedList<Attachment> queryAttachmentPagedList(
    /*TODO:自己定义条件*/int pageIndex, int pageSize) {
        //TODO:判断条件合法性
        
        //TODO:生成查询条件
        Map<String, Object> params = new HashMap<String, Object>();
        
        //TODO:根据实际情况，填入排序字段等条件，根据是否需要排序，选择调用dao内方法
        PagedList<Attachment> resPagedList = this.attachmentDao.queryAttachmentPagedList(params,
                pageIndex,
                pageSize);
        
        return resPagedList;
    }
    
    /**
      * 查询attachment列表总条数
      * TODO:补充说明
      * <功能详细描述>
      * @return [参数说明]
      * 
      * @return int [返回类型说明]
      * @exception throws [异常类型] [异常说明]
      * @see [类、类#方法、类#成员]
     */
    public int countAttachment(/*TODO:自己定义条件*/) {
        //TODO:判断条件合法性
        
        //TODO:生成查询条件
        Map<String, Object> params = new HashMap<String, Object>();
        
        //TODO:根据实际情况，填入排序字段等条件，根据是否需要排序，选择调用dao内方法
        int res = this.attachmentDao.countAttachment(params);
        
        return res;
    }
    
    /**
      * 根据id更新对象
      * <功能详细描述>
      * @param attachment
      * @return [参数说明]
      * 
      * @return boolean [返回类型说明]
      * @exception throws [异常类型] [异常说明]
      * @see [类、类#方法、类#成员]
     */
    @Transactional
    public boolean updateById(Attachment attachment) {
        //TODO:验证参数是否合法，必填字段是否填写，
        AssertUtils.notNull(attachment, "attachment is null.");
        AssertUtils.notEmpty(attachment.getId(), "attachment.id is empty.");
        
        //TODO:生成需要更新字段的hashMap
        Map<String, Object> updateRowMap = new HashMap<String, Object>();
        updateRowMap.put("id", attachment.getId());
        
        //TODO:需要更新的字段
        updateRowMap.put("deleteOperatorId", attachment.getDeleteOperatorId());
        updateRowMap.put("createOperatorId", attachment.getCreateOperatorId());
        updateRowMap.put("deleteDate", attachment.getDeleteDate());
        updateRowMap.put("nextId", attachment.getNextId());
        updateRowMap.put("serviceType", attachment.getServiceType());
        updateRowMap.put("lastUpdateOperatorId",
                attachment.getLastUpdateOperatorId());
        updateRowMap.put("lastUpdateDate", attachment.getLastUpdateDate());
        updateRowMap.put("fileId", attachment.getFileId());
        updateRowMap.put("parentId", attachment.getParentId());
        updateRowMap.put("filename", attachment.getFilename());
        updateRowMap.put("filenameExtension", attachment.getFilenameExtension());
        updateRowMap.put("createDate", attachment.getCreateDate());
        updateRowMap.put("preId", attachment.getPreId());
        
        int updateRowCount = this.attachmentDao.updateAttachment(updateRowMap);
        
        //TODO:如果需要大于1时，抛出异常并回滚，需要在这里修改
        return updateRowCount >= 1;
    }
}
