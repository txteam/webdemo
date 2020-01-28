/*
 * 描          述:  <描述>
 * 修  改   人:  Administrator
 * 修改时间:  2017年7月17日
 * <修改描述:>
 */
package com.tx.local.loanaccount.helper.paymentrecord;

import java.util.Comparator;

import org.apache.commons.lang3.math.NumberUtils;

import com.tx.local.loanaccount.model.PaymentRecord;

/**
  * 实收记录辅助类<br/>
  * <功能详细描述>
  * 
  * @author  Administrator
  * @version  [版本号, 2017年7月17日]
  * @see  [相关类/方法]
  * @since  [产品/模块版本]
  */
public class PaymentRecordHelper {
    
    /**
     * 根据期数进行排序<br/>
     */
    public static Comparator<PaymentRecord> PERIOD_COMPARATOR = new Comparator<PaymentRecord>() {
        @Override
        public int compare(PaymentRecord o1, PaymentRecord o2) {
            if (!NumberUtils.isDigits(o1.getPeriod()) && !NumberUtils.isDigits(o2.getPeriod())) {
                return o1.getPeriod().compareTo(o2.getPeriod());
            } else if (!NumberUtils.isDigits(o1.getPeriod())) {
                return 1;
            } else if (!NumberUtils.isDigits(o2.getPeriod())) {
                return -1;
            } else {
                Integer o1Period = NumberUtils.toInt(o1.getPeriod());
                Integer o2Period = NumberUtils.toInt(o2.getPeriod());
                return o1Period.compareTo(o2Period);
            }
        }
    };
}
