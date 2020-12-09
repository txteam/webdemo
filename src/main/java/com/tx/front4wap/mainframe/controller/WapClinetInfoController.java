package com.tx.front4wap.mainframe.controller;
//package com.tx.wapclient.mainframe.controller;
//
//import com.tx.local.clientinfo.model.ClientTypeEnum;
//import com.tx.local.institution.model.InstitutionCapacity;
//import com.tx.local.institution.model.InstitutionInfo;
//import com.tx.local.institution.model.InstitutionSummaryInfo;
//import com.tx.local.institution.service.InstitutionCapacityService;
//import com.tx.local.institution.service.InstitutionInfoService;
//import com.tx.local.personal.model.PersonalInfo;
//import com.tx.local.personal.model.PersonalSummary;
//import com.tx.local.personal.service.PersonalInfoService;
//import com.tx.local.personal.service.PersonalSummaryService;
//import com.tx.local.security.util.ClientWebContextUtils;
//import com.tx.local.security.util.WebContextUtils;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.ModelMap;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.ResponseBody;
//
//import javax.annotation.Resource;
//import javax.servlet.http.HttpServletRequest;
//import java.util.List;
//import java.util.Map;
//
///**
// * wap端个人填报相关页面<br/>
// * <功能详细描述>
// * 
// * @author  Administrator
// * @version  [版本号, 2020年3月30日]
// * @see  [相关类/方法]
// * @since  [产品/模块版本]
// */
//@Controller
//@RequestMapping("/wap/client/info")
//public class WapClinetInfoController {
//
//    //社属机构基本信息业务层
//    @Resource(name = "institutionInfoService")
//    private InstitutionInfoService institutionInfoService;
//
//    //机构产能业务层
//    @Resource(name = "institutionCapacityService")
//    private InstitutionCapacityService institutionCapacityService;
//
//    @Resource(name = "personalInfoService")
//    private PersonalInfoService personalInfoService;
//
//    @Resource(name = "personalSummaryService")
//    private PersonalSummaryService personalSummaryService;
//
//    /**
//     * 请求编码forbind<br/>
//     * <功能详细描述>
//     * @param request
//     * @return [参数说明]
//     *
//     * @return ModelAndView [返回类型说明]
//     * @exception throws [异常类型] [异常说明]
//     * @see [类、类#方法、类#成员]
//     */
//    @RequestMapping("/toClientInfoPage")
//    public String toClientInfoPage(HttpServletRequest request, ModelMap responsed) {
//        //个人
//        if(ClientWebContextUtils.getClientType() == ClientTypeEnum.PERSONAL){
//            PersonalInfo personalInfo = personalInfoService.findByClientId(ClientWebContextUtils.getClientId());
//            responsed.put("personalInfo", personalInfo);
//            return "userinfo/personal";
//        }
//        //企业与个体商户
//        if(ClientWebContextUtils.getClientType() == ClientTypeEnum.ENTERPRISE
//                || ClientWebContextUtils.getClientType() == ClientTypeEnum.SELF_EMPLOYED){
//            InstitutionInfo institutionInfo = institutionInfoService
//                    .findByClientId(ClientWebContextUtils.getClientId());
//            responsed.put("institutionInfo", institutionInfo);
//            return "userinfo/enterprise";
//        }
//        //如果用户完成注册，则跳转到登陆页
//        return "report/agriculturaldemand";
//    }
//
//    /**
//     * 修改wap端个人用户<br/>
//     * <功能详细描述>
//     * @param request
//     * @return [参数说明]
//     *
//     * @return ModelAndView [返回类型说明]
//     * @exception throws [异常类型] [异常说明]
//     * @see [类、类#方法、类#成员]
//     */
//    @ResponseBody
//    @RequestMapping("/updatePersonal")
//    public Map<String,Object> updatePersonal(PersonalSummary personalSummary, HttpServletRequest request,
//                                             PersonalInfo personalInfo){
//      Map<String,Object> responseMap = personalInfoService
//              .updatePersonalAndSummaryById(personalInfo,personalSummary);
//        return  responseMap;
//    }
//
//    /**
//     * 修改wap端企业用户<br/>
//     * <功能详细描述>
//     * @param request
//     * @return [参数说明]
//     *
//     * @return ModelAndView [返回类型说明]
//     * @exception throws [异常类型] [异常说明]
//     * @see [类、类#方法、类#成员]
//     */
//    @ResponseBody
//    @RequestMapping("/updateInstitution")
//    public Map<String,Object> updateInstitution(HttpServletRequest request,
//              InstitutionSummaryInfo institutionSummaryInfo, InstitutionInfo institutionInfo){
//        Map<String,Object> responseMap = institutionInfoService
//                .updatePersonalAndSummaryById(institutionInfo, institutionSummaryInfo);
//        return  responseMap;
//    }
//
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
//            @RequestParam(value = "institutionId",required=true) String institutionId,
//            @RequestParam(value = "type",required=true) String type){
//        List<InstitutionCapacity>  res =
//                this.institutionCapacityService.queryByTypeId(institutionId, type);
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
//    public boolean addCapacity(InstitutionCapacity institutionCapacity){
//        institutionCapacity.setVcid(ClientWebContextUtils.getClient().getVcid());
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
//
//
//}
