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
//import com.tx.local.report.model.AgricultureLaon;
//import com.tx.local.report.service.AgricultureLaonService;
//import com.tx.local.security.util.ClientWebContextUtils;
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
// * 农业贷款控制层<br/>
// * 
// * @author []
// * @version [版本号]
// * @see [相关类/方法]
// * @since [产品/模块版本]
// */
//@Controller
//@RequestMapping("wap/client/agricultureLaon")
//public class WapClientAgricultureLaonController {
//    
//    //农业贷款业务层
//    @Resource(name = "agricultureLaonService")
//    private AgricultureLaonService agricultureLaonService;
//
//    //客户扩展信息
//    @Resource(name = "clientExtendInfoService")
//    private ClientExtendInfoService clientExtendInfoService;
//
//    /**
//     * 跳转到新增农资贷款填报页面<br/>
//     * <功能详细描述>
//     * @return [参数说明]
//     *
//     * @return String [返回类型说明]
//     * @exception throws [异常类型] [异常说明]
//     * @see [类、类#方法、类#成员]
//     */
//    @RequestMapping("/toQueryPageList")
//    public String toQueryPageList(ModelMap response) {
//        response.put("agricultureLaon", new AgricultureLaon());
//        return "report/agriculturelaon";
//    }
//
//    /**
//     * 查询农资贷款填报实例分页列表<br/>
//     * <功能详细描述>
//     * @return [参数说明]
//     *
//     * @return List<AgricultureLaon> [返回类型说明]
//     * @exception throws [异常类型] [异常说明]
//     * @see [类、类#方法、类#成员]
//     */
//    @ResponseBody
//    @RequestMapping("/queryPagedList")
//    public PagedList<AgricultureLaon> queryPagedList(
//            @RequestParam(value = "pageNumber", required = false, defaultValue = "1") int pageIndex,
//            @RequestParam(value = "pageSize", required = false, defaultValue = "10") int pageSize,
//            @RequestParam MultiValueMap<String, String> request
//    ) {
//        Map<String, Object> params = new HashMap<>();
//        params.put("name", request.getFirst("name"));
//        params.put("clientId", ClientWebContextUtils.getClientId());
//        params.put("operationStatus", true);
//
//        PagedList<AgricultureLaon> resPagedList = this.agricultureLaonService.queryPagedList(
//                params,
//                pageIndex,
//                pageSize
//        );
//        return resPagedList;
//    }
//
//    /**
//     * 新增农资贷款填报<br/>
//     * <功能详细描述>
//     * @param agricultureLaon [参数说明]
//     *
//     * @return void [返回类型说明]
//     * @exception throws [异常类型] [异常说明]
//     * @see [类、类#方法、类#成员]
//     */
//    @ResponseBody
//    @RequestMapping("/add")
//    public AgricultureLaon insert(HttpServletRequest request,
//                                  AgricultureLaon agricultureLaon) {
//
//        ClientExtendInfo clientExtendInfo = clientExtendInfoService
//                .findByClientId(ClientWebContextUtils.getClientId());
//        agricultureLaon.setVcid(clientExtendInfo.getVcid());
//        agricultureLaon.setClientId(clientExtendInfo.getClientId());
//        agricultureLaon.setClientType(clientExtendInfo.getClientType());
//        agricultureLaon.setInstitutionId(clientExtendInfo.getInstitutionId());
//        this.agricultureLaonService.insert(agricultureLaon);
//        return agricultureLaon;
//    }
//
//    /**
//     * 更新农资贷款填报实例<br/>
//     * <功能详细描述>
//     * @param agricultureLaon
//     * @return [参数说明]
//     *
//     * @return boolean [返回类型说明]
//     * @exception throws [异常类型] [异常说明]
//     * @see [类、类#方法、类#成员]
//     */
//    @ResponseBody
//    @RequestMapping("/update")
//    public boolean update(AgricultureLaon agricultureLaon) {
//        boolean flag = this.agricultureLaonService.updateById(agricultureLaon);
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
//    public AgricultureLaon findById(@RequestParam(value = "id") String id) {
//        AgricultureLaon agricultureLaon = this.agricultureLaonService.findById(id);
//        return agricultureLaon;
//    }
//    
//}