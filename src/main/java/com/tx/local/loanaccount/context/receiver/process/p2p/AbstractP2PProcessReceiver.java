///*
// * 描          述:  <描述>
// * 修  改   人:  Administrator
// * 修改时间:  2014年11月26日
// * <修改描述:>
// */
//package com.tx.local.loanaccount.context.receiver.process.p2p;
//
//import com.tx.local.loanaccount.context.receiver.process.AbstractProcessReceiver;
//import com.tx.local.loanaccount.context.request.process.AbstractProcessRequest;
//import com.tx.local.loanaccount.model.LoanAccountTypeEnum;
//
///**
// * ProceessReciver的抽象类实现的父类<br/>
// * 
// * @author  Administrator
// * @version  [版本号, 2014年11月26日]
// * @see  [相关类/方法]
// * @since  [产品/模块版本]
// */
//abstract class AbstractP2PProcessReceiver<PR extends AbstractProcessRequest>
//        extends AbstractProcessReceiver<PR> {
//    
//    /**
//     * @return
//     */
//    @Override
//    public final LoanAccountTypeEnum[] getSupportLoanAccountTypes() {
//        return new LoanAccountTypeEnum[] { LoanAccountTypeEnum.P2P };
//    }
//}
