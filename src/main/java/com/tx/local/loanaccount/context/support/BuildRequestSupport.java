/*
 * 描          述:  <描述>
 * 修  改   人:  Administrator
 * 修改时间:  2014年5月21日
 * <修改描述:>
 */
package com.tx.local.loanaccount.context.support;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.tx.component.command.context.CommandResponse;
import com.tx.component.servicelogger.util.ServiceLoggerUtils;
import com.tx.core.exceptions.util.AssertUtils;
import com.tx.core.util.MessageUtils;
import com.tx.local.basicdata.model.FeeItemCfg;
import com.tx.local.basicdata.model.FeeItemEnum;
import com.tx.local.loanaccount.context.receiver.build.AbstractBuildReceiver;
import com.tx.local.loanaccount.context.request.build.AbstractBuildRequest;
import com.tx.local.loanaccount.helper.feecfgitem.LoanAccountFeeItemLazyMap;
import com.tx.local.loanaccount.helper.paymentschedule.PaymentScheduleHelper;
import com.tx.local.loanaccount.model.LARequestLog;
import com.tx.local.loanaccount.model.LoanAccount;
import com.tx.local.loanaccount.model.LoanAccountFeeItem;
import com.tx.local.loanaccount.model.PaymentSchedule;
import com.tx.local.loanaccount.model.PaymentScheduleEntry;
import com.tx.local.loanaccount.strategy.loanaccount.LoanAccountStrategy;
import com.tx.local.loanaccount.strategy.loanaccount.LoanAccountStrategyHelper;

/**
 * 构建请求支撑实现<br/>
 * 
 * @author  Administrator
 * @version  [版本号, 2014年5月21日]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
@Scope("prototype")
@Component("buildRequestSupport")
public class BuildRequestSupport extends
        AbstractLoanAccountRequestSupport<AbstractBuildRequest, AbstractBuildReceiver<AbstractBuildRequest>> {
    
    /** 贷款账户id */
    private String loanAccountId;
    
    /** 构建出的贷款账户 */
    private LoanAccount loanAccount;
    
    /** 费用项集合 */
    private List<LoanAccountFeeItem> feeItems;
    
    /** 还款计划分项列表 */
    private List<PaymentScheduleEntry> paymentScheduleEntryList;
    
    /** 还款计划列表 */
    private List<PaymentSchedule> paymentScheduleList;
    
    /** <默认构造函数> */
    public BuildRequestSupport() {
        super();
    }
    
    /** <默认构造函数> */
    public BuildRequestSupport(
            AbstractBuildReceiver<AbstractBuildRequest> receiver) {
        super(receiver);
    }
    
    /**
     * @param request
     * @param response
     * @return
     */
    @Override
    protected boolean isLockWhenHandle(AbstractBuildRequest request,
            CommandResponse response) {
        return false;
    }
    
    /** 
     * 构建请求处理逻辑<br/>
     * <功能详细描述>
     * @param request [参数说明]
     * 
     * @return void [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    protected final void doHandle(AbstractBuildRequest request,
            CommandResponse response) {
        //校验请求
        this.receiver.validateRequest(request, response);
        
        AssertUtils.notEmpty(request.getLoanAccountId(),
                "request.loanAccountId is empty.");
        this.loanAccountId = request.getLoanAccountId();
        //贷款账户
        this.loanAccount = this.receiver.buildLoanAccount(request);
        //利用构建请求中贷款账户id来生成贷款账户
        this.loanAccount.setId(request.getLoanAccountId());
        //费用配置项
        this.feeItems = this.receiver.buildLoanAccountFeeItems(request,
                loanAccount);
        
        //获取贷款账户对应的处理策略
        LoanAccountStrategy laStrategy = LoanAccountStrategyHelper
                .getStrategy(loanAccount);
        Map<FeeItemEnum, FeeItemCfg> feeItemCfgMap = laStrategy
                .getFeeItemCfgMap();
        this.loanAccount.setFeeItemCfgMap(feeItemCfgMap);
        
        //费用项
        Map<FeeItemEnum, LoanAccountFeeItem> feeItemMap = new LoanAccountFeeItemLazyMap(
                this.loanAccountId, this.feeItems);
        for (Entry<FeeItemEnum, FeeItemCfg> entryTemp : feeItemCfgMap
                .entrySet()) {
            FeeItemEnum feeItemTemp = entryTemp.getKey();
            if (feeItemMap.containsKey(feeItemTemp)) {
                continue;
            }
            LoanAccountFeeItem laFeeTemp = new LoanAccountFeeItem(loanAccountId,
                    feeItemTemp, "0");
            this.feeItems.add(laFeeTemp);
        }
        feeItemMap = new LoanAccountFeeItemLazyMap(this.loanAccountId,
                this.feeItems);
        this.loanAccount.setFeeItemMap(feeItemMap);
        
        //还款计划分项
        this.paymentScheduleList = this.receiver.buildPaymentSchedules(request,
                loanAccount,
                this.feeItems,
                laStrategy);
        this.paymentScheduleEntryList = new ArrayList<>();
        for (PaymentSchedule psTemp : this.paymentScheduleList) {
            AssertUtils.notNull(psTemp.getPaymentScheduleEntryList(),
                    "paymentSchedule.entryList is null.");//可以为empty.但不能为null.
            for (PaymentScheduleEntry pse : psTemp
                    .getPaymentScheduleEntryList()) {
                AssertUtils.isTrue(pse.getPaymentSchedule() == psTemp,
                        "对象引用不一致.");
            }
            this.paymentScheduleEntryList
                    .addAll(psTemp.getPaymentScheduleEntryList());
        }
        
        //调试打印还款计划于日志中
        debugPrintPaymentSchedule(this.paymentScheduleList,
                this.paymentScheduleEntryList);
        
        //持久化贷款账户
        this.loanAccountService.insert(loanAccount);
        this.loanAccountFeeItemService.batchInsert(feeItems);
        this.paymentScheduleService.batchInsert(paymentScheduleList);//批量持久化还款计划
        this.paymentScheduleEntryService.batchInsert(paymentScheduleEntryList);// 批量持久化还款计划分项
        
        //提交请求响应
        this.receiver.postHandle(request,
                response,
                loanAccount,
                this.feeItems,
                this.paymentScheduleList,
                this.paymentScheduleEntryList);
    }
    
    /**
     * @param request
     * @param response
     */
    @Override
    protected void afterHandle(AbstractBuildRequest request,
            CommandResponse response) {
        //构建请求后置处理逻辑
        this.receiver.afterHandle(request,
                response,
                this.loanAccount,
                this.feeItems,
                this.paymentScheduleList);
        
        //记录操作日志
        ServiceLoggerUtils.log(new LARequestLog(request,
                MessageUtils.format("构建账户_[{}]_类型[{}]_贷款金额[{}]_总期数[{}]",
                        new Object[] { this.loanAccount.getId(),
                                this.loanAccount.getLoanAccountType(),
                                this.loanAccount.getLoanAmount(),
                                this.loanAccount.getTotalPeriod() })));
    }
    
    /** 
     * debug打印还款计划于日志中
     *<功能详细描述>
     * @param paymentSchedules [参数说明]
     * 
     * @return void [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    private void debugPrintPaymentSchedule(
            List<PaymentSchedule> paymentScheduleList,
            List<PaymentScheduleEntry> paymentScheduleEntryList) {
        logger.info(PaymentScheduleHelper.toString(paymentScheduleList,
                paymentScheduleEntryList));
    }
}
