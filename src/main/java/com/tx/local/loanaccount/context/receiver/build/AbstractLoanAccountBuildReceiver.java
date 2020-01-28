/*
 * 描          述:  <描述>
 * 修  改   人:  Administrator
 * 修改时间:  2014年5月21日
 * <修改描述:>
 */
package com.tx.local.loanaccount.context.receiver.build;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.collections4.MapUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.joda.time.DateTime;
import org.springframework.core.Ordered;
import org.springframework.core.PriorityOrdered;

import com.tx.component.command.context.CommandResponse;
import com.tx.core.TxConstants;
import com.tx.core.exceptions.util.AssertUtils;
import com.tx.local.basicdata.model.District;
import com.tx.local.basicdata.model.FeeItemCfg;
import com.tx.local.basicdata.model.FeeItemEnum;
import com.tx.local.basicdata.model.IDCardTypeEnum;
import com.tx.local.basicdata.model.RepayWayEnum;
import com.tx.local.basicdata.model.TimeUnitTypeEnum;
import com.tx.local.loanaccount.context.request.build.AbstractBuildRequest;
import com.tx.local.loanaccount.model.AccountStatusEnum;
import com.tx.local.loanaccount.model.CollectionStatusEnum;
import com.tx.local.loanaccount.model.LoanAccount;
import com.tx.local.loanaccount.model.LoanAccountCategoryEnum;
import com.tx.local.loanaccount.model.LoanAccountFeeItem;
import com.tx.local.loanaccount.model.LoanAccountTypeEnum;
import com.tx.local.loanaccount.model.PaymentSchedule;
import com.tx.local.loanaccount.model.PaymentScheduleEntry;
import com.tx.local.loanaccount.model.ScheduleAssociateMap;
import com.tx.local.loanaccount.model.ScheduleEntryAssociateMap;
import com.tx.local.loanaccount.model.ScheduleTypeEnum;
import com.tx.local.loanaccount.model.SettleInterestStatusEnum;
import com.tx.local.loanaccount.strategy.loanaccount.LoanAccountStrategy;

/**
 * 构建贷款账户请求处理器<br/>
 * 
 * @author  Administrator
 * @version  [版本号, 2014年5月21日]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public abstract class AbstractLoanAccountBuildReceiver<BR extends AbstractBuildRequest>
        extends AbstractBuildReceiver<BR> implements PriorityOrdered {
    
    /**
     * @return
     */
    @Override
    public int getOrder() {
        return Ordered.LOWEST_PRECEDENCE;
    }
    
    /**
     * 判断是否匹配<br/>
     * <功能详细描述>
     * @param request
     * @return [参数说明]
     * 
     * @return boolean [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
    */
    @Override
    protected boolean isMatch(BR request) {
        LoanAccountTypeEnum type = supportLoanAccountType();
        if (type != null && type.equals(request.getLoanAccountType())) {
            //如果指定了贷款账户类型优先提取贷款账户类型
            return true;
        }
        LoanAccountCategoryEnum category = supportLoanAccountCategory();
        if (category != null && category
                .equals(request.getLoanAccountType().getCategory())) {
            //类型没有匹配上，通过类型的分类再匹配
            return true;
        }
        return false;
    }
    
    /**
     * 贷款账户类型分类<br/>
     * <功能详细描述>
     * @return [参数说明]
     * 
     * @return LoanAccountCategoryEnum [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    protected LoanAccountCategoryEnum supportLoanAccountCategory() {
        return null;
    }
    
    /**
     * 贷款账户类型分类<br/>
     * <功能详细描述>
     * @return [参数说明]
     * 
     * @return LoanAccountCategoryEnum [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    protected LoanAccountTypeEnum supportLoanAccountType() {
        return null;
    }
    
    /**
     * @param request
     * @return
     */
    @Override
    public LoanAccount buildLoanAccount(BR request) {
        // 获取客户账号
        LoanAccount loanAccount = new LoanAccount();
        // 生成时 初始化ID 
        loanAccount.setId(request.getLoanAccountId());
        
        //贷款账户账户状态
        AssertUtils.notNull(request.getLoanAccountType(),
                "request.loanAccountType is null.");
        loanAccount.setCategory(request.getLoanAccountType().getCategory());
        loanAccount.setLoanAccountType(request.getLoanAccountType()); // 贷款账户类型 默认为月结账户
        
        //设置贷款账户初始化值
        initLoanAccountStatus(request, loanAccount);
        
        //初始化贷款账户基础信息
        initLoanAccountInfo(request, loanAccount);
        
        //根据请求初始化贷款账户
        initLoanAccountByRequest(request, loanAccount);
        
        return loanAccount;
    }
    
    /** 
     * 根据合同实体初始化贷款账户<br/>
     * <功能详细描述>
     * @param loanBillId
     * @param clientId
     * @param constractId
     * @param loanAccount [参数说明]
     * 
     * @return void [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    protected void initLoanAccountByRequest(BR request,
            LoanAccount loanAccount) {
        String sourceLoanAccountId = request.getSourceTradingRecord() == null
                ? null : request.getSourceTradingRecord().getLoanAccountId();//原贷贷款账户id(续贷，再贷关联的原贷款账户)
        String sourceLastTradingRecordId = request
                .getSourceTradingRecord() == null ? null
                        : request.getSourceTradingRecord().getId();//原贷款账户最后一笔交易记录id
        
        //客户信息
        String clientId = request.getClientInfoId();
        String clientName = request.getClientName();
        IDCardTypeEnum idCardType = request.getClientIdCardType();
        String idCardNumber = request.getClientIdCardNumber();
        String clientAccountId = request.getClientAccountId();
        String creditInfoId = request.getCreditInfoId();
        String creditInfoVersion = request.getCreditInfoVersion();
        
        //合同信息
        String contractId = request.getContractId();
        String contractNumber = request.getContractNumber();
        Date effectiveDate = request.getEffectiveDate();
        Date expiryDate = request.getExpiryDate();
        Date factLoanDate = request.getFactLoanDate();//实际放款日
        int beforeDelayDay = request.getBeforeDelayDays();//贷前延期天数
        Date interestDate = request.getInterestDate();//首期还款日
        //DateTime interestDateTime = new DateTime(interestDate);
        int monthlyRepayDay = request.getMonthlyRepayDay();//每月还款日
        Date firstRepayDate = request.getFirstRepayDate();
        BigDecimal monthlyRepayAmount = request.getMonthlyRepayAmount();//每月还款金额
        
        //还款计划相关信息
        RepayWayEnum repayWay = request.getRepayWay();
        TimeUnitTypeEnum timeUnitType = request.getTimeUnitType();
        int time = request.getTime();
        Integer totalPeriod = request.getTotalPeriod();
        //金额相关信息
        BigDecimal loanAmount = request.getLoanAmount();//贷款金额
        BigDecimal factOutLoanAmount = request.getFactOutLoanAmount();//贷款金额
        
        //归属信息
        String vcid = request.getVcid();
        String organizationId = request.getOrganizationId();
        District district = request.getDistrict();
        
        //如果存在原贷款账户:
        loanAccount.setSourceLoanAccountId(sourceLoanAccountId);
        loanAccount.setSourceLastTradingRecordId(sourceLastTradingRecordId);
        
        //还款变更时发生变更的项
        loanAccount.setClientId(clientId);
        loanAccount.setClientAccountId(clientAccountId);
        loanAccount.setClientIdCardNumber(idCardNumber);
        loanAccount.setClientIdCardType(idCardType);
        loanAccount.setClientName(clientName);
        loanAccount.setCreditInfoId(creditInfoId);
        loanAccount.setCreditInfoVersion(creditInfoVersion);
        
        //合同信息
        loanAccount.setContractId(contractId);
        loanAccount.setContractNumber(contractNumber);
        loanAccount.setEffectiveDate(effectiveDate); // 生效日期
        loanAccount.setExpiryDate(expiryDate); // 失效日期
        loanAccount.setFactLoanDate(factLoanDate);
        loanAccount.setBeforeDelayDays(beforeDelayDay);
        loanAccount.setInterestDate(interestDate);
        loanAccount.setFirstRepayDate(firstRepayDate); // 首次还款日期
        loanAccount.setNextRepayDate(firstRepayDate);//下次还款日为首期还款日
        loanAccount.setNextDeductDate(firstRepayDate);//下次扣款日为首期还款日
        loanAccount.setCurrentPeriodExpireDate(firstRepayDate);
        loanAccount.setMonthlyRepayDay(monthlyRepayDay); // 每月还款日
        
        //业务发生时可能发生变化的项
        loanAccount.setRepayWay(repayWay);
        loanAccount.setTimeUnitType(timeUnitType);
        loanAccount.setTime(time);
        loanAccount.setLoanAmount(loanAmount);// 贷款金额
        loanAccount.setFactOutLoanAmount(factOutLoanAmount);//实际转账金额
        loanAccount.setMonthlyRepayAmount(monthlyRepayAmount);
        loanAccount.setTotalPeriod(totalPeriod); // 总期数
        loanAccount.setPrincipalBalance(loanAmount);//平息本金结余
        loanAccount.setPrincipalBalanceIrr(loanAmount);//递减本金结余
        
        loanAccount.setVcid(vcid);
        loanAccount.setOrganizationId(organizationId);
        loanAccount.setDistrict(district);
    }
    
    /** 
     * 初始化贷款账户基础信息
     * <功能详细描述>
     * @param now
     * @param loanAccount [参数说明]
     * 
     * @return void [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    protected void initLoanAccountInfo(BR request, LoanAccount loanAccount) {
        Date now = new Date();
        //贷款账户创建相关记录
        loanAccount.setLastRepayAmount(BigDecimal.ZERO);
        
        loanAccount.setCreateDate(request.getCreateDate() != null
                ? request.getCreateDate() : now); //账户创建时间
        loanAccount.setLastUpdateDate(request.getCreateDate() != null
                ? request.getCreateDate() : now); // 更新日
        loanAccount.setNextSettleInterestDate(now);
        loanAccount.setNextOverdueCheckDate(now);
        loanAccount.setCurrentPeriod("1"); //当前期数
        loanAccount.setPaidPeriod(BigDecimal.ZERO); // 已付期数
        //首次需要初始化，业务中需要处理的字段
        loanAccount.setLastLockDate(null); //最后锁定时间
        loanAccount.setLastRepayDate(null); //上次还款日
        loanAccount.setWriteOffDate(null); // 注销日期
        loanAccount.setWriteOff(false); // 是否注销 默认FLASE
        loanAccount.setWriteOffReason(null); //注销原因
        loanAccount.setOverdue(false); //是否逾期
        loanAccount.setOverdueDate(null); //逾期日期
        loanAccount.setOverdueSum(BigDecimal.ZERO); //逾期总额
        loanAccount.setOverdueAmount(BigDecimal.ZERO); //逾期金额
        loanAccount.setRevoked(false); // 是否退回
        loanAccount.setRevokeDate(null); // 退回日期
        loanAccount.setRevokeReason(null);// 退回原因
        loanAccount.setOverRepayAmount(BigDecimal.ZERO);
    }
    
    /** 
     * 初始化贷款账户状态相关信息
     *<功能详细描述>
     * @param loanAccount [参数说明]
     * 
     * @return void [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    protected void initLoanAccountStatus(BR request, LoanAccount loanAccount) {
        loanAccount.setAccountStatus(AccountStatusEnum.AC); //设置账户状态
        loanAccount.setCollectionStatus(CollectionStatusEnum.NA); //催收状态:  调用状态机填入，初始化时为AC
        loanAccount.setDied(false); //是否死亡
        loanAccount.setLegalProcedure(false); // 是否法律程序
        loanAccount.setLocked(false); // 是否锁定： 默认值为false,如果锁定则填入true
        loanAccount.setSettleInterestStatus(SettleInterestStatusEnum.WSI); // 结息状态 默认正常
    }
    
    /**
     * @param request
     * @param loanAccount
     * @return
     */
    @Override
    public List<LoanAccountFeeItem> buildLoanAccountFeeItems(BR request,
            LoanAccount loanAccount) {
        String loanAccountId = loanAccount.getId();
        
        List<LoanAccountFeeItem> resList = new ArrayList<>();
        
        if (MapUtils.isEmpty(request.getFeeItemMap())) {
            return resList;
        }
        
        for (Entry<FeeItemEnum, String> entryTemp : request.getFeeItemMap()
                .entrySet()) {
            LoanAccountFeeItem fi_lx = new LoanAccountFeeItem();
            fi_lx.setLoanAccountId(loanAccountId);
            fi_lx.setFeeItem(entryTemp.getKey());
            fi_lx.setValue(entryTemp.getValue());
            resList.add(fi_lx);
        }
        
        return resList;
    }
    
    /**
     * @param request
     * @param loanAccount
     * @param feeCfgItems
     * @return
     */
    @Override
    public List<PaymentSchedule> buildPaymentSchedules(BR request,
            LoanAccount loanAccount, List<LoanAccountFeeItem> feeCfgItems,
            LoanAccountStrategy strategy) {
        Map<FeeItemEnum, FeeItemCfg> feeItemCfgMap = loanAccount
                .getFeeItemCfgMap();
        Map<FeeItemEnum, LoanAccountFeeItem> feeItemMap = loanAccount
                .getFeeItemMap();
        
        //构建还款计划分项列表
        Map<String, Object> params = new HashMap<>();
        List<PaymentScheduleEntry> paymentScheduleEntryList = strategy
                .buildPaymentScheduleEntryList(loanAccount,
                        feeItemMap,
                        params,
                        0,
                        loanAccount.getRepayWay());
        ScheduleEntryAssociateMap<PaymentScheduleEntry> pseMap = new ScheduleEntryAssociateMap<>(
                paymentScheduleEntryList);
        //根据还款计划分项构建还款计划实例
        ScheduleAssociateMap<PaymentSchedule> psMap = buildPaymentScheduleByEntry(
                loanAccount, pseMap);
        
        //为还款计划生成
        DateTime firstRepayDateTime = new DateTime(
                loanAccount.getFirstRepayDate());
        BigDecimal loanAmount = loanAccount.getLoanAmount(); //贷款金额
        
        DateTime preMonthDateTime = firstRepayDateTime.plusMonths(-1);
        int monthlyRepayDay = loanAccount.getMonthlyRepayDay();
        //对应期数的还款日
        DateTime zeroRepayDateTime = new DateTime(preMonthDateTime.getYear(),
                preMonthDateTime.getMonthOfYear(), monthlyRepayDay, 23, 59, 59);
        
        //计算每一期的还款时间以及
        for (ScheduleTypeEnum scheduleType : psMap.keySet()) {
            BigDecimal principalBalance = loanAmount;//本金结余：
            
            String nextPeriod = "1";
            while (!StringUtils.isBlank(nextPeriod)) {
                //校验合法性
                AssertUtils.isTrue(psMap.contains(scheduleType, nextPeriod),
                        "paymentScheduleMap not conatains period:{}",
                        nextPeriod);
                int periodIndex = NumberUtils.toInt(nextPeriod);
                //获取对应期数的还款计划
                PaymentSchedule ps = psMap.get(scheduleType, nextPeriod);
                //设置还款日期
                ps.setRepaymentDate(
                        zeroRepayDateTime.plusMonths(periodIndex).toDate());
                //计算本金结余
                List<PaymentScheduleEntry> entryList = new ArrayList<>(
                        pseMap.get(scheduleType, nextPeriod).values());
                for (PaymentScheduleEntry entryTemp : entryList) {
                    FeeItemEnum feeItem = entryTemp.getFeeItem();
                    AssertUtils.isTrue(feeItemCfgMap.containsKey(feeItem),
                            "feeItem:{} is not exist.",
                            new Object[] { feeItem });
                    if (feeItemCfgMap.get(feeItem).isPrincipal()) {
                        principalBalance = principalBalance
                                .subtract(entryTemp.getReceivableAmount());
                    }
                }
                //设置计划中的本金结余
                ps.setPrincipalBalance(principalBalance);
                //下一期
                nextPeriod = ps.getNextPeriod();//下一期的期数
            }
            AssertUtils.isTrue(BigDecimal.ZERO.compareTo(principalBalance) == 0,
                    "last period principalBalance is not zero.principalBalance:{}",
                    new Object[] { principalBalance });
        }
        
        List<PaymentSchedule> paymentSchedules = new ArrayList<PaymentSchedule>(
                TxConstants.INITIAL_CONLLECTION_SIZE);
        paymentSchedules.addAll(psMap.valueList());
        return paymentSchedules;
    }
    
    /** 
     * 根据还款计划分项构建还款计划实例 
     *<功能详细描述>
     * @param paymentSchedules
     * @param paymentScheduleEntryMultiValueMap
     * @param paymentScheduleMap [参数说明]
     * 
     * @return void [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    protected ScheduleAssociateMap<PaymentSchedule> buildPaymentScheduleByEntry(
            LoanAccount loanAccount,
            ScheduleEntryAssociateMap<PaymentScheduleEntry> pseMap) {
        List<PaymentSchedule> psList = new ArrayList<>();
        
        for (ScheduleTypeEnum scheduleType : pseMap.keySet()) {
            for (Entry<String, Map<FeeItemEnum, PaymentScheduleEntry>> entry : pseMap
                    .get(scheduleType).entrySet()) {
                String period = entry.getKey();
                List<PaymentScheduleEntry> paymentScheduleEntyList = new ArrayList<>(
                        entry.getValue().values());
                PaymentScheduleEntry entryTemp = paymentScheduleEntyList.get(0);
                String prePeriod = entryTemp.getPrePeriod();
                String nextPeriod = entryTemp.getNextPeriod();
                AssertUtils.isTrue(period.equals(entryTemp.getPeriod()),
                        "peroid not equals entry.period.");
                //构建还款计划
                PaymentSchedule paymentSchedule = new PaymentSchedule();
                paymentSchedule.setScheduleType(scheduleType);
                paymentSchedule.setPeriod(period);
                paymentSchedule.setPrePeriod(prePeriod);
                paymentSchedule.setNextPeriod(nextPeriod);
                paymentSchedule
                        .setPaymentScheduleEntryList(paymentScheduleEntyList);
                BigDecimal receivableSum = BigDecimal.ZERO; //还款应收账款总额
                BigDecimal actualReceivedSum = BigDecimal.ZERO; //同一期的各费用项应收之和
                BigDecimal exemptSum = BigDecimal.ZERO;
                for (PaymentScheduleEntry paymentScheduleEntry : paymentScheduleEntyList) {
                    //为分项设置对应的paymentSchedule
                    paymentScheduleEntry.setPaymentSchedule(paymentSchedule);
                    //对分项的各数据一致合法性校验
                    AssertUtils.isTrue(
                            period.equals(paymentScheduleEntry.getPeriod()),
                            "period:{} not equals entry.period:{}.",
                            new Object[] { period,
                                    paymentScheduleEntry.getPeriod() });
                    AssertUtils.isEq(prePeriod,
                            paymentScheduleEntry.getPrePeriod(),
                            "prePeriod:{} not equals entry.prePeriod:{}.period:{} feeItem:{}",
                            new Object[] { prePeriod,
                                    paymentScheduleEntry.getPrePeriod(), period,
                                    paymentScheduleEntry.getFeeItem() });
                    AssertUtils.isEq(nextPeriod,
                            paymentScheduleEntry.getNextPeriod(),
                            "nextPeriod:{} not equals entry.nextPeriod:{}.period:{} feeItem:{}",
                            new Object[] { nextPeriod,
                                    paymentScheduleEntry.getNextPeriod(),
                                    period,
                                    paymentScheduleEntry.getFeeItem() });
                    //总计应收、实收、豁免
                    receivableSum = receivableSum
                            .add(paymentScheduleEntry.getReceivableAmount());
                    actualReceivedSum = actualReceivedSum.add(
                            paymentScheduleEntry.getActualReceivedAmount());
                    exemptSum = exemptSum
                            .add(paymentScheduleEntry.getExemptAmount());
                }
                //填入各合计以后的值
                paymentSchedule.setReceivableSum(receivableSum);
                paymentSchedule.setActualReceivedSum(actualReceivedSum);
                paymentSchedule.setExemptSum(exemptSum);
                //逾期金额
                paymentSchedule.setOverdueSum(BigDecimal.ZERO);
                paymentSchedule.setLoanAccountId(loanAccount.getId());
                
                //添加到LIST
                psList.add(paymentSchedule);
            }
        }
        
        ScheduleAssociateMap<PaymentSchedule> psMap = new ScheduleAssociateMap<>(
                psList);
        return psMap;
    }
    
    /**
     * @param request
     * @param loanAccount
     * @param feeCfgItems
     * @param paymentSchedules
     */
    @Override
    public void postHandle(BR request, CommandResponse response,
            LoanAccount loanAccount, List<LoanAccountFeeItem> feeCfgItems,
            List<PaymentSchedule> paymentScheduleList,List<PaymentScheduleEntry> paymentScheduleEntryList) {
        super.postHandle(request,
                response,
                loanAccount,
                feeCfgItems,
                paymentScheduleList,
                paymentScheduleEntryList);
    }
}
