/*
 * 描          述:  <描述>
 * 修  改   人:  
 * 修改时间:  
 * <修改描述:>
 */
package com.tx.local.basicdata.service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.tx.component.basicdata.service.AbstractBasicDataService;
import com.tx.core.exceptions.util.AssertUtils;
import com.tx.core.paged.model.PagedList;
import com.tx.local.basicdata.dao.BankInfoDao;
import com.tx.local.basicdata.model.BankInfo;

/**
 * BankInfo的业务层
 * <功能详细描述>
 *
 * @author
 * @version [版本号, ]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
@Component("bankInfoService")
public class BankInfoService extends AbstractBasicDataService<BankInfo> {
    
    @SuppressWarnings("unused")
    private Logger logger = LoggerFactory.getLogger(BankInfoService.class);
    
    @Resource(name = "bankInfoDao")
    private BankInfoDao bankInfoDao;
    
    //    /**
    //     * @throws Exception
    //     */
    //    @Override
    //    protected void init() throws Exception {
    //        this.configInitAbleHelper.doInit();
    //    }
    //    
    //    /**
    //     * @return
    //     */
    //    @Override
    //    protected List<BankInfo> loadDataFromConfig() {
    //        List<BankInfo> banks = new ArrayList<>();
    //        for (BankEnum bank : BankEnum.values()) {
    //            BankInfo bi = new BankInfo();
    //            bi.setCode(bank.getKey());
    //            bi.setName(bank.getName());
    //            bi.setPersonalLoginUrl(bank.getPersonalLoginUrl());
    //            bi.setInstitutionLoginUrl(bank.getInstitutionLoginUrl());
    //            bi.setValid(true);
    //            bi.setModifyAble(false);
    //            
    //            StringBuilder sb = new StringBuilder();
    //            for (String nameTemp : bank.getAliases()) {
    //                sb.append(nameTemp).append(",");
    //            }
    //            bi.setAliases(sb.toString());
    //            
    //            banks.add(bi);
    //        }
    //        return banks;
    //    }
    
    /**
      * 预处理别名<br/>
      * <功能详细描述>
      * @param aliases
      * @param name
      * @return [参数说明]
      * 
      * @return String [返回类型说明]
      * @exception throws [异常类型] [异常说明]
      * @see [类、类#方法、类#成员]
     */
    private String handleAliases(String aliases, String name) {
        if (aliases.indexOf(name) >= 0) {
            return aliases;
        }
        if (!StringUtils.isEmpty(aliases)) {
            if (!aliases.endsWith(",")) {
                aliases = aliases + ",";
            }
        }
        aliases = aliases + name + ",";
        return aliases;
    }
    
    /**
     * @param data
     */
    @Override
    @Transactional
    public void insert(BankInfo bankInfo) {
        //验证参数是否合法
        AssertUtils.notNull(bankInfo, "bankInfo is null.");
        AssertUtils.notEmpty(bankInfo.getCode(), "bankInfo.code is empty.");
        AssertUtils.notEmpty(bankInfo.getName(), "bankInfo.name is empty.");
        
        //为添加的数据需要填入默认值的字段填入默认值
        bankInfo.setValid(true);
        Date now = new Date();
        bankInfo.setCreateDate(now);
        bankInfo.setLastUpdateDate(now);
        
        bankInfo.setAliases(
                handleAliases(bankInfo.getAliases(), bankInfo.getName()));
        
        //调用数据持久层对实体进行持久化操作
        this.bankInfoDao.insert(bankInfo);
    }
    
    /**
     * 根据id删除bankInfo实例
     * 1、如果入参数为空，则抛出异常
     * 2、执行删除后，将返回数据库中被影响的条数
     *
     * @param id
     * @return int [返回类型说明]
     * @throws throws
     * @see [类、类#方法、类#成员]
     */
    @Override
    @Transactional
    public boolean deleteById(String id) {
        AssertUtils.notEmpty(id, "id is empty.");
        
        BankInfo condition = new BankInfo();
        condition.setId(id);
        int resInt = this.bankInfoDao.delete(condition);
        return resInt > 0;
    }
    
    /**
     * @param code
     * @return
     */
    @Override
    @Transactional
    public boolean deleteByCode(String code) {
        AssertUtils.notEmpty(code, "code is empty.");
        
        BankInfo condition = new BankInfo();
        condition.setCode(code);
        int resInt = this.bankInfoDao.delete(condition);
        return resInt > 0;
    }
    
    /**
     * @param id
     * @return
     */
    @Override
    public BankInfo findById(String id) {
        AssertUtils.notEmpty(id, "id is empty.");
        
        BankInfo condition = new BankInfo();
        condition.setId(id);
        
        BankInfo res = this.bankInfoDao.find(condition);
        return res;
    }
    
    /**
     * @param code
     * @return
     */
    @Override
    public BankInfo findByCode(String code) {
        AssertUtils.notEmpty(code, "code is empty.");
        
        BankInfo condition = new BankInfo();
        condition.setCode(code);
        
        BankInfo res = this.bankInfoDao.find(condition);
        return res;
    }
    
    /**
     * 判断是否已经存在<br/>
     * <功能详细描述>
     *
     * @return int [返回类型说明]
     * @throws throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    public boolean exist(Map<String, String> key2valueMap, String excludeId) {
        AssertUtils.notEmpty(key2valueMap, "key2valueMap is empty");
        
        //生成查询条件
        Map<String, Object> params = new HashMap<String, Object>();
        params.putAll(key2valueMap);
        params.put("excludeId", excludeId);
        
        //根据实际情况，填入排序字段等条件，根据是否需要排序，选择调用dao内方法
        int res = this.bankInfoDao.count(params);
        
        return res > 0;
    }
    
    /**
      * 删除银行logo文件<br/>
      * <功能详细描述>
      * @param bankInfoId
      * @return [参数说明]
      * 
      * @return boolean [返回类型说明]
      * @exception throws [异常类型] [异常说明]
      * @see [类、类#方法、类#成员]
     */
    @Transactional
    public boolean deleteLogoFile(String bankInfoId) {
        AssertUtils.notEmpty(bankInfoId, "bankInfoId is empty.");
        
        BankInfo bi = findById(bankInfoId);
        if (bi == null) {
            return false;
        }
        
        Map<String, Object> updateRowMap = new HashMap<String, Object>();
        updateRowMap.put("id", bi.getId());
        
        updateRowMap.put("logoFileId", null);
        updateRowMap.put("logoUrl", null);
        updateRowMap.put("lastUpdateDate", new Date());
        
        int updateRowCount = this.bankInfoDao.update(updateRowMap);
        
        //删除实际的文件
        //        FileContext.getContext().deleteById(bi.getLogoFileId());
        
        //如果需要大于1时，抛出异常并回滚，需要在这里修改
        return updateRowCount >= 1;
    }
    
    /**
     * 根据id更新对象
     * <功能详细描述>
     *
     * @param bankInfo
     * @return boolean [返回类型说明]
     * @throws throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @Transactional
    public boolean updateById(BankInfo bankInfo) {
        //验证参数是否合法，必填字段是否填写，
        AssertUtils.notNull(bankInfo, "bankInfo is null.");
        AssertUtils.notEmpty(bankInfo.getId(), "bankInfo.id is empty.");
        
        //生成需要更新字段的hashMap
        Map<String, Object> updateRowMap = new HashMap<String, Object>();
        updateRowMap.put("id", bankInfo.getId());
        
        //需要更新的字段
        //updateRowMap.put("code", bankInfo.getCode());
        if (bankInfo.isModifyAble()) {
            updateRowMap.put("name", bankInfo.getName());
            updateRowMap.put("aliases",
                    handleAliases(bankInfo.getAliases(), bankInfo.getName()));
            //updateRowMap.put("valid", bankInfo.isValid());
        }
        updateRowMap.put("logoFileId", bankInfo.getLogoFileId());
        updateRowMap.put("logoUrl", bankInfo.getLogoUrl());
        updateRowMap.put("personalLoginUrl", bankInfo.getPersonalLoginUrl());
        updateRowMap.put("institutionLoginUrl",
                bankInfo.getInstitutionLoginUrl());
        updateRowMap.put("remark", bankInfo.getRemark());
        
        updateRowMap.put("lastUpdateDate", new Date());
        
        int updateRowCount = this.bankInfoDao.update(updateRowMap);
        
        //如果需要大于1时，抛出异常并回滚，需要在这里修改
        return updateRowCount >= 1;
    }
    
    /**
     * 根据编码更新银行信息<br/>
     * @param bankInfo
     * @return
     */
    @Override
    @Transactional
    public boolean updateByCode(BankInfo bankInfo) {
        //验证参数是否合法，必填字段是否填写，
        AssertUtils.notNull(bankInfo, "bankInfo is null.");
        AssertUtils.notEmpty(bankInfo.getId(), "bankInfo.id is empty.");
        
        //生成需要更新字段的hashMap
        Map<String, Object> updateRowMap = new HashMap<String, Object>();
        updateRowMap.put("code", bankInfo.getId());
        
        //需要更新的字段
        //updateRowMap.put("code", bankInfo.getCode());
        if (bankInfo.isModifyAble()) {
            updateRowMap.put("name", bankInfo.getName());
            updateRowMap.put("aliases",
                    handleAliases(bankInfo.getAliases(), bankInfo.getName()));
            //updateRowMap.put("valid", bankInfo.isValid());
        }
        updateRowMap.put("logoFileId", bankInfo.getLogoFileId());
        updateRowMap.put("logoUrl", bankInfo.getLogoUrl());
        updateRowMap.put("personalLoginUrl", bankInfo.getPersonalLoginUrl());
        updateRowMap.put("institutionLoginUrl",
                bankInfo.getInstitutionLoginUrl());
        updateRowMap.put("remark", bankInfo.getRemark());
        
        updateRowMap.put("lastUpdateDate", new Date());
        
        int updateRowCount = this.bankInfoDao.update(updateRowMap);
        
        //如果需要大于1时，抛出异常并回滚，需要在这里修改
        return updateRowCount >= 1;
    }
    
    /**
     * 根据id禁用BankInfo<br/>
     * <功能详细描述>
     *
     * @param id
     * @return boolean [返回类型说明]
     * @throws throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @Transactional
    public boolean disableById(String id) {
        AssertUtils.notEmpty(id, "id is empty.");
        
        //生成查询条件
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("id", id);
        params.put("valid", false);
        
        this.bankInfoDao.update(params);
        
        return true;
    }
    
    /**
     * 根据id启用BankInfo<br/>
     * <功能详细描述>
     *
     * @param postId
     * @return boolean [返回类型说明]
     * @throws throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @Transactional
    public boolean enableById(String id) {
        AssertUtils.notEmpty(id, "id is empty.");
        
        //生成查询条件
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("id", id);
        params.put("valid", true);
        
        this.bankInfoDao.update(params);
        
        return true;
    }
    
    /**
     * @param valid
     * @param params
     * @return
     */
    @Override
    public List<BankInfo> queryList(Boolean valid, Map<String, Object> params) {
        params = params == null ? new HashMap<String, Object>() : params;
        params.put("valid", valid);
        
        List<BankInfo> resList = bankInfoDao.queryList(params);
        return resList;
    }
    
    /**
     * @param valid
     * @param params
     * @param pageIndex
     * @param pageSize
     * @return
     */
    @Override
    public PagedList<BankInfo> queryPagedList(Boolean valid,
            Map<String, Object> params, int pageIndex, int pageSize) {
        params = params == null ? new HashMap<String, Object>() : params;
        params.put("valid", valid);
        
        PagedList<BankInfo> resPagedList = bankInfoDao.queryPagedList(params,
                pageIndex,
                pageSize);
        return resPagedList;
    }
}
