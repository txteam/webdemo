/*
 * 描          述:  <描述>
 * 修  改   人:  
 * 修改时间:  
 * <修改描述:>
 */
package com.tx.local.operator.service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.collections4.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.tx.core.exceptions.util.AssertUtils;
import com.tx.core.paged.model.PagedList;
import com.tx.core.querier.model.Querier;
import com.tx.core.querier.model.QuerierBuilder;
import com.tx.local.operator.dao.OperatorRefItemDao;
import com.tx.local.operator.model.OperatorRefItem;

/**
 * OperatorRefItem的业务层[OperatorRefItemService]
 * <功能详细描述>
 * 
 * @author  
 * @version [版本号]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
@Component("operatorRefItemService")
public class OperatorRefItemService {
    
    @SuppressWarnings("unused")
    private Logger logger = LoggerFactory
            .getLogger(OperatorRefItemService.class);
    
    @Resource(name = "operatorRefItemDao")
    private OperatorRefItemDao operatorRefItemDao;
    
    /**
     * 新增角色引用实例<br/>
     * 将operatorRef插入数据库中保存
     * 1、如果operatorRef 为空时抛出参数为空异常
     * 2、如果operatorRef 中部分必要参数为非法值时抛出参数不合法异常
     * 
     * @param operatorRef [参数说明]
     * @return void [返回类型说明]
     * @exception throws
     * @see [类、类#方法、类#成员]
     */
    @Transactional
    public void insertToHis(OperatorRefItem operatorRef) {
        //验证参数是否合法
        AssertUtils.notNull(operatorRef, "operatorRef is null.");
        AssertUtils.notEmpty(operatorRef.getId(), "operatorRef.id is empty.");
        AssertUtils.notEmpty(operatorRef.getOperatorId(),
                "operatorRef.operatorId is empty.");
        AssertUtils.notEmpty(operatorRef.getRefType(),
                "operatorRef.refType is empty.");
        AssertUtils.notEmpty(operatorRef.getRefId(),
                "operatorRef.refId is empty.");
        
        //为添加的数据需要填入默认值的字段填入默认值
        Date now = new Date();
        operatorRef.setLastUpdateDate(now);
        
        //调用数据持久层对实例进行持久化操作
        this.operatorRefItemDao.insertToHis(operatorRef);
    }
    
    /**
     * 新增角色引用实例<br/>
     * 将operatorRefs插入数据库中保存
     * 1、如果operatorRefs 为空时抛出参数为空异常
     * 2、如果operatorRefs 中部分必要参数为非法值时抛出参数不合法异常
     * 
     * @param roleRefItem [参数说明]
     * @return void [返回类型说明]
     * @exception throws
     * @see [类、类#方法、类#成员]
     */
    @Transactional
    public void batchInsertToHis(List<OperatorRefItem> operatorRefs) {
        if (CollectionUtils.isEmpty(operatorRefs)) {
            return;
        }
        
        Date now = new Date();
        operatorRefs.stream().forEach(roleRef -> {
            roleRef.setLastUpdateDate(now);
            AssertUtils.notEmpty(roleRef.getId(), "authRef.id is empty.");
        });
        
        //调用数据持久层对实例进行持久化操作
        this.operatorRefItemDao.batchInsertToHis(operatorRefs);
    }
    
    /**
     * 新增OperatorRefItem实例<br/>
     * 将operatorRefItem插入数据库中保存
     * 1、如果operatorRefItem 为空时抛出参数为空异常
     * 2、如果operatorRefItem 中部分必要参数为非法值时抛出参数不合法异常
     * 
     * @param operatorRefItem [参数说明]
     * @return void [返回类型说明]
     * @exception throws
     * @see [类、类#方法、类#成员]
     */
    @Transactional
    public void insert(OperatorRefItem operatorRef) {
        //验证参数是否合法
        AssertUtils.notNull(operatorRef, "operatorRef is null.");
        AssertUtils.notEmpty(operatorRef.getOperatorId(),
                "operatorRef.operatorId is empty.");
        AssertUtils.notEmpty(operatorRef.getRefType(),
                "operatorRef.refType is empty.");
        AssertUtils.notEmpty(operatorRef.getRefId(),
                "operatorRef.refId is empty.");
        
        Date now = new Date();
        operatorRef.setLastUpdateDate(now);
        operatorRef.setCreateDate(now);
        
        //调用数据持久层对实例进行持久化操作
        this.operatorRefItemDao.insert(operatorRef);
    }
    
    /**
     * 新增角色引用实例<br/>
     * 将authRefItem插入数据库中保存
     * 1、如果authRefItem 为空时抛出参数为空异常
     * 2、如果authRefItem 中部分必要参数为非法值时抛出参数不合法异常
     * 
     * @param authRefItem [参数说明]
     * @return void [返回类型说明]
     * @exception throws
     * @see [类、类#方法、类#成员]
     */
    @Transactional
    public void batchInsert(List<OperatorRefItem> operatorRefs) {
        if (CollectionUtils.isEmpty(operatorRefs)) {
            return;
        }
        
        Date now = new Date();
        operatorRefs.stream().forEach(operatorRef -> {
            operatorRef.setLastUpdateDate(now);
            operatorRef.setCreateDate(now);
            if (operatorRef.getEffectiveDate() == null) {
                operatorRef.setEffectiveDate(now);
            }
            
            //验证参数是否合法
            AssertUtils.notNull(operatorRef, "operatorRef is null.");
            AssertUtils.notEmpty(operatorRef.getOperatorId(),
                    "operatorRef.operatorId is empty.");
            AssertUtils.notEmpty(operatorRef.getRefType(),
                    "operatorRef.refType is empty.");
            AssertUtils.notEmpty(operatorRef.getRefId(),
                    "operatorRef.refId is empty.");
        });
        
        //调用数据持久层对实例进行持久化操作
        this.operatorRefItemDao.batchInsert(operatorRefs);
    }
    
    /**
     * 根据id删除OperatorRefItem实例
     * 1、如果入参数为空，则抛出异常
     * 2、执行删除后，将返回数据库中被影响的条数 > 0，则返回true
     *
     * @param id
     * @return boolean 删除的条数>0则为true [返回类型说明]
     * @exception throws 
     * @see [类、类#方法、类#成员]
     */
    @Transactional
    public boolean deleteById(String id) {
        AssertUtils.notEmpty(id, "id is empty.");
        
        OperatorRefItem condition = new OperatorRefItem();
        condition.setId(id);
        
        int resInt = this.operatorRefItemDao.delete(condition);
        boolean flag = resInt > 0;
        return flag;
    }
    
    /**
     * 新增权限引用实例<br/>
     * 将authRefItem插入数据库中保存
     * 1、如果authRefItem 为空时抛出参数为空异常
     * 2、如果authRefItem 中部分必要参数为非法值时抛出参数不合法异常
     * 
     * @param authRefItem [参数说明]
     * @return void [返回类型说明]
     * @exception throws
     * @see [类、类#方法、类#成员]
     */
    @Transactional
    public void batchDelete(List<OperatorRefItem> operatorRefs) {
        if (CollectionUtils.isEmpty(operatorRefs)) {
            return;
        }
        
        Date now = new Date();
        operatorRefs.stream().forEach(operatorRef -> {
            operatorRef.setLastUpdateDate(now);
            AssertUtils.notEmpty(operatorRef.getId(), "authRef.id is empty.");
        });
        
        //调用数据持久层对实例进行持久化操作
        this.operatorRefItemDao.batchDelete(operatorRefs);
    }
    
    /**
     * 根据id查询OperatorRefItem实例
     * 1、当id为empty时抛出异常
     *
     * @param id
     * @return OperatorRefItem [返回类型说明]
     * @exception throws
     * @see [类、类#方法、类#成员]
     */
    public OperatorRefItem findById(String id) {
        AssertUtils.notEmpty(id, "id is empty.");
        
        OperatorRefItem condition = new OperatorRefItem();
        condition.setId(id);
        
        OperatorRefItem res = this.operatorRefItemDao.find(condition);
        return res;
    }
    
    /**
     * 查询OperatorRefItem实例列表
     * <功能详细描述>
     * @param params      
     * @return [参数说明]
     * 
     * @return List<OperatorRefItem> [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    public List<OperatorRefItem> queryList(Map<String, Object> params) {
        //判断条件合法性
        
        //生成查询条件
        params = params == null ? new HashMap<String, Object>() : params;
        
        //根据实际情况，填入排序字段等条件，根据是否需要排序，选择调用dao内方法
        List<OperatorRefItem> resList = this.operatorRefItemDao
                .queryList(params);
        
        return resList;
    }
    
    /**
     * 查询OperatorRefItem实例列表
     * <功能详细描述>
     * @param querier      
     * @return [参数说明]
     * 
     * @return List<OperatorRefItem> [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    public List<OperatorRefItem> queryList(Querier querier) {
        //判断条件合法性
        
        //生成查询条件
        querier = querier == null ? QuerierBuilder.newInstance().querier()
                : querier;
        
        //根据实际情况，填入排序字段等条件，根据是否需要排序，选择调用dao内方法
        List<OperatorRefItem> resList = this.operatorRefItemDao
                .queryList(querier);
        
        return resList;
    }
    
    /**
     * 分页查询OperatorRefItem实例列表
     * <功能详细描述>
     * @param params    
     * @param pageIndex 当前页index从1开始计算
     * @param pageSize 每页显示行数
     * 
     * <功能详细描述>
     * @return [参数说明]
     * 
     * @return List<OperatorRefItem> [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    public PagedList<OperatorRefItem> queryPagedList(Map<String, Object> params,
            int pageIndex, int pageSize) {
        //T判断条件合法性
        
        //生成查询条件
        params = params == null ? new HashMap<String, Object>() : params;
        
        //根据实际情况，填入排序字段等条件，根据是否需要排序，选择调用dao内方法
        PagedList<OperatorRefItem> resPagedList = this.operatorRefItemDao
                .queryPagedList(params, pageIndex, pageSize);
        
        return resPagedList;
    }
    
    /**
     * 分页查询OperatorRefItem实例列表
     * <功能详细描述>
     * @param querier    
     * @param pageIndex 当前页index从1开始计算
     * @param pageSize 每页显示行数
     * 
     * <功能详细描述>
     * @return [参数说明]
     * 
     * @return List<OperatorRefItem> [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    public PagedList<OperatorRefItem> queryPagedList(Querier querier,
            int pageIndex, int pageSize) {
        //T判断条件合法性
        
        //生成查询条件
        querier = querier == null ? QuerierBuilder.newInstance().querier()
                : querier;
        
        //根据实际情况，填入排序字段等条件，根据是否需要排序，选择调用dao内方法
        PagedList<OperatorRefItem> resPagedList = this.operatorRefItemDao
                .queryPagedList(querier, pageIndex, pageSize);
        
        return resPagedList;
    }
    
    /**
     * 查询OperatorRefItem实例数量<br/>
     * <功能详细描述>
     * @param params      
     * @return [参数说明]
     * 
     * @return List<OperatorRefItem> [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    public int count(Map<String, Object> params) {
        //判断条件合法性
        
        //生成查询条件
        params = params == null ? new HashMap<String, Object>() : params;
        
        //根据实际情况，填入排序字段等条件，根据是否需要排序，选择调用dao内方法
        int res = this.operatorRefItemDao.count(params);
        
        return res;
    }
    
    /**
     * 查询OperatorRefItem实例数量<br/>
     * <功能详细描述>
     * @param querier      
     * @return [参数说明]
     * 
     * @return List<OperatorRefItem> [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    public int count(Querier querier) {
        //判断条件合法性
        
        //生成查询条件
        querier = querier == null ? QuerierBuilder.newInstance().querier()
                : querier;
        
        //根据实际情况，填入排序字段等条件，根据是否需要排序，选择调用dao内方法
        int res = this.operatorRefItemDao.count(querier);
        
        return res;
    }
    
    /**
     * 判断OperatorRefItem实例是否已经存在<br/>
     * <功能详细描述>
     * @param key2valueMap
     * @param excludeId
     * @return
     * 
     * @return int [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    public boolean exists(Map<String, String> key2valueMap, String excludeId) {
        AssertUtils.notEmpty(key2valueMap, "key2valueMap is empty");
        
        //生成查询条件
        Map<String, Object> params = new HashMap<String, Object>();
        params.putAll(key2valueMap);
        
        //根据实际情况，填入排序字段等条件，根据是否需要排序，选择调用dao内方法
        int res = this.operatorRefItemDao.count(params, excludeId);
        
        return res > 0;
    }
    
    /**
     * 判断OperatorRefItem实例是否已经存在<br/>
     * <功能详细描述>
     * @param key2valueMap
     * @param excludeId
     * @return
     * 
     * @return int [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    public boolean exists(Querier querier, String excludeId) {
        AssertUtils.notNull(querier, "querier is null.");
        
        //根据实际情况，填入排序字段等条件，根据是否需要排序，选择调用dao内方法
        int res = this.operatorRefItemDao.count(querier, excludeId);
        
        return res > 0;
    }
    
    /**
     * 根据id更新OperatorRefItem实例<br/>
     * <功能详细描述>
     * @param operatorRefItem
     * @return [参数说明]
     * 
     * @return boolean [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @Transactional
    public boolean updateById(String id, OperatorRefItem operatorRefItem) {
        //验证参数是否合法，必填字段是否填写
        AssertUtils.notNull(operatorRefItem, "operatorRefItem is null.");
        AssertUtils.notEmpty(id, "id is empty.");
        
        //生成需要更新字段的hashMap
        Map<String, Object> updateRowMap = new HashMap<String, Object>();
        //FIXME:需要更新的字段
        updateRowMap.put("refId", operatorRefItem.getRefId());
        updateRowMap.put("refType", operatorRefItem.getRefType());
        updateRowMap.put("lastUpdateOperatorId",
                operatorRefItem.getLastUpdateOperatorId());
        updateRowMap.put("createOperatorId",
                operatorRefItem.getCreateOperatorId());
        updateRowMap.put("expiryDate", operatorRefItem.getExpiryDate());
        updateRowMap.put("operatorId", operatorRefItem.getOperatorId());
        updateRowMap.put("effectiveDate", operatorRefItem.getEffectiveDate());
        updateRowMap.put("lastUpdateDate", new Date());
        
        boolean flag = this.operatorRefItemDao.update(id, updateRowMap);
        //如果需要大于1时，抛出异常并回滚，需要在这里修改
        return flag;
    }
    
    /**
     * 根据id更新OperatorRefItem实例<br/>
     * <功能详细描述>
     * @param operatorRefItem
     * @return [参数说明]
     * 
     * @return boolean [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @Transactional
    public boolean updateById(OperatorRefItem operatorRefItem) {
        //验证参数是否合法，必填字段是否填写
        AssertUtils.notNull(operatorRefItem, "operatorRefItem is null.");
        AssertUtils.notEmpty(operatorRefItem.getId(),
                "operatorRefItem.id is empty.");
        
        boolean flag = updateById(operatorRefItem.getId(), operatorRefItem);
        //如果需要大于1时，抛出异常并回滚，需要在这里修改
        return flag;
    }
}
