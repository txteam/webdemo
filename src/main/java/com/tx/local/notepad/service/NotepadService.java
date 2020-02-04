/*
 * 描          述:  <描述>
 * 修  改   人:  
 * 修改时间:  
 * <修改描述:>
 */
package com.tx.local.notepad.service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.tx.local.notepad.dao.NotepadDao;
import com.tx.local.notepad.model.Notepad;
import com.tx.core.exceptions.util.AssertUtils;
import com.tx.core.paged.model.PagedList;
import com.tx.core.querier.model.Querier;
import com.tx.core.querier.model.QuerierBuilder;

/**
 * 记事本的业务层[NotepadService]
 * <功能详细描述>
 * 
 * @author  
 * @version [版本号]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
@Component("notepadService")
public class NotepadService {
    
    @SuppressWarnings("unused")
    private Logger logger = LoggerFactory.getLogger(NotepadService.class);
    
    @Resource(name = "notepadDao")
    private NotepadDao notepadDao;
    
    /**
     * 新增记事本实例<br/>
     * 将notepad插入数据库中保存
     * 1、如果notepad 为空时抛出参数为空异常
     * 2、如果notepad 中部分必要参数为非法值时抛出参数不合法异常
     * 
     * @param notepad [参数说明]
     * @return void [返回类型说明]
     * @exception throws
     * @see [类、类#方法、类#成员]
     */
    @Transactional
    public void save(Notepad notepad) {
        //验证参数是否合法
        AssertUtils.notNull(notepad, "notepad is null.");
        AssertUtils.notEmpty(notepad.getType(), "notepad.type is empty.");
        AssertUtils.notEmpty(notepad.getTopicType(),
                "notepad.topicType is empty.");
        AssertUtils.notEmpty(notepad.getTopicId(), "notepad.topicId is empty.");
        AssertUtils.notEmpty(notepad.getTitle(), "notepad.title is empty.");
        AssertUtils.notEmpty(notepad.getVcid(), "notepad.vcid is empty.");
        
        if (StringUtils.isEmpty(notepad.getId())) {
            insert(notepad);
        } else {
            Notepad dbNotepad = find(notepad);
            if (dbNotepad == null) {
                notepad.setId(null);
                insert(notepad);
            } else {
                dbNotepad.setTitle(notepad.getTitle());
                dbNotepad.setContent(notepad.getContent());
                updateById(dbNotepad);
            }
        }
    }
    
    /**
     * 新增记事本实例<br/>
     * 将notepad插入数据库中保存
     * 1、如果notepad 为空时抛出参数为空异常
     * 2、如果notepad 中部分必要参数为非法值时抛出参数不合法异常
     * 
     * @param notepad [参数说明]
     * @return void [返回类型说明]
     * @exception throws
     * @see [类、类#方法、类#成员]
     */
    @Transactional
    public void insert(Notepad notepad) {
        //验证参数是否合法
        AssertUtils.notNull(notepad, "notepad is null.");
        AssertUtils.notEmpty(notepad.getType(), "notepad.type is empty.");
        AssertUtils.notEmpty(notepad.getTopicType(),
                "notepad.topicType is empty.");
        AssertUtils.notEmpty(notepad.getTopicId(), "notepad.topicId is empty.");
        AssertUtils.notEmpty(notepad.getTitle(), "notepad.title is empty.");
        AssertUtils.notEmpty(notepad.getVcid(), "notepad.vcid is empty.");
        
        //为添加的数据需要填入默认值的字段填入默认值
        notepad.setLastUpdateDate(new Date());
        notepad.setCreateDate(new Date());
        
        //调用数据持久层对实例进行持久化操作
        this.notepadDao.insert(notepad);
    }
    
    /**
     * 根据id删除记事本实例
     * 1、如果入参数为空，则抛出异常
     * 2、执行删除后，将返回数据库中被影响的条数 > 0，则返回true
     *
     * @param id
     * @return boolean 删除的条数>0则为true [返回类型说明]
     * @exception throws 
     * @see [类、类#方法、类#成员]
     */
    @Transactional
    public boolean delete(Notepad condition) {
        AssertUtils.notNull(condition, "condition is null.");
        AssertUtils.notEmpty(condition.getId(), "condition.id is empty.");
        
        int resInt = this.notepadDao.delete(condition);
        boolean flag = resInt > 0;
        return flag;
    }
    
    /**
     * 根据id删除记事本实例
     * 1、如果入参数为空，则抛出异常
     * 2、执行删除后，将返回数据库中被影响的条数 > 0，则返回true
     *
     * @param id
     * @return boolean 删除的条数>0则为true [返回类型说明]
     * @exception throws 
     * @see [类、类#方法、类#成员]
     */
    @Transactional
    public boolean deleteById(String id) {
        AssertUtils.notEmpty(id, "id is empty.");
        
        Notepad condition = new Notepad();
        condition.setId(id);
        
        int resInt = this.notepadDao.delete(condition);
        boolean flag = resInt > 0;
        return flag;
    }
    
    /**
     * 根据id查询记事本实例
     * 1、当id为empty时抛出异常
     *
     * @param id
     * @return Notepad [返回类型说明]
     * @exception throws
     * @see [类、类#方法、类#成员]
     */
    public Notepad find(Notepad condition) {
        AssertUtils.notNull(condition, "condition is null.");
        AssertUtils.notEmpty(condition.getId(), "condition.id is empty.");
        
        Notepad res = this.notepadDao.find(condition);
        return res;
    }
    
    /**
     * 根据id查询记事本实例
     * 1、当id为empty时抛出异常
     *
     * @param id
     * @return Notepad [返回类型说明]
     * @exception throws
     * @see [类、类#方法、类#成员]
     */
    public Notepad findById(String id) {
        AssertUtils.notEmpty(id, "id is empty.");
        
        Notepad condition = new Notepad();
        condition.setId(id);
        
        Notepad res = this.notepadDao.find(condition);
        return res;
    }
    
    /**
     * 查询记事本实例列表
     * <功能详细描述>
     * @param params      
     * @return [参数说明]
     * 
     * @return List<Notepad> [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    public List<Notepad> queryList(Map<String, Object> params) {
        //判断条件合法性
        
        //生成查询条件
        params = params == null ? new HashMap<String, Object>() : params;
        
        //根据实际情况，填入排序字段等条件，根据是否需要排序，选择调用dao内方法
        List<Notepad> resList = this.notepadDao.queryList(params);
        
        return resList;
    }
    
    /**
     * 查询记事本实例列表
     * <功能详细描述>
     * @param querier      
     * @return [参数说明]
     * 
     * @return List<Notepad> [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    public List<Notepad> queryList(Querier querier) {
        //生成查询条件
        querier = querier == null ? QuerierBuilder.newInstance().querier()
                : querier;
        
        //根据实际情况，填入排序字段等条件，根据是否需要排序，选择调用dao内方法
        List<Notepad> resList = this.notepadDao.queryList(querier);
        
        return resList;
    }
    
    /**
     * 分页查询记事本实例列表
     * <功能详细描述>
     * @param params    
     * @param pageIndex 当前页index从1开始计算
     * @param pageSize 每页显示行数
     * 
     * <功能详细描述>
     * @return [参数说明]
     * 
     * @return List<Notepad> [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    public PagedList<Notepad> queryPagedList(Map<String, Object> params,
            int pageIndex, int pageSize) {
        //T判断条件合法性
        
        //生成查询条件
        params = params == null ? new HashMap<String, Object>() : params;
        
        //根据实际情况，填入排序字段等条件，根据是否需要排序，选择调用dao内方法
        PagedList<Notepad> resPagedList = this.notepadDao.queryPagedList(params,
                pageIndex,
                pageSize);
        
        return resPagedList;
    }
    
    /**
     * 分页查询记事本实例列表
     * <功能详细描述>
     * @param querier    
     * @param pageIndex 当前页index从1开始计算
     * @param pageSize 每页显示行数
     * 
     * <功能详细描述>
     * @return [参数说明]
     * 
     * @return List<Notepad> [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    public PagedList<Notepad> queryPagedList(Querier querier, int pageIndex,
            int pageSize) {
        //T判断条件合法性
        
        //生成查询条件
        querier = querier == null ? QuerierBuilder.newInstance().querier()
                : querier;
        
        //根据实际情况，填入排序字段等条件，根据是否需要排序，选择调用dao内方法
        PagedList<Notepad> resPagedList = this.notepadDao
                .queryPagedList(querier, pageIndex, pageSize);
        
        return resPagedList;
    }
    
    /**
     * 查询记事本实例数量<br/>
     * <功能详细描述>
     * @param params      
     * @return [参数说明]
     * 
     * @return List<Notepad> [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    public int count(Map<String, Object> params) {
        //判断条件合法性
        
        //生成查询条件
        params = params == null ? new HashMap<String, Object>() : params;
        
        //根据实际情况，填入排序字段等条件，根据是否需要排序，选择调用dao内方法
        int res = this.notepadDao.count(params);
        
        return res;
    }
    
    /**
     * 查询记事本实例数量<br/>
     * <功能详细描述>
     * @param querier      
     * @return [参数说明]
     * 
     * @return List<Notepad> [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    public int count(Querier querier) {
        //判断条件合法性
        
        //生成查询条件
        querier = querier == null ? QuerierBuilder.newInstance().querier()
                : querier;
        
        //根据实际情况，填入排序字段等条件，根据是否需要排序，选择调用dao内方法
        int res = this.notepadDao.count(querier);
        
        return res;
    }
    
    /**
     * 判断记事本实例是否已经存在<br/>
     * <功能详细描述>
     * @param key2valueMap
     * @param excludeId
     * @return
     * 
     * @return int [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    public boolean exists(Map<String, String> key2valueMap, String excludeId) {
        AssertUtils.notEmpty(key2valueMap, "key2valueMap is empty");
        
        //生成查询条件
        Map<String, Object> params = new HashMap<String, Object>();
        params.putAll(key2valueMap);
        
        //根据实际情况，填入排序字段等条件，根据是否需要排序，选择调用dao内方法
        int res = this.notepadDao.count(params, excludeId);
        
        return res > 0;
    }
    
    /**
     * 判断记事本实例是否已经存在<br/>
     * <功能详细描述>
     * @param key2valueMap
     * @param excludeId
     * @return
     * 
     * @return int [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    public boolean exists(Querier querier, String excludeId) {
        AssertUtils.notNull(querier, "querier is null.");
        
        //根据实际情况，填入排序字段等条件，根据是否需要排序，选择调用dao内方法
        int res = this.notepadDao.count(querier, excludeId);
        
        return res > 0;
    }
    
    /**
     * 根据id更新记事本实例<br/>
     * <功能详细描述>
     * @param notepad
     * @return [参数说明]
     * 
     * @return boolean [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @Transactional
    public boolean updateById(String id, Notepad notepad) {
        //验证参数是否合法，必填字段是否填写
        AssertUtils.notNull(notepad, "notepad is null.");
        AssertUtils.notEmpty(id, "id is empty.");
        AssertUtils.notEmpty(notepad.getTitle(), "notepad.title is empty.");
        
        //生成需要更新字段的hashMap
        Map<String, Object> updateRowMap = new HashMap<String, Object>();
        //需要更新的字段
        updateRowMap.put("type", notepad.getType());
        updateRowMap.put("topicId", notepad.getTopicId());
        updateRowMap.put("topicType", notepad.getTopicType());
        updateRowMap.put("vcid", notepad.getVcid());
        
        updateRowMap.put("lastUpdateUserId", notepad.getLastUpdateUserId());
        updateRowMap.put("lastUpdateDate", new Date());
        
        updateRowMap.put("title", notepad.getTitle());
        updateRowMap.put("content", notepad.getContent());
        updateRowMap.put("catalog", notepad.getCatalog());
        updateRowMap.put("remark", notepad.getRemark());
        updateRowMap.put("attributes", notepad.getAttributes());
        
        boolean flag = this.notepadDao.update(id, updateRowMap);
        //如果需要大于1时，抛出异常并回滚，需要在这里修改
        return flag;
    }
    
    /**
     * 根据id更新记事本实例<br/>
     * <功能详细描述>
     * @param notepad
     * @return [参数说明]
     * 
     * @return boolean [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @Transactional
    public boolean updateById(Notepad notepad) {
        //验证参数是否合法，必填字段是否填写
        AssertUtils.notNull(notepad, "notepad is null.");
        AssertUtils.notEmpty(notepad.getId(), "notepad.id is empty.");
        
        boolean flag = updateById(notepad.getId(), notepad);
        //如果需要大于1时，抛出异常并回滚，需要在这里修改
        return flag;
    }
}
