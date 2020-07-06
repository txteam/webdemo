/*
 * 描          述:  <描述>
 * 修  改   人:  
 * 修改时间:  
 * <修改描述:>
 */
package com.tx.local.personal.service;

import java.beans.PropertyDescriptor;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.persistence.Column;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.PropertyAccessorFactory;
import org.springframework.core.convert.TypeDescriptor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.tx.core.exceptions.util.AssertUtils;
import com.tx.core.paged.model.PagedList;
import com.tx.core.querier.model.Querier;
import com.tx.core.querier.model.QuerierBuilder;
import com.tx.local.personal.dao.PersonalSummaryDao;
import com.tx.local.personal.model.PersonalSummary;

/**
 * PersonalSummary的业务层[PersonalSummaryService]
 * <功能详细描述>
 * 
 * @author  
 * @version [版本号]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
@Component("personalSummaryService")
public class PersonalSummaryService {
    
    @SuppressWarnings("unused")
    private Logger logger = LoggerFactory
            .getLogger(PersonalSummaryService.class);
    
    @Resource(name = "personalSummaryDao")
    private PersonalSummaryDao personalSummaryDao;
    
    /**
     * 新增PersonalSummary实例<br/>
     * 将personalSummary插入数据库中保存
     * 1、如果personalSummary 为空时抛出参数为空异常
     * 2、如果personalSummary 中部分必要参数为非法值时抛出参数不合法异常
     * 
     * @param personalSummary [参数说明]
     * @return void [返回类型说明]
     * @exception throws
     * @see [类、类#方法、类#成员]
     */
    @Transactional
    public void insert(PersonalSummary personalSummary) {
        //验证参数是否合法
        AssertUtils.notNull(personalSummary, "personalSummary is null.");
        AssertUtils.notEmpty(personalSummary.getVcid(),
                "personalSummary.vcid is empty.");
        
        //FIXME:为添加的数据需要填入默认值的字段填入默认值
        
        //调用数据持久层对实例进行持久化操作
        this.personalSummaryDao.insert(personalSummary);
    }
    
    /**
     * 根据id删除PersonalSummary实例
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
        
        PersonalSummary condition = new PersonalSummary();
        condition.setId(id);
        
        int resInt = this.personalSummaryDao.delete(condition);
        boolean flag = resInt > 0;
        return flag;
    }
    
    /**
     * 根据id查询PersonalSummary实例
     * 1、当id为empty时抛出异常
     *
     * @param id
     * @return PersonalSummary [返回类型说明]
     * @exception throws
     * @see [类、类#方法、类#成员]
     */
    public PersonalSummary findById(String id) {
        AssertUtils.notEmpty(id, "id is empty.");
        
        PersonalSummary condition = new PersonalSummary();
        condition.setId(id);
        
        PersonalSummary res = this.personalSummaryDao.find(condition);
        return res;
    }

    /**
     * 根据id查询PersonalSummary实例
     * 1、当id为empty时抛出异常
     *
     * @param personalId
     * @return PersonalSummary [返回类型说明]
     * @exception throws
     * @see [类、类#方法、类#成员]
     */
    public PersonalSummary findByPersonalId(String personalId) {
        AssertUtils.notEmpty(personalId, "personalId is empty.");

        PersonalSummary condition = new PersonalSummary();
        condition.setPersonalId(personalId);

        PersonalSummary res = this.personalSummaryDao.find(condition);
        return res;
    }

    
    /**
     * 查询PersonalSummary实例列表
     * <功能详细描述>
     * @param params      
     * @return [参数说明]
     * 
     * @return List<PersonalSummary> [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    public List<PersonalSummary> queryList(Map<String, Object> params) {
        //判断条件合法性
        
        //生成查询条件
        params = params == null ? new HashMap<String, Object>() : params;
        
        //根据实际情况，填入排序字段等条件，根据是否需要排序，选择调用dao内方法
        List<PersonalSummary> resList = this.personalSummaryDao
                .queryList(params);
        
        return resList;
    }
    
    /**
     * 查询PersonalSummary实例列表
     * <功能详细描述>
     * @param querier      
     * @return [参数说明]
     * 
     * @return List<PersonalSummary> [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    public List<PersonalSummary> queryList(Querier querier) {
        //判断条件合法性
        
        //生成查询条件
        querier = querier == null ? QuerierBuilder.newInstance().querier()
                : querier;
        
        //根据实际情况，填入排序字段等条件，根据是否需要排序，选择调用dao内方法
        List<PersonalSummary> resList = this.personalSummaryDao
                .queryList(querier);
        
        return resList;
    }
    
    /**
     * 分页查询PersonalSummary实例列表
     * <功能详细描述>
     * @param params    
     * @param pageIndex 当前页index从1开始计算
     * @param pageSize 每页显示行数
     * 
     * <功能详细描述>
     * @return [参数说明]
     * 
     * @return List<PersonalSummary> [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    public PagedList<PersonalSummary> queryPagedList(Map<String, Object> params,
            int pageIndex, int pageSize) {
        //T判断条件合法性
        
        //生成查询条件
        params = params == null ? new HashMap<String, Object>() : params;
        
        //根据实际情况，填入排序字段等条件，根据是否需要排序，选择调用dao内方法
        PagedList<PersonalSummary> resPagedList = this.personalSummaryDao
                .queryPagedList(params, pageIndex, pageSize);
        
        return resPagedList;
    }
    
    /**
     * 分页查询PersonalSummary实例列表
     * <功能详细描述>
     * @param querier    
     * @param pageIndex 当前页index从1开始计算
     * @param pageSize 每页显示行数
     * 
     * <功能详细描述>
     * @return [参数说明]
     * 
     * @return List<PersonalSummary> [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    public PagedList<PersonalSummary> queryPagedList(Querier querier,
            int pageIndex, int pageSize) {
        //T判断条件合法性
        
        //生成查询条件
        querier = querier == null ? QuerierBuilder.newInstance().querier()
                : querier;
        
        //根据实际情况，填入排序字段等条件，根据是否需要排序，选择调用dao内方法
        PagedList<PersonalSummary> resPagedList = this.personalSummaryDao
                .queryPagedList(querier, pageIndex, pageSize);
        
        return resPagedList;
    }
    
    /**
     * 查询PersonalSummary实例数量<br/>
     * <功能详细描述>
     * @param params      
     * @return [参数说明]
     * 
     * @return List<PersonalSummary> [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    public int count(Map<String, Object> params) {
        //判断条件合法性
        
        //生成查询条件
        params = params == null ? new HashMap<String, Object>() : params;
        
        //根据实际情况，填入排序字段等条件，根据是否需要排序，选择调用dao内方法
        int res = this.personalSummaryDao.count(params);
        
        return res;
    }
    
    /**
     * 查询PersonalSummary实例数量<br/>
     * <功能详细描述>
     * @param querier      
     * @return [参数说明]
     * 
     * @return List<PersonalSummary> [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    public int count(Querier querier) {
        //判断条件合法性
        
        //生成查询条件
        querier = querier == null ? QuerierBuilder.newInstance().querier()
                : querier;
        
        //根据实际情况，填入排序字段等条件，根据是否需要排序，选择调用dao内方法
        int res = this.personalSummaryDao.count(querier);
        
        return res;
    }
    
    /**
     * 判断PersonalSummary实例是否已经存在<br/>
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
        int res = this.personalSummaryDao.count(params, excludeId);
        
        return res > 0;
    }
    
    /**
     * 判断PersonalSummary实例是否已经存在<br/>
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
        int res = this.personalSummaryDao.count(querier, excludeId);
        
        return res > 0;
    }
    
    /**
     * 根据id更新PersonalSummary实例<br/>
     * <功能详细描述>
     * @param personalSummary
     * @return [参数说明]
     * 
     * @return boolean [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @Transactional
    public boolean updateById(String id, Map<String, Object> updateRowMap) {
        //验证参数是否合法，必填字段是否填写
        AssertUtils.notEmpty(id, "id is empty.");
        AssertUtils.notEmpty(updateRowMap, "updateRowMap is empty.");
        
        boolean flag = this.personalSummaryDao.update(id, updateRowMap);
        //如果需要大于1时，抛出异常并回滚，需要在这里修改
        return flag;
    }
    
    /**
     * 根据id更新PersonalSummary实例<br/>
     * <功能详细描述>
     * @param personalSummary
     * @return [参数说明]
     * 
     * @return boolean [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @Transactional
    public boolean updateById(String id, PersonalSummary personalSummary) {
        //验证参数是否合法，必填字段是否填写
        AssertUtils.notNull(personalSummary, "personalSummary is null.");
        AssertUtils.notEmpty(id, "id is empty.");
        
        //生成需要更新字段的hashMap
        Map<String, Object> updateRowMap = new HashMap<String, Object>();
        //需要更新的字段
        updateRowMap.put("idCardDeadline", personalSummary.getIdCardDeadline());
        updateRowMap.put("idCardDistrict", personalSummary.getIdCardDistrict());
        updateRowMap.put("idCardExpiredDate",
                personalSummary.getIdCardExpiredDate());
        updateRowMap.put("landArea", personalSummary.getLandArea());
        updateRowMap.put("liveStatus", personalSummary.getLiveStatus());
        updateRowMap.put("frontOfIDCardUrl",
                personalSummary.getFrontOfIDCardUrl());
        updateRowMap.put("laborCount", personalSummary.getLaborCount());
        updateRowMap.put("familyCount", personalSummary.getFamilyCount());
        updateRowMap.put("fatherName", personalSummary.getFatherName());
        updateRowMap.put("fatherAlive", personalSummary.getFatherAlive());
        updateRowMap.put("fatherMobileNumber",
                personalSummary.getFatherMobileNumber());
        updateRowMap.put("motherName", personalSummary.getMotherName());
        updateRowMap.put("motherAlive", personalSummary.getMotherAlive());
        updateRowMap.put("motherMobileNumber",
                personalSummary.getMotherMobileNumber());
        updateRowMap.put("nativePlace", personalSummary.getNativePlace());
        updateRowMap.put("maritalStatus", personalSummary.getMaritalStatus());
        updateRowMap.put("reverseOfIDCardUrl",
                personalSummary.getReverseOfIDCardUrl());
        updateRowMap.put("identityState", personalSummary.getIdentityState());
        updateRowMap.put("marriageDate", personalSummary.getMarriageDate());
        updateRowMap.put("personalId", personalSummary.getPersonalId());
        updateRowMap.put("education", personalSummary.getEducation());
        
        boolean flag = this.personalSummaryDao.update(id, updateRowMap);
        //如果需要大于1时，抛出异常并回滚，需要在这里修改
        return flag;
    }
    
    /**
     * 根据id更新PersonalSummary实例<br/>
     * <功能详细描述>
     * @param personalSummary
     * @return [参数说明]
     * 
     * @return boolean [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @Transactional
    public boolean updateById(PersonalSummary personalSummary) {
        //验证参数是否合法，必填字段是否填写
        AssertUtils.notNull(personalSummary, "personalSummary is null.");
        AssertUtils.notEmpty(personalSummary.getId(),
                "personalSummary.id is empty.");
        
        Map<String, Object> updateRowMap = new HashMap<String, Object>();
        BeanWrapper bw = PropertyAccessorFactory
                .forBeanPropertyAccess(personalSummary);
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
        boolean flag = updateById(personalSummary.getId(), updateRowMap);
        //如果需要大于1时，抛出异常并回滚，需要在这里修改
        return flag;
    }
}
