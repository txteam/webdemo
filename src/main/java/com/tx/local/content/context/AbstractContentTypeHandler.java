/*
 * 描          述:  <描述>
 * 修  改   人:  Administrator
 * 修改时间:  2017年3月5日
 * <修改描述:>
 */
package com.tx.local.content.context;

import com.tx.core.util.MessageUtils;
import com.tx.local.content.model.ContentInfo;

/**
 * 内容类型处理器<br/>
 * <功能详细描述>
 * 
 * @author  Administrator
 * @version  [版本号, 2017年3月5日]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public abstract class AbstractContentTypeHandler implements ContentTypeHandler {
    
    /**
     * @return
     */
    @Override
    public String addView() {
        String type = type();
        String addView = MessageUtils.format("/content/{}/addContentInfo", type);
        return addView;
    }
    
    /**
     * @return
     */
    @Override
    public String updateView() {
        String type = type();
        String addView = MessageUtils.format("/content/{}/updateContentInfo",
                type);
        return addView;
    }
    
    /**
     * @param contentInfo
     */
    @Override
    public void doBeforeInsertHandle(ContentInfo contentInfo) {
    }
    
    /**
     * @param contentInfo
     */
    @Override
    public void doBeforeUpdateHandle(ContentInfo contentInfo) {
    }
    
    /**
     * @param contentInfo
     */
    @Override
    public void doAfterDeleteHandle(ContentInfo contentInfo) {
    }
    
    /**
     * @param contentInfo
     */
    @Override
    public void doAfterFindHandle(ContentInfo contentInfo) {
    }
    
    /**
     * @param contentInfo
     */
    @Override
    public void validate(ContentInfo contentInfo) {
    }
}
