/*
 * 描          述:  <描述>
 * 修  改   人:  Administrator
 * 修改时间:  2014年4月25日
 * <修改描述:>
 */
package com.tx.local.loanaccount.context.support;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.transaction.support.TransactionSynchronizationAdapter;
import org.springframework.transaction.support.TransactionSynchronizationManager;

import com.tx.component.command.context.CommandResponse;
import com.tx.component.event.context.EventContext;
import com.tx.component.servicelogger.util.ServiceLoggerUtils;
import com.tx.core.TxConstants;
import com.tx.core.exceptions.util.AssertUtils;
import com.tx.core.util.DateUtils;
import com.tx.core.util.MessageUtils;
import com.tx.local.basicdata.model.FeeItemCfg;
import com.tx.local.basicdata.model.FeeItemEnum;
import com.tx.local.loanaccount.LoanAccountConstants;
import com.tx.local.loanaccount.context.receiver.trading.AbstractTradingReceiver;
import com.tx.local.loanaccount.context.request.trading.AbstractTradingRequest;
import com.tx.local.loanaccount.event.change.OverdueEvent;
import com.tx.local.loanaccount.event.change.OverdueOverEvent;
import com.tx.local.loanaccount.event.trading.ChargeEvent;
import com.tx.local.loanaccount.event.trading.ExemptEvent;
import com.tx.local.loanaccount.event.trading.OverdueAmountChangeEvent;
import com.tx.local.loanaccount.event.trading.OverdueDateChangeEvent;
import com.tx.local.loanaccount.event.trading.PaymentEvent;
import com.tx.local.loanaccount.event.trading.TradingEvent;
import com.tx.local.loanaccount.handle.PaymentScheduleHandler;
import com.tx.local.loanaccount.helper.feecfgitem.LoanAccountFeeItemLazyMap;
import com.tx.local.loanaccount.helper.paymentrecord.PaymentRecordHelper;
import com.tx.local.loanaccount.helper.paymentschedule.PaymentScheduleEntryHelper;
import com.tx.local.loanaccount.helper.tradingrecord.LATradingRecordHelper;
import com.tx.local.loanaccount.model.AccountStatusEnum;
import com.tx.local.loanaccount.model.ChargeRecord;
import com.tx.local.loanaccount.model.ChargeRecordEntry;
import com.tx.local.loanaccount.model.CollectionStatusEnum;
import com.tx.local.loanaccount.model.ExemptRecord;
import com.tx.local.loanaccount.model.ExemptRecordEntry;
import com.tx.local.loanaccount.model.LARequestLog;
import com.tx.local.loanaccount.model.LATradingRecord;
import com.tx.local.loanaccount.model.LATradingRecordEntry;
import com.tx.local.loanaccount.model.LoanAccount;
import com.tx.local.loanaccount.model.LoanAccountFeeItem;
import com.tx.local.loanaccount.model.PaymentRecord;
import com.tx.local.loanaccount.model.PaymentRecordEntry;
import com.tx.local.loanaccount.model.PaymentSchedule;
import com.tx.local.loanaccount.model.PaymentScheduleEntry;
import com.tx.local.loanaccount.model.ScheduleAssociateMap;
import com.tx.local.loanaccount.model.ScheduleTypeEnum;
import com.tx.local.loanaccount.model.SettleInterestStatusEnum;
import com.tx.local.loanaccount.strategy.loanaccount.LoanAccountStrategy;
import com.tx.local.loanaccount.strategy.loanaccount.LoanAccountStrategyHelper;

/**
 * 操作请求支持类<br/>
 * 提供类似command模式中的Command对象的封装 以支持context的策略模式的功能实现<br/>
 * 
 * @author Administrator
 * @version [版本号, 2014年4月25日]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
@Scope("prototype")
@Component("tradingRequestSupport")
public class TradingRequestSupport extends
        AbstractLoanAccountRequestSupport<AbstractTradingRequest, AbstractTradingReceiver<AbstractTradingRequest>> {
    
    /** 贷款账户id */
    private String loanAccountId;
    
    /** 贷款账户实例 */
    private LoanAccount loanAccount;
    
    /** 贷款账户处理策略 */
    private LoanAccountStrategy loanAccountStrategy;
    
    /** 贷款账户费用配置映射 */
    private Map<FeeItemEnum, FeeItemCfg> feeItemCfgMap;
    
    /** 贷款账户费用项目映射 */
    private Map<FeeItemEnum, LoanAccountFeeItem> feeItemMap;
    
    /** 贷款账户还款计划处理器 */
    private PaymentScheduleHandler handler;
    
    /** 交易记录 */
    private LATradingRecord tradingRecord;
    
    private List<LATradingRecordEntry> tradingRecordEntryList;
    
    /** 计费记录 */
    private List<ChargeRecord> chargeRecordList;
    
    /** 计费记录分项 */
    private List<ChargeRecordEntry> chargeRecordEntryList;
    
    /** 豁免记录 */
    private List<ExemptRecord> exemptRecordList;
    
    /** 豁免记录分项 */
    private List<ExemptRecordEntry> exemptRecordEntryList;
    
    /** 实收记录 */
    private List<PaymentRecord> paymentRecordList;
    
    /** 实收记录分项 */
    private List<PaymentRecordEntry> paymentRecordEntryList;
    
    /** <默认构造函数> */
    public TradingRequestSupport() {
        super();
    }
    
    /** <默认构造函数> */
    public TradingRequestSupport(
            AbstractTradingReceiver<AbstractTradingRequest> receiver) {
        super(receiver);
    }
    
    /**
     * @param request
     * @param response
     * @return
     */
    @Override
    protected boolean isLockWhenHandle(AbstractTradingRequest request,
            CommandResponse response) {
        return true;
    }
    
    /**
     * 实际贷款账户操作处理逻辑 <功能详细描述>
     * 
     * @param request
     * @param loanAccountId
     * @param loanAccount
     *            [参数说明]
     * 
     * @return void [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    protected void doHandle(AbstractTradingRequest request,
            CommandResponse response) {
        AssertUtils.notEmpty(request.getLoanAccountId(),
                "request.loanAccountId is notEmpty.");
        AssertUtils.notNull(request.getLoanAccount(),
                "request.loanAccount is null.");
        this.loanAccountId = request.getLoanAccountId();
        this.loanAccount = request.getLoanAccount();
        this.loanAccountStrategy = LoanAccountStrategyHelper
                .getStrategy(this.loanAccount);
        
        //查询贷款账户实例
        this.feeItemMap = new LoanAccountFeeItemLazyMap(loanAccountId,
                this.loanAccountFeeItemService);
        this.feeItemCfgMap = this.loanAccountStrategy.getFeeItemCfgMap();
        loanAccount.setFeeItemMap(this.feeItemMap);
        loanAccount.setFeeItemCfgMap(this.feeItemCfgMap);
        
        //构建handler
        this.handler = paymentScheduleHandlerHelper
                .buildPaymentScheduleHandler(loanAccount);
        // 验证还款计划的正确性
        handler.validate();
        
        // 校验请求的合法性
        this.receiver.validateRequest(request, response, loanAccount, handler);
        
        // 创建交易记录:根据贷款单账户信息以及操作信息计算出对应的交易记录集合<br/>
        this.tradingRecord = this.receiver.createTradingRecord(request,
                response,
                loanAccount,
                handler);
        AssertUtils.notNull(tradingRecord, "tradingRecord is null");
        // 对交易记录进行加工，将请求以及贷款账户中的信息进行写入
        preProcessTradingRecord(request, loanAccount, tradingRecord);
        
        //创建计费记录
        this.chargeRecordEntryList = this.receiver.createChargeRecordEntryList(
                request, response, loanAccount, handler, tradingRecord);
        this.chargeRecordList = charge(chargeRecordEntryList,
                handler,
                tradingRecord);
        // 计费以后的本金结余
        calculatePrincipalBalanceByChargeRecord(loanAccount,
                handler,
                tradingRecord,
                chargeRecordList,
                tradingRecord.getPrincipalBalance(),
                tradingRecord.getPrincipalBalanceIrr());
        tradingRecord.setChargeRecordList(chargeRecordList);
        tradingRecord.setChargeRecordEntryList(chargeRecordEntryList);
        //System.out.println(PaymentScheduleHelper.toString(handler.getPaymentScheduleMap().valueList(), handler.getPaymentScheduleEntryMap().valueList()));
        
        // 创建豁免记录
        this.exemptRecordEntryList = this.receiver.createExemptRecordEntryList(
                request, response, loanAccount, handler, tradingRecord);
        this.exemptRecordList = exempt(exemptRecordEntryList,
                handler,
                tradingRecord);
        tradingRecord.setExemptRecordList(exemptRecordList);
        tradingRecord.setExemptRecordEntryList(exemptRecordEntryList);
        //System.out.println(PaymentScheduleHelper.toString(handler.getPaymentScheduleMap().valueList(), handler.getPaymentScheduleEntryMap().valueList()));
        
        // 还款记录
        this.paymentRecordEntryList = new ArrayList<>(
                TxConstants.INITIAL_CONLLECTION_SIZE);
        List<PaymentRecordEntry> sourcePaymentRecordEntryList = this.receiver
                .createPaymentRecordEntryList(request,
                        response,
                        loanAccount,
                        handler,
                        tradingRecord);
        if (!CollectionUtils.isEmpty(sourcePaymentRecordEntryList)) {
            // 将还款记录中amount = 0 同时 amountIrr = 0的记录抛弃掉
            for (PaymentRecordEntry entryTemp : sourcePaymentRecordEntryList) {
                if (BigDecimal.ZERO.compareTo(entryTemp.getAmount()) == 0) {
                    continue;
                }
                paymentRecordEntryList.add(entryTemp);
            }
        }
        // 根据还款计划分项生成还款记录
        this.paymentRecordList = payment(handler,
                tradingRecord,
                paymentRecordEntryList);
        // 根据实收计算本金结余//并填入paymentRecord以及tradingRecord中
        calculatePrincipalBalanceByPaymentRecord(tradingRecord,
                paymentRecordList,
                tradingRecord.getPrincipalBalance(),
                tradingRecord.getPrincipalBalanceIrr());
        tradingRecord.setPaymentRecordList(paymentRecordList);
        tradingRecord.setPaymentRecordEntryList(sourcePaymentRecordEntryList);
        //System.out.println(PaymentScheduleHelper.toString(handler.getPaymentScheduleMap().valueList(), handler.getPaymentScheduleEntryMap().valueList()));
        
        // 校验还款计划是否正确
        handler.validate();
        // 校验本金结余计算是否正确
        handler.validatePrincipalBalance(ScheduleTypeEnum.MAIN,
                loanAccount.getPrincipalBalance());
        handler.validatePrincipalBalance(ScheduleTypeEnum.IRR,
                loanAccount.getPrincipalBalanceIrr());
        
        // 计算交易完成后贷款账户的逾期金额以及逾期总额]
        // 需要再计算逾期金额以及逾期总额以前将当期到期还款日等信息设置入贷款账户<br/>
        loanAccount.setCurrentPeriod(handler.getCurrentPeriod());// 已付期数，当前期数
        Date currentPeriodExpirDate = handler.getPaymentScheduleMap()
                .get(ScheduleTypeEnum.MAIN, handler.getCurrentPeriod())
                .getRepaymentDate();// 设置当前期数到期日
        loanAccount.setCurrentPeriodExpireDate(currentPeriodExpirDate);// 当前期数到期时间
        loanAccount.setPaidPeriod(handler.getPaidPeriod());// 已付期数
        
        //计算逾期金额，约起总额
        calculateOverduleAmount(loanAccount, handler, this.receiver, request);
        
        // 根据paymentRecord生成tradingRecordEntry
        this.tradingRecordEntryList = this.receiver
                .createTradingRecordEntryList(request,
                        response,
                        loanAccount,
                        handler,
                        tradingRecord,
                        paymentRecordList);
        tradingRecord.setTradingRecordEntryList(tradingRecordEntryList);
        
        // 根据delayRecord修改loanAccount中的repayDay??(还款日)
        this.receiver.postHandle(request,
                response,
                loanAccount,
                handler,
                tradingRecord,
                chargeRecordList,
                exemptRecordList,
                paymentRecordList);
        
        //重置计费记录和还款计
        List<ChargeRecordEntry> newChargeRecordEntryList = this.receiver.resetChargeRecordEntryList(request,
                response,
                loanAccount,
                handler,
                chargeRecordEntryList);
        if (CollectionUtils.isNotEmpty(newChargeRecordEntryList)) {
            this.chargeRecordEntryList = newChargeRecordEntryList;
            this.chargeRecordList = charge(chargeRecordEntryList, handler, tradingRecord);
        }
       
        //在postHandle后进行贷款账户信息的更新
        processLoanAccountInfoAfterPostHandle(loanAccountId,
                loanAccount,
                handler,
                tradingRecord);
        
        // 计算账户状态
        processAccountStatus(request,
                response,
                loanAccount,
                handler,
                tradingRecord);
        
        // 处理逾期催收状态变更
        // * 1、修改账户是否逾期状态
        // * 2、修改账户逾期日期，如果不逾期了，将逾期日期修改为空
        // * 3、如果刚刚逾期，并且账户状态为NA，则将账户催收状态修改为DQ
        // * 4、如果不逾期了，并且账户状态为DQ,CO,则将账户状态修改为NA,
        // * 并且如果账户为停息状态，将账户状态修改为，正常
        processCollectionStatus(request,
                response,
                loanAccount,
                handler,
                tradingRecord);
        
        // 处理贷款账户的结息状态
        processSettleInterestStatus(request,
                response,
                loanAccount,
                handler,
                tradingRecord);
        
        // 交易记录中记录交易完成后贷款账户状态<br/>
        afterProcessTradingRecord(request, loanAccount, tradingRecord);
        
        // 持久化交易
        persistTradingRecord(loanAccount,
                handler,
                tradingRecord,
                chargeRecordEntryList,
                chargeRecordList,
                exemptRecordEntryList,
                exemptRecordList,
                paymentRecordEntryList,
                paymentRecordList,
                tradingRecordEntryList);
        
        // 触发内部事件Trading,charge,.....
        triggerTradingEvent(request,
                loanAccount,
                handler,
                tradingRecord,
                chargeRecordList,
                exemptRecordList,
                paymentRecordList);
        
    }
    
    /**
     * @param request
     * @param response
     */
    @Override
    protected void afterHandle(AbstractTradingRequest request,
            CommandResponse response) {
        this.receiver.afterHandle(request,
                response,
                loanAccount,
                handler,
                tradingRecord,
                chargeRecordList,
                exemptRecordList,
                paymentRecordList);
        
        // 记录操作日志
        ServiceLoggerUtils.log(new LARequestLog(request,
                MessageUtils.format("账户交易_[{}]_类型分类_[{}]_类型_[{}]",
                        new Object[] { loanAccountId,
                                tradingRecord.getCategory(),
                                tradingRecord.getType() })));
    }
    
    /** 
     * 在交易postHandle后进贷款账户信息的处理
     * <功能详细描述>
     * @param loanAccountId
     * @param loanAccount
     * @param handler
     * @param paymentScheduleMap
     * @param tradingRecord [参数说明]
     * 
     * @return void [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    private void processLoanAccountInfoAfterPostHandle(String loanAccountId,
            LoanAccount loanAccount, PaymentScheduleHandler handler,
            LATradingRecord tradingRecord) {
        loanAccount.setNextRepayDate(handler.getNextRepayDate());
    }
    
    /**
     * 处理贷款账户状态<br/>
     * <功能详细描述>
     * @param request
     * @param response
     * @param loanAccount
     * @param handler
     * @param paymentScheduleMap
     * @param tradingRecord
     *            [参数说明]
     * 
     * @return void [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    private void processAccountStatus(AbstractTradingRequest request,
            CommandResponse response, LoanAccount loanAccount,
            PaymentScheduleHandler handler, LATradingRecord tradingRecord) {
        AccountStatusEnum sourceAccountStatus = loanAccount.getAccountStatus();// 原账户状态
        AccountStatusEnum newAccountStatus = sourceAccountStatus;
        
        switch (sourceAccountStatus) {
            case AC:
            case ES:
            case RS:
            case ESL:
            case RSL:
            case FP:
            case SL:
                switch (tradingRecord.getCategory()) {
                    case LOAN:
                        //发放贷款
                        newAccountStatus = AccountStatusEnum.AC;
                        break;
                    case REVOKE_LOAN:
                        //贷款退回
                        newAccountStatus = AccountStatusEnum.ACCN;
                        break;
                    case REPAY:
                    case SETTLE:
                    case EXEMPT:
                    case REVOKE_CHARGE:
                    case SUSPEND:
                    case SUSPEND_RECEIVED:
                        if (handler.isFPSettle()) {
                            switch (tradingRecord.getType()) {
                                case EARLY_SETTLE:
                                    newAccountStatus = AccountStatusEnum.ES;
                                    break;
                                case RENEW_SETTLE:
                                    newAccountStatus = AccountStatusEnum.RS;
                                    break;
                                default:
                                    switch (sourceAccountStatus) {
                                        case ESL:
                                            newAccountStatus = AccountStatusEnum.ES;
                                            break;
                                        case RSL:
                                            newAccountStatus = AccountStatusEnum.RS;
                                            break;
                                        default:
                                            newAccountStatus = AccountStatusEnum.FP;
                                            break;
                                    }
                                    break;
                            }
                            loanAccount.setSettleDate(
                                    tradingRecord.getCreateDate());
                            loanAccount.setSettleRepayDate(
                                    tradingRecord.getRepayDate());
                            loanAccount.setSettle(true);
                        } else if (handler.isSLSettle()) {
                            switch (tradingRecord.getType()) {
                                case EARLY_SETTLE:
                                    newAccountStatus = AccountStatusEnum.ESL;
                                    break;
                                case RENEW_SETTLE:
                                    newAccountStatus = AccountStatusEnum.RSL;
                                    break;
                                default:
                                    newAccountStatus = AccountStatusEnum.SL;
                                    break;
                            }
                            loanAccount.setSettleSLDate(
                                    tradingRecord.getCreateDate());
                            loanAccount.setSettleSLRepayDate(
                                    tradingRecord.getRepayDate());
                            loanAccount.setSettleSL(true);
                        } else {
                            newAccountStatus = AccountStatusEnum.AC;
                        }
                        break;
                    case CHARGE:
                    case REVOKE_REPAY:
                    case REVOKE_SETTLE:
                    case REVOKE_EXEMPT:
                    case REVOKE_SUSPEND:
                    default:
                        if (handler.isFPSettle()) {
                            newAccountStatus = sourceAccountStatus;
                        } else if (handler.isSLSettle()) {
                            switch (sourceAccountStatus) {
                                case ES:
                                    newAccountStatus = AccountStatusEnum.ESL;
                                    break;
                                case RS:
                                    newAccountStatus = AccountStatusEnum.RSL;
                                    break;
                                default:
                                    newAccountStatus = AccountStatusEnum.SL;
                                    break;
                            }
                            break;
                        } else {
                            newAccountStatus = AccountStatusEnum.AC;
                        }
                        break;
                }
                break;
            case WO:
            case WFP:
            case WSL:
                //TODO:账户注销时的结息状态变更需求尚未确定，需要待账户注销逻辑梳理清楚以后进行处理
                //break;
            case ACCN:
            default:
                AssertUtils.isTrue(false,
                        "错误的结息状态变更：尚未添加相关业务逻辑.",
                        new Object[] { sourceAccountStatus });
                
        }
        AssertUtils.notNull(newAccountStatus,
                "newAccountStatus is null.requestType:{}",
                new Object[] { request.getClass() });
        loanAccount.setAccountStatus(newAccountStatus);
    }
    
    /**
     * 处理逾期相关信息： 1、修改账户是否逾期状态 2、修改账户逾期日期，如果不逾期了，将逾期日期修改为空
     * 3、如果刚刚逾期，并且账户状态为NA，则将账户催收状态修改为DQ 4、如果不逾期了，并且账户状态为DQ,CO,则将账户状态修改为NA,
     * 并且如果账户为停息状态，将账户状态修改为，正常 <功能详细描述>
     * 
     * @param request
     * @param response
     * @param loanAccount
     * @param handler
     * @param paymentScheduleMap
     * @param tradingRecord
     *            [参数说明]
     * 
     * @return void [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    private void processCollectionStatus(AbstractTradingRequest request,
            CommandResponse response, LoanAccount loanAccount,
            PaymentScheduleHandler handler, LATradingRecord tradingRecord) {
        // 根据还款计划更新贷款账户
        boolean targetOverdueFlag = handler.isOverdue();
        Date targetOverdueDate = handler.getOverdueDate();
        CollectionStatusEnum newCollectionStatus = loanAccount
                .getCollectionStatus();
        if (targetOverdueFlag) {
            // 如果账户状态为逾期
            switch (loanAccount.getCollectionStatus()) {
                case NA:
                    newCollectionStatus = CollectionStatusEnum.DQ;
                    break;
                default:
                    // DQ,OA,RA状态的催收状态不发生变化
                    break;
            }
        } else {
            // 如果账户状态为不逾期
            switch (loanAccount.getCollectionStatus()) {
                case DQ:
                case CO:
                    newCollectionStatus = CollectionStatusEnum.NA;
                    break;
                default:
                    // NA,OA,RA状态的催收状态不发生变化
                    break;
            }
        }
        loanAccount.setOverdue(targetOverdueFlag);
        if (targetOverdueFlag) {
            AssertUtils.notNull(targetOverdueDate,
                    "targetOverdueDate is null.");
            loanAccount.setOverdueDate(targetOverdueDate);
        } else {
            loanAccount.setOverdueDate(null);
        }
        loanAccount.setCollectionStatus(newCollectionStatus);
    }
    
    /**
     * 处理结息状态的联动变化 <功能详细描述>
     * 
     * @param request
     * @param response
     * @param loanAccount
     * @param handler
     * @param paymentScheduleMap
     * @param tradingRecord
     *            [参数说明]
     * 
     * @return void [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    private void processSettleInterestStatus(AbstractTradingRequest request,
            CommandResponse response, LoanAccount loanAccount,
            PaymentScheduleHandler handler, LATradingRecord tradingRecord) {
        // 根据还款计划更新贷款账户
        AccountStatusEnum accountStatus = loanAccount.getAccountStatus();
        SettleInterestStatusEnum sourceSettleInterestStatus = loanAccount
                .getSettleInterestStatus();
        
        SettleInterestStatusEnum newSettleInterestStatus = null;
        switch (accountStatus) {
            case ACCN:
                switch (sourceSettleInterestStatus) {
                    case WSI:
                        newSettleInterestStatus = SettleInterestStatusEnum.CWSI;
                        break;
                    default:
                        AssertUtils.isTrue(false,
                                "错误的结息状态变更：accountStatus:{} sourceSettleInterestStatus:{}",
                                new Object[] { accountStatus,
                                        sourceSettleInterestStatus });
                        break;
                }
                break;
            case ES:
            case ESL:
            case RS:
            case SL:
            case FP:
                switch (sourceSettleInterestStatus) {
                    case FSI:
                    case WSI:
                        newSettleInterestStatus = sourceSettleInterestStatus;
                        break;
                    case SSI:
                        newSettleInterestStatus = SettleInterestStatusEnum.WSI;
                        break;
                    case CWSI:
                    default:
                        AssertUtils.isTrue(false,
                                "错误的结息状态变更：accountStatus:{} sourceSettleInterestStatus:{}",
                                new Object[] { accountStatus,
                                        sourceSettleInterestStatus });
                        break;
                }
                break;
            case AC:
                switch (sourceSettleInterestStatus) {
                    case SSI:
                        //停息状态只有在还到不逾期后才会将状态由停息迁移至非停息状态
                        if (!loanAccount.isOverdue()) {
                            //如果已经不逾期，或者逾期天数小于91天
                            newSettleInterestStatus = SettleInterestStatusEnum.WSI;
                        } else {
                            newSettleInterestStatus = sourceSettleInterestStatus;
                        }
                        break;
                    case WSI:
                    case FSI:
                        if (loanAccount.isOverdue() && DateUtils
                                .calculateNumberOfDaysBetween(new Date(),
                                        loanAccount
                                                .getOverdueDate()) >= LoanAccountConstants.STOP_TAX_SETTLE_DAYS) {
                            //如果已经逾期，并且逾期天数大于或等于91天，将结息状态迁移至正常
                            newSettleInterestStatus = SettleInterestStatusEnum.SSI;
                        } else {
                            //否则结息状态保持不变：正常或结清待结息
                            newSettleInterestStatus = SettleInterestStatusEnum.WSI;
                        }
                        break;
                    case CWSI:
                    default:
                        AssertUtils.isTrue(false,
                                "错误的结息状态变更：accountStatus:{} sourceSettleInterestStatus:{}",
                                new Object[] { accountStatus,
                                        sourceSettleInterestStatus });
                        break;
                }
                break;
            case WO:
            case WFP:
            case WSL:
                // TODO:账户注销时的结息状态变更需求尚未确定，需要待账户注销逻辑梳理清楚以后进行处理
                //break;
            default:
                AssertUtils.isTrue(false,
                        "错误的结息状态变更：accountStatus:{} sourceSettleInterestStatus:{}",
                        new Object[] { accountStatus,
                                sourceSettleInterestStatus });
                newSettleInterestStatus = sourceSettleInterestStatus;
                break;
        }
        loanAccount.setSettleInterestStatus(newSettleInterestStatus);
    }
    
    /**
     * 触发交易相关事件 2、触发账户，逾期事件，或逾期结束事件，或逾期日期变更事件
     * 
     * @param request
     * @param loanAccount
     * @param paymentScheduleMap
     * @param tradingRecord
     * @param chargeRecordList
     * @param delayRecord
     * @param exemptRecordList
     * @param paymentRecordList
     *            [参数说明]
     * 
     * @return void [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    private void triggerTradingEvent(AbstractTradingRequest request,
            LoanAccount loanAccount, PaymentScheduleHandler handler,
            LATradingRecord tradingRecord, List<ChargeRecord> chargeRecordList,
            List<ExemptRecord> exemptRecordList,
            List<PaymentRecord> paymentRecordList) {
        if (!CollectionUtils.isEmpty(chargeRecordList)) {
            EventContext.getContext().trigger(new ChargeEvent(loanAccount,
                    tradingRecord, handler, request, chargeRecordList));
        }
        if (!CollectionUtils.isEmpty(exemptRecordList)) {
            EventContext.getContext().trigger(new ExemptEvent(loanAccount,
                    tradingRecord, handler, request, exemptRecordList));
        }
        if (!CollectionUtils.isEmpty(paymentRecordList)) {
            EventContext.getContext().trigger(new PaymentEvent(loanAccount,
                    tradingRecord, handler, request, paymentRecordList));
        }
        if (!CollectionUtils
                .isEmpty(tradingRecord.getTradingRecordEntryList())) {
            //
        }
        // 触发交易事件
        EventContext.getContext()
                .trigger(new TradingEvent(loanAccount, tradingRecord, handler,
                        request, chargeRecordList, exemptRecordList,
                        paymentRecordList));
        
        // 发送逾期事件
        if (tradingRecord.isAfterIsOverdue()
                && !tradingRecord.isBeforeIsOverdue()) {
            TransactionSynchronizationManager.registerSynchronization(
                    new TransactionSynchronizationAdapter() {
                        @Override
                        public void beforeCommit(boolean readOnly) {
                            EventContext.getContext().trigger(new OverdueEvent(
                                    loanAccount, loanAccount.getOverdueDate()));
                            super.beforeCommit(readOnly);
                        }
                    });
        } else if (!tradingRecord.isAfterIsOverdue()
                && tradingRecord.isBeforeIsOverdue()) {
            final LoanAccount finalLoanAccount = loanAccount;
            TransactionSynchronizationManager.registerSynchronization(
                    new TransactionSynchronizationAdapter() {
                        @Override
                        public void beforeCommit(boolean readOnly) {
                            EventContext.getContext().trigger(
                                    new OverdueOverEvent(finalLoanAccount));
                            super.beforeCommit(readOnly);
                        }
                    });
        } else if (tradingRecord.getBeforeOverdueDate() != null
                && tradingRecord.getAfterOverdueDate() != null) {
            if (DateUtils.compareByDay(tradingRecord.getBeforeOverdueDate(),
                    tradingRecord.getAfterOverdueDate()) != 0) {
                //一般来说逾期日期发生变更时，逾期金额也会伴随发生变化，所以在逾期日期变更时，则不需要再发送逾期金额变更事件
                EventContext.getContext()
                        .trigger(new OverdueDateChangeEvent(loanAccount,
                                tradingRecord, handler,
                                tradingRecord.getBeforeOverdueDate(),
                                tradingRecord.getAfterOverdueDate(), request));
            } else if (tradingRecord.getBeforeOverdueAmount()
                    .compareTo(tradingRecord.getAfterOverdueAmount()) != 0) {
                EventContext.getContext().trigger(new OverdueAmountChangeEvent(
                        loanAccount, tradingRecord, handler,
                        tradingRecord.getBeforeOverdueAmount(),
                        tradingRecord.getAfterOverdueAmount(), request));
            }
        }
    }
    
    /**
     * 持久化交易记录<br/>
     * <功能详细描述>
     * 
     * @param handler
     * @param tradingRecord
     * @param chargeRecordEntryList
     * @param chargeRecordList
     * @param delayRecord
     * @param delayRecordEntryList
     * @param exemptRecordEntryList
     * @param exemptRecordList
     * @param paymentRecordEntryList
     * @param paymentRecordList
     * @param tradingRecordEntryList
     *            [参数说明]
     * 
     * @return void [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    private void persistTradingRecord(LoanAccount loanAccount,
            PaymentScheduleHandler handler, LATradingRecord tradingRecord,
            List<ChargeRecordEntry> chargeRecordEntryList,
            List<ChargeRecord> chargeRecordList,
            List<ExemptRecordEntry> exemptRecordEntryList,
            List<ExemptRecord> exemptRecordList,
            List<PaymentRecordEntry> paymentRecordEntryList,
            List<PaymentRecord> paymentRecordList,
            List<LATradingRecordEntry> tradingRecordEntryList) {
        // 持久化还款计划<br/>
        paymentScheduleHandlerHelper.persist(handler, tradingRecord);
        
        // 持久化tradingRecord
        this.tradingRecordService.insert(tradingRecord);
        if (!CollectionUtils.isEmpty(tradingRecordEntryList)) {
            this.tradingRecordEntryService.batchInsert(tradingRecordEntryList);
        }
        // 持久化chargeRecord
        if (!CollectionUtils.isEmpty(chargeRecordList)) {
            this.chargeRecordService.batchInsert(chargeRecordList);
            this.chargeRecordEntryService.batchInsert(chargeRecordEntryList);
        }
        // 持久化exemptRecord
        if (!CollectionUtils.isEmpty(exemptRecordList)) {
            this.exemptRecordService.batchInsert(exemptRecordList);
            this.exemptRecordEntryService.batchInsert(exemptRecordEntryList);
        }
        // 持久化paymentRecord
        if (!CollectionUtils.isEmpty(paymentRecordList)) {
            this.paymentRecordService.batchInsert(paymentRecordList);
            this.paymentRecordEntryService.batchInsert(paymentRecordEntryList);
        }
        
        // 更新贷款账户的：
        // 最后更新时间
        // 最后一笔交易及记录
        // 如果不逾期了(更新逾期时间为空、逾期为false)
        // 更新逾期金额、逾期总额
        // 更新本金结余、递减本金结余
        // 更新贷后延期天数
        // 更新当前期数\已还期数
        // 更新：是否存在未收款的交易、是否存在暂缓交易
        this.loanAccountService.updateWhenTradingRequestHandle(loanAccount);
    }
    
    /**
     * 根据计费记录计算本金结余 <功能详细描述>
     * 
     * @param loanAccount
     * @param paymentScheduleMap
     * @param tradingRecord
     * @param paymentRecordList
     * @param principalBalance
     * @param principalBalanceIrr
     *            [参数说明]
     * 
     * @return void [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    private void calculatePrincipalBalanceByChargeRecord(
            LoanAccount loanAccount, PaymentScheduleHandler handler,
            LATradingRecord tradingRecord, List<ChargeRecord> chargeRecordList,
            BigDecimal principalBalance, BigDecimal principalBalanceIrr) {
        if (CollectionUtils.isEmpty(chargeRecordList)) {
            // 设置交易的本金结余
            tradingRecord.setPrincipalBalance(principalBalance);
            tradingRecord.setPrincipalBalanceIrr(principalBalanceIrr);
            loanAccount.setPrincipalBalance(principalBalance);
            loanAccount.setPrincipalBalanceIrr(principalBalanceIrr);
            return;
        }
        //ScheduleAssociateMap<PaymentSchedule> psMap = this.handler.getPaymentScheduleMap();
        BigDecimal mainPrincipalBalanceChangeAmount = BigDecimal.ZERO;
        BigDecimal irrPrincipalBalanceChangeAmount = BigDecimal.ZERO;
        //为1~n以外的期数计算本金结余
        for (ChargeRecord chargeRecord : chargeRecordList) {
            ScheduleTypeEnum scheduleType = chargeRecord.getScheduleType();
            //String period = chargeRecord.getPeriod();
            BigDecimal changeAmount = BigDecimal.ZERO;
            // NA,WO等期数的处理
            // 如果含有，取出其中还款分项判断其中是否含有为本金的费用项
            for (ChargeRecordEntry chargeRecordEntry : chargeRecord
                    .getChargeRecordEntryList()) {
                FeeItemEnum feeItem = chargeRecordEntry.getFeeItem();
                if (!this.feeItemCfgMap.get(feeItem).isPrincipal()) {
                    // 如果对应的费用类型非本金直接检查下一个
                    continue;
                }
                changeAmount = chargeRecordEntry.getAmount();
                if (ScheduleTypeEnum.MAIN.equals(scheduleType)) {
                    mainPrincipalBalanceChangeAmount = mainPrincipalBalanceChangeAmount
                            .add(changeAmount);
                } else if (ScheduleTypeEnum.IRR.equals(scheduleType)) {
                    irrPrincipalBalanceChangeAmount = irrPrincipalBalanceChangeAmount
                            .add(changeAmount);
                }
            }
        }
        tradingRecord.setPrincipalBalance(
                principalBalance.add(mainPrincipalBalanceChangeAmount));
        tradingRecord.setPrincipalBalanceIrr(
                principalBalanceIrr.add(irrPrincipalBalanceChangeAmount));
        loanAccount.setPrincipalBalance(
                principalBalance.add(mainPrincipalBalanceChangeAmount));
        loanAccount.setPrincipalBalanceIrr(
                principalBalanceIrr.add(irrPrincipalBalanceChangeAmount));
    }
    
    /**
     * 更急实收计算本金结余 <功能详细描述>
     * 
     * @param loanAccount
     * @param paymentScheduleMap
     * @param tradingRecord
     * @param paymentRecordList
     * @param principalBalance
     * @param principalBalanceIrr
     * 
     * @return void [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    private void calculatePrincipalBalanceByPaymentRecord(
            LATradingRecord tradingRecord,
            List<PaymentRecord> paymentRecordList, BigDecimal principalBalance,
            BigDecimal principalBalanceIrr) {
        if (CollectionUtils.isEmpty(paymentRecordList)) {
            // 设置交易的本金结余
            tradingRecord.setPrincipalBalance(principalBalance);
            tradingRecord.setPrincipalBalanceIrr(principalBalanceIrr);
            loanAccount.setPrincipalBalance(principalBalance);
            loanAccount.setPrincipalBalanceIrr(principalBalanceIrr);
            return;
        }
        Collections.sort(paymentRecordList,
                PaymentRecordHelper.PERIOD_COMPARATOR);
        //为1~n以外的期数计算本金结余
        ScheduleAssociateMap<PaymentSchedule> psMap = this.handler
                .getPaymentScheduleMap();
        BigDecimal mainPrincipalBalanceChangeAmount = BigDecimal.ZERO;
        BigDecimal irrPrincipalBalanceChangeAmount = BigDecimal.ZERO;
        for (PaymentRecord paymentRecord : paymentRecordList) {
            ScheduleTypeEnum scheduleType = paymentRecord.getScheduleType();
            String period = paymentRecord.getPeriod();
            PaymentSchedule psTemp = psMap.get(scheduleType, period);
            BigDecimal changeAmount = BigDecimal.ZERO;
            BigDecimal waitReceivePrincipalBalance = BigDecimal.ZERO;
            // NA,WO等期数的处理
            // 如果含有，取出其中还款分项判断其中是否含有为本金的费用项
            for (PaymentRecordEntry paymentRecordEntry : paymentRecord
                    .getPaymentRecordEntryList()) {
                FeeItemEnum feeItem = paymentRecordEntry.getFeeItem();
                if (!this.feeItemCfgMap.get(feeItem).isPrincipal()) {
                    // 如果对应的费用类型非本金直接检查下一个
                    continue;
                }
                changeAmount = paymentRecordEntry.getAmount();
                if (ScheduleTypeEnum.MAIN.equals(scheduleType)) {
                    mainPrincipalBalanceChangeAmount = mainPrincipalBalanceChangeAmount
                            .add(changeAmount);
                } else if (ScheduleTypeEnum.IRR.equals(scheduleType)) {
                    irrPrincipalBalanceChangeAmount = irrPrincipalBalanceChangeAmount
                            .add(changeAmount);
                }
                PaymentScheduleEntry pse = this.handler
                        .getPaymentScheduleEntryMap()
                        .get(scheduleType, period, feeItem);
                waitReceivePrincipalBalance = waitReceivePrincipalBalance
                        .add(pse.getReceivableAmount()
                                .add(pse.getExemptAmount())
                                .subtract(pse.getActualReceivedAmount()));
            }
            //实收记录的本金结余 = 还款计划的本金结余 +待收
            if (psTemp.getPrincipalBalance() != null) {
                paymentRecord.setPrincipalBalance(psTemp.getPrincipalBalance()
                        .add(waitReceivePrincipalBalance));
            }
        }
        tradingRecord.setPrincipalBalance(
                principalBalance.subtract(mainPrincipalBalanceChangeAmount));
        tradingRecord.setPrincipalBalanceIrr(
                principalBalanceIrr.subtract(irrPrincipalBalanceChangeAmount));
        loanAccount.setPrincipalBalance(
                principalBalance.subtract(mainPrincipalBalanceChangeAmount));
        loanAccount.setPrincipalBalanceIrr(
                principalBalanceIrr.subtract(irrPrincipalBalanceChangeAmount));
    }
    
    /**
     * 计算逾期金额、逾期金额总额 <功能详细描述>
     * @param paymentScheduleMap
     * @param loanAccount
     * 
     * @return void [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    public void calculateOverduleAmount(LoanAccount loanAccount,
            PaymentScheduleHandler psHandler,
            AbstractTradingReceiver<?> receiver,
            AbstractTradingRequest request) {
        Date now = new Date();
        BigDecimal overdueAmount = PaymentScheduleEntryHelper
                .calculateOverdueAmount(loanAccount.getFeeItemCfgMap(),
                        psHandler.getPaymentScheduleMap(),
                        psHandler.getPaymentScheduleEntryMap(),
                        ScheduleTypeEnum.MAIN,
                        now);
        BigDecimal overdueSum = PaymentScheduleEntryHelper.calculateOverdueSum(
                psHandler.getPaymentScheduleMap(),
                psHandler.getPaymentScheduleEntryMap(),
                ScheduleTypeEnum.MAIN,
                now);
        loanAccount.setOverdueSum(overdueSum);
        loanAccount.setOverdueAmount(overdueAmount);
    }
    
    /**
     * 前置交易记录处理 对交易记录中的request相关信息 loanAccount相关信息 默认信息等进行写入 <功能详细描述>
     * 
     * @param request
     * @param loanAccount
     * @param tradingRecord
     *            [参数说明]
     * 
     * @return void [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    private void preProcessTradingRecord(AbstractTradingRequest request,
            LoanAccount loanAccount, LATradingRecord tradingRecord) {
        AssertUtils.notNull(request, "request is null.");
        AssertUtils.notNull(tradingRecord, "tradingRecord is null.");
        
        Date nowTime = new Date();
        
        // 设置时间
        tradingRecord.setLastUpdateDate(request.getCreateDate() != null
                ? request.getCreateDate() : nowTime);
        tradingRecord.setCreateDate(request.getCreateDate() != null
                ? request.getCreateDate() : nowTime);
        
        //设置交易id
        if (StringUtils.isEmpty(tradingRecord.getId())) {
            tradingRecord.setId(LATradingRecordHelper
                    .generateLATradingRecordId(tradingRecord.getCreateDate()));
        }
        
        //交易的vcid应当与贷款账户一致
        tradingRecord.setVcid(loanAccount.getVcid());
        tradingRecord.setLoanAccountId(loanAccount.getId());
        
        // 交易与请求相关信息
        tradingRecord.setRequestId(request.getId());// 请求id
        tradingRecord.setOrganizationId(request.getOrganizationId());
        tradingRecord.setOperatorId(request.getOperatorId());
        tradingRecord.setLastUpdateOperatorId(request.getOperatorId());
        
        // 设置交易类型及交易记录类型
        tradingRecord.setCategory(request.getTradingCategory());
        tradingRecord.setType(request.getTradingRecordType());
        tradingRecord.setSummary(request.getTradingSummary());
        
        // 构建交易的撤销相关信息
        tradingRecord.setRevoked(false);
        //tradingRecord.setRevokeResean(null);
        tradingRecord.setRevokeOperatorId(null);
        
        // 填入交易与贷款账户相关的信息
        tradingRecord.setWriteOff(loanAccount.isWriteOff());
        
        // 获取
        if (StringUtils.isEmpty(tradingRecord.getCurrentPeriod())) {
            tradingRecord.setCurrentPeriod(loanAccount.getCurrentPeriod());
        }
        //如果还期数为空，则显示为当前期数
        if (StringUtils.isEmpty(tradingRecord.getRepayDatePeriod())) {
            tradingRecord.setRepayDatePeriod(loanAccount.getCurrentPeriod());
        }
        
        // 设置交易前置信息
        tradingRecord.setBeforeAccountStatus(loanAccount.getAccountStatus());
        tradingRecord
                .setBeforeCollectionStatus(loanAccount.getCollectionStatus());
        tradingRecord.setBeforeIsClose(loanAccount.isClosed());
        tradingRecord.setBeforeIsDie(loanAccount.isDied());
        tradingRecord.setBeforeIsLegalProcedure(loanAccount.isLegalProcedure());
        tradingRecord.setBeforeSettleInterestStatus(
                loanAccount.getSettleInterestStatus());
        tradingRecord
                .setBeforePrincipalBalance(loanAccount.getPrincipalBalance());
        tradingRecord.setBeforePrincipalBalanceIrr(
                loanAccount.getPrincipalBalanceIrr());
        tradingRecord.setBeforeIsOverdue(loanAccount.isOverdue());
        tradingRecord.setBeforeOverdueDate(loanAccount.getOverdueDate());
        tradingRecord.setBeforeOverdueAmount(loanAccount.getOverdueAmount());
        tradingRecord.setBeforeOverdueSum(loanAccount.getOverdueSum());
        
        tradingRecord.setPrincipalBalance(loanAccount.getPrincipalBalance());
        tradingRecord
                .setPrincipalBalanceIrr(loanAccount.getPrincipalBalanceIrr());
    }
    
    /**
     * 后置交易处理用于记录交易完成后贷款账户的信息 <功能详细描述>
     * 
     * @param request
     * @param loanAccount
     * @param tradingRecord
     *            [参数说明]
     * 
     * @return void [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    private void afterProcessTradingRecord(AbstractTradingRequest request,
            LoanAccount loanAccount, LATradingRecord tradingRecord) {
        // 设置交易前置信息
        tradingRecord.setAfterAccountStatus(loanAccount.getAccountStatus());
        tradingRecord
                .setAfterCollectionStatus(loanAccount.getCollectionStatus());
        tradingRecord.setAfterIsClose(loanAccount.isClosed());
        tradingRecord.setAfterIsDie(loanAccount.isDied());
        tradingRecord.setAfterIsLegalProcedure(loanAccount.isLegalProcedure());
        tradingRecord
                .setAfterPrincipalBalance(loanAccount.getPrincipalBalance());
        tradingRecord.setAfterPrincipalBalanceIrr(
                loanAccount.getPrincipalBalanceIrr());
        tradingRecord.setAfterSettleInterestStatus(
                loanAccount.getSettleInterestStatus());
        
        tradingRecord.setAfterIsOverdue(loanAccount.isOverdue());
        tradingRecord.setAfterOverdueDate(loanAccount.getOverdueDate());
        tradingRecord.setAfterOverdueAmount(loanAccount.getOverdueAmount());
        tradingRecord.setAfterOverdueSum(loanAccount.getOverdueSum());
    }
    
    /**
     * 还款 <功能详细描述>
     * 
     * @param handler
     * @param tradingRecord
     * @param paymentRecordEntryList
     * @return [参数说明]
     * 
     * @return List<PaymentRecord> [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    private List<PaymentRecord> payment(PaymentScheduleHandler handler,
            LATradingRecord tradingRecord,
            List<PaymentRecordEntry> paymentRecordEntryList) {
        List<PaymentRecord> paymentRecordList = null;
        if (CollectionUtils.isNotEmpty(paymentRecordEntryList)) {
            paymentRecordList = handler
                    .buildPaymentRecords(paymentRecordEntryList, tradingRecord);
            
            handler.payment(paymentRecordList);
        }
        return paymentRecordList;
    }
    
    /**
     * 创建计费记录分项联动还款计划变更 创建计费记录分项联动还款计划变更,并校验计费计费分项中source + amount = target
     * 生成的计费记录中也存在source + amount = target 校验成功后将记录传入paymentSchedule中联动还款计划变更
     * <功能详细描述>
     * 
     * @param chargeRecordEntryList
     * @param request
     * @param loanAccount
     * @param handler
     * @param paymentScheduleMap
     * @param tradingRecord
     * @return [参数说明]
     * 
     * @return List<ChargeRecord> [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    private List<ChargeRecord> charge(
            List<ChargeRecordEntry> chargeRecordEntryList,
            PaymentScheduleHandler handler, LATradingRecord tradingRecord) {
        List<ChargeRecord> chargeRecords = null;
        // 如果不存在计费记录，直接跳过该本分逻辑
        if (CollectionUtils.isNotEmpty(chargeRecordEntryList)) {
            // 校验计费记录分项目，并构建计费记录
            chargeRecords = handler.buildChargeRecords(chargeRecordEntryList,
                    tradingRecord);
            // 根据计费记录调整还款计划
            handler.charge(chargeRecords);
        }
        return chargeRecords;
    }
    
    /**
     * 创建还款记录分项联动还款计划变更 <功能详细描述>
     * 
     * @param request
     * @param loanAccount
     * @param handler
     * @param paymentScheduleMap
     * @param tradingRecord
     *            [参数说明]
     * 
     * @return void [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    private List<ExemptRecord> exempt(
            List<ExemptRecordEntry> exemptRecordEntryList,
            PaymentScheduleHandler handler, LATradingRecord tradingRecord) {
        List<ExemptRecord> exemptRecordList = null;
        if (CollectionUtils.isNotEmpty(exemptRecordEntryList)) {
            // 构建豁免记录
            exemptRecordList = handler.buildExemptRecords(exemptRecordEntryList,
                    tradingRecord);
            // 根据豁免调整还款计划
            handler.exempt(exemptRecordList);
        }
        return exemptRecordList;
    }
}
