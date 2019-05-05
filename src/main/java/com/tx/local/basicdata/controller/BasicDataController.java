/*
 * 描       述:  <描述>
 * 修  改 人:  
 * 修改时间:
 * <修改描述:>
 */
package com.tx.local.basicdata.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.view.ViewResolverComposite;

import com.tx.component.basicdata.context.BasicDataContext;
import com.tx.component.basicdata.registry.BasicDataEntityRegistry;
import com.tx.core.exceptions.util.AssertUtils;

/**
 * DataDict显示层逻辑<br/>
 * <功能详细描述>
 * 
 * @author  PengQingyang
 * @version  [版本号, 2013-8-27]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
@Controller
@RequestMapping("/basicData")
public class BasicDataController
        implements InitializingBean, ApplicationContextAware {
    
    /** spring容器句柄 */
    private ApplicationContext applicationContext;
    
    /** 基础数据实体注册表 */
    @Resource
    private BasicDataEntityRegistry basicDataEntityRegistry;
    
    /** 基础数据容器 */
    @Resource
    private BasicDataContext basicDataContext;
    
    private List<ViewResolver> viewResolvers;
    
    private ViewResolverComposite viewResolverComposite;
    
    /** <默认构造函数> */
    public BasicDataController(List<ViewResolver> viewResolvers) {
        super();
        this.viewResolvers = viewResolvers;
    }

    /**
     * @throws Exception
     */
    @Override
    public void afterPropertiesSet() throws Exception {
        AssertUtils.notEmpty(this.viewResolvers,"viewResolvers is empty.");
        this.viewResolverComposite = new ViewResolverComposite();
        this.viewResolverComposite.setViewResolvers(this.viewResolvers);
    }
    
    /**
     * @param applicationContext
     * @throws BeansException
     */
    @Override
    public void setApplicationContext(ApplicationContext applicationContext)
            throws BeansException {
        this.applicationContext = applicationContext;
    }
    
    /**
     * 跳转到数据字典管理页面<br/>
     * <功能详细描述>
     * @param response
     * @return [参数说明]
     *
     * @return String [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    @RequestMapping("/toQueryBasicDataMain")
    public String toQueryBasicDataMain(ModelMap response) {
        //加载数据类型
        return "/basicdata/queryBasicDataMain";
    }
    
//    /**
//     * 跳转到查询DataDict列表页面<br/>
//     *<功能详细描述>
//     * @return [参数说明]
//     *
//     * @return String [返回类型说明]
//     * @exception throws [异常类型] [异常说明]
//     * @see [类、类#方法、类#成员]
//    */
//    @RequestMapping("/toQuery")
//    public String toQuery(
//            @RequestParam(value = "basicDataTypeCode", required = false) String basicDataTypeCode,
//            ModelMap response) {
//        //基础数据类型集合
//        response.put("basicDataTypes", getCommonBasicDataTypeList());
//        
//        BDPageTypeEnum pageType = BDPageTypeEnum.QueryPagedList;
//        String pageName = pageType.getDefaultPage();
//        if (StringUtils.isEmpty(basicDataTypeCode)
//                || !this.commonCode2TypeMap.containsKey(basicDataTypeCode)) {
//            return "/basicdata/queryDataDictPagedList";
//        }
//        
//        BasicDataEntityInfo info = this.commonCode2TypeMap
//                .get(basicDataTypeCode);
//        String type = info.getType();
//        response.put("basicDataTypeCode", basicDataTypeCode);
//        response.put("basicDataType", bdType);
//        //基础数据类型
//        AssertUtils.notNull(type, "未设置对应的处理类:{}", basicDataTypeCode);
//        
//        //跳转到查询页
//        switch (info.getViewType()) {
//            case COMMON_PAGEDLIST:
//            case PAGEDLIST:
//                pageName = getPageName(type, BDPageTypeEnum.QueryPagedList);
//                break;
//            case COMMON_TREE:
//            case TREE:
//                pageName = getPageName(type, BDPageTypeEnum.QueryTree);
//                break;
//            case COMMON_LIST:
//            case LIST:
//            default:
//                pageName = getPageName(type, BDPageTypeEnum.QueryList);
//                break;
//        }
//        return pageName;
//    }
    //    
    //    /**
    //     * 跳转到添加DataDict页面<br/>
    //     * <功能详细描述>
    //     * @return [参数说明]
    //     *
    //     * @return String [返回类型说明]
    //     * @exception throws [异常类型] [异常说明]
    //     * @see [类、类#方法、类#成员]
    //    */
    //    @RequestMapping("/toAdd")
    //    public String toAdd(
    //            @RequestParam(value = "basicDataTypeCode") String basicDataTypeCode,
    //            @RequestParam Map<String, String> requestMap, ModelMap response) {
    //        BDPageTypeEnum bdPageType = BDPageTypeEnum.AddBasicData;
    //        String pageName = bdPageType.defaultPage;
    //        if (StringUtils.isEmpty(basicDataTypeCode)
    //                || !this.commonCode2TypeMap.containsKey(basicDataTypeCode)) {
    //            AssertUtils.isTrue(false,
    //                    "basicDataTypeCode is error.{}",
    //                    basicDataTypeCode);
    //        }
    //        
    //        //基础数据类型
    //        BasicDataType bdType = this.commonCode2TypeMap.get(basicDataTypeCode);
    //        response.put("basicDataTypeCode", basicDataTypeCode);//基础数据类型编码
    //        response.put("basicDataType", bdType);//基础数据类型编码
    //        
    //        Class<?> type = bdType.getType();
    //        AssertUtils.notNull(type,
    //                "type is null.basicDataTypeCode:{}",
    //                basicDataTypeCode);
    //        
    //        BasicData bd = (BasicData) ObjectUtils.newInstance(type);
    //        response.put("basicData", bd);
    //        
    //        pageName = getPageName(bdType, BDPageTypeEnum.AddBasicData);
    //        return pageName;
    //    }
    
    /**
    * 跳转到编辑DataDict页面
    *<功能详细描述>
    * @return [参数说明]
    *
    * @return String [返回类型说明]
    * @exception throws [异常类型] [异常说明]
    * @see [类、类#方法、类#成员]
    */
    @RequestMapping("/toUpdate")
    public String toUpdate(@RequestParam(value = "type") String type,
            @RequestParam("id") String id,
            @RequestParam MultiValueMap<String, String> requestMap,
            ModelMap response) {
        //        if (StringUtils.isEmpty(basicDataTypeCode)
        //                || !this.commonCode2TypeMap.containsKey(basicDataTypeCode)) {
        //            DataDict resDataDict = this.dataDictService.findById(id);
        //            AssertUtils.notNull(resDataDict, "resDataDict is null.id:{}", id);
        //            
        //            basicDataTypeCode = resDataDict.getBasicDataTypeCode();
        //        }
        //        
        //        BDPageTypeEnum bdPageType = BDPageTypeEnum.UpdateBasicData;
        //        String pageName = bdPageType.defaultPage;
        //        //基础数据类型
        //        BasicDataType bdType = this.commonCode2TypeMap.get(basicDataTypeCode);
        //        response.put("basicDataTypeCode", basicDataTypeCode);//基础数据类型编码
        //        response.put("basicDataType", bdType);//基础数据类型编码
        //        
        //        Class<? extends BasicData> type = bdType.getType();
        //        AssertUtils.notNull(type,
        //                "type is null.basicDataTypeCode:{}",
        //                basicDataTypeCode);
        //        BasicDataService<?> service = BasicDataContext.getContext()
        //                .getBasicDataService(type);
        //        BasicData bd = service.findById(id);
        //        response.put("basicData", bd);
        //        pageName = getPageName(bdType, bdPageType);
        
        String pageName = "/basicdata/updateBasicData";
        return pageName;
    }
    
//    /**
//     * 查询common = true的基础数据类型
//     * <功能详细描述>
//     * @return [参数说明]
//     *
//     * @return List<BasicDataType> [返回类型说明]
//     * @exception throws [异常类型] [异常说明]
//     * @see [类、类#方法、类#成员]
//    */
//    @ResponseBody
//    @RequestMapping("/queryTypeList")
//    public List<basic> queryTypeList(ModelMap response) {
//        List<BasicDataTypeTreeNode> resList = getCommonBasicDataTypeTreeNodeList();
//        return resList;
//    }
    //    
    //    /**
    //      * 获取通用的基础数据类型列表<br/>
    //      * <功能详细描述>
    //      * @return [参数说明]
    //      *
    //      * @return List<BasicDataType> [返回类型说明]
    //      * @exception throws [异常类型] [异常说明]
    //      * @see [类、类#方法、类#成员]
    //     */
    //    public List<BasicDataType> getCommonBasicDataTypeList() {
    //        Map<String, Object> paramMap = new HashMap<>();
    //        paramMap.put("common", true);
    //        List<BasicDataType> bdtList = this.basicDataTypeService.queryList(true,
    //                paramMap);
    //        
    //        return bdtList;
    //    }
    //    
    //    /**
    //      * 获取基础数据类型树状数据列表<br/>
    //      * <功能详细描述>
    //      * @return [参数说明]
    //      *
    //      * @return List<BasicDataTypeTreeNode> [返回类型说明]
    //      * @exception throws [异常类型] [异常说明]
    //      * @see [类、类#方法、类#成员]
    //     */
    //    public List<BasicDataTypeTreeNode> getCommonBasicDataTypeTreeNodeList() {
    //        List<BasicDataTypeTreeNode> nodeList = new ArrayList<>();
    //        List<BasicDataType> bdtList = getCommonBasicDataTypeList();
    //        
    //        for (BasicDataType bdt : bdtList) {
    //            nodeList.add(new BasicDataTypeTreeNode(bdt));
    //        }
    //        for (String module : this.moduleSet) {
    //            nodeList.add(new BasicDataTypeTreeNode(module));
    //        }
    //        //对基础数据进行排序--降序
    //        nodeList.sort(new Comparator<BasicDataTypeTreeNode>() {
    //            @Override
    //            public int compare(BasicDataTypeTreeNode o1,
    //                    BasicDataTypeTreeNode o2) {
    //                return o2.getName().compareTo(o1.getName());
    //            }
    //        });
    //        return nodeList;
    //    }
    //    
    //    /**
    //     * 判断DataDict:
    //     *  code
    //     *  basicDataTypeCode
    //     *
    //     * 是否已经被使用
    //     * @param uniqueGetterName
    //     * @param uniqueGetterName
    //     * @param excludeDataDictId
    //     * @return [参数说明]
    //     *
    //     * @return boolean [返回类型说明]
    //     * @exception throws [异常类型] [异常说明]
    //     * @see [类、类#方法、类#成员]
    //     */
    //    @ResponseBody
    //    @RequestMapping("/validateCodeIsExist")
    //    public Map<String, String> validateCodeIsExist(
    //            @RequestParam(value = "basicDataTypeCode", required = false) String basicDataTypeCode,
    //            @RequestParam("code") String code,
    //            @RequestParam(value = "id", required = false) String excludeDataDictId,
    //            @RequestParam MultiValueMap<String, String> request) {
    //        Map<String, String> key2valueMap = new HashMap<String, String>();
    //        key2valueMap.put("code", code);
    //        if (StringUtils.isEmpty(basicDataTypeCode)
    //                || !this.commonCode2TypeMap.containsKey(basicDataTypeCode)) {
    //            boolean flag = this.dataDictService.isExist(basicDataTypeCode,
    //                    key2valueMap,
    //                    excludeDataDictId);
    //            Map<String, String> resMap = new HashMap<String, String>();
    //            if (!flag) {
    //                resMap.put("ok", "可用的编码.");
    //            } else {
    //                resMap.put("error", "已经存在的编码.");
    //            }
    //            return resMap;
    //        }
    //        
    //        //基础数据类型
    //        BasicDataType bdType = this.commonCode2TypeMap.get(basicDataTypeCode);
    //        Class<? extends BasicData> type = bdType.getType();
    //        AssertUtils.notNull(type,
    //                "type is null.basicDataTypeCode:{}",
    //                basicDataTypeCode);
    //        
    //        BasicDataService<?> service = BasicDataContext.getContext()
    //                .getBasicDataService(type);
    //        boolean flag = service.isExist(key2valueMap, excludeDataDictId);
    //        Map<String, String> resMap = new HashMap<String, String>();
    //        if (!flag) {
    //            resMap.put("ok", "可用的编码.");
    //        } else {
    //            resMap.put("error", "已经存在的编码.");
    //        }
    //        return resMap;
    //    }
    //    
    //    /**
    //     * 查询DataDict列表<br/>
    //     * <功能详细描述>
    //     * @return [参数说明]
    //     *
    //     * @return List<DataDict> [返回类型说明]
    //     * @exception throws [异常类型] [异常说明]
    //     * @see [类、类#方法、类#成员]
    //    */
    //    @ResponseBody
    //    @RequestMapping("/queryList")
    //    public List<? extends BasicData> queryList(
    //            @RequestParam(value = "basicDataTypeCode", required = false) String basicDataTypeCode,
    //            @RequestParam(value = "valid", required = false) Boolean valid,
    //            @RequestParam(value = "modifyAble", required = false) Boolean modifyAble,
    //            @RequestParam MultiValueMap<String, String> request) {
    //        Map<String, Object> params = new HashMap<>();
    //        params.put("modifyAble", modifyAble);
    //        params.put("code", request.getFirst("code"));
    //        params.put("name", request.getFirst("name"));
    //        params.put("remark", request.getFirst("remark"));
    //        
    //        if (StringUtils.isEmpty(basicDataTypeCode)
    //                || !this.commonCode2TypeMap.containsKey(basicDataTypeCode)) {
    //            List<DataDict> resList = this.dataDictService
    //                    .queryList(basicDataTypeCode, valid, params);
    //            return resList;
    //        }
    //        
    //        //基础数据类型
    //        BasicDataType bdType = this.commonCode2TypeMap.get(basicDataTypeCode);
    //        Class<? extends BasicData> type = bdType.getType();
    //        AssertUtils.notNull(type,
    //                "type is null.basicDataTypeCode:{}",
    //                basicDataTypeCode);
    //        
    //        BasicDataService<?> service = BasicDataContext.getContext()
    //                .getBasicDataService(type);
    //        List<? extends BasicData> resList = service.queryList(valid, params);
    //        return resList;
    //    }
    //    
    //    /**
    //     * 查询DataDict分页列表<br/>
    //     *<功能详细描述>
    //     * @return [参数说明]
    //     *
    //     * @return List<DataDict> [返回类型说明]
    //     * @exception throws [异常类型] [异常说明]
    //     * @see [类、类#方法、类#成员]
    //    */
    //    @ResponseBody
    //    @RequestMapping("/queryPagedList")
    //    public PagedList<? extends BasicData> queryPagedList(
    //            @RequestParam(value = "basicDataTypeCode", required = false) String basicDataTypeCode,
    //            @RequestParam(value = "valid", required = false) Boolean valid,
    //            @RequestParam(value = "modifyAble", required = false) Boolean modifyAble,
    //            @RequestParam(value = "pageNumber", required = false, defaultValue = "1") int pageIndex,
    //            @RequestParam(value = "pageSize", required = false, defaultValue = "10") int pageSize,
    //            @RequestParam MultiValueMap<String, String> request) {
    //        Map<String, Object> params = new HashMap<>();
    //        params.put("modifyAble", modifyAble);
    //        params.put("code", request.getFirst("code"));
    //        params.put("name", request.getFirst("name"));
    //        params.put("remark", request.getFirst("remark"));
    //        
    //        if (StringUtils.isEmpty(basicDataTypeCode)
    //                || !this.commonCode2TypeMap.containsKey(basicDataTypeCode)) {
    //            PagedList<DataDict> resPagedList = this.dataDictService
    //                    .queryPagedList(basicDataTypeCode,
    //                            valid,
    //                            params,
    //                            pageIndex,
    //                            pageSize);
    //            return resPagedList;
    //        }
    //        
    //        //基础数据类型
    //        BasicDataType bdType = this.commonCode2TypeMap.get(basicDataTypeCode);
    //        Class<? extends BasicData> type = bdType.getType();
    //        AssertUtils.notNull(type,
    //                "type is null.basicDataTypeCode:{}",
    //                basicDataTypeCode);
    //        
    //        BasicDataService<? extends BasicData> service = BasicDataContext
    //                .getContext().getBasicDataService(type);
    //        PagedList<? extends BasicData> resPagedList = service
    //                .queryPagedList(valid, params, pageIndex, pageSize);
    //        return resPagedList;
    //    }
    //    
    //    /**
    //     * 添加组织结构页面
    //     *<功能详细描述>
    //     * @param organization [参数说明]
    //     *
    //     * @return void [返回类型说明]
    //     * @exception throws [异常类型] [异常说明]
    //     * @see [类、类#方法、类#成员]
    //    */
    //    @RequestMapping("/add")
    //    @ResponseBody
    //    @SuppressWarnings({ "rawtypes", "unchecked" })
    //    public boolean add(
    //            @RequestParam(value = "basicDataTypeCode") String basicDataTypeCode,
    //            @RequestParam MultiValueMap<String, String> requestMap) {
    //        if (StringUtils.isEmpty(basicDataTypeCode)
    //                || !this.commonCode2TypeMap.containsKey(basicDataTypeCode)) {
    //            return false;
    //        }
    //        
    //        //基础数据类型
    //        BasicDataType bdType = this.commonCode2TypeMap.get(basicDataTypeCode);
    //        Class<? extends BasicData> type = bdType.getType();
    //        if (type == null) {
    //            return false;
    //        }
    //        
    //        BasicData obj = ObjectUtils.newInstance(type);
    //        obj.setModifyAble(true);
    //        BeanWrapper bw = PropertyAccessorFactory.forBeanPropertyAccess(obj);
    //        for (PropertyDescriptor pd : bw.getPropertyDescriptors()) {
    //            String propertyName = pd.getName();
    //            //如果属性存在并且可以写入则进行写入
    //            if (bw.isWritableProperty(propertyName)
    //                    && requestMap.containsKey(propertyName)) {
    //                bw.setPropertyValue(propertyName,
    //                        requestMap.getFirst(propertyName));
    //            }
    //        }
    //        BasicDataService service = BasicDataContext.getContext()
    //                .getBasicDataService(type);
    //        service.insert(obj);
    //        return true;
    //    }
    //    
    //    /**
    //      * 更新组织<br/>
    //      * <功能详细描述>
    //      * @param dataDict
    //      * @return [参数说明]
    //      *
    //      * @return boolean [返回类型说明]
    //      * @exception throws [异常类型] [异常说明]
    //      * @see [类、类#方法、类#成员]
    //     */
    //    @RequestMapping("/update")
    //    @ResponseBody
    //    @SuppressWarnings({ "rawtypes", "unchecked" })
    //    public boolean update(
    //            @RequestParam(value = "basicDataTypeCode") String basicDataTypeCode,
    //            @RequestParam MultiValueMap<String, String> requestMap) {
    //        if (StringUtils.isEmpty(basicDataTypeCode)
    //                || !this.commonCode2TypeMap.containsKey(basicDataTypeCode)) {
    //            return false;
    //        }
    //        
    //        //基础数据类型
    //        BasicDataType bdType = this.commonCode2TypeMap.get(basicDataTypeCode);
    //        Class<? extends BasicData> type = bdType.getType();
    //        if (type == null) {
    //            return false;
    //        }
    //        
    //        BasicData obj = ObjectUtils.newInstance(type);
    //        BeanWrapper bw = PropertyAccessorFactory.forBeanPropertyAccess(obj);
    //        for (PropertyDescriptor pd : bw.getPropertyDescriptors()) {
    //            String propertyName = pd.getName();
    //            //如果属性存在并且可以写入则进行写入
    //            if (bw.isWritableProperty(propertyName)
    //                    && requestMap.containsKey(propertyName)) {
    //                bw.setPropertyValue(propertyName,
    //                        requestMap.getFirst(propertyName));
    //            }
    //        }
    //        BasicDataService service = BasicDataContext.getContext()
    //                .getBasicDataService(type);
    //        service.updateById(obj);
    //        return true;
    //    }
    //    
    //    /**
    //      * 删除指定DataDict<br/>
    //      *<功能详细描述>
    //      * @param dataDictId
    //      * @return [参数说明]
    //      *
    //      * @return boolean [返回类型说明]
    //      * @exception throws [异常类型] [异常说明]
    //      * @see [类、类#方法、类#成员]
    //     */
    //    @ResponseBody
    //    @RequestMapping("/deleteById")
    //    public boolean deleteById(
    //            @RequestParam(value = "basicDataTypeCode") String basicDataTypeCode,
    //            @RequestParam(value = "id") String id) {
    //        if (StringUtils.isEmpty(basicDataTypeCode)
    //                || !this.commonCode2TypeMap.containsKey(basicDataTypeCode)) {
    //            DataDict dd = this.dataDictService.findEntityById(id);
    //            if (dd == null) {
    //                return false;
    //            }
    //            basicDataTypeCode = dd.getBasicDataTypeCode();
    //        }
    //        
    //        //基础数据类型
    //        BasicDataType bdType = this.commonCode2TypeMap.get(basicDataTypeCode);
    //        Class<? extends BasicData> type = bdType.getType();
    //        AssertUtils.notNull(type,
    //                "type is null.basicDataTypeCode:{}",
    //                basicDataTypeCode);
    //        
    //        BasicDataService<? extends BasicData> service = BasicDataContext
    //                .getContext().getBasicDataService(type);
    //        boolean resFlag = service.deleteById(id);
    //        return resFlag;
    //    }
    //    
    //    /**
    //      * 禁用DataDict
    //      * @param dataDictId
    //      * @return [参数说明]
    //      *
    //      * @return boolean [返回类型说明]
    //      * @exception throws [异常类型] [异常说明]
    //      * @see [类、类#方法、类#成员]
    //     */
    //    @ResponseBody
    //    @RequestMapping("/disableById")
    //    public boolean disableById(
    //            @RequestParam(value = "basicDataTypeCode") String basicDataTypeCode,
    //            @RequestParam(value = "id") String id) {
    //        if (StringUtils.isEmpty(basicDataTypeCode)
    //                || !this.commonCode2TypeMap.containsKey(basicDataTypeCode)) {
    //            DataDict dd = this.dataDictService.findEntityById(id);
    //            if (dd == null) {
    //                return false;
    //            }
    //            basicDataTypeCode = dd.getBasicDataTypeCode();
    //        }
    //        
    //        //基础数据类型
    //        BasicDataType bdType = this.commonCode2TypeMap.get(basicDataTypeCode);
    //        Class<? extends BasicData> type = bdType.getType();
    //        AssertUtils.notNull(type,
    //                "type is null.basicDataTypeCode:{}",
    //                basicDataTypeCode);
    //        
    //        BasicDataService<? extends BasicData> service = BasicDataContext
    //                .getContext().getBasicDataService(type);
    //        boolean resFlag = service.disableById(id);
    //        return resFlag;
    //    }
    //    
    //    /**
    //      * 启用DataDict<br/>
    //      *<功能详细描述>
    //      * @param dataDictId
    //      * @return [参数说明]
    //      *
    //      * @return boolean [返回类型说明]
    //      * @exception throws [异常类型] [异常说明]
    //      * @see [类、类#方法、类#成员]
    //     */
    //    @ResponseBody
    //    @RequestMapping("/enableById")
    //    public boolean enableById(
    //            @RequestParam(value = "basicDataTypeCode") String basicDataTypeCode,
    //            @RequestParam(value = "id") String id) {
    //        if (StringUtils.isEmpty(basicDataTypeCode)
    //                || !this.commonCode2TypeMap.containsKey(basicDataTypeCode)) {
    //            DataDict dd = this.dataDictService.findEntityById(id);
    //            if (dd == null) {
    //                return false;
    //            }
    //            basicDataTypeCode = dd.getBasicDataTypeCode();
    //        }
    //        
    //        //基础数据类型
    //        BasicDataType bdType = this.commonCode2TypeMap.get(basicDataTypeCode);
    //        Class<? extends BasicData> type = bdType.getType();
    //        AssertUtils.notNull(type,
    //                "type is null.basicDataTypeCode:{}",
    //                basicDataTypeCode);
    //        
    //        BasicDataService<? extends BasicData> service = BasicDataContext
    //                .getContext().getBasicDataService(type);
    //        boolean resFlag = service.enableById(id);
    //        return resFlag;
    //    }
    //    
    //    /**
    //      * 获取页面名称<br/>
    //      * <功能详细描述>
    //      * @param pageType
    //      * @param basicDataTypeCode
    //      * @return [参数说明]
    //      *
    //      * @return String [返回类型说明]
    //      * @exception throws [异常类型] [异常说明]
    //      * @see [类、类#方法、类#成员]
    //     */
    //    private String getPageName(BasicDataEntityInfo entityInfo,
    //            BDPageTypeEnum pageType) {
    //        AssertUtils.notNull(entityInfo, "entityInfo is null.");
    //        AssertUtils.notNull(pageType, "methodName is null.");
    //        
    //        String module = baisBasicDataType.getModule().toLowerCase();
    //        String code = baisBasicDataType.getCode().toLowerCase();
    //        AssertUtils.notEmpty(module, "module is empty.");
    //        AssertUtils.notEmpty(code, "code is empty.");
    //        
    //        String page = "";
    //        if (!type2module2code2page.containsKey(pageType)) {
    //            type2module2code2page.put(pageType,
    //                    new HashMap<String, Map<String, String>>());
    //        }
    //        if (!type2module2code2page.get(pageType).containsKey(module)) {
    //            type2module2code2page.get(pageType).put(module,
    //                    new HashMap<String, String>());
    //        }
    //        if (type2module2code2page.get(pageType).get(module).containsKey(code)) {
    //            page = type2module2code2page.get(pageType).get(module).get(code);
    //            return page;
    //        }
    //        
    //        StringBuilder sb = new StringBuilder(TxConstants.INITIAL_STR_LENGTH);
    //        sb.append("/").append(module).append("/").append(code).append("/");
    //        sb.append(pageType.getPageName());
    //        page = sb.toString();
    //        if (!isExistOfJspPage(page)) {
    //            //如果页面不存在获取其对应的默认页面
    //            page = pageType.getDefaultPage();
    //        }
    //        type2module2code2page.get(pageType).get(module).put(code, page);
    //        return page;
    //    }
    
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
    private boolean exists(String page) {
        //        StringBuilder jspPageSB = new StringBuilder("/WEB-INF/view");
        //        jspPageSB.append("/").append(page).append(".jsp");
        //        String pagePath = StringUtils.cleanPath(jspPageSB.toString());
        //        org.springframework.core.io.Resource jspPageResource = resourceLoader
        //                .getResource(pagePath);
        //        if (jspPageResource.exists()) {
        //            return true;
        //        } else {
        //            return false;
        //        }
        View view = null;
        try {
            view = this.viewResolverComposite.resolveViewName(page, null);
        } catch (Exception e) {
            view = null;
        }
        if (view == null) {
            return false;
        } else {
            return true;
        }
        
    }
    
    /**
     * 基础数据页类型<br/>
     * <功能详细描述>
     *
     * @author  Administrator
     * @version  [版本号, 2016年10月10日]
     * @see  [相关类/方法]
     * @since  [产品/模块版本]
     */
    public static enum BDPageTypeEnum {
        //增加基础数据
        AddBasicData("add", "/basicdata/addBasicData"),
        
        //更新基础数据
        UpdateBasicData("update", "/basicdata/updateBasicData"),
        
        //查询树列表
        QueryTree("queryTree", "/basicdata/queryBasicDataPagedList"),
        
        //查询列表
        //如果类型为空的时候，跳转到基础数据列表《分页》
        QueryList("queryList", "/basicdata/queryBasicDataList"),
        
        //查询分页列表
        //如果类型为空的时候，跳转到基础数据列表《分页》
        QueryPagedList("queryPagedList", "/basicdata/queryBasicDataPagedList");
        
        /** 默认页面 */
        private final String pageName;
        
        private final String defaultPage;
        
        /** <默认构造函数> */
        private BDPageTypeEnum(String pageName, String defaultPage) {
            this.pageName = pageName;
            this.defaultPage = defaultPage;
        }
        
        public String getPageName() {
            return pageName;
        }
        
        public String getDefaultPage() {
            return defaultPage;
        }
    }
}