/*
 * 描          述:  <描述>
 * 修  改   人:  
 * 修改时间:  
 * <修改描述:>
 */
package com.tx.component.operator.dao.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.tx.component.operator.dao.RoleDao;
import com.tx.component.operator.model.Role;
import com.tx.core.mybatis.model.Order;
import com.tx.core.mybatis.support.MyBatisDaoSupport;
import com.tx.core.paged.model.PagedList;

/**
 * Role持久层
 * <功能详细描述>
 * 
 * @author  
 * @version  [版本号, 2012-12-11]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
@Component("roleDao")
public class RoleDaoImpl implements RoleDao {
    
    @Resource(name = "myBatisDaoSupport")
    private MyBatisDaoSupport myBatisDaoSupport;
    
    /**
     * @param condition
     */
    @Override
    public void insertRoleToHis(Role condition) {
        this.myBatisDaoSupport.insert("role.insertRoleToHis", condition);
    }
    
    /**
     * @param condition
     */
    @Override
    public void insert(Role condition) {
        this.myBatisDaoSupport.insertUseUUID("role.insertRole", condition, "id");
    }
    
    /**
     * @param condition
     * @return
     */
    @Override
    public int delete(Role condition) {
        return this.myBatisDaoSupport.delete("role.deleteRole", condition);
    }
    
    /**
     * @param condition
     * @return
     */
    @Override
    public Role find(Role condition) {
        return this.myBatisDaoSupport.<Role> find("role.findRole", condition);
    }
    
    /**
     * @param params
     * @return
     */
    @Override
    public List<Role> queryList(Map<String, Object> params) {
        return this.myBatisDaoSupport.<Role> queryList("role.queryRole", params);
    }
    
    /**
     * @param params
     * @param orderList
     * @return
     */
    @Override
    public List<Role> queryRoleList(Map<String, Object> params,
            List<Order> orderList) {
        return this.myBatisDaoSupport.<Role> queryList("role.queryRole",
                params,
                orderList);
    }
    
    /**
     * @param params
     * @return
     */
    @Override
    public int countRole(Map<String, Object> params) {
        return this.myBatisDaoSupport.<Integer> find("role.queryRoleCount",
                params);
    }
    
    /**
     * @param params
     * @param pageIndex
     * @param pageSize
     * @return
     */
    @Override
    public PagedList<Role> queryRolePagedList(Map<String, Object> params,
            int pageIndex, int pageSize) {
        return this.myBatisDaoSupport.<Role> queryPagedList("role.queryRole",
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
    public PagedList<Role> queryRolePagedList(Map<String, Object> params,
            int pageIndex, int pageSize, List<Order> orderList) {
        return this.myBatisDaoSupport.<Role> queryPagedList("role.queryRole",
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
    public int updateRole(Map<String, Object> updateRowMap) {
        return this.myBatisDaoSupport.update("role.updateRole", updateRowMap);
    }
}
