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

import com.tx.fetchhccredit.dao.HCLoanAccountDetailViewDao;
import com.tx.fetchhccredit.model.HCLoanAccountDetailView;
import com.tx.core.exceptions.util.AssertUtils;
import com.tx.core.paged.model.PagedList;

/**
 * HCLoanAccountDetailView的业务层
 * <功能详细描述>
 * 
 * @author  
 * @version  [版本号, ]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
@Component("hCLoanAccountDetailViewService")
public class HCLoanAccountDetailViewService {
    
    @SuppressWarnings("unused")
    private Logger logger = LoggerFactory.getLogger(HCLoanAccountDetailViewService.class);
    
    @Resource(name = "hCLoanAccountDetailViewDao")
    private HCLoanAccountDetailViewDao hCLoanAccountDetailViewDao;
    
    /** <默认构造函数> */
    public HCLoanAccountDetailViewService(
            HCLoanAccountDetailViewDao hCLoanAccountDetailViewDao) {
        super();
        this.hCLoanAccountDetailViewDao = hCLoanAccountDetailViewDao;
    }
    
    /** <默认构造函数> */
    public HCLoanAccountDetailViewService() {
        super();
    }
    
    /**
        * 将hCLoanAccountDetailView实例插入数据库中保存<br />
        * 1、如果hCLoanAccountDetailView为空时抛出参数为空异常<br />
        * 2、如果hCLoanAccountDetailView中部分必要参数为非法值时抛出参数不合法异常<br />
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
    public void insertHCLoanAccountDetailView(
            HCLoanAccountDetailView hCLoanAccountDetailView) {
        //TODO:验证参数是否合法
        AssertUtils.notNull(hCLoanAccountDetailView,
                "hCLoanAccountDetailView is null.");
        AssertUtils.notEmpty(hCLoanAccountDetailView.getId(),
                "hCLoanAccountDetailView.id is empty.");
        
        //TODO: 设置默认数据
        
        this.hCLoanAccountDetailViewDao.insertHCLoanAccountDetailView(hCLoanAccountDetailView);
    }
    
    /**
     * 根据id删除hCLoanAccountDetailView实例<br />
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
        
        HCLoanAccountDetailView condition = new HCLoanAccountDetailView();
        condition.setId(id);
        return this.hCLoanAccountDetailViewDao.deleteHCLoanAccountDetailView(condition);
    }
    
    /**
      * 根据Id查询HCLoanAccountDetailView实体
      * 1、当id为empty时抛出异常
      * <功能详细描述>
      * @param id
      * @return [参数说明]
      * 
      * @return HCLoanAccountDetailView [返回类型说明]
      * @exception throws 可能存在数据库访问异常DataAccessException
      * @see [类、类#方法、类#成员]
     */
    public HCLoanAccountDetailView findHCLoanAccountDetailViewById(String id) {
        AssertUtils.notEmpty(id, "id is empty.");
        
        HCLoanAccountDetailView condition = new HCLoanAccountDetailView();
        condition.setId(id);
        
        HCLoanAccountDetailView res = this.hCLoanAccountDetailViewDao.findHCLoanAccountDetailView(condition);
        return res;
    }
    
    /**
      * 根据HCLoanAccountDetailView实体列表
      * TODO:补充说明
      * 
      * <功能详细描述>
      * @return [参数说明]
      * 
      * @return List<HCLoanAccountDetailView> [返回类型说明]
      * @exception throws [异常类型] [异常说明]
      * @see [类、类#方法、类#成员]
     */
    public List<HCLoanAccountDetailView> queryHCLoanAccountDetailViewList(/*TODO:自己定义条件*/) {
        //TODO:判断条件合法性
        
        //TODO:生成查询条件
        Map<String, Object> params = new HashMap<String, Object>();
        
        //TODO:根据实际情况，填入排序字段等条件，根据是否需要排序，选择调用dao内方法
        List<HCLoanAccountDetailView> resList = this.hCLoanAccountDetailViewDao.queryHCLoanAccountDetailViewList(params);
        
        return resList;
    }
    
    /**
     * 分页查询HCLoanAccountDetailView实体列表
     * TODO:补充说明
     * 
     * <功能详细描述>
     * @return [参数说明]
     * 
     * @return List<HCLoanAccountDetailView> [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
    */
    public PagedList<HCLoanAccountDetailView> queryHCLoanAccountDetailViewPagedList(
    /*TODO:自己定义条件*/int pageIndex, int pageSize) {
        //TODO:判断条件合法性
        
        //TODO:生成查询条件
        Map<String, Object> params = new HashMap<String, Object>();
        
        //TODO:根据实际情况，填入排序字段等条件，根据是否需要排序，选择调用dao内方法
        PagedList<HCLoanAccountDetailView> resPagedList = this.hCLoanAccountDetailViewDao.queryHCLoanAccountDetailViewPagedList(params,
                pageIndex,
                pageSize);
        
        return resPagedList;
    }
    
    /**
      * 查询hCLoanAccountDetailView列表总条数
      * TODO:补充说明
      * <功能详细描述>
      * @return [参数说明]
      * 
      * @return int [返回类型说明]
      * @exception throws [异常类型] [异常说明]
      * @see [类、类#方法、类#成员]
     */
    public int countHCLoanAccountDetailView(/*TODO:自己定义条件*/) {
        //TODO:判断条件合法性
        
        //TODO:生成查询条件
        Map<String, Object> params = new HashMap<String, Object>();
        
        //TODO:根据实际情况，填入排序字段等条件，根据是否需要排序，选择调用dao内方法
        int res = this.hCLoanAccountDetailViewDao.countHCLoanAccountDetailView(params);
        
        return res;
    }
    
    /**
      * 根据id更新对象
      * <功能详细描述>
      * @param hCLoanAccountDetailView
      * @return [参数说明]
      * 
      * @return boolean [返回类型说明]
      * @exception throws [异常类型] [异常说明]
      * @see [类、类#方法、类#成员]
     */
    @Transactional
    public boolean updateById(HCLoanAccountDetailView hCLoanAccountDetailView) {
        //TODO:验证参数是否合法，必填字段是否填写，
        AssertUtils.notNull(hCLoanAccountDetailView,
                "hCLoanAccountDetailView is null.");
        AssertUtils.notEmpty(hCLoanAccountDetailView.getId(),
                "hCLoanAccountDetailView.id is empty.");
        
        //TODO:生成需要更新字段的hashMap
        Map<String, Object> updateRowMap = new HashMap<String, Object>();
        updateRowMap.put("id", hCLoanAccountDetailView.getId());
        
        //TODO:需要更新的字段
        updateRowMap.put("remark", hCLoanAccountDetailView.getRemark());
        updateRowMap.put("patchReason",
                hCLoanAccountDetailView.getPatchReason());
        updateRowMap.put("workUnitPhoneNumber",
                hCLoanAccountDetailView.getWorkUnitPhoneNumber());
        updateRowMap.put("spouseWorkUnitPhoneNumber",
                hCLoanAccountDetailView.getSpouseWorkUnitPhoneNumber());
        updateRowMap.put("hasHouse", hCLoanAccountDetailView.getHasHouse());
        updateRowMap.put("status", hCLoanAccountDetailView.getStatus());
        updateRowMap.put("hasMortgage",
                hCLoanAccountDetailView.getHasMortgage());
        updateRowMap.put("customerServiceManager",
                hCLoanAccountDetailView.getCustomerServiceManager());
        updateRowMap.put("applyDate", hCLoanAccountDetailView.getApplyDate());
        updateRowMap.put("spouseName", hCLoanAccountDetailView.getSpouseName());
        updateRowMap.put("firstTrialDate",
                hCLoanAccountDetailView.getFirstTrialDate());
        updateRowMap.put("districtName",
                hCLoanAccountDetailView.getDistrictName());
        updateRowMap.put("month", hCLoanAccountDetailView.getMonth());
        updateRowMap.put("patchDate", hCLoanAccountDetailView.getPatchDate());
        updateRowMap.put("idCardType", hCLoanAccountDetailView.getIdCardType());
        updateRowMap.put("housePhoneNumber",
                hCLoanAccountDetailView.getHousePhoneNumber());
        updateRowMap.put("idCardNumber",
                hCLoanAccountDetailView.getIdCardNumber());
        updateRowMap.put("sex", hCLoanAccountDetailView.getSex());
        updateRowMap.put("loanAmount", hCLoanAccountDetailView.getLoanAmount());
        //updateRowMap.put("use", hCLoanAccountDetailView.getUse());
        updateRowMap.put("refuseOtherReason",
                hCLoanAccountDetailView.getRefuseOtherReason());
        updateRowMap.put("linkManPhoneNumber3",
                hCLoanAccountDetailView.getLinkManPhoneNumber3());
        updateRowMap.put("linkManPhoneNumber4",
                hCLoanAccountDetailView.getLinkManPhoneNumber4());
        updateRowMap.put("linkManPhoneNumber1",
                hCLoanAccountDetailView.getLinkManPhoneNumber1());
        updateRowMap.put("linkManPhoneNumber2",
                hCLoanAccountDetailView.getLinkManPhoneNumber2());
        updateRowMap.put("customerServiceOfficer",
                hCLoanAccountDetailView.getCustomerServiceOfficer());
        updateRowMap.put("creditProductName",
                hCLoanAccountDetailView.getCreditProductName());
        updateRowMap.put("finalTrialOperator",
                hCLoanAccountDetailView.getFinalTrialOperator());
        updateRowMap.put("clientName", hCLoanAccountDetailView.getClientName());
        updateRowMap.put("kinPhoneNumber1",
                hCLoanAccountDetailView.getKinPhoneNumber1());
        updateRowMap.put("kinPhoneNumber2",
                hCLoanAccountDetailView.getKinPhoneNumber2());
        updateRowMap.put("createDate", hCLoanAccountDetailView.getCreateDate());
        updateRowMap.put("totalPeriod",
                hCLoanAccountDetailView.getTotalPeriod());
        updateRowMap.put("kinPhoneNumber3",
                hCLoanAccountDetailView.getKinPhoneNumber3());
        updateRowMap.put("kinPhoneNumber4",
                hCLoanAccountDetailView.getKinPhoneNumber4());
        updateRowMap.put("telePhoneNumber",
                hCLoanAccountDetailView.getTelePhoneNumber());
        updateRowMap.put("firstTrialOperator",
                hCLoanAccountDetailView.getFirstTrialOperator());
        updateRowMap.put("urgent", hCLoanAccountDetailView.isUrgent());
        updateRowMap.put("spouseTelePhoneNumber",
                hCLoanAccountDetailView.getSpouseTelePhoneNumber());
        updateRowMap.put("maxMonthlyRepayAmount",
                hCLoanAccountDetailView.getMaxMonthlyRepayAmount());
        updateRowMap.put("workUnitIndustry",
                hCLoanAccountDetailView.getWorkUnitIndustry());
        updateRowMap.put("createOperatorName",
                hCLoanAccountDetailView.getCreateOperatorName());
        updateRowMap.put("workUnitInfo",
                hCLoanAccountDetailView.getWorkUnitInfo());
        updateRowMap.put("interestRate",
                hCLoanAccountDetailView.getInterestRate());
        updateRowMap.put("finalTrialDate",
                hCLoanAccountDetailView.getFinalTrialDate());
        updateRowMap.put("workInfo", hCLoanAccountDetailView.getWorkInfo());
        updateRowMap.put("loanBillNumber",
                hCLoanAccountDetailView.getLoanBillNumber());
        updateRowMap.put("refuseReason",
                hCLoanAccountDetailView.getRefuseReason());
        updateRowMap.put("customerServiceTeamManager",
                hCLoanAccountDetailView.getCustomerServiceTeamManager());
        updateRowMap.put("startLiveDate",
                hCLoanAccountDetailView.getStartLiveDate());
        updateRowMap.put("linkInfo", hCLoanAccountDetailView.getLinkInfo());
        updateRowMap.put("serviceClientName",
                hCLoanAccountDetailView.getServiceClientName());
        
        int updateRowCount = this.hCLoanAccountDetailViewDao.updateHCLoanAccountDetailView(updateRowMap);
        
        //TODO:如果需要大于1时，抛出异常并回滚，需要在这里修改
        return updateRowCount >= 1;
    }
}
