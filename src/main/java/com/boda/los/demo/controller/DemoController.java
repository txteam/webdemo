/*
 * 描          述:  <描述>
 * 修  改   人:  PengQingyang
 * 修改时间:  2012-10-14
 * <修改描述:>
 */
package com.boda.los.demo.controller;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.boda.los.demo.model.Demo;
import com.boda.los.demo.service.DemoService111;

/**
 * <功能简述> <功能详细描述>
 * 
 * @author PengQingyang
 * @version [版本号, 2012-10-14]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
@Controller("demoController")
@RequestMapping(value = "/demo")
public class DemoController {
    
    private static Logger logger = LoggerFactory.getLogger(DemoController.class);
    
    @Resource(name = "demoService111")
    private DemoService111 demoService111;
    
    @RequestMapping("/queryDemoList")
    public String queryDemoList(
            @RequestParam(value = "code", required = false) String code,
            Model mv) {
        mv.addAttribute("resList", this.demoService111.queryDemoListByCode(code));
        
        return "demo/queryDemoList";
    }
    
    @RequestMapping("/addDemo1")
    public String addDemo1(Demo demo) {
        // 调用业务
        this.demoService111.insertDemo(demo);
        
        return "";
    }
    
    @RequestMapping("/delete")
    public String deleteDemo(
            @RequestParam(value = "id", required = false) String demoId,
            @RequestParam(value = "code", required = false) String code,
            Model mv) {
        boolean flag = this.demoService111.deleteDemoById(demoId);
        if (flag) {
            return queryDemoList(code, mv);
        } else {
            return "demo/fail";
        }
    }
    
    @RequestMapping("/update")
    public String updateDemo(Demo demo,
            @RequestParam(value = "code", required = false) String code,
            Model mv) {
        boolean flag = this.demoService111.updateDemoName(null);
        if (flag) {
            return queryDemoList(code, mv);
        } else {
            return "demo/fail";
        }
    }
    
    @RequestMapping("/ajax/addDemo")
    @ResponseBody
    public String ajaxDddDemo(Demo demo) {
        // 调用业务
        this.demoService111.insertDemo(demo);
        
        return "";
    }
    
    @RequestMapping("/ajax/delete")
    @ResponseBody
    public String ajaxDeleteDemo(
            @RequestParam(value = "id", required = false) String demoId,
            Model mv) {
        boolean flag = this.demoService111.deleteDemoById(demoId);
        if (flag) {
            return "删除成功";
        } else {
            return "删除失败";
        }
    }
    
    @RequestMapping("/ajax/update")
    @ResponseBody
    public String ajaxUpdateDemo(Demo demo) {
        boolean flag = this.demoService111.updateDemoName(null);
        if (flag) {
            return "更新成功";
        } else {
            return "更新失败";
        }
    }
    
}
