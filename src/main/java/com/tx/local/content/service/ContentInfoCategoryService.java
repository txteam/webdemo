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
//import org.apache.commons.lang3.StringUtils;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.stereotype.Component;
//import org.springframework.transaction.annotation.Transactional;
//
//import com.tx.component.basicdata.service.TreeAbleBasicDataService;
//import com.tx.core.exceptions.util.AssertUtils;
//import com.tx.core.paged.model.PagedList;
//import com.tx.core.util.ObjectUtils;
//import com.tx.local.content.dao.ContentInfoCategoryDao;
//import com.tx.local.content.model.ContentInfoCategory;
//import com.tx.local.mainframe.util.WebContextUtils;
//
///**
// * ContentInfoCategory的业务层
// * <功能详细描述>
// * 
// * @author  
// * @version  [版本号, ]
// * @see  [相关类/方法]
// * @since  [产品/模块版本]
// */
//@Component("contentInfoCategoryService")
//public class ContentInfoCategoryService
//        implements TreeAbleBasicDataService<ContentInfoCategory> {
//    
//    @SuppressWarnings("unused")
//    private Logger logger = LoggerFactory
//            .getLogger(ContentInfoCategoryService.class);
//    
//    @Resource(name = "contentInfoCategoryDao")
//    private ContentInfoCategoryDao contentInfoCategoryDao;
//    
//    /**
//      * 初始化期间调用的业务逻辑
//      * <功能详细描述>
//      * @param category
//      * @return [参数说明]
//      * 
//      * @return ContentInfoCategory [返回类型说明]
//      * @exception throws [异常类型] [异常说明]
//      * @see [类、类#方法、类#成员]
//     */
//    @Transactional
//    public ContentInfoCategory saveForInit(ContentInfoCategory categoryConfig) {
//        ContentInfoCategory contentInfoCategory = findByCode(
//                categoryConfig.getCode());
//        if (contentInfoCategory == null) {
//            contentInfoCategory = categoryConfig;
//            contentInfoCategory.setModifyAble(false);
//            insert(contentInfoCategory);//插入内容分类
//        } else {
//            if (!ObjectUtils.equals(contentInfoCategory,
//                    categoryConfig,
//                    "name",
//                    "remark",
//                    "orderIndex")
//                    || !ObjectUtils.equals(contentInfoCategory.getParent(),
//                            categoryConfig.getParent(),
//                            "id")
//                    || !ObjectUtils.equals(contentInfoCategory.getType(),
//                            categoryConfig.getType(),
//                            "code")
//                    || contentInfoCategory.isModifyAble()) {
//                contentInfoCategory.setType(categoryConfig.getType());
//                contentInfoCategory.setParent(categoryConfig.getParent());
//                
//                contentInfoCategory.setName(categoryConfig.getName());
//                contentInfoCategory.setRemark(categoryConfig.getRemark());
//                contentInfoCategory
//                        .setOrderIndex(categoryConfig.getOrderIndex());
//                
//                contentInfoCategory.setModifyAble(false);
//                contentInfoCategory.setValid(true);
//                updateById(contentInfoCategory);
//            }
//        }
//        return contentInfoCategory;
//    }
//    
//    /**
//     * 将contentInfoCategory实例插入数据库中保存
//     * 1、如果contentInfoCategory 为空时抛出参数为空异常
//     * 2、如果contentInfoCategory 中部分必要参数为非法值时抛出参数不合法异常
//     * 
//     * @param contentInfoCategory [参数说明]
//     * @return void [返回类型说明]
//     * @exception throws
//     * @see [类、类#方法、类#成员]
//     */
//    @Transactional
//    public void insert(ContentInfoCategory contentInfoCategory) {
//        //验证参数是否合法
//        AssertUtils.notNull(contentInfoCategory,
//                "contentInfoCategory is null.");
//        AssertUtils.notNull(contentInfoCategory.getCode(),
//                "contentInfoCategory.code is empty.");
//        AssertUtils.notNull(contentInfoCategory.getName(),
//                "contentInfoCategory.name is empty.");
//        
//        //为添加的数据需要填入默认值的字段填入默认值
//        Date now = new Date();
//        //contentInfoCategory.setModifyAble(true);
//        contentInfoCategory.setValid(true);
//        contentInfoCategory.setLastUpdateDate(now);
//        contentInfoCategory.setCreateDate(now);
//        
//        //获取当前登录人员id
//        String currentOperatorId = WebContextUtils.getCurrentOperatorId();
//        contentInfoCategory.setCreateOperatorId(currentOperatorId);
//        contentInfoCategory.setLastUpdateOperatorId(currentOperatorId);
//        //根据父级id设置对应的level值
//        if (!StringUtils.isEmpty(contentInfoCategory.getParentId())) {
//            ContentInfoCategory parent = findById(
//                    contentInfoCategory.getParentId());
//            if (parent == null) {
//                contentInfoCategory.setLevel(0);
//                contentInfoCategory.setParent(parent);
//            } else {
//                contentInfoCategory.setLevel(parent.getLevel() + 1);
//            }
//        } else {
//            contentInfoCategory.setLevel(0);
//        }
//        
//        //调用数据持久层对实体进行持久化操作
//        this.contentInfoCategoryDao.insert(contentInfoCategory);
//    }
//    
//    /**
//     * 根据id删除contentInfoCategory实例
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
//        ContentInfoCategory condition = new ContentInfoCategory();
//        condition.setId(id);
//        int resInt = this.contentInfoCategoryDao.delete(condition);
//        
//        boolean flag = resInt > 0;
//        return flag;
//    }
//    
//    /**
//     * @param paramString
//     * @return
//     */
//    @Override
//    @Transactional
//    public boolean deleteByCode(String code) {
//        AssertUtils.notEmpty(code, "code is empty.");
//        
//        ContentInfoCategory condition = new ContentInfoCategory();
//        condition.setCode(code);
//        int resInt = this.contentInfoCategoryDao.delete(condition);
//        
//        boolean flag = resInt > 0;
//        return flag;
//    }
//    
//    /**
//     * 根据Id查询ContentInfoCategory实体
//     * 1、当id为empty时抛出异常
//     *
//     * @param id
//     * @return ContentInfoCategory [返回类型说明]
//     * @exception throws
//     * @see [类、类#方法、类#成员]
//     */
//    public ContentInfoCategory findById(String id) {
//        AssertUtils.notEmpty(id, "id is empty.");
//        
//        ContentInfoCategory condition = new ContentInfoCategory();
//        condition.setId(id);
//        
//        ContentInfoCategory res = this.contentInfoCategoryDao.find(condition);
//        return res;
//    }
//    
//    /**
//     * @param paramString
//     * @return
//     */
//    @Override
//    public ContentInfoCategory findByCode(String code) {
//        AssertUtils.notEmpty(code, "code is empty.");
//        
//        ContentInfoCategory condition = new ContentInfoCategory();
//        condition.setCode(code);
//        
//        ContentInfoCategory res = this.contentInfoCategoryDao.find(condition);
//        return res;
//    }
//    
//    /**
//     * 查询ContentInfoCategory实体列表
//     * <功能详细描述>
//     * @param valid
//     * @param params      
//     * @return [参数说明]
//     * 
//     * @return List<ContentInfoCategory> [返回类型说明]
//     * @exception throws [异常类型] [异常说明]
//     * @see [类、类#方法、类#成员]
//     */
//    public List<ContentInfoCategory> queryList(Boolean valid,
//            Map<String, Object> params) {
//        //生成查询条件
//        params = params == null ? new HashMap<String, Object>() : params;
//        params.put("valid", valid);
//        
//        //根据实际情况，填入排序字段等条件，根据是否需要排序，选择调用dao内方法
//        List<ContentInfoCategory> resList = this.contentInfoCategoryDao
//                .queryList(params);
//        
//        return resList;
//    }
//    
//    /**
//     * 分页查询ContentInfoCategory实体列表
//     * <功能详细描述>
//     * @param valid
//      * @param params    
//     * @param pageIndex 当前页index从1开始计算
//     * @param pageSize 每页显示行数
//     * 
//     * <功能详细描述>
//     * @return [参数说明]
//     * 
//     * @return List<ContentInfoCategory> [返回类型说明]
//     * @exception throws [异常类型] [异常说明]
//     * @see [类、类#方法、类#成员]
//     */
//    public PagedList<ContentInfoCategory> queryPagedList(Boolean valid,
//            Map<String, Object> params, int pageIndex, int pageSize) {
//        //生成查询条件
//        params = params == null ? new HashMap<String, Object>() : params;
//        params.put("valid", valid);
//        
//        //根据实际情况，填入排序字段等条件，根据是否需要排序，选择调用dao内方法
//        PagedList<ContentInfoCategory> resPagedList = this.contentInfoCategoryDao
//                .queryPagedList(params, pageIndex, pageSize);
//        
//        return resPagedList;
//    }
//    
//    /**
//     * @param paramString
//     * @param paramBoolean
//     * @param paramMap
//     * @return
//     */
//    @Override
//    public List<ContentInfoCategory> queryListByParentId(String parentId,
//            Boolean valid, Map<String, Object> params) {
//        AssertUtils.notEmpty(parentId, "parentId is empty.");
//        
//        //生成查询条件
//        params = params == null ? new HashMap<String, Object>() : params;
//        params.put("valid", valid);
//        params.put("parentId", parentId);
//        
//        //根据实际情况，填入排序字段等条件，根据是否需要排序，选择调用dao内方法
//        List<ContentInfoCategory> resList = this.contentInfoCategoryDao
//                .queryList(params);
//        
//        return resList;
//    }
//    
//    /**
//     * @param paramString
//     * @param paramBoolean
//     * @param paramMap
//     * @param paramInt1
//     * @param paramInt2
//     * @return
//     */
//    @Override
//    public PagedList<ContentInfoCategory> queryPagedListByParentId(
//            String parentId, Boolean valid, Map<String, Object> params,
//            int pageIndex, int pageSize) {
//        AssertUtils.notEmpty(parentId, "parentId is empty.");
//        
//        //生成查询条件
//        params = params == null ? new HashMap<String, Object>() : params;
//        params.put("valid", valid);
//        params.put("parentId", parentId);
//        
//        //根据实际情况，填入排序字段等条件，根据是否需要排序，选择调用dao内方法
//        PagedList<ContentInfoCategory> resPagedList = this.contentInfoCategoryDao
//                .queryPagedList(params, pageIndex, pageSize);
//        
//        return resPagedList;
//    }
//    
//    
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
//        int res = this.contentInfoCategoryDao.count(params);
//        
//        return res > 0;
//    }
//    
//    /**
//      * 根据id更新对象
//      * <功能详细描述>
//      * @param contentInfoCategory
//      * @return [参数说明]
//      * 
//      * @return boolean [返回类型说明]
//      * @exception throws [异常类型] [异常说明]
//      * @see [类、类#方法、类#成员]
//     */
//    @Transactional
//    public boolean updateById(ContentInfoCategory contentInfoCategory) {
//        //验证参数是否合法，必填字段是否填写，
//        AssertUtils.notNull(contentInfoCategory,
//                "contentInfoCategory is null.");
//        AssertUtils.notEmpty(contentInfoCategory.getId(),
//                "contentInfoCategory.id is empty.");
//        
//        //根据父级id设置对应的level值
//        if (!StringUtils.isEmpty(contentInfoCategory.getParentId())) {
//            ContentInfoCategory parent = findById(
//                    contentInfoCategory.getParentId());
//            if (parent == null) {
//                contentInfoCategory.setLevel(0);
//                contentInfoCategory.setParent(parent);
//            } else {
//                contentInfoCategory.setLevel(parent.getLevel() + 1);
//            }
//        } else {
//            contentInfoCategory.setLevel(0);
//        }
//        
//        //生成需要更新字段的hashMap
//        Map<String, Object> updateRowMap = new HashMap<String, Object>();
//        updateRowMap.put("id", contentInfoCategory.getId());
//        
//        //需要更新的字段
//        //updateRowMap.put("code", contentInfoCategory.getCode());
//        updateRowMap.put("valid", contentInfoCategory.isValid());
//        updateRowMap.put("modifyAble", contentInfoCategory.isModifyAble());
//        updateRowMap.put("remark", contentInfoCategory.getRemark());
//        updateRowMap.put("orderIndex", contentInfoCategory.getOrderIndex());
//        updateRowMap.put("name", contentInfoCategory.getName());
//        updateRowMap.put("type", contentInfoCategory.getType());
//        updateRowMap.put("parentId", contentInfoCategory.getParentId());
//        updateRowMap.put("level", contentInfoCategory.getLevel());
//        
//        String currentOperatorId = WebContextUtils.getCurrentOperatorId();
//        Date now = new Date();
//        updateRowMap.put("lastUpdateOperatorId", currentOperatorId);
//        updateRowMap.put("lastUpdateDate", now);
//        
//        int updateRowCount = this.contentInfoCategoryDao.update(updateRowMap);
//        
//        //如果需要大于1时，抛出异常并回滚，需要在这里修改
//        return updateRowCount >= 1;
//    }
//    
//    /**
//     * 根据id禁用ContentInfoCategory<br/>
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
//        this.contentInfoCategoryDao.update(params);
//        
//        return true;
//    }
//    
//    /**
//      * 根据id启用ContentInfoCategory<br/>
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
//        this.contentInfoCategoryDao.update(params);
//        
//        return true;
//    }
//}
