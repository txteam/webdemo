/*
 * 描          述:  <描述>
 * 修  改   人:  PengQingyang
 * 修改时间:  2013-8-26
 * <修改描述:>
 */
package com.tx.component.mainframe.controller;

import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Controller;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tx.component.auth.context.AuthSessionContext;
import com.tx.component.auth.context.AuthTypeItemContext;
import com.tx.component.auth.model.AuthItem;
import com.tx.component.auth.model.AuthTypeItem;

/**
 * 权限显示层
 * <功能详细描述>
 * 
 * @author  PengQingyang
 * @version  [版本号, 2013-8-26]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
@Controller
@RequestMapping("/auth")
public class AuthController {
    
    /**
      * 跳转到查询权限视图<br/>
      *<功能详细描述>
      * @return [参数说明]
      * 
      * @return String [返回类型说明]
      * @exception throws [异常类型] [异常说明]
      * @see [类、类#方法、类#成员]
     */
    @RequestMapping("/toQueryAuthView")
    public String toQueryAuthView() {
        return "/mainframe/queryAuthView";
    }
    
    /**
      * 根据当前人员权限类型与权限列表<br/> 
      *<功能详细描述>
      * @return [参数说明]
      * 
      * @return List<AuthItem> [返回类型说明]
      * @exception throws [异常类型] [异常说明]
      * @see [类、类#方法、类#成员]
      */
    @ResponseBody
    @RequestMapping("/queryAuthType2AuthItemListMap")
    public Map<AuthTypeItem, List<AuthItem>> queryAuthType2AuthItemListMap() {
        List<AuthItem> hasAuthItemList = AuthSessionContext.getAuthItemListDependAuthRefOfSession();
        
        AuthTypeItemContext authTypeContext = AuthTypeItemContext.getContext();
        Map<String, AuthTypeItem> authTypeMapping = authTypeContext.getAllAuthTypeItemMap();
        MultiValueMap<AuthTypeItem, AuthItem> authType2AuthItemListMap = new LinkedMultiValueMap<AuthTypeItem, AuthItem>();
        if (CollectionUtils.isEmpty(hasAuthItemList)) {
            return authType2AuthItemListMap;
        }
        for (AuthItem authItemTemp : hasAuthItemList) {
            authType2AuthItemListMap.add(authTypeMapping.get(authItemTemp.getAuthType()),
                    authItemTemp);
        }
        return authType2AuthItemListMap;
    }
    
}
