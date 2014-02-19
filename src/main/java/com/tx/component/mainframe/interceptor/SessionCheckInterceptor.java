/*
 * 描          述:  <描述>
 * 修  改   人:  PengQingyang
 * 修改时间:  2013-12-13
 * <修改描述:>
 */
package com.tx.component.mainframe.interceptor;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.collections.map.LRUMap;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.core.io.Resource;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.tx.component.mainframe.context.WebContextUtils;
import com.tx.component.mainframe.exception.SessionLostException;
import com.tx.core.exceptions.SILException;
import com.tx.core.exceptions.util.AssertUtils;
import com.tx.core.exceptions.util.ExceptionWrapperUtils;

/**
 * 会话校验拦截器<br/>
 *     如果不为忽略session校验的功能
 *     则检查session中是否存在operator如果不存在则认为此次访问为非法访问<br/>
 * <功能详细描述>
 * 
 * @author  PengQingyang
 * @version  [版本号, 2013-12-13]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public class SessionCheckInterceptor implements HandlerInterceptor,
        InitializingBean {
    
    /** 日志记录器 */
    //private static Logger logger = LoggerFactory.getLogger(SessionCheckInterceptor.class);
    
    /** 配置文件地址 */
    private Resource configLocaton;
    
    /** 校验key */
    private String checkedKey = WebContextUtils.SESSION_CURRENT_OPERATOR;
    
    /** 配置实例 */
    private SessionCheckConfig config;
    
    /** 无需进行会话校验的路径缓存大小 */
    private int maxNeedSessionCheckPathCacheSize = 500;
    
    /** 需进行会话校验的路径缓存大小 */
    private int maxNotNeedSessionCheckPathCacheSize = 1000;
    
    /**
     * @throws Exception
     */
    @Override
    public void afterPropertiesSet() throws Exception {
        AssertUtils.notNull(configLocaton, "configLocaton is null");
        AssertUtils.isExist(configLocaton,
                "configLocaton:{} is not exist.",
                new Object[] { configLocaton });
        
        config = new SessionCheckConfig(configLocaton);
    }
    
    /**
     * 获取访问资源的path
     * @param request
     * @return
     */
    private String getServletPath(HttpServletRequest request) {
        String path = request.getRequestURI();
        String contextPath = request.getContextPath();
        if (StringUtils.isNotEmpty(contextPath)) {
            path = path.substring(contextPath.length());
        }
        return path;
    }
    
    /**
     * 判断Session是否丢失
     * 
     * @param request
     * @return
     */
    protected boolean isSessionInvalid(HttpServletRequest request) {
        // 有Session吗？
        HttpSession session = request.getSession(false);
        if (session == null) {
            return true;
        }
        // 有操作员吗?
        if (session.getAttribute(checkedKey) == null) {
            return true;
        }
        return false;
    }
    
    /**
     * @param request
     * @param response
     * @param handler
     * @return
     * @throws Exception
     */
    @Override
    public boolean preHandle(HttpServletRequest request,
            HttpServletResponse response, Object handler) throws Exception {
        //获取当前请求资源路径
        String path = getServletPath(request);
        //如果无需进行校验则继续执行后续逻辑
        if (config.isExclude(path)) {
            return true;
        } else {
            //如果需要校验，则进行校验
            if (!isSessionInvalid(request)) {
                return true;
            } else {
                throw new SessionLostException("访问路径：{}时发生会话丢失",
                        new Object[] { path });
            }
        }
    }
    
    /**
     * @param request
     * @param response
     * @param handler
     * @param modelAndView
     * @throws Exception
     */
    @Override
    public void postHandle(HttpServletRequest request,
            HttpServletResponse response, Object handler,
            ModelAndView modelAndView) throws Exception {
    }
    
    /**
     * @param request
     * @param response
     * @param handler
     * @param ex
     * @throws Exception
     */
    @Override
    public void afterCompletion(HttpServletRequest request,
            HttpServletResponse response, Object handler, Exception ex)
            throws Exception {
    }
    
    /**
     * @return 返回 configLocaton
     */
    public Resource getConfigLocaton() {
        return configLocaton;
    }
    
    /**
     * @param 对configLocaton进行赋值
     */
    public void setConfigLocaton(Resource configLocaton) {
        this.configLocaton = configLocaton;
    }
    
    /**
     * @return 返回 checkedKey
     */
    public String getCheckedKey() {
        return checkedKey;
    }
    
    /**
     * @param 对checkedKey进行赋值
     */
    public void setCheckedKey(String checkedKey) {
        this.checkedKey = checkedKey;
    }
    
    /**
     * @return 返回 maxNeedSessionCheckPathCacheSize
     */
    public int getMaxNeedSessionCheckPathCacheSize() {
        return maxNeedSessionCheckPathCacheSize;
    }
    
    /**
     * @param 对maxNeedSessionCheckPathCacheSize进行赋值
     */
    public void setMaxNeedSessionCheckPathCacheSize(
            int maxNeedSessionCheckPathCacheSize) {
        this.maxNeedSessionCheckPathCacheSize = maxNeedSessionCheckPathCacheSize;
    }
    
    /**
     * @return 返回 maxNotNeedSessionCheckPathCacheSize
     */
    public int getMaxNotNeedSessionCheckPathCacheSize() {
        return maxNotNeedSessionCheckPathCacheSize;
    }
    
    /**
     * @param 对maxNotNeedSessionCheckPathCacheSize进行赋值
     */
    public void setMaxNotNeedSessionCheckPathCacheSize(
            int maxNotNeedSessionCheckPathCacheSize) {
        this.maxNotNeedSessionCheckPathCacheSize = maxNotNeedSessionCheckPathCacheSize;
    }
    
    /**
      * 会话校验配置<br/>
      * <功能详细描述>
      * 
      * @author  PengQingyang
      * @version  [版本号, 2013-12-13]
      * @see  [相关类/方法]
      * @since  [产品/模块版本]
     */
    private class SessionCheckConfig {
        
        /** 例外路径正则表达式 */
        private List<Pattern> excludePathPatterns = new ArrayList<Pattern>();
        
        private Map<String, Pattern> notNeedSessionCheckPathLRUMapCache;
        
        private Map<String, Pattern> needSessionCheckPathLRUMapCache;
        
        /**
         * <默认构造函数>
         */
        @SuppressWarnings("unchecked")
        public SessionCheckConfig(Resource configFileResource) {
            AssertUtils.isExist(configFileResource,
                    "configFileResource is not exist");
            
            notNeedSessionCheckPathLRUMapCache = new LRUMap(
                    maxNotNeedSessionCheckPathCacheSize);
            needSessionCheckPathLRUMapCache = new LRUMap(
                    maxNeedSessionCheckPathCacheSize);
            
            InputStream configFileIN = null;
            try {
                configFileIN = configFileResource.getInputStream();
                init(configFileIN);
            } catch (Exception e) {
                throw ExceptionWrapperUtils.wrapperSILException(SILException.class,
                        "会话校验拦截器初始化异常",
                        e);
            } finally {
                IOUtils.closeQuietly(configFileIN);
            }
        }
        
        /**
          * 读取会话检查拦截器的配置<br/>
          *<功能详细描述>
          * @param file
          * @throws DocumentException [参数说明]
          * 
          * @return void [返回类型说明]
          * @exception throws [异常类型] [异常说明]
          * @see [类、类#方法、类#成员]
         */
        private void init(InputStream configFileInputStream)
                throws DocumentException {
            SAXReader reader = new SAXReader();
            Document doc = reader.read(configFileInputStream);
            Element root = doc.getRootElement(); // 取根元素
            
            String excludes = StringUtils.trim(root.elementText("exclude"));
            String[] lines = StringUtils.split(excludes);
            Set<String> lineSet = new HashSet<String>();
            for (String lineTemp : lines) {
                if (StringUtils.isBlank(lineTemp)) {
                    continue;
                }
                String _line = StringUtils.trim(lineTemp);
                String[] _lines = StringUtils.split(_line, ";");
                for (String line : _lines) {
                    lineSet.add(line);
                }
            }
            
            //转换为正则表达式存储
            for (String lineTemp : lineSet) {
                if (StringUtils.isEmpty(lineTemp)) {
                    continue;
                }
                excludePathPatterns.add(Pattern.compile(lineTemp.trim()));
            }
        }
        
        /**
          * 判断是否为例外路径<br/>
          *<功能详细描述>
          * @param path
          * @return [参数说明]
          * 
          * @return boolean [返回类型说明]
          * @exception throws [异常类型] [异常说明]
          * @see [类、类#方法、类#成员]
         */
        public boolean isExclude(String path) {
            //如果为需要会话校验的路径
            if (needSessionCheckPathLRUMapCache.containsKey(path)) {
                //命中计数
                needSessionCheckPathLRUMapCache.get(path);
                return false;
            }
            //如果不为需要会话校验的路径
            if (notNeedSessionCheckPathLRUMapCache.containsKey(path)) {
                //命中计数
                notNeedSessionCheckPathLRUMapCache.get(path);
                return true;
            }
            
            //根据配置分析是否需要路径校验
            boolean matches = false;
            for (Pattern pattern : excludePathPatterns) {
                if (pattern.matcher(path).matches()) {
                    matches = true;
                    break;
                }
            }
            
            if (matches) {
                //命中计数
                notNeedSessionCheckPathLRUMapCache.get(path);
            } else {
                //命中计数
                needSessionCheckPathLRUMapCache.get(path);
            }
            return matches;
        }
    }
}
