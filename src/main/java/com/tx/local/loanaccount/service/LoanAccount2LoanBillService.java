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

import com.tx.local.loanaccount.dao.LoanAccount2LoanBillDao;
import com.tx.local.loanaccount.model.LoanAccount;
import com.tx.local.loanaccount.model.LoanAccount2LoanBill;
import com.tx.core.exceptions.util.AssertUtils;
import com.tx.core.paged.model.PagedList;

/**
 * LoanAccount2LoanBill的业务层
 * <功能详细描述>
 * 
 * @author  
 * @version  [版本号, ]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
@Component("loanAccount2LoanBillService")
public class LoanAccount2LoanBillService {
    
    @SuppressWarnings("unused")
    private Logger logger = LoggerFactory.getLogger(LoanAccount2LoanBillService.class);
    
    @Resource(name = "loanAccount2LoanBillDao")
    private LoanAccount2LoanBillDao loanAccount2LoanBillDao;
    
    /**
    * 将loanAccount2LoanBill实例插入数据库中保存<br />
    * 1、如果loanAccount2LoanBill为空时抛出参数为空异常<br />
    * 2、如果loanAccount2LoanBill中部分必要参数为非法值时抛出参数不合法异常<br />
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
    public void insert(LoanAccount2LoanBill loanAccount2LoanBill) {
        //验证参数是否合法
        AssertUtils.notNull(loanAccount2LoanBill, "loanAccount2LoanBill is null.");
        AssertUtils.notNull(loanAccount2LoanBill.getLoanAccount(), "loanAccount2LoanBill.loanAccount is null.");
        AssertUtils.notEmpty(loanAccount2LoanBill.getLoanAccount().getId(),
                "loanAccount2LoanBill.loanAccount.id is empty.");
        
        //设置默认数据
        this.loanAccount2LoanBillDao.insert(loanAccount2LoanBill);
    }
    
    /**
     * 根据id删除loanAccount2LoanBill实例<br />
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
        
        LoanAccount2LoanBill condition = new LoanAccount2LoanBill();
        condition.setId(id);
        return this.loanAccount2LoanBillDao.delete(condition);
    }
    
    /**
      * 根据Id查询LoanAccount2LoanBill实体
      * 1、当id为empty时抛出异常
      * <功能详细描述>
      * @param id
      * @return [参数说明]
      * 
      * @return LoanAccount2LoanBill [返回类型说明]
      * @exception throws 可能存在数据库访问异常DataAccessException
      * @see [类、类#方法、类#成员]
     */
    public LoanAccount2LoanBill findById(String id) {
        AssertUtils.notEmpty(id, "id is empty.");
        
        LoanAccount2LoanBill condition = new LoanAccount2LoanBill();
        condition.setId(id);
        
        LoanAccount2LoanBill res = this.loanAccount2LoanBillDao.find(condition);
        return res;
    }
    
    /**
     * 根据Id查询LoanAccount2LoanBill实体
     * 1、当id为empty时抛出异常
     * <功能详细描述>
     * @param loanAccountId
     * @return [参数说明]
     * 
     * @return LoanAccount2LoanBill [返回类型说明]
     * @exception throws 可能存在数据库访问异常DataAccessException
     * @see [类、类#方法、类#成员]
    */
    public LoanAccount2LoanBill findByLoanAccountId(String loanAccountId) {
        AssertUtils.notEmpty(loanAccountId, "loanAccountId is empty.");
        
        LoanAccount2LoanBill condition = new LoanAccount2LoanBill();
        LoanAccount loanAccount = new LoanAccount();
        loanAccount.setId(loanAccountId);
        condition.setLoanAccount(loanAccount);
        
        LoanAccount2LoanBill res = this.loanAccount2LoanBillDao.find(condition);
        return res;
    }
    
    /**
      * 根据LoanAccount2LoanBill实体列表
      * <功能详细描述>
      * @return [参数说明]
      * 
      * @return List<LoanAccount2LoanBill> [返回类型说明]
      * @exception throws [异常类型] [异常说明]
      * @see [类、类#方法、类#成员]
     */
    public List<LoanAccount2LoanBill> queryList(Map<String, Object> params) {
        params = params == null ? new HashMap<String, Object>() : params;
        
        //根据实际情况，填入排序字段等条件，根据是否需要排序，选择调用dao内方法
        List<LoanAccount2LoanBill> resList = this.loanAccount2LoanBillDao.queryList(params);
        
        return resList;
    }
    
    /**
     * 分页查询LoanAccount2LoanBill实体列表
     * <功能详细描述>
     * @return [参数说明]
     * 
     * @return List<LoanAccount2LoanBill> [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
    */
    public PagedList<LoanAccount2LoanBill> queryPagedList(Map<String, Object> params, int pageIndex, int pageSize) {
        params = params == null ? new HashMap<String, Object>() : params;
        
        //根据实际情况，填入排序字段等条件，根据是否需要排序，选择调用dao内方法
        PagedList<LoanAccount2LoanBill> resPagedList = this.loanAccount2LoanBillDao.queryPagedList(params,
                pageIndex,
                pageSize);
        
        return resPagedList;
    }
    
    /**
      * 查询loanAccount2LoanBill列表总条数
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
        int res = this.loanAccount2LoanBillDao.count(params);
        
        return res;
    }
    
    /**
      * 根据id更新对象
      * <功能详细描述>
      * @param loanAccount2LoanBill
      * @return [参数说明]
      * 
      * @return boolean [返回类型说明]
      * @exception throws [异常类型] [异常说明]
      * @see [类、类#方法、类#成员]
     */
    @Transactional
    public boolean updateById(LoanAccount2LoanBill loanAccount2LoanBill) {
        //验证参数是否合法，必填字段是否填写，
        AssertUtils.notNull(loanAccount2LoanBill, "loanAccount2LoanBill is null.");
        AssertUtils.notEmpty(loanAccount2LoanBill.getId(), "loanAccount2LoanBill.id is empty.");
        
        //生成需要更新字段的hashMap
        Map<String, Object> updateRowMap = new HashMap<String, Object>();
        updateRowMap.put("id", loanAccount2LoanBill.getId());
        
        //TODO:需要更新的字段
        updateRowMap.put("loanBillId", loanAccount2LoanBill.getLoanBillId());
        updateRowMap.put("loanProductId", loanAccount2LoanBill.getLoanProductId());
        //type:java.lang.String
        updateRowMap.put("loanAccount", loanAccount2LoanBill.getLoanAccount());
        updateRowMap.put("loanBillNumber", loanAccount2LoanBill.getLoanBillNumber());
        updateRowMap.put("loanProductName", loanAccount2LoanBill.getLoanProductName());
        updateRowMap.put("loanType", loanAccount2LoanBill.getLoanType());
        
        int updateRowCount = this.loanAccount2LoanBillDao.update(updateRowMap);
        
        //如果需要大于1时，抛出异常并回滚，需要在这里修改
        return updateRowCount >= 1;
    }
}
