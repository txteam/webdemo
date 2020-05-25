/*
 * 描       述:  <描述>
 * 修  改 人:  
 * 修改时间:
 * <修改描述:>
 */
package com.tx.local.vitualcenter.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.tx.core.paged.model.PagedList;
import com.tx.core.querier.model.Querier;
import com.tx.local.vitualcenter.facade.ConfigItem4VCFacade;
import com.tx.local.vitualcenter.model.ConfigItem4VC;
import com.tx.local.vitualcenter.service.ConfigItem4VCService;

import io.swagger.annotations.Api;

/**
 * ConfigItem4VCAPI控制层[ConfigItem4VCAPIController]<br/>
 * 
 * @author []
 * @version [版本号]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
@RestController
@Api(tags = "ConfigItem4VCAPI")
@RequestMapping("/api/configItem4VC")
public class ConfigItem4VCAPIController implements ConfigItem4VCFacade {
    
    //ConfigItem4VC业务层
    @Resource(name = "configItem4VCService")
    private ConfigItem4VCService configItem4VCService;
    
    /**
     * 新增ConfigItem4VC<br/>
     * <功能详细描述>
     * @param configItem4VC [参数说明]
     * 
     * @return void [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @Override
    public ConfigItem4VC insert(@RequestBody ConfigItem4VC configItem4VC) {
        this.configItem4VCService.insert(configItem4VC);
        return configItem4VC;
    }
    
    /**
     * 根据id删除ConfigItem4VC<br/> 
     * <功能详细描述>
     * @param id
     * @return [参数说明]
     * 
     * @return boolean [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @Override
    public boolean deleteById(
    		@PathVariable(value = "id",required=true) String id) {
        boolean flag = this.configItem4VCService.deleteById(id);
        return flag;
    }
	
	/**
     * 根据code删除ConfigItem4VC<br/> 
     * <功能详细描述>
     * @param code
     * @return [参数说明]
     * 
     * @return boolean [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @Override
    public boolean deleteByCode(
    		@PathVariable(value = "code",required=true) String code){
        boolean flag = this.configItem4VCService.deleteByCode(code);
        return flag;    
    }
    
    /**
     * 更新ConfigItem4VC<br/>
     * <功能详细描述>
     * @param configItem4VC
     * @return [参数说明]
     * 
     * @return boolean [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @Override
    public boolean updateById(@PathVariable(value = "id",required=true) String id,
    		@RequestBody ConfigItem4VC configItem4VC) {
        boolean flag = this.configItem4VCService.updateById(id,configItem4VC);
        return flag;
    }
    

    /**
     * 根据主键查询ConfigItem4VC<br/>
     * <功能详细描述>
     * @return [参数说明]
     * 
     * @return ConfigItem4VC [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @Override
    public ConfigItem4VC findById(
            @PathVariable(value = "id", required = true) String id) {
        ConfigItem4VC res = this.configItem4VCService.findById(id);
        
        return res;
    }

    /**
     * 根据编码查询ConfigItem4VC<br/>
     * <功能详细描述>
     * @return [参数说明]
     * 
     * @return ConfigItem4VC [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @Override
    public ConfigItem4VC findByCode(
            @PathVariable(value = "code", required = true) String code) {
        ConfigItem4VC res = this.configItem4VCService.findByCode(code);
        
        return res;
    }

    /**
     * 查询ConfigItem4VC实例列表<br/>
     * <功能详细描述>
     * @param querier
     * @return [参数说明]
     * 
     * @return List<ConfigItem4VC> [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @Override
    public List<ConfigItem4VC> queryList(
    		@RequestBody Querier querier
    	) {
        List<ConfigItem4VC> resList = this.configItem4VCService.queryList(
			querier         
        );
  
        return resList;
    }
    
    /**
     * 查询ConfigItem4VC分页列表<br/>
     * <功能详细描述>
     * @param pageIndex
     * @param pageSize
     * @param querier
     * @return [参数说明]
     * 
     * @return PagedList<ConfigItem4VC> [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @Override
    public PagedList<ConfigItem4VC> queryPagedList(
			@RequestBody Querier querier,
			@PathVariable(value = "pageNumber", required = true) int pageIndex,
            @PathVariable(value = "pageSize", required = true) int pageSize
    	) {
        PagedList<ConfigItem4VC> resPagedList = this.configItem4VCService.queryPagedList(
			querier,
			pageIndex,
			pageSize
        );
        return resPagedList;
    }
    
	/**
     * 查询ConfigItem4VC数量<br/>
     * <功能详细描述>
     * @param querier
     * @return [参数说明]
     * 
     * @return int [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @Override
    public int count(
            @RequestBody Querier querier) {
        int count = this.configItem4VCService.count(
        	querier);
        
        return count;
    }

	/**
     * 查询ConfigItem4VC是否存在<br/>
	 * @param excludeId
     * @param querier
     * @return [参数说明]
     * 
     * @return boolean [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @Override
    public boolean exists(@RequestBody Querier querier,
            @RequestParam(value = "excludeId", required = false) String excludeId) {
        boolean flag = this.configItem4VCService.exists(querier, excludeId);
        
        return flag;
    }

	/**
     * 根据条件查询基础数据分页列表<br/>
     * <功能详细描述>
     * @param parentId
     * @param querier
     * @return [参数说明]
     * 
     * @return PagedList<T> [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @Override
    public List<ConfigItem4VC> queryChildrenByParentId(@PathVariable(value = "parentId", required = true) String parentId,
            Querier querier){
        List<ConfigItem4VC> resList = this.configItem4VCService.queryChildrenByParentId(parentId,
			querier         
        );
  
        return resList;
    }

	/**
     * 根据条件查询基础数据分页列表<br/>
     * <功能详细描述>
     * @param parentId
     * @param querier
     * @return [参数说明]
     * 
     * @return PagedList<T> [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @Override
    public List<ConfigItem4VC> queryDescendantsByParentId(@PathVariable(value = "parentId", required = true) String parentId,
            Querier querier){
        List<ConfigItem4VC> resList = this.configItem4VCService.queryDescendantsByParentId(parentId,
			querier         
        );
  
        return resList;
    }
}