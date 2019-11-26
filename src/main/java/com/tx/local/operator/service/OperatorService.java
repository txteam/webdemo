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

import com.tx.core.exceptions.util.AssertUtils;
import com.tx.core.paged.model.PagedList;
import com.tx.core.querier.model.Filter;
import com.tx.core.querier.model.Querier;
import com.tx.core.querier.model.QuerierBuilder;
import com.tx.core.util.MD5Utils;
import com.tx.local.operator.dao.OperatorDao;
import com.tx.local.operator.model.Operator;
import com.tx.local.organization.facade.OrganizationFacade;
import com.tx.local.organization.model.Organization;

/**
 * 操作人员的业务层[OperatorService]
 * <功能详细描述>
 * 
 * @author  
 * @version [版本号]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
@Component("operatorService")
public class OperatorService {
    
    @SuppressWarnings("unused")
    private Logger logger = LoggerFactory.getLogger(OperatorService.class);
    
    @Resource(name = "operatorDao")
    private OperatorDao operatorDao;
    
    @Resource
    private OrganizationFacade organizationFacade;
    
    /**
     * 新增操作人员实例<br/>
     * 将operator插入数据库中保存
     * 1、如果operator 为空时抛出参数为空异常
     * 2、如果operator 中部分必要参数为非法值时抛出参数不合法异常
     * 
     * @param operator [参数说明]
     * @return void [返回类型说明]
     * @exception throws
     * @see [类、类#方法、类#成员]
     */
    @Transactional
    public void insert(Operator operator) {
        //验证参数是否合法
        AssertUtils.notNull(operator, "operator is null.");
        AssertUtils.notEmpty(operator.getLoginName(),
                "operator.loginName is empty.");
        AssertUtils.notEmpty(operator.getOrganizationId(),
                "operator.organizationId is empty.");
        
        Organization org = this.organizationFacade
                .findById(operator.getOrganizationId());
        operator.setVcid(org.getVcid());
        
        //为添加的数据需要填入默认值的字段填入默认值
        operator.setValid(true);
        
        //写入默认时间
        Date now = new Date();
        operator.setCreateDate(now);
        operator.setLastUpdateDate(now);
        operator.setPwdUpdateDate(now);
        operator.setPwdErrCount(0);
        
        //密码加密
        //获取配置的默认密码并设值
        operator.setPassword(MD5Utils.encode("123456"));
        
        //调用数据持久层对实例进行持久化操作
        this.operatorDao.insert(operator);
    }
    
    /**
     * 根据id删除操作人员实例
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
        
        Operator condition = new Operator();
        condition.setId(id);
        
        int resInt = this.operatorDao.delete(condition);
        boolean flag = resInt > 0;
        return flag;
    }
    
    /**
     * 根据id查询操作人员实例
     * 1、当id为empty时抛出异常
     *
     * @param id
     * @return Operator [返回类型说明]
     * @exception throws
     * @see [类、类#方法、类#成员]
     */
    public Operator findById(String id) {
        AssertUtils.notEmpty(id, "id is empty.");
        
        Operator condition = new Operator();
        condition.setId(id);
        
        Operator res = this.operatorDao.find(condition);
        return res;
    }
    
    /**
     * 根据LoginName查询Operator实体
     * 1、当id为empty时抛出异常
     * <功能详细描述>
     * @param id
     * @return [参数说明]
     * 
     * @return Operator [返回类型说明]
     * @exception throws 可能存在数据库访问异常DataAccessException
     * @see [类、类#方法、类#成员]
     */
    public Operator findByLoginName(String loginName) {
        AssertUtils.notEmpty(loginName, "loginName is empty.");
        
        Operator condition = new Operator();
        condition.setLoginName(loginName);
        
        Operator res = this.operatorDao.find(condition);
        return res;
    }
    
    /**
     * 查询操作人员实例列表
     * <功能详细描述>
     * @param valid
     * @param params      
     * @return [参数说明]
     * 
     * @return List<Operator> [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    public List<Operator> queryList(Boolean valid, Map<String, Object> params) {
        //判断条件合法性
        
        //生成查询条件
        params = params == null ? new HashMap<String, Object>() : params;
        params.put("valid", valid);
        
        //根据实际情况，填入排序字段等条件，根据是否需要排序，选择调用dao内方法
        List<Operator> resList = this.operatorDao.queryList(params);
        
        return resList;
    }
    
    /**
     * 查询操作人员实例列表
     * <功能详细描述>
     * @param valid
     * @param querier      
     * @return [参数说明]
     * 
     * @return List<Operator> [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    public List<Operator> queryList(Boolean valid, Querier querier) {
        //判断条件合法性
        
        //生成查询条件
        querier = querier == null ? QuerierBuilder.newInstance().querier()
                : querier;
        if (valid != null) {
            querier.getFilters().add(Filter.eq("valid", valid));
        }
        
        //根据实际情况，填入排序字段等条件，根据是否需要排序，选择调用dao内方法
        List<Operator> resList = this.operatorDao.queryList(querier);
        
        return resList;
    }
    
    /**
     * 分页查询操作人员实例列表
     * <功能详细描述>
     * @param valid
     * @param params    
     * @param pageIndex 当前页index从1开始计算
     * @param pageSize 每页显示行数
     * 
     * <功能详细描述>
     * @return [参数说明]
     * 
     * @return List<Operator> [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    public PagedList<Operator> queryPagedList(Boolean valid,
            Map<String, Object> params, int pageIndex, int pageSize) {
        //T判断条件合法性
        
        //生成查询条件
        params = params == null ? new HashMap<String, Object>() : params;
        params.put("valid", valid);
        
        //根据实际情况，填入排序字段等条件，根据是否需要排序，选择调用dao内方法
        PagedList<Operator> resPagedList = this.operatorDao
                .queryPagedList(params, pageIndex, pageSize);
        
        return resPagedList;
    }
    
    /**
     * 分页查询操作人员实例列表
     * <功能详细描述>
     * @param valid
     * @param querier    
     * @param pageIndex 当前页index从1开始计算
     * @param pageSize 每页显示行数
     * 
     * <功能详细描述>
     * @return [参数说明]
     * 
     * @return List<Operator> [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    public PagedList<Operator> queryPagedList(Boolean valid, Querier querier,
            int pageIndex, int pageSize) {
        //T判断条件合法性
        
        //生成查询条件
        querier = querier == null ? QuerierBuilder.newInstance().querier()
                : querier;
        if (valid != null) {
            querier.getFilters().add(Filter.eq("valid", valid));
        }
        
        //根据实际情况，填入排序字段等条件，根据是否需要排序，选择调用dao内方法
        PagedList<Operator> resPagedList = this.operatorDao
                .queryPagedList(querier, pageIndex, pageSize);
        
        return resPagedList;
    }
    
    /**
     * 查询操作人员实例数量<br/>
     * <功能详细描述>
     * @param valid
     * @param params      
     * @return [参数说明]
     * 
     * @return List<Operator> [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    public int count(Boolean valid, Map<String, Object> params) {
        //判断条件合法性
        
        //生成查询条件
        params = params == null ? new HashMap<String, Object>() : params;
        params.put("valid", valid);
        
        //根据实际情况，填入排序字段等条件，根据是否需要排序，选择调用dao内方法
        int res = this.operatorDao.count(params);
        
        return res;
    }
    
    /**
     * 查询操作人员实例数量<br/>
     * <功能详细描述>
     * @param valid
     * @param querier      
     * @return [参数说明]
     * 
     * @return List<Operator> [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    public int count(Boolean valid, Querier querier) {
        //判断条件合法性
        
        //生成查询条件
        querier = querier == null ? QuerierBuilder.newInstance().querier()
                : querier;
        if (valid != null) {
            querier.getFilters().add(Filter.eq("valid", valid));
        }
        
        //根据实际情况，填入排序字段等条件，根据是否需要排序，选择调用dao内方法
        int res = this.operatorDao.count(querier);
        
        return res;
    }
    
    /**
     * 判断操作人员实例是否已经存在<br/>
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
        params.put("excludeId", excludeId);
        
        //根据实际情况，填入排序字段等条件，根据是否需要排序，选择调用dao内方法
        int res = this.operatorDao.count(params);
        
        return res > 0;
    }
    
    /**
     * 判断操作人员实例是否已经存在<br/>
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
        int res = this.operatorDao.count(querier, excludeId);
        
        return res > 0;
    }
    
    /**
     * 根据id更新操作人员实例<br/>
     * <功能详细描述>
     * @param operator
     * @return [参数说明]
     * 
     * @return boolean [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @Transactional
    public boolean updateById(String id, Operator operator) {
        //验证参数是否合法，必填字段是否填写
        AssertUtils.notNull(operator, "operator is null.");
        AssertUtils.notEmpty(id, "id is empty.");
        
        //生成需要更新字段的hashMap
        Map<String, Object> updateRowMap = new HashMap<String, Object>();
        //需要更新的字段
        //updateRowMap.put("pwdErrCount", operator.getPwdErrCount());
        //updateRowMap.put("pwdUpdateDate", operator.getPwdUpdateDate());
        //updateRowMap.put("mainPostId", operator.getMainPostId());
        updateRowMap.put("userName", operator.getUserName());
        updateRowMap.put("vcid", operator.getVcid());
        updateRowMap.put("organizationId", operator.getOrganizationId());
        
        //updateRowMap.put("loginName", operator.getLoginName());
        //updateRowMap.put("valid", operator.isValid());
        //updateRowMap.put("locked", operator.isLocked());
        //updateRowMap.put("examinePwd", operator.getExaminePwd());
        //updateRowMap.put("historyPwd", operator.getHistoryPwd());
        //updateRowMap.put("invalidDate", operator.getInvalidDate());
        //updateRowMap.put("password", operator.getPassword());
        
        updateRowMap.put("lastUpdateDate", new Date());
        
        boolean flag = this.operatorDao.update(id, updateRowMap);
        //如果需要大于1时，抛出异常并回滚，需要在这里修改
        return flag;
    }
    
    /**
     * 根据id更新操作人员实例<br/>
     * <功能详细描述>
     * @param operator
     * @return [参数说明]
     * 
     * @return boolean [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @Transactional
    public boolean updateById(Operator operator) {
        //验证参数是否合法，必填字段是否填写
        AssertUtils.notNull(operator, "operator is null.");
        AssertUtils.notEmpty(operator.getId(), "operator.id is empty.");
        
        boolean flag = updateById(operator.getId(), operator);
        //如果需要大于1时，抛出异常并回滚，需要在这里修改
        return flag;
    }
    
    /**
     * 根据id禁用操作人员<br/>
     * <功能详细描述>
     * @param id
     * @return [参数说明]
     * 
     * @return boolean [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @Transactional
    public boolean disableById(String id) {
        AssertUtils.notEmpty(id, "id is empty.");
        
        //生成条件
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("id", id);
        params.put("valid", false);
        
        boolean flag = this.operatorDao.update(params) > 0;
        
        return flag;
    }
    
    /**
     * 根据id启用操作人员<br/>
     * <功能详细描述>
     * @param id
     * @return [参数说明]
     * 
     * @return boolean [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @Transactional
    public boolean enableById(String id) {
        AssertUtils.notEmpty(id, "id is empty.");
        
        //生成查询条件
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("id", id);
        params.put("valid", true);
        
        boolean flag = this.operatorDao.update(params) > 0;
        
        return flag;
    }
    
    /**
     * 根据id更新对象
     * <功能详细描述>
     * @param operator
     * @return [参数说明]
     * 
     * @return boolean [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @Transactional
    public boolean updatePwdById(String operatorId, String newPassword) {
        //验证参数是否合法，必填字段是否填写
        AssertUtils.notEmpty(operatorId, "operatorId is empty.");
        AssertUtils.notEmpty(newPassword, "newPassword is empty.");
        
        //生成需要更新字段的hashMap
        Map<String, Object> updateRowMap = new HashMap<String, Object>();
        updateRowMap.put("id", operatorId);
        
        //需要更新的字段
        Date now = new Date();
        updateRowMap.put("lastUpdateDate", now);
        updateRowMap.put("pwdUpdateDate", now);
        updateRowMap.put("password", newPassword);
        int updateRowCount = this.operatorDao.update(updateRowMap);
        
        //如果需要大于1时，抛出异常并回滚，需要在这里修改
        return updateRowCount >= 1;
    }
    
    /**
     * 解锁操作员<br/>
     * <功能详细描述>
     * @param operatorId
     * @return [参数说明]
     * 
     * @return boolean [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @Transactional
    public boolean unlockById(String operatorId) {
        AssertUtils.notEmpty(operatorId, "operatorId is empty.");
        Map<String, Object> updateRowMap = new HashMap<String, Object>();
        updateRowMap.put("id", operatorId);
        updateRowMap.put("lastUpdateDate", new Date());
        updateRowMap.put("locked", false);
        @SuppressWarnings("unused")
        int updateRowCount = this.operatorDao.update(updateRowMap);
        return true;
    }
    
    /**
     * 
     *<功能简述>
     * <功能详细描述>
     * @param operatorId
     * @return [参数说明]
     * 
     * @return boolean [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @Transactional
    public boolean resetById(String operatorId) {
        AssertUtils.notEmpty(operatorId, "operatorId is empty.");
        Map<String, Object> updateRowMap = new HashMap<String, Object>();
        updateRowMap.put("id", operatorId);
        updateRowMap.put("lastUpdateDate", new Date());
        updateRowMap.put("password", MD5Utils.encode("123456"));
        @SuppressWarnings("unused")
        int updateRowCount = this.operatorDao.update(updateRowMap);
        return true;
    }
}
