package com.tx.front4wap.report.controller;
///*
// * 描       述:  <描述>
// * 修  改 人:  
// * 修改时间:
// * <修改描述:>
// */
//package com.tx.wapclient.report;
//
//import com.tx.core.paged.model.PagedList;
//import com.tx.local.clientinfo.model.ClientExtendInfo;
//import com.tx.local.clientinfo.service.ClientExtendInfoService;
//import com.tx.local.report.model.AgriculturalQuestionsInfo;
//import com.tx.local.report.model.AgricultureQuestions;
//import com.tx.local.report.service.AgriculturalQuestionsInfoService;
//import com.tx.local.report.service.AgricultureQuestionsService;
//import com.tx.local.security.util.ClientWebContextUtils;
//import com.tx.local.security.util.WebContextUtils;
//import org.apache.commons.lang3.StringUtils;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.ModelMap;
//import org.springframework.util.MultiValueMap;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.ResponseBody;
//
//import javax.annotation.Resource;
//import javax.servlet.http.HttpServletRequest;
//import java.util.HashMap;
//import java.util.Map;
//
///**
// * 农业问答控制层<br/>
// * 
// * @author []
// * @version [版本号]
// * @see [相关类/方法]
// * @since [产品/模块版本]
// */
//@Controller
//@RequestMapping("wap/client/agricultureQuestions")
//public class WapClientAgricultureQuestionsController {
//
//    //问题信息业务层
//    @Resource(name = "agriculturalQuestionsInfoService")
//    private AgriculturalQuestionsInfoService agriculturalQuestionsInfoService;
//    
//    //农业问答业务层
//    @Resource(name = "agricultureQuestionsService")
//    private AgricultureQuestionsService agricultureQuestionsService;
//
//    //客户扩展信息
//    @Resource(name = "clientExtendInfoService")
//    private ClientExtendInfoService clientExtendInfoService;
//
//    /**
//     * 跳转到新增农业问答填报页面<br/>
//     * <功能详细描述>
//     * @return [参数说明]
//     *
//     * @return String [返回类型说明]
//     * @exception throws [异常类型] [异常说明]
//     * @see [类、类#方法、类#成员]
//     */
//    @RequestMapping("/toQueryPageList")
//    public String toQueryPageList(ModelMap response) {
//        response.put("agricultureQuestions", new AgricultureQuestions());
//        String _vcid = "";
//        if(StringUtils.isNotBlank(WebContextUtils.getVcid())){
//            _vcid = WebContextUtils.getVcid();
//        }
//        if(ClientWebContextUtils.getClient() != null){
//            _vcid = ClientWebContextUtils.getClient().getVcid();
//        }
//
//        AgriculturalQuestionsInfo agriculturalQuestionsInfo =
//                agriculturalQuestionsInfoService.findByVcid(_vcid);
//        if(agriculturalQuestionsInfo != null) {
//            response.put("phoneNumber", agriculturalQuestionsInfo.getTelephoneNumber());
//            response.put("name", agriculturalQuestionsInfo.getName());
//        }else{
//            response.put("phoneNumber", "客户机构服务电话待添加");
//            response.put("name", "客户机构待添加");
//        }
//        return "report/agriculturequestions";
//    }
//
//    /**
//     * 查询农业问答填报实例分页列表<br/>
//     * <功能详细描述>
//     * @return [参数说明]
//     *
//     * @return List<AgricultureQuestions> [返回类型说明]
//     * @exception throws [异常类型] [异常说明]
//     * @see [类、类#方法、类#成员]
//     */
//    @ResponseBody
//    @RequestMapping("/queryPagedList")
//    public PagedList<AgricultureQuestions> queryPagedList(
//            @RequestParam(value = "pageNumber", required = false, defaultValue = "1") int pageIndex,
//            @RequestParam(value = "pageSize", required = false, defaultValue = "10") int pageSize,
//            @RequestParam MultiValueMap<String, String> request
//    ) {
//        Map<String, Object> params = new HashMap<>();
//        params.put("name", request.getFirst("name"));
//        params.put("clientId", ClientWebContextUtils.getClientId());
//        params.put("operationStatus", true);
//
//        PagedList<AgricultureQuestions> resPagedList = this.agricultureQuestionsService.queryPagedList(
//                params,
//                pageIndex,
//                pageSize
//        );
//        return resPagedList;
//    }
//
//    /**
//     * 新增农业问答填报<br/>
//     * <功能详细描述>
//     * @param agricultureQuestions [参数说明]
//     *
//     * @return void [返回类型说明]
//     * @exception throws [异常类型] [异常说明]
//     * @see [类、类#方法、类#成员]
//     */
//    @ResponseBody
//    @RequestMapping("/add")
//    public AgricultureQuestions insert(HttpServletRequest request,
//                                       AgricultureQuestions agricultureQuestions) {
//
//        ClientExtendInfo clientExtendInfo = clientExtendInfoService
//                .findByClientId(ClientWebContextUtils.getClientId());
//        agricultureQuestions.setVcid(clientExtendInfo.getVcid());
//        agricultureQuestions.setClientId(clientExtendInfo.getClientId());
//        agricultureQuestions.setClientType(clientExtendInfo.getClientType());
//        agricultureQuestions.setInstitutionId(clientExtendInfo.getInstitutionId());
//        this.agricultureQuestionsService.insert(agricultureQuestions);
//        return agricultureQuestions;
//    }
//
//    /**
//     * 更新农业问答填报实例<br/>
//     * <功能详细描述>
//     * @param agricultureQuestions
//     * @return [参数说明]
//     *
//     * @return boolean [返回类型说明]
//     * @exception throws [异常类型] [异常说明]
//     * @see [类、类#方法、类#成员]
//     */
//    @ResponseBody
//    @RequestMapping("/update")
//    public boolean update(AgricultureQuestions agricultureQuestions) {
//        boolean flag = this.agricultureQuestionsService.updateById(agricultureQuestions);
//        return flag;
//    }
//    /**
//     * 根据主键查询农资贷款填报实例<br/>
//     * <功能详细描述>
//     * @param id
//     * @return [参数说明]
//     *
//     * @return boolean [返回类型说明]
//     * @exception throws [异常类型] [异常说明]
//     * @see [类、类#方法、类#成员]
//     */
//    @ResponseBody
//    @RequestMapping("/findById")
//    public AgricultureQuestions findById(@RequestParam(value = "id") String id) {
//        AgricultureQuestions agricultureQuestions = this.agricultureQuestionsService.findById(id);
//        return agricultureQuestions;
//    }
//    
//}