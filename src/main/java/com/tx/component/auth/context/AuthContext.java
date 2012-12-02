/*
 * 描          述:  <描述>
 * 修  改   人:  brady
 * 修改时间:  2012-11-30
 * <修改描述:>
 */
package com.tx.component.auth.context;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.tx.component.auth.AuthConstant;
import com.tx.component.auth.service.AuthChecker;
import com.tx.component.auth.service.AuthService;

/**
  * <权限容器>
  * <功能详细描述>
  * 
  * @author  PengQingyang
  * @version  [版本号, 2012-12-1]
  * @see  [相关类/方法]
  * @since  [产品/模块版本]
 */
public class AuthContext {
    
    @SuppressWarnings("unused")
    private Logger logger = LoggerFactory.getLogger(AuthContext.class);
    
    /** 权限业务逻辑层 */
    private AuthService authService;
    
    /**
     * 权限类型检查器默认会添加几个检查器
     * 用户自定义添加的权限检查器会覆盖该检查器
     */
    private Map<String, AuthChecker> authCheckMapping = new HashMap<String, AuthChecker>();
    
    /**
      * <登录时初始化当前登录人的权限容器，权限容器放入session中>
      * 请求进入后将对应的权限容器放入线程中以备后续调用
      * <功能详细描述>
      * @param operatorId
      * @return [参数说明]
      * 
      * @return List<AuthItemRef> [返回类型说明]
      * @exception throws [异常类型] [异常说明]
      * @see [类、类#方法、类#成员]
     */
    public static void initCurrentUserAuthContextWhenLogin(String operatorId) {
        
    }
    
    /**
      *<判断是否具有某权限>
      * authType:除定制的几类权限特性以外，
      * 可以为
      *     业务权限
      *     产品权限
      *     这里权限验证会根据当前会话以及对应的权限验证器判断是否具有对应权限
      * @param authKey
      * @param authType
      * @param objects  
      *     判断权限传入的参数，可以为业务ID，可以为等等。。。。
      *     该参数会直接传入对应的authChecker中
      * @return [参数说明]
      * 
      * @return boolean [返回类型说明]
      * @exception throws [异常类型] [异常说明]
      * @see [类、类#方法、类#成员]
     */
    private static boolean isHasAuth(String authKey, String authType,
            Object... objects) {
        
        return false;
    }
    
    /**
      *<判断操作权限>
      *<功能详细描述>
      * @param authKey
      * @return [参数说明]
      * 
      * @return boolean [返回类型说明]
      * @exception throws [异常类型] [异常说明]
      * @see [类、类#方法、类#成员]
     */
    public static boolean isHasOperateAuth(String authKey) {
        return isHasAuth(authKey, AuthConstant.TYPE_OPERATE);
    }
    
    /**
      *<判断是否具有某数据列权限>
      *<功能详细描述>
      * @param authKey
      * @return [参数说明]
      * 
      * @return boolean [返回类型说明]
      * @exception throws [异常类型] [异常说明]
      * @see [类、类#方法、类#成员]
     */
    public static boolean isHasDataColumnAuth(String authKey) {
        return isHasAuth(authKey, AuthConstant.TYPE_DATA_COLUMN);
    }
    
    /**
      *<判断是否具有某数据行权限>
      *<功能详细描述>
      * @param authKey
      * @return [参数说明]
      * 
      * @return boolean [返回类型说明]
      * @exception throws [异常类型] [异常说明]
      * @see [类、类#方法、类#成员]
     */
    public static boolean isHasDataRowAuth(String authKey) {
        return isHasAuth(authKey, AuthConstant.TYPE_DATA_ROW);
    }
}
