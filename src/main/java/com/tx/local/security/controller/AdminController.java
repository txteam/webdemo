/*
 * 描          述:  <描述>
 * 修  改   人:  Administrator
 * 修改时间:  2019年11月27日
 * <修改描述:>
 */
package com.tx.local.security.controller;

import javax.annotation.Resource;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tx.local.security.model.OperatorUserDetails;
import com.tx.local.security.util.WebContextUtils;
import com.tx.local.vitualcenter.facade.VirtualCenterFacade;
import com.tx.local.vitualcenter.model.VirtualCenter;

/**
 *  超级管理员控制层<br/>
 * <功能详细描述>
 * 
 * @author  Administrator
 * @version  [版本号, 2019年11月27日]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
@PreAuthorize("hasRole('ROLE_SUPER_ADMIN')")
@Controller("adminController")
@RequestMapping("/admin")
public class AdminController {
    
    @Resource
    private VirtualCenterFacade virtualCenterFacade;
    
    /**
     * 是否拥有超级管理员权限<br/>
     * <功能详细描述>
     * @return [参数说明]
     * 
     * @return boolean [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @ResponseBody
    @RequestMapping(value = "/vcid")
    public VirtualCenter vcid(
            @RequestParam(value = "vcid", required = true) String vcid) {
        //虚中心
        VirtualCenter vc = this.virtualCenterFacade.findById(vcid);
        if (vc == null || !WebContextUtils.isSuperAdmin()) {
            return this.virtualCenterFacade.findById(WebContextUtils.getVcid());
        }
        OperatorUserDetails user = WebContextUtils.getUserDetails();
        if (user != null) {
            user.setVcid(vcid);
        }
        return vc;
    }
    
    /**
     * 跳转到虚中心Iframe<br/>
     * <功能详细描述>
     * @param src
     * @param response
     * @return [参数说明]
     * 
     * @return String [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @RequestMapping(value = "/vciframe")
    public String vciframe(
            @RequestParam(value = "src", required = true) String src,
            ModelMap response) {
        response.put("vcid", WebContextUtils.getVcid());
        response.put("src", src);
        return "security/vciframe";
    }
}
