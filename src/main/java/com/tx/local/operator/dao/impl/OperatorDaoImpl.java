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
import com.tx.local.operator.dao.OperatorDao;
import com.tx.local.operator.model.Operator;

/**
 * Operator持久层
 * <功能详细描述>
 * 
 * @author  
 * @version [版本号]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
@Component("operatorDao")
public class OperatorDaoImpl extends MybatisBaseDaoImpl<Operator, String>
        implements OperatorDao {
    
    @Resource(name = "myBatisDaoSupport")
    private MyBatisDaoSupport myBatisDaoSupport;
    
    /**
     * @param entity
     */
    @Override
    public void insertToHis(Operator operatorRole) {
        this.myBatisDaoSupport.insert("operator.insertToHis", operatorRole);
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
