/*
 * 描          述:  <描述>
 * 修  改   人:  
 * 修改时间:  
 * <修改描述:>
 */
package com.tx.local.operator.dao.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.tx.core.mybatis.dao.impl.MybatisBaseDaoImpl;
import com.tx.core.mybatis.support.MyBatisDaoSupport;
import com.tx.local.operator.dao.OperatorRefItemDao;
import com.tx.local.operator.model.OperatorRefItem;

/**
 * OperatorRefItem持久层
 * <功能详细描述>
 * 
 * @author  
 * @version [版本号]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
@Component("operatorRefItemDao")
public class OperatorRefItemDaoImpl
        extends MybatisBaseDaoImpl<OperatorRefItem, String>
        implements OperatorRefItemDao {
    
    @Resource(name = "myBatisDaoSupport")
    private MyBatisDaoSupport myBatisDaoSupport;
    
    /**
     * @param operatorRef
     */
    @Override
    public void insertToHis(OperatorRefItem operatorRef) {
        this.myBatisDaoSupport.insert("operatorRefItem.insertToHis",
                operatorRef);
    }
    
    /**
     * @param operatorRefs
     */
    @Override
    public void batchInsertToHis(List<OperatorRefItem> operatorRefs) {
        this.myBatisDaoSupport.batchInsert("operatorRefItem.insertToHis",
                operatorRefs,
                false);
    }
    
    /**
     * @param entityList
     */
    @Override
    public void batchInsert(List<OperatorRefItem> entityList) {
        this.myBatisDaoSupport.batchInsertUseUUID("operatorRefItem.insert",
                entityList,
                "id",
                false);
    }
    
    /**
     * @param entityList
     */
    @Override
    public void batchDelete(List<OperatorRefItem> entityList) {
        this.myBatisDaoSupport.batchDelete("operatorRefItem.delete",
                entityList,
                false);
    }
    
    /**
     * @param updateEntityMapList
     */
    @Override
    public void batchUpdate(List<Map<String, Object>> updateEntityMapList) {
        this.myBatisDaoSupport.batchUpdate("operatorRefItem.update",
                updateEntityMapList,
                false);
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
