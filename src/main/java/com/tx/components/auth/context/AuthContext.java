/*
 * 描          述:  <描述>
 * 修  改   人:  brady
 * 修改时间:  2012-11-30
 * <修改描述:>
 */
package com.tx.components.auth.context;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.cxf.common.util.StringUtils;
import org.apache.ibatis.io.ResolverUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ConfigurableApplicationContext;

import com.tx.components.auth.AuthConstant;
import com.tx.components.auth.model.AuthItemRef;
import com.tx.components.auth.service.AuthChecker;
import com.tx.components.auth.service.AuthLoader;
import com.tx.components.auth.service.AuthService;
import com.tx.components.auth.service.impl.XmlAuthLoader;

/**
 * <权限容器> <功能详细描述>
 * 
 * @author PengQingyang
 * @version [版本号, 2012-12-1]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
public class AuthContext implements FactoryBean<AuthContext>,
        ApplicationContextAware, InitializingBean {
    
    /** 日志记录器 */
    private static final Logger logger = LoggerFactory.getLogger(AuthContext.class);
    
    /** 懒汉模式工厂实例  */
    private static final AuthContext context = new AuthContext();
    
    /** 业务日志记录器：默认使用logback日志记录器  */
    private Logger serviceLogger = LoggerFactory.getLogger(AuthContext.class);
    
    /** 当前spring容器 */
    private ApplicationContext applicationContext;
    
    /** 权限业务逻辑层 */
    private AuthService authService;
    
    /** 权限检查器实现 */
    private List<AuthChecker> authCheckers;
    
    /** 权限加载器 : 默认为通过xml配置加载权限，*/
    private AuthLoader authLoader = new XmlAuthLoader();
    
    /**
     * 权限检查器映射，以权限
     * 权限类型检查器默认会添加几个检查器 用户自定义添加的权限检查器会覆盖该检查器
     */
    private static Map<String, AuthChecker> authCheckMapping = new HashMap<String, AuthChecker>();
    
    /**
     * 线程变量:当前会话容器，获取到该容器后可以，获取当前回话的session从而获取到相应的权限列表
     */
    private static ThreadLocal<CurrentSessionContext> currentSessionContext = new ThreadLocal<CurrentSessionContext>() {
        /**
         * @return
         */
        @Override
        protected CurrentSessionContext initialValue() {
            CurrentSessionContext csContext = new CurrentSessionContext();
            return csContext;
        }
    };
    
    /**
     * ApplicationContextAware接口实现，用以获取spring容器引用
     * @param context
     * @throws BeansException
     */
    @Override
    public void setApplicationContext(ApplicationContext context)
            throws BeansException {
        this.applicationContext = context;
    }
    
    /**
     * @return
     * @throws Exception
     */
    @Override
    public AuthContext getObject() throws Exception {
        return AuthContext.context;
    }
    
    /**
     * @return
     */
    @Override
    public Class<?> getObjectType() {
        return AuthContext.class;
    }
    
    /**
     * @return
     */
    @Override
    public boolean isSingleton() {
        return true;
    }
    
    /**
     * InitializingBean接口的实现，用以在容器参数设置完成后加载相关权限
     * @throws Exception
     */
    @Override
    public void afterPropertiesSet() throws Exception {
        //自动加载容器中实现的权限检查器
        //提供，3个默认的权限检查器
        loadAuthChecker();
        
        //加载系统配置的权限
        //TODO:reloadAuthItemConfig 以后提供重新加载功能
        this.authService.loadAuthItemConfig();
    }
    
    /**
     * 加载检查器<br/>
     *  <功能详细描述> [参数说明]
     * 
     * @return void [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    private void loadAuthChecker() {
        Set<AuthChecker> authCheckers = new HashSet<AuthChecker>();
        
        //加入默认的权限检查器
        authCheckers.add(new operate);
        
        
    }
    
    /**
     * 将当前会话绑定到线程中
     * <功能详细描述>
     * @param request
     * @param response [参数说明]
     * 
     * @return void [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
    */
    public void bindCurrentSessionToThread(HttpServletRequest request,
            HttpServletResponse response) {
        //绑定线程前先remove一次，以保证不会残留上一次的会话，虽然不是特别需要，也不会占用太多资源
        currentSessionContext.remove();
        //将当前会话绑定到现成中
        currentSessionContext.get().install(request, response);
    }
    
    /**
      * 从当前线程中移除当前会话
      * <功能详细描述> [参数说明]
      * 
      * @return void [返回类型说明]
      * @exception throws [异常类型] [异常说明]
      * @see [类、类#方法、类#成员]
     */
    public void removeCurrentSessionFromThread() {
        currentSessionContext.get().uninstall();
        currentSessionContext.remove();
    }
    
    /**
      * 从当前现成中获取到当前会话<br/>
      * 该会话可能为空
      * <功能详细描述>
      * @return [参数说明]
      * 
      * @return CurrentSessionContext [返回类型说明]
      * @exception throws [异常类型] [异常说明]
      * @see [类、类#方法、类#成员]
     */
    public CurrentSessionContext getCurrentSessionContext() {
        return currentSessionContext.get();
    }
    
    /**
     * 登录时初始化当前登录人的权限容器，权限容器放入session中<br/>
     * 请求进入后将对应的权限容器放入线程中以备后续调用 <功能详细描述>
     * 
     * @param operatorId
     * @return [参数说明]
     * 
     * @return List<AuthItemRef> [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    public void initCurrentUserAuthContextWhenLogin(String operatorId) {
        List<AuthItemRef> authItemRefList = authService.queryAuthItemRefSetByOperatorId(operatorId);
        getCurrentSessionContext().setCurrentOperatorAuthToSession(new HashSet<AuthItemRef>(
                authItemRefList));
    }
    
    /**
     * <判断是否具有某权限> authType:除定制的几类权限特性以外， 可以为 业务权限 产品权限
     * 这里权限验证会根据当前会话以及对应的权限验证器判断是否具有对应权限
     * 
     * @param authKey
     * @param authType
     * @param objects
     *            判断权限传入的参数，可以为业务ID，可以为等等。。。。 该参数会直接传入对应的authChecker中
     * @return [参数说明]
     * 
     * @return boolean [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    private boolean isHasAuth(String authKey, String authType,
            Object... objects) {
        return authCheckMapping.get(authType).isHasAuth(authKey, objects);
    }
    
    /**
     * <判断操作权限> 
     * <功能详细描述>
     * 
     * @param authKey
     * @return [参数说明]
     * 
     * @return boolean [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    public boolean isHasOperateAuth(String authKey) {
        return isHasAuth(authKey, AuthConstant.TYPE_OPERATE);
    }
    
    /**
     * <判断是否具有某数据列权限> <功能详细描述>
     * 
     * @param authKey
     * @return [参数说明]
     * 
     * @return boolean [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    public boolean isHasDataColumnAuth(String authKey) {
        return isHasAuth(authKey, AuthConstant.TYPE_DATA_COLUMN);
    }
    
    /**
     * <判断是否具有某数据行权限> <功能详细描述>
     * 
     * @param authKey
     * @return [参数说明]
     * 
     * @return boolean [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    public boolean isHasDataRowAuth(String authKey) {
        return isHasAuth(authKey, AuthConstant.TYPE_DATA_ROW);
    }
    
    /**
     * <功能简述> <功能详细描述>
     * 
     * @param packageName
     * @return [参数说明]
     * 
     * @return Set<Class<? extends AuthChecker>> [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    private Set<Class<? extends AuthChecker>> loadConfigAuthCheckerByPackage(
            String packageName) {
        ResolverUtil<AuthChecker> resolverUtil = new ResolverUtil<AuthChecker>();
        
        resolverUtil.find(new ResolverUtil.IsA(AuthChecker.class), packageName);
        Set<Class<? extends AuthChecker>> handlerSet = resolverUtil.getClasses();
        
        return handlerSet;
    }
}
