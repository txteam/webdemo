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

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.tx.local.clientinfo.dao.ClientSourceDao;
import com.tx.local.clientinfo.model.ClientSource;
import com.tx.core.exceptions.util.AssertUtils;
import com.tx.core.paged.model.PagedList;

/**
 * ClientSource的业务层
 * <功能详细描述>
 * 
 * @author  
 * @version  [版本号, ]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
@Component("clientSourceService")
public class ClientSourceService {
    
    @SuppressWarnings("unused")
    private Logger logger = LoggerFactory.getLogger(ClientSourceService.class);
    
    @Resource(name = "clientSourceDao")
    private ClientSourceDao clientSourceDao;
    
    /**
     * 将clientSource插入数据库中保存
     * 1、如果clientSource 为空时抛出参数为空异常
     * 2、如果clientSource 中部分必要参数为非法值时抛出参数不合法异常
     * 
     * @param clientSource [参数说明]
     * @return void [返回类型说明]
     * @exception throws
     * @see [类、类#方法、类#成员]
     */
    @Transactional
    public void insert(ClientSource clientSource) {
        //验证参数是否合法
        AssertUtils.notNull(clientSource, "clientSource is null.");
		AssertUtils.notEmpty(clientSource.getCode(), "clientSource.code is empty.");
		AssertUtils.notEmpty(clientSource.getName(), "clientSource.name is empty.");
		AssertUtils.notEmpty(clientSource.isModifyAble(), "clientSource.modifyAble is empty.");
           
        //FIXME:为添加的数据需要填入默认值的字段填入默认值
		clientSource.setLastUpdateDate(new Date());
		clientSource.setValid(true);
		clientSource.setCreateDate(new Date());
        
        //调用数据持久层对实体进行持久化操作
        this.clientSourceDao.insert(clientSource);
    }
    
    /**
     * 根据id删除ClientSource实例
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
        
        ClientSource condition = new ClientSource();
        condition.setId(id);
        
        int resInt = this.clientSourceDao.delete(condition);
        boolean flag = resInt > 0;
        return flag;
    }
    
    /**
     * 根据id查询ClientSource实体
     * 1、当id为empty时抛出异常
     *
     * @param id
     * @return ClientSource [返回类型说明]
     * @exception throws
     * @see [类、类#方法、类#成员]
     */
    public ClientSource findById(String id) {
        AssertUtils.notEmpty(id, "id is empty.");
        
        ClientSource condition = new ClientSource();
        condition.setId(id);
        
        ClientSource res = this.clientSourceDao.find(condition);
        return res;
    }
    
    /**
     * 根据code查询ClientSource实体
     * 1、当code为empty时抛出异常
     *
     * @param code
     * @return ClientSource [返回类型说明]
     * @exception throws
     * @see [类、类#方法、类#成员]
     */
    public ClientSource findByCode(String code) {
        AssertUtils.notEmpty(code, "code is empty.");
        
        ClientSource condition = new ClientSource();
        condition.setCode(code);
        
        ClientSource res = this.clientSourceDao.find(condition);
        return res;
    }
    
    /**
     * 查询ClientSource实体列表
     * <功能详细描述>
     * @param valid
     * @param params      
     * @return [参数说明]
     * 
     * @return List<ClientSource> [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    public List<ClientSource> queryList(
		Boolean valid,
		Map<String,Object> params   
    	) {
        //判断条件合法性
        
        //生成查询条件
        params = params == null ? new HashMap<String, Object>() : params;
		params.put("valid",valid);

        //根据实际情况，填入排序字段等条件，根据是否需要排序，选择调用dao内方法
        List<ClientSource> resList = this.clientSourceDao.queryList(params);
        
        return resList;
    }
    
	/**
     * 分页查询ClientSource实体列表
     * <功能详细描述>
     * @param valid
     * @param params    
     * @param pageIndex 当前页index从1开始计算
     * @param pageSize 每页显示行数
     * 
     * <功能详细描述>
     * @return [参数说明]
     * 
     * @return List<ClientSource> [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    public PagedList<ClientSource> queryPagedList(
		Boolean valid,
		Map<String,Object> params,
    	int pageIndex,
        int pageSize) {
        //T判断条件合法性
        
        //生成查询条件
        params = params == null ? new HashMap<String, Object>() : params;
		params.put("valid",valid);
 
        //根据实际情况，填入排序字段等条件，根据是否需要排序，选择调用dao内方法
        PagedList<ClientSource> resPagedList = this.clientSourceDao.queryPagedList(params, pageIndex, pageSize);
        
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
    public boolean exists(Map<String,String> key2valueMap, String excludeId) {
        AssertUtils.notEmpty(key2valueMap, "key2valueMap is empty");
        
        //生成查询条件
        Map<String, Object> params = new HashMap<String, Object>();
        params.putAll(key2valueMap);
        params.put("excludeId", excludeId);
        
        //根据实际情况，填入排序字段等条件，根据是否需要排序，选择调用dao内方法
        int res = this.clientSourceDao.count(params);
        
        return res > 0;
    }
    
    /**
     * 根据id更新对象
     * <功能详细描述>
     * @param clientSource
     * @return [参数说明]
     * 
     * @return boolean [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @Transactional
    public boolean updateById(ClientSource clientSource) {
        //验证参数是否合法，必填字段是否填写
        AssertUtils.notNull(clientSource, "clientSource is null.");
        AssertUtils.notEmpty(clientSource.getId(), "clientSource.id is empty.");
		AssertUtils.notEmpty(clientSource.getName(), "clientSource.name is empty.");
		AssertUtils.notEmpty(clientSource.isModifyAble(), "clientSource.modifyAble is empty.");

        //生成需要更新字段的hashMap
        Map<String, Object> updateRowMap = new HashMap<String, Object>();
        updateRowMap.put("id", clientSource.getId());
        
        //FIXME:需要更新的字段
		updateRowMap.put("name", clientSource.getName());
		updateRowMap.put("valid", clientSource.isValid());
		updateRowMap.put("modifyAble", clientSource.isModifyAble());
		updateRowMap.put("remark", clientSource.getRemark());
		updateRowMap.put("lastUpdateDate", new Date());

        int updateRowCount = this.clientSourceDao.update(updateRowMap); 
        //如果需要大于1时，抛出异常并回滚，需要在这里修改
        return updateRowCount >= 1;
    }
     /**
      * 根据id禁用ClientSource<br/>
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
        
        this.clientSourceDao.update(params);
        
        return true;
    }
    
    /**
      * 根据id启用ClientSource<br/>
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
        
        this.clientSourceDao.update(params);
        
        return true;
    }
}
