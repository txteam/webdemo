package com.tx.local.loanaccount.context.request.trading.charge;

import java.util.Date;

import com.tx.local.basicdata.model.FeeItemEnum;
import com.tx.local.loanaccount.model.LATradingRecordTypeEnum;
import com.tx.local.loanaccount.model.RequestSourceTypeEnum;

/**
 * 逾期利息计费请求<br/>
 *     扣款失败手续费计费请求<br/>
 *     扣款失败后：如果当日已经计收过扣款失败手续费，则无需再进行计收<br/>
 * <功能详细描述>
 * 
 * @author  lenovo
 * @version  [版本号, 2015年1月8日]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public class OverdueInterestChargeRequest extends AbstractChargeRequest {
    
    /** 记录日期 */
    private final Date repayDate;
    
    /** 费用项 */
    private final FeeItemEnum feeItem;
    
    /** <默认构造函数> */
    public OverdueInterestChargeRequest(String loanAccountId, Date repayDate, FeeItemEnum feeItem) {
        super(loanAccountId, RequestSourceTypeEnum.AUTO_SCHEDULE_REQUEST);
        
        this.repayDate = repayDate;
        this.feeItem = feeItem;
    }
    
    /**
     * @return 返回 feeItem
     */
    public FeeItemEnum getFeeItem() {
        return feeItem;
    }
    
    /**
     * @return 返回 repayDate
     */
    public Date getRepayDate() {
        return repayDate;
    }
    
    /**
     * @return
     */
    @Override
    public LATradingRecordTypeEnum getTradingRecordType() {
        return LATradingRecordTypeEnum.CHARGE_OVERDUE_INTEREST;
    }
    
}
