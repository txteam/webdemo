/*
 * 描          述:  <描述>
 * 修  改   人:  
 * 修改时间:  
 * <修改描述:>
 */
package com.tx.component.operator.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.tx.component.operator.dao.OperatorRefDao;
import com.tx.component.operator.model.OperatorRef;
import com.tx.core.exceptions.util.AssertUtils;
import com.tx.core.paged.model.PagedList;

/**
 * OperatorRef的业务层
 * <功能详细描述>
 * 
 * @author  
 * @version  [版本号, ]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
@Component("operatorRefService")
public class OperatorRefService {
    
    @SuppressWarnings("unused")
    private Logger logger = LoggerFactory.getLogger(OperatorRefService.class);
    
    @Resource(name = "operatorRefDao")
    private OperatorRefDao operatorRefDao;
    
    /**
      * 将operatorRef实例插入数据库中保存
      * 1、如果operatorRef为空时抛出参数为空异常
      * 2、如果operatorRef中部分必要参数为非法值时抛出参数不合法异常
     * <功能详细描述>
     * @param district [参数说明]
     * 
     * @return void [返回类型说明]
     * @exception throws
     * @see [类、类#方法、类#成员]
    */
    @Transactional
    public void insertOperatorRef(OperatorRef operatorRef) {
        //TODO:验证参数是否合法，必填字段是否填写，
        AssertUtils.notNull(operatorRef, "operatorRef is null.");
        AssertUtils.notEmpty(operatorRef.getOperatorId(), "operatorRef.operatorId is empty.");
        
        this.operatorRefDao.insertOperatorRef(operatorRef);
    }
      
     /**
      * 根据operatorId删除operatorRef实例
      * 1、如果入参数为空，则抛出异常
      * 2、执行删除后，将返回数据库中被影响的条数
      * @param operatorId
      * @return 返回删除的数据条数，<br/>
      * 有些业务场景，如果已经被别人删除同样也可以认为是成功的
      * 这里讲通用生成的业务层代码定义为返回影响的条数
      * @return int [返回类型说明]
      * @exception throws 
      * @see [类、类#方法、类#成员]
     */
    @Transactional
    public int deleteByOperatorId(String operatorId) {
        AssertUtils.notEmpty(operatorId, "operatorId is empty.");
        
        OperatorRef condition = new OperatorRef();
        condition.setOperatorId(operatorId);
        return this.operatorRefDao.deleteOperatorRef(condition);
    }
    
    /**
      * 根据OperatorId查询OperatorRef实体
      * 1、当operatorId为empty时抛出异常
      * <功能详细描述>
      * @param operatorId
      * @return [参数说明]
      * 
      * @return OperatorRef [返回类型说明]
      * @exception throws 可能存在数据库访问异常DataAccessException
      * @see [类、类#方法、类#成员]
     */
    public OperatorRef findOperatorRefByOperatorId(String operatorId) {
        AssertUtils.notEmpty(operatorId, "operatorId is empty.");
        
        OperatorRef condition = new OperatorRef();
        condition.setOperatorId(operatorId);
        return this.operatorRefDao.findOperatorRef(condition);
    }
    
    /**
      * 根据OperatorRef实体列表
      * TODO:补充说明
      * 
      * <功能详细描述>
      * @return [参数说明]
      * 
      * @return List<OperatorRef> [返回类型说明]
      * @exception throws [异常类型] [异常说明]
      * @see [类、类#方法、类#成员]
     */
    public List<OperatorRef> queryOperatorRefList(/*TODO:自己定义条件*/) {
        //TODO:判断条件合法性
        
        //TODO:生成查询条件
        Map<String, Object> params = new HashMap<String, Object>();
        
        //TODO:根据实际情况，填入排序字段等条件，根据是否需要排序，选择调用dao内方法
        List<OperatorRef> resList = this.operatorRefDao.queryOperatorRefList(params);
        
        return resList;
    }
    
    /**
     * 分页查询OperatorRef实体列表
     * TODO:补充说明
     * 
     * <功能详细描述>
     * @return [参数说明]
     * 
     * @return List<OperatorRef> [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
    */
    public PagedList<OperatorRef> queryOperatorRefPagedList(/*TODO:自己定义条件*/int pageIndex,
            int pageSize) {
        //TODO:判断条件合法性
        
        //TODO:生成查询条件
        Map<String, Object> params = new HashMap<String, Object>();
        
        //TODO:根据实际情况，填入排序字段等条件，根据是否需要排序，选择调用dao内方法
        PagedList<OperatorRef> resPagedList = this.operatorRefDao.queryOperatorRefPagedList(params, pageIndex, pageSize);
        
        return resPagedList;
    }
    
    /**
      * 查询operatorRef列表总条数
      * TODO:补充说明
      * <功能详细描述>
      * @return [参数说明]
      * 
      * @return int [返回类型说明]
      * @exception throws [异常类型] [异常说明]
      * @see [类、类#方法、类#成员]
     */
    public int countOperatorRef(/*TODO:自己定义条件*/){
        //TODO:判断条件合法性
        
        //TODO:生成查询条件
        Map<String, Object> params = new HashMap<String, Object>();
        
        //TODO:根据实际情况，填入排序字段等条件，根据是否需要排序，选择调用dao内方法
        int res = this.operatorRefDao.countOperatorRef(params);
        
        return res;
    }
    
    /**
      * 根据operatorId更新对象
      * <功能详细描述>
      * @param operatorRef
      * @return [参数说明]
      * 
      * @return boolean [返回类型说明]
      * @exception throws [异常类型] [异常说明]
      * @see [类、类#方法、类#成员]
     */
    @Transactional
    public boolean updateByOperatorId(OperatorRef operatorRef) {
        //TODO:验证参数是否合法，必填字段是否填写，
        AssertUtils.notNull(operatorRef, "operatorRef is null.");
        AssertUtils.notEmpty(operatorRef.getOperatorId(), "operatorRef.operatorId is empty.");
        
        
        //TODO:生成需要更新字段的hashMap
        Map<String, Object> updateRowMap = new HashMap<String, Object>();
        updateRowMap.put("operatorId", operatorRef.getOperatorId());
        
        //TODO:需要更新的字段
		updateRowMap.put("refId", operatorRef.getRefId());	
		updateRowMap.put("endDate", operatorRef.getEndDate());	
		updateRowMap.put("createDate", operatorRef.getCreateDate());	
		updateRowMap.put("effectiveDate", operatorRef.getEffectiveDate());	
		updateRowMap.put("refType", operatorRef.getRefType());	
        
        int updateRowCount = this.operatorRefDao.updateOperatorRef(updateRowMap);
        
        //TODO:如果需要大于1时，抛出异常并回滚，需要在这里修改
        return updateRowCount >= 1;
    }
}
