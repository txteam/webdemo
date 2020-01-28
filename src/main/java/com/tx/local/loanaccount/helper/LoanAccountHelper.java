/*
 * 描          述:  <描述>
 * 修  改   人:  Administrator
 * 修改时间:  2015年2月4日
 * <修改描述:>
 */
package com.tx.local.loanaccount.helper;

import java.util.Comparator;

import com.tx.core.TxConstants;
import com.tx.local.loanaccount.model.CollectionStatusEnum;
import com.tx.local.loanaccount.model.LoanAccount;

/**
 * 贷款账户辅助类<br/>
 * <功能详细描述>
 * 
 * @author  Administrator
 * @version  [版本号, 2015年2月4日]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public abstract class LoanAccountHelper {
    
    /** 贷款账户依赖生效时间的排序比较器 */
    public static Comparator<LoanAccount> EFFICTIVE_DATE_COMPARATOR = new Comparator<LoanAccount>() {
        @Override
        public int compare(LoanAccount o1, LoanAccount o2) {
            if (o1.getEffectiveDate().compareTo(o2.getEffectiveDate()) == 0) {
                return o1.getCreateDate().compareTo(o2.getCreateDate());
            } else {
                return o1.getEffectiveDate().compareTo(o2.getEffectiveDate());
            }
        }
    };
    
    /**
      * 获取贷款账户状态<br/>
      * <功能详细描述>
      * @param loanAccount
      * @return [参数说明]
      * 
      * @return String [返回类型说明]
      * @exception throws [异常类型] [异常说明]
      * @see [类、类#方法、类#成员]
     */
    public static String getLoanAccountStatus(LoanAccount loanAccount) {
        StringBuffer accountCode = new StringBuffer(
                TxConstants.INITIAL_STR_LENGTH);
        accountCode.append(loanAccount.getAccountStatus());
        if (!loanAccount.getCollectionStatus().equals(CollectionStatusEnum.NA)) {
            accountCode.append("/").append(loanAccount.getCollectionStatus());
        }
        if (loanAccount.isDied()) {
            accountCode.append("/").append("DA");
        }
        if (loanAccount.isLegalProcedure()) {
            accountCode.append("/").append("LP");
        }
        if (loanAccount.isClosed()) {
            accountCode.append("/").append("XX");
        }
        return accountCode.toString();
    }
}
