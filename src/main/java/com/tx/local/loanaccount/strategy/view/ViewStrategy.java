/*
 * 描          述:  <描述>
 * 修  改   人:  Administrator
 * 修改时间:  2017年6月28日
 * <修改描述:>
 */
package com.tx.local.loanaccount.strategy.view;

import com.tx.component.strategy.context.Strategy;
import com.tx.local.loanaccount.model.LoanAccountTypeEnum;

/**
 * 视图策略<br/>
 * <功能详细描述>
 * 
 * @author  Administrator
 * @version  [版本号, 2017年6月28日]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public interface ViewStrategy extends Strategy {
    
    /**
     * 根据传入的视图类型获取对应的页面名称<br/>
     * <功能详细描述>
     * @param viewType
     * @return [参数说明]
     * 
     * @return String [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
    */
    public String getPageName(String viewType, String viewName, LoanAccountTypeEnum loanAccountType);
}
