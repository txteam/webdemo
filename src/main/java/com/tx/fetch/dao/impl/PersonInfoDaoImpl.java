/*
 * 描          述:  <描述>
 * 修  改   人:  
 * 修改时间:  
 * <修改描述:>
 */
package com.tx.fetch.dao.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.tx.fetch.dao.PersonInfoDao;
import com.tx.fetch.model.PersonInfo;
import com.tx.core.mybatis.model.Order;
import com.tx.core.mybatis.support.MyBatisDaoSupport;
import com.tx.core.paged.model.PagedList;

/**
 * PersonInfo持久层
 * <功能详细描述>
 * 
 * @author  
 * @version  [版本号, 2012-12-11]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
@Component("personInfoDao")
public class PersonInfoDaoImpl implements PersonInfoDao {
    
    @Resource(name = "myBatisDaoSupport")
    private MyBatisDaoSupport myBatisDaoSupport;
    
    /**
     * @param condition
     */
    @Override
    public void batchInsertPersonInfo(List<PersonInfo> condition){
        this.myBatisDaoSupport.batchInsertUseUUID("personInfo.insertPersonInfo", condition, "id",true);
    }
    
    /**
     * @param condition
     */
    @Override
    public void batchUpdatePersonInfo(List<Map<String,Object>> updateRowMapList){
        this.myBatisDaoSupport.batchUpdate("personInfo.updatePersonInfo", updateRowMapList,true);
    }
    
    /**
     * @param condition
     */
    @Override
    public void insertPersonInfo(PersonInfo condition) {
        this.myBatisDaoSupport.insertUseUUID("personInfo.insertPersonInfo", condition, "id");
    }
    
    /**
     * @param condition
     * @return
     */
    @Override
    public int deletePersonInfo(PersonInfo condition) {
        return this.myBatisDaoSupport.delete("personInfo.deletePersonInfo", condition);
    }
    
    /**
     * @param condition
     * @return
     */
    @Override
    public PersonInfo findPersonInfo(PersonInfo condition) {
        return this.myBatisDaoSupport.<PersonInfo> find("personInfo.findPersonInfo", condition);
    }
    
    /**
     * @param params
     * @return
     */
    @Override
    public List<PersonInfo> queryPersonInfoList(Map<String, Object> params) {
        return this.myBatisDaoSupport.<PersonInfo> queryList("personInfo.queryPersonInfo",
                params);
    }
    
    /**
     * @param params
     * @param orderList
     * @return
     */
    @Override
    public List<PersonInfo> queryPersonInfoList(Map<String, Object> params,
            List<Order> orderList) {
        return this.myBatisDaoSupport.<PersonInfo> queryList("personInfo.queryPersonInfo",
                params,
                orderList);
    }
    
    /**
     * @param params
     * @return
     */
    @Override
    public int countPersonInfo(Map<String, Object> params) {
        return this.myBatisDaoSupport.<Integer> find("personInfo.queryPersonInfoCount",
                params);
    }
    
    /**
     * @param params
     * @param pageIndex
     * @param pageSize
     * @return
     */
    @Override
    public PagedList<PersonInfo> queryPersonInfoPagedList(Map<String, Object> params,
            int pageIndex, int pageSize) {
        return this.myBatisDaoSupport.<PersonInfo> queryPagedList("personInfo.queryPersonInfo",
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
    public PagedList<PersonInfo> queryPersonInfoPagedList(Map<String, Object> params,
            int pageIndex, int pageSize, List<Order> orderList) {
        return this.myBatisDaoSupport.<PersonInfo> queryPagedList("personInfo.queryPersonInfo",
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
    public int updatePersonInfo(Map<String, Object> updateRowMap) {
        return this.myBatisDaoSupport.update("personInfo.updatePersonInfo", updateRowMap);
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
