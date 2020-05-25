///*
// * 描          述:  <描述>
// * 修  改   人:  
// * 修改时间:  
// * <修改描述:>
// */
//package com.tx.local.personal.service;
//
//import java.util.Date;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//import java.util.stream.Collectors;
//
//import javax.annotation.Resource;
//
//import com.tx.component.basicdata.context.BasicDataContext;
//import com.tx.component.basicdata.service.BasicDataService;
//import com.tx.local.auditrecord.model.AuditDataSourceType;
//import com.tx.local.auditrecord.model.AuditRecord;
//import com.tx.local.auditrecord.service.AuditRecordService;
//import com.tx.local.creditinfo.model.Education;
//import com.tx.local.security.util.WebContextUtils;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.stereotype.Component;
//import org.springframework.transaction.annotation.Transactional;
//
//import com.tx.local.peasant.dao.PeasantManageDao;
//import com.tx.local.personal.model.PeasantInfo;
//import com.tx.local.produceinfo.model.BreedInfo;
//import com.tx.local.produceinfo.model.PlantInfo;
//import com.tx.local.produceinfo.service.BreedManageService;
//import com.tx.local.produceinfo.service.PlantManageService;
//import com.tx.core.exceptions.util.AssertUtils;
//import com.tx.core.paged.model.PagedList;
//import com.tx.core.querier.model.Querier;
//import com.tx.core.querier.model.QuerierBuilder;
//
///**
// * PeasantManage的业务层[PeasantManageService]
// * <功能详细描述>
// * 
// * @author  
// * @version [版本号]
// * @see [相关类/方法]
// * @since [产品/模块版本]
// */
//@Component("peasantManageService")
//public class PeasantManageService {
//    
//    @SuppressWarnings("unused")
//    private Logger logger = LoggerFactory.getLogger(PeasantManageService.class);
//    //农户信息
//    @Resource(name = "peasantManageDao")
//    private PeasantManageDao peasantManageDao;
//    //审核信息
//    @Resource(name = "auditRecordService")
//    private AuditRecordService auditRecordService;
//    //养殖信息
//    @Resource(name = "plantManageService")
//    private PlantManageService plantManageService;
//    //种植信息
//    @Resource(name = "breedManageService")
//    private BreedManageService breedManageService;
//
//
//
//    /**
//     * 审核种植<br/>
//     * @param auditRecord [参数说明]
//     * @return void [返回类型说明]
//     * @exception throws
//     * @see [类、类#方法、类#成员]
//     */
//    @Transactional
//    public boolean audit(AuditRecord auditRecord) {
//        AssertUtils.notEmpty(auditRecord, "auditRecord is empty.");
//
//        auditRecord.setSourceType(AuditDataSourceType.NH);
//        auditRecord.setOperatorId(WebContextUtils.getOperatorId());
//        auditRecord.setOperatorName(WebContextUtils.getOperatorUsername());
//        auditRecordService.insert(auditRecord);
//
//        Map<String,Object> params = new HashMap<>();
//        params.put("id",auditRecord.getSourceId());
//        if(auditRecord.isAuditStatus()){
//            params.put("auditStatus",1);
//        }else{
//            params.put("auditStatus",2);
//        }
//        int flag = peasantManageDao.update(params);
//        return flag > 0;
//    }
//    /**
//     * 新增PeasantManage实例<br/>
//     * 将peasantManage插入数据库中保存
//     * 1、如果peasantManage 为空时抛出参数为空异常
//     * 2、如果peasantManage 中部分必要参数为非法值时抛出参数不合法异常
//     * 
//     * @param peasantManage [参数说明]
//     * @return void [返回类型说明]
//     * @exception throws
//     * @see [类、类#方法、类#成员]
//     */
//    @Transactional
//    public void insert(PeasantInfo peasantManage) {
//        //验证参数是否合法
//        AssertUtils.notNull(peasantManage, "peasantManage is null.");
//           
//        //FIXME:为添加的数据需要填入默认值的字段填入默认值
//		peasantManage.setLastUpdateDate(new Date());
//		peasantManage.setCreateDate(new Date());
//		//拼接姓名
//        peasantManage.setName(peasantManage.getFristName() + peasantManage.getLastName());
//        //审核状态
//        peasantManage.setAuditStatus(0);
//        
//        //调用数据持久层对实例进行持久化操作
//        this.peasantManageDao.insert(peasantManage);
//    }
//    
//    /**
//     * 根据id删除PeasantManage实例
//     * 1、如果入参数为空，则抛出异常
//     * 2、执行删除后，将返回数据库中被影响的条数 > 0，则返回true
//     *
//     * @param id
//     * @return boolean 删除的条数>0则为true [返回类型说明]
//     * @exception throws 
//     * @see [类、类#方法、类#成员]
//     */
//    @Transactional
//    public boolean deleteById(String id) {
//        AssertUtils.notEmpty(id, "id is empty.");
//        
//        PeasantInfo condition = new PeasantInfo();
//        condition.setId(id);
//        Map<String, Object> params = new HashMap<>();
//        params.put("peasantManageId", id);
//        //养殖信息
//        List<BreedInfo> breedList = breedManageService.queryList(params);
//        //种植信息
//        List<PlantInfo> plantList = plantManageService.queryList(params);
//        if(breedList != null && breedList.size() > 0){
//            return false;
//        }
//        if(plantList != null && plantList.size() > 0){
//            return false;
//        }
//        int resInt = this.peasantManageDao.delete(condition);
//        boolean flag = resInt > 0;
//        return flag;
//    }
//    
//    /**
//     * 根据id查询PeasantManage实例
//     * 1、当id为empty时抛出异常
//     *
//     * @param id
//     * @return PeasantManage [返回类型说明]
//     * @exception throws
//     * @see [类、类#方法、类#成员]
//     */
//    public PeasantInfo findById(String id) {
//        AssertUtils.notEmpty(id, "id is empty.");
//        
//        PeasantInfo condition = new PeasantInfo();
//        condition.setId(id);
//        
//        PeasantInfo res = this.peasantManageDao.find(condition);
//        return res;
//    }
//    
//    /**
//     * 查询PeasantManage实例列表
//     * <功能详细描述>
//     * @param params      
//     * @return [参数说明]
//     * 
//     * @return List<PeasantManage> [返回类型说明]
//     * @exception throws [异常类型] [异常说明]
//     * @see [类、类#方法、类#成员]
//     */
//    public List<PeasantInfo> queryList(
//		Map<String,Object> params   
//    	) {
//        //判断条件合法性
//        
//        //生成查询条件
//        params = params == null ? new HashMap<String, Object>() : params;
//
//        //根据实际情况，填入排序字段等条件，根据是否需要排序，选择调用dao内方法
//        List<PeasantInfo> resList = this.peasantManageDao.queryList(params);
//        
//        return resList;
//    }
//
//    /**
//     * 根据合作社ID与虚中心ID查询农户
//     * 查询PeasantManage实例列表
//     * <功能详细描述>
//     * @param params
//     * @return [参数说明]
//     *
//     * @return List<PeasantManage> [返回类型说明]
//     * @exception throws [异常类型] [异常说明]
//     * @see [类、类#方法、类#成员]
//     */
//    public List<PeasantInfo> queryListByCooperativeId(
//        Map<String, Object> params
//        ) {
//        //生成查询条件
//        params = params == null ? new HashMap<String, Object>() : params;
//        //根据实际情况，填入排序字段等条件，根据是否需要排序，选择调用dao内方法
//        List<PeasantInfo> resList = this.peasantManageDao.queryList(params);
//        if(resList == null || resList.size() <= 0){
//            //若没有则返回所有合作社
//            resList = this.peasantManageDao.queryList(new HashMap<String, Object>());
//        }
//        return resList;
//    }
//    /**
//     * 查询PeasantManage实例列表
//     * <功能详细描述>
//     * @param querier      
//     * @return [参数说明]
//     * 
//     * @return List<PeasantManage> [返回类型说明]
//     * @exception throws [异常类型] [异常说明]
//     * @see [类、类#方法、类#成员]
//     */
//    public List<PeasantInfo> queryList(
//		Querier querier   
//    	) {
//        //判断条件合法性
//        
//        //生成查询条件
//        querier = querier == null ? QuerierBuilder.newInstance().querier()
//                : querier;
//
//        //根据实际情况，填入排序字段等条件，根据是否需要排序，选择调用dao内方法
//        List<PeasantInfo> resList = this.peasantManageDao.queryList(querier);
//        
//        return resList;
//    }
//    
//    /**
//     * 分页查询PeasantManage实例列表
//     * <功能详细描述>
//     * @param params    
//     * @param pageIndex 当前页index从1开始计算
//     * @param pageSize 每页显示行数
//     * 
//     * <功能详细描述>
//     * @return [参数说明]
//     * 
//     * @return List<PeasantManage> [返回类型说明]
//     * @exception throws [异常类型] [异常说明]
//     * @see [类、类#方法、类#成员]
//     */
//    public PagedList<PeasantInfo> queryPagedList(
//		Map<String,Object> params,
//    	int pageIndex,
//        int pageSize) {
//        //T判断条件合法性
//        
//        //生成查询条件
//        params = params == null ? new HashMap<String, Object>() : params;
//
//        //根据实际情况，填入排序字段等条件，根据是否需要排序，选择调用dao内方法
//        PagedList<PeasantInfo> resPagedList = this.peasantManageDao.queryPagedList(params, pageIndex, pageSize);
//
//        BasicDataService<Education> educationService = BasicDataContext.getContext().getBasicDataService(Education.class);
//        List<Education> educationList = educationService.queryList(true, null);
//        //给学历赋值
//        Map<String, Education> mappedEducation = educationList.stream().collect(Collectors.toMap(Education::getId, (p) -> p));
//        List<PeasantInfo> list=resPagedList.getList();
//        for (PeasantInfo item: list) {
//            item.setEducation(mappedEducation.get(item.getEducation().getId()));
//        }
//        resPagedList.setList(list);
//
//        return resPagedList;
//    }
//    
//	/**
//     * 分页查询PeasantManage实例列表
//     * <功能详细描述>
//     * @param querier    
//     * @param pageIndex 当前页index从1开始计算
//     * @param pageSize 每页显示行数
//     * 
//     * <功能详细描述>
//     * @return [参数说明]
//     * 
//     * @return List<PeasantManage> [返回类型说明]
//     * @exception throws [异常类型] [异常说明]
//     * @see [类、类#方法、类#成员]
//     */
//    public PagedList<PeasantInfo> queryPagedList(
//		Querier querier,
//    	int pageIndex,
//        int pageSize) {
//        //T判断条件合法性
//        
//        //生成查询条件
//        querier = querier == null ? QuerierBuilder.newInstance().querier()
//                : querier;
//
//        //根据实际情况，填入排序字段等条件，根据是否需要排序，选择调用dao内方法
//        PagedList<PeasantInfo> resPagedList = this.peasantManageDao.queryPagedList(querier, pageIndex, pageSize);
//
//
//        BasicDataService<Education> educationService = BasicDataContext.getContext().getBasicDataService(Education.class);
//        List<Education> educationList = educationService.queryList(true, null);
//        //给学历赋值
//        Map<String, Education> mappedEducation = educationList.stream().collect(Collectors.toMap(Education::getId, (p) -> p));
//        List<PeasantInfo> list=resPagedList.getList();
//        for (PeasantInfo item: list) {
//            item.setEducation(mappedEducation.get(item.getEducation().getId()));
//        }
//        resPagedList.setList(list);
//
//        return resPagedList;
//    }
//    
//    /**
//     * 查询PeasantManage实例数量<br/>
//     * <功能详细描述>
//     * @param params      
//     * @return [参数说明]
//     * 
//     * @return List<PeasantManage> [返回类型说明]
//     * @exception throws [异常类型] [异常说明]
//     * @see [类、类#方法、类#成员]
//     */
//    public int count(
//		Map<String,Object> params   
//    	) {
//        //判断条件合法性
//        
//        //生成查询条件
//        params = params == null ? new HashMap<String, Object>() : params;
//
//        //根据实际情况，填入排序字段等条件，根据是否需要排序，选择调用dao内方法
//        int res = this.peasantManageDao.count(params);
//        
//        return res;
//    }
//    
//    /**
//     * 查询PeasantManage实例数量<br/>
//     * <功能详细描述>
//     * @param querier      
//     * @return [参数说明]
//     * 
//     * @return List<PeasantManage> [返回类型说明]
//     * @exception throws [异常类型] [异常说明]
//     * @see [类、类#方法、类#成员]
//     */
//    public int count(
//		Querier querier   
//    	) {
//        //判断条件合法性
//        
//        //生成查询条件
//        querier = querier == null ? QuerierBuilder.newInstance().querier()
//                : querier;
//
//        //根据实际情况，填入排序字段等条件，根据是否需要排序，选择调用dao内方法
//        int res = this.peasantManageDao.count(querier);
//        
//        return res;
//    }
//    
//    /**
//     * 判断PeasantManage实例是否已经存在<br/>
//     * <功能详细描述>
//     * @param key2valueMap
//     * @param excludeId
//     * @return
//     * 
//     * @return int [返回类型说明]
//     * @exception throws [异常类型] [异常说明]
//     * @see [类、类#方法、类#成员]
//     */
//    public boolean exists(Map<String,String> key2valueMap, String excludeId) {
//        AssertUtils.notEmpty(key2valueMap, "key2valueMap is empty");
//        
//        //生成查询条件
//        Map<String, Object> params = new HashMap<String, Object>();
//        params.putAll(key2valueMap);
//        
//        //根据实际情况，填入排序字段等条件，根据是否需要排序，选择调用dao内方法
//        int res = this.peasantManageDao.count(params,excludeId);
//        
//        return res > 0;
//    }
//    
//    /**
//     * 判断PeasantManage实例是否已经存在<br/>
//     * <功能详细描述>
//     * @param excludeId
//     * @return
//     * 
//     * @return int [返回类型说明]
//     * @exception throws [异常类型] [异常说明]
//     * @see [类、类#方法、类#成员]
//     */
//    public boolean exists(Querier querier, String excludeId) {
//        AssertUtils.notNull(querier, "querier is null.");
//        
//        //根据实际情况，填入排序字段等条件，根据是否需要排序，选择调用dao内方法
//        int res = this.peasantManageDao.count(querier,excludeId);
//        
//        return res > 0;
//    }
//    
//    /**
//     * 根据id更新PeasantManage实例<br/>
//     * <功能详细描述>
//     * @param peasantManage
//     * @return [参数说明]
//     * 
//     * @return boolean [返回类型说明]
//     * @exception throws [异常类型] [异常说明]
//     * @see [类、类#方法、类#成员]
//     */
//    @Transactional
//    public boolean updateById(String id,PeasantInfo peasantManage) {
//        //验证参数是否合法，必填字段是否填写
//        AssertUtils.notNull(peasantManage, "peasantManage is null.");
//        AssertUtils.notEmpty(id, "id is empty.");
//
//        //生成需要更新字段的hashMap
//        Map<String, Object> updateRowMap = new HashMap<String, Object>();
//        //FIXME:需要更新的字段
//		updateRowMap.put("idCardNumber", peasantManage.getIdCardNumber());
//		updateRowMap.put("idCardCity", peasantManage.getIdCardCity());
//		updateRowMap.put("idCardCounty", peasantManage.getIdCardCounty());
//		updateRowMap.put("idCardProvince", peasantManage.getIdCardProvince());
//		updateRowMap.put("age", peasantManage.getAge());
//		updateRowMap.put("joinCooperativeDate", peasantManage.getJoinCooperativeDate());
//		updateRowMap.put("lastName", peasantManage.getLastName());
//		updateRowMap.put("lastUpdateOperatorId", peasantManage.getLastUpdateOperatorId());
//		updateRowMap.put("liveAddress", peasantManage.getLiveAddress());
//		updateRowMap.put("liveStatus", peasantManage.getLiveStatus());
//        updateRowMap.put("name", peasantManage.getFristName() + peasantManage.getLastName());
//        updateRowMap.put("quitAgencyType", peasantManage.isQuitAgencyType());
//		updateRowMap.put("quitCooperativeDate", peasantManage.getQuitCooperativeDate());
//        updateRowMap.put("auditStatus", peasantManage.getAuditStatus());
//		updateRowMap.put("fristName", peasantManage.getFristName());
//		updateRowMap.put("createOperatorId", peasantManage.getCreateOperatorId());
//		updateRowMap.put("familyCount", peasantManage.getFamilyCount());
//		updateRowMap.put("gender", peasantManage.isGender());
//		updateRowMap.put("nativeId", peasantManage.getNativeId());
//		updateRowMap.put("maritalStatus", peasantManage.getMaritalStatus());
//		updateRowMap.put("birthday", peasantManage.getBirthday());
//		updateRowMap.put("identityState", peasantManage.getIdentityState());
//		updateRowMap.put("education", peasantManage.getEducation());
//		updateRowMap.put("telephoneNumber", peasantManage.getTelephoneNumber());
//		updateRowMap.put("cooperativeId", peasantManage.getCooperativeId());
//		updateRowMap.put("subsistenceAllowance", peasantManage.isSubsistenceAllowance());
//		updateRowMap.put("lastUpdateDate", new Date());
//
//        boolean flag = this.peasantManageDao.update(id,updateRowMap); 
//        //如果需要大于1时，抛出异常并回滚，需要在这里修改
//        return flag;
//    }
//    
//    /**
//     * 根据id更新PeasantManage实例<br/>
//     * <功能详细描述>
//     * @param peasantManage
//     * @return [参数说明]
//     * 
//     * @return boolean [返回类型说明]
//     * @exception throws [异常类型] [异常说明]
//     * @see [类、类#方法、类#成员]
//     */
//    @Transactional
//    public boolean updateById(PeasantInfo peasantManage) {
//        //验证参数是否合法，必填字段是否填写
//        AssertUtils.notNull(peasantManage, "peasantManage is null.");
//        AssertUtils.notEmpty(peasantManage.getId(), "peasantManage.id is empty.");
//
//        boolean flag = updateById(peasantManage.getId(),peasantManage); 
//        //如果需要大于1时，抛出异常并回滚，需要在这里修改
//        return flag;
//    }
//}
