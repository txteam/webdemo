/*
 * 描          述:  <描述>
 * 修  改   人:  
 * 修改时间:  
 * <修改描述:>
 */
package com.tx.component.operator.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.EnumUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import auth.AuthContextUtils;

import com.tx.component.mainframe.servicelog.SystemOperateLog;
import com.tx.component.operator.OperatorConstants;
import com.tx.component.operator.dao.RoleDao;
import com.tx.component.operator.model.Role;
import com.tx.component.operator.model.RoleEnum;
import com.tx.component.operator.model.RoleTypeEnum;
import com.tx.component.operator.model.VirtualCenter;
import com.tx.component.servicelog.context.ServiceLoggerContext;
import com.tx.core.exceptions.util.AssertUtils;
import com.tx.core.util.MessageUtils;

/**
 * Role的业务层<br/>
 * <功能详细描述>
 * 
 * @author  
 * @version  [版本号, ]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
@Component("roleService")
public class RoleService implements InitializingBean {
    
    @SuppressWarnings("unused")
    private Logger logger = LoggerFactory.getLogger(RoleService.class);
    
    @Resource(name = "roleDao")
    private RoleDao roleDao;
    
    @Resource(name = "operatorRefService")
    private OperatorRefService operatorRefService;
    
    @Resource(name = "virtualCenterService")
    private VirtualCenterService virtualCenterService;
    
    /**
     * @throws Exception
     */
    @Override
    public void afterPropertiesSet() throws Exception {
        List<Role> roleList = this.queryList(null, null, null);
        
        Map<String, RoleEnum> roleEnumMap = EnumUtils.<RoleEnum> getEnumMap(RoleEnum.class);
        
        Map<RoleEnum, Role> resMap = new HashMap<RoleEnum, Role>();
        for (Role roleTemp : roleList) {
            if (roleTemp.getRoleKey() == null) {
                continue;
            }
            resMap.put(roleTemp.getRoleKey(), roleTemp);
        }
        
        for (RoleEnum roleKeyTemp : roleEnumMap.values()) {
            if (resMap.containsKey(roleKeyTemp)) {
                continue;
            }
            
            //将新的虚中心插入表中
            Role role = new Role();
            role.setName(roleKeyTemp.getName());
            role.setRoleKey(roleKeyTemp);
            role.setRoleType(roleKeyTemp.getRoleType());
            role.setEditAble(false);
            role.setValid(true);
            role.setCode(roleKeyTemp.toString());
            role.setRemark(roleKeyTemp.getName());
            if (roleKeyTemp.getVirtualCenterKey() != null) {
                VirtualCenter vc = this.virtualCenterService.findVirtualCenterByKey(roleKeyTemp.getVirtualCenterKey());
                role.setVcid(vc.getId());
            }
            this.insert(role);
        }
    }
    
    /**
      * 将role实例插入数据库中保存
      * 1、如果role为空时抛出参数为空异常
      * 2、如果role中部分必要参数为非法值时抛出参数不合法异常
     * <功能详细描述>
     * @param role [参数说明]
     * 
     * @return void [返回类型说明]
     * @exception throws
     * @see [类、类#方法、类#成员]
    */
    @Transactional
    public void insert(Role role) {
        AssertUtils.notNull(role, "role is null.");
        AssertUtils.notEmpty(role.getName(), "role.name is empty.");
        
        role.setValid(true);
        
        //调用数据持久层对实体进行持久化操作
        this.roleDao.insert(role);
        
        //记录操作日志
        ServiceLoggerContext.getLogger(SystemOperateLog.class)
                .log(new SystemOperateLog("wtms", "新增角色",
                        MessageUtils.format("新增角色[{}]",
                                new Object[] { role.getName() }), null));
    }
    
    /**
     * 根据id删除role实例
     * 1、如果入参数为空，则抛出异常
     * 2、执行删除后，将返回数据库中被影响的条数
     * @param id
     * @return 返回删除的数据条数，<br/>
     * 有些业务场景，如果已经被别人删除同样也可以认为是成功的
     * 这里讲通用生成的业务层代码定义为返回影响的条数
     * @return int [返回类型说明]
     * @exception throws 
     * @see [类、类#方法、类#成员]
    */
    @Transactional
    public boolean deleteById(String id) {
        AssertUtils.notEmpty(id, "id is empty.");
        AssertUtils.isTrue(isEditAble(id), "role:{} is not editAble", id);
        AssertUtils.isTrue(isDeleteOrDisableAble(id),
                "role:{} is not delete able.maybe has ref to operator",
                id);
        
        Role role = findById(id);
        this.roleDao.insertRoleToHis(role);
        
        //记录操作日志
        ServiceLoggerContext.getLogger(SystemOperateLog.class)
                .log(new SystemOperateLog("wtms", "删除角色",
                        MessageUtils.format("删除角色[{}]",
                                new Object[] { role.getName() }), null));
        
        Role condition = new Role();
        condition.setId(id);
        int resInt = this.roleDao.delete(condition);
        return resInt > 0;
    }
    
    /**
      * 是否可删除<br/>
      * <功能详细描述>
      * @param roleId
      * @return [参数说明]
      * 
      * @return boolean [返回类型说明]
      * @exception throws [异常类型] [异常说明]
      * @see [类、类#方法、类#成员]
     */
    public boolean isDeleteOrDisableAble(String roleId) {
        AssertUtils.notEmpty(roleId, "roleId is empty.");
        
        Set<String> operatorIdSet = this.operatorRefService.queryOperatorIdSetByRefId(OperatorConstants.OPERATORREF_TYPE_ROLE,
                roleId);
        if (CollectionUtils.isEmpty(operatorIdSet)) {
            return true;
        } else {
            return false;
        }
    }
    
    /**
      * 根据Id查询Role实体
      * 1、当id为empty时抛出异常
      * <功能详细描述>
      * @param id
      * @return [参数说明]
      * 
      * @return Role [返回类型说明]
      * @exception throws 可能存在数据库访问异常DataAccessException
      * @see [类、类#方法、类#成员]
     */
    public Role findById(String id) {
        AssertUtils.notEmpty(id, "id is empty.");
        
        Role condition = new Role();
        condition.setId(id);
        
        Role res = this.roleDao.find(condition);
        return res;
    }
    
    /**
     * 查询Role实体列表
     *     不包含无效的实体
     * <功能详细描述>
     * @param vcid
     * @param organizationId
     * @param code
     * @param name
     *       
     * @return [参数说明]
     * 
     * @return List<Role> [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    public List<Role> queryList(Boolean valid, RoleTypeEnum roleType,
            String excludeRoleId) {
        //生成查询条件
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("valid", valid);
        params.put("roleType", roleType);
        params.put("excludeRoleId", excludeRoleId);
        
        //根据实际情况，填入排序字段等条件，根据是否需要排序，选择调用dao内方法
        List<Role> resList = this.roleDao.queryList(params);
        
        return resList;
    }
    
    /**
      * 查询Role实体列表
      *     不包含无效的实体
      * <功能详细描述>
      * @param vcid
      * @param organizationId
      * @param code
      * @param name
      *       
      * @return [参数说明]
      * 
      * @return List<Role> [返回类型说明]
      * @exception throws [异常类型] [异常说明]
      * @see [类、类#方法、类#成员]
      */
    public List<Role> queryList(String vcid,String name,  String code,
            Boolean valid, RoleTypeEnum roleType, String excludeRoleId) {
        //生成查询条件
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("vcid", vcid);
        params.put("name", name);
        params.put("code", code);
        params.put("valid", valid);
        params.put("roleType", roleType);
        params.put("excludeRoleId", excludeRoleId);
        
        //根据实际情况，填入排序字段等条件，根据是否需要排序，选择调用dao内方法
        List<Role> resList = this.roleDao.queryList(params);
        
        return resList;
    }
    
    /**
     * 查询Role实体列表
     *     不包含无效的实体
     * <功能详细描述>
     * @param vcid
     * @param organizationId
     * @param code
     * @param name
     *       
     * @return [参数说明]
     * 
     * @return List<Role> [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    public List<Role> queryRoleListByAuth(String authKey, Boolean valid,
            RoleTypeEnum roleType, String excludeRoleId) {
        AssertUtils.notEmpty(authKey, "authKey is empty.");
        
        AuthContextUtils.setQueryByAuth("vcid", authKey);
        
        //生成查询条件
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("valid", valid);
        params.put("roleType", roleType);
        params.put("excludeRoleId", excludeRoleId);
        
        //根据实际情况，填入排序字段等条件，根据是否需要排序，选择调用dao内方法
        List<Role> resList = this.roleDao.queryList(params);
        
        AuthContextUtils.clearQueryByAuth();
        
        return resList;
    }
    
    /**
     * 查询Role实体列表
     *     不包含无效的实体
     * <功能详细描述>
     * @param vcid
     * @param organizationId
     * @param code
     * @param name
     *       
     * @return [参数说明]
     * 
     * @return List<Role> [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    public List<Role> queryRoleListByAuth(String authKey, String vcid,
            String name, String code, Boolean valid, RoleTypeEnum roleType,
            String excludeRoleId) {
        AssertUtils.notEmpty(authKey, "authKey is empty.");
        
        AuthContextUtils.setQueryByAuth("vcid", authKey);
        
        //生成查询条件
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("vcid", vcid);
        params.put("name", name);
        params.put("code", code);
        params.put("valid", valid);
        params.put("roleType", roleType);
        params.put("excludeRoleId", excludeRoleId);
        
        //根据实际情况，填入排序字段等条件，根据是否需要排序，选择调用dao内方法
        List<Role> resList = this.roleDao.queryList(params);
        
        AuthContextUtils.clearQueryByAuth();
        
        return resList;
    }
    
    /**
     * 判断是否已经存在<br/>
     * <功能详细描述>
     * @return [参数说明]
     * 
     * @return int [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
    */
    public boolean isExist(Map<String, String> key2valueMap, String excludeId) {
        AssertUtils.notEmpty(key2valueMap, "key2valueMap is empty");
        
        //生成查询条件
        Map<String, Object> params = new HashMap<String, Object>();
        params.putAll(key2valueMap);
        params.put("excludeRoleId", excludeId);
        
        //根据实际情况，填入排序字段等条件，根据是否需要排序，选择调用dao内方法
        int res = this.roleDao.countRole(params);
        
        return res > 0;
    }
    
    /**
      * 根据id更新对象
      * <功能详细描述>
      * @param role
      * @return [参数说明]
      * 
      * @return boolean [返回类型说明]
      * @exception throws [异常类型] [异常说明]
      * @see [类、类#方法、类#成员]
     */
    @Transactional
    public boolean updateById(Role role) {
        AssertUtils.notNull(role, "role is null.");
        AssertUtils.notEmpty(role.getId(), "role.id is empty.");
        AssertUtils.isTrue(isEditAble(role.getId()),
                "role:{} is not editAble",
                role.getId());
        
        //生成需要更新字段的hashMap
        Map<String, Object> updateRowMap = new HashMap<String, Object>();
        updateRowMap.put("id", role.getId());
        
        updateRowMap.put("valid", role.isValid());
        updateRowMap.put("code", role.getCode());
        updateRowMap.put("remark", role.getRemark());
        updateRowMap.put("name", role.getName());
        int updateRowCount = this.roleDao.updateRole(updateRowMap);
        
        //如果需要大于1时，抛出异常并回滚，需要在这里修改
        return updateRowCount >= 1;
    }
    
    /**
     * 根据id禁用Role<br/>
     * <功能详细描述>
     * @param id
     * @return [参数说明]
     * 
     * @return boolean [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
    */
    @Transactional
    public boolean disableById(String id) {
        AssertUtils.notEmpty(id, "id is empty.");
        AssertUtils.isTrue(isEditAble(id), "role:{} is not editAble", id);
        AssertUtils.isTrue(isDeleteOrDisableAble(id),
                "role:{} is not delete able.maybe has ref to operator",
                id);
        
        //生成查询条件
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("id", id);
        params.put("valid", false);
        
        this.roleDao.updateRole(params);
        
        //记录操作日志
        ServiceLoggerContext.getLogger(SystemOperateLog.class)
                .log(new SystemOperateLog("wtms", "禁用角色",
                        MessageUtils.format("禁用角色[{}]",
                                new Object[] { findById(id).getName() }),
                        null));
        
        return true;
    }
    
    /**
      * 根据id启用Role<br/>
      * <功能详细描述>
      * @param postId
      * @return [参数说明]
      * 
      * @return boolean [返回类型说明]
      * @exception throws [异常类型] [异常说明]
      * @see [类、类#方法、类#成员]
     */
    @Transactional
    public boolean enableById(String id) {
        AssertUtils.notEmpty(id, "id is empty.");
        
        //生成查询条件
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("id", id);
        params.put("valid", true);
        
        this.roleDao.updateRole(params);
        
        //记录操作日志
        ServiceLoggerContext.getLogger(SystemOperateLog.class)
                .log(new SystemOperateLog("wtms", "启用角色",
                        MessageUtils.format("启用角色[{}]",
                                new Object[] { findById(id).getName() }),
                        null));
        
        return true;
    }
    
    /**
      * 判断对应的角色是否可编辑<br/>
      * <功能详细描述>
      * @param roleId
      * @return [参数说明]
      * 
      * @return boolean [返回类型说明]
      * @exception throws [异常类型] [异常说明]
      * @see [类、类#方法、类#成员]
     */
    private boolean isEditAble(String roleId) {
        AssertUtils.notEmpty(roleId, "roleId is empty.");
        
        Role role = findById(roleId);
        AssertUtils.notNull(role,
                "role:{} is not exist.may be deleted.",
                new Object[] { roleId });
        
        return role.isEditAble();
    }
}
