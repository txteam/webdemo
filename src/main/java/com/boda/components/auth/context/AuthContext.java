/*
 * 描          述:  <描述>
 * 修  改   人:  brady
 * 修改时间:  2012-11-30
 * <修改描述:>
 */
package com.boda.components.auth.context;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.cxf.common.util.StringUtils;
import org.apache.ibatis.io.ResolverUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ConfigurableApplicationContext;

import com.boda.components.auth.AuthConstant;
import com.boda.components.auth.model.AuthItemRef;
import com.boda.components.auth.service.AuthChecker;
import com.boda.components.auth.service.AuthService;

/**
 * <权限容器> <功能详细描述>
 * 
 * @author PengQingyang
 * @version [版本号, 2012-12-1]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
public class AuthContext implements ApplicationContextAware, InitializingBean {

	private static final Logger logger = LoggerFactory.getLogger(AuthContext.class);
	
	@SuppressWarnings("unused")
	private ApplicationContext context;

	/** autuChecker所在 */
	private String auchCheckerPackage;

	/** 权限业务逻辑层 */
	private AuthService authService;

	/**
	 * 权限类型检查器默认会添加几个检查器 用户自定义添加的权限检查器会覆盖该检查器
	 */
	private static Map<String, AuthChecker> authCheckMapping = new HashMap<String, AuthChecker>();

	@Override
	public void setApplicationContext(ApplicationContext context)
			throws BeansException {
		this.context = context;
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		//加载系统配置的权限
		//TODO:reloadAuthItemConfig 以后提供重新加载功能
		this.authService.loadAuthItemConfig();
		
		//自动加载容器中实现的权限检查器
		//提供，3个默认的权限检查器
		loadConfigAuthChecker();
		
		//加载2个默认的无权限处理器
		//TODO:XXX
	}

	/**
	 * <功能简述>加载检查器 <功能详细描述> [参数说明]
	 * 
	 * @return void [返回类型说明]
	 * @exception throws [异常类型] [异常说明]
	 * @see [类、类#方法、类#成员]
	 */
	private void loadConfigAuthChecker() {
		if (StringUtils.isEmpty(this.auchCheckerPackage)) {
			return;
		}

		Set<Class<? extends AuthChecker>> authCheckerClassSet = new HashSet<Class<? extends AuthChecker>>();

		String[] authCheckerPackageArray = org.springframework.util.StringUtils
				.tokenizeToStringArray(
						this.auchCheckerPackage,
						ConfigurableApplicationContext.CONFIG_LOCATION_DELIMITERS);

		for (String authCheckerPagTemp : authCheckerPackageArray) {
			authCheckerClassSet
					.addAll(loadConfigAuthCheckerByPackage(authCheckerPagTemp));
		}

		for (Class<? extends AuthChecker> authCheckClassTemp : authCheckerClassSet) {
			AuthChecker checkerImp;
			try {
				checkerImp = authCheckClassTemp.newInstance();
				authCheckMapping.put(checkerImp.getCheckAuthType(),
						checkerImp);
			} catch (InstantiationException e) {
				logger.error("实例化异常", e);
			} catch (IllegalAccessException e) {
				logger.error("非法接收异常", e);
			}
		}
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
		Set<Class<? extends AuthChecker>> handlerSet = resolverUtil
				.getClasses();

		return handlerSet;
	}

	/**
	 * <登录时初始化当前登录人的权限容器，权限容器放入session中> 请求进入后将对应的权限容器放入线程中以备后续调用 <功能详细描述>
	 * 
	 * @param operatorId
	 * @return [参数说明]
	 * 
	 * @return List<AuthItemRef> [返回类型说明]
	 * @exception throws [异常类型] [异常说明]
	 * @see [类、类#方法、类#成员]
	 */
	public static void initCurrentUserAuthContextWhenLogin(String operatorId) {
		AuthContext context = new AuthContext();
		List<AuthItemRef> authItemRefList = context.authService
				.queryAuthItemRefList(operatorId);
		SessionAuthContext.getRequest().getSession()
				.setAttribute("authItemRefList", authItemRefList);
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
	private static boolean isHasAuth(String authKey, String authType,
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
	public static boolean isHasOperateAuth(String authKey) {
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
	public static boolean isHasDataColumnAuth(String authKey) {
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
	public static boolean isHasDataRowAuth(String authKey) {
		return isHasAuth(authKey, AuthConstant.TYPE_DATA_ROW);
	}

	/**
	 * @return 返回 auchCheckerPackage
	 */
	public String getAuchCheckerPackage() {
		return auchCheckerPackage;
	}

	/**
	 * @param 对auchCheckerPackage进行赋值
	 */
	public void setAuchCheckerPackage(String auchCheckerPackage) {
		this.auchCheckerPackage = auchCheckerPackage;
	}

}
