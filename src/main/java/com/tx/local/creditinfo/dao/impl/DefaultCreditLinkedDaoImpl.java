/*
 * 描          述:  <描述>
 * 修  改   人:  Administrator
 * 修改时间:  2020年1月14日
 * <修改描述:>
 */
package com.tx.local.creditinfo.dao.impl;

import com.tx.core.exceptions.util.AssertUtils;
import com.tx.core.mybatis.dao.impl.MybatisBaseDaoImpl;
import com.tx.core.mybatis.support.MyBatisDaoSupport;
import com.tx.local.creditinfo.dao.CreditLinkedDao;
import com.tx.local.creditinfo.model.CreditLinked;

/**
 * 默认的信用信息持久层<br/>
 * <功能详细描述>
 * 
 * @author  Administrator
 * @version  [版本号, 2020年1月14日]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public class DefaultCreditLinkedDaoImpl<T extends CreditLinked>
        extends MybatisBaseDaoImpl<T, String> implements CreditLinkedDao<T> {
    
    /** mybatis持久层句柄 */
    private MyBatisDaoSupport myBatisDaoSupport;
    
    /** <默认构造函数> */
    public DefaultCreditLinkedDaoImpl(Class<T> entityType,
            MyBatisDaoSupport myBatisDaoSupport) {
        super(entityType, String.class);
        
        this.myBatisDaoSupport = myBatisDaoSupport;
    }
    
    /**
     * 
     */
    @Override
    public void afterPropertiesSet() {
        AssertUtils.notNull(getMyBatisDaoSupport(),
                "myBatisDaoSupport is null.");
        AssertUtils.notNull(this.entityType, "entityType is null.");
        AssertUtils.notNull(this.pkPropertyType, "pkPropertyType is null.");
        
        this.assistant = new CreditLinkedMapperBuilderAssistant(
                getMyBatisDaoSupport().getConfiguration(), this.entityType);
        AssertUtils.isTrue(
                this.pkPropertyType.isAssignableFrom(
                        this.assistant.getPkColumn().getPropertyType()),
                "pkPropertyType:[{}] is not assignable from actual pkPropertyType:[{}]",
                new Object[] { this.pkPropertyType,
                        this.assistant.getPkColumn().getPropertyType() });
        
        this.assistant.registe();
    }
    
    /**
     * @return
     */
    @Override
    public MyBatisDaoSupport getMyBatisDaoSupport() {
        return this.myBatisDaoSupport;
    }
}
