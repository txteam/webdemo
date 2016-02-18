/*
 * 描          述:  <描述>
 * 修  改   人:  
 * 修改时间:  
 * <修改描述:>
 */
package com.tx.fetchhccredit.dao.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.tx.fetchhccredit.dao.HCLoanAccountDetailViewDao;
import com.tx.fetchhccredit.model.HCLoanAccountDetailView;
import com.tx.core.mybatis.model.Order;
import com.tx.core.mybatis.support.MyBatisDaoSupport;
import com.tx.core.paged.model.PagedList;

/**
 * HCLoanAccountDetailView持久层
 * <功能详细描述>
 * 
 * @author  
 * @version  [版本号, 2012-12-11]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
@Component("hCLoanAccountDetailViewDao")
public class HCLoanAccountDetailViewDaoImpl implements
        HCLoanAccountDetailViewDao {
    
    @Resource(name = "myBatisDaoSupport")
    private MyBatisDaoSupport myBatisDaoSupport;
    
    /** <默认构造函数> */
    public HCLoanAccountDetailViewDaoImpl(MyBatisDaoSupport myBatisDaoSupport) {
        super();
        this.myBatisDaoSupport = myBatisDaoSupport;
    }
    
    /** <默认构造函数> */
    public HCLoanAccountDetailViewDaoImpl() {
        super();
    }
    
    /**
     * @param condition
     */
    @Override
    public void batchInsertHCLoanAccountDetailView(
            List<HCLoanAccountDetailView> condition) {
        this.myBatisDaoSupport.batchInsertUseUUID("hCLoanAccountDetailView.insertHCLoanAccountDetailView",
                condition,
                "id",
                true);
    }
    
    /**
     * @param condition
     */
    @Override
    public void batchUpdateHCLoanAccountDetailView(
            List<Map<String, Object>> updateRowMapList) {
        this.myBatisDaoSupport.batchUpdate("hCLoanAccountDetailView.updateHCLoanAccountDetailView",
                updateRowMapList,
                true);
    }
    
    /**
     * @param condition
     */
    @Override
    public void insertHCLoanAccountDetailView(HCLoanAccountDetailView condition) {
        this.myBatisDaoSupport.insertUseUUID("hCLoanAccountDetailView.insertHCLoanAccountDetailView",
                condition,
                "id");
    }
    
    /**
     * @param condition
     * @return
     */
    @Override
    public int deleteHCLoanAccountDetailView(HCLoanAccountDetailView condition) {
        return this.myBatisDaoSupport.delete("hCLoanAccountDetailView.deleteHCLoanAccountDetailView",
                condition);
    }
    
    /**
     * @param condition
     * @return
     */
    @Override
    public HCLoanAccountDetailView findHCLoanAccountDetailView(
            HCLoanAccountDetailView condition) {
        return this.myBatisDaoSupport.<HCLoanAccountDetailView> find("hCLoanAccountDetailView.findHCLoanAccountDetailView",
                condition);
    }
    
    /**
     * @param params
     * @return
     */
    @Override
    public List<HCLoanAccountDetailView> queryHCLoanAccountDetailViewList(
            Map<String, Object> params) {
        return this.myBatisDaoSupport.<HCLoanAccountDetailView> queryList("hCLoanAccountDetailView.queryHCLoanAccountDetailView",
                params);
    }
    
    /**
     * @param params
     * @param orderList
     * @return
     */
    @Override
    public List<HCLoanAccountDetailView> queryHCLoanAccountDetailViewList(
            Map<String, Object> params, List<Order> orderList) {
        return this.myBatisDaoSupport.<HCLoanAccountDetailView> queryList("hCLoanAccountDetailView.queryHCLoanAccountDetailView",
                params,
                orderList);
    }
    
    /**
     * @param params
     * @return
     */
    @Override
    public int countHCLoanAccountDetailView(Map<String, Object> params) {
        return this.myBatisDaoSupport.<Integer> find("hCLoanAccountDetailView.queryHCLoanAccountDetailViewCount",
                params);
    }
    
    /**
     * @param params
     * @param pageIndex
     * @param pageSize
     * @return
     */
    @Override
    public PagedList<HCLoanAccountDetailView> queryHCLoanAccountDetailViewPagedList(
            Map<String, Object> params, int pageIndex, int pageSize) {
        return this.myBatisDaoSupport.<HCLoanAccountDetailView> queryPagedList("hCLoanAccountDetailView.queryHCLoanAccountDetailView",
                params,
                pageIndex,
                pageSize);
    }
    
    /**
     * @param params
     * @param pageIndex
     * @param pageSize
     * @param orderList
     * @return
     */
    @Override
    public PagedList<HCLoanAccountDetailView> queryHCLoanAccountDetailViewPagedList(
            Map<String, Object> params, int pageIndex, int pageSize,
            List<Order> orderList) {
        return this.myBatisDaoSupport.<HCLoanAccountDetailView> queryPagedList("hCLoanAccountDetailView.queryHCLoanAccountDetailView",
                params,
                pageIndex,
                pageSize,
                orderList);
    }
    
    /**
     * @param updateRowMap
     * @return
     */
    @Override
    public int updateHCLoanAccountDetailView(Map<String, Object> updateRowMap) {
        return this.myBatisDaoSupport.update("hCLoanAccountDetailView.updateHCLoanAccountDetailView",
                updateRowMap);
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
