/*
 * 描          述:  <描述>
 * 修  改   人:  Administrator
 * 修改时间:  2019年11月24日
 * <修改描述:>
 */
package com.tx.admin.controller;

import javax.annotation.security.RolesAllowed;

import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * <功能简述>
 * <功能详细描述>
 * 
 * @author  Administrator
 * @version  [版本号, 2019年11月24日]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
@RequestMapping("/admin/mainframe")
public class MainframeController {
    
    /**
     * 跳转到虚中心iframe界面<br/>
     * <功能详细描述>
     * @param src
     * @param response
     * @return [参数说明]
     * 
     * @return String [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @RolesAllowed(value = {"ROLE_SUPER_ADMIN"})
    @RequestMapping("/toVCIframe")
    public String toVCIframe(
            @RequestParam(value = "src", required = true) String src,
            ModelMap response) {
        response.put(src, src);
        return null;
    }
}
