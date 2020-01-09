/*
 * 描          述:  <描述>
 * 修  改   人:  
 * 修改时间:  
 * <修改描述:>
 */
package com.tx.local.operator.service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.annotation.Resource;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.tx.component.configuration.util.ConfigContextUtils;
import com.tx.core.exceptions.util.AssertUtils;
import com.tx.core.paged.model.PagedList;
import com.tx.core.querier.model.Filter;
import com.tx.core.querier.model.Querier;
import com.tx.core.querier.model.QuerierBuilder;
import com.tx.local.WebdemoConstants;
import com.tx.local.operator.dao.OperatorDao;
import com.tx.local.operator.model.Operator;
import com.tx.local.organization.facade.OrganizationFacade;
import com.tx.local.organization.facade.PostFacade;
import com.tx.local.organization.model.Organization;
import com.tx.local.organization.model.Post;

/**
 * 操作人员的业务层[OperatorService]
 * <功能详细描述>
 * 
 * @author  
 * @version [版本号]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
@Component("operatorService")
public class OperatorService {
    
    @SuppressWarnings("unused")
    private Logger logger = LoggerFactory.getLogger(OperatorService.class);
    
    @Resource(name = "operatorPasswordEncoder")
    public PasswordEncoder operatorPasswordEncoder;
    
    @Resource(name = "operatorDao")
    private OperatorDao operatorDao;
    
    @Resource
    private OrganizationFacade organizationFacade;
    
    @Resource
    private PostFacade postFacade;
    
    /**
     * 新增操作人员实例<br/>
     * 将operator插入数据库中保存
     * 1、如果operator 为空时抛出参数为空异常
     * 2、如果operator 中部分必要参数为非法值时抛出参数不合法异常
     * 
     * @param operator [参数说明]
     * @return void [返回类型说明]
     * @exception throws
     * @see [类、类#方法、类#成员]
     */
    @Transactional
    public void insert(Operator operator) {
        //验证参数是否合法
        AssertUtils.notNull(operator, "operator is null.");
        AssertUtils.notEmpty(operator.getUsername(),
                "operator.username is empty.");
        AssertUtils.notEmpty(operator.getVcid(), "operator.vcid is empty.");
        AssertUtils.notEmpty(operator.getOrganizationId(),
                "operator.organizationId is empty.");
        
        Organization org = this.organizationFacade
                .findById(operator.getOrganizationId());
        AssertUtils.isTrue(operator.getVcid().equals(org.getVcid()),
                "vcid{} != org.vcid:{}.{}",
                operator.getVcid(),
                operator.getOrganizationId(),
                org.getVcid());
        
        //为添加的数据需要填入默认值的字段填入默认值
        operator.setValid(true);
        operator.setLocked(false);
        operator.setModifyAble(true);
        
        //写入默认时间
        Date now = new Date();
        operator.setCreateDate(now);
        operator.setLastUpdateDate(now);
        operator.setPwdUpdateDate(now);
        operator.setPwdErrCount(0);
        operator.setUsernameChangeCount(0);
        
        //密码加密
        //获取配置的默认密码并设值
        String rawPwd = "";
        if (StringUtils.isEmpty(operator.getPassword())) {
            String defaultPwd = ConfigContextUtils
                    .getValue(WebdemoConstants.CONFIG_DEFAULT_PASSWORD);
            rawPwd = this.operatorPasswordEncoder.encode(defaultPwd);
        } else {
            rawPwd = this.operatorPasswordEncoder
                    .encode(operator.getPassword());
        }
        operator.setPassword(rawPwd);
        
        //处理职位
        if (!StringUtils.isEmpty(operator.getMainPostId())) {
            String mainPostId = operator.getMainPostId();
            Post mp = this.postFacade.findById(mainPostId);
            AssertUtils.isTrue(operator.getVcid().equals(mp.getVcid()),
                    "operator.vcid:{} not eq post.vcid:{}",
                    operator.getVcid(),
                    mp.getVcid());
            if (mp != null) {
                operator.setOrganizationId(mp.getOrganizationId());
            }
        }
        
        //调用数据持久层对实例进行持久化操作
        this.operatorDao.insert(operator);
    }
    
    /**
     * 根据id删除操作人员实例
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
        
        Operator condition = new Operator();
        condition.setId(id);
        
        int resInt = this.operatorDao.delete(condition);
        boolean flag = resInt > 0;
        return flag;
    }
    
    /**
     * 根据id查询操作人员实例
     * 1、当id为empty时抛出异常
     *
     * @param id
     * @return Operator [返回类型说明]
     * @exception throws
     * @see [类、类#方法、类#成员]
     */
    public Operator findById(String id) {
        AssertUtils.notEmpty(id, "id is empty.");
        
        Operator condition = new Operator();
        condition.setId(id);
        
        Operator res = this.operatorDao.find(condition);
        return res;
    }
    
    /**
     * 根据LoginName查询Operator实体
     * 1、当id为empty时抛出异常
     * <功能详细描述>
     * @param id
     * @return [参数说明]
     * 
     * @return Operator [返回类型说明]
     * @exception throws 可能存在数据库访问异常DataAccessException
     * @see [类、类#方法、类#成员]
     */
    public Operator findByUsername(String username) {
        AssertUtils.notEmpty(username, "username is empty.");
        
        Operator condition = new Operator();
        condition.setUsername(username);
        
        Operator res = this.operatorDao.find(condition);
        return res;
    }
    
    /**
     * 查询操作人员实例列表
     * <功能详细描述>
     * @param valid
     * @param params      
     * @return [参数说明]
     * 
     * @return List<Operator> [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    public List<Operator> queryList(Boolean valid, Map<String, Object> params) {
        //生成查询条件
        params = params == null ? new HashMap<String, Object>() : params;
        params.put("valid", valid);
        
        //根据实际情况，填入排序字段等条件，根据是否需要排序，选择调用dao内方法
        List<Operator> resList = this.operatorDao.queryList(params);
        
        return resList;
    }
    
    /**
     * 查询操作人员实例列表
     * <功能详细描述>
     * @param valid
     * @param querier      
     * @return [参数说明]
     * 
     * @return List<Operator> [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    public List<Operator> queryList(Boolean valid, Querier querier) {
        //生成查询条件
        querier = querier == null ? QuerierBuilder.newInstance().querier()
                : querier;
        if (valid != null) {
            querier.getFilters().add(Filter.eq("valid", valid));
        }
        
        //根据实际情况，填入排序字段等条件，根据是否需要排序，选择调用dao内方法
        List<Operator> resList = this.operatorDao.queryList(querier);
        
        return resList;
    }
    
    /**
     * 分页查询操作人员实例列表
     * <功能详细描述>
     * @param valid
     * @param params    
     * @param pageIndex 当前页index从1开始计算
     * @param pageSize 每页显示行数
     * 
     * <功能详细描述>
     * @return [参数说明]
     * 
     * @return List<Operator> [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    public PagedList<Operator> queryPagedList(Boolean valid,
            Map<String, Object> params, int pageIndex, int pageSize) {
        //生成查询条件
        params = params == null ? new HashMap<String, Object>() : params;
        params.put("valid", valid);
        
        //根据实际情况，填入排序字段等条件，根据是否需要排序，选择调用dao内方法
        PagedList<Operator> resPagedList = this.operatorDao
                .queryPagedList(params, pageIndex, pageSize);
        
        return resPagedList;
    }
    
    /**
     * 分页查询操作人员实例列表
     * <功能详细描述>
     * @param valid
     * @param querier    
     * @param pageIndex 当前页index从1开始计算
     * @param pageSize 每页显示行数
     * 
     * <功能详细描述>
     * @return [参数说明]
     * 
     * @return List<Operator> [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    public PagedList<Operator> queryPagedList(Boolean valid, Querier querier,
            int pageIndex, int pageSize) {
        //T判断条件合法性
        
        //生成查询条件
        querier = querier == null ? QuerierBuilder.newInstance().querier()
                : querier;
        if (valid != null) {
            querier.getFilters().add(Filter.eq("valid", valid));
        }
        
        //根据实际情况，填入排序字段等条件，根据是否需要排序，选择调用dao内方法
        PagedList<Operator> resPagedList = this.operatorDao
                .queryPagedList(querier, pageIndex, pageSize);
        
        return resPagedList;
    }
    
    /**
     * 查询操作人员实例数量<br/>
     * <功能详细描述>
     * @param valid
     * @param params      
     * @return [参数说明]
     * 
     * @return List<Operator> [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    public int count(Boolean valid, Map<String, Object> params) {
        //判断条件合法性
        
        //生成查询条件
        params = params == null ? new HashMap<String, Object>() : params;
        params.put("valid", valid);
        
        //根据实际情况，填入排序字段等条件，根据是否需要排序，选择调用dao内方法
        int res = this.operatorDao.count(params);
        
        return res;
    }
    
    /**
     * 查询操作人员实例数量<br/>
     * <功能详细描述>
     * @param valid
     * @param querier      
     * @return [参数说明]
     * 
     * @return List<Operator> [返回类型说明]
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
        int res = this.operatorDao.count(querier);
        
        return res;
    }
    
    /**
     * 判断操作人员实例是否已经存在<br/>
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
        int res = this.operatorDao.count(params, excludeId);
        
        return res > 0;
    }
    
    /**
     * 判断操作人员实例是否已经存在<br/>
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
        int res = this.operatorDao.count(querier, excludeId);
        
        return res > 0;
    }
    
    /**
     * 根据id更新操作人员实例<br/>
     * <功能详细描述>
     * @param operator
     * @return [参数说明]
     * 
     * @return boolean [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @Transactional
    public boolean updateById(Operator operator) {
        //验证参数是否合法，必填字段是否填写
        AssertUtils.notNull(operator, "operator is null.");
        AssertUtils.notEmpty(operator.getId(), "operator.id is empty.");
        
        boolean flag = updateById(operator.getId(), operator);
        //如果需要大于1时，抛出异常并回滚，需要在这里修改
        return flag;
    }
    
    /**
     * 根据id更新操作人员实例<br/>
     * <功能详细描述>
     * @param operator
     * @return [参数说明]
     * 
     * @return boolean [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @Transactional
    public boolean updateById(String id, Operator operator) {
        //验证参数是否合法，必填字段是否填写
        AssertUtils.notNull(operator, "operator is null.");
        AssertUtils.notEmpty(id, "id is empty.");
        
        //生成需要更新字段的hashMap
        Map<String, Object> updateRowMap = new HashMap<String, Object>();
        //updateRowMap.put("vcid", operator.getVcid());
        //updateRowMap.put("username", operator.getUsername());
        //updateRowMap.put("examinePwd", operator.getExaminePwd());
        //处理职位
        if (!StringUtils.isEmpty(operator.getMainPostId())) {
            String mainPostId = operator.getMainPostId();
            Post mp = this.postFacade.findById(mainPostId);
            AssertUtils.isTrue(operator.getVcid().equals(mp.getVcid()),
                    "operator.vcid:{} not eq post.vcid:{}",
                    operator.getVcid(),
                    mp.getVcid());
            if (mp != null) {
                operator.setOrganizationId(mp.getOrganizationId());
            }
        }
        
        //更新客户信息
        //updateRowMap.put("username", operator.getUsername());
        updateRowMap.put("uernameChangeAble", operator.isUsernameChangeAble());
        //updateRowMap.put("usernameChangeCount", operator.getUsernameChangeCount());
        
        updateRowMap.put("name", operator.getName());
        updateRowMap.put("organizationId", operator.getOrganizationId());
        updateRowMap.put("mainPostId", operator.getMainPostId());
        
        //updateRowMap.put("modifyAble", operator.isModifyAble());
        
        updateRowMap.put("lastUpdateDate", new Date());
        
        boolean flag = this.operatorDao.update(id, updateRowMap);
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
        
        Operator oper = findById(id);
        if (oper == null) {
            return false;
        }
        
        //生成需要更新字段的hashMap
        Map<String, Object> updateRowMap = new HashMap<String, Object>();
        updateRowMap.put("id", id);
        
        //需要更新的字段
        Date now = new Date();
        
        updateRowMap.put("pwdErrCount", errorCount);
        updateRowMap.put("lastUpdateDate", now);
        
        int updateRowCount = this.operatorDao.update(updateRowMap);
        
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
        
        Operator oper = findById(id);
        if (oper == null) {
            return false;
        }
        
        //生成需要更新字段的hashMap
        Map<String, Object> updateRowMap = new HashMap<String, Object>();
        updateRowMap.put("id", id);
        
        //需要更新的字段
        Date now = new Date();
        String rawPwd = this.operatorPasswordEncoder.encode(newPassword);
        
        updateRowMap.put("password", rawPwd);
        updateRowMap.put("historyPwd", oper.getPassword());
        updateRowMap.put("pwdUpdateDate", now);
        updateRowMap.put("lastUpdateDate", now);
        
        int updateRowCount = this.operatorDao.update(updateRowMap);
        
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
        
        Operator oper = findById(id);
        if (oper == null) {
            return false;
        }
        String rawHisPwd = this.operatorPasswordEncoder.encode(newPassword);
        if (StringUtils.equalsIgnoreCase(rawHisPwd, oper.getPassword())) {
            
        }
        
        //生成需要更新字段的hashMap
        Map<String, Object> updateRowMap = new HashMap<String, Object>();
        updateRowMap.put("id", id);
        
        //需要更新的字段
        Date now = new Date();
        String rawPwd = this.operatorPasswordEncoder.encode(newPassword);
        updateRowMap.put("password", rawPwd);
        updateRowMap.put("pwdUpdateDate", now);
        updateRowMap.put("lastUpdateDate", now);
        
        int updateRowCount = this.operatorDao.update(updateRowMap);
        
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
        
        Operator oper = findById(id);
        if (oper == null) {
            return false;
        }
        
        Map<String, Object> updateRowMap = new HashMap<String, Object>();
        updateRowMap.put("id", id);
        
        Date now = new Date();
        updateRowMap.put("pwdErrCount", 0);
        //重置密码后，密码更新时间设置为null，首次登陆检查密码更新时间为空，则跳转到修改密码界面
        updateRowMap.put("pwdUpdateDate", null);
        updateRowMap.put("historyPwd", oper.getPassword());
        
        String defaultPwd = ConfigContextUtils
                .getValue(WebdemoConstants.CONFIG_DEFAULT_PASSWORD);
        String rawPwd = this.operatorPasswordEncoder.encode(defaultPwd);
        updateRowMap.put("password", rawPwd);
        updateRowMap.put("lastUpdateDate", now);
        
        @SuppressWarnings("unused")
        int updateRowCount = this.operatorDao.update(updateRowMap);
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
        
        Operator oper = findById(id);
        if (oper == null) {
            return false;
        }
        
        //生成需要更新字段的hashMap
        Map<String, Object> updateRowMap = new HashMap<String, Object>();
        updateRowMap.put("id", id);
        
        //需要更新的字段
        Date now = new Date();
        updateRowMap.put("username", username);
        updateRowMap.put("usernameChangeCount",
                oper.getUsernameChangeCount() + 1);
        updateRowMap.put("usernameChangeAble", false);
        updateRowMap.put("lastUpdateDate", now);
        
        int updateRowCount = this.operatorDao.update(updateRowMap);
        
        //如果需要大于1时，抛出异常并回滚，需要在这里修改
        return updateRowCount >= 1;
    }
    
    /**
     * 锁定操作员<br/>
     * <功能详细描述>
     * @param operatorId
     * @return [参数说明]
     * 
     * @return boolean [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @Transactional
    public boolean lockById(String operatorId) {
        AssertUtils.notEmpty(operatorId, "operatorId is empty.");
        Map<String, Object> updateRowMap = new HashMap<String, Object>();
        updateRowMap.put("id", operatorId);
        
        updateRowMap.put("locked", true);
        updateRowMap.put("lastUpdateDate", new Date());
        updateRowMap.put("pwdErrCount", 0);//锁定的时候，同事将密码错误次数重置为0
        
        boolean flag = this.operatorDao.update(updateRowMap) > 0;
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
        
        boolean flag = this.operatorDao.update(updateRowMap) > 0;
        return flag;
    }
    
    /**
     * 根据id禁用操作人员<br/>
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
        
        Map<String, Object> updateRowMap = new HashMap<String, Object>();
        updateRowMap.put("id", id);
        
        updateRowMap.put("valid", false);
        updateRowMap.put("invalidDate", new Date());
        updateRowMap.put("lastUpdateDate", new Date());
        
        boolean flag = this.operatorDao.update(updateRowMap) > 0;
        
        return flag;
    }
    
    /**
     * 根据id启用操作人员<br/>
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
        
        Map<String, Object> updateRowMap = new HashMap<String, Object>();
        updateRowMap.put("id", id);
        
        updateRowMap.put("valid", true);
        updateRowMap.put("invalidDate", null);
        updateRowMap.put("lastUpdateDate", new Date());
        
        boolean flag = this.operatorDao.update(updateRowMap) > 0;
        
        return flag;
    }
    
    /**
     * 更新职位<br/>
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
    public boolean updateMainPostById(String id, String postId) {
        //验证参数是否合法，必填字段是否填写
        AssertUtils.notEmpty(id, "id is empty.");
        AssertUtils.notEmpty(postId, "postId is empty.");
        
        Operator oper = findById(id);
        if (oper == null) {
            return false;
        }
        
        //生成需要更新字段的hashMap
        Map<String, Object> updateRowMap = new HashMap<String, Object>();
        updateRowMap.put("id", id);
        
        //需要更新的字段
        Date now = new Date();
        Post post = this.postFacade.findById(postId);
        AssertUtils.notNull(post, "post is null.postId:{}", postId);
        
        updateRowMap.put("mainPostId", post.getId());
        updateRowMap.put("organizationId", post.getOrganizationId());
        updateRowMap.put("lastUpdateDate", now);
        
        int updateRowCount = this.operatorDao.update(updateRowMap);
        
        //如果需要大于1时，抛出异常并回滚，需要在这里修改
        return updateRowCount >= 1;
    }
    
    /**
     * 保存职位和用户之间的关联关系<br/>
     * <功能详细描述>
     * @param postId
     * @param operatorIds
     * @param filterOperatorIds [参数说明]
     * 
     * @return void [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @Transactional
    public void savePost2Operators(String postId, List<String> operatorIds,
            List<String> filterOperatorIds) {
        AssertUtils.notEmpty(postId, "postId is empty.");
        
        //查询原关联权限
        Map<String, Object> params = new HashMap<>();
        params.put("mainPostId", postId);
        //原拥有该职位的人员
        List<String> sourceIdList = queryList(null, params).stream()
                .map(oper -> oper.getId())
                .collect(Collectors.toList());
        if (!CollectionUtils.isEmpty(filterOperatorIds)) {
            sourceIdList = sourceIdList.stream().filter(idTemp -> {
                return filterOperatorIds.contains(idTemp);
            }).collect(Collectors.toList());
        }
        final List<String> finalSourceIdList = sourceIdList;
        
        //识别需要删除的权限
        List<String> needDeleteOperIds = finalSourceIdList.stream()
                .filter(idTemp -> {
                    return !operatorIds.contains(idTemp);
                })
                .collect(Collectors.toList());
        for (String idTemp : needDeleteOperIds) {
            //生成需要更新字段的hashMap
            Map<String, Object> updateRowMap = new HashMap<String, Object>();
            updateRowMap.put("id", idTemp);
            //需要更新的字段
            Date now = new Date();
            updateRowMap.put("mainPostId", null);
            updateRowMap.put("lastUpdateDate", now);
            this.operatorDao.update(updateRowMap);
        }
        
        //识别需要添加的权限列表
        List<String> needAddOperIds = operatorIds.stream().filter(refIdTemp -> {
            return !finalSourceIdList.contains(refIdTemp);
        }).collect(Collectors.toList());
        for (String idTemp : needAddOperIds) {
            updateMainPostById(idTemp, postId);
        }
    }
}
