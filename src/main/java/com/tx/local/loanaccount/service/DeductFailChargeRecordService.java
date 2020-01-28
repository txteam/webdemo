/*
 * 描述: <描述>
 * 修改人:  
 * 修改时间:  
 * <修改描述:>
 */
package com.tx.local.loanaccount.service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.tx.local.loanaccount.dao.DeductFailChargeRecordDao;
import com.tx.local.loanaccount.model.DeductFailChargeRecord;
import com.tx.core.exceptions.util.AssertUtils;
import com.tx.core.paged.model.PagedList;

/**
 * DeductFailChargeRecord的业务层
 * <功能详细描述>
 * 
 * @author  
 * @version  [版本号, ]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
@Component("deductFailChargeRecordService")
public class DeductFailChargeRecordService {
    
    @SuppressWarnings("unused")
    private Logger logger = LoggerFactory.getLogger(DeductFailChargeRecordService.class);
    
    @Resource(name = "deductFailChargeRecordDao")
    private DeductFailChargeRecordDao deductFailChargeRecordDao;
    
    /**
    * 将deductFailChargeRecord实例插入数据库中保存<br />
    * 1、如果deductFailChargeRecord为空时抛出参数为空异常<br />
    * 2、如果deductFailChargeRecord中部分必要参数为非法值时抛出参数不合法异常<br />
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
    public void insert(DeductFailChargeRecord deductFailChargeRecord) {
        //验证参数是否合法
        AssertUtils.notNull(deductFailChargeRecord, "deductFailChargeRecord is null.");
        AssertUtils.notEmpty(deductFailChargeRecord.getLoanAccountId(),
                "deductFailChargeRecord.loanAccountId is empty.");
        AssertUtils.notNull(deductFailChargeRecord.getFeeItem(), "deductFailChargeRecord.feeItem is null.");
        AssertUtils.notNull(deductFailChargeRecord.getAmount(), "deductFailChargeRecord.amount is null.");
        AssertUtils.notNull(deductFailChargeRecord.getRecordDate(), "deductFailChargeRecord.recordDate is null.");
        AssertUtils.notEmpty(deductFailChargeRecord.getDuductRecordId(),
                "deductFailChargeRecord.duductTradingRecordId is empty.");
        AssertUtils.notEmpty(deductFailChargeRecord.getTradingRecordId(),
                "deductFailChargeRecord.tradingRecordId is empty.");
        
        Date now = new Date();
        deductFailChargeRecord.setCreateDate(now);
        deductFailChargeRecord.setLastUpdateDate(now);
        deductFailChargeRecord.setRevoked(false);
        deductFailChargeRecord.setRevokeDate(null);
        
        this.deductFailChargeRecordDao.insert(deductFailChargeRecord);
    }
    
    /**
     * 根据id删除deductFailChargeRecord实例<br />
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
        
        DeductFailChargeRecord condition = new DeductFailChargeRecord();
        condition.setId(id);
        return this.deductFailChargeRecordDao.delete(condition);
    }
    
    /**
      * 根据Id查询DeductFailChargeRecord实体
      * 1、当id为empty时抛出异常
      * <功能详细描述>
      * @param id
      * @return [参数说明]
      * 
      * @return DeductFailChargeRecord [返回类型说明]
      * @exception throws 可能存在数据库访问异常DataAccessException
      * @see [类、类#方法、类#成员]
     */
    public DeductFailChargeRecord findById(String id) {
        AssertUtils.notEmpty(id, "id is empty.");
        
        DeductFailChargeRecord condition = new DeductFailChargeRecord();
        condition.setId(id);
        
        DeductFailChargeRecord res = this.deductFailChargeRecordDao.find(condition);
        return res;
    }
    
    /**
      * 根据DeductFailChargeRecord实体列表
      * <功能详细描述>
      * @return [参数说明]
      * 
      * @return List<DeductFailChargeRecord> [返回类型说明]
      * @exception throws [异常类型] [异常说明]
      * @see [类、类#方法、类#成员]
     */
    public List<DeductFailChargeRecord> queryList(Map<String, Object> params) {
        params = params == null ? new HashMap<String, Object>() : params;
        
        //根据实际情况，填入排序字段等条件，根据是否需要排序，选择调用dao内方法
        List<DeductFailChargeRecord> resList = this.deductFailChargeRecordDao.queryList(params);
        
        return resList;
    }
    
    /**
     * 分页查询DeductFailChargeRecord实体列表
     * <功能详细描述>
     * @return [参数说明]
     * 
     * @return List<DeductFailChargeRecord> [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
    */
    public PagedList<DeductFailChargeRecord> queryPagedList(Map<String, Object> params, int pageIndex, int pageSize) {
        params = params == null ? new HashMap<String, Object>() : params;
        
        //根据实际情况，填入排序字段等条件，根据是否需要排序，选择调用dao内方法
        PagedList<DeductFailChargeRecord> resPagedList = this.deductFailChargeRecordDao.queryPagedList(params,
                pageIndex,
                pageSize);
        
        return resPagedList;
    }
    
    /**
      * 查询deductFailChargeRecord列表总条数
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
        int res = this.deductFailChargeRecordDao.count(params);
        
        return res;
    }
    
    /**
      * 根据id更新对象
      * <功能详细描述>
      * @param deductFailChargeRecord
      * @return [参数说明]
      * 
      * @return boolean [返回类型说明]
      * @exception throws [异常类型] [异常说明]
      * @see [类、类#方法、类#成员]
     */
    @Transactional
    public boolean updateById(DeductFailChargeRecord deductFailChargeRecord) {
        //验证参数是否合法，必填字段是否填写，
        AssertUtils.notNull(deductFailChargeRecord, "deductFailChargeRecord is null.");
        AssertUtils.notEmpty(deductFailChargeRecord.getId(), "deductFailChargeRecord.id is empty.");
        
        //生成需要更新字段的hashMap
        Map<String, Object> updateRowMap = new HashMap<String, Object>();
        updateRowMap.put("id", deductFailChargeRecord.getId());
        
        //需要更新的字段
        updateRowMap.put("lastUpdateDate", new Date());
        updateRowMap.put("revokeDate", deductFailChargeRecord.getRevokeDate());
        updateRowMap.put("revoked", deductFailChargeRecord.isRevoked());
        
        int updateRowCount = this.deductFailChargeRecordDao.update(updateRowMap);
        
        //如果需要大于1时，抛出异常并回滚，需要在这里修改
        return updateRowCount >= 1;
    }
}
