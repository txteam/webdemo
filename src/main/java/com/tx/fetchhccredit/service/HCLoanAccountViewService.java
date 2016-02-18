/*
 * 描述: <描述>
 * 修改人:  
 * 修改时间:  
 * <修改描述:>
 */
package com.tx.fetchhccredit.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.tx.fetchhccredit.dao.HCLoanAccountViewDao;
import com.tx.fetchhccredit.model.HCLoanAccountView;
import com.tx.core.exceptions.util.AssertUtils;
import com.tx.core.paged.model.PagedList;

/**
 * HCLoanAccountView的业务层
 * <功能详细描述>
 * 
 * @author  
 * @version  [版本号, ]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
@Component("hCLoanAccountViewService")
public class HCLoanAccountViewService {
    
    @SuppressWarnings("unused")
    private Logger logger = LoggerFactory.getLogger(HCLoanAccountViewService.class);
    
    @Resource(name = "hCLoanAccountViewDao")
    private HCLoanAccountViewDao hCLoanAccountViewDao;
    
    /** <默认构造函数> */
    public HCLoanAccountViewService() {
        super();
    }
    
    /** <默认构造函数> */
    public HCLoanAccountViewService(HCLoanAccountViewDao hCLoanAccountViewDao) {
        super();
        this.hCLoanAccountViewDao = hCLoanAccountViewDao;
    }
    
    @Transactional
    public void batchInsertHCLoanAccountView(
            List<HCLoanAccountView> hcLoanAccountViews) {
        
        this.hCLoanAccountViewDao.batchInsertHCLoanAccountView(hcLoanAccountViews);
    }
    
    /**
        * 将hCLoanAccountView实例插入数据库中保存<br />
        * 1、如果hCLoanAccountView为空时抛出参数为空异常<br />
        * 2、如果hCLoanAccountView中部分必要参数为非法值时抛出参数不合法异常<br />
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
    public void insertHCLoanAccountView(HCLoanAccountView hCLoanAccountView) {
        //验证参数是否合法
        AssertUtils.notNull(hCLoanAccountView, "hCLoanAccountView is null.");
        AssertUtils.notEmpty(hCLoanAccountView.getId(),
                "hCLoanAccountView.id is empty.");
        
        //TODO: 设置默认数据
        
        this.hCLoanAccountViewDao.insertHCLoanAccountView(hCLoanAccountView);
    }
    
    /**
     * 根据id删除hCLoanAccountView实例<br />
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
        
        HCLoanAccountView condition = new HCLoanAccountView();
        condition.setId(id);
        return this.hCLoanAccountViewDao.deleteHCLoanAccountView(condition);
    }
    
    /**
      * 根据Id查询HCLoanAccountView实体
      * 1、当id为empty时抛出异常
      * <功能详细描述>
      * @param id
      * @return [参数说明]
      * 
      * @return HCLoanAccountView [返回类型说明]
      * @exception throws 可能存在数据库访问异常DataAccessException
      * @see [类、类#方法、类#成员]
     */
    public HCLoanAccountView findHCLoanAccountViewById(String id) {
        AssertUtils.notEmpty(id, "id is empty.");
        
        HCLoanAccountView condition = new HCLoanAccountView();
        condition.setId(id);
        
        HCLoanAccountView res = this.hCLoanAccountViewDao.findHCLoanAccountView(condition);
        return res;
    }
    
    /**
      * 根据HCLoanAccountView实体列表
      * TODO:补充说明
      * 
      * <功能详细描述>
      * @return [参数说明]
      * 
      * @return List<HCLoanAccountView> [返回类型说明]
      * @exception throws [异常类型] [异常说明]
      * @see [类、类#方法、类#成员]
     */
    public List<HCLoanAccountView> queryHCLoanAccountViewList(/*TODO:自己定义条件*/) {
        //TODO:判断条件合法性
        
        //TODO:生成查询条件
        Map<String, Object> params = new HashMap<String, Object>();
        
        //TODO:根据实际情况，填入排序字段等条件，根据是否需要排序，选择调用dao内方法
        List<HCLoanAccountView> resList = this.hCLoanAccountViewDao.queryHCLoanAccountViewList(params);
        
        return resList;
    }
    
    /**
     * 分页查询HCLoanAccountView实体列表
     * TODO:补充说明
     * 
     * <功能详细描述>
     * @return [参数说明]
     * 
     * @return List<HCLoanAccountView> [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
    */
    public PagedList<HCLoanAccountView> queryHCLoanAccountViewPagedList(
            int pageIndex, int pageSize) {
        Map<String, Object> params = new HashMap<String, Object>();
        
        //根据实际情况，填入排序字段等条件，根据是否需要排序，选择调用dao内方法
        PagedList<HCLoanAccountView> resPagedList = this.hCLoanAccountViewDao.queryHCLoanAccountViewPagedList(params,
                pageIndex,
                pageSize);
        
        return resPagedList;
    }
    
    /**
      * 查询hCLoanAccountView列表总条数
      * TODO:补充说明
      * <功能详细描述>
      * @return [参数说明]
      * 
      * @return int [返回类型说明]
      * @exception throws [异常类型] [异常说明]
      * @see [类、类#方法、类#成员]
     */
    public int countHCLoanAccountView(/*TODO:自己定义条件*/) {
        //TODO:判断条件合法性
        
        //TODO:生成查询条件
        Map<String, Object> params = new HashMap<String, Object>();
        
        //TODO:根据实际情况，填入排序字段等条件，根据是否需要排序，选择调用dao内方法
        int res = this.hCLoanAccountViewDao.countHCLoanAccountView(params);
        
        return res;
    }
    
    /**
      * 根据id更新对象
      * <功能详细描述>
      * @param hCLoanAccountView
      * @return [参数说明]
      * 
      * @return boolean [返回类型说明]
      * @exception throws [异常类型] [异常说明]
      * @see [类、类#方法、类#成员]
     */
    @Transactional
    public boolean updateById(HCLoanAccountView hCLoanAccountView) {
        //TODO:验证参数是否合法，必填字段是否填写，
        AssertUtils.notNull(hCLoanAccountView, "hCLoanAccountView is null.");
        AssertUtils.notEmpty(hCLoanAccountView.getId(),
                "hCLoanAccountView.id is empty.");
        
        //TODO:生成需要更新字段的hashMap
        Map<String, Object> updateRowMap = new HashMap<String, Object>();
        updateRowMap.put("id", hCLoanAccountView.getId());
        
        //TODO:需要更新的字段
        updateRowMap.put("idCardNumber", hCLoanAccountView.getIdCardNumber());
        updateRowMap.put("status", hCLoanAccountView.getStatus());
        updateRowMap.put("customerServiceManager",
                hCLoanAccountView.getCustomerServiceManager());
        updateRowMap.put("applyDate", hCLoanAccountView.getApplyDate());
        updateRowMap.put("districtName", hCLoanAccountView.getDistrictName());
        updateRowMap.put("creditProductName",
                hCLoanAccountView.getCreditProductName());
        updateRowMap.put("customerServiceOfficer",
                hCLoanAccountView.getCustomerServiceOfficer());
        updateRowMap.put("loanBillNumber",
                hCLoanAccountView.getLoanBillNumber());
        updateRowMap.put("phoneNumber", hCLoanAccountView.getPhoneNumber());
        updateRowMap.put("clientName", hCLoanAccountView.getClientName());
        updateRowMap.put("branchName", hCLoanAccountView.getBranchName());
        updateRowMap.put("customerServiceTeamManager",
                hCLoanAccountView.getCustomerServiceTeamManager());
        
        int updateRowCount = this.hCLoanAccountViewDao.updateHCLoanAccountView(updateRowMap);
        
        //TODO:如果需要大于1时，抛出异常并回滚，需要在这里修改
        return updateRowCount >= 1;
    }
}
