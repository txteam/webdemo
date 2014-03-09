/*
 * 描          述:  <描述>
 * 修  改   人:  PengQingyang
 * 修改时间:  2013-8-27
 * <修改描述:>
 */
package com.tx.component.operator.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tx.component.auth.annotation.CheckOperateAuth;
import com.tx.component.auth.context.AuthContext;
import com.tx.component.mainframe.context.WebContextUtils;
import com.tx.component.operator.OperatorConstants;
import com.tx.component.operator.model.VirtualCenter;
import com.tx.component.operator.service.VirtualCenterService;

/**
 * 虚中心结构管理<br/>
 * <功能详细描述>
 * 
 * @author  PengQingyang
 * @version  [版本号, 2013-8-27]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
@Controller("virtualCenterController")
@RequestMapping("/virtualCenter")
@CheckOperateAuth(key = "virtualCenter_manage",name="虚中心管理")
public class VirtualCenterController {
    
    @Resource(name = "virtualCenterService")
    private VirtualCenterService virtualCenterService;
    
    /**
      * 跳转到虚中心查询页面
      *<功能详细描述> [参数说明]
      * 
      * @return void [返回类型说明]
      * @exception throws [异常类型] [异常说明]
      * @see [类、类#方法、类#成员]
     */
    @RequestMapping("/toQueryVirtualCenterList")
    public String toQueryVirtualCenterList() {
        return "/operator/queryVirtualCenterList";
    }
    
    /**
     * 跳转到添加虚中心页面
     *<功能详细描述>
     * @param virtualCenterId [参数说明]
     * 
     * @return void [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
    */
    @RequestMapping("/toAddVirtualCenter")
    public String toAddVirtualCenter(
            @RequestParam(value = "parentVirtualCenterId", required = false) String parentVirtualCenterId,
            ModelMap response) {
        response.put("virtualCenter", new VirtualCenter());
        
        if (!StringUtils.isEmpty(parentVirtualCenterId)) {
            VirtualCenter parentVirtualCenter = this.virtualCenterService.findVirtualCenterById(parentVirtualCenterId);
            response.put("parentVirtualCenter", parentVirtualCenter);
        }
        
        return "/operator/addVirtualCenter";
    }
    
    /**
      * 跳转到更新虚中心页面
      *<功能详细描述>
      * @return [参数说明]
      * 
      * @return String [返回类型说明]
      * @exception throws [异常类型] [异常说明]
      * @see [类、类#方法、类#成员]
     */
    @RequestMapping("/toUpdateVirtualCenter")
    public String toUpdateVirtualCenter(
            @RequestParam("virtualCenterId") String virtualCenterId,
            ModelMap modelMap) {
        VirtualCenter resVirtualCenter = this.virtualCenterService.findVirtualCenterById(virtualCenterId);
        modelMap.put("virtualCenter", resVirtualCenter);
        modelMap.put("parentVirtualCenterName",
                StringUtils.isEmpty(resVirtualCenter.getParentId()) ? ""
                        : this.virtualCenterService.findVirtualCenterById(resVirtualCenter.getParentId())
                                .getName());
        
        modelMap.put("virtualCenter", resVirtualCenter);
        return "/operator/updateVirtualCenter";
    }
    
    /**
      * 跳转到选择虚中心页面
      *<功能详细描述>
      * @return [参数说明]
      * 
      * @return String [返回类型说明]
      * @exception throws [异常类型] [异常说明]
      * @see [类、类#方法、类#成员]
     */
    @RequestMapping("/toChooseVirtualCenter")
    public String toChooseVirtualCenter(
            @RequestParam(value = "eventName", required = false) String chooseEventName,
            ModelMap responseMap) {
        responseMap.put("eventName", chooseEventName);
        return "/operator/chooseVirtualCenter";
    }
    
    /**
      * 根据虚中心id查询虚中心信息
      *<功能详细描述>
      * @param vcid
      * @return [参数说明]
      * 
      * @return VirtualCenter [返回类型说明]
      * @exception throws [异常类型] [异常说明]
      * @see [类、类#方法、类#成员]
     */
    @RequestMapping("/findVirtualcenterById")
    @ResponseBody
    public VirtualCenter findVirtualcenterById(@RequestParam("vcid")String vcid) {
        VirtualCenter vc = this.virtualCenterService.findVirtualCenterById(vcid);
        
        return vc;
    }
    
    /**
     * 查询所有虚中心的树列表<br/>
     *<功能详细描述>
     * @return [参数说明]
     * 
     * @return List<VirtualCenter> [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
    */
    @RequestMapping("/queryVirtualCenterListByAuth")
    @ResponseBody
    public List<VirtualCenter> queryVirtualCenterListByAuth() {
        List<VirtualCenter> virtualCenterList = null;
        if (AuthContext.getContext()
                .hasAuth(OperatorConstants.AUTHKEY_QUERY_ALL_VC)) {
            virtualCenterList = this.virtualCenterService.listVirtualCenter();
        } else {
            String vcid = WebContextUtils.getCurrentVcid();
            virtualCenterList = this.virtualCenterService.queryCurrentAndChildsVirtualCenterList(vcid);
            virtualCenterList.add(this.virtualCenterService.findVirtualCenterById(vcid));
        }
        
        return virtualCenterList;
    }
    
    /**
      * 查询所有虚中心的树列表<br/>
      *<功能详细描述>
      * @return [参数说明]
      * 
      * @return List<VirtualCenter> [返回类型说明]
      * @exception throws [异常类型] [异常说明]
      * @see [类、类#方法、类#成员]
     */
    @RequestMapping("/queryAllVirtualCenterList")
    @CheckOperateAuth(key = "query_All_VirtualCenter", name = "查询虚中心")
    @ResponseBody
    public List<VirtualCenter> queryAllVirtualCenterList() {
        List<VirtualCenter> virtualCenterList = null;
        virtualCenterList = this.virtualCenterService.listVirtualCenter();
        
        return virtualCenterList;
    }
    
    /**
     * 添加虚中心结构页面
     *<功能详细描述>
     * @param virtualCenterId [参数说明]
     * 
     * @return void [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
    */
    @RequestMapping("/addVirtualCenter")
    @CheckOperateAuth(key = "add_VirtualCenter", name = "新增虚中心")
    @ResponseBody
    public boolean addVirtualCenter(VirtualCenter virtualCenter) {
        this.virtualCenterService.insertVirtualCenter(virtualCenter);
        
        return true;
    }
    
    /**
     * 跳转到添加虚中心结构页面
     *<功能详细描述>
     * @param virtualCenterId [参数说明]
     * 
     * @return void [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
    */
    @ResponseBody
    @RequestMapping("/virtualCenterNameIsExist")
    public Map<String, String> virtualCenterNameIsExist(
            @RequestParam("name") String name,
            @RequestParam(value = "id", required = false) String excludeVirtualCenterId) {
        boolean resFlag = this.virtualCenterService.virtualCenterNameIsExist(name,
                excludeVirtualCenterId);
        Map<String, String> resMap = new HashMap<String, String>();
        if (!resFlag) {
            resMap.put("ok", "");
        } else {
            resMap.put("error", "重复的虚中心名");
        }
        return resMap;
    }
    
    /**
      * 更新虚中心结构信息<br/>
      *<功能详细描述>
      * @param virtualCenter
      * @return [参数说明]
      * 
      * @return boolean [返回类型说明]
      * @exception throws [异常类型] [异常说明]
      * @see [类、类#方法、类#成员]
     */
    @RequestMapping("/updateVirtualCenter")
    @CheckOperateAuth(key = "update_VirtualCenter", name = "更新虚中心")
    @ResponseBody
    public boolean updateVirtualCenter(VirtualCenter virtualCenter) {
        boolean resFlag = this.virtualCenterService.updateById(virtualCenter);
        return resFlag;
    }
    
    /**
     * 检查对应虚中心是否能够被删除
     *<功能详细描述>
     * @param virtualCenterId
     * @return [参数说明]
     * 
     * @return boolean [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
    */
    @ResponseBody
    @RequestMapping("/isDeleteAble")
    public boolean isDeleteAble(
            @RequestParam("virtualCenterId") String virtualCenterId) {
        boolean resFlag = this.virtualCenterService.isDeleteAble(virtualCenterId);
        
        //如果存在尚未停用的下级虚中心则不能被停用
        if (resFlag) {
            return true;
        } else {
            return false;
        }
    }
    
    /**
      * 根据虚中心id删除对应虚中心<br/> 
      *<功能详细描述>
      * @param virtualCenterId
      * @return [参数说明]
      * 
      * @return boolean [返回类型说明]
      * @exception throws [异常类型] [异常说明]
      * @see [类、类#方法、类#成员]
     */
    @ResponseBody
    @CheckOperateAuth(key = "delete_VirtualCenter", name = "删除虚中心", configAble = false)
    @RequestMapping("/deleteVirtualCenterById")
    public boolean deleteVirtualCenterById(
            @RequestParam("virtualCenterId") String virtualCenterId) {
        boolean resFlag = this.virtualCenterService.deleteById(virtualCenterId);
        
        return resFlag;
    }
}
