/*
 * 描          述:  <描述>
 * 修  改   人:  
 * 修改时间:  
 * <修改描述:>
 */
package com.tx.component.operator.service;

import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.tx.component.operator.dao.PostTypeDao;
import com.tx.component.operator.model.PostType;
import com.tx.core.exceptions.util.AssertUtils;
import com.tx.core.paged.model.PagedList;

/**
 * PostType的业务层
 * <功能详细描述>
 * 
 * @author  
 * @version  [版本号, ]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
@Component("postTypeService")
public class PostTypeService {
    
    @SuppressWarnings("unused")
    private Logger logger = LoggerFactory.getLogger(PostTypeService.class);
    
    @Resource(name = "postTypeDao")
    private PostTypeDao postTypeDao;
    
    /**
      * 将postType实例插入数据库中保存
      * 1、如果postType为空时抛出参数为空异常
      * 2、如果postType中部分必要参数为非法值时抛出参数不合法异常
     * <功能详细描述>
     * @param postType [参数说明]
     * 
     * @return void [返回类型说明]
     * @exception throws
     * @see [类、类#方法、类#成员]
    */
    @Transactional
    public void insertPostType(PostType postType) {
        //验证参数是否合法
        AssertUtils.notNull(postType, "postType is null.");
        AssertUtils.notEmpty(postType.getCode(), "postType.code is empty.");
        AssertUtils.notEmpty(postType.getName(), "postType.code is empty.");
        
        //为添加的数据需要填入默认值的字段填入默认值
        Date now = new Date();
        postType.setCreateDate(now);
        postType.setLastUpdateDate(now);
        postType.setValid(true);
        
        //调用数据持久层对实体进行持久化操作
        this.postTypeDao.insertPostType(postType);
    }
    
    /**
     * 根据id删除postType实例
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
        
        PostType condition = new PostType();
        condition.setId(id);
        int resInt = this.postTypeDao.deletePostType(condition);
        return resInt > 0;
    }
    
    /**
      * 根据Id查询PostType实体
      * 1、当id为empty时抛出异常
      * <功能详细描述>
      * @param id
      * @return [参数说明]
      * 
      * @return PostType [返回类型说明]
      * @exception throws 可能存在数据库访问异常DataAccessException
      * @see [类、类#方法、类#成员]
     */
    public PostType findPostTypeById(String id) {
        AssertUtils.notEmpty(id, "id is empty.");
        
        PostType condition = new PostType();
        condition.setId(id);
        
        PostType res = this.postTypeDao.findPostType(condition);
        return res;
    }
    
    /**
      * 查询PostType实体列表
      *     不包含无效的实体
      * <功能详细描述>
      * @param code
      * @param name
      *       
      * @return [参数说明]
      * 
      * @return List<PostType> [返回类型说明]
      * @exception throws [异常类型] [异常说明]
      * @see [类、类#方法、类#成员]
      */
    public List<PostType> queryPostTypeList(String code, String name) {
        //判断条件合法性
        
        //生成查询条件
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("code", code);
        params.put("name", name);
        params.put("valid", true);
        
        //根据实际情况，填入排序字段等条件，根据是否需要排序，选择调用dao内方法
        List<PostType> resList = this.postTypeDao.queryPostTypeList(params);
        
        return resList;
    }
    
    /**
     * 查询PostType实体列表,
     *		如果实体集合中不包含appointId对应的实体将根据该id查询到对应的实体然后加入到集合中（appointId被禁用的情况）
     *     appointId对应的实体如果不存在，系统将抛出异常（appointId被删除的情况）
     * <功能详细描述>
     * @param code
     * @param name
     *       
     * @return [参数说明]
     * 
     * @return List<PostType> [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    public List<PostType> queryPostTypeListIncludeAppointId(String code,
            String name, String appointId) {
        //判断条件合法性
        AssertUtils.notEmpty(appointId, "appointId is empty.");
        
        //生成查询条件
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("code", code);
        params.put("name", name);
        params.put("valid", true);
        
        //根据实际情况，填入排序字段等条件，根据是否需要排序，选择调用dao内方法
        List<PostType> resList = this.postTypeDao.queryPostTypeList(params);
        
        Set<String> idSet = new HashSet<String>();
        for (PostType temp : resList) {
            idSet.add(temp.getId());
        }
        if (!idSet.contains(appointId)) {
            PostType findRes = findPostTypeById(appointId);
            resList.add(0, findRes);
        }
        
        return resList;
    }
    
    /**
      * 查询包含已经停用的PostType实体列表
      * <功能详细描述>
      * @param code
      * @param name
      *       
      * @return [参数说明]
      * 
      * @return List<PostType> [返回类型说明]
      * @exception throws [异常类型] [异常说明]
      * @see [类、类#方法、类#成员]
     */
    public List<PostType> queryPostTypeListIncludeInvalid(String code,
            String name) {
        //判断条件合法性
        
        //生成查询条件
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("code", code);
        params.put("name", name);
        
        //根据实际情况，填入排序字段等条件，根据是否需要排序，选择调用dao内方法
        List<PostType> resList = this.postTypeDao.queryPostTypeList(params);
        
        return resList;
    }
    
    /**
     * 分页查询PostType实体列表
      *     不包含无效的实体
      * @param code
      * @param name
     * @param pageIndex 当前页index从1开始计算
     * @param pageSize 每页显示行数
     * 
     * <功能详细描述>
     * @return [参数说明]
     * 
     * @return List<PostType> [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
    */
    public PagedList<PostType> queryPostTypePagedList(String code, String name,
            int pageIndex, int pageSize) {
        //T判断条件合法性
        
        //生成查询条件
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("code", code);
        params.put("name", name);
        params.put("valid", true);
        
        //根据实际情况，填入排序字段等条件，根据是否需要排序，选择调用dao内方法
        PagedList<PostType> resPagedList = this.postTypeDao.queryPostTypePagedList(params,
                pageIndex,
                pageSize);
        
        return resPagedList;
    }
    
    /**
     * 分页查询PostType实体列表
     *     包含无效的的实体
      * @param code
      * @param name
     * @param pageIndex 当前页index从1开始计算
     * @param pageSize 每页显示行数
     * 
     * <功能详细描述>
     * @return [参数说明]
     * 
     * @return List<PostType> [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
    */
    public PagedList<PostType> queryPostTypePagedListIncludeInvalid(
            String code, String name, int pageIndex, int pageSize) {
        //T判断条件合法性
        
        //生成查询条件
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("code", code);
        params.put("name", name);
        
        //根据实际情况，填入排序字段等条件，根据是否需要排序，选择调用dao内方法
        PagedList<PostType> resPagedList = this.postTypeDao.queryPostTypePagedList(params,
                pageIndex,
                pageSize);
        
        return resPagedList;
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
        params.put("excludeId", excludeId);
        
        //根据实际情况，填入排序字段等条件，根据是否需要排序，选择调用dao内方法
        int res = this.postTypeDao.countPostType(params);
        
        return res > 0;
    }
    
    /**
      * 根据id更新对象
      * <功能详细描述>
      * @param postType
      * @return [参数说明]
      * 
      * @return boolean [返回类型说明]
      * @exception throws [异常类型] [异常说明]
      * @see [类、类#方法、类#成员]
     */
    @Transactional
    public boolean updateById(PostType postType) {
        //验证参数是否合法，必填字段是否填写，
        AssertUtils.notNull(postType, "postType is null.");
        AssertUtils.notEmpty(postType.getId(), "postType.id is empty.");
        
        //生成需要更新字段的hashMap
        Map<String, Object> updateRowMap = new HashMap<String, Object>();
        updateRowMap.put("id", postType.getId());
        
        //需要更新的字段
        updateRowMap.put("code", postType.getCode());
        updateRowMap.put("remark", postType.getRemark());
        updateRowMap.put("lastUpdateDate", new Date());
        updateRowMap.put("name", postType.getName());
        int updateRowCount = this.postTypeDao.updatePostType(updateRowMap);
        
        //如果需要大于1时，抛出异常并回滚，需要在这里修改
        return updateRowCount >= 1;
    }
    
    /**
     * 根据id禁用PostType<br/>
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
        
        //生成查询条件
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("id", id);
        params.put("valid", false);
        
        this.postTypeDao.updatePostType(params);
        
        return true;
    }
    
    /**
      * 根据id启用PostType<br/>
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
        
        this.postTypeDao.updatePostType(params);
        
        return true;
    }
}
