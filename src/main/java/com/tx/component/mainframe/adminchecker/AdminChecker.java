/*
 * 描          述:  <描述>
 * 修  改   人:  PengQingyang
 * 修改时间:  2013-9-8
 * <修改描述:>
 */
package com.tx.component.mainframe.adminchecker;

import org.springframework.stereotype.Component;

import com.tx.component.auth.AuthConstant;

/**
 * <功能简述>
 * <功能详细描述>
 * 
 * @author  PengQingyang
 * @version  [版本号, 2013-9-8]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
@Component
public class AdminChecker implements
        com.tx.component.auth.context.adminchecker.AdminChecker {
    
    /**
     * @return
     */
    @Override
    public String refType() {
        return AuthConstant.AUTHREFTYPE_OPERATOR;
    }
    
    /**
     * @param refId
     * @return
     */
    @Override
    public boolean isSuperAdmin(String refId) {
        if("123456".equals(refId)){
            return true;
        }
        
        return false;
    }
    
}
