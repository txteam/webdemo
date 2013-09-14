/*
 * 描          述:  <描述>
 * 修  改   人:  brady
 * 修改时间:  2012-12-26
 * <修改描述:>
 */
package com.tx.component.mainframe.context;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.tx.component.auth.context.AuthContext;
import com.tx.component.operator.model.Operator;
import com.tx.component.operator.model.Organization;
import com.tx.component.operator.model.Post;
import com.tx.core.exceptions.util.AssertUtils;

/**
 * 请求获取请求属性集
 * <功能详细描述>
 * 
 * @author  brady
 * @version  [版本号, 2012-12-26]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public class WebContextUtils {
    
    public static final String SESSION_ISSUPERADMIN_FLAG = "isSuperAdmin";
    
    /** 当前登录人员在session中的key */
    public static final String SESSION_CURRENT_OPERATOR = "operator";
    
    /** 当前登录人员所在组织在 session中的key */
    public static final String SESSION_CURRENT_ORGANIZATION = "organization";
    
    /** 当前登录人员在session中的职位列表 */
    public static final String SESSION_CURRENT_POSTLIST = "postList";
    
    /** 获取当前登录人员的职位 */
    public static final String SESSION_CURRENT_POST = "post";
    
    /**
      * 将是否超级管理员的标志压栈到会话中，该功能为包内可见<br/>
      *<功能详细描述>
      * @param isSuperAdmin [参数说明]
      * 
      * @return void [返回类型说明]
      * @exception throws [异常类型] [异常说明]
      * @see [类、类#方法、类#成员]
     */
    static void putIsSuperAdminFlagInSession(boolean isSuperAdmin) {
        HttpSession session = getSession(true);
        
        session.setAttribute(SESSION_ISSUPERADMIN_FLAG, isSuperAdmin);
    }
    
    /**
      * 是否为超级管理员
      *<功能详细描述>
      * @return [参数说明]
      * 
      * @return boolean [返回类型说明]
      * @exception throws [异常类型] [异常说明]
      * @see [类、类#方法、类#成员]
     */
    static boolean isSuperAdmin() {
        HttpSession session = getSession(true);
        Boolean isSuperAdminFlag = (Boolean) session.getAttribute(SESSION_ISSUPERADMIN_FLAG);
        
        boolean isSuperAdmin = (isSuperAdminFlag != null && isSuperAdminFlag.booleanValue());
        return isSuperAdmin;
    }
    
    /**
      * 当前登录人员是否是所属组织主管 
      *<功能简述>
      *<功能详细描述>
      * @return [参数说明]
      * 
      * @return boolean [返回类型说明]
      * @exception throws [异常类型] [异常说明]
      * @see [类、类#方法、类#成员]
     */
    public static boolean isOrganizationChief() {
        Operator currentOperator = getCurrentOperator();
        Organization currentOrganization = getCurrentOrganization();
        
        if (isSuperAdmin()) {
            return true;
        } else {
            currentOrganization.getChiefType();
            currentOrganization.getChiefId();
            
            //TODO:
            return true;
        }
    }
    
    /**
      * 判断是否有权限<br/>
      *<功能详细描述>
      * @param authKey
      * @return [参数说明]
      * 
      * @return boolean [返回类型说明]
      * @exception throws [异常类型] [异常说明]
      * @see [类、类#方法、类#成员]
     */
    public static boolean hasAuth(String authKey) {
        boolean res = AuthContext.isHasAuth(authKey);
        return res;
    }
    
    /**
      * 判断是否有权限<br/>
      *<功能详细描述>
      * @param authKey
      * @param params
      * @return [参数说明]
      * 
      * @return boolean [返回类型说明]
      * @exception throws [异常类型] [异常说明]
      * @see [类、类#方法、类#成员]
     */
    public static boolean isHasAuth(String authKey, Object... params) {
        boolean res = AuthContext.isHasAuth(authKey, params);
        return res;
    }
    
    /**
      * 从会话中获取当前组织<br/>
      *<功能详细描述>
      * @return [参数说明]
      * 
      * @return Organization [返回类型说明]
      * @exception throws [异常类型] [异常说明]
      * @see [类、类#方法、类#成员]
     */
    public static Organization getCurrentOrganization() {
        HttpSession session = getSession(true);
        
        Organization currentOrganization = (Organization) session.getAttribute(SESSION_CURRENT_ORGANIZATION);
        return currentOrganization;
    }
    
    /**
     * 从会话中获取当前登录人员
     *<功能详细描述>
     * @return [参数说明]
     * 
     * @return Operator [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
    */
    public static Operator getCurrentOperator() {
        HttpSession session = getSession(true);
        
        Operator currentOperator = (Operator) session.getAttribute(SESSION_CURRENT_OPERATOR);
        return currentOperator;
    }
    
    /**
     * 放入会话中的组织现允许为空<br/>
     *     超级管理员对应的组织和职位都是空的<br/>
     *<功能详细描述>
     * @param organization [参数说明]
     * 
     * @return void [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
    */
    public static void putMainPostInSession(Post post) {
        HttpSession session = getSession(true);
        
        session.setAttribute(SESSION_CURRENT_POST, post);
    }
    
    /**
     * 放入会话中的组织现允许为空<br/>
     *     超级管理员对应的组织和职位都是空的<br/>
     *<功能详细描述>
     * @param organization [参数说明]
     * 
     * @return void [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
    */
    public static void putPostListInSession(List<Post> postList) {
        HttpSession session = getSession(true);
        
        session.setAttribute(SESSION_CURRENT_POSTLIST, postList);
    }
    
    /**
      * 放入会话中的组织现允许为空<br/>
      *     超级管理员对应的组织和职位都是空的<br/>
      *<功能详细描述>
      * @param organization [参数说明]
      * 
      * @return void [返回类型说明]
      * @exception throws [异常类型] [异常说明]
      * @see [类、类#方法、类#成员]
     */
    public static void putOganizationInSession(Organization organization) {
        HttpSession session = getSession(true);
        if (!isSuperAdmin()) {
            AssertUtils.notNull(organization, "organization is null");
        }
        
        session.setAttribute(SESSION_CURRENT_ORGANIZATION, organization);
    }
    
    /**
      * 将当前登录人员放入当前会话中
      *<功能详细描述>
      * @param operator [参数说明]
      * 
      * @return void [返回类型说明]
      * @exception throws [异常类型] [异常说明]
      * @see [类、类#方法、类#成员]
     */
    public static void putOperatorInSession(Operator operator) {
        AssertUtils.notNull(operator, "operator is null");
        HttpSession session = getSession(true);
        
        session.setAttribute(SESSION_CURRENT_OPERATOR, operator);
    }
    
    /**
      * 获取当前请求RequestAttributes
      * <功能详细描述>
      * @return [参数说明]
      * 
      * @return HttpServletRequest [返回类型说明]
      * @exception throws [异常类型] [异常说明]
      * @see [类、类#方法、类#成员]
     */
    public static RequestAttributes getRequestAttributes() {
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        
        return requestAttributes;
    }
    
    /**
      * 获取当前请求request
      * <功能详细描述>
      * @return [参数说明]
      * 
      * @return HttpServletRequest [返回类型说明]
      * @exception throws [异常类型] [异常说明]
      * @see [类、类#方法、类#成员]
     */
    public static HttpServletRequest getRequest() {
        if (getRequestAttributes() != null) {
            return ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        } else {
            return null;
        }
    }
    
    /**
      * 获取当前会话
      * <功能详细描述>
      * @return [参数说明]
      * 
      * @return HttpSession [返回类型说明]
      * @exception throws [异常类型] [异常说明]
      * @see [类、类#方法、类#成员]
     */
    public static HttpSession getSession() {
        return getSession(true);
    }
    
    /**
      * 获取当前会话
      * <功能详细描述>
      * @param flag
      * @return [参数说明]
      * 
      * @return HttpSession [返回类型说明]
      * @exception throws [异常类型] [异常说明]
      * @see [类、类#方法、类#成员]
     */
    public static HttpSession getSession(boolean flag) {
        if (getRequest() == null) {
            return null;
        }
        return getRequest().getSession(flag);
    }
    
}
