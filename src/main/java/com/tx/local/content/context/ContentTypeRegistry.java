///*
// * 描          述:  <描述>
// * 修  改   人:  Administrator
// * 修改时间:  2017年3月3日
// * <修改描述:>
// */
//package com.tx.local.content.context;
//
//import java.util.Collection;
//import java.util.HashMap;
//import java.util.Map;
//
//import org.apache.commons.collections4.CollectionUtils;
//import org.apache.commons.lang3.StringUtils;
//import org.springframework.beans.BeansException;
//import org.springframework.beans.factory.InitializingBean;
//import org.springframework.context.ApplicationContext;
//import org.springframework.context.ApplicationContextAware;
//import org.springframework.stereotype.Component;
//
//import com.tx.core.exceptions.util.AssertUtils;
//import com.tx.local.content.context.typehandler.DBRichContentHandler;
//import com.tx.local.content.model.ContentInfoTypeEnum;
//
///**
// * 内容类型注册表<br/>
// *    
// * <功能详细描述>
// * 
// * @author  Administrator
// * @version  [版本号, 2017年3月3日]
// * @see  [相关类/方法]
// * @since  [产品/模块版本]
// */
//@Component("contentTypeRegistry")
//public class ContentTypeRegistry implements InitializingBean,
//        ApplicationContextAware {
//    
//    /** 默认类型处理器 */
//    private ContentTypeHandler defaultTypeHandler = new DBRichContentHandler(
//            "default", "默认类型");
//    
//    /** 编码对处理器的映射 */
//    private Map<String, ContentTypeHandler> code2handlerMap;
//    
//    /** 编码对处理器的映射 */
//    private Map<String, ContentInfoTypeEnum> code2typeMap;
//    
//    /** spring容器句柄 */
//    private ApplicationContext applicationContext;
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
//     * @throws Exception
//     */
//    @Override
//    public void afterPropertiesSet() throws Exception {
//        Collection<ContentTypeHandler> handlers = this.applicationContext.getBeansOfType(ContentTypeHandler.class)
//                .values();
//        
//        if (CollectionUtils.isEmpty(handlers)) {
//            return;
//        }
//        this.code2typeMap = new HashMap<>();
//        this.code2handlerMap = new HashMap<>();
//        
//        for (ContentTypeHandler handlerTemp : handlers) {
//            ContentInfoTypeEnum typeTemp = buildType(handlerTemp);
//            this.code2typeMap.put(typeTemp.getCode(), typeTemp);
//            this.code2handlerMap.put(typeTemp.getCode(), handlerTemp);
//        }
//        
//        ContentInfoTypeEnum typeTemp = buildType(this.defaultTypeHandler);
//        this.code2typeMap.put(typeTemp.getCode(), typeTemp);
//        this.code2handlerMap.put(typeTemp.getCode(), this.defaultTypeHandler);
//    }
//    
//    /**
//      * 构建内容类型<br/>
//      * <功能详细描述>
//      * @param handler
//      * @return [参数说明]
//      * 
//      * @return ContentInfoType [返回类型说明]
//      * @exception throws [异常类型] [异常说明]
//      * @see [类、类#方法、类#成员]
//     */
//    private ContentInfoTypeEnum buildType(ContentTypeHandler handler) {
//        AssertUtils.notNull(handler, "handler is null.");
//        AssertUtils.notEmpty(handler.type(), "handler.type is empty.");
//        
//        ContentInfoTypeEnum type = new ContentInfoTypeEnum();
//        type.setCode(handler.type());
//        type.setName(handler.name());
//        type.setRemark(handler.remark());
//        type.setModifyAble(false);
//        type.setValid(true);
//        return type;
//    }
//    
//    /**
//      * 获取类型编码获取对应的类型<br/>
//      * <功能详细描述>
//      * @param typeCode
//      * @return [参数说明]
//      * 
//      * @return ContentInfoType [返回类型说明]
//      * @exception throws [异常类型] [异常说明]
//      * @see [类、类#方法、类#成员]
//     */
//    public ContentInfoTypeEnum getTypeByCode(String typeCode) {
//        if (StringUtils.isEmpty(typeCode)) {
//            typeCode = this.defaultTypeHandler.type();
//        }
//        
//        ContentInfoTypeEnum cit = this.code2typeMap.get(typeCode);
//        return cit;
//    }
//    
//    /**
//      * 根据内容类型编码获取对应的类型处理器<br/>
//      * <功能详细描述>
//      * @param typeCode
//      * @return [参数说明]
//      * 
//      * @return ContentTypeHandler [返回类型说明]
//      * @exception throws [异常类型] [异常说明]
//      * @see [类、类#方法、类#成员]
//     */
//    public ContentTypeHandler getTypeHandlerByCode(String typeCode) {
//        if (StringUtils.isEmpty(typeCode)) {
//            return this.defaultTypeHandler;
//        }
//        
//        ContentTypeHandler handler = this.code2handlerMap.get(typeCode);
//        if (handler == null) {
//            return this.defaultTypeHandler;
//        } else {
//            return handler;
//        }
//    }
//    
//    /**
//     * @return 返回 code2handlerMap
//     */
//    public Map<String, ContentTypeHandler> getCode2handlerMap() {
//        return code2handlerMap;
//    }
//    
//    /**
//     * @return 返回 code2typeMap
//     */
//    public Map<String, ContentInfoTypeEnum> getCode2typeMap() {
//        return code2typeMap;
//    }
//}
