/*
 * 描          述:  <描述>
 * 修  改   人:  PengQingyang
 * 修改时间:  2013-12-14
 * <修改描述:>
 */
package com.tx.component.mainframe.controller;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tx.component.mainframe.context.WebContextUtils;
import com.tx.component.operator.service.OperatorService;

/**
 * 控制面板视图逻辑层
 * <功能详细描述>
 * 
 * @author  PengQingyang
 * @version  [版本号, 2013-12-14]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
@Controller("controlPanelController")
@RequestMapping("/controlPanel")
public class ControlPanelController {
    
    @Resource(name = "operatorService")
    private OperatorService operatorService;
    
    /**
      * 跳转到修改个人密码页面
      *<功能详细描述> [参数说明]
      * 
      * @return void [返回类型说明]
      * @exception throws [异常类型] [异常说明]
      * @see [类、类#方法、类#成员]
     */
    @RequestMapping("/toModifyMyPassword")
    public String toModifyMyPassword() {
        return "/mainframe/modifyMyPassword";
    }
    
    /**
      * 校验密码
      *<功能详细描述>
      * @param oldPassword
      * @return [参数说明]
      * 
      * @return boolean [返回类型说明]
      * @exception throws [异常类型] [异常说明]
      * @see [类、类#方法、类#成员]
     */
    @ResponseBody
    @RequestMapping("/checkMyPassword")
    public Map<String, String> checkMyPassword(
            @RequestParam("oldPassword") String oldPassword) {
        String operatorId = WebContextUtils.getCurrentOperator().getId();
        boolean resFlag = this.operatorService.checkPassword(operatorId,
                oldPassword);
        Map<String, String> resMap = new HashMap<String, String>();
        if (!resFlag) {
            resMap.put("ok", "可用的登录名");
        } else {
            resMap.put("error", "已经存在的登录名");
        }
        return resMap;
    }
    
    /**
      * 修改密码<br/>
      *<功能详细描述>
      * @param oldPassword
      * @param newPassword
      * @return [参数说明]
      * 
      * @return boolean [返回类型说明]
      * @exception throws [异常类型] [异常说明]
      * @see [类、类#方法、类#成员]
     */
    @ResponseBody
    @RequestMapping("/modifyMyPassword")
    public boolean modifyMyPassword(
            @RequestParam("oldPassword") String oldPassword,
            @RequestParam("newPassword") String newPassword) {
        String operatorId = WebContextUtils.getCurrentOperator().getId();
        boolean resFlag = false;
        if (this.operatorService.checkPassword(operatorId, oldPassword)) {
            resFlag = this.operatorService.updatePasswordById(operatorId,
                    newPassword);
        }
        return resFlag;
    }
    
}
