/*
 * 描          述:  <描述>
 * 修  改   人:  
 * 修改时间:  
 * <修改描述:>
 */
package com.tx.local.loanaccount.service;

import java.math.BigDecimal;
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
import com.tx.local.loanaccount.dao.OverRepayRecordDao;
import com.tx.local.loanaccount.model.OverRepayRecord;

/**
 * OverRepayRecord的业务层
 * <功能详细描述>
 * 
 * @author  
 * @version  [版本号, ]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
@Component("overRepayRecordService")
public class OverRepayRecordService {
    
    @SuppressWarnings("unused")
    private Logger logger = LoggerFactory
            .getLogger(OverRepayRecordService.class);
    
    @Resource(name = "overRepayRecordDao")
    private OverRepayRecordDao overRepayRecordDao;
    
    /**
     * 批量插入计费记录
     * <功能详细描述>
     * @param chargeRecordList [参数说明]
     * 
     * @return void [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @Transactional
    public void batchInsert(List<OverRepayRecord> overRepayRecordList) {
        if (CollectionUtils.isEmpty(overRepayRecordList)) {
            return;
        }
        for (OverRepayRecord overRepayRecord : overRepayRecordList) {
            validate4insert(overRepayRecord);
        }
        this.overRepayRecordDao.batchInsert(overRepayRecordList);
    }
    
    /**
     * 将overRepayRecord实例插入数据库中保存
     * 1、如果overRepayRecord为空时抛出参数为空异常
     * 2、如果overRepayRecord中部分必要参数为非法值时抛出参数不合法异常
     * <功能详细描述>
     * @param district [参数说明]
     * 
     * @return void [返回类型说明]
     * @exception throws
     * @see [类、类#方法、类#成员]
     */
    @Transactional
    public void insert(OverRepayRecord overRepayRecord) {
        validate4insert(overRepayRecord);
        
        //设置默认数据
        this.overRepayRecordDao.insert(overRepayRecord);
    }
    
    /** 
     * 验证插入的数据是否合法<br/>
     * <功能详细描述>
     * @param overRepayRecord [参数说明]
     * 
     * @return void [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    private void validate4insert(OverRepayRecord overRepayRecord) {
        //验证参数是否合法
        AssertUtils.notNull(overRepayRecord, "overRepayRecord is null.");
        AssertUtils.notEmpty(overRepayRecord.getLoanAccountId(),
                "overRepayRecord.loanAccountId is empty.");
        AssertUtils.notEmpty(overRepayRecord.getPeriod(),
                "overRepayRecord.period is empty.");
        AssertUtils.notNull(overRepayRecord.getFeeItem(),
                "overRepayRecord.feeItem is null.");
        AssertUtils.notEmpty(overRepayRecord.getTradingRecordId(),
                "overRepayRecord.tradingRecordId is empty.");
        AssertUtils.notNull(overRepayRecord.getAmount(),
                "overRepayRecord.amount is null.");
        AssertUtils.isTrue(
                overRepayRecord.getAmount().compareTo(BigDecimal.ZERO) > 0,
                "overRepayRecord.amount should > 0.");
    }
    
    /**
     * 批量插入计费记录
     * <功能详细描述>
     * @param chargeRecordList [参数说明]
     * 
     * @return void [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @Transactional
    public void batchInsertToHis(List<OverRepayRecord> overRepayRecordList) {
        if (CollectionUtils.isEmpty(overRepayRecordList)) {
            return;
        }
        for (OverRepayRecord overRepayRecord : overRepayRecordList) {
            validate4insert(overRepayRecord);
        }
        this.overRepayRecordDao.batchInsertToHis(overRepayRecordList);
    }
    
    /**
     * 将overRepayRecord实例插入数据库中保存
     * 1、如果overRepayRecord为空时抛出参数为空异常
     * 2、如果overRepayRecord中部分必要参数为非法值时抛出参数不合法异常
     * <功能详细描述>
     * @param district [参数说明]
     * 
     * @return void [返回类型说明]
     * @exception throws
     * @see [类、类#方法、类#成员]
     */
    @Transactional
    public void insertToHis(OverRepayRecord overRepayRecord) {
        validate4insert(overRepayRecord);
        
        //设置默认数据
        this.overRepayRecordDao.insertToHis(overRepayRecord);
    }
    
    /**
     * 根据超额还款记录id删除超额还款数据<br/>
     * <功能详细描述>
     * @param overRepayRecordId
     * @return [参数说明]
     * 
     * @return boolean [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @Transactional
    public boolean deleteById(String overRepayRecordId) {
        AssertUtils.notEmpty(overRepayRecordId, "overRepayRecordId is empty.");
        
        OverRepayRecord condition = new OverRepayRecord();
        condition.setId(overRepayRecordId);
        int resInt = this.overRepayRecordDao.delete(condition);
        return resInt > 0;
    }
    
    /**
     * 根据超额还款记录id删除超额还款数据<br/>
     * <功能详细描述>
     * @param overRepayRecordId
     * @return [参数说明]
     * 
     * @return boolean [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @Transactional
    public boolean deleteByTradingRecordId(String tradingRecordId) {
        AssertUtils.notEmpty(tradingRecordId, "tradingRecordId is empty.");
        
        OverRepayRecord condition = new OverRepayRecord();
        condition.setTradingRecordId(tradingRecordId);
        int resInt = this.overRepayRecordDao.delete(condition);
        return resInt > 0;
    }
    
    /**
     * 根据Id查询OverRepayRecord实体
     * 1、当id为empty时抛出异常
     * <功能详细描述>
     * @param id
     * @return [参数说明]
     * 
     * @return OverRepayRecord [返回类型说明]
     * @exception throws 可能存在数据库访问异常DataAccessException
     * @see [类、类#方法、类#成员]
     */
    public OverRepayRecord findById(String id) {
        AssertUtils.notEmpty(id, "id is empty.");
        
        OverRepayRecord condition = new OverRepayRecord();
        condition.setId(id);
        
        OverRepayRecord res = this.overRepayRecordDao.find(condition);
        return res;
    }
    
    /**
     * 根据OverRepayRecord实体列表
     * 
     * <功能详细描述>
     * @return [参数说明]
     * 
     * @return List<OverRepayRecord> [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    public List<OverRepayRecord> queryListByTradingRecordId(
            String tradingRecordId, Map<String, Object> params) {
        AssertUtils.notEmpty(tradingRecordId, "tradingRecordId");
        
        params = params == null ? new HashMap<String, Object>() : params;
        params.put("tradingRecordId", tradingRecordId);
        
        //根据实际情况，填入排序字段等条件，根据是否需要排序，选择调用dao内方法
        List<OverRepayRecord> resList = this.overRepayRecordDao
                .queryList(params);
        
        return resList;
    }
    
    /**
     * 根据OverRepayRecord实体列表
     * 
     * <功能详细描述>
     * @return [参数说明]
     * 
     * @return List<OverRepayRecord> [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    public List<OverRepayRecord> queryList(Map<String, Object> params) {
        params = params == null ? new HashMap<String, Object>() : params;
        
        //根据实际情况，填入排序字段等条件，根据是否需要排序，选择调用dao内方法
        List<OverRepayRecord> resList = this.overRepayRecordDao
                .queryList(params);
        
        return resList;
    }
    
    /**
     * 分页查询OverRepayRecord实体列表
     * 
     * <功能详细描述>
     * @return [参数说明]
     * 
     * @return List<OverRepayRecord> [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    public PagedList<OverRepayRecord> queryPagedList(Map<String, Object> params,
            int pageIndex, int pageSize) {
        params = params == null ? new HashMap<String, Object>() : params;
        
        //根据实际情况，填入排序字段等条件，根据是否需要排序，选择调用dao内方法
        PagedList<OverRepayRecord> resPagedList = this.overRepayRecordDao
                .queryPagedList(params, pageIndex, pageSize);
        
        return resPagedList;
    }
    
    /**
     * 查询overRepayRecord列表总条数
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
        int res = this.overRepayRecordDao.count(params);
        
        return res;
    }
    
    /**
     * 根据id更新对象
     * <功能详细描述>
     * @param overRepayRecord
     * @return [参数说明]
     * 
     * @return boolean [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @Transactional
    public boolean updateById(OverRepayRecord overRepayRecord) {
        //验证参数是否合法，必填字段是否填写，
        AssertUtils.notNull(overRepayRecord, "overRepayRecord is null.");
        AssertUtils.notEmpty(overRepayRecord.getId(),
                "overRepayRecord.id is empty.");
        
        //生成需要更新字段的hashMap
        Map<String, Object> updateRowMap = new HashMap<String, Object>();
        updateRowMap.put("id", overRepayRecord.getId());
        
        //需要更新的字段
        updateRowMap.put("receiveDate", overRepayRecord.getReceiveDate());
        updateRowMap.put("received", overRepayRecord.isReceived());
        updateRowMap.put("revokeDate", overRepayRecord.getRevokeDate());
        updateRowMap.put("revoked", overRepayRecord.isRevoked());
        updateRowMap.put("revokeTradingRecordId",
                overRepayRecord.getRevokeTradingRecordId());
        
        int updateRowCount = this.overRepayRecordDao.update(updateRowMap);
        
        //如果需要大于1时，抛出异常并回滚，需要在这里修改
        return updateRowCount >= 1;
    }
}
