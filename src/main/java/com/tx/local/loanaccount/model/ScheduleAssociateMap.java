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

import com.tx.core.exceptions.util.AssertUtils;

/**
  * 还款计划映射关系<br/>
  * <功能详细描述>
  * 
  * @author  Administrator
  * @version  [版本号, 2017年5月9日]
  * @see  [相关类/方法]
  * @since  [产品/模块版本]
  */
public class ScheduleAssociateMap<E extends ScheduleAssociate>
        extends HashMap<ScheduleTypeEnum, Map<String, E>> {
    
    /** 注释内容 */
    private static final long serialVersionUID = 5290650968841632140L;
    
    /** <默认构造函数> */
    public ScheduleAssociateMap() {
        super();
    }
    
    /** <默认构造函数> */
    public ScheduleAssociateMap(Collection<E> c) {
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
            get(sa.getScheduleType()).put(sa.getPeriod(), sa);
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
        get(sa.getScheduleType()).put(sa.getPeriod(), sa);
    }
    
    /** 获取指定计划类型期数对应的计划关联项映射 */
    public boolean contains(ScheduleTypeEnum scheduleType, String period) {
        AssertUtils.notNull(scheduleType, "scheduleType is null.");
        AssertUtils.notEmpty(period, "period is empty.");
        
        if (!this.containsKey(scheduleType)) {
            return false;
        } else {
            boolean flag = this.get(scheduleType).containsKey(period);
            return flag;
        }
    }
    
    /** 获取指定计划类型期数对应的计划关联项映射 */
    public E get(ScheduleTypeEnum scheduleType, String period) {
        AssertUtils.notNull(scheduleType, "scheduleType is null.");
        AssertUtils.notEmpty(period, "period is empty.");
        
        if (!this.containsKey(scheduleType)) {
            return null;
        } else {
            E item = this.get(scheduleType).get(period);
            return item;
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
            return new ArrayList<>(get(scheduleType).values());
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
    public List<E> valueList(String period) {
        AssertUtils.notEmpty(period, "period is empty.");
        
        List<E> resList = new ArrayList<>();
        if (MapUtils.isEmpty(this)) {
            return resList;
        }
        
        for (Map<String, E> mapTemp : values()) {
            if (mapTemp.containsKey(period)) {
                resList.add(mapTemp.get(period));
            }
        }
        return resList;
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
        
        for (Map<String, E> mapTemp : values()) {
            resList.addAll(mapTemp.values());
        }
        return resList;
    }
    
}
