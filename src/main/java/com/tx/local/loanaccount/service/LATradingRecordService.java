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

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.tx.core.exceptions.util.AssertUtils;
import com.tx.local.loanaccount.dao.LATradingRecordDao;
import com.tx.local.loanaccount.model.LATradingRecord;

/**
 * TradingRecord的业务层
 * <功能详细描述>
 * 
 * @author  
 * @version  [版本号, ]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
@Component("laTradingRecordService")
public class LATradingRecordService {
    
    @SuppressWarnings("unused")
    private Logger logger = LoggerFactory.getLogger(LATradingRecordService.class);
    
    @Resource(name = "laTradingRecordDao")
    private LATradingRecordDao tradingRecordDao;
    
    /**
      * 将tradingRecord实例插入数据库中保存
      * 1、如果tradingRecord为空时抛出参数为空异常
      * 2、如果tradingRecord中部分必要参数为非法值时抛出参数不合法异常
     * <功能详细描述>
     * @param district [参数说明]
     * 
     * @return void [返回类型说明]
     * @exception throws
     * @see [类、类#方法、类#成员]
    */
    @Transactional
    public void insert(LATradingRecord tradingRecord) {
        AssertUtils.notNull(tradingRecord, "tradingRecord is null.");
        
        //设置默认数据
        Date now = new Date();
        tradingRecord.setCreateDate(now);
        tradingRecord.setLastUpdateDate(now);
        
        this.tradingRecordDao.insert(tradingRecord);
    }
    
    /**
     * 
      * 批量保存<br/>
      *<功能详细描述>
      * @param tradingRecords [参数说明]
      * 
      * @return void [返回类型说明]
      * @exception throws [异常类型] [异常说明]
      * @see [类、类#方法、类#成员]
     */
    @Transactional
    public void batchInsert(List<LATradingRecord> tradingRecords) {
        AssertUtils.notTrue(tradingRecords.isEmpty(),
                "tradingRecords is empty.");
        this.tradingRecordDao.batchInsert(tradingRecords);
    }
    
    /**
     * 根据id删除tradingRecord实例
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
        
        LATradingRecord condition = new LATradingRecord();
        condition.setId(id);
        return this.tradingRecordDao.delete(condition);
    }
    
    /**
     * 根据id查询交易记录并附带分项
     * <功能详细描述>
     * @param id
     * @return [参数说明]
     * 
     * @return TradingRecord [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    public LATradingRecord findById(String id) {
        AssertUtils.notEmpty(id, "id is empty.");
        
        LATradingRecord condition = new LATradingRecord();
        condition.setId(id);
        
        LATradingRecord res = this.tradingRecordDao.find(condition);
        return res;
    }
    
    /**
     * 根据Id查询TradingRecord实体
     * 1、当id为empty时抛出异常
     * <功能详细描述>
     * @param id
     * @return [参数说明]
     * 
     * @return TradingRecord [返回类型说明]
     * @exception throws 可能存在数据库访问异常DataAccessException
     * @see [类、类#方法、类#成员]
     */
    public LATradingRecord findTradingRecordById(String id) {
        AssertUtils.notEmpty(id, "id is empty.");
        
        LATradingRecord condition = new LATradingRecord();
        condition.setId(id);
        
        LATradingRecord res = this.tradingRecordDao.find(condition);
        return res;
    }
    
    /**
     * 根据交易组id查询交易集合<br/>
     * <功能详细描述>
     * @param groupId
     * @return [参数说明]
     * 
     * @return List<TradingRecord> [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    public List<LATradingRecord> queryList(Boolean viewAble,
            Boolean revokeAble, Map<String, Object> params) {
        params = params == null ? new HashMap<>() : params;
        params.put("viewAble", viewAble);
        params.put("revokeAble", revokeAble);
        
        List<LATradingRecord> tradingRecords = this.tradingRecordDao.queryList(params);
        return tradingRecords;
    }
    
    /**
     * 根据贷款账户id和交易类型查询交易记录
     * <功能详细描述>
     * @param loanAccountId
     * @param tradingType
     * @return [参数说明]
     * 
     * @return List<TradingRecord> [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
    */
    public List<LATradingRecord> queryListByLoanAccountId(String loanAccountId,
            Boolean viewAble, Boolean revokeAble, Map<String, Object> params) {
        //判断条件合法性
        AssertUtils.notEmpty(loanAccountId, "loanAccountId is empty.");
        
        //生成查询条件
        params = params == null ? new HashMap<>() : params;
        params.put("loanAccountId", loanAccountId);
        params.put("viewAble", viewAble);
        params.put("revokeAble", revokeAble);
        
        //根据实际情况，填入排序字段等条件，根据是否需要排序，选择调用dao内方法
        List<LATradingRecord> resList = this.tradingRecordDao.queryList(params);
        
        return resList;
    }
    
    //    /**
    //      * 根据条件查询贷款单账户中可被撤销的交易记录id<br/>
    //      * <功能详细描述>
    //      * @param loanAccountId
    //      * @param tradingTypes
    //      * @param tradingRecordGroupTypes
    //      * @return [参数说明]
    //      * 
    //      * @return TradingRecord [返回类型说明]
    //      * @exception throws [异常类型] [异常说明]
    //      * @see [类、类#方法、类#成员]
    //     */
    //    public LATradingRecord findLastRevokeAbleByLoanAccountId(String loanAccountId, LATradingCategoryEnum[] tradingTypes,
    //            List<LATradingRecordChannelEnum> tradingRecordGroupTypes) {
    //        AssertUtils.notEmpty(loanAccountId, "loanAccountId is empty.");
    //        AssertUtils.notEmpty(tradingTypes, "tradingTypes is empty.");
    //        
    //        Map<String, Object> params = new HashMap<>();
    //        params.put("loanAccountId", loanAccountId);
    //        params.put("tradingTypes", tradingTypes);
    //        params.put("revoked", false);
    //        params.put("revokeAble", true);
    //        
    //        //利用这样的查询条件，查询出结果后再过滤组的信息，在同一个事务中能够避免多次查询时，利用二级缓存，降低查询频次
    //        List<LATradingRecord> queryList = this.tradingRecordDao.queryList(params);
    //        
    //        List<LATradingRecord> recordList = new ArrayList<>();
    //        for (LATradingRecord tradingRecord : queryList) {
    //            if (!CollectionUtils.isEmpty(tradingRecordGroupTypes)
    //                    && !tradingRecordGroupTypes.contains(tradingRecord.getTradingRecordGroupType())) {
    //                continue;
    //            }
    //            recordList.add(tradingRecord);
    //        }
    //        if (CollectionUtils.isEmpty(recordList)) {
    //            return null;
    //        }
    //        Collections.sort(recordList, OrderComparator.INSTANCE);
    //        return recordList.get(recordList.size() - 1);
    //    }
    //
    //    /**
    //      * 根据条件查询贷款单账户中可被撤销的交易记录id<br/>
    //      *<功能详细描述>
    //      * @param loanAccountId
    //      * @param tradingTypes
    //      * @param excludeTradingRecordTypes
    //      * @param tradingRecordGroupTypes
    //      * @return [参数说明]
    //      * 
    //      * @return TradingRecord [返回类型说明]
    //      * @exception throws [异常类型] [异常说明]
    //      * @see [类、类#方法、类#成员]
    //     */
    //    public LATradingRecord findLastRevokeAbleTradingRecordByLoanAccountId(String loanAccountId,
    //            LATradingCategoryEnum[] tradingTypes, List<LATradingRecordTypeEnum> excludeTradingRecordTypes,
    //            List<LATradingRecordChannelEnum> tradingRecordGroupTypes) {
    //        AssertUtils.notEmpty(loanAccountId, "loanAccountId is empty.");
    //        AssertUtils.notEmpty(tradingTypes, "tradingTypes is empty.");
    //        
    //        Map<String, Object> params = new HashMap<>();
    //        params.put("loanAccountId", loanAccountId);
    //        params.put("tradingTypes", tradingTypes);
    //        params.put("excludeTradingRecordTypes", excludeTradingRecordTypes);
    //        params.put("revoked", false);
    //        params.put("revokeAble", true);
    //        
    //        //利用这样的查询条件，查询出结果后再过滤组的信息，在同一个事务中能够避免多次查询时，利用二级缓存，降低查询频次
    //        List<LATradingRecord> queryList = this.tradingRecordDao.queryList(params);
    //        
    //        List<LATradingRecord> recordList = new ArrayList<>();
    //        for (LATradingRecord tradingRecord : queryList) {
    //            if (!CollectionUtils.isEmpty(tradingRecordGroupTypes)
    //                    && !tradingRecordGroupTypes.contains(tradingRecord.getTradingRecordGroupType())) {
    //                continue;
    //            }
    //            recordList.add(tradingRecord);
    //        }
    //        if (CollectionUtils.isEmpty(recordList)) {
    //            return null;
    //        }
    //        Collections.sort(recordList, OrderComparator.INSTANCE);
    //        return recordList.get(recordList.size() - 1);
    //    }
    //
    //    /**
    //     * 根据条件查询贷款单账户中可被撤销的交易记录id<br/>
    //     * <功能详细描述>
    //     * @param loanAccountId
    //     * @param tradingTypes
    //     * @param tradingRecordGroupTypes
    //     * @return [参数说明]
    //     * 
    //     * @return TradingRecord [返回类型说明]
    //     * @exception throws [异常类型] [异常说明]
    //     * @see [类、类#方法、类#成员]
    //    */
    //    public List<LATradingRecord> queryRevokeAbleTradingRecordByPreTradingRecodId(String tradingRecordId) {
    //        AssertUtils.notEmpty(tradingRecordId, "tradingRecordId is empty.");
    //        
    //        Map<String, Object> params = new HashMap<>();
    //        params.put("preTradingRecordId", tradingRecordId);
    //        params.put("revoked", false);
    //        params.put("revokeAble", true);
    //        
    //        //利用这样的查询条件，查询出结果后再过滤组的信息，在同一个事务中能够避免多次查询时，利用二级缓存，降低查询频次
    //        List<LATradingRecord> resList = this.tradingRecordDao.queryList(params);
    //        
    //        return resList;
    //    }
    //
    //    public List<LATradingRecord> queryRevokeAbleByLoanAccountId(String loanAccountId,
    //            List<LATradingRecordTypeEnum> tradingRecordTypes,
    //            List<LATradingRecordChannelEnum> tradingRecordGroupTypes) {
    //        AssertUtils.notEmpty(loanAccountId, "loanAccountId is empty.");
    //        AssertUtils.notEmpty(tradingRecordTypes, "tradingRecordTypes is empty.");
    //        
    //        Map<String, Object> params = new HashMap<>();
    //        params.put("loanAccountId", loanAccountId);
    //        params.put("tradingRecordTypes", tradingRecordTypes);
    //        params.put("revoked", false);
    //        params.put("revokeAble", true);
    //        
    //        //利用这样的查询条件，查询出结果后再过滤组的信息，在同一个事务中能够避免多次查询时，利用二级缓存，降低查询频次
    //        List<LATradingRecord> queryList = this.tradingRecordDao.queryList(params);
    //        
    //        //可撤销交易记录集合
    //        List<LATradingRecord> recordList = new ArrayList<>(TxConstants.INITIAL_CONLLECTION_SIZE);
    //        for (LATradingRecord tradingRecord : queryList) {
    //            if (!CollectionUtils.isEmpty(tradingRecordGroupTypes)
    //                    && !tradingRecordGroupTypes.contains(tradingRecord.getTradingRecordGroupType())) {
    //                continue;
    //            }
    //            recordList.add(tradingRecord);
    //        }
    //        Collections.sort(recordList, OrderComparator.INSTANCE);
    //        return recordList;
    //    }
    //
    //    /**
    //     * 根据条件查询贷款单账户中可被撤销的交易记录id<br/>
    //     * <功能详细描述>
    //     * @param loanAccountId
    //     * @param tradingTypes
    //     * @param tradingRecordGroupTypes
    //     * @return [参数说明]
    //     * 
    //     * @return TradingRecord [返回类型说明]
    //     * @exception throws [异常类型] [异常说明]
    //     * @see [类、类#方法、类#成员]
    //    */
    //    public List<LATradingRecord> queryRevokeAbleByLoanAccountId(String loanAccountId,
    //            List<LATradingCategoryEnum> tradingTypes, List<LATradingRecordTypeEnum> excludeTradingRecordTypes) {
    //        AssertUtils.notEmpty(loanAccountId, "loanAccountId is empty.");
    //        AssertUtils.notEmpty(tradingTypes, "tradingTypes is empty.");
    //        
    //        Map<String, Object> params = new HashMap<>();
    //        params.put("loanAccountId", loanAccountId);
    //        params.put("tradingTypes", tradingTypes);
    //        params.put("excludeTradingRecordTypes", excludeTradingRecordTypes);
    //        params.put("revoked", false);
    //        params.put("revokeAble", true);
    //        
    //        //利用这样的查询条件，查询出结果后再过滤组的信息，在同一个事务中能够避免多次查询时，利用二级缓存，降低查询频次
    //        List<LATradingRecord> queryList = this.tradingRecordDao.queryList(params);
    //        
    //        //可撤销交易记录集合
    //        List<LATradingRecord> recordList = new ArrayList<>(TxConstants.INITIAL_CONLLECTION_SIZE);
    //        for (LATradingRecord tradingRecord : queryList) {
    //            //            if (!CollectionUtils.isEmpty(tradingRecordGroupTypes)
    //            //                    && !tradingRecordGroupTypes.contains(tradingRecord.getTradingRecordGroupType())) {
    //            //                continue;
    //            //            }
    //            recordList.add(tradingRecord);
    //        }
    //        Collections.sort(recordList, OrderComparator.INSTANCE);
    //        return recordList;
    //    }
    //
    //    /**
    //      * 根据条件查询贷款单账户中可被撤销的交易记录id<br/>
    //      *<功能详细描述>
    //      * @param loanAccountId
    //      * @param tradingTypes
    //      * @param excludeTradingRecordTypes
    //      * @param tradingRecordGroupTypes
    //      * @param minRepayDate
    //      * @param maxRepayDate
    //      * @return [参数说明]
    //      * 
    //      * @return List<TradingRecord> [返回类型说明]
    //      * @exception throws [异常类型] [异常说明]
    //      * @see [类、类#方法、类#成员]
    //     */
    //    public List<LATradingRecord> queryRevokeAbleByLoanAccountId(String loanAccountId,
    //            List<LATradingCategoryEnum> tradingTypes, List<LATradingRecordTypeEnum> excludeTradingRecordTypes,
    //            Date minProcessDate, Date maxProcessDate) {
    //        AssertUtils.notEmpty(loanAccountId, "loanAccountId is empty.");
    //        AssertUtils.notEmpty(tradingTypes, "tradingTypes is empty.");
    //        
    //        Map<String, Object> params = new HashMap<>();
    //        params.put("loanAccountId", loanAccountId);
    //        params.put("tradingTypes", tradingTypes);
    //        params.put("excludeTradingRecordTypes", excludeTradingRecordTypes);
    //        params.put("revoked", false);
    //        params.put("revokeAble", true);
    //        params.put("minProcessDate", minProcessDate);
    //        params.put("maxProcessDate", maxProcessDate);
    //        
    //        //利用这样的查询条件，查询出结果后再过滤组的信息，在同一个事务中能够避免多次查询时，利用二级缓存，降低查询频次
    //        List<LATradingRecord> queryList = this.tradingRecordDao.queryList(params);
    //        
    //        //可撤销交易记录集合
    //        List<LATradingRecord> recordList = new ArrayList<>(TxConstants.INITIAL_CONLLECTION_SIZE);
    //        for (LATradingRecord tradingRecord : queryList) {
    //            //            if (!CollectionUtils.isEmpty(tradingRecordGroupTypes)) {
    //            //                //&& !tradingRecordGroupTypes.contains(tradingRecord.getTradingRecordGroupType())
    //            //                continue;
    //            //            }
    //            recordList.add(tradingRecord);
    //        }
    //        Collections.sort(recordList, OrderComparator.INSTANCE);
    //        return recordList;
    //    }
    //
    //    /**
    //      * 根据交易id查询该交易的下一条或多条交易<br/>
    //      * <功能详细描述>
    //      * @param tradingRecordId
    //      * @return [参数说明]
    //      * 
    //      * @return List<TradingRecord> [返回类型说明]
    //      * @exception throws [异常类型] [异常说明]
    //      * @see [类、类#方法、类#成员]
    //     */
    //    public List<LATradingRecord> queryTradingRecordListByPreTradingRecordId(String loanAccountId,
    //            String tradingRecordId) {
    //        //判断条件合法性
    //        AssertUtils.notEmpty(loanAccountId, "loanAccountId is empty.");
    //        AssertUtils.notEmpty(tradingRecordId, "tradingRecordId is empty.");
    //        
    //        //生成查询条件
    //        Map<String, Object> params = new HashMap<String, Object>();
    //        params.put("loanAccountId", loanAccountId);
    //        params.put("preTradingRecordId", tradingRecordId);
    //        
    //        //根据实际情况，填入排序字段等条件，根据是否需要排序，选择调用dao内方法
    //        List<LATradingRecord> resList = this.tradingRecordDao.queryList(params);
    //        
    //        return resList;
    //    }
    //    
    //    /**
    //      * 查询贷款账户的未到账交易<br/>
    //      *<功能详细描述>
    //      * @param loanAccountId
    //      * @return [参数说明]
    //      * 
    //      * @return List<TradingRecord> [返回类型说明]
    //      * @exception throws [异常类型] [异常说明]
    //      * @see [类、类#方法、类#成员]
    //     */
    //    public List<LATradingRecord> queryNotExistReceivedTradingByLoanAccountId(String loanAccountId) {
    //        //判断条件合法性
    //        AssertUtils.notEmpty(loanAccountId, "loanAccountId is empty.");
    //        
    //        //生成查询条件
    //        Map<String, Object> params = new HashMap<String, Object>();
    //        params.put("loanAccountId", loanAccountId);
    //        params.put("received", false);
    //        params.put("revoked", false);
    //        //params.put("tradingType", LATradingCategoryEnum.还款);
    //        
    //        //根据实际情况，填入排序字段等条件，根据是否需要排序，选择调用dao内方法
    //        List<LATradingRecord> resList = this.tradingRecordDao.queryList(params);
    //        
    //        return resList;
    //    }
    
    /**
      * 撤销指定交易<br/>
      * <功能详细描述>
      * @param tradingRecordId
      * @param revokeReason [参数说明]
      * 
      * @return void [返回类型说明]
      * @exception throws [异常类型] [异常说明]
      * @see [类、类#方法、类#成员]
     */
    @Transactional
    public void updateWhenRevoke(LATradingRecord tradingRecord) {
        AssertUtils.notNull(tradingRecord, "tradingRecord is null.");
        AssertUtils.notEmpty(tradingRecord.getId(),
                "tradingRecord.id is empty.");
        
        //查询交易记录
        Date now = new Date();
        Map<String, Object> updateRowMap = new HashMap<String, Object>();
        updateRowMap.put("id", tradingRecord.getId());
        
        updateRowMap.put("lastUpdateDate", now);
        updateRowMap.put("lastUpdateOperatorId", tradingRecord.getOperatorId());
        updateRowMap.put("revoked", true);
        updateRowMap.put("revokeAble", false);
        
        updateRowMap.put("viewAble", tradingRecord.isViewAble());
        updateRowMap.put("revokeResean", tradingRecord.getRevokeResean());
        updateRowMap.put("revokeDate", tradingRecord.getRevokeDate());
        updateRowMap.put("revokeOperatorId",
                tradingRecord.getRevokeOperatorId());
        updateRowMap.put("remark", tradingRecord.getRemark());
        
        this.tradingRecordDao.update(updateRowMap);
    }
    
    /**
     * 撤销指定交易<br/>
     * <功能详细描述>
     * @param tradingRecordId
     * @param revokeReason [参数说明]
     * 
     * @return void [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
    */
    @Transactional
    public void updateWhenReceived(LATradingRecord tradingRecord) {
        AssertUtils.notNull(tradingRecord, "tradingRecord is null.");
        AssertUtils.notEmpty(tradingRecord.getId(),
                "tradingRecord.id is empty.");
        
        //查询交易记录
        Date now = new Date();
        tradingRecord.setReceived(true);
        tradingRecord.setReceiveDate(now);
        tradingRecord.setLastUpdateDate(now);
        tradingRecord.setLastUpdateOperatorId(tradingRecord.getOperatorId());
        
        Map<String, Object> updateRowMap = new HashMap<String, Object>();
        updateRowMap.put("id", tradingRecord.getId());
        
        updateRowMap.put("lastUpdateDate", now);
        updateRowMap.put("lastUpdateOperatorId", tradingRecord.getOperatorId());
        
        updateRowMap.put("received", true);
        updateRowMap.put("receiveDate", now);
        
        updateRowMap.put("remark", tradingRecord.getRemark());
        
        this.tradingRecordDao.update(updateRowMap);
    }
    
    /**
      * 根据id更新对象
      * <功能详细描述>
      * @param tradingRecord
      * @return [参数说明]
      * 
      * @return boolean [返回类型说明]
      * @exception throws [异常类型] [异常说明]
      * @see [类、类#方法、类#成员]
     */
    @Transactional
    protected boolean updateById(LATradingRecord tradingRecord) {
        //验证参数是否合法，必填字段是否填写，
        AssertUtils.notNull(tradingRecord, "tradingRecord is null.");
        AssertUtils.notEmpty(tradingRecord.getId(),
                "tradingRecord.id is empty.");
        
        //生成需要更新字段的hashMap
        Map<String, Object> updateRowMap = new HashMap<String, Object>();
        updateRowMap.put("id", tradingRecord.getId());
        
        //需要更新的字段
        //updateRowMap.put("category", tradingRecord.getCategory());
        //updateRowMap.put("type", tradingRecord.getType());
        //updateRowMap.put("sum", tradingRecord.getSum());
        //updateRowMap.put("organizationId", tradingRecord.getOrganizationId());
        //updateRowMap.put("bankAccountId", tradingRecord.getBankAccountId());
        
        updateRowMap.put("lastUpdateDate", tradingRecord.getLastUpdateDate());
        updateRowMap.put("lastUpdateOperatorId",
                tradingRecord.getLastUpdateOperatorId());
        
        updateRowMap.put("remark", tradingRecord.getRemark());
        
        updateRowMap.put("received", tradingRecord.isReceived());
        updateRowMap.put("receiveDate", tradingRecord.getReceiveDate());
        
        updateRowMap.put("revoked", tradingRecord.isRevoked());
        updateRowMap.put("revokeOperatorId",
                tradingRecord.getRevokeOperatorId());
        updateRowMap.put("revokeResean", tradingRecord.getRevokeResean());
        updateRowMap.put("revokeDate", tradingRecord.getRevokeDate());
        
        updateRowMap.put("beforeAccountStatus",
                tradingRecord.getBeforeAccountStatus());
        updateRowMap.put("beforeCollectionStatus",
                tradingRecord.getBeforeCollectionStatus());
        updateRowMap.put("beforeSettleInterestStatus",
                tradingRecord.getBeforeSettleInterestStatus());
        updateRowMap.put("beforeIsDie", tradingRecord.isBeforeIsDie());
        updateRowMap.put("beforeIsLegalProcedure",
                tradingRecord.isBeforeIsLegalProcedure());
        updateRowMap.put("beforeIsClose", tradingRecord.isBeforeIsClose());
        
        updateRowMap.put("afterAccountStatus",
                tradingRecord.getAfterAccountStatus());
        updateRowMap.put("afterCollectionStatus",
                tradingRecord.getAfterCollectionStatus());
        updateRowMap.put("afterSettleInterestStatus",
                tradingRecord.getAfterSettleInterestStatus());
        updateRowMap.put("afterIsDie", tradingRecord.isAfterIsDie());
        updateRowMap.put("afterIsLegalProcedure",
                tradingRecord.isAfterIsLegalProcedure());
        updateRowMap.put("afterIsClose", tradingRecord.isAfterIsClose());
        
        updateRowMap.put("beforePrincipalBalance",
                tradingRecord.getBeforePrincipalBalance());
        updateRowMap.put("beforePrincipalBalanceIrr",
                tradingRecord.getBeforePrincipalBalanceIrr());
        updateRowMap.put("afterPrincipalBalance",
                tradingRecord.getAfterPrincipalBalance());
        updateRowMap.put("afterPrincipalBalanceIrr",
                tradingRecord.getAfterPrincipalBalanceIrr());
        
        int updateRowCount = this.tradingRecordDao.update(updateRowMap);
        
        //如果需要大于1时，抛出异常并回滚，需要在这里修改
        return updateRowCount >= 1;
    }
    
}
