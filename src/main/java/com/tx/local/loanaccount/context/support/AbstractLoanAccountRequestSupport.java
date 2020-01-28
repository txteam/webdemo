/*
c * 描          述:  <描述>
 * 修  改   人:  Administrator
 * 修改时间:  2014年4月25日
 * <修改描述:>
 */
package com.tx.local.loanaccount.context.support;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.tx.component.command.context.support.AbstractRequestSupport;
import com.tx.local.loanaccount.context.receiver.AbstractLoanAccountReceiver;
import com.tx.local.loanaccount.context.request.AbstractLoanAccountRequest;
import com.tx.local.loanaccount.handle.PaymentScheduleHandlerHelper;
import com.tx.local.loanaccount.service.ChargeRecordEntryService;
import com.tx.local.loanaccount.service.ChargeRecordService;
import com.tx.local.loanaccount.service.ExemptRecordEntryService;
import com.tx.local.loanaccount.service.ExemptRecordService;
import com.tx.local.loanaccount.service.LoanAccountFeeItemService;
import com.tx.local.loanaccount.service.LoanAccountService;
import com.tx.local.loanaccount.service.PaymentRecordEntryService;
import com.tx.local.loanaccount.service.PaymentRecordService;
import com.tx.local.loanaccount.service.PaymentScheduleEntryService;
import com.tx.local.loanaccount.service.PaymentScheduleService;
import com.tx.local.loanaccount.strategy.loanaccount.LoanAccountStrategyHelper;
import com.tx.local.loanaccount.service.LATradingRecordEntryService;
import com.tx.local.loanaccount.service.LATradingRecordService;

/**
 * 操作请求支持类<br/>
 *     提供类似command模式中的Command对象的封装
 *     以支持context的策略模式的功能实现<br/>
 * 
 * @author  Administrator
 * @version  [版本号, 2014年4月25日]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public abstract class AbstractLoanAccountRequestSupport<PR extends AbstractLoanAccountRequest, RE extends AbstractLoanAccountReceiver<PR>>
        extends AbstractRequestSupport<PR, RE> {
    
    /** 日志记录器 */
    protected Logger logger = LoggerFactory.getLogger(AbstractLoanAccountRequestSupport.class);
    
    /** 贷款账户业务层 */
    @Resource(name = "loanAccountService")
    protected LoanAccountService loanAccountService;
    
    /** 贷款账户费用项配置业务层 */
    @Resource(name = "loanAccountFeeItemService")
    protected LoanAccountFeeItemService loanAccountFeeItemService;
    
    /** 交易记录业务层 */
    @Resource(name = "laTradingRecordService")
    protected LATradingRecordService tradingRecordService;
    
    /** 交易记录分项业务层 */
    @Resource(name = "laTradingRecordEntryService")
    protected LATradingRecordEntryService tradingRecordEntryService;
    
    /** 计费记录业务层 */
    @Resource(name = "chargeRecordService")
    protected ChargeRecordService chargeRecordService;
    
    /** 计费记录分项业务层 */
    @Resource(name = "chargeRecordEntryService")
    protected ChargeRecordEntryService chargeRecordEntryService;
    
    /** 豁免记录业务层 */
    @Resource(name = "exemptRecordService")
    protected ExemptRecordService exemptRecordService;
    
    /** 豁免记录分项业务层 */
    @Resource(name = "exemptRecordEntryService")
    protected ExemptRecordEntryService exemptRecordEntryService;
    
    /** 实收记录业务层 */
    @Resource(name = "paymentRecordService")
    protected PaymentRecordService paymentRecordService;
    
    /** 实收记录分项业务层 */
    @Resource(name = "paymentRecordEntryService")
    protected PaymentRecordEntryService paymentRecordEntryService;
    
    /** 还款计划业务层 */
    @Resource(name = "paymentScheduleService")
    protected PaymentScheduleService paymentScheduleService;
    
    /** 还款计划分项业务层 */
    @Resource(name = "paymentScheduleEntryService")
    protected PaymentScheduleEntryService paymentScheduleEntryService;
    
    /** 还款计划处理器帮助类 */
    @Resource(name = "paymentScheduleHandlerHelper")
    protected PaymentScheduleHandlerHelper paymentScheduleHandlerHelper;
    
    /** 贷款账户策略辅助器 */
    @Resource(name = "loanAccountStrategyHelper")
    protected LoanAccountStrategyHelper loanAccountStrategyHelper;
    
    /** */
    public AbstractLoanAccountRequestSupport() {
        super();
    }
    
    /** <默认构造函数> */
    public AbstractLoanAccountRequestSupport(RE receiver) {
        super(receiver);
    }
}
