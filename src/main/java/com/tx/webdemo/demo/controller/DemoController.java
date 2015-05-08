/*
 * 描          述:  <描述>
 * 修  改   人:  PengQingyang
 * 修改时间:  2012-10-14
 * <修改描述:>
 */
package com.tx.webdemo.demo.controller;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang.time.DateFormatUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tx.component.mainframe.config.MenuItemConfig;
import com.tx.component.mainframe.context.MenuContext;
import com.tx.component.mainframe.model.DefaultMenuItem;
import com.tx.component.mainframe.model.MenuItem;
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
    
    @SuppressWarnings("unused")
    private static Logger logger = LoggerFactory.getLogger(DemoController.class);
    
    @Resource(name = "demoService")
    private DemoService demoService;
    
    /**
     * 出发批量插入demo<br/>
     * 为测试准备测试数据<br/>
     * 这里数据没有从界面上去获取<br/>
     * 批量插入的业务，一般来说会在业务层对批量方法结果进行处理<br/>
     * <功能详细描述> [参数说明]
     * 
     * @return void [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
    */
   @RequestMapping("/batchInsertDemoForUse")
   @ResponseBody()
   public boolean batchInsertDemoForUse(){
       List<Demo> demoList = new ArrayList<Demo>();
       String loginName = "testBatchInserDemoNonStop"
               + DateFormatUtils.format(new Date(), "yyyyMMddHHmmssSSS");

       for (int i = 0; i < 100; i++) {
           Demo newDemo = new Demo();
           newDemo.setName("testName");
           newDemo.setLoginName(loginName + (int) (Math.random() * 10000000));
           newDemo.setPassword("test");
           newDemo.setCreateDate(new Date());
           newDemo.setLastUpdateDate(new Date());
           newDemo.setTestBigDecimal(new BigDecimal("" + (int) (Math.random() * 10000000)));
           newDemo.setTestInt((int) (Math.random() * 10000000));
           newDemo.setTestIntegerObj((int) (Math.random() * 10000000));
           newDemo.setEmail("" + (int) (Math.random() * 10000000) + "@qq.com");
           demoList.add(newDemo);
       }
       
       this.demoService.batchInsertDemoNotStopWhenException(demoList,false);  
       return true;
   }
    
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
        List<MenuItem> menuItemList = MenuContext.getContext().getMenuItemListFromCurrentSession(MenuItem.TYPE_MAIN_MENU);
        
        List<MenuItem> menuItemTreeList = TreeUtils.changeToTree(menuItemList);
        
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
    
    @SuppressWarnings("unchecked")
    public static void main(String[] args) throws Exception{
        MenuItemConfig mic = new MenuItemConfig();
        mic.setId("test");
        mic.setAuthKey("authKey1,authKey2");
        mic.setChilds(new ArrayList<MenuItemConfig>());
        mic.getChilds().add(new MenuItemConfig());
        mic.getChilds().get(0).setId("testChild0");
        
        DefaultMenuItem dmi = new DefaultMenuItem();
        
        PropertyUtils.copyProperties(dmi, mic);
        
        System.out.println(dmi.getId());
        
        Map<String, Object> test = PropertyUtils.describe(dmi);
        System.out.println();
        
        MapUtils.verbosePrint(System.out, "map : ", test);
        
        System.out.println(PropertyUtils.getIndexedProperty(dmi, "childs",0));
        
        System.out.println(PropertyUtils.getSimpleProperty(dmi, "id"));
        
        //System.out.println(PropertyUtils.getMappedProperty(dmi, "sub.id"));
        
        System.out.println(PropertyUtils.getNestedProperty(dmi, "childs[0].id"));
        
        System.out.println(PropertyUtils.getProperty(dmi, "childs[0].id"));
    }
   
}
