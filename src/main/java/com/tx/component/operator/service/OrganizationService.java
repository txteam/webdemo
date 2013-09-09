/*
 * 描          述:  <描述>
 * 修  改   人:  
 * 修改时间:  
 * <修改描述:>
 */
package com.tx.component.operator.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.tx.component.operator.dao.OrganizationDao;
import com.tx.component.operator.model.Organization;
import com.tx.core.TxConstants;
import com.tx.core.exceptions.util.AssertUtils;

/**
 * Organization的业务层<br/>
 *      queryOrganization为根据条件查询<br/>
 *      queryChildOrganization为迭代查询<br/>
 * <功能详细描述>
 * 
 * @author  
 * @version  [版本号, ]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
@Component("newOrganizationService")
public class OrganizationService implements InitializingBean,
        ApplicationContextAware, RelateOrganization {
    
    @SuppressWarnings("unused")
    private Logger logger = LoggerFactory.getLogger(OrganizationService.class);
    
    @SuppressWarnings("unused")
    //@Resource(name = "serviceLogger")
    private Logger serviceLogger;
    
    @Resource(name = "newOrganizationDao")
    private OrganizationDao organizationDao;
    
    private ApplicationContext applicationContext;
    
    private List<RelateOrganization> relateOrganizationList = new ArrayList<RelateOrganization>();
    
    /**
     * @param applicationContext
     * @throws BeansException
     */
    @Override
    public void setApplicationContext(ApplicationContext applicationContext)
            throws BeansException {
        this.applicationContext = applicationContext;
    }
    
    /**
     * @throws Exception
     */
    @Override
    public void afterPropertiesSet() throws Exception {
        Map<String, RelateOrganization> reMap = this.applicationContext.getBeansOfType(RelateOrganization.class);
        if (!MapUtils.isEmpty(reMap)) {
            relateOrganizationList.addAll(reMap.values());
        }
    }
    
    /**
     * @return
     */
    @Override
    public String organizationReferenceName() {
        return "其他组织";
    }
    
    /**
     * @param organizationId
     * @return
     */
    @Override
    public boolean isReferenceOrganization(String organizationId) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("organizationId", organizationId);
        
        int counts = this.organizationDao.countOrganization(params);
        boolean isBeReferenced = counts > 0;
        return isBeReferenced;
    }
    
    /** 
     * 生成组织全名
     *<功能详细描述>
     * @param organization [参数说明]
     * 
     * @return void [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    private String generateOrganizationFullName(String parentId,
            String organizationName) {
        StringBuilder sb = new StringBuilder(TxConstants.INITIAL_STR_LENGTH);
        sb.append(organizationName);
        
        //利用该集合避免循环引用以及自引用的情况，保证循环跳出
        if (!StringUtils.isEmpty(parentId)) {
            Organization parent = findOrganizationById(parentId);
            if (parent != null) {
                sb.insert(0, "_");
                sb.insert(0, parent.getFullName());
            }
        }
        String newFullName = sb.toString();
        return newFullName;
    }
    
    /**
      * 将organization实例插入数据库中保存
      * 1、如果organization为空时抛出参数为空异常
      * 2、如果organization中部分必要参数为非法值时抛出参数不合法异常
     * <功能详细描述>
     * @param district [参数说明]
     * 
     * @return void [返回类型说明]
     * @exception throws
     * @see [类、类#方法、类#成员]
    */
    @Transactional
    public void insertOrganization(Organization organization) {
        //验证参数是否合法，必填字段是否填写，
        AssertUtils.notNull(organization, "organization is null");
        AssertUtils.notEmpty(organization.getName(),
                "organization.name is null");
        AssertUtils.notEmpty(organization.getCode(),
                "organization.code is null");
        
        //生成组织全名
        organization.setFullName(generateOrganizationFullName(organization.getParentId(),
                organization.getName()));
        
        //插入组织实体
        this.organizationDao.insertOrganization(organization);
    }
    
    /**
     * 根据code查询Organization实体
     * 1、当id为empty时抛出异常
     * <功能详细描述>
     * @param id
     * @return [参数说明]
     * 
     * @return Organization [返回类型说明]
     * @exception throws 可能存在数据库访问异常DataAccessException
     * @see [类、类#方法、类#成员]
    */
    public Organization findOrganizationByCode(String code) {
        AssertUtils.notEmpty(code, "code is empty.");
        
        Organization condition = new Organization();
        condition.setCode(code);
        return this.organizationDao.findOrganization(condition);
    }
    
    /**
      * 对应的编码已经存在<br/>
      *<功能详细描述>
      * @param code
      * @param excludeOrganizationId 需要抛出的组织id
      * @return [参数说明]
      * 
      * @return boolean [返回类型说明]
      * @exception throws [异常类型] [异常说明]
      * @see [类、类#方法、类#成员]
     */
    public boolean organizationCodeIsExist(String code,
            String excludeOrganizationId) {
        AssertUtils.notEmpty(code, "code is empty.");
        
        Map<String, Object> countCondition = new HashMap<String, Object>();
        countCondition.put("code", code);
        countCondition.put("excludeOrganizationId", excludeOrganizationId);
        
        int count = this.organizationDao.countOrganization(countCondition);
        return count > 0;
    }
    
    /**
      * 根据Id查询Organization实体
      * 1、当id为empty时抛出异常
      * <功能详细描述>
      * @param id
      * @return [参数说明]
      * 
      * @return Organization [返回类型说明]
      * @exception throws 可能存在数据库访问异常DataAccessException
      * @see [类、类#方法、类#成员]
     */
    public Organization findOrganizationById(String id) {
        AssertUtils.notEmpty(id, "id is empty.");
        
        Organization condition = new Organization();
        condition.setId(id);
        return this.organizationDao.findOrganization(condition);
    }
    
    /**
      * 根据Organization实体列表
      *     如果rootOrgId为空则返回所有的组织，如果不为空则返回以rootOrg下级的列表
      * 
      * <功能详细描述>
      * @return [参数说明]
      * 
      * @return List<Organization> [返回类型说明]
      * @exception throws [异常类型] [异常说明]
      * @see [类、类#方法、类#成员]
     */
    public List<Organization> queryOrganizationList(String parentOrganizationId) {
        //生成查询条件
        List<Organization> resList = null;
        if (StringUtils.isEmpty(parentOrganizationId)) {
            resList = this.organizationDao.queryOrganizationList(null);
        } else {
            resList = queryChildOrganizationListByParentId(parentOrganizationId);
        }
        
        return resList;
    }
    
    /**
      * 根据父级id查询组织列表
      *<功能详细描述>
      * @param parentOrganizationId
      * @return [参数说明]
      * 
      * @return List<Organization> [返回类型说明]
      * @exception throws [异常类型] [异常说明]
      * @see [类、类#方法、类#成员]
     */
    public List<Organization> queryOrganizationListByParentId(
            String parentOrganizationId) {
        //生成查询条件
        List<Organization> resList = null;
        if (StringUtils.isEmpty(parentOrganizationId)) {
            resList = this.organizationDao.queryOrganizationList(null);
        } else {
            resList = queryChildOrganizationListByParentId(parentOrganizationId);
        }
        
        return resList;
    }
    
    /**
      * 根据父组织id迭代查询当前组织及其子组织的列表<br/>
      *<功能详细描述>
      * @param parentOrganizationId
      * @return [参数说明]
      * 
      * @return List<Organization> [返回类型说明]
      * @exception throws [异常类型] [异常说明]
      * @see [类、类#方法、类#成员]
     */
    public List<Organization> queryChildOrganizationListByParentId(
            String parentOrganizationId) {
        AssertUtils.notEmpty(parentOrganizationId,
                "parentOrganizationId is empty");
        //生成查询条件
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("parentId", parentOrganizationId);
        
        List<Organization> resList = new ArrayList<Organization>();
        //根据实际情况，填入排序字段等条件，根据是否需要排序，选择调用dao内方法
        List<Organization> childList = this.organizationDao.queryOrganizationList(params);
        if (!CollectionUtils.isEmpty(childList)) {
            resList.addAll(childList);
            for (Organization childTemp : childList) {
                if (childTemp == null) {
                    continue;
                }
                resList.addAll(queryChildOrganizationListByParentId(childTemp.getId()));
            }
        }
        
        return resList;
    }
    
    /**
      * 获取子组织的列表<br/>
      *<功能详细描述>
      * @param parentOrganizationId
      * @return [参数说明]
      * 
      * @return List<String> [返回类型说明]
      * @exception throws [异常类型] [异常说明]
      * @see [类、类#方法、类#成员]
     */
    public List<String> queryChildOrganizationIdListByParentId(
            String parentOrganizationId) {
        AssertUtils.notEmpty(parentOrganizationId,
                "parentOrganizationId is empty");
        
        List<Organization> orgList = queryChildOrganizationListByParentId(parentOrganizationId);
        List<String> resList = new ArrayList<String>();
        if (CollectionUtils.isEmpty(orgList)) {
            return resList;
        }
        
        for (Organization orgTemp : orgList) {
            resList.add(orgTemp.getId());
        }
        
        return resList;
    }
    
    /**
      *<功能简述>
      *<功能详细描述>
      * @param organizationId
      * @param name [参数说明]
      * 
      * @return void [返回类型说明]
      * @exception throws [异常类型] [异常说明]
      * @see [类、类#方法、类#成员]
     */
    @SuppressWarnings("unused")
    private void iteratorUpdateChildOrganization(
            List<Organization> childOrganizationList, String newParentId,
            String newParentFullName) {
        if (CollectionUtils.isEmpty(childOrganizationList)) {
            return;
        }
        
        for (Organization orgTemp : childOrganizationList) {
            Map<String, Object> updateOrgTempParams = new HashMap<String, Object>();
            //generateOrganizationFullName(organization);
            //updateOrgTempParams.put("id", orgTemp.getId());
            //updateOrgTempParams.put(key, value)
        }
    }
    
    /**
      * 根据id更新对象
      * <功能详细描述>
      * @param organization
      * @return [参数说明]
      * 
      * @return boolean [返回类型说明]
      * @exception throws [异常类型] [异常说明]
      * @see [类、类#方法、类#成员]
     */
    @Transactional
    public boolean updateById(Organization organization) {
        //验证参数是否合法，必填字段是否填写，
        AssertUtils.notNull(organization, "organization is null.");
        AssertUtils.notEmpty(organization.getId(), "organization.id is empty.");
        AssertUtils.notEmpty(organization.getName(),
                "organization.name is empty.");
        AssertUtils.notEmpty(organization.getCode(),
                "organization.code is empty.");
        
        //生成需要更新字段的hashMap
        Map<String, Object> updateRowMap = new HashMap<String, Object>();
        updateRowMap.put("id", organization.getId());
        
        //需要更新的字段
        updateRowMap.put("fullAddress", organization.getFullAddress());
        updateRowMap.put("alias", organization.getAlias());
        updateRowMap.put("remark", organization.getRemark());
        updateRowMap.put("code", organization.getCode());
        updateRowMap.put("type", organization.getType());
        updateRowMap.put("parentId", organization.getParentId());
        updateRowMap.put("address", organization.getAddress());
        updateRowMap.put("name", organization.getName());
        updateRowMap.put("fullName",
                generateOrganizationFullName(organization.getParentId(),
                        organization.getName()));
        updateRowMap.put("chiefType", organization.getChiefType());
        updateRowMap.put("chiefId", organization.getChiefId());
        
        int updateRowCount = this.organizationDao.updateOrganization(updateRowMap);
        
        //如果需要大于1时，抛出异常并回滚，需要在这里修改
        return updateRowCount >= 1;
    }
    
    /**
     * 根据id删除organization实例
     *      1、如果入参数为空，则抛出异常
     *      2、执行删除后，将返回数据库中被影响的条数
     *      3、考虑如果是级联删除，影响较大，可能一个误操作引起不必要的错误，
     *             而且与组织关联的对象可能很多，组织一旦被启用后，删除后对原系统冲击过大
     *             在未建外键关联的情况下，不允许轻易删除组织
     *      4、采取方案为删除对应组织后，下级组织自动上移一级
     *      5、并且先上移以后再进行删除
     *      开发阶段暂不考虑提供组织的删除功能
     * @param id
     * @return 返回删除的数据条数，<br/>
     * 有些业务场景，如果已经被别人删除同样也可以认为是成功的
     * 这里讲通用生成的业务层代码定义为返回影响的条数
     * @return int [返回类型说明]
     * @exception throws 
     * @see [类、类#方法、类#成员]
    */
    @Transactional
    public int deleteById(String id) {
        AssertUtils.notEmpty(id, "id is empty.");
        
        //如果对应
        if (isBeReferenced(id)) {
            //throw new NotAllowedOperateException("对应组织被引用不允许被删除");
        }
        
        Organization condition = new Organization();
        condition.setId(id);
        return this.organizationDao.deleteOrganization(condition);
    }
    
    /**
      * 对应组织是否被引用<br/>
      *<功能详细描述>
      * @param organizationId
      * @return [参数说明]
      * 
      * @return boolean [返回类型说明]
      * @exception throws [异常类型] [异常说明]
      * @see [类、类#方法、类#成员]
     */
    private boolean isBeReferenced(String organizationId) {
        for (RelateOrganization roTemp : this.relateOrganizationList) {
            if (roTemp.isReferenceOrganization(organizationId)) {
                return true;
            }
        }
        return false;
    }
    
    /**
      * 获取存在的引用名称<br/>
      *<功能详细描述>
      * @param organizationId
      * @return [参数说明]
      * 
      * @return List<String> [返回类型说明]
      * @exception throws [异常类型] [异常说明]
      * @see [类、类#方法、类#成员]
     */
    private List<String> getReferenceOrganizationNames(String organizationId) {
        List<String> resList = new ArrayList<String>();
        for (RelateOrganization roTemp : this.relateOrganizationList) {
            if (roTemp.isReferenceOrganization(organizationId)) {
                resList.add(roTemp.organizationReferenceName());
            }
        }
        return resList;
    }
}
