///*
// * 描          述:  <描述>
// * 修  改   人:  Administrator
// * 修改时间:  2016年5月29日
// * <修改描述:>
// */
//package com.tx.local.loanaccount.context.receiver.process.p2p;
//
//import java.math.BigDecimal;
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//import java.util.Map.Entry;
//
//import javax.annotation.Resource;
//
//import org.apache.commons.collections.CollectionUtils;
//import org.apache.commons.collections.MapUtils;
//import org.joda.time.DateTime;
//import org.springframework.stereotype.Component;
//
//import com.tx.component.command.context.CommandContext;
//import com.tx.component.command.context.CommandResponse;
//import com.tx.core.exceptions.util.AssertUtils;
//import com.tx.local.clientaccount.context.request.trading.settle.SettleInvestRequest;
//import com.tx.local.clientaccount.model.SettleInvestIntention;
//import com.tx.local.clientaccount.service.ClientInvestItemAccountService;
//import com.tx.local.clientaccount.service.ClientInvestItemSettleScheduleService;
//import com.tx.local.investaccount.model.InvestAccount;
//import com.tx.local.investaccount.model.InvestAccountStatusEnum;
//import com.tx.local.investaccount.model.InvestAccountSettleSchedule;
//import com.tx.local.loanaccount.LoanAccountConstants;
//import com.tx.local.loanaccount.context.request.process.AutoTransferRepayAndSettleDispatcherRequest;
//import com.tx.local.loanaccount.context.request.trading.repay.AutoTransferRepayRequest;
//import com.tx.local.loanaccount.context.request.trading.repay.OverRepayRequest;
//import com.tx.local.loanaccount.model.LoanAccount;
//import com.tx.local.loanaccount.model.RepayChannelTypeEnum;
//import com.tx.local.loanaccount.model.RepayIntention;
//import com.tx.local.loanaccount.model.TradingRecord;
//
///**
// * 自动转账还款结算调度处理器<br/>
// * <功能详细描述>
// * 
// * @author  Administrator
// * @version  [版本号, 2016年5月29日]
// * @see  [相关类/方法]
// * @since  [产品/模块版本]
// */
//@Component
//public class AutoTransferRepayAndSettleDispatcherReceiver
//        extends
//        AbstractP2PProcessReceiver<AutoTransferRepayAndSettleDispatcherRequest> {
//    
//    @Resource(name = "clientInvestItemAccountService")
//    private ClientInvestItemAccountService clientInvestItemAccountService;
//    
//    @Resource(name = "clientInvestItemSettleScheduleService")
//    private ClientInvestItemSettleScheduleService clientInvestItemSettleScheduleService;
//    
//    /**
//     * @param request
//     * @param response
//     * @param loanAccount
//     */
//    @Override
//    public void validateRequest(
//            AutoTransferRepayAndSettleDispatcherRequest request,
//            CommandResponse response, LoanAccount loanAccount) {
//        AssertUtils.notNull(loanAccount, "贷款账户不能为空.");
//        AssertUtils.notNull(request.getRepayIntention(), "还款意愿不能为空.");
//        AssertUtils.notNull(request.getRepayIntention()
//                .getAmount()
//                .compareTo(BigDecimal.ZERO) > 0,
//                "还款意愿中还款金额应大于0.");
//        
//        AssertUtils.isTrue("INVEST_PROJECT".equals(loanAccount.getRefType()),
//                "贷款账户关联类型应为投资项目.");
//        AssertUtils.notEmpty(loanAccount.getRefId(), "投资项目id不能为空.");
//        AssertUtils.notEmpty(loanAccount.getClientId(),"客户id不能为空。");
//        AssertUtils.notEmpty(loanAccount.getClientAccountId(),"客户账户id不能为空。");
//    }
//    
//    /**
//     * @param request
//     * @param response
//     * @param loanAccount
//     */
//    @Override
//    public void handle(
//            AutoTransferRepayAndSettleDispatcherRequest request,
//            CommandResponse response, LoanAccount loanAccount) {
//        List<TradingRecord> resTradingRecordList = new ArrayList<>();
//        
//        CommandResponse atrResponse = buildAutoTransferRepay(request,
//                loanAccount.getId(),
//                request.getRepayIntention(),
//                resTradingRecordList);
//        
//        //检查是否存在超额还款金额1s
//        @SuppressWarnings("unchecked")
//        Map<RepayChannelTypeEnum, BigDecimal> atrOverRepayAmountMap = (Map<RepayChannelTypeEnum, BigDecimal>) atrResponse.getAttribute(LoanAccountConstants.RESPONSE_KEY_OVER_REPAY_AMOUNT_MAP_KEY);
//        buildOverRepay(request,
//                atrOverRepayAmountMap,
//                resTradingRecordList);
//        
//        String investProjectId = request.getLoanAccount().getRefId();
//        Map<String, Object> params = new HashMap<String, Object>();
//        DateTime maxNextSettleDateTime = new DateTime(request.getRepayIntention().getRepayDate());
//        maxNextSettleDateTime = maxNextSettleDateTime.plusDays(1);
//        maxNextSettleDateTime = new DateTime(maxNextSettleDateTime.getYear(),maxNextSettleDateTime.getMonthOfYear(),
//                maxNextSettleDateTime.getDayOfMonth(),0,0,0);
//        
//        params.put("status", InvestAccountStatusEnum.REPAYING);
//        params.put("maxNextSettleDate", maxNextSettleDateTime.toDate());
//        params.put("investProjectId", investProjectId);
//        
//        List<InvestAccount> ciiaList = this.clientInvestItemAccountService.queryClientInvestItemAccountList(params);
//        
//        if(!CollectionUtils.isEmpty(ciiaList)){
//            for(InvestAccount ciiaTemp : ciiaList){
//                String clientInvestItemAccountId = ciiaTemp.getId();
//                List<InvestAccountSettleSchedule> settleSchedules = this.clientInvestItemSettleScheduleService.queryClientInvestItemSettleScheduleListByClientInvestItemAccountId(clientInvestItemAccountId);
//                List<InvestAccountSettleSchedule> shouldSettleSchedule = new ArrayList<>();
//                
//                for (InvestAccountSettleSchedule settleSchedule : settleSchedules) {
//                    if (settleSchedule.getShouldSettleDate()
//                            .compareTo(maxNextSettleDateTime.toDate()) < 0
//                            && settleSchedule.getReceivableAmount()
//                                    .subtract(settleSchedule.getActualReceivedAmount())
//                                    .compareTo(BigDecimal.ZERO) > 0) {
//                        shouldSettleSchedule.add(settleSchedule);
//                    }
//                }
//                
//                if (!CollectionUtils.isEmpty(shouldSettleSchedule)) {
//                    SettleInvestIntention intention = new SettleInvestIntention(
//                            ciiaTemp, shouldSettleSchedule);
//                    SettleInvestRequest siRequest = new SettleInvestRequest(intention);
//                    CommandContext.getContext().post(siRequest);
//                }
//                
//                InvestAccount newIIA = this.clientInvestItemAccountService.findClientInvestItemAccountById(clientInvestItemAccountId);
//                if (InvestAccountStatusEnum.REPAYING.equals(newIIA.getStatus())) {
//                    newIIA.setNextSettleDate(maxNextSettleDateTime.toDate());
//                    this.clientInvestItemAccountService.updateById(newIIA);
//                }
//            }
//        }
//    }
//    
//    /** 
//     * 构建自动转账还款后的超额还款<br/>
//     * <功能详细描述>
//     * @param request
//     * @param overRepayAmountMap [参数说明]
//     * 
//     * @return void [返回类型说明]
//     * @exception throws [异常类型] [异常说明]
//     * @see [类、类#方法、类#成员]
//     */
//    private void buildOverRepay(AutoTransferRepayAndSettleDispatcherRequest request,
//            Map<RepayChannelTypeEnum, BigDecimal> overRepayAmountMap,
//            List<TradingRecord> tradingRecordList) {
//        if (MapUtils.isEmpty(overRepayAmountMap)) {
//            return;
//        }
//        BigDecimal overRepaySum = BigDecimal.ZERO;
//        for (Entry<RepayChannelTypeEnum, BigDecimal> overRepayAmountEntry : overRepayAmountMap.entrySet()) {
//            if (overRepayAmountEntry.getValue().compareTo(BigDecimal.ZERO) == 0) {
//                continue;
//            }
//            overRepaySum = overRepaySum.add(overRepayAmountEntry.getValue());
//        }
//        if (overRepaySum.compareTo(BigDecimal.ZERO) == 0) {
//            return;
//        }
//        
//        //现金还款时应当仅存在一个超额还款（即超额还款_小额贷款）
//        RepayIntention overRepayRepayIntention = new RepayIntention();
//        overRepayRepayIntention.setAmount(overRepaySum);
//        overRepayRepayIntention.setRepayChannelType(RepayChannelTypeEnum.DK_ZX);
//        overRepayRepayIntention.setLoanAccountId(request.getLoanAccountId());
//        overRepayRepayIntention.setBankAccountId(request.getRepayIntention()
//                .getBankAccountId());
//        overRepayRepayIntention.setRepayAmountType(request.getRepayIntention()
//                .getRepayAmountType());
//        overRepayRepayIntention.setRepayDate(request.getRepayIntention()
//                .getRepayDate());
//        overRepayRepayIntention.setRepayType(request.getRepayIntention()
//                .getRepayType());
//        
//        OverRepayRequest orRequest = new OverRepayRequest(true,
//                request.getLoanAccountId(), overRepayRepayIntention,
//                request.getSourceType());
//        CommandResponse orResponse = CommandContext.getContext().post(orRequest);
//        
//        TradingRecord orTradingRecord = (TradingRecord) orResponse.getAttribute(LoanAccountConstants.RESPONSE_KEY_TRADINGRECORD);
//        tradingRecordList.add(orTradingRecord);
//    }
//    
//    /** 
//     * 构建自动转账还款及对应的扣款任务<br/>
//     * <功能详细描述>
//     * @param request
//     * @param loanAccountId
//     * @param repayIntention
//     * @param repayChannelType2tradingRecordMap
//     * @return [参数说明]
//     * 
//     * @return Response [返回类型说明]
//     * @exception throws [异常类型] [异常说明]
//     * @see [类、类#方法、类#成员]
//     */
//    private CommandResponse buildAutoTransferRepay(
//            AutoTransferRepayAndSettleDispatcherRequest request, String loanAccountId,
//            RepayIntention repayIntention,
//            List<TradingRecord> tradingRecordList) {
//        AutoTransferRepayRequest atrRquest = new AutoTransferRepayRequest(true,
//                loanAccountId, repayIntention, request.getSourceType());
//        CommandResponse crResponse = CommandContext.getContext().post(atrRquest);
//        
//        TradingRecord atrTradingRecord = (TradingRecord) crResponse.getAttribute(LoanAccountConstants.RESPONSE_KEY_TRADINGRECORD);
//        tradingRecordList.add(atrTradingRecord);
//        
//        return crResponse;
//    }
//    
//}
