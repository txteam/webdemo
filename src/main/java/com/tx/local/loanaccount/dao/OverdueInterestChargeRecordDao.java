/*
 * 描          述:  <描述>
 * 修  改   人:  
 * 修改时间:  
 * <修改描述:>
 */
package com.tx.local.loanaccount.dao;

import java.util.List;

import com.tx.core.mybatis.dao.MybatisBaseDao;
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
public interface OverdueInterestChargeRecordDao
        extends MybatisBaseDao<OverdueInterestChargeRecord, String> {
    
    /**
     * 逾期利息计费记录插入历史表<br/>
     * <功能详细描述>
     * @param overRepayRecord [参数说明]
     * 
     * @return void [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    public void insertToHis(OverdueInterestChargeRecord oicr);
    
    /**
     * 逾期利息计费集合插入历史表<br/>
     * <功能详细描述>
     * @param overRepayRecords [参数说明]
     * 
     * @return void [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    public void batchInsertToHis(List<OverdueInterestChargeRecord> oicrs);
}
