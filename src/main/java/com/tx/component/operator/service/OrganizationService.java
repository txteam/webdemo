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

import com.tx.component.mainframe.servicelog.SystemOperateLog;
import com.tx.component.mainframe.treeview.TreeNode;
import com.tx.component.mainframe.treeview.TreeNodeAdapter;
import com.tx.component.operator.OperatorConstants;
import com.tx.component.operator.dao.OrganizationDao;
import com.tx.component.operator.model.Organization;
import com.tx.component.operator.model.OrganizationTypeEnum;
import com.tx.component.operator.model.Post;
import com.tx.component.servicelog.context.ServiceLoggerContext;
import com.tx.core.TxConstants;
import com.tx.core.exceptions.argument.IllegalArgException;
import com.tx.core.exceptions.util.AssertUtils;
import com.tx.core.util.MessageUtils;
import com.tx.core.util.UUIDUtils;

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
@Component("organizationService")
public class OrganizationService {
    
    @SuppressWarnings("unused")
    private Logger logger = LoggerFactory.getLogger(OrganizationService.class);
    
    @Resource(name = "organizationDao")
    private OrganizationDao organizationDao;
    
    @Resource(name = "postService")
    private PostService postService;
    
    /**
      * 根据组织ID生成虚中心id
      *<功能详细描述>
      * @param organizationId
      * @return [参数说明]
      * 
      * @return String [返回类型说明]
      * @exception throws [异常类型] [异常说明]
      * @see [类、类#方法、类#成员]
     */
    private String generateVcid(Organization organization) {
        AssertUtils.notEmpty(organization.getId(), "organization.id is empty.");
        //如果不存在父级组织时，对应组织类型应当为公司或分公司
        if (StringUtils.isEmpty(organization.getParentId())) {
            if (!OrganizationTypeEnum.公司.equals(organization.getType())
                    && !OrganizationTypeEnum.分公司.equals(organization.getType())) {
                throw new IllegalArgException(
                        "organization.parent is null and type is not company");
            }
        }
        
        String vcid = null;
        if (OrganizationTypeEnum.公司.equals(organization.getType())
                || OrganizationTypeEnum.分公司.equals(organization.getType())) {
            vcid = organization.getId();
        } else {
            Organization parentOrg = findOrganizationById(organization.getParentId());
            vcid = generateVcid(parentOrg);
        }
        
        return vcid;
    }
    
    /**
      * 根据虚中心id查询组织职位数据列表<br/>
      *     在sqlmap中利用是否能够查询所有组织的权限控制，是否能够查询所有组织
      *     如果没有查询所有组织的权限，则仅能查询当前登录人员所属虚中心的组织
      *<功能详细描述>
      * @return [参数说明]
      * 
      * @return List<TreeNode> [返回类型说明]
      * @exception throws [异常类型] [异常说明]
      * @see [类、类#方法、类#成员]
     */
    public List<TreeNode> queryOrganizationPostTreeNodeList() {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("valid", true);
        List<Organization> orgList = this.organizationDao.queryOrganizationList(params);
        
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
        
        //生成组织唯一键
        organization.setId(UUIDUtils.generateUUID());
        organization.setValid(true);
        
        //生成组织全名
        organization.setFullName(generateOrganizationFullName(organization.getParentId(),
                organization.getName()));
        
        //获取当前组织所属虚拟中心并设值
        organization.setVcid(generateVcid(organization));
        
        //插入组织实体
        this.organizationDao.insertOrganization(organization);
        
        //记录操作日志
        ServiceLoggerContext.getLogger(SystemOperateLog.class)
                .log(new SystemOperateLog("webdemo", "新增组织",
                        MessageUtils.createMessage("新增组织[{}]",
                                new Object[] { organization.getName() }), null));
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
      * 查询组织列表（包括已经被禁用的组织）
      *<功能简述>
      *<功能详细描述>
      * @param virtualCenterId 允许为空，如果为空时，查询当前人员能够查询的虚中心列表
      * @return [参数说明]
      * 
      * @return List<Organization> [返回类型说明]
      * @exception throws [异常类型] [异常说明]
      * @see [类、类#方法、类#成员]
     */
    public List<Organization> queryOrganizationListIncludeInvalid(
            String virtualCenterId) {
        Map<String, Object> params = new HashMap<String, Object>();
        if (!StringUtils.isEmpty(virtualCenterId)) {
            params.put("vcid", virtualCenterId);
        }
        List<Organization> orgList = this.organizationDao.queryOrganizationList(params);
        
        return orgList;
    }
    
    /**
     * 查询组织列表
     *<功能详细描述>
     * @return [参数说明]
     * 
     * @return List<Organization> [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
    */
    public List<Organization> queryOrganizationList() {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("valid", true);
        List<Organization> orgList = this.organizationDao.queryOrganizationList(params);
        
        return orgList;
    }
    
    /**
     * 查询组织id列表
     *<功能详细描述>
     * @return [参数说明]
     * 
     * @return List<Organization> [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
    */
    public List<String> queryOrganizationIdList() {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("valid", true);
        List<Organization> orgList = this.organizationDao.queryOrganizationList(params);
        
        List<String> orgIdList = new ArrayList<String>();
        if (CollectionUtils.isEmpty(orgList)) {
            return orgIdList;
        }
        for (Organization orgTemp : orgList) {
            orgIdList.add(orgTemp.getId());
        }
        return orgIdList;
    }
    
    /**
      * 根据父级组织id查询下一级组织列表<br/>
      *<功能详细描述>
      * @param parentOrganizationId
      * @param isIncludeInvalidOrganization
      * @return [参数说明]
      * 
      * @return List<Organization> [返回类型说明]
      * @exception throws [异常类型] [异常说明]
      * @see [类、类#方法、类#成员]
     */
    private List<Organization> queryOrganizationListByParentId(
            String parentOrganizationId, boolean isIncludeInvalidOrganization) {
        AssertUtils.notEmpty(parentOrganizationId,
                "parentOrganizationId is empty");
        
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("parentId", parentOrganizationId);
        if (!isIncludeInvalidOrganization) {
            params.put("valid", true);
        }
        
        List<Organization> orgList = this.organizationDao.queryOrganizationList(params);
        return orgList;
    }
    
    /**
      * 更新当前组织名信息时，需要将子组织的全名进行更新<br/>
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
            List<Organization> childOrganizationList, String newParentFullName) {
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
        updateRowMap.put("alias", organization.getAlias());
        updateRowMap.put("fullAddress", organization.getFullAddress());
        updateRowMap.put("remark", organization.getRemark());
        updateRowMap.put("code", organization.getCode());
        //updateRowMap.put("type", organization.getType());
        updateRowMap.put("address", organization.getAddress());
        updateRowMap.put("name", organization.getName());
        updateRowMap.put("chiefType", organization.getChiefType());
        updateRowMap.put("chiefId", organization.getChiefId());
        updateRowMap.put("fullName",
                generateOrganizationFullName(organization.getParentId(),
                        organization.getName()));
        
        int updateRowCount = this.organizationDao.updateOrganization(updateRowMap);
        
        //记录操作日志
        ServiceLoggerContext.getLogger(SystemOperateLog.class)
                .log(new SystemOperateLog("webdemo", "更新组织",
                        MessageUtils.createMessage("更新组织[{}]",
                                new Object[] { organization.getName() }), null));
        
        //如果需要大于1时，抛出异常并回滚，需要在这里修改
        return updateRowCount >= 1;
    }
    
    /**
     * 如果不存在尚未被禁用子组织则可以被禁用
     *<功能详细描述>
     * @param id
     * @return [参数说明]
     * 
     * @return boolean [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
    */
    public boolean isDisableAble(String id) {
        Map<String, Object> countParams = new HashMap<String, Object>();
        countParams.put("parentId", id);
        countParams.put("valid", true);
        
        int res = this.organizationDao.countOrganization(countParams);
        return res == 0;
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
    public boolean disableOrganizationById(String organizationId) {
        AssertUtils.notEmpty(organizationId, "organizationId is empty.");
        
        //查询下级组织是否都已经禁用
        List<Organization> childs = queryOrganizationListByParentId(organizationId,
                false);
        AssertUtils.isEmpty(childs, "valid child organization is exist");
        
        Map<String, Object> updateRowMap = new HashMap<String, Object>();
        updateRowMap.put("id", organizationId);
        updateRowMap.put("valid", false);
        
        //记录操作日志
        ServiceLoggerContext.getLogger(SystemOperateLog.class)
                .log(new SystemOperateLog(
                        "webdemo",
                        "停用组织",
                        MessageUtils.createMessage("停用组织[{}]",
                                new Object[] { findOrganizationById(organizationId).getName() }),
                        null));
        
        int updateRowCount = this.organizationDao.updateOrganization(updateRowMap);
        
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
    public boolean enableOrganizationById(String organizationId) {
        AssertUtils.notEmpty(organizationId, "organizationId is empty.");
        
        Map<String, Object> updateRowMap = new HashMap<String, Object>();
        updateRowMap.put("id", organizationId);
        updateRowMap.put("valid", true);
        
        //记录操作日志
        ServiceLoggerContext.getLogger(SystemOperateLog.class)
                .log(new SystemOperateLog(
                        "webdemo",
                        "启用组织",
                        MessageUtils.createMessage("启用组织[{}]",
                                new Object[] { findOrganizationById(organizationId).getName() }),
                        null));
        
        int updateRowCount = this.organizationDao.updateOrganization(updateRowMap);
        
        return updateRowCount > 0;
    }
    
    /**
      * 如果不存在子组织则可以被删除
      *<功能详细描述>
      * @param id
      * @return [参数说明]
      * 
      * @return boolean [返回类型说明]
      * @exception throws [异常类型] [异常说明]
      * @see [类、类#方法、类#成员]
     */
    public boolean isDeleteAble(String id) {
        Map<String, Object> countParams = new HashMap<String, Object>();
        countParams.put("parentId", id);
        
        int res = this.organizationDao.countOrganization(countParams);
        return res == 0;
    }
    
    /**
     * 根据id删除organization实例
     *      1、如果入参数为空，则抛出异常
     *      2、考虑如果是级联删除，影响较大，可能一个误操作引起不必要的错误，
     *             而且与组织关联的对象可能很多，组织一旦被启用后，删除后对原系统冲击过大
     *             在未建外键关联的情况下，不允许轻易删除组织
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
        
        //如果存在子级组织不能被删除
        List<Organization> childs = queryOrganizationListByParentId(id, true);
        AssertUtils.isTrue(CollectionUtils.isEmpty(childs), "对应组织尚存在子级组织不能被删除.");
        
        //如果不存在则可以被删除
        Organization condition = new Organization();
        condition.setId(id);
        Organization res = this.organizationDao.findOrganization(condition);
        if (res == null) {
            return false;
        }
        
        //记录操作日志
        ServiceLoggerContext.getLogger(SystemOperateLog.class)
                .log(new SystemOperateLog("webdemo", "删除组织",
                        MessageUtils.createMessage("删除组织[{}]",
                                new Object[] { res.getName() }), null));
        //将要删除的组织信息写入历史表中
        this.organizationDao.insertOrganizationToHis(res);
        
        return this.organizationDao.deleteOrganization(condition) > 0;
    }
    
    /** 组织转换为树节点的适配器 */
    private static final TreeNodeAdapter<Organization> organizationAdapter = new TreeNodeAdapter<Organization>() {
        
        public Object getTarget(Organization obj) {
            return obj;
        }
        
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
    
    /** 职位与树节点转换的适配器 */
    private static final TreeNodeAdapter<Post> postAdapter = new TreeNodeAdapter<Post>() {
        
        public Object getTarget(Post obj) {
            return obj;
        }
        
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
            String parentId = StringUtils.isEmpty(obj.getParentId()) ? obj.getOrganization()
                    .getId()
                    : obj.getParentId();
            return parentId;
        }
        
        @Override
        public String getName(Post obj) {
            return obj.getName();
        }
    };
}
