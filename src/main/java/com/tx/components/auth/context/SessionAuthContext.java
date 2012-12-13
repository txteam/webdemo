/*
 * 描          述:  <描述>
 * 修  改   人:  brady
 * 修改时间:  2012-12-3
 * <修改描述:>
 */
package com.tx.components.auth.context;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 回话权限容器 <功能详细描述>
 * 
 * @author brady
 * @version [版本号, 2012-12-3]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
public class SessionAuthContext {

	private static final String SESSIONAUTHCONTEXT_THREADKEY_HEADER = "SessionAuthContext_";
	
	private static Logger logger = LoggerFactory.getLogger(AuthContext.class);

	/**
	 * 线程变量
	 */
	private static ThreadLocal<Map<String, Object>> context = new ThreadLocal<Map<String, Object>>() {

		/**
		 * @return
		 */
		@Override
		protected Map<String, Object> initialValue() {
			Map<String, Object> contextMap = new HashMap<String, Object>();
			return contextMap;
		}

		/**
		 * 
		 */
		@Override
		public void remove() {
			get().clear();
			super.remove();
		}
	};
	
	private SessionAuthContext(){
		
	}
	
	/**
	  *<功能简述>把request和response绑定当线程中去
	  *<功能详细描述>
	  * @param request
	  * @param response [参数说明]
	  * 
	  * @return void [返回类型说明]
	  * @exception throws [异常类型] [异常说明]
	  * @see [类、类#方法、类#成员]
	 */
	public static void preHandler(HttpServletRequest request,HttpServletResponse response){
		bindValueToThread(request);
		bindValueToThread(response);
	}
	
	/**
	 * 
	  *<功能简述>把线程变量中的值清空
	  *<功能详细描述> [参数说明]
	  * 
	  * @return void [返回类型说明]
	  * @exception throws [异常类型] [异常说明]
	  * @see [类、类#方法、类#成员]
	 */
	public static void afterHandler(){
		context.remove();
	}
	
	/**
	 * @return 得到线程变量中的request
	 */
	public static HttpServletRequest getRequest() {
		return (HttpServletRequest)getValueFromThread(HttpServletRequest.class);
	}

	/**
	 * @return 得到线程变量中的response
	 */
	public static HttpServletResponse getResponse() {
		return (HttpServletResponse)getValueFromThread(HttpServletResponse.class);
	}

	/**
	  * 绑定变量到线程上
	  *<功能详细描述>
	  * @param obj [参数说明]
	  * 
	  * @return void [返回类型说明]
	  * @exception throws [异常类型] [异常说明]
	  * @see [类、类#方法、类#成员]
	 */
	private static <T> void bindValueToThread(T obj) {
		SessionAuthContext.context.get().put(
				SESSIONAUTHCONTEXT_THREADKEY_HEADER + obj.getClass(), obj);
	}
	/**
	  *<功能简述>从线程中获取变量
	  *<功能详细描述>
	  * @param obj
	  * @return [参数说明]
	  * 
	  * @return T [返回类型说明]
	  * @exception throws [异常类型] [异常说明]
	  * @see [类、类#方法、类#成员]
	 */
	@SuppressWarnings("unchecked")
	private static <T> T getValueFromThread(Class<T> obj) {
		try{
			return (T) SessionAuthContext.context.get().get(
					SESSIONAUTHCONTEXT_THREADKEY_HEADER + obj.getClass());
		}catch (Exception e) {
			logger.error("从线程中获取对象发生异常",e);
		}
		return null;
	}

}
