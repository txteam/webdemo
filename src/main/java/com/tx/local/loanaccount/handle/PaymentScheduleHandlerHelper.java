/*
 * 描          述:  <描述>
 * 修  改   人:  Administrator
 * 修改时间:  2015年3月4日
 * <修改描述:>
 */
package com.tx.local.loanaccount.handle;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.tx.component.command.context.AbstractHelper;
import com.tx.core.exceptions.util.AssertUtils;
import com.tx.core.util.DateUtils;
import com.tx.local.basicdata.model.FeeItemCfg;
import com.tx.local.basicdata.model.FeeItemEnum;
import com.tx.local.loanaccount.LoanAccountConstants;
import com.tx.local.loanaccount.helper.feecfgitem.LoanAccountFeeItemLazyMap;
import com.tx.local.loanaccount.model.LATradingRecord;
import com.tx.local.loanaccount.model.LoanAccount;
import com.tx.local.loanaccount.model.LoanAccountFeeItem;
import com.tx.local.loanaccount.model.PaymentSchedule;
import com.tx.local.loanaccount.model.PaymentScheduleEntry;
import com.tx.local.loanaccount.model.ScheduleTypeEnum;
import com.tx.local.loanaccount.service.LoanAccountFeeItemService;
import com.tx.local.loanaccount.service.LoanAccountService;
import com.tx.local.loanaccount.service.PaymentScheduleEntryService;
import com.tx.local.loanaccount.service.PaymentScheduleService;
import com.tx.local.loanaccount.strategy.loanaccount.LoanAccountStrategyHelper;

/**
 * 还款计划处理器工厂<br/>
 * <功能详细描述>
 * 
 * @author Administrator
 * @version [版本号, 2015年3月4日]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
@Component("paymentScheduleHandlerHelper")
public class PaymentScheduleHandlerHelper
        extends AbstractHelper<PaymentScheduleHandlerHelper> {
    
    private Logger logger = LoggerFactory
            .getLogger(PaymentScheduleHandler.class);
    
    /** 贷款账户业务层 */
    @Resource(name = "loanAccountService")
    private LoanAccountService loanAccountService;
    
    /** 贷款账户费用项业务层 */
    @Resource(name = "loanAccountFeeItemService")
    private LoanAccountFeeItemService loanAccountFeeItemService;
    
    /** 还款计划记录 service */
    @Resource(name = "paymentScheduleService")
    private PaymentScheduleService paymentScheduleService;
    
    /** 还款计划分项记录 service */
    @Resource(name = "paymentScheduleEntryService")
    private PaymentScheduleEntryService paymentScheduleEntryService;
    
    /**
     * 根据贷款账户构建还款计划处理器<br/>
     * <功能详细描述>
     * @param loanAccount
     * @return [参数说明]
     * 
     * @return PaymentScheduleHandler [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    public PaymentScheduleHandler buildPaymentScheduleHandler(
            LoanAccount loanAccount) {
        AssertUtils.notNull(loanAccount, "loanAccount is null.");
        AssertUtils.notEmpty(loanAccount.getId(), "loanAccount.id is empty.");
        
        String loanAccountId = loanAccount.getId();
        Map<FeeItemEnum, FeeItemCfg> feeItemCfgMap = loanAccount
                .getFeeItemCfgMap();
        if (feeItemCfgMap == null) {
            AssertUtils.notNull(
                    LoanAccountStrategyHelper.getStrategy(loanAccount),
                    "getStratety is null.loanAccountId:{}",
                    new Object[] { loanAccount.getId() });
            feeItemCfgMap = LoanAccountStrategyHelper.getStrategy(loanAccount)
                    .getFeeItemCfgMap();
            loanAccount.setFeeItemCfgMap(feeItemCfgMap);
        }
        Map<FeeItemEnum, LoanAccountFeeItem> feeItemMap = loanAccount
                .getFeeItemMap();
        if (feeItemMap == null) {
            List<LoanAccountFeeItem> feeItemList = loanAccountFeeItemService
                    .queryListByLoanAccountId(loanAccountId);
            feeItemMap = new LoanAccountFeeItemLazyMap(loanAccountId,
                    feeItemList);
            loanAccount.setFeeItemMap(feeItemMap);
        }
        List<PaymentSchedule> paymentScheduleList = this.paymentScheduleService
                .queryListByLoanAccountId(loanAccountId, null);
        List<PaymentScheduleEntry> paymentScheduleEntryList = this.paymentScheduleEntryService
                .queryListByLoanAccountId(loanAccountId, null);
        PaymentScheduleHandler handler = new PaymentScheduleHandler(loanAccount,
                feeItemCfgMap, feeItemMap, paymentScheduleList,
                paymentScheduleEntryList);
        
        return handler;
    }
    
    /**
     * 根据贷款账户构建还款计划处理器<br/>
     * <功能详细描述>
     * @param loanAccount
     * @return [参数说明]
     * 
     * @return PaymentScheduleHandler [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    public PaymentScheduleHandler buildPaymentScheduleHandler(
            LoanAccount loanAccount, List<PaymentSchedule> paymentScheduleList,
            List<PaymentScheduleEntry> paymentScheduleEntryList) {
        AssertUtils.notNull(loanAccount, "loanAccount is null.");
        AssertUtils.notEmpty(loanAccount.getId(), "loanAccount.id is empty.");
        AssertUtils.notEmpty(paymentScheduleList,
                "paymentScheduleList is empty.");
        AssertUtils.notEmpty(paymentScheduleEntryList,
                "paymentScheduleEntryList is empty.");
        
        String loanAccountId = loanAccount.getId();
        Map<FeeItemEnum, FeeItemCfg> feeItemCfgMap = loanAccount
                .getFeeItemCfgMap();
        if (feeItemCfgMap == null) {
            AssertUtils.notNull(
                    LoanAccountStrategyHelper.getStrategy(loanAccount),
                    "getStratety is null.loanAccountId:{}",
                    new Object[] { loanAccount.getId() });
            feeItemCfgMap = LoanAccountStrategyHelper.getStrategy(loanAccount)
                    .getFeeItemCfgMap();
        }
        Map<FeeItemEnum, LoanAccountFeeItem> feeItemMap = loanAccount
                .getFeeItemMap();
        if (feeItemMap == null) {
            List<LoanAccountFeeItem> feeItemList = loanAccountFeeItemService
                    .queryListByLoanAccountId(loanAccountId);
            feeItemMap = new LoanAccountFeeItemLazyMap(loanAccountId,
                    feeItemList);
        }
        PaymentScheduleHandler handler = new PaymentScheduleHandler(loanAccount,
                feeItemCfgMap, feeItemMap, paymentScheduleList,
                paymentScheduleEntryList);
        
        return handler;
    }
    
    /**
     * 持久化还款计划 <功能详细描述> [参数说明]
     * 
     * @return void [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    public void persist(PaymentScheduleHandler handler,
            LATradingRecord tradingRecord) {
        logger.info("---开始持久化贷款账户：[{}]还款计划：",
                new Object[] { handler.getLoanAccount().getId() });
        //校验还款计划中 每期中的 计划 与 计划分项目 是否匹配
        //校验还款计划中 平息应收之和 + 平息豁免之和 = 递减应收 + 递减豁免  平息实收之和 == 递减实收之和
        logger.info("   ---校验还款计划：",
                new Object[] { handler.getLoanAccount().getId() });
        handler.validate();
        logger.info("   ---编辑还款计划结清以及逾期标致：",
                new Object[] { handler.getLoanAccount().getId() });
        //计算还款计划，其中如果存在已经结清的某一期，则将对应期数结清标志进行调整
        prePersist(handler, tradingRecord);
        //持久化新增、更新的还款计划及其分项
        if (!CollectionUtils.isEmpty(handler.getAddPaymentScheduleList())) {
            List<PaymentSchedule> psList = handler.getAddPaymentScheduleList();
            logger.info("   ---[{}]新增还款计划：'{}'条",
                    new Object[] { handler.getLoanAccount().getId(),
                            psList.size() });
            this.paymentScheduleService.batchInsert(psList);
        }
        if (!CollectionUtils.isEmpty(handler.getUpdatePaymentScheduleList())) {
            List<PaymentSchedule> psList = handler
                    .getUpdatePaymentScheduleList();
            logger.info("   ---[{}]更新还款计划：'{}'条",
                    new Object[] { handler.getLoanAccount().getId(),
                            psList.size() });
            this.paymentScheduleService.batchUpdate(psList);
        }
        if (!CollectionUtils
                .isEmpty(handler.getAddPaymentScheduleEntryList())) {
            List<PaymentScheduleEntry> pseList = handler
                    .getAddPaymentScheduleEntryList();
            logger.info("   ---[{}]新增还款计划分项：'{}'条",
                    new Object[] { handler.getLoanAccount().getId(),
                            pseList.size() });
            this.paymentScheduleEntryService.batchInsert(pseList);
        }
        if (!CollectionUtils
                .isEmpty(handler.getUpdatePaymentScheduleEntryList())) {
            List<PaymentScheduleEntry> pseList = handler
                    .getUpdatePaymentScheduleEntryList();
            logger.info("   ---[{}]更新还款计划分项：'{}'条",
                    new Object[] { handler.getLoanAccount().getId(),
                            pseList.size() });
            this.paymentScheduleEntryService.batchUpdate(pseList);
        }
        logger.info("---持久化贷款账户：[{}]成功.：", handler.getLoanAccount().getId());
    }
    
    /**
     * 结清逾期校验<br/>
     * <功能详细描述> [参数说明]
     * 
     * @return void [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    private void prePersist(PaymentScheduleHandler handler,
            LATradingRecord tradingRecord) {
        Date nowDate = new Date();
        for (ScheduleTypeEnum scheduleType : handler.getPaymentScheduleMap()
                .keySet()) {
            String nextPeriod = "1";
            while (!StringUtils.isBlank(nextPeriod)) {
                PaymentSchedule paymentSchedule = handler
                        .getPaymentScheduleMap().get(scheduleType, nextPeriod);
                
                //是否逾期仅看平息账
                BigDecimal check = paymentSchedule.getReceivableSum()
                        .add(paymentSchedule.getExemptSum())
                        .subtract(paymentSchedule.getActualReceivedSum());
                boolean isSettle = check
                        .compareTo(LoanAccountConstants.ONE_CENT) < 0 ? true
                                : false;//小于1分为结清
                if (isSettle) {
                    paymentSchedule.setSettleProcessDate(tradingRecord == null
                            ? nowDate : tradingRecord.getCreateDate());
                    paymentSchedule.setSettleRepayDate(tradingRecord == null
                            ? nowDate : tradingRecord.getRepayDate());
                }
                boolean isOverdue = false;
                //如果当日尚未到期，则是否逾期标致一定为未逾期
                if (DateUtils.compareByDay(paymentSchedule.getRepaymentDate(),
                        nowDate) < 0) {
                    if (isSettle) {
                        if (paymentSchedule.getLastRepayDate() != null
                                && DateUtils.compareByDay(
                                        paymentSchedule.getRepaymentDate(),
                                        paymentSchedule
                                                .getLastRepayDate()) <= 0) {
                            isOverdue = false;
                        } else {
                            isOverdue = true;
                        }
                    } else {
                        isOverdue = true;
                    }
                }
                
                //如果是否结清，或是否逾期的标志发生变更便更新对应记录的标志
                if (paymentSchedule.isSettle() != isSettle
                        || paymentSchedule.isOverdue() != isOverdue) {
                    paymentSchedule.setSettle(isSettle);
                    paymentSchedule.setOverdue(isOverdue);
                }
                nextPeriod = paymentSchedule.getNextPeriod();
            }
        }
    }
}
