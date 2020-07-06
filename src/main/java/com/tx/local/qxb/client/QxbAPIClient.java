/*
 * 描          述:  <描述>
 * 修  改   人:  PengQingyang
 * 修改时间:  2020年6月11日
 * <修改描述:>
 */
package com.tx.local.qxb.client;

import com.tx.local.qxb.model.QxbEnterpriseInfo;

/**
 * 企信宝API客户端<br/>
 * <功能详细描述>
 * 
 * @author  PengQingyang
 * @version  [版本号, 2020年6月11日]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public interface QxbAPIClient {
    
    //    /**
    //     * 工商实时信息<br/>
    //     * /enterprise/getDetailByNameOnline
    //     * <功能详细描述>
    //     * @param name [参数说明]
    //     * 
    //     * @return void [返回类型说明]
    //     * @exception throws [异常类型] [异常说明]
    //     * @see [类、类#方法、类#成员]
    //     */
    //    public void getDetailByNameOnline(String name);
    
    /**
     * 工商报告<br/>
     * <功能详细描述>
     * @param name
     * @return [参数说明]
     * 
     * @return QxbEnterpriseInfo [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    public QxbEnterpriseInfo getDetailAndContactByName(String name);
}
