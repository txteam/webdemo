/*
 * 描          述:  <描述>
 * 修  改   人:  Administrator
 * 修改时间:  2018年7月7日
 * <修改描述:>
 */
package com.tx.front4wap.mainframe.controller;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.tx.local.clientinfo.service.ClientInfoService;
import com.tx.local.institution.service.InstitutionInfoService;
import com.tx.local.menu.context.MenuContext;
import com.tx.local.security.SecurityConstants;
import com.tx.local.security.util.WebContextUtils;
import com.tx.security4client.util.ClientWebContextUtils;

/**
 * 主框架页面逻辑层<br/>
 * <功能详细描述>
 * 
 * @author  Administrator
 * @version  [版本号, 2018年7月7日]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
@Controller(value = "wap.client.mainframeController")
@RequestMapping("/wap/client/mainframe")
public class MainframeController {
    
    @Resource(name = "menuContext")
    private MenuContext menuContext;
    
    //社属机构基本信息业务层
    @Resource(name = "institutionInfoService")
    private InstitutionInfoService institutionInfoService;
    
    @Resource(name = "clientInfoService")
    private ClientInfoService clientInfoService;
    
    /**
     * 跳转到主页面中<br/> 
     * <功能详细描述>
     * @param model
     * @return [参数说明]
     * 
     * @return String [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @RequestMapping(value = { "", "/" })
    public String toMainframe(Model model, ModelMap response) {
        WebContextUtils.getSession()
                .setAttribute(SecurityConstants.ACCESS_DOMAIN_KEY,
                        SecurityConstants.ACCESS_DOMAIN_DEFAULT);
        
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        model.addAttribute("now", df.format(new Date()));
        
        response.put("number", ClientWebContextUtils.getClient().getUsername());
        
        return "mainframe/mainframe";
    }
}
