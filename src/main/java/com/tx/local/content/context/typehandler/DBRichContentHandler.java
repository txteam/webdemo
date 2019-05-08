/*
 * 描          述:  <描述>
 * 修  改   人:  Administrator
 * 修改时间:  2017年3月5日
 * <修改描述:>
 */
package com.tx.local.content.context.typehandler;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import com.tx.core.exceptions.util.AssertUtils;
import com.tx.local.content.context.AbstractContentTypeHandler;
import com.tx.local.content.model.ContentInfo;

/**
 * 默认的内容类型处理器<br/>
 * <功能详细描述>
 * 
 * @author  Administrator
 * @version  [版本号, 2017年3月5日]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
@Component("dbRichContentHandler")
public class DBRichContentHandler extends AbstractContentTypeHandler {
    
    /** 数据库存储 富文本内容 类型 db_for_rich_content*/
    private String type = "dbRichContent";
    
    /** 类型处理器名:富文本内容DB类型 */
    private String name = "富文本内容DB类型";
    
    /** <默认构造函数> */
    public DBRichContentHandler() {
        super();
    }
    
    /** <默认构造函数> */
    public DBRichContentHandler(String type, String name) {
        super();
        this.type = type;
        this.name = name;
    }
    
    /**
     * @return
     */
    @Override
    public String type() {
        return this.type;
    }
    
    /**
     * @return
     */
    @Override
    public String name() {
        return this.name;
    }
    
    /**
     * @return
     */
    @Override
    public String remark() {
        return "存储内容至数据库,content内容存储于数据库中，content中字长一般不超过2000.";
    }
    
    /**
     * @param contentInfo
     */
    @Override
    public void validate(ContentInfo contentInfo) {
        AssertUtils.notNull(contentInfo, "contentInfo is null.");
        if (!StringUtils.isEmpty(contentInfo.getContent())) {
            AssertUtils.isTrue(contentInfo.getContent().length() <= 2000,
                    "contentInfo.content.length:{} should <= 2000",
                    new Object[] { contentInfo.getContent().length() });
        }
    }
    
    /**
     * @return 返回 type
     */
    public String getType() {
        return type;
    }
    
    /**
     * @param 对type进行赋值
     */
    public void setType(String type) {
        this.type = type;
    }
    
    /**
     * @return 返回 name
     */
    public String getName() {
        return name;
    }
    
    /**
     * @param 对name进行赋值
     */
    public void setName(String name) {
        this.name = name;
    }
}
