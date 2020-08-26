/*
 * 描          述:  <描述>
 * 修  改   人:  
 * 修改时间:  
 * <修改描述:>
 */
package com.tx.local.personal.service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.tx.core.exceptions.util.AssertUtils;
import com.tx.core.paged.model.PagedList;
import com.tx.core.querier.model.Querier;
import com.tx.core.querier.model.QuerierBuilder;
import com.tx.local.clientinfo.facade.ClientInfoFacade;
import com.tx.local.clientinfo.model.ClientInfo;
import com.tx.local.clientinfo.model.ClientStatusEnum;
import com.tx.local.clientinfo.model.ClientTypeEnum;
import com.tx.local.clientinfo.service.ClientInfoService;
import com.tx.local.personal.dao.PersonalInfoDao;
import com.tx.local.personal.model.PersonalInfo;
import com.tx.local.personal.model.PersonalSummary;
import com.tx.local.security.service.ClientUserDetailsService;
import com.tx.local.security.util.ClientWebContextUtils;
import com.tx.local.security.util.WebContextUtils;

/**
 * PersonalInfo的业务层[PersonalInfoService]
 * <功能详细描述>
 * 
 * @author  
 * @version [版本号]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
@Component("personalInfoService")
public class PersonalInfoService {
    
    @SuppressWarnings("unused")
    private Logger logger = LoggerFactory.getLogger(PersonalInfoService.class);
    
    @Resource(name = "personalInfoDao")
    private PersonalInfoDao personalInfoDao;
    
    @Resource
    private ClientInfoFacade clientInfoFacade;
    
    @Resource(name = "clientInfoService")
    private ClientInfoService clientInfoService;
    
    @Resource(name = "personalSummaryService")
    private PersonalSummaryService personalSummaryService;
    
    @Resource(name = "clientUserDetailsService")
    private ClientUserDetailsService clientUserDetailsService;
    
    /**
     * 新增personalInfoo实例<br/>
     * 将personalInfo插入数据库中保存
     * 1、如果personalInfo 为空时抛出参数为空异常
     * 2、如果personalInfo 中部分必要参数为非法值时抛出参数不合法异常
     *
     * @param personalInfo [参数说明]
     * @return void [返回类型说明]
     * @exception throws
     * @see [类、类#方法、类#成员]
     */
    @Transactional
    public Map<String, Object> insertClientAndPersonal(
            PersonalInfo personalInfo) {
        //返回数据
        Map<String, Object> responseMap = new HashMap<>();
        
        personalInfo.setName(
                personalInfo.getFristName() + personalInfo.getLastName());
        //验证参数是否合法
        AssertUtils.notNull(personalInfo, "personalInfo is null.");
        AssertUtils.notEmpty(personalInfo.getLastName(),
                "personalInfo.lastName is empty.");
        AssertUtils.notEmpty(personalInfo.getName(),
                "personalInfo.name is empty.");
        AssertUtils.notEmpty(personalInfo.getType(),
                "personalInfo.type is empty.");
        AssertUtils.notEmpty(personalInfo.getVcid(),
                "personalInfo.vcid is empty.");
        AssertUtils.notEmpty(personalInfo.getFristName(),
                "personalInfo.fristName is empty.");
        
        //手机号验证
        Map<String, String> params = new HashMap<>();
        params.put("linkMobileNumber", personalInfo.getLinkMobileNumber());
        boolean exists = exists(params, null);
        if (exists) {
            responseMap.put("success", false);
            responseMap.put("msg", "该手机号码已注册!");
            return responseMap;
        }
        
        //身份证验证
        params = new HashMap<>();
        params.put("idCardNumber", personalInfo.getIdCardNumber());
        exists = exists(params, null);
        if (exists) {
            responseMap.put("success", false);
            responseMap.put("msg", "该身份证已注册!");
            return responseMap;
        }
        
        ClientInfo clientInfo = new ClientInfo();
        clientInfo.setStatus(ClientStatusEnum.WAIT_ACTIVATE);
        clientInfo.setType(ClientTypeEnum.PERSONAL);
        clientInfo.setVcid(personalInfo.getVcid());
        clientInfo.setValid(true);
        if (StringUtils.isEmpty(clientInfo.getName())) {
            clientInfo.setName(personalInfo.getName());
        }
        clientInfo.setUsernameChangeAble(true);//允许用户名修改
        //如果用户名为空，则在用户自动插入期间，会生成一个随机的用户名
        this.clientInfoFacade.insert(clientInfo);
        //激活该客户
        this.clientInfoFacade.enableById(clientInfo.getId());
        //根据不同的客户类型 默认给该客户插入不同的角色权限
        clientUserDetailsService.setClientUserRole(clientInfo.getId(),
                clientInfo.getType());
        
        //给前端用户返回的登录账号
        responseMap.put("msg", clientInfo.getCode());
        
        personalInfo.setClientId(clientInfo.getId());
        //验证参数是否合法
        insert(personalInfo);
        
        return responseMap;
    }
    
    /**
     * 修改个人用户时把客户扩展信息修改
     *
     * @param personalInfo [参数说明]
     * @return void [返回类型说明]
     * @exception throws
     * @see [类、类#方法、类#成员]
     */
    @Transactional
    public boolean updateClientAndPersonal(PersonalInfo personalInfo) {
        //验证参数是否合法，必填字段是否填写
        AssertUtils.notNull(personalInfo, "personalInfo is null.");
        AssertUtils.notEmpty(personalInfo.getId(), "personalInfo.id is empty.");
        personalInfo.setName(
                personalInfo.getFristName() + personalInfo.getLastName());
        
        //调用数据持久层对实例进行持久化操作
        boolean flag = updateById(personalInfo);
        
        return flag;
    }
    
    @Transactional
    public Map<String, Object> updatePersonalAndSummaryById(
            PersonalInfo personalInfo, PersonalSummary personalSummary) {
        //返回数据
        Map<String, Object> responseMap = new HashMap<>();
        //验证参数是否合法，必填字段是否填写
        AssertUtils.notNull(personalInfo, "personalInfo is null.");
        AssertUtils.notEmpty(personalInfo.getId(), "personalInfo.id is empty.");
        personalInfo.setFristName(personalInfo.getName().substring(0, 1));
        personalInfo.setLastName(personalInfo.getName()
                .substring(1, personalInfo.getName().length()));
        
        //手机号验证
        Map<String, String> params = new HashMap<>();
        params.put("linkMobileNumber", personalInfo.getLinkMobileNumber());
        boolean exists = exists(params, personalInfo.getId());
        if (exists) {
            responseMap.put("success", false);
            responseMap.put("msg", "该手机号码已注册!");
            return responseMap;
        }
        if (exists) {
            responseMap.put("success", false);
            responseMap.put("msg", "该手机号码已注册!");
            return responseMap;
        }
        //身份证验证
        params = new HashMap<>();
        params.put("idCardNumber", personalInfo.getIdCardNumber());
        exists = exists(params, personalInfo.getId());
        if (exists) {
            responseMap.put("success", false);
            responseMap.put("msg", "该身份证已注册!");
            return responseMap;
        }
        
        PersonalSummary psyItem = personalSummaryService
                .findByPersonalId(personalInfo.getId());
        personalSummary.setId(psyItem.getId());
        personalSummary.setVcid(personalInfo.getVcid());
        personalSummary.setPersonalId(personalInfo.getId());
        personalSummaryService.updateById(personalSummary);
        
        //调用数据持久层对实例进行持久化操作
        updateById(personalInfo);
        
        responseMap.put("success", true);
        return responseMap;
    }
    
    /**
     * 新增PersonalInfo实例<br/>
     * 将personalInfo插入数据库中保存
     * 1、如果personalInfo 为空时抛出参数为空异常
     * 2、如果personalInfo 中部分必要参数为非法值时抛出参数不合法异常
     * 
     * @param personalInfo [参数说明]
     * @return void [返回类型说明]
     * @exception throws
     * @see [类、类#方法、类#成员]
     */
    @Transactional
    public void insert(PersonalInfo personalInfo) {
        //验证参数是否合法
        AssertUtils.notNull(personalInfo, "personalInfo is null.");
        AssertUtils.notEmpty(personalInfo.getLastName(),
                "personalInfo.lastName is empty.");
        AssertUtils.notEmpty(personalInfo.getName(),
                "personalInfo.name is empty.");
        AssertUtils.notEmpty(personalInfo.getType(),
                "personalInfo.type is empty.");
        AssertUtils.notEmpty(personalInfo.getVcid(),
                "personalInfo.vcid is empty.");
        AssertUtils.notEmpty(personalInfo.getFristName(),
                "personalInfo.fristName is empty.");
        AssertUtils.notEmpty(personalInfo.isCreditInfoBinding(),
                "personalInfo.creditInfoBinding is empty.");
        AssertUtils.notEmpty(personalInfo.isModifyAble(),
                "personalInfo.modifyAble is empty.");
        
        //FIXME:为添加的数据需要填入默认值的字段填入默认值
        personalInfo.setLastUpdateDate(new Date());
        personalInfo.setCreateDate(new Date());
        
        //调用数据持久层对实例进行持久化操作
        this.personalInfoDao.insert(personalInfo);
    }
    
    /**
     * 根据id删除PersonalInfo实例
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
        
        PersonalInfo condition = new PersonalInfo();
        condition.setId(id);
        
        int resInt = this.personalInfoDao.delete(condition);
        boolean flag = resInt > 0;
        return flag;
    }
    
    /**
     * 根据id查询PersonalInfo实例
     * 1、当id为empty时抛出异常
     *
     * @param id
     * @return PersonalInfo [返回类型说明]
     * @exception throws
     * @see [类、类#方法、类#成员]
     */
    public PersonalInfo findById(String id) {
        AssertUtils.notEmpty(id, "id is empty.");
        
        PersonalInfo condition = new PersonalInfo();
        condition.setId(id);
        
        PersonalInfo res = this.personalInfoDao.find(condition);
        return res;
    }
    
    public PersonalInfo findByClientId(String client) {
        AssertUtils.notEmpty(client, "client is empty.");
        
        PersonalInfo condition = new PersonalInfo();
        condition.setClientId(client);
        
        PersonalInfo res = this.personalInfoDao.find(condition);
        if (res != null) {
            PersonalSummary personalSummary = personalSummaryService
                    .findByPersonalId(res.getId());
            res.setPersonalSummary(personalSummary);
        }
        return res;
    }
    
    /**
     * 查询PersonalInfo实例列表
     * <功能详细描述>
     * @param params      
     * @return [参数说明]
     * 
     * @return List<PersonalInfo> [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    public List<PersonalInfo> queryList(Map<String, Object> params) {
        //判断条件合法性
        
        //生成查询条件
        params = params == null ? new HashMap<String, Object>() : params;
        params.put("isClient", "true");//查询没有被锁定的用户
        
        String _vcid = "";
        if (StringUtils.isNotBlank(WebContextUtils.getVcid())) {
            _vcid = WebContextUtils.getVcid();
        }
        if (ClientWebContextUtils.getClient() != null) {
            _vcid = ClientWebContextUtils.getClient().getVcid();
        }
        if (!"PT".equals(_vcid)) {
            params.put("vcid", _vcid);
        }
        
        //根据实际情况，填入排序字段等条件，根据是否需要排序，选择调用dao内方法
        List<PersonalInfo> resList = this.personalInfoDao.queryList(params);
        
        Map<String, Object> map = new HashMap<String, Object>();
        if (!"PT".equals(_vcid)) {
            map.put("vcid", _vcid);
        }
        
        List<PersonalSummary> summarys = personalSummaryService.queryList(map);
        Map<String, PersonalSummary> mappedPersonalSummary = summarys.stream()
                .collect(Collectors.toMap(PersonalSummary::getPersonalId,
                        (p) -> p));
        
        for (PersonalInfo item : resList) {
            item.setPersonalSummary(mappedPersonalSummary.get(item.getId()));
        }
        
        return resList;
    }
    
    /**
     * 查询PersonalInfo实例列表
     * <功能详细描述>
     * @param querier      
     * @return [参数说明]
     * 
     * @return List<PersonalInfo> [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    public List<PersonalInfo> queryList(Querier querier) {
        //判断条件合法性
        
        //生成查询条件
        querier = querier == null ? QuerierBuilder.newInstance().querier()
                : querier;
        
        //根据实际情况，填入排序字段等条件，根据是否需要排序，选择调用dao内方法
        List<PersonalInfo> resList = this.personalInfoDao.queryList(querier);
        
        return resList;
    }
    
    /**
     * 分页查询PersonalInfo实例列表
     * <功能详细描述>
     * @param params    
     * @param pageIndex 当前页index从1开始计算
     * @param pageSize 每页显示行数
     * 
     * <功能详细描述>
     * @return [参数说明]
     * 
     * @return List<PersonalInfo> [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    public PagedList<PersonalInfo> queryPagedList(Map<String, Object> params,
            int pageIndex, int pageSize) {
        //T判断条件合法性
        
        //生成查询条件
        params = params == null ? new HashMap<String, Object>() : params;
        params.put("isClient", "true");//查询没有被锁定的用户
        
        String _vcid = "";
        if (StringUtils.isNotBlank(WebContextUtils.getVcid())) {
            _vcid = WebContextUtils.getVcid();
        }
        if (ClientWebContextUtils.getClient() != null) {
            _vcid = ClientWebContextUtils.getClient().getVcid();
        }
        if (!"PT".equals(_vcid)) {
            params.put("vcid", _vcid);
        }
        
        //根据实际情况，填入排序字段等条件，根据是否需要排序，选择调用dao内方法
        PagedList<PersonalInfo> resPagedList = this.personalInfoDao
                .queryPagedList(params, pageIndex, pageSize);
        
        Map<String, Object> map = new HashMap<String, Object>();
        if (!"PT".equals(_vcid)) {
            map.put("vcid", _vcid);
        }
        List<PersonalSummary> summarys = personalSummaryService
                .queryList(new HashMap<>());
        Map<String, PersonalSummary> mappedPersonalSummary = summarys.stream()
                .collect(Collectors.toMap(PersonalSummary::getPersonalId,
                        (p) -> p));
        
        List<ClientInfo> clientInfos = clientInfoService.queryList(true, map);
        Map<String, ClientInfo> mappedClient = clientInfos.stream()
                .collect(Collectors.toMap(ClientInfo::getId, (p) -> p));
        
        List<PersonalInfo> list = resPagedList.getList();
        for (PersonalInfo item : list) {
            item.setPersonalSummary(mappedPersonalSummary.get(item.getId()));
            item.setClientInfo(mappedClient.get(item.getClientId()));
        }
        resPagedList.setList(list);
        return resPagedList;
    }
    
    /**
     * 分页查询PersonalInfo实例列表
     * <功能详细描述>
     * @param querier    
     * @param pageIndex 当前页index从1开始计算
     * @param pageSize 每页显示行数
     * 
     * <功能详细描述>
     * @return [参数说明]
     * 
     * @return List<PersonalInfo> [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    public PagedList<PersonalInfo> queryPagedList(Querier querier,
            int pageIndex, int pageSize) {
        //T判断条件合法性
        
        //生成查询条件
        querier = querier == null ? QuerierBuilder.newInstance().querier()
                : querier;
        
        //根据实际情况，填入排序字段等条件，根据是否需要排序，选择调用dao内方法
        PagedList<PersonalInfo> resPagedList = this.personalInfoDao
                .queryPagedList(querier, pageIndex, pageSize);
        
        return resPagedList;
    }
    
    /**
     * 查询PersonalInfo实例数量<br/>
     * <功能详细描述>
     * @param params      
     * @return [参数说明]
     * 
     * @return List<PersonalInfo> [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    public int count(Map<String, Object> params) {
        //判断条件合法性
        
        //生成查询条件
        params = params == null ? new HashMap<String, Object>() : params;
        
        //根据实际情况，填入排序字段等条件，根据是否需要排序，选择调用dao内方法
        int res = this.personalInfoDao.count(params);
        
        return res;
    }
    
    /**
     * 查询PersonalInfo实例数量<br/>
     * <功能详细描述>
     * @param querier      
     * @return [参数说明]
     * 
     * @return List<PersonalInfo> [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    public int count(Querier querier) {
        //判断条件合法性
        
        //生成查询条件
        querier = querier == null ? QuerierBuilder.newInstance().querier()
                : querier;
        
        //根据实际情况，填入排序字段等条件，根据是否需要排序，选择调用dao内方法
        int res = this.personalInfoDao.count(querier);
        
        return res;
    }
    
    /**
     * 判断PersonalInfo实例是否已经存在<br/>
     * <功能详细描述>
     * @param key2valueMap
     * @param excludeId
     * @return
     * 
     * @return int [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    public boolean exists(Map<String, String> key2valueMap, String excludeId) {
        AssertUtils.notEmpty(key2valueMap, "key2valueMap is empty");
        
        //生成查询条件
        Map<String, Object> params = new HashMap<String, Object>();
        params.putAll(key2valueMap);
        
        //根据实际情况，填入排序字段等条件，根据是否需要排序，选择调用dao内方法
        int res = this.personalInfoDao.count(params, excludeId);
        
        return res > 0;
    }
    
    /**
     * 判断PersonalInfo实例是否已经存在<br/>
     * <功能详细描述>
     * @param querier
     * @param excludeId
     * @return
     * 
     * @return int [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    public boolean exists(Querier querier, String excludeId) {
        AssertUtils.notNull(querier, "querier is null.");
        
        //根据实际情况，填入排序字段等条件，根据是否需要排序，选择调用dao内方法
        int res = this.personalInfoDao.count(querier, excludeId);
        
        return res > 0;
    }
    
    /**
     * 删除掉该企业所有信息
     * @param id
     * @return
     */
    @Transactional
    public boolean deleteLogicById(String id) {
        AssertUtils.notEmpty(id, "id is empty.");
        
        HashMap map = new HashMap<String, Object>();
        map.put("operationStatus", false);
        
        //根据ID查询 企业信息
        PersonalInfo personalInfo = findById(id);
        return true;
    }
    
    /**
     * 根据id更新PersonalInfo实例<br/>
     * <功能详细描述>
     * @param personalInfo
     * @return [参数说明]
     * 
     * @return boolean [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @Transactional
    public boolean updateById(String id, PersonalInfo personalInfo) {
        //验证参数是否合法，必填字段是否填写
        AssertUtils.notNull(personalInfo, "personalInfo is null.");
        AssertUtils.notEmpty(id, "id is empty.");
        AssertUtils.notEmpty(personalInfo.getName(),
                "personalInfo.name is empty.");
        AssertUtils.notEmpty(personalInfo.getType(),
                "personalInfo.type is empty.");
        AssertUtils.notEmpty(personalInfo.isCreditInfoBinding(),
                "personalInfo.creditInfoBinding is empty.");
        AssertUtils.notEmpty(personalInfo.isModifyAble(),
                "personalInfo.modifyAble is empty.");
        
        //生成需要更新字段的hashMap
        Map<String, Object> updateRowMap = new HashMap<String, Object>();
        //FIXME:需要更新的字段
        updateRowMap.put("idCardNumber", personalInfo.getIdCardNumber());
        updateRowMap.put("sex", personalInfo.getSex());
        updateRowMap.put("city", personalInfo.getCity());
        updateRowMap.put("fullAddress", personalInfo.getFullAddress());
        updateRowMap.put("lastUpdateUserId",
                personalInfo.getLastUpdateUserId());
        updateRowMap.put("linkMobileNumber",
                personalInfo.getLinkMobileNumber());
        updateRowMap.put("name", personalInfo.getName());
        updateRowMap.put("type", personalInfo.getType());
        updateRowMap.put("clientId", personalInfo.getClientId());
        updateRowMap.put("county", personalInfo.getCounty());
        updateRowMap.put("creditInfoId", personalInfo.getCreditInfoId());
        updateRowMap.put("creditInfoBinding",
                personalInfo.isCreditInfoBinding());
        updateRowMap.put("modifyAble", personalInfo.isModifyAble());
        updateRowMap.put("remark", personalInfo.getRemark());
        updateRowMap.put("address", personalInfo.getAddress());
        updateRowMap.put("birthday", personalInfo.getBirthday());
        updateRowMap.put("district", personalInfo.getDistrict());
        updateRowMap.put("province", personalInfo.getProvince());
        updateRowMap.put("lastUpdateDate", new Date());
        
        boolean flag = this.personalInfoDao.update(id, updateRowMap);
        //如果需要大于1时，抛出异常并回滚，需要在这里修改
        return flag;
    }
    
    /**
     * 根据id更新PersonalInfo实例<br/>
     * <功能详细描述>
     * @param personalInfo
     * @return [参数说明]
     * 
     * @return boolean [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @Transactional
    public boolean updateById(PersonalInfo personalInfo) {
        //验证参数是否合法，必填字段是否填写
        AssertUtils.notNull(personalInfo, "personalInfo is null.");
        AssertUtils.notEmpty(personalInfo.getId(), "personalInfo.id is empty.");
        
        boolean flag = updateById(personalInfo.getId(), personalInfo);
        //如果需要大于1时，抛出异常并回滚，需要在这里修改
        return flag;
    }
}
