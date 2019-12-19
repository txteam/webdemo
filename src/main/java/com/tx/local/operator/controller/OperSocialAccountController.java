/*
 * 描       述:  <描述>
 * 修  改 人:  
 * 修改时间:
 * <修改描述:>
 */
package com.tx.local.operator.controller;

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

import com.tx.local.operator.model.OperSocialAccount;
import com.tx.local.operator.service.OperSocialAccountService;
import com.tx.core.paged.model.PagedList;

import com.tx.local.operator.model.OperSocialAccountTypeEnum;

/**
 * 操作人员第三方账户控制层<br/>
 * 
 * @author []
 * @version [版本号]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
@Controller
@RequestMapping("/operSocialAccount")
public class OperSocialAccountController {
    
    //操作人员第三方账户业务层
    @Resource(name = "operSocialAccountService")
    private OperSocialAccountService operSocialAccountService;
    
    /**
     * 跳转到查询操作人员第三方账户分页列表页面<br/>
     * <功能详细描述>
     * @return [参数说明]
     * 
     * @return String [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @RequestMapping("/toQueryPagedList")
    public String toQueryPagedList(ModelMap response) {
		response.put("types", OperSocialAccountTypeEnum.values());

        return "operator/queryOperSocialAccountPagedList";
    }
    
    /**
     * 跳转到新增操作人员第三方账户页面<br/>
     * <功能详细描述>
     * @return [参数说明]
     * 
     * @return String [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @RequestMapping("/toAdd")
    public String toAdd(ModelMap response) {
    	response.put("operSocialAccount", new OperSocialAccount());
    	
		response.put("types", OperSocialAccountTypeEnum.values());

        return "operator/addOperSocialAccount";
    }
    
    /**
     * 跳转到编辑操作人员第三方账户页面
     * <功能详细描述>
     * @return [参数说明]
     * 
     * @return String [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @RequestMapping("/toUpdate")
    public String toUpdate(
    		@RequestParam("id") String id,
            ModelMap response) {
        OperSocialAccount operSocialAccount = this.operSocialAccountService.findById(id); 
        response.put("operSocialAccount", operSocialAccount);

		response.put("types", OperSocialAccountTypeEnum.values());
        
        return "operator/updateOperSocialAccount";
    }

    /**
     * 查询操作人员第三方账户实例列表<br/>
     * <功能详细描述>
     * @return [参数说明]
     * 
     * @return List<OperSocialAccount> [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @ResponseBody
    @RequestMapping("/queryList")
    public List<OperSocialAccount> queryList(
    		@RequestParam MultiValueMap<String, String> request
    	) {
        Map<String, Object> params = new HashMap<>();
		params.put("name", request.getFirst("name"));
    	
        List<OperSocialAccount> resList = this.operSocialAccountService.queryList(
			params         
        );
  
        return resList;
    }
    
    /**
     * 查询操作人员第三方账户实例分页列表<br/>
     * <功能详细描述>
     * @return [参数说明]
     * 
     * @return List<OperSocialAccount> [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @ResponseBody
    @RequestMapping("/queryPagedList")
    public PagedList<OperSocialAccount> queryPagedList(
			@RequestParam(value = "pageNumber", required = false, defaultValue = "1") int pageIndex,
            @RequestParam(value = "pageSize", required = false, defaultValue = "10") int pageSize,
            @RequestParam MultiValueMap<String, String> request
    	) {
		Map<String, Object> params = new HashMap<>();
		params.put("name", request.getFirst("name"));

        PagedList<OperSocialAccount> resPagedList = this.operSocialAccountService.queryPagedList(
			params,
			pageIndex,
			pageSize
        );
        return resPagedList;
    }
    
    /**
     * 新增操作人员第三方账户实例
     * <功能详细描述>
     * @param operSocialAccount [参数说明]
     * 
     * @return void [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @ResponseBody
    @RequestMapping("/add")
    public boolean add(OperSocialAccount operSocialAccount) {
        this.operSocialAccountService.insert(operSocialAccount);
        return true;
    }
    
    /**
     * 更新操作人员第三方账户实例<br/>
     * <功能详细描述>
     * @param operSocialAccount
     * @return [参数说明]
     * 
     * @return boolean [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @ResponseBody
    @RequestMapping("/update")
    public boolean update(OperSocialAccount operSocialAccount) {
        boolean flag = this.operSocialAccountService.updateById(operSocialAccount);
        return flag;
    }
    
    /**
     * 根据主键查询操作人员第三方账户实例<br/> 
     * <功能详细描述>
     * @param id
     * @return [参数说明]
     * 
     * @return boolean [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @ResponseBody
    @RequestMapping("/findById")
    public OperSocialAccount findById(@RequestParam(value = "id") String id) {
        OperSocialAccount operSocialAccount = this.operSocialAccountService.findById(id);
        return operSocialAccount;
    }

    /**
     * 删除操作人员第三方账户实例<br/> 
     * <功能详细描述>
     * @param id
     * @return [参数说明]
     * 
     * @return boolean [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @ResponseBody
    @RequestMapping("/deleteById")
    public boolean deleteById(@RequestParam(value = "id") String id) {
        boolean flag = this.operSocialAccountService.deleteById(id);
        return flag;
    }
    
	/**
     * 校验是否重复<br/>
	 * @param excludeId
     * @param params
     * @return [参数说明]
     * 
     * @return boolean [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @ResponseBody
    @RequestMapping("/validate")
    public Map<String, String> validate(
            @RequestParam(value = "id", required = false) String excludeId,
            @RequestParam Map<String, String> params) {
        params.remove("id");
        boolean flag = this.operSocialAccountService.exists(params, excludeId);
        
        Map<String, String> resMap = new HashMap<String, String>();
        if (!flag) {
            resMap.put("ok", "");
        } else {
            resMap.put("error", "重复值");
        }
        return resMap;
    }
    
}