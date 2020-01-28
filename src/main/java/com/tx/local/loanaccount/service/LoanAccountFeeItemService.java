/*
 * 描          述:  <描述>
 * 修  改   人:  
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
import com.tx.local.basicdata.model.FeeItemEnum;
import com.tx.local.loanaccount.dao.LoanAccountFeeItemDao;
import com.tx.local.loanaccount.model.LoanAccountFeeItem;

/**
 * LoanAccountFeeCfgItem的业务层
 * <功能详细描述>
 * 
 * @author  
 * @version  [版本号, ]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
@Component("loanAccountFeeItemService")
public class LoanAccountFeeItemService {
    
    @SuppressWarnings("unused")
    private Logger logger = LoggerFactory
            .getLogger(LoanAccountFeeItemService.class);
    
    @Resource(name = "loanAccountFeeItemDao")
    private LoanAccountFeeItemDao loanAccountFeeItemDao;
    
    /** 
     * 插入前置验证<br/>
     * <功能详细描述>
     * @param loanAccountFeeCfgItem [参数说明]
     * 
     * @return void [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    private void validate4insert(LoanAccountFeeItem loanAccountFeeCfgItem) {
        AssertUtils.notNull(loanAccountFeeCfgItem,
                "loanAccountFeeCfgItem is null.");
        AssertUtils.notEmpty(loanAccountFeeCfgItem.getLoanAccountId(),
                "loanAccountFeeCfgItem.loanAccountId is empty.");
        AssertUtils.notNull(loanAccountFeeCfgItem.getFeeItem(),
                "loanAccountFeeCfgItem.feeItem is null.");
    }
    
    /**
      * 将loanAccountFeeCfgItem实例插入数据库中保存
      * 1、如果loanAccountFeeCfgItem为空时抛出参数为空异常
      * 2、如果loanAccountFeeCfgItem中部分必要参数为非法值时抛出参数不合法异常
     * <功能详细描述>
     * @param district [参数说明]
     * 
     * @return void [返回类型说明]
     * @exception throws
     * @see [类、类#方法、类#成员]
    */
    @Transactional
    public void insert(LoanAccountFeeItem loanAccountFeeCfgItem) {
        //验证参数是否合法
        validate4insert(loanAccountFeeCfgItem);
        
        //设置默认数据
        this.loanAccountFeeItemDao.insert(loanAccountFeeCfgItem);
    }
    
    /**
     * 批量插入贷款账户费用设置项<br/>
     *<功能详细描述>
     * @param loanAccountFeeCfgItems [参数说明]
     * 
     * @return void [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
    */
    @Transactional
    public void batchInsert(List<LoanAccountFeeItem> loanAccountFeeCfgItems) {
        AssertUtils.notEmpty(loanAccountFeeCfgItems,
                "loanAccountFeeCfgItems is empty.");
        for (LoanAccountFeeItem feeCfgItem : loanAccountFeeCfgItems) {
            validate4insert(feeCfgItem);
        }
        this.loanAccountFeeItemDao.batchInsert(loanAccountFeeCfgItems);
    }
    
    /**
     * 根据id删除loanAccountFeeCfgItem实例
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
    public int deleteById(String id) {
        AssertUtils.notEmpty(id, "id is empty.");
        
        LoanAccountFeeItem condition = new LoanAccountFeeItem();
        condition.setId(id);
        return this.loanAccountFeeItemDao.delete(condition);
    }
    
    /**
      * 根据Id查询LoanAccountFeeCfgItem实体
      * 1、当id为empty时抛出异常
      * <功能详细描述>
      * @param id
      * @return [参数说明]
      * 
      * @return LoanAccountFeeCfgItem [返回类型说明]
      * @exception throws 可能存在数据库访问异常DataAccessException
      * @see [类、类#方法、类#成员]
     */
    public LoanAccountFeeItem findById(String id) {
        AssertUtils.notEmpty(id, "id is empty.");
        
        LoanAccountFeeItem condition = new LoanAccountFeeItem();
        condition.setId(id);
        
        LoanAccountFeeItem res = this.loanAccountFeeItemDao.find(condition);
        return res;
    }
    
    /**
     * 根据loanAccountId 、FeeItem 获取费用配置
     * 
     * <功能详细描述>
     * @return [参数说明]
     * 
     * @return List<LoanAccountFeeCfgItem> [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    public LoanAccountFeeItem find(String loanAccountId, FeeItemEnum feeItem) {
        AssertUtils.notEmpty(loanAccountId, "loanAccountId is empty.");
        AssertUtils.notNull(feeItem, "feeItem is empty.");
        
        //生成查询条件
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("loanAccountId", loanAccountId);
        params.put("feeItem", feeItem);
        
        //根据实际情况，填入排序字段等条件，根据是否需要排序，选择调用dao内方法
        List<LoanAccountFeeItem> resList = this.loanAccountFeeItemDao
                .queryList(params);
        AssertUtils.isTrue(resList.size() < 2,
                "id为:{},费用项为:{}的贷款单账户存在多条相同的费用项配置.",
                new Object[] { loanAccountId, feeItem });
        
        return resList.get(0);
    }
    
    /**
     * 根据LoanAccountFeeCfgItem实体列表
     * <功能详细描述>
     * @return [参数说明]
     * 
     * @return List<LoanAccountFeeCfgItem> [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
    */
    public List<LoanAccountFeeItem> queryListByLoanAccountId(
            String loanAccountId) {
        AssertUtils.notEmpty(loanAccountId, "loanAccountId is empty.");
        
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("loanAccountId", loanAccountId);
        
        //根据实际情况，填入排序字段等条件，根据是否需要排序，选择调用dao内方法
        List<LoanAccountFeeItem> resList = this.loanAccountFeeItemDao
                .queryList(params);
        
        return resList;
    }
    
    /**
     * 根据LoanAccountFeeCfgItem实体列表
     * <功能详细描述>
     * @return [参数说明]
     * 
     * @return List<LoanAccountFeeCfgItem> [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    public List<LoanAccountFeeItem> queryList(Map<String, Object> params) {
        params = params == null ? new HashMap<String, Object>() : params;
        
        //根据实际情况，填入排序字段等条件，根据是否需要排序，选择调用dao内方法
        List<LoanAccountFeeItem> resList = this.loanAccountFeeItemDao
                .queryList(params);
        
        return resList;
    }
    
    /**
     * 分页查询LoanAccountFeeCfgItem实体列表
     * <功能详细描述>
     * @return [参数说明]
     * 
     * @return List<LoanAccountFeeCfgItem> [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    public PagedList<LoanAccountFeeItem> queryPagedList(
            Map<String, Object> params, int pageIndex, int pageSize) {
        params = params == null ? new HashMap<String, Object>() : params;
        
        //根据实际情况，填入排序字段等条件，根据是否需要排序，选择调用dao内方法
        PagedList<LoanAccountFeeItem> resPagedList = this.loanAccountFeeItemDao
                .queryPagedList(params, pageIndex, pageSize);
        
        return resPagedList;
    }
    
    /**
     * 查询loanAccountFeeCfgItem列表总条数
     * <功能详细描述>
     * @return [参数说明]
     * 
     * @return int [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    public int count(Map<String, Object> params) {
        params = params == null ? new HashMap<String, Object>() : params;
        
        //根据实际情况，填入排序字段等条件，根据是否需要排序，选择调用dao内方法
        int res = this.loanAccountFeeItemDao.count(params);
        
        return res;
    }
    
    /**
     * 根据id更新对象
     * <功能详细描述>
     * @param loanAccountFeeCfgItem
     * @return [参数说明]
     * 
     * @return boolean [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @Transactional
    public boolean updateById(LoanAccountFeeItem loanAccountFeeCfgItem) {
        //验证参数是否合法，必填字段是否填写，
        AssertUtils.notNull(loanAccountFeeCfgItem,
                "loanAccountFeeCfgItem is null.");
        AssertUtils.notEmpty(loanAccountFeeCfgItem.getId(),
                "loanAccountFeeCfgItem.id is empty.");
        
        //生成需要更新字段的hashMap
        Map<String, Object> updateRowMap = new HashMap<String, Object>();
        updateRowMap.put("id", loanAccountFeeCfgItem.getId());
        
        //需要更新的字段
        //updateRowMap.put("loanAccountId", loanAccountFeeCfgItem.getLoanAccountId());  
        //updateRowMap.put("feeItem", loanAccountFeeCfgItem.getFeeItem());
        updateRowMap.put("value", loanAccountFeeCfgItem.getValue());
        
        int updateRowCount = this.loanAccountFeeItemDao.update(updateRowMap);
        
        //如果需要大于1时，抛出异常并回滚，需要在这里修改
        return updateRowCount >= 1;
    }
}
