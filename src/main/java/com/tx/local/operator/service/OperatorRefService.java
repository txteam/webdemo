/*
 * 描          述:  <描述>
 * 修  改   人:  
 * 修改时间:  
 * <修改描述:>
 */
package com.tx.local.operator.service;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.apache.commons.collections4.CollectionUtils;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.MultiValueMap;

import com.tx.core.exceptions.util.AssertUtils;
import com.tx.core.querier.model.Querier;
import com.tx.local.operator.model.OperatorRef;

/**
 * OperatorRef的业务层[OperatorRefService]
 * <功能详细描述>
 * 
 * @author  
 * @version [版本号]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
public interface OperatorRefService {
    
    /**
     * 查询权限关联集合<br/>
     * <功能详细描述>
     * @param querier
     * @return [参数说明]
     * 
     * @return List<OperatorRef> [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    public List<OperatorRef> queryList(Boolean effective, Querier querier);
    
    /**
     * 查询权限关联集合<br/>
     * <功能详细描述>
     * @param params
     * @return [参数说明]
     * 
     * @return List<OperatorRef> [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    public List<OperatorRef> queryList(Boolean effective,
            Map<String, Object> params);
    
    /**
     * 根据关联类型映射查询权限关联<br/>
     * <功能详细描述>
     * @param refMap
     * @return [参数说明]
     * 
     * @return List<OperatorRef> [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    default List<OperatorRef> queryListByRefMap(Boolean effective,
            MultiValueMap<String, String> refMap) {
        AssertUtils.notEmpty(refMap, "refMap is empty.");
        
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("refMap", refMap);
        
        List<OperatorRef> resList = queryList(true, params);
        return resList;
    }
    
    /**
     * 根据关联类型以及关联id查询权限关联<br/>
     * <功能详细描述>
     * @param refType
     * @param refId
     * @return [参数说明]
     * 
     * @return List<OperatorRef> [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    default List<OperatorRef> queryListByRef(Boolean effective, String refType,
            String refId, Map<String, Object> params) {
        AssertUtils.notEmpty(refType, "refType is empty.");
        AssertUtils.notEmpty(refId, "refId is empty.");
        
        params = params == null ? new HashMap<String, Object>() : params;
        params.put("refType", refType);
        params.put("refId", refId);
        
        List<OperatorRef> resList = queryList(effective, params);
        return resList;
    }
    
    /**
     * 根据关联类型以及权限id查询权限关联实例<br/>
     * <功能详细描述>
     * @param operatorId
     * @param refType(允许为空)
     * @return [参数说明]
     * 
     * @return List<OperatorRef> [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    default List<OperatorRef> queryListByOperatorId(Boolean effective,
            String operatorId, String refType, Map<String, Object> params) {
        AssertUtils.notEmpty(operatorId, "operatorId is empty.");
        
        params = params == null ? new HashMap<String, Object>() : params;
        params.put("operatorId", operatorId);
        params.put("refType", refType);
        
        List<OperatorRef> resList = queryList(effective, params);
        return resList;
    }
    
    /**
     * 为指定权限新增关联引用： 应用于针对某权限赋予多个[人员]的场景<br/>
     *    在引用唯一键方面系统不依赖于数据库唯一键去限定，由代码进行控制，允许多个临时权限存在.
     *    add方法一般服务于新增临时权限场景
     * <功能详细描述>
     * @param operatorId
     * @param refType
     * @param refIds [参数说明]
     * 
     * @return void [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @Transactional
    default void addForRefIds(String operatorId, String refType,
            List<String> refIds, Date effectiveDate, Duration duration) {
        if (CollectionUtils.isEmpty(refIds)) {
            return;
        }
        AssertUtils.notEmpty(operatorId, "operatorId is empty.");
        AssertUtils.notEmpty(refType, "refType is empty.");
        AssertUtils.notNull(effectiveDate, "effectiveDate is null.");
        AssertUtils.notNull(duration, "duration is null.");
        
        //查询原关联权限
        Map<String, Object> params = new HashMap<>();
        params.put("hasExpiryDate", false);
        List<OperatorRef> sourceRefList = queryListByOperatorId(true,
                operatorId,
                refType,
                params);
        
        //识别需要添加的权限列表
        List<String> sourceRefIds = new ArrayList<>();
        sourceRefList.stream().forEach(operatorRefTemp -> {
            sourceRefIds.add(operatorRefTemp.getRefId());
        });
        List<String> needAddRefIds = refIds.stream().filter(refIdTemp -> {
            return !sourceRefIds.contains(refIdTemp);
        }).collect(Collectors.toList());
        batchInsertForRefIds(operatorId,
                refType,
                needAddRefIds,
                effectiveDate,
                duration);
    }
    
    /**
     * 为指定[人员]添加权限关联：应用于针对某人员一次性赋予多个权限的场景<br/>
     * <功能详细描述>
     * @param refType
     * @param refId
     * @param operatorIds [参数说明]
     * 
     * @return void [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @Transactional
    default void addForOperatorIds(String refType, String refId,
            List<String> operatorIds, Date effectiveDate, Duration duration) {
        if (CollectionUtils.isEmpty(operatorIds)) {
            return;
        }
        AssertUtils.notEmpty(refId, "refId is empty.");
        AssertUtils.notEmpty(refType, "refType is empty.");
        AssertUtils.notNull(effectiveDate, "effectiveDate is null.");
        AssertUtils.notNull(duration, "duration is null.");
        
        //查询原关联权限
        Map<String, Object> params = new HashMap<>();
        params.put("hasExpiryDate", false);
        List<OperatorRef> sourceRefList = queryListByRef(true,
                refType,
                refId,
                params);
        
        //识别需要添加的权限列表
        List<String> sourceOperatorIds = new ArrayList<>();
        sourceRefList.stream().forEach(operatorRefTemp -> {
            sourceOperatorIds.add(operatorRefTemp.getOperatorId());
        });
        List<String> needAddOperatorIds = operatorIds.stream()
                .filter(operatorIdTemp -> {
                    return !sourceOperatorIds.contains(operatorIdTemp);
                })
                .collect(Collectors.toList());
        batchInsertForOperatorIds(refType,
                refId,
                needAddOperatorIds,
                effectiveDate,
                duration);
    }
    
    /**
     * 
     * <功能详细描述>
     * @param operatorId
     * @param refType
     * @param refIds
     * @param filterRefIds [参数说明]
     * 
     * @return void [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @Transactional
    default void saveForRefIds(String operatorId, String refType,
            List<String> refIds, List<String> filterRefIds) {
        AssertUtils.notEmpty(operatorId, "operatorId is empty.");
        AssertUtils.notNull(refIds, "refIds is null.");
        
        //查询原关联权限
        Map<String, Object> params = new HashMap<>();
        params.put("hasExpiryDate", false);
        List<OperatorRef> sourceRefList = queryListByOperatorId(true,
                operatorId,
                refType,
                params);
        if (!CollectionUtils.isEmpty(filterRefIds)) {
            sourceRefList = sourceRefList.stream().filter(operatorRefTemp -> {
                return filterRefIds.contains(operatorRefTemp.getRefId());
            }).collect(Collectors.toList());
        }
        
        //识别需要删除的权限
        List<String> sourceRefIds = new ArrayList<>();
        sourceRefList.stream().forEach(operatorRefTemp -> {
            sourceRefIds.add(operatorRefTemp.getRefId());
        });
        List<OperatorRef> needDeleteRefs = sourceRefList.stream()
                .filter(operatorRefTemp -> {
                    return !refIds.contains(operatorRefTemp.getRefId());
                })
                .collect(Collectors.toList());
        //移除到历史表
        batchMoveToHis(needDeleteRefs);
        
        //识别需要添加的权限列表
        List<String> needAddRefIds = refIds.stream().filter(refIdTemp -> {
            return !sourceRefIds.contains(refIdTemp);
        }).collect(Collectors.toList());
        batchInsertForRefIds(operatorId,
                refType,
                needAddRefIds,
                new Date(),
                null);
    }
    
    /**
     * 
     * <功能详细描述>
     * @param refType
     * @param refId
     * @param operatorIds
     * @param filterOperatorIds [参数说明]
     * 
     * @return void [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @Transactional
    default void saveForOperatorIds(String refType, String refId,
            List<String> operatorIds, List<String> filterOperatorIds) {
        AssertUtils.notEmpty(refType, "refType is empty.");
        AssertUtils.notEmpty(refId, "refId is empty.");
        AssertUtils.notNull(operatorIds, "operatorIds is null.");
        
        //查询原关联权限
        Map<String, Object> params = new HashMap<>();
        params.put("hasExpiryDate", false);
        List<OperatorRef> sourceRefList = queryListByRef(true,
                refType,
                refId,
                params);
        if (!CollectionUtils.isEmpty(filterOperatorIds)) {
            sourceRefList = sourceRefList.stream().filter(operatorRefTemp -> {
                return filterOperatorIds
                        .contains(operatorRefTemp.getOperatorId());
            }).collect(Collectors.toList());
        }
        
        //识别需要删除的权限
        List<String> sourceOperatorIds = new ArrayList<>();
        sourceRefList.stream().forEach(operatorRefTemp -> {
            sourceOperatorIds.add(operatorRefTemp.getOperatorId());
        });
        List<OperatorRef> needDeleteRefs = sourceRefList.stream()
                .filter(operatorRefTemp -> {
                    return !operatorIds
                            .contains(operatorRefTemp.getOperatorId());
                })
                .collect(Collectors.toList());
        //移除到历史表
        batchMoveToHis(needDeleteRefs);
        
        //识别需要添加的权限列表
        List<String> needAddOperatorIds = operatorIds.stream()
                .filter(operatorIdTemp -> {
                    return !sourceOperatorIds.contains(operatorIdTemp);
                })
                .collect(Collectors.toList());
        batchInsertForOperatorIds(refType,
                refId,
                needAddOperatorIds,
                new Date(),
                null);
    }
    
    /**
     * 服务于给人员配置多个权限的场景<br/>
     * <功能详细描述>
     * @param refType
     * @param refId
     * @param operatorIds [参数说明]
     * 
     * @return void [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @Transactional
    public void batchInsertForOperatorIds(String refType, String refId,
            List<String> operatorIds, Date effectiveDate, Duration duration);
    
    /**
     * 根据关联id集合进行批量插入<br/>
     *    服务于将权限配置给多个人员的场景<br/>
     * <功能详细描述>
     * @param operatorId
     * @param refType
     * @param refIds [参数说明]
     * 
     * @return void [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @Transactional
    public void batchInsertForRefIds(String operatorId, String refType,
            List<String> refIds, Date effectiveDate, Duration duration);
    
    /**
     * 批量插入历史表<br/>
     *    用于修改配置后，将历史的存储于历史表中，便于回溯<br/>
     * <功能详细描述>
     * @param operatorRefs [参数说明]
     * 
     * @return void [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @Transactional
    public void batchMoveToHis(List<OperatorRef> operatorRefs);
}
