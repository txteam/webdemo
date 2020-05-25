/*
 * 描          述:  <描述>
 * 修  改   人:  
 * 修改时间:  
 * <修改描述:>
 */
package com.tx.local.message.dao.impl;

import javax.annotation.Resource;

import com.tx.local.message.model.NoticeMessageDetail;
import org.springframework.stereotype.Component;

import com.tx.core.mybatis.dao.impl.MybatisBaseDaoImpl;
import com.tx.core.mybatis.support.MyBatisDaoSupport;
import com.tx.local.message.dao.NoticeMessageDao;
import com.tx.local.message.model.NoticeMessage;

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
@Component("noticeMessageDao")
public class NoticeMessageDaoImpl 
		extends MybatisBaseDaoImpl<NoticeMessage, String>
		implements NoticeMessageDao {
    
    @Resource(name = "myBatisDaoSupport")
    private MyBatisDaoSupport myBatisDaoSupport;
    
    /**
     * @return 返回 myBatisDaoSupport
     */
    public MyBatisDaoSupport getMyBatisDaoSupport() {
        return myBatisDaoSupport;
    }

    /**
     * @param 对myBatisDaoSupport进行赋值
     */
    public void setMyBatisDaoSupport(MyBatisDaoSupport myBatisDaoSupport) {
        this.myBatisDaoSupport = myBatisDaoSupport;
    }

    @Override
    public List<NoticeMessageDetail> queryPagedListByCondition(Map<String, Object> params) {
        return myBatisDaoSupport.getSqlSessionTemplate().selectList("noticeMessage.queryPagedListByCondition",params);
    }

    @Override
    public Integer queryCountByCondition(Map<String, Object> params) {
        return myBatisDaoSupport.getSqlSessionTemplate().selectOne("noticeMessage.queryCountByCondition",params);
    }
}
