/*
 * 描          述:  <描述>
 * 修  改   人:  Administrator
 * 修改时间:  2020年1月14日
 * <修改描述:>
 */
package com.tx.local.creditinfo.service.impl;

import java.util.List;

import org.springframework.beans.factory.InitializingBean;

import com.tx.core.querier.model.Querier;
import com.tx.local.creditinfo.context.CreditInfo;
import com.tx.local.creditinfo.dao.CreditInfoDao;
import com.tx.local.creditinfo.service.MultipCreditInfoService;

/**
 * <功能简述>
 * <功能详细描述>
 * 
 * @author  Administrator
 * @version  [版本号, 2020年1月14日]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public class MultipCreditInfoServiceImpl<T extends CreditInfo>
        implements MultipCreditInfoService<T>, InitializingBean {
    
    private Class<T> type;
    
    private CreditInfoDao<T> trunkDao;
    
    private CreditInfoDao<T> branchDao;
    
    private CreditInfoDao<T> tagDao;
    
    /**
     * @throws Exception
     */
    @Override
    public void afterPropertiesSet() throws Exception {
        // TODO Auto-generated method stub
    }
    
    /**
     * @return
     */
    @Override
    public Class<T> type() {
        return this.type;
    }
    
    /**
     * @param creditInfoId
     * @param version
     * @param querier
     * @return
     */
    public List<T> queryFromTrunk(String creditInfoId, Querier querier) {
        return null;
    }
    
    /**
     * @param creditInfoId
     * @param version
     * @param querier
     * @return
     */
    @Override
    public List<T> queryFromBranch(String creditInfoId, int version,
            Querier querier) {
        // TODO Auto-generated method stub
        return null;
    }
    
    /**
     * @param creditInfoId
     * @return
     */
    @Override
    public List<Integer> listVersionsFromBranch(String creditInfoId) {
        // TODO Auto-generated method stub
        return null;
    }
    
    /**
     * @param infos
     */
    public void tag(String branchVersion, String tagVersion) {
        // TODO Auto-generated method stub
        
    }
    
    /**
     * @param creditInfoId
     * @return
     */
    @Override
    public List<Integer> listVersionsFromTag(String creditInfoId) {
        // TODO Auto-generated method stub
        return null;
    }
    
    /**
     * @param creditInfoId
     * @param version
     * @param querier
     * @return
     */
    @Override
    public List<T> queryFromTag(String creditInfoId, int version,
            Querier querier) {
        // TODO Auto-generated method stub
        return null;
    }
}
