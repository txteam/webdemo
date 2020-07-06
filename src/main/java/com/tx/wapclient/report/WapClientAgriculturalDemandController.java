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
//import com.tx.local.report.model.*;
//import com.tx.local.report.service.AgriculturalDemandService;
//import com.tx.local.report.service.AgriculturalTypeService;
//import com.tx.local.report.service.AgriculturalUnitService;
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
//import java.util.List;
//import java.util.Map;
//
///**
// * 农资需求填报控制层<br/>
// * 
// * @author []
// * @version [版本号]
// * @see [相关类/方法]
// * @since [产品/模块版本]
// */
//@Controller
//@RequestMapping("wap/client/agriculturalDemand")
//public class WapClientAgriculturalDemandController {
//
//    //农资需求填报业务层
//    @Resource(name = "agriculturalDemandService")
//    private AgriculturalDemandService agriculturalDemandService;
//
//    //客户扩展信息
//    @Resource(name = "clientExtendInfoService")
//    private ClientExtendInfoService clientExtendInfoService;
//
//    //客户扩展信息
//    @Resource(name = "agriculturalTypeService")
//    private AgriculturalTypeService agriculturalTypeService;
//
//    @Resource(name = "agriculturalUnitService")
//    private AgriculturalUnitService agriculturalUnitService;
//
//    /**
//     * 跳转到新增农资需求填报页面<br/>
//     * <功能详细描述>
//     * @return [参数说明]
//     *
//     * @return String [返回类型说明]
//     * @exception throws [异常类型] [异常说明]
//     * @see [类、类#方法、类#成员]
//     */
//    @RequestMapping("/toQueryPageList")
//    public String toQueryPageList(ModelMap response) {
//        response.put("agriculturalDemand", new AgriculturalDemand());
//        this.initAgriculturalDemand(null, response);
//        return "report/agriculturaldemand";
//    }
//
//    /**
//     * 查询农资需求填报实例分页列表<br/>
//     * <功能详细描述>
//     * @return [参数说明]
//     *
//     * @return List<AgriculturalDemand> [返回类型说明]
//     * @exception throws [异常类型] [异常说明]
//     * @see [类、类#方法、类#成员]
//     */
//    @ResponseBody
//    @RequestMapping("/queryPagedList")
//    public PagedList<AgriculturalDemand> queryPagedList(
//            @RequestParam(value = "pageNumber", required = false, defaultValue = "1") int pageIndex,
//            @RequestParam(value = "pageSize", required = false, defaultValue = "10") int pageSize,
//            @RequestParam MultiValueMap<String, String> request) {
//        Map<String, Object> params = new HashMap<>();
//        params.put("name", request.getFirst("name"));
//        params.put("clientId", ClientWebContextUtils.getClientId());
//        params.put("operationStatus", true);
//
//        PagedList<AgriculturalDemand> resPagedList = this.agriculturalDemandService
//                .queryPagedList(params, pageIndex, pageSize);
//        return resPagedList;
//    }
//
//    /**
//     * 新增农资需求填报<br/>
//     * <功能详细描述>
//     * @param agriculturalDemand [参数说明]
//     *
//     * @return void [返回类型说明]
//     * @exception throws [异常类型] [异常说明]
//     * @see [类、类#方法、类#成员]
//     */
//    @ResponseBody
//    @RequestMapping("/add")
//    public AgriculturalDemand insert(HttpServletRequest request,
//                                     AgriculturalDemand agriculturalDemand) {
//
//        ClientExtendInfo clientExtendInfo = clientExtendInfoService
//                .findByClientId(ClientWebContextUtils.getClientId());
//        agriculturalDemand.setVcid(clientExtendInfo.getVcid());
//        agriculturalDemand.setClientId(clientExtendInfo.getClientId());
//        agriculturalDemand.setClientType(clientExtendInfo.getClientType());
//        agriculturalDemand.setInstitutionId(clientExtendInfo.getInstitutionId());
//        this.agriculturalDemandService.insert(agriculturalDemand);
//        return agriculturalDemand;
//    }
//
//    /**
//     * 更新农资需求填报实例<br/>
//     * <功能详细描述>
//     * @param agriculturalDemand
//     * @return [参数说明]
//     *
//     * @return boolean [返回类型说明]
//     * @exception throws [异常类型] [异常说明]
//     * @see [类、类#方法、类#成员]
//     */
//    @ResponseBody
//    @RequestMapping("/update")
//    public boolean update(AgriculturalDemand agriculturalDemand) {
//        boolean flag = this.agriculturalDemandService
//                .updateById(agriculturalDemand);
//        return flag;
//    }
//
//    /**
//     * 根据主键查询农资需求填报实例<br/>
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
//    public AgriculturalDemand findById(@RequestParam(value = "id") String id) {
//        AgriculturalDemand agriculturalDemand = this.agriculturalDemandService
//                .findById(id);
//        return agriculturalDemand;
//    }
//
//    /**
//     * 查询农资单位
//     * @param agriculturalDemand
//     * @param model
//     */
//    public void initAgriculturalDemand(AgriculturalDemand agriculturalDemand,
//                                       ModelMap model) {
//        HashMap map = new HashMap<String, Object>();
//        map.put("agriculturalEnum", AgriculturalEnum.NZ);
//        //农资种类
//        List<AgriculturalType> types = agriculturalTypeService.queryList(true, map);
//        model.put("types", types);
//        //农资种类单位
//        List<AgriculturalUnit> units = agriculturalUnitService.queryList(true, map);
//        model.put("units", units);
//    }
//    
//}