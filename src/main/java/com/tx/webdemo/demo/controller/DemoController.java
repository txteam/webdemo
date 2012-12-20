/*
 * 描          述:  <描述>
 * 修  改   人:  PengQingyang
 * 修改时间:  2012-10-14
 * <修改描述:>
 */
package com.tx.webdemo.demo.controller;

import java.text.ParseException;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang.time.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.tx.component.mainframe.model.MenuItem;
import com.tx.component.mainframe.service.MenuService;
import com.tx.core.paged.model.PagedList;
import com.tx.core.tree.util.TreeUtils;
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
@RequestMapping(value = "/demo")
public class DemoController {
    
    private static Logger logger = LoggerFactory.getLogger(DemoController.class);
    
    @Resource(name = "demoService")
    private DemoService demoService;
    
    @Resource(name= "menuService")
    private MenuService menuService;
    
    /**
      * 树demo
      * <功能详细描述>
      * @return [参数说明]
      * 
      * @return String [返回类型说明]
      * @exception throws [异常类型] [异常说明]
      * @see [类、类#方法、类#成员]
     */
    @RequestMapping("/queryAuthTreeDemo")
    public String queryAuthTreeDemo(Model model){
        
        //列表向树的转换属于业务逻辑应该写在业务层，这里写这里只是为了显示
        List<MenuItem> menuItemList = this.menuService.getMainMenuItemList();
        
        List<MenuItem> menuItemTreeList = TreeUtils.changToTree(menuItemList);
        
        model.addAttribute("menuItemTreeList", menuItemTreeList);
        
        return "/demo/demoAuthTree";
    }
    
    /**
      * 查询demo列表
      * @param model
      * @return [参数说明]
      * 
      * @return String [返回类型说明]
      * @exception throws [异常类型] [异常说明]
      * @see [类、类#方法、类#成员]
     */
    @RequestMapping(value = "/queryDemoList")
    public String queryDemoList(Model model) {
        List<Demo> demoList = this.demoService.queryDemoList();
        
        model.addAttribute("demoList", demoList);
        
        return "/demo/demoList";
    }
    
    /**
      * 查询demo分页列表
      * <功能详细描述>
      * @param model
      * @return [参数说明]
      * 
      * @return String [返回类型说明]
      * @exception throws [异常类型] [异常说明]
      * @see [类、类#方法、类#成员]
     */
    public String queryDemoPagedList(int pageIndex, int pageSize, Model model) {
        PagedList<Demo> demoPagedList = this.demoService.queryDemoPagedList(pageIndex,
                pageSize);
        
        model.addAttribute("demoPagedList", demoPagedList);
        
        return "/demo/demoPagedList";
    }
    
}
