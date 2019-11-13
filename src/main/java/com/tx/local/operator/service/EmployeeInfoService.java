/*
 * 描          述:  <描述>
 * 修  改   人:  
 * 修改时间:  
 * <修改描述:>
 */
package com.tx.local.operator.service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.tx.local.operator.dao.EmployeeInfoDao;
import com.tx.local.operator.model.EmployeeInfo;
import com.tx.core.exceptions.util.AssertUtils;
import com.tx.core.paged.model.PagedList;
import com.tx.core.querier.model.Querier;
import com.tx.core.querier.model.QuerierBuilder;

/**
 * EmployeeInfo的业务层[EmployeeInfoService]
 * <功能详细描述>
 * 
 * @author  
 * @version [版本号]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
@Component("employeeInfoService")
public class EmployeeInfoService {
    
    @SuppressWarnings("unused")
    private Logger logger = LoggerFactory.getLogger(EmployeeInfoService.class);
    
    @Resource(name = "employeeInfoDao")
    private EmployeeInfoDao employeeInfoDao;
    
    /**
     * 新增EmployeeInfo实例<br/>
     * 将employeeInfo插入数据库中保存
     * 1、如果employeeInfo 为空时抛出参数为空异常
     * 2、如果employeeInfo 中部分必要参数为非法值时抛出参数不合法异常
     * 
     * @param employeeInfo [参数说明]
     * @return void [返回类型说明]
     * @exception throws
     * @see [类、类#方法、类#成员]
     */
    @Transactional
    public void insert(EmployeeInfo employeeInfo) {
        //验证参数是否合法
        AssertUtils.notNull(employeeInfo, "employeeInfo is null.");
           
        //FIXME:为添加的数据需要填入默认值的字段填入默认值
		employeeInfo.setLastUpdateDate(new Date());
		employeeInfo.setCreateDate(new Date());
        
        //调用数据持久层对实例进行持久化操作
        this.employeeInfoDao.insert(employeeInfo);
    }
    
    /**
     * 根据id删除EmployeeInfo实例
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
        
        EmployeeInfo condition = new EmployeeInfo();
        condition.setId(id);
        
        int resInt = this.employeeInfoDao.delete(condition);
        boolean flag = resInt > 0;
        return flag;
    }

    /**
     * 根据code删除EmployeeInfo实例
     * 1、当code为empty时抛出异常
     * 2、执行删除后，将返回数据库中被影响的条数 > 0，则返回true
     *
     * @param code
     * @return EmployeeInfo [返回类型说明]
     * @exception throws
     * @see [类、类#方法、类#成员]
     */
    public boolean deleteByCode(String code) {
        AssertUtils.notEmpty(code, "code is empty.");
        
        EmployeeInfo condition = new EmployeeInfo();
        condition.setCode(code);
        
        int resInt = this.employeeInfoDao.delete(condition);
        boolean flag = resInt > 0;
        return flag;
    }
    
    /**
     * 根据id查询EmployeeInfo实例
     * 1、当id为empty时抛出异常
     *
     * @param id
     * @return EmployeeInfo [返回类型说明]
     * @exception throws
     * @see [类、类#方法、类#成员]
     */
    public EmployeeInfo findById(String id) {
        AssertUtils.notEmpty(id, "id is empty.");
        
        EmployeeInfo condition = new EmployeeInfo();
        condition.setId(id);
        
        EmployeeInfo res = this.employeeInfoDao.find(condition);
        return res;
    }

    /**
     * 根据code查询EmployeeInfo实例
     * 1、当code为empty时抛出异常
     *
     * @param code
     * @return EmployeeInfo [返回类型说明]
     * @exception throws
     * @see [类、类#方法、类#成员]
     */
    public EmployeeInfo findByCode(String code) {
        AssertUtils.notEmpty(code, "code is empty.");
        
        EmployeeInfo condition = new EmployeeInfo();
        condition.setCode(code);
        
        EmployeeInfo res = this.employeeInfoDao.find(condition);
        return res;
    }
    
    /**
     * 查询EmployeeInfo实例列表
     * <功能详细描述>
     * @param params      
     * @return [参数说明]
     * 
     * @return List<EmployeeInfo> [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    public List<EmployeeInfo> queryList(
		Map<String,Object> params   
    	) {
        //判断条件合法性
        
        //生成查询条件
        params = params == null ? new HashMap<String, Object>() : params;

        //根据实际情况，填入排序字段等条件，根据是否需要排序，选择调用dao内方法
        List<EmployeeInfo> resList = this.employeeInfoDao.queryList(params);
        
        return resList;
    }
    
    /**
     * 查询EmployeeInfo实例列表
     * <功能详细描述>
     * @param querier      
     * @return [参数说明]
     * 
     * @return List<EmployeeInfo> [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    public List<EmployeeInfo> queryList(
		Querier querier   
    	) {
        //判断条件合法性
        
        //生成查询条件
        querier = querier == null ? QuerierBuilder.newInstance().querier()
                : querier;

        //根据实际情况，填入排序字段等条件，根据是否需要排序，选择调用dao内方法
        List<EmployeeInfo> resList = this.employeeInfoDao.queryList(querier);
        
        return resList;
    }
    
    /**
     * 分页查询EmployeeInfo实例列表
     * <功能详细描述>
     * @param params    
     * @param pageIndex 当前页index从1开始计算
     * @param pageSize 每页显示行数
     * 
     * <功能详细描述>
     * @return [参数说明]
     * 
     * @return List<EmployeeInfo> [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    public PagedList<EmployeeInfo> queryPagedList(
		Map<String,Object> params,
    	int pageIndex,
        int pageSize) {
        //T判断条件合法性
        
        //生成查询条件
        params = params == null ? new HashMap<String, Object>() : params;
 
        //根据实际情况，填入排序字段等条件，根据是否需要排序，选择调用dao内方法
        PagedList<EmployeeInfo> resPagedList = this.employeeInfoDao.queryPagedList(params, pageIndex, pageSize);
        
        return resPagedList;
    }
    
	/**
     * 分页查询EmployeeInfo实例列表
     * <功能详细描述>
     * @param querier    
     * @param pageIndex 当前页index从1开始计算
     * @param pageSize 每页显示行数
     * 
     * <功能详细描述>
     * @return [参数说明]
     * 
     * @return List<EmployeeInfo> [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    public PagedList<EmployeeInfo> queryPagedList(
		Querier querier,
    	int pageIndex,
        int pageSize) {
        //T判断条件合法性
        
        //生成查询条件
        querier = querier == null ? QuerierBuilder.newInstance().querier()
                : querier;
 
        //根据实际情况，填入排序字段等条件，根据是否需要排序，选择调用dao内方法
        PagedList<EmployeeInfo> resPagedList = this.employeeInfoDao.queryPagedList(querier, pageIndex, pageSize);
        
        return resPagedList;
    }
    
    /**
     * 查询EmployeeInfo实例数量<br/>
     * <功能详细描述>
     * @param params      
     * @return [参数说明]
     * 
     * @return List<EmployeeInfo> [返回类型说明]
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
        int res = this.employeeInfoDao.count(params);
        
        return res;
    }
    
    /**
     * 查询EmployeeInfo实例数量<br/>
     * <功能详细描述>
     * @param querier      
     * @return [参数说明]
     * 
     * @return List<EmployeeInfo> [返回类型说明]
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
        int res = this.employeeInfoDao.count(querier);
        
        return res;
    }
    
    /**
     * 判断EmployeeInfo实例是否已经存在<br/>
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
        params.put("excludeId", excludeId);
        
        //根据实际情况，填入排序字段等条件，根据是否需要排序，选择调用dao内方法
        int res = this.employeeInfoDao.count(params);
        
        return res > 0;
    }
    
    /**
     * 判断EmployeeInfo实例是否已经存在<br/>
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
        int res = this.employeeInfoDao.count(querier,excludeId);
        
        return res > 0;
    }
    
    /**
     * 根据id更新EmployeeInfo实例<br/>
     * <功能详细描述>
     * @param employeeInfo
     * @return [参数说明]
     * 
     * @return boolean [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @Transactional
    public boolean updateById(String id,EmployeeInfo employeeInfo) {
        //验证参数是否合法，必填字段是否填写
        AssertUtils.notNull(employeeInfo, "employeeInfo is null.");
        AssertUtils.notEmpty(id, "id is empty.");

        //生成需要更新字段的hashMap
        Map<String, Object> updateRowMap = new HashMap<String, Object>();
        //FIXME:需要更新的字段
		updateRowMap.put("code", employeeInfo.getCode());
		updateRowMap.put("idCardType", employeeInfo.getIdCardType());
		updateRowMap.put("idCardNumber", employeeInfo.getIdCardNumber());
		updateRowMap.put("age", employeeInfo.getAge());
		updateRowMap.put("sex", employeeInfo.getSex());
		updateRowMap.put("name", employeeInfo.getName());
		updateRowMap.put("email", employeeInfo.getEmail());
		updateRowMap.put("entryDate", employeeInfo.getEntryDate());
		updateRowMap.put("phoneNumber", employeeInfo.getPhoneNumber());
		updateRowMap.put("leaving", employeeInfo.isLeaving());
		updateRowMap.put("leavingDate", employeeInfo.getLeavingDate());
		updateRowMap.put("birthday", employeeInfo.getBirthday());
		updateRowMap.put("official", employeeInfo.isOfficial());
		updateRowMap.put("officialDate", employeeInfo.getOfficialDate());
		updateRowMap.put("operator", employeeInfo.getOperator());
		updateRowMap.put("lastUpdateDate", new Date());

        boolean flag = this.employeeInfoDao.update(id,updateRowMap); 
        //如果需要大于1时，抛出异常并回滚，需要在这里修改
        return flag;
    }
    
    /**
     * 根据id更新EmployeeInfo实例<br/>
     * <功能详细描述>
     * @param employeeInfo
     * @return [参数说明]
     * 
     * @return boolean [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @Transactional
    public boolean updateById(EmployeeInfo employeeInfo) {
        //验证参数是否合法，必填字段是否填写
        AssertUtils.notNull(employeeInfo, "employeeInfo is null.");
        AssertUtils.notEmpty(employeeInfo.getId(), "employeeInfo.id is empty.");

        boolean flag = updateById(employeeInfo.getId(),employeeInfo); 
        //如果需要大于1时，抛出异常并回滚，需要在这里修改
        return flag;
    }
}
