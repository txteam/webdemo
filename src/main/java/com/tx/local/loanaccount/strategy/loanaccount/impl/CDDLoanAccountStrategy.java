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
  * 仓单贷贷款策略实现<br/>
  * <功能详细描述>
  * 
  * @author  Administrator
  * @version  [版本号, 2017年5月10日]
  * @see  [相关类/方法]
  * @since  [产品/模块版本]
  */
@Component("cddLoanAccountStrategy")
public class CDDLoanAccountStrategy extends AbstractLoanAccountStrategy {
    
    /**
     * @return
     */
    @Override
    public LoanAccountTypeEnum supportLoanAccountType() {
        return LoanAccountTypeEnum.CDD;
    }
    
    /** <默认构造函数> */
    public CDDLoanAccountStrategy() {
        super();
    }
    
    /**
     * @return
     */
    @Override
    protected FeeItemStrategy getFeeItemStrategy() {
        FeeItemStrategy feeItemStrategy = StrategyContext.getContext()
                .getStrategy(FeeItemStrategy.class, "cddFeeItemStrategy");
        return feeItemStrategy;
    }
    
    /**
     * @return
     */
    @Override
    protected SettleStrategy getSettleStrategy() {
        SettleStrategy settleStrategy = StrategyContext.getContext()
                .getStrategy(SettleStrategy.class, "cddSettleStrategy");
        return settleStrategy;
    }
    
    @Override
    protected RepayStrategy getRepayStrategy() {
        RepayStrategy repayStrategy = StrategyContext.getContext()
                .getStrategy(RepayStrategy.class, "cddRepayStrategy");
        return repayStrategy;
    }
    
    /**
    * @return
    */
    @Override
    protected PaymentScheduleEntryBuilder getPaymentScheduleEntryBuilder(
            RepayWayEnum repayWay) {
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
        PaymentScheduleEntryBuilder pseStrategy = StrategyContext.getContext()
                .getStrategy(PaymentScheduleEntryBuilder.class, strategy);
        return pseStrategy;
    }
    
}
