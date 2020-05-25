/*
 * 描          述:  <描述>
 * 修  改   人:  
 * 修改时间:  
 * <修改描述:>
 */
package com.tx.local.personal.service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.tx.local.personal.dao.PersonalInfoDao;
import com.tx.local.personal.model.PersonalInfo;
import com.tx.core.exceptions.util.AssertUtils;
import com.tx.core.paged.model.PagedList;
import com.tx.core.querier.model.Querier;
import com.tx.core.querier.model.QuerierBuilder;

/**
 * PersonalInfo的业务层[PersonalInfoService]
 * <功能详细描述>
 * 
 * @author  
 * @version [版本号]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
@Component("personalInfoService")
public class PersonalInfoService {
    
    @SuppressWarnings("unused")
    private Logger logger = LoggerFactory.getLogger(PersonalInfoService.class);
    
    @Resource(name = "personalInfoDao")
    private PersonalInfoDao personalInfoDao;
    
    /**
     * 新增PersonalInfo实例<br/>
     * 将personalInfo插入数据库中保存
     * 1、如果personalInfo 为空时抛出参数为空异常
     * 2、如果personalInfo 中部分必要参数为非法值时抛出参数不合法异常
     * 
     * @param personalInfo [参数说明]
     * @return void [返回类型说明]
     * @exception throws
     * @see [类、类#方法、类#成员]
     */
    @Transactional
    public void insert(PersonalInfo personalInfo) {
        //验证参数是否合法
        AssertUtils.notNull(personalInfo, "personalInfo is null.");
		AssertUtils.notEmpty(personalInfo.getLastName(), "personalInfo.lastName is empty.");
		AssertUtils.notEmpty(personalInfo.getName(), "personalInfo.name is empty.");
		AssertUtils.notEmpty(personalInfo.getType(), "personalInfo.type is empty.");
		AssertUtils.notEmpty(personalInfo.getVcid(), "personalInfo.vcid is empty.");
		AssertUtils.notEmpty(personalInfo.getFristName(), "personalInfo.fristName is empty.");
		AssertUtils.notEmpty(personalInfo.getClientId(), "personalInfo.clientId is empty.");
		AssertUtils.notEmpty(personalInfo.getCreditInfoId(), "personalInfo.creditInfoId is empty.");
		AssertUtils.notEmpty(personalInfo.isCreditInfoBinding(), "personalInfo.creditInfoBinding is empty.");
		AssertUtils.notEmpty(personalInfo.isModifyAble(), "personalInfo.modifyAble is empty.");
           
        //FIXME:为添加的数据需要填入默认值的字段填入默认值
		personalInfo.setLastUpdateDate(new Date());
		personalInfo.setCreateDate(new Date());
        
        //调用数据持久层对实例进行持久化操作
        this.personalInfoDao.insert(personalInfo);
    }
    
    /**
     * 根据id删除PersonalInfo实例
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
        
        PersonalInfo condition = new PersonalInfo();
        condition.setId(id);
        
        int resInt = this.personalInfoDao.delete(condition);
        boolean flag = resInt > 0;
        return flag;
    }
    
    /**
     * 根据id查询PersonalInfo实例
     * 1、当id为empty时抛出异常
     *
     * @param id
     * @return PersonalInfo [返回类型说明]
     * @exception throws
     * @see [类、类#方法、类#成员]
     */
    public PersonalInfo findById(String id) {
        AssertUtils.notEmpty(id, "id is empty.");
        
        PersonalInfo condition = new PersonalInfo();
        condition.setId(id);
        
        PersonalInfo res = this.personalInfoDao.find(condition);
        return res;
    }
    
    /**
     * 查询PersonalInfo实例列表
     * <功能详细描述>
     * @param params      
     * @return [参数说明]
     * 
     * @return List<PersonalInfo> [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    public List<PersonalInfo> queryList(
		Map<String,Object> params   
    	) {
        //判断条件合法性
        
        //生成查询条件
        params = params == null ? new HashMap<String, Object>() : params;

        //根据实际情况，填入排序字段等条件，根据是否需要排序，选择调用dao内方法
        List<PersonalInfo> resList = this.personalInfoDao.queryList(params);
        
        return resList;
    }
    
    /**
     * 查询PersonalInfo实例列表
     * <功能详细描述>
     * @param querier      
     * @return [参数说明]
     * 
     * @return List<PersonalInfo> [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    public List<PersonalInfo> queryList(
		Querier querier   
    	) {
        //判断条件合法性
        
        //生成查询条件
        querier = querier == null ? QuerierBuilder.newInstance().querier()
                : querier;

        //根据实际情况，填入排序字段等条件，根据是否需要排序，选择调用dao内方法
        List<PersonalInfo> resList = this.personalInfoDao.queryList(querier);
        
        return resList;
    }
    
    /**
     * 分页查询PersonalInfo实例列表
     * <功能详细描述>
     * @param params    
     * @param pageIndex 当前页index从1开始计算
     * @param pageSize 每页显示行数
     * 
     * <功能详细描述>
     * @return [参数说明]
     * 
     * @return List<PersonalInfo> [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    public PagedList<PersonalInfo> queryPagedList(
		Map<String,Object> params,
    	int pageIndex,
        int pageSize) {
        //T判断条件合法性
        
        //生成查询条件
        params = params == null ? new HashMap<String, Object>() : params;
 
        //根据实际情况，填入排序字段等条件，根据是否需要排序，选择调用dao内方法
        PagedList<PersonalInfo> resPagedList = this.personalInfoDao.queryPagedList(params, pageIndex, pageSize);
        
        return resPagedList;
    }
    
	/**
     * 分页查询PersonalInfo实例列表
     * <功能详细描述>
     * @param querier    
     * @param pageIndex 当前页index从1开始计算
     * @param pageSize 每页显示行数
     * 
     * <功能详细描述>
     * @return [参数说明]
     * 
     * @return List<PersonalInfo> [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    public PagedList<PersonalInfo> queryPagedList(
		Querier querier,
    	int pageIndex,
        int pageSize) {
        //T判断条件合法性
        
        //生成查询条件
        querier = querier == null ? QuerierBuilder.newInstance().querier()
                : querier;
 
        //根据实际情况，填入排序字段等条件，根据是否需要排序，选择调用dao内方法
        PagedList<PersonalInfo> resPagedList = this.personalInfoDao.queryPagedList(querier, pageIndex, pageSize);
        
        return resPagedList;
    }
    
    /**
     * 查询PersonalInfo实例数量<br/>
     * <功能详细描述>
     * @param params      
     * @return [参数说明]
     * 
     * @return List<PersonalInfo> [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    public int count(
		Map<String,Object> params   
    	) {
        //判断条件合法性
        
        //生成查询条件
        params = params == null ? new HashMap<String, Object>() : params;

        //根据实际情况，填入排序字段等条件，根据是否需要排序，选择调用dao内方法
        int res = this.personalInfoDao.count(params);
        
        return res;
    }
    
    /**
     * 查询PersonalInfo实例数量<br/>
     * <功能详细描述>
     * @param querier      
     * @return [参数说明]
     * 
     * @return List<PersonalInfo> [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    public int count(
		Querier querier   
    	) {
        //判断条件合法性
        
        //生成查询条件
        querier = querier == null ? QuerierBuilder.newInstance().querier()
                : querier;

        //根据实际情况，填入排序字段等条件，根据是否需要排序，选择调用dao内方法
        int res = this.personalInfoDao.count(querier);
        
        return res;
    }
    
    /**
     * 判断PersonalInfo实例是否已经存在<br/>
     * <功能详细描述>
     * @param key2valueMap
     * @param excludeId
     * @return
     * 
     * @return int [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    public boolean exists(Map<String,String> key2valueMap, String excludeId) {
        AssertUtils.notEmpty(key2valueMap, "key2valueMap is empty");
        
        //生成查询条件
        Map<String, Object> params = new HashMap<String, Object>();
        params.putAll(key2valueMap);
        
        //根据实际情况，填入排序字段等条件，根据是否需要排序，选择调用dao内方法
        int res = this.personalInfoDao.count(params,excludeId);
        
        return res > 0;
    }
    
    /**
     * 判断PersonalInfo实例是否已经存在<br/>
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
        int res = this.personalInfoDao.count(querier,excludeId);
        
        return res > 0;
    }
    
    /**
     * 根据id更新PersonalInfo实例<br/>
     * <功能详细描述>
     * @param personalInfo
     * @return [参数说明]
     * 
     * @return boolean [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @Transactional
    public boolean updateById(String id,PersonalInfo personalInfo) {
        //验证参数是否合法，必填字段是否填写
        AssertUtils.notNull(personalInfo, "personalInfo is null.");
        AssertUtils.notEmpty(id, "id is empty.");
		AssertUtils.notEmpty(personalInfo.getName(), "personalInfo.name is empty.");
		AssertUtils.notEmpty(personalInfo.getType(), "personalInfo.type is empty.");
		AssertUtils.notEmpty(personalInfo.isCreditInfoBinding(), "personalInfo.creditInfoBinding is empty.");
		AssertUtils.notEmpty(personalInfo.isModifyAble(), "personalInfo.modifyAble is empty.");

        //生成需要更新字段的hashMap
        Map<String, Object> updateRowMap = new HashMap<String, Object>();
        //FIXME:需要更新的字段
		updateRowMap.put("sex", personalInfo.getSex());
		updateRowMap.put("city", personalInfo.getCity());
		updateRowMap.put("fullAddress", personalInfo.getFullAddress());
		updateRowMap.put("lastUpdateUserId", personalInfo.getLastUpdateUserId());
		updateRowMap.put("name", personalInfo.getName());
		updateRowMap.put("type", personalInfo.getType());
		updateRowMap.put("county", personalInfo.getCounty());
		updateRowMap.put("creditInfoBinding", personalInfo.isCreditInfoBinding());
		updateRowMap.put("modifyAble", personalInfo.isModifyAble());
		updateRowMap.put("remark", personalInfo.getRemark());
		updateRowMap.put("address", personalInfo.getAddress());
		updateRowMap.put("birthday", personalInfo.getBirthday());
		updateRowMap.put("district", personalInfo.getDistrict());
		updateRowMap.put("province", personalInfo.getProvince());
		updateRowMap.put("lastUpdateDate", new Date());

        boolean flag = this.personalInfoDao.update(id,updateRowMap); 
        //如果需要大于1时，抛出异常并回滚，需要在这里修改
        return flag;
    }
    
    /**
     * 根据id更新PersonalInfo实例<br/>
     * <功能详细描述>
     * @param personalInfo
     * @return [参数说明]
     * 
     * @return boolean [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @Transactional
    public boolean updateById(PersonalInfo personalInfo) {
        //验证参数是否合法，必填字段是否填写
        AssertUtils.notNull(personalInfo, "personalInfo is null.");
        AssertUtils.notEmpty(personalInfo.getId(), "personalInfo.id is empty.");

        boolean flag = updateById(personalInfo.getId(),personalInfo); 
        //如果需要大于1时，抛出异常并回滚，需要在这里修改
        return flag;
    }
}
