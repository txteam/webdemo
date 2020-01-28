/*
 * 描          述:  <描述>
 * 修  改   人:  Administrator
 * 修改时间:  2017年6月22日
 * <修改描述:>
 */
package com.tx.local.loanaccount.context.request.trading.exempt;

import com.tx.local.loanaccount.model.ExemptIntention;
import com.tx.local.loanaccount.model.LATradingRecordTypeEnum;
import com.tx.local.loanaccount.model.RequestSourceTypeEnum;

/**
  * 豁免请求<br/>
  * <功能详细描述>
  * 
  * @author  Administrator
  * @version  [版本号, 2017年6月22日]
  * @see  [相关类/方法]
  * @since  [产品/模块版本]
  */
public class ExemptRequest extends AbstractExemptRequest {
    
    /** <默认构造函数> */
    public ExemptRequest() {
        super();
    }
    
    /** <默认构造函数> */
    public ExemptRequest(String loanAccountId, ExemptIntention exemptIntention) {
        super(loanAccountId, exemptIntention, RequestSourceTypeEnum.OPER_REQUEST);
    }
    
    /**
     * @return
     */
    @Override
    public LATradingRecordTypeEnum getTradingRecordType() {
        return LATradingRecordTypeEnum.EXEMPT;
    }
}
