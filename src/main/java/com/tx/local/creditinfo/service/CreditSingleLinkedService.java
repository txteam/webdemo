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
public interface CreditSingleLinkedService<T extends CreditSingleLinked>
        extends CreditLinkedService<T> {
    
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
            
            updateById(dbEntity);
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
    
    /**
     * 对信用信息打标记<br/>
     * <功能详细描述>
     * @param tagCreditInfo
     * @param fromCreditInfoId
     * @return [参数说明]
     * 
     * @return T [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @Transactional
    default T tag(CreditInfo tagCreditInfo, String fromCreditInfoId) {
        AssertUtils.notEmpty(fromCreditInfoId, "fromCreditInfoId is empty.");
        
        return null;
    }
    
    /**
     * 创建分支版本<br/>
     * <功能详细描述>
     * @param branchCreditInfo
     * @param fromCreditInfoId
     * @return [参数说明]
     * 
     * @return T [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @Transactional
    default T branch(CreditInfo branchCreditInfo, String fromCreditInfoId) {
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
}
