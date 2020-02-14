/*
 * 描       述:  <描述>
 * 修  改 人:  
 * 修改时间:
 * <修改描述:>
 */
package com.tx.local.message.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.tx.core.paged.model.PagedList;
import com.tx.core.querier.model.Querier;
import com.tx.local.message.facade.NoticeMessageFacade;
import com.tx.local.message.model.NoticeMessage;
import com.tx.local.message.service.NoticeMessageService;

import io.swagger.annotations.Api;

/**
 * 公告消息API控制层[NoticeMessageAPIController]<br/>
 * 
 * @author []
 * @version [版本号]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
@RestController
@Api(tags = "公告消息API")
@RequestMapping("/api/noticeMessage")
public class NoticeMessageAPIController implements NoticeMessageFacade {
    
    //公告消息业务层
    @Resource(name = "noticeMessageService")
    private NoticeMessageService noticeMessageService;
    
    /**
     * 新增公告消息<br/>
     * <功能详细描述>
     * @param noticeMessage [参数说明]
     * 
     * @return void [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @Override
    public NoticeMessage insert(@RequestBody NoticeMessage noticeMessage) {
        this.noticeMessageService.insert(noticeMessage);
        return noticeMessage;
    }
    
    /**
     * 根据id删除公告消息<br/> 
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
        boolean flag = this.noticeMessageService.deleteById(id);
        return flag;
    }
    
    /**
     * 更新公告消息<br/>
     * <功能详细描述>
     * @param noticeMessage
     * @return [参数说明]
     * 
     * @return boolean [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @Override
    public boolean updateById(@PathVariable(value = "id",required=true) String id,
    		@RequestBody NoticeMessage noticeMessage) {
        boolean flag = this.noticeMessageService.updateById(id,noticeMessage);
        return flag;
    }
    
    /**
     * 禁用公告消息<br/>
     * @param id
     * @return [参数说明]
     * 
     * @return boolean [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
	@Override
    public boolean disableById(
    		@PathVariable(value = "id", required = true) String id) {
        boolean flag = this.noticeMessageService.disableById(id);
        return flag;
    }
    
    /**
     * 启用公告消息<br/>
     * <功能详细描述>
     * @param id
     * @return [参数说明]
     * 
     * @return boolean [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @Override
    public boolean enableById(
    		@PathVariable(value = "id", required = true) String id) {
        boolean flag = this.noticeMessageService.enableById(id);
        return flag;
    }

    /**
     * 根据主键查询公告消息<br/>
     * <功能详细描述>
     * @return [参数说明]
     * 
     * @return NoticeMessage [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @Override
    public NoticeMessage findById(
            @PathVariable(value = "id", required = true) String id) {
        NoticeMessage res = this.noticeMessageService.findById(id);
        
        return res;
    }

    /**
     * 查询公告消息实例列表<br/>
     * <功能详细描述>
     * @param valid
     * @param querier
     * @return [参数说明]
     * 
     * @return List<NoticeMessage> [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @Override
    public List<NoticeMessage> queryList(
			@RequestParam(value = "valid", required = false) Boolean valid,
    		@RequestBody Querier querier
    	) {
        List<NoticeMessage> resList = this.noticeMessageService.queryList(
			valid,
			querier         
        );
  
        return resList;
    }
    
    /**
     * 查询公告消息分页列表<br/>
     * <功能详细描述>
     * @param valid
     * @param pageIndex
     * @param pageSize
     * @param querier
     * @return [参数说明]
     * 
     * @return PagedList<NoticeMessage> [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @Override
    public PagedList<NoticeMessage> queryPagedList(
			@RequestParam(value = "valid", required = false) Boolean valid,
			@RequestBody Querier querier,
			@PathVariable(value = "pageNumber", required = true) int pageIndex,
            @PathVariable(value = "pageSize", required = true) int pageSize
    	) {
        PagedList<NoticeMessage> resPagedList = this.noticeMessageService.queryPagedList(
			valid,
			querier,
			pageIndex,
			pageSize
        );
        return resPagedList;
    }
    
	/**
     * 查询公告消息数量<br/>
     * <功能详细描述>
     * @param valid
     * @param querier
     * @return [参数说明]
     * 
     * @return int [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @Override
    public int count(
			@RequestParam(value = "valid", required = false) Boolean valid,
            @RequestBody Querier querier) {
        int count = this.noticeMessageService.count(
			valid,
        	querier);
        
        return count;
    }

	/**
     * 查询公告消息是否存在<br/>
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
        boolean flag = this.noticeMessageService.exists(querier, excludeId);
        
        return flag;
    }
}