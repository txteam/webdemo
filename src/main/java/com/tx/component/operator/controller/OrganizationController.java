/*
O * 描          述:  <描述>
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
import com.tx.component.mainframe.treeview.TreeNode;
import com.tx.component.operator.model.ChiefTypeEnum;
import com.tx.component.operator.model.Organization;
import com.tx.component.operator.model.OrganizationTypeEnum;
import com.tx.component.operator.service.OrganizationService;
import com.tx.component.operator.service.VirtualCenterService;

/**
 * 组织结构管理<br/>
 * <功能详细描述>
 * 
 * @author  PengQingyang
 * @version  [版本号, 2013-8-27]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
@Controller("newOrganizationController")
@RequestMapping("/organization")
@CheckOperateAuth(key = "organization_manage", parentKey = "operator_config_center", description = "组织结构管理", name = "组织结构管理")
public class OrganizationController {
    
    @Resource(name = "organizationService")
    private OrganizationService organizationService;
    
    @Resource(name = "virtualCenterService")
    private VirtualCenterService virtualCenterService;
    
    /**
      * 跳转到组织查询页面
      *<功能详细描述> [参数说明]
      * 
      * @return void [返回类型说明]
      * @exception throws [异常类型] [异常说明]
      * @see [类、类#方法、类#成员]
     */
    @RequestMapping("/toQueryOrganizationList")
    public String toQueryOrganizationList() {
        return "/operator/queryOrganizationList";
    }
    
    /**
     * 跳转到添加组织页面
     *<功能详细描述>
     * @param organization [参数说明]
     * 
     * @return void [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
    */
    @RequestMapping("/toAddOrganization")
    public String toAddOrganization(
            @RequestParam(value = "parentOrganizationId", required = false) String parentOrganizationId,
            ModelMap response) {
        response.put("organization", new Organization());
        response.put("chiefTypes", ChiefTypeEnum.values());
        response.put("organizationTypes", OrganizationTypeEnum.values());
        if (!StringUtils.isEmpty(parentOrganizationId)) {
            Organization parentOrganization = this.organizationService.findOrganizationById(parentOrganizationId);
            response.put("parentOrganization", parentOrganization);
        }
        
        return "/operator/addOrganization";
    }
    
    /**
     * 查询所有组织的树列表<br/>
     *<功能详细描述>
     * @return [参数说明]
     * 
     * @return List<Organization> [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
    */
    @CheckOperateAuth(key = "query_organization", name = "查询组织")
    @RequestMapping("/queryOrganizationListIncludeInvalid")
    @ResponseBody
    public List<Organization> queryOrganizationListIncludeInvalid(
            @RequestParam(value = "virtualCenterId", required = false) String virtualCenterId) {
        List<Organization> orgList = this.organizationService.queryOrganizationListIncludeInvalid(virtualCenterId);
        
        return orgList;
    }
    
    /**
      * 跳转到更新组织页面
      *<功能详细描述>
      * @return [参数说明]
      * 
      * @return String [返回类型说明]
      * @exception throws [异常类型] [异常说明]
      * @see [类、类#方法、类#成员]
     */
    @RequestMapping("/toUpdateOrganization")
    public String toUpdateOrganization(
            @RequestParam("organizationId") String organizationId,
            ModelMap modelMap) {
        Organization resOrganization = this.organizationService.findOrganizationById(organizationId);
        modelMap.put("organization", resOrganization);
        modelMap.put("chiefTypes", ChiefTypeEnum.values());
        modelMap.put("organizationTypes", OrganizationTypeEnum.values());
        modelMap.put("parentOrganizationName",
                StringUtils.isEmpty(resOrganization.getParentId()) ? ""
                        : this.organizationService.findOrganizationById(resOrganization.getParentId())
                                .getName());
        modelMap.put("virtualCenter",
                this.virtualCenterService.findVirtualCenterById(resOrganization.getVcid()));
        
        modelMap.put("organization", resOrganization);
        return "/operator/updateOrganization";
    }
    
    /**
      * 跳转到选择组织页面
      *<功能详细描述>
      * @return [参数说明]
      * 
      * @return String [返回类型说明]
      * @exception throws [异常类型] [异常说明]
      * @see [类、类#方法、类#成员]
     */
    @RequestMapping("/toChooseOrgnization")
    public String toChooseOrgnization(
            @RequestParam(value = "eventName", required = false) String chooseEventName,
            ModelMap responseMap) {
        responseMap.put("eventName", chooseEventName);
        return "/operator/chooseOrganization";
    }
    
    /**
      * 查询组织职位树数据列表
      *     一般服务于选择组织树
      *     不包含已经禁用的组织
      *<功能详细描述>
      * @return [参数说明]
      * 
      * @return List<OrganizationPostTreeNode> [返回类型说明]
      * @exception throws [异常类型] [异常说明]
      * @see [类、类#方法、类#成员]
     */
    @ResponseBody
    @RequestMapping("/queryOrganizationPostTreeNodeList")
    public List<TreeNode> queryOrganizationPostTreeNodeList() {
        List<TreeNode> resList = this.organizationService.queryOrganizationPostTreeNodeList();
        
        return resList;
    }
    
    /**
      * 查询所有组织的树列表<br/>
      *<功能详细描述>
      * @return [参数说明]
      * 
      * @return List<Organization> [返回类型说明]
      * @exception throws [异常类型] [异常说明]
      * @see [类、类#方法、类#成员]
     */
    @RequestMapping("/queryOrganizationList")
    @ResponseBody
    public List<Organization> queryOrganizationList() {
        List<Organization> orgList = this.organizationService.queryOrganizationList();
        
        return orgList;
    }
    
    /**
     * 添加组织结构页面
     *<功能详细描述>
     * @param organization [参数说明]
     * 
     * @return void [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
    */
    @CheckOperateAuth(key = "add_organization", name = "增加组织")
    @RequestMapping("/addOrganization")
    @ResponseBody
    public boolean addOrganization(Organization organization) {
        this.organizationService.insertOrganization(organization);
        
        return true;
    }
    
    /**
     * 跳转到添加组织结构页面
     *<功能详细描述>
     * @param organization [参数说明]
     * 
     * @return void [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
    */
    @ResponseBody
    @RequestMapping("/organizationCodeIsExist")
    public Map<String, String> organizationCodeIsExist(
            @RequestParam("code") String code,
            @RequestParam(value = "id", required = false) String excludeOrganizationId) {
        boolean resFlag = this.organizationService.organizationCodeIsExist(code,
                excludeOrganizationId);
        Map<String, String> resMap = new HashMap<String, String>();
        if (!resFlag) {
            resMap.put("ok", "");
        } else {
            resMap.put("error", "重复的组织编号");
        }
        return resMap;
    }
    
    /**
      * 更新组织结构信息<br/>
      *<功能详细描述>
      * @param organization
      * @return [参数说明]
      * 
      * @return boolean [返回类型说明]
      * @exception throws [异常类型] [异常说明]
      * @see [类、类#方法、类#成员]
     */
    @CheckOperateAuth(key = "update_organization", name = "更新组织")
    @RequestMapping("/updateOrganization")
    @ResponseBody
    public boolean updateOrganization(Organization organization) {
        boolean resFlag = this.organizationService.updateById(organization);
        return resFlag;
    }
    
    /**
     * 检查对应组织是否能够被删除
     *<功能详细描述>
     * @param organizationId
     * @return [参数说明]
     * 
     * @return boolean [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
    */
    @ResponseBody
    @RequestMapping("/isDeleteAble")
    public boolean isDeleteAble(
            @RequestParam("organizationId") String organizationId) {
        boolean resFlag = this.organizationService.isDeleteAble(organizationId);
        
        //如果存在尚未停用的下级组织则不能被停用
        if (resFlag) {
            return true;
        } else {
            return false;
        }
    }
    
    /**
      * 检查对应组织是否能够被禁用<br/>
      *<功能详细描述>
      * @param organizationId
      * @return [参数说明]
      * 
      * @return boolean [返回类型说明]
      * @exception throws [异常类型] [异常说明]
      * @see [类、类#方法、类#成员]
     */
    @ResponseBody
    @RequestMapping("/isDisableAble")
    public boolean isDisableAble(
            @RequestParam("organizationId") String organizationId) {
        boolean resFlag = this.organizationService.isDisableAble(organizationId);
        
        //如果存在尚未停用的下级组织则不能被停用
        if (resFlag) {
            return true;
        } else {
            return false;
        }
    }
    
    /**
      * 根据组织id删除对应组织<br/> 
      *<功能详细描述>
      * @param organizationId
      * @return [参数说明]
      * 
      * @return boolean [返回类型说明]
      * @exception throws [异常类型] [异常说明]
      * @see [类、类#方法、类#成员]
     */
    @CheckOperateAuth(key = "delete_organization", name = "删除组织", configAble = false)
    @ResponseBody
    @RequestMapping("/deleteOrganizationById")
    public boolean deleteOrganizationById(
            @RequestParam("organizationId") String organizationId) {
        boolean resFlag = this.organizationService.deleteById(organizationId);
        
        return resFlag;
    }
    
    /**
      * 根据组织id进行禁用<br/>
      *<功能详细描述>
      * @param organizationId
      * @return [参数说明]
      * 
      * @return boolean [返回类型说明]
      * @exception throws [异常类型] [异常说明]
      * @see [类、类#方法、类#成员]
     */
    @CheckOperateAuth(key = "disable_organization", name = "禁用组织")
    @ResponseBody
    @RequestMapping("/disableOrganizationById")
    public boolean disableOrganizationById(
            @RequestParam("organizationId") String organizationId) {
        boolean resFlag = this.organizationService.disableOrganizationById(organizationId);
        
        return resFlag;
    }
    
    /**
      * 根据组织id进行启用<br/> 
      *<功能详细描述>
      * @param organizationId
      * @return [参数说明]
      * 
      * @return boolean [返回类型说明]
      * @exception throws [异常类型] [异常说明]
      * @see [类、类#方法、类#成员]
     */
    @CheckOperateAuth(key = "enable_organization", name = "启用组织")
    @ResponseBody
    @RequestMapping("/enableOrganizationById")
    public boolean enableOrganizationById(
            @RequestParam("organizationId") String organizationId) {
        boolean resFlag = this.organizationService.enableOrganizationById(organizationId);
        
        return resFlag;
    }
    
}
