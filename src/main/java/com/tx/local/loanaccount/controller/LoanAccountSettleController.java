/*
 * 描          述:  <描述>
 * 修  改   人:  Administrator
 * 修改时间:  2017年6月13日
 * <修改描述:>
 */
package com.tx.local.loanaccount.controller;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.time.DateFormatUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tx.component.command.context.HelperFactory;
import com.tx.core.exceptions.util.AssertUtils;
import com.tx.core.util.DateUtils;
import com.tx.local.basicdata.model.FeeItemCfg;
import com.tx.local.basicdata.model.FeeItemEnum;
import com.tx.local.loanaccount.LoanAccountConstants;
import com.tx.local.loanaccount.handle.PaymentScheduleHandler;
import com.tx.local.loanaccount.handle.PaymentScheduleHandlerHelper;
import com.tx.local.loanaccount.helper.feecfgitem.LoanAccountFeeItemLazyMap;
import com.tx.local.loanaccount.model.LABankAccount;
import com.tx.local.loanaccount.model.LABankAccountTypeEnum;
import com.tx.local.loanaccount.model.LATradingRecord;
import com.tx.local.loanaccount.model.LoanAccount;
import com.tx.local.loanaccount.model.LoanAccountFeeItem;
import com.tx.local.loanaccount.model.LoanAccountSettleDetail;
import com.tx.local.loanaccount.model.LoanAccountTypeEnum;
import com.tx.local.loanaccount.model.ScheduleTypeEnum;
import com.tx.local.loanaccount.model.SettleIntention;
import com.tx.local.loanaccount.service.LABankAccountService;
import com.tx.local.loanaccount.service.LATradingRecordDetailService;
import com.tx.local.loanaccount.service.LATradingRecordService;
import com.tx.local.loanaccount.service.LoanAccountFeeItemService;
import com.tx.local.loanaccount.service.LoanAccountService;
import com.tx.local.loanaccount.service.PaymentScheduleDetailService;
import com.tx.local.loanaccount.strategy.loanaccount.LoanAccountStrategy;
import com.tx.local.loanaccount.strategy.loanaccount.LoanAccountStrategyHelper;
import com.tx.local.organization.service.OrganizationService;

/**
  * 贷款账户还款控制器<br/>
  * <功能详细描述>
  * 
  * @author  Administrator
  * @version  [版本号, 2017年6月13日]
  * @see  [相关类/方法]
  * @since  [产品/模块版本]
  */
@Controller("loanAccountSettleController")
@RequestMapping("/loanAccountSettle")
public class LoanAccountSettleController {
    
    /** 组织信息业务层 */
    @Resource(name = "organizationService")
    protected OrganizationService organizationService;
    
    /** 贷款账户业务层 */
    @Resource(name = "loanAccountService")
    private LoanAccountService loanAccountService;
    
    /** 还款计划详情业务层 */
    @Resource(name = "paymentScheduleDetailService")
    private PaymentScheduleDetailService paymentScheduleDetailService;
    
    /** 交易记录业务层 */
    @Resource(name = "laTradingRecordService")
    private LATradingRecordService laTradingRecordService;
    
    /** 交易记录详情业务层 */
    @Resource(name = "laTradingRecordDetailService")
    private LATradingRecordDetailService laTradingRecordDetailService;
    
    /** 银行账户业务层 */
    @Resource(name = "laBankAccountService")
    private LABankAccountService bankAccountService;
    
    /** 贷款账户费用项业务层 */
    @Resource(name = "loanAccountFeeItemService")
    private LoanAccountFeeItemService loanAccountFeeItemService;
    
    /**
     * 跳转到还款<br/>
     * <功能详细描述>
     * 
     * @param loanAccountId
     * @param mode
     * @return [参数说明]
     *         
     * @return String [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @RequestMapping("toSettle")
    public String toSettle(SettleIntention settleIntention, ModelMap response) {
        AssertUtils.notNull(settleIntention, "settleIntention is null.");
        AssertUtils.notEmpty(settleIntention.getLoanAccountId(), "settleIntention.loanAccountId is empty.");
        
        if (settleIntention.getRepayDate() == null) {
            settleIntention.setRepayDate(new Date());
        }
        String loanAccountId = settleIntention.getLoanAccountId();
        Date repayDate = settleIntention.getRepayDate();
        repayDate = repayDate == null ? new Date() : repayDate;
        settleIntention.setRepayDate(repayDate);
        
        // 查询贷款账户
        LoanAccount loanAccount = this.loanAccountService.findById(loanAccountId);
        LoanAccountStrategy las = LoanAccountStrategyHelper.getStrategy(loanAccount);
        PaymentScheduleHandler handler = HelperFactory.getHelper(PaymentScheduleHandlerHelper.class)
                .buildPaymentScheduleHandler(loanAccount);
        Map<FeeItemEnum, FeeItemCfg> feeItemCfgMap = las.getFeeItemCfgMap();
        Map<FeeItemEnum, LoanAccountFeeItem> feeItemMap = new LoanAccountFeeItemLazyMap(loanAccountId,
                loanAccountFeeItemService);
//        List<ChargeRecordEntry> creList = las.buildSettleChargeEntryList(loanAccount,
//                feeItemMap,
//                feeItemCfgMap,
//                handler,
//                null,
//                repayDate);
//        handler.chargeByEntry(creList);
        Map<FeeItemEnum, BigDecimal> feeItem2amountMap = handler.getReceivableFeeItem2SumMap(ScheduleTypeEnum.MAIN);
        
        // 必要数据
        response.put("loanAccountId", loanAccountId);
        response.put("loanAccount", loanAccount);
        response.put("handler", handler);
        response.put("loanAmount", loanAccount.getLoanAmount());
        response.put("feeCfgItemList", las.getFeeItemCfgMap());
        String currentPeriod = loanAccount.getCurrentPeriod();
        response.put("currentPeriod", currentPeriod);
        
        //费用项目2金额映射
        response.put("feeItem2amountMap", feeItem2amountMap);
        LoanAccountSettleDetail loanAccountSettleDetail = las.buildLoanAccountSettleDetail(loanAccount,
                feeItemMap,
                feeItemCfgMap,
                handler,
                repayDate);
        
        // 写入应还金额
        response.put("settleSum", loanAccountSettleDetail.getTotal());
        response.put("exemptAbleSum", loanAccountSettleDetail.getMainExemptAbleSum());
        
        Date now = new Date();
        Date expireDate = handler.getPaymentSchedule(ScheduleTypeEnum.MAIN, currentPeriod).getRepaymentDate(); // 到期还款日
        response.put("settleIntention", settleIntention);
        response.put("expireDate", expireDate);
        response.put("repayDate", DateFormatUtils.format(repayDate, "yyyy-MM-dd"));
        
        //贷款生效日期:最小的还款日期,最大还款日期
        String effectiveDate = DateFormatUtils.format(loanAccount.getEffectiveDate(), "yyyy-MM-dd");
        response.put("effectiveDate", effectiveDate);
        String maxRepayDate = DateFormatUtils.format(now, "yyyy-MM-dd");
        response.put("maxRepayDate", maxRepayDate);
        
        //是否可以提前结清
        if (DateUtils.compareByDay(now, loanAccount.getExpiryDate()) < 0) {
            response.put("earlySettleAble", true);
        } else {
            response.put("earlySettleAble", false);
        }
        String pageName = las.getPageName(LoanAccountConstants.VIEW_TYPE_SETTLE, "settle");
        
        return pageName;
    }
    
    /**
     * 查询银行账户列表<br/>
     * <功能详细描述>
     * @param bankAccountType
     * @param loanAccountType
     * @return [参数说明]
     * 
     * @return List<LABankAccount> [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @RequestMapping("queryBankAccountList")
    @ResponseBody
    public List<LABankAccount> queryBankAccountList(
            @RequestParam("bankAccountType[]") LABankAccountTypeEnum[] bankAccountTypes,
            @RequestParam("loanAccountType") LoanAccountTypeEnum loanAccountType) {
        List<LABankAccount> baList = this.bankAccountService.queryListByTypes(bankAccountTypes, loanAccountType);
        return baList;
    }
    
    /**
     * 金额自动分配<br/>
     * @return [参数说明]
     *         
     * @return Map<String,BigDecimal> [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @RequestMapping("autoAssign")
    @ResponseBody
    public Map<FeeItemEnum, BigDecimal> autoAssign(SettleIntention settleIntention) {
        AssertUtils.notNull(settleIntention, "settleIntention is null.");
        AssertUtils.notEmpty(settleIntention.getLoanAccountId(), "settleIntention.loanAccountId is empty.");
        
        String loanAccountId = settleIntention.getLoanAccountId();
        LoanAccount loanAccount = this.loanAccountService.findById(loanAccountId);
        LoanAccountStrategy las = LoanAccountStrategyHelper.getStrategy(loanAccount);
        PaymentScheduleHandler handler = HelperFactory.getHelper(PaymentScheduleHandlerHelper.class)
                .buildPaymentScheduleHandler(loanAccount);
        Map<FeeItemEnum, FeeItemCfg> feeItemCfgMap = las.getFeeItemCfgMap();
        Map<FeeItemEnum, LoanAccountFeeItem> feeItemMap = new LoanAccountFeeItemLazyMap(loanAccountId,
                loanAccountFeeItemService);
        
        Map<FeeItemEnum, BigDecimal> resMap = las.autoAssignOfSettle(loanAccount,
                feeItemMap,
                feeItemCfgMap,
                handler,
                settleIntention);
        return resMap;
    }
    
    /**
     * 还款控制层<br/>
     * <功能详细描述>
     * @param settleIntention
     * @return [参数说明]
     * 
     * @return boolean [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @RequestMapping("settle")
    @ResponseBody
    public boolean settle(SettleIntention settleIntention) {
        AssertUtils.notNull(settleIntention, "settleIntention is null.");
        AssertUtils.notEmpty(settleIntention.getLoanAccountId(), "settleIntention.loanAccountId is empty.");
        AssertUtils.notNull(settleIntention.getRepayDate(), "settleIntention.repayDate is null.");
        
        String loanAccountId = settleIntention.getLoanAccountId();
        LoanAccount loanAccount = this.loanAccountService.findById(loanAccountId);
        LoanAccountStrategy las = LoanAccountStrategyHelper.getStrategy(loanAccount);
        
        @SuppressWarnings("unused")
        LATradingRecord tr = las.settle(loanAccount, settleIntention);
        
        return true;
    }
}
