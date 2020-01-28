package com.tx.local.loanaccount.model;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.OneToMany;

import com.tx.core.exceptions.util.AssertUtils;

/**
  * 还款计划实体接口<br/>
  * <功能详细描述>
  * 
  * @author  Administrator
  * @version  [版本号, 2014年5月15日]
  * @see  [相关类/方法]
  * @since  [产品/模块版本]
 */
public class PaymentScheduleDetail implements Serializable {
    
    /** 注释内容 */
    private static final long serialVersionUID = 2924023656543345618L;
    
    /** 期数 */
    private String period;
    
    /** 主还款计划 */
    private PaymentSchedule mainPaymentSchedule;
    
    /** 还款计划分项 */
    @OneToMany
    private final Map<ScheduleTypeEnum, PaymentSchedule> paymentScheduleMap = new HashMap<ScheduleTypeEnum, PaymentSchedule>();
    
    /** <默认构造函数> */
    public PaymentScheduleDetail() {
        super();
    }
    
    /** <默认构造函数> */
    public PaymentScheduleDetail(String period, List<PaymentSchedule> paymentSchedules) {
        super();
        AssertUtils.notEmpty(period, "period is empty.");
        AssertUtils.notEmpty(paymentSchedules, "paymentSchedules is empty.");
        
        this.paymentScheduleMap.clear();
        this.period = period;
        for (PaymentSchedule ps : paymentSchedules) {
            AssertUtils.isTrue(period.equals(ps.getPeriod()), "period not matched.");
            
            if (ScheduleTypeEnum.MAIN.equals(ps.getScheduleType())) {
                this.mainPaymentSchedule = ps;
            }
            this.paymentScheduleMap.put(ps.getScheduleType(), ps);
        }
        AssertUtils.notNull(this.mainPaymentSchedule, "mainPaymentSchedule is null.");
    }
    
    /**
     * @return 返回 mainPaymentSchedule
     */
    public PaymentSchedule getMainPaymentSchedule() {
        return mainPaymentSchedule;
    }
    
    /**
     * @param 对mainPaymentSchedule进行赋值
     */
    public void setMainPaymentSchedule(PaymentSchedule mainPaymentSchedule) {
        this.mainPaymentSchedule = mainPaymentSchedule;
    }
    
    /**
     * @return 返回 paymentScheduleMap
     */
    public Map<ScheduleTypeEnum, PaymentSchedule> getPaymentScheduleMap() {
        return paymentScheduleMap;
    }
    
    /**
     * @return 返回 period
     */
    public String getPeriod() {
        return period;
    }
    
    /**
     * @param 对period进行赋值
     */
    public void setPeriod(String period) {
        this.period = period;
    }
}