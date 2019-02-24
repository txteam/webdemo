/*
 * 描          述:  <描述>
 * 修  改   人:  
 * 修改时间:  
 * <修改描述:>
 */
package com.tx.component.operator.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
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
import com.tx.component.operator.dao.OperatorDao;
import com.tx.component.operator.dao.OrganizationDao;
import com.tx.component.operator.dao.PostDao;
import com.tx.component.operator.dao.RoleDao;
import com.tx.component.operator.dao.VirtualCenterDao;
import com.tx.component.operator.model.VirtualCenter;
import com.tx.component.operator.model.VirtualCenterEnum;
import com.tx.component.servicelog.context.ServiceLoggerContext;
import com.tx.core.exceptions.util.AssertUtils;
import com.tx.core.util.MessageUtils;

/**
 * VirtualCenter的业务层
 * <功能详细描述>
 * 
 * @author  
 * @version  [版本号, ]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
@Component("virtualCenterService")
public class VirtualCenterService implements InitializingBean {
    
    @SuppressWarnings("unused")
    private Logger logger = LoggerFactory.getLogger(VirtualCenterService.class);
    
    @Resource(name = "virtualCenterDao")
    private VirtualCenterDao virtualCenterDao;
    
    @Resource(name = "organizationDao")
    private OrganizationDao organizationDao;
    
    @Resource(name = "operatorDao")
    private OperatorDao operatorDao;
    
    @Resource(name = "roleDao")
    private RoleDao roleDao;
    
    @Resource(name = "postDao")
    private PostDao postDao;
    
    /**
     * 初始化方法
     *     根据虚中心枚举，如果对应的虚中心没有被自动创建，则系统自动创建<br/>
     * @throws Exception
     */
    @Override
    public void afterPropertiesSet() throws Exception {
        List<VirtualCenter> virtualCenterList = this.queryVirtualCenter(null);
        
        Map<String, VirtualCenterEnum> virtualCenterEnumMap = EnumUtils.<VirtualCenterEnum> getEnumMap(VirtualCenterEnum.class);
        
        Map<VirtualCenterEnum, VirtualCenter> virtualCenterMap = new HashMap<VirtualCenterEnum, VirtualCenter>();
        for (VirtualCenter virtualCenter : virtualCenterList) {
            if (virtualCenter.getVirtualCenterKey() == null) {
                continue;
            }
            virtualCenterMap.put(virtualCenter.getVirtualCenterKey(),
                    virtualCenter);
        }
        
        for (VirtualCenterEnum virtualCenterKeyTemp : virtualCenterEnumMap.values()) {
            if (virtualCenterMap.containsKey(virtualCenterKeyTemp)) {
                continue;
            }
            
            //将新的虚中心插入表中
            VirtualCenter virtualCenter = new VirtualCenter();
            virtualCenter.setName(virtualCenterKeyTemp.getName());
            virtualCenter.setVirtualCenterKey(virtualCenterKeyTemp);
            virtualCenter.setEditAble(false);
            this.insertVirtualCenter(virtualCenter);
        }
    }
    
    /**
      * 将virtualCenter实例插入数据库中保存
      * 1、如果virtualCenter为空时抛出参数为空异常
      * 2、如果virtualCenter中部分必要参数为非法值时抛出参数不合法异常
     * <功能详细描述>
     * @param district [参数说明]
     * 
     * @return void [返回类型说明]
     * @exception throws
     * @see [类、类#方法、类#成员]
    */
    @Transactional
    public void insertVirtualCenter(VirtualCenter virtualCenter) {
        AssertUtils.notNull(virtualCenter, "virtualCenter is null.");
        AssertUtils.notEmpty(virtualCenter.getName(),
                "virtualCenter.name is empty.");
        if (virtualCenter.getVirtualCenterKey() != null) {
            AssertUtils.isTrue(!virtualCenterKeyIsExist(virtualCenter.getVirtualCenterKey(),
                    null),
                    "virtualCenter:{} is exist.",
                    new Object[] { virtualCenter.getVirtualCenterKey() });
        }
        AssertUtils.isTrue(!virtualCenterNameIsExist(virtualCenter.getName(),
                null),
                "virtualCenter:{} is exist.",
                new Object[] { virtualCenter.getName() });
        
        //设置创建时间为当前时间
        virtualCenter.setCreateDate(new Date());
        virtualCenter.setValid(true);
        
        //记录操作日志
        ServiceLoggerContext.getLogger(SystemOperateLog.class)
                .log(new SystemOperateLog("wtms", "新增虚中心",
                        MessageUtils.format("新增虚中心[{}]",
                                new Object[] { virtualCenter.getName() }), null));
        
        this.virtualCenterDao.insertVirtualCenter(virtualCenter);
    }
    
    /**
     * 根据id删除virtualCenter实例
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
        //再次验证虚中心允许被删除
        delateBeforeCheck(id);
        AssertUtils.isTrue(isEditAble(id),
                "virtualCenter:{} is not editAble.",
                id);
        
        //记录操作日志
        ServiceLoggerContext.getLogger(SystemOperateLog.class)
                .log(new SystemOperateLog(
                        "wtms",
                        "删除虚中心",
                        MessageUtils.format("删除虚中心[{}]",
                                new Object[] { findVirtualCenterById(id).getName() }),
                        null));
        
        VirtualCenter condition = new VirtualCenter();
        condition.setId(id);
        return this.virtualCenterDao.deleteVirtualCenter(condition) > 0;
    }
    
    /**
      * 指定虚中心名是否在系统中已经存在<br/>
      *<功能详细描述>
      * @param name
      * @param excludeVirtualCenterId
      * @return [参数说明]
      * 
      * @return boolean [返回类型说明]
      * @exception throws [异常类型] [异常说明]
      * @see [类、类#方法、类#成员]
     */
    public boolean virtualCenterNameIsExist(String name,
            String excludeVirtualCenterId) {
        
        //检查对应用户名是否已经存在
        Map<String, Object> countParams = new HashMap<String, Object>();
        countParams.put("name", name);
        countParams.put("excludeVirtualCenterId", excludeVirtualCenterId);
        int resCount = this.virtualCenterDao.countVirtualCenter(countParams);
        boolean resFlag = resCount > 0;
        
        return resFlag;
    }
    
    /**
     * 指定虚中心名是否在系统中已经存在<br/>
     *<功能详细描述>
     * @param name
     * @param excludeVirtualCenterId
     * @return [参数说明]
     * 
     * @return boolean [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
    */
    public boolean virtualCenterKeyIsExist(VirtualCenterEnum virtualCenterKey,
            String excludeVirtualCenterId) {
        
        //检查对应用户名是否已经存在
        Map<String, Object> countParams = new HashMap<String, Object>();
        countParams.put("virtualCenterKey", virtualCenterKey);
        countParams.put("excludeVirtualCenterId", excludeVirtualCenterId);
        int resCount = this.virtualCenterDao.countVirtualCenter(countParams);
        boolean resFlag = resCount > 0;
        
        return resFlag;
    }
    
    /**
      * 根据Id查询VirtualCenter实体
      * 1、当id为empty时抛出异常
      * <功能详细描述>
      * @param id
      * @return [参数说明]
      * 
      * @return VirtualCenter [返回类型说明]
      * @exception throws 可能存在数据库访问异常DataAccessException
      * @see [类、类#方法、类#成员]
     */
    public VirtualCenter findVirtualCenterById(String id) {
        AssertUtils.notEmpty(id, "id is empty.");
        
        VirtualCenter condition = new VirtualCenter();
        condition.setId(id);
        return this.virtualCenterDao.findVirtualCenter(condition);
    }
    
    /**
      * 根据virtualCenterKey获取虚中心实例<br/>
      * <功能详细描述>
      * @param virtualCenterKey
      * @return [参数说明]
      * 
      * @return VirtualCenter [返回类型说明]
      * @exception throws [异常类型] [异常说明]
      * @see [类、类#方法、类#成员]
     */
    public VirtualCenter findVirtualCenterByKey(
            VirtualCenterEnum virtualCenterKey) {
        AssertUtils.notEmpty(virtualCenterKey, "virtualCenterKey is empty.");
        VirtualCenter condition = new VirtualCenter();
        condition.setVirtualCenterKey(virtualCenterKey);
        return this.virtualCenterDao.findVirtualCenter(condition);
    }
    
    /**
      * 根据parentId查询虚中心列表<br/>
      *<功能详细描述>
      * @param parentId
      * @return [参数说明]
      * 
      * @return List<VirtualCenter> [返回类型说明]
      * @exception throws [异常类型] [异常说明]
      * @see [类、类#方法、类#成员]
     */
    public List<VirtualCenter> queryVirtualCenterListByParentId(
            String parentId, Boolean valid) {
        AssertUtils.notEmpty(parentId, "parentId is empty.");
        
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("parentId", parentId);
        params.put("valid", valid);
        List<VirtualCenter> resList = this.virtualCenterDao.queryVirtualCenterList(params);
        
        return resList;
    }
    
    /**
      * 查询子级虚中心列表<br/>
      * <功能详细描述>
      * @param parentId
      * @return [参数说明]
      * 
      * @return List<VirtualCenter> [返回类型说明]
      * @exception throws [异常类型] [异常说明]
      * @see [类、类#方法、类#成员]
     */
    public Set<VirtualCenter> queryChildsVirtualCenterSet(String vcid,
            Boolean valid) {
        AssertUtils.notEmpty(vcid, "vcid is empty.");
        
        Set<VirtualCenter> resSet = new HashSet<VirtualCenter>();
        
        List<VirtualCenter> parentVirtualCenterList = queryVirtualCenterListByParentId(vcid,
                valid);
        //如果不存在子集列表则返回仅包含当前虚中心的列表
        if (CollectionUtils.isEmpty(parentVirtualCenterList)) {
            return resSet;
        }
        //迭代查询子集虚中心
        nestedQueryChildVirtualCenter(resSet, parentVirtualCenterList, valid);
        return resSet;
    }
    
    /**
      * 迭代查询子集虚中心<br/>
      * <功能详细描述>
      * @param parentSet
      * @param resSet [参数说明]
      * 
      * @return void [返回类型说明]
      * @exception throws [异常类型] [异常说明]
      * @see [类、类#方法、类#成员]
     */
    private void nestedQueryChildVirtualCenter(Set<VirtualCenter> resSet,
            List<VirtualCenter> parentVirtualCenterList, Boolean valid) {
        if (CollectionUtils.isEmpty(parentVirtualCenterList)) {
            return;
        }
        List<VirtualCenter> newParentVirtualCenterList = new ArrayList<>();
        for (VirtualCenter vcTemp : parentVirtualCenterList) {
            if (!resSet.contains(vcTemp)) {
                resSet.add(vcTemp);
                newParentVirtualCenterList.addAll(queryVirtualCenterListByParentId(vcTemp.getId(),
                        valid));
            }
        }
        //迭代查询
        nestedQueryChildVirtualCenter(resSet, newParentVirtualCenterList, valid);
    }
    
    /**
      * 根据条件查询虚中心列表<br/>
      * <功能详细描述>
      * @return [参数说明]
      * 
      * @return List<VirtualCenter> [返回类型说明]
      * @exception throws [异常类型] [异常说明]
      * @see [类、类#方法、类#成员]
     */
    public List<VirtualCenter> queryVirtualCenter(Boolean valid) {
        Map<String, Object> params = new HashMap<>();
        params.put("valid", valid);
        List<VirtualCenter> resList = this.virtualCenterDao.queryVirtualCenterList(params);
        return resList;
    }
    
    /**
     * 根据条件查询虚中心列表<br/>
     * <功能详细描述>
     * @return [参数说明]
     * 
     * @return List<VirtualCenter> [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
    */
    public List<VirtualCenter> queryVirtualCenterByAuth(String authKey) {
        AssertUtils.notEmpty(authKey, "authKey is empty.");
        AuthContextUtils.setQueryByAuth("id", authKey);
        
        Map<String, Object> params = new HashMap<>();
        params.put("valid", true);
        List<VirtualCenter> resList = this.virtualCenterDao.queryVirtualCenterList(params);
        
        AuthContextUtils.clearQueryByAuth();
        return resList;
    }
    
    /**
      * 根据id更新对象
      * <功能详细描述>
      * @param virtualCenter
      * @return [参数说明]
      * 
      * @return boolean [返回类型说明]
      * @exception throws [异常类型] [异常说明]
      * @see [类、类#方法、类#成员]
     */
    @Transactional
    public boolean updateById(VirtualCenter virtualCenter) {
        //验证参数是否合法，必填字段是否填写，
        AssertUtils.notNull(virtualCenter, "virtualCenter is null.");
        AssertUtils.notEmpty(virtualCenter.getId(),
                "virtualCenter.id is empty.");
        AssertUtils.isTrue(isEditAble(virtualCenter.getId()),
                "virtualCenter:{} is not editAble.",
                virtualCenter.getId());
        
        //生成需要更新字段的hashMap
        Map<String, Object> updateRowMap = new HashMap<String, Object>();
        updateRowMap.put("id", virtualCenter.getId());
        
        updateRowMap.put("remark", virtualCenter.getRemark());
        updateRowMap.put("name", virtualCenter.getName());
        updateRowMap.put("valid", virtualCenter.isValid());
        updateRowMap.put("virtualCenterKey",
                virtualCenter.getVirtualCenterKey());
        
        //记录操作日志
        ServiceLoggerContext.getLogger(SystemOperateLog.class)
                .log(new SystemOperateLog("wtms", "更新虚中心",
                        MessageUtils.format("更新虚中心[{}]",
                                new Object[] { virtualCenter.getName() }), null));
        
        int updateRowCount = this.virtualCenterDao.updateVirtualCenter(updateRowMap);
        
        //如果需要大于1时，抛出异常并回滚，需要在这里修改
        return updateRowCount >= 1;
    }
    
    /**
     * 根据组织id禁用组织
     *<功能详细描述>
     * @param organizationId
     * @return [参数说明]
     * 
     * @return boolean [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
    */
    @Transactional
    public boolean disableVirtualCenterById(String virtualCenterId) {
        AssertUtils.notEmpty(virtualCenterId, "organizationId is empty.");
        disableBeforeCheck(virtualCenterId);
        AssertUtils.isTrue(isEditAble(virtualCenterId),
                "virtualCenter:{} is not editAble.",
                virtualCenterId);
        //查询下级虚中心是否都已经禁用
        AssertUtils.isTrue(isDisableAble(virtualCenterId),
                "virtualCenter:{} is not disableAble.",
                virtualCenterId);
        
        Map<String, Object> updateRowMap = new HashMap<String, Object>();
        updateRowMap.put("id", virtualCenterId);
        updateRowMap.put("valid", false);
        int updateRowCount = this.virtualCenterDao.updateVirtualCenter(updateRowMap);
        
        //记录操作日志
        ServiceLoggerContext.getLogger(SystemOperateLog.class)
                .log(new SystemOperateLog(
                        "wtms",
                        "停用虚中心",
                        MessageUtils.format("停用虚中心[{}]",
                                new Object[] { findVirtualCenterById(virtualCenterId).getName() }),
                        null));
        
        return updateRowCount > 0;
    }
    
    /**
      * 根据组织id启用组织
      *<功能详细描述>
      * @param organizationId
      * @return [参数说明]
      * 
      * @return boolean [返回类型说明]
      * @exception throws [异常类型] [异常说明]
      * @see [类、类#方法、类#成员]
     */
    @Transactional
    public boolean enableVirtualCenterById(String virtualCenterId) {
        AssertUtils.notEmpty(virtualCenterId, "organizationId is empty.");
        AssertUtils.isTrue(isEditAble(virtualCenterId),
                "virtualCenter:{} is not editAble.",
                virtualCenterId);
        
        //记录操作日志
        ServiceLoggerContext.getLogger(SystemOperateLog.class)
                .log(new SystemOperateLog(
                        "wtms",
                        "启用虚中心",
                        MessageUtils.format("启用虚中心[{}]",
                                new Object[] { findVirtualCenterById(virtualCenterId).getName() }),
                        null));
        
        Map<String, Object> updateRowMap = new HashMap<String, Object>();
        updateRowMap.put("id", virtualCenterId);
        updateRowMap.put("valid", true);
        int updateRowCount = this.virtualCenterDao.updateVirtualCenter(updateRowMap);
        
        return updateRowCount > 0;
    }
    
    /**
      * 判断指定的虚中心id对应的虚中心对象是否可编辑<br/> 
      * <功能详细描述>
      * @param virtualCenterId
      * @return [参数说明]
      * 
      * @return boolean [返回类型说明]
      * @exception throws [异常类型] [异常说明]
      * @see [类、类#方法、类#成员]
     */
    private boolean isEditAble(String virtualCenterId) {
        AssertUtils.notEmpty(virtualCenterId, "virtualCenterId is empty.");
        VirtualCenter virtualCenter = findVirtualCenterById(virtualCenterId);
        AssertUtils.notNull(virtualCenter,
                "virtualCenter:{} not exist.maybe deleted.");
        
        boolean isEditAble = virtualCenter.isEditAble();
        return isEditAble;
    }
    
    /**
     * 虚中心是否可被删除<br/>
     * <功能详细描述>
     * @param virtualCenterId
     * @return [参数说明]
     * 
     * @return boolean [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
    */
    public boolean isDisableAble(String virtualCenterId) {
        //如果存在下级虚中心，则虚中心不能删除
        {
            Map<String, Object> countParams = new HashMap<String, Object>();
            countParams.put("parentId", virtualCenterId);
            countParams.put("valid", true);
            int resCount = this.virtualCenterDao.countVirtualCenter(countParams);
            if (resCount > 0) {
                return false;
            }
        }
        
        //如果在该虚中心下存在组织，则对应组织不能删除
        {
            Map<String, Object> queryOrgParams = new HashMap<String, Object>();
            queryOrgParams.put("vcid", virtualCenterId);
            int orgCount = this.organizationDao.countOrganization(queryOrgParams);
            AssertUtils.isTrue(orgCount == 0, "存在子集组织,不能禁用.");
        }
        
        //如果在该虚中心下存在组织，则对应组织不能删除
        {
            Map<String, Object> queryPostParams = new HashMap<String, Object>();
            queryPostParams.put("vcid", virtualCenterId);
            int postCount = this.postDao.countPost(queryPostParams);
            AssertUtils.isTrue(postCount == 0, "存在职位,不能禁用.");
        }
        
        //如果在该虚中心下存在组织，则对应组织不能删除
        {
            Map<String, Object> queryRoleParams = new HashMap<String, Object>();
            queryRoleParams.put("vcid", virtualCenterId);
            int roleCount = this.roleDao.countRole(queryRoleParams);
            AssertUtils.isTrue(roleCount == 0, "存在角色,不能删除.");
        }
        
        //如果在该虚中心下存在组织，则对应组织不能删除
        {
            Map<String, Object> queryOperatorParams = new HashMap<String, Object>();
            queryOperatorParams.put("vcid", virtualCenterId);
            int operCount = this.operatorDao.countOperator(queryOperatorParams);
            AssertUtils.isTrue(operCount == 0, "存在操作员,不能禁用.");
        }
        return true;
    }
    
    /**
     * 禁用前调用检查
     *    存在子集合虚中心不能删除，如果子集都禁用了则可禁用
      *<功能详细描述>
      * @param isDisable [参数说明]
      * 
      * @return void [返回类型说明]
      * @exception throws [异常类型] [异常说明]
      * @see [类、类#方法、类#成员]
     */
    private void disableBeforeCheck(String virtualCenterId) {
        //如果存在下级虚中心，则虚中心不能删除
        {
            Map<String, Object> countParams = new HashMap<String, Object>();
            countParams.put("parentId", virtualCenterId);
            countParams.put("valid", true);
            int resCount = this.virtualCenterDao.countVirtualCenter(countParams);
            AssertUtils.isTrue(resCount == 0, "存在未禁用的子集虚中心,不能禁用.");
        }
        
        //如果在该虚中心下存在组织，则对应组织不能删除
        {
            Map<String, Object> queryOrgParams = new HashMap<String, Object>();
            queryOrgParams.put("vcid", virtualCenterId);
            int orgCount = this.organizationDao.countOrganization(queryOrgParams);
            AssertUtils.isTrue(orgCount == 0, "存在子集组织,不能删除.");
        }
        
        //如果在该虚中心下存在职位，则对应组织不能删除
        {
            Map<String, Object> queryPostParams = new HashMap<String, Object>();
            queryPostParams.put("vcid", virtualCenterId);
            int postCount = this.postDao.countPost(queryPostParams);
            AssertUtils.isTrue(postCount == 0, "存在职位,不能删除.");
        }
        
        //如果在该虚中心下存在角色，则对应组织不能删除
        {
            Map<String, Object> queryRoleParams = new HashMap<String, Object>();
            queryRoleParams.put("vcid", virtualCenterId);
            int roleCount = this.roleDao.countRole(queryRoleParams);
            AssertUtils.isTrue(roleCount == 0, "存在角色,不能删除.");
        }
        
        //如果在该虚中心下存在人员，则对应组织不能删除
        {
            Map<String, Object> queryOperatorParams = new HashMap<String, Object>();
            queryOperatorParams.put("vcid", virtualCenterId);
            int operCount = this.operatorDao.countOperator(queryOperatorParams);
            AssertUtils.isTrue(operCount == 0, "存在操作员,不能删除.");
        }
    }
    
    /**
      * 虚中心是否可被删除<br/>
      * <功能详细描述>
      * @param virtualCenterId
      * @return [参数说明]
      * 
      * @return boolean [返回类型说明]
      * @exception throws [异常类型] [异常说明]
      * @see [类、类#方法、类#成员]
     */
    public boolean isDeleteAble(String virtualCenterId) {
        AssertUtils.notEmpty(virtualCenterId, "virtualCenterId is empty.");
        //如果存在下级虚中心，则虚中心不能删除
        {
            Map<String, Object> countParams = new HashMap<String, Object>();
            countParams.put("parentId", virtualCenterId);
            int resCount = this.virtualCenterDao.countVirtualCenter(countParams);
            if (resCount > 0) {
                return false;
            }
        }
        
        //如果在该虚中心下存在组织，则对应组织不能删除
        {
            Map<String, Object> queryOrgParams = new HashMap<String, Object>();
            queryOrgParams.put("vcid", virtualCenterId);
            int orgCount = this.organizationDao.countOrganization(queryOrgParams);
            if (orgCount > 0) {
                return false;
            }
        }
        
        //如果在该虚中心下存在职位，则对应组织不能删除
        {
            Map<String, Object> queryPostParams = new HashMap<String, Object>();
            queryPostParams.put("vcid", virtualCenterId);
            int postCount = this.postDao.countPost(queryPostParams);
            if (postCount > 0) {
                return false;
            }
        }
        
        //如果在该虚中心下存在角色，则对应组织不能删除
        {
            Map<String, Object> queryRoleParams = new HashMap<String, Object>();
            queryRoleParams.put("vcid", virtualCenterId);
            int roleCount = this.roleDao.countRole(queryRoleParams);
            if (roleCount > 0) {
                return false;
            }
        }
        
        //如果在该虚中心下存在人员，则对应组织不能删除
        {
            Map<String, Object> queryOperatorParams = new HashMap<String, Object>();
            queryOperatorParams.put("vcid", virtualCenterId);
            int operCount = this.operatorDao.countOperator(queryOperatorParams);
            if (operCount > 0) {
                return false;
            }
        }
        return true;
    }
    
    /**
     * 删除前调用检查
     *    存在子集合虚中心不能删除，如果子集都禁用了则可禁用
      *<功能详细描述>
      * @param isDisable [参数说明]
      * 
      * @return void [返回类型说明]
      * @exception throws [异常类型] [异常说明]
      * @see [类、类#方法、类#成员]
     */
    private void delateBeforeCheck(String virtualCenterId) {
        //如果存在下级虚中心，则虚中心不能删除
        {
            Map<String, Object> countParams = new HashMap<String, Object>();
            countParams.put("parentId", virtualCenterId);
            int resCount = this.virtualCenterDao.countVirtualCenter(countParams);
            AssertUtils.isTrue(resCount == 0, "存在子集虚中心,不能删除.");
        }
        //如果在该虚中心下存在组织，则对应组织不能删除
        {
            Map<String, Object> queryOrgParams = new HashMap<String, Object>();
            queryOrgParams.put("vcid", virtualCenterId);
            int orgCount = this.organizationDao.countOrganization(queryOrgParams);
            AssertUtils.isTrue(orgCount == 0, "存在子集组织,不能删除.");
        }
        
        //如果在该虚中心下存在职位，则对应组织不能删除
        {
            Map<String, Object> queryPostParams = new HashMap<String, Object>();
            queryPostParams.put("vcid", virtualCenterId);
            int postCount = this.postDao.countPost(queryPostParams);
            AssertUtils.isTrue(postCount == 0, "存在职位,不能删除.");
        }
        
        //如果在该虚中心下存在角色，则对应组织不能删除
        {
            Map<String, Object> queryRoleParams = new HashMap<String, Object>();
            queryRoleParams.put("vcid", virtualCenterId);
            int roleCount = this.roleDao.countRole(queryRoleParams);
            AssertUtils.isTrue(roleCount == 0, "存在角色,不能删除.");
        }
        
        //如果在该虚中心下存在人员，则对应组织不能删除
        {
            Map<String, Object> queryOperatorParams = new HashMap<String, Object>();
            queryOperatorParams.put("vcid", virtualCenterId);
            int operCount = this.operatorDao.countOperator(queryOperatorParams);
            AssertUtils.isTrue(operCount == 0, "存在操作员,不能删除.");
        }
    }
}
