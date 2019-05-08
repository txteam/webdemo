///*
// * 描          述:  <描述>
// * 修  改   人:  
// * 修改时间:  
// * <修改描述:>
// */
//package com.tx.local.content.service;
//
//import java.util.Date;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//import javax.annotation.Resource;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.stereotype.Component;
//import org.springframework.transaction.annotation.Transactional;
//
//import com.tx.component.basicdata.service.AbstractBasicDataService;
//import com.tx.core.exceptions.util.AssertUtils;
//import com.tx.core.paged.model.PagedList;
//import com.tx.core.util.WebContextUtils;
//import com.tx.local.content.dao.ContentInfoLevelDao;
//import com.tx.local.content.model.ContentInfoLevel;
//
///**
// * ContentInfoLevel的业务层
// * <功能详细描述>
// * 
// * @author  
// * @version  [版本号, ]
// * @see  [相关类/方法]
// * @since  [产品/模块版本]
// */
//@Component("contentInfoLevelService")
//public class ContentInfoLevelService
//        extends AbstractBasicDataService<ContentInfoLevel> {
//    
//    @SuppressWarnings("unused")
//    private Logger logger = LoggerFactory
//            .getLogger(ContentInfoLevelService.class);
//    
//    @Resource(name = "contentInfoLevelDao")
//    private ContentInfoLevelDao contentInfoLevelDao;
//    
//    /**
//     * 将contentInfoLevel实例插入数据库中保存
//     * 1、如果contentInfoLevel 为空时抛出参数为空异常
//     * 2、如果contentInfoLevel 中部分必要参数为非法值时抛出参数不合法异常
//     * 
//     * @param contentInfoLevel [参数说明]
//     * @return void [返回类型说明]
//     * @exception throws
//     * @see [类、类#方法、类#成员]
//     */
//    @Transactional
//    public void insert(ContentInfoLevel contentInfoLevel) {
//        //验证参数是否合法
//        AssertUtils.notNull(contentInfoLevel, "contentInfoLevel is null.");
//        AssertUtils.notEmpty(contentInfoLevel.getCode(),
//                "contentInfoLevel.code is empty.");
//        AssertUtils.notEmpty(contentInfoLevel.getName(),
//                "contentInfoLevel.name is empty.");
//        AssertUtils.notNull(contentInfoLevel.getCategory(),
//                "contentInfoLevel.category is null.");
//        AssertUtils.notEmpty(contentInfoLevel.getCategory().getCode(),
//                "contentInfoLevel.category.code is empty.");
//        
//        //为添加的数据需要填入默认值的字段填入默认值
//        Date now = new Date();
//        contentInfoLevel.setValid(true);
//        contentInfoLevel.setCreateDate(now);
//        contentInfoLevel.setLastUpdateDate(now);
//        
//        String currentOperatorId = WebContextUtils.getCurrentOperatorId();
//        contentInfoLevel.setCreateOperatorId(currentOperatorId);
//        
//        //调用数据持久层对实体进行持久化操作
//        this.contentInfoLevelDao.insert(contentInfoLevel);
//    }
//    
//    /**
//     * 根据id删除contentInfoLevel实例
//     * 1、如果入参数为空，则抛出异常
//     * 2、执行删除后，将返回数据库中被影响的条数 > 0，则返回true
//     *
//     * @param id
//     * @return boolean 删除的条数>0则为true [返回类型说明]
//     * @exception throws 
//     * @see [类、类#方法、类#成员]
//     */
//    @Transactional
//    public boolean deleteById(String id) {
//        AssertUtils.notEmpty(id, "id is empty.");
//        
//        ContentInfoLevel condition = new ContentInfoLevel();
//        condition.setId(id);
//        int resInt = this.contentInfoLevelDao.delete(condition);
//        
//        boolean flag = resInt > 0;
//        return flag;
//    }
//    
//    /**
//     * @param arg0
//     * @return
//     */
//    @Override
//    @Transactional
//    public boolean deleteByCode(String code) {
//        AssertUtils.notEmpty(code, "code is empty.");
//        
//        ContentInfoLevel condition = new ContentInfoLevel();
//        condition.setCode(code);
//        int resInt = this.contentInfoLevelDao.delete(condition);
//        
//        boolean flag = resInt > 0;
//        return flag;
//    }
//    
//    /**
//     * 根据Id查询ContentInfoLevel实体
//     * 1、当id为empty时抛出异常
//     *
//     * @param id
//     * @return ContentInfoLevel [返回类型说明]
//     * @exception throws
//     * @see [类、类#方法、类#成员]
//     */
//    public ContentInfoLevel findById(String id) {
//        AssertUtils.notEmpty(id, "id is empty.");
//        
//        ContentInfoLevel condition = new ContentInfoLevel();
//        condition.setId(id);
//        
//        ContentInfoLevel res = this.contentInfoLevelDao.find(condition);
//        return res;
//    }
//    
//    /**
//     * @param arg0
//     * @return
//     */
//    @Override
//    public ContentInfoLevel findByCode(String code) {
//        AssertUtils.notEmpty(code, "code is empty.");
//        
//        ContentInfoLevel condition = new ContentInfoLevel();
//        condition.setCode(code);
//        
//        ContentInfoLevel res = this.contentInfoLevelDao.find(condition);
//        return res;
//    }
//    
//    /**
//     * 查询ContentInfoLevel实体列表
//     * <功能详细描述>
//     * @param valid
//     * @param params      
//     * @return [参数说明]
//     * 
//     * @return List<ContentInfoLevel> [返回类型说明]
//     * @exception throws [异常类型] [异常说明]
//     * @see [类、类#方法、类#成员]
//     */
//    public List<ContentInfoLevel> queryList(Boolean valid,
//            Map<String, Object> params) {
//        //判断条件合法性
//        
//        //生成查询条件
//        params = params == null ? new HashMap<String, Object>() : params;
//        params.put("valid", valid);
//        
//        //根据实际情况，填入排序字段等条件，根据是否需要排序，选择调用dao内方法
//        List<ContentInfoLevel> resList = this.contentInfoLevelDao
//                .queryList(params);
//        
//        return resList;
//    }
//    
//    /**
//     * 分页查询ContentInfoLevel实体列表
//     * <功能详细描述>
//     * @param valid
//      * @param params    
//     * @param pageIndex 当前页index从1开始计算
//     * @param pageSize 每页显示行数
//     * 
//     * <功能详细描述>
//     * @return [参数说明]
//     * 
//     * @return List<ContentInfoLevel> [返回类型说明]
//     * @exception throws [异常类型] [异常说明]
//     * @see [类、类#方法、类#成员]
//     */
//    public PagedList<ContentInfoLevel> queryPagedList(Boolean valid,
//            Map<String, Object> params, int pageIndex, int pageSize) {
//        //T判断条件合法性
//        
//        //生成查询条件
//        params = params == null ? new HashMap<String, Object>() : params;
//        params.put("valid", valid);
//        
//        //根据实际情况，填入排序字段等条件，根据是否需要排序，选择调用dao内方法
//        PagedList<ContentInfoLevel> resPagedList = this.contentInfoLevelDao
//                .queryPagedList(params, pageIndex, pageSize);
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
//        int res = this.contentInfoLevelDao.count(params);
//        
//        return res > 0;
//    }
//    
//    /**
//      * 根据id更新对象
//      * <功能详细描述>
//      * @param contentInfoLevel
//      * @return [参数说明]
//      * 
//      * @return boolean [返回类型说明]
//      * @exception throws [异常类型] [异常说明]
//      * @see [类、类#方法、类#成员]
//     */
//    @Transactional
//    public boolean updateById(ContentInfoLevel contentInfoLevel) {
//        //验证参数是否合法，必填字段是否填写，
//        AssertUtils.notNull(contentInfoLevel, "contentInfoLevel is null.");
//        AssertUtils.notEmpty(contentInfoLevel.getId(),
//                "contentInfoLevel.id is empty.");
//        
//        //生成需要更新字段的hashMap
//        Map<String, Object> updateRowMap = new HashMap<String, Object>();
//        updateRowMap.put("id", contentInfoLevel.getId());
//        
//        //需要更新的字段
//        //updateRowMap.put("modifyAble", contentInfoLevel.isModifyAble());
//        //updateRowMap.put("category", contentInfoLevel.getCategory());
//        updateRowMap.put("name", contentInfoLevel.getName());
//        updateRowMap.put("remark", contentInfoLevel.getRemark());
//        String currentOperatorId = WebContextUtils.getCurrentOperatorId();
//        Date now = new Date();
//        updateRowMap.put("lastUpdateOperatorId", currentOperatorId);
//        updateRowMap.put("lastUpdateDate", now);
//        
//        int updateRowCount = this.contentInfoLevelDao.update(updateRowMap);
//        
//        //如果需要大于1时，抛出异常并回滚，需要在这里修改
//        return updateRowCount >= 1;
//    }
//    
//    /**
//     * 根据id禁用ContentInfoLevel<br/>
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
//        this.contentInfoLevelDao.update(params);
//        
//        return true;
//    }
//    
//    /**
//      * 根据id启用ContentInfoLevel<br/>
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
//        this.contentInfoLevelDao.update(params);
//        
//        return true;
//    }
//}
