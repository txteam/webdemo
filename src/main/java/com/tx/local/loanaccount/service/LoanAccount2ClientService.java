/*
 * 描          述:  <描述>
 * 修  改   人:  
 * 修改时间:  
 * <修改描述:>
 */
package com.tx.local.loanaccount.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.tx.core.TxConstants;
import com.tx.core.exceptions.util.AssertUtils;
import com.tx.local.loanaccount.dao.LoanAccount2ClientDao;
import com.tx.local.loanaccount.model.LoanAccount2Client;

/**
 * LoanAccount2Client的业务层 <功能详细描述>
 * 
 * @author
 * @version [版本号, ]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
@Component("loanAccount2ClientService")
public class LoanAccount2ClientService {
    
    @SuppressWarnings("unused")
    private Logger logger = LoggerFactory
            .getLogger(LoanAccount2ClientService.class);
    
    @Resource(name = "loanAccount2ClientDao")
    private LoanAccount2ClientDao loanAccount2ClientDao;
    
    @Resource(name = "loanAccountService")
    private LoanAccountService loanAccountService;
    
    /**
     * 批量插入贷款账户与客户之间的映射 <功能详细描述>
     * 
     * @param loanAccount2Clients [参数说明]
     * 
     * @return void [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @Transactional
    public void batchInsertLoanAccount2Client(
            List<LoanAccount2Client> loanAccount2ClientList) {
        if (CollectionUtils.isEmpty(loanAccount2ClientList)) {
            return;
        }
        AssertUtils.notEmpty(loanAccount2ClientList,
                "loanAccount2ClientList is empty.");
        
        this.loanAccount2ClientDao.batchInsert(loanAccount2ClientList);
    }
    
    /**
     * 根据id删除loanAccount2Client实例 1、如果入参数为空，则抛出异常 2、执行删除后，将返回数据库中被影响的条数
     * 
     * @param id
     * @return 返回删除的数据条数，<br/>
     *         有些业务场景，如果已经被别人删除同样也可以认为是成功的 这里讲通用生成的业务层代码定义为返回影响的条数
     * @return int [返回类型说明]
     * @exception throws
     * @see [类、类#方法、类#成员]
     */
    @Transactional
    public int deleteById(String id) {
        AssertUtils.notEmpty(id, "id is empty.");
        
        LoanAccount2Client condition = new LoanAccount2Client();
        condition.setId(id);
        return this.loanAccount2ClientDao.delete(condition);
    }
    
    /**
     * 根据客户姓名查询贷款单账户与客户之间的关联关系<br/>
     * <功能详细描述>
     * 
     * @param clientName
     * @return [参数说明]
     * 
     * @return List<LoanAccount2Client> [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    public List<LoanAccount2Client> queryLoanAccount2ClientListByClientName(
            String clientName) {
        AssertUtils.notEmpty(clientName, "clientName is empty.");
        
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("clientName", clientName);
        
        List<LoanAccount2Client> resList = this.loanAccount2ClientDao
                .queryList(params);
        return resList;
    }
    
    /****
     * 根据ClientIdCardNumber查询LoanAccount2Client列表 <功能简述> <功能详细描述>
     * 
     * @param loanAccount
     * @return [参数说明]
     * 
     * @return List<LoanAccount2Client> [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    public List<LoanAccount2Client> queryLoanAccount2ClientListByIdCardNumber(
            String idCardNumber) {
        AssertUtils.notEmpty(idCardNumber, "idCardNumber is empty.");
        
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("idCardNumber", idCardNumber);
        
        List<LoanAccount2Client> resList = this.loanAccount2ClientDao
                .queryList(params);
        return resList;
    }
    
    /****
     * 根据loanAccountId查询LoanAccount2Client列表 <功能简述> <功能详细描述>
     * 
     * @param loanAccount
     * @return [参数说明]
     * 
     * @return List<LoanAccount2Client> [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    public List<LoanAccount2Client> queryLoanAccount2ClientListByLoanAccountId(
            String loanAccountId) {
        AssertUtils.notEmpty(loanAccountId, "loanAccountId is empty.");
        
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("loanAccountId", loanAccountId);
        
        List<LoanAccount2Client> resList = this.loanAccount2ClientDao
                .queryList(params);
        return resList;
    }
    
    /**
     * 根据主、次贷人姓名查询贷款账户id集合 <功能详细描述>
     * 
     * @param clientName
     * @return [参数说明]
     * 
     * @return List<String> [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    public List<String> queryLoanAccountIdsByClientName(String clientName) {
        Map<String, Object> params = new HashMap<>();
        params.put("clientName", clientName);
        
        List<LoanAccount2Client> loanAccount2Clients = this.loanAccount2ClientDao
                .queryList(params);
        Set<String> resSet = new HashSet<String>(
                TxConstants.INITIAL_CONLLECTION_SIZE);
        if (CollectionUtils.isEmpty(loanAccount2Clients)) {
            return new ArrayList<String>(resSet);
        }
        
        for (LoanAccount2Client la2cTemp : loanAccount2Clients) {
            resSet.add(la2cTemp.getLoanAccount().getId());
        }
        return new ArrayList<String>(resSet);
    }
    
    /**
     * 根据主、次贷人证件号码查询贷款账户id <功能详细描述>
     * 
     * @param idCardNubmer
     * @return [参数说明]
     * 
     * @return List<String> [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    public List<String> queryLoanAccountIdsByIdCardNumber(String idCardNumber) {
        Map<String, Object> params = new HashMap<>();
        params.put("idCardNumber", idCardNumber);
        
        List<LoanAccount2Client> loanAccount2Clients = this.loanAccount2ClientDao
                .queryList(params);
        Set<String> resSet = new HashSet<String>(
                TxConstants.INITIAL_CONLLECTION_SIZE);
        if (CollectionUtils.isEmpty(loanAccount2Clients)) {
            return new ArrayList<String>(resSet);
        }
        
        for (LoanAccount2Client la2cTemp : loanAccount2Clients) {
            resSet.add(la2cTemp.getLoanAccount().getId());
        }
        return new ArrayList<String>(resSet);
    }
}
