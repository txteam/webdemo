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

import com.tx.local.operator.model.OperSecurityAccount;
import com.tx.local.operator.service.OperSecurityAccountService;
import com.tx.core.paged.model.PagedList;

import com.tx.local.basicdata.model.IDCardTypeEnum;

/**
 * 操作人员账户安全设置控制层<br/>
 * 
 * @author []
 * @version [版本号]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
@Controller
@RequestMapping("/operSecurityAccount")
public class OperSecurityAccountController {
    
    //操作人员账户安全设置业务层
    @Resource(name = "operSecurityAccountService")
    private OperSecurityAccountService operSecurityAccountService;
    
    /**
     * 跳转到查询操作人员账户安全设置分页列表页面<br/>
     * <功能详细描述>
     * @return [参数说明]
     * 
     * @return String [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @RequestMapping("/toQueryPagedList")
    public String toQueryPagedList(ModelMap response) {
		response.put("idCardTypes", IDCardTypeEnum.values());

        return "operator/queryOperSecurityAccountPagedList";
    }
    
    /**
     * 跳转到新增操作人员账户安全设置页面<br/>
     * <功能详细描述>
     * @return [参数说明]
     * 
     * @return String [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @RequestMapping("/toAdd")
    public String toAdd(ModelMap response) {
    	response.put("operSecurityAccount", new OperSecurityAccount());
    	
		response.put("idCardTypes", IDCardTypeEnum.values());

        return "operator/addOperSecurityAccount";
    }
    
    /**
     * 跳转到编辑操作人员账户安全设置页面
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
        OperSecurityAccount operSecurityAccount = this.operSecurityAccountService.findById(id); 
        response.put("operSecurityAccount", operSecurityAccount);

		response.put("idCardTypes", IDCardTypeEnum.values());
        
        return "operator/updateOperSecurityAccount";
    }

    /**
     * 查询操作人员账户安全设置实例列表<br/>
     * <功能详细描述>
     * @return [参数说明]
     * 
     * @return List<OperSecurityAccount> [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @ResponseBody
    @RequestMapping("/queryList")
    public List<OperSecurityAccount> queryList(
    		@RequestParam MultiValueMap<String, String> request
    	) {
        Map<String, Object> params = new HashMap<>();
		params.put("name", request.getFirst("name"));
    	
        List<OperSecurityAccount> resList = this.operSecurityAccountService.queryList(
			params         
        );
  
        return resList;
    }
    
    /**
     * 查询操作人员账户安全设置实例分页列表<br/>
     * <功能详细描述>
     * @return [参数说明]
     * 
     * @return List<OperSecurityAccount> [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @ResponseBody
    @RequestMapping("/queryPagedList")
    public PagedList<OperSecurityAccount> queryPagedList(
			@RequestParam(value = "pageNumber", required = false, defaultValue = "1") int pageIndex,
            @RequestParam(value = "pageSize", required = false, defaultValue = "10") int pageSize,
            @RequestParam MultiValueMap<String, String> request
    	) {
		Map<String, Object> params = new HashMap<>();
		params.put("name", request.getFirst("name"));

        PagedList<OperSecurityAccount> resPagedList = this.operSecurityAccountService.queryPagedList(
			params,
			pageIndex,
			pageSize
        );
        return resPagedList;
    }
    
    /**
     * 新增操作人员账户安全设置实例
     * <功能详细描述>
     * @param operSecurityAccount [参数说明]
     * 
     * @return void [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @ResponseBody
    @RequestMapping("/add")
    public boolean add(OperSecurityAccount operSecurityAccount) {
        this.operSecurityAccountService.insert(operSecurityAccount);
        return true;
    }
    
    /**
     * 更新操作人员账户安全设置实例<br/>
     * <功能详细描述>
     * @param operSecurityAccount
     * @return [参数说明]
     * 
     * @return boolean [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @ResponseBody
    @RequestMapping("/update")
    public boolean update(OperSecurityAccount operSecurityAccount) {
        boolean flag = this.operSecurityAccountService.updateById(operSecurityAccount);
        return flag;
    }
    
    /**
     * 根据主键查询操作人员账户安全设置实例<br/> 
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
    public OperSecurityAccount findById(@RequestParam(value = "id") String id) {
        OperSecurityAccount operSecurityAccount = this.operSecurityAccountService.findById(id);
        return operSecurityAccount;
    }

    /**
     * 删除操作人员账户安全设置实例<br/> 
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
        boolean flag = this.operSecurityAccountService.deleteById(id);
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
        boolean flag = this.operSecurityAccountService.exists(params, excludeId);
        
        Map<String, String> resMap = new HashMap<String, String>();
        if (!flag) {
            resMap.put("ok", "");
        } else {
            resMap.put("error", "重复值");
        }
        return resMap;
    }
    
}