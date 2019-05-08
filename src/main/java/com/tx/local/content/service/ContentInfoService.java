///*
// * 描          述:  <描述>
// * 修  改   人:  
// * 修改时间:  
// * <修改描述:>
// */
//package com.tx.component.content.service;
//
//import java.util.Date;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//import javax.annotation.Resource;
//import javax.sql.DataSource;
//
//import org.apache.commons.lang3.StringUtils;
//import org.joda.time.DateTime;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.stereotype.Component;
//import org.springframework.transaction.annotation.Transactional;
//
//import com.tx.component.content.context.ContentTypeHandler;
//import com.tx.component.content.context.ContentTypeRegistry;
//import com.tx.component.content.dao.ContentInfoDao;
//import com.tx.component.content.model.ContentInfo;
//import com.tx.component.mainframe.context.WebContextUtils;
//import com.tx.component.sequence.context.SequenceContext;
//import com.tx.core.exceptions.util.AssertUtils;
//import com.tx.core.paged.model.PagedList;
//import com.tx.core.support.entrysupport.model.EntityEntry;
//import com.tx.core.support.entrysupport.support.AbstractEntryAbleService;
//import com.tx.core.support.entrysupport.support.EntityEntrySupport;
//import com.tx.core.support.entrysupport.support.EntityEntrySupportFactory;
//
///**
// * ContentInfo的业务层
// * <功能详细描述>
// * 
// * @author  
// * @version  [版本号, ]
// * @see  [相关类/方法]
// * @since  [产品/模块版本]
// */
//@Component("contentInfoService")
//public class ContentInfoService extends
//        AbstractEntryAbleService<EntityEntry, ContentInfo> {
//    
//    /** 日志记录器 */
//    @SuppressWarnings("unused")
//    private Logger logger = LoggerFactory.getLogger(ContentInfoService.class);
//    
//    /** 数据源 */
//    @Resource(name = "dataSource")
//    private DataSource dataSource;
//    
//    /** 内容持久层 */
//    @Resource(name = "contentInfoDao")
//    private ContentInfoDao contentInfoDao;
//    
//    /** 内容类型注册表 */
//    @Resource(name = "contentTypeRegistry")
//    private ContentTypeRegistry contentTypeRegistry;
//    
//    /**
//     * @return
//     * @throws Exception
//     */
//    @Override
//    protected EntityEntrySupport<EntityEntry> doBuildEntityEntrySupport()
//            throws Exception {
//        EntityEntrySupport<EntityEntry> entityEntrySupport = EntityEntrySupportFactory.getSupport("ci_content_info_entry",
//                this.dataSource);
//        return entityEntrySupport;
//    }
//    
//    /**
//      * 生成内容的id
//      * <功能详细描述>
//      * @return [参数说明]
//      * 
//      * @return String [返回类型说明]
//      * @exception throws [异常类型] [异常说明]
//      * @see [类、类#方法、类#成员]
//     */
//    @Transactional
//    public String generateContentInfoId() {
//        long seqLong = SequenceContext.getContext().nextVal("seq_content_info");
//        String sequence = StringUtils.leftPad(String.valueOf(seqLong), 26, '0');
//        String seq = DateTime.now().toString("yyMMdd") + sequence;
//        return seq;
//    }
//    
//    /**
//     * @param contentInfo
//     */
//    @Override
//    protected void insertEntity(ContentInfo contentInfo) {
//        //验证参数是否合法
//        AssertUtils.notNull(contentInfo, "contentInfo is null.");
//        AssertUtils.notNull(contentInfo.getCategory(),
//                "contentInfo.contentInfoCategory is null.");
//        AssertUtils.notNull(contentInfo.getCategory().getCode(),
//                "contentInfo.contentInfoCategory.code is empty.");
//        
//        if (StringUtils.isEmpty(contentInfo.getId())) {
//            contentInfo.setId(generateContentInfoId());
//        }
//        //为添加的数据需要填入默认值的字段填入默认值
//        contentInfo.setValid(true);
//        Date now = new Date();
//        String currentOperatorId = WebContextUtils.getCurrentOperatorId();
//        contentInfo.setCreateDate(now);
//        contentInfo.setLastUpdateDate(now);
//        contentInfo.setCreateOperatorId(currentOperatorId);
//        contentInfo.setLastUpdateOperatorId(currentOperatorId);
//        
//        ContentTypeHandler ctHandler = contentTypeRegistry.getTypeHandlerByCode(contentInfo.getType()
//                .getCode());
//        ctHandler.doBeforeInsertHandle(contentInfo);
//        
//        //调用数据持久层对实体进行持久化操作
//        this.contentInfoDao.insert(contentInfo);
//    }
//    
//    /**
//     * @param entity
//     * @return
//     */
//    @Override
//    public boolean updateEntityById(ContentInfo contentInfo) {
//        //验证参数是否合法，必填字段是否填写
//        AssertUtils.notNull(contentInfo, "contentInfo is null.");
//        AssertUtils.notEmpty(contentInfo.getId(), "contentInfo.id is empty.");
//        AssertUtils.notNull(contentInfo.getCategory(),
//                "contentInfo.contentInfoCategory is null.");
//        AssertUtils.notNull(contentInfo.getCategory().getCode(),
//                "contentInfo.contentInfoCategory.code is empty.");
//        
//        Date now = new Date();
//        String currentOperatorId = WebContextUtils.getCurrentOperatorId();
//        contentInfo.setLastUpdateDate(now);
//        contentInfo.setLastUpdateOperatorId(currentOperatorId);
//        
//        //更新前预处理
//        ContentTypeHandler ctHandler = contentTypeRegistry.getTypeHandlerByCode(contentInfo.getType()
//                .getCode());
//        ctHandler.doBeforeUpdateHandle(contentInfo);
//        
//        //生成需要更新字段的hashMap
//        Map<String, Object> updateRowMap = new HashMap<String, Object>();
//        updateRowMap.put("id", contentInfo.getId());
//        
//        //需要更新的字段
//        
//        //updateRowMap.put("type", contentInfo.getType());
//        //updateRowMap.put("category", contentInfo.getCategory());
//        updateRowMap.put("level", contentInfo.getLevel());
//        updateRowMap.put("name", contentInfo.getName());
//        updateRowMap.put("title", contentInfo.getTitle());
//        updateRowMap.put("linkUrl", contentInfo.getLinkUrl());
//        updateRowMap.put("remark", contentInfo.getRemark());
//        updateRowMap.put("content", contentInfo.getContent());
//        updateRowMap.put("fileId", contentInfo.getFileId());
//        updateRowMap.put("fileUrl", contentInfo.getFileUrl());
//        
//        updateRowMap.put("lastUpdateOperatorId", currentOperatorId);
//        updateRowMap.put("lastUpdateDate", now);
//        int updateRowCount = this.contentInfoDao.update(updateRowMap);
//        
//        //如果需要大于1时，抛出异常并回滚，需要在这里修改
//        return updateRowCount >= 1;
//    }
//    
//    /**
//     * @param entityId
//     * @return
//     */
//    @Override
//    protected boolean deleteEntityById(String id) {
//        AssertUtils.notEmpty(id, "id is empty.");
//        
//        ContentInfo findcondition = new ContentInfo();
//        findcondition.setId(id);
//        ContentInfo contentInfo = this.contentInfoDao.find(findcondition);
//        
//        ContentInfo condition = new ContentInfo();
//        condition.setId(id);
//        int resInt = this.contentInfoDao.delete(condition);
//        boolean flag = resInt > 0;
//        
//        if (contentInfo != null) {
//            ContentTypeHandler ctHandler = contentTypeRegistry.getTypeHandlerByCode(contentInfo.getType()
//                    .getCode());
//            ctHandler.doAfterDeleteHandle(contentInfo);
//        }
//        
//        return flag;
//    }
//    
//    /**
//     * @param entityId
//     * @return
//     */
//    @Override
//    public ContentInfo findEntityById(String id) {
//        AssertUtils.notEmpty(id, "id is empty.");
//        
//        ContentInfo condition = new ContentInfo();
//        condition.setId(id);
//        ContentInfo res = this.contentInfoDao.find(condition);
//        
//        //更新前预处理
//        ContentTypeHandler ctHandler = contentTypeRegistry.getTypeHandlerByCode(res.getType()
//                .getCode());
//        ctHandler.doAfterFindHandle(res);
//        
//        return res;
//    }
//    
//    /**
//     * 查询ContentInfo实体列表
//     * <功能详细描述>
//     * @param valid
//     * @param params      
//     * @return [参数说明]
//     * 
//     * @return List<ContentInfo> [返回类型说明]
//     * @exception throws [异常类型] [异常说明]
//     * @see [类、类#方法、类#成员]
//     */
//    public List<ContentInfo> queryList(Boolean valid, Map<String, Object> params) {
//        //生成查询条件
//        params = params == null ? new HashMap<String, Object>() : params;
//        params.put("valid", valid);
//        
//        //根据实际情况，填入排序字段等条件，根据是否需要排序，选择调用dao内方法
//        List<ContentInfo> resList = this.contentInfoDao.queryList(params);
//        
//        return resList;
//    }
//    
//    /**
//     * 分页查询ContentInfo实体列表
//     * <功能详细描述>
//     * @param valid
//      * @param params    
//     * @param pageIndex 当前页index从1开始计算
//     * @param pageSize 每页显示行数
//     * 
//     * <功能详细描述>
//     * @return [参数说明]
//     * 
//     * @return List<ContentInfo> [返回类型说明]
//     * @exception throws [异常类型] [异常说明]
//     * @see [类、类#方法、类#成员]
//     */
//    public PagedList<ContentInfo> queryPagedList(Boolean valid,
//            Map<String, Object> params, int pageIndex, int pageSize) {
//        //生成查询条件
//        params = params == null ? new HashMap<String, Object>() : params;
//        params.put("valid", valid);
//        
//        //根据实际情况，填入排序字段等条件，根据是否需要排序，选择调用dao内方法
//        PagedList<ContentInfo> resPagedList = this.contentInfoDao.queryPagedList(params,
//                pageIndex,
//                pageSize);
//        
//        return resPagedList;
//    }
//    
//    /**
//     * 判断是否已经存在<br/>
//     * <功能详细描述>
//     * @return [参数说明]
//     * 
//     * @return int [返回类型说明]
//     * @exception throws [异常类型] [异常说明]
//     * @see [类、类#方法、类#成员]
//    */
//    public boolean isExist(Map<String, String> key2valueMap, String excludeId) {
//        AssertUtils.notEmpty(key2valueMap, "key2valueMap is empty");
//        
//        //生成查询条件
//        Map<String, Object> params = new HashMap<String, Object>();
//        params.putAll(key2valueMap);
//        params.put("excludeId", excludeId);
//        
//        //根据实际情况，填入排序字段等条件，根据是否需要排序，选择调用dao内方法
//        int res = this.contentInfoDao.count(params);
//        
//        return res > 0;
//    }
//    
//    /**
//     * 根据id禁用ContentInfo<br/>
//     * <功能详细描述>
//     * @param id
//     * @return [参数说明]
//     * 
//     * @return boolean [返回类型说明]
//     * @exception throws [异常类型] [异常说明]
//     * @see [类、类#方法、类#成员]
//    */
//    @Transactional
//    public boolean disableById(String id) {
//        AssertUtils.notEmpty(id, "id is empty.");
//        
//        //生成查询条件
//        Map<String, Object> params = new HashMap<String, Object>();
//        params.put("id", id);
//        params.put("valid", false);
//        
//        this.contentInfoDao.update(params);
//        
//        return true;
//    }
//    
//    /**
//      * 根据id启用ContentInfo<br/>
//      * <功能详细描述>
//      * @param postId
//      * @return [参数说明]
//      * 
//      * @return boolean [返回类型说明]
//      * @exception throws [异常类型] [异常说明]
//      * @see [类、类#方法、类#成员]
//     */
//    @Transactional
//    public boolean enableById(String id) {
//        AssertUtils.notEmpty(id, "id is empty.");
//        
//        //生成查询条件
//        Map<String, Object> params = new HashMap<String, Object>();
//        params.put("id", id);
//        params.put("valid", true);
//        
//        this.contentInfoDao.update(params);
//        
//        return true;
//    }
//}
