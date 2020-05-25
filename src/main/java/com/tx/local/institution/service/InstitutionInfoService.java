/*
 * 描          述:  <描述>
 * 修  改   人:  
 * 修改时间:  
 * <修改描述:>
 */
package com.tx.local.institution.service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.tx.component.configuration.util.ConfigContextUtils;
import com.tx.core.exceptions.util.AssertUtils;
import com.tx.core.paged.model.PagedList;
import com.tx.core.querier.model.Querier;
import com.tx.core.querier.model.QuerierBuilder;
import com.tx.local.clientinfo.facade.ClientInfoFacade;
import com.tx.local.clientinfo.model.Client;
import com.tx.local.clientinfo.model.ClientInfo;
import com.tx.local.clientinfo.model.ClientStatusEnum;
import com.tx.local.clientinfo.model.ClientTypeEnum;
import com.tx.local.institution.dao.InstitutionInfoDao;
import com.tx.local.institution.model.InstitutionInfo;

/**
 * InstitutionInfo的业务层[InstitutionInfoService]
 * <功能详细描述>
 * 
 * @author  
 * @version [版本号]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
@Component("institutionInfoService")
public class InstitutionInfoService implements InitializingBean {
    
    @SuppressWarnings("unused")
    private Logger logger = LoggerFactory
            .getLogger(InstitutionInfoService.class);
    
    @Resource(name = "institutionInfoDao")
    private InstitutionInfoDao institutionInfoDao;
    
    @Resource
    private ClientInfoFacade clientInfoFacade;
    
    private String defaultClientPassword;
    
    /**
     * @throws Exception
     */
    @Override
    public void afterPropertiesSet() throws Exception {
        this.defaultClientPassword = ConfigContextUtils
                .getValue("system.config.client.default.password");
        AssertUtils.notEmpty(this.defaultClientPassword,
                "default client password is empty.");
    }
    
    /**
     * 新增InstitutionInfo实例<br/>
     * 将institutionInfo插入数据库中保存
     * 1、如果institutionInfo 为空时抛出参数为空异常
     * 2、如果institutionInfo 中部分必要参数为非法值时抛出参数不合法异常
     * 
     * @param institutionInfo [参数说明]
     * @return void [返回类型说明]
     * @exception throws
     * @see [类、类#方法、类#成员]
     */
    @Transactional
    public void insertClientAndInstitution(InstitutionInfo institutionInfo) {
        //验证参数是否合法
        AssertUtils.notNull(institutionInfo, "institutionInfo is null.");
        AssertUtils.notEmpty(institutionInfo.getVcid(),
                "institutionInfo.vcid is empty.");
        AssertUtils.notNull(institutionInfo.getType(),
                "institutionInfo.type is empty.");
        AssertUtils.notEmpty(institutionInfo.getName(),
                "institutionInfo.name is empty.");
        
        ClientInfo clientInfo = new ClientInfo();
        clientInfo.setStatus(ClientStatusEnum.WAIT_ACTIVATE);
        clientInfo.setType(ClientTypeEnum.INSTITUTION);
        clientInfo.setPassword(this.defaultClientPassword);
        if (StringUtils.isEmpty(clientInfo.getName())) {
            clientInfo.setName(institutionInfo.getName());
        }
        
        //if (clientInfoFacade.exists(QuerierBuilder.newInstance()
        //        .addFilter("username", OperatorEnum.eq, clientInfo.getName())
        //    .querier(), null)) {
        ////允许用中文名作为用户名
        //    clientInfo.setUsername(clientInfo.getName());
        //}
        clientInfo.setUsernameChangeAble(true);//允许用户名修改
        //如果用户名为空，则在用户自动插入期间，会生成一个随机的用户名
        this.clientInfoFacade.insert(clientInfo);
        
        institutionInfo.setClientId(clientInfo.getId());
        //验证参数是否合法
        insert(institutionInfo);
    }
    
    /**
     * 新增InstitutionInfo实例<br/>
     * 将institutionInfo插入数据库中保存
     * 1、如果institutionInfo 为空时抛出参数为空异常
     * 2、如果institutionInfo 中部分必要参数为非法值时抛出参数不合法异常
     * 
     * @param institutionInfo [参数说明]
     * @return void [返回类型说明]
     * @exception throws
     * @see [类、类#方法、类#成员]
     */
    @Transactional
    public void insert(InstitutionInfo institutionInfo) {
        //验证参数是否合法
        AssertUtils.notNull(institutionInfo, "institutionInfo is null.");
        AssertUtils.notEmpty(institutionInfo.getVcid(),
                "institutionInfo.vcid is empty.");
        AssertUtils.notNull(institutionInfo.getType(),
                "institutionInfo.type is empty.");
        AssertUtils.notEmpty(institutionInfo.getName(),
                "institutionInfo.name is empty.");
        
        //clientId不能为空
        AssertUtils.notEmpty(institutionInfo.getClientId(),
                "institutionInfo.clientId is empty.");
        
        //为添加的数据需要填入默认值的字段填入默认值
        institutionInfo.setLastUpdateDate(new Date());
        institutionInfo.setCreateDate(new Date());
        
        //调用数据持久层对实例进行持久化操作
        this.institutionInfoDao.insert(institutionInfo);
    }
    
    /**
     * 根据id删除InstitutionInfo实例
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
        
        InstitutionInfo condition = new InstitutionInfo();
        condition.setId(id);
        
        int resInt = this.institutionInfoDao.delete(condition);
        boolean flag = resInt > 0;
        return flag;
    }
    
    /**
     * 根据id查询InstitutionInfo实例
     * 1、当id为empty时抛出异常
     *
     * @param id
     * @return InstitutionInfo [返回类型说明]
     * @exception throws
     * @see [类、类#方法、类#成员]
     */
    public InstitutionInfo findById(String id) {
        AssertUtils.notEmpty(id, "id is empty.");
        
        InstitutionInfo condition = new InstitutionInfo();
        condition.setId(id);
        
        InstitutionInfo res = this.institutionInfoDao.find(condition);
        return res;
    }
    
    /**
     * 查询InstitutionInfo实例列表
     * <功能详细描述>
     * @param params      
     * @return [参数说明]
     * 
     * @return List<InstitutionInfo> [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    public List<InstitutionInfo> queryList(Map<String, Object> params) {
        //判断条件合法性
        
        //生成查询条件
        params = params == null ? new HashMap<String, Object>() : params;
        
        //根据实际情况，填入排序字段等条件，根据是否需要排序，选择调用dao内方法
        List<InstitutionInfo> resList = this.institutionInfoDao
                .queryList(params);
        
        return resList;
    }
    
    /**
     * 查询InstitutionInfo实例列表
     * <功能详细描述>
     * @param querier      
     * @return [参数说明]
     * 
     * @return List<InstitutionInfo> [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    public List<InstitutionInfo> queryList(Querier querier) {
        //判断条件合法性
        
        //生成查询条件
        querier = querier == null ? QuerierBuilder.newInstance().querier()
                : querier;
        
        //根据实际情况，填入排序字段等条件，根据是否需要排序，选择调用dao内方法
        List<InstitutionInfo> resList = this.institutionInfoDao
                .queryList(querier);
        
        return resList;
    }
    
    /**
     * 分页查询InstitutionInfo实例列表
     * <功能详细描述>
     * @param params    
     * @param pageIndex 当前页index从1开始计算
     * @param pageSize 每页显示行数
     * 
     * <功能详细描述>
     * @return [参数说明]
     * 
     * @return List<InstitutionInfo> [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    public PagedList<InstitutionInfo> queryPagedList(Map<String, Object> params,
            int pageIndex, int pageSize) {
        //T判断条件合法性
        
        //生成查询条件
        params = params == null ? new HashMap<String, Object>() : params;
        
        //根据实际情况，填入排序字段等条件，根据是否需要排序，选择调用dao内方法
        PagedList<InstitutionInfo> resPagedList = this.institutionInfoDao
                .queryPagedList(params, pageIndex, pageSize);
        
        return resPagedList;
    }
    
    /**
     * 分页查询InstitutionInfo实例列表
     * <功能详细描述>
     * @param params    
     * @param pageIndex 当前页index从1开始计算
     * @param pageSize 每页显示行数
     * 
     * <功能详细描述>
     * @return [参数说明]
     * 
     * @return List<InstitutionInfo> [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    public PagedList<InstitutionInfo> queryDetailPagedList(
            Map<String, Object> params, int pageIndex, int pageSize) {
        //T判断条件合法性
        
        //生成查询条件
        params = params == null ? new HashMap<String, Object>() : params;
        
        //根据实际情况，填入排序字段等条件，根据是否需要排序，选择调用dao内方法
        PagedList<InstitutionInfo> resPagedList = this.institutionInfoDao
                .queryPagedList(params, pageIndex, pageSize);
        
        Querier clientQuerier = QuerierBuilder.newInstance().querier();
        List<String> clientIds = resPagedList.getList().stream().map(ins -> {
            return ins.getClientId();
        }).collect(Collectors.toList());
        clientQuerier.getParams().put("ids", clientIds);
        List<ClientInfo> clientInfo = this.clientInfoFacade.queryList(null, clientQuerier);
        Map<String, Client> clientMap = new HashMap<>();
        clientInfo.forEach(ci -> {
            clientMap.put(ci.getId(), ci);
        });
        resPagedList.getList().forEach(ins -> {
            ins.setClient(clientMap.get(ins.getClientId()));
        });
        
         
        return resPagedList;
    }
    
    /**
     * 分页查询InstitutionInfo实例列表
     * <功能详细描述>
     * @param querier    
     * @param pageIndex 当前页index从1开始计算
     * @param pageSize 每页显示行数
     * 
     * <功能详细描述>
     * @return [参数说明]
     * 
     * @return List<InstitutionInfo> [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    public PagedList<InstitutionInfo> queryPagedList(Querier querier,
            int pageIndex, int pageSize) {
        //T判断条件合法性
        
        //生成查询条件
        querier = querier == null ? QuerierBuilder.newInstance().querier()
                : querier;
        
        //根据实际情况，填入排序字段等条件，根据是否需要排序，选择调用dao内方法
        PagedList<InstitutionInfo> resPagedList = this.institutionInfoDao
                .queryPagedList(querier, pageIndex, pageSize);
        
        return resPagedList;
    }
    
    /**
     * 查询InstitutionInfo实例数量<br/>
     * <功能详细描述>
     * @param params      
     * @return [参数说明]
     * 
     * @return List<InstitutionInfo> [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    public int count(Map<String, Object> params) {
        //判断条件合法性
        
        //生成查询条件
        params = params == null ? new HashMap<String, Object>() : params;
        
        //根据实际情况，填入排序字段等条件，根据是否需要排序，选择调用dao内方法
        int res = this.institutionInfoDao.count(params);
        
        return res;
    }
    
    /**
     * 查询InstitutionInfo实例数量<br/>
     * <功能详细描述>
     * @param querier      
     * @return [参数说明]
     * 
     * @return List<InstitutionInfo> [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    public int count(Querier querier) {
        //判断条件合法性
        
        //生成查询条件
        querier = querier == null ? QuerierBuilder.newInstance().querier()
                : querier;
        
        //根据实际情况，填入排序字段等条件，根据是否需要排序，选择调用dao内方法
        int res = this.institutionInfoDao.count(querier);
        
        return res;
    }
    
    /**
     * 判断InstitutionInfo实例是否已经存在<br/>
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
        int res = this.institutionInfoDao.count(params, excludeId);
        
        return res > 0;
    }
    
    /**
     * 判断InstitutionInfo实例是否已经存在<br/>
     * <功能详细描述>
     * @param key2valueMap
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
        int res = this.institutionInfoDao.count(querier, excludeId);
        
        return res > 0;
    }
    
    /**
     * 根据id更新InstitutionInfo实例<br/>
     * <功能详细描述>
     * @param institutionInfo
     * @return [参数说明]
     * 
     * @return boolean [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @Transactional
    public boolean updateById(String id, InstitutionInfo institutionInfo) {
        //验证参数是否合法，必填字段是否填写
        AssertUtils.notNull(institutionInfo, "institutionInfo is null.");
        AssertUtils.notEmpty(id, "id is empty.");
        AssertUtils.notEmpty(institutionInfo.getType(),
                "institutionInfo.type is empty.");
        AssertUtils.notEmpty(institutionInfo.isModifyAble(),
                "institutionInfo.modifyAble is empty.");
        
        //生成需要更新字段的hashMap
        Map<String, Object> updateRowMap = new HashMap<String, Object>();
        //FIXME:需要更新的字段
        updateRowMap.put("city", institutionInfo.getCity());
        updateRowMap.put("fullAddress", institutionInfo.getFullAddress());
        updateRowMap.put("lastUpdateUserId",
                institutionInfo.getLastUpdateUserId());
        updateRowMap.put("linkName", institutionInfo.getLinkName());
        updateRowMap.put("linkMobileNumber",
                institutionInfo.getLinkMobileNumber());
        updateRowMap.put("name", institutionInfo.getName());
        updateRowMap.put("type", institutionInfo.getType());
        updateRowMap.put("clientId", institutionInfo.getClientId());
        updateRowMap.put("county", institutionInfo.getCounty());
        updateRowMap.put("modifyAble", institutionInfo.isModifyAble());
        updateRowMap.put("remark", institutionInfo.getRemark());
        updateRowMap.put("address", institutionInfo.getAddress());
        updateRowMap.put("district", institutionInfo.getDistrict());
        updateRowMap.put("postcode", institutionInfo.getPostcode());
        updateRowMap.put("province", institutionInfo.getProvince());
        updateRowMap.put("lastUpdateDate", new Date());
        
        boolean flag = this.institutionInfoDao.update(id, updateRowMap);
        //如果需要大于1时，抛出异常并回滚，需要在这里修改
        return flag;
    }
    
    /**
     * 根据id更新InstitutionInfo实例<br/>
     * <功能详细描述>
     * @param institutionInfo
     * @return [参数说明]
     * 
     * @return boolean [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @Transactional
    public boolean updateById(InstitutionInfo institutionInfo) {
        //验证参数是否合法，必填字段是否填写
        AssertUtils.notNull(institutionInfo, "institutionInfo is null.");
        AssertUtils.notEmpty(institutionInfo.getId(),
                "institutionInfo.id is empty.");
        
        boolean flag = updateById(institutionInfo.getId(), institutionInfo);
        //如果需要大于1时，抛出异常并回滚，需要在这里修改
        return flag;
    }
}
