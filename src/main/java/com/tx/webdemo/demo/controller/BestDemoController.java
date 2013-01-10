/*
 * 描          述:  <描述>
 * 修  改   人:  PengQingyang
 * 修改时间:  2012-12-22
 * <修改描述:>
 */
package com.tx.webdemo.demo.controller;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang.math.NumberUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tx.core.paged.model.PagedList;
import com.tx.webdemo.demo.model.Demo;
import com.tx.webdemo.demo.service.DemoService;

/**
 * 最佳实践参考
 * <功能详细描述>
 * 
 * @author  PengQingyang
 * @version  [版本号, 2012-12-22]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
@Controller("bestDemoController")
@RequestMapping("/bestDemo")
public class BestDemoController {
    
    @Resource(name = "demoService")
    private DemoService demoService;
    
    /**
      * 查询demo列表<br/>
      * <功能详细描述>
      * @param params
      * @return [参数说明]
      * 
      * @return List<Demo> [返回类型说明]
      * @exception throws [异常类型] [异常说明]
      * @see [类、类#方法、类#成员]
     */
    @RequestMapping("/queryDemoList")
    public String queryDemoList(
            @RequestParam Map<String, String> queryCondition,
            @RequestParam MultiValueMap<String, String> queryCondition2,
            Model requestAttrs) {
        //不同的逻辑，总有不同的查询条件，查询条件很难用统一的一个bean去处理，规定统一接收参数用 MultiValueMap 去接收
        //当然如果，条件中，不存在多个入参的条件，这里也可以用Map处理，如果用Map,多选框进入的值将只会接收到第一个
        for (MultiValueMap.Entry<String, String> entry : queryCondition.entrySet()) {
            System.out.println(entry.getKey() + " : "
                    + queryCondition.get(entry.getKey()));
        }
        for (MultiValueMap.Entry<String, List<String>> entry : queryCondition2.entrySet()) {
            System.out.println(entry.getKey() + " : "
                    + queryCondition2.getFirst(entry.getKey()));
        }
        
        List<String> testCheckbox = queryCondition2.get("testCheckbox");
        if(testCheckbox != null){
            for (String testCheckboxTemp : testCheckbox) {
                System.out.println("testCheckbox :" + testCheckboxTemp);
            }
        }
        
        
        //查询demo类表
        List<Demo> demoList = this.demoService.queryDemoList();
        
        requestAttrs.addAttribute("demoList", demoList);
        requestAttrs.addAllAttributes(queryCondition);
        //如果要将queryCondition2压入返回值列表中，需要在这里作特殊处理
        //如果不处理list返回页面将多一个[]
        
        return "/demo/demoList";
    }
    
    /**
      * 跳转到ajaxDemo列表
      * <功能详细描述>
      * @return [参数说明]
      * 
      * @return String [返回类型说明]
      * @exception throws [异常类型] [异常说明]
      * @see [类、类#方法、类#成员]
     */
    @RequestMapping("/toAjaxQueryDemoList")
    public String toAjaxQueryDemoList(Model requestAttrs) {
        
        return "/demo/ajaxDemoList";
    }
    
    /**
     * 跳转到ajaxDemo分页列表
     * <功能详细描述>
     * @return [参数说明]
     * 
     * @return String [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
    */
    @RequestMapping("/toAjaxQueryDemoPagedList")
    public String toAjaxQueryDemoPagedList(Model requestAttrs) {
        
        return "/demo/ajaxDemoPagedList";
    }
    
    /**
      * 增加demo页面
      * <功能详细描述>
      * @param requestAttrs
      * @return [参数说明]
      * 
      * @return String [返回类型说明]
      * @exception throws [异常类型] [异常说明]
      * @see [类、类#方法、类#成员]
     */
    @RequestMapping("/toAddDemo")
    public String toAddDemo(Model requestAttrs) {
        
        return "/demo/addDemo";
    }
    
    /**
     * 查看demo详情页面
     * <功能详细描述>
     * @param requestAttrs
     * @return [参数说明]
     * 
     * @return String [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
    */
    @RequestMapping("/toViewDemo")
    public String toViewDemo(Model requestAttrs) {
        
        return "/demo/viewDemo";
    }
    
    /**
     * 跳转到更新demo页面
     * <功能详细描述>
     * @param requestAttrs
     * @return [参数说明]
     * 
     * @return String [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
    */
    @RequestMapping("/toUpdateDemo")
    public String toUpdateDemo(Model requestAttrs) {
        
        return "/demo/updateDemo";
    }
    
    /**
     * ajax查询demo列表
     * <功能详细描述>
     * @param params
     * @return [参数说明]
     * 
     * @return List<Demo> [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
    */
    @RequestMapping("/ajax/ajaxQueryDemoList")
    @ResponseBody()
    public List<Demo> ajaxQueryDemoList(
            @RequestParam MultiValueMap<String, String> queryCondition,
            Model requestAttrs) {
        
        List<Demo> demoList = this.demoService.queryDemoList();
        
        return demoList;
    }
    
    /**
      * ajax查询demo分页
      * <功能详细描述>
      * @param params
      * @return [参数说明]
      * 
      * @return String [返回类型说明]
      * @exception throws [异常类型] [异常说明]
      * @see [类、类#方法、类#成员]
     */
    @RequestMapping("/ajax/ajaxQueryDemoPagedList")
    @ResponseBody()
    public PagedList<Demo> ajaxQueryDemoPagedList(
            @RequestParam MultiValueMap<String, String> queryCondition) {
        for (MultiValueMap.Entry<String, List<String>> entry : queryCondition.entrySet()) {
            System.out.println(entry.getKey() + " : "
                    + queryCondition.getFirst(entry.getKey()));
        }
        List<String> testCheckbox = queryCondition.get("testCheckbox");
        if(testCheckbox != null){
            for (String testCheckboxTemp : testCheckbox) {
                System.out.println("testCheckbox :" + testCheckboxTemp);
            }
        }
        
        int pageIndex = NumberUtils.toInt(queryCondition.getFirst("pageIndex"),
                1);
        int pageSize = NumberUtils.toInt(queryCondition.getFirst("pageSize"),
                10);
        
        PagedList<Demo> demoPagedList = this.demoService.queryDemoPagedList(pageIndex,
                pageSize);
        
        return demoPagedList;
    }
    
}
