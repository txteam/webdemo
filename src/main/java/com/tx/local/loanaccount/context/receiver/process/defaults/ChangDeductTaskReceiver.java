//package com.tx.local.loanaccount.context.receiver.process.defaults;
//
//import java.util.Date;
//import java.util.UUID;
//
//import javax.annotation.Resource;
//
//import org.springframework.stereotype.Component;
//
//import com.tx.component.command.context.CommandResponse;
//import com.tx.component.mainframe.context.WebContextUtils;
//import com.tx.core.exceptions.util.AssertUtils;
//import com.tx.local.loanaccount.context.receiver.process.AbstractProcessReceiver;
//import com.tx.local.loanaccount.context.request.process.ChangDeductTaskRequest;
//import com.tx.local.loanaccount.helper.deducttask.DeductTaskHelper;
//import com.tx.local.loanaccount.model.DeductTask;
//import com.tx.local.loanaccount.model.DeductTaskStatusEnum;
//import com.tx.local.loanaccount.model.LATradingRecord;
//import com.tx.local.loanaccount.model.LATradingRecordTypeEnum;
//import com.tx.local.loanaccount.model.LoanAccount;
//import com.tx.local.loanaccount.service.DeductTaskService;
//import com.tx.local.loanaccount.service.LATradingRecordService;
//import com.tx.local.paymentchannel.model.PaymentOrder;
//
///**
// * 
//  * 扣款任务改变，扣款成功，扣款失败<br/>
//  * 支持暂缓还款和自动转账还款   
//  * <功能详细描述>
//  * 
//  * @author  huangdonggang
//  * @version  [版本号, 2017年12月29日]
//  * @see  [相关类/方法]
//  * @since  [产品/模块版本]
// */
//@Component("changDeductTaskReceiver")
//public class ChangDeductTaskReceiver extends AbstractProcessReceiver<ChangDeductTaskRequest>{
//    
//    /**交易记录*/
//    @Resource(name = "laTradingRecordService")
//    private LATradingRecordService laTradingRecordService;
//    
//    /**扣款任务*/
//    @Resource(name = "deductTaskService")
//    private DeductTaskService deductTaskService;
//    
//    
//    @Override
//    public void validateRequest(ChangDeductTaskRequest request,
//            CommandResponse response, LoanAccount loanAccount) {
//        
//        //指定更改状态的扣款任务
//        DeductTask deductTask=request.getDeductTask();//扣款任务
//        
//        //解释回执报文，回传的扣款结果
//        PaymentOrder paymentOrder=request.getPaymentOrder();//支付订单
//        if(null==deductTask && null!=paymentOrder){
//            deductTask=new DeductTask();
//            deductTask.setId(paymentOrder.getDeductId());
//            deductTask.setStatus(DeductTaskHelper.paymentOrderStatus2DeductTaskStatus(paymentOrder.getPaymentOrderStatus()));
//        }
//        
//        AssertUtils.notNull(deductTask, "deductTask is null.");
//        AssertUtils.notEmpty(deductTask.getId(), "deductTask.id is empty.");
//        
//        DeductTask deductTaskSelect=  deductTaskService.findById(deductTask.getId());
//        AssertUtils.notNull(deductTask, "扣款任务不存在");
//        
//        deductTask.setTradingRecordId(deductTaskSelect.getTradingRecordId());
//        
//        AssertUtils.notNull(deductTask.getTradingRecordId(), "tradingRecordId is null.");
//        
//        LATradingRecord laTradingRecord = this.laTradingRecordService.findTradingRecordById(deductTask.getTradingRecordId());
//        AssertUtils.notNull(laTradingRecord, "扣款任务对应的交易记录不存在");
//        
//        AssertUtils.isTrue(deductTaskSelect.getStatus().equals(DeductTaskStatusEnum.WAIT_RECEIPT), "待回执状态下才能更改扣款任务");
//        
//        AssertUtils.isTrue(DeductTaskStatusEnum.SUCCESS.equals(deductTask.getStatus()) || 
//                DeductTaskStatusEnum.FAIL.equals(deductTask.getStatus()) || 
//                DeductTaskStatusEnum.WAIT_CONFIRM.equals(deductTask.getStatus())
//                , "扣款任务的状态类型应为成功、失败、待确认 三种中的一个");
//        AssertUtils.isTrue( LATradingRecordTypeEnum.SUSPEND  .equals(laTradingRecord.getType())||
//                LATradingRecordTypeEnum.TRANSFER_REPAY  .equals(laTradingRecord.getType())
//                , "扣款任务对应的交易记录类型应为暂缓交易或自动转账还款");
//        
//        request.setLaTradingRecord(laTradingRecord);//记录扣款任务对应的交易记录
//        request.setDeductTask(deductTask);//更新扣款任务
//    }
//    
//    
//    @Override
//    public void handle(ChangDeductTaskRequest request,CommandResponse response, LoanAccount loanAccount) {
//        DeductTask deductTask=request.getDeductTask();
//        //交易记录
//        LATradingRecord laTradingRecord = request.getLaTradingRecord();
//       
//        Date now = new Date();
//        
//        //扣款成功，并且还款类型为暂缓还款, 写入一笔新的成功的自动转账还款类型的交易记录
//        if(DeductTaskStatusEnum.SUCCESS.equals(deductTask.getStatus()) && LATradingRecordTypeEnum.SUSPEND  .equals(laTradingRecord.getType())){
//            
//            //标记扣款状态为成功
//            deductTask.setStatus(DeductTaskStatusEnum.SUCCESS);
//            //标记已到账
//            laTradingRecord.setReceived(true);
//            //到账日期
//            laTradingRecord.setReceiveDate(now);
//            
//            //写入一笔新的自动转账还款的交易记录
//            LATradingRecord laTradingRecordNew =laTradingRecord;
//            laTradingRecordNew.setId(null);
//            laTradingRecordNew.setType(LATradingRecordTypeEnum.TRANSFER_REPAY);
//            laTradingRecordNew.setRequestId(UUID.randomUUID().toString().replaceAll("\\-", ""));
//            laTradingRecordNew.setSummary(LATradingRecordTypeEnum.TRANSFER_REPAY.getSummary());
//            laTradingRecordService.insert(laTradingRecordNew);
//        }
//        
//        //扣款失败，撤销暂缓交易或自动转账交易
//        if(DeductTaskStatusEnum.FAIL.equals(deductTask.getStatus())){
//            
//            //修改扣款状态为失败
//            deductTask.setStatus(DeductTaskStatusEnum.FAIL);
//            //是否撤销
//            laTradingRecord.setRevoked(true);
//            //撤销原因
//            laTradingRecord.setRevokeResean("撤销交易");
//            //操作员
//            laTradingRecord.setRevokeOperatorId(WebContextUtils.getCurrentOperator().getId());
//            //撤销实际时间
//            laTradingRecord.setRevokeDate(now);
//            
//            laTradingRecord.setLastUpdateDate(now);
//            //修改交易记录 
//            boolean updateFlag=deductTaskService.updateLaTradingRecord(laTradingRecord);
//            
//            response.setAttribute("updateFlag", updateFlag);
//        }
//        
//        //部分成功
//        if(DeductTaskStatusEnum.PARTSUCCESS.equals(deductTask.getStatus())){
//            PaymentOrder paymentOrder=request.getPaymentOrder();
//            
//            //修改扣款状态为部分成功
//            deductTask.setStatus(DeductTaskStatusEnum.PARTSUCCESS);
//            
//            //是否撤销
//            laTradingRecord.setRevoked(true);
//            //撤销原因
//            laTradingRecord.setRevokeResean("部分成功，撤销交易");
//            //操作员
//            laTradingRecord.setRevokeOperatorId(WebContextUtils.getCurrentOperator().getId());
//            //撤销实际时间
//            laTradingRecord.setRevokeDate(now);
//            
//            laTradingRecord.setLastUpdateDate(now);
//            
//            //写入已扣款成功部分的一笔新的自动转账还款的交易记录
//            LATradingRecord laTradingRecordNew =laTradingRecord;
//            laTradingRecordNew.setId(null);
//            laTradingRecordNew.setSum(paymentOrder.getSuccessAmount());//成功的扣款金额
//            laTradingRecordNew.setType(LATradingRecordTypeEnum.TRANSFER_REPAY);
//            laTradingRecordNew.setRequestId(UUID.randomUUID().toString().replaceAll("\\-", ""));
//            laTradingRecordNew.setSummary(LATradingRecordTypeEnum.TRANSFER_REPAY.getSummary());
//            
//            //新增交易记录
//            laTradingRecordService.insert(laTradingRecordNew);
//            
//        }
//        
//        //修改交易记录 
//        deductTaskService.updateLaTradingRecord(laTradingRecord);
//        //最后修改时间
//        deductTask.setLastUpdateDate(now);
//        //修改扣款任务
//        deductTaskService.update(deductTask);
//        
//    }
//    
//    
//}
