/*
 * 描          述:  <描述>
 * 修  改   人:  Administrator
 * 修改时间:  2017年6月13日
 * <修改描述:>
 */
package com.tx.local.loanaccount.controller;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tx.core.exceptions.util.AssertUtils;
import com.tx.local.loanaccount.LoanAccountConstants;
import com.tx.local.loanaccount.model.LABankAccount;
import com.tx.local.loanaccount.model.LABankAccountTypeEnum;
import com.tx.local.loanaccount.model.LATradingRecord;
import com.tx.local.loanaccount.model.LATradingRecordTypeEnum;
import com.tx.local.loanaccount.model.LoanAccount;
import com.tx.local.loanaccount.model.RevokeCategoryEnum;
import com.tx.local.loanaccount.model.RevokeTypeEnum;
import com.tx.local.loanaccount.service.LABankAccountService;
import com.tx.local.loanaccount.service.LATradingRecordDetailService;
import com.tx.local.loanaccount.service.LATradingRecordService;
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
@Controller("loanAccountRevokeController")
@RequestMapping("/loanAccountRevoke")
public class LoanAccountRevokeController {
    
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
    private LABankAccountService laBankAccountService;
    
    /**
     * 获取可撤销的最后一条交易记录<br/>
     *    可为：还款、计费、豁免.通过该方法可以由下向上进行撤销<br/>
     * <功能详细描述>
     * @param loanAccountId
     * @return [参数说明]
     *         
     * @return List<TradingRecord> [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @ResponseBody
    @RequestMapping("/getRevokeAbleTradingRecords")
    public List<LATradingRecord> getRevokeAbleTradingRecords(@RequestParam("loanAccountId") String loanAccountId) {
        AssertUtils.notEmpty(loanAccountId, "loanAccountId is empty.");
        
        //查询贷款账户
        LoanAccount loanAccount = this.loanAccountService.findById(loanAccountId);
        LoanAccountStrategy las = LoanAccountStrategyHelper.getStrategy(loanAccount);
        
        List<LATradingRecord> revokeAbleTradingRecords = las.getRevokeAbleTradingRecords(loanAccount);
        
        return revokeAbleTradingRecords;
    }
    
    /**
     * 获取可撤销的还款、放款交易记录集合<br/>
     * <功能详细描述>
     * 
     * @param loanAccountId
     * @return [参数说明]
     *         
     * @return List<TradingRecord> [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @ResponseBody
    @RequestMapping("/getRevokeAbleRepayTradingRecords")
    public List<LATradingRecord> getRevokeAbleRepayTradingRecords(@RequestParam("loanAccountId") String loanAccountId) {
        AssertUtils.notEmpty(loanAccountId, "loanAccountId is empty.");
        
        //查询贷款账户
        LoanAccount loanAccount = this.loanAccountService.findById(loanAccountId);
        LoanAccountStrategy las = LoanAccountStrategyHelper.getStrategy(loanAccount);
        
        List<LATradingRecord> revokeAbleTradingRecords = las.getRevokeAbleRepayTradingRecords(loanAccount);
        
        return revokeAbleTradingRecords;
    }
    
    /**
    * 跳转到撤销归还款项页面<br/>
    * <功能详细描述>
    * 
    * @param loanAccountId
    * @param revokeTradingRecordId
    * @param model
    * @return [参数说明]
    *         
    * @return String [返回类型说明]
    * @exception throws [异常类型] [异常说明]
    * @see [类、类#方法、类#成员]
    */
    @RequestMapping("toRevoke")
    public String toRevoke(@RequestParam("loanAccountId") String loanAccountId,
            @RequestParam("revokeCategory") String revokeCategory,
            @RequestParam(value = "revokeTradingRecordId", required = true) String revokeTradingRecordId,
            ModelMap model) {
        //查询贷款账户
        LoanAccount loanAccount = this.loanAccountService.findById(loanAccountId);
        LoanAccountStrategy las = LoanAccountStrategyHelper.getStrategy(loanAccount);
        
        //撤销交易
        List<LATradingRecord> revokeTradingRecords = null;
        switch (revokeCategory) {
            case "REVOKE_REPAY_TRADING":
                revokeTradingRecords = las.getRevokeAbleRepayTradingRecords(loanAccount);
                break;
            case "REVOKE_TRADING":
                revokeTradingRecords = las.getRevokeAbleTradingRecords(loanAccount);
                break;
        }
        Set<String> revokeAbleTradingRecordIds = new HashSet<>();
        for (LATradingRecord trTemp : revokeTradingRecords) {
            revokeAbleTradingRecordIds.add(trTemp.getId());
        }
        model.put("loanAccountId", loanAccountId);
        model.put("revokeCategory", revokeCategory);
        model.put("revokeAbleTradingRecordIds", revokeAbleTradingRecordIds);
        model.put("revokeAbleTradingRecords", revokeTradingRecords);
        
        //判断合法性
        AssertUtils.isTrue(revokeAbleTradingRecordIds.contains(revokeTradingRecordId), 202, "撤销交易id不合法.");
        
        //交易记录
        LATradingRecord revokeTradingRecordDetail = this.laTradingRecordDetailService
                .findRepaymentDetailById(revokeTradingRecordId);
        //获取撤销交易类型
        LATradingRecordTypeEnum revokeTradingRecordType = revokeTradingRecordDetail.getType()
                .getRevokeTradingRecordType();
        AssertUtils.notNull(revokeTradingRecordType,
                "tradingRecordType:{}.revokeTradingRecordType is null. :{}",
                new Object[] { revokeTradingRecordDetail.getType() });
        
        //撤销类型集合
        List<RevokeTypeEnum> revokeTypes = new ArrayList<RevokeTypeEnum>();
        revokeTypes.add(RevokeTypeEnum.REVOKE);
        if(RevokeCategoryEnum.REVOKE_REPAY_TRADING.equals(revokeCategory)  ){
            if (las.isRefundAble(loanAccount, revokeTradingRecordDetail)) {
                List<LABankAccount> bankAccountList = this.laBankAccountService.queryListByType(LABankAccountTypeEnum.TK,
                        loanAccount.getLoanAccountType());
                model.put("bankAccountList", bankAccountList);
                revokeTypes.add(RevokeTypeEnum.REFUND);
            }
            if (las.isRevokeToWaitAccountAble(loanAccount, revokeTradingRecordDetail)) {
                revokeTypes.add(RevokeTypeEnum.REVOKE_TO_WA);
            }
        }
        
        model.put("revokeTypes", revokeTypes);
        model.put("tradingRecord", revokeTradingRecordDetail);
        model.put("revokeTradingRecordType", revokeTradingRecordType);
        String pageName = las.getPageName(LoanAccountConstants.VIEW_TYPE_REVOKE, "revoke");
        
        return pageName;
    }
    
    /**
     * 撤销交易/归还款项/撤销至待入账<br/>
     * @param loanAccountId
     * @param revokeTradingRecordId
     * @param revokeReason
     * @return [参数说明]
     *         
     * @return boolean [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @ResponseBody
    @RequestMapping("revoke")
    public boolean revoke(@RequestParam("revokeType") RevokeTypeEnum revokeType,
            @RequestParam("loanAccountId") String loanAccountId, @RequestParam("id") String revokeTradingRecordId,
            @RequestParam("revokeResean") String revokeResean) {
        //查询贷款账户
        LoanAccount loanAccount = this.loanAccountService.findById(loanAccountId);
        LoanAccountStrategy las = LoanAccountStrategyHelper.getStrategy(loanAccount);
        
        boolean flag = las.revoke(revokeType, loanAccount, revokeTradingRecordId, revokeResean);
        return flag;
    }
    
    /**
     * 撤销交易/归还款项/撤销至待入账<br/>
     * @param loanAccountId
     * @param revokeTradingRecordId
     * @param revokeReason
     * @return [参数说明]
     *         
     * @return boolean [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @ResponseBody
    @RequestMapping("revokeToWaitAccount")
    public boolean revokeToWaitAccount(@RequestParam("revokeType") RevokeTypeEnum revokeType,
            @RequestParam("loanAccountId") String loanAccountId, @RequestParam("id") String revokeTradingRecordId,
            @RequestParam("revokeResean") String revokeResean) {
        //查询贷款账户
        LoanAccount loanAccount = this.loanAccountService.findById(loanAccountId);
        LoanAccountStrategy las = LoanAccountStrategyHelper.getStrategy(loanAccount);
        
        boolean flag = las.revokeToWaitAccount(revokeType, loanAccount, revokeTradingRecordId, revokeResean);
        return flag;
    }
    
    /**
     * 撤销交易/归还款项/撤销至待入账<br/>
     * @param loanAccountId
     * @param revokeTradingRecordId
     * @param revokeReason
     * @return [参数说明]
     *         
     * @return boolean [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @ResponseBody
    @RequestMapping("refund")
    public boolean refund(@RequestParam("revokeType") RevokeTypeEnum revokeType,
            @RequestParam("loanAccountId") String loanAccountId, @RequestParam("id") String revokeTradingRecordId,
            @RequestParam("revokeResean") String revokeResean,
            @RequestParam(value = "bankAccountId") String bankAccountId) {
        //查询贷款账户
        LoanAccount loanAccount = this.loanAccountService.findById(loanAccountId);
        LoanAccountStrategy las = LoanAccountStrategyHelper.getStrategy(loanAccount);
        
        boolean flag = las.refund(revokeType, loanAccount, revokeTradingRecordId, revokeResean, bankAccountId);
        return flag;
    }
}
