/*
 * 描          述:  <描述>
 * 修  改   人:  Administrator
 * 修改时间:  2014年9月24日
 * <修改描述:>
 */
package com.tx.local.loanaccount.helper.overdueinterest;

import java.math.BigDecimal;
import java.util.Comparator;
import java.util.Date;
import java.util.Map;

import org.joda.time.LocalDate;

import com.tx.component.command.context.AbstractHelper;
import com.tx.core.exceptions.util.AssertUtils;
import com.tx.local.basicdata.model.FeeItemEnum;
import com.tx.local.loanaccount.model.LATradingRecord;
import com.tx.local.loanaccount.model.LoanAccount;
import com.tx.local.loanaccount.model.LoanAccountFeeItem;
import com.tx.local.loanaccount.model.OverdueInterestChargeRecord;

/**
 * 逾期裂隙记录帮助类<br/>
 * <功能详细描述>
 * 
 * @author  Administrator
 * @version  [版本号, 2014年9月24日]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public abstract class OverdueInterestRecordHelper extends AbstractHelper<OverdueInterestRecordHelper> {
    
    /**
     * 逾期利息记录<br/>
     */
    public static Comparator<OverdueInterestChargeRecord> overdueInterestRecordComparator = new Comparator<OverdueInterestChargeRecord>() {
        /**
         * @param o1
         * @param o2
         * @return
         */
        @Override
        public int compare(OverdueInterestChargeRecord o1, OverdueInterestChargeRecord o2) {
            if (o1.getRecordDate().compareTo(o2.getRecordDate()) == 0) {
                return o1.getCreateDate().compareTo(o2.getCreateDate());
            } else {
                //优先利用RepayDate比较，除非RepayDate一致才使用CreateDate比较
                return o1.getRecordDate().compareTo(o2.getRecordDate());
            }
        }
    };
    
    //    /**
    //     * 
    //     * 构建逾期利息记录
    //     *
    //     * @param repayChannelType 还款渠道类型
    //     * @param loanAccount 贷款账户
    //     * @param feeCfgItemMap 费用项和费用项配置映射
    //     * @param period2PaymentScheduleMap 期数和还款计划映射
    //     * @param repayDate 还款日期
    //     * @param tradingRecord 交易记录
    //     * 
    //     * @return List<OverdueInterestRecord> 逾期利息计收记录
    //     * @exception throws [异常类型] [异常说明]
    //     * @see [类、类#方法、类#成员]
    //     * @version [版本号, 2015年12月31日]
    //     * @author rain
    //     */
    //    public abstract List<OverdueInterestChargeRecord> buildOverdueInterestRecords(RepayChannelTypeEnum repayChannelType,
    //            LoanAccount loanAccount, Map<FeeItemEnum, LoanAccountFeeItem> feeCfgItemMap,
    //            Map<String, PaymentSchedule> period2PaymentScheduleMap, Date repayDate, LATradingRecord tradingRecord);
    
    /**
      * 构建逾期利息记录<br/>
      * <功能详细描述>
      * @param loanAccount
      * @param feeItem
      * @param overdueInterestAmount
      * @param overdueDays
      * @param overdueInterestRate
      * @param processDate
      * @param repayDate
      * @param tradingRecordId
      * @return [参数说明]
      * 
      * @return OverdueInterestRecord [返回类型说明]
      * @exception throws [异常类型] [异常说明]
      * @see [类、类#方法、类#成员]
     */
    protected OverdueInterestChargeRecord buildOverdueInterestRecord(LATradingRecord tradingRecord,
            LoanAccount loanAccount, Map<FeeItemEnum, LoanAccountFeeItem> feeItemMap, FeeItemEnum feeItem,
            BigDecimal overdueInterestRate, int dayCount, BigDecimal amount, LocalDate recordDate) {
        AssertUtils.notNull(loanAccount, "loanAccount is null.");
        
        
        OverdueInterestChargeRecord oir = new OverdueInterestChargeRecord();
        oir.setLoanAccountId(loanAccount.getId());
        oir.setTradingRecordId(tradingRecord.getId());
        oir.setCurrentPeriod(loanAccount.getCurrentPeriod());
        oir.setPrincipalBalance(loanAccount.getPrincipalBalance());
        oir.setOverdueAmount(loanAccount.getOverdueAmount());
        oir.setOverdueSum(loanAccount.getOverdueSum());
        
        oir.setOverdueInterestRate(overdueInterestRate);
        oir.setDayCount(dayCount);
        oir.setFeeItem(feeItem);
        oir.setRecordDate(recordDate.toDate());
        //TODO: recordDatePeriod oir.setRecordDate(recordDate.toDate());
        
        oir.setRevoked(false);
        oir.setRevokeDate(null);
        oir.setRevokeTradingRecordId(null);
        
        Date now = new Date();
        oir.setCreateDate(now);
        oir.setLastUpdateDate(now);
        oir.setRemark(null);
        
        return oir;
    }
}
