/*
 * 描          述:  <描述>
 * 修  改   人:  
 * 修改时间:  
 * <修改描述:>
 */
package com.tx.component.mainframe.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.boda.los.clientele.model.Clientinfo;
import com.boda.los.util.ObjectUtil;
import com.boda.los.util.StringUtil;
import com.tx.component.mainframe.dao.DistrictDao;
import com.tx.component.mainframe.model.District;
import com.tx.core.exceptions.util.AssertUtils;
import com.tx.core.paged.model.PagedList;

/**
 * District的业务层
 * <功能详细描述>
 * 
 * @author  
 * @version  [版本号, ]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
@Component("districtService")
public class DistrictService {
    
    @SuppressWarnings("unused")
    private Logger logger = LoggerFactory.getLogger(DistrictService.class);
    
    @SuppressWarnings("unused")
    //@Resource(name = "serviceLogger")
    private Logger serviceLogger;
    
    @Resource(name = "districtDao")
    private DistrictDao districtDao;
    
    /**
      * 将district实例插入数据库中保存
      * 1、如果district为空时抛出参数为空异常
      * 2、如果district中部分必要参数为非法值时抛出参数不合法异常
     * <功能详细描述>
     * @param district [参数说明]
     * 
     * @return void [返回类型说明]
     * @exception throws
     * @see [类、类#方法、类#成员]
    */
    @Transactional
    public void insertDistrict(District district) {
        //TODO:验证参数是否合法，必填字段是否填写，
        AssertUtils.notNull(district, "district is null.");
        AssertUtils.notEmpty(district.getId(), "district.id is empty.");
        
        this.districtDao.insertDistrict(district);
    }
      
     /**
      * 根据id删除district实例
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
        
        District condition = new District();
        condition.setId(id);
        return this.districtDao.deleteDistrict(condition);
    }
    
    /**
      * 根据Id查询District实体
      * 1、当id为empty时抛出异常
      * <功能详细描述>
      * @param id
      * @return [参数说明]
      * 
      * @return District [返回类型说明]
      * @exception throws 可能存在数据库访问异常DataAccessException
      * @see [类、类#方法、类#成员]
     */
    public District findDistrictById(String id) {
        AssertUtils.notEmpty(id, "id is empty.");
        
        District condition = new District();
        condition.setId(id);
        return this.districtDao.findDistrict(condition);
    }
    
    /**
      * 根据District实体列表
      * TODO:补充说明
      * 
      * <功能详细描述>
      * @return [参数说明]
      * 
      * @return List<District> [返回类型说明]
      * @exception throws [异常类型] [异常说明]
      * @see [类、类#方法、类#成员]
     */
    public List<District> queryDistrictList(/*TODO:自己定义条件*/) {
        //TODO:判断条件合法性
        
        //TODO:生成查询条件
        Map<String, Object> params = new HashMap<String, Object>();
        
        //TODO:根据实际情况，填入排序字段等条件，根据是否需要排序，选择调用dao内方法
        List<District> resList = this.districtDao.queryDistrictList(params);
        
        return resList;
    }
    
    /**
     * <获取省级地区>
     * <功能详细描述>
     * @author 龙剑豪
     * @return 省级地区
     * @throws Exception [参数说明]
     * 
     * @return List<District> [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
    */
   public List<District> queryProvinceSelectList() {
       Map<String, Object> params = new HashMap<String, Object>();
       params.put("visible", true);
       params.put("type", "0");//type:类型{(0:省),(1:市),(2:区)
       List<District> dList = this.districtDao.queryDistrictList(params);
       return dList;
   }
   
   /**
    * 根据传入的type和上级地区provinceId 查询出下级地区列表
    * <功能详细描述>
    * @param type:类型{(0:省),(1:市),(2:区)
    * @param provinceId 省级/市级地区id
    * @return 省级地区/市级下属的所有市级地区/区县
    * @throws Exception [参数说明]
    * @return List<District> [返回类型说明]
    * @exception throws [异常类型] [异常说明]
    * @see [类、类#方法、类#成员]
   */
   public List<District> queryCitySelectListByProvinceId(String provinceId,String type) {
       if(StringUtils.isEmpty(provinceId)) {
           return new ArrayList<District>();
       }
       Map<String, Object> params = new HashMap<String, Object>();
       District province = new District();
       province.setId(provinceId);
       params.put("parent", province);
       params.put("visible", true);
       params.put("type", type);
       List<District> dList = this.districtDao.queryDistrictList(params);
       return dList;
   }
    
    /**
     * 分页查询District实体列表
     * TODO:补充说明
     * 
     * <功能详细描述>
     * @return [参数说明]
     * 
     * @return List<District> [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
    */
    public PagedList<District> queryDistrictPagedList(/*TODO:自己定义条件*/int pageIndex,
            int pageSize) {
        //TODO:判断条件合法性
        
        //TODO:生成查询条件
        Map<String, Object> params = new HashMap<String, Object>();
        
        //TODO:根据实际情况，填入排序字段等条件，根据是否需要排序，选择调用dao内方法
        PagedList<District> resPagedList = this.districtDao.queryDistrictPagedList(params, pageIndex, pageSize);
        
        return resPagedList;
    }
    
    /**
      * 查询district列表总条数
      * TODO:补充说明
      * <功能详细描述>
      * @return [参数说明]
      * 
      * @return int [返回类型说明]
      * @exception throws [异常类型] [异常说明]
      * @see [类、类#方法、类#成员]
     */
    public int countDistrict(/*TODO:自己定义条件*/){
        //TODO:判断条件合法性
        
        //TODO:生成查询条件
        Map<String, Object> params = new HashMap<String, Object>();
        
        //TODO:根据实际情况，填入排序字段等条件，根据是否需要排序，选择调用dao内方法
        int res = this.districtDao.countDistrict(params);
        
        return res;
    }
    
    /**
      * 根据id更新对象
      * <功能详细描述>
      * @param district
      * @return [参数说明]
      * 
      * @return boolean [返回类型说明]
      * @exception throws [异常类型] [异常说明]
      * @see [类、类#方法、类#成员]
     */
    @Transactional
    public boolean updateById(District district) {
        //TODO:验证参数是否合法，必填字段是否填写，
        AssertUtils.notNull(district, "district is null.");
        AssertUtils.notEmpty(district.getId(), "district.id is empty.");
        
        
        //TODO:生成需要更新字段的hashMap
        Map<String, Object> updateRowMap = new HashMap<String, Object>();
        updateRowMap.put("id", district.getId());
        
        //TODO:需要更新的字段
		updateRowMap.put("parentId", district.getParentId());	
		updateRowMap.put("description", district.getDescription());	
		updateRowMap.put("name", district.getName());	
		updateRowMap.put("type", district.getType());	
        
        int updateRowCount = this.districtDao.updateDistrict(updateRowMap);
        
        //TODO:如果需要大于1时，抛出异常并回滚，需要在这里修改
        return updateRowCount >= 1;
    }
    
    /**
     * <获取客户的身份证所在地详细地址>
     * <功能详细描述>
     * @author 龙剑豪
     * @param clientinfo 客户信息实体
     * @return 客户的身份证所在地
     * 
     * @return String [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
    */
   public String getIdCardRegisteredByClientinfo(Clientinfo clientinfo) {
       StringBuilder address = new StringBuilder();
       
       /*
        * 获取省级地区
        */
       String idcardProId = clientinfo.getIdcardProId();
       if(!StringUtil.isEmpty(idcardProId)) {
           District idcardPro = this.findDistrictById(idcardProId);
           if(!ObjectUtil.isEmpty(idcardPro)) {
               address.append(idcardPro.getName());
           }
       }
       
       /*
        * 获取市级地区
        */
       String idcardCityId = clientinfo.getIdcardCityId();
       if(!StringUtil.isEmpty(idcardCityId)) {
           District idcardCity = this.findDistrictById(idcardCityId);
           if(!ObjectUtil.isEmpty(idcardCity)) {
               address.append(idcardCity.getName());
           }
       }
       
       /*
        * 获取区/县
        */
       String idcardCountyId = clientinfo.getIdcardCountyId();
       if(!StringUtil.isEmpty(idcardCountyId)) {
           District idcardCounty = this.findDistrictById(idcardCountyId);
           if(!ObjectUtil.isEmpty(idcardCounty)) {
               address.append(idcardCounty.getName());
           }
       }
       
       address.append(clientinfo.getAddress());//详细地址
       
       return address.toString();
   }
}
