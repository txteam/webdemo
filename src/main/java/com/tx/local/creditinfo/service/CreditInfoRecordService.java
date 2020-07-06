/*
 * 描          述:  <描述>
 * 修  改   人:  
 * 修改时间:  
 * <修改描述:>
 */
package com.tx.local.creditinfo.service;

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
import com.tx.local.creditinfo.dao.CreditInfoRecordDao;
import com.tx.local.creditinfo.model.CreditInfoRecord;

/**
 * CreditInfoRecord的业务层[CreditInfoRecordService]
 * <功能详细描述>
 * 
 * @author  
 * @version [版本号]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
@Component("creditInfoRecordService")
public class CreditInfoRecordService {
    
    @SuppressWarnings("unused")
    private Logger logger = LoggerFactory
            .getLogger(CreditInfoRecordService.class);
    
    @Resource(name = "creditInfoRecordDao")
    private CreditInfoRecordDao creditInfoRecordDao;
    
    /**
     * 新增CreditInfoRecord实例<br/>
     * 将creditInfoRecord插入数据库中保存
     * 1、如果creditInfoRecord 为空时抛出参数为空异常
     * 2、如果creditInfoRecord 中部分必要参数为非法值时抛出参数不合法异常
     * 
     * @param creditInfoRecord [参数说明]
     * @return void [返回类型说明]
     * @exception throws
     * @see [类、类#方法、类#成员]
     */
    @Transactional
    public void insert(CreditInfoRecord creditInfoRecord) {
        //验证参数是否合法
        AssertUtils.notNull(creditInfoRecord, "creditInfoRecord is null.");
        AssertUtils.notEmpty(creditInfoRecord.getType(),
                "creditInfoRecord.type is empty.");
        AssertUtils.notEmpty(creditInfoRecord.getClientId(),
                "creditInfoRecord.clientId is empty.");
        
        //为添加的数据需要填入默认值的字段填入默认值
        creditInfoRecord.setLastUpdateDate(new Date());
        creditInfoRecord.setCreateDate(new Date());
        
        //调用数据持久层对实例进行持久化操作
        this.creditInfoRecordDao.insert(creditInfoRecord);
    }
    
    /**
     * 根据id删除CreditInfoRecord实例
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
        
        CreditInfoRecord condition = new CreditInfoRecord();
        condition.setId(id);
        
        int resInt = this.creditInfoRecordDao.delete(condition);
        boolean flag = resInt > 0;
        return flag;
    }
    
    /**
     * 根据id查询CreditInfoRecord实例
     * 1、当id为empty时抛出异常
     *
     * @param id
     * @return CreditInfoRecord [返回类型说明]
     * @exception throws
     * @see [类、类#方法、类#成员]
     */
    public CreditInfoRecord findById(String id) {
        AssertUtils.notEmpty(id, "id is empty.");
        
        CreditInfoRecord condition = new CreditInfoRecord();
        condition.setId(id);
        
        CreditInfoRecord res = this.creditInfoRecordDao.find(condition);
        return res;
    }
    
    /**
     * 查询CreditInfoRecord实例列表
     * <功能详细描述>
     * @param params      
     * @return [参数说明]
     * 
     * @return List<CreditInfoRecord> [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    public List<CreditInfoRecord> queryList(Map<String, Object> params) {
        //判断条件合法性
        
        //生成查询条件
        params = params == null ? new HashMap<String, Object>() : params;
        
        //根据实际情况，填入排序字段等条件，根据是否需要排序，选择调用dao内方法
        List<CreditInfoRecord> resList = this.creditInfoRecordDao
                .queryList(params);
        
        return resList;
    }
    
    /**
     * 查询CreditInfoRecord实例列表
     * <功能详细描述>
     * @param querier      
     * @return [参数说明]
     * 
     * @return List<CreditInfoRecord> [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    public List<CreditInfoRecord> queryList(Querier querier) {
        //判断条件合法性
        
        //生成查询条件
        querier = querier == null ? QuerierBuilder.newInstance().querier()
                : querier;
        
        //根据实际情况，填入排序字段等条件，根据是否需要排序，选择调用dao内方法
        List<CreditInfoRecord> resList = this.creditInfoRecordDao
                .queryList(querier);
        
        return resList;
    }
    
    /**
     * 分页查询CreditInfoRecord实例列表
     * <功能详细描述>
     * @param params    
     * @param pageIndex 当前页index从1开始计算
     * @param pageSize 每页显示行数
     * 
     * <功能详细描述>
     * @return [参数说明]
     * 
     * @return List<CreditInfoRecord> [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    public PagedList<CreditInfoRecord> queryPagedList(
            Map<String, Object> params, int pageIndex, int pageSize) {
        //T判断条件合法性
        
        //生成查询条件
        params = params == null ? new HashMap<String, Object>() : params;
        
        //根据实际情况，填入排序字段等条件，根据是否需要排序，选择调用dao内方法
        PagedList<CreditInfoRecord> resPagedList = this.creditInfoRecordDao
                .queryPagedList(params, pageIndex, pageSize);
        
        return resPagedList;
    }
    
    /**
     * 分页查询CreditInfoRecord实例列表
     * <功能详细描述>
     * @param querier    
     * @param pageIndex 当前页index从1开始计算
     * @param pageSize 每页显示行数
     * 
     * <功能详细描述>
     * @return [参数说明]
     * 
     * @return List<CreditInfoRecord> [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    public PagedList<CreditInfoRecord> queryPagedList(Querier querier,
            int pageIndex, int pageSize) {
        //T判断条件合法性
        
        //生成查询条件
        querier = querier == null ? QuerierBuilder.newInstance().querier()
                : querier;
        
        //根据实际情况，填入排序字段等条件，根据是否需要排序，选择调用dao内方法
        PagedList<CreditInfoRecord> resPagedList = this.creditInfoRecordDao
                .queryPagedList(querier, pageIndex, pageSize);
        
        return resPagedList;
    }
    
    /**
     * 查询CreditInfoRecord实例数量<br/>
     * <功能详细描述>
     * @param params      
     * @return [参数说明]
     * 
     * @return List<CreditInfoRecord> [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    public int count(Map<String, Object> params) {
        //判断条件合法性
        
        //生成查询条件
        params = params == null ? new HashMap<String, Object>() : params;
        
        //根据实际情况，填入排序字段等条件，根据是否需要排序，选择调用dao内方法
        int res = this.creditInfoRecordDao.count(params);
        
        return res;
    }
    
    /**
     * 查询CreditInfoRecord实例数量<br/>
     * <功能详细描述>
     * @param querier      
     * @return [参数说明]
     * 
     * @return List<CreditInfoRecord> [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    public int count(Querier querier) {
        //判断条件合法性
        
        //生成查询条件
        querier = querier == null ? QuerierBuilder.newInstance().querier()
                : querier;
        
        //根据实际情况，填入排序字段等条件，根据是否需要排序，选择调用dao内方法
        int res = this.creditInfoRecordDao.count(querier);
        
        return res;
    }
    
    /**
     * 判断CreditInfoRecord实例是否已经存在<br/>
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
        int res = this.creditInfoRecordDao.count(params, excludeId);
        
        return res > 0;
    }
    
    /**
     * 判断CreditInfoRecord实例是否已经存在<br/>
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
        int res = this.creditInfoRecordDao.count(querier, excludeId);
        
        return res > 0;
    }
    
    /**
     * 根据id更新CreditInfoRecord实例<br/>
     * <功能详细描述>
     * @param creditInfoRecord
     * @return [参数说明]
     * 
     * @return boolean [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @Transactional
    public boolean updateById(String id, CreditInfoRecord creditInfoRecord) {
        //验证参数是否合法，必填字段是否填写
        AssertUtils.notNull(creditInfoRecord, "creditInfoRecord is null.");
        AssertUtils.notEmpty(id, "id is empty.");
        AssertUtils.notEmpty(creditInfoRecord.getBaseVersion(),
                "creditInfoRecord.baseVersion is empty.");
        AssertUtils.notEmpty(creditInfoRecord.isLocked(),
                "creditInfoRecord.locked is empty.");
        AssertUtils.notEmpty(creditInfoRecord.getVersion(),
                "creditInfoRecord.version is empty.");
        
        //生成需要更新字段的hashMap
        Map<String, Object> updateRowMap = new HashMap<String, Object>();
        //需要更新的字段
        updateRowMap.put("idCardType", creditInfoRecord.getIdCardType());
        updateRowMap.put("idCardNumber", creditInfoRecord.getIdCardNumber());
        //updateRowMap.put("baseVersion", creditInfoRecord.getBaseVersion());
        
        updateRowMap.put("locked", creditInfoRecord.isLocked());
        updateRowMap.put("version", creditInfoRecord.getVersion());
        
        updateRowMap.put("lastUpdateUserId",
                creditInfoRecord.getLastUpdateUserId());
        updateRowMap.put("lastUpdateDate", new Date());
        
        boolean flag = this.creditInfoRecordDao.update(id, updateRowMap);
        //如果需要大于1时，抛出异常并回滚，需要在这里修改
        return flag;
    }
    
    /**
     * 根据id更新CreditInfoRecord实例<br/>
     * <功能详细描述>
     * @param creditInfoRecord
     * @return [参数说明]
     * 
     * @return boolean [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @Transactional
    public boolean updateById(CreditInfoRecord creditInfoRecord) {
        //验证参数是否合法，必填字段是否填写
        AssertUtils.notNull(creditInfoRecord, "creditInfoRecord is null.");
        AssertUtils.notEmpty(creditInfoRecord.getId(),
                "creditInfoRecord.id is empty.");
        
        boolean flag = updateById(creditInfoRecord.getId(), creditInfoRecord);
        //如果需要大于1时，抛出异常并回滚，需要在这里修改
        return flag;
    }
}
