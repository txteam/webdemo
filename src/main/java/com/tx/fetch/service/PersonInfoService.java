/*
 * 描          述:  <描述>
 * 修  改   人:  
 * 修改时间:  
 * <修改描述:>
 */
package com.tx.fetch.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.tx.core.exceptions.util.AssertUtils;
import com.tx.core.paged.model.PagedList;
import com.tx.core.support.poi.excel.model.ExportExcelModel;
import com.tx.fetch.dao.PersonInfoDao;
import com.tx.fetch.excelmodel.PersonInfoExportExcelModel;
import com.tx.fetch.model.PersonInfo;

/**
 * PersonInfo的业务层
 * <功能详细描述>
 * 
 * @author  
 * @version  [版本号, ]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
@Component("personInfoService")
public class PersonInfoService {
    
    @SuppressWarnings("unused")
    private Logger logger = LoggerFactory.getLogger(PersonInfoService.class);
    
    @Resource(name = "personInfoDao")
    private PersonInfoDao personInfoDao;
    
    /** <默认构造函数> */
    public PersonInfoService() {
        super();
    }
    
    /** <默认构造函数> */
    public PersonInfoService(PersonInfoDao personInfoDao) {
        super();
        this.personInfoDao = personInfoDao;
    }
    
    /**
      * 将personInfo实例插入数据库中保存
      * 1、如果personInfo为空时抛出参数为空异常
      * 2、如果personInfo中部分必要参数为非法值时抛出参数不合法异常
     * <功能详细描述>
     * @param district [参数说明]
     * 
     * @return void [返回类型说明]
     * @exception throws
     * @see [类、类#方法、类#成员]
    */
    @Transactional
    public void insertPersonInfo(PersonInfo personInfo) {
        //TODO:验证参数是否合法
        AssertUtils.notNull(personInfo, "personInfo is null.");
        AssertUtils.notEmpty(personInfo.getId(), "personInfo.id is empty.");
        
        //TODO: 设置默认数据
        
        this.personInfoDao.insertPersonInfo(personInfo);
    }
    
    /**
     * 根据id删除personInfo实例
     * 1、如果入参数为空，则抛出异常
     * 2、执行删除后，将返回数据库中被影响的条数
     * @param id
     * @return 返回删除的数据条数，<br/>
     * 有些业务场景，如果已经被别人删除同样也可以认为是成功的
     * 这里讲通用生成的业务层代码定义为返回影响的条数
     * @return int [返回类型说明]
     * @exception throws 
     * @see [类、类#方法、类#成员]
    */
    @Transactional
    public int deleteById(String id) {
        AssertUtils.notEmpty(id, "id is empty.");
        
        PersonInfo condition = new PersonInfo();
        condition.setId(id);
        return this.personInfoDao.deletePersonInfo(condition);
    }
    
    /**
      * 根据Id查询PersonInfo实体
      * 1、当id为empty时抛出异常
      * <功能详细描述>
      * @param id
      * @return [参数说明]
      * 
      * @return PersonInfo [返回类型说明]
      * @exception throws 可能存在数据库访问异常DataAccessException
      * @see [类、类#方法、类#成员]
     */
    public PersonInfo findPersonInfoById(String id) {
        AssertUtils.notEmpty(id, "id is empty.");
        
        PersonInfo condition = new PersonInfo();
        condition.setId(id);
        
        PersonInfo res = this.personInfoDao.findPersonInfo(condition);
        return res;
    }
    
    public PersonInfo findPersonInfoByCardNumAndIname(String cardNum,
            String iname) {
        AssertUtils.notEmpty(iname, "iname is empty.");
        AssertUtils.notEmpty(cardNum, "cardNum is empty.");
        
        PersonInfo condition = new PersonInfo();
        condition.setCardNum(cardNum);
        condition.setIname(iname);
        
        PersonInfo res = this.personInfoDao.findPersonInfo(condition);
        return res;
    }
    
    /**
     * 根据Id查询PersonInfo实体
     * 1、当id为empty时抛出异常
     * <功能详细描述>
     * @param id
     * @return [参数说明]
     * 
     * @return PersonInfo [返回类型说明]
     * @exception throws 可能存在数据库访问异常DataAccessException
     * @see [类、类#方法、类#成员]
    */
    public PersonInfo findPersonInfoByIdCardNumber(String idCardNumber) {
        AssertUtils.notEmpty(idCardNumber, "idCardNumber is empty.");
        
        PersonInfo condition = new PersonInfo();
        condition.setIdCardNumber(idCardNumber);
        
        PersonInfo res = this.personInfoDao.findPersonInfo(condition);
        return res;
    }
    
    /**
      * 根据PersonInfo实体列表
      * TODO:补充说明
      * 
      * <功能详细描述>
      * @return [参数说明]
      * 
      * @return List<PersonInfo> [返回类型说明]
      * @exception throws [异常类型] [异常说明]
      * @see [类、类#方法、类#成员]
     */
    public List<PersonInfo> queryPersonInfoList(/*TODO:自己定义条件*/) {
        //TODO:判断条件合法性
        
        //TODO:生成查询条件
        Map<String, Object> params = new HashMap<String, Object>();
        
        //TODO:根据实际情况，填入排序字段等条件，根据是否需要排序，选择调用dao内方法
        List<PersonInfo> resList = this.personInfoDao.queryPersonInfoList(params);
        
        return resList;
    }
    
    /**
     * 分页查询PersonInfo实体列表
     * TODO:补充说明
     * 
     * <功能详细描述>
     * @return [参数说明]
     * 
     * @return List<PersonInfo> [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
    */
    public PagedList<PersonInfo> queryPersonInfoPagedList(
            Map<String, Object> params, int pageIndex, int pageSize) {
        
        PagedList<PersonInfo> resPagedList = this.personInfoDao.queryDistinctPersonInfoPagedList(params,
                pageIndex,
                pageSize);
        
        return resPagedList;
    }
    
    /**
      * 查询personInfo列表总条数
      * TODO:补充说明
      * <功能详细描述>
      * @return [参数说明]
      * 
      * @return int [返回类型说明]
      * @exception throws [异常类型] [异常说明]
      * @see [类、类#方法、类#成员]
     */
    public int countPersonInfo(/*TODO:自己定义条件*/) {
        //TODO:判断条件合法性
        
        //TODO:生成查询条件
        Map<String, Object> params = new HashMap<String, Object>();
        
        //TODO:根据实际情况，填入排序字段等条件，根据是否需要排序，选择调用dao内方法
        int res = this.personInfoDao.countPersonInfo(params);
        
        return res;
    }
    
    @Transactional
    public ExportExcelModel<PersonInfo> exporteExcel(List<PersonInfo> personInfoList){
//        List<Map<String, Object>> updateRowMapList = new ArrayList<>();
//        Date now = new Date();
//        for(PersonInfo personTemp : personInfoList){
//            Map<String, Object> updateRowMap = new HashMap<String, Object>();
//            updateRowMap.put("id", personTemp.getId());
//            
//            //TODO:需要更新的字段
//            updateRowMap.put("exported", personTemp.isExported());
//            updateRowMap.put("exportedDate", now);
//            updateRowMapList.add(updateRowMap);
//        }
//        this.personInfoDao.batchUpdatePersonInfo(updateRowMapList);
        
        
        PersonInfoExportExcelModel excel = new PersonInfoExportExcelModel(personInfoList);
        excel.buildExcel();
        return excel;
    }
    
    /**
      * 根据id更新对象
      * <功能详细描述>
      * @param personInfo
      * @return [参数说明]
      * 
      * @return boolean [返回类型说明]
      * @exception throws [异常类型] [异常说明]
      * @see [类、类#方法、类#成员]
     */
    @Transactional
    public boolean updateById(PersonInfo personInfo) {
        //TODO:验证参数是否合法，必填字段是否填写，
        AssertUtils.notNull(personInfo, "personInfo is null.");
        AssertUtils.notEmpty(personInfo.getId(), "personInfo.id is empty.");
        
        //TODO:生成需要更新字段的hashMap
        Map<String, Object> updateRowMap = new HashMap<String, Object>();
        updateRowMap.put("id", personInfo.getId());
        
        //TODO:需要更新的字段
        updateRowMap.put("idCardNumber", personInfo.getIdCardNumber());
        updateRowMap.put("iname", personInfo.getIname());
        updateRowMap.put("regDate", personInfo.getRegDate());
        updateRowMap.put("disruptTypeName", personInfo.getDisruptTypeName());
        updateRowMap.put("sexy", personInfo.getSexy());
        updateRowMap.put("caseCode", personInfo.getCaseCode());
        updateRowMap.put("areaName", personInfo.getAreaName());
        updateRowMap.put("duty", personInfo.getDuty());
        updateRowMap.put("partyTypeName", personInfo.getPartyTypeName());
        updateRowMap.put("courtName", personInfo.getCourtName());
        updateRowMap.put("performance", personInfo.getPerformance());
        updateRowMap.put("gistUnit", personInfo.getGistUnit());
        updateRowMap.put("age", personInfo.getAge());
        updateRowMap.put("gistId", personInfo.getGistId());
        updateRowMap.put("publishDate", personInfo.getPublishDate());
        updateRowMap.put("cardNum", personInfo.getCardNum());
        updateRowMap.put("focusNumber", personInfo.getFocusNumber());
        
        int updateRowCount = this.personInfoDao.updatePersonInfo(updateRowMap);
        
        //TODO:如果需要大于1时，抛出异常并回滚，需要在这里修改
        return updateRowCount >= 1;
    }
}
