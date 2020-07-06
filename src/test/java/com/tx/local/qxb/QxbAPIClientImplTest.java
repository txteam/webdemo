/*
 * 描          述:  <描述>
 * 修  改   人:  PengQingyang
 * 修改时间:  2020年6月11日
 * <修改描述:>
 */
package com.tx.local.qxb;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.client.RestTemplateAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit4.SpringRunner;

import com.tx.local.qxb.client.QxbAPIClient;
import com.tx.local.qxb.client.impl.QxbAPIClientImpl;

/**
 * 企信宝接口测试<br/>
 * <功能详细描述>
 * 
 * @author  PengQingyang
 * @version  [版本号, 2020年6月11日]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
@RunWith(SpringRunner.class)
@Import({ RestTemplateAutoConfiguration.class })
@SpringBootTest(classes = QxbAPIClientImpl.class)
public class QxbAPIClientImplTest {
    
    @Autowired
    private QxbAPIClient client;
    
    //测试插入
    //    @Test
    //    public void testGetDetailByNameOnline() {
    //        try {
    //            this.client.getDetailByNameOnline("重庆添馨网络科技有限公司");
    //        } catch (Exception e) {
    //            // TODO Auto-generated catch block
    //            e.printStackTrace();
    //        }
    //    }
    
    /**
     * 测试
     * <功能详细描述> [参数说明]
     * 
     * @return void [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @Test
    public void testGetDetailByNameOnline() {
        try {
            this.client.getDetailAndContactByName("重庆添馨网络科技有限公司");
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
