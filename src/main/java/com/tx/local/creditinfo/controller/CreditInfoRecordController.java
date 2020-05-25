/*
 * 描       述:  <描述>
 * 修  改 人:  
 * 修改时间:
 * <修改描述:>
 */
package com.tx.local.creditinfo.controller;

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

import com.tx.local.creditinfo.model.CreditInfoRecord;
import com.tx.local.creditinfo.service.CreditInfoRecordService;
import com.tx.core.paged.model.PagedList;

import com.tx.local.basicdata.model.IDCardTypeEnum;
import com.tx.local.creditinfo.context.CreditInfoTypeEnum;

/**
 * CreditInfoRecord控制层<br/>
 * 
 * @author []
 * @version [版本号]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
@Controller
@RequestMapping("/creditInfoRecord")
public class CreditInfoRecordController {
    
    //CreditInfoRecord业务层
    @Resource(name = "creditInfoRecordService")
    private CreditInfoRecordService creditInfoRecordService;
    
    /**
     * 跳转到查询CreditInfoRecord分页列表页面<br/>
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
		response.put("types", CreditInfoTypeEnum.values());

        return "creditinfo/queryCreditInfoRecordPagedList";
    }
    
    /**
     * 跳转到新增CreditInfoRecord页面<br/>
     * <功能详细描述>
     * @return [参数说明]
     * 
     * @return String [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @RequestMapping("/toAdd")
    public String toAdd(ModelMap response) {
    	response.put("creditInfoRecord", new CreditInfoRecord());
    	
		response.put("idCardTypes", IDCardTypeEnum.values());
		response.put("types", CreditInfoTypeEnum.values());

        return "creditinfo/addCreditInfoRecord";
    }
    
    /**
     * 跳转到编辑CreditInfoRecord页面
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
        CreditInfoRecord creditInfoRecord = this.creditInfoRecordService.findById(id); 
        response.put("creditInfoRecord", creditInfoRecord);

		response.put("idCardTypes", IDCardTypeEnum.values());
		response.put("types", CreditInfoTypeEnum.values());
        
        return "creditinfo/updateCreditInfoRecord";
    }

    /**
     * 查询CreditInfoRecord实例列表<br/>
     * <功能详细描述>
     * @return [参数说明]
     * 
     * @return List<CreditInfoRecord> [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @ResponseBody
    @RequestMapping("/queryList")
    public List<CreditInfoRecord> queryList(
    		@RequestParam MultiValueMap<String, String> request
    	) {
        Map<String, Object> params = new HashMap<>();
		params.put("name", request.getFirst("name"));
    	
        List<CreditInfoRecord> resList = this.creditInfoRecordService.queryList(
			params         
        );
  
        return resList;
    }
    
    /**
     * 查询CreditInfoRecord实例分页列表<br/>
     * <功能详细描述>
     * @return [参数说明]
     * 
     * @return List<CreditInfoRecord> [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @ResponseBody
    @RequestMapping("/queryPagedList")
    public PagedList<CreditInfoRecord> queryPagedList(
			@RequestParam(value = "pageNumber", required = false, defaultValue = "1") int pageIndex,
            @RequestParam(value = "pageSize", required = false, defaultValue = "10") int pageSize,
            @RequestParam MultiValueMap<String, String> request
    	) {
		Map<String, Object> params = new HashMap<>();
		params.put("name", request.getFirst("name"));

        PagedList<CreditInfoRecord> resPagedList = this.creditInfoRecordService.queryPagedList(
			params,
			pageIndex,
			pageSize
        );
        return resPagedList;
    }
    
    /**
     * 新增CreditInfoRecord实例
     * <功能详细描述>
     * @param creditInfoRecord [参数说明]
     * 
     * @return void [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @ResponseBody
    @RequestMapping("/add")
    public boolean add(CreditInfoRecord creditInfoRecord) {
        this.creditInfoRecordService.insert(creditInfoRecord);
        return true;
    }
    
    /**
     * 更新CreditInfoRecord实例<br/>
     * <功能详细描述>
     * @param creditInfoRecord
     * @return [参数说明]
     * 
     * @return boolean [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @ResponseBody
    @RequestMapping("/update")
    public boolean update(CreditInfoRecord creditInfoRecord) {
        boolean flag = this.creditInfoRecordService.updateById(creditInfoRecord);
        return flag;
    }
    
    /**
     * 根据主键查询CreditInfoRecord实例<br/> 
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
    public CreditInfoRecord findById(@RequestParam(value = "id") String id) {
        CreditInfoRecord creditInfoRecord = this.creditInfoRecordService.findById(id);
        return creditInfoRecord;
    }

    /**
     * 删除CreditInfoRecord实例<br/> 
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
        boolean flag = this.creditInfoRecordService.deleteById(id);
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
        boolean flag = this.creditInfoRecordService.exists(params, excludeId);
        
        Map<String, String> resMap = new HashMap<String, String>();
        if (!flag) {
            resMap.put("ok", "");
        } else {
            resMap.put("error", "重复值");
        }
        return resMap;
    }
    
}