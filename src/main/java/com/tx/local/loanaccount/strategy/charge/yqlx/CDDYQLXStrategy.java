/*
 * 描          述:  <描述>
 * 修  改   人:  Bobby
 * 修改时间:  2018年2月27日
 * <修改描述:>
 */
package com.tx.local.loanaccount.strategy.charge.yqlx;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import com.tx.core.exceptions.util.AssertUtils;
import com.tx.core.util.DateUtils;
import com.tx.local.basicdata.model.FeeItemCfg;
import com.tx.local.basicdata.model.FeeItemEnum;
import com.tx.local.loanaccount.LoanAccountConstants;
import com.tx.local.loanaccount.handle.PaymentScheduleHandler;
import com.tx.local.loanaccount.model.LATradingRecord;
import com.tx.local.loanaccount.model.LoanAccount;
import com.tx.local.loanaccount.model.LoanAccountFeeItem;
import com.tx.local.loanaccount.model.OverdueInterestChargeRecord;
import com.tx.local.loanaccount.model.PaymentSchedule;
import com.tx.local.loanaccount.model.ScheduleTypeEnum;
import com.tx.local.loanaccount.service.OverdueInterestChargeRecordService;
import com.tx.local.loanaccount.strategy.charge.YQLXChargeStrategy;

/**
 * <仓单贷逾期利息计算>
 * <功能详细描述>
 * 
 * @author  Bobby
 * @version  [版本号, 2018年2月27日]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
@Component("cddYQLXStrategy")
public class CDDYQLXStrategy implements YQLXChargeStrategy {
    
    /** 逾期利息计费记录业务层 */
    @Resource(name = "overdueInterestChargeRecordService")
    private OverdueInterestChargeRecordService oicrService;
    
    /**
     * @param loanAccount
     * @param feeItemMap
     * @param feeItemCfgMap
     * @param handler
     * @param tradingRecord
     * @param recordDate
     * @return
     */
    @Override
    public List<OverdueInterestChargeRecord> buildYQLXRecords(
            LoanAccount loanAccount,
            Map<FeeItemEnum, LoanAccountFeeItem> feeItemMap,
            Map<FeeItemEnum, FeeItemCfg> feeItemCfgMap,
            PaymentScheduleHandler handler, LATradingRecord tradingRecord,
            Date recordDate) {
        AssertUtils.notNull(loanAccount, "loanAccount is null.");
        AssertUtils.notEmpty(loanAccount.getId(), "loanAccount.id is empty.");
        AssertUtils.notNull(handler, "handler is null.");
        AssertUtils.notNull(recordDate, "recordDate is null.");
        List<OverdueInterestChargeRecord> resList = new ArrayList<>();
        String loanAccountId = loanAccount.getId();
        Date overdueDate = handler.getOverdueDate();
        OverdueInterestChargeRecord lastOICR = oicrService
                .findLastByLoanAccountId(loanAccountId, FeeItemEnum.DK_YQLX);
        Date startDate = DateUtils.max(overdueDate,
                lastOICR == null ? null : lastOICR.getRecordDate());
        BigDecimal overdueAmount = handler.getOverdueAmount(recordDate,
                ScheduleTypeEnum.MAIN);
        BigDecimal principalBalance = handler
                .getPrincipalBalance(ScheduleTypeEnum.MAIN);
        if (overdueDate == null || overdueAmount.compareTo(BigDecimal.ZERO) <= 0
                || DateUtils.compareByDay(recordDate, startDate) <= 0) {
            return resList;
        }
        Map<String, PaymentSchedule> period2PaymentScheduleMap = handler
                .getPaymentScheduleMap().get(ScheduleTypeEnum.MAIN);
        String nextPeriod = "1";
        while (!StringUtils.isBlank(nextPeriod)) {
            PaymentSchedule psTemp = period2PaymentScheduleMap.get(nextPeriod);
            if (DateUtils.compareByDay(psTemp.getRepaymentDate(),
                    recordDate) >= 0) {
                break;
            }
            BigDecimal overdueSumTemp = psTemp.getReceivableSum()
                    .add(psTemp.getExemptSum())
                    .subtract(psTemp.getActualReceivedSum());
            if (overdueSumTemp.compareTo(BigDecimal.ZERO) > 0) {
                int dayCount = DateUtils.calculateNumberOfDaysBetween(
                        recordDate, psTemp.getRepaymentDate());
                AssertUtils.isTrue(dayCount > 0,
                        "dayCount:{} should > 0.",
                        new Object[] { dayCount });
                OverdueInterestChargeRecord oicr = new OverdueInterestChargeRecord();
                Date now = new Date();
                oicr.setLastUpdateDate(now);
                oicr.setCreateDate(now);
                oicr.setPrincipalBalance(principalBalance);
                oicr.setOverdueAmount(overdueSumTemp);
                oicr.setOverdueSum(overdueSumTemp);
                oicr.setLoanAccountId(loanAccountId);
                oicr.setCurrentPeriod(psTemp.getPeriod());
                LoanAccountFeeItem lafi = feeItemMap.get(FeeItemEnum.DK_YQLX);
                BigDecimal overdueInterestRate = lafi != null
                        ? new BigDecimal(lafi.getValue())
                        : (new BigDecimal(
                                feeItemMap.get(FeeItemEnum.DK_LX).getValue())
                                        .divide(new BigDecimal("365"),
                                                8,
                                                RoundingMode.UP));
                oicr.setPeriod(LoanAccountConstants.PERIOD_NA);
                oicr.setFeeItem(FeeItemEnum.DK_YQLX);
                oicr.setOverdueInterestRate(overdueInterestRate);
                oicr.setOverdueDate(psTemp.getRepaymentDate());
                oicr.setStartDate(startDate);
                oicr.setRecordDate(recordDate);
                oicr.setRecordDatePeriod(psTemp.getPeriod());
                oicr.setDayCount(dayCount);
                oicr.setAmount(overdueSumTemp.multiply(overdueInterestRate)
                        .multiply(new BigDecimal(dayCount))
                        .setScale(0, RoundingMode.UP));
                oicr.setRemark(null);
                oicr.setRevoked(false);
                oicr.setRevokeDate(null);
                oicr.setRevokeTradingRecordId(null);
                oicr.setTradingRecordId(
                        tradingRecord == null ? null : tradingRecord.getId());
                resList.add(oicr);
            }
            nextPeriod = psTemp.getNextPeriod();
        }
        return resList;
    }
    
}
