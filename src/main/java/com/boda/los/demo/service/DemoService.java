/*
 * 描          述:  <描述>
 * 修  改   人:  
 * 修改时间:  
 * <修改描述:>
 */
package com.boda.los.demo.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.boda.los.demo.dao.DemoDao;
import com.boda.los.demo.model.Demo;
import com.tx.core.exceptions.parameter.ParameterIsEmptyException;
import com.tx.core.paged.model.PagedList;

/**
 * Demo的业务层
 * <功能详细描述>
 * 
 * @author  
 * @version  [版本号, ]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
@Component("demoService")
public class DemoService {
    
    @SuppressWarnings("unused")
    private Logger logger = LoggerFactory.getLogger(DemoService.class);
    
    @SuppressWarnings("unused")
    //@Resource(name = "serviceLogger")
    private Logger serviceLogger;
    
    @Resource(name = "demoDao")
    private DemoDao demoDao;
    
    /**
      * 根据Id查询Demo实体
      * 1、当id为empty时返回null
      * <功能详细描述>
      * @param id
      * @return [参数说明]
      * 
      * @return Demo [返回类型说明]
      * @exception throws 可能存在数据库访问异常DataAccessException
      * @see [类、类#方法、类#成员]
     */
    public Demo findDemoById(String id) {
        if (StringUtils.isEmpty(id)) {
            return null;
        }
        
        Demo condition = new Demo();
        condition.setId(id);
        return this.demoDao.findDemo(condition);
    }
    
    /**
      * 根据Demo实体列表
      * TODO:补充说明
      * 
      * <功能详细描述>
      * @return [参数说明]
      * 
      * @return List<Demo> [返回类型说明]
      * @exception throws [异常类型] [异常说明]
      * @see [类、类#方法、类#成员]
     */
    public List<Demo> queryDemoList(/*TODO:自己定义条件*/) {
        //TODO:判断条件合法性
        
        //TODO:生成查询条件
        Map<String, Object> params = new HashMap();
        
        //TODO:根据实际情况，填入排序字段等条件，根据是否需要排序，选择调用dao内方法
        List<Demo> resList = this.demoDao.queryDemoList(params);
        
        return resList;
    }
    
    /**
     * 分页查询Demo实体列表
     * TODO:补充说明
     * 
     * <功能详细描述>
     * @return [参数说明]
     * 
     * @return List<Demo> [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
    */
    public PagedList<Demo> queryDemoPagedList(/*TODO:自己定义条件*/int pageIndex,
            int pageSize) {
        //TODO:判断条件合法性
        
        //TODO:生成查询条件
        Map<String, Object> params = new HashMap<String, Object>();
        
        //TODO:根据实际情况，填入排序字段等条件，根据是否需要排序，选择调用dao内方法
        PagedList<Demo> resPagedList = this.demoDao.queryDemoPagedList(params, pageIndex, pageSize);
        
        return resPagedList;
    }
    
    /**
      * 查询demo列表总条数
      * TODO:补充说明
      * <功能详细描述>
      * @return [参数说明]
      * 
      * @return int [返回类型说明]
      * @exception throws [异常类型] [异常说明]
      * @see [类、类#方法、类#成员]
     */
    public int countDemo(/*TODO:自己定义条件*/){
        //TODO:判断条件合法性
        
        //TODO:生成查询条件
        Map<String, Object> params = new HashMap<String, Object>();
        
        //TODO:根据实际情况，填入排序字段等条件，根据是否需要排序，选择调用dao内方法
        int res = this.demoDao.countDemo(params);
        
        return res;
    }
    
    /**
      * 将demo实例插入数据库中保存
      * 1、如果demo为空时抛出参数为空异常
      * 2、如果demo中部分必要参数为非法值时抛出参数不合法异常
      * <功能详细描述>
      * @param demo [参数说明]
      * 
      * @return void [返回类型说明]
      * @exception throws 可能存在数据库访问异常DataAccessException
      * @see [类、类#方法、类#成员]
     */
    @Transactional
    public void insertDemo(Demo demo) {
        //TODO:验证参数是否合法，必填字段是否填写，
        //如果没有填写抛出parameterIsEmptyException,
        //如果有参数不合法ParameterIsInvalidException
        if (demo == null /*TODO:|| 其他参数验证*/) {
            throw new ParameterIsEmptyException(
                    "DemoService.insertDemo demo isNull.");
        }
        
        this.demoDao.insertDemo(demo);
    }
    
    /**
      * 根据id删除demo实例
      * 1、如果入参数为空，则抛出异常
      * 2、执行删除后，将返回数据库中被影响的条数
      * @param id
      * @return 返回删除的数据条数，<br/>
      * 有些业务场景，如果已经被别人删除同样也可以认为是成功的
      * 这里讲通用生成的业务层代码定义为返回影响的条数
      * @return int [返回类型说明]
      * @exception throws 可能存在数据库访问异常DataAccessException
      * @see [类、类#方法、类#成员]
     */
    @Transactional
    public int deleteById(String id) {
        if (StringUtils.isEmpty(id)) {
            throw new ParameterIsEmptyException(
                    "DemoService.deleteById id isEmpty.");
        }
        
        Demo condition = new Demo();
        condition.setId(id);
        return this.demoDao.deleteDemo(condition);
    }
    
    /**
      * 根据id更新对象
      * <功能详细描述>
      * @param demo
      * @return [参数说明]
      * 
      * @return boolean [返回类型说明]
      * @exception throws [异常类型] [异常说明]
      * @see [类、类#方法、类#成员]
     */
    @Transactional
    public boolean updateById(Demo demo) {
        //TODO:验证参数是否合法，必填字段是否填写，
        //如果没有填写抛出parameterIsEmptyException,
        //如果有参数不合法ParameterIsInvalidException
        if (demo == null || StringUtils.isEmpty(demo.getId())) {
            throw new ParameterIsEmptyException(
                    "DemoService.updateById demo or demo.id is empty.");
        }
        
        //TODO:生成需要更新字段的hashMap
        Map<String, Object> updateRowMap = new HashMap<String, Object>();
        updateRowMap.put("id", demo.getId());
        
        //TODO:需要更新的字段
		updateRowMap.put("parentId", demo.getParentId());	
		updateRowMap.put("email", demo.getEmail());	
		updateRowMap.put("description", demo.getDescription());	
		updateRowMap.put("name", demo.getName());	
		//type:java.lang.String
		updateRowMap.put("subDemo", demo.getSubDemo());
		updateRowMap.put("createDate", demo.getCreateDate());	
		updateRowMap.put("password", demo.getPassword());	
		updateRowMap.put("isValid", demo.getIsValid());	
		updateRowMap.put("loginName", demo.getLoginName());	
		updateRowMap.put("lastUpdateDate", demo.getLastUpdateDate());	
        
        int updateRowCount = this.demoDao.updateDemo(updateRowMap);
        
        //TODO:如果需要大于1时，抛出异常并回滚，需要在这里修改
        return updateRowCount >= 1;
    }
}
