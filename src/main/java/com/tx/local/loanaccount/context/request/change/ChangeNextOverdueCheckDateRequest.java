/*
 * 描          述:  <描述>
 * 修  改   人:  Administrator
 * 修改时间:  2017年12月27日
 * <修改描述:>
 */
package com.tx.local.loanaccount.context.request.change;

import java.util.Date;

import com.tx.core.exceptions.util.AssertUtils;
import com.tx.local.loanaccount.model.LATradingRecordTypeEnum;
import com.tx.local.loanaccount.model.RequestSourceTypeEnum;

/**
  * <功能简述>
  * <功能详细描述>
  * 
  * @author  Administrator
  * @version  [版本号, 2017年12月27日]
  * @see  [相关类/方法]
  * @since  [产品/模块版本]
  */
public class ChangeNextOverdueCheckDateRequest extends AbstractChangeRequest {
    
    /** 下次逾期检测日期 */
    private Date nextOverdueCheckDate;
    
    /** <默认构造函数> */
    public ChangeNextOverdueCheckDateRequest() {
        super();
    }
    
    /** <默认构造函数> */
    public ChangeNextOverdueCheckDateRequest(String loanAccountId,
            Date nextOverdueCheckDate) {
        super(loanAccountId, RequestSourceTypeEnum.AUTO_SCHEDULE_REQUEST);
        
        AssertUtils.notNull(nextOverdueCheckDate,
                "nextOverdueCheckDate is null.");
        this.nextOverdueCheckDate = nextOverdueCheckDate;
    }
    
    /**
     * @return 返回 nextOverdueCheckDate
     */
    public Date getNextOverdueCheckDate() {
        return nextOverdueCheckDate;
    }
    
    /**
     * @param 对nextOverdueCheckDate进行赋值
     */
    public void setNextOverdueCheckDate(Date nextOverdueCheckDate) {
        this.nextOverdueCheckDate = nextOverdueCheckDate;
    }
    
    /**
     * @return
     */
    @Override
    public LATradingRecordTypeEnum getTradingRecordType() {
        return LATradingRecordTypeEnum.CHANGE_ACCOUNT_INFO;
    }
}
