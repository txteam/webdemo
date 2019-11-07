/*
 * 描       述:  <描述>
 * 修  改 人:  
 * 修改时间:
 * <修改描述:>
 */
package com.tx.local.basicdata.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tx.core.paged.model.PagedList;
import com.tx.core.util.StringUtils;
import com.tx.local.basicdata.model.District;
import com.tx.local.basicdata.model.DistrictTypeEnum;
import com.tx.local.basicdata.service.DistrictService;

/**
 * District显示层逻辑<br/>
 * <功能详细描述>
 * 
 * @author  PengQingyang
 * @version  [版本号, 2013-8-27]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
@Controller
@RequestMapping("/district")
public class DistrictController {
    
    /** districtService */
    @Resource
    private DistrictService districtService;
    
    /**
     * 跳转到查询District分页列表页面<br/>
     *<功能详细描述>
     * @return [参数说明]
     * 
     * @return String [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @RequestMapping("/toQueryTreeList")
    public String toQueryTreeList(ModelMap response) {
        return "basicdata/queryDistrictTreeList";
    }
    
    /**
     * 跳转到添加District页面<br/>
     *<功能详细描述>
     * @return [参数说明]
     * 
     * @return String [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @RequestMapping("/toAdd")
    public String toAdd(ModelMap response) {
        response.put("district", new District());
        
        return "basicdata/addDistrict";
    }
    
    /**
     * 跳转到编辑District页面
     *<功能详细描述>
     * @return [参数说明]
     * 
     * @return String [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @RequestMapping("/toUpdate")
    public String toUpdate(@RequestParam("districtId") String districtId,
            ModelMap response) {
        District resDistrict = this.districtService.findById(districtId);
        response.put("district", resDistrict);
        
        return "basicdata/updateDistrict";
    }
    
    /**
     * 判断District:
     * 是否已经被使用
     * @param uniqueGetterName
     * @param excludeDistrictId
     * @return [参数说明]
     * 
     * @return boolean [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @ResponseBody
    @RequestMapping("/validate")
    public Map<String, String> validate(
            @RequestParam MultiValueMap<String, String> request,
            @RequestParam("code") String code,
            @RequestParam(value = "id", required = false) String excludeId) {
        Map<String, String> key2valueMap = new HashMap<String, String>();
        key2valueMap.put("code", code);
        boolean flag = this.districtService.exists(key2valueMap, excludeId);
        Map<String, String> resMap = new HashMap<String, String>();
        if (!flag) {
            resMap.put("ok", "可用的区域编码");
        } else {
            resMap.put("error", "已经存在的区域编码");
        }
        return resMap;
    }
    
    /**
     * 查询District列表<br/>
     *<功能详细描述>
     * @return [参数说明]
     * 
     * @return List<District> [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @ResponseBody
    @RequestMapping("/queryList")
    public List<District> queryList(
            @RequestParam(value = "valid", required = false) Boolean valid,
            @RequestParam(value = "type", required = false) DistrictTypeEnum type,
            @RequestParam(value = "level", required = false) Integer level,
            @RequestParam MultiValueMap<String, String> request) {
        Map<String, Object> params = new HashMap<>();
        
        if (StringUtils.isEmpty(request.getFirst("parentIdLike"))) {
            params.put("parentId", request.getFirst("parentId"));
        } else {
            String parentIdLike = request.getFirst("parentIdLike");
            parentIdLike = parentIdLike.substring(0,
                    parentIdLike.indexOf("0000"));
            params.put("parentIdLike", parentIdLike);
        }
        
        params.put("countryId", request.getFirst("countryId"));
        params.put("provinceId", request.getFirst("provinceId"));
        params.put("cityId", request.getFirst("cityId"));
        
        params.put("name", request.getFirst("name"));
        params.put("fullName", request.getFirst("fullName"));
        params.put("code", request.getFirst("code"));
        
        params.put("type", type);
        params.put("level", level);
        params.put("maxLevel", request.getFirst("maxLevel"));
        
        List<District> resList = this.districtService.queryList(valid, params);
        
        return resList;
    }
    
    /**
     * 查询District分页列表<br/>
     *<功能详细描述>
     * @return [参数说明]
     * 
     * @return List<District> [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @ResponseBody
    @RequestMapping("/queryPagedList")
    public PagedList<District> queryPagedList(
            @RequestParam(value = "valid", required = false) Boolean valid,
            @RequestParam(value = "type", required = false) DistrictTypeEnum type,
            @RequestParam(value = "level", required = false) Integer level,
            @RequestParam(value = "modifyAble", required = false) Boolean modifyAble,
            @RequestParam MultiValueMap<String, String> request,
            @RequestParam(value = "pageNumber", required = false, defaultValue = "1") int pageIndex,
            @RequestParam(value = "pageSize", required = false, defaultValue = "10") int pageSize) {
        Map<String, Object> params = new HashMap<>();
        
        params.put("parentId", request.getFirst("parentId"));
        
        params.put("countryId", request.getFirst("countryId"));
        params.put("provinceId", request.getFirst("provinceId"));
        params.put("cityId", request.getFirst("cityId"));
        
        params.put("name", request.getFirst("name"));
        params.put("fullName", request.getFirst("fullName"));
        params.put("code", request.getFirst("code"));
        params.put("postcode", request.getFirst("postcode"));
        
        params.put("type", type);
        params.put("level", level);
        params.put("modifyAble", modifyAble);
        
        PagedList<District> resPagedList = this.districtService
                .queryPagedList(valid, params, pageIndex, pageSize);
        return resPagedList;
    }
    
    /**
     * 添加组织结构页面
     *<功能详细描述>
     * @param organization [参数说明]
     * 
     * @return void [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @RequestMapping("/add")
    @ResponseBody
    public boolean add(District district) {
        this.districtService.insert(district);
        return true;
    }
    
    /**
     * 更新组织<br/>
     *<功能详细描述>
     * @param district
     * @return [参数说明]
     * 
     * @return boolean [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @RequestMapping("/update")
    @ResponseBody
    public boolean update(District district) {
        this.districtService.updateById(district);
        return true;
    }
    
    /**
     * 删除指定District<br/> 
     *<功能详细描述>
     * @param districtId
     * @return [参数说明]
     * 
     * @return boolean [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @RequestMapping("/deleteById")
    @ResponseBody
    public boolean deleteById(
            @RequestParam(value = "districtId") String districtId) {
        boolean resFlag = this.districtService.deleteById(districtId);
        return resFlag;
    }
    
    /**
     * 禁用District
     * @param districtId
     * @return [参数说明]
     * 
     * @return boolean [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @ResponseBody
    @RequestMapping("/disableById")
    public boolean disableById(
            @RequestParam(value = "districtId") String districtId) {
        boolean resFlag = this.districtService.disableById(districtId);
        return resFlag;
    }
    
    /**
     * 启用District<br/>
     *<功能详细描述>
     * @param districtId
     * @return [参数说明]
     * 
     * @return boolean [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @ResponseBody
    @RequestMapping("/enableById")
    public boolean enableById(
            @RequestParam(value = "districtId") String districtId) {
        boolean resFlag = this.districtService.enableById(districtId);
        return resFlag;
    }
}