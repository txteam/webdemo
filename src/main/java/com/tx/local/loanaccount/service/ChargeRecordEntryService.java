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
import com.tx.local.loanaccount.dao.ChargeRecordEntryDao;
import com.tx.local.loanaccount.model.ChargeRecordEntry;

/**
 * ChargeRecordEntry的业务层
 * <功能详细描述>
 * 
 * @author  
 * @version  [版本号, ]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
@Component("chargeRecordEntryService")
public class ChargeRecordEntryService {
    
    @SuppressWarnings("unused")
    private Logger logger = LoggerFactory.getLogger(ChargeRecordEntryService.class);
    
    @Resource(name = "chargeRecordEntryDao")
    private ChargeRecordEntryDao chargeRecordEntryDao;
    
    /**
      * 批量插入计费记录分项<br/>
      *<功能详细描述>
      * @param chargeRecordEntryList [参数说明]
      * 
      * @return void [返回类型说明]
      * @exception throws [异常类型] [异常说明]
      * @see [类、类#方法、类#成员]
     */
    @Transactional
    public void batchInsert(List<ChargeRecordEntry> chargeRecordEntryList) {
        if (CollectionUtils.isEmpty(chargeRecordEntryList)) {
            return;
        }
        for (ChargeRecordEntry cre : chargeRecordEntryList) {
            validate4insert(cre);
        }
        this.chargeRecordEntryDao.batchInsert(chargeRecordEntryList);
    }
    
    /**
     * 将chargeRecordEntry实例插入数据库中保存<br />
     * 1、如果chargeRecordEntry为空时抛出参数为空异常<br />
     * 2、如果chargeRecordEntry中部分必要参数为非法值时抛出参数不合法异常<br />
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
    public void insert(ChargeRecordEntry chargeRecordEntry) {
        validate4insert(chargeRecordEntry);
        
        this.chargeRecordEntryDao.insert(chargeRecordEntry);
    }
    
    /** 
     *<功能简述>
     *<功能详细描述>
     * @param chargeRecordEntry [参数说明]
     * 
     * @return void [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    private void validate4insert(ChargeRecordEntry chargeRecordEntry) {
        //验证参数是否合法
        AssertUtils.notNull(chargeRecordEntry, "chargeRecordEntry is null.");
        AssertUtils.notEmpty(chargeRecordEntry.getLoanAccountId(), "chargeRecordEntry.loanAccountId is empty.");
        AssertUtils.notNull(chargeRecordEntry.getTradingRecord(), "chargeRecordEntry.tradingRecord is null.");
        AssertUtils.notNull(chargeRecordEntry.getChargeRecord(), "chargeRecordEntry.chargeRecord is null.");
        AssertUtils.notNull(chargeRecordEntry.getScheduleType(), "chargeRecordEntry.scheduleType is null.");
        AssertUtils.notEmpty(chargeRecordEntry.getPeriod(), "chargeRecordEntry.period is empty.");
        AssertUtils.notNull(chargeRecordEntry.getSourceAmount(), "chargeRecordEntry.sourceAmount is null.");
        AssertUtils.notNull(chargeRecordEntry.getAmount(), "chargeRecordEntry.amount is null.");
        AssertUtils.notNull(chargeRecordEntry.getTargetAmount(), "chargeRecordEntry.targetAmount is null.");
        AssertUtils.notNull(
                chargeRecordEntry.getSourceAmount()
                        .add(chargeRecordEntry.getAmount())
                        .compareTo(chargeRecordEntry.getTargetAmount()) == 0,
                "chargeRecordEntry.sourceAmount:{} +  amount:{} != targetAmount:{}.");
        
        Date now = new Date();
        chargeRecordEntry.setCreateDate(now);
        chargeRecordEntry.setLastUpdateDate(now);
        chargeRecordEntry.setRevoked(false);
        chargeRecordEntry.setRevokeDate(null);
    }
    
    /**
     * 根据ChargeRecordId查询ChargeRecordEntry实体列表
     * <功能详细描述>
     * @return [参数说明]
     * 
     * @return List<ChargeRecordEntry> [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    public List<ChargeRecordEntry> queryListByLoanAccountId(String loanAccountId) {
        AssertUtils.notEmpty(loanAccountId, "loanAccountId is empty.");
        
        //生成查询条件
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("loanAccountId", loanAccountId);
        
        //根据实际情况，填入排序字段等条件，根据是否需要排序，选择调用dao内方法
        List<ChargeRecordEntry> resList = this.chargeRecordEntryDao.queryList(params);
        
        return resList;
    }
    
    /**
     * 根据ChargeRecordId查询ChargeRecordEntry实体列表
     * <功能详细描述>
     * @return [参数说明]
     * 
     * @return List<ChargeRecordEntry> [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    public List<ChargeRecordEntry> queryListByChargeRecordId(String chargeRecordId) {
        AssertUtils.notEmpty(chargeRecordId, "chargeRecordId is empty.");
        
        //生成查询条件
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("chargeRecordId", chargeRecordId);
        
        //根据实际情况，填入排序字段等条件，根据是否需要排序，选择调用dao内方法
        List<ChargeRecordEntry> resList = this.chargeRecordEntryDao.queryList(params);
        
        return resList;
    }
    
    /**
     * 根据ChargeRecordId查询ChargeRecordEntry实体列表
     * <功能详细描述>
     * @return [参数说明]
     * 
     * @return List<ChargeRecordEntry> [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
    */
    public List<ChargeRecordEntry> queryListByTradingRecordId(String tradingRecordId) {
        AssertUtils.notEmpty(tradingRecordId, "tradingRecordId is empty.");
        
        //生成查询条件
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("tradingRecordId", tradingRecordId);
        
        //根据实际情况，填入排序字段等条件，根据是否需要排序，选择调用dao内方法
        List<ChargeRecordEntry> resList = this.chargeRecordEntryDao.queryList(params);
        
        return resList;
    }
    
    /**
     * 根据ChargeRecordEntry实体列表
     * <功能详细描述>
     * @return [参数说明]
     * 
     * @return List<ChargeRecordEntry> [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
    */
    public List<ChargeRecordEntry> queryList(Map<String, Object> params) {
        params = params == null ? new HashMap<String, Object>() : params;
        
        //根据实际情况，填入排序字段等条件，根据是否需要排序，选择调用dao内方法
        List<ChargeRecordEntry> resList = this.chargeRecordEntryDao.queryList(params);
        
        return resList;
    }
    
    /**
    * 分页查询ChargeRecordEntry实体列表
    * <功能详细描述>
    * @return [参数说明]
    * 
    * @return List<ChargeRecordEntry> [返回类型说明]
    * @exception throws [异常类型] [异常说明]
    * @see [类、类#方法、类#成员]
    */
    public PagedList<ChargeRecordEntry> queryPagedList(Map<String, Object> params, int pageIndex, int pageSize) {
        params = params == null ? new HashMap<String, Object>() : params;
        
        //根据实际情况，填入排序字段等条件，根据是否需要排序，选择调用dao内方法
        PagedList<ChargeRecordEntry> resPagedList = this.chargeRecordEntryDao.queryPagedList(params,
                pageIndex,
                pageSize);
        
        return resPagedList;
    }
    
    /**
     * 查询chargeRecordEntry列表总条数
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
        int res = this.chargeRecordEntryDao.count(params);
        
        return res;
    }
    
    /**
     * 根据id更新对象
     * <功能详细描述>
     * @param chargeRecordEntry
     * @return [参数说明]
     * 
     * @return boolean [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
    */
    @Transactional
    public boolean updateById(ChargeRecordEntry chargeRecordEntry) {
        //验证参数是否合法，必填字段是否填写，
        AssertUtils.notNull(chargeRecordEntry, "chargeRecordEntry is null.");
        AssertUtils.notEmpty(chargeRecordEntry.getId(), "chargeRecordEntry.id is empty.");
        
        //生成需要更新字段的hashMap
        Map<String, Object> updateRowMap = new HashMap<String, Object>();
        updateRowMap.put("id", chargeRecordEntry.getId());
        
        //需要更新的字段   
        updateRowMap.put("lastUpdateDate", new Date());
        updateRowMap.put("revokeDate", chargeRecordEntry.getRevokeDate());
        updateRowMap.put("revoked", chargeRecordEntry.isRevoked());
        
        int updateRowCount = this.chargeRecordEntryDao.update(updateRowMap);
        
        //如果需要大于1时，抛出异常并回滚，需要在这里修改
        return updateRowCount >= 1;
    }
}
