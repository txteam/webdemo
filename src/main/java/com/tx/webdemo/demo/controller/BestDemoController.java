/*
 * 描          述:  <描述>
 * 修  改   人:  PengQingyang
 * 修改时间:  2012-12-22
 * <修改描述:>
 */
package com.tx.webdemo.demo.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.math.NumberUtils;
import org.apache.commons.lang.time.DateFormatUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tx.component.mainframe.context.WebContextUtils;
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
    
    @Resource(name="demoService")
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
            demoList.add(newDemo);
        }
        
        this.demoService.batchInsertDemoNotStopWhenException(demoList,false);  
        return true;
    }
    
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
    public String queryDemoList(HashMap<String, Object> params){
        
        List<Demo> demoList = this.demoService.queryDemoList();
        
        params.put("demoList", demoList);
        return "/demo/best/demoList";
    }
    
    /**
     * 
      *<功能简述>
      *<功能详细描述>
      * @param params
      * @return [参数说明]
      * 
      * @return String [返回类型说明]
      * @exception throws [异常类型] [异常说明]
      * @see [类、类#方法、类#成员]
     */
    @RequestMapping("/ajax/ajaxQueryPagedDemoList")
    @ResponseBody()
    public PagedList<Demo> ajaxQueryPagedDemoList(HashMap<String, Object> params) {
        PagedList<Demo> demoPagedList = this.demoService.queryDemoPagedList(1,
                10);
        
        return demoPagedList;
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
    public List<Demo> ajaxQueryDemoList(HashMap<String, Object> params){
        List<Demo> demoList = this.demoService.queryDemoList();
        
        return demoList;
    }
    
    /**
      * ajax查询demo分页列表
      * <功能详细描述>
      * @return [参数说明]
      * 
      * @return PagedList<Demo> [返回类型说明]
      * @exception throws [异常类型] [异常说明]
      * @see [类、类#方法、类#成员]
     */
    @RequestMapping("/ajax/ajaxQueryDemoPagedList")
    @ResponseBody()
    public PagedList<Demo> ajaxQueryDemoPagedList(){
        HttpServletRequest request = WebContextUtils.getRequest();
        int pageIndex = NumberUtils.toInt(request.getParameter("pageIndex"),1);
        int pageSize = NumberUtils.toInt(request.getParameter("pageSize"),10);
        
        PagedList<Demo> demoPagedList = this.demoService.queryDemoPagedList(pageIndex, pageSize);
        
        return demoPagedList;
    }
    
    
}
