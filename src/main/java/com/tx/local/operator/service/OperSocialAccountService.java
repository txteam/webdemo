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
import com.tx.core.querier.model.Querier;
import com.tx.core.querier.model.QuerierBuilder;
import com.tx.local.operator.dao.OperSocialAccountDao;
import com.tx.local.operator.model.OperSocialAccount;
import com.tx.local.operator.model.OperSocialAccountTypeEnum;

/**
 * 操作人员第三方账户的业务层[OperSocialAccountService]
 * <功能详细描述>
 * 
 * @author  
 * @version [版本号]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
@Component("operSocialAccountService")
public class OperSocialAccountService {
    
    @SuppressWarnings("unused")
    private Logger logger = LoggerFactory
            .getLogger(OperSocialAccountService.class);
    
    @Resource(name = "operSocialAccountDao")
    private OperSocialAccountDao operSocialAccountDao;
    
    /**
     * 保存操作员安全账户信息<br/>
     * <功能详细描述>
     * @param operSocialAccount [参数说明]
     * 
     * @return void [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @Transactional
    public void save(OperSocialAccount operSocialAccount) {
        //验证参数是否合法
        AssertUtils.notNull(operSocialAccount, "operSocialAccount is null.");
        AssertUtils.notEmpty(operSocialAccount.getUniqueId(),
                "operSocialAccount.uniqueId is empty.");
        AssertUtils.notEmpty(operSocialAccount.getOperatorId(),
                "operSocialAccount.operatorId is empty.");
        AssertUtils.notNull(operSocialAccount.getType(),
                "operSocialAccount.type is null.");
        
        OperSocialAccount db = findByOperatorId(
                operSocialAccount.getOperatorId(), operSocialAccount.getType());
        if (db == null) {
            insert(operSocialAccount);
        } else {
            updateById(db.getId(), operSocialAccount);
        }
    }
    
    /**
     * 新增操作人员第三方账户实例<br/>
     * 将operSocialAccount插入数据库中保存
     * 1、如果operSocialAccount 为空时抛出参数为空异常
     * 2、如果operSocialAccount 中部分必要参数为非法值时抛出参数不合法异常
     * 
     * @param operSocialAccount [参数说明]
     * @return void [返回类型说明]
     * @exception throws
     * @see [类、类#方法、类#成员]
     */
    @Transactional
    public void insert(OperSocialAccount operSocialAccount) {
        //验证参数是否合法
        AssertUtils.notNull(operSocialAccount, "operSocialAccount is null.");
        AssertUtils.notEmpty(operSocialAccount.getUniqueId(),
                "operSocialAccount.uniqueId is empty.");
        AssertUtils.notEmpty(operSocialAccount.getOperatorId(),
                "operSocialAccount.operatorId is empty.");
        AssertUtils.notNull(operSocialAccount.getType(),
                "operSocialAccount.type is null.");
        
        //为添加的数据需要填入默认值的字段填入默认值
        operSocialAccount.setLastUpdateDate(new Date());
        operSocialAccount.setCreateDate(new Date());
        
        //调用数据持久层对实例进行持久化操作
        this.operSocialAccountDao.insert(operSocialAccount);
    }
    
    /**
     * 根据id删除操作人员第三方账户实例
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
        
        OperSocialAccount condition = new OperSocialAccount();
        condition.setId(id);
        
        int resInt = this.operSocialAccountDao.delete(condition);
        boolean flag = resInt > 0;
        return flag;
    }
    
    /**
     * 根据id删除操作人员第三方账户实例
     * 1、如果入参数为空，则抛出异常
     * 2、执行删除后，将返回数据库中被影响的条数 > 0，则返回true
     *
     * @param id
     * @return boolean 删除的条数>0则为true [返回类型说明]
     * @exception throws 
     * @see [类、类#方法、类#成员]
     */
    @Transactional
    public boolean deleteByOperatorId(String operatorId,
            OperSocialAccountTypeEnum type) {
        AssertUtils.notEmpty(operatorId, "operatorId is empty.");
        AssertUtils.notNull(type, "type is null.");
        
        OperSocialAccount condition = new OperSocialAccount();
        condition.setOperatorId(operatorId);
        condition.setType(type);
        
        int resInt = this.operSocialAccountDao.delete(condition);
        boolean flag = resInt > 0;
        return flag;
    }
    
    /**
     * 根据id删除操作人员第三方账户实例
     * 1、如果入参数为空，则抛出异常
     * 2、执行删除后，将返回数据库中被影响的条数 > 0，则返回true
     *
     * @param id
     * @return boolean 删除的条数>0则为true [返回类型说明]
     * @exception throws 
     * @see [类、类#方法、类#成员]
     */
    @Transactional
    public boolean deleteByUniqueId(String uniqueId,
            OperSocialAccountTypeEnum type) {
        AssertUtils.notEmpty(uniqueId, "uniqueId is empty.");
        AssertUtils.notNull(type, "type is null.");
        
        OperSocialAccount condition = new OperSocialAccount();
        condition.setUniqueId(uniqueId);
        condition.setType(type);
        
        int resInt = this.operSocialAccountDao.delete(condition);
        boolean flag = resInt > 0;
        return flag;
    }
    
    /**
     * 根据id查询操作人员第三方账户实例
     * 1、当id为empty时抛出异常
     *
     * @param id
     * @return OperSocialAccount [返回类型说明]
     * @exception throws
     * @see [类、类#方法、类#成员]
     */
    public OperSocialAccount findById(String id) {
        AssertUtils.notEmpty(id, "id is empty.");
        
        OperSocialAccount condition = new OperSocialAccount();
        condition.setId(id);
        
        OperSocialAccount res = this.operSocialAccountDao.find(condition);
        return res;
    }
    
    /**
     * 根据operatorId查询操作人员第三方账户实例
     * 1、当id为empty时抛出异常
     *
     * @param id
     * @return OperSocialAccount [返回类型说明]
     * @exception throws
     * @see [类、类#方法、类#成员]
     */
    public OperSocialAccount findByOperatorId(String operatorId,
            OperSocialAccountTypeEnum type) {
        AssertUtils.notEmpty(operatorId, "operatorId is empty.");
        AssertUtils.notNull(type, "type is null.");
        
        OperSocialAccount condition = new OperSocialAccount();
        condition.setOperatorId(operatorId);
        condition.setType(type);
        
        OperSocialAccount res = this.operSocialAccountDao.find(condition);
        return res;
    }
    
    /**
     * 查询操作人员第三方账户实例列表
     * <功能详细描述>
     * @param params      
     * @return [参数说明]
     * 
     * @return List<OperSocialAccount> [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    public List<OperSocialAccount> queryList(Map<String, Object> params) {
        //判断条件合法性
        
        //生成查询条件
        params = params == null ? new HashMap<String, Object>() : params;
        
        //根据实际情况，填入排序字段等条件，根据是否需要排序，选择调用dao内方法
        List<OperSocialAccount> resList = this.operSocialAccountDao
                .queryList(params);
        
        return resList;
    }
    
    /**
     * 查询操作人员第三方账户实例列表
     * <功能详细描述>
     * @param querier      
     * @return [参数说明]
     * 
     * @return List<OperSocialAccount> [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    public List<OperSocialAccount> queryList(Querier querier) {
        //判断条件合法性
        
        //生成查询条件
        querier = querier == null ? QuerierBuilder.newInstance().querier()
                : querier;
        
        //根据实际情况，填入排序字段等条件，根据是否需要排序，选择调用dao内方法
        List<OperSocialAccount> resList = this.operSocialAccountDao
                .queryList(querier);
        
        return resList;
    }
    
    /**
     * 分页查询操作人员第三方账户实例列表
     * <功能详细描述>
     * @param params    
     * @param pageIndex 当前页index从1开始计算
     * @param pageSize 每页显示行数
     * 
     * <功能详细描述>
     * @return [参数说明]
     * 
     * @return List<OperSocialAccount> [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    public PagedList<OperSocialAccount> queryPagedList(
            Map<String, Object> params, int pageIndex, int pageSize) {
        //T判断条件合法性
        
        //生成查询条件
        params = params == null ? new HashMap<String, Object>() : params;
        
        //根据实际情况，填入排序字段等条件，根据是否需要排序，选择调用dao内方法
        PagedList<OperSocialAccount> resPagedList = this.operSocialAccountDao
                .queryPagedList(params, pageIndex, pageSize);
        
        return resPagedList;
    }
    
    /**
     * 分页查询操作人员第三方账户实例列表
     * <功能详细描述>
     * @param querier    
     * @param pageIndex 当前页index从1开始计算
     * @param pageSize 每页显示行数
     * 
     * <功能详细描述>
     * @return [参数说明]
     * 
     * @return List<OperSocialAccount> [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    public PagedList<OperSocialAccount> queryPagedList(Querier querier,
            int pageIndex, int pageSize) {
        //T判断条件合法性
        
        //生成查询条件
        querier = querier == null ? QuerierBuilder.newInstance().querier()
                : querier;
        
        //根据实际情况，填入排序字段等条件，根据是否需要排序，选择调用dao内方法
        PagedList<OperSocialAccount> resPagedList = this.operSocialAccountDao
                .queryPagedList(querier, pageIndex, pageSize);
        
        return resPagedList;
    }
    
    /**
     * 查询操作人员第三方账户实例数量<br/>
     * <功能详细描述>
     * @param params      
     * @return [参数说明]
     * 
     * @return List<OperSocialAccount> [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    public int count(Map<String, Object> params) {
        //判断条件合法性
        
        //生成查询条件
        params = params == null ? new HashMap<String, Object>() : params;
        
        //根据实际情况，填入排序字段等条件，根据是否需要排序，选择调用dao内方法
        int res = this.operSocialAccountDao.count(params);
        
        return res;
    }
    
    /**
     * 查询操作人员第三方账户实例数量<br/>
     * <功能详细描述>
     * @param querier      
     * @return [参数说明]
     * 
     * @return List<OperSocialAccount> [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    public int count(Querier querier) {
        //判断条件合法性
        
        //生成查询条件
        querier = querier == null ? QuerierBuilder.newInstance().querier()
                : querier;
        
        //根据实际情况，填入排序字段等条件，根据是否需要排序，选择调用dao内方法
        int res = this.operSocialAccountDao.count(querier);
        
        return res;
    }
    
    /**
     * 判断操作人员第三方账户实例是否已经存在<br/>
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
        int res = this.operSocialAccountDao.count(params, excludeId);
        
        return res > 0;
    }
    
    /**
     * 判断操作人员第三方账户实例是否已经存在<br/>
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
        int res = this.operSocialAccountDao.count(querier, excludeId);
        
        return res > 0;
    }
    
    /**
     * 根据id更新操作人员第三方账户实例<br/>
     * <功能详细描述>
     * @param operSocialAccount
     * @return [参数说明]
     * 
     * @return boolean [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @Transactional
    public boolean updateById(String id, OperSocialAccount operSocialAccount) {
        //验证参数是否合法，必填字段是否填写
        AssertUtils.notNull(operSocialAccount, "operSocialAccount is null.");
        AssertUtils.notEmpty(id, "id is empty.");
        
        //生成需要更新字段的hashMap
        Map<String, Object> updateRowMap = new HashMap<String, Object>();
        //需要更新的字段
        //updateRowMap.put("operatorId", operSocialAccount.getOperatorId());
        //updateRowMap.put("uniqueId", operSocialAccount.getUniqueId());
        //updateRowMap.put("type", operSocialAccount.getType());
        updateRowMap.put("username", operSocialAccount.getUsername());
        updateRowMap.put("headImgUrl", operSocialAccount.getHeadImgUrl());
        updateRowMap.put("attributes", operSocialAccount.getAttributes());
        updateRowMap.put("lastUpdateDate", new Date());
        
        boolean flag = this.operSocialAccountDao.update(id, updateRowMap);
        //如果需要大于1时，抛出异常并回滚，需要在这里修改
        return flag;
    }
    
    /**
     * 根据id更新操作人员第三方账户实例<br/>
     * <功能详细描述>
     * @param operSocialAccount
     * @return [参数说明]
     * 
     * @return boolean [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @Transactional
    public boolean updateById(OperSocialAccount operSocialAccount) {
        //验证参数是否合法，必填字段是否填写
        AssertUtils.notNull(operSocialAccount, "operSocialAccount is null.");
        AssertUtils.notEmpty(operSocialAccount.getId(),
                "operSocialAccount.id is empty.");
        
        boolean flag = updateById(operSocialAccount.getId(), operSocialAccount);
        //如果需要大于1时，抛出异常并回滚，需要在这里修改
        return flag;
    }
}
