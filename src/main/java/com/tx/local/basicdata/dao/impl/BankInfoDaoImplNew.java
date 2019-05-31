/*
 * 描          述:  <描述>
 * 修  改   人:  
 * 修改时间:  
 * <修改描述:>
 */
package com.tx.local.basicdata.dao.impl;

import com.tx.local.base.BaseDaoImpl;
import com.tx.local.basicdata.dao.BankInfoDaoNew;
import com.tx.local.basicdata.model.BankInfo;
import org.springframework.stereotype.Repository;

/**
 * BankInfo持久层
 * <功能详细描述>
 * 
 * @author  
 * @version  [版本号, ]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
@Repository
public class BankInfoDaoImplNew extends BaseDaoImpl<BankInfo> implements BankInfoDaoNew {

    @Override
    public String entityName() {
        return "bankInfo";
    }


}
