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

import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.tx.core.TxConstants;
import com.tx.core.exceptions.util.AssertUtils;
import com.tx.local.loanaccount.dao.PaymentScheduleDao;
import com.tx.local.loanaccount.model.PaymentSchedule;

/**
 * PaymentSchedule的业务层
 * <功能详细描述>
 * 
 * @author  
 * @version  [版本号, ]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
@Component("paymentScheduleService")
public class PaymentScheduleService {
    
    @SuppressWarnings("unused")
    private Logger logger = LoggerFactory.getLogger(PaymentScheduleService.class);
    
    @Resource(name = "paymentScheduleDao")
    private PaymentScheduleDao paymentScheduleDao;
    
    /** 
     * 批量新增还款计划项<br/>
     * <功能详细描述>
     * @param paymentSchedules [参数说明]
     * 
     * @return void [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @Transactional
    public void batchInsert(List<PaymentSchedule> paymentSchedules) {
        AssertUtils.notEmpty(paymentSchedules, "paymentSchedules is empty.");
        
        this.paymentScheduleDao.batchInsert(paymentSchedules);
    }
    
    /**
      * 将paymentSchedule实例插入数据库中保存
      * 1、如果paymentSchedule为空时抛出参数为空异常
      * 2、如果paymentSchedule中部分必要参数为非法值时抛出参数不合法异常
     * <功能详细描述>
     * @param district [参数说明]
     * 
     * @return void [返回类型说明]
     * @exception throws
     * @see [类、类#方法、类#成员]
    */
    @Transactional
    public void insert(PaymentSchedule paymentSchedule) {
        //验证参数是否合法
        AssertUtils.notNull(paymentSchedule, "paymentSchedule is null.");
        
        this.paymentScheduleDao.insert(paymentSchedule);
    }
    
    /**
     * 根据id删除paymentSchedule实例
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
        
        PaymentSchedule condition = new PaymentSchedule();
        condition.setId(id);
        return this.paymentScheduleDao.delete(condition);
    }
    
    /**
      * 根据Id查询PaymentSchedule实体
      * 1、当id为empty时抛出异常
      * <功能详细描述>
      * @param id
      * @return [参数说明]
      * 
      * @return PaymentSchedule [返回类型说明]
      * @exception throws 可能存在数据库访问异常DataAccessException
      * @see [类、类#方法、类#成员]
     */
    public PaymentSchedule findById(String id) {
        AssertUtils.notEmpty(id, "id is empty.");
        
        PaymentSchedule condition = new PaymentSchedule();
        condition.setId(id);
        PaymentSchedule res = this.paymentScheduleDao.find(condition);
        
        return res;
    }
    
    /**
     * 根据loanAccountId获取 PaymentSchedule实体列表
     * 
     * <功能详细描述>
     * @return [参数说明]
     * 
     * @return List<PaymentSchedule> [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    public List<PaymentSchedule> queryListByLoanAccountId(String loanAccountId, Map<String, Object> params) {
        AssertUtils.notEmpty(loanAccountId, "loanAccountId is empty.");
        
        //生成查询条件
        params = params == null ? new HashMap<String, Object>() : params;
        params.put("loanAccountId", loanAccountId);
        
        //根据实际情况，填入排序字段等条件，根据是否需要排序，选择调用dao内方法
        List<PaymentSchedule> resList = this.paymentScheduleDao.queryList(params);
        
        return resList;
    }
    
    /**
      * 批量更新还款计划<br/>
      *<功能详细描述>
      * @param paymentScheduleList [参数说明]
      * 
      * @return void [返回类型说明]
      * @exception throws [异常类型] [异常说明]
      * @see [类、类#方法、类#成员]
     */
    @Transactional
    public void batchUpdate(List<PaymentSchedule> paymentScheduleList) {
        if (CollectionUtils.isEmpty(paymentScheduleList)) {
            return;
        }
        List<Map<String, Object>> rowMapList = new ArrayList<>(TxConstants.INITIAL_CONLLECTION_SIZE);
        for (PaymentSchedule paymentSchedule : paymentScheduleList) {
            //验证参数是否合法，必填字段是否填写，
            AssertUtils.notNull(paymentSchedule, "paymentSchedule is null.");
            AssertUtils.notEmpty(paymentSchedule.getId(), "paymentSchedule.id is empty.");
            
            //生成需要更新字段的hashMap
            Map<String, Object> updateRowMap = new HashMap<String, Object>();
            updateRowMap.put("id", paymentSchedule.getId());
            
            //需要更新的字段
            //updateRowMap.put("loanAccountId", paymentSchedule.getLoanAccountId());
            //updateRowMap.put("receivableSumIrr",paymentSchedule.getReceivableSumIrr());
            //updateRowMap.put("principalBalanceIrr",paymentSchedule.getPrincipalBalanceIrr());
            //updateRowMap.put("exemptSumIrr", paymentSchedule.getExemptSumIrr());
            //updateRowMap.put("actualReceivedSumIrr",paymentSchedule.getActualReceivedSumIrr());
            updateRowMap.put("lastRepayDate", paymentSchedule.getLastRepayDate());
            updateRowMap.put("prePeriod", paymentSchedule.getPrePeriod());
            updateRowMap.put("period", paymentSchedule.getPeriod());
            updateRowMap.put("nextPeriod", paymentSchedule.getNextPeriod());
            
            updateRowMap.put("principalBalance", paymentSchedule.getPrincipalBalance());
            updateRowMap.put("receivableSum", paymentSchedule.getReceivableSum());
            updateRowMap.put("exemptSum", paymentSchedule.getExemptSum());
            updateRowMap.put("actualReceivedSum", paymentSchedule.getActualReceivedSum());
            updateRowMap.put("overdue", paymentSchedule.isOverdue());
            updateRowMap.put("overdueSum", paymentSchedule.getOverdueSum());
            updateRowMap.put("settle", paymentSchedule.isSettle());
            updateRowMap.put("repaymentDate", paymentSchedule.getRepaymentDate());
            
            rowMapList.add(updateRowMap);
        }
        this.paymentScheduleDao.batchUpdate(rowMapList);
    }
    
    /**
      * 根据id更新对象
      * <功能详细描述>
      * @param paymentSchedule
      * @return [参数说明]
      * 
      * @return boolean [返回类型说明]
      * @exception throws [异常类型] [异常说明]
      * @see [类、类#方法、类#成员]
     */
    @Transactional
    public boolean updateById(PaymentSchedule paymentSchedule) {
        //验证参数是否合法，必填字段是否填写，
        AssertUtils.notNull(paymentSchedule, "paymentSchedule is null.");
        AssertUtils.notEmpty(paymentSchedule.getId(), "paymentSchedule.id is empty.");
        
        //生成需要更新字段的hashMap
        Map<String, Object> updateRowMap = new HashMap<String, Object>();
        updateRowMap.put("id", paymentSchedule.getId());
        
        //需要更新的字段
        //updateRowMap.put("loanAccountId", paymentSchedule.getLoanAccountId());
        //updateRowMap.put("receivableSumIrr", paymentSchedule.getReceivableSumIrr());
        //updateRowMap.put("principalBalanceIrr", paymentSchedule.getPrincipalBalanceIrr());
        //updateRowMap.put("exemptSumIrr", paymentSchedule.getExemptSumIrr());
        //updateRowMap.put("actualReceivedSumIrr", paymentSchedule.getActualReceivedSumIrr());
        updateRowMap.put("lastRepayDate", paymentSchedule.getLastRepayDate());
        updateRowMap.put("prePeriod", paymentSchedule.getPrePeriod());
        updateRowMap.put("period", paymentSchedule.getPeriod());
        updateRowMap.put("nextPeriod", paymentSchedule.getNextPeriod());
        
        updateRowMap.put("principalBalance", paymentSchedule.getPrincipalBalance());
        updateRowMap.put("receivableSum", paymentSchedule.getReceivableSum());
        updateRowMap.put("exemptSum", paymentSchedule.getExemptSum());
        updateRowMap.put("actualReceivedSum", paymentSchedule.getActualReceivedSum());
        updateRowMap.put("overdue", paymentSchedule.isOverdue());
        updateRowMap.put("overdueSum", paymentSchedule.getOverdueSum());
        updateRowMap.put("settle", paymentSchedule.isSettle());
        updateRowMap.put("repaymentDate", paymentSchedule.getRepaymentDate());
        
        int updateRowCount = this.paymentScheduleDao.update(updateRowMap);
        
        //如果需要大于1时，抛出异常并回滚，需要在这里修改
        return updateRowCount >= 1;
    }
}
