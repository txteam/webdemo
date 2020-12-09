package com.tx.front4client.personal.controller;
//package com.tx.client.personalInfo.controller;
//
//import com.tx.core.paged.model.PagedList;
//import com.tx.local.adminstitution.model.AdmInstitutionInfo;
//import com.tx.local.adminstitution.service.AdmInstitutionInfoService;
//import com.tx.local.basicdata.model.SexEnum;
//import com.tx.local.clientinfo.model.ClientExtendInfo;
//import com.tx.local.clientinfo.service.ClientExtendInfoService;
//import com.tx.local.cooinstitution.model.CooInstitutionInfo;
//import com.tx.local.cooinstitution.service.CooInstitutionInfoService;
//import com.tx.local.institution.model.InstitutionInfo;
//import com.tx.local.institution.model.InstitutionTypeEnum;
//import com.tx.local.institution.service.InstitutionInfoService;
//import com.tx.local.personal.model.PersonalInfo;
//import com.tx.local.personal.model.PersonalSummary;
//import com.tx.local.personal.model.PersonalTypeEnum;
//import com.tx.local.personal.service.PersonalInfoService;
//import com.tx.local.personal.service.PersonalSummaryService;
//import com.tx.local.security.util.ClientWebContextUtils;
//import com.tx.local.security.util.WebContextUtils;
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
//import javax.annotation.Resource;
//import javax.servlet.http.HttpServletRequest;
//import java.math.BigDecimal;
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//import java.util.stream.Collectors;
//
///**
// * PersonalInfo控制层<br/>
// *
// * @author []
// * @version [版本号]
// * @see [相关类/方法]
// * @since [产品/模块版本]
// */
//@Controller("client.personalInfoController")
//@RequestMapping("/client/personalInfo")
//public class PersonalInfoController {
//
//    @Resource(name = "personalInfoService")
//    private PersonalInfoService personalInfoService;
//
//    @Resource(name = "personalSummaryService")
//    private PersonalSummaryService personalSummaryService;
//
//    @Resource(name = "clientExtendInfoService")
//    private ClientExtendInfoService clientExtendInfoService;
//
//    @Resource(name = "institutionInfoService")
//    private InstitutionInfoService institutionInfoService;
//
//    @Resource(name = "admInstitutionInfoService")
//    private AdmInstitutionInfoService admInstitutionInfoService;
//
//    @Resource(name = "cooInstitutionInfoService")
//    private CooInstitutionInfoService cooInstitutionInfoService;
//
//    @Resource
//    private TransactionTemplate transactionTemplate;
//
//
//    /**
//     * 跳转到查询PersonalInfo分页列表页面
//     * @param response
//     * @return
//     */
//    @RequestMapping("/toQueryPagedList")
//    public String toQueryPagedList(ModelMap response) {
//        response.put("sexs", SexEnum.values());
//        response.put("types", PersonalTypeEnum.values());
//        setLayUiInstitutionInfo(response);
//        return "personal/queryPersonalPagedList";
//    }
//
//
//
//
//    @RequestMapping("/toQueryClientPage")
//    public String toQueryClientPage(
//            @RequestParam("id") String id,
//            ModelMap response){
//        PersonalInfo personalInfo = personalInfoService.findByClientId(id);
//        response.put("personalInfo",personalInfo);
//        return "personal/queryPersonalInfoDetail";
//    }
//
//    /**
//     * 查询PersonalInfo实例分页列表
//     * @param pageIndex
//     * @param pageSize
//     * @param request
//     * @return
//     */
//    @ResponseBody
//    @RequestMapping("/queryPagedList")
//    public PagedList<PersonalInfo> queryPagedList(
//            @RequestParam(value = "pageNumber", required = false, defaultValue = "1") int pageIndex,
//            @RequestParam(value = "pageSize", required = false, defaultValue = "10") int pageSize,
//            @RequestParam MultiValueMap<String, String> request
//    ) {
//        Map<String, Object> params = new HashMap<>();
//        params.put("searchValue", request.getFirst("searchValue"));
//        params.put("linkMobileNumber", request.getFirst("linkMobileNumber"));
//        params.put("clientId", request.getFirst("clientId"));
//        /*
//        String institutionId = request.getFirst("institutionId");
//        if(StringUtils.isNotBlank(institutionId)){
//            params.put("institutionId", institutionId);
//        }
//        */
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
//            List<ClientExtendInfo> clientList =
//                    clientExtendInfoService.getClientExtendListById(institutionId);
//            params.put("clients", clientList);
//        }
//
//        PagedList<PersonalInfo> resPagedList = this.personalInfoService.queryPagedList(
//                params,
//                pageIndex,
//                pageSize
//        );
//        List<InstitutionInfo> addsList = this.institutionInfoService.queryList(new HashMap<>());
//        Map<String, InstitutionInfo> mappedAdds = addsList.stream().collect(Collectors.toMap(InstitutionInfo::getId, (p) -> p));
//
//        List<ClientExtendInfo> clisList = this.clientExtendInfoService.queryList(new HashMap<>());
//        Map<String, ClientExtendInfo> mappedClis = clisList.stream().collect(Collectors.toMap(ClientExtendInfo::getClientId, (p) -> p));
//
//        List<PersonalInfo>  resList = resPagedList.getList();
//        for (PersonalInfo item: resList) {
//            if(mappedClis.containsKey(item.getClientId())){
//                if(mappedAdds.containsKey(mappedClis.get(item.getClientId()).getInstitutionId())){
//                    item.setInstitutionInfo(mappedAdds.get(mappedClis.get(item.getClientId()).getInstitutionId()));
//                }
//            }
//        }
//        resPagedList.setList(resList);
//
//        return resPagedList;
//    }
//
//    @ResponseBody
//    @RequestMapping("/statisticData")
//    public Map<String,Object> statisticData(@RequestParam Map<String, Object> params) {
//        Map<String,Object> responseMap = new HashMap<>();
//        Integer rk = 0;
//        Integer count = 0;
//        BigDecimal mj = new BigDecimal(0);
//        Map<String, Object> result = new HashMap<>();
//        /*
//        String institutionId = (String) params.get("institutionId");
//        if(StringUtils.isNotBlank(institutionId)){
//            params.put("institutionId", institutionId);
//        }
//       */
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
//            List<ClientExtendInfo> clientList =
//                    clientExtendInfoService.getClientExtendListById(institutionId);
//            result.put("clients", clientList);
//        }
//
//        result.put("operationStatus",true);
//        List<PersonalInfo> list = personalInfoService.queryList(result);
//        for(PersonalInfo item: list){
//            count ++ ;
//            rk = item.getPersonalSummary().getFamilyCount() + rk;
//            mj = mj.add(item.getPersonalSummary().getLandArea());
//        }
//        responseMap.put("rk", rk);
//        responseMap.put("mj", mj);
//        responseMap.put("count", count);
//        return responseMap;
//    }
//
//    @ResponseBody
//    @RequestMapping("/deleteLogicById")
//    public boolean deleteLogicById(@RequestParam(value = "id") String id) {
//        boolean flag = this.personalInfoService.deleteLogicById(id);
//        return flag;
//    }
//
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
//            adiList = admInstitutionInfoService.queryDescendantsByParentId(admInstitutionInfo.getId(), new HashMap<>());
//            model.put("list", adiList);
//        }
//        if (institutionInfo.getType() == InstitutionTypeEnum.COO_INS) {
//            CooInstitutionInfo cooInstitutionInfo = cooInstitutionInfoService
//                    .findByClientId(institutionInfo.getClientId());
//            model.put("clientParentId", cooInstitutionInfo.getId());
//            model.put("institutionId", cooInstitutionInfo.getInstitutionId());
//            List<CooInstitutionInfo> adiList = new ArrayList<>();
//            adiList =  cooInstitutionInfoService.queryDescendantsByParentId(cooInstitutionInfo.getId(), new HashMap<>());
//            model.put("list", adiList);
//        }
//
//    }
//
//    /**
//     *  ======================个人操作 =============================
//     */
//
//    /**
//     * 企业用户转个人用户
//     * @return
//     */
//    @RequestMapping("/transUpdate")
//    public String transUpdate(ModelMap response,String clientId) {
//        response.put("types", PersonalTypeEnum.values());
//        response.put("clientId", clientId);
//        setLayUiInstitutionInfo(response);
//        return "personal/transPersonalInfo";
//    }
//    /**
//     * 添加个人用户
//     * @param personalSummary
//     * @param personalInfo
//     * @param request
//     * @return
//     */
//    @ResponseBody
//    @RequestMapping("/transToUser")
//    public Map<String,Object> transToUser(
//            PersonalSummary personalSummary, PersonalInfo personalInfo,
//            HttpServletRequest request) {
//        //返回数据
//        Map<String,Object> responseMap = new HashMap<>();
//        //如果用户从微信端获取的UUID
//        personalInfo.setVcid(ClientWebContextUtils.getClient().getVcid());
//        personalInfo.setFristName(personalInfo.getName().substring(0,1));
//        personalInfo.setLastName(personalInfo.getName().substring(1, personalInfo.getName().length()));
//
//
//        if(!responseMap.containsKey("success")){
//            responseMap.put("success",true);
//        }
//        return responseMap;
//    }
//
//
//    /**
//     * 个人注册
//     * @return
//     */
//    @RequestMapping("/toAdd")
//    public String toAdd(ModelMap response) {
//        response.put("types", PersonalTypeEnum.values());
//        setLayUiInstitutionInfo(response);
//        return "personal/addPersonalInfo";
//    }
//
//    /**
//     * 个人注册
//     * @return
//     */
//    @RequestMapping("/toUpdate")
//    public String toUpdate(ModelMap response, String clientId) {
//        PersonalInfo personalInfo = personalInfoService.findByClientId(clientId);
//        response.put("personalInfo", personalInfo);
//        return "personal/updatePersonalInfo";
//    }
//
//    /**
//     * 添加个人用户
//     * @param personalSummary
//     * @param personalInfo
//     * @param request
//     * @return
//     */
//    @ResponseBody
//    @RequestMapping("/add")
//    public Map<String,Object> add(
//            PersonalSummary personalSummary, PersonalInfo personalInfo,
//            HttpServletRequest request) {
//        //返回数据
//        Map<String,Object> responseMap = new HashMap<>();
//        //如果用户从微信端获取的UUID
//        personalInfo.setVcid(ClientWebContextUtils.getClient().getVcid());
//        personalInfo.setFristName(personalInfo.getName().substring(0,1));
//        personalInfo.setLastName(personalInfo.getName().substring(1, personalInfo.getName().length()));
//        //添加用户
//        transactionTemplate.execute(new TransactionCallbackWithoutResult() {
//            @Override
//            protected void doInTransactionWithoutResult(
//                    TransactionStatus status) {
//                responseMap.putAll(personalInfoService.insertClientAndPersonal(personalInfo));
//                if(StringUtils.isNotBlank(personalInfo.getClientId())){
//                    personalSummary.setVcid(personalInfo.getVcid());
//                    personalSummary.setPersonalId(personalInfo.getId());
//                    personalSummaryService.insert(personalSummary);
//                }
//            }
//        });
//        if(!responseMap.containsKey("success")){
//            responseMap.put("success",true);
//        }
//        return responseMap;
//    }
//
//    /**
//     * 修改个人用户<br/>
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
//    public Map<String,Object> updatePersonal(PersonalSummary personalSummary, HttpServletRequest request,
//                                             PersonalInfo personalInfo){
//        Map<String,Object> responseMap = personalInfoService
//                .updatePersonalAndSummaryById(personalInfo,personalSummary);
//        return  responseMap;
//    }
//}
