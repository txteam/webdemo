/*
 * 描          述:  <描述>
 * 修  改   人:  Administrator
 * 修改时间:  2017年6月1日
 * <修改描述:>
 */
package com.tx.local.loanaccount.controller;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tx.core.paged.model.PagedList;
import com.tx.local.loanaccount.model.LoanAccount;
import com.tx.local.loanaccount.service.LoanAccountService;

/**
  * 贷款账户处理控制器<br/>
  * <功能详细描述>
  * 
  * @author  Administrator
  * @version  [版本号, 2017年6月1日]
  * @see  [相关类/方法]
  * @since  [产品/模块版本]
  */
@Controller("loanAccountProcessListingController")
@RequestMapping("/loanAccountProcessListing")
public class LoanAccountProcessListingController {
    
    /** 贷款账户业务层 */
    @Resource(name = "loanAccountService")
    private LoanAccountService loanAccountService;
    
    /**
      * 跳转到还款查询列表<br/>
      * <功能详细描述>
      * @param response
      * @return [参数说明]
      * 
      * @return String [返回类型说明]
      * @exception throws [异常类型] [异常说明]
      * @see [类、类#方法、类#成员]
     */
    @RequestMapping("/toQueryListForRepay")
    public String toQueryListForRepay(ModelMap response) {
        return "/loanaccount/processlisting/queryListForRepay";
    }
    
    /**
      * 跳转到转账还款列表<br/>
      * <功能详细描述>
      * @param response
      * @return [参数说明]
      * 
      * @return String [返回类型说明]
      * @exception throws [异常类型] [异常说明]
      * @see [类、类#方法、类#成员]
     */
    @RequestMapping("/toQueryListForDeduct")
    public String toQueryListForDeduct(ModelMap response) {
        return "/loanaccount/processlisting/queryListForDeduct";
    }
    
    /**
      * 跳转到豁免查询列表<br/>
      * <功能详细描述>
      * @param response
      * @return [参数说明]
      * 
      * @return String [返回类型说明]
      * @exception throws [异常类型] [异常说明]
      * @see [类、类#方法、类#成员]
     */
    @RequestMapping("/toQueryListForExempt")
    public String toQueryListForExempt(ModelMap response) {
        return "/loanaccount/processlisting/queryListForExempt";
    }
    
    /**
    * 查询LoanAccount列表
    * <功能详细描述>
    * @param request
    * @return [参数说明]
    * 
    * @return List<LoanAccount> [返回类型说明]
    * @exception throws [异常类型] [异常说明]
    * @see [类、类#方法、类#成员]
    */
    @ResponseBody
    @RequestMapping("/queryLoanAccountList")
    public PagedList<LoanAccount> queryLoanAccountList(@RequestParam MultiValueMap<String, String> request,
            @RequestParam(value = "contractNumber", required = false) String contractNumber,
            @RequestParam(value = "idCardNumber", required = false) String idCardNumber,
            @RequestParam(value = "pageNumber", required = false, defaultValue = "1") int pageNumber,
            @RequestParam(value = "pageSize", required = false, defaultValue = "1000") int pageSize) {
        if (StringUtils.isBlank(contractNumber) && StringUtils.isBlank(idCardNumber)) {
            ///return null;
        }
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("contractNumber", contractNumber);
        params.put("idCardNumber", idCardNumber);
        //params.put("useAuth", AuthTypeEnum.CACH_REPAY_RANGE);
        //List<LoanAccount> resList = this.loanAccountService.queryLoanAccountList(params);
        PagedList<LoanAccount> resPagedList = this.loanAccountService.queryPagedList(params, pageNumber, pageSize);
        return resPagedList;
    }
}
