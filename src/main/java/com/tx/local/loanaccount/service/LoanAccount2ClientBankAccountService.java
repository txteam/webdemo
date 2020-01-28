/*
 * 描          述:  <描述>
 * 修  改   人:  
 * 修改时间:  
 * <修改描述:>
 */
package com.tx.local.loanaccount.service;

import java.util.Date;
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
import com.tx.local.loanaccount.dao.LAClientBankAccountDao;
import com.tx.local.loanaccount.model.LAClientBankAccount;

/**
 * LoanAccount2ClientBankAccount的业务层
 * <功能详细描述>
 * 
 * @author  
 * @version  [版本号, ]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
@Component("loanAccount2ClientBankAccountService")
public class LoanAccount2ClientBankAccountService {
    
    @SuppressWarnings("unused")
    private Logger logger = LoggerFactory.getLogger(LoanAccount2ClientBankAccountService.class);
    
    @Resource(name = "laClientBankAccountDao")
    private LAClientBankAccountDao laClientBankAccountDao;
    
    /**
     * 将loanAccount2ClientBankAccount实例插入数据库中保存
     * 1、如果loanAccount2ClientBankAccount 为空时抛出参数为空异常
     * 2、如果loanAccount2ClientBankAccount 中部分必要参数为非法值时抛出参数不合法异常
     * 
     * @param loanAccount2ClientBankAccount [参数说明]
     * @return void [返回类型说明]
     * @exception throws
     * @see [类、类#方法、类#成员]
     */
    @Transactional
    public void insert(LAClientBankAccount loanAccount2ClientBankAccount) {
        //FIXME:验证参数是否合法
        AssertUtils.notNull(loanAccount2ClientBankAccount, "loanAccount2ClientBankAccount is null.");
                
        //FIXME:为添加的数据需要填入默认值的字段填入默认值
        loanAccount2ClientBankAccount.setCreateDate(new Date());
        //调用数据持久层对实体进行持久化操作
        this.laClientBankAccountDao.insert(loanAccount2ClientBankAccount);
    }
    
    /**
     * 根据id删除loanAccount2ClientBankAccount实例
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
        
        LAClientBankAccount condition = new LAClientBankAccount();
        condition.setId(id);
        int resInt = this.laClientBankAccountDao.delete(condition);
        
        boolean flag = resInt > 0;
        return flag;
    }
    
    /**
     * 根据Id查询LoanAccount2ClientBankAccount实体
     * 1、当id为empty时抛出异常
     *
     * @param id
     * @return LoanAccount2ClientBankAccount [返回类型说明]
     * @exception throws
     * @see [类、类#方法、类#成员]
     */
    public LAClientBankAccount findById(String id) {
        AssertUtils.notEmpty(id, "id is empty.");
        
        LAClientBankAccount condition = new LAClientBankAccount();
        condition.setId(id);
        
        LAClientBankAccount res = this.laClientBankAccountDao.find(condition);
        return res;
    }
    
    /**
     * 查询LoanAccount2ClientBankAccount实体列表
     * <功能详细描述>
     * @param params      
     * @return [参数说明]
     * 
     * @return List<LoanAccount2ClientBankAccount> [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    public List<LAClientBankAccount> queryList(
		Map<String,Object> params   
    	) {
        //判断条件合法性
        
        //生成查询条件
        params = params == null ? new HashMap<String, Object>() : params;

        //根据实际情况，填入排序字段等条件，根据是否需要排序，选择调用dao内方法
        List<LAClientBankAccount> resList = this.laClientBankAccountDao.queryList(params);
        
        return resList;
    }
    
    /**
     * 分页查询LoanAccount2ClientBankAccount实体列表
     * <功能详细描述>
      * @param params    
     * @param pageIndex 当前页index从1开始计算
     * @param pageSize 每页显示行数
     * 
     * <功能详细描述>
     * @return [参数说明]
     * 
     * @return List<LoanAccount2ClientBankAccount> [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    public PagedList<LAClientBankAccount> queryPagedList(
		Map<String,Object> params,
    	int pageIndex,
        int pageSize) {
        //T判断条件合法性
        
        //生成查询条件
        params = params == null ? new HashMap<String, Object>() : params;
 
        //根据实际情况，填入排序字段等条件，根据是否需要排序，选择调用dao内方法
        PagedList<LAClientBankAccount> resPagedList = this.laClientBankAccountDao.queryPagedList(params, pageIndex, pageSize);
        
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
    public boolean isExist(Map<String,String> key2valueMap, String excludeId) {
        AssertUtils.notEmpty(key2valueMap, "key2valueMap is empty");
        
        //生成查询条件
        Map<String, Object> params = new HashMap<String, Object>();
        params.putAll(key2valueMap);
        params.put("excludeId", excludeId);
        
        //根据实际情况，填入排序字段等条件，根据是否需要排序，选择调用dao内方法
        int res = this.laClientBankAccountDao.count(params);
        
        return res > 0;
    }
    
    /**
      * 根据id更新对象
      * <功能详细描述>
      * @param loanAccount2ClientBankAccount
      * @return [参数说明]
      * 
      * @return boolean [返回类型说明]
      * @exception throws [异常类型] [异常说明]
      * @see [类、类#方法、类#成员]
     */
    @Transactional
    public boolean updateById(LAClientBankAccount loanAccount2ClientBankAccount) {
        //FIXME:验证参数是否合法，必填字段是否填写，
        AssertUtils.notNull(loanAccount2ClientBankAccount, "loanAccount2ClientBankAccount is null.");
        AssertUtils.notEmpty(loanAccount2ClientBankAccount.getId(), "loanAccount2ClientBankAccount.id is empty.");
        
        
        //生成需要更新字段的hashMap
        Map<String, Object> updateRowMap = new HashMap<String, Object>();
        updateRowMap.put("id", loanAccount2ClientBankAccount.getId());
        
        //FIXME:需要更新的字段
		updateRowMap.put("valid", loanAccount2ClientBankAccount.isValid());	
		updateRowMap.put("defaultAccount", loanAccount2ClientBankAccount.isDefaultAccount());	
		updateRowMap.put("lastUpdateDate", loanAccount2ClientBankAccount.getLastUpdateDate());	
        int updateRowCount = this.laClientBankAccountDao.update(updateRowMap);
        
        //如果需要大于1时，抛出异常并回滚，需要在这里修改
        return updateRowCount >= 1;
    }

}
