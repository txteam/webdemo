//package com.tx.local.loanaccount.controller;
//
//import java.util.ArrayList;
//import java.util.Date;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//import javax.annotation.Resource;
//
//import org.apache.commons.lang3.StringUtils;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.ModelMap;
//import org.springframework.util.MultiValueMap;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.ResponseBody;
//
//import com.tx.component.auth.annotation.CheckOperateAuth;
//import com.tx.component.operator.model.Operator;
//import com.tx.component.operator.service.OperatorService;
//import com.tx.component.operator.service.OrganizationService;
//import com.tx.core.TxConstants;
//import com.tx.core.paged.model.PagedList;
//import com.tx.local.loanaccount.handle.PaymentScheduleHandler;
//import com.tx.local.loanaccount.handle.PaymentScheduleHandlerHelper;
//import com.tx.local.loanaccount.model.LoanAccount;
//import com.tx.local.loanaccount.model.PaymentRecord;
//import com.tx.local.loanaccount.model.PaymentSchedule;
//import com.tx.local.loanaccount.model.RepayIntention;
//import com.tx.local.loanaccount.model.LATradingCategoryEnum;
//import com.tx.local.loanaccount.model.LATradingRecord;
//import com.tx.local.loanaccount.service.LoanAccountFeeCfgItemService;
//import com.tx.local.loanaccount.service.LoanAccountService;
//import com.tx.local.loanaccount.service.PaymentRecordService;
//import com.tx.local.loanaccount.service.PaymentScheduleEntryService;
//import com.tx.local.loanaccount.service.PaymentScheduleService;
//import com.tx.local.loanaccount.service.LATradingRecordEntryService;
//import com.tx.local.loanaccount.service.LATradingRecordService;
//
///**
//  * 贷款账户视图逻辑层<br/>
//  * <功能详细描述>
//  * 
//  * @author  vincent
//  * @version  [版本号, 2014年5月29日]
//  * @see  [相关类/方法]
//  * @since  [产品/模块版本]
// */
//@CheckOperateAuth(key = "loan_account_manage", name = "贷款账户管理")
//@Controller("loanAccountController")
//@RequestMapping("/loanAccount")
//public class LoanAccountController {
//    
//    @Resource(name = "loanAccountService")
//    private LoanAccountService loanAccountService;
//    
//    @Resource(name = "paymentScheduleService")
//    private PaymentScheduleService paymentScheduleService;
//    
//    @Resource(name = "paymentScheduleEntryService")
//    private PaymentScheduleEntryService paymentScheduleEntryService;
//    
//    @Resource(name = "tradingRecordService")
//    private LATradingRecordService tradingRecordService;
//    
//    @Resource(name = "tradingRecordEntryService")
//    private LATradingRecordEntryService tradingRecordEntryService;
//    
//    @Resource(name = "paymentRecordService")
//    private PaymentRecordService paymentRecordService;
//    
//    /** 组织业务 */
//    @Resource(name = "organizationService")
//    private OrganizationService organizationService;
//    
//    /** 人员业务 */
//    @Resource(name = "operatorService")
//    private OperatorService operatorService;
//    
//    /**
//     * 跳转到查询LoanAccount列表页面<br/>
//     * <功能详细描述>
//     * @return [参数说明]
//     * 
//     * @return String [返回类型说明]
//     * @exception throws [异常类型] [异常说明]
//     * @see [类、类#方法、类#成员]
//    */
//    @RequestMapping("/toQueryLoanAccountListForRepay")
//    public String toQueryLoanAccountList() {
//        return "/loanaccount/queryLoanAccountListForRepay";
//    }
//    
//    /**
//      * 查询LoanAccount列表
//      * <功能详细描述>
//      * @param request
//      * @return [参数说明]
//      * 
//      * @return List<LoanAccount> [返回类型说明]
//      * @exception throws [异常类型] [异常说明]
//      * @see [类、类#方法、类#成员]
//     */
//    @ResponseBody
//    @RequestMapping("/queryLoanAccountList")
//    public PagedList<LoanAccount> queryLoanAccountList(
//            @RequestParam MultiValueMap<String, String> request,
//            @RequestParam(value = "contractNumber", required = false) String contractNumber,
//            @RequestParam(value = "idCardNumber", required = false) String idCardNumber,
//            @RequestParam(value = "pageNumber", required = false, defaultValue = "1") int pageNumber,
//            @RequestParam(value = "pageSize", required = false, defaultValue = "20") int pageSize) {
//        if (StringUtils.isBlank(contractNumber)
//                && StringUtils.isBlank(idCardNumber)) {
//            ///return null;
//        }
//        
//        Map<String, Object> params = new HashMap<String, Object>();
//        //params.put("contractNumber", contractNumber);
//        //params.put("mainOrSubprimeIdCardNumber", idCardNumber);
//        //params.put("useAuth", AuthTypeEnum.CACH_REPAY_RANGE);
//        //List<LoanAccount> resList = this.loanAccountService.queryLoanAccountList(params);
//        PagedList<LoanAccount> resPagedList = this.loanAccountService.queryPagedList(params,
//                pageNumber,
//                pageSize);
//        return resPagedList;
//    }
//    
//    /**
//      * 跳转到还款记录列表
//      * <功能详细描述>
//      * @param loanAccountId
//      * @param model
//      * @return [参数说明]
//      * 
//      * @return String [返回类型说明]
//      * @exception throws [异常类型] [异常说明]
//      * @see [类、类#方法、类#成员]
//     */
//    @RequestMapping("/toQueryRepaymentRecordList")
//    public String toQueryRepaymentRecordList(
//            @RequestParam("loanAccountId") String loanAccountId, ModelMap model) {
//        model.put("loanAccountId", loanAccountId);
//        return "/loanaccount/queryRepaymentRecordList";
//    }
//    
//    /**
//      * 查询逾期资料<br/>
//      *     <功能详细描述>
//      * @return [参数说明]
//      * 
//      * @return List<PaymentSchedule> [返回类型说明]
//      * @exception throws [异常类型] [异常说明]
//      * @see [类、类#方法、类#成员]
//     */
//    @ResponseBody
//    @RequestMapping("/queryPaymentScheduleOfOverdue")
//    public List<PaymentSchedule> queryPaymentScheduleOfOverdue(
//            @RequestParam("loanAccountId") String loanAccountId) {
//        List<PaymentSchedule> paymentSchedules = this.paymentScheduleService.queryOverdulePaymentScheduleWithEntryByLoanAccountId(loanAccountId);
//        return paymentSchedules;
//    }
//    
//    /**
//      *  跳转到豁免历史列表页面
//      *<功能详细描述>
//      * @param loanAccountId
//      * @param mode
//      * @return [参数说明]
//      * 
//      * @return String [返回类型说明]
//      * @exception throws [异常类型] [异常说明]
//      * @see [类、类#方法、类#成员]
//     */
//    @RequestMapping("/toQueryExemptRecordList")
//    public String toQueryExemptRecordList(
//            @RequestParam("loanAccountId") String loanAccountId, ModelMap model) {
//        model.put("loanAccountId", loanAccountId);
//        return "/loanaccount/queryExemptRecordList";
//    }
//    
//    /**
//      * 根据贷款账户id查询还款记录
//      * <功能详细描述>
//      * @param loanAccountId
//      * @return [参数说明]
//      * 
//      * @return List<PaymentRecord> [返回类型说明]
//      * @exception throws [异常类型] [异常说明]
//      * @see [类、类#方法、类#成员]
//     */
//    @ResponseBody
//    @RequestMapping("/queryPaymentRecordList")
//    public List<PaymentRecord> queryPaymentRecordList(
//            @RequestParam("loanAccountId") String loanAccountId) {
//        List<PaymentRecord> paymentRecordList = this.paymentRecordService.queryDetailListByLoanAccountId(loanAccountId);
//        return paymentRecordList;
//    }
//    
//    /**
//      * 根据贷款账户id查询交易记录和还款记录
//      *<功能详细描述>
//      * @param loanAccountId
//      * @return [参数说明]
//      * 
//      * @return List<TradingRecord> [返回类型说明]
//      * @exception throws [异常类型] [异常说明]
//      * @see [类、类#方法、类#成员]
//     */
//    @ResponseBody
//    @RequestMapping("/queryTradingRecordListAndPaymentRecordList")
//    public List<LATradingRecord> queryTradingRecordListAndPaymentRecordList(
//            @RequestParam("loanAccountId") String loanAccountId) {
//        LATradingCategoryEnum[] tradingTypes = { LATradingCategoryEnum.贷后展期,
//                LATradingCategoryEnum.撤销贷后展期, LATradingCategoryEnum.撤销放款,
//                LATradingCategoryEnum.撤销还款, LATradingCategoryEnum.退款, LATradingCategoryEnum.还款,
//                LATradingCategoryEnum.放款, LATradingCategoryEnum.暂缓, LATradingCategoryEnum.撤销暂缓 };
//        List<LATradingRecord> resList = this.tradingRecordService.queryTradingRecordDetailListByLoanAccountIdAndTradingTypes(loanAccountId,
//                tradingTypes);
//        return resList;
//    }
//    
//    /**
//     * 查询交易记录
//     * <功能详细描述>
//     * @param loanAccountId
//     * @return [参数说明]
//     * 
//     * @return List<TradingRecord> [返回类型说明]
//     * @exception throws [异常类型] [异常说明]
//     * @see [类、类#方法、类#成员]
//    */
//    @ResponseBody
//    @RequestMapping("/queryTradingRecordList")
//    public List<LATradingRecord> queryTradingRecordList(
//            @RequestParam("loanAccountId") String loanAccountId) {
//        LATradingCategoryEnum[] tradingRecordTypes = { LATradingCategoryEnum.撤销放款,
//                LATradingCategoryEnum.撤销还款, LATradingCategoryEnum.计费, LATradingCategoryEnum.豁免,
//                LATradingCategoryEnum.撤销豁免, LATradingCategoryEnum.撤销计费, LATradingCategoryEnum.还款,
//                LATradingCategoryEnum.放款 };
//        List<LATradingRecord> tradingRecordList = this.tradingRecordService.queryTradingRecordListByLoanAccountIdAndTradingTypes(loanAccountId,
//                tradingRecordTypes);
//        return tradingRecordList;
//    }
//    
//    /**
//      * 根据贷款账户id查询还款计划
//      *<功能详细描述>
//      * @param loanAccountId
//      * @return [参数说明]
//      * 
//      * @return List<PaymentSchedule> [返回类型说明]
//      * @exception throws [异常类型] [异常说明]
//      * @see [类、类#方法、类#成员]
//     */
//    @ResponseBody
//    @RequestMapping("/queryPaymentScheduleWithEntryByLoanAccountId")
//    public Map<String, Object> queryPaymentScheduleWithEntryByLoanAccountId(
//            @RequestParam("loanAccountId") String loanAccountId, Date repayDate) {
//        Map<String, Object> map = new HashMap<String, Object>();
//        
//        RepayIntention repayIntention = new RepayIntention();
//        repayIntention.setRepayDate(repayDate == null ? new Date() : repayDate);
//        PaymentScheduleHandler handler = PaymentScheduleHandlerHelper.buildRepayPaymentScheduleHandlerByLoanAccountId(loanAccountId,
//                repayIntention);
//        
//        map.put("paymentScheduleList", handler.getPaymentScheduleList());
//        map.put("loanAccountFeeCfgItemList", handler.getFeeCfgItemList());
//        return map;
//    }
//    
//    /**
//      * 根据交易id查询交易记录
//      * <功能详细描述>
//      * @param tradingRecordId
//      * @return [参数说明]
//      * 
//      * @return TradingRecord [返回类型说明]
//      * @exception throws [异常类型] [异常说明]
//      * @see [类、类#方法、类#成员]
//     */
//    @ResponseBody
//    @RequestMapping("/findTradingRecord")
//    public Map<String, Object> findTradingRecord(
//            @RequestParam("tradingRecordId") String tradingRecordId) {
//        Map<String, Object> map = new HashMap<String, Object>(
//                TxConstants.INITIAL_MAP_SIZE);
//        LATradingRecord tradingRecord = tradingRecordService.findDetailById(tradingRecordId);
//        map.put("tradingRecord", tradingRecord);
//        
//        // 获取最后更新人、处理人、组织名称
//        if (null != tradingRecord) {
//            //String organizationName = organizationService.findOrganizationById(tradingRecord.getOrganizationId())
//            //        .getName();
//            Operator lastOperator = null;
//            Operator operator = null;
//            if(!StringUtils.isEmpty(tradingRecord.getLastUpdateOperatorId())){
//                lastOperator = operatorService.findById(tradingRecord.getLastUpdateOperatorId());
//            }
//            if(!StringUtils.isEmpty(tradingRecord.getOperatorId())){
//                operator = operatorService.findById(tradingRecord.getOperatorId());
//            } 
//            map.put("organizationName", "");
//            map.put("lastUpdateOperatorName", lastOperator == null ? "" : lastOperator.getUserName());
//            map.put("operatorName",
//                    null == operator ? "" : operator.getUserName());
//        }
//        
//        return map;
//    }
//    
//    /**
//      * 查询正常的还款记录
//      *<功能详细描述>
//      * @param loanAccountId
//      * @return [参数说明]
//      * 
//      * @return List<TradingRecord> [返回类型说明]
//      * @exception throws [异常类型] [异常说明]
//      * @see [类、类#方法、类#成员]
//     */
//    @ResponseBody
//    @RequestMapping("/queryRepaymentTradingRecordList")
//    public List<LATradingRecord> queryRepaymentTradingRecordList(
//            @RequestParam(value = "loanAccountId") String loanAccountId,
//            @RequestParam(value = "minProcessDate", required = false) Date minProcessDate,
//            @RequestParam(value = "maxProcessDate", required = false) Date maxProcessDate) {
//        LATradingCategoryEnum[] tradingTypes = { LATradingCategoryEnum.还款,
//                LATradingCategoryEnum.放款 };
//        List<LATradingRecord> tradingRecordList = tradingRecordService.queryTradingRecordDetailListByLoanAccountIdAndTradingTypes(loanAccountId,
//                tradingTypes,
//                minProcessDate,
//                maxProcessDate);
//        List<LATradingRecord> resList = new ArrayList<LATradingRecord>();
//        for (LATradingRecord tradingRecord : tradingRecordList) {
//            if (tradingRecord.isRevoked()) {
//                continue;
//            }
//            resList.add(tradingRecord);
//        }
//        return resList;
//    }
//}
