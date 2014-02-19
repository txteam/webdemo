/*
 * 描          述:  <描述>
 * 修  改   人:  Administrator
 * 修改时间:  2014年2月19日
 * <修改描述:>
 */
package com.tx.component.operator.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.tx.component.auth.annotation.CheckOperateAuth;


 /**
  * 职位分组控制器<br/>
  * <功能详细描述>
  * 
  * @author  Administrator
  * @version  [版本号, 2014年2月19日]
  * @see  [相关类/方法]
  * @since  [产品/模块版本]
  */
@Controller
@RequestMapping("/postGroup")
@CheckOperateAuth(key = "operator_manage")
public class PostGroupController {
    
}
