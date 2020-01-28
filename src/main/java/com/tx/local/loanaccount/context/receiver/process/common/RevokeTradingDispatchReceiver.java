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
//import com.tx.component.command.context.CommandContext;
//import com.tx.component.command.context.CommandResponse;
//import com.tx.core.exceptions.util.AssertUtils;
//import com.tx.local.loanaccount.context.receiver.process.AbstractProcessReceiver;
//import com.tx.local.loanaccount.context.request.process.RevokeTradingDispatchRequest;
//import com.tx.local.loanaccount.context.request.trading.revoke.revokeearlysettle.RevokeCashEarlySettleRequest;
//import com.tx.local.loanaccount.context.request.trading.revoke.revokeloan.RevokeNewLoanRequest;
//import com.tx.local.loanaccount.context.request.trading.revoke.revokerepay.RevokeAutoTransferRepayRequest;
//import com.tx.local.loanaccount.context.request.trading.revoke.revokerepay.RevokeCashRepayRequest;
//import com.tx.local.loanaccount.context.request.trading.revoke.revokerepay.RevokeOverRepayRequest;
//import com.tx.local.loanaccount.model.LoanAccount;
//import com.tx.local.loanaccount.model.LoanAccountTypeEnum;
//import com.tx.local.loanaccount.model.LATradingRecord;
//import com.tx.local.loanaccount.service.LATradingRecordService;
//
///**
// * 撤销贷款账户的最后一笔交易<br/>
// * 
// * @author  lenovo
// * @version  [版本号, 2014年6月17日]
// * @see  [相关类/方法]
// * @since  [产品/模块版本]
// */
//@Component("revokeTradingDispatchReceiver")
//public class RevokeTradingDispatchReceiver extends
//        AbstractProcessReceiver<RevokeTradingDispatchRequest> {
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
//    public void handle(RevokeTradingDispatchRequest request, CommandResponse response,
//            LoanAccount loanAccount) {
//        //查询最后一笔交易
//        String revokeTradingRecordId = request.getRevokeTradingRecordId();
//        LATradingRecord revokeTradingRecord = tradingRecordService.findTradingRecordById(revokeTradingRecordId);
//        
//        switch (revokeTradingRecord.getTradingRecordType()) {
//            case 新贷:
//                //如果最后一笔交易为新贷：调用新贷退回
//                RevokeNewLoanRequest revokeNewLoanRequest = new RevokeNewLoanRequest(
//                        request.getLoanAccountId(),
//                        request.getRevokeTradingRecordId(),
//                        request.getRevokeReason());
//                CommandContext.getContext().post(revokeNewLoanRequest,
//                        response);
//                break;
//            case 现金还款:
//                RevokeCashRepayRequest revokeCashRepayRequest = new RevokeCashRepayRequest(
//                        request.getLoanAccountId(),
//                        request.getRevokeTradingRecordId(),
//                        request.getRevokeReason(), request.getSourceType());
//                CommandContext.getContext().post(revokeCashRepayRequest,
//                        response);
//                break;
//            case 自动转账还款:
//                RevokeAutoTransferRepayRequest revokeAutoTransferRepayRequest = new RevokeAutoTransferRepayRequest(
//                        request.getLoanAccountId(),
//                        request.getRevokeTradingRecordId(),
//                        request.getRevokeReason(), request.getSourceType());
//                CommandContext.getContext()
//                        .post(revokeAutoTransferRepayRequest, response);
//                break;
//            case 现金提前结清:
//                RevokeCashEarlySettleRequest rcesRequest = new RevokeCashEarlySettleRequest(
//                        request.getLoanAccountId(),
//                        request.getRevokeTradingRecordId(),
//                        request.getRevokeReason(), request.getSourceType());
//                CommandContext.getContext().post(rcesRequest, response);
//                break;
//            case 超额还款:
//                RevokeOverRepayRequest revokeOverRepayRequest = new RevokeOverRepayRequest(
//                        request.getLoanAccountId(), revokeTradingRecordId,
//                        request.getRevokeReason(), request.getSourceType());
//                CommandContext.getContext().post(revokeOverRepayRequest,
//                        response);
//                break;
//            default:
//                AssertUtils.isTrue(false,
//                        "错误的交易记录类型:{}",
//                        new Object[] { revokeTradingRecord.getTradingRecordType() });
//        }
//    }
//}
