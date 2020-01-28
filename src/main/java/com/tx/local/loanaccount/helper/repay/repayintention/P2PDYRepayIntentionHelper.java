///*
// * 描          述:  <描述>
// * 修  改   人:  Administrator
// * 修改时间:  2015年3月7日
// * <修改描述:>
// */
//package com.tx.local.loanaccount.helper.repay.repayintention;
//
//import java.math.BigDecimal;
//import java.util.ArrayList;
//import java.util.Date;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//import java.util.Map.Entry;
//
//import javax.annotation.Resource;
//
//import org.apache.commons.collections.MapUtils;
//import org.apache.commons.lang3.StringUtils;
//import org.springframework.stereotype.Component;
//
//import com.tx.component.basicdata.model.FeeItemEnum;
//import com.tx.core.exceptions.util.AssertUtils;
//import com.tx.local.loanaccount.LoanAccountConstants;
//import com.tx.local.loanaccount.DefaultLoanAccountConstants;
//import com.tx.local.loanaccount.helper.repay.RepayIntentionHelper;
//import com.tx.local.loanaccount.model.LoanAccount;
//import com.tx.local.loanaccount.model.LoanAccountTypeEnum;
//import com.tx.local.loanaccount.model.PaymentRecordEntry;
//import com.tx.local.loanaccount.model.PaymentSchedule;
//import com.tx.local.loanaccount.model.PaymentScheduleEntry;
//import com.tx.local.loanaccount.model.RepayAmountTypeEnum;
//import com.tx.local.loanaccount.model.RepayChannelTypeEnum;
//import com.tx.local.loanaccount.model.RepayIntention;
//import com.tx.local.loanaccount.model.RepayIntentionTypeEnum;
//import com.tx.local.loanaccount.model.LATradingRecord;
//import com.tx.local.loanaccount.service.PaymentRecordEntryService;
//
///**
// * 中山抵质押贷还款意愿辅助类<br/>
// * 
// * @author Rain.he
// * @version  [版本号, 2015年09月10日]
// * @see  [相关类/方法]
// * @since  [产品/模块版本]
// */
//@Component("zsdyRepayIntentionHelper")
//public class P2PDYRepayIntentionHelper extends RepayIntentionHelper {
//    
//    @Resource(name = "paymentRecordEntryService")
//    private PaymentRecordEntryService paymentRecordEntryService;
//    
//    /** 咨询公司费用项 */
//    private static List<FeeItemEnum> zxFeeItemCollections = DefaultLoanAccountConstants.DEFAULT_ZX_FEEITEM_COLLECTIONS;
//    
//    /** 贷款公司费用项 */
//    private static List<FeeItemEnum> dkFeeItemCollections = DefaultLoanAccountConstants.DEFAULT_DK_FEEITEM_COLLECTIONS;
//    
//    /**
//     * @param params
//     * @return
//     */
//    @Override
//    public boolean supports(Object... params) {
//        LoanAccountTypeEnum loanAccountType = getParam(params, 0, LoanAccountTypeEnum.class);
//        if(LoanAccountTypeEnum.P2P.equals(loanAccountType)){
//            return true;
//        }else{
//            return false;
//        }
//    }
//    
//    @Override
//    public RepayIntention buildRepayIntentionByTryOverdueAuto(LoanAccount loanAccount, BigDecimal amount) {
//        RepayIntention repayIntention = new RepayIntention();
//        repayIntention.setRepayType(RepayIntentionTypeEnum.TRANSFER_REPAY);
//        repayIntention.setRepayAmountType(RepayAmountTypeEnum.MONTHLY_REPAY_SUM);
//        repayIntention.setRepayChannelType(RepayChannelTypeEnum.DK);
//        repayIntention.setRepayDate(new Date());
//        repayIntention.setLoanAccountId(loanAccount.getId());
//        repayIntention.setAmount(amount);
//        repayIntention.setRepayPeriod(loanAccount.getCurrentPeriod());
//        
////        List<BankAccount> bankAccountList = this.bankAccountService.queryBankAccountListByBankAccountTypeAndVcid(BankAccountTypeEnum.自动转账还款_贷款公司,
////                loanAccount.getVcid());
////        AssertUtils.notEmpty(bankAccountList,
////                "bankAccountList is empty.type:{} vcid:{} loanAccountId:{}",
////                new Object[] { BankAccountTypeEnum.自动转账还款_贷款公司,
////                        loanAccount.getVcid(), loanAccount.getId() });
////        repayIntention.setBankAccountId(bankAccountList.get(0).getId());
//        
//        return repayIntention;
//    }
//    
//    /**
//     * @param repayIntention
//     * @param calmAssignMap
//     * @return
//     */
//    @Override
//    public List<RepayIntention> splitRepayIntention(LoanAccount loanAccount,
//            RepayIntention repayIntention,
//            Map<String, Map<FeeItemEnum, BigDecimal>> calmAssignMap) {
//        AssertUtils.notNull(repayIntention, "repayIntention is null.");
//        AssertUtils.notNull(calmAssignMap, "calmAssignMap is null.");
//        repayIntention.validate();
//        Map<RepayChannelTypeEnum, BigDecimal> sourceRepayChannelType2AmountMap = repayIntention.getRepayChannelType2AmountMap();
//        
//        List<RepayIntention> resList = new ArrayList<>();
//        Map<FeeItemEnum, BigDecimal> zxRepayAmountMapping = new HashMap<>();
//        BigDecimal zxRepaySum = BigDecimal.ZERO;
//        Map<FeeItemEnum, BigDecimal> dkRepayAmountMapping = new HashMap<>();
//        BigDecimal dkRepaySum = BigDecimal.ZERO;
//        for (Entry<String, Map<FeeItemEnum, BigDecimal>> entryTemp : calmAssignMap.entrySet()) {
//            for (Entry<FeeItemEnum, BigDecimal> entry : entryTemp.getValue()
//                    .entrySet()) {
//                FeeItemEnum feeItemTemp = entry.getKey();
//                BigDecimal valueTemp = entry.getValue();
//                if (zxFeeItemCollections.contains(feeItemTemp)) {
//                    if (!zxRepayAmountMapping.containsKey(feeItemTemp)) {
//                        zxRepayAmountMapping.put(feeItemTemp, BigDecimal.ZERO);
//                    }
//                    zxRepayAmountMapping.put(feeItemTemp,
//                            zxRepayAmountMapping.get(feeItemTemp)
//                                    .add(valueTemp));
//                    zxRepaySum = zxRepaySum.add(valueTemp);
//                } else if (dkFeeItemCollections.contains(feeItemTemp)) {
//                    if (!dkRepayAmountMapping.containsKey(feeItemTemp)) {
//                        dkRepayAmountMapping.put(feeItemTemp, BigDecimal.ZERO);
//                    }
//                    dkRepayAmountMapping.put(feeItemTemp,
//                            dkRepayAmountMapping.get(feeItemTemp)
//                                    .add(valueTemp));
//                    dkRepaySum = dkRepaySum.add(valueTemp);
//                } else {
//                    AssertUtils.isTrue(false,
//                            "错误的费用项。feeItemTemp:{}",
//                            new Object[] { feeItemTemp });
//                }
//            }
//        }
//        //根据豁免设置项所属的公司，将豁免拆分为两个公司各自的豁免
////        List<String> exemptSettingIdList = repayIntention.getExemptSettingIdList();
////        List<ExemptSetting> exemptSettingList = this.exemptSettingService.queryExemptSettingListByExemptSettingIdList(exemptSettingIdList);
////        List<String> zxExemptSettingIdList = new ArrayList<>();
////        List<String> dkExemptSettingIdList = new ArrayList<>();
////        for (ExemptSetting exemptSettingTemp : exemptSettingList) {
////            FeeItemEnum feeItemTemp = exemptSettingTemp.getFeeItem();
////            if (zxFeeItemCollections.contains(feeItemTemp)) {
////                zxExemptSettingIdList.add(exemptSettingTemp.getId());
////            } else if (dkFeeItemCollections.contains(feeItemTemp)) {
////                dkExemptSettingIdList.add(exemptSettingTemp.getId());
////            } else {
////                AssertUtils.isTrue(false,
////                        "错误的费用项。feeItemTemp:{}",
////                        new Object[] { feeItemTemp });
////            }
////        }
//        if (!MapUtils.isEmpty(sourceRepayChannelType2AmountMap)) {
//            AssertUtils.isTrue(zxRepaySum.compareTo(sourceRepayChannelType2AmountMap.get(RepayChannelTypeEnum.ZX)) <= 0,
//                    "error assignMap.");
//            AssertUtils.isTrue(dkRepaySum.compareTo(sourceRepayChannelType2AmountMap.get(RepayChannelTypeEnum.DK)) <= 0,
//                    "error assignMap.");
//            
//            zxRepaySum = sourceRepayChannelType2AmountMap.get(RepayChannelTypeEnum.ZX);
//            dkRepaySum = sourceRepayChannelType2AmountMap.get(RepayChannelTypeEnum.DK);
//        }
//        
//        if (dkRepaySum.compareTo(BigDecimal.ZERO) > 0) {
//            RepayIntention dkRepayIntention = new RepayIntention();
//            String dkBankAccountId = null;
//            if (!MapUtils.isEmpty(repayIntention.getRepayChannelType2BankAccountIdMap())
//                    && repayIntention.getRepayChannelType2BankAccountIdMap()
//                            .containsKey(RepayChannelTypeEnum.DK)) {
//                dkBankAccountId = repayIntention.getRepayChannelType2BankAccountIdMap()
//                        .get(RepayChannelTypeEnum.DK);
//            }
//            if (StringUtils.isEmpty(dkBankAccountId)) {
////                List<BankAccount> bankAccountList = this.bankAccountService.queryBankAccountListByBankAccountTypeAndVcid(BankAccountTypeEnum.自动转账还款_贷款公司,
////                        loanAccount.getVcid());
////                AssertUtils.notEmpty(bankAccountList,
////                        "bankAccountList is empty.type:{} vcid:{} loanAccountId:{}",
////                        new Object[] { BankAccountTypeEnum.自动转账还款_贷款公司,
////                                loanAccount.getVcid(), loanAccount.getId() });
////                dkBankAccountId = bankAccountList.get(0).getId();
//            }
//            AssertUtils.notEmpty(dkBankAccountId, "dkBankAccountId is empty.");
//            
//            dkRepayIntention.setAmount(dkRepaySum);
//            dkRepayIntention.setBankAccountId(dkBankAccountId);
//            dkRepayIntention.setLoanAccountId(loanAccount.getId());
//            
//            dkRepayIntention.setRepayChannelType(RepayChannelTypeEnum.DK);
//            dkRepayIntention.setRepayAmountType(repayIntention.getRepayAmountType());
//            dkRepayIntention.setRepayType(repayIntention.getRepayType());
//            dkRepayIntention.setRepayDate(repayIntention.getRepayDate());
//            dkRepayIntention.setRepayPeriod(repayIntention.getRepayPeriod());
//            dkRepayIntention.setFeeItem2AmountMap(dkRepayAmountMapping);
//            
//            //调账操作无需做豁免
//            dkRepayIntention.setOutsourceAssignRecordId(repayIntention.getOutsourceAssignRecordId());
//            //dkRepayIntention.setExemptSettingIdList(dkExemptSettingIdList);
//            
//            resList.add(dkRepayIntention);
//        }
//        
//        AssertUtils.isTrue(repayIntention.getAmount()
//                .subtract(dkRepaySum)
//                .compareTo(zxRepaySum) >= 0,
//                "repay.amount - dkRepaySum should >= zxRepaySum.");
//        //咨询公司还款金额 = 还款金额 - 贷款公司还款金额
//        if (repayIntention.getAmount()
//                .subtract(dkRepaySum)
//                .compareTo(BigDecimal.ZERO) > 0) {
//            RepayIntention zxRepayIntention = new RepayIntention();
//            String zxBankAccountId = null;
//            if (!MapUtils.isEmpty(repayIntention.getRepayChannelType2BankAccountIdMap())
//                    && repayIntention.getRepayChannelType2BankAccountIdMap()
//                            .containsKey(RepayChannelTypeEnum.ZX)) {
//                zxBankAccountId = repayIntention.getRepayChannelType2BankAccountIdMap()
//                        .get(RepayChannelTypeEnum.ZX);
//            }
//            if (StringUtils.isEmpty(zxBankAccountId)) {
////                List<BankAccount> bankAccountList = this.bankAccountService.queryBankAccountListByBankAccountTypeAndVcid(BankAccountTypeEnum.自动转账还款_咨询公司,
////                        loanAccount.getVcid());
////                AssertUtils.notEmpty(bankAccountList,
////                        "bankAccountList is empty.type:{} vcid:{} loanAccountId:{}",
////                        new Object[] { BankAccountTypeEnum.自动转账还款_贷款公司,
////                                loanAccount.getVcid(), loanAccount.getId() });
////                zxBankAccountId = bankAccountList.get(0).getId();
//            }
//            AssertUtils.notEmpty(zxBankAccountId, "zxBankAccountId is empty.");
//            
//            zxRepayIntention.setAmount(repayIntention.getAmount()
//                    .subtract(dkRepaySum));
//            zxRepayIntention.setBankAccountId(zxBankAccountId);
//            zxRepayIntention.setLoanAccountId(loanAccount.getId());
//            
//            zxRepayIntention.setRepayChannelType(RepayChannelTypeEnum.ZX);
//            zxRepayIntention.setRepayAmountType(repayIntention.getRepayAmountType());
//            zxRepayIntention.setRepayType(repayIntention.getRepayType());
//            zxRepayIntention.setRepayDate(repayIntention.getRepayDate());
//            zxRepayIntention.setRepayPeriod(repayIntention.getRepayPeriod());
//            zxRepayIntention.setFeeItem2AmountMap(zxRepayAmountMapping);
//            
//            //调账操作无需做豁免
//            zxRepayIntention.setOutsourceAssignRecordId(repayIntention.getOutsourceAssignRecordId());
//            //zxRepayIntention.setExemptSettingIdList(zxExemptSettingIdList);
//            
//            resList.add(zxRepayIntention);
//        }
//        return resList;
//    }
//    
//    /**
//     * 服务于调账后重新还款<br/>
//     * @param tradingRecord
//     * @param paymentRecordEntryList
//     * @return
//     */
//    @Override
//    public RepayIntention buildRepayIntentionBySourceTradingRecordAndPaymentRecordEntryList(
//            LATradingRecord tradingRecord,
//            List<PaymentRecordEntry> paymentRecordEntryList) {
//        AssertUtils.notNull(tradingRecord, "tradingRecord is null.");
//        AssertUtils.notEmpty(paymentRecordEntryList,
//                "paymentRecordEntryList is null.");
//        
//        //原还款金额从交易金额中获取，防止由于超额还款造成记录中的还款金额与实际还款金额不一致的情况出现
//        BigDecimal amount = tradingRecord.getSum();
//        String bankAccountId = tradingRecord.getBankAccountId();
//        String loanAccountId = tradingRecord.getLoanAccountId();
//        
//        RepayIntention repayIntention = new RepayIntention();
//        repayIntention.setAmount(amount);
//        repayIntention.setBankAccountId(bankAccountId);
//        repayIntention.setLoanAccountId(loanAccountId);
//        
//        RepayIntention sourceRepayIntention = fromString(tradingRecord.getRepayIntention());
//        repayIntention.setRepayChannelType(sourceRepayIntention.getRepayChannelType());
//        repayIntention.setRepayAmountType(sourceRepayIntention.getRepayAmountType());
//        repayIntention.setRepayType(sourceRepayIntention.getRepayType());
//        repayIntention.setRepayDate(sourceRepayIntention.getRepayDate());
//        
//        Map<RepayChannelTypeEnum, BigDecimal> repayChannel2AmountMap = new HashMap<>();
//        Map<FeeItemEnum, BigDecimal> repayAmountMapping = new HashMap<>();
//        for (PaymentRecordEntry prEntry : paymentRecordEntryList) {
//            FeeItemEnum feeItemTemp = prEntry.getFeeItem();
//            if (!repayAmountMapping.containsKey(feeItemTemp)) {
//                repayAmountMapping.put(feeItemTemp, BigDecimal.ZERO);
//            }
//            repayAmountMapping.put(feeItemTemp,
//                    repayAmountMapping.get(feeItemTemp)
//                            .add(prEntry.getAmount()));
//            
//            if (dkFeeItemCollections.contains(feeItemTemp)) {
//                if (!repayChannel2AmountMap.containsKey(RepayChannelTypeEnum.DK)) {
//                    repayChannel2AmountMap.put(RepayChannelTypeEnum.DK,
//                            BigDecimal.ZERO);
//                }
//                repayChannel2AmountMap.put(RepayChannelTypeEnum.DK,
//                        repayChannel2AmountMap.get(RepayChannelTypeEnum.DK)
//                                .add(prEntry.getAmount()));
//            } else if (zxFeeItemCollections.contains(feeItemTemp)) {
//                if (!repayChannel2AmountMap.containsKey(RepayChannelTypeEnum.ZX)) {
//                    repayChannel2AmountMap.put(RepayChannelTypeEnum.ZX,
//                            BigDecimal.ZERO);
//                }
//                repayChannel2AmountMap.put(RepayChannelTypeEnum.ZX,
//                        repayChannel2AmountMap.get(RepayChannelTypeEnum.ZX)
//                                .add(prEntry.getAmount()));
//            } else {
//                AssertUtils.isTrue(false,
//                        "错误的费用项类型.feeItem:{}",
//                        new Object[] { feeItemTemp });
//            }
//        }
//        repayIntention.setFeeItem2AmountMap(repayAmountMapping);
//        repayIntention.setRepayChannelType2AmountMap(repayChannel2AmountMap);
//        //调账操作无需做豁免
//        repayIntention.setOutsourceAssignRecordId(null);
//        
//        return repayIntention;
//    }
//    
//    /**
//     * 根据原交易构建新的还款意愿<br/>
//     * @param amount
//     * @param tradingRecord
//     * @return
//     */
//    @Override
//    public RepayIntention buildRepayIntentionBySourceTradingRecord(
//            BigDecimal amount, LATradingRecord tradingRecord) {
//        AssertUtils.notNull(tradingRecord, "tradingRecord is null.");
//        AssertUtils.notNull(amount, "amount is null.");
//        
//        //原还款金额从交易金额中获取，防止由于超额还款造成记录中的还款金额与实际还款金额不一致的情况出现
//        BigDecimal sourceAmount = tradingRecord.getSum();
//        AssertUtils.notNull(amount.compareTo(sourceAmount) <= 0,
//                "amount:{} should less than sourceAmount:{} is null.",
//                new Object[] { amount, sourceAmount });
//        
//        String bankAccountId = tradingRecord.getBankAccountId();
//        String loanAccountId = tradingRecord.getLoanAccountId();
//        
//        RepayIntention repayIntention = new RepayIntention();
//        repayIntention.setAmount(amount);
//        repayIntention.setBankAccountId(bankAccountId);
//        repayIntention.setLoanAccountId(loanAccountId);
//        
//        RepayIntention sourceRepayIntention = fromString(tradingRecord.getRepayIntention());
//        repayIntention.setRepayChannelType(sourceRepayIntention.getRepayChannelType());
//        repayIntention.setRepayAmountType(sourceRepayIntention.getRepayAmountType());
//        repayIntention.setRepayType(sourceRepayIntention.getRepayType());
//        repayIntention.setRepayDate(sourceRepayIntention.getRepayDate());
//        
//        BigDecimal repayAmountSum = BigDecimal.ZERO;
//        Map<FeeItemEnum, BigDecimal> repayAmountMapping = new HashMap<>();
//        if (!MapUtils.isEmpty(sourceRepayIntention.getFeeItem2AmountMap())) {
//            for (Entry<FeeItemEnum, BigDecimal> entryTemp : sourceRepayIntention.getFeeItem2AmountMap()
//                    .entrySet()) {
//                FeeItemEnum feeItem = entryTemp.getKey();
//                BigDecimal amountTemp = entryTemp.getValue();
//                if (!repayAmountMapping.containsKey(feeItem)) {
//                    repayAmountMapping.put(feeItem, amountTemp);
//                } else {
//                    repayAmountMapping.put(feeItem, amountTemp);
//                }
//                repayAmountSum = repayAmountSum.add(amountTemp);
//            }
//        }
//        if (repayAmountSum.compareTo(amount) > 0) {
//            repayAmountMapping.clear();
//        }
//        repayIntention.setFeeItem2AmountMap(repayAmountMapping);
//        
//        //调账操作无需做豁免
//        repayIntention.setOutsourceAssignRecordId(null);
//        
//        return repayIntention;
//    }
//    
//    /**
//     * 根据原交易的还款意愿构建还款意愿<br/>
//     * @param tradingRecord
//     * @return
//     */
//    @Override
//    public RepayIntention buildRepayIntentionBySourceTradingRecord(
//            LATradingRecord sourceTradingRecord) {
//        RepayIntention sourceRepayIntention = fromString(sourceTradingRecord.getRepayIntention());
//        return sourceRepayIntention;
//    }
//    
//    /**
//     * @param loanAccount
//     * @param repayDate
//     * @param paymentSchedule
//     * @param paymentScheduleEntryList
//     * @return
//     */
//    @Override
//    public RepayIntention buildRepayIntentionByPaymentSchedule(
//            LoanAccount loanAccount, Date repayDate,
//            PaymentSchedule paymentSchedule,
//            List<PaymentScheduleEntry> paymentScheduleEntryList) {
//        BigDecimal amount = paymentSchedule.getReceivableSum()
//                .add(paymentSchedule.getExemptSum())
//                .subtract(paymentSchedule.getActualReceivedSum());
//        AssertUtils.isTrue(amount.compareTo(BigDecimal.ZERO) > 0,
//                "repayAmount should more than 0:{}",
//                new Object[] { amount });
//        
//        RepayIntention repayIntention = new RepayIntention();
//        repayIntention.setRepayType(RepayIntentionTypeEnum.TRANSFER_REPAY);
//        repayIntention.setRepayAmountType(RepayAmountTypeEnum.MONTHLY_REPAY_SUM);
//        repayIntention.setRepayChannelType(RepayChannelTypeEnum.DK_ZX);
//        repayIntention.setRepayDate(repayDate);
//        repayIntention.setLoanAccountId(loanAccount.getId());
//        repayIntention.setAmount(amount);
//        repayIntention.setRepayPeriod(paymentSchedule.getPeriod());
//        
//        BigDecimal amountTempSum = BigDecimal.ZERO;
//        Map<FeeItemEnum, BigDecimal> feeItem2amountMap = new HashMap<FeeItemEnum, BigDecimal>();
//        for (PaymentScheduleEntry entry : paymentScheduleEntryList) {
//            FeeItemEnum feeItemTemp = entry.getFeeItem();
//            BigDecimal amountTemp = entry.getReceivableAmount()
//                    .add(entry.getExemptAmount())
//                    .subtract(entry.getActualReceivedAmount());
//            amountTempSum = amountTempSum.add(amountTemp);
//            
//            if (!feeItem2amountMap.containsKey(feeItemTemp)) {
//                feeItem2amountMap.put(feeItemTemp, BigDecimal.ZERO);
//            }
//            feeItem2amountMap.put(feeItemTemp,
//                    feeItem2amountMap.get(feeItemTemp).add(amountTemp));
//        }
//        AssertUtils.isTrue(amountTempSum.compareTo(amount) == 0,
//                "amountTempSum should equals amount:{} == {}",
//                new Object[] { amountTempSum, amount });
//        repayIntention.setFeeItem2AmountMap(feeItem2amountMap);
//        
//        return repayIntention;
//    }
//    
//}
