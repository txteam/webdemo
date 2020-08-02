/*
 * 描       述:  <描述>
 * 修  改 人:  
 * 修改时间:
 * <修改描述:>
 */
package com.tx.local.personal.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.tx.core.paged.model.PagedList;
import com.tx.core.querier.model.Querier;
import com.tx.local.personal.facade.PersonalInfoFacade;
import com.tx.local.personal.model.PersonalInfo;
import com.tx.local.personal.service.PersonalInfoService;

import io.swagger.annotations.Api;

/**
 * PersonalInfoAPI控制层[PersonalInfoAPIController]<br/>
 * 
 * @author []
 * @version [版本号]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
@RestController
@Api(tags = "PersonalInfoAPI")
@RequestMapping("/api/personalInfo")
public class PersonalInfoAPIController implements PersonalInfoFacade {
    
    //PersonalInfo业务层
    @Resource(name = "personalInfoService")
    private PersonalInfoService personalInfoService;
    
    /**
     * 新增PersonalInfo<br/>
     * <功能详细描述>
     * @param personalInfo [参数说明]
     * 
     * @return void [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @Override
    public PersonalInfo insert(@RequestBody PersonalInfo personalInfo) {
        this.personalInfoService.insert(personalInfo);
        return personalInfo;
    }
    
    /**
     * 根据id删除PersonalInfo<br/> 
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
            @PathVariable(value = "id", required = true) String id) {
        boolean flag = this.personalInfoService.deleteById(id);
        return flag;
    }
    
    /**
     * 更新PersonalInfo<br/>
     * <功能详细描述>
     * @param personalInfo
     * @return [参数说明]
     * 
     * @return boolean [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @Override
    public boolean updateById(
            @PathVariable(value = "id", required = true) String id,
            @RequestBody PersonalInfo personalInfo) {
        boolean flag = this.personalInfoService.updateById(id, personalInfo);
        return flag;
    }
    
    /**
     * 根据主键查询PersonalInfo<br/>
     * <功能详细描述>
     * @return [参数说明]
     * 
     * @return PersonalInfo [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @Override
    public PersonalInfo findById(
            @PathVariable(value = "id", required = true) String id) {
        PersonalInfo res = this.personalInfoService.findById(id);
        
        return res;
    }
    
    /**
     * 查询PersonalInfo实例列表<br/>
     * <功能详细描述>
     * @param querier
     * @return [参数说明]
     * 
     * @return List<PersonalInfo> [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @Override
    public List<PersonalInfo> queryList(@RequestBody Querier querier) {
        List<PersonalInfo> resList = this.personalInfoService
                .queryList(querier);
        
        return resList;
    }
    
    /**
     * 查询PersonalInfo分页列表<br/>
     * <功能详细描述>
     * @param pageIndex
     * @param pageSize
     * @param querier
     * @return [参数说明]
     * 
     * @return PagedList<PersonalInfo> [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @Override
    public PagedList<PersonalInfo> queryPagedList(@RequestBody Querier querier,
            @PathVariable(value = "pageNumber", required = true) int pageIndex,
            @PathVariable(value = "pageSize", required = true) int pageSize) {
        PagedList<PersonalInfo> resPagedList = this.personalInfoService
                .queryPagedList(querier, pageIndex, pageSize);
        return resPagedList;
    }
    
    /**
     * 查询PersonalInfo数量<br/>
     * <功能详细描述>
     * @param querier
     * @return [参数说明]
     * 
     * @return int [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @Override
    public int count(@RequestBody Querier querier) {
        int count = this.personalInfoService.count(querier);
        
        return count;
    }
    
    /**
     * 查询PersonalInfo是否存在<br/>
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
        boolean flag = this.personalInfoService.exists(querier, excludeId);
        
        return flag;
    }
}