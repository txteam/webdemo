/*
 * 描          述:  <描述>
 * 修  改   人:  
 * 修改时间:  
 * <修改描述:>
 */
package com.tx.local.institution.service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.tx.local.institution.dao.InstitutionSummaryInfoDao;
import com.tx.local.institution.model.InstitutionSummaryInfo;
import com.tx.core.exceptions.util.AssertUtils;
import com.tx.core.paged.model.PagedList;
import com.tx.core.querier.model.Querier;
import com.tx.core.querier.model.QuerierBuilder;

/**
 * InstitutionSummaryInfo的业务层[InstitutionSummaryInfoService]
 * <功能详细描述>
 * 
 * @author  
 * @version [版本号]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
@Component("institutionSummaryInfoService")
public class InstitutionSummaryInfoService {
    
    @SuppressWarnings("unused")
    private Logger logger = LoggerFactory.getLogger(InstitutionSummaryInfoService.class);
    
    @Resource(name = "institutionSummaryInfoDao")
    private InstitutionSummaryInfoDao institutionSummaryInfoDao;
    
    /**
     * 新增InstitutionSummaryInfo实例<br/>
     * 将institutionSummaryInfo插入数据库中保存
     * 1、如果institutionSummaryInfo 为空时抛出参数为空异常
     * 2、如果institutionSummaryInfo 中部分必要参数为非法值时抛出参数不合法异常
     * 
     * @param institutionSummaryInfo [参数说明]
     * @return void [返回类型说明]
     * @exception throws
     * @see [类、类#方法、类#成员]
     */
    @Transactional
    public void insert(InstitutionSummaryInfo institutionSummaryInfo) {
        //验证参数是否合法
        AssertUtils.notNull(institutionSummaryInfo, "institutionSummaryInfo is null.");
		//AssertUtils.notEmpty(institutionSummaryInfo.getCreditInfoId(), "institutionSummaryInfo.creditInfoId is empty.");
           
        //FIXME:为添加的数据需要填入默认值的字段填入默认值
		institutionSummaryInfo.setLastUpdateDate(new Date());
		institutionSummaryInfo.setCreateDate(new Date());
        
        //调用数据持久层对实例进行持久化操作
        this.institutionSummaryInfoDao.insert(institutionSummaryInfo);
    }
    
    /**
     * 根据id删除InstitutionSummaryInfo实例
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
        
        InstitutionSummaryInfo condition = new InstitutionSummaryInfo();
        condition.setId(id);
        
        int resInt = this.institutionSummaryInfoDao.delete(condition);
        boolean flag = resInt > 0;
        return flag;
    }
    
    /**
     * 根据id查询InstitutionSummaryInfo实例
     * 1、当id为empty时抛出异常
     *
     * @param id
     * @return InstitutionSummaryInfo [返回类型说明]
     * @exception throws
     * @see [类、类#方法、类#成员]
     */
    public InstitutionSummaryInfo findById(String id) {
        AssertUtils.notEmpty(id, "id is empty.");
        
        InstitutionSummaryInfo condition = new InstitutionSummaryInfo();
        condition.setId(id);
        
        InstitutionSummaryInfo res = this.institutionSummaryInfoDao.find(condition);
        return res;
    }

    /**
     * 根据id查询InstitutionSummaryInfo实例
     * 1、当id为empty时抛出异常
     *
     * @param institutionNumber
     * @return InstitutionSummaryInfo [返回类型说明]
     * @exception throws
     * @see [类、类#方法、类#成员]
     */
    public InstitutionSummaryInfo findByInstitutionNumber(String institutionNumber) {
        AssertUtils.notEmpty(institutionNumber, "institutionNumber is empty.");

        InstitutionSummaryInfo condition = new InstitutionSummaryInfo();
        condition.setInstitutionNumber(institutionNumber);

        InstitutionSummaryInfo res = this.institutionSummaryInfoDao.find(condition);
        return res;
    }

    /**
     * 查询InstitutionSummaryInfo实例列表
     * <功能详细描述>
     * @param params      
     * @return [参数说明]
     * 
     * @return List<InstitutionSummaryInfo> [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    public List<InstitutionSummaryInfo> queryList(
		Map<String,Object> params   
    	) {
        //判断条件合法性
        
        //生成查询条件
        params = params == null ? new HashMap<String, Object>() : params;

        //根据实际情况，填入排序字段等条件，根据是否需要排序，选择调用dao内方法
        List<InstitutionSummaryInfo> resList = this.institutionSummaryInfoDao.queryList(params);
        
        return resList;
    }
    
    /**
     * 查询InstitutionSummaryInfo实例列表
     * <功能详细描述>
     * @param querier      
     * @return [参数说明]
     * 
     * @return List<InstitutionSummaryInfo> [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    public List<InstitutionSummaryInfo> queryList(
		Querier querier   
    	) {
        //判断条件合法性
        
        //生成查询条件
        querier = querier == null ? QuerierBuilder.newInstance().querier()
                : querier;

        //根据实际情况，填入排序字段等条件，根据是否需要排序，选择调用dao内方法
        List<InstitutionSummaryInfo> resList = this.institutionSummaryInfoDao.queryList(querier);
        
        return resList;
    }
    
    /**
     * 分页查询InstitutionSummaryInfo实例列表
     * <功能详细描述>
     * @param params    
     * @param pageIndex 当前页index从1开始计算
     * @param pageSize 每页显示行数
     * 
     * <功能详细描述>
     * @return [参数说明]
     * 
     * @return List<InstitutionSummaryInfo> [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    public PagedList<InstitutionSummaryInfo> queryPagedList(
		Map<String,Object> params,
    	int pageIndex,
        int pageSize) {
        //T判断条件合法性
        
        //生成查询条件
        params = params == null ? new HashMap<String, Object>() : params;
 
        //根据实际情况，填入排序字段等条件，根据是否需要排序，选择调用dao内方法
        PagedList<InstitutionSummaryInfo> resPagedList = this.institutionSummaryInfoDao.queryPagedList(params, pageIndex, pageSize);
        
        return resPagedList;
    }
    
	/**
     * 分页查询InstitutionSummaryInfo实例列表
     * <功能详细描述>
     * @param querier    
     * @param pageIndex 当前页index从1开始计算
     * @param pageSize 每页显示行数
     * 
     * <功能详细描述>
     * @return [参数说明]
     * 
     * @return List<InstitutionSummaryInfo> [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    public PagedList<InstitutionSummaryInfo> queryPagedList(
		Querier querier,
    	int pageIndex,
        int pageSize) {
        //T判断条件合法性
        
        //生成查询条件
        querier = querier == null ? QuerierBuilder.newInstance().querier()
                : querier;
 
        //根据实际情况，填入排序字段等条件，根据是否需要排序，选择调用dao内方法
        PagedList<InstitutionSummaryInfo> resPagedList = this.institutionSummaryInfoDao.queryPagedList(querier, pageIndex, pageSize);
        
        return resPagedList;
    }
    
    /**
     * 查询InstitutionSummaryInfo实例数量<br/>
     * <功能详细描述>
     * @param params      
     * @return [参数说明]
     * 
     * @return List<InstitutionSummaryInfo> [返回类型说明]
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
        int res = this.institutionSummaryInfoDao.count(params);
        
        return res;
    }
    
    /**
     * 查询InstitutionSummaryInfo实例数量<br/>
     * <功能详细描述>
     * @param querier      
     * @return [参数说明]
     * 
     * @return List<InstitutionSummaryInfo> [返回类型说明]
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
        int res = this.institutionSummaryInfoDao.count(querier);
        
        return res;
    }
    
    /**
     * 判断InstitutionSummaryInfo实例是否已经存在<br/>
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
        int res = this.institutionSummaryInfoDao.count(params,excludeId);
        
        return res > 0;
    }
    
    /**
     * 判断InstitutionSummaryInfo实例是否已经存在<br/>
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
        int res = this.institutionSummaryInfoDao.count(querier,excludeId);
        
        return res > 0;
    }
    
    /**
     * 根据id更新InstitutionSummaryInfo实例<br/>
     * <功能详细描述>
     * @param institutionSummaryInfo
     * @return [参数说明]
     * 
     * @return boolean [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @Transactional
    public boolean updateById(String id,InstitutionSummaryInfo institutionSummaryInfo) {
        //验证参数是否合法，必填字段是否填写
        AssertUtils.notNull(institutionSummaryInfo, "institutionSummaryInfo is null.");
        AssertUtils.notEmpty(id, "id is empty.");

        //生成需要更新字段的hashMap
        Map<String, Object> updateRowMap = new HashMap<String, Object>();
        //FIXME:需要更新的字段
		updateRowMap.put("taxNumber", institutionSummaryInfo.getTaxNumber());
		updateRowMap.put("landArea", institutionSummaryInfo.getLandArea());
		updateRowMap.put("openAccountLicenseUrl", institutionSummaryInfo.getOpenAccountLicenseUrl());
		updateRowMap.put("agentName", institutionSummaryInfo.getAgentName());
		updateRowMap.put("agentIdCardType", institutionSummaryInfo.getAgentIdCardType());
		updateRowMap.put("agentIdCardNumber", institutionSummaryInfo.getAgentIdCardNumber());
		updateRowMap.put("legalName", institutionSummaryInfo.getLegalName());
		updateRowMap.put("legalIdCardType", institutionSummaryInfo.getLegalIdCardType());
		updateRowMap.put("legalIdCardNumber", institutionSummaryInfo.getLegalIdCardNumber());
		updateRowMap.put("phoneNumber", institutionSummaryInfo.getPhoneNumber());
		updateRowMap.put("clientId", institutionSummaryInfo.getClientId());
		updateRowMap.put("expiryDate", institutionSummaryInfo.getExpiryDate());
		updateRowMap.put("businessLicenseUrl", institutionSummaryInfo.getBusinessLicenseUrl());
		updateRowMap.put("businessLicenseNumber", institutionSummaryInfo.getBusinessLicenseNumber());
		updateRowMap.put("businessLicenseWithSealUrl", institutionSummaryInfo.getBusinessLicenseWithSealUrl());
		updateRowMap.put("institutionNumber", institutionSummaryInfo.getInstitutionNumber());
		updateRowMap.put("authorizationUrl", institutionSummaryInfo.getAuthorizationUrl());
		updateRowMap.put("lastUpdateDate", new Date());

        boolean flag = this.institutionSummaryInfoDao.update(id,updateRowMap); 
        //如果需要大于1时，抛出异常并回滚，需要在这里修改
        return flag;
    }
    
    /**
     * 根据id更新InstitutionSummaryInfo实例<br/>
     * <功能详细描述>
     * @param institutionSummaryInfo
     * @return [参数说明]
     * 
     * @return boolean [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @Transactional
    public boolean updateById(InstitutionSummaryInfo institutionSummaryInfo) {
        //验证参数是否合法，必填字段是否填写
        AssertUtils.notNull(institutionSummaryInfo, "institutionSummaryInfo is null.");
        AssertUtils.notEmpty(institutionSummaryInfo.getId(), "institutionSummaryInfo.id is empty.");

        boolean flag = updateById(institutionSummaryInfo.getId(),institutionSummaryInfo); 
        //如果需要大于1时，抛出异常并回滚，需要在这里修改
        return flag;
    }
}
