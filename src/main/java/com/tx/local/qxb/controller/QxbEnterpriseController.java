/*
 * 描          述:  <描述>
 * 修  改   人:  PengQingyang
 * 修改时间:  2020年6月14日
 * <修改描述:>
 */
package com.tx.local.qxb.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 企信宝控制层<br/>
 * <功能详细描述>
 * 
 * @author  PengQingyang
 * @version  [版本号, 2020年6月14日]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
@Controller
@RequestMapping("/qxb/enterprise")
public class QxbEnterpriseController {
    
    /**
     * 企信宝企业信息控制层<br/>
     * <功能详细描述>
     * @param creditInfoId
     * @return [参数说明]
     * 
     * @return String [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @RequestMapping("/toDetail")
    public String toDetail(String creditInfoId) {
        return "qxb/enterpriseDetail";
    }
}
