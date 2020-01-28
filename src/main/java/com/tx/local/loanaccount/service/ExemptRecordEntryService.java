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

import org.apache.commons.collections4.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.tx.core.exceptions.util.AssertUtils;
import com.tx.core.paged.model.PagedList;
import com.tx.local.loanaccount.dao.ExemptRecordEntryDao;
import com.tx.local.loanaccount.model.ExemptRecordEntry;

/**
 * ExemptRecordEntry的业务层
 * <功能详细描述>
 * 
 * @author  
 * @version  [版本号, ]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
@Component("exemptRecordEntryService")
public class ExemptRecordEntryService {
    
    @SuppressWarnings("unused")
    private Logger logger = LoggerFactory.getLogger(ExemptRecordEntryService.class);
    
    @Resource(name = "exemptRecordEntryDao")
    private ExemptRecordEntryDao exemptRecordEntryDao;
    
    /**
      * 批量插入豁免记录分项
      *<功能详细描述>
      * @param exemptRecordEntryList [参数说明]
      * 
      * @return void [返回类型说明]
      * @exception throws [异常类型] [异常说明]
      * @see [类、类#方法、类#成员]
     */
    @Transactional
    public void batchInsert(List<ExemptRecordEntry> exemptRecordEntryList) {
        if (CollectionUtils.isEmpty(exemptRecordEntryList)) {
            return;
        }
        for (ExemptRecordEntry ere : exemptRecordEntryList) {
            validate4insert(ere);
        }
        
        this.exemptRecordEntryDao.batchInsert(exemptRecordEntryList);
    }
    
    /**
     * 将exemptRecordEntry实例插入数据库中保存<br />
     * 1、如果exemptRecordEntry为空时抛出参数为空异常<br />
     * 2、如果exemptRecordEntry中部分必要参数为非法值时抛出参数不合法异常<br />
     *
     * <功能详细描述>
     * 
     * @param district [参数说明]
     * 
     * @return void [返回类型说明]
     * @exception throws
     * @see [类、类#方法、类#成员]
     */
    @Transactional
    public void insert(ExemptRecordEntry exemptRecordEntry) {
        //验证参数是否合法
        validate4insert(exemptRecordEntry);
        
        this.exemptRecordEntryDao.insert(exemptRecordEntry);
    }
    
    /** 
     *<功能简述>
     *<功能详细描述>
     * @param exemptRecordEntry [参数说明]
     * 
     * @return void [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    private void validate4insert(ExemptRecordEntry exemptRecordEntry) {
        //验证参数是否合法
        AssertUtils.notNull(exemptRecordEntry, "exemptRecordEntry is null.");
        AssertUtils.notEmpty(exemptRecordEntry.getLoanAccountId(), "exemptRecordEntry.loanAccountId is empty.");
        AssertUtils.notNull(exemptRecordEntry.getTradingRecord(), "exemptRecordEntry.tradingRecord is null.");
        AssertUtils.notNull(exemptRecordEntry.getExemptRecord(), "exemptRecordEntry.exemptRecord is null.");
        AssertUtils.notNull(exemptRecordEntry.getScheduleType(), "exemptRecordEntry.scheduleType is null.");
        AssertUtils.notEmpty(exemptRecordEntry.getPeriod(), "exemptRecordEntry.period is empty.");
        AssertUtils.notNull(exemptRecordEntry.getSourceAmount(), "exemptRecordEntry.sourceAmount is null.");
        AssertUtils.notNull(exemptRecordEntry.getAmount(), "exemptRecordEntry.amount is null.");
        AssertUtils.notNull(exemptRecordEntry.getTargetAmount(), "exemptRecordEntry.targetAmount is null.");
        AssertUtils.notNull(
                exemptRecordEntry.getSourceAmount()
                        .add(exemptRecordEntry.getAmount())
                        .compareTo(exemptRecordEntry.getTargetAmount()) == 0,
                "exemptRecordEntry.sourceAmount:{} +  amount:{} != targetAmount:{}.");
        
        Date now = new Date();
        exemptRecordEntry.setCreateDate(now);
        exemptRecordEntry.setLastUpdateDate(now);
        exemptRecordEntry.setRevoked(false);
        exemptRecordEntry.setRevokeDate(null);
    }
    
    /**
     * 根据id删除exemptRecordEntry实例<br />
     * 1、如果入参数为空，则抛出异常<br />
     * 2、执行删除后，将返回数据库中被影响的条数<br />
     * 有些业务场景，如果已经被别人删除同样也可以认为是成功的<br />
     * 这里讲通用生成的业务层代码定义为返回影响的条数<br />
     *
     * @param id
     *
     * @return 返回删除的数据条数<br/>
     * @exception throws 
     * @see [类、类#方法、类#成员]
     */
    @Transactional
    public int deleteById(String id) {
        AssertUtils.notEmpty(id, "id is empty.");
        
        ExemptRecordEntry condition = new ExemptRecordEntry();
        condition.setId(id);
        return this.exemptRecordEntryDao.delete(condition);
    }
    
    /**
      * 根据Id查询ExemptRecordEntry实体
      * 1、当id为empty时抛出异常
      * <功能详细描述>
      * @param id
      * @return [参数说明]
      * 
      * @return ExemptRecordEntry [返回类型说明]
      * @exception throws 可能存在数据库访问异常DataAccessException
      * @see [类、类#方法、类#成员]
     */
    public ExemptRecordEntry findById(String id) {
        AssertUtils.notEmpty(id, "id is empty.");
        
        ExemptRecordEntry condition = new ExemptRecordEntry();
        condition.setId(id);
        
        ExemptRecordEntry res = this.exemptRecordEntryDao.find(condition);
        return res;
    }
    
    /**
     * 根据ExemptRecordEntry实体列表
     * 
     * <功能详细描述>
     * @return [参数说明]
     * 
     * @return List<ExemptRecordEntry> [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    public List<ExemptRecordEntry> queryListByLoanAccountId(String loanAccountId) {
        //判断条件合法性
        AssertUtils.notEmpty(loanAccountId, "loanAccountId is empty.");
        
        //生成查询条件
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("loanAccountId", loanAccountId);
        
        //根据实际情况，填入排序字段等条件，根据是否需要排序，选择调用dao内方法
        List<ExemptRecordEntry> resList = this.exemptRecordEntryDao.queryList(params);
        
        return resList;
    }
    
    /**
     * 根据ExemptRecordEntry实体列表
     * <功能详细描述>
     * @return [参数说明]
     * 
     * @return List<ExemptRecordEntry> [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
    */
    public List<ExemptRecordEntry> queryList(Map<String, Object> params) {
        params = params == null ? new HashMap<String, Object>() : params;
        
        //根据实际情况，填入排序字段等条件，根据是否需要排序，选择调用dao内方法
        List<ExemptRecordEntry> resList = this.exemptRecordEntryDao.queryList(params);
        
        return resList;
    }
    
    /**
     * 分页查询ExemptRecordEntry实体列表
     * <功能详细描述>
     * @return [参数说明]
     * 
     * @return List<ExemptRecordEntry> [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
    */
    public PagedList<ExemptRecordEntry> queryPagedList(Map<String, Object> params, int pageIndex, int pageSize) {
        params = params == null ? new HashMap<String, Object>() : params;
        
        //根据实际情况，填入排序字段等条件，根据是否需要排序，选择调用dao内方法
        PagedList<ExemptRecordEntry> resPagedList = this.exemptRecordEntryDao.queryPagedList(params,
                pageIndex,
                pageSize);
        
        return resPagedList;
    }
    
    /**
      * 根据ExemptRecordEntry实体列表
      * TODO:补充说明
      * 
      * <功能详细描述>
      * @return [参数说明]
      * 
      * @return List<ExemptRecordEntry> [返回类型说明]
      * @exception throws [异常类型] [异常说明]
      * @see [类、类#方法、类#成员]
     */
    public List<ExemptRecordEntry> queryListByTradingRecordId(String tradingRecordId) {
        //判断条件合法性
        AssertUtils.notEmpty(tradingRecordId, "tradingRecordId is empty.");
        
        //生成查询条件
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("tradingRecordId", tradingRecordId);
        
        //根据实际情况，填入排序字段等条件，根据是否需要排序，选择调用dao内方法
        List<ExemptRecordEntry> resList = this.exemptRecordEntryDao.queryList(params);
        
        return resList;
    }
    
    /**
     * 查询exemptRecordEntry列表总条数
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
        int res = this.exemptRecordEntryDao.count(params);
        
        return res;
    }
    
    /**
    * 根据id更新对象
    * <功能详细描述>
    * @param exemptRecordEntry
    * @return [参数说明]
    * 
    * @return boolean [返回类型说明]
    * @exception throws [异常类型] [异常说明]
    * @see [类、类#方法、类#成员]
    */
    @Transactional
    public boolean updateById(ExemptRecordEntry exemptRecordEntry) {
        //验证参数是否合法，必填字段是否填写，
        AssertUtils.notNull(exemptRecordEntry, "exemptRecordEntry is null.");
        AssertUtils.notEmpty(exemptRecordEntry.getId(), "exemptRecordEntry.id is empty.");
        
        //生成需要更新字段的hashMap
        Map<String, Object> updateRowMap = new HashMap<String, Object>();
        updateRowMap.put("id", exemptRecordEntry.getId());
        
        //需要更新的字段
        updateRowMap.put("lastUpdateDate", new Date());
        updateRowMap.put("revokeDate", exemptRecordEntry.getRevokeDate());
        updateRowMap.put("revoked", exemptRecordEntry.isRevoked());
        
        int updateRowCount = this.exemptRecordEntryDao.update(updateRowMap);
        
        //如果需要大于1时，抛出异常并回滚，需要在这里修改
        return updateRowCount >= 1;
    }
}
