/*
 * 描          述:  <描述>
 * 修  改   人:  Administrator
 * 修改时间:  2020年1月14日
 * <修改描述:>
 */
package com.tx.local.creditinfo.service;

import java.util.List;

import org.apache.commons.collections4.CollectionUtils;
import org.springframework.transaction.annotation.Transactional;

import com.tx.core.exceptions.SILException;
import com.tx.core.exceptions.util.AssertUtils;
import com.tx.core.paged.model.PagedList;
import com.tx.core.querier.model.Filter;
import com.tx.core.querier.model.Querier;
import com.tx.core.querier.model.QuerierBuilder;
import com.tx.core.util.MessageUtils;
import com.tx.local.creditinfo.model.CreditInfo;
import com.tx.local.creditinfo.model.CreditSingleLinked;

/**
 * 1:1信用信息业务层<br/>
 * <功能详细描述>
 * 
 * @author  Administrator
 * @version  [版本号, 2020年1月14日]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public interface SingleCreditInfoService<T extends CreditSingleLinked> {
    
    /**
     * 实体业务层对应的信用信息实体类型<br/>
     * <功能详细描述>
     * @return [参数说明]
     * 
     * @return T [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    Class<T> type();
    
    /**
     * 查询信用信息实体<br/>
     * <功能详细描述>
     * @param creditInfoId
     * @return [参数说明]
     * 
     * @return T [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    default T findByCreditInfoId(String creditInfoId) {
        AssertUtils.notEmpty(creditInfoId, "creditInfoId is empty.");
        
        Querier querier = QuerierBuilder.newInstance().querier();
        querier.getFilters().add(Filter.eq("creditInfoId", creditInfoId));
        
        List<T> resList = queryList(querier);
        if (CollectionUtils.isEmpty(resList)) {
            return null;
        } else if (resList.size() == 0) {
            T res = resList.get(0);
            return res;
        }
        
        //根据信用信息id查询实体
        throw new SILException(MessageUtils.format(
                "findByCreditInfoId is not single.creditInfoId:{}",
                creditInfoId));
    }
    
    /**
     * 保存信用信息<br/>
     * <功能详细描述>
     * @param creditInfoId
     * @param info [参数说明]
     * 
     * @return void [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @Transactional
    default void save(String creditInfoId, T info) {
        AssertUtils.notEmpty(creditInfoId, "creditInfoId is empty.");
        T dbEntity = findByCreditInfoId(creditInfoId);
        
        if (dbEntity == null) {
            info.setCreditInfoId(creditInfoId);
            insert(info);
        } else {
            info.setId(dbEntity.getId());
            info.setCreditInfoId(creditInfoId);
            updateById(dbEntity.getId(), info);
        }
    }
    
    /**
     * 对企业信用信息打tag标志<br/>
     * <功能详细描述>
     * @param tagCreditInfoId
     * @param trunkCreditInfoId [参数说明]
     * 
     * @return void [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @Transactional
    default T copy(String toCreditInfoId, String fromCreditInfoId) {
        AssertUtils.notEmpty(fromCreditInfoId, "fromCreditInfoId is empty.");
        AssertUtils.notEmpty(toCreditInfoId, "toCreditInfoId is empty.");
        
        T dbEntity = findByCreditInfoId(toCreditInfoId);
        AssertUtils.isNull(dbEntity,
                "Entity is exists.creditInfoId:{}",
                toCreditInfoId);
        
        T sourceEntity = findByCreditInfoId(fromCreditInfoId);
        sourceEntity.setId(null);
        sourceEntity.setCreditInfoId(toCreditInfoId);
        return sourceEntity;
    }
    
    
    @Transactional
    default T tag(CreditInfo tagCreditInfo, String fromCreditInfoId){
        AssertUtils.notEmpty(fromCreditInfoId, "fromCreditInfoId is empty.");
        
        
        return null;
    }
    
    @Transactional
    default T branch(CreditInfo branchCreditInfo, String fromCreditInfoId){
        AssertUtils.notEmpty(fromCreditInfoId, "fromCreditInfoId is empty.");
        
        return null;
    }
    
    /**
     * 信息合并<br/>
     * <功能详细描述>
     * @param trunkCreditInfoId
     * @param branchCreditInfoId [参数说明]
     * 
     * @return void [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @Transactional
    default void merge(String toCreditInfoId, String fromCreditInfoId) {
        AssertUtils.notEmpty(fromCreditInfoId, "fromCreditInfoId is empty.");
        AssertUtils.notEmpty(toCreditInfoId, "toCreditInfoId is empty.");
        
        T dbEntity = findByCreditInfoId(fromCreditInfoId);
        AssertUtils.notNull(dbEntity,
                "Entity is exists.creditInfoId:{}",
                fromCreditInfoId);
        
        //保存信用信息
        save(toCreditInfoId, dbEntity);
    }
    
    /**
     * 新增信用信息<br/>
     * <功能详细描述>
     * @param creditInfoRecord [参数说明]
     * 
     * @return void [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    public T insert(T entity);
    
    /**
     * 根据id删除对应实体<br/>
     * <功能详细描述>
     * @param id
     * @return [参数说明]
     * 
     * @return boolean [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    public boolean deleteById(String id);
    
    /**
     * 更新实体<br/>
     * <功能详细描述>
     * @param id
     * @param creditInfoRecord
     * @return [参数说明]
     * 
     * @return boolean [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    public boolean updateById(String id, T creditInfoRecord);
    
    /**
     * 根据id查询实体实例<br/>
     * <功能详细描述>
     * @param id
     * @return [参数说明]
     * 
     * @return T [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    public T findById(String id);
    
    /**
     * 根据查询条件查询实体<br/>
     * <功能详细描述>
     * @param querier
     * @return [参数说明]
     * 
     * @return List<T> [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    public List<T> queryList(Querier querier);
    
    /**
     * 分页查询实体<br/>
     * <功能详细描述>
     * @param querier
     * @param pageIndex
     * @param pageSize
     * @return [参数说明]
     * 
     * @return PagedList<T> [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    public PagedList<T> queryPagedList(Querier querier, int pageIndex,
            int pageSize);
    
    /**
     * 根据条件统计对象数量<br/>
     * <功能详细描述>
     * @param querier
     * @return [参数说明]
     * 
     * @return int [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    public int count(Querier querier);
    
    /**
     * 判断对象是否存在<br/>
     * <功能详细描述>
     * @param querier
     * @param excludeId
     * @return [参数说明]
     * 
     * @return boolean [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    public boolean exists(Querier querier, String excludeId);
}
