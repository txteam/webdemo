/*
 * 描          述:  <描述>
 * 修  改   人:  
 * 修改时间:  
 * <修改描述:>
 */
package com.tx.component.attachment.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.tx.component.attachment.dao.AttachmentRefDao;
import com.tx.component.attachment.model.AttachmentRef;
import com.tx.core.exceptions.util.AssertUtils;
import com.tx.core.paged.model.PagedList;

/**
 * AttachmentRef的业务层
 * <功能详细描述>
 * 
 * @author  
 * @version  [版本号, ]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
@Component("attachmentRefService")
public class AttachmentRefService {
    
    @SuppressWarnings("unused")
    private Logger logger = LoggerFactory.getLogger(AttachmentRefService.class);
    
    @Resource(name = "attachmentRefDao")
    private AttachmentRefDao attachmentRefDao;
    
    /**
      * 将attachmentRef实例插入数据库中保存
      * 1、如果attachmentRef为空时抛出参数为空异常
      * 2、如果attachmentRef中部分必要参数为非法值时抛出参数不合法异常
     * <功能详细描述>
     * @param district [参数说明]
     * 
     * @return void [返回类型说明]
     * @exception throws
     * @see [类、类#方法、类#成员]
    */
    @Transactional
    public void insertAttachmentRef(AttachmentRef attachmentRef) {
        //TODO:验证参数是否合法
        AssertUtils.notNull(attachmentRef, "attachmentRef is null.");
        AssertUtils.notEmpty(attachmentRef.getId(), "attachmentRef.id is empty.");
        
        //TODO: 设置默认数据
        
        this.attachmentRefDao.insertAttachmentRef(attachmentRef);
    }
      
     /**
      * 根据id删除attachmentRef实例
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
        
        AttachmentRef condition = new AttachmentRef();
        condition.setId(id);
        return this.attachmentRefDao.deleteAttachmentRef(condition);
    }
    
    /**
      * 根据Id查询AttachmentRef实体
      * 1、当id为empty时抛出异常
      * <功能详细描述>
      * @param id
      * @return [参数说明]
      * 
      * @return AttachmentRef [返回类型说明]
      * @exception throws 可能存在数据库访问异常DataAccessException
      * @see [类、类#方法、类#成员]
     */
    public AttachmentRef findAttachmentRefById(String id) {
        AssertUtils.notEmpty(id, "id is empty.");
        
        AttachmentRef condition = new AttachmentRef();
        condition.setId(id);
        
        AttachmentRef res = this.attachmentRefDao.findAttachmentRef(condition);
        return res;
    }
    
    /**
      * 根据AttachmentRef实体列表
      * TODO:补充说明
      * 
      * <功能详细描述>
      * @return [参数说明]
      * 
      * @return List<AttachmentRef> [返回类型说明]
      * @exception throws [异常类型] [异常说明]
      * @see [类、类#方法、类#成员]
     */
    public List<AttachmentRef> queryAttachmentRefList(/*TODO:自己定义条件*/) {
        //TODO:判断条件合法性
        
        //TODO:生成查询条件
        Map<String, Object> params = new HashMap<String, Object>();
        
        //TODO:根据实际情况，填入排序字段等条件，根据是否需要排序，选择调用dao内方法
        List<AttachmentRef> resList = this.attachmentRefDao.queryAttachmentRefList(params);
        
        return resList;
    }
    
    /**
     * 分页查询AttachmentRef实体列表
     * TODO:补充说明
     * 
     * <功能详细描述>
     * @return [参数说明]
     * 
     * @return List<AttachmentRef> [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
    */
    public PagedList<AttachmentRef> queryAttachmentRefPagedList(/*TODO:自己定义条件*/int pageIndex,
            int pageSize) {
        //TODO:判断条件合法性
        
        //TODO:生成查询条件
        Map<String, Object> params = new HashMap<String, Object>();
        
        //TODO:根据实际情况，填入排序字段等条件，根据是否需要排序，选择调用dao内方法
        PagedList<AttachmentRef> resPagedList = this.attachmentRefDao.queryAttachmentRefPagedList(params, pageIndex, pageSize);
        
        return resPagedList;
    }
    
    /**
      * 查询attachmentRef列表总条数
      * TODO:补充说明
      * <功能详细描述>
      * @return [参数说明]
      * 
      * @return int [返回类型说明]
      * @exception throws [异常类型] [异常说明]
      * @see [类、类#方法、类#成员]
     */
    public int countAttachmentRef(/*TODO:自己定义条件*/){
        //TODO:判断条件合法性
        
        //TODO:生成查询条件
        Map<String, Object> params = new HashMap<String, Object>();
        
        //TODO:根据实际情况，填入排序字段等条件，根据是否需要排序，选择调用dao内方法
        int res = this.attachmentRefDao.countAttachmentRef(params);
        
        return res;
    }
    
    /**
      * 根据id更新对象
      * <功能详细描述>
      * @param attachmentRef
      * @return [参数说明]
      * 
      * @return boolean [返回类型说明]
      * @exception throws [异常类型] [异常说明]
      * @see [类、类#方法、类#成员]
     */
    @Transactional
    public boolean updateById(AttachmentRef attachmentRef) {
        //TODO:验证参数是否合法，必填字段是否填写，
        AssertUtils.notNull(attachmentRef, "attachmentRef is null.");
        AssertUtils.notEmpty(attachmentRef.getId(), "attachmentRef.id is empty.");
        
        
        //TODO:生成需要更新字段的hashMap
        Map<String, Object> updateRowMap = new HashMap<String, Object>();
        updateRowMap.put("id", attachmentRef.getId());
        
        //TODO:需要更新的字段
		updateRowMap.put("attachmentId", attachmentRef.getAttachmentId());	
		updateRowMap.put("refId", attachmentRef.getRefId());	
		updateRowMap.put("refType", attachmentRef.getRefType());	
        
        int updateRowCount = this.attachmentRefDao.updateAttachmentRef(updateRowMap);
        
        //TODO:如果需要大于1时，抛出异常并回滚，需要在这里修改
        return updateRowCount >= 1;
    }
}
