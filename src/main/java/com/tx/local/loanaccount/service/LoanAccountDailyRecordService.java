/*
 * 描          述:  <描述>
 * 修  改   人:  
 * 修改时间:  
 * <修改描述:>
 */
package com.tx.local.loanaccount.service;

import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.joda.time.LocalDate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.tx.core.exceptions.util.AssertUtils;
import com.tx.core.paged.model.PagedList;
import com.tx.local.loanaccount.dao.LoanAccountDailyRecordDao;
import com.tx.local.loanaccount.model.LoanAccount;
import com.tx.local.loanaccount.model.LoanAccountDailyRecord;
import com.tx.local.loanaccount.model.LoanAccountDailyRecordTypeEnum;

/**
 * LoanAccountDailyRecord的业务层
 * <功能详细描述>
 * 
 * @author  
 * @version  [版本号, ]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
@Component("loanAccountDailyRecordService")
public class LoanAccountDailyRecordService {
    
    @SuppressWarnings("unused")
    private Logger logger = LoggerFactory.getLogger(LoanAccountDailyRecordService.class);
    
    @Resource(name = "loanAccountDailyRecordDao")
    private LoanAccountDailyRecordDao loanAccountDailyRecordDao;
    
    /** dailyRecor默认比较器 */
    private Comparator<LoanAccountDailyRecord> drComparator = new Comparator<LoanAccountDailyRecord>() {
        @Override
        public int compare(LoanAccountDailyRecord o1, LoanAccountDailyRecord o2) {
            if (o1 == o2) {
                return 0;
            }
            if ((o1 == null) || (o2 == null)) {
                return (o1 == null) ? -1 : 1;
            }
            int res = o1.getRecordDate().compareTo(o2.getRecordDate());
            return res;
        }
    };
    
    /**
      * 判断两个记录之间是否有变化<br/>
      * <功能详细描述>
      * @param source
      * @param target
      * @return [参数说明]
      * 
      * @return boolean [返回类型说明]
      * @exception throws [异常类型] [异常说明]
      * @see [类、类#方法、类#成员]
     */
    public boolean isEquals(LoanAccountDailyRecord source, LoanAccountDailyRecord target) {
        if (source == null) {
            return false;
        }
        if (!source.getType().equals(target.getType())) {
            return false;
        }
        if (!source.getLoanAccountId().equals(target.getLoanAccountId())) {
            return false;
        }
        if (!source.getAccountStatus().equals(target.getAccountStatus())) {
            return false;
        }
        if (!source.getCollectionStatus().equals(target.getCollectionStatus())) {
            return false;
        }
        if (!source.getSettleInterestStatus().equals(target.getSettleInterestStatus())) {
            return false;
        }
        if (!org.apache.commons.lang3.time.DateUtils.isSameDay(source.getOverdueDate(), target.getOverdueDate())) {
            return false;
        }
        if (!source.isOverdue() == target.isOverdue()) {
            return false;
        }
        if (!source.isDied() == target.isDied()) {
            return false;
        }
        if (!source.isLegalProcedure() == target.isLegalProcedure()) {
            return false;
        }
        if (!source.isClosed() == target.isClosed()) {
            return false;
        }
        if (source.getPrincipalBalance().compareTo(target.getPrincipalBalance()) != 0) {
            return false;
        }
        if (source.getPrincipalBalanceIrr().compareTo(target.getPrincipalBalanceIrr()) != 0) {
            return false;
        }
        if (source.getOverdueAmount().compareTo(target.getOverdueAmount()) != 0) {
            return false;
        }
        if (source.getOverdueSum().compareTo(target.getOverdueSum()) != 0) {
            return false;
        }
        return true;
    }
    
    /**
      * 根据贷款账户构建贷款账户日记录<br/>
      * <功能详细描述>
      * @param loanAccount
      * @param type
      * @param recordDate
      * @param createDate
      * @return [参数说明]
      * 
      * @return LoanAccountDailyRecord [返回类型说明]
      * @exception throws [异常类型] [异常说明]
      * @see [类、类#方法、类#成员]
     */
    public LoanAccountDailyRecord build4insert(LoanAccount loanAccount, LoanAccountDailyRecordTypeEnum type,
            LocalDate recordDate) {
        LoanAccountDailyRecord ladr = new LoanAccountDailyRecord();
        
        ladr.setRecordDate(recordDate.toDate());
        ladr.setCreateDate(new Date());
        ladr.setType(type);
        
        ladr.setLoanAccountId(loanAccount.getId());
        ladr.setClientId(loanAccount.getClientId());
        
        ladr.setAccountStatus(loanAccount.getAccountStatus());
        ladr.setCollectionStatus(loanAccount.getCollectionStatus());
        ladr.setSettleInterestStatus(loanAccount.getSettleInterestStatus());
        
        ladr.setOverdue(loanAccount.isOverdue());
        ladr.setClosed(loanAccount.isClosed());
        ladr.setDied(loanAccount.isDied());
        ladr.setLegalProcedure(loanAccount.isLegalProcedure());
        
        ladr.setPrincipalBalance(loanAccount.getPrincipalBalance());
        ladr.setPrincipalBalanceIrr(loanAccount.getPrincipalBalanceIrr());
        ladr.setOverdueAmount(loanAccount.getOverdueAmount());
        ladr.setOverdueSum(loanAccount.getOverdueSum());
        ladr.setOverdueDate(loanAccount.getOverdueDate());
        
        return ladr;
    }
    
    /**
      * 批量插入贷款账户记录<br/>
      * <功能详细描述>
      * @param dailyRecordList [参数说明]
      * 
      * @return void [返回类型说明]
      * @exception throws [异常类型] [异常说明]
      * @see [类、类#方法、类#成员]
     */
    @Transactional
    public void batchInsert(List<LoanAccountDailyRecord> dailyRecordList) {
        if (CollectionUtils.isEmpty(dailyRecordList)) {
            return;
        }
        this.loanAccountDailyRecordDao.batchInsert(dailyRecordList);
    }
    
    /**
      * 将loanAccountDailyRecord实例插入数据库中保存
      * 1、如果loanAccountDailyRecord为空时抛出参数为空异常
      * 2、如果loanAccountDailyRecord中部分必要参数为非法值时抛出参数不合法异常
     * <功能详细描述>
     * @param district [参数说明]
     * 
     * @return void [返回类型说明]
     * @exception throws
     * @see [类、类#方法、类#成员]
    */
    @Transactional
    public void insert(LoanAccountDailyRecord loanAccountDailyRecord) {
        AssertUtils.notNull(loanAccountDailyRecord, "loanAccountDailyRecord is null.");
        AssertUtils.notEmpty(loanAccountDailyRecord.getLoanAccountId(),
                "loanAccountDailyRecord.loanAccountId is empty.");
        AssertUtils.notEmpty(loanAccountDailyRecord.getClientId(), "loanAccountDailyRecord.clientId is empty.");
        AssertUtils.notNull(loanAccountDailyRecord.getAccountStatus(), "loanAccountDailyRecord.accountStatus is null.");
        AssertUtils.notNull(loanAccountDailyRecord.getCollectionStatus(),
                "loanAccountDailyRecord.collectionStatus is null.");
        AssertUtils.notNull(loanAccountDailyRecord.getSettleInterestStatus(),
                "loanAccountDailyRecord.settleInterestStatus is null.");
        AssertUtils.notNull(loanAccountDailyRecord.getRecordDate(), "loanAccountDailyRecord.recordDate is null.");
        
        if (loanAccountDailyRecord.getCreateDate() == null) {
            loanAccountDailyRecord.setCreateDate(new Date());
        }
        
        this.loanAccountDailyRecordDao.insert(loanAccountDailyRecord);
    }
    
    /**
     * 根据id删除loanAccountDailyRecord实例
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
        
        LoanAccountDailyRecord condition = new LoanAccountDailyRecord();
        condition.setId(id);
        return this.loanAccountDailyRecordDao.delete(condition);
    }
    
    /**
      * 根据贷款账户
      * <功能详细描述>
      * @param loanAccountId
      * @return [参数说明]
      * 
      * @return LoanAccountDailyRecord [返回类型说明]
      * @exception throws [异常类型] [异常说明]
      * @see [类、类#方法、类#成员]
     */
    public LoanAccountDailyRecord findLastByLoanAccountId(String loanAccountId, LoanAccountDailyRecordTypeEnum type,
            Date maxRecordDate) {
        AssertUtils.notEmpty(loanAccountId, "loanAccountId is empty.");
        AssertUtils.notNull(maxRecordDate, "maxRecordDate is empty.");
        AssertUtils.notNull(type, "type is empty.");
        
        List<LoanAccountDailyRecord> drList = queryListByLoanAccountId(loanAccountId, type, maxRecordDate);
        if (CollectionUtils.isEmpty(drList)) {
            return null;
        }
        Collections.sort(drList, drComparator);
        LoanAccountDailyRecord drTemp = drList.get(drList.size() - 1);
        return drTemp;
    }
    
    /**
      * 根据Id查询LoanAccountDailyRecord实体
      * 1、当id为empty时抛出异常
      * <功能详细描述>
      * @param id
      * @return [参数说明]
      * 
      * @return LoanAccountDailyRecord [返回类型说明]
      * @exception throws 可能存在数据库访问异常DataAccessException
      * @see [类、类#方法、类#成员]
     */
    public LoanAccountDailyRecord findById(String id) {
        AssertUtils.notEmpty(id, "id is empty.");
        
        LoanAccountDailyRecord condition = new LoanAccountDailyRecord();
        condition.setId(id);
        
        LoanAccountDailyRecord res = this.loanAccountDailyRecordDao.find(condition);
        return res;
    }
    
    /**
      * 根据贷款账户id查询贷款账户日记录<br/>
      * <功能详细描述>
      * @param loanAccountId
      * @param maxRecordDate
      * @return [参数说明]
      * 
      * @return List<LoanAccountDailyRecord> [返回类型说明]
      * @exception throws [异常类型] [异常说明]
      * @see [类、类#方法、类#成员]
     */
    public List<LoanAccountDailyRecord> queryListByLoanAccountId(String loanAccountId,
            LoanAccountDailyRecordTypeEnum type, Date maxRecordDate) {
        AssertUtils.notEmpty(loanAccountId, "loanAccountId is empty.");
        AssertUtils.notNull(maxRecordDate, "maxRecordDate is empty.");
        AssertUtils.notNull(type, "type is empty.");
        
        //生成查询条件
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("loanAccountId", loanAccountId);
        params.put("type", type);
        params.put("maxRecordDate", maxRecordDate);
        
        //根据实际情况，填入排序字段等条件，根据是否需要排序，选择调用dao内方法
        List<LoanAccountDailyRecord> resList = this.loanAccountDailyRecordDao.queryList(params);
        return resList;
    }
    
    /**
     * 根据LoanAccountDailyRecord实体列表
     * <功能详细描述>
     * @return [参数说明]
     * 
     * @return List<LoanAccountDailyRecord> [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
    */
    public List<LoanAccountDailyRecord> queryList(Map<String, Object> params) {
        params = params == null ? new HashMap<String, Object>() : params;
        
        //根据实际情况，填入排序字段等条件，根据是否需要排序，选择调用dao内方法
        List<LoanAccountDailyRecord> resList = this.loanAccountDailyRecordDao.queryList(params);
        
        return resList;
    }
    
    /**
    * 分页查询LoanAccountDailyRecord实体列表
    * <功能详细描述>
    * @return [参数说明]
    * 
    * @return List<LoanAccountDailyRecord> [返回类型说明]
    * @exception throws [异常类型] [异常说明]
    * @see [类、类#方法、类#成员]
    */
    public PagedList<LoanAccountDailyRecord> queryPagedList(Map<String, Object> params, int pageIndex, int pageSize) {
        params = params == null ? new HashMap<String, Object>() : params;
        
        //根据实际情况，填入排序字段等条件，根据是否需要排序，选择调用dao内方法
        PagedList<LoanAccountDailyRecord> resPagedList = this.loanAccountDailyRecordDao.queryPagedList(params,
                pageIndex,
                pageSize);
        
        return resPagedList;
    }
    
    /**
     * 查询loanAccountDailyRecord列表总条数
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
        int res = this.loanAccountDailyRecordDao.count(params);
        
        return res;
    }
    
    /**
      * 根据id更新对象
      * <功能详细描述>
      * @param loanAccountDailyRecord
      * @return [参数说明]
      * 
      * @return boolean [返回类型说明]
      * @exception throws [异常类型] [异常说明]
      * @see [类、类#方法、类#成员]
     */
    @Transactional
    public boolean updateById(LoanAccountDailyRecord loanAccountDailyRecord) {
        //验证参数是否合法，必填字段是否填写，
        AssertUtils.notNull(loanAccountDailyRecord, "loanAccountDailyRecord is null.");
        AssertUtils.notEmpty(loanAccountDailyRecord.getId(), "loanAccountDailyRecord.id is empty.");
        
        //生成需要更新字段的hashMap
        Map<String, Object> updateRowMap = new HashMap<String, Object>();
        updateRowMap.put("id", loanAccountDailyRecord.getId());
        
        //需要更新的字段
        updateRowMap.put("settleInterestStatus", loanAccountDailyRecord.getSettleInterestStatus());
        updateRowMap.put("principalBalanceIrr", loanAccountDailyRecord.getPrincipalBalanceIrr());
        updateRowMap.put("overdueDate", loanAccountDailyRecord.getOverdueDate());
        updateRowMap.put("type", loanAccountDailyRecord.getType());
        updateRowMap.put("clientId", loanAccountDailyRecord.getClientId());
        updateRowMap.put("principalBalance", loanAccountDailyRecord.getPrincipalBalance());
        updateRowMap.put("closed", loanAccountDailyRecord.isClosed());
        updateRowMap.put("legalProcedure", loanAccountDailyRecord.isLegalProcedure());
        updateRowMap.put("overdue", loanAccountDailyRecord.isOverdue());
        updateRowMap.put("accountStatus", loanAccountDailyRecord.getAccountStatus());
        updateRowMap.put("recordDate", loanAccountDailyRecord.getRecordDate());
        updateRowMap.put("died", loanAccountDailyRecord.isDied());
        updateRowMap.put("loanAccountId", loanAccountDailyRecord.getLoanAccountId());
        updateRowMap.put("overdueAmount", loanAccountDailyRecord.getOverdueAmount());
        updateRowMap.put("collectionStatus", loanAccountDailyRecord.getCollectionStatus());
        
        int updateRowCount = this.loanAccountDailyRecordDao.update(updateRowMap);
        
        //如果需要大于1时，抛出异常并回滚，需要在这里修改
        return updateRowCount >= 1;
    }
}
