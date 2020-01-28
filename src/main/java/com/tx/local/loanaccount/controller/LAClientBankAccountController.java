/*
 * 描       述:  <描述>
 * 修  改 人:  
 * 修改时间:
 * <修改描述:>
 */
package com.tx.local.loanaccount.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tx.core.paged.model.PagedList;
import com.tx.local.basicdata.model.BankCardTypeEnum;
import com.tx.local.basicdata.model.IDCardTypeEnum;
import com.tx.local.loanaccount.model.LAClientBankAccount;
import com.tx.local.loanaccount.model.LAClientBankAccountTypeEnum;
import com.tx.local.loanaccount.service.LoanAccount2ClientBankAccountService;

/**
 * LoanAccount2ClientBankAccount显示层逻辑<br/>
 * <功能详细描述>
 * 
 * @author  PengQingyang
 * @version  [版本号, 2013-8-27]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
//FIXME:指定自动生成的权限上级权限,以及对应的权限名称
@Controller("loanAccount2ClientBankAccountController")
@RequestMapping("/loanAccount2ClientBankAccount")
public class LAClientBankAccountController {
    
    @Resource(name = "loanAccount2ClientBankAccountService")
    private LoanAccount2ClientBankAccountService loanAccount2ClientBankAccountService;
    
    /**
      * 跳转到查询LoanAccount2ClientBankAccount列表页面<br/>
      *<功能详细描述>
      * @return [参数说明]
      * 
      * @return String [返回类型说明]
      * @exception throws [异常类型] [异常说明]
      * @see [类、类#方法、类#成员]
     */
    @RequestMapping("/toQueryLoanAccount2ClientBankAccountList")
    public String toQueryLoanAccount2ClientBankAccountList(ModelMap response) {
        response.put("idCardTypeList", IDCardTypeEnum.values());
        response.put("bankCardTypeList", BankCardTypeEnum.values());
        response.put("laClientBankAccountTypeList", LAClientBankAccountTypeEnum.values());

        return "/loanaccount/queryLoanAccount2ClientBankAccountList";
    }
    
     /**
      * 跳转到查询LoanAccount2ClientBankAccount分页列表页面<br/>
      *<功能详细描述>
      * @return [参数说明]
      * 
      * @return String [返回类型说明]
      * @exception throws [异常类型] [异常说明]
      * @see [类、类#方法、类#成员]
     */
    @RequestMapping("/toQueryLoanAccount2ClientBankAccountPagedList")
    public String toQueryLoanAccount2ClientBankAccountPagedList(ModelMap response) {
        response.put("idCardTypeList", IDCardTypeEnum.values());
        response.put("bankCardTypeList", BankCardTypeEnum.values());
        response.put("laClientBankAccountTypeList", LAClientBankAccountTypeEnum.values());

        return "/loanaccount/queryLoanAccount2ClientBankAccountPagedList";
    }
    
    /**
      * 跳转到添加LoanAccount2ClientBankAccount页面<br/>
      *<功能详细描述>
      * @return [参数说明]
      * 
      * @return String [返回类型说明]
      * @exception throws [异常类型] [异常说明]
      * @see [类、类#方法、类#成员]
     */
    @RequestMapping("/toAddLoanAccount2ClientBankAccount")
    public String toAddLoanAccount2ClientBankAccount(ModelMap response) {
        response.put("loanAccount2ClientBankAccount", new LAClientBankAccount());

        response.put("idCardTypeList", IDCardTypeEnum.values());
        response.put("bankCardTypeList", BankCardTypeEnum.values());
        response.put("laClientBankAccountTypeList", LAClientBankAccountTypeEnum.values());

        return "/loanaccount/addLoanAccount2ClientBankAccount";
    }
    
    /**
     * 跳转到编辑LoanAccount2ClientBankAccount页面
     *<功能详细描述>
     * @return [参数说明]
     * 
     * @return String [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
    */
    @RequestMapping("/toUpdateLoanAccount2ClientBankAccount")
    public String toUpdateLoanAccount2ClientBankAccount(
            @RequestParam("loanAccount2ClientBankAccountId") String loanAccount2ClientBankAccountId,
            ModelMap response) {
        LAClientBankAccount resLoanAccount2ClientBankAccount = this.loanAccount2ClientBankAccountService.findById(loanAccount2ClientBankAccountId); 
        response.put("loanAccount2ClientBankAccount", resLoanAccount2ClientBankAccount);

        response.put("idCardTypeList", IDCardTypeEnum.values());
        response.put("bankCardTypeList", BankCardTypeEnum.values());
        response.put("laClientBankAccountTypeList", LAClientBankAccountTypeEnum.values());
        
        return "/loanaccount/updateLoanAccount2ClientBankAccount";
    }

    /**
    * 跳转到编辑LoanAccount2ClientBankAccount页面
    *<功能详细描述>
    * @return [参数说明]
    *
    * @return String [返回类型说明]
    * @exception throws [异常类型] [异常说明]
    * @see [类、类#方法、类#成员]
    */
    @RequestMapping("/toViewLoanAccount2ClientBankAccount")
    public String toViewByIdLoanAccount2ClientBankAccount(
    @RequestParam("loanAccount2ClientBankAccountId") String loanAccount2ClientBankAccountId,
    ModelMap response) {
LAClientBankAccount resLoanAccount2ClientBankAccount = this.loanAccount2ClientBankAccountService.findById(loanAccount2ClientBankAccountId);
    response.put("loanAccount2ClientBankAccount", resLoanAccount2ClientBankAccount);

    return "/loanaccount/detailLoanAccount2ClientBankAccount";
    }

    /**
     * 判断LoanAccount2ClientBankAccount:
     *  taskId
     *
     * 是否已经被使用
     * @param uniqueGetterName
     * @param excludeLoanAccount2ClientBankAccountId
     * @return [参数说明]
     * 
     * @return boolean [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @ResponseBody
    @RequestMapping("/validateTaskIdIsExist")
    public Map<String, String> validateTaskIdIsExist(
            @RequestParam("taskId") String taskId,
            @RequestParam(value = "id", required = false) String excludeLoanAccount2ClientBankAccountId,
            @RequestParam MultiValueMap<String, String> request) {
        
        Map<String, String> key2valueMap = new HashMap<String, String>();
        key2valueMap.put("taskId", taskId);
        
        boolean flag = this.loanAccount2ClientBankAccountService.isExist(key2valueMap, excludeLoanAccount2ClientBankAccountId);
        
        Map<String, String> resMap = new HashMap<String, String>();
        if (!flag) {
            //FIXME:修改验证重复成功提示信息
            resMap.put("ok", "可用的loanAccount2ClientBankAccount taskId");
        } else {
            //FIXME:修改验证重复失败提示信息
            resMap.put("error", "已经存在的loanAccount2ClientBankAccount taskId");
        }
        return resMap;
    }
    
    /**
     * 查询LoanAccount2ClientBankAccount列表<br/>
     *<功能详细描述>
     * @return [参数说明]
     * 
     * @return List<LoanAccount2ClientBankAccount> [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
    */
    @ResponseBody
    @RequestMapping("/queryList")
    public List<LAClientBankAccount> queryList(
            @RequestParam(value="valid",required=false) Boolean valid,
            @RequestParam(value="idCardType",required=false) IDCardTypeEnum idCardType,
            @RequestParam(value="laClientBankAccountType",required=false) LAClientBankAccountTypeEnum laClientBankAccountType,
            @RequestParam(value="bankCardType",required=false) BankCardTypeEnum bankCardType,
            @RequestParam(value="defaultAccount",required=false) Boolean defaultAccount,
            @RequestParam MultiValueMap<String, String> request
        ) {
        Map<String,Object> params = new HashMap<>();

        params.put("valid",valid);
        params.put("idCardType",idCardType);
        params.put("laClientBankAccountType",laClientBankAccountType);
        params.put("bankCardNumber",request.getFirst("bankCardNumber"));
        params.put("clientName",request.getFirst("clientName"));
        params.put("idCardNumber",request.getFirst("idCardNumber"));
        params.put("bankCardType",bankCardType);
        params.put("id",request.getFirst("id"));
        params.put("defaultAccount",defaultAccount);
        
        List<LAClientBankAccount> resList = this.loanAccount2ClientBankAccountService.queryList(
            params         
        );
  
        return resList;
    }
    
    /**
     * 查询LoanAccount2ClientBankAccount分页列表<br/>
     *<功能详细描述>
     * @return [参数说明]
     * 
     * @return List<LoanAccount2ClientBankAccount> [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
    */
    @ResponseBody
    @RequestMapping("/queryPagedList")
    public PagedList<LAClientBankAccount> queryPagedList(
            @RequestParam(value="valid",required=false) Boolean valid,
            @RequestParam(value="idCardType",required=false) IDCardTypeEnum idCardType,
            @RequestParam(value="laClientBankAccountType",required=false) LAClientBankAccountTypeEnum laClientBankAccountType,
            @RequestParam(value="bankCardType",required=false) BankCardTypeEnum bankCardType,
            @RequestParam(value="defaultAccount",required=false) Boolean defaultAccount,
            @RequestParam(value = "pageNumber", required = false, defaultValue = "1") int pageIndex,
            @RequestParam(value = "pageSize", required = false, defaultValue = "10") int pageSize,
            @RequestParam MultiValueMap<String, String> request
        ) {
        Map<String,Object> params = new HashMap<>();

        params.put("valid",valid);
        params.put("idCardType",idCardType);
        params.put("laClientBankAccountType",laClientBankAccountType);
        params.put("bankCardNumber",request.getFirst("bankCardNumber"));
        params.put("clientName",request.getFirst("clientName"));
        params.put("idCardNumber",request.getFirst("idCardNumber"));
        params.put("bankCardType",bankCardType);
        params.put("id",request.getFirst("id"));
        params.put("defaultAccount",defaultAccount);

        PagedList<LAClientBankAccount> resPagedList = this.loanAccount2ClientBankAccountService.queryPagedList(
            params,
            pageIndex,
            pageSize
        );
        return resPagedList;
    }
    
    /**
     * 添加组织结构页面
     *<功能详细描述>
     * @param organization [参数说明]
     * 
     * @return void [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
    */
    //FIXME:修改删增加权限名称
    @RequestMapping("/add")
    @ResponseBody
    public boolean add(LAClientBankAccount loanAccount2ClientBankAccount) {
        this.loanAccount2ClientBankAccountService.insert(loanAccount2ClientBankAccount);
        return true;
    }
    
    /**
      * 更新组织<br/>
      *<功能详细描述>
      * @param loanAccount2ClientBankAccount
      * @return [参数说明]
      * 
      * @return boolean [返回类型说明]
      * @exception throws [异常类型] [异常说明]
      * @see [类、类#方法、类#成员]
     */
    //FIXME:修改删编辑权限名称
    @RequestMapping("/update")
    @ResponseBody
    public boolean update(LAClientBankAccount loanAccount2ClientBankAccount) {
        this.loanAccount2ClientBankAccountService.updateById(loanAccount2ClientBankAccount);
        
        return true;
    }
    
    /**
      * 删除指定LoanAccount2ClientBankAccount<br/> 
      *<功能详细描述>
      * @param loanAccount2ClientBankAccountId
      * @return [参数说明]
      * 
      * @return boolean [返回类型说明]
      * @exception throws [异常类型] [异常说明]
      * @see [类、类#方法、类#成员]
     */
    //FIXME:修改删除权限名称
    @ResponseBody
    @RequestMapping("/deleteById")
    public boolean deleteById(@RequestParam(value = "loanAccount2ClientBankAccountId") String loanAccount2ClientBankAccountId) {
        boolean resFlag = this.loanAccount2ClientBankAccountService.deleteById(loanAccount2ClientBankAccountId);
        return resFlag;
    }
    
}