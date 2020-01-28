/*
 * 描          述:  <描述>
 * 修  改   人:  
 * 修改时间:  
 * <修改描述:>
 */
package com.tx.local.loanaccount.service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.tx.core.exceptions.util.AssertUtils;
import com.tx.core.paged.model.PagedList;
import com.tx.local.loanaccount.dao.ExemptRecordDao;
import com.tx.local.loanaccount.model.ExemptRecord;

/**
 * ExemptRecord的业务层
 * <功能详细描述>
 * 
 * @author  
 * @version  [版本号, ]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
@Component("exemptRecordService")
public class ExemptRecordService {
    
    @SuppressWarnings("unused")
    private Logger logger = LoggerFactory.getLogger(ExemptRecordService.class);
    
    @Resource(name = "exemptRecordDao")
    private ExemptRecordDao exemptRecordDao;
    
    /**
      * 批量插入豁免记录
      *<功能详细描述>
      * @param exemptRecordList [参数说明]
      * 
      * @return void [返回类型说明]
      * @exception throws [异常类型] [异常说明]
      * @see [类、类#方法、类#成员]
     */
    @Transactional
    public void batchInsert(List<ExemptRecord> exemptRecordList) {
        if (CollectionUtils.isEmpty(exemptRecordList)) {
            return;
        }
        for (ExemptRecord cxemptRecord : exemptRecordList) {
            validate4Insert(cxemptRecord);
        }
        this.exemptRecordDao.batchInsert(exemptRecordList);
    }
    
    /**
      * 将exemptRecord实例插入数据库中保存
      * 1、如果exemptRecord为空时抛出参数为空异常
      * 2、如果exemptRecord中部分必要参数为非法值时抛出参数不合法异常
     * <功能详细描述>
     * @param district [参数说明]
     * 
     * @return void [返回类型说明]
     * @exception throws
     * @see [类、类#方法、类#成员]
    */
    @Transactional
    public void insert(ExemptRecord exemptRecord) {
        //验证参数是否合法
        validate4Insert(exemptRecord);
        
        this.exemptRecordDao.insert(exemptRecord);
    }
    
    /** 
     * 校验插入的豁免记录
     * <功能详细描述>
     * @param exemptRecord [参数说明]
     * 
     * @return void [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    private void validate4Insert(ExemptRecord exemptRecord) {
        AssertUtils.notNull(exemptRecord, "exemptRecord is null.");
        AssertUtils.notEmpty(exemptRecord.getLoanAccountId(), "exemptRecord.loanAccountId is empty.");
        AssertUtils.notNull(exemptRecord.getTradingRecord(), "exemptRecord.tradingRecord is null.");
        AssertUtils.notNull(exemptRecord.getScheduleType(), "exemptRecord.scheduleType is null.");
        AssertUtils.notEmpty(exemptRecord.getPeriod(), "exemptRecord.period is empty.");
        AssertUtils.notNull(exemptRecord.getSourceSum(), "exemptRecord.sourceSum is null.");
        AssertUtils.notNull(exemptRecord.getSum(), "exemptRecord.sum is null.");
        AssertUtils.notNull(exemptRecord.getTargetSum(), "exemptRecord.targetSum is null.");
        AssertUtils.notNull(
                exemptRecord.getSourceSum().add(exemptRecord.getSum()).compareTo(exemptRecord.getTargetSum()) == 0,
                "exemptRecord.sourceSum:{} +  sum:{} != targetSum:{}.");
        
        Date now = new Date();
        exemptRecord.setCreateDate(now);
        exemptRecord.setLastUpdateDate(now);
        exemptRecord.setRevoked(false);
        exemptRecord.setRevokeDate(null);
    }
    
    /**
     * 根据id删除exemptRecord实例
     * 1、如果入参数为空，则抛出异常
     * 2、执行删除后，将返回数据库中被影响的条数
     * @param id
     * @return 返回删除的数据条数，<br/>
     * 有些业务场景，如果已经被别人删除同样也可以认为是成功的
     * 这里讲通用生成的业务层代码定义为返回影响的条数
     * @return int [返回类型说明]
     * @exception throws 
     * @see [类、类#方法、类#成员]
    */
    @Transactional
    public int deleteById(String id) {
        AssertUtils.notEmpty(id, "id is empty.");
        
        ExemptRecord condition = new ExemptRecord();
        condition.setId(id);
        return this.exemptRecordDao.delete(condition);
    }
    
    /**
      * 根据Id查询ExemptRecord实体
      * 1、当id为empty时抛出异常
      * <功能详细描述>
      * @param id
      * @return [参数说明]
      * 
      * @return ExemptRecord [返回类型说明]
      * @exception throws 可能存在数据库访问异常DataAccessException
      * @see [类、类#方法、类#成员]
     */
    public ExemptRecord findById(String id) {
        AssertUtils.notEmpty(id, "id is empty.");
        
        ExemptRecord condition = new ExemptRecord();
        condition.setId(id);
        
        ExemptRecord res = this.exemptRecordDao.find(condition);
        return res;
    }
    
    /**
     * 根据ExemptRecord实体列表
     * <功能详细描述>
     * @return [参数说明]
     * 
     * @return List<ExemptRecord> [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    public List<ExemptRecord> queryListByLoanAccountId(String loanAccountId) {
        AssertUtils.notEmpty(loanAccountId, "loanAccountId is empty.");
        
        //生成查询条件
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("loanAccountId", loanAccountId);
        
        //根据实际情况，填入排序字段等条件，根据是否需要排序，选择调用dao内方法
        List<ExemptRecord> resList = this.exemptRecordDao.queryList(params);
        
        return resList;
    }
    
    /**
     * 根据ExemptRecord实体列表
     * <功能详细描述>
     * @return [参数说明]
     * 
     * @return List<ExemptRecord> [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    public List<ExemptRecord> queryList(Map<String, Object> params) {
        params = params == null ? new HashMap<String, Object>() : params;
        
        //根据实际情况，填入排序字段等条件，根据是否需要排序，选择调用dao内方法
        List<ExemptRecord> resList = this.exemptRecordDao.queryList(params);
        
        return resList;
    }
    
    /**
    * 分页查询ExemptRecord实体列表
    * <功能详细描述>
    * @return [参数说明]
    * 
    * @return List<ExemptRecord> [返回类型说明]
    * @exception throws [异常类型] [异常说明]
    * @see [类、类#方法、类#成员]
    */
    public PagedList<ExemptRecord> queryPagedList(Map<String, Object> params, int pageIndex, int pageSize) {
        params = params == null ? new HashMap<String, Object>() : params;
        
        //根据实际情况，填入排序字段等条件，根据是否需要排序，选择调用dao内方法
        PagedList<ExemptRecord> resPagedList = this.exemptRecordDao.queryPagedList(params, pageIndex, pageSize);
        
        return resPagedList;
    }
    
    /**
    * 查询exemptRecord列表总条数
    * <功能详细描述>
    * @return [参数说明]
    * 
    * @return int [返回类型说明]
    * @exception throws [异常类型] [异常说明]
    * @see [类、类#方法、类#成员]
    */
    public int count(Map<String, Object> params) {
        params = params == null ? new HashMap<String, Object>() : params;
        
        //根据实际情况，填入排序字段等条件，根据是否需要排序，选择调用dao内方法
        int res = this.exemptRecordDao.count(params);
        
        return res;
    }
    
    /**
     * 根据id更新对象
     * <功能详细描述>
     * @param exemptRecord
     * @return [参数说明]
     * 
     * @return boolean [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
    */
    @Transactional
    public boolean updateById(ExemptRecord exemptRecord) {
        //验证参数是否合法，必填字段是否填写，
        AssertUtils.notNull(exemptRecord, "exemptRecord is null.");
        AssertUtils.notEmpty(exemptRecord.getId(), "exemptRecord.id is empty.");
        
        //生成需要更新字段的hashMap
        Map<String, Object> updateRowMap = new HashMap<String, Object>();
        updateRowMap.put("id", exemptRecord.getId());
        
        //需要更新的字段
        updateRowMap.put("lastUpdateDate", exemptRecord.getLastUpdateDate());
        updateRowMap.put("revokeDate", exemptRecord.getRevokeDate());
        updateRowMap.put("revoked", exemptRecord.isRevoked());
        
        int updateRowCount = this.exemptRecordDao.update(updateRowMap);
        
        //如果需要大于1时，抛出异常并回滚，需要在这里修改
        return updateRowCount >= 1;
    }
}
