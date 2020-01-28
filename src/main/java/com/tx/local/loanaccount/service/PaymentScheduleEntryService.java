/*
 * 描          述:  <描述>
 * 修  改   人:  
 * 修改时间:  
 * <修改描述:>
 */
package com.tx.local.loanaccount.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.tx.core.TxConstants;
import com.tx.core.exceptions.util.AssertUtils;
import com.tx.core.paged.model.PagedList;
import com.tx.local.basicdata.model.FeeItemEnum;
import com.tx.local.loanaccount.dao.PaymentScheduleEntryDao;
import com.tx.local.loanaccount.model.PaymentSchedule;
import com.tx.local.loanaccount.model.PaymentScheduleEntry;
import com.tx.local.loanaccount.model.ScheduleTypeEnum;

/**
 * PaymentScheduleEntry的业务层
 * <功能详细描述>
 * 
 * @author  
 * @version  [版本号, ]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
@Component("paymentScheduleEntryService")
public class PaymentScheduleEntryService {
    
    @SuppressWarnings("unused")
    private Logger logger = LoggerFactory
            .getLogger(PaymentScheduleEntryService.class);
    
    @Resource(name = "paymentScheduleEntryDao")
    private PaymentScheduleEntryDao paymentScheduleEntryDao;
    
    /** 
     *批量新增
     *<功能详细描述>
     * @param paymentScheduleEntryList [参数说明]
     * 
     * @return void [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @Transactional
    public void batchInsert(
            List<PaymentScheduleEntry> paymentScheduleEntryList) {
        AssertUtils.notEmpty(paymentScheduleEntryList,
                "paymentScheduleEntryList is empty.");
        
        this.paymentScheduleEntryDao.batchInsert(paymentScheduleEntryList);
    }
    
    /**
      * 将paymentScheduleEntry实例插入数据库中保存
      * 1、如果paymentScheduleEntry为空时抛出参数为空异常
      * 2、如果paymentScheduleEntry中部分必要参数为非法值时抛出参数不合法异常
     * <功能详细描述>
     * @param district [参数说明]
     * 
     * @return void [返回类型说明]
     * @exception throws
     * @see [类、类#方法、类#成员]
    */
    @Transactional
    public void insert(PaymentScheduleEntry paymentScheduleEntry) {
        //验证参数是否合法
        AssertUtils.notNull(paymentScheduleEntry,
                "paymentScheduleEntry is null.");
        AssertUtils.notEmpty(paymentScheduleEntry.getId(),
                "paymentScheduleEntry.id is empty.");
        
        //设置默认数据
        this.paymentScheduleEntryDao.insert(paymentScheduleEntry);
    }
    
    /**
     * 根据id删除paymentScheduleEntry实例
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
        
        PaymentScheduleEntry condition = new PaymentScheduleEntry();
        condition.setId(id);
        return this.paymentScheduleEntryDao.delete(condition);
    }
    
    /**
     * 根据Id查询PaymentScheduleEntry实体
     * 1、当id为empty时抛出异常
     * <功能详细描述>
     * @param id
     * @return [参数说明]
     * 
     * @return PaymentScheduleEntry [返回类型说明]
     * @exception throws 可能存在数据库访问异常DataAccessException
     * @see [类、类#方法、类#成员]
     */
    public PaymentScheduleEntry findById(String id) {
        AssertUtils.notEmpty(id, "id is empty.");
        
        PaymentScheduleEntry condition = new PaymentScheduleEntry();
        condition.setId(id);
        
        PaymentScheduleEntry res = this.paymentScheduleEntryDao.find(condition);
        return res;
    }
    
    /** 
     *根据费用项及paymentSchedule 查询还款计划分项
     *<功能详细描述>
     * @param feeItem
     * @param paymentSchedule
     * @return [参数说明]
     * 
     * @return PaymentScheduleEntry [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    public PaymentScheduleEntry findByPaymentScheduleId(
            String paymentScheduleId, FeeItemEnum feeItem) {
        AssertUtils.notEmpty(paymentScheduleId, "paymentScheduleId is null.");
        AssertUtils.notEmpty(feeItem, "feeItem is null.");
        
        PaymentScheduleEntry condition = new PaymentScheduleEntry();
        condition.setFeeItem(feeItem);
        PaymentSchedule paymentSchedule = new PaymentSchedule();
        paymentSchedule.setId(paymentScheduleId);
        condition.setPaymentSchedule(paymentSchedule);
        
        return this.paymentScheduleEntryDao.find(condition);
    }
    
    /** 
     *根据费用项及paymentSchedule 查询还款计划分项
     *<功能详细描述>
     * @param feeItem
     * @param paymentSchedule
     * @return [参数说明]
     * 
     * @return PaymentScheduleEntry [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    public PaymentScheduleEntry findByPeriod(ScheduleTypeEnum scheduleType,
            String period, FeeItemEnum feeItem) {
        AssertUtils.notNull(scheduleType, "scheduleType is null.");
        AssertUtils.notEmpty(period, "period is empty.");
        AssertUtils.notNull(feeItem, "feeItem is null.");
        
        PaymentScheduleEntry condition = new PaymentScheduleEntry();
        condition.setFeeItem(feeItem);
        condition.setScheduleType(scheduleType);
        condition.setPeriod(period);
        
        return this.paymentScheduleEntryDao.find(condition);
    }
    
    /**
     * 根据PaymentScheduleEntry实体列表
     * @return [参数说明]
     * 
     * @return List<PaymentScheduleEntry> [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
    */
    public List<PaymentScheduleEntry> queryList(Map<String, Object> params) {
        params = params == null ? new HashMap<String, Object>() : params;
        
        //根据实际情况，填入排序字段等条件，根据是否需要排序，选择调用dao内方法
        List<PaymentScheduleEntry> resList = this.paymentScheduleEntryDao
                .queryList(params);
        
        return resList;
    }
    
    /**
     * 根据还款计划主键查询还款计划分项集合<br/>
     *<功能详细描述>
     * @param paymentScheduleId [参数说明]
     * 
     * @return void [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    public List<PaymentScheduleEntry> queryListByPaymentScheduleId(
            String paymentScheduleId, Map<String, Object> params) {
        AssertUtils.notEmpty(paymentScheduleId, "paymentScheduleId is empty.");
        
        params = params == null ? new HashMap<String, Object>() : params;
        params.put("paymentScheduleId", paymentScheduleId);
        
        List<PaymentScheduleEntry> resList = this.paymentScheduleEntryDao
                .queryList(params);
        return resList;
    }
    
    /**
     * 根据 loanAccountId 查询PaymentScheduleEntry实体集合
     * <功能详细描述>
     * @return [参数说明]
     * 
     * @return List<PaymentScheduleEntry> [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    public List<PaymentScheduleEntry> queryListByLoanAccountId(
            String loanAccountId, Map<String, Object> params) {
        AssertUtils.notEmpty(loanAccountId, "loanAccountId is empty.");
        
        params = params == null ? new HashMap<String, Object>() : params;
        params.put("loanAccountId", loanAccountId);
        
        //根据实际情况，填入排序字段等条件，根据是否需要排序，选择调用dao内方法
        List<PaymentScheduleEntry> resList = this.paymentScheduleEntryDao
                .queryList(params);
        
        return resList;
    }
    
    /**
     * 分页查询PaymentScheduleEntry实体列表
     * <功能详细描述>
     * @return [参数说明]
     * 
     * @return List<PaymentScheduleEntry> [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    public PagedList<PaymentScheduleEntry> queryPagedList(int pageIndex,
            int pageSize, Map<String, Object> params) {
        params = params == null ? new HashMap<String, Object>() : params;
        
        //根据实际情况，填入排序字段等条件，根据是否需要排序，选择调用dao内方法
        PagedList<PaymentScheduleEntry> resPagedList = this.paymentScheduleEntryDao
                .queryPagedList(params, pageIndex, pageSize);
        
        return resPagedList;
    }
    
    /**
     * 查询paymentScheduleEntry列表总条数
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
        int res = this.paymentScheduleEntryDao.count(params);
        
        return res;
    }
    
    /**
     * 批量更新还款计划分项
     * <功能详细描述>
     * @param paymentScheduleEntryList [参数说明]
     * 
     * @return void [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @Transactional
    public void batchUpdate(
            List<PaymentScheduleEntry> paymentScheduleEntryList) {
        AssertUtils.notEmpty(paymentScheduleEntryList,
                "paymentScheduleEntryList is empty.");
        List<Map<String, Object>> updateRowMapList = new ArrayList<>(
                TxConstants.INITIAL_CONLLECTION_SIZE);
        for (PaymentScheduleEntry paymentScheduleEntry : paymentScheduleEntryList) {
            //生成需要更新字段的hashMap
            Map<String, Object> updateRowMap = new HashMap<String, Object>();
            updateRowMap.put("id", paymentScheduleEntry.getId());
            
            //需要更新的字段
            //updateRowMap.put("paymentSchedule", paymentScheduleEntry.getPaymentSchedule());
            //updateRowMap.put("period", paymentScheduleEntry.getPeriod());
            //updateRowMap.put("feeItem", paymentScheduleEntry.getFeeItem());
            //updateRowMap.put("loanAccountId", paymentScheduleEntry.getLoanAccountId());
            
            updateRowMap.put("prePeriod", paymentScheduleEntry.getPrePeriod());
            updateRowMap.put("nextPeriod",
                    paymentScheduleEntry.getNextPeriod());
            updateRowMap.put("receivableAmount",
                    paymentScheduleEntry.getReceivableAmount());
            updateRowMap.put("exemptAmount",
                    paymentScheduleEntry.getExemptAmount());
            updateRowMap.put("actualReceivedAmount",
                    paymentScheduleEntry.getActualReceivedAmount());
            //updateRowMap.put("receivableAmountIrr",paymentScheduleEntry.getReceivableAmountIrr());
            //updateRowMap.put("exemptAmountIrr",paymentScheduleEntry.getExemptAmountIrr());
            //updateRowMap.put("actualReceivedAmountIrr",paymentScheduleEntry.getActualReceivedAmountIrr());
            
            updateRowMapList.add(updateRowMap);
        }
        
        this.paymentScheduleEntryDao.batchUpdate(updateRowMapList);
    }
    
    /**
     * 根据id更新对象
     * <功能详细描述>
     * @param paymentScheduleEntry
     * @return [参数说明]
     * 
     * @return boolean [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @Transactional
    public boolean updateById(PaymentScheduleEntry paymentScheduleEntry) {
        //验证参数是否合法，必填字段是否填写，
        AssertUtils.notNull(paymentScheduleEntry,
                "paymentScheduleEntry is null.");
        AssertUtils.notEmpty(paymentScheduleEntry.getId(),
                "paymentScheduleEntry.id is empty.");
        
        //生成需要更新字段的hashMap
        Map<String, Object> updateRowMap = new HashMap<String, Object>();
        updateRowMap.put("id", paymentScheduleEntry.getId());
        
        //需要更新的字段
        //updateRowMap.put("paymentSchedule", paymentScheduleEntry.getPaymentSchedule());
        //updateRowMap.put("period", paymentScheduleEntry.getPeriod());
        //updateRowMap.put("feeItem", paymentScheduleEntry.getFeeItem());
        //updateRowMap.put("loanAccountId", paymentScheduleEntry.getLoanAccountId());
        
        updateRowMap.put("prePeriod", paymentScheduleEntry.getPrePeriod());
        updateRowMap.put("nextPeriod", paymentScheduleEntry.getNextPeriod());
        updateRowMap.put("receivableAmount",
                paymentScheduleEntry.getReceivableAmount());
        updateRowMap.put("exemptAmount",
                paymentScheduleEntry.getExemptAmount());
        updateRowMap.put("actualReceivedAmount",
                paymentScheduleEntry.getActualReceivedAmount());
        //updateRowMap.put("receivableAmountIrr",paymentScheduleEntry.getReceivableAmountIrr());
        //updateRowMap.put("exemptAmountIrr",paymentScheduleEntry.getExemptAmountIrr());
        //updateRowMap.put("actualReceivedAmountIrr",paymentScheduleEntry.getActualReceivedAmountIrr());
        
        int updateRowCount = this.paymentScheduleEntryDao.update(updateRowMap);
        
        //如果需要大于1时，抛出异常并回滚，需要在这里修改
        return updateRowCount >= 1;
    }
}
