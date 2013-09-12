/*
 * 描          述:  <描述>
 * 修  改   人:  brady
 * 修改时间:  2013-8-28
 * <修改描述:>
 */
package com.tx.component.operator.controller;

import java.util.Map;

import javax.annotation.Resource;
import javax.ws.rs.core.MultivaluedMap;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.tx.component.operator.model.Operator;
import com.tx.component.operator.service.OperatorService;
import com.tx.core.paged.model.PagedList;

/**
 * 人员操作视图逻辑层
 * <功能详细描述>
 * 
 * @author  brady
 * @version  [版本号, 2013-8-28]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
@Controller
@RequestMapping("/operator")
public class OperatorController {
    
    @Resource(name = "operatorService")
    private OperatorService operatorService;
    
    /**
      * 跳转到查询人员列表页面<BR/>
      *<功能详细描述>
      * @return [参数说明]
      * 
      * @return String [返回类型说明]
      * @exception throws [异常类型] [异常说明]
      * @see [类、类#方法、类#成员]
     */
    @RequestMapping("/toQueryOperatorList")
    public String toQueryOperatorList() {
        return "/operator/queryOperatorList";
    }
    
    /**
      * 跳转到新增操作员列表页面<br/>
      *<功能详细描述>
      * @return [参数说明]
      * 
      * @return String [返回类型说明]
      * @exception throws [异常类型] [异常说明]
      * @see [类、类#方法、类#成员]
     */
    @RequestMapping("/toAddOperator")
    public String toAddOperator() {
        return "/operator/addOperator";
    }
    
    /**
      * 跳转到更新操作员信息页面<br/>
      *<功能详细描述>
      * @return [参数说明]
      * 
      * @return String [返回类型说明]
      * @exception throws [异常类型] [异常说明]
      * @see [类、类#方法、类#成员]
     */
    @RequestMapping("/toUpdateOperator")
    public String toUpdateOperator() {
        return "/operator/updateOperator";
    }
    
    /**
      * 分页查询操作人员<br/>
      *<功能详细描述>
      * @param requestMap
      * @param pageIndex
      * @param pageSize
      * @param responseMap
      * @return [参数说明]
      * 
      * @return PagedList<Operator> [返回类型说明]
      * @exception throws [异常类型] [异常说明]
      * @see [类、类#方法、类#成员]
     */
    public PagedList<Operator> queryOperatorPagedList(
            @RequestParam Map<String, String> requestMap,
            @RequestParam(value = "pageIndex", required = false, defaultValue = "1") int pageIndex,
            @RequestParam(value = "pageSize", required = false, defaultValue = "10") int pageSize,
            ModelMap responseMap) {
        String organizationId = requestMap.get("organizationId");
        String postId = requestMap.get("postId");
        
        //String organizationId = requestMap.get("");
        PagedList<Operator> resPagedList = this.operatorService.queryOperatorPagedList(pageIndex,
                pageSize);
        return resPagedList;
    }
    
}
