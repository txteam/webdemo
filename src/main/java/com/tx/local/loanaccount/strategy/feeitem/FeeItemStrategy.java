/*
 * 描          述:  <描述>
 * 修  改   人:  Administrator
 * 修改时间:  2017年6月23日
 * <修改描述:>
 */
package com.tx.local.loanaccount.strategy.feeitem;

import java.util.Collection;
import java.util.Map;

import com.tx.component.strategy.context.Strategy;
import com.tx.local.basicdata.model.FeeItemCfg;
import com.tx.local.basicdata.model.FeeItemEnum;
import com.tx.local.loanaccount.model.ExemptIntention;
import com.tx.local.loanaccount.model.FeeItemGroup;
import com.tx.local.loanaccount.model.RepayIntention;

/**
  * 费用项策略<br/>
  * <功能详细描述>
  * 
  * @author  Administrator
  * @version  [版本号, 2017年6月23日]
  * @see  [相关类/方法]
  * @since  [产品/模块版本]
  */
public interface FeeItemStrategy extends Strategy {
    
    /**
     * 获取贷款账户费用项配置<br/>
     * <功能详细描述>
     * @return [参数说明]
     * 
     * @return Map<FeeItemEnum,FeeItemCfg> [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
    */
    public Map<FeeItemEnum, FeeItemCfg> getFeeItemCfgMap();
    
    /**
     * 根据还款意愿获取费用项集合<br/>
     * <功能详细描述>
     * @param repayIntention
     * @return [参数说明]
     * 
     * @return Collection<FeeItemEnum> [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    public Collection<FeeItemEnum> getFeeItemsByRepayIntention(
            RepayIntention repayIntention);
    
    /**
     * 获取费用项分组<br/>
     * <功能详细描述>
     * @return [参数说明]
     * 
     * @return Map<String,FeeItemEnum[]> [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    public Collection<FeeItemGroup> getFeeItemGroupsOfRepay();
    
    /**
     * 获取结清费用项分组<br/>
     * <功能详细描述>
     * @return [参数说明]
     * 
     * @return List<FeeItemGroup> [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    public Collection<FeeItemGroup> getFeeItemGroupsOfSettle();
    
    /**
     * 根据还款意愿获取费用项集合<br/>
     * <功能详细描述>
     * @param repayIntention
     * @return [参数说明]
     * 
     * @return Collection<FeeItemEnum> [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    public Collection<FeeItemEnum> getFeeItemsByExemptIntention(
            ExemptIntention exemptIntention);
    
    /**
     * 获取费用项分组<br/>
     * <功能详细描述>
     * @return [参数说明]
     * 
     * @return Map<String,FeeItemEnum[]> [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    public Collection<FeeItemGroup> getFeeItemGroupsOfRepayExempt();
    
    /**
     * 获取费用项分组<br/>
     * <功能详细描述>
     * @return [参数说明]
     * 
     * @return Map<String,FeeItemEnum[]> [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    public Collection<FeeItemGroup> getFeeItemGroupsOfSettleExempt();
}
