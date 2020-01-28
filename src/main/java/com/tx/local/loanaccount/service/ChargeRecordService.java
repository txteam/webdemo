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
import com.tx.local.loanaccount.dao.ChargeRecordDao;
import com.tx.local.loanaccount.model.ChargeRecord;

/**
 * ChargeRecord的业务层
 * <功能详细描述>
 * 
 * @author  
 * @version  [版本号, ]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
@Component("chargeRecordService")
public class ChargeRecordService {
    
    @SuppressWarnings("unused")
    private Logger logger = LoggerFactory.getLogger(ChargeRecordService.class);
    
    @Resource(name = "chargeRecordDao")
    private ChargeRecordDao chargeRecordDao;
    
    /**
      * 批量插入计费记录
      *<功能详细描述>
      * @param chargeRecordList [参数说明]
      * 
      * @return void [返回类型说明]
      * @exception throws [异常类型] [异常说明]
      * @see [类、类#方法、类#成员]
     */
    @Transactional
    public void batchInsert(List<ChargeRecord> chargeRecordList) {
        if (CollectionUtils.isEmpty(chargeRecordList)) {
            return;
        }
        for (ChargeRecord chargeRecord : chargeRecordList) {
            validate4Insert(chargeRecord);
        }
        this.chargeRecordDao.batchInsert(chargeRecordList);
    }
    
    /**
      * 将chargeRecord实例插入数据库中保存
      * 1、如果chargeRecord为空时抛出参数为空异常
      * 2、如果chargeRecord中部分必要参数为非法值时抛出参数不合法异常
     * <功能详细描述>
     * @param district [参数说明]
     * 
     * @return void [返回类型说明]
     * @exception throws
     * @see [类、类#方法、类#成员]
    */
    @Transactional
    public void insert(ChargeRecord chargeRecord) {
        //验证参数是否合法
        validate4Insert(chargeRecord);
        
        this.chargeRecordDao.insert(chargeRecord);
    }
    
    /** 
     * 校验插入的计费记录
     * <功能详细描述>
     * @param chargeRecord [参数说明]
     * 
     * @return void [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    private void validate4Insert(ChargeRecord chargeRecord) {
        AssertUtils.notNull(chargeRecord, "chargeRecord is null.");
        AssertUtils.notEmpty(chargeRecord.getLoanAccountId(), "chargeRecord.loanAccountId is empty.");
        AssertUtils.notNull(chargeRecord.getTradingRecord(), "chargeRecord.tradingRecord is null.");
        AssertUtils.notNull(chargeRecord.getScheduleType(), "chargeRecord.scheduleType is null.");
        AssertUtils.notEmpty(chargeRecord.getPeriod(), "chargeRecord.period is empty.");
        AssertUtils.notNull(chargeRecord.getSourceSum(), "chargeRecord.sourceSum is null.");
        AssertUtils.notNull(chargeRecord.getSum(), "chargeRecord.sum is null.");
        AssertUtils.notNull(chargeRecord.getTargetSum(), "chargeRecord.targetSum is null.");
        AssertUtils.notNull(
                chargeRecord.getSourceSum().add(chargeRecord.getSum()).compareTo(chargeRecord.getTargetSum()) == 0,
                "chargeRecord.sourceSum:{} +  sum:{} != targetSum:{}.");
        
        Date now = new Date();
        chargeRecord.setCreateDate(now);
        chargeRecord.setLastUpdateDate(now);
        chargeRecord.setRevoked(false);
        chargeRecord.setRevokeDate(null);
    }
    
    /**
     * 根据id删除chargeRecord实例
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
        
        ChargeRecord condition = new ChargeRecord();
        condition.setId(id);
        return this.chargeRecordDao.delete(condition);
    }
    
    /**
      * 根据Id查询ChargeRecord实体
      * 1、当id为empty时抛出异常
      * <功能详细描述>
      * @param id
      * @return [参数说明]
      * 
      * @return ChargeRecord [返回类型说明]
      * @exception throws 可能存在数据库访问异常DataAccessException
      * @see [类、类#方法、类#成员]
     */
    public ChargeRecord findById(String id) {
        AssertUtils.notEmpty(id, "id is empty.");
        
        ChargeRecord condition = new ChargeRecord();
        condition.setId(id);
        
        ChargeRecord res = this.chargeRecordDao.find(condition);
        return res;
    }
    
    /**
     * 根据ChargeRecord实体列表
     * 
     * <功能详细描述>
     * @return [参数说明]
     * 
     * @return List<ChargeRecord> [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    public List<ChargeRecord> queryListByLoanAccountId(String loanAccountId) {
        AssertUtils.notEmpty(loanAccountId, "loanAccountId is empty");
        
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("loanAccountId", loanAccountId);
        
        //根据实际情况，填入排序字段等条件，根据是否需要排序，选择调用dao内方法
        List<ChargeRecord> resList = this.chargeRecordDao.queryList(params);
        
        return resList;
    }
    
    /**
     * 根据ChargeRecord实体列表
     * 
     * <功能详细描述>
     * @return [参数说明]
     * 
     * @return List<ChargeRecord> [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    public List<ChargeRecord> queryList(Map<String, Object> params) {
        params = params == null ? new HashMap<String, Object>() : params;
        
        //根据实际情况，填入排序字段等条件，根据是否需要排序，选择调用dao内方法
        List<ChargeRecord> resList = this.chargeRecordDao.queryList(params);
        
        return resList;
    }
    
    /**
     * 分页查询ChargeRecord实体列表
     * 
     * <功能详细描述>
     * @return [参数说明]
     * 
     * @return List<ChargeRecord> [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
    */
    public PagedList<ChargeRecord> queryPagedList(Map<String, Object> params, int pageIndex, int pageSize) {
        params = params == null ? new HashMap<String, Object>() : params;
        
        //根据实际情况，填入排序字段等条件，根据是否需要排序，选择调用dao内方法
        PagedList<ChargeRecord> resPagedList = this.chargeRecordDao.queryPagedList(params, pageIndex, pageSize);
        
        return resPagedList;
    }
    
    /**
      * 查询chargeRecord列表总条数
      * <功能详细描述>
      * @return [参数说明]
      * 
      * @return int [返回类型说明]
      * @exception throws [异常类型] [异常说明]
      * @see [类、类#方法、类#成员]
     */
    public int count(Map<String, Object> params) {
        params = params == null ? new HashMap<String, Object>() : params;
        
        int res = this.chargeRecordDao.count(params);
        
        return res;
    }
    
    /**
      * 根据id更新对象
      * <功能详细描述>
      * @param chargeRecord
      * @return [参数说明]
      * 
      * @return boolean [返回类型说明]
      * @exception throws [异常类型] [异常说明]
      * @see [类、类#方法、类#成员]
     */
    @Transactional
    public boolean updateById(ChargeRecord chargeRecord) {
        //验证参数是否合法，必填字段是否填写，
        AssertUtils.notNull(chargeRecord, "chargeRecord is null.");
        AssertUtils.notEmpty(chargeRecord.getId(), "chargeRecord.id is empty.");
        
        //生成需要更新字段的hashMap
        Map<String, Object> updateRowMap = new HashMap<String, Object>();
        updateRowMap.put("id", chargeRecord.getId());
        
        //需要更新的字段
        updateRowMap.put("revoke", chargeRecord.isRevoked());
        updateRowMap.put("revokeDate", chargeRecord.getRevokeDate());
        updateRowMap.put("loanAccountId", chargeRecord.getLoanAccountId());
        
        updateRowMap.put("lastUpdateDate", chargeRecord.getLastUpdateDate());
        
        int updateRowCount = this.chargeRecordDao.update(updateRowMap);
        
        //如果需要大于1时，抛出异常并回滚，需要在这里修改
        return updateRowCount >= 1;
    }
}
