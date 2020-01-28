///*
// * 描       述:  <描述>
// * 修  改 人:  
// * 修改时间:
// * <修改描述:>
// */
//package com.tx.local.loanaccount.controller;
//
//import java.io.FileNotFoundException;
//import java.io.IOException;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//import javax.annotation.Resource;
//import javax.servlet.http.HttpServletResponse;
//
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.ModelMap;
//import org.springframework.util.MultiValueMap;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.ResponseBody;
//import org.springframework.web.multipart.MultipartFile;
//
//import com.tx.component.auth.annotation.CheckOperateAuth;
//import com.tx.component.basicdata.model.BankCardTypeEnum;
//import com.tx.component.basicdata.model.IDCardTypeEnum;
//import com.tx.component.operator.service.VirtualCenterService;
//import com.tx.core.paged.model.PagedList;
//import com.tx.local.loanaccount.model.DeductChannelTypeEnum;
//import com.tx.local.loanaccount.model.DeductTask;
//import com.tx.local.loanaccount.model.DeductTaskCurrencyEnum;
//import com.tx.local.loanaccount.model.DeductTaskStatusEnum;
//import com.tx.local.loanaccount.service.DeductTaskService;
//
///**
// * DeductTask显示层逻辑<br/>
// * <功能详细描述>
// * 
// * @author  PengQingyang
// * @version  [版本号, 2013-8-27]
// * @see  [相关类/方法]
// * @since  [产品/模块版本]
// */
//@CheckOperateAuth(key = "deductTask_manage_menuauth", name = "deductTask管理")
//@Controller("deductTaskController")
//@RequestMapping("/deductTask")
//public class DeductTaskController {
//    
//    @Resource(name = "deductTaskService")
//    private DeductTaskService deductTaskService;
//    
//    @Resource(name = "virtualCenterService")
//    private VirtualCenterService virtualCenterService;
//    
//    /**
//      * 跳转到查询DeductTask列表页面<br/>
//      *<功能详细描述>
//      * @return [参数说明]
//      * 
//      * @return String [返回类型说明]
//      * @exception throws [异常类型] [异常说明]
//      * @see [类、类#方法、类#成员]
//     */
//    @RequestMapping("/toQueryDeductTaskList")
//    public String toQueryDeductTaskList(ModelMap response) {
//        response.put("statusList", DeductTaskStatusEnum.values());
//        response.put("clientBankCardTypeList", BankCardTypeEnum.values());
//        response.put("currencyList", DeductTaskCurrencyEnum.values());
//        response.put("deductChannelTypeList", DeductChannelTypeEnum.values());
//        response.put("idCardTypeList", IDCardTypeEnum.values());
//        
//        return "/task/queryDeductTaskList";
//    }
//    
//    /**
//     * 跳转到查询DeductTask分页列表页面<br/>
//     *<功能详细描述>
//     * @return [参数说明]
//     * 
//     * @return String [返回类型说明]
//     * @exception throws [异常类型] [异常说明]
//     * @see [类、类#方法、类#成员]
//    */
//    @RequestMapping("/toQueryDeductTaskPagedList")
//    public String toQueryDeductTaskPagedList(ModelMap response) {
//        response.put("statusList", DeductTaskStatusEnum.values());
//        response.put("clientBankCardTypeList", BankCardTypeEnum.values());
//        response.put("currencyList", DeductTaskCurrencyEnum.values());
//        response.put("deductChannelTypeList", DeductChannelTypeEnum.values());
//        response.put("idCardTypeList", IDCardTypeEnum.values());
//        
//        return "/task/queryDeductTaskPagedList";
//    }
//    
//    /**
//      * 跳转到添加DeductTask页面<br/>
//      *<功能详细描述>
//      * @return [参数说明]
//      * 
//      * @return String [返回类型说明]
//      * @exception throws [异常类型] [异常说明]
//      * @see [类、类#方法、类#成员]
//     */
//    @RequestMapping("/toAddDeductTask")
//    public String toAddDeductTask(ModelMap response) {
//        response.put("deductTask", new DeductTask());
//        
//        response.put("statusList", DeductTaskStatusEnum.values());
//        response.put("clientBankCardTypeList", BankCardTypeEnum.values());
//        response.put("currencyList", DeductTaskCurrencyEnum.values());
//        response.put("deductChannelTypeList", DeductChannelTypeEnum.values());
//        response.put("idCardTypeList", IDCardTypeEnum.values());
//        
//        return "/task/addDeductTask";
//    }
//    
//    /**
//     * 跳转到编辑DeductTask页面
//     *<功能详细描述>
//     * @return [参数说明]
//     * 
//     * @return String [返回类型说明]
//     * @exception throws [异常类型] [异常说明]
//     * @see [类、类#方法、类#成员]
//    */
//    @RequestMapping("/toUpdateDeductTask")
//    public String toUpdateDeductTask(
//            @RequestParam("deductTaskId") String deductTaskId,
//            ModelMap response) {
//        DeductTask resDeductTask = this.deductTaskService
//                .findById(deductTaskId);
//        response.put("deductTask", resDeductTask);
//        
//        response.put("statusList", DeductTaskStatusEnum.values());
//        response.put("clientBankCardTypeList", BankCardTypeEnum.values());
//        response.put("currencyList", DeductTaskCurrencyEnum.values());
//        response.put("deductChannelTypeList", DeductChannelTypeEnum.values());
//        response.put("idCardTypeList", IDCardTypeEnum.values());
//        
//        return "/task/updateDeductTask";
//    }
//    
//    /**
//    * 跳转到编辑DeductTask页面
//    *<功能详细描述>
//    * @return [参数说明]
//    *
//    * @return String [返回类型说明]
//    * @exception throws [异常类型] [异常说明]
//    * @see [类、类#方法、类#成员]
//    */
//    @RequestMapping("/toViewDeductTask")
//    public String toViewByIdDeductTask(
//            @RequestParam("deductTaskId") String deductTaskId,
//            ModelMap response) {
//        DeductTask resDeductTask = this.deductTaskService
//                .findById(deductTaskId);
//        response.put("deductTask", resDeductTask);
//        
//        return "/task/detailDeductTask";
//    }
//    
//    /**
//     * 判断DeductTask:
//     *  taskId
//     *
//     * 是否已经被使用
//     * @param uniqueGetterName
//     * @param excludeDeductTaskId
//     * @return [参数说明]
//     * 
//     * @return boolean [返回类型说明]
//     * @exception throws [异常类型] [异常说明]
//     * @see [类、类#方法、类#成员]
//     */
//    @ResponseBody
//    @RequestMapping("/validateTaskIdIsExist")
//    public Map<String, String> validateTaskIdIsExist(
//            @RequestParam("taskId") String taskId,
//            @RequestParam(value = "id", required = false) String excludeDeductTaskId,
//            @RequestParam MultiValueMap<String, String> request) {
//        
//        Map<String, String> key2valueMap = new HashMap<String, String>();
//        key2valueMap.put("taskId", taskId);
//        
//        boolean flag = this.deductTaskService.isExist(key2valueMap,
//                excludeDeductTaskId);
//        
//        Map<String, String> resMap = new HashMap<String, String>();
//        if (!flag) {
//            resMap.put("ok", "可用的deductTask taskId");
//        } else {
//            resMap.put("error", "已经存在的deductTask taskId");
//        }
//        return resMap;
//    }
//    
//    /**
//     * 查询DeductTask列表<br/>
//     *<功能详细描述>
//     * @return [参数说明]
//     * 
//     * @return List<DeductTask> [返回类型说明]
//     * @exception throws [异常类型] [异常说明]
//     * @see [类、类#方法、类#成员]
//    */
//    @ResponseBody
//    @RequestMapping("/queryList")
//    public List<DeductTask> queryList(
//            @RequestParam(value = "currency", required = false) DeductTaskCurrencyEnum currency,
//            @RequestParam(value = "deductChannelType", required = false) DeductChannelTypeEnum deductChannelType,
//            @RequestParam(value = "status", required = false) DeductTaskStatusEnum status,
//            @RequestParam MultiValueMap<String, String> request) {
//        Map<String, Object> params = new HashMap<>();
//        
//        params.put("clientBankCardNumber",
//                request.getFirst("clientBankCardNumber"));
//        params.put("clientId", request.getFirst("clientId"));
//        params.put("clientName", request.getFirst("clientName"));
//        params.put("loanAccountId", request.getFirst("loanAccountId"));
//        params.put("tradingRecordId", request.getFirst("tradingRecordId"));
//        params.put("idCardNumber", request.getFirst("idCardNumber"));
//        params.put("contractNumber", request.getFirst("contractNumber"));
//        params.put("currency", currency);
//        params.put("deductChannelType", deductChannelType);
//        params.put("status", status);
//        params.put("vcid", request.getFirst("vcid"));
//        
//        List<DeductTask> resList = this.deductTaskService.queryList(params);
//        
//        return resList;
//    }
//    
//    /**
//     * 查询DeductTask分页列表<br/>
//     *<功能详细描述>
//     * @return [参数说明]
//     * 
//     * @return List<DeductTask> [返回类型说明]
//     * @exception throws [异常类型] [异常说明]
//     * @see [类、类#方法、类#成员]
//    */
//    @ResponseBody
//    @RequestMapping("/queryPagedList")
//    public PagedList<DeductTask> queryPagedList(
//            @RequestParam(value = "currency", required = false) DeductTaskCurrencyEnum currency,
//            @RequestParam(value = "deductChannelType", required = false) DeductChannelTypeEnum deductChannelType,
//            @RequestParam(value = "status", required = false) DeductTaskStatusEnum status,
//            @RequestParam(value = "pageNumber", required = false, defaultValue = "1") int pageIndex,
//            @RequestParam(value = "pageSize", required = false, defaultValue = "10") int pageSize,
//            @RequestParam MultiValueMap<String, String> request) {
//        Map<String, Object> params = new HashMap<>();
//        
//        params.put("clientBankCardNumber",
//                request.getFirst("clientBankCardNumber"));
//        params.put("clientId", request.getFirst("clientId"));
//        params.put("clientName", request.getFirst("clientName"));
//        params.put("loanAccountId", request.getFirst("loanAccountId"));
//        params.put("tradingRecordId", request.getFirst("tradingRecordId"));
//        params.put("idCardNumber", request.getFirst("idCardNumber"));
//        params.put("contractNumber", request.getFirst("contractNumber"));
//        params.put("currency", currency);
//        params.put("deductChannelType", deductChannelType);
//        params.put("status", status);
//        params.put("phone", request.getFirst("phone"));
//        params.put("vcid", request.getFirst("vcid"));
//        
//        PagedList<DeductTask> resPagedList = this.deductTaskService
//                .queryPagedList(params, pageIndex, pageSize);
//        return resPagedList;
//    }
//    
//    /**
//     * 添加组织结构页面
//     *<功能详细描述>
//     * @param organization [参数说明]
//     * 
//     * @return void [返回类型说明]
//     * @exception throws [异常类型] [异常说明]
//     * @see [类、类#方法、类#成员]
//    */
//    @CheckOperateAuth(key = "add_deductTask_oauth", name = "增加DeductTask")
//    @RequestMapping("/add")
//    @ResponseBody
//    public boolean add(DeductTask deductTask) {
//        this.deductTaskService.insert(deductTask);
//        return true;
//    }
//    
//    /**
//      * 扣款任务状态的变更<br/>
//      *<功能详细描述>
//      * @param deductTask
//      * @return [参数说明]
//      * 
//      * @return boolean [返回类型说明]
//      * @exception throws [异常类型] [异常说明]
//      * @see [类、类#方法、类#成员]
//     */
//    @CheckOperateAuth(key = "update_deductTask_oauth", name = "编辑DeductTask")
//    @RequestMapping("/updateStatus")
//    @ResponseBody
//    public boolean updateStatus(DeductTask deductTask) {
//        return deductTaskService.updateStatus(deductTask);
//    }
//    
//    /**
//     * 更新组织<br/>
//     *<功能详细描述>
//     * @param deductTask
//     * @return [参数说明]
//     * 
//     * @return boolean [返回类型说明]
//     * @exception throws [异常类型] [异常说明]
//     * @see [类、类#方法、类#成员]
//     */
//    @CheckOperateAuth(key = "update_deductTask_oauth", name = "编辑DeductTask")
//    @RequestMapping("/update")
//    @ResponseBody
//    public boolean update(DeductTask deductTask) {
//        return deductTaskService.update(deductTask)>0;
//    }
//    
//    
//    
//    /**
//      * 删除指定DeductTask<br/> 
//      *<功能详细描述>
//      * @param deductTaskId
//      * @return [参数说明]
//      * 
//      * @return boolean [返回类型说明]
//      * @exception throws [异常类型] [异常说明]
//      * @see [类、类#方法、类#成员]
//     */
//    @CheckOperateAuth(key = "delete_deductTask_oauth", name = "删除DeductTask")
//    @ResponseBody
//    @RequestMapping("/deleteById")
//    public boolean deleteById(
//            @RequestParam(value = "deductTaskId") String deductTaskId) {
//        boolean resFlag = this.deductTaskService.deleteById(deductTaskId);
//        return resFlag;
//    }
//    
//    /**
//     * 
//      *导出扣款报文
//      *<功能详细描述>
//      * @param response
//      * @param vcid 虚中心ID
//      * @param deductChannelType 支付渠道
//      * @throws FileNotFoundException
//      * @throws IOException [参数说明]
//      * 
//      * @return void [返回类型说明]
//      * @exception throws [异常类型] [异常说明]
//      * @see [类、类#方法、类#成员]
//     */
//    @RequestMapping("/exportDeductTask")
//    public void exportDeductTask(HttpServletResponse response, String vcid,
//            DeductChannelTypeEnum deductChannelType)
//            throws FileNotFoundException, IOException {
//        deductTaskService.exportDeductFile(response, vcid, deductChannelType);
//    }
//    
//    /**
//     * 
//      *导入回执报文
//      *<功能详细描述>
//      * @param multipartFile [参数说明]
//      * 
//      * @return void [返回类型说明]
//     * @throws IOException 
//      * @exception throws [异常类型] [异常说明]
//      * @see [类、类#方法、类#成员]
//     */
//    @ResponseBody
//    @RequestMapping("/importReceiptFile")
//    public Map<String, Object> importReceiptFile(
//            @RequestParam(value = "processDefFile") MultipartFile multipartFile,
//            DeductChannelTypeEnum deductChannelTypeEnum) throws IOException {
//        Map<String, Object> map = new HashMap<String, Object>();
//        map.put("message", "操作成功");
//        
//        String fileName = multipartFile.getOriginalFilename();
//        if (fileName.indexOf(".") == -1) {
//            map.put("message", "上传的文件格式不正确");
//            return map;
//        }
//        
//        //获取文件后缀
//        String filePostfix = fileName.substring(fileName.lastIndexOf(".") + 1,
//                fileName.length());
//        if (!filePostfix.toLowerCase().equals("xls")
//                && !filePostfix.toLowerCase().equals("xlsx")
//                && !filePostfix.toLowerCase().equals("csv")) {
//            map.put("message", "上传的文件格式不正确");
//            return map;
//        }
//        
//        deductTaskService.explainFile(multipartFile, deductChannelTypeEnum);
//        
//        return map;
//    }
//    
//}