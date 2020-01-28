/*
 * 描       述:  <描述>
 * 修  改 人:  
 * 修改时间:
 * <修改描述:>
 */
package com.tx.local.loanaccount.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tx.local.loanaccount.model.OverdueInterestChargeRecord;
import com.tx.local.loanaccount.service.OverdueInterestChargeRecordService;

/**
  * 
  * <逾期利息记录>
  * <功能详细描述>
  * 
  * @author  Bobby
  * @version  [版本号, 2018年3月1日]
  * @see  [相关类/方法]
  * @since  [产品/模块版本]
 */
@Controller("overdueInterestRecordController")
@RequestMapping("/overdueInterestRecord")
public class OverdueInterestRecordController {
    
    @Resource(name = "overdueInterestChargeRecordService")
    private OverdueInterestChargeRecordService overdueInterestRecordService;
    
    /**
      * 跳转到查询OverdueInterestRecord列表页面<br/>
      * <功能详细描述>
      * @return [参数说明]
      * 
      * @return String [返回类型说明]
      * @exception throws [异常类型] [异常说明]
      * @see [类、类#方法、类#成员]
      */
    @RequestMapping("/toQueryOverdueInterestRecordList")
    public String toQueryOverdueInterestRecordList() {
        return "/loanaccount/queryOverdueInterestRecordList";
    }
    
    /**
      * 
      *<功能简述>
      *<功能详细描述>
      * @return [参数说明]
      * 
      * @return String [返回类型说明]
      * @exception throws [异常类型] [异常说明]
      * @see [类、类#方法、类#成员]
     */
    @ResponseBody
    @RequestMapping("/queryOverdueInterestRecordList")
    public List<OverdueInterestChargeRecord> queryOverdueInterestRecordList(
            @RequestParam("loanAccountId") String loanAccountId) {
        List<OverdueInterestChargeRecord>  overdueInterestRecordList= overdueInterestRecordService.queryListByLoanAccountId(loanAccountId);
        return overdueInterestRecordList;
    }
    
    /**
     * 跳转到查询OverdueInterestRecord分页列表页面<br/>
     *<功能详细描述>
     * @return [参数说明]
     * 
     * @return String [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
    */
    @RequestMapping("/toQueryOverdueInterestRecordPagedList")
    public String toQueryOverdueInterestRecordPagedList() {
        return "/loanaccount/queryOverdueInterestRecordPagedList";
    }
}
