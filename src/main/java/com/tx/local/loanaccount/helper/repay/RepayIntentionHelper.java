/*
 * 描          述:  <描述>
 * 修  改   人:  Administrator
 * 修改时间:  2015年2月7日
 * <修改描述:>
 */
package com.tx.local.loanaccount.helper.repay;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.thoughtworks.xstream.XStream;
import com.tx.component.command.context.AbstractHelper;
import com.tx.core.exceptions.util.AssertUtils;
import com.tx.core.util.XstreamUtils;
import com.tx.local.basicdata.model.FeeItemEnum;
import com.tx.local.loanaccount.model.LATradingRecord;
import com.tx.local.loanaccount.model.LoanAccount;
import com.tx.local.loanaccount.model.PaymentRecordEntry;
import com.tx.local.loanaccount.model.PaymentSchedule;
import com.tx.local.loanaccount.model.PaymentScheduleEntry;
import com.tx.local.loanaccount.model.RepayIntention;
import com.tx.local.loanaccount.model.SettleIntention;

/**
 * 还款意愿辅助类<br/>
 * <功能详细描述>
 * 
 * @author  Administrator
 * @version  [版本号, 2015年2月7日]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public abstract class RepayIntentionHelper extends AbstractHelper<RepayIntentionHelper> {
    
    /** 还款意愿转换器 */
    private static XStream repayIntentionXstream = XstreamUtils.getXstream(RepayIntention.class, true, false);
    
    /**
     * 将还款意愿转换为字符串进行表示<br/>
     * <功能详细描述>
     * @param repayIntention
     * @return [参数说明]
     * 
     * @return String [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    public static String toString(RepayIntention repayIntention) {
        String repayIntentionString = repayIntentionXstream.toXML(repayIntention);
        return repayIntentionString;
    }
    
    /**
     * 将还款意愿转换为字符串进行表示<br/>
     * <功能详细描述>
     * @param settleIntention
     * @return [参数说明]
     * 
     * @return String [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    public static String toString(SettleIntention settleIntention) {
        String settleIntentionString = repayIntentionXstream.toXML(settleIntention);
        return settleIntentionString;
    }
    
    /**
     * 将还款意愿字符串转换为还款意愿实体<br/>
     * <功能详细描述>
     * @param repayIntentionStr
     * @return [参数说明]
     * 
     * @return RepayIntention [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
    */
    public static RepayIntention fromString(String repayIntentionString) {
        RepayIntention RepayIntention = (RepayIntention) repayIntentionXstream.fromXML(repayIntentionString);
        return RepayIntention;
    }
    
    /**
      * 构建超额还款的还款意愿<br/>
      * <功能详细描述>
      * @param overRepayAmount
      * @param sourceRepayIntention
      * @return [参数说明]
      * 
      * @return RepayIntention [返回类型说明]
      * @exception throws [异常类型] [异常说明]
      * @see [类、类#方法、类#成员]
     */
    public static RepayIntention buildRepayIntentionOfOverRepay(BigDecimal overRepayAmount,
            RepayIntention sourceRepayIntention) {
        AssertUtils.notNull(overRepayAmount, "overRepayAmount is null.");
        AssertUtils.isTrue(overRepayAmount.compareTo(BigDecimal.ZERO) > 0, "overRepayAmount should > 0.");
        AssertUtils.notNull(sourceRepayIntention, "sourceRepayIntention is null.");
        
        RepayIntention ri = new RepayIntention();
        ri.setLoanAccountId(sourceRepayIntention.getLoanAccountId());
        ri.setBankAccountId(sourceRepayIntention.getBankAccountId());
        ri.setAmount(overRepayAmount);
        
        ri.setOutsourceAssignRecordId(sourceRepayIntention.getOutsourceAssignRecordId());
        ri.setGroupType(sourceRepayIntention.getGroupType());
        ri.setRepayDate(sourceRepayIntention.getRepayDate());
        ri.setType(sourceRepayIntention.getType());
        return ri;
    }
    
    /**
     * 构建还款意愿:服务于夜间事务,逾期30天自动试扣款
     *
     * @param loanAccount 账户
     * @param amount 扣款金额
     *            
     * @return RepayIntention [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     * @version [版本号, 2016年3月17日]
     * @author rain
     */
    public abstract RepayIntention buildRepayIntentionByTryOverdueAuto(LoanAccount loanAccount, BigDecimal amount);
    
    /**
      * 构建还款意愿：服务于调账逻辑
      *    以保证最终交易的，平息还款与原交易严格一致<br/>
      * <功能详细描述>
      * @param tradingRecord
      * @param paymentRecordEntryList
      * @return [参数说明]
      * 
      * @return RepayIntention [返回类型说明]
      * @exception throws [异常类型] [异常说明]
      * @see [类、类#方法、类#成员]
     */
    public abstract RepayIntention buildRepayIntentionBySourceTradingRecordAndPaymentRecordEntryList(
            LATradingRecord tradingRecord, List<PaymentRecordEntry> paymentRecordEntryList);
    
    /**
      * 构建还款意愿：服务于重做撤销交易
      *    paymentRecordEntryList 之所以传入主要是考虑到历史数据的处理逻辑
      * <功能详细描述>
      * @param tradingRecord
      * @param paymentRecordEntryList
      * @return [参数说明]
      * 
      * @return RepayIntention [返回类型说明]
      * @exception throws [异常类型] [异常说明]
      * @see [类、类#方法、类#成员]
     */
    public abstract RepayIntention buildRepayIntentionBySourceTradingRecord(LATradingRecord tradingRecord);
    
    /**
      * 构建还款意愿：服务于扣款部分成功的逻辑<br/>
      * <功能详细描述>
      * @param amount
      * @param tradingRecord
      * @param paymentRecordEntryList
      * @return [参数说明]
      * 
      * @return RepayIntention [返回类型说明]
      * @exception throws [异常类型] [异常说明]
      * @see [类、类#方法、类#成员]
     */
    public abstract RepayIntention buildRepayIntentionBySourceTradingRecord(BigDecimal amount,
            LATradingRecord tradingRecord);
    
    /**
     * 根据还款计划项，及对应的还款计划分项，构建还款意愿<br/>
     * <功能详细描述>
     * @param loanAccount
     * @param repayDate
     * @param paymentSchedule
     * @param paymentScheduleEntryList
     * @return [参数说明]
     * 
     * @return RepayIntention [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
    */
    public abstract RepayIntention buildRepayIntentionByPaymentSchedule(LoanAccount loanAccount, Date repayDate,
            PaymentSchedule paymentSchedule, List<PaymentScheduleEntry> paymentScheduleEntryList);
    
    /**
      * 将还款意愿拆分为多个<br/>
      * <功能详细描述>
      * @param repayIntention
      * @return [参数说明]
      * 
      * @return List<RepayIntention> [返回类型说明]
      * @exception throws [异常类型] [异常说明]
      * @see [类、类#方法、类#成员]
     */
    public List<RepayIntention> splitRepayIntention(LoanAccount loanAccount, RepayIntention repayIntention,
            Map<String, Map<FeeItemEnum, BigDecimal>> calmAssignMap) {
        //throw new UnsupportedProcessException("错误的调用操作.");
        return null;
    }
}
