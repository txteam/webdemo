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
import com.tx.local.loanaccount.dao.PaymentRecordDao;
import com.tx.local.loanaccount.model.PaymentRecord;

/**
 * PaymentRecord的业务层
 * <功能详细描述>
 * 
 * @author  
 * @version  [版本号, ]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
@Component("paymentRecordService")
public class PaymentRecordService {
    
    @SuppressWarnings("unused")
    private Logger logger = LoggerFactory.getLogger(PaymentRecordService.class);
    
    @Resource(name = "paymentRecordDao")
    private PaymentRecordDao paymentRecordDao;
    
    /**
      * 批量插入还款记录集合
      *<功能详细描述>
      * @param paymentRecordList [参数说明]
      * 
      * @return void [返回类型说明]
      * @exception throws [异常类型] [异常说明]
      * @see [类、类#方法、类#成员]
     */
    @Transactional
    public void batchInsert(List<PaymentRecord> paymentRecordList) {
        if (CollectionUtils.isEmpty(paymentRecordList)) {
            return;
        }
        for (PaymentRecord paymentRecord : paymentRecordList) {
            validate4Insert(paymentRecord);
        }
        this.paymentRecordDao.batchInsert(paymentRecordList);
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
    public void insert(PaymentRecord paymentRecord) {
        //验证参数是否合法
        validate4Insert(paymentRecord);
        
        this.paymentRecordDao.insert(paymentRecord);
    }
    
    /** 
    * 校验插入的计费记录
    * <功能详细描述>
    * @param paymentRecord [参数说明]
    * 
    * @return void [返回类型说明]
    * @exception throws [异常类型] [异常说明]
    * @see [类、类#方法、类#成员]
    */
    private void validate4Insert(PaymentRecord paymentRecord) {
        AssertUtils.notNull(paymentRecord, "paymentRecord is null.");
        AssertUtils.notEmpty(paymentRecord.getLoanAccountId(), "paymentRecord.loanAccountId is empty.");
        AssertUtils.notNull(paymentRecord.getTradingRecord(), "paymentRecord.tradingRecord is null.");
        AssertUtils.notEmpty(paymentRecord.getTradingRecord().getId(), "paymentRecord.tradingRecord.id is empty.");
        AssertUtils.notNull(paymentRecord.getBuildTradingRecord(), "paymentRecord.buildTradingRecord is null.");
        AssertUtils.notEmpty(paymentRecord.getBuildTradingRecord().getId(), "paymentRecord.buildTradingRecord.id is empty.");
        AssertUtils.notNull(paymentRecord.getScheduleType(), "paymentRecord.scheduleType is null.");
        AssertUtils.notEmpty(paymentRecord.getPeriod(), "paymentRecord.period is empty.");
        AssertUtils.notNull(paymentRecord.getSourceSum(), "paymentRecord.sourceSum is null.");
        AssertUtils.notNull(paymentRecord.getSum(), "paymentRecord.sum is null.");
        AssertUtils.notNull(paymentRecord.getTargetSum(), "paymentRecord.targetSum is null.");
        AssertUtils.notNull(
                paymentRecord.getSourceSum().add(paymentRecord.getSum()).compareTo(paymentRecord.getTargetSum()) == 0,
                "paymentRecord.sourceSum:{} +  sum:{} != targetSum:{}.");
        
        Date now = new Date();
        paymentRecord.setCreateDate(now);
        paymentRecord.setLastUpdateDate(now);
        paymentRecord.setRevoked(false);
        paymentRecord.setRevokeDate(null);
    }
    
    /**
     * 根据id删除paymentRecord实例
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
        
        PaymentRecord condition = new PaymentRecord();
        condition.setId(id);
        return this.paymentRecordDao.delete(condition);
    }
    
    /**
      * 根据Id查询PaymentRecord实体
      * 1、当id为empty时抛出异常
      * <功能详细描述>
      * @param id
      * @return [参数说明]
      * 
      * @return PaymentRecord [返回类型说明]
      * @exception throws 可能存在数据库访问异常DataAccessException
      * @see [类、类#方法、类#成员]
     */
    public PaymentRecord findById(String id) {
        AssertUtils.notEmpty(id, "id is empty.");
        
        PaymentRecord condition = new PaymentRecord();
        condition.setId(id);
        PaymentRecord res = this.paymentRecordDao.find(condition);
        return res;
    }
    
    /**
     * 根据贷款账户id查询还款记录并附带还款记录分项
     * <功能详细描述>
     * @param loanAccountId
     * @return [参数说明]
     * 
     * @return List<PaymentRecord> [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    public List<PaymentRecord> queryListByTradingRecordId(String tradingRecordId) {
        AssertUtils.notEmpty(tradingRecordId, "tradingRecordId is empty");
        
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("tradingRecordId", tradingRecordId);
        
        List<PaymentRecord> paymentRecordList = this.paymentRecordDao.queryList(params);
        return paymentRecordList;
    }
    
    /**
     * 根据贷款账户id查询还款记录并附带还款记录分项
     * <功能详细描述>
     * @param loanAccountId
     * @return [参数说明]
     * 
     * @return List<PaymentRecord> [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    public List<PaymentRecord> queryListByLoanAccountId(String loanAccountId) {
        AssertUtils.notEmpty(loanAccountId, "loanAccountId is empty");
        
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("loanAccountId", loanAccountId);
        
        List<PaymentRecord> paymentRecordList = this.paymentRecordDao.queryList(params);
        return paymentRecordList;
    }
    
    /**
      * 根据id更新对象
      * <功能详细描述>
      * @param paymentRecord
      * @return [参数说明]
      * 
      * @return boolean [返回类型说明]
      * @exception throws [异常类型] [异常说明]
      * @see [类、类#方法、类#成员]
     */
    @Transactional
    protected boolean updateById(PaymentRecord paymentRecord) {
        //验证参数是否合法，必填字段是否填写，
        AssertUtils.notNull(paymentRecord, "paymentRecord is null.");
        AssertUtils.notEmpty(paymentRecord.getId(), "paymentRecord.id is empty.");
        
        //生成需要更新字段的hashMap
        Map<String, Object> updateRowMap = new HashMap<String, Object>();
        updateRowMap.put("id", paymentRecord.getId());
        
        //需要更新的字段
        updateRowMap.put("sourceSum", paymentRecord.getSourceSum());
        //        updateRowMap.put("summary", paymentRecord.getSummary());
        //        updateRowMap.put("sumIrr", paymentRecord.getSumIrr());
        //        updateRowMap.put("sourceSumIrr", paymentRecord.getSourceSumIrr());
        //        updateRowMap.put("operatorId", paymentRecord.getOperatorId());
        //        updateRowMap.put("writeOff", paymentRecord.isWriteOff());
        updateRowMap.put("tradingRecord", paymentRecord.getTradingRecord());
        //        updateRowMap.put("requestId", paymentRecord.getRequestId());
        updateRowMap.put("paymentSchedule", paymentRecord.getPaymentSchedule());
        //        updateRowMap.put("principalBalanceIrr", paymentRecord.getPrincipalBalanceIrr());
        //        updateRowMap.put("vcid", paymentRecord.getVcid());
        updateRowMap.put("sum", paymentRecord.getSum());
        //        updateRowMap.put("organizationId", paymentRecord.getOrganizationId());
        updateRowMap.put("period", paymentRecord.getPeriod());
        //        updateRowMap.put("targetSumIrr", paymentRecord.getTargetSumIrr());
        //        updateRowMap.put("principalBalance", paymentRecord.getPrincipalBalance());
        //        updateRowMap.put("expireDate", paymentRecord.getExpireDate());
        updateRowMap.put("repayDate", paymentRecord.getRepayDate());
        //        updateRowMap.put("processDate", paymentRecord.getProcessDate());
        updateRowMap.put("targetSum", paymentRecord.getTargetSum());
        updateRowMap.put("loanAccountId", paymentRecord.getLoanAccountId());
        updateRowMap.put("createDate", paymentRecord.getCreateDate());
        
        int updateRowCount = this.paymentRecordDao.update(updateRowMap);
        
        //如果需要大于1时，抛出异常并回滚，需要在这里修改
        return updateRowCount >= 1;
    }
    
    //  /**
    //     * 根据贷款账户id查询还款记录并附带还款记录分项
    //     * <功能详细描述>
    //     * @param loanAccountId
    //     * @return [参数说明]
    //     * 
    //     * @return List<PaymentRecord> [返回类型说明]
    //     * @exception throws [异常类型] [异常说明]
    //     * @see [类、类#方法、类#成员]
    //    */
    //   public List<PaymentRecord> queryDetailListByLoanAccountId(String loanAccountId) {
    //       AssertUtils.notEmpty(loanAccountId, "loanAccountId is empty");
    //       
    //       Map<String, Object> params = new HashMap<String, Object>();
    //       params.put("loanAccountId", loanAccountId);
    //       
    //       List<PaymentRecord> paymentRecordList = this.paymentRecordDao.queryList(params);
    //       List<PaymentRecordEntry> paymentRecordEntryList = this.paymentRecordEntryService
    //               .queryListByLoanAccountId(loanAccountId);
    //       
    //       MultiValueMap<String, PaymentRecordEntry> entryMultiValueMap = new LinkedMultiValueMap<String, PaymentRecordEntry>();
    //       for (PaymentRecordEntry entry : paymentRecordEntryList) {
    //           entryMultiValueMap.add(entry.getPaymentRecord().getId(), entry);
    //       }
    //       for (PaymentRecord record : paymentRecordList) {
    //           List<PaymentRecordEntry> entryList = entryMultiValueMap.get(record.getId());
    //           record.setPaymentRecordEntryList(entryList);
    //       }
    //       return paymentRecordList;
    //   }
}
