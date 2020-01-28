/*
 * 描述: <描述>
 * 修改人:  
 * 修改时间:  
 * <修改描述:>
 */
package com.tx.local.loanaccount.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.tx.core.exceptions.util.AssertUtils;
import com.tx.core.paged.model.PagedList;
import com.tx.local.loanaccount.dao.LADeductRecordDao;
import com.tx.local.loanaccount.model.LADeductRecord;

/**
 * DeductRecord的业务层
 * <功能详细描述>
 * 
 * @author  
 * @version  [版本号, ]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
@Component("deductRecordService")
public class LADeductRecordService {
    
    @SuppressWarnings("unused")
    private Logger logger = LoggerFactory.getLogger(LADeductRecordService.class);
    
    @Resource(name = "laDeductRecordDao")
    private LADeductRecordDao deductRecordDao;
    
   /**
    * 将deductRecord实例插入数据库中保存<br />
    * 1、如果deductRecord为空时抛出参数为空异常<br />
    * 2、如果deductRecord中部分必要参数为非法值时抛出参数不合法异常<br />
    *
    * <功能详细描述>
    * 
    * @param district [参数说明]
    * 
    * @return void [返回类型说明]
    * @exception throws
    * @see [类、类#方法、类#成员]
    */
    @Transactional
    public void insert(LADeductRecord deductRecord) {
        //TODO:验证参数是否合法
        AssertUtils.notNull(deductRecord, "deductRecord is null.");
       	
        //TODO: 设置默认数据
        
        this.deductRecordDao.insert(deductRecord);
    }
      
    /**
     * 根据id删除deductRecord实例<br />
     * 1、如果入参数为空，则抛出异常<br />
     * 2、执行删除后，将返回数据库中被影响的条数<br />
     * 有些业务场景，如果已经被别人删除同样也可以认为是成功的<br />
     * 这里讲通用生成的业务层代码定义为返回影响的条数<br />
     *
     * @param id
     *
     * @return 返回删除的数据条数<br/>
     * @exception throws 
     * @see [类、类#方法、类#成员]
     */
    @Transactional
    public int deleteById(String id) {
        AssertUtils.notEmpty(id, "id is empty.");
        
        LADeductRecord condition = new LADeductRecord();
        condition.setId(id);
        return this.deductRecordDao.delete(condition);
    }
    
    /**
      * 根据Id查询DeductRecord实体
      * 1、当id为empty时抛出异常
      * <功能详细描述>
      * @param id
      * @return [参数说明]
      * 
      * @return DeductRecord [返回类型说明]
      * @exception throws 可能存在数据库访问异常DataAccessException
      * @see [类、类#方法、类#成员]
     */
    public LADeductRecord findById(String id) {
        AssertUtils.notEmpty(id, "id is empty.");
        
        LADeductRecord condition = new LADeductRecord();
        condition.setId(id);
        
        LADeductRecord res = this.deductRecordDao.find(condition);
        return res;
    }
    
    /**
      * 根据DeductRecord实体列表
      * <功能详细描述>
      * @return [参数说明]
      * 
      * @return List<DeductRecord> [返回类型说明]
      * @exception throws [异常类型] [异常说明]
      * @see [类、类#方法、类#成员]
     */
    public List<LADeductRecord> queryList(Map<String,Object> params) {
        params = params == null ? new HashMap<String, Object>() : params;
        
        //根据实际情况，填入排序字段等条件，根据是否需要排序，选择调用dao内方法
        List<LADeductRecord> resList = this.deductRecordDao.queryList(params);
        
        return resList;
    }
    
    /**
     * 分页查询DeductRecord实体列表
     * <功能详细描述>
     * @return [参数说明]
     * 
     * @return List<DeductRecord> [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
    */
    public PagedList<LADeductRecord> queryPagedList(Map<String,Object> params,int pageIndex,
            int pageSize) {
        params = params == null ? new HashMap<String, Object>() : params;
        
        //根据实际情况，填入排序字段等条件，根据是否需要排序，选择调用dao内方法
        PagedList<LADeductRecord> resPagedList = this.deductRecordDao.queryPagedList(params, pageIndex, pageSize);
        
        return resPagedList;
    }
    
    /**
      * 查询deductRecord列表总条数
      * <功能详细描述>
      * @return [参数说明]
      * 
      * @return int [返回类型说明]
      * @exception throws [异常类型] [异常说明]
      * @see [类、类#方法、类#成员]
     */
    public int count(Map<String,Object> params){
        params = params == null ? new HashMap<String, Object>() : params;
        
        //根据实际情况，填入排序字段等条件，根据是否需要排序，选择调用dao内方法
        int res = this.deductRecordDao.count(params);
        
        return res;
    }
    
    /**
      * 根据id更新对象
      * <功能详细描述>
      * @param deductRecord
      * @return [参数说明]
      * 
      * @return boolean [返回类型说明]
      * @exception throws [异常类型] [异常说明]
      * @see [类、类#方法、类#成员]
     */
    @Transactional
    public boolean updateById(LADeductRecord deductRecord) {
        //验证参数是否合法，必填字段是否填写，
        AssertUtils.notNull(deductRecord, "deductRecord is null.");
        AssertUtils.notEmpty(deductRecord.getId(), "deductRecord.id is empty.");
        
        
        //生成需要更新字段的hashMap
        Map<String, Object> updateRowMap = new HashMap<String, Object>();
        updateRowMap.put("id", deductRecord.getId());
        
        //TODO:需要更新的字段
		updateRowMap.put("loanAccountId", deductRecord.getLoanAccountId());	
		updateRowMap.put("tradingRecordId", deductRecord.getTradingRecordId());	
		//type:java.lang.String
		updateRowMap.put("bankAccount", deductRecord.getBankAccount());
		updateRowMap.put("bankAccountType", deductRecord.getBankAccountType());	
		updateRowMap.put("completeCount", deductRecord.getCompleteCount());	
		updateRowMap.put("completeDate", deductRecord.getCompleteDate());	
		updateRowMap.put("completeSum", deductRecord.getCompleteSum());	
		updateRowMap.put("count", deductRecord.getCount());	
		updateRowMap.put("createDate", deductRecord.getCreateDate());	
		updateRowMap.put("failSum", deductRecord.getFailSum());	
		updateRowMap.put("lastUpdateDate", deductRecord.getLastUpdateDate());	
		updateRowMap.put("status", deductRecord.getStatus());	
		updateRowMap.put("successSum", deductRecord.getSuccessSum());	
		updateRowMap.put("sum", deductRecord.getSum());	
		updateRowMap.put("type", deductRecord.getType());	
        
        int updateRowCount = this.deductRecordDao.update(updateRowMap);
        
        //如果需要大于1时，抛出异常并回滚，需要在这里修改
        return updateRowCount >= 1;
    }
}
