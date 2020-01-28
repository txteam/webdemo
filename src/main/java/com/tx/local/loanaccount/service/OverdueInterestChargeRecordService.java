/*
 * 描          述:  <描述>
 * 修  改   人:  
 * 修改时间:  
 * <修改描述:>
 */
package com.tx.local.loanaccount.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
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
import com.tx.local.basicdata.model.FeeItemEnum;
import com.tx.local.loanaccount.dao.OverdueInterestChargeRecordDao;
import com.tx.local.loanaccount.helper.overdueinterest.OverdueInterestRecordHelper;
import com.tx.local.loanaccount.model.OverdueInterestChargeRecord;

/**
 * OverdueInterestRecord的业务层
 * <功能详细描述>
 * 
 * @author  
 * @version  [版本号, ]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
@Component("overdueInterestChargeRecordService")
public class OverdueInterestChargeRecordService {
    
    @SuppressWarnings("unused")
    private Logger logger = LoggerFactory.getLogger(OverdueInterestChargeRecordService.class);
    
    @Resource(name = "overdueInterestChargeRecordDao")
    private OverdueInterestChargeRecordDao overdueInterestChargeRecordDao;
    
    /**
      * 将overdueInterestRecord实例插入数据库中保存
      * 1、如果overdueInterestRecord为空时抛出参数为空异常
      * 2、如果overdueInterestRecord中部分必要参数为非法值时抛出参数不合法异常
     * <功能详细描述>
     * @param overdueInterestRecord [参数说明]
     * 
     * @return void [返回类型说明]
     * @exception throws
     * @see [类、类#方法、类#成员]
    */
    @Transactional
    public void insert(OverdueInterestChargeRecord overdueInterestChargeRecord) {
        //验证参数是否合法
        AssertUtils.notNull(overdueInterestChargeRecord, "overdueInterestChargeRecord is null.");
        AssertUtils.notEmpty(overdueInterestChargeRecord.getTradingRecordId(),
                "overdueInterestChargeRecord.tradingRecordId is empty.");
        AssertUtils.notNull(overdueInterestChargeRecord.getFeeItem(), "overdueInterestChargeRecord.feeItem is null.");
        AssertUtils.notNull(overdueInterestChargeRecord.getOverdueInterestRate(),
                "overdueInterestChargeRecord.overdueInterestRate is null.");
        AssertUtils.notNull(overdueInterestChargeRecord.getAmount(), "overdueInterestChargeRecord.amount is null.");
        AssertUtils.notNull(overdueInterestChargeRecord.getRecordDate(),
                "overdueInterestChargeRecord.recordDate is null.");
        
        overdueInterestChargeRecord.setCreateDate(new Date());
        
        //调用数据持久层对实体进行持久化操作
        this.overdueInterestChargeRecordDao.insert(overdueInterestChargeRecord);
    }
    
    /**
     * 将overdueInterestRecord实例插入数据库中保存
     * 1、如果overdueInterestRecord为空时抛出参数为空异常
     * 2、如果overdueInterestRecord中部分必要参数为非法值时抛出参数不合法异常
    * <功能详细描述>
    * @param overdueInterestRecord [参数说明]
    * 
    * @return void [返回类型说明]
    * @exception throws
    * @see [类、类#方法、类#成员]
    */
    @Transactional
    public void moveToHis(OverdueInterestChargeRecord overdueInterestChargeRecord) {
        AssertUtils.notNull(overdueInterestChargeRecord, "overdueInterestChargeRecord is null.");
        AssertUtils.notEmpty(overdueInterestChargeRecord.getId(), "overdueInterestChargeRecord.id is null.");
        AssertUtils.notEmpty(overdueInterestChargeRecord.getTradingRecordId(),
                "overdueInterestChargeRecord.tradingRecordId is empty.");
        AssertUtils.notNull(overdueInterestChargeRecord.getFeeItem(), "overdueInterestChargeRecord.feeItem is null.");
        AssertUtils.notNull(overdueInterestChargeRecord.getOverdueInterestRate(),
                "overdueInterestChargeRecord.overdueInterestRate is null.");
        AssertUtils.notNull(overdueInterestChargeRecord.getAmount(), "overdueInterestChargeRecord.amount is null.");
        AssertUtils.notNull(overdueInterestChargeRecord.getRecordDate(),
                "overdueInterestChargeRecord.recordDate is null.");
        
        //调用数据持久层对实体进行持久化操作
        this.overdueInterestChargeRecordDao.insertToHis(overdueInterestChargeRecord);
        this.overdueInterestChargeRecordDao.delete(overdueInterestChargeRecord);
    }
    
    /**
     * 根据id删除overdueInterestRecord实例
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
    public boolean deleteById(String id) {
        AssertUtils.notEmpty(id, "id is empty.");
        
        OverdueInterestChargeRecord condition = new OverdueInterestChargeRecord();
        condition.setId(id);
        int resInt = this.overdueInterestChargeRecordDao.delete(condition);
        return resInt > 0;
    }
    
    /**
      * 根据Id查询OverdueInterestRecord实体
      * 1、当id为empty时抛出异常
      * <功能详细描述>
      * @param id
      * @return [参数说明]
      * 
      * @return OverdueInterestRecord [返回类型说明]
      * @exception throws 可能存在数据库访问异常DataAccessException
      * @see [类、类#方法、类#成员]
     */
    public OverdueInterestChargeRecord findById(String id) {
        AssertUtils.notEmpty(id, "id is empty.");
        
        OverdueInterestChargeRecord condition = new OverdueInterestChargeRecord();
        condition.setId(id);
        
        OverdueInterestChargeRecord res = this.overdueInterestChargeRecordDao.find(condition);
        return res;
    }
    
    /**
     * 根据tradingRecordId查询OverdueInterestRecord实体
     * 1、当id为empty时抛出异常
     * <功能详细描述>
     * @param id
     * @return [参数说明]
     * 
     * @return OverdueInterestRecord [返回类型说明]
     * @exception throws 可能存在数据库访问异常DataAccessException
     * @see [类、类#方法、类#成员]
     */
    public List<OverdueInterestChargeRecord> queryListByTradingRecordId(String tradingRecordId) {
        AssertUtils.notEmpty(tradingRecordId, "tradingRecordId is empty.");
        
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("tradingRecordId", tradingRecordId);
        
        List<OverdueInterestChargeRecord> resList = this.overdueInterestChargeRecordDao
                .queryList(params);
        return resList;
    }
    
//    /**
//      * 根据贷款账户，查询指定还款渠道类型集合下的最后一次的计息日<br/>
//      * <功能详细描述>
//      * @param loanAccountId
//      * @param repayChannelTypes
//      * @return [参数说明]
//      * 
//      * @return Date [返回类型说明]
//      * @exception throws [异常类型] [异常说明]
//      * @see [类、类#方法、类#成员]
//     */
//    public Date getLastInterestAccrualDate(String loanAccountId, List<RepayChannelTypeEnum> repayChannelTypes) {
//        OverdueInterestChargeRecord oir = findLastOverdueInterestRecordByLoanAccountId(loanAccountId,
//                repayChannelTypes);
//        if (oir == null) {
//            return null;
//        } else {
//            return DateUtils.max(oir.getInterestAccrualDate(), oir.getRepayDate());
//        }
//    }
    
    /**
     * 根据贷款账户id查询逾期利息记录列表<br/>
     * <功能详细描述>
     * @param loanAccountId
     * @param repayChannelTypes
     * @return [参数说明]
     * 
     * @return List<OverdueInterestRecord> [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
    */
    public OverdueInterestChargeRecord findLastByLoanAccountId(String loanAccountId,FeeItemEnum feeItem) {
        AssertUtils.notEmpty(loanAccountId, "loanAccountId is empty.");
        
        List<OverdueInterestChargeRecord> oirList = queryListByLoanAccountId(loanAccountId,
                Arrays.asList(feeItem));
        if (CollectionUtils.isEmpty(oirList)) {
            return null;
        }
        Collections.sort(oirList, OverdueInterestRecordHelper.overdueInterestRecordComparator);
        return oirList.get(oirList.size() - 1);
    }
    
    /**
      * 根据贷款账户id查询逾期利息记录列表<br/>
      * <功能详细描述>
      * @param loanAccountId
      * @param repayChannelTypes
      * @return [参数说明]
      * 
      * @return List<OverdueInterestRecord> [返回类型说明]
      * @exception throws [异常类型] [异常说明]
      * @see [类、类#方法、类#成员]
     */
    public List<OverdueInterestChargeRecord> queryListByLoanAccountId(String loanAccountId,
            List<FeeItemEnum> feeItems) {
        AssertUtils.notEmpty(loanAccountId, "loanAccountId is empty.");
        
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("loanAccountId", loanAccountId);
        
        List<OverdueInterestChargeRecord> oirList = this.overdueInterestChargeRecordDao
                .queryList(params);
        if (CollectionUtils.isEmpty(feeItems)) {
            return oirList;
        }
        
        List<OverdueInterestChargeRecord> resList = new ArrayList<>();
        for (OverdueInterestChargeRecord oirTemp : oirList) {
            if (feeItems.contains(oirTemp.getFeeItem())) {
                resList.add(oirTemp);
            }
        }
        return resList;
    }
    
    /**
      * 查询OverdueInterestRecord实体列表
      * <功能详细描述>
      *       
      * @return [参数说明]
      * 
      * @return List<OverdueInterestRecord> [返回类型说明]
      * @exception throws [异常类型] [异常说明]
      * @see [类、类#方法、类#成员]
      */
    public List<OverdueInterestChargeRecord> queryListByLoanAccountId(String loanAccountId) {
        //判断条件合法性
        AssertUtils.notEmpty(loanAccountId, "loanAccountId is empty");
        //生成查询条件
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("loanAccountId", loanAccountId);
        
        //根据实际情况，填入排序字段等条件，根据是否需要排序，选择调用dao内方法
        List<OverdueInterestChargeRecord> resList = this.overdueInterestChargeRecordDao
                .queryList(params);
        
        return resList;
    }
    
    /**
     * 判断是否已经存在<br/>
     * <功能详细描述>
     * @return [参数说明]
     * 
     * @return int [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
    */
    public boolean isExist(Map<String, String> key2valueMap, String excludeId) {
        AssertUtils.notEmpty(key2valueMap, "key2valueMap is empty");
        
        //生成查询条件
        Map<String, Object> params = new HashMap<String, Object>();
        params.putAll(key2valueMap);
        params.put("excludeId", excludeId);
        
        //根据实际情况，填入排序字段等条件，根据是否需要排序，选择调用dao内方法
        int res = this.overdueInterestChargeRecordDao.count(params);
        
        return res > 0;
    }
    
    /**
      * 根据id更新对象
      * <功能详细描述>
      * @param overdueInterestRecord
      * @return [参数说明]
      * 
      * @return boolean [返回类型说明]
      * @exception throws [异常类型] [异常说明]
      * @see [类、类#方法、类#成员]
     */
    @Transactional
    public boolean updateById(OverdueInterestChargeRecord overdueInterestRecord) {
        //验证参数是否合法，必填字段是否填写，
        AssertUtils.notNull(overdueInterestRecord, "overdueInterestRecord is null.");
        AssertUtils.notEmpty(overdueInterestRecord.getId(), "overdueInterestRecord.id is empty.");
        
        //生成需要更新字段的hashMap
        Map<String, Object> updateRowMap = new HashMap<String, Object>();
        updateRowMap.put("id", overdueInterestRecord.getId());
        
        //需要更新的字段
        //updateRowMap.put("overdueInterestAmount", overdueInterestRecord.getOverdueInterestAmount());
        int updateRowCount = this.overdueInterestChargeRecordDao.update(updateRowMap);
        
        //如果需要大于1时，抛出异常并回滚，需要在这里修改
        return updateRowCount >= 1;
    }
}
