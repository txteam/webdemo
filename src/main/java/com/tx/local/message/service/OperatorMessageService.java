/*
 * 描          述:  <描述>
 * 修  改   人:  Administrator
 * 修改时间:  2020年2月2日
 * <修改描述:>
 */
package com.tx.local.message.service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.tx.core.exceptions.util.AssertUtils;
import com.tx.core.paged.model.PagedList;
import com.tx.local.message.model.MsgUserTypeEnum;
import com.tx.local.message.model.PrivateMessage;
import com.tx.local.message.model.PrivateMessageCatalogEnum;
import com.tx.local.message.model.PrivateMessageTypeEnum;

/**
 * 操作人员私信业务层<br/>
 * <功能详细描述>
 * 
 * @author  Administrator
 * @version  [版本号, 2020年2月2日]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
@Component("operatorMessageService")
public class OperatorMessageService {
    
    @Resource
    private PrivateMessageService privateMessageService;
    
    /**
     * 新增私信实例<br/>
     * 将privateMessage插入数据库中保存
     * 1、如果privateMessage 为空时抛出参数为空异常
     * 2、如果privateMessage 中部分必要参数为非法值时抛出参数不合法异常
     * 
     * @param privateMessage [参数说明]
     * @return void [返回类型说明]
     * @exception throws
     * @see [类、类#方法、类#成员]
     */
    @Transactional
    public void insert(String title, String content, String vcid,
            String receiveUserId, String sender,String senderUserId) {
        //验证参数是否合法
        AssertUtils.notEmpty(title, "title is empty.");
        AssertUtils.notEmpty(vcid, "vcid is empty.");
        AssertUtils.notEmpty(receiveUserId, "receiveUserId is empty.");
        
        //为添加的数据需要填入默认值的字段填入默认值
        PrivateMessage pm = new PrivateMessage();
        pm.setTitle(title);
        pm.setContent(content);
        pm.setVcid(vcid);
        Date now = new Date();
        pm.setLastUpdateDate(now);
        pm.setCreateDate(now);
        
        pm.setCatalog(PrivateMessageCatalogEnum.MESSAGE);
        pm.setUserType(MsgUserTypeEnum.OPERATOR);
        pm.setCreateUserId(senderUserId);
        pm.setLastUpdateUserId(senderUserId);
        
        PrivateMessage send = null;
        PrivateMessage receive = (PrivateMessage) pm.clone();
        if (!StringUtils.isEmpty(senderUserId)) {
            send = (PrivateMessage) pm.clone();
            send.setUserId(senderUserId);
            send.setType(PrivateMessageTypeEnum.SEND);
            send.setSender(senderUserId);
            send.setReceiver(receiveUserId);
            //调用数据持久层对实例进行持久化操作
            this.privateMessageService.insert(send);
        }
        
        receive.setUserId(receiveUserId);
        receive.setType(PrivateMessageTypeEnum.RECEIVE);
        receive.setSourceId(send != null ? send.getId() : null);
        send.setSender(sender);
        send.setReceiver(receiveUserId);
        //调用数据持久层对实例进行持久化操作
        this.privateMessageService.insert(receive);
    }
    
    /**
     * 新增私信实例<br/>
     * 将privateMessage插入数据库中保存
     * 1、如果privateMessage 为空时抛出参数为空异常
     * 2、如果privateMessage 中部分必要参数为非法值时抛出参数不合法异常
     * 
     * @param privateMessage [参数说明]
     * @return void [返回类型说明]
     * @exception throws
     * @see [类、类#方法、类#成员]
     */
    @Transactional
    public boolean delete(String id, String operatorId, String vcid) {
        //验证参数是否合法
        AssertUtils.notEmpty(id, "id is empty.");
        AssertUtils.notEmpty(operatorId, "operatorId is empty.");
        AssertUtils.notEmpty(vcid, "vcid is empty.");
        
        //为添加的数据需要填入默认值的字段填入默认值
        PrivateMessage condition = new PrivateMessage();
        condition.setId(id);
        condition.setVcid(vcid);
        condition.setUserId(operatorId);
        condition.setUserType(MsgUserTypeEnum.OPERATOR);
        condition.setCatalog(PrivateMessageCatalogEnum.MESSAGE);
        
        boolean flag = this.privateMessageService.delete(condition);
        return flag;
    }
    
    /**
     * 阅读操作人员私信<br/>
     * <功能详细描述>
     * @param id [参数说明]
     * 
     * @return void [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @Transactional
    public boolean read(String id, String operatorId, String vcid) {
        AssertUtils.notEmpty(id, "id is empty.");
        
        PrivateMessage pm = find(id, operatorId, vcid);
        if (pm == null) {
            return false;
        }
        pm.setUnread(false);
        pm.setReadDate(new Date());
        
        //生成需要更新字段的hashMap
        Map<String, Object> rowMap = new HashMap<String, Object>();
        rowMap.put("id", pm.getId());
        rowMap.put("userId", pm.getUserId());
        rowMap.put("userType", pm.getUserType());
        rowMap.put("vcid", pm.getVcid());
        //需要更新的字段
        Date readDate = new Date();
        rowMap.put("unread", false);
        rowMap.put("readDate", readDate);
        boolean flag = this.privateMessageService.updateById(id, rowMap);
        
        if (!StringUtils.isEmpty(pm.getSourceId())) {
            Map<String, Object> sourceRowMap = new HashMap<String, Object>();
            sourceRowMap.put("id", pm.getId());
            sourceRowMap.put("userId", pm.getUserId());
            sourceRowMap.put("userType", pm.getUserType());
            sourceRowMap.put("vcid", pm.getVcid());
            
            //需要更新的字段
            sourceRowMap.put("unread", false);
            sourceRowMap.put("readDate", readDate);
            this.privateMessageService.updateById(pm.getSourceId(),
                    sourceRowMap);
        }
        return flag;
    }
    
    /**
     * 查询私信详情<br/>
     * <功能详细描述>
     * @param id
     * @param operatorId
     * @param vcid
     * @return [参数说明]
     * 
     * @return PrivateMessage [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    public PrivateMessage find(String id, String operatorId, String vcid) {
        //验证参数是否合法
        AssertUtils.notEmpty(id, "id is empty.");
        AssertUtils.notEmpty(operatorId, "operatorId is empty.");
        AssertUtils.notEmpty(vcid, "vcid is empty.");
        
        //为添加的数据需要填入默认值的字段填入默认值
        PrivateMessage condition = new PrivateMessage();
        condition.setId(id);
        condition.setVcid(vcid);
        condition.setUserId(operatorId);
        condition.setUserType(MsgUserTypeEnum.OPERATOR);
        condition.setCatalog(PrivateMessageCatalogEnum.MESSAGE);
        
        PrivateMessage res = this.privateMessageService.find(condition);
        return res;
    }
    
    /**
     * 查询私信<br/>
     * <功能详细描述>
     * @param operatorId
     * @param vcid
     * @param params
     * @param pageIndex
     * @param pageSize
     * @return [参数说明]
     * 
     * @return PagedList<PrivateMessage> [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    public PagedList<PrivateMessage> queryPagedList(String operatorId,
            String vcid, Map<String, Object> params, int pageIndex,
            int pageSize) {
        AssertUtils.notEmpty(vcid, "vcid is empty.");
        
        params = params == null ? new HashMap<String, Object>() : params;
        params.put("vcid", vcid);
        params.put("userId", operatorId);
        params.put("userType", MsgUserTypeEnum.OPERATOR);
        
        PagedList<PrivateMessage> resPagedList = this.privateMessageService
                .queryPagedList(params, pageIndex, pageSize);
        return resPagedList;
    }
    
    /**
     * 统计操作人员消息业务层<br/>
     * <功能详细描述>
     * @param operatorId
     * @param vcid
     * @param params
     * @return [参数说明]
     * 
     * @return int [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    public int count(String operatorId, String vcid,
            Map<String, Object> params) {
        AssertUtils.notEmpty(vcid, "vcid is empty.");
        
        params = params == null ? new HashMap<String, Object>() : params;
        params.put("vcid", vcid);
        params.put("userId", operatorId);
        params.put("userType", MsgUserTypeEnum.OPERATOR);
        params.put("catalog", PrivateMessageCatalogEnum.MESSAGE);
        
        int count = this.privateMessageService.count(params);
        return count;
    }
    
}
