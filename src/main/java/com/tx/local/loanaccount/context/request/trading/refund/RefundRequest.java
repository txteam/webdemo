package com.tx.local.loanaccount.context.request.trading.refund;
///*
// * 描          述:  <描述>
// * 修  改   人:  lenovo
// * 修改时间:  2014年6月17日
// * <修改描述:>
// */
//package com.tx.local.loanaccount.context.request.trading.revoke.revokerepay;
//
//import com.tx.local.loanaccount.dto.LATradingSummaryEnum;
//import com.tx.local.loanaccount.dto.RequestTypeEnum;
//import com.tx.local.loanaccount.model.LATradingRecordTypeEnum;
//import com.tx.local.loanaccount.model.RequestSourceTypeEnum;
//
///**
// * 将最后一笔交易作归还款项处理<br/>
// * 
// * @author  lenovo
// * @version  [版本号, 2014年6月17日]
// * @see  [相关类/方法]
// * @since  [产品/模块版本]
// */
//public class RefundRequest extends AbstractRevokeRepayRequest {
//    
//    /** 归还款项时选中的归还款款项银行账户 */
//    private final String bankAccountId;
//    
//    /** <默认构造函数> */
//    public RefundRequest(String bankAccountId, String loanAccountId,
//            String revokeTradingRecordId, String revokeReason) {
//        super(loanAccountId, revokeTradingRecordId, revokeReason,
//                RequestSourceTypeEnum.操作请求, RequestTypeEnum.归还款项);
//        
//        this.bankAccountId = bankAccountId;
//    }
//    
//    /**
//     * @return 返回 bankAccountId
//     */
//    public String getBankAccountId() {
//        return bankAccountId;
//    }
//    
//    /**
//     * @return
//     */
//    @Override
//    public LATradingRecordTypeEnum getTradingRecordType() {
//        return LATradingRecordTypeEnum.归还款项;
//    }
//    
//    /**
//     * @return
//     */
//    @Override
//    public LATradingSummaryEnum getTradingSummary() {
//        return LATradingSummaryEnum.归还款项;
//    }
//}
