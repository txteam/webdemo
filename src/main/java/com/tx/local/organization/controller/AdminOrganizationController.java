/*
 * 描       述:  <描述>
 * 修  改 人:  
 * 修改时间:
 * <修改描述:>
 */
package com.tx.local.organization.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.tx.local.organization.model.OrganizationTypeEnum;
import com.tx.local.organization.service.OrganizationService;
import com.tx.local.security.util.WebContextUtils;
import com.tx.local.vitualcenter.facade.VirtualCenterFacade;

/**
 * 组织控制层<br/>
 *    仅为范例展示，如果需要超级管理员
 *    进行特殊的跨虚中心的处理时，则可添加类似的逻辑进行处理
 * 
 * @author []
 * @version [版本号]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
@Controller
@RequestMapping("/admin/organization")
public class AdminOrganizationController {
    
    //组织业务层
    @Resource(name = "organizationService")
    private OrganizationService organizationService;
    
    //虚中心业务层
    @Resource
    private VirtualCenterFacade virtualCenterFacade;
    
    /**
     * 跳转到查询组织列表页面<br/>
     * <功能详细描述>
     * @return [参数说明]
     * 
     * @return String [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @RequestMapping("/toQueryList")
    public String toQueryList(ModelMap response) {
        response.put("types", OrganizationTypeEnum.values());
        response.put("vcid", WebContextUtils.getVcid());
        
        return "/organization/admin/queryOrganizationList";
    }
}