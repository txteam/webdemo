/*
 * 描          述:  <描述>
 * 修  改   人:  
 * 修改时间:  
 * <修改描述:>
 */
package com.tx.local.basicdata.dao.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.tx.core.mybatis.support.MyBatisDaoSupport;
import com.tx.core.paged.model.PagedList;
import com.tx.local.basicdata.dao.BankInfoDao;
import com.tx.local.basicdata.model.BankInfo;

/**
 * BankInfo持久层
 * <功能详细描述>
 * 
 * @author  
 * @version  [版本号, 2012-12-11]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
@Component("bankInfoDao")
public class BankInfoDaoImpl implements BankInfoDao {
    
    @Resource(name = "myBatisDaoSupport")
    private MyBatisDaoSupport myBatisDaoSupport;
    
    /**
     * @param condition
     */
    @Override
    public void batchInsert(List<BankInfo> condition) {
        this.myBatisDaoSupport.batchInsertUseUUID("bankInfo.insertBankInfo",
                condition,
                "id",
                true);
    }
    
    /**
     * @param condition
     */
    @Override
    public void batchUpdate(List<Map<String, Object>> updateRowMapList) {
        this.myBatisDaoSupport.batchUpdate("bankInfo.updateBankInfo",
                updateRowMapList,
                true);
    }
    
    /**
     * @param condition
     */
    @Override
    public void insert(BankInfo condition) {
        this.myBatisDaoSupport.insertUseUUID("bankInfo.insertBankInfo",
                condition,
                "id");
    }
    
    /**
     * @param condition
     * @return
     */
    @Override
    public int delete(BankInfo condition) {
        return this.myBatisDaoSupport.delete("bankInfo.deleteBankInfo",
                condition);
    }
    
    /**
     * @param condition
     * @return
     */
    @Override
    public BankInfo find(BankInfo condition) {
        return this.myBatisDaoSupport.<BankInfo> find("bankInfo.findBankInfo",
                condition);
    }
    
    /**
     * @param params
     * @return
     */
    @Override
    public List<BankInfo> queryList(Map<String, Object> params) {
        return this.myBatisDaoSupport
                .<BankInfo> queryList("bankInfo.queryBankInfo", params);
    }
    
    /**
     * @param params
     * @return
     */
    @Override
    public int count(Map<String, Object> params) {
        return this.myBatisDaoSupport
                .<Integer> find("bankInfo.queryBankInfoCount", params);
    }
    
    /**
     * @param params
     * @param pageIndex
     * @param pageSize
     * @return
     */
    @Override
    public PagedList<BankInfo> queryPagedList(Map<String, Object> params,
            int pageIndex, int pageSize) {
        return this.myBatisDaoSupport.<BankInfo> queryPagedList(
                "bankInfo.queryBankInfo", params, pageIndex, pageSize);
    }
    
    /**
     * @param updateRowMap
     * @return
     */
    @Override
    public int update(Map<String, Object> updateRowMap) {
        return this.myBatisDaoSupport.update("bankInfo.updateBankInfo",
                updateRowMap);
    }
}
