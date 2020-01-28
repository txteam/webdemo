/*
 * 描          述:  <描述>
 * 修  改   人:  Administrator
 * 修改时间:  2017年6月17日
 * <修改描述:>
 */
package com.tx.local.loanaccount.helper.tradingrecord;

import java.util.Comparator;
import java.util.Date;

import org.apache.commons.lang3.StringUtils;
import org.joda.time.DateTime;

import com.tx.core.exceptions.util.AssertUtils;
import com.tx.local.loanaccount.model.LATradingRecord;

/**
 * 交易记录辅助类<br/>
 * <功能详细描述>
 * 
 * @author  Administrator
 * @version  [版本号, 2017年6月17日]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public class LATradingRecordHelper {
    
    /**
     * 生成交易id<br/>
     * <功能详细描述>
     * @return [参数说明]
     * 
     * @return String [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    public static String generateLATradingRecordId(Date createDate) {
        AssertUtils.notNull(createDate, "createDate is null.");
        
        StringBuilder sb = new StringBuilder(128);
        DateTime createDateTime = new DateTime(createDate);
        sb.append(createDateTime.toString("yyyyMMddHH"));
        
        long tradingRecordSeq = 1l;
        //FIXME: 这里待修改，没有seq容器后需要替代实现
        //SequenceContext.getContext().nextVal("SEQ_LA_TRADING_RECORD");
        sb.append(StringUtils.leftPad("" + tradingRecordSeq, 22, "0"));
        return sb.toString();
    }
    
    /**
     * 根据期数进行排序<br/>
     */
    public static Comparator<LATradingRecord> ID_COMPARATOR = new Comparator<LATradingRecord>() {
        @Override
        public int compare(LATradingRecord o1, LATradingRecord o2) {
            return o1.getId().compareTo(o2.getId());
        }
    };
    
    /**
     * 根据期数进行排序<br/>
     */
    public static Comparator<LATradingRecord> CREATEDATE_COMPARATOR = new Comparator<LATradingRecord>() {
        @Override
        public int compare(LATradingRecord o1, LATradingRecord o2) {
            return o1.getCreateDate().compareTo(o2.getCreateDate());
        }
    };
}
