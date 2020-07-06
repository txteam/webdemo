/*
 * 描          述:  <描述>
 * 修  改   人:  Administrator
 * 修改时间:  2020年1月14日
 * <修改描述:>
 */
package com.tx.local.creditinfo.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.tx.core.exceptions.util.AssertUtils;
import com.tx.core.querier.model.Filter;
import com.tx.core.querier.model.Querier;
import com.tx.core.querier.model.QuerierBuilder;
import com.tx.local.creditinfo.model.CreditInfo;
import com.tx.local.creditinfo.model.CreditMultipLinked;

/**
 * 1:n的信用信息业务层<br/>
 * <功能详细描述>
 * 
 * @author  Administrator
 * @version  [版本号, 2020年1月14日]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public interface CreditMultipLinkedService<T extends CreditMultipLinked>
        extends CreditLinkedService<T> {
    
    /**
     * 根据信用信息ID查询信息<br/>
     * <功能详细描述>
     * @param creditInfoId
     * @param querier
     * @return [参数说明]
     * 
     * @return List<T> [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    default List<T> queryByCreditInfoId(String creditInfoId, Querier querier) {
        AssertUtils.notEmpty(creditInfoId, "creditInfoId is empty.");
        
        querier = querier == null ? QuerierBuilder.newInstance().querier()
                : querier;
        querier.getFilters().add(Filter.eq("creditInfoId", creditInfoId));
        
        List<T> resList = queryList(querier);
        return resList;
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
        
        return null;
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
        
    }
}
