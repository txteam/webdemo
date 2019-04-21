/*
 * 描          述:  <描述>
 * 修  改   人:  Administrator
 * 修改时间:  2019年4月21日
 * <修改描述:>
 */
package com.tx.local.menu.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.tx.core.exceptions.util.AssertUtils;
import com.tx.local.menu.context.MenuContext;
import com.tx.local.menu.model.Menu;
import com.tx.local.menu.model.MenuCatalogItem;
import com.tx.local.menu.model.MenuNode;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

/**
 * 菜单容器API
 * <功能详细描述>
 * 
 * @author  Administrator
 * @version  [版本号, 2019年4月21日]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
@Api(value = "/api/menu", tags = "菜单API")
@RequestMapping("/api/menu")
@RestController
public class MenuAPIController {
    
    @Resource(name = "menuContext")
    private MenuContext menuContext;
    
    /**
     * 查询菜单分类列表
     * <功能详细描述>
     * @return [参数说明]
     * 
     * @return List<MenuItem> [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @ApiOperation(value = "查询菜单分类列表", notes = "")
    @RequestMapping(value = "/catalog/list", method = RequestMethod.GET)
    public List<MenuCatalogItem> queryMenuCatalogItemList() {
        List<MenuCatalogItem> resList = menuContext.getMenuCatalogItemList();
        return resList;
    }
    
    /**
     * 根据菜单分类查询菜单列表<br/>
     * <功能详细描述>
     * @return [参数说明]
     * 
     * @return List<MenuItem> [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @ApiOperation(value = "根据菜单分类查询菜单列表", notes = "")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "catalog", value = "菜单分类", required = true, dataType = "String") })
    @RequestMapping(value = "/list/{catalog}", method = RequestMethod.GET)
    public List<Menu> queryMenuListByCatalog(
            @PathVariable(required = true, name = "catalog") String catalog) {
        AssertUtils.notEmpty(catalog, "catalog is empty.");
        
        //根据权限及菜单配置生成最终权限列表
        List<Menu> resList = menuContext.getMenuListByCatalog(catalog);
        return resList;
    }
    
    /**
     * 根据菜单分类查询菜单树节点列表<br/>
     * <功能详细描述>
     * @return [参数说明]
     * 
     * @return List<MenuItem> [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @ApiOperation(value = "根据菜单分类查询菜单树节点列表", notes = "")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "catalog", value = "菜单分类", required = true, dataType = "String")})
    @RequestMapping(value = "/menunode/list/{catalog}", method = RequestMethod.GET)
    public List<MenuNode> queryMenuNodeListByCatalog(
            @PathVariable(required = true, name = "catalog") String catalog) {
        AssertUtils.notEmpty(catalog, "catalog is empty.");
        
        //根据权限及菜单配置生成最终权限列表
        List<MenuNode> resList = menuContext.getMenuNodeListByCatalog(catalog);
        return resList;
    }
}
