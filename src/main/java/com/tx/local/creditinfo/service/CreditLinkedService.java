/*
 * 描          述:  <描述>
 * 修  改   人:  Administrator
 * 修改时间:  2020年1月14日
 * <修改描述:>
 */
package com.tx.local.creditinfo.service;

import java.beans.PropertyDescriptor;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.Column;

import org.springframework.beans.BeanWrapper;
import org.springframework.beans.PropertyAccessorFactory;
import org.springframework.core.convert.TypeDescriptor;
import org.springframework.transaction.annotation.Transactional;

import com.tx.core.exceptions.util.AssertUtils;
import com.tx.core.paged.model.PagedList;
import com.tx.core.querier.model.Querier;
import com.tx.local.creditinfo.model.CreditLinked;

/**
 * 1:n的信用信息业务层<br/>
 * <功能详细描述>
 * 
 * @author  Administrator
 * @version  [版本号, 2020年1月14日]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public interface CreditLinkedService<T extends CreditLinked> {
    
    /**
     * 实体业务层对应的信用信息实体类型<br/>
     * <功能详细描述>
     * @return [参数说明]
     * 
     * @return T [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    Class<? extends T> type();
    
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
     * @param entity
     * @return [参数说明]
     * 
     * @return boolean [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @Transactional
    default boolean updateById(T entity) {
        AssertUtils.notNull(entity, "entity is null.");
        AssertUtils.notEmpty(entity.getId(), "entity.id is empty.");
        
        Map<String, Object> updateRowMap = new HashMap<String, Object>();
        BeanWrapper bw = PropertyAccessorFactory.forBeanPropertyAccess(entity);
        for (PropertyDescriptor pd : bw.getPropertyDescriptors()) {
            if (pd.getWriteMethod() == null || pd.getReadMethod() == null) {
                continue;
            }
            if ("id".equals(pd.getName())) {
                continue;
            }
            TypeDescriptor td1 = bw.getPropertyTypeDescriptor(pd.getName());
            if (td1.hasAnnotation(Column.class)
                    && !td1.getAnnotation(Column.class).updatable()) {
                continue;
            }
            updateRowMap.put(pd.getName(), bw.getPropertyValue(pd.getName()));
        }
        boolean flag = updateById(entity.getId(), updateRowMap);
        return flag;
    }
    
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
    public boolean updateById(String id, Map<String, Object> rowMap);
    
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
    
    /**
     * 获取信用信息记录业务层<br/>
     * <功能详细描述>
     * @return [参数说明]
     * 
     * @return CreditInfoRecordService [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    public CreditInfoRecordService getCreditInfoRecordService();
}
