//package com.tx.client.institution.controller;
//
//import java.math.BigDecimal;
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//import java.util.stream.Collectors;
//
//import javax.annotation.Resource;
//import javax.servlet.http.HttpServletRequest;
//
//import org.apache.commons.lang.StringUtils;
//import org.springframework.stereotype.Controller;
//import org.springframework.transaction.TransactionStatus;
//import org.springframework.transaction.support.TransactionCallbackWithoutResult;
//import org.springframework.transaction.support.TransactionTemplate;
//import org.springframework.ui.ModelMap;
//import org.springframework.util.MultiValueMap;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.ResponseBody;
//
//import com.alibaba.fastjson.JSON;
//import com.alibaba.fastjson.JSONArray;
//import com.tx.core.paged.model.PagedList;
//import com.tx.local.adminstitution.model.AdmInstitutionInfo;
//import com.tx.local.clientinfo.model.ClientExtendInfo;
//import com.tx.local.cooinstitution.model.CooInstitutionInfo;
//import com.tx.local.institution.model.InstitutionCapacity;
//import com.tx.local.institution.model.InstitutionInfo;
//import com.tx.local.institution.model.InstitutionSummaryInfo;
//import com.tx.local.institution.model.InstitutionTypeEnum;
//import com.tx.local.institution.service.InstitutionInfoService;
//import com.tx.local.institution.service.InstitutionSummaryInfoService;
//import com.tx.local.personal.model.PersonalInfo;
//import com.tx.local.personal.model.PersonalSummary;
//import com.tx.local.personal.service.PersonalInfoService;
//import com.tx.local.personal.service.PersonalSummaryService;
//import com.tx.local.security.util.ClientWebContextUtils;
//
///**
// * InstitutionInfo控制层<br/>
// *
// * @author []
// * @version [版本号]
// * @see [相关类/方法]
// * @since [产品/模块版本]
// */
//@Controller("client.institutionInfoController")
//@RequestMapping("/client/institutionInfo")
//public class InstitutionInfoController {
//    
//    @Resource(name = "institutionInfoService")
//    private InstitutionInfoService institutionInfoService;
//    
//    @Resource
//    private TransactionTemplate transactionTemplate;
//    
//    @Resource(name = "personalInfoService")
//    private PersonalInfoService personalInfoService;
//    
//    @Resource(name = "personalSummaryService")
//    private PersonalSummaryService personalSummaryService;
//    
//    //机构扩展信息业务层
//    @Resource(name = "institutionSummaryInfoService")
//    private InstitutionSummaryInfoService institutionSummaryInfoService;
//    
//    @RequestMapping("/navigation")
//    public String navigation(ModelMap response) {
//        String clientId = ClientWebContextUtils.getClientId();
//        InstitutionInfo institutionInfo = institutionInfoService
//                .findByClientId(clientId);
//        if (InstitutionTypeEnum.ADM_INS.equals(institutionInfo.getType())) {
//            return "redirect:/client/admInstitutionInfo/queryDetail";
//        } else {
//            return "redirect:/client/cooInstitutionInfo/queryDetail";
//        }
//    }
//    
//    @RequestMapping("/toQueryClientPage")
//    public String toQueryClientPage(@RequestParam("id") String id,
//            ModelMap response) {
//        InstitutionInfo institutionInfo = institutionInfoService
//                .findByClientId(id);
//        response.put("institutionInfo", institutionInfo);
//        Map<String, Object> params = new HashMap<>();
//        params.put("institutionId", institutionInfo.getId());
//        List<InstitutionCapacity> institutionCapacities = institutionCapacityService
//                .queryList(params);
//        response.put("institutionCapacities", institutionCapacities);
//        return "institution/queryEnterpriseInfoDetail";
//    }
//    
//    /**
//     * 跳转到查询InstitutionInfo分页列表页面
//     * @param response
//     * @return
//     */
//    @RequestMapping("/toQueryPagedList")
//    public String toQueryList(ModelMap response) {
//        response.put("types", InstitutionTypeEnum.values());
//        setLayUiInstitutionInfo(response);
//        return "institution/queryInstitutionPagedList";
//    }
//    
//    /**
//     * 查询InstitutionInfo实例分页列表<
//     * @param pageIndex
//     * @param pageSize
//     * @param request
//     * @return
//     */
//    @ResponseBody
//    @RequestMapping("/queryPagedList")
//    public PagedList<InstitutionInfo> queryPagedList(
//            @RequestParam(value = "pageNumber", required = false, defaultValue = "1") int pageIndex,
//            @RequestParam(value = "pageSize", required = false, defaultValue = "10") int pageSize,
//            @RequestParam MultiValueMap<String, String> request) {
//        Map<String, Object> params = new HashMap<>();
//        params.put("searchValue", request.getFirst("searchValue"));
//        params.put("linkMobileNumber", request.getFirst("linkMobileNumber"));
//        params.put("clientId", request.getFirst("clientId"));
//        params.put("isClientType", "isClientType");
//        /*
//        String institutionId = request.getFirst("institutionId");
//        if(StringUtils.isNotBlank(institutionId)){
//            params.put("institutionId", institutionId);
//        }
//        */
//        
//        String institutionId = request.getFirst("institutionId");
//        
//        if (StringUtils.isBlank(institutionId)) {
//            InstitutionInfo isnItem = institutionInfoService
//                    .findByClientId(ClientWebContextUtils.getClientId());
//            if (isnItem != null) {
//                institutionId = isnItem.getId();
//            }
//        }
//        if (StringUtils.isNotBlank(institutionId)) {
//            List<ClientExtendInfo> clientList = clientExtendInfoService
//                    .getClientExtendListById(institutionId);
//            params.put("clients", clientList);
//        }
//        
//        PagedList<InstitutionInfo> resPagedList = this.institutionInfoService
//                .queryPagedList(params, pageIndex, pageSize);
//        
//        List<InstitutionInfo> addsList = this.institutionInfoService
//                .queryList(new HashMap<>());
//        Map<String, InstitutionInfo> mappedAdds = addsList.stream()
//                .collect(Collectors.toMap(InstitutionInfo::getId, (p) -> p));
//        
//        List<ClientExtendInfo> clisList = this.clientExtendInfoService
//                .queryList(new HashMap<>());
//        Map<String, ClientExtendInfo> mappedClis = clisList.stream()
//                .collect(Collectors.toMap(ClientExtendInfo::getClientId,
//                        (p) -> p));
//        
//        List<InstitutionInfo> resList = resPagedList.getList();
//        for (InstitutionInfo item : resList) {
//            if (mappedClis.containsKey(item.getClientId())) {
//                if (mappedAdds.containsKey(mappedClis.get(item.getClientId())
//                        .getInstitutionId())) {
//                    item.setInstitutionInfo(
//                            mappedAdds.get(mappedClis.get(item.getClientId())
//                                    .getInstitutionId()));
//                }
//            }
//        }
//        
//        resPagedList.setList(resList);
//        
//        return resPagedList;
//    }
//    
//    @RequestMapping("/toQueryInstitutionCapacityList")
//    public String toQueryInstitutionCapacityList(String institutionId,
//            ModelMap response) {
//        Map<String, Object> params = new HashMap<>();
//        params.put("institutionId", institutionId);
//        List<InstitutionCapacity> institutionCapacities = institutionCapacityService
//                .queryList(params);
//        response.put("institutionCapacities", institutionCapacities);
//        return "institution/queryInstitutionCapacityList";
//    }
//    
//    @ResponseBody
//    @RequestMapping("/statisticData")
//    public Map<String, Object> statisticData(
//            @RequestParam Map<String, Object> params) {
//        Map<String, Object> result = new HashMap<>();
//        Integer count = 0;
//        BigDecimal mj = new BigDecimal(0);
//        
//        Map<String, Object> paramx = new HashMap<>();
//        /*
//        String institutionId = (String) params.get("institutionId");
//        if(StringUtils.isNotBlank(institutionId)){
//            params.put("institutionId", institutionId);
//        }
//        */
//        String institutionId = (String) params.get("institutionId");
//        
//        if (StringUtils.isBlank(institutionId)) {
//            InstitutionInfo isnItem = institutionInfoService
//                    .findByClientId(ClientWebContextUtils.getClientId());
//            if (isnItem != null) {
//                institutionId = isnItem.getId();
//            }
//        }
//        if (StringUtils.isNotBlank(institutionId)) {
//            List<ClientExtendInfo> clientList = clientExtendInfoService
//                    .getClientExtendListById(institutionId);
//            paramx.put("clients", clientList);
//        }
//        
//        paramx.put("isClientType", "isClientType");
//        List<InstitutionInfo> list = this.institutionInfoService
//                .queryList(paramx);
//        for (InstitutionInfo item : list) {
//            count++;
//            if (item.getInstitutionSummaryInfo() != null) {
//                mj = mj.add(item.getInstitutionSummaryInfo().getLandArea());
//            }
//        }
//        result.put("mj", mj);
//        result.put("count", count);
//        return result;
//    }
//    
//    @ResponseBody
//    @RequestMapping("/deleteLogicById")
//    public boolean deleteLogicById(@RequestParam(value = "id") String id) {
//        boolean flag = this.institutionInfoService.deleteLogicById(id);
//        return flag;
//    }
//    
//    public void setLayUiInstitutionInfo(ModelMap model) {
//        InstitutionInfo institutionInfo = institutionInfoService
//                .findByClientId(ClientWebContextUtils.getClientId());
//        
//        if (institutionInfo.getType() == InstitutionTypeEnum.ADM_INS) {
//            AdmInstitutionInfo admInstitutionInfo = admInstitutionInfoService
//                    .findByClientId(institutionInfo.getClientId());
//            model.put("clientParentId", admInstitutionInfo.getId());
//            model.put("institutionId", admInstitutionInfo.getInstitutionId());
//            List<AdmInstitutionInfo> adiList = new ArrayList<>();
//            adiList = admInstitutionInfoService.queryDescendantsByParentId(
//                    admInstitutionInfo.getId(),
//                    new HashMap<>());
//            model.put("list", adiList);
//        }
//        if (institutionInfo.getType() == InstitutionTypeEnum.COO_INS) {
//            CooInstitutionInfo cooInstitutionInfo = cooInstitutionInfoService
//                    .findByClientId(institutionInfo.getClientId());
//            model.put("clientParentId", cooInstitutionInfo.getId());
//            model.put("institutionId", cooInstitutionInfo.getInstitutionId());
//            List<CooInstitutionInfo> adiList = new ArrayList<>();
//            adiList = cooInstitutionInfoService.queryDescendantsByParentId(
//                    cooInstitutionInfo.getId(),
//                    new HashMap<>());
//            model.put("list", adiList);
//        }
//        
//    }
//    
//    /**
//     * =============================添加修改企业操作===========================
//     */
//    
//    /**
//     * 企业用户转个人用户
//     * @return
//     */
//    @RequestMapping("/transUpdate")
//    public String transUpdate(ModelMap response, String clientId) {
//        response.put("types", InstitutionTypeEnum.values());
//        response.put("clientInfoId", clientId);
//        setLayUiInstitutionInfo(response);
//        return "institution/transInstitutionInfo";
//    }
//    
//    @ResponseBody
//    @RequestMapping("/transToUser")
//    public Map<String, Object> transToUser(String clientInfoId,
//            String capacitys, String business, HttpServletRequest request,
//            InstitutionInfo institutionInfo,
//            InstitutionSummaryInfo institutionSummaryInfo) {
//        //返回数据
//        Map<String, Object> responseMap = new HashMap<>();
//        //主营业务与产能
//        JSONArray jsonCapacitys = JSON.parseArray(capacitys);
//        JSONArray jsonBusiness = JSON.parseArray(business);
//        jsonCapacitys.addAll(jsonBusiness);
//        institutionInfo.setClientId(clientInfoId);
//        institutionInfo.setVcid(ClientWebContextUtils.getClient().getVcid());
//        //添加用户
//        transactionTemplate.execute(new TransactionCallbackWithoutResult() {
//            @Override
//            protected void doInTransactionWithoutResult(
//                    TransactionStatus status) {
//                responseMap.putAll(institutionInfoService
//                        .transClientAndInstitution(institutionInfo));
//                if (StringUtils.isNotBlank(institutionInfo.getClientId())) {
//                    institutionCapacityService.insertCapacitys(institutionInfo,
//                            jsonCapacitys);
//                    InstitutionSummaryInfo insInfo = new InstitutionSummaryInfo();
//                    insInfo.setClientId(institutionInfo.getClientId());
//                    insInfo.setInstitutionNumber(institutionInfo.getId());
//                    insInfo.setLandArea(institutionSummaryInfo.getLandArea());
//                    institutionSummaryInfoService.insert(insInfo);
//                }
//                PersonalInfo per = personalInfoService
//                        .findByClientId(clientInfoId);
//                if (per != null) {
//                    personalInfoService.deleteById(per.getId());
//                    PersonalSummary perSummary = personalSummaryService
//                            .findByPersonalId(per.getId());
//                    if (perSummary != null) {
//                        personalSummaryService.deleteById(perSummary.getId());
//                    }
//                }
//                
//            }
//        });
//        if (!responseMap.containsKey("success")) {
//            responseMap.put("success", true);
//        }
//        return responseMap;
//    }
//    
//    /**
//     * 企业注册
//     * @return
//     */
//    @RequestMapping("/toAdd")
//    public String toAdd(ModelMap response) {
//        response.put("types", InstitutionTypeEnum.values());
//        setLayUiInstitutionInfo(response);
//        return "institution/addInstitutionInfo";
//    }
//    
//    /**
//     * 企业注册
//     * @return
//     */
//    @RequestMapping("/toUpdate")
//    public String toUpdate(ModelMap response, String clientId) {
//        InstitutionInfo institutionInfo = institutionInfoService
//                .findByClientId(clientId);
//        response.put("institutionInfo", institutionInfo);
//        return "institution/updateInstitutionInfo";
//    }
//    
//    /**
//     * 修改企业用户<br/>
//     * <功能详细描述>
//     * @param request
//     * @return [参数说明]
//     *
//     * @return ModelAndView [返回类型说明]
//     * @exception throws [异常类型] [异常说明]
//     * @see [类、类#方法、类#成员]
//     */
//    @ResponseBody
//    @RequestMapping("/update")
//    public Map<String, Object> updateInstitution(HttpServletRequest request,
//            InstitutionSummaryInfo institutionSummaryInfo,
//            InstitutionInfo institutionInfo) {
//        Map<String, Object> responseMap = institutionInfoService
//                .updatePersonalAndSummaryById(institutionInfo,
//                        institutionSummaryInfo);
//        return responseMap;
//    }
//    
//    @ResponseBody
//    @RequestMapping("/add")
//    public Map<String, Object> add(String capacitys, String business,
//            HttpServletRequest request, InstitutionInfo institutionInfo,
//            InstitutionSummaryInfo institutionSummaryInfo) {
//        //返回数据
//        Map<String, Object> responseMap = new HashMap<>();
//        //主营业务与产能
//        JSONArray jsonCapacitys = JSON.parseArray(capacitys);
//        JSONArray jsonBusiness = JSON.parseArray(business);
//        jsonCapacitys.addAll(jsonBusiness);
//        institutionInfo.setVcid(ClientWebContextUtils.getClient().getVcid());
//        //添加用户
//        transactionTemplate.execute(new TransactionCallbackWithoutResult() {
//            @Override
//            protected void doInTransactionWithoutResult(
//                    TransactionStatus status) {
//                responseMap.putAll(institutionInfoService
//                        .insertClientAndInstitution(institutionInfo));
//                if (StringUtils.isNotBlank(institutionInfo.getClientId())) {
//                    institutionCapacityService.insertCapacitys(institutionInfo,
//                            jsonCapacitys);
//                    InstitutionSummaryInfo insInfo = new InstitutionSummaryInfo();
//                    insInfo.setClientId(institutionInfo.getClientId());
//                    insInfo.setInstitutionNumber(institutionInfo.getId());
//                    insInfo.setLandArea(institutionSummaryInfo.getLandArea());
//                    institutionSummaryInfoService.insert(insInfo);
//                }
//            }
//        });
//        if (!responseMap.containsKey("success")) {
//            responseMap.put("success", true);
//        }
//        return responseMap;
//    }
//    
//    /**
//     * 查询企业产能与主营业务
//     * @param institutionId
//     * @param type
//     * @return
//     */
//    @ResponseBody
//    @RequestMapping("/queryCapacityByTypeId")
//    public List<InstitutionCapacity> queryCapacityByTypeId(
//            @RequestParam(value = "institutionId", required = true) String institutionId,
//            @RequestParam(value = "type", required = true) String type) {
//        List<InstitutionCapacity> res = this.institutionCapacityService
//                .queryByTypeId(institutionId, type);
//        return res;
//    }
//    
//    /**
//     * 添加企业产能与主营业务
//     * @param institutionCapacity
//     * @return
//     */
//    @ResponseBody
//    @RequestMapping("/addCapacity")
//    public boolean addCapacity(InstitutionCapacity institutionCapacity) {
//        institutionCapacity
//                .setVcid(ClientWebContextUtils.getClient().getVcid());
//        this.institutionCapacityService.insert(institutionCapacity);
//        return true;
//    }
//    
//    /**
//     * 删除企业产能与主营业务<br/>
//     * <功能详细描述>
//     * @param id
//     * @return [参数说明]
//     */
//    @ResponseBody
//    @RequestMapping("/deleteCapacityById")
//    public boolean deleteCapacityById(@RequestParam(value = "id") String id) {
//        boolean flag = this.institutionCapacityService.deleteById(id);
//        return flag;
//    }
//    
//    /**
//     * =============================个人转企业操作===========================
//     */
//}
