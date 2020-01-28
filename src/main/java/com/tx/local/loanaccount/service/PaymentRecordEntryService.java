/*
 * 描          述:  <描述>
 * 修  改   人:  
 * 修改时间:  
 * <修改描述:>
 */
package com.tx.local.loanaccount.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.tx.core.exceptions.util.AssertUtils;
import com.tx.local.loanaccount.dao.PaymentRecordEntryDao;
import com.tx.local.loanaccount.model.PaymentRecordEntry;

/**
 * PaymentRecordEntry的业务层
 * <功能详细描述>
 * 
 * @author  
 * @version  [版本号, ]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
@Component("paymentRecordEntryService")
public class PaymentRecordEntryService {
    
    @SuppressWarnings("unused")
    private Logger logger = LoggerFactory.getLogger(PaymentRecordEntryService.class);
    
    @Resource(name = "paymentRecordEntryDao")
    private PaymentRecordEntryDao paymentRecordEntryDao;
    
    /**
      * 批量插入还款记录分项
      *<功能详细描述>
      * @param paymentRecordEntryList [参数说明]
      * 
      * @return void [返回类型说明]
      * @exception throws [异常类型] [异常说明]
      * @see [类、类#方法、类#成员]
     */
    @Transactional
    public void batchInsert(List<PaymentRecordEntry> paymentRecordEntryList) {
        AssertUtils.notEmpty(paymentRecordEntryList, "paymentRecordEntryList is empty.");
        this.paymentRecordEntryDao.batchInsert(paymentRecordEntryList);
    }
    
    /**
      * 根据PaymentRecordEntry实体列表
      * <功能详细描述>
      * @return [参数说明]
      * 
      * @return List<PaymentRecordEntry> [返回类型说明]
      * @exception throws [异常类型] [异常说明]
      * @see [类、类#方法、类#成员]
     */
    public List<PaymentRecordEntry> queryListByTradingRecordId(String tradingRecordId) {
        AssertUtils.notEmpty(tradingRecordId, "tradingRecordId is empty.");
        
        //生成查询条件
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("tradingRecordId", tradingRecordId);
        
        //根据实际情况，填入排序字段等条件，根据是否需要排序，选择调用dao内方法
        List<PaymentRecordEntry> resList = this.paymentRecordEntryDao.queryList(params);
        
        return resList;
    }
    
    /**
     * 根据PaymentRecordEntry实体列表
     * <功能详细描述>
     * @return [参数说明]
     * 
     * @return List<PaymentRecordEntry> [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
    */
    public List<PaymentRecordEntry> queryListByLoanAccountId(String loanAccountId) {
        AssertUtils.notEmpty(loanAccountId, "loanAccountId is empty.");
        
        //生成查询条件
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("loanAccountId", loanAccountId);
        
        //根据实际情况，填入排序字段等条件，根据是否需要排序，选择调用dao内方法
        List<PaymentRecordEntry> resList = this.paymentRecordEntryDao.queryList(params);
        
        return resList;
    }
}
