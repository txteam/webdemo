/*
 * 描          述:  <描述>
 * 修  改   人:  Administrator
 * 修改时间:  2017年5月10日
 * <修改描述:>
 */
package com.tx.local.loanaccount.strategy.loanaccount.impl;

import org.springframework.stereotype.Component;

import com.tx.component.strategy.context.StrategyContext;
import com.tx.local.basicdata.model.RepayWayEnum;
import com.tx.local.loanaccount.model.LoanAccountTypeEnum;
import com.tx.local.loanaccount.strategy.build.PaymentScheduleEntryBuilder;
import com.tx.local.loanaccount.strategy.feeitem.FeeItemStrategy;
import com.tx.local.loanaccount.strategy.loanaccount.AbstractLoanAccountStrategy;
import com.tx.local.loanaccount.strategy.repay.RepayStrategy;
import com.tx.local.loanaccount.strategy.settle.SettleStrategy;

/**
  * 订单贷款策略实现<br/>
  * <功能详细描述>
  * 
  * @author  Administrator
  * @version  [版本号, 2017年5月10日]
  * @see  [相关类/方法]
  * @since  [产品/模块版本]
  */
@Component("dddLoanAccountStrategy")
public class DDDLoanAccountStrategy extends AbstractLoanAccountStrategy {
    
    /**
     * @return
     */
    @Override
    public LoanAccountTypeEnum supportLoanAccountType() {
        return LoanAccountTypeEnum.DDD;
    }
    
    /** <默认构造函数> */
    public DDDLoanAccountStrategy() {
        super();
    }
    
    /**
     * @return
     */
    @Override
    protected FeeItemStrategy getFeeItemStrategy() {
        FeeItemStrategy feeItemStrategy = StrategyContext.getContext().getStrategy(FeeItemStrategy.class, "dddFeeItemStrategy");
        return feeItemStrategy;
    }
    
    @Override
    protected RepayStrategy getRepayStrategy() {
        RepayStrategy repayStrategy = StrategyContext.getContext().getStrategy(RepayStrategy.class, "dddRepayStrategy");
        return repayStrategy;
    }
    
    /**
     * @return
     */
    @Override
    protected SettleStrategy getSettleStrategy() {
        SettleStrategy settleStrategy = StrategyContext.getContext().getStrategy(SettleStrategy.class, "dddSettleStrategy");
        return settleStrategy;
    }
    
    /**
    * @return
    */
    @Override
    protected PaymentScheduleEntryBuilder getPaymentScheduleEntryBuilder(RepayWayEnum repayWay) {
        String strategy = "";
        switch (repayWay) {
            case DEBX:
                strategy = "pseBuilderDEBX";
                break;
            case DEBJ:
                strategy = "pseBuilderDEBJ";
                break;
            case AYFXDQHB:
                strategy = "pseBuilderAYFXDQHB";
                break;
            case DEBJ_ARJX:
                strategy = "pseBuilderDEBJ_ARJX";
                break;
            case DBDX:
                strategy = "pseBuilderDEDX";
                break;
            default:
                strategy = "pseBuilderDQHBFX";
                break;
        }
        PaymentScheduleEntryBuilder pseStrategy = StrategyContext.getContext().getStrategy(PaymentScheduleEntryBuilder.class,
                strategy);
        return pseStrategy;
    }
    
}
