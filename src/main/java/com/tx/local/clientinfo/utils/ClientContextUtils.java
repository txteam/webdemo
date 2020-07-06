/*
 * 描          述:  <描述>
 * 修  改   人:  PengQingyang
 * 修改时间:  2020年5月31日
 * <修改描述:>
 */
package com.tx.local.clientinfo.utils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import com.tx.core.exceptions.util.AssertUtils;
import com.tx.core.paged.model.PagedList;
import com.tx.core.querier.model.Querier;
import com.tx.core.querier.model.QuerierBuilder;
import com.tx.local.clientinfo.facade.ClientInfoFacade;
import com.tx.local.clientinfo.model.Client;
import com.tx.local.clientinfo.model.ClientAware;
import com.tx.local.clientinfo.model.ClientInfo;

/**
 * 客户容器工具类<br/>
 * <功能详细描述>
 * 
 * @author  PengQingyang
 * @version  [版本号, 2020年5月31日]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public class ClientContextUtils {
    
    /**
     * 为可注入客户的实体注入客户实例<br/>
     * <功能详细描述>
     * @param clientInfoFacade
     * @param clientAware [参数说明]
     * 
     * @return void [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    public static void setup(ClientInfoFacade clientInfoFacade,
            ClientAware clientAware) {
        AssertUtils.notNull(clientAware, "clientAware is null.");
        AssertUtils.notEmpty(clientAware.getClientId(),
                "clientAware.clientId is empty.");
        
        String clientId = clientAware.getClientId();
        Client client = clientInfoFacade.findById(clientId);
        clientAware.setClient(client);
    }
    
    /**
     * 装载客户详情<br/>
     * <功能详细描述>
     * @param clientInfoFacade
     * @param clientAwareList [参数说明]
     * 
     * @return void [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    public static void setup(ClientInfoFacade clientInfoFacade,
            List<? extends ClientAware> clientAwareList) {
        if (CollectionUtils.isEmpty(clientAwareList)) {
            return;
        }
        
        Querier clientQuerier = QuerierBuilder.newInstance().querier();
        List<String> clientIds = clientAwareList.stream().filter(ins -> {
            //客户id不为空的客户id集合
            return !StringUtils.isEmpty(ins.getClientId());
        }).map(ins -> {
            return ins.getClientId();
        }).collect(Collectors.toList());
        clientQuerier.getParams().put("ids", clientIds);
        List<ClientInfo> clientInfo = clientInfoFacade.queryList(null,
                clientQuerier);
        Map<String, Client> clientMap = new HashMap<>();
        clientInfo.forEach(ci -> {
            clientMap.put(ci.getId(), ci);
        });
        
        clientAwareList.forEach(ins -> {
            ins.setClient(clientMap.get(ins.getClientId()));
        });
    }
    
    /**
     * 装载客户详情<br/>
     * <功能详细描述>
     * @param clientInfoFacade
     * @param clientAwareList [参数说明]
     * 
     * @return void [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    public static void setup(ClientInfoFacade clientInfoFacade,
            PagedList<? extends ClientAware> clientAwarePagedList) {
        if (clientAwarePagedList == null
                || CollectionUtils.isEmpty(clientAwarePagedList.getList())) {
            return;
        }
        
        Querier clientQuerier = QuerierBuilder.newInstance().querier();
        List<String> clientIds = clientAwarePagedList.getList()
                .stream()
                .filter(ins -> {
                    //客户id不为空的客户id集合
                    return !StringUtils.isEmpty(ins.getClientId());
                })
                .map(ins -> {
                    return ins.getClientId();
                })
                .collect(Collectors.toList());
        clientQuerier.getParams().put("ids", clientIds);
        List<ClientInfo> clientInfo = clientInfoFacade.queryList(null,
                clientQuerier);
        Map<String, Client> clientMap = new HashMap<>();
        clientInfo.forEach(ci -> {
            clientMap.put(ci.getId(), ci);
        });
        
        clientAwarePagedList.getList().forEach(ins -> {
            ins.setClient(clientMap.get(ins.getClientId()));
        });
    }
}
