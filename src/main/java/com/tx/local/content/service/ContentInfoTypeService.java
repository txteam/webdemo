///*
// * 描          述:  <描述>
// * 修  改   人:  
// * 修改时间:  
// * <修改描述:>
// */
//package com.tx.local.content.service;
//
//import java.util.ArrayList;
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
//import org.springframework.transaction.support.TransactionTemplate;
//
//import com.tx.component.basicdata.context.AbstractBasicDataService;
//import com.tx.component.content.context.ContentTypeRegistry;
//import com.tx.component.content.dao.ContentInfoTypeDao;
//import com.tx.component.content.model.ContentInfoType;
//import com.tx.component.mainframe.context.WebContextUtils;
//import com.tx.core.exceptions.util.AssertUtils;
//import com.tx.core.paged.model.PagedList;
//import com.tx.core.util.ObjectUtils;
//
///**
// * ContentInfoType的业务层
// * <功能详细描述>
// * 
// * @author  
// * @version  [版本号, ]
// * @see  [相关类/方法]
// * @since  [产品/模块版本]
// */
//@Component("contentInfoTypeService")
//public class ContentInfoTypeService extends
//        AbstractBasicDataService<ContentInfoType> {
//    
//    @SuppressWarnings("unused")
//    private Logger logger = LoggerFactory.getLogger(ContentInfoTypeService.class);
//    
//    @Resource(name = "contentInfoTypeDao")
//    private ContentInfoTypeDao contentInfoTypeDao;
//    
//    @Resource(name = "contentTypeRegistry")
//    private ContentTypeRegistry contentTypeRegistry;
//    
//    @Resource(name = "transactionTemplate")
//    private TransactionTemplate transactionTemplate;
//    
//    /**
//     * @return
//     */
//    @Override
//    protected List<ContentInfoType> loadDataFromConfig() {
//        List<ContentInfoType> typeList = new ArrayList<>(
//                this.contentTypeRegistry.getCode2typeMap().values());
//        return typeList;
//    }
//    
//    /**
//     * @param ciaOfDBTemp
//     * @param ciaOfConfig
//     * @return
//     */
//    @Override
//    protected boolean isNeedUpdate(ContentInfoType ciaOfDBTemp,
//            ContentInfoType ciaOfConfig) {
//        boolean flag = !ObjectUtils.equals(ciaOfDBTemp,
//                ciaOfConfig,
//                "code",
//                "name",
//                "remark");
//        return flag;
//    }
//    
//    /**
//     * @throws Exception
//     */
//    @Override
//    protected void init() throws Exception {
//        this.configInitAbleHelper.init(this.transactionTemplate);
//    }
//    
//    /**
//     * 将contentInfoType实例插入数据库中保存
//     * 1、如果contentInfoType 为空时抛出参数为空异常
//     * 2、如果contentInfoType 中部分必要参数为非法值时抛出参数不合法异常
//     * 
//     * @param contentInfoType [参数说明]
//     * @return void [返回类型说明]
//     * @exception throws
//     * @see [类、类#方法、类#成员]
//     */
//    @Transactional
//    public void insert(ContentInfoType contentInfoType) {
//        //验证参数是否合法
//        AssertUtils.notNull(contentInfoType, "contentInfoType is null.");
//        AssertUtils.notEmpty(contentInfoType.getCode(),
//                "contentInfoType.code is null.");
//        AssertUtils.notEmpty(contentInfoType.getName(),
//                "contentInfoType.name is null.");
//        
//        //为添加的数据需要填入默认值的字段填入默认值
//        contentInfoType.setValid(true);
//        Date now = new Date();
//        contentInfoType.setCreateDate(now);
//        contentInfoType.setLastUpdateDate(now);
//        
//        //调用数据持久层对实体进行持久化操作
//        this.contentInfoTypeDao.insert(contentInfoType);
//    }
//    
//    /**
//     * 根据id删除contentInfoType实例
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
//        ContentInfoType condition = new ContentInfoType();
//        condition.setId(id);
//        int resInt = this.contentInfoTypeDao.delete(condition);
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
//        ContentInfoType condition = new ContentInfoType();
//        condition.setCode(code);
//        int resInt = this.contentInfoTypeDao.delete(condition);
//        
//        boolean flag = resInt > 0;
//        return flag;
//    }
//    
//    /**
//     * 根据Id查询ContentInfoType实体
//     * 1、当id为empty时抛出异常
//     *
//     * @param id
//     * @return ContentInfoType [返回类型说明]
//     * @exception throws
//     * @see [类、类#方法、类#成员]
//     */
//    public ContentInfoType findById(String id) {
//        AssertUtils.notEmpty(id, "id is empty.");
//        
//        ContentInfoType condition = new ContentInfoType();
//        condition.setId(id);
//        
//        ContentInfoType res = this.contentInfoTypeDao.find(condition);
//        return res;
//    }
//    
//    /**
//     * @param paramString
//     * @return
//     */
//    @Override
//    public ContentInfoType findByCode(String code) {
//        AssertUtils.notEmpty(code, "code is empty.");
//        
//        ContentInfoType condition = new ContentInfoType();
//        condition.setCode(code);
//        
//        ContentInfoType res = this.contentInfoTypeDao.find(condition);
//        return res;
//    }
//    
//    /**
//     * 查询ContentInfoType实体列表
//     * <功能详细描述>
//     * @param valid
//     * @param params      
//     * @return [参数说明]
//     * 
//     * @return List<ContentInfoType> [返回类型说明]
//     * @exception throws [异常类型] [异常说明]
//     * @see [类、类#方法、类#成员]
//     */
//    public List<ContentInfoType> queryList(Boolean valid,
//            Map<String, Object> params) {
//        //生成查询条件
//        params = params == null ? new HashMap<String, Object>() : params;
//        params.put("valid", valid);
//        
//        //根据实际情况，填入排序字段等条件，根据是否需要排序，选择调用dao内方法
//        List<ContentInfoType> resList = this.contentInfoTypeDao.queryList(params);
//        
//        return resList;
//    }
//    
//    /**
//     * 分页查询ContentInfoType实体列表
//     * <功能详细描述>
//     * @param valid
//      * @param params    
//     * @param pageIndex 当前页index从1开始计算
//     * @param pageSize 每页显示行数
//     * 
//     * <功能详细描述>
//     * @return [参数说明]
//     * 
//     * @return List<ContentInfoType> [返回类型说明]
//     * @exception throws [异常类型] [异常说明]
//     * @see [类、类#方法、类#成员]
//     */
//    public PagedList<ContentInfoType> queryPagedList(Boolean valid,
//            Map<String, Object> params, int pageIndex, int pageSize) {
//        //生成查询条件
//        params = params == null ? new HashMap<String, Object>() : params;
//        params.put("valid", valid);
//        
//        //根据实际情况，填入排序字段等条件，根据是否需要排序，选择调用dao内方法
//        PagedList<ContentInfoType> resPagedList = this.contentInfoTypeDao.queryPagedList(params,
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
//        int res = this.contentInfoTypeDao.count(params);
//        
//        return res > 0;
//    }
//    
//    /**
//      * 根据id更新对象
//      * <功能详细描述>
//      * @param contentInfoType
//      * @return [参数说明]
//      * 
//      * @return boolean [返回类型说明]
//      * @exception throws [异常类型] [异常说明]
//      * @see [类、类#方法、类#成员]
//     */
//    @Transactional
//    public boolean updateById(ContentInfoType contentInfoType) {
//        //验证参数是否合法，必填字段是否填写，
//        AssertUtils.notNull(contentInfoType, "contentInfoType is null.");
//        AssertUtils.notEmpty(contentInfoType.getId(),
//                "contentInfoType.id is empty.");
//        
//        //生成需要更新字段的hashMap
//        Map<String, Object> updateRowMap = new HashMap<String, Object>();
//        updateRowMap.put("id", contentInfoType.getId());
//        
//        //需要更新的字段
//        //updateRowMap.put("code", contentInfoType.getCode());
//        updateRowMap.put("remark", contentInfoType.getRemark());
//        updateRowMap.put("name", contentInfoType.getName());
//        updateRowMap.put("modifyAble", contentInfoType.isModifyAble());
//        updateRowMap.put("valid", contentInfoType.isValid());
//        
//        String currentOperatorId = WebContextUtils.getCurrentOperatorId();//获取当前登录人员id
//        updateRowMap.put("lastUpdateOperatorId", currentOperatorId);
//        updateRowMap.put("lastUpdateDate", new Date());
//        int updateRowCount = this.contentInfoTypeDao.update(updateRowMap);
//        
//        //如果需要大于1时，抛出异常并回滚，需要在这里修改
//        return updateRowCount >= 1;
//    }
//    
//    /**
//     * 根据id禁用ContentInfoType<br/>
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
//        this.contentInfoTypeDao.update(params);
//        
//        return true;
//    }
//    
//    /**
//      * 根据id启用ContentInfoType<br/>
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
//        this.contentInfoTypeDao.update(params);
//        
//        return true;
//    }
//}
