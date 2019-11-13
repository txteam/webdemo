///*
// * 描          述:  <描述>
// * 修  改   人:  Administrator
// * 修改时间:  2017年3月5日
// * <修改描述:>
// */
//package com.tx.local.content.service;
//
//import java.io.InputStream;
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//import javax.annotation.Resource;
//
//import org.apache.commons.collections4.CollectionUtils;
//import org.springframework.beans.factory.InitializingBean;
//import org.springframework.context.ResourceLoaderAware;
//import org.springframework.core.io.ResourceLoader;
//import org.springframework.stereotype.Component;
//import org.springframework.transaction.TransactionStatus;
//import org.springframework.transaction.support.TransactionCallbackWithoutResult;
//import org.springframework.transaction.support.TransactionTemplate;
//
//import com.thoughtworks.xstream.XStream;
//import com.tx.core.exceptions.util.AssertUtils;
//import com.tx.core.support.initable.helper.ConfigInitAbleHelper;
//import com.tx.core.util.ObjectUtils;
//import com.tx.core.util.XstreamUtils;
//import com.tx.local.content.config.CategoryConfig;
//import com.tx.local.content.config.ContentConfig;
//import com.tx.local.content.config.LevelConfig;
//import com.tx.local.content.context.ContentTypeRegistry;
//import com.tx.local.content.model.ContentInfoCategory;
//import com.tx.local.content.model.ContentInfoLevel;
//
///**
// * 内容配置注册表<br/>
// * <功能详细描述>
// * 
// * @author  Administrator
// * @version  [版本号, 2017年3月5日]
// * @see  [相关类/方法]
// * @since  [产品/模块版本]
// */
//@Component("contentConfigRegistry")
//public class ContentConfigRegistry
//        implements InitializingBean, ResourceLoaderAware {
//    
//    /** 内容类型注册表 */
//    @Resource(name = "contentTypeRegistry")
//    private ContentTypeRegistry contentTypeRegistry;
//    
//    /** 内容信息分类业务层 */
//    @Resource(name = "contentInfoCategoryService")
//    private ContentInfoCategoryService contentInfoCategoryService;
//    
//    /** 内容等级业务层 */
//    @Resource(name = "contentInfoLevelService")
//    private ContentInfoLevelService contentInfoLevelService;
//    
//    /** 事务处理句柄 */
//    @Resource(name = "transactionTemplate")
//    private TransactionTemplate transactionTemplate;
//    
//    /** 内容配置解析器 */
//    private static XStream contentConfigXstream = XstreamUtils
//            .getXstream(ContentConfig.class);
//    
//    /** 资源加载器 */
//    private ResourceLoader resourceLoader;
//    
//    /** 配置文件存放路径 */
//    private String configLocation = "classpath:content/content_config.xml";
//    
//    /** 分类配置列表 */
//    private Map<String, ContentInfoCategory> categoryMap;
//    
//    /** 等级配置列表 */
//    private List<ContentInfoLevel> levelList;
//    
//    /** 等级配置初始化辅助类 */
//    protected ConfigInitAbleHelper<ContentInfoLevel> levelConfigInitAbleHelper;
//    
//    /**
//     * @param resourceLoader
//     */
//    @Override
//    public void setResourceLoader(ResourceLoader resourceLoader) {
//        this.resourceLoader = resourceLoader;
//    }
//    
//    /**
//     * @throws Exception
//     */
//    @Override
//    public void afterPropertiesSet() throws Exception {
//        AssertUtils.notEmpty(this.configLocation, "configLocation is empty.");
//        org.springframework.core.io.Resource config = this.resourceLoader
//                .getResource(this.configLocation);
//        
//        ContentConfig contentConfig = null;
//        try (InputStream in = config.getInputStream()) {
//            String configXml = "";//IOUtils.toString(in, "UTF-8");
//            contentConfig = (ContentConfig) contentConfigXstream
//                    .fromXML(configXml);
//        } catch (Exception e) {
//            AssertUtils.wrap(e, "read configLocation.");
//        }
//        AssertUtils.notNull(contentConfig, "contentConfig is null.");
//        
//        this.categoryMap = new HashMap<>();
//        this.levelList = new ArrayList<>();
//        //初始化分配配置
//        final List<CategoryConfig> finalCategoryConfigList = contentConfig
//                .getCategoryConfigList();
//        this.transactionTemplate
//                .execute(new TransactionCallbackWithoutResult() {
//                    @Override
//                    protected void doInTransactionWithoutResult(
//                            TransactionStatus arg0) {
//                        parseCategoryConfig(null, finalCategoryConfigList);
//                    }
//                });
//        //初始化等级配置
//        initCategoryConfigInitAbleHelper(this.levelList);
//        this.levelConfigInitAbleHelper.init(transactionTemplate);
//    }
//    
//    /**
//      * 解析分类配置<br/>
//      * <功能详细描述>
//      * @param parent
//      * @param categoryConfigList [参数说明]
//      * 
//      * @return void [返回类型说明]
//      * @exception throws [异常类型] [异常说明]
//      * @see [类、类#方法、类#成员]
//     */
//    private void parseCategoryConfig(ContentInfoCategory parent,
//            List<CategoryConfig> categoryConfigList) {
//        for (CategoryConfig categoryConfig : categoryConfigList) {
//            ContentInfoCategory category = buildCategory(parent,
//                    categoryConfig);
//            category = this.contentInfoCategoryService.saveForInit(category);
//            this.categoryMap.put(category.getCode(), category);
//            
//            if (!CollectionUtils.isEmpty(categoryConfig.getLevelConfigList())) {
//                for (LevelConfig levelConfig : categoryConfig
//                        .getLevelConfigList()) {
//                    ContentInfoLevel level = buildLevel(category, levelConfig);
//                    this.levelList.add(level);
//                }
//            }
//            if (!CollectionUtils
//                    .isEmpty(categoryConfig.getCategoryConfigList())) {
//                parseCategoryConfig(category,
//                        categoryConfig.getCategoryConfigList());
//            }
//        }
//        //遍历所有的分类，将非配置的分类设置为可编辑
//        List<ContentInfoCategory> categoryList = this.contentInfoCategoryService
//                .queryList(null, (Map<String, Object>) null);
//        for (ContentInfoCategory categoryTemp : categoryList) {
//            if (categoryTemp.isModifyAble()
//                    || this.categoryMap.containsKey(categoryTemp.getCode())) {
//                continue;
//            }
//            categoryTemp.setModifyAble(true);
//            this.contentInfoCategoryService.updateById(categoryTemp);
//        }
//    }
//    
//    /**
//      * 构建分类<br/>
//      * <功能详细描述>
//      * @param parent
//      * @param categoryConfig
//      * @return [参数说明]
//      * 
//      * @return ContentInfoCategory [返回类型说明]
//      * @exception throws [异常类型] [异常说明]
//      * @see [类、类#方法、类#成员]
//     */
//    private ContentInfoCategory buildCategory(ContentInfoCategory parent,
//            CategoryConfig categoryConfig) {
//        AssertUtils.notNull(categoryConfig, "categoryConfig is null.");
//        
//        ContentInfoCategory category = new ContentInfoCategory();
//        category.setParent(parent);
//        category.setCode(categoryConfig.getCode());
//        category.setName(categoryConfig.getName());
//        category.setOrderIndex(categoryConfig.getOrderIndex());
//        category.setRemark(categoryConfig.getRemark());
//        category.setModifyAble(false);
//        category.setValid(true);
//        category.setType(this.contentTypeRegistry
//                .getTypeByCode(categoryConfig.getType()));
//        return category;
//    }
//    
//    /**
//     * 构建分类<br/>
//     * <功能详细描述>
//     * @param parent
//     * @param categoryConfig
//     * @return [参数说明]
//     * 
//     * @return ContentInfoCategory [返回类型说明]
//     * @exception throws [异常类型] [异常说明]
//     * @see [类、类#方法、类#成员]
//    */
//    private ContentInfoLevel buildLevel(ContentInfoCategory category,
//            LevelConfig levelConfig) {
//        AssertUtils.notNull(levelConfig, "levelConfig is null.");
//        
//        ContentInfoLevel level = new ContentInfoLevel();
//        level.setCategory(category);
//        level.setCode(levelConfig.getCode());
//        level.setName(levelConfig.getName());
//        level.setRemark(levelConfig.getRemark());
//        level.setModifyAble(false);
//        category.setValid(true);
//        return level;
//    }
//    
//    /**
//     * 初始化分类初始化辅助器<br/>
//     * <功能详细描述>
//     * @param categoryList
//     * @throws Exception [参数说明]
//     * 
//     * @return void [返回类型说明]
//     * @exception throws [异常类型] [异常说明]
//     * @see [类、类#方法、类#成员]
//    */
//    private void initCategoryConfigInitAbleHelper(
//            final List<ContentInfoLevel> levelList) {
//        final ContentInfoLevelService levelService = this.contentInfoLevelService;
//        
//        this.levelConfigInitAbleHelper = new ConfigInitAbleHelper<ContentInfoLevel>() {
//            protected void batchUpdate(List<ContentInfoLevel> needUpdateList) {
//                levelService.batchUpdate(needUpdateList);
//            }
//            
//            protected void batchInsert(List<ContentInfoLevel> needInsertList) {
//                levelService.batchInsert(needInsertList);
//            }
//            
//            protected List<ContentInfoLevel> queryListFromConfig() {
//                List<ContentInfoLevel> resCfgList = levelList;
//                return resCfgList;
//            }
//            
//            protected List<ContentInfoLevel> queryListFromDB() {
//                List<ContentInfoLevel> resDbList = levelService.queryList(null,
//                        (Map<String, Object>)null);
//                return resDbList;
//            }
//            
//            protected boolean isNeedUpdate(ContentInfoLevel ciaOfDBTemp,
//                    ContentInfoLevel ciaOfConfig) {
//                boolean flag = false;
//                if (!ObjectUtils.equals(ciaOfDBTemp,
//                        ciaOfConfig,
//                        "name",
//                        "remark")
//                        || !ObjectUtils.equals(ciaOfDBTemp.getCategory(),
//                                ciaOfConfig.getCategory(),
//                                "code")) {
//                    flag = true;
//                }
//                return flag;
//            }
//            
//            protected void doBeforeUpdate(ContentInfoLevel ciaOfDB,
//                    ContentInfoLevel ciaOfCfg) {
//                ciaOfDB.setName(ciaOfCfg.getName());
//                ciaOfDB.setRemark(ciaOfCfg.getRemark());
//                ciaOfDB.setCategory(ciaOfCfg.getCategory());
//            }
//        };
//    }
//    
//}
