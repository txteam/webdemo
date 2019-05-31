///*
// * 描       述:  <描述>
// * 修  改 人:
// * 修改时间:
// * <修改描述:>
// */
//package com.tx.local.basicdata.controller;
//
//import java.util.Arrays;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//import javax.annotation.Resource;
//
//import org.apache.commons.lang3.StringUtils;
//import org.springframework.beans.BeansException;
//import org.springframework.beans.factory.InitializingBean;
//import org.springframework.context.ApplicationContext;
//import org.springframework.context.ApplicationContextAware;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.ModelMap;
//import org.springframework.util.MultiValueMap;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.ResponseBody;
//import org.springframework.web.servlet.View;
//import org.springframework.web.servlet.ViewResolver;
//import org.springframework.web.servlet.view.ViewResolverComposite;
//
//import com.tx.component.basicdata.context.BasicDataContext;
//import com.tx.component.basicdata.model.BasicData;
//import com.tx.component.basicdata.model.BasicDataEntityInfo;
//import com.tx.component.basicdata.model.BasicDataViewTypeEnum;
//import com.tx.component.basicdata.registry.BasicDataEntityRegistry;
//import com.tx.component.basicdata.service.BasicDataService;
//import com.tx.core.TxConstants;
//import com.tx.core.exceptions.util.AssertUtils;
//import com.tx.core.paged.model.PagedList;
//
///**
// * DataDict显示层逻辑<br/>
// * <功能详细描述>
// *
// * @author  PengQingyang
// * @version  [版本号, 2013-8-27]
// * @see  [相关类/方法]
// * @since  [产品/模块版本]
// */
//@Controller
//@RequestMapping("/basicdata")
//public class BasicDataController
//        implements InitializingBean, ApplicationContextAware {
//
//    /** spring容器句柄 */
//    protected ApplicationContext applicationContext;
//
//    /** 基础数据实体注册表 */
//    @Resource
//    private BasicDataEntityRegistry basicDataEntityRegistry;
//
//    /** 基础数据容器 */
//    @Resource
//    private BasicDataContext basicDataContext;
//
//    /** 视图逻辑解析层 */
//    private List<ViewResolver> viewResolvers;
//
//    /** 视图逻辑解析对象 */
//    private ViewResolverComposite viewResolverComposite;
//
//    /** 页面映射 */
//    private final Map<String, String> pageMap = new HashMap<>();
//
//    /** <默认构造函数> */
//    public BasicDataController(List<ViewResolver> viewResolvers) {
//        super();
//        this.viewResolvers = viewResolvers;
//    }
//
//    /**
//     * @throws Exception
//     */
//    @Override
//    public void afterPropertiesSet() throws Exception {
//        AssertUtils.notEmpty(this.viewResolvers, "viewResolvers is empty.");
//        this.viewResolverComposite = new ViewResolverComposite();
//        this.viewResolverComposite.setViewResolvers(this.viewResolvers);
//
//    }
//
//    /**
//     * @param applicationContext
//     * @throws BeansException
//     */
//    @Override
//    public void setApplicationContext(ApplicationContext applicationContext)
//            throws BeansException {
//        this.applicationContext = applicationContext;
//    }
//
//    /**
//     * 跳转到数据字典管理页面<br/>
//     * <功能详细描述>
//     * @param response
//     * @return [参数说明]
//     *
//     * @return String [返回类型说明]
//     * @exception throws [异常类型] [异常说明]
//     * @see [类、类#方法、类#成员]
//     */
//    @RequestMapping("/toQueryBasicDataMain")
//    public String toQueryBasicDataMain(ModelMap response) {
//        //加载数据类型
//        return "/basicdata/queryBasicDataMain";
//    }
//
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
//            @RequestParam(value = "type", required = false) String type,
//            @RequestParam Map<String, String> requestMap, ModelMap response) {
//        //基础数据类型集合
//        response.put("type", type);
//        PageTypeEnum defaultPageType = PageTypeEnum.QueryList;
//        response.put("pageType", defaultPageType);
//
//        String pageName = defaultPageType.getDefaultPage();
//        BasicDataEntityInfo info = this.basicDataEntityRegistry
//                .getEntityInfo(type);
//        if (info == null) {
//            //返回默认页面
//            return pageName;
//        }
//        response.put("typeInfo", info);
//
//        //跳转到查询页
//        switch (info.getViewType()) {
//            case COMMON_PAGEDLIST:
//            case PAGEDLIST:
//                pageName = getPageName(info, PageTypeEnum.QueryPagedList);
//                break;
//            case COMMON_TREE:
//            case TREE:
//                pageName = getPageName(info, PageTypeEnum.QueryTree);
//                break;
//            case COMMON_LIST:
//            case LIST:
//            default:
//                pageName = getPageName(info, PageTypeEnum.QueryList);
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
//    public String toAdd(@RequestParam(value = "type") String type,
//            @RequestParam Map<String, String> requestMap, ModelMap response) {
//        //基础数据类型集合
//        response.put("type", type);
//        PageTypeEnum defaultPageType = PageTypeEnum.Add;
//        response.put("pageType", defaultPageType);
//
//        String pageName = defaultPageType.getDefaultPage();
//        BasicDataEntityInfo info = this.basicDataEntityRegistry
//                .getEntityInfo(type);
//        if (info == null) {
//            //返回默认页面
//            return pageName;
//        }
//        //跳转到查询页
//        pageName = getPageName(info, PageTypeEnum.Add);
//        return pageName;
//    }
//
//    /**
//    * 跳转到编辑DataDict页面
//    *<功能详细描述>
//    * @return [参数说明]
//    *
//    * @return String [返回类型说明]
//    * @exception throws [异常类型] [异常说明]
//    * @see [类、类#方法、类#成员]
//    */
//    @RequestMapping("/toUpdate")
//    public String toUpdate(@RequestParam(value = "type") String type,
//            @RequestParam("id") String id,
//            @RequestParam MultiValueMap<String, String> requestMap,
//            ModelMap response) {
//        //基础数据类型集合
//        response.put("type", type);
//        PageTypeEnum defaultPageType = PageTypeEnum.Add;
//        response.put("pageType", defaultPageType);
//
//        String pageName = defaultPageType.getDefaultPage();
//        BasicDataEntityInfo info = this.basicDataEntityRegistry
//                .getEntityInfo(type);
//        if (info == null) {
//            //返回默认页面
//            return pageName;
//        }
//
//        Class<? extends BasicData> entityClass = info.getEntityClass();
//        AssertUtils.notNull(entityClass, "entityClass is null.type:{}", type);
//        BasicDataService<?> service = BasicDataContext.getContext()
//                .getBasicDataService(entityClass);
//        BasicData bd = service.findById(id);
//        response.put("data", bd);
//        //跳转到查询页
//        pageName = getPageName(info, PageTypeEnum.Add);
//        return pageName;
//    }
//
//    /**
//     * 查询common = true的基础数据类型
//     * <功能详细描述>
//     * @return [参数说明]
//     *
//     * @return List<BasicDataType> [返回类型说明]
//     * @exception throws [异常类型] [异常说明]
//     * @see [类、类#方法、类#成员]
//     */
//    @ResponseBody
//    @RequestMapping("/queryCommonEntityInfo")
//    public List<BasicDataEntityInfo> queryCommonEntityInfo(ModelMap response) {
//        List<BasicDataEntityInfo> infoList = basicDataEntityRegistry
//                .getEntityInfoList(null,
//                        Arrays.asList(BasicDataViewTypeEnum.COMMON_LIST,
//                                BasicDataViewTypeEnum.COMMON_PAGEDLIST,
//                                BasicDataViewTypeEnum.COMMON_TREE));
//        return infoList;
//    }
//
//    //
//    //    /**
//    //      * 获取通用的基础数据类型列表<br/>
//    //      * <功能详细描述>
//    //      * @return [参数说明]
//    //      *
//    //      * @return List<BasicDataType> [返回类型说明]
//    //      * @exception throws [异常类型] [异常说明]
//    //      * @see [类、类#方法、类#成员]
//    //     */
//    //    public List<BasicDataType> getCommonBasicDataTypeList() {
//    //        Map<String, Object> paramMap = new HashMap<>();
//    //        paramMap.put("common", true);
//    //        List<BasicDataType> bdtList = this.basicDataTypeService.queryList(true,
//    //                paramMap);
//    //
//    //        return bdtList;
//    //    }
//    //
//    //    /**
//    //      * 获取基础数据类型树状数据列表<br/>
//    //      * <功能详细描述>
//    //      * @return [参数说明]
//    //      *
//    //      * @return List<BasicDataTypeTreeNode> [返回类型说明]
//    //      * @exception throws [异常类型] [异常说明]
//    //      * @see [类、类#方法、类#成员]
//    //     */
//    //    public List<BasicDataTypeTreeNode> getCommonBasicDataTypeTreeNodeList() {
//    //        List<BasicDataTypeTreeNode> nodeList = new ArrayList<>();
//    //        List<BasicDataType> bdtList = getCommonBasicDataTypeList();
//    //
//    //        for (BasicDataType bdt : bdtList) {
//    //            nodeList.add(new BasicDataTypeTreeNode(bdt));
//    //        }
//    //        for (String module : this.moduleSet) {
//    //            nodeList.add(new BasicDataTypeTreeNode(module));
//    //        }
//    //        //对基础数据进行排序--降序
//    //        nodeList.sort(new Comparator<BasicDataTypeTreeNode>() {
//    //            @Override
//    //            public int compare(BasicDataTypeTreeNode o1,
//    //                    BasicDataTypeTreeNode o2) {
//    //                return o2.getName().compareTo(o1.getName());
//    //            }
//    //        });
//    //        return nodeList;
//    //    }
//    //
//    //    /**
//    //     * 判断DataDict:
//    //     *  code
//    //     *  basicDataTypeCode
//    //     *
//    //     * 是否已经被使用
//    //     * @param uniqueGetterName
//    //     * @param uniqueGetterName
//    //     * @param excludeDataDictId
//    //     * @return [参数说明]
//    //     *
//    //     * @return boolean [返回类型说明]
//    //     * @exception throws [异常类型] [异常说明]
//    //     * @see [类、类#方法、类#成员]
//    //     */
//    //    @ResponseBody
//    //    @RequestMapping("/validateCodeIsExist")
//    //    public Map<String, String> validateCodeIsExist(
//    //            @RequestParam(value = "basicDataTypeCode", required = false) String basicDataTypeCode,
//    //            @RequestParam("code") String code,
//    //            @RequestParam(value = "id", required = false) String excludeDataDictId,
//    //            @RequestParam MultiValueMap<String, String> request) {
//    //        Map<String, String> key2valueMap = new HashMap<String, String>();
//    //        key2valueMap.put("code", code);
//    //        if (StringUtils.isEmpty(basicDataTypeCode)
//    //                || !this.commonCode2TypeMap.containsKey(basicDataTypeCode)) {
//    //            boolean flag = this.dataDictService.isExist(basicDataTypeCode,
//    //                    key2valueMap,
//    //                    excludeDataDictId);
//    //            Map<String, String> resMap = new HashMap<String, String>();
//    //            if (!flag) {
//    //                resMap.put("ok", "可用的编码.");
//    //            } else {
//    //                resMap.put("error", "已经存在的编码.");
//    //            }
//    //            return resMap;
//    //        }
//    //
//    //        //基础数据类型
//    //        BasicDataType bdType = this.commonCode2TypeMap.get(basicDataTypeCode);
//    //        Class<? extends BasicData> type = bdType.getType();
//    //        AssertUtils.notNull(type,
//    //                "type is null.basicDataTypeCode:{}",
//    //                basicDataTypeCode);
//    //
//    //        BasicDataService<?> service = BasicDataContext.getContext()
//    //                .getBasicDataService(type);
//    //        boolean flag = service.isExist(key2valueMap, excludeDataDictId);
//    //        Map<String, String> resMap = new HashMap<String, String>();
//    //        if (!flag) {
//    //            resMap.put("ok", "可用的编码.");
//    //        } else {
//    //            resMap.put("error", "已经存在的编码.");
//    //        }
//    //        return resMap;
//    //    }
//    //
//    /**
//     * 查询DataDict列表<br/>
//     * <功能详细描述>
//     * @return [参数说明]
//     *
//     * @return List<DataDict> [返回类型说明]
//     * @exception throws [异常类型] [异常说明]
//     * @see [类、类#方法、类#成员]
//    */
//    @SuppressWarnings("unchecked")
//    @ResponseBody
//    @RequestMapping("/queryList")
//    public <T extends BasicData> List<T> queryList(
//            @RequestParam(value = "type", required = false) String type,
//            @RequestParam(value = "valid", required = false) Boolean valid,
//            @RequestParam MultiValueMap<String, String> request) {
//        Map<String, Object> params = new HashMap<>();
//        params.put("code", request.getFirst("code"));
//        params.put("name", request.getFirst("name"));
//        params.put("remark", request.getFirst("remark"));
//
//        Class<T> entityClass = (Class<T>) BasicDataContext.getContext()
//                .getEntityClass(type);
//        AssertUtils.notNull(entityClass, "entityClass is null. type:{}.", type);
//
//        BasicDataService<T> service = BasicDataContext.getContext()
//                .getBasicDataService(entityClass);
//        List<T> resList = service.queryList(valid, params);
//
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
//    @SuppressWarnings("unchecked")
//    @ResponseBody
//    @RequestMapping("/queryPagedList")
//    public <T extends BasicData> PagedList<T> queryPagedList(
//            @RequestParam(value = "type", required = false) String type,
//            @RequestParam(value = "valid", required = false) Boolean valid,
//            @RequestParam(value = "pageNumber", required = false, defaultValue = "1") int pageIndex,
//            @RequestParam(value = "pageSize", required = false, defaultValue = "10") int pageSize,
//            @RequestParam MultiValueMap<String, String> request) {
//        Map<String, Object> params = new HashMap<>();
//        params.put("code", request.getFirst("code"));
//        params.put("name", request.getFirst("name"));
//        params.put("remark", request.getFirst("remark"));
//
//        Class<T> entityClass = (Class<T>) BasicDataContext.getContext()
//                .getEntityClass(type);
//        AssertUtils.notNull(entityClass, "entityClass is null. type:{}.", type);
//
//        BasicDataService<T> service = BasicDataContext.getContext()
//                .getBasicDataService(entityClass);
//        PagedList<T> resPagedList = service.queryPagedList(valid,
//                params,
//                pageIndex,
//                pageSize);
//        return resPagedList;
//    }
//
//    //
//    //    /**
//    //     * 添加组织结构页面
//    //     *<功能详细描述>
//    //     * @param organization [参数说明]
//    //     *
//    //     * @return void [返回类型说明]
//    //     * @exception throws [异常类型] [异常说明]
//    //     * @see [类、类#方法、类#成员]
//    //    */
//    //    @RequestMapping("/add")
//    //    @ResponseBody
//    //    @SuppressWarnings({ "rawtypes", "unchecked" })
//    //    public boolean add(
//    //            @RequestParam(value = "basicDataTypeCode") String basicDataTypeCode,
//    //            @RequestParam MultiValueMap<String, String> requestMap) {
//    //        if (StringUtils.isEmpty(basicDataTypeCode)
//    //                || !this.commonCode2TypeMap.containsKey(basicDataTypeCode)) {
//    //            return false;
//    //        }
//    //
//    //        //基础数据类型
//    //        BasicDataType bdType = this.commonCode2TypeMap.get(basicDataTypeCode);
//    //        Class<? extends BasicData> type = bdType.getType();
//    //        if (type == null) {
//    //            return false;
//    //        }
//    //
//    //        BasicData obj = ObjectUtils.newInstance(type);
//    //        obj.setModifyAble(true);
//    //        BeanWrapper bw = PropertyAccessorFactory.forBeanPropertyAccess(obj);
//    //        for (PropertyDescriptor pd : bw.getPropertyDescriptors()) {
//    //            String propertyName = pd.getName();
//    //            //如果属性存在并且可以写入则进行写入
//    //            if (bw.isWritableProperty(propertyName)
//    //                    && requestMap.containsKey(propertyName)) {
//    //                bw.setPropertyValue(propertyName,
//    //                        requestMap.getFirst(propertyName));
//    //            }
//    //        }
//    //        BasicDataService service = BasicDataContext.getContext()
//    //                .getBasicDataService(type);
//    //        service.insert(obj);
//    //        return true;
//    //    }
//    //
//    //    /**
//    //      * 更新组织<br/>
//    //      * <功能详细描述>
//    //      * @param dataDict
//    //      * @return [参数说明]
//    //      *
//    //      * @return boolean [返回类型说明]
//    //      * @exception throws [异常类型] [异常说明]
//    //      * @see [类、类#方法、类#成员]
//    //     */
//    //    @RequestMapping("/update")
//    //    @ResponseBody
//    //    @SuppressWarnings({ "rawtypes", "unchecked" })
//    //    public boolean update(
//    //            @RequestParam(value = "basicDataTypeCode") String basicDataTypeCode,
//    //            @RequestParam MultiValueMap<String, String> requestMap) {
//    //        if (StringUtils.isEmpty(basicDataTypeCode)
//    //                || !this.commonCode2TypeMap.containsKey(basicDataTypeCode)) {
//    //            return false;
//    //        }
//    //
//    //        //基础数据类型
//    //        BasicDataType bdType = this.commonCode2TypeMap.get(basicDataTypeCode);
//    //        Class<? extends BasicData> type = bdType.getType();
//    //        if (type == null) {
//    //            return false;
//    //        }
//    //
//    //        BasicData obj = ObjectUtils.newInstance(type);
//    //        BeanWrapper bw = PropertyAccessorFactory.forBeanPropertyAccess(obj);
//    //        for (PropertyDescriptor pd : bw.getPropertyDescriptors()) {
//    //            String propertyName = pd.getName();
//    //            //如果属性存在并且可以写入则进行写入
//    //            if (bw.isWritableProperty(propertyName)
//    //                    && requestMap.containsKey(propertyName)) {
//    //                bw.setPropertyValue(propertyName,
//    //                        requestMap.getFirst(propertyName));
//    //            }
//    //        }
//    //        BasicDataService service = BasicDataContext.getContext()
//    //                .getBasicDataService(type);
//    //        service.updateById(obj);
//    //        return true;
//    //    }
//    //
//    //    /**
//    //      * 删除指定DataDict<br/>
//    //      *<功能详细描述>
//    //      * @param dataDictId
//    //      * @return [参数说明]
//    //      *
//    //      * @return boolean [返回类型说明]
//    //      * @exception throws [异常类型] [异常说明]
//    //      * @see [类、类#方法、类#成员]
//    //     */
//    //    @ResponseBody
//    //    @RequestMapping("/deleteById")
//    //    public boolean deleteById(
//    //            @RequestParam(value = "basicDataTypeCode") String basicDataTypeCode,
//    //            @RequestParam(value = "id") String id) {
//    //        if (StringUtils.isEmpty(basicDataTypeCode)
//    //                || !this.commonCode2TypeMap.containsKey(basicDataTypeCode)) {
//    //            DataDict dd = this.dataDictService.findEntityById(id);
//    //            if (dd == null) {
//    //                return false;
//    //            }
//    //            basicDataTypeCode = dd.getBasicDataTypeCode();
//    //        }
//    //
//    //        //基础数据类型
//    //        BasicDataType bdType = this.commonCode2TypeMap.get(basicDataTypeCode);
//    //        Class<? extends BasicData> type = bdType.getType();
//    //        AssertUtils.notNull(type,
//    //                "type is null.basicDataTypeCode:{}",
//    //                basicDataTypeCode);
//    //
//    //        BasicDataService<? extends BasicData> service = BasicDataContext
//    //                .getContext().getBasicDataService(type);
//    //        boolean resFlag = service.deleteById(id);
//    //        return resFlag;
//    //    }
//    //
//    //    /**
//    //      * 禁用DataDict
//    //      * @param dataDictId
//    //      * @return [参数说明]
//    //      *
//    //      * @return boolean [返回类型说明]
//    //      * @exception throws [异常类型] [异常说明]
//    //      * @see [类、类#方法、类#成员]
//    //     */
//    //    @ResponseBody
//    //    @RequestMapping("/disableById")
//    //    public boolean disableById(
//    //            @RequestParam(value = "basicDataTypeCode") String basicDataTypeCode,
//    //            @RequestParam(value = "id") String id) {
//    //        if (StringUtils.isEmpty(basicDataTypeCode)
//    //                || !this.commonCode2TypeMap.containsKey(basicDataTypeCode)) {
//    //            DataDict dd = this.dataDictService.findEntityById(id);
//    //            if (dd == null) {
//    //                return false;
//    //            }
//    //            basicDataTypeCode = dd.getBasicDataTypeCode();
//    //        }
//    //
//    //        //基础数据类型
//    //        BasicDataType bdType = this.commonCode2TypeMap.get(basicDataTypeCode);
//    //        Class<? extends BasicData> type = bdType.getType();
//    //        AssertUtils.notNull(type,
//    //                "type is null.basicDataTypeCode:{}",
//    //                basicDataTypeCode);
//    //
//    //        BasicDataService<? extends BasicData> service = BasicDataContext
//    //                .getContext().getBasicDataService(type);
//    //        boolean resFlag = service.disableById(id);
//    //        return resFlag;
//    //    }
//    //
//    //    /**
//    //      * 启用DataDict<br/>
//    //      *<功能详细描述>
//    //      * @param dataDictId
//    //      * @return [参数说明]
//    //      *
//    //      * @return boolean [返回类型说明]
//    //      * @exception throws [异常类型] [异常说明]
//    //      * @see [类、类#方法、类#成员]
//    //     */
//    //    @ResponseBody
//    //    @RequestMapping("/enableById")
//    //    public boolean enableById(
//    //            @RequestParam(value = "basicDataTypeCode") String basicDataTypeCode,
//    //            @RequestParam(value = "id") String id) {
//    //        if (StringUtils.isEmpty(basicDataTypeCode)
//    //                || !this.commonCode2TypeMap.containsKey(basicDataTypeCode)) {
//    //            DataDict dd = this.dataDictService.findEntityById(id);
//    //            if (dd == null) {
//    //                return false;
//    //            }
//    //            basicDataTypeCode = dd.getBasicDataTypeCode();
//    //        }
//    //
//    //        //基础数据类型
//    //        BasicDataType bdType = this.commonCode2TypeMap.get(basicDataTypeCode);
//    //        Class<? extends BasicData> type = bdType.getType();
//    //        AssertUtils.notNull(type,
//    //                "type is null.basicDataTypeCode:{}",
//    //                basicDataTypeCode);
//    //
//    //        BasicDataService<? extends BasicData> service = BasicDataContext
//    //                .getContext().getBasicDataService(type);
//    //        boolean resFlag = service.enableById(id);
//    //        return resFlag;
//    //    }
//    //
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
//            PageTypeEnum pageType) {
//        AssertUtils.notNull(entityInfo, "entityInfo is null.");
//        AssertUtils.notNull(pageType, "methodName is null.");
//
//        String type = entityInfo.getType().toLowerCase();
//        AssertUtils.notEmpty(type, "type is empty.");
//        String catalog = StringUtils.isEmpty(entityInfo.getCatalog())
//                ? "basicdata" : entityInfo.getCatalog().toLowerCase();
//
//        //页面缓存键
//        String pageCacheKey = (new StringBuilder(
//                TxConstants.INITIAL_STR_LENGTH)).append(catalog)
//                        .append("_")
//                        .append(type)
//                        .append("_")
//                        .append(pageType.getPageName())
//                        .toString();
//        String page = "";
//        if (pageMap.containsKey(pageCacheKey)) {
//            page = pageMap.get(pageCacheKey);
//            return page;
//        }
//
//        StringBuilder sb = new StringBuilder(TxConstants.INITIAL_STR_LENGTH);
//        sb//.append("/")
//                .append(catalog)
//                .append("/")
//                .append(type)
//                .append("/")
//                .append(pageType.getPageName());
//        page = sb.toString();
//        if (!existPage(page)) {
//            //如果页面不存在获取其对应的默认页面
//            page = pageType.getDefaultPage();
//        }
//        pageMap.put(pageCacheKey, page);
//        return page;
//    }
//
//    /**
//     * 判断Jsp页面是否存在
//     * <功能详细描述>
//     * @param page
//     * @return [参数说明]
//     *
//     * @return boolean [返回类型说明]
//     * @exception throws [异常类型] [异常说明]
//     * @see [类、类#方法、类#成员]
//     */
//    private boolean existPage(String page) {
//        View view = null;
//        try {
//            view = this.viewResolverComposite.resolveViewName(page, null);
//        } catch (Exception e) {
//            view = null;
//        }
//        if (view == null) {
//            return false;
//        } else {
//            return true;
//        }
//    }
//
//    /**
//     * 基础数据页类型<br/>
//     * <功能详细描述>
//     *
//     * @author  Administrator
//     * @version  [版本号, 2016年10月10日]
//     * @see  [相关类/方法]
//     * @since  [产品/模块版本]
//     */
//    public static enum PageTypeEnum {
//        //增加基础数据
//        Add("add", "/basicdata/addBasicData"),
//
//        //更新基础数据
//        Update("update", "/basicdata/updateBasicData"),
//
//        //查询树列表
//        QueryTree("queryTree", "/basicdata/queryBasicDataPagedList"),
//
//        //查询列表
//        //如果类型为空的时候，跳转到基础数据列表《分页》
//        QueryList("queryList", "/basicdata/queryBasicDataList"),
//
//        //查询分页列表
//        //如果类型为空的时候，跳转到基础数据列表《分页》
//        QueryPagedList("queryPagedList", "/basicdata/queryBasicDataPagedList");
//
//        /** 默认页面 */
//        private final String pageName;
//
//        /** 默认页 */
//        private final String defaultPage;
//
//        /** <默认构造函数> */
//        private PageTypeEnum(String pageName, String defaultPage) {
//            this.pageName = pageName;
//            this.defaultPage = defaultPage;
//        }
//
//        public String getPageName() {
//            return pageName;
//        }
//
//        public String getDefaultPage() {
//            return defaultPage;
//        }
//    }
//}