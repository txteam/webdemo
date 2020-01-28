/*
 * 描          述:  <描述>
 * 修  改   人:  Administrator
 * 修改时间:  2017年5月9日
 * <修改描述:>
 */
package com.tx.local.loanaccount.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.MapUtils;

import com.tx.core.TxConstants;
import com.tx.core.exceptions.util.AssertUtils;
import com.tx.local.basicdata.model.FeeItemEnum;

/**
  * 还款计划映射关系<br/>
  * <功能详细描述>
  * 
  * @author  Administrator
  * @version  [版本号, 2017年5月9日]
  * @see  [相关类/方法]
  * @since  [产品/模块版本]
  */
public class ScheduleEntryAssociateMap<E extends ScheduleEntryAssociate>
        extends HashMap<ScheduleTypeEnum, Map<String, Map<FeeItemEnum, E>>> {
    
    /** 注释内容 */
    private static final long serialVersionUID = 5290650968841632140L;
    
    /** <默认构造函数> */
    public ScheduleEntryAssociateMap() {
        super();
    }
    
    /** <默认构造函数> */
    public ScheduleEntryAssociateMap(Collection<E> c) {
        super();
        
        addAll(c);
    }
    
    /** 添加集合 */
    public void addAll(Collection<E> c) {
        if (CollectionUtils.isEmpty(c)) {
            return;
        }
        for (E sa : c) {
            if (!containsKey(sa.getScheduleType())) {
                put(sa.getScheduleType(), new HashMap<>());
            }
            if (!get(sa.getScheduleType()).containsKey(sa.getPeriod())) {
                get(sa.getScheduleType()).put(sa.getPeriod(), new HashMap<>());
            }
            get(sa.getScheduleType()).get(sa.getPeriod()).put(sa.getFeeItem(),
                    sa);
        }
    }
    
    /** 添加 */
    public void add(E sa) {
        if (sa == null) {
            return;
        }
        if (!containsKey(sa.getScheduleType())) {
            put(sa.getScheduleType(), new HashMap<>());
        }
        if (!get(sa.getScheduleType()).containsKey(sa.getPeriod())) {
            get(sa.getScheduleType()).put(sa.getPeriod(), new HashMap<>());
        }
        get(sa.getScheduleType()).get(sa.getPeriod()).put(sa.getFeeItem(), sa);
    }
    
    /** 获取指定计划类型期数对应的计划关联项映射 */
    public boolean contains(ScheduleTypeEnum scheduleType, String period,
            FeeItemEnum feeItem) {
        AssertUtils.notNull(scheduleType, "scheduleType is null.");
        AssertUtils.notEmpty(period, "period is empty.");
        AssertUtils.notNull(feeItem, "feeItem is null.");
        
        if (!this.containsKey(scheduleType)) {
            return false;
        } else if (!this.get(scheduleType).containsKey(period)) {
            return false;
        } else {
            boolean flag = this.get(scheduleType)
                    .get(period)
                    .containsKey(feeItem);
            return flag;
        }
    }
    
    /** 获取指定计划类型期数对应的计划关联项映射 */
    public E get(ScheduleTypeEnum scheduleType, String period,
            FeeItemEnum feeItem) {
        AssertUtils.notNull(scheduleType, "scheduleType is null.");
        AssertUtils.notEmpty(period, "period is empty.");
        AssertUtils.notNull(feeItem, "feeItem is null.");
        
        if (!this.containsKey(scheduleType)) {
            return null;
        } else if (!this.get(scheduleType).containsKey(period)) {
            return null;
        } else {
            E item = this.get(scheduleType).get(period).get(feeItem);
            return item;
        }
    }
    
    /** 获取指定计划类型期数对应的计划关联项映射 */
    public Map<FeeItemEnum, E> get(ScheduleTypeEnum scheduleType,
            String period) {
        AssertUtils.notNull(scheduleType, "scheduleType is null.");
        AssertUtils.notEmpty(period, "period is empty.");
        
        if (!this.containsKey(scheduleType)) {
            return null;
        } else {
            Map<FeeItemEnum, E> itemMap = this.get(scheduleType).get(period);
            return itemMap;
        }
    }
    
    /**
     * 获取指定类型下的计划实现项<br/>
     * <功能详细描述>
     * @param scheduleType
     * @return [参数说明]
     * 
     * @return Collection<E> [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
    */
    public List<E> valueList(ScheduleTypeEnum scheduleType) {
        AssertUtils.notNull(scheduleType, "scheduleType is null.");
        
        if (!this.containsKey(scheduleType)) {
            return new ArrayList<>();
        } else {
            List<E> resList = new ArrayList<>(
                    TxConstants.INITIAL_CONLLECTION_SIZE);
            
            for (Map<FeeItemEnum, E> entryTemp : get(scheduleType).values()) {
                resList.addAll(entryTemp.values());
            }
            return resList;
        }
    }
    
    /**
     * 获取指定类型下的计划实现项<br/>
     * <功能详细描述>
     * @param scheduleType
     * @return [参数说明]
     * 
     * @return Collection<E> [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
    */
    public List<E> valueList(ScheduleTypeEnum scheduleType, String period) {
        AssertUtils.notNull(scheduleType, "scheduleType is null.");
        AssertUtils.notEmpty(period, "period is empty.");
        
        if (!containsKey(scheduleType)) {
            return new ArrayList<>();
        } else if (!get(scheduleType).containsKey(period)) {
            return new ArrayList<>();
        } else {
            List<E> resList = new ArrayList<>(
                    get(scheduleType).get(period).values());
            return resList;
        }
    }
    
    /**
     * 获取指定类型下的计划实现项<br/>
     * <功能详细描述>
     * @param scheduleType
     * @return [参数说明]
     * 
     * @return Collection<E> [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
    */
    public List<E> valueList(ScheduleTypeEnum scheduleType,
            FeeItemEnum feeItem) {
        AssertUtils.notNull(scheduleType, "scheduleType is null.");
        AssertUtils.notNull(feeItem, "feeItem is null.");
        
        if (!containsKey(scheduleType)) {
            return new ArrayList<>();
        } else {
            List<E> resList = new ArrayList<>();
            for (Map<FeeItemEnum, E> mt : get(scheduleType).values()) {
                if (!mt.containsKey(feeItem)) {
                    continue;
                }
                resList.add(mt.get(feeItem));
            }
            return resList;
        }
    }
    
    /**
     * 获取指定类型下的计划实现项<br/>
     * <功能详细描述>
     * @param scheduleType
     * @return [参数说明]
     * 
     * @return Collection<E> [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
    */
    public List<E> valueList() {
        List<E> resList = new ArrayList<>();
        if (MapUtils.isEmpty(this)) {
            return resList;
        }
        
        for (Map<String, Map<FeeItemEnum, E>> mapTemp : values()) {
            for (Map<FeeItemEnum, E> mt : mapTemp.values()) {
                resList.addAll(mt.values());
            }
        }
        return resList;
    }
    
}
