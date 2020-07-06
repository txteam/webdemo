/*
 * 描          述:  <描述>
 * 修  改   人:  PengQingyang
 * 修改时间:  2020年6月12日
 * <修改描述:>
 */
package com.tx.local.creditinfo.service.impl;

import java.beans.PropertyDescriptor;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.annotation.Resource;
import javax.persistence.Column;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.beans.PropertyAccessorFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.core.convert.TypeDescriptor;
import org.springframework.transaction.annotation.Transactional;

import com.tx.core.exceptions.util.AssertUtils;
import com.tx.core.mybatis.support.MyBatisDaoSupport;
import com.tx.core.paged.model.PagedList;
import com.tx.core.querier.model.Querier;
import com.tx.core.querier.model.QuerierBuilder;
import com.tx.local.creditinfo.dao.CreditLinkedDao;
import com.tx.local.creditinfo.dao.impl.DefaultCreditLinkedDaoImpl;
import com.tx.local.creditinfo.model.CreditSingleLinked;
import com.tx.local.creditinfo.service.CreditInfoRecordService;
import com.tx.local.creditinfo.service.CreditSingleLinkedService;

/**
 * 默认的信用信息关联对象业务层<br/>
 * <功能详细描述>
 * 
 * @author  PengQingyang
 * @version  [版本号, 2020年6月12日]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public class DefaultCreditSingleLinkedServiceImpl<T extends CreditSingleLinked>
        implements CreditSingleLinkedService<T>, InitializingBean {
    
    @SuppressWarnings("unused")
    private Logger logger = LoggerFactory
            .getLogger(CreditInfoRecordService.class);
    
    @Resource(name = "creditInfoRecordService")
    private CreditInfoRecordService creditInfoRecordService;
    
    @Resource(name = "myBatisDaoSupport")
    private MyBatisDaoSupport myBatisDaoSupport;
    
    private CreditLinkedDao<T> creditLinkedDao;
    
    /** 类型 */
    private Class<T> type;
    
    /** pdMap */
    private Map<String, PropertyDescriptor> pdMap = new HashMap<>();
    
    /** tyMap */
    private Map<String, TypeDescriptor> typeMap = new HashMap<>();
    
    /** 默认的信用信息链接 */
    public DefaultCreditSingleLinkedServiceImpl() {
        super();
    }
    
    /** 默认的信用信息链接 */
    public DefaultCreditSingleLinkedServiceImpl(Class<T> type) {
        super();
        this.type = type;
    }
    
    /**
     * @throws Exception
     */
    @Override
    public void afterPropertiesSet() throws Exception {
        AssertUtils.notNull(this.type, "type is null.");
        //持久层
        DefaultCreditLinkedDaoImpl<T> dao = new DefaultCreditLinkedDaoImpl<T>(
                type, this.myBatisDaoSupport);
        dao.afterPropertiesSet();
        this.creditLinkedDao = dao;
        
        //bw读取pdMap typeMap
        BeanWrapper bw = new BeanWrapperImpl(this.type);
        for (PropertyDescriptor pd : BeanUtils.getPropertyDescriptors(type)) {
            if (pd.getReadMethod() == null || pd.getWriteMethod() == null) {
                continue;
            }
            this.pdMap.put(pd.getName(), pd);
            this.typeMap.put(pd.getName(),
                    bw.getPropertyTypeDescriptor(pd.getName()));
        }
    }
    
    /**
     * @return
     */
    @Override
    public Class<T> type() {
        return this.type;
    }
    
    /**
     * @param entity
     * @return
     */
    @Override
    @Transactional
    public T insert(T entity) {
        //验证参数是否合法
        AssertUtils.notNull(entity, "entity is null.");
        AssertUtils.notEmpty(entity.getCreditInfoId(),
                "entity.creditInfoId is empty.");
        AssertUtils.notEmpty(entity.getClientId(), "entity.clientId is empty.");
        
        //为添加的数据需要填入默认值的字段填入默认值
        BeanWrapper bw = PropertyAccessorFactory.forBeanPropertyAccess(entity);
        Date now = new Date();
        if (this.pdMap.containsKey("lastUpdateDate")
                || Date.class.isAssignableFrom(
                        this.pdMap.get("lastUpdateDate").getPropertyType())) {
            bw.setPropertyValue("lastUpdateDate", now);
        }
        if (this.pdMap.containsKey("createDate") || Date.class.isAssignableFrom(
                this.pdMap.get("createDate").getPropertyType())) {
            bw.setPropertyValue("createDate", now);
        }
        
        //调用数据持久层对实例进行持久化操作
        this.creditLinkedDao.insert(entity);
        return entity;
    }
    
    /**
     * @param id
     * @return
     */
    @Override
    @Transactional
    public boolean deleteById(String id) {
        AssertUtils.notEmpty(id, "id is empty.");
        
        T condition = BeanUtils.instantiateClass(this.type);
        condition.setId(id);
        
        int resInt = this.creditLinkedDao.delete(condition);
        boolean flag = resInt > 0;
        return flag;
    }
    
    /**
     * @param id
     * @param creditInfoRecord
     * @return
     */
    @Override
    @Transactional
    public boolean updateById(String id, Map<String, Object> rowMap) {
        AssertUtils.notEmpty(id, "id is empty.");
        
        Map<String, Object> filterRowMap = new HashMap<>();
        for (Entry<String, Object> entryTemp : rowMap.entrySet()) {
            String key = entryTemp.getKey();
            if (StringUtils.equalsAnyIgnoreCase(key, "id")
                    || StringUtils.equalsAnyIgnoreCase(key, "createDate")) {
                continue;
            }
            if (!this.typeMap.containsKey(key)) {
                continue;
            }
            TypeDescriptor td = this.typeMap.get(key);
            if (td.hasAnnotation(Column.class)
                    && !td.getAnnotation(Column.class).updatable()) {
                //如果有注解，并且标注为不能更新，则不能进行更新
                continue;
            }
            filterRowMap.put(key, entryTemp.getValue());
        }
        
        boolean flag = this.creditLinkedDao.update(id, filterRowMap);
        return flag;
    }
    
    /**
     * @param id
     * @return
     */
    @Override
    public T findById(String id) {
        AssertUtils.notEmpty(id, "id is empty.");
        
        T condition = BeanUtils.instantiateClass(this.type);
        condition.setId(id);
        T res = this.creditLinkedDao.find(condition);
        return res;
    }
    
    /**
     * @param querier
     * @return
     */
    @Override
    public List<T> queryList(Querier querier) {
        //生成查询条件
        querier = querier == null ? QuerierBuilder.newInstance().querier()
                : querier;
        
        //根据实际情况，填入排序字段等条件，根据是否需要排序，选择调用dao内方法
        List<T> resList = this.creditLinkedDao.queryList(querier);
        return resList;
    }
    
    /**
     * @param querier
     * @param pageIndex
     * @param pageSize
     * @return
     */
    @Override
    public PagedList<T> queryPagedList(Querier querier, int pageIndex,
            int pageSize) {
        //生成查询条件
        querier = querier == null ? QuerierBuilder.newInstance().querier()
                : querier;
        
        //根据实际情况，填入排序字段等条件，根据是否需要排序，选择调用dao内方法
        PagedList<T> resPagedList = this.creditLinkedDao
                .queryPagedList(querier, pageIndex, pageSize);
        
        return resPagedList;
    }
    
    /**
     * @param querier
     * @return
     */
    @Override
    public int count(Querier querier) {
        //生成查询条件
        querier = querier == null ? QuerierBuilder.newInstance().querier()
                : querier;
        
        //根据实际情况，填入排序字段等条件，根据是否需要排序，选择调用dao内方法
        int res = this.creditLinkedDao.count(querier);
        
        return res;
    }
    
    /**
     * @param querier
     * @param excludeId
     * @return
     */
    @Override
    public boolean exists(Querier querier, String excludeId) {
        AssertUtils.notNull(querier, "querier is null.");
        
        //根据实际情况，填入排序字段等条件，根据是否需要排序，选择调用dao内方法
        int res = this.creditLinkedDao.count(querier, excludeId);
        
        return res > 0;
    }
    
    /**
     * @return
     */
    @Override
    public CreditInfoRecordService getCreditInfoRecordService() {
        return this.creditInfoRecordService;
    }
    
    /**
     * @param 对creditInfoRecordService进行赋值
     */
    public void setCreditInfoRecordService(
            CreditInfoRecordService creditInfoRecordService) {
        this.creditInfoRecordService = creditInfoRecordService;
    }
    
    /**
     * @param 对myBatisDaoSupport进行赋值
     */
    public void setMyBatisDaoSupport(MyBatisDaoSupport myBatisDaoSupport) {
        this.myBatisDaoSupport = myBatisDaoSupport;
    }
    
    /**
     * @param 对type进行赋值
     */
    public void setType(Class<T> type) {
        this.type = type;
    }
}
