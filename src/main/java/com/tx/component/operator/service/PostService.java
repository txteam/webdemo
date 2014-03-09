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

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.tx.component.mainframe.servicelog.SystemOperateLog;
import com.tx.component.operator.dao.PostDao;
import com.tx.component.operator.model.Organization;
import com.tx.component.operator.model.Post;
import com.tx.component.servicelog.context.ServiceLoggerContext;
import com.tx.core.exceptions.util.AssertUtils;
import com.tx.core.util.MessageUtils;

/**
 * Post的业务层
 * <功能详细描述>
 * 
 * @author  
 * @version  [版本号, ]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
@Component("postService")
public class PostService {
    
    @SuppressWarnings("unused")
    private Logger logger = LoggerFactory.getLogger(PostService.class);
    
    @Resource(name = "postDao")
    private PostDao postDao;
    
    @Resource(name = "organizationService")
    private OrganizationService organizationService;
    
    /**
      * 将组织信息装载入职位信息中
      *<功能详细描述>
      * @param postList [参数说明]
      * 
      * @return void [返回类型说明]
      * @exception throws [异常类型] [异常说明]
      * @see [类、类#方法、类#成员]
     */
    private void setupOrganizationInfo(List<Post> postList) {
        if (CollectionUtils.isEmpty(postList)) {
            return;
        }
        
        for (Post post : postList) {
            setupOrganizationInfo(post);
        }
    }
    
    /**
      * 为职位装载组织信息<br/>
      *<功能详细描述>
      * @param post [参数说明]
      * 
      * @return void [返回类型说明]
      * @exception throws [异常类型] [异常说明]
      * @see [类、类#方法、类#成员]
     */
    private void setupOrganizationInfo(Post post) {
        //当职位中的所在组织不存在，或已经将所在组织的组织名已经进行了装载的则不重复进行装载
        if (post == null || post.getOrganization() == null
                || StringUtils.isEmpty(post.getOrganization().getId())
                || !StringUtils.isEmpty(post.getOrganization().getName())) {
            return;
        }
        
        Organization org = this.organizationService.findOrganizationById(post.getOrganization()
                .getId());
        if (org != null) {
            post.setOrganization(org);
        }
    }
    
    /**
      * 将post实例插入数据库中保存
      * 1、如果post为空时抛出参数为空异常
      * 2、如果post中部分必要参数为非法值时抛出参数不合法异常
     * <功能详细描述>
     * @param district [参数说明]
     * 
     * @return void [返回类型说明]
     * @exception throws
     * @see [类、类#方法、类#成员]
    */
    @Transactional
    public void insertPost(Post post) {
        //验证参数是否合法，必填字段是否填写，
        AssertUtils.notNull(post, "post is null.");
        AssertUtils.notEmpty(post.getCode(), "post.code is empty.");
        AssertUtils.notEmpty(post.getName(), "post.name is empty.");
        AssertUtils.notNull(post.getOrganization(),
                "post.organization is null.");
        AssertUtils.notEmpty(post.getOrganization().getId(),
                "post.organization.id is empty.");
        
        post.setValid(true);
        //查询所在组织
        Organization org = this.organizationService.findOrganizationById(post.getOrganization()
                .getId());
        AssertUtils.notNull(org,
                "post.organization.id:{} is not exist.",
                post.getOrganization().getId());
        
        //父级组织应该与指定的组织一致
        if (!StringUtils.isEmpty(post.getParentId())) {
            Post parentPost = findPostById(post.getParentId());
            AssertUtils.notNull(parentPost,
                    "parentPostId:{} is not exist.",
                    post.getParentId());
            AssertUtils.isTrue(org.getId().equals(parentPost.getOrganization()
                    .getId()));
        }
        
        //生成职位全名
        String postFullName = org.getFullName() + post.getName();
        post.setFullName(postFullName);
        
        this.postDao.insertPost(post);
        
        //记录操作日志
        ServiceLoggerContext.getLogger(SystemOperateLog.class)
                .log(new SystemOperateLog("webdemo", "新增职位",
                        MessageUtils.createMessage("新增职位[{}]",
                                new Object[] { post.getName() }), null));
    }
    
    /**
     * 根据id删除post实例
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
        
        //校验业务意义上的删除是否合法
        List<Post> childPostList = queryPostListByParentId(id);
        AssertUtils.isEmpty(childPostList, "职位存在下级职位不能被删除");
        Post res = findPostById(id);
        AssertUtils.notNull(res, "Post id:{} is not exist", id);
        //将要删除的数据放入历史表
        this.postDao.insertPostToHis(res);
        
        //删除
        Post condition = new Post();
        condition.setId(id);
        
        //记录操作日志
        ServiceLoggerContext.getLogger(SystemOperateLog.class)
                .log(new SystemOperateLog("webdemo", "删除职位",
                        MessageUtils.createMessage("删除职位[{}]",
                                new Object[] { findPostById(id).getName() }),
                        null));
        
        int resInt = this.postDao.deletePost(condition);
        return resInt > 0;
    }
    
    /**
      * 根据Id查询Post实体
      * 1、当id为empty时抛出异常
      * <功能详细描述>
      * @param id
      * @return [参数说明]
      * 
      * @return Post [返回类型说明]
      * @exception throws 可能存在数据库访问异常DataAccessException
      * @see [类、类#方法、类#成员]
     */
    public Post findPostById(String id) {
        AssertUtils.notEmpty(id, "id is empty.");
        
        Post condition = new Post();
        condition.setId(id);
        Post res = this.postDao.findPost(condition);
        
        //为查询结果装载组织信息
        setupOrganizationInfo(res);
        return res;
    }
    
    /**
      * 查询包括无效职位在内的职位列表<br/>
      *<功能详细描述>
      * @return [参数说明]
      * 
      * @return List<Post> [返回类型说明]
      * @exception throws [异常类型] [异常说明]
      * @see [类、类#方法、类#成员]
     */
    public List<Post> queryPostListIncludeInvalid(String parentPostId,
            String organizationId) {
        List<Post> resList = null;
        
        if (StringUtils.isEmpty(parentPostId)
                && StringUtils.isEmpty(organizationId)) {
            //生成查询条件
            Map<String, Object> params = new HashMap<String, Object>();
            resList = this.postDao.queryPostList(params);
        } else if (!StringUtils.isEmpty(organizationId)) {
            //生成查询条件
            Map<String, Object> params = new HashMap<String, Object>();
            //传入了organizationId,权限判断植入的代码即不生效
            params.put("organizationId", organizationId);
            resList = this.postDao.queryPostList(params);
        } else {
            //生成查询条件
            Map<String, Object> params = new HashMap<String, Object>();
            params.put("parentId", parentPostId);
            resList = this.postDao.queryPostList(params);
        }
        
        //为查询结果装载组织信息
        setupOrganizationInfo(resList);
        return resList;
    }
    
    /**
      * 根据权限查看职位列表
      *     如果没有查看所有组织职位的权限，
      *     则默认只能查看当前组织及其下级的组织含有的职位<br/>
      *<功能详细描述>
      * @return [参数说明]
      * 
      * @return List<Post> [返回类型说明]
      * @exception throws [异常类型] [异常说明]
      * @see [类、类#方法、类#成员]
     */
    public List<Post> queryPostList() {
        //生成查询条件
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("valid", true);
        //在底层查询中，会根据权限，查询当前人员能够看到的所有职位
        //如果为超级管理员则能查看所有的组织的所有职位
        List<Post> resList = this.postDao.queryPostList(params);
        
        //为查询结果装载组织信息
        setupOrganizationInfo(resList);
        return resList;
    }
    
    /**
      * 根据父级职位id查询子集职位id
      *<功能详细描述>
      * @param parentPostId
      * @return [参数说明]
      * 
      * @return List<Post> [返回类型说明]
      * @exception throws [异常类型] [异常说明]
      * @see [类、类#方法、类#成员]
     */
    public List<Post> queryPostListByParentId(String parentPostId) {
        AssertUtils.notEmpty(parentPostId, "parentPostId is empty");
        
        //生成查询条件
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("valid", true);
        params.put("parentId", parentPostId);
        
        List<Post> resList = this.postDao.queryPostList(params);
        
        //为查询结果装载组织信息
        setupOrganizationInfo(resList);
        return resList;
    }
    
    /**
      * 根据组织id查询职位列表<br/>
      *<功能详细描述>
      * @param organizationId
      * @return [参数说明]
      * 
      * @return List<Post> [返回类型说明]
      * @exception throws [异常类型] [异常说明]
      * @see [类、类#方法、类#成员]
     */
    public List<Post> queryPostListByOrganizationId(String organizationId) {
        AssertUtils.notEmpty(organizationId, "organizationId is empty");
        
        //生成查询条件
        Map<String, Object> params = new HashMap<String, Object>();
        //传入了organizationId,权限判断植入的代码即不生效
        params.put("organizationId", organizationId);
        params.put("valid", true);
        
        List<Post> resList = this.postDao.queryPostList(params);
        
        //为查询结果装载组织信息
        setupOrganizationInfo(resList);
        
        return resList;
    }
    
    /**
      * 判断职位编码是否已经存在<br/>
      * <功能详细描述>
      * @return [参数说明]
      * 
      * @return int [返回类型说明]
      * @exception throws [异常类型] [异常说明]
      * @see [类、类#方法、类#成员]
     */
    public boolean postCodeIsExist(String code, String excludePostId) {
        AssertUtils.notEmpty(code, "code is empty");
        
        //生成查询条件
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("code", code);
        params.put("excludePostId", excludePostId);
        
        //根据实际情况，填入排序字段等条件，根据是否需要排序，选择调用dao内方法
        int res = this.postDao.countPost(params);
        
        return res > 0;
    }
    
    /**
      * 根据id更新对象
      * <功能详细描述>
      * @param post
      * @return [参数说明]
      * 
      * @return boolean [返回类型说明]
      * @exception throws [异常类型] [异常说明]
      * @see [类、类#方法、类#成员]
     */
    @Transactional
    public boolean updateById(Post post) {
        AssertUtils.notNull(post, "post is null.");
        AssertUtils.notEmpty(post.getId(), "post.id is empty.");
        
        //生成需要更新字段的hashMap
        Map<String, Object> updateRowMap = new HashMap<String, Object>();
        updateRowMap.put("id", post.getId());
        updateRowMap.put("parentId", post.getParentId());
        updateRowMap.put("organization", post.getOrganization());
        
        updateRowMap.put("remark", post.getRemark());
        updateRowMap.put("name", post.getName());
        updateRowMap.put("fullName", post.getFullName());
        updateRowMap.put("code", post.getCode());
        
        //记录操作日志
        ServiceLoggerContext.getLogger(SystemOperateLog.class)
                .log(new SystemOperateLog("webdemo", "更新职位",
                        MessageUtils.createMessage("更新职位[{}]",
                                new Object[] { post.getName() }), null));
        int updateRowCount = this.postDao.updatePost(updateRowMap);
        
        //如果需要大于1时，抛出异常并回滚，需要在这里修改
        return updateRowCount >= 1;
    }
    
    /**
      * 是否可删除<br/> 
      * 如果存子职位则不能被删除
      * @param postId
      * @return [参数说明]
      * 
      * @return boolean [返回类型说明]
      * @exception throws [异常类型] [异常说明]
      * @see [类、类#方法、类#成员]
     */
    public boolean isDeleteAble(String postId) {
        AssertUtils.notEmpty(postId, "postId is empty.");
        
        //生成查询条件
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("parentId", postId);
        //利用该条件能够屏蔽掉底层sql中权限查询限定的逻辑
        params.put("organization", new Organization());
        
        int count = this.postDao.countPost(params);
        boolean flag = count <= 0;
        
        return flag;
    }
    
    /**
      * 是否可禁用<BR/>
      *     如果子级职位存在尚未被禁用的职位，则该职位不能被禁用<br/>
      *<功能详细描述>
      * @param postId
      * @return [参数说明]
      * 
      * @return boolean [返回类型说明]
      * @exception throws [异常类型] [异常说明]
      * @see [类、类#方法、类#成员]
     */
    public boolean isDisableAble(String postId) {
        AssertUtils.notEmpty(postId, "postId is empty.");
        
        //生成查询条件
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("valid", true);
        params.put("parentId", postId);
        //利用该条件能够屏蔽掉底层sql中权限查询限定的逻辑
        params.put("organization", new Organization());
        
        int count = this.postDao.countPost(params);
        boolean flag = count <= 0;
        
        return flag;
    }
    
    /**
      * 根据职位id禁用职位<br/>
      *<功能详细描述>
      * @param postId
      * @return [参数说明]
      * 
      * @return boolean [返回类型说明]
      * @exception throws [异常类型] [异常说明]
      * @see [类、类#方法、类#成员]
     */
    @Transactional
    public boolean disableById(String postId) {
        AssertUtils.notEmpty(postId, "postId is empty.");
        
        //生成查询条件
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("id", postId);
        params.put("valid", false);
        
        //记录操作日志
        ServiceLoggerContext.getLogger(SystemOperateLog.class)
                .log(new SystemOperateLog(
                        "webdemo",
                        "禁用职位",
                        MessageUtils.createMessage("禁用职位[{}]",
                                new Object[] { findPostById(postId).getName() }),
                        null));
        
        this.postDao.updatePost(params);
        
        return true;
    }
    
    /**
     * 根据职位id启用职位<br/>
      *<功能详细描述>
      * @param postId
      * @return [参数说明]
      * 
      * @return boolean [返回类型说明]
      * @exception throws [异常类型] [异常说明]
      * @see [类、类#方法、类#成员]
     */
    @Transactional
    public boolean enableById(String postId) {
        AssertUtils.notEmpty(postId, "postId is empty.");
        
        //生成查询条件
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("id", postId);
        params.put("valid", true);
        
        //记录操作日志
        ServiceLoggerContext.getLogger(SystemOperateLog.class)
                .log(new SystemOperateLog(
                        "webdemo",
                        "启用职位",
                        MessageUtils.createMessage("启用职位[{}]",
                                new Object[] { findPostById(postId).getName() }),
                        null));
        
        this.postDao.updatePost(params);
        
        return true;
    }
    
}
