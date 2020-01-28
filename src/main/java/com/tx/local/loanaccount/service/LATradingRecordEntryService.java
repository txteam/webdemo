/*
 * 描          述:  <描述>
 * 修  改   人:  Administrator
 * 修改时间:  2020年1月29日
 * <修改描述:>
 */
package com.tx.local.loanaccount.service;

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
import com.tx.local.loanaccount.dao.LATradingRecordEntryDao;
import com.tx.local.loanaccount.model.LATradingRecordEntry;

/**
 * 交易记录分项业务层<br/>
 * <功能详细描述>
 * 
 * @author  Administrator
 * @version  [版本号, 2020年1月29日]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
@Component("laTradingRecordEntryService")
public class LATradingRecordEntryService {
    
    @SuppressWarnings("unused")
    private Logger logger = LoggerFactory
            .getLogger(LATradingRecordEntryService.class);
    
    @Resource(name = "laTradingRecordEntryDao")
    private LATradingRecordEntryDao laTradingRecordEntryDao;
    
    /**
      * 批量插入交易记录分项
      *<功能详细描述>
      * @param tradingRecordEntryList [参数说明]
      * 
      * @return void [返回类型说明]
      * @exception throws [异常类型] [异常说明]
      * @see [类、类#方法、类#成员]
     */
    @Transactional
    public void batchInsert(List<LATradingRecordEntry> tradingRecordEntryList) {
        if (CollectionUtils.isEmpty(tradingRecordEntryList)) {
            return;
        }
        for (LATradingRecordEntry trEntry : tradingRecordEntryList) {
            validate4insert(trEntry);
        }
        this.laTradingRecordEntryDao.batchInsert(tradingRecordEntryList);
    }
    
    /**
     * 将lATradingRecordEntry实例插入数据库中保存<br />
     * 1、如果lATradingRecordEntry为空时抛出参数为空异常<br />
     * 2、如果lATradingRecordEntry中部分必要参数为非法值时抛出参数不合法异常<br />
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
    public void insert(LATradingRecordEntry lATradingRecordEntry) {
        //验证参数是否合法
        validate4insert(lATradingRecordEntry);
        
        this.laTradingRecordEntryDao.insert(lATradingRecordEntry);
    }
    
    /** 
     * 验证插入的交易分项数据是否合法<br/>
     * <功能详细描述>
     * @param lATradingRecordEntry [参数说明]
     * 
     * @return void [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    private void validate4insert(LATradingRecordEntry lATradingRecordEntry) {
        AssertUtils.notNull(lATradingRecordEntry,
                "lATradingRecordEntry is null.");
        AssertUtils.notEmpty(lATradingRecordEntry.getLoanAccountId(),
                "lATradingRecordEntry.loanAccountId is empty.");
        AssertUtils.notNull(lATradingRecordEntry.getTradingRecord(),
                "lATradingRecordEntry.tradingRecord is null.");
        AssertUtils.notEmpty(lATradingRecordEntry.getAccountTitleCompanyCode(),
                "lATradingRecordEntry.accountTitleCompanyCode is empty.");
        AssertUtils.notEmpty(lATradingRecordEntry.getAccountTitleCode(),
                "lATradingRecordEntry.accountTitleCode is empty.");
        AssertUtils.notNull(lATradingRecordEntry.getDirection(),
                "lATradingRecordEntry.direction is null.");
        AssertUtils.notNull(lATradingRecordEntry.getAmount(),
                "lATradingRecordEntry.amount is null.");
    }
    
    /**
     * 根据id删除lATradingRecordEntry实例<br />
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
        
        LATradingRecordEntry condition = new LATradingRecordEntry();
        condition.setId(id);
        return this.laTradingRecordEntryDao.delete(condition);
    }
    
    /**
     * 根据Id查询LATradingRecordEntry实体
     * 1、当id为empty时抛出异常
     * <功能详细描述>
     * @param id
     * @return [参数说明]
     * 
     * @return LATradingRecordEntry [返回类型说明]
     * @exception throws 可能存在数据库访问异常DataAccessException
     * @see [类、类#方法、类#成员]
    */
    public LATradingRecordEntry findById(String id) {
        AssertUtils.notEmpty(id, "id is empty.");
        
        LATradingRecordEntry condition = new LATradingRecordEntry();
        condition.setId(id);
        
        LATradingRecordEntry res = this.laTradingRecordEntryDao.find(condition);
        return res;
    }
    
    /**
      * 根据贷款账户ID查询交易记录分项
      * <功能详细描述>
      * @return [参数说明]
      * 
      * @return List<TradingRecordEntry> [返回类型说明]
      * @exception throws [异常类型] [异常说明]
      * @see [类、类#方法、类#成员]
     */
    public List<LATradingRecordEntry> queryListByLoanAccountId(
            String loanAccountId) {
        //生成查询条件
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("loanAccountId", loanAccountId);
        
        //根据实际情况，填入排序字段等条件，根据是否需要排序，选择调用dao内方法
        List<LATradingRecordEntry> resList = this.laTradingRecordEntryDao
                .queryList(params);
        return resList;
    }
    
    /**
      * 根据交易记录ID查询交易记录分项
      *<功能详细描述>
      * @param tradingRecordId
      * @return [参数说明]
      * 
      * @return List<TradingRecordEntry> [返回类型说明]
      * @exception throws [异常类型] [异常说明]
      * @see [类、类#方法、类#成员]
     */
    public List<LATradingRecordEntry> queryListByTradingRecordId(
            String tradingRecordId) {
        //生成查询条件
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("tradingRecordId", tradingRecordId);
        
        //根据实际情况，填入排序字段等条件，根据是否需要排序，选择调用dao内方法
        List<LATradingRecordEntry> resList = this.laTradingRecordEntryDao
                .queryList(params);
        return resList;
    }
    
    /**
     * 根据LATradingRecordEntry实体列表
     * <功能详细描述>
     * @return [参数说明]
     * 
     * @return List<LATradingRecordEntry> [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
    */
    public List<LATradingRecordEntry> queryList(Map<String, Object> params) {
        params = params == null ? new HashMap<String, Object>() : params;
        
        //根据实际情况，填入排序字段等条件，根据是否需要排序，选择调用dao内方法
        List<LATradingRecordEntry> resList = this.laTradingRecordEntryDao
                .queryList(params);
        
        return resList;
    }
    
    /**
    * 分页查询LATradingRecordEntry实体列表
    * <功能详细描述>
    * @return [参数说明]
    * 
    * @return List<LATradingRecordEntry> [返回类型说明]
    * @exception throws [异常类型] [异常说明]
    * @see [类、类#方法、类#成员]
    */
    public PagedList<LATradingRecordEntry> queryPagedList(
            Map<String, Object> params, int pageIndex, int pageSize) {
        params = params == null ? new HashMap<String, Object>() : params;
        
        //根据实际情况，填入排序字段等条件，根据是否需要排序，选择调用dao内方法
        PagedList<LATradingRecordEntry> resPagedList = this.laTradingRecordEntryDao
                .queryPagedList(params, pageIndex, pageSize);
        
        return resPagedList;
    }
    
    /**
     * 查询lATradingRecordEntry列表总条数
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
        int res = this.laTradingRecordEntryDao.count(params);
        
        return res;
    }
    
    /**
     * 根据id更新对象
     * <功能详细描述>
     * @param lATradingRecordEntry
     * @return [参数说明]
     * 
     * @return boolean [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
    */
    @Transactional
    public boolean updateById(LATradingRecordEntry lATradingRecordEntry) {
        //验证参数是否合法，必填字段是否填写，
        AssertUtils.notNull(lATradingRecordEntry,
                "lATradingRecordEntry is null.");
        AssertUtils.notEmpty(lATradingRecordEntry.getId(),
                "lATradingRecordEntry.id is empty.");
        
        //生成需要更新字段的hashMap
        Map<String, Object> updateRowMap = new HashMap<String, Object>();
        updateRowMap.put("id", lATradingRecordEntry.getId());
        
        //需要更新的字段
        //updateRowMap.put("loanAccountId", lATradingRecordEntry.getLoanAccountId()); 
        updateRowMap.put("accountTitleCode",
                lATradingRecordEntry.getAccountTitleCode());
        updateRowMap.put("accountTitleCompanyCode",
                lATradingRecordEntry.getAccountTitleCompanyCode());
        //updateRowMap.put("amount", lATradingRecordEntry.getAmount());   
        //updateRowMap.put("debitCreditType", lATradingRecordEntry.getDebitCreditType()); 
        //type:java.lang.String
        //updateRowMap.put("tradingRecord", lATradingRecordEntry.getTradingRecord());
        
        int updateRowCount = this.laTradingRecordEntryDao.update(updateRowMap);
        
        //如果需要大于1时，抛出异常并回滚，需要在这里修改
        return updateRowCount >= 1;
    }
}
