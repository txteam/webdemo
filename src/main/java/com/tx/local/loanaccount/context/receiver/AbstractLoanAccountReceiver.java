/*
 * 描          述:  <描述>
 * 修  改   人:  Administrator
 * 修改时间:  2014年4月29日
 * <修改描述:>
 */
package com.tx.local.loanaccount.context.receiver;

import javax.annotation.Resource;

import org.apache.ibatis.type.TypeReference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.tx.component.command.context.CommandReceiver;
import com.tx.component.command.context.CommandRequest;
import com.tx.component.command.context.CommandResponse;
import com.tx.local.loanaccount.context.request.AbstractLoanAccountRequest;
import com.tx.local.loanaccount.service.ChargeRecordEntryService;
import com.tx.local.loanaccount.service.ExemptRecordEntryService;
import com.tx.local.loanaccount.service.LATradingRecordEntryService;
import com.tx.local.loanaccount.service.LATradingRecordService;
import com.tx.local.loanaccount.service.LoanAccountFeeItemService;
import com.tx.local.loanaccount.service.LoanAccountService;
import com.tx.local.loanaccount.service.OverdueInterestChargeRecordService;
import com.tx.local.loanaccount.service.PaymentRecordEntryService;
import com.tx.local.organization.service.OrganizationService;
import com.tx.local.vitualcenter.service.VirtualCenterService;

/**
 * 操作处理接收器(Receiver)基类<br/>
 * TypeReference
 * 
 * @author  Tim.peng
 * @version  [版本号, 2014年4月29日]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public abstract class AbstractLoanAccountReceiver<PR extends AbstractLoanAccountRequest> extends TypeReference<PR>
        implements CommandReceiver<PR> {
    
    /** 日志 */
    protected Logger logger = LoggerFactory.getLogger(AbstractLoanAccountReceiver.class);
    
    /** 贷款账户业务层 */
    @Resource(name = "loanAccountService")
    protected LoanAccountService loanAccountService;
    
    /** 贷款单账户费用配置项目业务层 */
    @Resource(name = "loanAccountFeeItemService")
    protected LoanAccountFeeItemService loanAccountFeeItemService;
    
    /** 计费记录分项业务层 */
    @Resource(name = "chargeRecordEntryService")
    protected ChargeRecordEntryService chargeRecordEntryService;
    
    /** 豁免记录分项业务层 */
    @Resource(name = "exemptRecordEntryService")
    protected ExemptRecordEntryService exemptRecordEntryService;
    
    /** 还款记录分项业务层 */
    @Resource(name = "paymentRecordEntryService")
    protected PaymentRecordEntryService paymentRecordEntryService;
    
    /** 逾期利息业务 */
    @Resource(name = "overdueInterestChargeRecordService")
    protected OverdueInterestChargeRecordService overdueInterestRecordService;
    
    /** 交易记录业务层 */
    @Resource(name = "laTradingRecordService")
    protected LATradingRecordService tradingRecordService;
    
    /** 交易记录分项业务层 */
    @Resource(name = "laTradingRecordEntryService")
    protected LATradingRecordEntryService tradingRecordEntryService;
    
    /** 组织业务层 */
    @Resource(name = "organizationService")
    protected OrganizationService organizationService;
    
    /** 虚中心业务层 */
    @Resource(name = "virtualCenterService")
    protected VirtualCenterService virtualCenterService;
    
    /** 无参构造"操作接收处理器" */
    public AbstractLoanAccountReceiver() {
        super();
    }
    
    
    /** 获取请求类型 */
    @SuppressWarnings("unchecked")
    @Override
    public Class<? extends AbstractLoanAccountRequest> getRequestType() {
        return (Class<? extends AbstractLoanAccountRequest>) getRawType();
    }
    
    /**
     * @param request
     * @return
     */
    @Override
    public final boolean supports(CommandRequest request) {
        if (!getRequestType().isInstance(request)) {
            return false;
        }
        @SuppressWarnings("unchecked")
        boolean flag = isMatch((PR) request);
        return flag;
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
    protected abstract boolean isMatch(PR request);
    
    /**
     * 前置处理方法<br/>
     * @param request
     * @param response
     * @return
     */
    @Override
    public boolean preHandle(PR request, CommandResponse response) {
        return true;
    }
    
    /**
     * 后置处理方法<br/>
     * @param request
     * @param response
     * @param e
     */
    @Override
    public void afterCompletion(PR request, CommandResponse response, Throwable e) {
    }
    
    /** 贷款账户业务层 */
    protected LoanAccountService getLoanAccountService() {
        return loanAccountService;
    }
    
    /** 贷款单账户费用配置项目业务层 */
    protected LoanAccountFeeItemService getLoanAccountFeeItemService() {
        return loanAccountFeeItemService;
    }
    
    /** 计费记录分项业务层 */
    protected ChargeRecordEntryService getChargeRecordEntryService() {
        return chargeRecordEntryService;
    }
    
    /** 豁免记录分项业务层 */
    protected ExemptRecordEntryService getExemptRecordEntryService() {
        return exemptRecordEntryService;
    }
    
    /** 还款记录分项业务层 */
    protected PaymentRecordEntryService getPaymentRecordEntryService() {
        return paymentRecordEntryService;
    }
    
    /** 逾期利息业务 */
    protected OverdueInterestChargeRecordService getOverdueInterestRecordService() {
        return overdueInterestRecordService;
    }
    
    /** 交易记录业务层 */
    public LATradingRecordService getTradingRecordService() {
        return tradingRecordService;
    }
    
    /** 交易记录分项业务层 */
    protected LATradingRecordEntryService getTradingRecordEntryService() {
        return tradingRecordEntryService;
    }
    
    /** 贷款账户业务层 */
    public void setLoanAccountService(LoanAccountService loanAccountService) {
        this.loanAccountService = loanAccountService;
    }
    
    /** 计费记录分项业务层 */
    public void setChargeRecordEntryService(ChargeRecordEntryService chargeRecordEntryService) {
        this.chargeRecordEntryService = chargeRecordEntryService;
    }
    
    /** 豁免记录分项业务层 */
    public void setExemptRecordEntryService(ExemptRecordEntryService exemptRecordEntryService) {
        this.exemptRecordEntryService = exemptRecordEntryService;
    }
    
    /** 还款记录分项业务层 */
    public void setPaymentRecordEntryService(PaymentRecordEntryService paymentRecordEntryService) {
        this.paymentRecordEntryService = paymentRecordEntryService;
    }
    
    /** 逾期利息业务 */
    public void setOverdueInterestRecordService(OverdueInterestChargeRecordService overdueInterestRecordService) {
        this.overdueInterestRecordService = overdueInterestRecordService;
    }
    
    /** 交易记录业务层 */
    public void setTradingRecordService(LATradingRecordService tradingRecordService) {
        this.tradingRecordService = tradingRecordService;
    }
    
    /** 交易记录分项业务层 */
    public void setTradingRecordEntryService(LATradingRecordEntryService tradingRecordEntryService) {
        this.tradingRecordEntryService = tradingRecordEntryService;
    }
}
