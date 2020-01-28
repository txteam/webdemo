/*
 * 描          述:  <描述>
 * 修  改   人:  
 * 修改时间:  
 * <修改描述:>
 */
package com.tx.local.loanaccount.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.tx.core.exceptions.util.AssertUtils;
import com.tx.core.paged.model.PagedList;
import com.tx.local.loanaccount.dao.LoanAccountDao;
import com.tx.local.loanaccount.model.LoanAccount;

/**
 * LoanAccount的业务层
 * <功能详细描述>
 * 
 * @author  
 * @version  [版本号, ]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
@Component("loanAccountService")
public class LoanAccountService {
    
    @Resource(name = "loanAccountDao")
    private LoanAccountDao loanAccountDao;
    
    @Resource(name = "paymentScheduleService")
    private PaymentScheduleService paymentScheduleService;
    
    @Resource(name = "loanAccountFeeItemService")
    private LoanAccountFeeItemService loanAccountFeeItemService;
    
    @Resource(name = "paymentScheduleEntryService")
    private PaymentScheduleEntryService paymentScheduleEntryService;
    
    @Resource(name = "overdueInterestChargeRecordService")
    private OverdueInterestChargeRecordService overdueInterestRecordService;
    
    //  @Resource(name = "tradingRecordService")
    //    private LATradingRecordService tradingRecordService;
    
    /**
      * 将loanAccount实例插入数据库中保存
      * 1、如果loanAccount为空时抛出参数为空异常
      * 2、如果loanAccount中部分必要参数为非法值时抛出参数不合法异常
     * <功能详细描述>
     * @param district [参数说明]
     * 
     * @return void [返回类型说明]
     * @exception throws
     * @see [类、类#方法、类#成员]
    */
    @Transactional
    public void insert(LoanAccount loanAccount) {
        AssertUtils.notNull(loanAccount, "loanAccount is null.");
        AssertUtils.notNull(loanAccount.getCategory(),
                "loanAccount.category is null.");
        AssertUtils.notNull(loanAccount.getLoanAccountType(),
                "loanAccount.loanAccountType is null.");
        
        AssertUtils.notNull(loanAccount.getLoanAmount(),
                "loanAccount.loanAmount is null.");
        AssertUtils.notNull(loanAccount.getRepayWay(),
                "loanAccount.repayWay is null.");
        AssertUtils.notNull(loanAccount.getTimeUnitType(),
                "loanAccount.timeUnitType is null.");
        
        //        AssertUtils.notEmpty(loanAccount.getClientId(),
        //                "loanAccount.clientId is empty.");
        AssertUtils.notNull(loanAccount.getClientIdCardType(),
                "loanAccount.idCardType is null.");
        AssertUtils.notEmpty(loanAccount.getClientIdCardNumber(),
                "loanAccount.idCardNumber is empty.");
        
        AssertUtils.notNull(loanAccount.getAccountStatus(),
                "loanAccount.accountStatus is null.");
        AssertUtils.notNull(loanAccount.getCollectionStatus(),
                "loanAccount.collectionStatus is null.");
        AssertUtils.notNull(loanAccount.getSettleInterestStatus(),
                "loanAccount.settleInterestStatus is null.");
        
        this.loanAccountDao.insert(loanAccount);
    }
    
    /**
     * 根据id删除loanAccount实例
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
        
        LoanAccount condition = new LoanAccount();
        condition.setId(id);
        return this.loanAccountDao.delete(condition);
    }
    
    /**
     * 根据Id查询LoanAccount实体
     * 1、当id为empty时抛出异常
     * <功能详细描述>
     * @param id
     * @return [参数说明]
     * 
     * @return LoanAccount [返回类型说明]
     * @exception throws 可能存在数据库访问异常DataAccessException
     * @see [类、类#方法、类#成员]
    */
    public LoanAccount findById(String id) {
        AssertUtils.notEmpty(id, "id is empty.");
        
        LoanAccount condition = new LoanAccount();
        condition.setId(id);
        
        LoanAccount res = this.loanAccountDao.find(condition);
        return res;
    }
    
    /**
      * 
      *<功能简述>
      *<功能详细描述>
      * @param id
      * @return [参数说明]
      * 
      * @return LoanAccount [返回类型说明]
      * @exception throws [异常类型] [异常说明]
      * @see [类、类#方法、类#成员]
     */
    public LoanAccount findByContractNumber(String contractNumber) {
        AssertUtils.notEmpty(contractNumber, "contractNumber is empty.");
        LoanAccount condition = new LoanAccount();
        condition.setContractNumber(contractNumber);
        LoanAccount res = this.loanAccountDao.find(condition);
        return res;
    }
    
    /**
     * 根据LoanAccount实体列表
     * <功能详细描述>
     * @return [参数说明]
     * 
     * @return List<LoanAccount> [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
    */
    public List<LoanAccount> queryList(Map<String, Object> params) {
        params = params == null ? new HashMap<String, Object>() : params;
        
        //根据实际情况，填入排序字段等条件，根据是否需要排序，选择调用dao内方法
        List<LoanAccount> resList = this.loanAccountDao.queryList(params);
        
        return resList;
    }
    
    /**
     * 
     *<功能简述>
     *<功能详细描述>
     * @param clientIdCardNumber
     * @return [参数说明]
     * 
     * @return List<LoanAccount> [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
    */
   public List<LoanAccount> queryListByClientIdCardNumber(String clientIdCardNumber) {
       if (StringUtils.isEmpty(clientIdCardNumber)) {
           return new ArrayList<>();
       }
       Map<String, Object> params = new HashMap<String, Object>();
       params.put("clientIdCardNumber", clientIdCardNumber);
       List<LoanAccount> resList = this.loanAccountDao.queryList(params);
       return resList;
   }
    
    /**
      * 根据主查询编号查询贷款账户列表<br/>
      * <功能详细描述>
      * @param number
      * @return [参数说明]
      * 
      * @return List<LoanAccount> [返回类型说明]
      * @exception throws [异常类型] [异常说明]
      * @see [类、类#方法、类#成员]
     */
    public List<LoanAccount> queryListByNumber(String number) {
        if (StringUtils.isEmpty(number)) {
            return new ArrayList<>();
        }
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("number", number);
        List<LoanAccount> resList = this.loanAccountDao.queryList(params);
        return resList;
    }
    
    /**
     * 分页查询LoanAccount实体列表
     * 
     * <功能详细描述>
     * @return [参数说明]
     * 
     * @return List<LoanAccount> [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
    */
    public PagedList<LoanAccount> queryPagedList(Map<String, Object> params,
            int pageIndex, int pageSize) {
        params = params == null ? new HashMap<String, Object>() : params;
        
        //根据实际情况，填入排序字段等条件，根据是否需要排序，选择调用dao内方法
        PagedList<LoanAccount> resPagedList = this.loanAccountDao
                .queryPagedList(params, pageIndex, pageSize);
        
        return resPagedList;
    }
    
    /**
     * 查询loanAccount列表总条数
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
        int res = this.loanAccountDao.count(params);
        
        return res;
    }
    
    //    /**
    //      * 更新下次逾期检查日 
    //      * <功能详细描述>
    //      * @param loanAccountId
    //      * @param nextOverdueCheckDate [参数说明]
    //      * 
    //      * @return void [返回类型说明]
    //      * @exception throws [异常类型] [异常说明]
    //      * @see [类、类#方法、类#成员]
    //     */
    //    @Transactional
    //    public void updateNextOverdueCheckDate(String loanAccountId, Date nextOverdueCheckDate) {
    //        AssertUtils.notEmpty(loanAccountId, "loanAccountId is empty.");
    //        AssertUtils.notNull(nextOverdueCheckDate, "nextOverdueCheckDate is empty.");
    //        
    //        Date now = new Date();
    //        Map<String, Object> updateRowMap = new HashMap<String, Object>(TxConstants.INITIAL_MAP_SIZE);
    //        updateRowMap.put("id", loanAccountId);
    //        
    //        updateRowMap.put("lastUpdateDate", now);
    //        updateRowMap.put("nextOverdueCheckDate", nextOverdueCheckDate);
    //        
    //        this.loanAccountDao.update(updateRowMap);
    //    }
    //    /**
    //     * 更新下次逾期检查日 
    //     * <功能详细描述>
    //     * @param loanAccountId
    //     * @param nextSettleInterestDate [参数说明]
    //     * 
    //     * @return void [返回类型说明]
    //     * @exception throws [异常类型] [异常说明]
    //     * @see [类、类#方法、类#成员]
    //    */
    //    @Transactional
    //    public void updateNextSettleInterestDate(String loanAccountId,
    //            Date nextSettleInterestDate) {
    //        AssertUtils.notEmpty(loanAccountId, "loanAccountId is empty.");
    //        AssertUtils.notNull(nextSettleInterestDate,
    //                "nextSettleInterestDate is empty.");
    //        
    //        Date now = new Date();
    //        Map<String, Object> updateRowMap = new HashMap<String, Object>(
    //                TxConstants.INITIAL_MAP_SIZE);
    //        updateRowMap.put("id", loanAccountId);
    //        
    //        updateRowMap.put("lastUpdateDate", now);
    //        updateRowMap.put("nextSettleInterestDate", nextSettleInterestDate);
    //        
    //        this.loanAccountDao.update(updateRowMap);
    //    }
    //    /**
    //      * 更新贷款账户的逾期金额以及逾期总额<br/>
    //      * <功能详细描述>
    //      * @param loanAccountId
    //      * @param overdueAmount
    //      * @param overdueSum [参数说明]
    //      * 
    //      * @return void [返回类型说明]
    //      * @exception throws [异常类型] [异常说明]
    //      * @see [类、类#方法、类#成员]
    //     */
    //    @Transactional
    //    public void updateOverdueAmountAndSum(String loanAccountId,
    //            BigDecimal overdueAmount, BigDecimal overdueSum) {
    //        AssertUtils.notEmpty(loanAccountId, "loanAccountId is empty.");
    //        AssertUtils.notNull(overdueAmount, "nextSettleInterestDate is empty.");
    //        AssertUtils.notNull(overdueSum, "overdueSum is empty.");
    //        
    //        Date now = new Date();
    //        Map<String, Object> updateRowMap = new HashMap<String, Object>(
    //                TxConstants.INITIAL_MAP_SIZE);
    //        updateRowMap.put("id", loanAccountId);
    //        
    //        updateRowMap.put("lastUpdateDate", now);
    //        updateRowMap.put("overdueSum", overdueSum);//逾期总额 
    //        updateRowMap.put("overdueAmount", overdueAmount);//逾期总额 
    //        
    //        this.loanAccountDao.update(updateRowMap);
    //    }
    //    /**
    //      * 改变下次还款日<br/>
    //      *<功能详细描述>
    //      * @param loanAccountId
    //      * @param preRepayDate
    //      * @param lastRepayDate [参数说明]
    //      * 
    //      * @return void [返回类型说明]
    //      * @exception throws [异常类型] [异常说明]
    //      * @see [类、类#方法、类#成员]
    //     */
    //    @Transactional
    //    public void updateNextRepayDate(String loanAccountId, Date preRepayDate,
    //            Date repayDate) {
    //        AssertUtils.notEmpty(loanAccountId, "loanAccountId is empty.");
    //        AssertUtils.notNull(preRepayDate, "preRepayDate is empty.");
    //        AssertUtils.notNull(repayDate, "repayDate is empty.");
    //        AssertUtils.isTrue(preRepayDate.compareTo(repayDate) <= 0,
    //                "preRepayDate must less than repayDate.{}  {}",
    //                new Object[] { preRepayDate, repayDate });
    //        
    //        Date now = new Date();
    //        Map<String, Object> updateRowMap = new HashMap<String, Object>(
    //                TxConstants.INITIAL_MAP_SIZE);
    //        updateRowMap.put("id", loanAccountId);
    //        
    //        updateRowMap.put("lastUpdateDate", now);
    //        updateRowMap.put("nextRepayDate", repayDate);
    //        
    //        this.loanAccountDao.update(updateRowMap);
    //    }
    //    /** 
    //     *更新是否有未到账交易
    //     *<功能详细描述>
    //     * @param loanAccount [参数说明]
    //     * 
    //     * @return void [返回类型说明]
    //     * @exception throws [异常类型] [异常说明]
    //     * @see [类、类#方法、类#成员]
    //     */
    //    public void updateExistNotReceivedTrading(String loanAccountId,
    //            boolean existNotReceivedTrading) {
    //        AssertUtils.notEmpty(loanAccountId, "loanAccountId is empty.");
    //        LoanAccount loanAccount = this.findLoanAccountById(loanAccountId);
    //        AssertUtils.notEmpty(loanAccount, "loanAccount is empty.");
    //        
    //        Map<String, Object> updateRowMap = new HashMap<String, Object>(
    //                TxConstants.INITIAL_MAP_SIZE);
    //        
    //        updateRowMap.put("existNotReceivedTrading", existNotReceivedTrading);
    //        updateRowMap.put("id", loanAccountId);
    //        
    //        this.loanAccountDao.updateLoanAccount(updateRowMap);
    //    }
    
    /**
      * 在change请求中更新贷款账户的还款类型<br/>
      * <功能详细描述>
      * @param loanAccount [参数说明]
      * 
      * @return void [返回类型说明]
      * @exception throws [异常类型] [异常说明]
      * @see [类、类#方法、类#成员]
     */
    @Transactional
    public void updateWhenChangeRequestHandle(LoanAccount loanAccount) {
        //验证参数是否合法，必填字段是否填写
        AssertUtils.notNull(loanAccount, "loanAccount is null.");
        AssertUtils.notEmpty(loanAccount.getId(), "loanAccount.id is empty.");
        
        //生成需要更新字段的hashMap
        Date now = new Date();
        Map<String, Object> updateRowMap = new HashMap<String, Object>();
        updateRowMap.put("id", loanAccount.getId());
        
        //最后更新时间
        updateRowMap.put("lastUpdateDate", now);
        
        //更新贷款单账户状态
        updateRowMap.put("collectionStatus", loanAccount.getCollectionStatus());
        updateRowMap.put("settleInterestStatus",
                loanAccount.getSettleInterestStatus());
        updateRowMap.put("died", loanAccount.isDied());
        updateRowMap.put("legalProcedure", loanAccount.isLegalProcedure());
        updateRowMap.put("closed", loanAccount.isClosed());
        
        //更新逾期相关信息
        updateRowMap.put("overdueDate", loanAccount.getOverdueDate());
        updateRowMap.put("overdue", loanAccount.isOverdue());
        updateRowMap.put("overdueSum", loanAccount.getOverdueSum());//逾期总额 
        updateRowMap.put("overdueAmount", loanAccount.getOverdueAmount());//逾期金额
        
        updateRowMap.put("nextOverdueCheckDate",
                loanAccount.getNextOverdueCheckDate());
        updateRowMap.put("nextSettleInterestDate",
                loanAccount.getNextSettleInterestDate());
        updateRowMap.put("nextRepayDate", loanAccount.getNextRepayDate());
        updateRowMap.put("nextDeductDate", loanAccount.getNextDeductDate());
        
        //委外相关信息
        updateRowMap.put("outsourceHappend", loanAccount.isOutsourceHappend());
        updateRowMap.put("outsourceAssignRecordId",
                loanAccount.getOutsourceAssignRecordId());
        
        updateRowMap.put("outsource", loanAccount.isOutsource());
        updateRowMap.put("outsourcePercentage",
                loanAccount.getOutsourcePercentage());
        updateRowMap.put("outsourceAmount", loanAccount.getOutsourceAmount());
        
        updateRowMap.put("lastLockDate", loanAccount.getLastLockDate());
        updateRowMap.put("locked", loanAccount.isLocked());
        
        this.loanAccountDao.update(updateRowMap);
    }
    
    /**
      * 当交易中对贷款账户进行更新<br/>
      * <功能详细描述>
      * @param loanAccount [参数说明]
      * 
      * @return void [返回类型说明]
      * @exception throws [异常类型] [异常说明]
      * @see [类、类#方法、类#成员]
     */
    @Transactional
    public void updateWhenTradingRequestHandle(LoanAccount loanAccount) {
        //验证参数是否合法，必填字段是否填写
        AssertUtils.notNull(loanAccount, "loanAccount is null.");
        AssertUtils.notEmpty(loanAccount.getId(), "loanAccount.id is empty.");
        
        //生成需要更新字段的hashMap
        Date now = new Date();
        Map<String, Object> updateRowMap = new HashMap<String, Object>();
        updateRowMap.put("id", loanAccount.getId());
        
        updateRowMap.put("nextRepayDate", loanAccount.getNextRepayDate());
        updateRowMap.put("nextDeductDate", loanAccount.getNextDeductDate());
        updateRowMap.put("afterDelayDays", loanAccount.getAfterDelayDays());//贷后延期天数
        updateRowMap.put("monthlyRepayDay", loanAccount.getMonthlyRepayDay());//每月还款日
        updateRowMap.put("firstRepayDate", loanAccount.getFirstRepayDate());//每月还款日
        //更新贷后延期天数
        updateRowMap.put("afterDelayDays", loanAccount.getAfterDelayDays());
        updateRowMap.put("interestDate", loanAccount.getInterestDate());
        
        //最后更新时间
        updateRowMap.put("lastUpdateDate", now);
        
        updateRowMap.put("lastRepayAmount", loanAccount.getLastRepayAmount());// 最近还款金额
        updateRowMap.put("lastRepayDate", loanAccount.getLastRepayDate());// 更新最后还款日期
        
        //更新金额
        updateRowMap.put("overdueAmount", loanAccount.getOverdueAmount());//逾期金额
        updateRowMap.put("overdueSum", loanAccount.getOverdueSum());//逾期总额 
        
        //更新本金结余
        updateRowMap.put("principalBalanceIrr",
                loanAccount.getPrincipalBalanceIrr());//更新递减本金结余
        updateRowMap.put("principalBalance", loanAccount.getPrincipalBalance());//更新平息本金结余
        
        updateRowMap.put("currentPeriod", loanAccount.getCurrentPeriod());//当前期数
        updateRowMap.put("currentPeriodExpireDate",
                loanAccount.getCurrentPeriodExpireDate()); //当前期数到期日
        updateRowMap.put("paidPeriod", loanAccount.getPaidPeriod());//已付期数
        
        updateRowMap.put("overRepayAmount", loanAccount.getOverRepayAmount());//超额还款金额
        
        //贷后取消原因，取消时间，是否取消
        updateRowMap.put("revokeReason", loanAccount.getRevokeReason());
        updateRowMap.put("revokeDate", loanAccount.getRevokeDate());
        updateRowMap.put("revoked", loanAccount.isRevoked());
        
        //更新逾期相关信息
        updateRowMap.put("overdueDate", loanAccount.getOverdueDate());
        updateRowMap.put("overdue", loanAccount.isOverdue());
        
        updateRowMap.put("accountStatus", loanAccount.getAccountStatus());//修改账户状态：注销时会调用
        updateRowMap.put("collectionStatus", loanAccount.getCollectionStatus());//更新贷款账户催收状态
        updateRowMap.put("settleInterestStatus",
                loanAccount.getSettleInterestStatus());//更新贷款账户催收状态
        
        // 结清数据
        updateRowMap.put("settleDate", loanAccount.getSettleDate());
        updateRowMap.put("settleRepayDate", loanAccount.getSettleRepayDate());
        updateRowMap.put("settleSLDate", loanAccount.getSettleSLDate());
        updateRowMap.put("settleSLRepayDate",
                loanAccount.getSettleSLRepayDate());
        updateRowMap.put("settleSL", loanAccount.isSettleSL());
        updateRowMap.put("settle", loanAccount.isSettle());
        
        this.loanAccountDao.update(updateRowMap);
    }
    
    /**
     * 根据客户的电话号码，返回该客户所有的贷款单ID <功能详细描述>
     * 
     * @param phoneNumber
     * @return [参数说明]
     * 
     * @return List<String> [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    public List<String> queryAccountIdsByClientinfoPhoneNumber(
            String phoneNumber) {
        //        Set<String> billIdSet = billClientinfoFacade.queryBillIdByPhone(null,
        //                phoneNumber,
        //                null);
        //        List<String> loanAccountIdList = new ArrayList<String>(
        //                TxConstants.INITIAL_CONLLECTION_SIZE);
        //        
        //        for (String billId : billIdSet) {
        //            Map<String, Object> params = new HashMap<String, Object>();
        //            params.put("billId", billId);
        //            List<LoanAccount> loanAccounts = loanAccountDao.queryLoanAccountList(params);
        //            if (CollectionUtils.isNotEmpty(loanAccounts)) {
        //                loanAccountIdList.add(loanAccounts.get(0).getId());
        //            }
        //        }
        //        return loanAccountIdList;
        return null;
    }
    
    //    /**
    //     * 
    //      *锁定账户
    //      *<功能详细描述>
    //      * @param loanAccountId 贷款账户ID
    //      * @return [参数说明]
    //      * 
    //      * @return int [返回类型说明]
    //      * @exception throws [异常类型] [异常说明]
    //      * @see [类、类#方法、类#成员]
    //     */
    //    @Transactional
    //    public int lockByLoanAccountId(String loanAccountId) {
    //        
    //        Map<String, Object> updateRowMap = new HashMap<String, Object>();
    //        
    //        Date date = new Date();
    //        
    //        updateRowMap.put("id", loanAccountId);
    //        updateRowMap.put("locked", true);
    //        updateRowMap.put("lastLockDate", date);
    //        updateRowMap.put("lastUpdateDate", date);
    //        int count = loanAccountDao.update(updateRowMap);
    //        
    //        return count;
    //    }
    
}
