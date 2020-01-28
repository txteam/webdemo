///*
// * 描          述:  <描述>
// * 修  改   人:  Administrator
// * 修改时间:  2015年3月8日
// * <修改描述:>
// */
//package com.tx.local.loanaccount.helper.paymentschedule.impl;
//
//import java.math.BigDecimal;
//import java.util.Arrays;
//import java.util.Date;
//import java.util.List;
//import java.util.Map;
//
//import org.apache.commons.lang3.math.NumberUtils;
//import org.springframework.stereotype.Component;
//
//import com.tx.component.basicdata.model.FeeItemEnum;
//import com.tx.local.loanaccount.helper.paymentschedule.PaymentScheduleHelper;
//import com.tx.local.loanaccount.model.LoanAccount;
//import com.tx.local.loanaccount.model.LoanAccountFeeItem;
//import com.tx.local.loanaccount.model.LoanAccountTypeEnum;
//import com.tx.local.loanaccount.model.PaymentSchedule;
//import com.tx.local.loanaccount.model.PaymentScheduleEntry;
//
///**
// * 重庆还款计划辅助类<br/>
// * <功能详细描述>
// * 
// * @author  Administrator
// * @version  [版本号, 2015年3月8日]
// * @see  [相关类/方法]
// * @since  [产品/模块版本]
// */
//@Component("cqPaymentScheduleHelper")
//public class P2PPaymentScheduleHelper extends PaymentScheduleHelper {
//    
//    /**
//     * @param params
//     * @return
//     */
//    @Override
//    public boolean supports(Object... params) {
//        if(LoanAccountTypeEnum.P2P.equals(params[0])){
//            return true;
//        }
//        return false;
//    }
//    
//    /**
//     * 计算提前结清金额
//     * <功能详细描述>
//     * @param loanAccount
//     * @param feeCfgItemMap
//     * @param period2PaymentScheduleMap
//     * @param repayDate
//     * @return [参数说明]
//     * 
//     * @return BigDecimal [返回类型说明]
//     * @exception throws [异常类型] [异常说明]
//     * @see [类、类#方法、类#成员]
//    */
//    public BigDecimal caculateEarlySettleAmount(LoanAccount loanAccount,
//            Map<FeeItemEnum, LoanAccountFeeItem> feeCfgItemMap,
//            Map<String, PaymentSchedule> period2PaymentScheduleMap,
//            Date repayDate) {
//        String receivablePeriodOfRepayDate = getReceivablePeriodOfRepayDate(period2PaymentScheduleMap,
//                repayDate);
//        int receivablePeriodOfRepayDateInt = NumberUtils.toInt(receivablePeriodOfRepayDate);
//        
//        BigDecimal earlySettleAmount = BigDecimal.ZERO;
//        for (PaymentSchedule psTemp : period2PaymentScheduleMap.values()) {
//            for (PaymentScheduleEntry psEntryTemp : psTemp.getPaymentScheduleEntryList()) {
//                if (NumberUtils.isDigits(psEntryTemp.getPeriod())) {
//                    //所有带数字的期数
//                    if (feeCfgItemMap.get(psEntryTemp.getFeeItem())
//                            .isPrincipal()) {
//                        //平息    
//                        BigDecimal balanceReceivableAmountTemp = psEntryTemp.getReceivableAmount()
//                                .add(psEntryTemp.getExemptAmount())
//                                .subtract(psEntryTemp.getActualReceivedAmount());
//                        earlySettleAmount = earlySettleAmount.add(balanceReceivableAmountTemp);
//                    } else if (NumberUtils.toInt(psEntryTemp.getPeriod()) <= receivablePeriodOfRepayDateInt) {
//                        //平息    
//                        BigDecimal balanceReceivableAmountTemp = psEntryTemp.getReceivableAmount()
//                                .add(psEntryTemp.getExemptAmount())
//                                .subtract(psEntryTemp.getActualReceivedAmount());
//                        earlySettleAmount = earlySettleAmount.add(balanceReceivableAmountTemp);
//                    } else {
//                        //+0 //利息管理费不收
//                    }
//                } else {
//                    if (FeeItemEnum.TQHKWYJ.equals(psEntryTemp.getFeeItem())) {
//                        continue;
//                    }
//                    //平息    
//                    BigDecimal balanceReceivableAmountTemp = psEntryTemp.getReceivableAmount()
//                            .add(psEntryTemp.getExemptAmount())
//                            .subtract(psEntryTemp.getActualReceivedAmount());
//                    earlySettleAmount = earlySettleAmount.add(balanceReceivableAmountTemp);
//                }
//            }
//        }
//        
//        BigDecimal earlyRepayDamageValue = new BigDecimal(
//                feeCfgItemMap.get(FeeItemEnum.TQHKWYJ).getValue());
//        // 计算违约金
//        BigDecimal earlyRepayDamageAmount = loanAccount.getPrincipalBalance()
//                .multiply(earlyRepayDamageValue)
//                .setScale(0, BigDecimal.ROUND_UP);
//        BigDecimal receivableBalance = caculateReceivableBalanceExcludeEarlySettleDamageAmount(loanAccount,
//                feeCfgItemMap,
//                period2PaymentScheduleMap,
//                repayDate);
//        BigDecimal realEarlySettleAmount = BigDecimal.ZERO;
//        if (earlySettleAmount.add(earlyRepayDamageAmount)
//                .compareTo(receivableBalance) >= 0) {
//            realEarlySettleAmount = receivableBalance;
//        } else {
//            realEarlySettleAmount = earlySettleAmount.add(earlyRepayDamageAmount);
//        }
//        return realEarlySettleAmount;
//    }
//    
//    /**
//     * 计算指定还款日的提前结清违约金
//     * <功能详细描述>
//     * @param loanAccount
//     * @param feeCfgItemMap
//     * @param period2PaymentScheduleMap
//     * @param repayDate
//     * @return [参数说明]
//     * 
//     * @return BigDecimal [返回类型说明]
//     * @exception throws [异常类型] [异常说明]
//     * @see [类、类#方法、类#成员]
//    */
//    public BigDecimal calculateEarlySettleDamageAmount(LoanAccount loanAccount,
//            Map<FeeItemEnum, LoanAccountFeeItem> feeCfgItemMap,
//            Map<String, PaymentSchedule> period2PaymentScheduleMap,
//            Date repayDate) {
//        String receivablePeriodOfRepayDate = getReceivablePeriodOfRepayDate(period2PaymentScheduleMap,
//                repayDate);
//        int receivablePeriodOfRepayDateInt = NumberUtils.toInt(receivablePeriodOfRepayDate);
//        
//        BigDecimal earlySettleAmount = BigDecimal.ZERO;
//        for (PaymentSchedule psTemp : period2PaymentScheduleMap.values()) {
//            for (PaymentScheduleEntry psEntryTemp : psTemp.getPaymentScheduleEntryList()) {
//                if (NumberUtils.isDigits(psEntryTemp.getPeriod())) {
//                    //所有带数字的期数
//                    if (feeCfgItemMap.get(psEntryTemp.getFeeItem())
//                            .isPrincipal()) {
//                        //平息    
//                        BigDecimal balanceReceivableAmountTemp = psEntryTemp.getReceivableAmount()
//                                .add(psEntryTemp.getExemptAmount())
//                                .subtract(psEntryTemp.getActualReceivedAmount());
//                        earlySettleAmount = earlySettleAmount.add(balanceReceivableAmountTemp);
//                    } else if (NumberUtils.toInt(psEntryTemp.getPeriod()) <= receivablePeriodOfRepayDateInt) {
//                        //平息    
//                        BigDecimal balanceReceivableAmountTemp = psEntryTemp.getReceivableAmount()
//                                .add(psEntryTemp.getExemptAmount())
//                                .subtract(psEntryTemp.getActualReceivedAmount());
//                        earlySettleAmount = earlySettleAmount.add(balanceReceivableAmountTemp);
//                    } else {
//                        //+0 //利息管理费不收
//                    }
//                } else {
//                    if (FeeItemEnum.TQHKWYJ.equals(psEntryTemp.getFeeItem())) {
//                        continue;
//                    }
//                    //平息    
//                    BigDecimal balanceReceivableAmountTemp = psEntryTemp.getReceivableAmount()
//                            .add(psEntryTemp.getExemptAmount())
//                            .subtract(psEntryTemp.getActualReceivedAmount());
//                    earlySettleAmount = earlySettleAmount.add(balanceReceivableAmountTemp);
//                }
//            }
//        }
//        
//        BigDecimal earlyRepayDamageValue = new BigDecimal(
//                feeCfgItemMap.get(FeeItemEnum.TQHKWYJ).getValue());
//        // 计算违约金
//        BigDecimal earlyRepayDamageAmount = loanAccount.getPrincipalBalance()
//                .multiply(earlyRepayDamageValue)
//                .setScale(0, BigDecimal.ROUND_UP);
//        BigDecimal receivableBalance = caculateReceivableBalanceExcludeEarlySettleDamageAmount(loanAccount,
//                feeCfgItemMap,
//                period2PaymentScheduleMap,
//                repayDate);
//        BigDecimal realEarlyRepayDamageAmount = BigDecimal.ZERO;
//        if (earlySettleAmount.add(earlyRepayDamageAmount)
//                .compareTo(receivableBalance) >= 0) {
//            realEarlyRepayDamageAmount = BigDecimal.ZERO;
//        } else {
//            realEarlyRepayDamageAmount = earlyRepayDamageAmount;
//        }
//        return realEarlyRepayDamageAmount;
//    }
//    
//    /**
//     * 判断提前节结清豁免期间是否需要将未到期的费用项应收置0
//     * <功能详细描述>
//     * @param loanAccount
//     * @param feeCfgItemMap
//     * @param period2PaymentScheduleMap
//     * @param repayDate
//     * @return [参数说明]
//     * 
//     * @return BigDecimal [返回类型说明]
//     * @exception throws [异常类型] [异常说明]
//     * @see [类、类#方法、类#成员]
//    */
//    public boolean isNeedSetUpZeroWhenEarlySettleExempt(
//            LoanAccount loanAccount,
//            Map<FeeItemEnum, LoanAccountFeeItem> feeCfgItemMap,
//            Map<String, PaymentSchedule> period2PaymentScheduleMap,
//            Date repayDate) {
//        String receivablePeriodOfRepayDate = getReceivablePeriodOfRepayDate(period2PaymentScheduleMap,
//                repayDate);
//        int receivablePeriodOfRepayDateInt = NumberUtils.toInt(receivablePeriodOfRepayDate);
//        
//        BigDecimal earlySettleAmount = BigDecimal.ZERO;
//        for (PaymentSchedule psTemp : period2PaymentScheduleMap.values()) {
//            for (PaymentScheduleEntry psEntryTemp : psTemp.getPaymentScheduleEntryList()) {
//                if (NumberUtils.isDigits(psEntryTemp.getPeriod())) {
//                    //所有带数字的期数
//                    if (feeCfgItemMap.get(psEntryTemp.getFeeItem())
//                            .isPrincipal()) {
//                        //平息    
//                        BigDecimal balanceReceivableAmountTemp = psEntryTemp.getReceivableAmount()
//                                .add(psEntryTemp.getExemptAmount())
//                                .subtract(psEntryTemp.getActualReceivedAmount());
//                        earlySettleAmount = earlySettleAmount.add(balanceReceivableAmountTemp);
//                    } else if (NumberUtils.toInt(psEntryTemp.getPeriod()) <= receivablePeriodOfRepayDateInt) {
//                        //平息    
//                        BigDecimal balanceReceivableAmountTemp = psEntryTemp.getReceivableAmount()
//                                .add(psEntryTemp.getExemptAmount())
//                                .subtract(psEntryTemp.getActualReceivedAmount());
//                        earlySettleAmount = earlySettleAmount.add(balanceReceivableAmountTemp);
//                    } else {
//                        //+0 //利息管理费不收
//                    }
//                } else {
//                    if (FeeItemEnum.TQHKWYJ.equals(psEntryTemp.getFeeItem())) {
//                        continue;
//                    }
//                    //平息    
//                    BigDecimal balanceReceivableAmountTemp = psEntryTemp.getReceivableAmount()
//                            .add(psEntryTemp.getExemptAmount())
//                            .subtract(psEntryTemp.getActualReceivedAmount());
//                    earlySettleAmount = earlySettleAmount.add(balanceReceivableAmountTemp);
//                }
//            }
//        }
//        
//        BigDecimal earlyRepayDamageValue = new BigDecimal(
//                feeCfgItemMap.get(FeeItemEnum.TQHKWYJ).getValue());
//        // 计算违约金
//        BigDecimal earlyRepayDamageAmount = loanAccount.getPrincipalBalance()
//                .multiply(earlyRepayDamageValue)
//                .setScale(0, BigDecimal.ROUND_UP);
//        BigDecimal receivableBalance = caculateReceivableBalanceExcludeEarlySettleDamageAmount(loanAccount,
//                feeCfgItemMap,
//                period2PaymentScheduleMap,
//                repayDate);
//        
//        if (earlySettleAmount.add(earlyRepayDamageAmount)
//                .compareTo(receivableBalance) >= 0) {
//            return false;
//        } else {
//            return true;
//        }
//    }
//}
