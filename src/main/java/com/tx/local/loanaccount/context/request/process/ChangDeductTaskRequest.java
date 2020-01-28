//package com.tx.local.loanaccount.context.request.process;
//
//import com.tx.local.loanaccount.model.DeductTask;
//import com.tx.local.loanaccount.model.LATradingRecord;
//import com.tx.local.loanaccount.model.RequestSourceTypeEnum;
//import com.tx.local.paymentchannel.model.PaymentOrder;
//
///**
// * 
//  * 扣款任务改变 ,扣款成功，扣款失败 , <br/>
//  * 支持暂缓还款和自动转账还款
//  * <功能详细描述>
//  * 
//  * @author  huangdonggang
//  * @version  [版本号, 2017年12月29日]
//  * @see  [相关类/方法]
//  * @since  [产品/模块版本]
// */
//public class ChangDeductTaskRequest extends AbstractProcessRequest {
//    
//    /**扣款任务*/
//    private DeductTask deductTask;
//    
//    /**交易记录*/
//    private LATradingRecord laTradingRecord;
//    
//    /**支付订单*/
//    private PaymentOrder paymentOrder;
//    
//    public ChangDeductTaskRequest(DeductTask deductTask) {
//        super(deductTask.getLoanAccountId(), RequestSourceTypeEnum.OPER_REQUEST);
//        this.deductTask = deductTask;
//    }
//    
//    public ChangDeductTaskRequest(PaymentOrder paymentOrders) {
//        super(paymentOrders.getLoanAccountId(),
//                RequestSourceTypeEnum.OPER_REQUEST);
//        this.paymentOrder = paymentOrder;
//    }
//    
//    /**
//     * @return 返回 deductTask
//     */
//    public DeductTask getDeductTask() {
//        return deductTask;
//    }
//    
//    /**
//     * @param 对deductTask进行赋值
//     */
//    public void setDeductTask(DeductTask deductTask) {
//        this.deductTask = deductTask;
//    }
//    
//    /**
//     * @return 返回 laTradingRecord
//     */
//    public LATradingRecord getLaTradingRecord() {
//        return laTradingRecord;
//    }
//    
//    /**
//     * @param 对laTradingRecord进行赋值
//     */
//    public void setLaTradingRecord(LATradingRecord laTradingRecord) {
//        this.laTradingRecord = laTradingRecord;
//    }
//    
//    /**
//     * @return 返回 paymentOrder
//     */
//    public PaymentOrder getPaymentOrder() {
//        return paymentOrder;
//    }
//    
//    /**
//     * @param 对paymentOrder进行赋值
//     */
//    public void setPaymentOrder(PaymentOrder paymentOrder) {
//        this.paymentOrder = paymentOrder;
//    }
//    
//}
