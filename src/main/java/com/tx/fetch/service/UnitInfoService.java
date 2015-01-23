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
import com.tx.fetch.dao.UnitInfoDao;
import com.tx.fetch.excelmodel.UnitInfoExportExcelModel;
import com.tx.fetch.model.UnitInfo;

/**
 * UnitInfo的业务层
 * <功能详细描述>
 * 
 * @author  
 * @version  [版本号, ]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
@Component("unitInfoService")
public class UnitInfoService {
    
    @SuppressWarnings("unused")
    private Logger logger = LoggerFactory.getLogger(UnitInfoService.class);
    
    @Resource(name = "unitInfoDao")
    private UnitInfoDao unitInfoDao;
    
    /** <默认构造函数> */
    public UnitInfoService(UnitInfoDao unitInfoDao) {
        super();
        this.unitInfoDao = unitInfoDao;
    }

    /** <默认构造函数> */
    public UnitInfoService() {
        super();
    }
    
    @Transactional
    public ExportExcelModel<UnitInfo> exporteExcel(List<UnitInfo> unitInfoList){
//        List<Map<String, Object>> updateRowMapList = new ArrayList<>();
//        Date now = new Date();
//        for(UnitInfo unitInfoTemp : unitInfoList){
//            Map<String, Object> updateRowMap = new HashMap<String, Object>();
//            updateRowMap.put("id", unitInfoTemp.getId());
//            
//            //TODO:需要更新的字段
//            updateRowMap.put("exported", unitInfoTemp.isExported());
//            updateRowMap.put("exportedDate", now);
//            updateRowMapList.add(updateRowMap);
//        }
//        this.unitInfoDao.batchUpdateUnitInfo(updateRowMapList);
        
        
        UnitInfoExportExcelModel excel = new UnitInfoExportExcelModel(unitInfoList);
        excel.buildExcel();
        return excel;
    }

    /**
      * 将unitInfo实例插入数据库中保存
      * 1、如果unitInfo为空时抛出参数为空异常
      * 2、如果unitInfo中部分必要参数为非法值时抛出参数不合法异常
     * <功能详细描述>
     * @param district [参数说明]
     * 
     * @return void [返回类型说明]
     * @exception throws
     * @see [类、类#方法、类#成员]
    */
    @Transactional
    public void insertUnitInfo(UnitInfo unitInfo) {
        //TODO:验证参数是否合法
        AssertUtils.notNull(unitInfo, "unitInfo is null.");
        AssertUtils.notEmpty(unitInfo.getId(), "unitInfo.id is empty.");
        
        //TODO: 设置默认数据
        
        this.unitInfoDao.insertUnitInfo(unitInfo);
    }
      
     /**
      * 根据id删除unitInfo实例
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
        
        UnitInfo condition = new UnitInfo();
        condition.setId(id);
        return this.unitInfoDao.deleteUnitInfo(condition);
    }
    
    /**
      * 根据Id查询UnitInfo实体
      * 1、当id为empty时抛出异常
      * <功能详细描述>
      * @param id
      * @return [参数说明]
      * 
      * @return UnitInfo [返回类型说明]
      * @exception throws 可能存在数据库访问异常DataAccessException
      * @see [类、类#方法、类#成员]
     */
    public UnitInfo findUnitInfoById(String id) {
        AssertUtils.notEmpty(id, "id is empty.");
        
        UnitInfo condition = new UnitInfo();
        condition.setId(id);
        
        UnitInfo res = this.unitInfoDao.findUnitInfo(condition);
        return res;
    }
    
    /**
      * 根据UnitInfo实体列表
      * TODO:补充说明
      * 
      * <功能详细描述>
      * @return [参数说明]
      * 
      * @return List<UnitInfo> [返回类型说明]
      * @exception throws [异常类型] [异常说明]
      * @see [类、类#方法、类#成员]
     */
    public List<UnitInfo> queryUnitInfoList(/*TODO:自己定义条件*/) {
        //TODO:判断条件合法性
        
        //TODO:生成查询条件
        Map<String, Object> params = new HashMap<String, Object>();
        
        //TODO:根据实际情况，填入排序字段等条件，根据是否需要排序，选择调用dao内方法
        List<UnitInfo> resList = this.unitInfoDao.queryUnitInfoList(params);
        
        return resList;
    }
    
    /**
     * 分页查询UnitInfo实体列表
     * TODO:补充说明
     * 
     * <功能详细描述>
     * @return [参数说明]
     * 
     * @return List<UnitInfo> [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
    */
    public PagedList<UnitInfo> queryUnitInfoPagedList(Map<String, Object> params,int pageIndex,
            int pageSize) {
        
        //TODO:根据实际情况，填入排序字段等条件，根据是否需要排序，选择调用dao内方法
        PagedList<UnitInfo> resPagedList = this.unitInfoDao.queryDistinctUnitInfoPagedList(params, pageIndex, pageSize);
        
        return resPagedList;
    }
    
    /**
      * 查询unitInfo列表总条数
      * TODO:补充说明
      * <功能详细描述>
      * @return [参数说明]
      * 
      * @return int [返回类型说明]
      * @exception throws [异常类型] [异常说明]
      * @see [类、类#方法、类#成员]
     */
    public int countUnitInfo(/*TODO:自己定义条件*/){
        //TODO:判断条件合法性
        
        //TODO:生成查询条件
        Map<String, Object> params = new HashMap<String, Object>();
        
        //TODO:根据实际情况，填入排序字段等条件，根据是否需要排序，选择调用dao内方法
        int res = this.unitInfoDao.countUnitInfo(params);
        
        return res;
    }
    
    /**
      * 根据id更新对象
      * <功能详细描述>
      * @param unitInfo
      * @return [参数说明]
      * 
      * @return boolean [返回类型说明]
      * @exception throws [异常类型] [异常说明]
      * @see [类、类#方法、类#成员]
     */
    @Transactional
    public boolean updateById(UnitInfo unitInfo) {
        //TODO:验证参数是否合法，必填字段是否填写，
        AssertUtils.notNull(unitInfo, "unitInfo is null.");
        AssertUtils.notEmpty(unitInfo.getId(), "unitInfo.id is empty.");
        
        
        //TODO:生成需要更新字段的hashMap
        Map<String, Object> updateRowMap = new HashMap<String, Object>();
        updateRowMap.put("id", unitInfo.getId());
        
        //TODO:需要更新的字段
		updateRowMap.put("iname", unitInfo.getIname());	
		updateRowMap.put("currentPageIndex", unitInfo.getCurrentPageIndex());	
		updateRowMap.put("regDate", unitInfo.getRegDate());	
		updateRowMap.put("exported", unitInfo.isExported());	
		updateRowMap.put("exportedDate", unitInfo.getExportedDate());	
		updateRowMap.put("fetchDate", unitInfo.getFetchDate());	
		updateRowMap.put("pageIndex", unitInfo.getPageIndex());	
		updateRowMap.put("disruptTypeName", unitInfo.getDisruptTypeName());	
		updateRowMap.put("areaName", unitInfo.getAreaName());	
		updateRowMap.put("caseCode", unitInfo.getCaseCode());	
		updateRowMap.put("partyTypeName", unitInfo.getPartyTypeName());	
		updateRowMap.put("courtName", unitInfo.getCourtName());	
		updateRowMap.put("duty", unitInfo.getDuty());	
		updateRowMap.put("performance", unitInfo.getPerformance());	
		updateRowMap.put("gistUnit", unitInfo.getGistUnit());	
		updateRowMap.put("gistId", unitInfo.getGistId());	
		updateRowMap.put("publishDate", unitInfo.getPublishDate());	
		updateRowMap.put("businessEntity", unitInfo.getBusinessEntity());	
		updateRowMap.put("focusNumber", unitInfo.getFocusNumber());	
		updateRowMap.put("cardNum", unitInfo.getCardNum());	
        
        int updateRowCount = this.unitInfoDao.updateUnitInfo(updateRowMap);
        
        //TODO:如果需要大于1时，抛出异常并回滚，需要在这里修改
        return updateRowCount >= 1;
    }
}
