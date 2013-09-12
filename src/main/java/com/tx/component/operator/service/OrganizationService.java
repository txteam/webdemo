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
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.tx.component.mainframe.context.WebContextUtils;
import com.tx.component.operator.OperatorConstants;
import com.tx.component.operator.dao.OrganizationDao;
import com.tx.component.operator.model.Organization;
import com.tx.component.operator.model.Post;
import com.tx.component.operator.treeview.TreeNode;
import com.tx.component.operator.treeview.TreeNodeAdapter;
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
public class OrganizationService {
    
    @SuppressWarnings("unused")
    private Logger logger = LoggerFactory.getLogger(OrganizationService.class);
    
    @Resource(name = "newOrganizationDao")
    private OrganizationDao organizationDao;
    
    @Resource(name = "newPostService")
    private PostService postService;
    
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
      * 查询获取组织职位树数据列表<br/>
      *<功能简述>
      *<功能详细描述>
      * @param includeInvalidOrganization  是否包括已经停用的组织<br/>
      * @return [参数说明]
      * 
      * @return List<OrganizationPostTreeNode> [返回类型说明]
      * @exception throws [异常类型] [异常说明]
      * @see [类、类#方法、类#成员]
     */
    public List<TreeNode> queryOrganizationPostTreeNodeListByAuth(
            boolean includeInvalidOrganization) {
        List<Organization> orgList = queryOrganizationListByAuth(includeInvalidOrganization);
        
        List<TreeNode> resList = new ArrayList<TreeNode>();
        for (Organization orgTemp : orgList) {
            resList.add(new TreeNode(organizationAdapter, orgTemp));
            
            List<Post> postList = this.postService.queryPostListByOrganizationId(orgTemp.getId());
            for (Post postTemp : postList) {
                resList.add(new TreeNode(postAdapter, postTemp));
            }
        }
        return resList;
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
      *     根据权限查询组织列表<br/>
      *     默认：查询当前组织及其下级组织的权限<br/>
      *     如果有查询所有组织的权限，则能查询所有组织的的数据<br/>
      *     
      *     如果rootOrgId为空则返回所有的组织，如果不为空则返回以rootOrg下级的列表
      *<功能简述>
      *<功能详细描述>
      * @param includeInvalidOrganization
      * @return [参数说明]
      * 
      * @return List<Organization> [返回类型说明]
      * @exception throws [异常类型] [异常说明]
      * @see [类、类#方法、类#成员]
     */
    public List<Organization> queryOrganizationListByAuth(
            boolean includeInvalidOrganization) {
        Organization currentOrgnization = WebContextUtils.getOrganizationFromSession();
        String parentOrganizationId = "";
        if (currentOrgnization != null) {
            parentOrganizationId = currentOrgnization.getId();
        }
        
        List<Organization> resList = null;
        //如果拥有查询所有组织的权限
        if (WebContextUtils.hasAuth("query_all_org_post")) {
            //生成查询条件
            Map<String, Object> params = new HashMap<String, Object>();
            if (!includeInvalidOrganization) {
                params.put("valid", true);
            }
            resList = this.organizationDao.queryOrganizationList(params);
        } else {
            resList = queryChildOrganizationListByParentId(parentOrganizationId,
                    includeInvalidOrganization);
        }
        
        return resList;
    }
    
    /**
      * 查询所有组织的列表<br/>
      *<功能详细描述>
      * @return [参数说明]
      * 
      * @return List<Organization> [返回类型说明]
      * @exception throws [异常类型] [异常说明]
      * @see [类、类#方法、类#成员]
     */
    public List<Organization> queryAllOrganizationList() {
        List<Organization> resList = this.organizationDao.queryOrganizationList(null);
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
            String parentOrganizationId, boolean includeInvalidOrganization) {
        //生成查询条件
        List<Organization> resList = null;
        if (StringUtils.isEmpty(parentOrganizationId)) {
            //生成查询条件
            Map<String, Object> params = new HashMap<String, Object>();
            if (!includeInvalidOrganization) {
                params.put("valid", true);
            }
            resList = this.organizationDao.queryOrganizationList(params);
        } else {
            resList = queryChildOrganizationListByParentId(parentOrganizationId,
                    includeInvalidOrganization);
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
            String parentOrganizationId, boolean includeInvalidOrganization) {
        AssertUtils.notEmpty(parentOrganizationId,
                "parentOrganizationId is empty");
        //生成查询条件
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("parentId", parentOrganizationId);
        //如果不包括无效组织
        if (!includeInvalidOrganization) {
            params.put("valid", true);
        }
        
        List<Organization> resList = new ArrayList<Organization>();
        //根据实际情况，填入排序字段等条件，根据是否需要排序，选择调用dao内方法
        List<Organization> childList = this.organizationDao.queryOrganizationList(params);
        if (!CollectionUtils.isEmpty(childList)) {
            resList.addAll(childList);
            for (Organization childTemp : childList) {
                if (childTemp == null) {
                    continue;
                }
                resList.addAll(queryChildOrganizationListByParentId(childTemp.getId(),
                        includeInvalidOrganization));
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
            String parentOrganizationId, boolean includeInvalidOrganization) {
        AssertUtils.notEmpty(parentOrganizationId,
                "parentOrganizationId is empty");
        
        List<Organization> orgList = queryChildOrganizationListByParentId(parentOrganizationId,
                includeInvalidOrganization);
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
      * 停用指定组织
      *<功能详细描述>
      * @param organizationId
      * @return [参数说明]
      * 
      * @return boolean [返回类型说明]
      * @exception throws [异常类型] [异常说明]
      * @see [类、类#方法、类#成员]
     */
    public boolean disableOrganizationById(String organizationId) {
        AssertUtils.notEmpty(organizationId, "organizationId is empty.");
        
        //获取对应组织
        List<Organization> childs = queryOrganizationListByParentId(organizationId,
                false);
        AssertUtils.isEmpty(childs, "valid child organization is exist");
        
        Map<String, Object> updateRowMap = new HashMap<String, Object>();
        updateRowMap.put("id", organizationId);
        updateRowMap.put("valid", false);
        
        int updateRowCount = this.organizationDao.updateOrganization(updateRowMap);
        return updateRowCount > 0;
    }
    
    /**
      * 启用指定组织
      *<功能详细描述>
      * @param organizationId
      * @return [参数说明]
      * 
      * @return boolean [返回类型说明]
      * @exception throws [异常类型] [异常说明]
      * @see [类、类#方法、类#成员]
     */
    public boolean enableOrganizationById(String organizationId) {
        AssertUtils.notEmpty(organizationId, "organizationId is empty.");
        
        Map<String, Object> updateRowMap = new HashMap<String, Object>();
        updateRowMap.put("id", organizationId);
        updateRowMap.put("valid", true);
        
        int updateRowCount = this.organizationDao.updateOrganization(updateRowMap);
        return updateRowCount > 0;
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
     *      6、如果组织被其他模块所引用，组织的删除将会影响到其他模块
     *      7、所以组织的删除将不会真正被执行，组织紧紧只能停用，如果需要真正的删除
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
        
        Organization condition = new Organization();
        condition.setId(id);
        return this.organizationDao.deleteOrganization(condition);
    }
    
    /** 组织转换为树节点的适配器 */
    private static final TreeNodeAdapter<Organization> organizationAdapter = new TreeNodeAdapter<Organization>() {
        
        @Override
        public String getId(Organization obj) {
            return obj.getId();
        }
        
        @Override
        public int getType(Organization obj) {
            return OperatorConstants.TREENODE_TYPE_ORGANIZATION;
        }
        
        @Override
        public String getParentId(Organization obj) {
            return obj.getParentId();
        }
        
        @Override
        public String getName(Organization obj) {
            return obj.getName();
        }
    };
    
    private static final TreeNodeAdapter<Post> postAdapter = new TreeNodeAdapter<Post>() {
        
        @Override
        public String getId(Post obj) {
            return obj.getId();
        }
        
        @Override
        public int getType(Post obj) {
            return OperatorConstants.TREENODE_TYPE_POST;
        }
        
        @Override
        public String getParentId(Post obj) {
            return obj.getParentId();
        }
        
        @Override
        public String getName(Post obj) {
            return obj.getName();
        }
    };
}
