/*
 * 描          述:  <描述>
 * 修  改   人:  PengQingyang
 * 修改时间:  2013-8-23
 * <修改描述:>
 */
package com.tx.component.mainframe.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


 /**
  * 门户视图逻辑控制层
  * <功能详细描述>
  * 
  * @author  PengQingyang
  * @version  [版本号, 2013-8-23]
  * @see  [相关类/方法]
  * @since  [产品/模块版本]
  */
@Controller
@RequestMapping("/portal")
public class PortalViewController {
    
    /**
      * 显示portalIndex
      *<功能详细描述>
      * @return [参数说明]
      * 
      * @return String [返回类型说明]
      * @exception throws [异常类型] [异常说明]
      * @see [类、类#方法、类#成员]
     */
    @RequestMapping("/index")
    public String toPortalIndex(){
        return "/portal/index";
    }
    
    @RequestMapping("/changeLog")
    public String toChangeLog(){
        return "/portal/changeLog";
    }
    
    @RequestMapping("/bugView")
    public String toBugView(){
        return "/portal/bugView";
    }
    
    @RequestMapping("/links")
    public String toLinks(){
        return "/portal/links";
    }
    
    @RequestMapping("/calandar")
    public String toCalandar(){
        return "/portal/calandar";
    }
    
}
