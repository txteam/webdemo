/*
 * 描          述:  <描述>
 * 修  改   人:  Administrator
 * 修改时间:  2017年6月13日
 * <修改描述:>
 */
package com.tx.local.loanaccount.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import com.tx.core.exceptions.util.AssertUtils;
import com.tx.local.loanaccount.model.PaymentSchedule;
import com.tx.local.loanaccount.model.PaymentScheduleEntry;

/**
  * 还款计划详情业务层<br/>
  * <功能详细描述>
  * 
  * @author  Administrator
  * @version  [版本号, 2017年6月13日]
  * @see  [相关类/方法]
  * @since  [产品/模块版本]
  */
@Component("paymentScheduleDetailService")
public class PaymentScheduleDetailService {
    
    /** 还款计划业务层 */
    @Resource(name = "paymentScheduleService")
    private PaymentScheduleService paymentScheduleService;
    
    /** 还款计划分项业务层 */
    @Resource(name = "paymentScheduleEntryService")
    private PaymentScheduleEntryService paymentScheduleEntryService;
    
    /**
     * 查询已经逾期的还款计划项及还款计划分项<br/>
     * <功能详细描述>
     * @param now [参数说明]
     * 
     * @return void [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
    */
    public List<PaymentSchedule> queryDetailListByLoanAccountId(String loanAccountId,Map<String, Object> params) {
        AssertUtils.notEmpty(loanAccountId, "loanAccountId is empty.");
        
        //生成查询条件
        params = params == null ? new HashMap<String, Object>() : params;
        //根据实际情况，填入排序字段等条件，根据是否需要排序，选择调用dao内方法
        List<PaymentSchedule> resList = this.paymentScheduleService.queryListByLoanAccountId(loanAccountId,params);
        List<PaymentScheduleEntry> paymentScheduleEntryList = this.paymentScheduleEntryService.queryListByLoanAccountId(loanAccountId, params);
        MultiValueMap<String, PaymentScheduleEntry> pseMMap = new LinkedMultiValueMap<>();
        for(PaymentScheduleEntry pseTemp : paymentScheduleEntryList){
            pseMMap.add(pseTemp.getPaymentSchedule().getId(), pseTemp);
        }
        if (!CollectionUtils.isEmpty(resList)) {
            for (PaymentSchedule paymentSchedule : resList) {
                List<PaymentScheduleEntry> entryList = pseMMap.get(paymentSchedule.getId());
                paymentSchedule.setPaymentScheduleEntryList(entryList);
            }
        }
        return resList;
    }
    
//  /**
  //      * 将还款计划分项根据费用配置项的还款优先级从小到大排序
  //      *<功能详细描述>
  //      * @param paymentScheduleEntryList
  //      * @param feeCfgItemMap [参数说明]
  //      * 
  //      * @return void [返回类型说明]
  //      * @exception throws [异常类型] [异常说明]
  //      * @see [类、类#方法、类#成员]
  //     */
  //    public void sortPaymentScheduleEntryByRepayPriority(List<PaymentScheduleEntry> paymentScheduleEntryList,
  //            final Map<FeeItemEnum, LoanAccountFeeItem> feeCfgItemMap) {
  //        Collections.sort(paymentScheduleEntryList, new Comparator<PaymentScheduleEntry>() {
  //            @Override
  //            public int compare(PaymentScheduleEntry o1, PaymentScheduleEntry o2) {
  //                LoanAccountFeeItem i1 = feeCfgItemMap.get(o1.getFeeItem());
  //                LoanAccountFeeItem i2 = feeCfgItemMap.get(o2.getFeeItem());
  //                return Integer.compare(i1.getRepayPriority(), i2.getRepayPriority());
  //            }
  //        });
  //    }
  //    
  //    /**
  //      * 根据贷款账户id查询还款计划分项
  //      *<功能详细描述>
  //      * @param loanAccountId
  //      * @return [参数说明]
  //      * 
  //      * @return MultiValueMap<FeeItemEnum,PaymentScheduleEntry> [返回类型说明]
  //      * @exception throws [异常类型] [异常说明]
  //      * @see [类、类#方法、类#成员]
  //     */
  //    public MultiValueMap<FeeItemEnum, PaymentScheduleEntry> queryPaymentScheduleEntryMapByLoanAccountId(
  //            String loanAccountId) {
  //        Map<String, Object> params = new HashMap<String, Object>();
  //        params.put("loanAccountId", loanAccountId);
  //        List<PaymentSchedule> paymentScheduleList = this.paymentScheduleDao.queryPaymentScheduleList(params);
  //        Map<String, PaymentSchedule> scheduleMap = new HashMap<String, PaymentSchedule>();
  //        for (PaymentSchedule paymentSchedule : paymentScheduleList) {
  //            scheduleMap.put(paymentSchedule.getPeriod(), paymentSchedule);
  //        }
  //        
  //        MultiValueMap<FeeItemEnum, PaymentScheduleEntry> entryMultiValueMap = new LinkedMultiValueMap<FeeItemEnum, PaymentScheduleEntry>();
  //        Map<FeeItemEnum, LoanAccountFeeItem> loanAccountFeeItemCfgMap = this
  //                .queryLoanAccountFeeCfgItemMap(loanAccountId);
  //        for (PaymentScheduleEntry paymentScheduleEntry : paymentScheduleEntryList) {
  //            LoanAccountFeeItem loanAccountFeeCfgItem = loanAccountFeeItemCfgMap
  //                    .get(paymentScheduleEntry.getFeeItem());
  //            if (!loanAccountFeeCfgItem.isExemptAble()) {
  //                continue;
  //            }
  //            BigDecimal receivableAmount = paymentScheduleEntry.getReceivableAmount();
  //            BigDecimal exemptAmount = paymentScheduleEntry.getExemptAmount();
  //            BigDecimal actualReceivedAmount = paymentScheduleEntry.getActualReceivedAmount();
  //            /**
  //             * 1、应收 + 豁免 - 实收 大于0
  //             * 2、应收 + 豁免 等于0 且 豁免不等于0
  //             * 3、应收 + 豁免 - 实收 等于0 且豁免不等于0
  //             */
  //            if (receivableAmount.add(exemptAmount).subtract(actualReceivedAmount).compareTo(BigDecimal.ZERO) > 0
  //                    || (receivableAmount.add(exemptAmount).compareTo(BigDecimal.ZERO) == 0
  //                            && exemptAmount.compareTo(BigDecimal.ZERO) != 0)
  //                    || (receivableAmount.add(exemptAmount)
  //                            .subtract(actualReceivedAmount)
  //                            .compareTo(BigDecimal.ZERO) == 0 && exemptAmount.compareTo(BigDecimal.ZERO) != 0)) {
  //                PaymentSchedule paymentSchedule = scheduleMap.get(paymentScheduleEntry.getPeriod());
  //                if (paymentSchedule.isSettle()) {
  //                    continue;
  //                }
  //                paymentScheduleEntry.setPaymentSchedule(paymentSchedule);
  //                entryMultiValueMap.add(paymentScheduleEntry.getFeeItem(), paymentScheduleEntry);
  //            }
  //        }
  //        return entryMultiValueMap;
  //    }
  //    
  //    /**
  //      * 根据贷款账户id查询还款计划，并附带还款计划分项
  //      *<功能详细描述>
  //      * @param loanAccountId
  //      * @return [参数说明]
  //      * 
  //      * @return List<PaymentSchedule> [返回类型说明]
  //      * @exception throws [异常类型] [异常说明]
  //      * @see [类、类#方法、类#成员]
  //     */
  //    public List<PaymentSchedule> queryPaymentScheduleWithEntryByLoanAccountId(String loanAccountId) {
  //        List<PaymentSchedule> paymentScheduleList = this.queryListByLoanAccountId(loanAccountId);
  //        Map<String, PaymentSchedule> paymentScheduleMap = new HashMap<String, PaymentSchedule>();
  //        for (PaymentSchedule paymentSchedule : paymentScheduleList) {
  //            paymentScheduleMap.put(paymentSchedule.getPeriod(), paymentSchedule);
  //        }
  //        List<PaymentScheduleEntry> paymentScheduleEntryList = this.paymentScheduleEntryService
  //                .queryPaymentScheduleEntryListByLoanAccountId(loanAccountId);
  //        MultiValueMap<String, PaymentScheduleEntry> entryMultiValueMap = new LinkedMultiValueMap<String, PaymentScheduleEntry>();
  //        for (PaymentScheduleEntry paymentScheduleEntry : paymentScheduleEntryList) {
  //            entryMultiValueMap.add(paymentScheduleEntry.getPeriod(), paymentScheduleEntry);
  //        }
  //        for (Map.Entry<String, List<PaymentScheduleEntry>> entry : entryMultiValueMap.entrySet()) {
  //            String period = entry.getKey();
  //            List<PaymentScheduleEntry> entryList = entry.getValue();
  //            PaymentSchedule paymentSchedule = paymentScheduleMap.get(period);
  //            paymentSchedule.setPaymentScheduleEntryList(entryList);
  //        }
  //        return paymentScheduleList;
  //    }
}
