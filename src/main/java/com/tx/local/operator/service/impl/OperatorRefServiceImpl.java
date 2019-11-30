/*
 * 描          述:  <描述>
 * 修  改   人:  Administrator
 * 修改时间:  2019年11月29日
 * <修改描述:>
 */
package com.tx.local.operator.service.impl;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.annotation.Resource;

import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.tx.core.exceptions.util.AssertUtils;
import com.tx.core.querier.model.Querier;
import com.tx.local.operator.model.OperatorRef;
import com.tx.local.operator.model.OperatorRefItem;
import com.tx.local.operator.service.OperatorRefItemService;
import com.tx.local.operator.service.OperatorRefService;

/**
 * 操作员引用业务层<br/>
 * <功能详细描述>
 * 
 * @author  Administrator
 * @version  [版本号, 2019年11月29日]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
@Component("operatorRefService")
public class OperatorRefServiceImpl implements OperatorRefService {
    
    @Resource(name = "operatorRefItemService")
    private OperatorRefItemService operatorRefItemService;
    
    /**
     * @param effective
     * @param querier
     * @return
     */
    @Override
    public List<OperatorRef> queryList(Boolean effective, Querier querier) {
        querier = querier == null ? new Querier() : querier;
        querier.getParams().put("effective", effective);
        
        List<OperatorRef> operatorRefList = operatorRefItemService
                .queryList(querier).stream().collect(Collectors.toList());
        return operatorRefList;
    }
    
    /**
     * @param effective
     * @param params
     * @return
     */
    @Override
    public List<OperatorRef> queryList(Boolean effective,
            Map<String, Object> params) {
        params = params == null ? new HashMap<String, Object>() : params;
        params.put("effective", effective);
        
        List<OperatorRef> operatorRefList = operatorRefItemService
                .queryList(params).stream().collect(Collectors.toList());
        return operatorRefList;
    }
    
    /**
     * @param refType
     * @param refId
     * @param operatorIds
     * @param effictiveDate
     * @param duration
     */
    @Override
    public void batchInsertForOperatorIds(String refType, String refId,
            List<String> operatorIds, Date effectiveDate, Duration duration) {
        if (CollectionUtils.isEmpty(operatorIds)) {
            return;
        }
        AssertUtils.notEmpty(refType, "refType is empty.");
        AssertUtils.notEmpty(refId, "refId is empty.");
        if (effectiveDate == null) {
            effectiveDate = new Date();
        }
        Date expiryDate = null;
        if (duration != null) {
            expiryDate = com.tx.core.util.DateUtils.add(effectiveDate,
                    duration);
        }
        
        Date now = new Date();
        String createOperatorId = null;
        List<OperatorRefItem> operatorRefList = new ArrayList<>();
        for (String operatorIdTemp : operatorIds) {
            OperatorRefItem operatorRef = new OperatorRefItem();
            operatorRef.setCreateDate(now);
            operatorRef.setCreateOperatorId(createOperatorId);
            operatorRef.setEffectiveDate(effectiveDate);
            operatorRef.setExpiryDate(expiryDate);
            operatorRef.setRefId(refId);
            operatorRef.setRefType(refType);
            operatorRef.setOperatorId(operatorIdTemp);
            
            operatorRefList.add(operatorRef);
        }
        
        this.operatorRefItemService.batchInsert(operatorRefList);
    }
    
    /**
     * @param operatorId
     * @param refType
     * @param refIds
     * @param effectiveDate
     * @param duration
     */
    @Override
    @Transactional
    public void batchInsertForRefIds(String operatorId, String refType,
            List<String> refIds, Date effectiveDate, Duration duration) {
        if (CollectionUtils.isEmpty(refIds)) {
            return;
        }
        AssertUtils.notEmpty(operatorId, "operatorId is empty.");
        AssertUtils.notEmpty(refType, "refType is empty.");
        if (effectiveDate == null) {
            effectiveDate = new Date();
        }
        Date expiryDate = null;
        if (duration != null) {
            expiryDate = com.tx.core.util.DateUtils.add(effectiveDate,
                    duration);
        }
        
        Date now = new Date();
        String createOperatorId = null;
        List<OperatorRefItem> operatorRefList = new ArrayList<>();
        for (String refIdTemp : refIds) {
            OperatorRefItem operatorRef = new OperatorRefItem();
            operatorRef.setCreateDate(now);
            operatorRef.setCreateOperatorId(createOperatorId);
            operatorRef.setEffectiveDate(effectiveDate);
            operatorRef.setExpiryDate(expiryDate);
            operatorRef.setRefId(refIdTemp);
            operatorRef.setRefType(refType);
            operatorRef.setOperatorId(operatorId);
            
            operatorRefList.add(operatorRef);
        }
        
        this.operatorRefItemService.batchInsert(operatorRefList);
    }
    
    /**
     * @param operatorRefs
     */
    @Override
    @Transactional
    public void batchMoveToHis(List<OperatorRef> operatorRefs) {
        if (CollectionUtils.isEmpty(operatorRefs)) {
            return;
        }
        
        List<OperatorRefItem> items = new ArrayList<>();
        operatorRefs.stream().forEach(item -> {
            if (item instanceof OperatorRefItem) {
                items.add((OperatorRefItem) item);
            } else {
                OperatorRefItem newitem = new OperatorRefItem();
                BeanUtils.copyProperties(item, newitem);
                items.add(newitem);
            }
        });
        this.operatorRefItemService.batchInsertToHis(items);
        this.operatorRefItemService.batchDelete(items);
    }
    
}
