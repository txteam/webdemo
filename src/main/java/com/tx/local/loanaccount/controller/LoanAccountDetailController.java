/*
 * 描          述:  <描述>
 * 修  改   人:  Administrator
 * 修改时间:  2015年1月29日
 * <修改描述:>
 */
package com.tx.local.loanaccount.controller;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tx.component.command.context.HelperFactory;
import com.tx.core.TxConstants;
import com.tx.core.util.DateUtils;
import com.tx.local.basicdata.model.FeeItemCfg;
import com.tx.local.basicdata.model.FeeItemEnum;
import com.tx.local.loanaccount.LoanAccountConstants;
import com.tx.local.loanaccount.handle.PaymentScheduleHandler;
import com.tx.local.loanaccount.handle.PaymentScheduleHandlerHelper;
import com.tx.local.loanaccount.helper.feecfgitem.LoanAccountFeeItemLazyMap;
import com.tx.local.loanaccount.helper.paymentschedule.PaymentScheduleHelper;
import com.tx.local.loanaccount.helper.tradingrecord.LATradingRecordHelper;
import com.tx.local.loanaccount.model.LATradingCategoryEnum;
import com.tx.local.loanaccount.model.LATradingRecord;
import com.tx.local.loanaccount.model.LoanAccount;
import com.tx.local.loanaccount.model.LoanAccountDetail;
import com.tx.local.loanaccount.model.LoanAccountFeeItem;
import com.tx.local.loanaccount.model.LoanAccountSettleDetail;
import com.tx.local.loanaccount.model.PaymentSchedule;
import com.tx.local.loanaccount.model.PaymentScheduleDetail;
import com.tx.local.loanaccount.model.ScheduleAssociateMap;
import com.tx.local.loanaccount.model.ScheduleTypeEnum;
import com.tx.local.loanaccount.service.LATradingRecordDetailService;
import com.tx.local.loanaccount.service.LATradingRecordService;
import com.tx.local.loanaccount.service.LoanAccountFeeItemService;
import com.tx.local.loanaccount.service.LoanAccountService;
import com.tx.local.loanaccount.service.PaymentScheduleDetailService;
import com.tx.local.loanaccount.strategy.loanaccount.LoanAccountStrategy;
import com.tx.local.loanaccount.strategy.loanaccount.LoanAccountStrategyHelper;
import com.tx.local.organization.service.OrganizationService;

/**
 * 基础贷款账户详情控制器<br/>
 * <功能详细描述>
 * 
 * @author  PengQY
 * @version  [版本号, 2017年06月04日]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
@Controller("loanAccountDetailController")
@RequestMapping("/loanAccountDetail")
public class LoanAccountDetailController {
    
    /** 组织信息业务层 */
    @Resource(name = "organizationService")
    protected OrganizationService organizationService;
    
    /** 贷款账户业务层 */
    @Resource(name = "loanAccountService")
    private LoanAccountService loanAccountService;
    
    /** 贷款账户费用项业务层 */
    @Resource(name = "loanAccountFeeItemService")
    private LoanAccountFeeItemService loanAccountFeeItemService;
    
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
      * 初始化Binder<br/>
      * <功能详细描述>
      * @param binder [参数说明]
      * 
      * @return void [返回类型说明]
      * @exception throws [异常类型] [异常说明]
      * @see [类、类#方法、类#成员]
     */
    @InitBinder
    public void initBinder(WebDataBinder binder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        dateFormat.setLenient(false);
        binder.registerCustomEditor(FeeItemEnum.class, new CustomDateEditor(dateFormat, false));
    }
    
    /**
     * 跳转到查询贷款单详情列表<br/>
     * <功能详细描述>
     * @param loanAccountId
     * @param isSerchMain 是否是主查询，如果是主查询过来的，则只读
     * @param isShowHead 是否显示头部主贷人信息
     * @return [参数说明]
     * 
     * @return String [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
    */
    @RequestMapping("/toLoanAccountDetail")
    public String toLoanAccountDetail(@RequestParam("loanAccountId") String loanAccountId,
            @RequestParam(value = "isShowHead", required = false, defaultValue = "true") Boolean isShowHead,
            @RequestParam(value = "isProcessAble", required = false, defaultValue = "true") Boolean isProcessAble,
            @RequestParam MultiValueMap<String, String> request, ModelMap response) {
        response.put("isShowHead", isShowHead == null ? true : isShowHead);
        response.put("isProcessAble", isProcessAble == null ? true : isProcessAble);
        
        Date repayDate = new Date();
        LoanAccount loanAccount = this.loanAccountService.findById(loanAccountId);
        LoanAccountStrategy las = LoanAccountStrategyHelper.getStrategy(loanAccount);
        Map<FeeItemEnum, FeeItemCfg> feeItemCfgMap = las.getFeeItemCfgMap();
        PaymentScheduleHandler handler = HelperFactory.getHelper(PaymentScheduleHandlerHelper.class)
                .buildPaymentScheduleHandler(loanAccount);
        Map<FeeItemEnum, LoanAccountFeeItem> feeItemMap = new LoanAccountFeeItemLazyMap(loanAccountId,
                loanAccountFeeItemService);
        
        LoanAccountDetail loanAccountDetail = las.buildLoanAccountDetail(loanAccount, handler, repayDate);
        response.put("loanAccountDetail", loanAccountDetail);
        response.put("overdueInfoMapList", buildOverdueInfoMapList(handler, repayDate));
        
        LoanAccountSettleDetail loanAccountSettleDetail = las.buildLoanAccountSettleDetail(loanAccount,
                feeItemMap,
                feeItemCfgMap,
                handler,
                repayDate);
        response.put("today", repayDate);
        response.put("loanAccountSettleDetail", loanAccountSettleDetail);
        //System.out.println(DateFormatUtils.format(loanAccountSettleDetail.getSettleDate(), "yyyy-MM-dd"));
        //System.out.println(loanAccountSettleDetail.getSettleSum());
        
        String pageName = las.getPageName(LoanAccountConstants.VIEW_TYPE_DETAIL, "loanAccountDetail");
        return pageName;
    }
    
    /**
     * 处理逾期记录列表[参数说明]
     * @return void [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    protected List<Map<String, Object>> buildOverdueInfoMapList(PaymentScheduleHandler handler, Date repayDate) {
        Map<String, PaymentSchedule> period2PaymentScheduleMap = handler.getPaymentScheduleMap()
                .get(ScheduleTypeEnum.MAIN);
        List<Map<String, Object>> overdueInfoMapList = new ArrayList<>();
        String nextPeriod = "1";
        while (!StringUtils.isBlank(nextPeriod)) {
            PaymentSchedule psTemp = period2PaymentScheduleMap.get(nextPeriod);
            if (DateUtils.compareByDay(psTemp.getRepaymentDate(), repayDate) >= 0) {
                break;
            }
            BigDecimal overdueSumTemp = psTemp.getReceivableSum()
                    .add(psTemp.getExemptSum())
                    .subtract(psTemp.getActualReceivedSum());
            if (overdueSumTemp.compareTo(BigDecimal.ZERO) > 0) {
                Map<String, Object> resMap = new HashMap<String, Object>();
                resMap.put("period", psTemp.getPeriod());
                resMap.put("expireDate", DateFormatUtils.format(psTemp.getRepaymentDate(), "yyyy-MM-dd"));
                resMap.put("overdueAmount", overdueSumTemp);
                
                resMap.put("overdueDay", DateUtils.calculateNumberOfDaysBetween(repayDate, psTemp.getRepaymentDate()));
                resMap.put("overdueInterestAmount", " - ");
                resMap.put("deductFailFeeAmount", " - ");
                resMap.put("totalSum", overdueSumTemp);
                
                overdueInfoMapList.add(resMap);
            }
            nextPeriod = psTemp.getNextPeriod();
        }
        return overdueInfoMapList;
    }
    
    /**
     * 构建贷款账户结清详情<br/>
     * <功能详细描述>
     * @param loanAccountId
     * @param repayDate
     * @return [参数说明]
     * 
     * @return LoanAccountSettleDetail [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @ResponseBody
    @RequestMapping("/changeSettleDate")
    public Map<String, Object> changeSettleDate(@RequestParam("loanAccountId") String loanAccountId,
            @RequestParam("repayDate") Date repayDate) {
        //贷款账户
        LoanAccount loanAccount = this.loanAccountService.findById(loanAccountId);
        LoanAccountStrategy las = LoanAccountStrategyHelper.getStrategy(loanAccount);
        Map<FeeItemEnum, FeeItemCfg> feeItemCfgMap = las.getFeeItemCfgMap();
        PaymentScheduleHandler handler = HelperFactory.getHelper(PaymentScheduleHandlerHelper.class)
                .buildPaymentScheduleHandler(loanAccount);
        Map<FeeItemEnum, LoanAccountFeeItem> feeItemMap = new LoanAccountFeeItemLazyMap(loanAccountId,
                loanAccountFeeItemService);
        
        //settleReceivableSum_LX
        //settleReceivableSum_GLF
        //settleReceivableSum_YQLX
        //settleReceivableSum_KQSBSXF
        //settleReceivableSum_WBYJ
        //settleReceivableSum_TQHKWYJ
        //settleReceivableSum
        //settleReceivableSum_WBYJ
        //settleReceivableSum_ZXHHK
        //total
        //结清详情
        Map<String, Object> resMap = new HashMap<>();
        LoanAccountSettleDetail loanAccountSettleDetail = las.buildLoanAccountSettleDetail(loanAccount,
                feeItemMap,
                feeItemCfgMap,
                handler,
                repayDate);
        resMap.put("settleReceivableSum_LX", loanAccountSettleDetail.getSum(ScheduleTypeEnum.MAIN, FeeItemEnum.DK_LX));//利息
        resMap.put("settleReceivableSum_GLF",
                loanAccountSettleDetail.getSum(ScheduleTypeEnum.MAIN, FeeItemEnum.ZX_GLF));//管理费
        resMap.put("settleReceivableSum_YQLX",
                loanAccountSettleDetail.getSum(ScheduleTypeEnum.MAIN, FeeItemEnum.DK_YQLX)
                        .add(loanAccountSettleDetail.getSum(ScheduleTypeEnum.MAIN, FeeItemEnum.ZX_YQLX)));//逾期利息
        resMap.put("settleReceivableSum_KQSBSXF",
                loanAccountSettleDetail.getSum(ScheduleTypeEnum.MAIN, FeeItemEnum.DK_KQSBSXF)
                        .add(loanAccountSettleDetail.getSum(ScheduleTypeEnum.MAIN, FeeItemEnum.ZX_KQSBSXF)));//扣款失败手续费
        resMap.put("settleReceivableSum_WBYJ",
                loanAccountSettleDetail.getSum(ScheduleTypeEnum.MAIN, FeeItemEnum.DK_WBYJ)
                        .add(loanAccountSettleDetail.getSum(ScheduleTypeEnum.MAIN, FeeItemEnum.ZX_WBYJ)));//外包佣金
        resMap.put("settleReceivableSum_TQHKWYJ",
                loanAccountSettleDetail.getSum(ScheduleTypeEnum.MAIN, FeeItemEnum.DK_TQHKWYJ)
                        .add(loanAccountSettleDetail.getSum(ScheduleTypeEnum.MAIN, FeeItemEnum.ZX_TQHKWYJ)));//提前还款违约金
        resMap.put("settleReceivableSum", loanAccountSettleDetail.getSettleSum());//提前还款违约金
        
        resMap.put("settleReceivableSum_WBYJ", loanAccountSettleDetail.getWBYJSum());//提前还款违约金
        resMap.put("settleReceivableSum_ZXHHK", loanAccountSettleDetail.getZXHHKSum());//提前还款违约金
        resMap.put("total", loanAccountSettleDetail.getTotal());//提前还款违约金
        
        return resMap;
    }
    
    /**
     * 查询交易记录详情列表<br/>
     * <功能详细描述>
     * @param loanAccountId
     * @return [参数说明]
     * 
     * @return List<LATradingRecord> [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @ResponseBody
    @RequestMapping("/queryRepaymentRecordDetailList")
    public List<LATradingRecord> queryRepaymentRecordDetailList(@RequestParam("loanAccountId") String loanAccountId,
            @RequestParam MultiValueMap<String, String> request) {
        LATradingCategoryEnum[] categories = { LATradingCategoryEnum.LOAN, LATradingCategoryEnum.REVOKE_LOAN,
                LATradingCategoryEnum.REPAY, LATradingCategoryEnum.REVOKE_REPAY, LATradingCategoryEnum.SETTLE,
                LATradingCategoryEnum.REVOKE_SETTLE, LATradingCategoryEnum.SUSPEND,
                LATradingCategoryEnum.REVOKE_SUSPEND };
        Map<String, Object> params = new HashMap<>();
        params.put("categories", categories);
        
        List<LATradingRecord> resList = this.laTradingRecordDetailService.queryRepaymentListByLoanAccountId(loanAccountId,
                true,
                null,
                params);
        Collections.sort(resList, LATradingRecordHelper.ID_COMPARATOR);
        return resList;
    }
    
    /**
     * 查询交易记录详情列表<br/>
     * <功能详细描述>
     * @param loanAccountId
     * @return [参数说明]
     * 
     * @return List<LATradingRecord> [返回类型说明]
     * @exception throws [异常类型] [异常说明]  
     * @see [类、类#方法、类#成员]
     */
    @ResponseBody
    @RequestMapping("/queryTradingRecordDetailList")
    public List<LATradingRecord> queryTradingRecordDetailList(@RequestParam("loanAccountId") String loanAccountId,
            @RequestParam MultiValueMap<String, String> request) {
        LATradingCategoryEnum[] categories = { LATradingCategoryEnum.LOAN, LATradingCategoryEnum.REVOKE_LOAN,
                LATradingCategoryEnum.EXEMPT, LATradingCategoryEnum.REVOKE_EXEMPT, LATradingCategoryEnum.CHARGE,
                LATradingCategoryEnum.REVOKE_CHARGE, LATradingCategoryEnum.REPAY, LATradingCategoryEnum.REVOKE_REPAY,
                LATradingCategoryEnum.SETTLE, LATradingCategoryEnum.REVOKE_SETTLE, LATradingCategoryEnum.SUSPEND,
                LATradingCategoryEnum.REVOKE_SUSPEND };
        Map<String, Object> params = new HashMap<>();
        params.put("categories", categories);
        
        List<LATradingRecord> resList = this.laTradingRecordDetailService.queryListByLoanAccountId(loanAccountId,
                true,
                null,
                params);
        Collections.sort(resList, LATradingRecordHelper.ID_COMPARATOR);
        return resList;
    }
    
    /**
      * 查询还款计划详情列表<br/>
      * <功能详细描述>
      * @param loanAccountId
      * @return [参数说明]
      * 
      * @return List<PaymentSchedule> [返回类型说明]
      * @exception throws [异常类型] [异常说明]
      * @see [类、类#方法、类#成员]
     */
    @ResponseBody
    @RequestMapping("/queryPaymentScheduleDetailList")
    public List<PaymentScheduleDetail> queryPaymentScheduleDetailList(
            @RequestParam("loanAccountId") String loanAccountId, @RequestParam MultiValueMap<String, String> request) {
        Map<String, Object> params = new HashMap<>();
        List<PaymentSchedule> psList = this.paymentScheduleDetailService.queryDetailListByLoanAccountId(loanAccountId,
                params);
        ScheduleAssociateMap<PaymentSchedule> psMap = new ScheduleAssociateMap<>(psList);
        //还款计划详情列表
        List<PaymentScheduleDetail> psdList = PaymentScheduleHelper.buildPaymentScheduleDetails(psMap);
        
        return psdList;
    }
    
    /**
      * 获取交易记录详情<br/>
      * <功能详细描述>
      * @param tradingRecordId
      * @return [参数说明]
      * 
      * @return LATradingRecord [返回类型说明]
      * @exception throws [异常类型] [异常说明]
      * @see [类、类#方法、类#成员]
     */
    @ResponseBody
    @RequestMapping("/findTradingRecordDetail")
    public Map<String, Object> findTradingRecordDetail(@RequestParam("tradingRecordId") String tradingRecordId) {
        Map<String, Object> map = new HashMap<String, Object>(TxConstants.INITIAL_MAP_SIZE);
        
        LATradingRecord tradingRecord = this.laTradingRecordDetailService.findRepaymentDetailById(tradingRecordId);
        map.put("tradingRecord", tradingRecord);
        if (null != tradingRecord) {
            //            String lastUpdateOperatorId = tradingRecord.getLastUpdateOperatorId();
            //            if (StringUtils.isNotBlank(lastUpdateOperatorId)) {
            //                Operator lastOperator = operatorService.findOperatorById(lastUpdateOperatorId);
            //                map.put("lastUpdateOperatorName", lastOperator.getUserName());
            //            }
            //            
            //            String organizationId = tradingRecord.getOrganizationId();
            //            if (StringUtils.isNotBlank(organizationId)) {
            //                String organizationName = organizationService.getOrganizationNameById(organizationId);
            //                map.put("organizationName", organizationName);
            //            }
            //            String operatorId = tradingRecord.getOperatorId();
            //            if (StringUtils.isNotBlank(operatorId)) {
            //                Operator operator = operatorService.findOperatorById(operatorId);
            //                map.put("operatorName", null == operator ? "" : operator.getUserName());
            //            }
        }
        // 获取银行代码 
        if (null != tradingRecord && StringUtils.isNotBlank(tradingRecord.getBankAccountId())) {
            //            BankAccount bankAccount = bankAccountService.findBankAccountById(tradingRecord.getBankAccountId());
            //            map.put("bankAccountName",
            //                    null == bankAccount ? "" : bankAccount.getCode());
        }
        
        return map;
    }
    
    //  /**
    //   * @param loanAccountId
    //   * @param repayDate
    //   * @return
    //   */
    //  @ResponseBody
    //  @RequestMapping("/changeRepayDate")
    //  public Map<String, BigDecimal> changeRepayDate(
    //          @RequestParam("loanAccountId") String loanAccountId,
    //          @RequestParam("repayDate") Date repayDate) {
    //      LoanAccount loanAccount = this.loanAccountService.findById(loanAccountId);
    //      LoanAccountDetail loanAccountDetail = this.loanAccountDetailService.bulidLoanAccountDetail(loanAccount,
    //              repayDate,
    //              false);
    //      
    //      BigDecimal receivableMonthlyInterestAmount = BigDecimal.ZERO;
    //      BigDecimal receivableMonthlyManagementFeeAmount = BigDecimal.ZERO;
    //      if (loanAccountDetail.isNeedEarlySettleDamageAmountCharge()) {
    //          receivableMonthlyInterestAmount = loanAccountDetail.getSumOfReceivable(FeeItemEnum.LX);
    //          receivableMonthlyManagementFeeAmount = loanAccountDetail.getSumOfReceivable(FeeItemEnum.GLF);
    //      } else {
    //          receivableMonthlyInterestAmount = loanAccountDetail.getSum(FeeItemEnum.LX);
    //          receivableMonthlyManagementFeeAmount = loanAccountDetail.getSum(FeeItemEnum.GLF);
    //      }
    ////      BigDecimal overdueInterestSum = loanAccountDetail.getSum(FeeItemEnum.YQLX_ZX)
    ////              .add(loanAccountDetail.getSum(FeeItemEnum.YQLX_DK));
    ////      BigDecimal debitFailFeeSum = loanAccountDetail.getSum(FeeItemEnum.KQSBSXF_ZX)
    ////              .add(loanAccountDetail.getSum(FeeItemEnum.KQSBSXF_DK));
    ////      BigDecimal earlyRepayDamageSum = loanAccountDetail.getSum(FeeItemEnum.TQHKWYJ_ZX)
    ////              .add(loanAccountDetail.getSum(FeeItemEnum.TQHKWYJ_DK));
    //      BigDecimal earlySettleAmount = loanAccountDetail.getEarlySettleAmount();
    //      BigDecimal outsourceCommissionAmount = loanAccountDetail.getOutsourceCommissionAmount();
    //      BigDecimal receivableTotalSum = loanAccountDetail.getReceivableTotalSum();
    //      
    //      Map<String, BigDecimal> resMap = new HashMap<>();
    //      resMap.put("receivableMonthlyInterestAmount",
    //              receivableMonthlyInterestAmount);
    //      resMap.put("receivableMonthlyManagementFeeAmount",
    //              receivableMonthlyManagementFeeAmount);
    ////      resMap.put("overdueInterestSum", overdueInterestSum);
    ////      resMap.put("debitFailFeeSum", debitFailFeeSum);
    ////      resMap.put("earlyRepayDamageSum", earlyRepayDamageSum);
    //      resMap.put("earlySettleAmount", earlySettleAmount);
    //      resMap.put("outsourceCommissionAmount", outsourceCommissionAmount);
    //      resMap.put("receivableTotalSum", receivableTotalSum);
    //      
    //      return resMap;
    //  }
    //    
    //    /**
    //      * 改变还款日期<br/>
    //      * <功能详细描述>
    //      * @param loanAccountId
    //      * @param repayDate
    //      * @return [参数说明]
    //      * 
    //      * @return Map<String,BigDecimal> [返回类型说明]
    //      * @exception throws [异常类型] [异常说明]
    //      * @see [类、类#方法、类#成员]
    //     */
    //    public Map<String, BigDecimal> changeRepayDate(@RequestParam("loanAccountId") String loanAccountId,
    //            @RequestParam("repayDate") Date repayDate) {
    //        return new HashMap<String, BigDecimal>();
    //    }
}
