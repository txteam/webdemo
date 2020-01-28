/*
 * 描          述:  <描述>
 * 修  改   人:  Administrator
 * 修改时间:  2017年6月12日
 * <修改描述:>
 */
package com.tx.local.loanaccount.strategy.loanaccount;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ResourceLoaderAware;
import org.springframework.core.io.ResourceLoader;

import com.tx.component.strategy.context.StrategyContext;
import com.tx.core.exceptions.util.AssertUtils;
import com.tx.local.basicdata.model.FeeItemCfg;
import com.tx.local.basicdata.model.FeeItemEnum;
import com.tx.local.basicdata.model.RepayWayEnum;
import com.tx.local.loanaccount.handle.PaymentScheduleHandler;
import com.tx.local.loanaccount.helper.exemptallocator.ExemptAllocator;
import com.tx.local.loanaccount.helper.repayallocator.RepayAllocator;
import com.tx.local.loanaccount.model.ChargeRecordEntry;
import com.tx.local.loanaccount.model.ExemptIntention;
import com.tx.local.loanaccount.model.ExemptRecordEntry;
import com.tx.local.loanaccount.model.FeeItemGroup;
import com.tx.local.loanaccount.model.LATradingRecord;
import com.tx.local.loanaccount.model.LoanAccount;
import com.tx.local.loanaccount.model.LoanAccountDetail;
import com.tx.local.loanaccount.model.LoanAccountFeeItem;
import com.tx.local.loanaccount.model.LoanAccountSettleDetail;
import com.tx.local.loanaccount.model.LoanAccountTypeEnum;
import com.tx.local.loanaccount.model.OverRepayRecord;
import com.tx.local.loanaccount.model.OverdueInterestChargeRecord;
import com.tx.local.loanaccount.model.PaymentRecordEntry;
import com.tx.local.loanaccount.model.PaymentScheduleEntry;
import com.tx.local.loanaccount.model.RepayIntention;
import com.tx.local.loanaccount.model.RevokeTypeEnum;
import com.tx.local.loanaccount.model.SettleIntention;
import com.tx.local.loanaccount.strategy.build.PaymentScheduleEntryBuilder;
import com.tx.local.loanaccount.strategy.charge.YQLXChargeStrategy;
import com.tx.local.loanaccount.strategy.detail.DetailStrategy;
import com.tx.local.loanaccount.strategy.exempt.ExemptStrategy;
import com.tx.local.loanaccount.strategy.feeitem.FeeItemStrategy;
import com.tx.local.loanaccount.strategy.repay.RepayStrategy;
import com.tx.local.loanaccount.strategy.revoke.RevokeStrategy;
import com.tx.local.loanaccount.strategy.settle.SettleStrategy;

/**
 * 抽象贷款账户策略实现
 * <功能详细描述>
 * 
 * @author  Administrator
 * @version  [版本号, 2017年6月12日]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public abstract class AbstractLoanAccountStrategy
        implements LoanAccountStrategy, ResourceLoaderAware, InitializingBean {
    
    /** 页面名称正则表达式 */
    private final static Pattern PAGE_NAME_PATTERN = Pattern
            .compile("^\\w+([/]\\w+)*$");
    
    /** 页面名映射 */
    protected final Map<String, Map<String, String>> pageNameMap = new HashMap<>();
    
    /** 资源加载器 */
    protected ResourceLoader resourceLoader;
    
    /** 排序优先级 */
    protected int orderPriority = 0;
    
    /** <默认构造函数> */
    public AbstractLoanAccountStrategy() {
        super();
    }
    
    /**
     * @param resourceLoader
     */
    @Override
    public void setResourceLoader(ResourceLoader resourceLoader) {
        this.resourceLoader = resourceLoader;
    }
    
    /**
     * @param loanAccount
     * @return
     */
    @Override
    public boolean support(LoanAccount loanAccount) {
        AssertUtils.notNull(loanAccount, "loanAccount is null.");
        AssertUtils.notNull(loanAccount.getLoanAccountType(),
                "loanAccount.loanAccountType is null.");
        if (supportLoanAccountType() != null && supportLoanAccountType()
                .equals(loanAccount.getLoanAccountType())) {
            return true;
        }
        return false;
    }
    
    /** 支持的贷款账户类型 */
    public LoanAccountTypeEnum supportLoanAccountType() {
        return null;
    }
    
    /**
     * @return
     */
    @Override
    public int getOrder() {
        return this.orderPriority;
    }
    
    /**
     * @throws Exception
     */
    @Override
    public void afterPropertiesSet() throws Exception {
        initPageNameMap();//初始化页面路径映射
    }
    
    /**
     * 初始化<br/>
     * <功能详细描述> [参数说明]
     * 
     * @return void [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
    */
    protected void initPageNameMap() {
    }
    
    /**
     * @param viewType
     * @return
     */
    @Override
    public String getPageName(String viewType, String pageName) {
        AssertUtils.notEmpty(viewType, "viewType is empty.");
        AssertUtils.notEmpty(pageName, "pageName is empty.");
        
        AssertUtils.isTrue(PAGE_NAME_PATTERN.matcher(viewType).matches(),
                "viewType:{} is not validate.",
                viewType);
        AssertUtils.isTrue(PAGE_NAME_PATTERN.matcher(pageName).matches(),
                "pageName:{} is not validate.",
                pageName);
        viewType = viewType.toLowerCase();//viewType只能是小写的
        
        if (this.pageNameMap.containsKey(viewType)
                && this.pageNameMap.get(viewType).containsKey(pageName)) {
            //如果存在指定页面，则返回指定页面
            return this.pageNameMap.get(viewType).get(pageName);
        }
        
        //拼接页面
        StringBuilder pageNameSB = new StringBuilder();
        if (viewType.startsWith("loanaccount")) {
            pageNameSB.append(viewType);
        } else {
            pageNameSB.append("loanaccount/").append(viewType);
        }
        pageNameSB.append("/");
        
        String page = "";
        //判断账户类型对应的特殊目录中是否存在页面
        if (isExistOfJspPage(pageNameSB.toString()
                + supportLoanAccountType().toString().toLowerCase() + "/"
                + pageName)) {
            page = pageNameSB
                    .append(supportLoanAccountType().toString().toLowerCase())
                    .append("/")
                    .append(pageName)
                    .toString();
        } else if (isExistOfJspPage(pageNameSB.toString()
                + supportLoanAccountType().getPath() + "/" + pageName)) {
            page = pageNameSB.append(supportLoanAccountType().getPath())
                    .append("/")
                    .append(pageName)
                    .toString();
        } else {
            page = pageNameSB.append(pageName).toString();
        }
        
        return page;
    }
    
    /**
     * 判断Jsp页面是否存在
     * <功能详细描述>
     * @param page
     * @return [参数说明]
     * 
     * @return boolean [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
    */
    private boolean isExistOfJspPage(String page) {
        org.springframework.core.io.Resource jspPageResource = resourceLoader
                .getResource("/WEB-INF/view/" + page + ".jsp");
        if (jspPageResource.exists()) {
            return true;
        } else {
            return false;
        }
    }
    
    /**
     * 获取费用项目策略<br/>
     * <功能详细描述>
     * @return [参数说明]
     * 
     * @return FeeItemStrategy [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    protected FeeItemStrategy getFeeItemStrategy() {
        FeeItemStrategy feeItemStrategy = StrategyContext.getContext()
                .getStrategy(FeeItemStrategy.class, "defaultFeeItemStrategy");
        return feeItemStrategy;
    }
    
    /**
     * @return
     */
    @Override
    public Map<FeeItemEnum, FeeItemCfg> getFeeItemCfgMap() {
        FeeItemStrategy feeItemStrategy = getFeeItemStrategy();
        Map<FeeItemEnum, FeeItemCfg> resMap = feeItemStrategy
                .getFeeItemCfgMap();
        return resMap;
    }
    
    /**
     * @param repayIntention
     * @return
     */
    @Override
    public Collection<FeeItemEnum> getFeeItemsByRepayIntention(
            RepayIntention repayIntention) {
        FeeItemStrategy feeItemStrategy = getFeeItemStrategy();
        Collection<FeeItemEnum> res = feeItemStrategy
                .getFeeItemsByRepayIntention(repayIntention);
        return res;
    }
    
    /**
     * @return
     */
    @Override
    public Collection<FeeItemGroup> getFeeItemGroupsOfRepay() {
        FeeItemStrategy feeItemStrategy = getFeeItemStrategy();
        Collection<FeeItemGroup> res = feeItemStrategy
                .getFeeItemGroupsOfRepay();
        return res;
    }
    
    /**
     * @return
     */
    @Override
    public Collection<FeeItemGroup> getFeeItemGroupsOfSettle() {
        FeeItemStrategy feeItemStrategy = getFeeItemStrategy();
        Collection<FeeItemGroup> res = feeItemStrategy
                .getFeeItemGroupsOfSettle();
        return res;
    }
    
    /**
     * @param exemptIntention
     * @return
     */
    @Override
    public Collection<FeeItemEnum> getFeeItemsByExemptIntention(
            ExemptIntention exemptIntention) {
        FeeItemStrategy feeItemStrategy = getFeeItemStrategy();
        Collection<FeeItemEnum> res = feeItemStrategy
                .getFeeItemsByExemptIntention(exemptIntention);
        return res;
    }
    
    /**
     * @return
     */
    @Override
    public Collection<FeeItemGroup> getFeeItemGroupsOfRepayExempt() {
        FeeItemStrategy feeItemStrategy = getFeeItemStrategy();
        Collection<FeeItemGroup> res = feeItemStrategy
                .getFeeItemGroupsOfRepayExempt();
        return res;
    }
    
    /**
     * @return
     */
    @Override
    public Collection<FeeItemGroup> getFeeItemGroupsOfSettleExempt() {
        FeeItemStrategy feeItemStrategy = getFeeItemStrategy();
        Collection<FeeItemGroup> res = feeItemStrategy
                .getFeeItemGroupsOfSettleExempt();
        return res;
    }
    
    /**
     * 获取豁免策略<br/>
     * <功能详细描述>
     * @return [参数说明]
     * 
     * @return ExemptStrategy [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    protected DetailStrategy getDetailStrategy() {
        DetailStrategy detailStrategy = StrategyContext.getContext()
                .getStrategy(DetailStrategy.class,
                        "defaultLoanBillDetailStrategy");
        return detailStrategy;
    }
    
    /**
     * @param loanAccount
     * @param handler
     * @param repayDate
     * @return
     */
    @Override
    public LoanAccountDetail buildLoanAccountDetail(LoanAccount loanAccount,
            PaymentScheduleHandler handler, Date repayDate) {
        DetailStrategy detailStrategy = getDetailStrategy();
        LoanAccountDetail detail = detailStrategy
                .buildLoanAccountDetail(loanAccount, handler, repayDate);
        return detail;
    }
    
    /**
     * 获取豁免策略<br/>
     * <功能详细描述>
     * @return [参数说明]
     * 
     * @return ExemptStrategy [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    protected ExemptStrategy getExemptStrategy() {
        ExemptStrategy exemptStrategy = StrategyContext.getContext()
                .getStrategy(ExemptStrategy.class, "defaultExemptStrategy");
        return exemptStrategy;
    }
    
    /**
     * @param loanAccount
     * @param exemptIntention
     * @return
     */
    @Override
    public LATradingRecord exempt(LoanAccount loanAccount,
            ExemptIntention exemptIntention) {
        ExemptStrategy exemptStrategy = getExemptStrategy();
        LATradingRecord tr = exemptStrategy.exempt(loanAccount,
                exemptIntention);
        return tr;
    }
    
    /**
     * @param loanAccount
     * @param handler
     * @param exemptIntention
     * @return
     */
    @Override
    public ExemptAllocator assignOfExempt(LoanAccount loanAccount,
            PaymentScheduleHandler handler, ExemptIntention exemptIntention) {
        ExemptStrategy exemptStrategy = getExemptStrategy();
        ExemptAllocator exemptAllocator = exemptStrategy
                .assignOfExempt(loanAccount, handler, exemptIntention);
        return exemptAllocator;
    }
    
    /**
     * @param loanAccount
     * @param handler
     * @param exemptIntention
     * @return
     */
    @Override
    public Map<FeeItemEnum, BigDecimal> mainAssignOfExempt(
            LoanAccount loanAccount, PaymentScheduleHandler handler,
            ExemptIntention exemptIntention) {
        ExemptStrategy exemptStrategy = getExemptStrategy();
        Map<FeeItemEnum, BigDecimal> resMap = exemptStrategy
                .mainAssignOfExempt(loanAccount, handler, exemptIntention);
        return resMap;
    }
    
    /**
     * @param tradingRecord
     * @param loanAccount
     * @param handler
     * @param exemptAllocator
     * @return
     */
    @Override
    public List<ExemptRecordEntry> assignOfExempt(LATradingRecord tradingRecord,
            LoanAccount loanAccount, PaymentScheduleHandler handler,
            ExemptAllocator exemptAllocator) {
        ExemptStrategy exemptStrategy = getExemptStrategy();
        List<ExemptRecordEntry> resList = exemptStrategy.assignOfExempt(
                tradingRecord, loanAccount, handler, exemptAllocator);
        return resList;
    }
    
    /**
     * 获取还款策略<br/>
     * <功能详细描述>
     * @return [参数说明]
     * 
     * @return RepayStrategy [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    protected RepayStrategy getRepayStrategy() {
        RepayStrategy repayStrategy = StrategyContext.getContext()
                .getStrategy(RepayStrategy.class, "defaultRepayStrategy");
        return repayStrategy;
    }
    
    /**
     * @param loanAccount
     * @param repayIntention
     * @return
     */
    @Override
    public LATradingRecord repay(LoanAccount loanAccount,
            RepayIntention repayIntention) {
        RepayStrategy repayStrategy = getRepayStrategy();
        LATradingRecord tr = repayStrategy.repay(loanAccount, repayIntention);
        return tr;
    }
    
    /**
     * @param loanAccount
     * @param paymentScheduleHandler
     * @param repayIntention
     * @return
     */
    @Override
    public RepayAllocator assignOfRepay(LoanAccount loanAccount,
            PaymentScheduleHandler paymentScheduleHandler,
            RepayIntention repayIntention) {
        RepayStrategy repayStrategy = getRepayStrategy();
        RepayAllocator assignAllocator = repayStrategy.assignOfRepay(
                loanAccount, paymentScheduleHandler, repayIntention);
        return assignAllocator;
    }
    
    /**
     * @param loanAccount
     * @param handler
     * @param repayIntention
     * @return
     */
    @Override
    public Map<FeeItemEnum, BigDecimal> autoAssignOfRepay(
            LoanAccount loanAccount, PaymentScheduleHandler handler,
            RepayIntention repayIntention) {
        RepayStrategy repayStrategy = getRepayStrategy();
        Map<FeeItemEnum, BigDecimal> resMap = repayStrategy
                .autoAssignOfRepay(loanAccount, handler, repayIntention);
        return resMap;
    }
    
    /**
     * @param tradingRecord
     * @param loanAccount
     * @param feeItemCfgMap
     * @param handler
     * @param repayIntention
     * @param received
     * @return
     */
    @Override
    public List<PaymentRecordEntry> assignOfRepay(LATradingRecord tradingRecord,
            LoanAccount loanAccount, PaymentScheduleHandler handler,
            RepayAllocator assignAllocator, boolean isReceived) {
        RepayStrategy repayStrategy = getRepayStrategy();
        List<PaymentRecordEntry> resList = repayStrategy.assignOfRepay(
                tradingRecord,
                loanAccount,
                handler,
                assignAllocator,
                isReceived);
        return resList;
    }
    
    /**
     * @param loanAccount
     * @param handler
     * @param tradingRecord
     * @param repayIntention
     * @return
     */
    @Override
    public List<OverRepayRecord> buildOverRepayRecords(LoanAccount loanAccount,
            PaymentScheduleHandler handler, LATradingRecord tradingRecord,
            RepayIntention repayIntention) {
        RepayStrategy repayStrategy = getRepayStrategy();
        List<OverRepayRecord> resList = repayStrategy.buildOverRepayRecords(
                loanAccount, handler, tradingRecord, repayIntention);
        return resList;
    }
    
    /**
     * @param loanAccount
     * @param feeItemMap
     * @param feeItemCfgMap
     * @param handler
     * @param tradingRecord
     * @param repayDate
     * @return
     */
    @Override
    public List<ChargeRecordEntry> buildRepayChargeEntryList(
            LoanAccount loanAccount,
            Map<FeeItemEnum, LoanAccountFeeItem> feeItemMap,
            Map<FeeItemEnum, FeeItemCfg> feeItemCfgMap,
            PaymentScheduleHandler handler, LATradingRecord tradingRecord,
            Date repayDate) {
        RepayStrategy repayStrategy = getRepayStrategy();
        List<ChargeRecordEntry> resList = repayStrategy
                .buildRepayChargeEntryList(loanAccount,
                        feeItemMap,
                        feeItemCfgMap,
                        handler,
                        tradingRecord,
                        repayDate);
        return resList;
    }
    
    /**
     * 
     * @param loanAccount
     * @param handler
     * @param chargeRecordEntryList
     * @return
     */
    @Override
    public List<ChargeRecordEntry> resetChargeRecordEntryList(
            LoanAccount loanAccount, PaymentScheduleHandler handler,
            List<ChargeRecordEntry> chargeRecordEntryList) {
        RepayStrategy repayStrategy = getRepayStrategy();
        List<ChargeRecordEntry> resList = repayStrategy
                .resetChargeRecordEntryList(loanAccount,
                        handler,
                        chargeRecordEntryList);
        return resList;
    }
    
    /**
     * @param loanAccount
     * @param feeItemMap
     * @param handler
     * @param tradingRecord
     * @param overRepayRecord
     * @param isReceived
     * @return
     */
    @Override
    public List<PaymentRecordEntry> buildCEHKEntryList(LoanAccount loanAccount,
            PaymentScheduleHandler handler, LATradingRecord tradingRecord,
            List<OverRepayRecord> overRepayRecords, boolean isReceived) {
        RepayStrategy repayStrategy = getRepayStrategy();
        List<PaymentRecordEntry> resList = repayStrategy.buildCEHKEntryList(
                loanAccount,
                handler,
                tradingRecord,
                overRepayRecords,
                isReceived);
        return resList;
    }
    
    /**
     * 获取结清策略<br/>
     * <功能详细描述>
     * @return [参数说明]
     * 
     * @return SettleStrategy [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    protected SettleStrategy getSettleStrategy() {
        SettleStrategy settleStrategy = StrategyContext.getContext()
                .getStrategy(SettleStrategy.class, "defaultSettleStrategy");
        return settleStrategy;
    }
    
    /**
     * @param loanAccount
     * @param settleIntention
     * @return
     */
    @Override
    public LATradingRecord settle(LoanAccount loanAccount,
            SettleIntention settleIntention) {
        SettleStrategy settleStrategy = getSettleStrategy();
        LATradingRecord tradingRecord = settleStrategy.settle(loanAccount,
                settleIntention);
        return tradingRecord;
    }
    
    /**
     * @param loanAccount
     * @param feeItemCfgMap
     * @param handler
     * @param repayDate
     * @return
     */
    @Override
    public LoanAccountSettleDetail buildLoanAccountSettleDetail(
            LoanAccount loanAccount,
            Map<FeeItemEnum, LoanAccountFeeItem> feeItemMap,
            Map<FeeItemEnum, FeeItemCfg> feeItemCfgMap,
            PaymentScheduleHandler handler, Date repayDate) {
        SettleStrategy settleStrategy = getSettleStrategy();
        LoanAccountSettleDetail detail = settleStrategy
                .buildLoanAccountSettleDetail(loanAccount,
                        feeItemMap,
                        feeItemCfgMap,
                        handler,
                        repayDate);
        return detail;
    }
    
    /**
     * @param loanAccount
     * @param feeItemCfgMap
     * @param handler
     * @param settleIntention
     * @return
     */
    @Override
    public Map<FeeItemEnum, BigDecimal> autoAssignOfSettle(
            LoanAccount loanAccount,
            Map<FeeItemEnum, LoanAccountFeeItem> feeItemMap,
            Map<FeeItemEnum, FeeItemCfg> feeItemCfgMap,
            PaymentScheduleHandler handler, SettleIntention settleIntention) {
        SettleStrategy settleStrategy = getSettleStrategy();
        Map<FeeItemEnum, BigDecimal> resMap = settleStrategy.autoAssignOfSettle(
                loanAccount,
                feeItemMap,
                feeItemCfgMap,
                handler,
                settleIntention);
        return resMap;
    }
    
    /**
     * @param loanAccount
     * @param feeItemMap
     * @param feeItemCfgMap
     * @param handler
     * @param tradingRecord
     * @param repayDate
     * @return
     */
    @Override
    public List<ChargeRecordEntry> buildSettleChargeEntryList(
            LoanAccount loanAccount,
            Map<FeeItemEnum, LoanAccountFeeItem> feeItemMap,
            Map<FeeItemEnum, FeeItemCfg> feeItemCfgMap,
            PaymentScheduleHandler handler, LATradingRecord tradingRecord,
            Date repayDate) {
        SettleStrategy settleStrategy = getSettleStrategy();
        List<ChargeRecordEntry> resList = settleStrategy
                .buildSettleChargeEntryList(loanAccount,
                        feeItemMap,
                        feeItemCfgMap,
                        handler,
                        tradingRecord,
                        repayDate);
        return resList;
    }
    
    /**
     * @param loanAccount
     * @param handler
     * @param settleIntention
     * @return
     */
    @Override
    public RepayAllocator assignOfSettle(LoanAccount loanAccount,
            PaymentScheduleHandler handler, SettleIntention settleIntention) {
        SettleStrategy settleStrategy = getSettleStrategy();
        RepayAllocator allocator = settleStrategy.assignOfSettle(loanAccount,
                handler,
                settleIntention);
        return allocator;
    }
    
    /**
     * @param loanAccount
     * @param handler
     * @param settleIntention
     * @return
     */
    @Override
    public ExemptAllocator assignOfSettleExempt(LoanAccount loanAccount,
            PaymentScheduleHandler handler, SettleIntention settleIntention) {
        SettleStrategy settleStrategy = getSettleStrategy();
        ExemptAllocator ea = settleStrategy.assignOfSettleExempt(loanAccount,
                handler,
                settleIntention);
        return ea;
    }
    
    /**
     * @param tradingRecord
     * @param loanAccount
     * @param handler
     * @param exemptAllocator
     * @return
     */
    @Override
    public List<ExemptRecordEntry> assignOfSettleExempt(
            LATradingRecord tradingRecord, LoanAccount loanAccount,
            PaymentScheduleHandler handler, ExemptAllocator exemptAllocator) {
        SettleStrategy settleStrategy = getSettleStrategy();
        List<ExemptRecordEntry> ereList = settleStrategy.assignOfSettleExempt(
                tradingRecord, loanAccount, handler, exemptAllocator);
        return ereList;
    }
    
    /**
     * @param tradingRecord
     * @param loanAccount
     * @param feeItemCfgMap
     * @param handler
     * @param assignAllocator
     * @param isReceived
     * @return
     */
    @Override
    public List<PaymentRecordEntry> assignOfSettle(
            LATradingRecord tradingRecord, LoanAccount loanAccount,
            PaymentScheduleHandler handler, RepayAllocator assignAllocator,
            boolean isReceived) {
        SettleStrategy settleStrategy = getSettleStrategy();
        List<PaymentRecordEntry> resList = settleStrategy.assignOfSettle(
                tradingRecord,
                loanAccount,
                handler,
                assignAllocator,
                isReceived);
        return resList;
    }
    
    /**
     * 获取撤销策略实现<br/>
     * <功能详细描述>
     * @return [参数说明]
     * 
     * @return RevokeStrategy [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    protected RevokeStrategy getRevokeStrategy() {
        RevokeStrategy revoker = StrategyContext.getContext()
                .getStrategy(RevokeStrategy.class, "defaultRevokeStrategy");
        return revoker;
    }
    
    /**
     * @param loanAccountId
     * @return
     */
    @Override
    public List<LATradingRecord> getRevokeAbleTradingRecords(
            LoanAccount loanAccount) {
        RevokeStrategy revoker = getRevokeStrategy();
        List<LATradingRecord> tradingRecords = revoker
                .getRevokeAbleTradingRecords(loanAccount);
        return tradingRecords;
    }
    
    /**
     * @param loanAccountId
     * @return
     */
    @Override
    public List<LATradingRecord> getRevokeAbleRepayTradingRecords(
            LoanAccount loanAccount) {
        RevokeStrategy revoker = getRevokeStrategy();
        List<LATradingRecord> tradingRecords = revoker
                .getRevokeAbleRepayTradingRecords(loanAccount);
        return tradingRecords;
    }
    
    /**
     * @param tradingRecord
     * @return
     */
    @Override
    public boolean isRevokeAble(LoanAccount loanAccount,
            LATradingRecord tradingRecord) {
        RevokeStrategy revoker = getRevokeStrategy();
        boolean isRevokeAble = revoker.isRevokeAble(loanAccount, tradingRecord);
        return isRevokeAble;
    }
    
    /**
     * @param tradingRecord
     * @return
     */
    @Override
    public boolean isRefundAble(LoanAccount loanAccount,
            LATradingRecord tradingRecord) {
        RevokeStrategy revoker = getRevokeStrategy();
        boolean isRefundAble = revoker.isRefundAble(loanAccount, tradingRecord);
        return isRefundAble;
    }
    
    /**
     * @param tradingRecord
     * @return
     */
    @Override
    public boolean isRevokeToWaitAccountAble(LoanAccount loanAccount,
            LATradingRecord tradingRecord) {
        RevokeStrategy revoker = getRevokeStrategy();
        boolean isRevokeToWaitAccountAble = revoker
                .isRevokeToWaitAccountAble(loanAccount, tradingRecord);
        return isRevokeToWaitAccountAble;
    }
    
    /**
     * @param revokeType
     * @param loanAccount
     * @param revokeTradingRecordId
     * @param revokeResean
     * @return
     */
    @Override
    public boolean revoke(RevokeTypeEnum revokeType, LoanAccount loanAccount,
            String revokeTradingRecordId, String revokeResean) {
        RevokeStrategy revoker = getRevokeStrategy();
        boolean flag = revoker.revoke(revokeType,
                loanAccount,
                revokeTradingRecordId,
                revokeResean);
        return flag;
    }
    
    /**
     * @param revokeType
     * @param loanAccount
     * @param revokeTradingRecordId
     * @param revokeResean
     * @return
     */
    @Override
    public boolean revokeToWaitAccount(RevokeTypeEnum revokeType,
            LoanAccount loanAccount, String revokeTradingRecordId,
            String revokeResean) {
        RevokeStrategy revoker = getRevokeStrategy();
        boolean flag = revoker.revokeToWaitAccount(revokeType,
                loanAccount,
                revokeTradingRecordId,
                revokeResean);
        return flag;
    }
    
    /**
     * @param revokeType
     * @param loanAccount
     * @param revokeTradingRecordId
     * @param revokeResean
     * @param bankAccountId
     * @return
     */
    @Override
    public boolean refund(RevokeTypeEnum revokeType, LoanAccount loanAccount,
            String revokeTradingRecordId, String revokeResean,
            String bankAccountId) {
        RevokeStrategy revoker = getRevokeStrategy();
        boolean flag = revoker.refund(revokeType,
                loanAccount,
                revokeTradingRecordId,
                revokeResean,
                bankAccountId);
        return flag;
    }
    
    /**
     * 获取撤销策略实现<br/>
     * <功能详细描述>
     * @return [参数说明]
     * 
     * @return RevokeStrategy [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    protected PaymentScheduleEntryBuilder getPaymentScheduleEntryBuilder(
            RepayWayEnum repayWay) {
        PaymentScheduleEntryBuilder pseBuilder = StrategyContext.getContext()
                .getStrategy(PaymentScheduleEntryBuilder.class,
                        "pseBuilderAYHBFX_PXDJ");
        return pseBuilder;
    }
    
    /**
     * @param loanAmount
     * @param totalPeriod
     * @param feeItemMap
     * @param params
     * @param scale
     * @return
     */
    @Override
    public BigDecimal caculateMonthlyRepayAmount(BigDecimal loanAmount,
            int totalPeriod, Map<FeeItemEnum, String> feeItemMap,
            Map<String, Object> params, int scale, RepayWayEnum repayWay) {
        PaymentScheduleEntryBuilder pseBuilder = getPaymentScheduleEntryBuilder(
                repayWay);
        BigDecimal res = pseBuilder.caculateMonthlyRepayAmount(loanAmount,
                totalPeriod,
                feeItemMap,
                params,
                scale,
                repayWay);
        return res;
    }
    
    /**
     * @param loanAmount
     * @param totalPeriod
     * @param feeItemMap
     * @param params
     * @param scale
     * @return
     */
    @Override
    public Map<FeeItemEnum, BigDecimal> caculateFeeItemAmountMap(
            BigDecimal loanAmount, int totalPeriod,
            Map<FeeItemEnum, String> feeItemMap, Map<String, Object> params,
            int scale, RepayWayEnum repayWay) {
        PaymentScheduleEntryBuilder pseBuilder = getPaymentScheduleEntryBuilder(
                repayWay);
        Map<FeeItemEnum, BigDecimal> resMap = pseBuilder
                .caculateFeeItemAmountMap(loanAmount,
                        totalPeriod,
                        feeItemMap,
                        params,
                        scale,
                        repayWay);
        return resMap;
    }
    
    /**
     * @param loanAmount
     * @param totalPeriod
     * @param feeItemMap
     * @param params
     * @param scale
     * @return
     */
    @Override
    public BigDecimal caculateFactLoanAmount(BigDecimal loanAmount,
            int totalPeriod, Map<FeeItemEnum, String> feeItemMap,
            Map<String, Object> params, int scale, RepayWayEnum repayWay) {
        PaymentScheduleEntryBuilder pseBuilder = getPaymentScheduleEntryBuilder(
                repayWay);
        BigDecimal res = pseBuilder.caculateFactLoanAmount(loanAmount,
                totalPeriod,
                feeItemMap,
                params,
                scale,
                repayWay);
        return res;
    }
    
    /**
     * @param loanAccountId
     * @param feeItemMap
     * @param params
     * @param scale
     * @return
     */
    @Override
    public List<PaymentScheduleEntry> buildPaymentScheduleEntryList(
            String loanAccountId, Map<FeeItemEnum, String> feeItemMap,
            Map<String, Object> params, int scale, RepayWayEnum repayWay) {
        PaymentScheduleEntryBuilder pseBuilder = getPaymentScheduleEntryBuilder(
                repayWay);
        List<PaymentScheduleEntry> resList = pseBuilder
                .buildPaymentScheduleEntryList(loanAccountId,
                        feeItemMap,
                        params,
                        scale,
                        repayWay);
        return resList;
    }
    
    /**
     * @param loanAccount
     * @param loanAccountFeeItemMap
     * @param params
     * @param scale
     * @return
     */
    @Override
    public List<PaymentScheduleEntry> buildPaymentScheduleEntryList(
            LoanAccount loanAccount,
            Map<FeeItemEnum, LoanAccountFeeItem> loanAccountFeeItemMap,
            Map<String, Object> params, int scale, RepayWayEnum repayWay) {
        PaymentScheduleEntryBuilder pseBuilder = getPaymentScheduleEntryBuilder(
                repayWay);
        List<PaymentScheduleEntry> resList = pseBuilder
                .buildPaymentScheduleEntryList(loanAccount,
                        loanAccountFeeItemMap,
                        params,
                        scale,
                        repayWay);
        return resList;
    }
    
    /**
     * 获取逾期利息计费略<br/>
     * <功能详细描述>
     * @return [参数说明]
     * 
     * @return YQLXChargeStrategy [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    protected YQLXChargeStrategy getYQLXChargeStrategy() {
        YQLXChargeStrategy yqlxStrategy = StrategyContext.getContext()
                .getStrategy(YQLXChargeStrategy.class, "defaultYQLXStrategy");
        return yqlxStrategy;
    }
    
    /**
     * @param loanAccount
     * @param feeItemMap
     * @param feeItemCfgMap
     * @param handler
     * @param tradingRecord
     * @param recordDate
     * @return vg jm
     */
    @Override
    public List<OverdueInterestChargeRecord> buildYQLXRecords(
            LoanAccount loanAccount,
            Map<FeeItemEnum, LoanAccountFeeItem> feeItemMap,
            Map<FeeItemEnum, FeeItemCfg> feeItemCfgMap,
            PaymentScheduleHandler handler, LATradingRecord tradingRecord,
            Date recordDate) {
        YQLXChargeStrategy yqlxStrategy = getYQLXChargeStrategy();
        List<OverdueInterestChargeRecord> chargeRecords = yqlxStrategy
                .buildYQLXRecords(loanAccount,
                        feeItemMap,
                        feeItemCfgMap,
                        handler,
                        tradingRecord,
                        recordDate);
        return chargeRecords;
    }
}
