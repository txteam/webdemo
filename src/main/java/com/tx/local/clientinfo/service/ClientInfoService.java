/*
 * 描          述:  <描述>
 * 修  改   人:  
 * 修改时间:  
 * <修改描述:>
 */
package com.tx.local.clientinfo.service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.annotation.DependsOn;
import org.springframework.jdbc.support.incrementer.DataFieldMaxValueIncrementer;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.tx.component.configuration.util.ConfigContextUtils;
import com.tx.component.servicelogger.util.ServiceLoggerUtils;
import com.tx.core.exceptions.util.AssertUtils;
import com.tx.core.paged.model.PagedList;
import com.tx.core.querier.model.Filter;
import com.tx.core.querier.model.Querier;
import com.tx.core.querier.model.QuerierBuilder;
import com.tx.core.util.MessageUtils;
import com.tx.front4client.security.util.ClientWebContextUtils;
import com.tx.local.basicdata.model.IDCardTypeEnum;
import com.tx.local.clientinfo.dao.ClientInfoDao;
import com.tx.local.clientinfo.model.ClientInfo;
import com.tx.local.clientinfo.model.ClientSecOperateLog;
import com.tx.local.clientinfo.model.ClientStatusEnum;
import com.tx.local.security.util.WebContextUtils;

/**
 * ClientInfo的业务层[ClientInfoService]
 * <功能详细描述>
 * 
 * @author  
 * @version [版本号]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
@DependsOn("configContext")
@Component("clientInfoService")
public class ClientInfoService implements InitializingBean {
    
    @SuppressWarnings("unused")
    private Logger logger = LoggerFactory.getLogger(ClientInfoService.class);
    
    @Resource
    public PasswordEncoder passwordEncoder;
    
    @Resource(name = "clientInfoDao")
    private ClientInfoDao clientInfoDao;
    
    @Resource(name = "clientinfo.sequence")
    private DataFieldMaxValueIncrementer codeValueIncrementer;
    
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
     * 生成新的编码<br/>
     * <功能详细描述>
     * @return [参数说明]
     * 
     * @return String [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    private String generateCode() {
        String newCode = "" + DateTime.now().getYear()
                + codeValueIncrementer.nextStringValue();
        return newCode;
    }
    
    /**
     * 新增ClientInfo实例<br/>
     * 将clientInfo插入数据库中保存
     * 1、如果clientInfo 为空时抛出参数为空异常
     * 2、如果clientInfo 中部分必要参数为非法值时抛出参数不合法异常
     * 
     * @param clientInfo [参数说明]
     * @return void [返回类型说明]
     * @exception throws
     * @see [类、类#方法、类#成员]
     */
    @Transactional
    public void insert(ClientInfo clientInfo) {
        //验证参数是否合法
        AssertUtils.notNull(clientInfo, "clientInfo is null.");
        AssertUtils.notNull(clientInfo.getType(), "clientInfo.type is null.");
        AssertUtils.notNull(clientInfo.getStatus(),
                "clientInfo.status is null.");
        AssertUtils.notEmpty(clientInfo.getVcid(), "clientInfo.vcid is empty.");
        
        if (StringUtils.isEmpty(clientInfo.getCode())) {
            //如果编码为空，则自动生成code写入
            clientInfo.setCode(generateCode());
        }
        if (StringUtils.isEmpty(clientInfo.getUsername())) {
            //如果编码为空，则自动生成code写入
            clientInfo.setUsername(clientInfo.getCode());
        }
        if (StringUtils.isEmpty(clientInfo.getPassword())) {
            //如果编码为空，则自动生成code写入
            clientInfo.setPassword(this.defaultClientPassword);
        }
        
        //为添加的数据需要填入默认值的字段填入默认值
        //clientInfo.setValid(true);
        switch (clientInfo.getStatus()) {
            case ACTIVATED:
                clientInfo.setValid(true);
                break;
            case WAIT_ACTIVATE:
            case DISABLED:
            default:
                clientInfo.setValid(false);
                break;
        }
        clientInfo.setLocked(false);
        clientInfo.setPassword(
                this.passwordEncoder.encode(clientInfo.getPassword()));
        
        //写入默认时间
        Date now = new Date();
        clientInfo.setCreateDate(now);
        clientInfo.setLastUpdateDate(now);
        clientInfo.setPwdErrCount(0);
        clientInfo.setUsernameChangeCount(0);
        
        //调用数据持久层对实例进行持久化操作
        this.clientInfoDao.insert(clientInfo);
    }
    
    /**
     * 根据id删除ClientInfo实例
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
        
        ClientInfo condition = new ClientInfo();
        condition.setId(id);
        
        int resInt = this.clientInfoDao.delete(condition);
        boolean flag = resInt > 0;
        return flag;
    }
    
    /**
     * 根据id查询ClientInfo实例
     * 1、当id为empty时抛出异常
     *
     * @param id
     * @return ClientInfo [返回类型说明]
     * @exception throws
     * @see [类、类#方法、类#成员]
     */
    public ClientInfo findById(String id) {
        AssertUtils.notEmpty(id, "id is empty.");
        
        ClientInfo condition = new ClientInfo();
        condition.setId(id);
        
        ClientInfo res = this.clientInfoDao.find(condition);
        return res;
    }
    
    /**
     * 根据id查询ClientInfo实例
     * 1、当id为empty时抛出异常
     *
     * @param id
     * @return ClientInfo [返回类型说明]
     * @exception throws
     * @see [类、类#方法、类#成员]
     */
    public ClientInfo findByUsername(String username) {
        AssertUtils.notEmpty(username, "username is empty.");
        
        ClientInfo condition = new ClientInfo();
        condition.setUsername(username);
        
        ClientInfo res = this.clientInfoDao.find(condition);
        return res;
    }
    
    /**
     * 根据id查询ClientInfo实例
     * 1、当id为empty时抛出异常
     *
     * @param id
     * @return ClientInfo [返回类型说明]
     * @exception throws
     * @see [类、类#方法、类#成员]
     */
    public ClientInfo findByCode(String code) {
        AssertUtils.notEmpty(code, "code is empty.");
        
        ClientInfo condition = new ClientInfo();
        condition.setCode(code);
        
        ClientInfo res = this.clientInfoDao.find(condition);
        return res;
    }
    
    /**
     * 查询ClientInfo实例列表
     * <功能详细描述>
     * @param valid
     * @param params      
     * @return [参数说明]
     * 
     * @return List<ClientInfo> [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    public List<ClientInfo> queryList(Boolean valid,
            Map<String, Object> params) {
        //判断条件合法性
        
        //生成查询条件
        params = params == null ? new HashMap<String, Object>() : params;
        params.put("valid", valid);
        
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
        List<ClientInfo> resList = this.clientInfoDao.queryList(params);
        
        return resList;
    }
    
    /**
     * 查询ClientInfo实例列表
     * <功能详细描述>
     * @param valid
     * @param querier      
     * @return [参数说明]
     * 
     * @return List<ClientInfo> [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    public List<ClientInfo> queryList(Boolean valid, Querier querier) {
        //判断条件合法性
        
        //生成查询条件
        querier = querier == null ? QuerierBuilder.newInstance().querier()
                : querier;
        if (valid != null) {
            querier.getFilters().add(Filter.eq("valid", valid));
        }
        
        //根据实际情况，填入排序字段等条件，根据是否需要排序，选择调用dao内方法
        List<ClientInfo> resList = this.clientInfoDao.queryList(querier);
        
        return resList;
    }
    
    /**
     * 分页查询ClientInfo实例列表
     * <功能详细描述>
     * @param valid
     * @param params    
     * @param pageIndex 当前页index从1开始计算
     * @param pageSize 每页显示行数
     * 
     * <功能详细描述>
     * @return [参数说明]
     * 
     * @return List<ClientInfo> [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    public PagedList<ClientInfo> queryPagedList(Boolean valid,
            Map<String, Object> params, int pageIndex, int pageSize) {
        //T判断条件合法性
        
        //生成查询条件
        params = params == null ? new HashMap<String, Object>() : params;
        params.put("valid", valid);
        
        //根据实际情况，填入排序字段等条件，根据是否需要排序，选择调用dao内方法
        PagedList<ClientInfo> resPagedList = this.clientInfoDao
                .queryPagedList(params, pageIndex, pageSize);
        
        return resPagedList;
    }
    
    /**
     * 分页查询ClientInfo实例列表
     * <功能详细描述>
     * @param valid
     * @param querier    
     * @param pageIndex 当前页index从1开始计算
     * @param pageSize 每页显示行数
     * 
     * <功能详细描述>
     * @return [参数说明]
     * 
     * @return List<ClientInfo> [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    public PagedList<ClientInfo> queryPagedList(Boolean valid, Querier querier,
            int pageIndex, int pageSize) {
        //T判断条件合法性
        
        //生成查询条件
        querier = querier == null ? QuerierBuilder.newInstance().querier()
                : querier;
        if (valid != null) {
            querier.getFilters().add(Filter.eq("valid", valid));
        }
        
        //根据实际情况，填入排序字段等条件，根据是否需要排序，选择调用dao内方法
        PagedList<ClientInfo> resPagedList = this.clientInfoDao
                .queryPagedList(querier, pageIndex, pageSize);
        
        return resPagedList;
    }
    
    /**
     * 查询ClientInfo实例数量<br/>
     * <功能详细描述>
     * @param valid
     * @param params      
     * @return [参数说明]
     * 
     * @return List<ClientInfo> [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    public int count(Boolean valid, Map<String, Object> params) {
        //判断条件合法性
        
        //生成查询条件
        params = params == null ? new HashMap<String, Object>() : params;
        params.put("valid", valid);
        
        //根据实际情况，填入排序字段等条件，根据是否需要排序，选择调用dao内方法
        int res = this.clientInfoDao.count(params);
        
        return res;
    }
    
    /**
     * 查询ClientInfo实例数量<br/>
     * <功能详细描述>
     * @param valid
     * @param querier      
     * @return [参数说明]
     * 
     * @return List<ClientInfo> [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    public int count(Boolean valid, Querier querier) {
        //判断条件合法性
        
        //生成查询条件
        querier = querier == null ? QuerierBuilder.newInstance().querier()
                : querier;
        if (valid != null) {
            querier.getFilters().add(Filter.eq("valid", valid));
        }
        
        //根据实际情况，填入排序字段等条件，根据是否需要排序，选择调用dao内方法
        int res = this.clientInfoDao.count(querier);
        
        return res;
    }
    
    /**
     * 判断ClientInfo实例是否已经存在<br/>
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
        int res = this.clientInfoDao.count(params, excludeId);
        
        return res > 0;
    }
    
    /**
     * 判断ClientInfo实例是否已经存在<br/>
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
        int res = this.clientInfoDao.count(querier, excludeId);
        
        return res > 0;
    }
    
    /**
     * 根据id更新操作人员账户安全设置实例<br/>
     * <功能详细描述>
     * @param clientSecurityAccount
     * @return [参数说明]
     * 
     * @return boolean [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @Transactional
    public boolean updateSecurityAccountById(ClientInfo clientSecurityAccount) {
        //验证参数是否合法，必填字段是否填写
        AssertUtils.notNull(clientSecurityAccount,
                "clientSecurityAccount is null.");
        AssertUtils.notEmpty(clientSecurityAccount.getId(), "id is empty.");
        
        //生成需要更新字段的hashMap
        Map<String, Object> updateRowMap = new HashMap<String, Object>();
        //需要更新的字段
        updateRowMap.put("idCardType", clientSecurityAccount.getIdCardType());
        updateRowMap.put("idCardNumber",
                clientSecurityAccount.getIdCardNumber());
        updateRowMap.put("idCardExpiryDate",
                clientSecurityAccount.getIdCardExpiryDate());
        updateRowMap.put("realNameAuthenticated",
                clientSecurityAccount.isRealNameAuthenticated());
        updateRowMap.put("realNameErrCount",
                clientSecurityAccount.getRealNameErrCount());
        updateRowMap.put("realNameLastErrDate",
                clientSecurityAccount.getRealNameLastErrDate());
        
        updateRowMap.put("creditInfoBinding",
                clientSecurityAccount.isCreditInfoBinding());
        updateRowMap.put("creditInfoId",
                clientSecurityAccount.getCreditInfoId());
        
        updateRowMap.put("email", clientSecurityAccount.getEmail());
        updateRowMap.put("emailBinding",
                clientSecurityAccount.isEmailBinding());
        
        updateRowMap.put("mobileNumber",
                clientSecurityAccount.getMobileNumber());
        updateRowMap.put("mobileBinding",
                clientSecurityAccount.isMobileBinding());
        updateRowMap.put("mobileLoginEnable",
                clientSecurityAccount.isMobileLoginEnable());
        updateRowMap.put("lastUpdateDate", new Date());
        
        boolean flag = this.clientInfoDao.update(clientSecurityAccount.getId(),
                updateRowMap);
        //如果需要大于1时，抛出异常并回滚，需要在这里修改
        return flag;
    }
    
    /**
     * 根据id更新ClientInfo实例<br/>
     * <功能详细描述>
     * @param clientInfo
     * @return [参数说明]
     * 
     * @return boolean [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @Transactional
    public boolean updateById(String id, ClientInfo clientInfo) {
        //验证参数是否合法，必填字段是否填写
        AssertUtils.notNull(clientInfo, "clientInfo is null.");
        AssertUtils.notEmpty(id, "id is empty.");
        
        //生成需要更新字段的hashMap
        Map<String, Object> updateRowMap = new HashMap<String, Object>();
        
        //需要更新的字段
        //updateRowMap.put("type", clientInfo.getType());
        //updateRowMap.put("status", clientInfo.getStatus());
        updateRowMap.put("code", clientInfo.getCode());
        updateRowMap.put("referralCode", clientInfo.getReferralCode());
        updateRowMap.put("name", clientInfo.getName());
        
        //updateRowMap.put("clientSource", clientInfo.getClientSource());
        //updateRowMap.put("promotionChannel", clientInfo.getPromotionChannel());
        updateRowMap.put("lastUpdateDate", new Date());
        
        boolean flag = this.clientInfoDao.update(id, updateRowMap);
        //如果需要大于1时，抛出异常并回滚，需要在这里修改
        return flag;
    }
    
    /**
     * 根据id更新ClientInfo实例<br/>
     * <功能详细描述>
     * @param clientInfo
     * @return [参数说明]
     * 
     * @return boolean [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @Transactional
    public boolean updateById(ClientInfo clientInfo) {
        //验证参数是否合法，必填字段是否填写
        AssertUtils.notNull(clientInfo, "clientInfo is null.");
        AssertUtils.notEmpty(clientInfo.getId(), "clientInfo.id is empty.");
        
        boolean flag = updateById(clientInfo.getId(), clientInfo);
        //如果需要大于1时，抛出异常并回滚，需要在这里修改
        return flag;
    }
    
    /**
     * 更新密码错误次数<br/>
     * <功能详细描述>
     * @param id
     * @param errorCount
     * @return [参数说明]
     * 
     * @return boolean [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @Transactional
    public boolean updatePwdErrorCountById(String id, int errorCount) {
        //验证参数是否合法，必填字段是否填写
        AssertUtils.notEmpty(id, "id is empty.");
        
        ClientInfo client = findById(id);
        if (client == null) {
            return false;
        }
        
        //生成需要更新字段的hashMap
        Map<String, Object> updateRowMap = new HashMap<String, Object>();
        updateRowMap.put("id", id);
        
        //需要更新的字段
        Date now = new Date();
        
        updateRowMap.put("pwdErrCount", errorCount);
        updateRowMap.put("lastUpdateDate", now);
        
        int updateRowCount = this.clientInfoDao.update(updateRowMap);
        
        //如果需要大于1时，抛出异常并回滚，需要在这里修改
        return updateRowCount >= 1;
    }
    
    /**
     * 更新密码<br/>
     * <功能详细描述>
     * @param id
     * @param newPassword
     * @return [参数说明]
     * 
     * @return boolean [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @Transactional
    public boolean updatePwdById(String id, String newPassword) {
        //验证参数是否合法，必填字段是否填写
        AssertUtils.notEmpty(id, "id is empty.");
        AssertUtils.notEmpty(newPassword, "newPassword is empty.");
        
        ClientInfo client = findById(id);
        if (client == null) {
            return false;
        }
        
        //生成需要更新字段的hashMap
        Map<String, Object> updateRowMap = new HashMap<String, Object>();
        updateRowMap.put("id", id);
        
        //需要更新的字段
        Date now = new Date();
        String rawPwd = this.passwordEncoder.encode(newPassword);
        
        updateRowMap.put("password", rawPwd);
        updateRowMap.put("historyPwd", client.getPassword());
        updateRowMap.put("pwdUpdateDate", now);
        updateRowMap.put("lastUpdateDate", now);
        
        int updateRowCount = this.clientInfoDao.update(updateRowMap);
        
        //记录业务日志
        ServiceLoggerUtils.log(ClientSecOperateLog.builder()
                .clientId(id)
                .message(MessageUtils.format("客户账户[{}]修改密码.", id))
                .build());
        
        //如果需要大于1时，抛出异常并回滚，需要在这里修改
        return updateRowCount >= 1;
    }
    
    /**
     * 根据id更新对象
     * <功能详细描述>
     * @param operator
     * @return [参数说明]
     * 
     * @return boolean [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @Transactional
    public boolean updatePwdById(String id, String hisPassword,
            String newPassword) {
        //验证参数是否合法，必填字段是否填写
        AssertUtils.notEmpty(id, "id is empty.");
        AssertUtils.notEmpty(hisPassword, "hisPassword is empty.");
        AssertUtils.notEmpty(newPassword, "newPassword is empty.");
        
        ClientInfo client = findById(id);
        if (client == null) {
            return false;
        }
        String rawHisPwd = this.passwordEncoder.encode(hisPassword);
        if (StringUtils.equalsIgnoreCase(rawHisPwd, client.getPassword())) {
            return false;
        }
        
        //生成需要更新字段的hashMap
        Map<String, Object> updateRowMap = new HashMap<String, Object>();
        updateRowMap.put("id", id);
        
        //需要更新的字段
        Date now = new Date();
        String rawPwd = this.passwordEncoder.encode(newPassword);
        updateRowMap.put("password", rawPwd);
        updateRowMap.put("pwdUpdateDate", now);
        updateRowMap.put("lastUpdateDate", now);
        
        int updateRowCount = this.clientInfoDao.update(updateRowMap);
        
        //如果需要大于1时，抛出异常并回滚，需要在这里修改
        return updateRowCount >= 1;
    }
    
    /**
     * 重置密码<br/>
     * <功能详细描述>
     * @param operatorId
     * @return [参数说明]
     * 
     * @return boolean [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @Transactional
    public boolean resetPwdById(String id) {
        AssertUtils.notEmpty(id, "id is empty.");
        
        ClientInfo client = findById(id);
        if (client == null) {
            return false;
        }
        
        Map<String, Object> updateRowMap = new HashMap<String, Object>();
        updateRowMap.put("id", id);
        
        Date now = new Date();
        updateRowMap.put("pwdErrCount", 0);
        //重置密码后，密码更新时间设置为null，首次登陆检查密码更新时间为空，则跳转到修改密码界面
        updateRowMap.put("pwdUpdateDate", null);
        updateRowMap.put("historyPwd", client.getPassword());
        
        String rawPwd = this.passwordEncoder.encode(this.defaultClientPassword);
        updateRowMap.put("password", rawPwd);
        updateRowMap.put("lastUpdateDate", now);
        
        @SuppressWarnings("unused")
        int updateRowCount = this.clientInfoDao.update(updateRowMap);
        
        //记录业务日志
        ServiceLoggerUtils.log(ClientSecOperateLog.builder()
                .clientId(id)
                .message(MessageUtils.format("客户账户[{}]重置密码.", id))
                .build());
        
        return true;
    }
    
    /**
     * 更新用户名<br/>
     * <功能详细描述>
     * @param id
     * @param username
     * @return [参数说明]
     * 
     * @return boolean [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @Transactional
    public boolean updateUsernameById(String id, String username) {
        //验证参数是否合法，必填字段是否填写
        AssertUtils.notEmpty(id, "id is empty.");
        AssertUtils.notEmpty(username, "username is empty.");
        
        ClientInfo client = findById(id);
        if (client == null) {
            return false;
        }
        
        //生成需要更新字段的hashMap
        Map<String, Object> updateRowMap = new HashMap<String, Object>();
        updateRowMap.put("id", id);
        
        //需要更新的字段
        Date now = new Date();
        updateRowMap.put("username", username);
        updateRowMap.put("usernameChangeCount",
                client.getUsernameChangeCount() + 1);
        updateRowMap.put("usernameChangeAble", false);
        updateRowMap.put("lastUpdateDate", now);
        
        int updateRowCount = this.clientInfoDao.update(updateRowMap);
        
        //记录业务日志
        ServiceLoggerUtils.log(ClientSecOperateLog.builder()
                .clientId(id)
                .message(MessageUtils.format("客户账户[{}]修改用户名.", id))
                .build());
        
        //如果需要大于1时，抛出异常并回滚，需要在这里修改
        return updateRowCount >= 1;
    }
    
    /**
     * 根据id禁用ClientInfo<br/>
     * <功能详细描述>
     * @param id
     * @return [参数说明]
     * 
     * @return boolean [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @Transactional
    public boolean disableById(String id) {
        AssertUtils.notEmpty(id, "id is empty.");
        
        //生成条件
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("id", id);
        params.put("valid", false);
        params.put("status", ClientStatusEnum.DISABLED);
        params.put("lastUpdateDate", new Date());
        
        boolean flag = this.clientInfoDao.update(params) > 0;
        
        return flag;
    }
    
    /**
     * 根据id启用ClientInfo<br/>
     * <功能详细描述>
     * @param id
     * @return [参数说明]
     * 
     * @return boolean [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @Transactional
    public boolean enableById(String id) {
        AssertUtils.notEmpty(id, "id is empty.");
        
        //生成查询条件
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("id", id);
        params.put("valid", true);
        params.put("status", ClientStatusEnum.ACTIVATED);
        params.put("lastUpdateDate", new Date());
        
        boolean flag = this.clientInfoDao.update(params) > 0;
        
        return flag;
    }
    
    /**
     * 锁定操作员<br/>
     * <功能详细描述>
     * @param id
     * @return [参数说明]
     * 
     * @return boolean [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @Transactional
    public boolean lockById(String id) {
        AssertUtils.notEmpty(id, "id is empty.");
        Map<String, Object> updateRowMap = new HashMap<String, Object>();
        updateRowMap.put("id", id);
        
        updateRowMap.put("locked", true);
        updateRowMap.put("lastUpdateDate", new Date());
        updateRowMap.put("pwdErrCount", 0);//锁定的时候，同事将密码错误次数重置为0
        
        boolean flag = this.clientInfoDao.update(updateRowMap) > 0;
        
        //记录业务日志
        ServiceLoggerUtils.log(ClientSecOperateLog.builder()
                .clientId(id)
                .message(MessageUtils.format("锁定客户账户[{}].", id))
                .build());
        
        return flag;
    }
    
    /**
     * 解锁操作员<br/>
     * <功能详细描述>
     * @param operatorId
     * @return [参数说明]
     * 
     * @return boolean [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @Transactional
    public boolean unlockById(String id) {
        AssertUtils.notEmpty(id, "id is empty.");
        
        Map<String, Object> updateRowMap = new HashMap<String, Object>();
        updateRowMap.put("id", id);
        
        updateRowMap.put("locked", false);
        updateRowMap.put("lastUpdateDate", new Date());
        updateRowMap.put("pwdErrCount", 0);//解锁的时候，同事将密码错误次数重置为0
        
        boolean flag = this.clientInfoDao.update(updateRowMap) > 0;
        
        //记录业务日志
        ServiceLoggerUtils.log(ClientSecOperateLog.builder()
                .clientId(id)
                .message(MessageUtils.format("解锁客户账户[{}].", id))
                .build());
        return flag;
    }
    
    /**
     * 绑定移动电话号码<br/>
     * <功能详细描述>
     * @param operatorId
     * @return [参数说明]
     * 
     * @return boolean [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @Transactional
    public boolean mobileBinding(String id, String mobileNumber) {
        AssertUtils.notEmpty(id, "id is empty.");
        AssertUtils.notEmpty(mobileNumber, "mobileNumber is empty.");
        
        Map<String, Object> updateRowMap = new HashMap<String, Object>();
        updateRowMap.put("id", id);
        
        updateRowMap.put("mobileNumber", mobileNumber);
        updateRowMap.put("mobileBinding", true);
        updateRowMap.put("lastUpdateDate", new Date());
        
        boolean flag = this.clientInfoDao.update(updateRowMap) > 0;
        
        //记录业务日志
        ServiceLoggerUtils.log(ClientSecOperateLog.builder()
                .clientId(id)
                .message(MessageUtils
                        .format("客户账户绑定电话号码[{}][{}].", id, mobileNumber))
                .build());
        return flag;
    }
    
    /**
     * 绑定电子邮件<br/>
     * <功能详细描述>
     * @param operatorId
     * @return [参数说明]
     * 
     * @return boolean [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @Transactional
    public boolean emailBinding(String id, String email) {
        AssertUtils.notEmpty(id, "id is empty.");
        AssertUtils.notEmpty(email, "email is empty.");
        
        Map<String, Object> updateRowMap = new HashMap<String, Object>();
        updateRowMap.put("id", id);
        
        updateRowMap.put("email", email);
        updateRowMap.put("emailBinding", true);
        updateRowMap.put("lastUpdateDate", new Date());
        
        boolean flag = this.clientInfoDao.update(updateRowMap) > 0;
        
        //记录业务日志
        ServiceLoggerUtils.log(ClientSecOperateLog.builder()
                .clientId(id)
                .message(MessageUtils.format("客户账户绑定电子邮件[{}][{}].", id, email))
                .build());
        return flag;
    }
    
    /**
     * 实名认证<br/>
     * <功能详细描述>
     * @param operatorId
     * @return [参数说明]
     * 
     * @return boolean [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @Transactional
    public boolean realNameAuthenticated(String id, IDCardTypeEnum idCardType,
            String idCardNumber, Date idCardExpiryDate) {
        AssertUtils.notEmpty(id, "id is empty.");
        AssertUtils.notNull(idCardType, "idCardType is null.");
        AssertUtils.notEmpty(idCardNumber, "idCardNumber is empty.");
        
        Map<String, Object> updateRowMap = new HashMap<String, Object>();
        updateRowMap.put("id", id);
        
        updateRowMap.put("realNameAuthenticated", true);
        updateRowMap.put("idCardType", idCardType);
        updateRowMap.put("idCardNumber", idCardNumber);
        updateRowMap.put("idCardExpiryDate", idCardExpiryDate);
        updateRowMap.put("lastUpdateDate", new Date());
        
        boolean flag = this.clientInfoDao.update(updateRowMap) > 0;
        
        //记录业务日志
        ServiceLoggerUtils.log(ClientSecOperateLog.builder()
                .clientId(id)
                .message(MessageUtils.format("客户账户实名认证[{}][{}][{}].",
                        new Object[] { id, idCardType, idCardNumber }))
                .build());
        return flag;
    }
    
    /**
     * 更新密码错误次数<br/>
     * <功能详细描述>
     * @param id
     * @param errorCount
     * @return [参数说明]
     * 
     * @return boolean [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @Transactional
    public boolean updateRealNameErrCountById(String id, int errorCount) {
        //验证参数是否合法，必填字段是否填写
        AssertUtils.notEmpty(id, "id is empty.");
        
        ClientInfo client = findById(id);
        if (client == null) {
            return false;
        }
        
        //生成需要更新字段的hashMap
        Map<String, Object> updateRowMap = new HashMap<String, Object>();
        updateRowMap.put("id", id);
        
        //需要更新的字段
        Date now = new Date();
        
        updateRowMap.put("realNameErrCount", errorCount);
        updateRowMap.put("realNameLastErrDate", now);
        updateRowMap.put("lastUpdateDate", now);
        
        int updateRowCount = this.clientInfoDao.update(updateRowMap);
        
        //如果需要大于1时，抛出异常并回滚，需要在这里修改
        return updateRowCount >= 1;
    }
    
    /**
     * 绑定信用信息<br/>
     * <功能详细描述>
     * @param operatorId
     * @return [参数说明]
     * 
     * @return boolean [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @Transactional
    public boolean creditInfoBinding(String id, String creditInfoId,
            IDCardTypeEnum idCardType, String idCardNumber) {
        AssertUtils.notEmpty(id, "id is empty.");
        AssertUtils.notEmpty(creditInfoId, "creditInfoId is empty.");
        
        Map<String, Object> updateRowMap = new HashMap<String, Object>();
        updateRowMap.put("id", id);
        
        updateRowMap.put("idCardType", idCardType);
        updateRowMap.put("idCardNumber", idCardNumber);
        updateRowMap.put("creditInfoId", creditInfoId);
        updateRowMap.put("creditInfoBinding", true);
        updateRowMap.put("lastUpdateDate", new Date());
        
        boolean flag = this.clientInfoDao.update(updateRowMap) > 0;
        
        //记录业务日志
        ServiceLoggerUtils.log(ClientSecOperateLog.builder()
                .clientId(id)
                .message(MessageUtils
                        .format("客户账户绑定信用信息[{}][{}].", id, creditInfoId))
                .build());
        return flag;
    }
    
}
