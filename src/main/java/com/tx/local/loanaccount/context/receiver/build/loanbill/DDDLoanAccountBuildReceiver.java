/*
 * 描          述:  <描述>
 * 修  改   人:  Administrator
 * 修改时间:  2017年10月24日
 * <修改描述:>
 */
package com.tx.local.loanaccount.context.receiver.build.loanbill;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.annotation.Resource;

import org.apache.commons.collections4.MapUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.joda.time.DateTime;
import org.springframework.stereotype.Component;

import com.tx.component.command.context.CommandResponse;
import com.tx.core.TxConstants;
import com.tx.core.exceptions.util.AssertUtils;
import com.tx.local.basicdata.model.FeeItemCfg;
import com.tx.local.basicdata.model.FeeItemEnum;
import com.tx.local.loanaccount.context.receiver.build.AbstractLoanAccountBuildReceiver;
import com.tx.local.loanaccount.context.request.build.LoanBillLoanAccountBuildRequest;
import com.tx.local.loanaccount.model.LoanAccount;
import com.tx.local.loanaccount.model.LoanAccount2LoanBill;
import com.tx.local.loanaccount.model.LoanAccountFeeItem;
import com.tx.local.loanaccount.model.LoanAccountTypeEnum;
import com.tx.local.loanaccount.model.PaymentSchedule;
import com.tx.local.loanaccount.model.PaymentScheduleEntry;
import com.tx.local.loanaccount.model.ScheduleAssociateMap;
import com.tx.local.loanaccount.model.ScheduleEntryAssociateMap;
import com.tx.local.loanaccount.model.ScheduleTypeEnum;
import com.tx.local.loanaccount.service.LoanAccount2LoanBillService;
import com.tx.local.loanaccount.strategy.loanaccount.LoanAccountStrategy;

/**
  * 车辆融资租赁贷款账户构建处理器<br/>
  * <功能详细描述>
  * 
  * @author  Administrator
  * @version  [版本号, 2017年10月24日]
  * @see  [相关类/方法]
  * @since  [产品/模块版本]
  */
@Component("dddLoanAccountBuildReceiver")
public class DDDLoanAccountBuildReceiver extends AbstractLoanAccountBuildReceiver<LoanBillLoanAccountBuildRequest> {
    
    @Resource(name = "loanAccount2LoanBillService")
    private LoanAccount2LoanBillService loanAccount2LoanBillService;
    
    /**
     * @return
     */
    @Override
    public int getOrder() {
        return 0;
    }
    
    /**
     * @param request
     * @return
     */
    @Override
    protected boolean isMatch(LoanBillLoanAccountBuildRequest request) {
        if (LoanAccountTypeEnum.DDD.equals(request.getLoanAccountType())) {
            return true;
        }
        return false;
    }
    
    /**
     * @param request
     * @return
     */
    @Override
    public LoanAccount buildLoanAccount(
            LoanBillLoanAccountBuildRequest request) {
        LoanAccount loanAccount = super.buildLoanAccount(request);
        
        return loanAccount;
    }
    
    /**
     * @param request
     * @param loanAccount
     * @return
     */
    @Override
    public List<LoanAccountFeeItem> buildLoanAccountFeeItems(
            LoanBillLoanAccountBuildRequest request, LoanAccount loanAccount) {
        String loanAccountId = loanAccount.getId();
        
        List<LoanAccountFeeItem> resList = new ArrayList<>();
        
        if (MapUtils.isEmpty(request.getFeeItemMap())) {
            return resList;
        }
        
        for (Entry<FeeItemEnum, String> entryTemp : request.getFeeItemMap()
                .entrySet()) {
            LoanAccountFeeItem fi_lx = new LoanAccountFeeItem();
            fi_lx.setLoanAccountId(loanAccountId);
            fi_lx.setFeeItem(entryTemp.getKey());
            fi_lx.setValue(entryTemp.getValue());
            
            resList.add(fi_lx);
        }
        
        return resList;
    }
    
    /**
     * @param request
     * @param response
     * @param loanAccount
     * @param feeCfgItems
     * @param paymentSchedules
     */
    @Override
    public void postHandle(LoanBillLoanAccountBuildRequest request,
            CommandResponse response, LoanAccount loanAccount,
            List<LoanAccountFeeItem> feeCfgItems,
            List<PaymentSchedule> paymentScheduleList,List<PaymentScheduleEntry> paymentScheduleEntryList) {
        super.postHandle(request,
                response,
                loanAccount,
                feeCfgItems,
                paymentScheduleList,
                paymentScheduleEntryList);
        
        //LoanAccount2LoanBill 持久化
        LoanAccount2LoanBill la2lb = new LoanAccount2LoanBill();
        la2lb.setLoanAccount(loanAccount);
        la2lb.setLoanType(request.getLoanType());
        la2lb.setLoanBillId(request.getLoanBillId());
        la2lb.setLoanBillNumber(request.getLoanBillNumber());
        la2lb.setLoanProductId(request.getLoanProductName());
        la2lb.setLoanProductName(request.getLoanProductName());
        this.loanAccount2LoanBillService.insert(la2lb);
    }
    
    /**
     * @param request
     * @param loanAccount
     * @param feeCfgItems
     * @return
     */
    @Override
    public List<PaymentSchedule> buildPaymentSchedules(
            LoanBillLoanAccountBuildRequest request, LoanAccount loanAccount,
            List<LoanAccountFeeItem> feeCfgItems,
            LoanAccountStrategy strategy) {
        Map<FeeItemEnum, FeeItemCfg> feeItemCfgMap = loanAccount
                .getFeeItemCfgMap();
        Map<FeeItemEnum, LoanAccountFeeItem> feeItemMap = loanAccount
                .getFeeItemMap();
        
        //构建还款计划分项列表
        Map<String, Object> params = new HashMap<>();
        List<PaymentScheduleEntry> paymentScheduleEntryList = strategy
                .buildPaymentScheduleEntryList(loanAccount,
                        feeItemMap,
                        params,
                        0,
                        loanAccount.getRepayWay());
        ScheduleEntryAssociateMap<PaymentScheduleEntry> pseMap = new ScheduleEntryAssociateMap<>(
                paymentScheduleEntryList);
        //根据还款计划分项构建还款计划实例
        ScheduleAssociateMap<PaymentSchedule> psMap = buildPaymentScheduleByEntry(
                loanAccount, pseMap);
        
        //为还款计划生成
        DateTime firstRepayDateTime = new DateTime(
                loanAccount.getFirstRepayDate());
        BigDecimal loanAmount = loanAccount.getLoanAmount(); //贷款金额
        
        DateTime preMonthDateTime = firstRepayDateTime.plusMonths(-1);
        int monthlyRepayDay = loanAccount.getMonthlyRepayDay();
        //对应期数的还款日
        DateTime zeroRepayDateTime = new DateTime(preMonthDateTime.getYear(),
                preMonthDateTime.getMonthOfYear(), monthlyRepayDay, 23, 59, 59);
        
        //计算每一期的还款时间以及
        for (ScheduleTypeEnum scheduleType : psMap.keySet()) {
            BigDecimal principalBalance = loanAmount;//本金结余：
            
            String nextPeriod = "1";
            while (!StringUtils.isBlank(nextPeriod)) {
                //校验合法性
                AssertUtils.isTrue(psMap.contains(scheduleType, nextPeriod),
                        "paymentScheduleMap not conatains period:{}",
                        nextPeriod);
                int periodIndex = NumberUtils.toInt(nextPeriod);
                //获取对应期数的还款计划
                PaymentSchedule ps = psMap.get(scheduleType, nextPeriod);
                //设置还款日期
                ps.setRepaymentDate(
                        zeroRepayDateTime.plusMonths(periodIndex).toDate());
                //计算本金结余
                List<PaymentScheduleEntry> entryList = new ArrayList<>(
                        pseMap.get(scheduleType, nextPeriod).values());
                for (PaymentScheduleEntry entryTemp : entryList) {
                    FeeItemEnum feeItem = entryTemp.getFeeItem();
                    AssertUtils.isTrue(feeItemCfgMap.containsKey(feeItem),
                            "请检查贷款账户类型为'{}'的LoanAccountStrategy的实现中，FeeItemStrategy的策略，其中不含有费用项目:'{}'",
                            new Object[] { request.getLoanAccountType(),
                                    feeItem });
                    
                    if (feeItemCfgMap.get(feeItem).isPrincipal()) {
                        principalBalance = principalBalance
                                .subtract(entryTemp.getReceivableAmount());
                    }
                }
                //设置计划中的本金结余
                ps.setPrincipalBalance(principalBalance);
                //下一期
                nextPeriod = ps.getNextPeriod();//下一期的期数
            }
            AssertUtils.isTrue(BigDecimal.ZERO.compareTo(principalBalance) == 0,
                    "last period principalBalance is not zero.principalBalance:{}",
                    new Object[] { principalBalance });
        }
        
        List<PaymentSchedule> paymentSchedules = new ArrayList<PaymentSchedule>(
                TxConstants.INITIAL_CONLLECTION_SIZE);
        paymentSchedules.addAll(psMap.valueList());
        return paymentSchedules;
    }
    
}
