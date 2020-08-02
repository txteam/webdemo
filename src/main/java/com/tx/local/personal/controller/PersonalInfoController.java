/*
 * 描       述:  <描述>
 * 修  改 人:  
 * 修改时间:
 * <修改描述:>
 */
package com.tx.local.personal.controller;

import java.math.BigDecimal;
import java.util.Date;
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
import com.tx.local.basicdata.model.SexEnum;
import com.tx.local.clientinfo.model.ClientExtendInfo;
import com.tx.local.institution.model.InstitutionInfo;
import com.tx.local.institution.service.InstitutionInfoService;
import com.tx.local.personal.model.PersonalInfo;
import com.tx.local.personal.model.PersonalTypeEnum;
import com.tx.local.personal.service.PersonalInfoService;
import com.tx.local.security.util.WebContextUtils;

/**
 * PersonalInfo控制层<br/>
 * 
 * @author []
 * @version [版本号]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
@Controller
@RequestMapping("/personalInfo")
public class PersonalInfoController {
    
    //PersonalInfo业务层
    @Resource(name = "personalInfoService")
    private PersonalInfoService personalInfoService;
    
    @Resource(name = "institutionInfoService")
    private InstitutionInfoService institutionInfoService;
    
    /**
     * 跳转到查询PersonalInfo分页列表页面<br/>
     * <功能详细描述>
     * @return [参数说明]
     * 
     * @return String [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @RequestMapping("/toQueryPagedList")
    public String toQueryPagedList(ModelMap response) {
        response.put("sexs", SexEnum.values());
        response.put("types", PersonalTypeEnum.values());
        
        return "personal/queryPersonalInfoPagedList";
    }
    
    /**
     * 跳转到查询个人用户详细信息列表
     * <功能详细描述>
     * @return [参数说明]
     *
     * @return String [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @RequestMapping("/toQueryList")
    public String toQueryList(ModelMap response) {
        response.put("sexs", SexEnum.values());
        response.put("types", PersonalTypeEnum.values());
        
        return "personal/queryPersonalPagedList";
    }
    
    /**
     * 跳转到新增PersonalInfo页面<br/>
     * <功能详细描述>
     * @return [参数说明]
     * 
     * @return String [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @RequestMapping("/toAdd")
    public String toAdd(ModelMap response) {
        response.put("personalInfo", new PersonalInfo());
        
        response.put("sexs", SexEnum.values());
        response.put("date", new Date());
        response.put("types", PersonalTypeEnum.values());
        
        return "personal/addPersonalInfo";
    }
    
    /**
     * 跳转到编辑PersonalInfo页面
     * <功能详细描述>
     * @return [参数说明]
     * 
     * @return String [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @RequestMapping("/toUpdate")
    public String toUpdate(@RequestParam("id") String id, ModelMap response) {
        PersonalInfo personalInfo = this.personalInfoService.findById(id);
        response.put("personalInfo", personalInfo);
        
        response.put("sexs", SexEnum.values());
        response.put("date", new Date());
        response.put("types", PersonalTypeEnum.values());
        
        return "personal/updatePersonalInfo";
    }
    
    /**
     * 查询PersonalInfo实例列表<br/>
     * <功能详细描述>
     * @return [参数说明]
     * 
     * @return List<PersonalInfo> [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @ResponseBody
    @RequestMapping("/queryList")
    public List<PersonalInfo> queryList(
            @RequestParam MultiValueMap<String, String> request) {
        Map<String, Object> params = new HashMap<>();
        params.put("name", request.getFirst("name"));
        
        List<PersonalInfo> resList = this.personalInfoService.queryList(params);
        
        return resList;
    }
    
    /**
     * 查询PersonalInfo实例分页列表<br/>
     * <功能详细描述>
     * @return [参数说明]
     * 
     * @return List<PersonalInfo> [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @ResponseBody
    @RequestMapping("/queryPagedList")
    public PagedList<PersonalInfo> queryPagedList(
            @RequestParam(value = "pageNumber", required = false, defaultValue = "1") int pageIndex,
            @RequestParam(value = "pageSize", required = false, defaultValue = "10") int pageSize,
            @RequestParam MultiValueMap<String, String> request) {
        Map<String, Object> params = new HashMap<>();
        params.put("searchValue", request.getFirst("searchValue"));
        params.put("linkMobileNumber", request.getFirst("linkMobileNumber"));
        params.put("clientId", request.getFirst("clientId"));
        /*
        String institutionId = request.getFirst("institutionId");
        if(StringUtils.isNotBlank(institutionId)){
            params.put("institutionId", institutionId);
        }
        */
        
        PagedList<PersonalInfo> resPagedList = this.personalInfoService
                .queryPagedList(params, pageIndex, pageSize);
        List<InstitutionInfo> addsList = this.institutionInfoService
                .queryList(new HashMap<>());
        Map<String, InstitutionInfo> mappedAdds = addsList.stream()
                .collect(Collectors.toMap(InstitutionInfo::getId, (p) -> p));
        
        List<ClientExtendInfo> clisList = this.clientExtendInfoService
                .queryList(new HashMap<>());
        Map<String, ClientExtendInfo> mappedClis = clisList.stream()
                .collect(Collectors.toMap(ClientExtendInfo::getClientId,
                        (p) -> p));
        
        List<PersonalInfo> resList = resPagedList.getList();
        for (PersonalInfo item : resList) {
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
     * 新增PersonalInfo实例
     * <功能详细描述>
     * @param personalInfo [参数说明]
     * 
     * @return void [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @ResponseBody
    @RequestMapping("/add")
    public boolean add(PersonalInfo personalInfo) {
        String vcid = WebContextUtils.getVcid();
        personalInfo.setVcid(vcid);
        this.personalInfoService.insertClientAndPersonal(personalInfo);
        return true;
    }
    
    /**
     * 更新PersonalInfo实例<br/>
     * <功能详细描述>
     * @param personalInfo
     * @return [参数说明]
     * 
     * @return boolean [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @ResponseBody
    @RequestMapping("/update")
    public boolean update(PersonalInfo personalInfo) {
        boolean flag = this.personalInfoService
                .updateClientAndPersonal(personalInfo);
        return flag;
    }
    
    /**
     * 根据主键查询PersonalInfo实例<br/> 
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
    public PersonalInfo findById(@RequestParam(value = "id") String id) {
        PersonalInfo personalInfo = this.personalInfoService.findById(id);
        return personalInfo;
    }
    
    @RequestMapping("/toQueryClientPage")
    public String toQueryClientPage(@RequestParam("id") String id,
            ModelMap response) {
        PersonalInfo personalInfo = personalInfoService.findByClientId(id);
        response.put("personalInfo", personalInfo);
        return "personal/toQueryClientPage";
    }
    
    /**
     * 删除PersonalInfo实例<br/> 
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
        boolean flag = this.personalInfoService.deleteById(id);
        return flag;
    }
    
    @ResponseBody
    @RequestMapping("/deleteLogicById")
    public boolean deleteLogicById(@RequestParam(value = "id") String id) {
        boolean flag = this.personalInfoService.deleteLogicById(id);
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
        boolean flag = this.personalInfoService.exists(params, excludeId);
        
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
        Map<String, Object> responseMap = new HashMap<>();
        Integer rk = 0;
        Integer count = 0;
        BigDecimal mj = new BigDecimal(0);
        Map<String, Object> result = new HashMap<>();
        /*
        String institutionId = (String) params.get("institutionId");
        if(StringUtils.isNotBlank(institutionId)){
            result.put("institutionId", institutionId);
        }
        */
        String institutionId = (String) params.get("institutionId");
        if (StringUtils.isNotBlank(institutionId)) {
            List<ClientExtendInfo> clientList = clientExtendInfoService
                    .getClientExtendListById(institutionId);
            result.put("clients", clientList);
        }
        
        result.put("operationStatus", true);
        List<PersonalInfo> list = personalInfoService.queryList(result);
        for (PersonalInfo item : list) {
            count++;
            rk = item.getPersonalSummary().getFamilyCount() + rk;
            mj = mj.add(item.getPersonalSummary().getLandArea());
        }
        responseMap.put("rk", rk);
        responseMap.put("mj", mj);
        responseMap.put("count", count);
        return responseMap;
    }
    
}