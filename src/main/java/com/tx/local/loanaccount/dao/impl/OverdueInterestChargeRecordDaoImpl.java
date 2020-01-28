/*
 * 描          述:  <描述>
 * 修  改   人:  
 * 修改时间:  
 * <修改描述:>
 */
package com.tx.local.loanaccount.dao.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.tx.core.mybatis.dao.impl.MybatisBaseDaoImpl;
import com.tx.core.mybatis.support.MyBatisDaoSupport;
import com.tx.local.loanaccount.dao.OverdueInterestChargeRecordDao;
import com.tx.local.loanaccount.model.OverdueInterestChargeRecord;

/**
 * OverdueInterestChargeRecord持久层
 * <功能详细描述>
 * 
 * @author  
 * @version [版本号]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
@Component("overdueInterestChargeRecordDao")
public class OverdueInterestChargeRecordDaoImpl
        extends MybatisBaseDaoImpl<OverdueInterestChargeRecord, String>
        implements OverdueInterestChargeRecordDao {
    
    @Resource(name = "myBatisDaoSupport")
    private MyBatisDaoSupport myBatisDaoSupport;
    
    /**
     * @param oicr
     */
    @Override
    public void insertToHis(OverdueInterestChargeRecord oicr) {
        this.myBatisDaoSupport.insert("overdueInterestChargeRecord.insertToHis",
                oicr);
    }
    
    /**
     * @param oicrs
     */
    @Override
    public void batchInsertToHis(List<OverdueInterestChargeRecord> oicrs) {
        this.myBatisDaoSupport
                .batchInsert("overdueInterestChargeRecord.insertToHis", oicrs);
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
