/*
 * 描          述:  <描述>
 * 修  改   人:  Administrator
 * 修改时间:  2017年6月28日
 * <修改描述:>
 */
package com.tx.local.loanaccount.strategy.view;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ResourceLoaderAware;
import org.springframework.core.io.ResourceLoader;

import com.tx.core.exceptions.util.AssertUtils;
import com.tx.local.loanaccount.model.LoanAccountTypeEnum;

/**
  * 抽象视图逻辑策略<br/>
  * <功能详细描述>
  * 
  * @author  Administrator
  * @version  [版本号, 2017年6月28日]
  * @see  [相关类/方法]
  * @since  [产品/模块版本]
  */
public class AbstractViewStrategy implements ViewStrategy, ResourceLoaderAware, InitializingBean {
    
    /** 页面名称正则表达式 */
    private final static Pattern PAGE_NAME_PATTERN = Pattern.compile("^\\w+([/]\\w+)*$");
    
    /** 资源加载器 */
    protected ResourceLoader resourceLoader;
    
    /** 页面名映射 */
    protected final Map<String, Map<String, String>> pageNameMap = new HashMap<>();
    
    /**
     * @param resourceLoader
     */
    @Override
    public final void setResourceLoader(ResourceLoader resourceLoader) {
        this.resourceLoader = resourceLoader;
    }
    
    /**
     * @throws Exception
     */
    @Override
    public void afterPropertiesSet() throws Exception {
        initPageNameMap();//初始化页面路径映射
    }
    
    /**
     * 初始化<br/>
     * <功能详细描述> [参数说明]
     * 
     * @return void [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
    */
    protected void initPageNameMap() {
    }
    
    /**
     * @param viewType
     * @param viewName
     * @return
     */
    @Override
    public String getPageName(String viewType, String viewName, LoanAccountTypeEnum loanAccountType) {
        AssertUtils.notEmpty(viewType, "viewType is empty.");
        AssertUtils.notEmpty(viewName, "viewName is empty.");
        
        AssertUtils.isTrue(PAGE_NAME_PATTERN.matcher(viewType).matches(), "viewType:{} is not validate.", viewType);
        AssertUtils.isTrue(PAGE_NAME_PATTERN.matcher(viewName).matches(), "viewName:{} is not validate.", viewName);
        viewType = viewType.toLowerCase();//viewType只能是小写的
        
        if (this.pageNameMap.containsKey(viewType) && this.pageNameMap.get(viewType).containsKey(viewName)) {
            //如果存在指定页面，则返回指定页面
            return this.pageNameMap.get(viewType).get(viewName);
        }
        
        //拼接页面
        StringBuilder pageNameSB = new StringBuilder();
        if (viewType.startsWith("loanaccount")) {
            pageNameSB.append(viewType);
        } else {
            pageNameSB.append("loanaccount/").append(viewType);
        }
        pageNameSB.append("/");
        
        String page = "";
        //判断账户类型对应的特殊目录中是否存在页面
        if (isExistOfJspPage(pageNameSB.toString() + loanAccountType.toString().toLowerCase() + "/" + viewName)) {
            page = pageNameSB.append(loanAccountType.toString().toLowerCase()).append("/").append(viewName).toString();
        } else {
            page = pageNameSB.append(viewName).toString();
        }
        
        return page;
    }
    
    /**
     * 判断Jsp页面是否存在
     * <功能详细描述>
     * @param page
     * @return [参数说明]
     * 
     * @return boolean [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
    */
    private boolean isExistOfJspPage(String page) {
        org.springframework.core.io.Resource jspPageResource = resourceLoader
                .getResource("/WEB-INF/view/" + page + ".jsp");
        if (jspPageResource.exists()) {
            return true;
        } else {
            return false;
        }
    }
}
