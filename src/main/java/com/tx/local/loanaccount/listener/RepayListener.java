///*
// * 描          述:  <描述>
// * 修  改   人:  bobby
// * 修改时间:  2017年10月12日
// * <修改描述:>
// */
//package com.tx.local.loanaccount.listener;
//
//import javax.annotation.Resource;
//
//import org.springframework.stereotype.Component;
//
//import com.tx.component.event.annotation.EventListener;
//import com.tx.component.event.annotation.EventListeners;
//import com.tx.local.clientaccount.facade.CreditAccountFacade;
//import com.tx.local.loanaccount.event.trading.RepayEvent;
//
///**
// * <功能简述>
// * <功能详细描述>
// * 
// * @author  bobby
// * @version  [版本号, 2017年10月12日]
// * @see  [相关类/方法]
// * @since  [产品/模块版本]
// */
//@Component("repayListener")
//@EventListeners
//public class RepayListener {
//    
//    @Resource(name = "creditAccountFacade")
//    private CreditAccountFacade creditAccountFacade;
//    
//    /**
//    * 还款事件监听器<br/> 
//    * <功能详细描述>
//    * @param repayEvent [参数说明]
//    * 
//    * @return void [返回类型说明]
//    * @exception throws [异常类型] [异常说明]
//    * @see [类、类#方法、类#成员]
//    */
//    @EventListener(eventType = RepayEvent.class)
//    public void onRepayEvent(RepayEvent repayEvent) {
//        creditAccountFacade.repay(repayEvent.getTradingRecord().getId(),
//                repayEvent.getLoanAccount().getClientIdCardNumber(),
//                repayEvent.getTradingRecord().getSummary(),
//                repayEvent.getRepayPrincipalAmount());
//        System.out.println("还款本金" + repayEvent.getRepayPrincipalAmount());
//    }
//}
