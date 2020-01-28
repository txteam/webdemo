//package com.tx.local.loanaccount.context.request.trading.charge;
//
//import com.tx.local.loanaccount.model.DeductRecord;
//import com.tx.local.loanaccount.model.LATradingRecordTypeEnum;
//import com.tx.local.loanaccount.model.RequestSourceTypeEnum;
//
///**
// * 逾期利息计费请求<br/>
// *     扣款失败手续费计费请求<br/>
// *     扣款失败后：如果当日已经计收过扣款失败手续费，则无需再进行计收<br/>
// * <功能详细描述>
// * 
// * @author  lenovo
// * @version  [版本号, 2015年1月8日]
// * @see  [相关类/方法]
// * @since  [产品/模块版本]
// */
//public class DeductFailChargeRequest extends AbstractChargeRequest {
//    
//    /** 扣款交易记录 */
//    private final DeductRecord deductRecord;
//    
//    /** 扣款失败计费请求 */
//    public DeductFailChargeRequest(String loanAccountId, DeductRecord deductRecord) {
//        super(loanAccountId, RequestSourceTypeEnum.RELATE_SCHEDULE);
//        
//        this.deductRecord = deductRecord;
//    }
//    
//    /**
//     * @return 返回 deductRecord
//     */
//    public DeductRecord getDeductRecord() {
//        return deductRecord;
//    }
//    
//    /**
//     * @return
//     */
//    @Override
//    public LATradingRecordTypeEnum getTradingRecordType() {
//        return LATradingRecordTypeEnum.CHARGE_DEDUCT_FAIL_FEE;
//    }
//    
//}
