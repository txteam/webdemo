/*
 * 描       述:  <描述>
 * 修  改 人:  
 * 修改时间:
 * <修改描述:>
 */
package com.tx.local.institution.controller;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tx.core.paged.model.PagedList;
import com.tx.local.clientinfo.model.ClientExtendInfo;
import com.tx.local.institution.model.CapacityTypeEnum;
import com.tx.local.institution.model.InstitutionCapacity;
import com.tx.local.institution.model.InstitutionInfo;
import com.tx.local.institution.model.InstitutionTypeEnum;
import com.tx.local.institution.service.InstitutionInfoService;
import com.tx.local.security.util.WebContextUtils;

/**
 * InstitutionInfo控制层<br/>
 * 
 * @author []
 * @version [版本号]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
@Controller
@RequestMapping("/institutionInfo")
public class InstitutionInfoController {
    
    //InstitutionInfo业务层
    @Resource(name = "institutionInfoService")
    private InstitutionInfoService institutionInfoService;
    
    /**
     * 跳转到查询InstitutionInfo分页列表页面<br/>
     * <功能详细描述>
     * @return [参数说明]
     *
     * @return String [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @RequestMapping("/toQueryList")
    public String toQueryList(ModelMap response) {
        response.put("types", InstitutionTypeEnum.values());
        
        return "institution/queryInstitutionPagedList";
    }
    
    /**
     * 跳转到查询InstitutionInfo分页列表页面<br/>
     * <功能详细描述>
     * @return [参数说明]
     * 
     * @return String [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @RequestMapping("/toQueryPagedList")
    public String toQueryPagedList(ModelMap response) {
        response.put("types", InstitutionTypeEnum.values());
        
        return "institution/queryInstitutionInfoPagedList";
    }
    
    @RequestMapping("/toQueryClientPage")
    public String toQueryClientPage(@RequestParam("id") String id,
            ModelMap response) {
        InstitutionInfo institutionInfo = institutionInfoService
                .findByClientId(id);
        response.put("institutionInfo", institutionInfo);
        Map<String, Object> params = new HashMap<>();
        params.put("institutionId", institutionInfo.getId());
        List<InstitutionCapacity> institutionCapacities = institutionCapacityService
                .queryList(params);
        response.put("institutionCapacities", institutionCapacities);
        return "institution/toQueryClientPage";
    }
    
    /**
     * 跳转到新增InstitutionInfo页面<br/>
     * <功能详细描述>
     * @return [参数说明]
     * 
     * @return String [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @RequestMapping("/toAdd")
    public String toAdd(ModelMap response) {
        response.put("institutionInfo", new InstitutionInfo());
        
        response.put("types", InstitutionTypeEnum.values());
        
        return "institution/addInstitutionInfo";
    }
    
    /**
     * 跳转到编辑InstitutionInfo页面
     * <功能详细描述>
     * @return [参数说明]
     * 
     * @return String [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @RequestMapping("/toUpdate")
    public String toUpdate(@RequestParam("id") String id, ModelMap response) {
        InstitutionInfo institutionInfo = this.institutionInfoService
                .findById(id);
        response.put("institutionInfo", institutionInfo);
        
        response.put("types", InstitutionTypeEnum.values());
        
        return "institution/updateInstitutionInfo";
    }
    
    /**
     * 跳转到编辑机构信息主页面<br/>
     *   可编辑机构信息
     *   可在其中编辑机构信息的产能信息
     * <功能详细描述>
     * @return [参数说明]
     *
     * @return String [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @RequestMapping("/toModifyMain")
    public String toModifyMain(@RequestParam("id") String id,
            ModelMap response) {
        
        InstitutionInfo institutionInfo = this.institutionInfoService
                .findById(id);
        response.put("institutionInfo", institutionInfo);
        
        response.put("types", InstitutionTypeEnum.values());
        
        return "institution/toQueryPagedList";
    }
    
    /**
     * 跳转到编辑机构信息主页面<br/>
     *   可编辑社属机构信息
     *   可在其中编辑机构信息的产能信息
     * <功能详细描述>
     * @return [参数说明]
     *
     * @return String [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @RequestMapping("/toModify")
    public String toModify(@RequestParam("id") String id, ModelMap response) {
        
        InstitutionInfo institutionInfo = this.institutionInfoService
                .findById(id);
        response.put("institutionInfo", institutionInfo);
        
        response.put("types", CapacityTypeEnum.values());
        
        return "institution/modifyInstitutionInfo";
    }
    
    /**
     * 查询InstitutionInfo实例列表<br/>
     * <功能详细描述>
     * @return [参数说明]
     * 
     * @return List<InstitutionInfo> [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @ResponseBody
    @RequestMapping("/queryList")
    public List<InstitutionInfo> queryList(
            @RequestParam MultiValueMap<String, String> request) {
        Map<String, Object> params = new HashMap<>();
        params.put("name", request.getFirst("name"));
        
        List<InstitutionInfo> resList = this.institutionInfoService
                .queryList(params);
        
        return resList;
    }
    
    /**
     * 查询InstitutionInfo实例分页列表<br/>
     * <功能详细描述>
     * @return [参数说明]
     * 
     * @return List<InstitutionInfo> [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @ResponseBody
    @RequestMapping("/queryDetailPagedList")
    public PagedList<InstitutionInfo> queryDetailPagedList(
            @RequestParam(value = "pageNumber", required = false, defaultValue = "1") int pageIndex,
            @RequestParam(value = "pageSize", required = false, defaultValue = "10") int pageSize,
            @RequestParam MultiValueMap<String, String> request) {
        Map<String, Object> params = new HashMap<>();
        params.put("name", request.getFirst("name"));
        
        PagedList<InstitutionInfo> resPagedList = this.institutionInfoService
                .queryPagedList(params, pageIndex, pageSize);
        return resPagedList;
    }
    
    /**
     * 查询InstitutionInfo实例分页列表<br/>
     * <功能详细描述>
     * @return [参数说明]
     * 
     * @return List<InstitutionInfo> [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @ResponseBody
    @RequestMapping("/queryPagedList")
    public PagedList<InstitutionInfo> queryPagedList(
            @RequestParam(value = "pageNumber", required = false, defaultValue = "1") int pageIndex,
            @RequestParam(value = "pageSize", required = false, defaultValue = "10") int pageSize,
            @RequestParam MultiValueMap<String, String> request) {
        Map<String, Object> params = new HashMap<>();
        params.put("searchValue", request.getFirst("searchValue"));
        params.put("linkMobileNumber", request.getFirst("linkMobileNumber"));
        params.put("clientId", request.getFirst("clientId"));
        params.put("isClientType", "isClientType");
        /*
        String institutionId = request.getFirst("institutionId");
        if(StringUtils.isNotBlank(institutionId)){
            params.put("institutionId", institutionId);
        }
        */
        
        String institutionId = request.getFirst("institutionId");
        if (StringUtils.isNotBlank(institutionId)) {
            List<ClientExtendInfo> clientList = clientExtendInfoService
                    .getClientExtendListById(institutionId);
            params.put("clients", clientList);
        }
        
        PagedList<InstitutionInfo> resPagedList = this.institutionInfoService
                .queryPagedList(params, pageIndex, pageSize);
        
        List<InstitutionInfo> addsList = this.institutionInfoService
                .queryList(new HashMap<>());
        Map<String, InstitutionInfo> mappedAdds = addsList.stream()
                .collect(Collectors.toMap(InstitutionInfo::getId, (p) -> p));
        
        List<InstitutionInfo> resList = resPagedList.getList();
        for (InstitutionInfo item : resList) {
            if (mappedClis.containsKey(item.getClientId())) {
                if (mappedAdds.containsKey(mappedClis.get(item.getClientId())
                        .getInstitutionId())) {
                    item.setInstitutionInfo(
                            mappedAdds.get(mappedClis.get(item.getClientId())
                                    .getInstitutionId()));
                }
            }
        }
        resPagedList.setList(resList);
        
        return resPagedList;
    }
    
    /**
     * 新增InstitutionInfo实例
     * <功能详细描述>
     * @param institutionInfo [参数说明]
     * 
     * @return void [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @ResponseBody
    @RequestMapping("/add")
    public InstitutionInfo add(InstitutionInfo institutionInfo) {
        String vcid = WebContextUtils.getVcid();
        institutionInfo.setVcid(vcid);
        
        this.institutionInfoService.insertClientAndInstitution(institutionInfo);
        return institutionInfo;
    }
    
    /**
     * 更新InstitutionInfo实例<br/>
     * <功能详细描述>
     * @param institutionInfo
     * @return [参数说明]
     * 
     * @return boolean [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @ResponseBody
    @RequestMapping("/update")
    public boolean update(InstitutionInfo institutionInfo) {
        boolean flag = this.institutionInfoService
                .updateClientAndInstitution(institutionInfo);
        return flag;
    }
    
    /**
     * 根据主键查询InstitutionInfo实例<br/> 
     * <功能详细描述>
     * @param id
     * @return [参数说明]
     * 
     * @return boolean [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @ResponseBody
    @RequestMapping("/findById")
    public InstitutionInfo findById(@RequestParam(value = "id") String id) {
        InstitutionInfo institutionInfo = this.institutionInfoService
                .findById(id);
        return institutionInfo;
    }
    
    /**
     * 删除InstitutionInfo实例<br/> 
     * <功能详细描述>
     * @param id
     * @return [参数说明]
     * 
     * @return boolean [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @ResponseBody
    @RequestMapping("/deleteById")
    public boolean deleteById(@RequestParam(value = "id") String id) {
        boolean flag = this.institutionInfoService.deleteById(id);
        return flag;
    }
    
    @ResponseBody
    @RequestMapping("/deleteLogicById")
    public boolean deleteLogicById(@RequestParam(value = "id") String id) {
        boolean flag = this.institutionInfoService.deleteLogicById(id);
        return flag;
    }
    
    /**
     * 校验是否重复<br/>
     * @param excludeId
     * @param params
     * @return [参数说明]
     * 
     * @return boolean [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @ResponseBody
    @RequestMapping("/validate")
    public Map<String, String> validate(
            @RequestParam(value = "id", required = false) String excludeId,
            @RequestParam Map<String, String> params) {
        params.remove("id");
        boolean flag = this.institutionInfoService.exists(params, excludeId);
        
        Map<String, String> resMap = new HashMap<String, String>();
        if (!flag) {
            resMap.put("ok", "");
        } else {
            resMap.put("error", "重复值");
        }
        return resMap;
    }
    
    @ResponseBody
    @RequestMapping("/statisticData")
    public Map<String, Object> statisticData(
            @RequestParam Map<String, Object> params) {
        Map<String, Object> result = new HashMap<>();
        Integer count = 0;
        BigDecimal mj = new BigDecimal(0);
        
        Map<String, Object> paramx = new HashMap<>();
        /*
        String institutionId = (String) params.get("institutionId");
        if(StringUtils.isNotBlank(institutionId)){
           params.put("institutionId", institutionId);
        }
        */
        String institutionId = (String) params.get("institutionId");
        if (StringUtils.isNotBlank(institutionId)) {
            List<ClientExtendInfo> clientList = clientExtendInfoService
                    .getClientExtendListById(institutionId);
            paramx.put("clients", clientList);
        }
        paramx.put("isClientType", "isClientType");
        List<InstitutionInfo> list = this.institutionInfoService
                .queryList(paramx);
        for (InstitutionInfo item : list) {
            count++;
            if (item.getInstitutionSummaryInfo() != null) {
                mj = mj.add(item.getInstitutionSummaryInfo().getLandArea());
            }
        }
        result.put("mj", mj);
        result.put("count", count);
        return result;
    }
    
}