///*
// * 描          述:  <描述>
// * 修  改   人:  lenovo
// * 修改时间:  2014年6月17日
// * <修改描述:>
// */
//package com.tx.local.loanaccount.context.receiver.process.common;
//
//import javax.annotation.Resource;
//
//import org.springframework.stereotype.Component;
//
//import com.tx.component.command.context.HelperFactory;
//import com.tx.component.command.context.CommandContext;
//import com.tx.component.command.context.CommandResponse;
//import com.tx.core.exceptions.util.AssertUtils;
//import com.tx.local.loanaccount.context.receiver.process.AbstractProcessReceiver;
//import com.tx.local.loanaccount.context.request.process.RedoRepayDispatchRequest;
//import com.tx.local.loanaccount.context.request.trading.repay.TransferRepayRequest;
//import com.tx.local.loanaccount.helper.repay.RepayIntentionHelper;
//import com.tx.local.loanaccount.model.LoanAccount;
//import com.tx.local.loanaccount.model.LoanAccountTypeEnum;
//import com.tx.local.loanaccount.model.RepayIntention;
//import com.tx.local.loanaccount.model.RepayIntentionTypeEnum;
//import com.tx.local.loanaccount.model.LATradingRecord;
//import com.tx.local.loanaccount.service.LATradingRecordService;
//
///**
// * 重做贷款账户的最后一笔交易<br/>
// * 
// * @author  lenovo
// * @version  [版本号, 2014年6月17日]
// * @see  [相关类/方法]
// * @since  [产品/模块版本]
// */
//@Component("redoRepayDispatchReceiver")
//public class RedoRepayDispatchReceiver extends
//        AbstractProcessReceiver<RedoRepayDispatchRequest> {
//    
//    @Resource(name = "tradingRecordService")
//    private LATradingRecordService tradingRecordService;
//    
//    @Override
//    public final LoanAccountTypeEnum[] getSupportLoanAccountTypes() {
//        return new LoanAccountTypeEnum[] { LoanAccountTypeEnum.P2P };
//    }
//    
//    @Override
//    public void handle(RedoRepayDispatchRequest request,
//            CommandResponse response, LoanAccount loanAccount) {
//        //查询最后一笔交易
//        String revokedTradingRecordId = request.getRevokedTradingRecordId();
//        LATradingRecord revokeTradingRecord = tradingRecordService.findTradingRecordById(revokedTradingRecordId);
//        
//        RepayIntentionHelper repayIntentionHelper = HelperFactory.getHelper(RepayIntentionHelper.class,
//                loanAccount.getLoanAccountType());
//        RepayIntention repayIntention = repayIntentionHelper.buildRepayIntentionBySourceTradingRecord(revokeTradingRecord);
//        
//        //重做的交易，原交易应当为到账交易
//        AssertUtils.isTrue(revokeTradingRecord.isReceived(),
//                "重做交易的原交易应当为到账交易。revokedTradingRecordId:{}",
//                revokedTradingRecordId);
//        AssertUtils.isTrue(RepayIntentionTypeEnum.TRANSFER_REPAY.equals(revokeTradingRecord.getRepayType()),
//                "原来交易的还款类型应当为 AUTO_TRANSFER_REPAY 实际上为:{revokeTradingRecord.getRepayType()}",
//                revokedTradingRecordId);
//        
//        switch (revokeTradingRecord.getTradingRecordType()) {
//            case 自动转账还款:
//            case 超额还款:
//                TransferRepayRequest atrRequest = new TransferRepayRequest(
//                        true, loanAccount.getId(), repayIntention,
//                        request.getSourceType());
//                CommandContext.getContext().post(atrRequest, response);
//                break;
//            default:
//                AssertUtils.isTrue(false,
//                        "错误的交易记录类型:{}",
//                        new Object[] { revokeTradingRecord.getTradingRecordType() });
//        }
//    }
//}
