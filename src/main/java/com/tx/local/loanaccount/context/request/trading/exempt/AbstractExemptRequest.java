/*
 * 描          述:  <描述>
 * 修  改   人:  Administrator
 * 修改时间:  2017年6月22日
 * <修改描述:>
 */
package com.tx.local.loanaccount.context.request.trading.exempt;

import java.math.BigDecimal;
import java.util.Date;

import com.tx.core.exceptions.util.AssertUtils;
import com.tx.local.loanaccount.context.request.trading.AbstractTradingRequest;
import com.tx.local.loanaccount.model.ExemptIntention;
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
public abstract class AbstractExemptRequest extends AbstractTradingRequest {
    
    /** 还款意愿 */
    protected ExemptIntention exemptIntention;
    
    /** <默认构造函数> */
    public AbstractExemptRequest() {
        super();
    }
    
    /** 根据"账户ID"、"交易来源"和"贷款账户操作类型"构建交易请求 */
    public AbstractExemptRequest(String loanAccountId, ExemptIntention exemptIntention,
            RequestSourceTypeEnum sourceType) {
        super(loanAccountId, sourceType);
        
        AssertUtils.notNull(exemptIntention, "exemptIntention is null.");
        AssertUtils.isTrue(loanAccountId.equals(exemptIntention.getLoanAccountId()),
                "loanAccountId is not match exemptIntention.loanAccountId.");
        AssertUtils.isTrue(exemptIntention.getAmount().compareTo(BigDecimal.ZERO) > 0,
                "exemptIntention.amount should > 0.");
        AssertUtils.notNull(exemptIntention.getRepayDate(), "exemptIntention.repayDate is null.");
        
        this.exemptIntention = exemptIntention;
    }
    
    /**
     * 获取还款日期<br/>
     * <功能详细描述>
     * @return [参数说明]
     * 
     * @return Date [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    public final Date getRepayDate() {
        AssertUtils.notNull(this.exemptIntention, "exemptIntention is null.");
        return this.exemptIntention.getRepayDate();
    }
    
    /**
      * 获取还款金额<br/>
      * <功能详细描述>
      * @return [参数说明]
      * 
      * @return BigDecimal [返回类型说明]
      * @exception throws [异常类型] [异常说明]
      * @see [类、类#方法、类#成员]
     */
    public final BigDecimal getAmount() {
        AssertUtils.notNull(this.exemptIntention, "exemptIntention is null.");
        return this.exemptIntention.getAmount();
    }
    
    /**
     * @return 返回 exemptIntention
     */
    public ExemptIntention getExemptIntention() {
        return exemptIntention;
    }
    
    /**
     * @param 对exemptIntention进行赋值
     */
    public void setExemptIntention(ExemptIntention exemptIntention) {
        this.exemptIntention = exemptIntention;
    }
}
