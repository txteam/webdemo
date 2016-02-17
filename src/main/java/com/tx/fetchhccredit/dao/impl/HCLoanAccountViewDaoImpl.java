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

import com.tx.fetchhccredit.dao.HCLoanAccountViewDao;
import com.tx.fetchhccredit.model.HCLoanAccountView;
import com.tx.core.mybatis.model.Order;
import com.tx.core.mybatis.support.MyBatisDaoSupport;
import com.tx.core.paged.model.PagedList;

/**
 * HCLoanAccountView持久层
 * <功能详细描述>
 * 
 * @author  
 * @version  [版本号, 2012-12-11]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
@Component("hCLoanAccountViewDao")
public class HCLoanAccountViewDaoImpl implements HCLoanAccountViewDao {
    
    @Resource(name = "myBatisDaoSupport")
    private MyBatisDaoSupport myBatisDaoSupport;
    
    /**
     * @param condition
     */
    @Override
    public void batchInsertHCLoanAccountView(List<HCLoanAccountView> condition){
        this.myBatisDaoSupport.batchInsertUseUUID("hCLoanAccountView.insertHCLoanAccountView", condition, "id",true);
    }
    
    /**
     * @param condition
     */
    @Override
    public void batchUpdateHCLoanAccountView(List<Map<String,Object>> updateRowMapList){
        this.myBatisDaoSupport.batchUpdate("hCLoanAccountView.updateHCLoanAccountView", updateRowMapList,true);
    }
    
    /**
     * @param condition
     */
    @Override
    public void insertHCLoanAccountView(HCLoanAccountView condition) {
        this.myBatisDaoSupport.insertUseUUID("hCLoanAccountView.insertHCLoanAccountView", condition, "id");
    }
    
    /**
     * @param condition
     * @return
     */
    @Override
    public int deleteHCLoanAccountView(HCLoanAccountView condition) {
        return this.myBatisDaoSupport.delete("hCLoanAccountView.deleteHCLoanAccountView", condition);
    }
    
    /**
     * @param condition
     * @return
     */
    @Override
    public HCLoanAccountView findHCLoanAccountView(HCLoanAccountView condition) {
        return this.myBatisDaoSupport.<HCLoanAccountView> find("hCLoanAccountView.findHCLoanAccountView", condition);
    }
    
    /**
     * @param params
     * @return
     */
    @Override
    public List<HCLoanAccountView> queryHCLoanAccountViewList(Map<String, Object> params) {
        return this.myBatisDaoSupport.<HCLoanAccountView> queryList("hCLoanAccountView.queryHCLoanAccountView",
                params);
    }
    
    /**
     * @param params
     * @param orderList
     * @return
     */
    @Override
    public List<HCLoanAccountView> queryHCLoanAccountViewList(Map<String, Object> params,
            List<Order> orderList) {
        return this.myBatisDaoSupport.<HCLoanAccountView> queryList("hCLoanAccountView.queryHCLoanAccountView",
                params,
                orderList);
    }
    
    /**
     * @param params
     * @return
     */
    @Override
    public int countHCLoanAccountView(Map<String, Object> params) {
        return this.myBatisDaoSupport.<Integer> find("hCLoanAccountView.queryHCLoanAccountViewCount",
                params);
    }
    
    /**
     * @param params
     * @param pageIndex
     * @param pageSize
     * @return
     */
    @Override
    public PagedList<HCLoanAccountView> queryHCLoanAccountViewPagedList(Map<String, Object> params,
            int pageIndex, int pageSize) {
        return this.myBatisDaoSupport.<HCLoanAccountView> queryPagedList("hCLoanAccountView.queryHCLoanAccountView",
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
    public PagedList<HCLoanAccountView> queryHCLoanAccountViewPagedList(Map<String, Object> params,
            int pageIndex, int pageSize, List<Order> orderList) {
        return this.myBatisDaoSupport.<HCLoanAccountView> queryPagedList("hCLoanAccountView.queryHCLoanAccountView",
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
    public int updateHCLoanAccountView(Map<String, Object> updateRowMap) {
        return this.myBatisDaoSupport.update("hCLoanAccountView.updateHCLoanAccountView", updateRowMap);
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
