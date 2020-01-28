/*
 * 描          述:  <描述>
 * 修  改   人:  Administrator
 * 修改时间:  2014年6月11日
 * <修改描述:>
 */
package com.tx.local.loanaccount.helper.feecfgitem;

import java.util.Comparator;

import com.tx.local.basicdata.model.FeeItemCfg;

/**
 * 贷款账户费用配置辅助类<br/>
 * <功能详细描述>
 * 
 * @author  Administrator
 * @version  [版本号, 2014年6月11日]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public class FeeItemCfgHelper {
    
    /**
     * 费用设置的还款优先级比较器<br/>
     */
    public static final Comparator<FeeItemCfg> repayPriorityComparator = new Comparator<FeeItemCfg>() {
        @Override
        public int compare(FeeItemCfg o1, FeeItemCfg o2) {
            if (o1.getRepayPriority() == o2.getRepayPriority()) {
                return 0;
            } else if (o1.getRepayPriority() > o2.getRepayPriority()) {
                return 1;
            } else {
                return -1;
            }
        }
    };
    
    /**
     * 费用设置的停息后还款优先级比较器<br/>
     */
    public static final Comparator<FeeItemCfg> stopInterestRepayPriorityComparator = new Comparator<FeeItemCfg>() {
        @Override
        public int compare(FeeItemCfg o1, FeeItemCfg o2) {
            if (o1.getRepayPriority() == o2.getRepayPriority()) {
                return 0;
            } else if (o1.getRepayPriority() > o2.getRepayPriority()) {
                return 1;
            } else {
                return -1;
            }
        }
    };
}
