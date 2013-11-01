/*
 * 描          述:  <描述>
 * 修  改   人:  
 * 修改时间:  
 * <修改描述:>
 */
package com.tx.component.operator.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.tx.component.operator.dao.OrganizationDao;
import com.tx.component.operator.dao.VirtualCenterDao;
import com.tx.component.operator.model.VirtualCenter;
import com.tx.core.exceptions.util.AssertUtils;

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
public class VirtualCenterService {
    
    @SuppressWarnings("unused")
    private Logger logger = LoggerFactory.getLogger(VirtualCenterService.class);
    
    @Resource(name = "virtualCenterDao")
    private VirtualCenterDao virtualCenterDao;
    
    @Resource(name = "organizationDao")
    private OrganizationDao organizationDao;
    
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
        AssertUtils.isTrue(isDeleteAble(id),
                "virtualCenter is not deleteAble.id:{}",
                id);
        
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
      * 检查指定虚中心是否能在系统中被删除 
      *<功能详细描述>
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
        Map<String, Object> countParams = new HashMap<String, Object>();
        countParams.put("parentId", virtualCenterId);
        int resCount = this.virtualCenterDao.countVirtualCenter(countParams);
        if (resCount > 0) {
            return false;
        }
        
        //如果在该虚中心下存在组织，则对应组织不能删除
        Map<String, Object> queryOrgParams = new HashMap<String, Object>();
        queryOrgParams.put("vcid", virtualCenterId);
        int orgCount = this.organizationDao.countOrganization(queryOrgParams);
        if (orgCount > 0) {
            return false;
        }
        
        return true;
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
      * 根据parentId查询虚中心列表<br/>
      *<功能详细描述>
      * @param parentId
      * @return [参数说明]
      * 
      * @return List<VirtualCenter> [返回类型说明]
      * @exception throws [异常类型] [异常说明]
      * @see [类、类#方法、类#成员]
     */
    public List<VirtualCenter> queryVirtualCenterListByParentId(String parentId) {
        AssertUtils.notEmpty(parentId, "parentId is empty.");
        
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("parentId", parentId);
        List<VirtualCenter> resList = this.virtualCenterDao.queryVirtualCenterList(params);
        
        return resList;
    }
    
    /**
      * 查询当前虚中心及其子集虚中心集合<br/>
      *<功能详细描述>
      * @param vcid
      * @return [参数说明]
      * 
      * @return List<VirtualCenter> [返回类型说明]
      * @exception throws [异常类型] [异常说明]
      * @see [类、类#方法、类#成员]
     */
    public List<VirtualCenter> queryCurrentAndChildsVirtualCenterList(
            String vcid) {
        AssertUtils.notEmpty(vcid, "vcid is empty.");
        
        Set<VirtualCenter> resSet = new HashSet<VirtualCenter>();
        //如果不存在子集列表则返回仅包含当前虚中心的列表
        List<VirtualCenter> resList = new ArrayList<VirtualCenter>();
        resList.add(findVirtualCenterById(vcid));
        
        List<VirtualCenter> tempList = queryVirtualCenterListByParentId(vcid);
        //如果不存在子集列表则返回仅包含当前虚中心的列表
        if (CollectionUtils.isEmpty(tempList)) {
            return resList;
        }
        
        //迭代查询子集虚中心
        queryChildVirtualCenterSet(new HashSet<VirtualCenter>(tempList), resSet);
        
        resList.addAll(resSet);
        return resList;
    }
    
    /**
      * 查询子级虚中心列表<br/>
      *<功能详细描述>
      * @param parentId
      * @return [参数说明]
      * 
      * @return List<VirtualCenter> [返回类型说明]
      * @exception throws [异常类型] [异常说明]
      * @see [类、类#方法、类#成员]
     */
    public List<VirtualCenter> queryChildsVirtualCenterList(String vcid) {
        AssertUtils.notEmpty(vcid, "vcid is empty.");
        
        Set<VirtualCenter> resSet = new HashSet<VirtualCenter>();
        //如果不存在子集列表则返回仅包含当前虚中心的列表
        List<VirtualCenter> resList = new ArrayList<VirtualCenter>();
        resList.add(findVirtualCenterById(vcid));
        
        List<VirtualCenter> tempList = queryVirtualCenterListByParentId(vcid);
        //如果不存在子集列表则返回仅包含当前虚中心的列表
        if (CollectionUtils.isEmpty(tempList)) {
            return resList;
        }
        
        //迭代查询子集虚中心
        queryChildVirtualCenterSet(new HashSet<VirtualCenter>(tempList), resSet);
        
        resList.addAll(resSet);
        return resList;
    }
    
    /**
      * 迭代查询子集虚中心<br/>
      *<功能详细描述>
      * @param parentSet
      * @param resSet [参数说明]
      * 
      * @return void [返回类型说明]
      * @exception throws [异常类型] [异常说明]
      * @see [类、类#方法、类#成员]
     */
    private void queryChildVirtualCenterSet(Set<VirtualCenter> parentSet,
            Set<VirtualCenter> resSet) {
        if (!CollectionUtils.isEmpty(parentSet)) {
            Set<VirtualCenter> iteSet = new HashSet<VirtualCenter>();
            for (VirtualCenter vcTemp : parentSet) {
                if (!resSet.contains(vcTemp)) {
                    resSet.add(vcTemp);
                    iteSet.add(vcTemp);
                }
            }
            //迭代查询
            queryChildVirtualCenterSet(iteSet, resSet);
        }
    }
    
    /**
      * 根据VirtualCenter实体列表
      * 补充说明
      * 
      * <功能详细描述>
      * @return [参数说明]
      * 
      * @return List<VirtualCenter> [返回类型说明]
      * @exception throws [异常类型] [异常说明]
      * @see [类、类#方法、类#成员]
     */
    public List<VirtualCenter> listVirtualCenter() {
        //根据实际情况，填入排序字段等条件，根据是否需要排序，选择调用dao内方法
        List<VirtualCenter> resList = this.virtualCenterDao.queryVirtualCenterList(null);
        
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
        
        //生成需要更新字段的hashMap
        Map<String, Object> updateRowMap = new HashMap<String, Object>();
        updateRowMap.put("id", virtualCenter.getId());
        
        updateRowMap.put("remark", virtualCenter.getRemark());
        updateRowMap.put("name", virtualCenter.getName());
        
        int updateRowCount = this.virtualCenterDao.updateVirtualCenter(updateRowMap);
        
        //如果需要大于1时，抛出异常并回滚，需要在这里修改
        return updateRowCount >= 1;
    }
}
