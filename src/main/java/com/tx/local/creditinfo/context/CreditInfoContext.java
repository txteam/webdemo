/*
 * 描          述:  <描述>
 * 修  改   人:  Administrator
 * 修改时间:  2020年1月13日
 * <修改描述:>
 */
package com.tx.local.creditinfo.context;

import com.tx.local.creditinfo.model.CreditMultipLinked;
import com.tx.local.creditinfo.model.CreditSingleLinked;
import com.tx.local.creditinfo.service.CreditMultipLinkedService;
import com.tx.local.creditinfo.service.CreditSingleLinkedService;

/**
 * 信用信息容器<br/>
 * <功能详细描述>
 * 
 * @author  Administrator
 * @version  [版本号, 2020年1月13日]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public class CreditInfoContext {
    
    /**
     * 获取1:n的信用信息业务层<br/>
     * <功能详细描述>
     * @param type
     * @return [参数说明]
     * 
     * @return MultipCreditInfoService<T> [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    public <T extends CreditMultipLinked> CreditMultipLinkedService<T> getMultipService(
            Class<T> type) {
        return null;
    }
    
    /**
     * 获取1:1的信用信息业务层<br/>
     * <功能详细描述>
     * @param type
     * @return [参数说明]
     * 
     * @return SingleCreditInfoService<T> [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    public <T extends CreditSingleLinked> CreditSingleLinkedService<T> getSingleService(
            Class<T> type) {
        return null;
    }
}
