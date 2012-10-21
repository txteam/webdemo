/*
 * 描          述:  <描述>
 * 修  改   人:  PengQingyang
 * 修改时间:  2012-10-14
 * <修改描述:>
 */
package com.tx.webdemo.demo.controller;

import java.text.ParseException;
import java.util.Date;

import javax.annotation.Resource;

import org.apache.commons.lang.time.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.tx.webdemo.demo.model.Demo;
import com.tx.webdemo.demo.service.DemoService;


 /**
  * <功能简述>
  * <功能详细描述>
  * 
  * @author  PengQingyang
  * @version  [版本号, 2012-10-14]
  * @see  [相关类/方法]
  * @since  [产品/模块版本]
  */
@Controller("demoController")
@RequestMapping(value="/demo")
public class DemoController {
    
    private static Logger logger = LoggerFactory.getLogger(DemoController.class);
    
    @Resource(name="demoService")
    private DemoService demoService;
    
    public DemoController(){
        logger.info("Instance DemoController............................");
    }
    
    @RequestMapping(value="/toAddDemo")
    public ModelAndView toAddDemo(){
        ModelAndView mv = new ModelAndView();
        
        mv.setViewName("/demo/addDemo");
        return mv;
    }
    
    
    @RequestMapping(value="/addDemo1")
    public ModelAndView addDemo1(@RequestParam("name") String name,
            @RequestParam("passowrd") String passowrd,
            @RequestParam("email") String email,
            @RequestParam("createDate") String createDate){
        ModelAndView mv = new ModelAndView();
        
        Demo demo = new Demo();
        demo.setName(name);
        demo.setPassowrd(passowrd);
        demo.setEmail(email);
        Date createDateInfo = null;
        try {
            createDateInfo = DateUtils.parseDate(createDate, new String[]{"yyyy-MM-dd HH:mm:ss","yyyy-MM-dd"});
        }
        catch (ParseException e) {
            createDateInfo = new Date();
        }
        demo.setCreateDate(createDateInfo);
        
        this.demoService.insertDemo(demo);
        
        mv.setViewName("demo/addSuccess");
        mv.addObject("resFlag","success");
        return mv;
    }
}
