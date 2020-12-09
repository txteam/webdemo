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
//import com.tx.local.report.model.AgriculturalEnum;
//import com.tx.local.report.model.AgriculturalType;
//import com.tx.local.report.model.AgriculturalUnit;
//import com.tx.local.report.model.AgricultureProducts;
//import com.tx.local.report.service.AgriculturalTypeService;
//import com.tx.local.report.service.AgriculturalUnitService;
//import com.tx.local.report.service.AgricultureProductsService;
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
//
///**
// * 滞销农产品填报控制层<br/>
// * 
// * @author []
// * @version [版本号]
// * @see [相关类/方法]
// * @since [产品/模块版本]
// */
//@Controller
//@RequestMapping("/wap/client/agricultureProducts")
//public class WapClientAgricultureProductsController {
//    
//    //滞销农产品填报业务层
//    @Resource(name = "agricultureProductsService")
//    private AgricultureProductsService agricultureProductsService;
//
//    //客户扩展信息
//    @Resource(name = "clientExtendInfoService")
//    private ClientExtendInfoService clientExtendInfoService;
//
//    @Resource(name = "agriculturalTypeService")
//    private AgriculturalTypeService agriculturalTypeService;
//
//    @Resource(name = "agriculturalUnitService")
//    private AgriculturalUnitService agriculturalUnitService;
//
//
//    /**
//     * 跳转到新增滞销农产品填报页面<br/>
//     * <功能详细描述>
//     * @return [参数说明]
//     *
//     * @return String [返回类型说明]
//     * @exception throws [异常类型] [异常说明]
//     * @see [类、类#方法、类#成员]
//     */
//    @RequestMapping("/toQueryPageList")
//    public String toQueryPageList(ModelMap response) {
//        response.put("agricultureProducts", new AgricultureProducts());
//        this.initAgricultureProducts(null,response);
//        return "report/agricultureproducts";
//    }
//
//    /**
//     * 查询滞销农产品填报实例分页列表<br/>
//     * <功能详细描述>
//     * @return [参数说明]
//     *
//     * @return List<AgricultureProducts> [返回类型说明]
//     * @exception throws [异常类型] [异常说明]
//     * @see [类、类#方法、类#成员]
//     */
//    @ResponseBody
//    @RequestMapping("/queryPagedList")
//    public PagedList<AgricultureProducts> queryPagedList(
//            @RequestParam(value = "pageNumber", required = false, defaultValue = "1") int pageIndex,
//            @RequestParam(value = "pageSize", required = false, defaultValue = "10") int pageSize,
//            @RequestParam MultiValueMap<String, String> request
//    ) {
//        Map<String, Object> params = new HashMap<>();
//        params.put("name", request.getFirst("name"));
//        params.put("clientId", ClientWebContextUtils.getClientId());
//        params.put("operationStatus", true);
//
//        PagedList<AgricultureProducts> resPagedList = this.agricultureProductsService.queryPagedList(
//                params,
//                pageIndex,
//                pageSize
//        );
//        return resPagedList;
//    }
//
//
//    /**
//     * 新增滞销农产品填报<br/>
//     * <功能详细描述>
//     * @param agricultureProducts [参数说明]
//     *
//     * @return void [返回类型说明]
//     * @exception throws [异常类型] [异常说明]
//     * @see [类、类#方法、类#成员]
//     */
//    @ResponseBody
//    @RequestMapping("/add")
//    public AgricultureProducts insert(HttpServletRequest request,
//                                      AgricultureProducts agricultureProducts) {
//        ClientExtendInfo clientExtendInfo = clientExtendInfoService
//                .findByClientId(ClientWebContextUtils.getClientId());
//        agricultureProducts.setVcid(clientExtendInfo.getVcid());
//        agricultureProducts.setClientId(clientExtendInfo.getClientId());
//        agricultureProducts.setClientType(clientExtendInfo.getClientType());
//        agricultureProducts.setInstitutionId(clientExtendInfo.getInstitutionId());
//        this.agricultureProductsService.insert(agricultureProducts);
//        return agricultureProducts;
//    }
//    /**
//     * 更新滞销农产品填报实例<br/>
//     * <功能详细描述>
//     * @param agricultureProducts
//     * @return [参数说明]
//     *
//     * @return boolean [返回类型说明]
//     * @exception throws [异常类型] [异常说明]
//     * @see [类、类#方法、类#成员]
//     */
//    @ResponseBody
//    @RequestMapping("/update")
//    public boolean update(HttpServletRequest request,
//                          AgricultureProducts agricultureProducts) {
//        boolean flag = this.agricultureProductsService.updateById(agricultureProducts);
//        return flag;
//    }
//
//    /**
//     * 根据主键查询滞销农产品填报实例<br/>
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
//    public AgricultureProducts findById(@RequestParam(value = "id") String id) {
//        AgricultureProducts agricultureProducts = this.agricultureProductsService.findById(id);
//        return agricultureProducts;
//    }
//
//    /**
//     * 查询农产品单位
//     * @param agricultureProducts
//     * @param model
//     */
//    public void initAgricultureProducts(AgricultureProducts agricultureProducts, ModelMap model){
//        HashMap map = new HashMap<String, Object>();
//        map.put("agriculturalEnum", AgriculturalEnum.NCP);
//        //农资种类
//        List<AgriculturalType> types = agriculturalTypeService.queryList(true,map);
//        model.put("types",types);
//        //农资种类
//        List<AgriculturalUnit> units = agriculturalUnitService.queryList(true,map);
//        model.put("units",units);
//    }
//    
//}