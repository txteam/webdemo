/*
 * 描          述:  <描述>
 * 修  改   人:  Administrator
 * 修改时间:  2016年11月17日
 * <修改描述:>
 */
package com.tx.local.creditinfo.dao.impl;

import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.session.Configuration;

import com.tx.core.mybatis.assistant.BaseDaoMapperBuilderAssistant;

/**
 * Mapper构建助手扩展类<br/>
 *    该逻辑的调用，在设计上应该避免在修改statement期间出现业务调用的情况，不然可能出现不可预知的错误<br/>
 * <功能详细描述>
 * 
 * @author  Administrator
 * @version  [版本号, 2016年11月17日]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public class CreditLinkedMapperBuilderAssistant
        extends BaseDaoMapperBuilderAssistant {
    
    /** <默认构造函数> */
    public CreditLinkedMapperBuilderAssistant(Configuration configuration,
            Class<?> beanType) {
        super(configuration, beanType);
        
        this.tableName = this.tableName.toUpperCase();
        
        //namespace别名,使用特殊的命名范围
        String currentNamespace = "creditinfo."
                + StringUtils.uncapitalize(entityType.getSimpleName());
        setCurrentNamespace(currentNamespace);
    }
}