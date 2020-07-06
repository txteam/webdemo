/*
 * 描          述:  <描述>
 * 修  改   人:  Administrator
 * 修改时间:  2020年1月14日
 * <修改描述:>
 */
package com.tx.local.creditinfo.dao;

import com.tx.core.mybatis.dao.MybatisBaseDao;
import com.tx.local.creditinfo.model.CreditLinked;

/**
 * 信用信息持久层<br/>
 * <功能详细描述>
 * 
 * @author  Administrator
 * @version  [版本号, 2020年1月14日]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public interface CreditLinkedDao<T extends CreditLinked>
        extends MybatisBaseDao<T, String> {
    
}
