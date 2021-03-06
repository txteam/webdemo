/*
 * 描          述:  <描述>
 * 修  改   人:  
 * 修改时间:  
 * <修改描述:>
 */
package com.tx.local.operator.dao.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.tx.core.mybatis.dao.impl.MybatisBaseDaoImpl;
import com.tx.core.mybatis.support.MyBatisDaoSupport;
import com.tx.local.operator.dao.OperatorRoleDao;
import com.tx.local.operator.model.OperatorRole;

/**
 * OperatorRole持久层
 * <功能详细描述>
 * 
 * @author  
 * @version [版本号]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
@Component("operatorRoleDao")
public class OperatorRoleDaoImpl extends
        MybatisBaseDaoImpl<OperatorRole, String> implements OperatorRoleDao {
    
    @Resource(name = "myBatisDaoSupport")
    private MyBatisDaoSupport myBatisDaoSupport;
    
    /**
     * @param entity
     */
    @Override
    public void insertToHis(OperatorRole operatorRole) {
        this.myBatisDaoSupport.insert("operatorRole.insertToHis", operatorRole);
    }
    
    /**
     * @return 返回 myBatisDaoSupport
     */
    public MyBatisDaoSupport getMyBatisDaoSupport() {
        return myBatisDaoSupport;
    }
    
    /**
     * @param 对myBatisDaoSupport进行赋值
     */
    public void setMyBatisDaoSupport(MyBatisDaoSupport myBatisDaoSupport) {
        this.myBatisDaoSupport = myBatisDaoSupport;
    }
}
