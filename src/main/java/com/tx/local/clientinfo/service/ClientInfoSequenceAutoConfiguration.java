/*
 * 描          述:  <描述>
 * 修  改   人:  PengQingyang
 * 修改时间:  2020年5月9日
 * <修改描述:>
 */
package com.tx.local.clientinfo.service;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.support.incrementer.DataFieldMaxValueIncrementer;

import com.tx.core.datasource.MysqlSequenceMaxValueIncrementer;

/**
 * <功能简述>
 * <功能详细描述>
 * 
 * @author  PengQingyang
 * @version  [版本号, 2020年5月9日]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
//core_sequece.sql
@Configuration
public class ClientInfoSequenceAutoConfiguration {
    
    private static final String SEQ_CLIENT_INFO_CODE_KEY = "seq_client_info_code";
    
    /**
     * 用内存缓存代替redis
     * <功能详细描述>
     * @return [参数说明]
     * 
     * @return CacheManager [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @Bean("clientinfo.sequence")
    public DataFieldMaxValueIncrementer clientInfoCodeSequence(
            DataSource dataSource) {
        MysqlSequenceMaxValueIncrementer s = new MysqlSequenceMaxValueIncrementer();
        s.setDataSource(dataSource);
        s.setIncrementerName(SEQ_CLIENT_INFO_CODE_KEY);
        s.setPaddingLength(6);
        return s;
    }
}
