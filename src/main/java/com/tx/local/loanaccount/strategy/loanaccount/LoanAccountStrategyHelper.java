/*
 * 描          述:  <描述>
 * 修  改   人:  Administrator
 * 修改时间:  2017年5月31日
 * <修改描述:>
 */
package com.tx.local.loanaccount.strategy.loanaccount;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.core.OrderComparator;
import org.springframework.stereotype.Component;

import com.tx.core.exceptions.util.AssertUtils;
import com.tx.local.loanaccount.model.LoanAccount;
import com.tx.local.loanaccount.model.LoanAccountTypeEnum;

/**
  * 贷款账户策略辅助器<br/>
  * <功能详细描述>
  * 
  * @author  Administrator
  * @version  [版本号, 2017年5月31日]
  * @see  [相关类/方法]
  * @since  [产品/模块版本]
  */
@Component("loanAccountStrategyHelper")
public class LoanAccountStrategyHelper implements ApplicationContextAware, InitializingBean {
    
    /** spring容器句柄 */
    private static ApplicationContext applicationContext;
    
    /** 贷款账户策略集合 */
    private static List<LoanAccountStrategy> strategyList;
    
    /**
     * @param applicationContext
     * @throws BeansException
     */
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        LoanAccountStrategyHelper.applicationContext = applicationContext;
    }
    
    /**
     * @throws Exception
     */
    @Override
    public void afterPropertiesSet() throws Exception {
        List<LoanAccountStrategy> strategyList = new ArrayList<>(
                LoanAccountStrategyHelper.applicationContext.getBeansOfType(LoanAccountStrategy.class).values());
        
        LoanAccountStrategyHelper.strategyList = strategyList;
        Collections.sort(LoanAccountStrategyHelper.strategyList, OrderComparator.INSTANCE);
    }
    
    /**
     * 获取辅助类实例<br/>
     * <功能详细描述>
     * @param loanAccount
     * @return [参数说明]
     * 
     * @return H [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
    */
    public static LoanAccountStrategy getStrategy(LoanAccount loanAccount) {
        AssertUtils.notNull(loanAccount, "loanAccount is null.");
        
        LoanAccountStrategy strategy = null;
        for (LoanAccountStrategy strategyTemp : LoanAccountStrategyHelper.strategyList) {
            if (strategyTemp.support(loanAccount)) {
                strategy = strategyTemp;
                break;
            }
        }
        AssertUtils.notNull(strategy, "strategy is not exist.loanAccountId:{}", new Object[] { loanAccount.getId() });
        
        return strategy;
    }
    
    /**
     * 获取辅助类实例<br/>
     * <功能详细描述>
     * @param loanAccountType
     * @return [参数说明]
     * 
     * @return H [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
    */
    public static LoanAccountStrategy getStrategy(LoanAccountTypeEnum loanAccountType) {
        AssertUtils.notNull(loanAccountType, "loanAccountType is null.");
        
        LoanAccountStrategy strategy = null;
        for (LoanAccountStrategy strategyTemp : LoanAccountStrategyHelper.strategyList) {
            if (loanAccountType.equals(strategyTemp.supportLoanAccountType())) {
                strategy = strategyTemp;
                break;
            }
        }
        AssertUtils.notNull(strategy, "strategy is not exist.loanAccountType:{}", new Object[] { loanAccountType });
        
        return strategy;
    }
}
