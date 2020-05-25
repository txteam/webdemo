/*
 * 描          述:  <描述>
 * 修  改   人:  
 * 修改时间:  
 * <修改描述:>
 */
package com.tx.local.message.dao;

import com.tx.core.mybatis.dao.MybatisBaseDao;
import com.tx.local.message.model.NoticeMessage;
import com.tx.local.message.model.NoticeMessageDetail;

import java.util.List;
import java.util.Map;

/**
 * NoticeMessage持久层
 * <功能详细描述>
 * 
 * @author  
 * @version [版本号]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
public interface NoticeMessageDao extends MybatisBaseDao<NoticeMessage, String>{

    List<NoticeMessageDetail> queryPagedListByCondition(Map<String,Object> params);

    Integer queryCountByCondition(Map<String,Object> params);
}
