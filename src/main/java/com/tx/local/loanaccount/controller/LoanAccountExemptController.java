/*
 * 描          述:  <描述>
 * 修  改   人:  Administrator
 * 修改时间:  2017年6月13日
 * <修改描述:>
 */
package com.tx.local.loanaccount.controller;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.math.NumberUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tx.component.command.context.HelperFactory;
import com.tx.core.exceptions.util.AssertUtils;
import com.tx.local.basicdata.model.FeeItemCfg;
import com.tx.local.basicdata.model.FeeItemEnum;
import com.tx.local.loanaccount.LoanAccountConstants;
import com.tx.local.loanaccount.handle.PaymentScheduleHandler;
import com.tx.local.loanaccount.handle.PaymentScheduleHandlerHelper;
import com.tx.local.loanaccount.helper.paymentschedule.PaymentScheduleHelper;
import com.tx.local.loanaccount.model.ExemptIntention;
import com.tx.local.loanaccount.model.LATradingRecord;
import com.tx.local.loanaccount.model.LoanAccount;
import com.tx.local.loanaccount.model.PaymentSchedule;
import com.tx.local.loanaccount.model.PaymentScheduleEntry;
import com.tx.local.loanaccount.model.ScheduleTypeEnum;
import com.tx.local.loanaccount.service.LATradingRecordDetailService;
import com.tx.local.loanaccount.service.LATradingRecordService;
import com.tx.local.loanaccount.service.LoanAccountService;
import com.tx.local.loanaccount.service.PaymentScheduleDetailService;
import com.tx.local.loanaccount.strategy.loanaccount.LoanAccountStrategy;
import com.tx.local.loanaccount.strategy.loanaccount.LoanAccountStrategyHelper;
import com.tx.local.organization.service.OrganizationService;

/**
  * 贷款账户豁免控制器<br/>
  * <功能详细描述>
  * 
  * @author  Administrator
  * @version  [版本号, 2017年6月13日]
  * @see  [相关类/方法]
  * @since  [产品/模块版本]
  */
@Controller("loanAccountExemptController")
@RequestMapping("/loanAccountExempt")
public class LoanAccountExemptController {
    
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
    
    /**
     * 跳转到豁免<br/>
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
    @RequestMapping("toExempt")
    public String toRepay(ExemptIntention exemptIntention, ModelMap response) {
        AssertUtils.notNull(exemptIntention, "exemptIntention is null.");
        AssertUtils.notEmpty(exemptIntention.getLoanAccountId(), "exemptIntention.loanAccountId is empty.");
        
        if (exemptIntention.getRepayDate() == null) {
            exemptIntention.setRepayDate(new Date());
        }
        String loanAccountId = exemptIntention.getLoanAccountId();
        Date repayDate = exemptIntention.getRepayDate();
        repayDate = repayDate == null ? new Date() : repayDate;
        
        // 查询贷款账户
        LoanAccount loanAccount = this.loanAccountService.findById(loanAccountId);
        LoanAccountStrategy las = LoanAccountStrategyHelper.getStrategy(loanAccount);
        PaymentScheduleHandler handler = HelperFactory.getHelper(PaymentScheduleHandlerHelper.class)
                .buildPaymentScheduleHandler(loanAccount);
        Map<FeeItemEnum, FeeItemCfg> feeItemCfgMap = las.getFeeItemCfgMap();
        
        // 必要数据
        response.put("loanAccountId", loanAccountId);
        response.put("loanAccount", loanAccount);
        response.put("handler", handler);
        response.put("loanAmount", loanAccount.getLoanAmount());
        response.put("feeCfgItemList", las.getFeeItemCfgMap());
        String currentPeriod = loanAccount.getCurrentPeriod();
        response.put("currentPeriod", currentPeriod);
        
        // 写入应还金额
        String waitReceivePeriodString = PaymentScheduleHelper.getWaitReceivePeriod(handler.getPaymentScheduleMap(),
                ScheduleTypeEnum.MAIN);
        String receivablePeriodString = PaymentScheduleHelper.getReceivablePeriod(handler.getPaymentScheduleMap(),
                ScheduleTypeEnum.MAIN,
                repayDate);
        int maxExemptAblePeriod = NumberUtils.max(NumberUtils.toInt(waitReceivePeriodString),
                NumberUtils.toInt(receivablePeriodString, 1));
        
        BigDecimal maxExemptAbleSum = BigDecimal.ZERO;
        Map<String, BigDecimal> pseMap = new HashMap<String, BigDecimal>();
        for (PaymentSchedule ps : handler.getPaymentScheduleMap().valueList(ScheduleTypeEnum.MAIN)) {
            if (NumberUtils.isDigits(ps.getPeriod()) && NumberUtils.toInt(ps.getPeriod()) > maxExemptAblePeriod) {
                continue;
            }
            for (PaymentScheduleEntry pse : ps.getPaymentScheduleEntryList()) {
                FeeItemEnum feeItem = pse.getFeeItem();
                if (feeItemCfgMap.get(feeItem).isPrincipal() || !feeItemCfgMap.get(feeItem).isExemptAble()) {
                    continue;
                }
                if (!pseMap.containsKey(feeItem.getCode())) {
                    pseMap.put(feeItem.getCode(), BigDecimal.ZERO);
                }
                BigDecimal sumTemp = pse.getReceivableAmount()
                        .add(pse.getExemptAmount())
                        .subtract(pse.getActualReceivedAmount());
                
                pseMap.put(feeItem.getCode(), pseMap.get(feeItem.getCode()).add(sumTemp));
                maxExemptAbleSum = maxExemptAbleSum.add(sumTemp);
            }
        }
        response.put("paymentScheduleEntryMap", pseMap);
        response.put("maxExemptAbleSum", maxExemptAbleSum);
        
        Date now = new Date();
        Date expireDate = handler.getPaymentSchedule(ScheduleTypeEnum.MAIN, currentPeriod).getRepaymentDate(); // 到期还款日
        response.put("exemptIntention", exemptIntention);
        response.put("expireDate", expireDate);
        response.put("repayDate", DateFormatUtils.format(repayDate, "yyyy-MM-dd"));
        
        //贷款生效日期:最小的还款日期,最大还款日期
        String effectiveDate = DateFormatUtils.format(loanAccount.getEffectiveDate(), "yyyy-MM-dd");
        response.put("effectiveDate", effectiveDate);
        String maxRepayDate = DateFormatUtils.format(now, "yyyy-MM-dd");
        response.put("maxRepayDate", maxRepayDate);
        
        String pageName = las.getPageName(LoanAccountConstants.VIEW_TYPE_EXEMPT, "exempt");
        
        return pageName;
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
    public Map<FeeItemEnum, BigDecimal> autoAssign(ExemptIntention exemptIntention) {
        AssertUtils.notNull(exemptIntention, "exemptIntention is null.");
        AssertUtils.notEmpty(exemptIntention.getLoanAccountId(), "exemptIntention.loanAccountId is empty.");
        
        String loanAccountId = exemptIntention.getLoanAccountId();
        LoanAccount loanAccount = this.loanAccountService.findById(loanAccountId);
        LoanAccountStrategy las = LoanAccountStrategyHelper.getStrategy(loanAccount);
        PaymentScheduleHandler handler = HelperFactory.getHelper(PaymentScheduleHandlerHelper.class)
                .buildPaymentScheduleHandler(loanAccount);
        
        Map<FeeItemEnum, BigDecimal> resMap = las.mainAssignOfExempt(loanAccount, handler, exemptIntention);
        return resMap;
    }
    
    /**
     * 还款控制层<br/>
     * <功能详细描述>
     * @param repayIntention
     * @return [参数说明]
     * 
     * @return boolean [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @RequestMapping("exempt")
    @ResponseBody
    public boolean exempt(ExemptIntention exemptIntention) {
        AssertUtils.notNull(exemptIntention, "exemptIntention is null.");
        AssertUtils.notEmpty(exemptIntention.getLoanAccountId(), "exemptIntention.loanAccountId is empty.");
        AssertUtils.notEmpty(exemptIntention.getLoanAccountId(), "exemptIntention.loanAccountId is empty.");
        
        String loanAccountId = exemptIntention.getLoanAccountId();
        LoanAccount loanAccount = this.loanAccountService.findById(loanAccountId);
        LoanAccountStrategy las = LoanAccountStrategyHelper.getStrategy(loanAccount);
        
        @SuppressWarnings("unused")
        LATradingRecord tr = las.exempt(loanAccount, exemptIntention);
        
        return true;
    }
}
