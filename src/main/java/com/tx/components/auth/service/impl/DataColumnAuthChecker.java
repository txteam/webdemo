/*
 * 描          述:  <描述>
 * 修  改   人:  grace
 * 修改时间:  2012-12-10
 * <修改描述:>
 */
package com.tx.components.auth.service.impl;

import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.tx.components.auth.AuthConstant;
import com.tx.components.auth.context.AuthContext;
import com.tx.components.auth.context.CurrentSessionContext;
import com.tx.components.auth.model.AuthItem;
import com.tx.components.auth.model.DefaultAuthItemRef;
import com.tx.components.auth.service.AuthChecker;
import com.tx.components.auth.service.AuthService;

/**
* <功能简述>
* <功能详细描述>
* 
* @author  grace
* @version  [版本号, 2012-12-10]
* @see  [相关类/方法]
* @since  [产品/模块版本]
*/
public class DataColumnAuthChecker implements AuthChecker {
    
    @Resource(name = "authService")
    private AuthService authService;
    
    /**
     * @return
     */
    @Override
    public String getCheckAuthType() {
        return AuthConstant.TYPE_DATA_COLUMN;
    }
    
    /**
     * @param authKey
     * @param objects
     * @return
     */
    @Override
    public boolean isHasAuth(String authKey, Object... objects) {
        //如果当前不在请求回话中认为没有权限
        if (AuthContext.getContext().getCurrentSessionContext() == null
                || AuthContext.getContext()
                        .getCurrentSessionContext()
                        .getSession() == null) {
            return false;
        }
        
        //如果对应authKey中系统中不存在对应权限，认为鉴权失败
        Map<String, AuthItem> getAllSysAuthItem = AuthContext.getContext().getAuthItemMapping();
        
        //获取当前操作人员权限map
        Map<String, DefaultAuthItemRef> currentOperatorAuthRefMap = AuthContext.getContext()
                .getCurrentSessionContext()
                .getCurrentOperatorAuthMapFromSession();
        
        AuthItem item = this.authService.getAuthItemMapping().get(authKey);
        if (item == null
                || !AuthConstant.TYPE_DATA_COLUMN.equals(item.getAuthType())) {
            return false;
        }
        
        HttpSession session = request.getSession();
        List<DefaultAuthItemRef> authItemRefList = (List<DefaultAuthItemRef>) session.getAttribute("authItemRefList");
        if (authItemRefList == null) {
            return false;
        }
        
        for (Object obj : objects) {
            if (authItemRefList.contains(obj)) {
                return true;
            }
        }
        
        return false;
    }
    
}
