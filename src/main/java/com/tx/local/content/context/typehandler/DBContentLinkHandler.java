///*
// * 描          述:  <描述>
// * 修  改   人:  Administrator
// * 修改时间:  2017年3月5日
// * <修改描述:>
// */
//package com.tx.local.content.context.typehandler;
//
//import org.apache.commons.lang3.StringUtils;
//import org.springframework.stereotype.Component;
//
//import com.tx.core.exceptions.util.AssertUtils;
//import com.tx.local.content.context.AbstractContentTypeHandler;
//import com.tx.local.content.model.ContentInfo;
//
///**
// * 默认的内容类型处理器<br/>
// * <功能详细描述>
// * 
// * @author  Administrator
// * @version  [版本号, 2017年3月5日]
// * @see  [相关类/方法]
// * @since  [产品/模块版本]
// */
//@Component("dbContentLinkHandler")
//public class DBContentLinkHandler extends AbstractContentTypeHandler {
//    
//    /**
//     * @return
//     */
//    @Override
//    public String type() {
//        return "dbContentLink";
//    }
//    
//    /**
//     * @return
//     */
//    @Override
//    public String name() {
//        return "内容链接DB类型";
//    }
//    
//    /**
//     * @return
//     */
//    @Override
//    public String remark() {
//        return "存储内容至数据库,title为链接的悬停内容,contentFileId为图片id,content为显示文字.";
//    }
//    
//    /**
//     * @param contentInfo
//     */
//    @Override
//    public void validate(ContentInfo contentInfo) {
//        AssertUtils.notNull(contentInfo, "contentInfo is null.");
//        if (!StringUtils.isEmpty(contentInfo.getContent())) {
//            AssertUtils.isTrue(contentInfo.getContent().length() <= 2000,
//                    "contentInfo.content.length:{} should <= 2000",
//                    new Object[] { contentInfo.getContent().length() });
//        }
//    }
//}
