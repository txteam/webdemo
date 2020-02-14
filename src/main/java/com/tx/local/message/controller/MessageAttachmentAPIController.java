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
import com.tx.local.message.facade.MessageAttachmentFacade;
import com.tx.local.message.model.MessageAttachment;
import com.tx.local.message.service.MessageAttachmentService;

import io.swagger.annotations.Api;

/**
 * 消息附件API控制层[MessageAttachmentAPIController]<br/>
 * 
 * @author []
 * @version [版本号]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
@RestController
@Api(tags = "消息附件API")
@RequestMapping("/api/messageAttachment")
public class MessageAttachmentAPIController implements MessageAttachmentFacade {
    
    //消息附件业务层
    @Resource(name = "messageAttachmentService")
    private MessageAttachmentService messageAttachmentService;
    
    /**
     * 新增消息附件<br/>
     * <功能详细描述>
     * @param messageAttachment [参数说明]
     * 
     * @return void [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @Override
    public MessageAttachment insert(@RequestBody MessageAttachment messageAttachment) {
        this.messageAttachmentService.insert(messageAttachment);
        return messageAttachment;
    }
    
    /**
     * 根据id删除消息附件<br/> 
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
        boolean flag = this.messageAttachmentService.deleteById(id);
        return flag;
    }
    
    /**
     * 更新消息附件<br/>
     * <功能详细描述>
     * @param messageAttachment
     * @return [参数说明]
     * 
     * @return boolean [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @Override
    public boolean updateById(@PathVariable(value = "id",required=true) String id,
    		@RequestBody MessageAttachment messageAttachment) {
        boolean flag = this.messageAttachmentService.updateById(id,messageAttachment);
        return flag;
    }
    

    /**
     * 根据主键查询消息附件<br/>
     * <功能详细描述>
     * @return [参数说明]
     * 
     * @return MessageAttachment [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @Override
    public MessageAttachment findById(
            @PathVariable(value = "id", required = true) String id) {
        MessageAttachment res = this.messageAttachmentService.findById(id);
        
        return res;
    }

    /**
     * 查询消息附件实例列表<br/>
     * <功能详细描述>
     * @param querier
     * @return [参数说明]
     * 
     * @return List<MessageAttachment> [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @Override
    public List<MessageAttachment> queryList(
    		@RequestBody Querier querier
    	) {
        List<MessageAttachment> resList = this.messageAttachmentService.queryList(
			querier         
        );
  
        return resList;
    }
    
    /**
     * 查询消息附件分页列表<br/>
     * <功能详细描述>
     * @param pageIndex
     * @param pageSize
     * @param querier
     * @return [参数说明]
     * 
     * @return PagedList<MessageAttachment> [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @Override
    public PagedList<MessageAttachment> queryPagedList(
			@RequestBody Querier querier,
			@PathVariable(value = "pageNumber", required = true) int pageIndex,
            @PathVariable(value = "pageSize", required = true) int pageSize
    	) {
        PagedList<MessageAttachment> resPagedList = this.messageAttachmentService.queryPagedList(
			querier,
			pageIndex,
			pageSize
        );
        return resPagedList;
    }
    
	/**
     * 查询消息附件数量<br/>
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
        int count = this.messageAttachmentService.count(
        	querier);
        
        return count;
    }

	/**
     * 查询消息附件是否存在<br/>
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
        boolean flag = this.messageAttachmentService.exists(querier, excludeId);
        
        return flag;
    }
}