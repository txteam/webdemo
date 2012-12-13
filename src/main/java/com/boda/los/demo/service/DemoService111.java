/*
 * 描          述:  <描述>
 * 修  改   人:  PengQingyang
 * 修改时间:  2012-10-14
 * <修改描述:>
 */
package com.boda.los.demo.service;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.time.DateFormatUtils;
import org.apache.commons.lang.time.DateUtils;
import org.apache.cxf.common.util.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.DigestUtils;

import com.boda.los.demo.dao.DemoDao111;
import com.boda.los.demo.model.Demo;
import com.tx.core.exceptions.parameter.ParameterIsEmptyException;
import com.tx.core.exceptions.parameter.ParameterIsInvalidException;
import com.tx.core.mybatis.model.BatchResult;
import com.tx.core.mybatis.model.Order;
import com.tx.core.paged.model.PagedList;

/**
 * <业务层>
 * <功能详细描述>
 * 
 * @author  PengQingyang
 * @version  [版本号, 2012-10-14]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
@Component("demoService111")
public class DemoService111 {
    
    /* 是否需要引入日志记录，根据具体业务定，这里是为了打印启动加载情况才加入的  */
    private Logger logger = LoggerFactory.getLogger(DemoService111.class);
    
    @Resource(name = "demoDao111")
    private DemoDao111 demoDao;
    
    public DemoService111() {
        logger.info("Instance DemoService............................");
    }
    
    
    public void test(){
        findDemoById("a");
        findDemoById("b");
        
        
    }
    
    /**
      * 插入demo对象
      * <功能详细描述>
      * @param demo [参数说明]
      * 
      * @return void [返回类型说明]
      * @exception throws [异常类型] [异常说明]
      * @see [类、类#方法、类#成员]
     */
    @Transactional
    public void insertDemo(Demo demo) {
        //验证传入的demo合法性
        if (demo == null || StringUtils.isEmpty(demo.getPassword())
                || StringUtils.isEmpty(demo.getLoginName())) {
            throw new ParameterIsEmptyException(
                    "insertDemo fail. loginName或password不能为空");
        }
        
        //处理业务逻辑
        //加密密码字段
        demo.setPassword(DigestUtils.md5DigestAsHex(demo.getPassword()
                .getBytes()));
        
        //demo.setLastUpdateDate(new Date());
        
        //持久化
        this.demoDao.insertDemo(demo);
    }
    
    /**
     * @param demoList
     */
    @Transactional
    public BatchResult batchInsertDemoNotStopWhenException(List<Demo> demoList,boolean testAfterSuccessThrowException) {
        //验证传入的demo合法性
        if (CollectionUtils.isEmpty(demoList)) {
            throw new ParameterIsEmptyException(
                    "batchInsertDemoNotStopWhenException fail. demoList不能为空");
        }
        
        //处理业务逻辑
        //加密密码字段
        for (Demo demoTemp : demoList) {
            demoTemp.setPassword(DigestUtils.md5DigestAsHex(demoTemp.getPassword()
                    .getBytes()));
            demoTemp.setLastUpdateDate(new Date());
        }
        this.demoDao.insertDemo(demoList.get(0));
        BatchResult  res = this.demoDao.batchInsertDemo(demoList, false);
        demoList.get(0).setLoginName("test"
                + DateFormatUtils.format(new Date(), "yyyyMMddHHmmssSSS"));
        
        this.demoDao.insertDemo(demoList.get(0));
        
        if(testAfterSuccessThrowException){
            throw new ParameterIsEmptyException("testException");
        }
        return res;
    }
    
    /**
     * @param demoList
     */
    @Transactional
    public BatchResult batchInsertDemoStopWhenException(List<Demo> demoList) {
        //验证传入的demo合法性
        if (CollectionUtils.isEmpty(demoList)) {
            throw new ParameterIsEmptyException(
                    "batchInsertDemoNotStopWhenException fail. demoList不能为空");
        }
        
        //处理业务逻辑
        //加密密码字段
        for (Demo demoTemp : demoList) {
            demoTemp.setPassword(DigestUtils.md5DigestAsHex(demoTemp.getPassword()
                    .getBytes()));
            demoTemp.setLastUpdateDate(new Date());
        }
        
        //持久化
        return this.demoDao.batchInsertDemo(demoList, true);
    }
    
    /**
      * 查询demo对象
      * <功能详细描述>
      * @param id
      * @return [参数说明]
      * 
      * @return Demo [返回类型说明]
      * @exception throws [异常类型] [异常说明]
      * @see [类、类#方法、类#成员]
     */
    public Demo findDemoById(String id) {
        if (StringUtils.isEmpty(id)) {
            return null;
        }
        
        //组装查询条件
        Demo findCondition = new Demo();
        findCondition.setId(id);
        
        return this.demoDao.findDemo(findCondition);
    }
    
    /**
      * 查询demo实例
      *<功能详细描述>
      * @param loginName
      * @return [参数说明]
      * 
      * @return Demo [返回类型说明]
      * @exception throws [异常类型] [异常说明]
      * @see [类、类#方法、类#成员]
     */
    public Demo findDemoByLoginName(String loginName) {
        if (StringUtils.isEmpty(loginName)) {
            return null;
        }
        
        //组装查询条件
        Demo findCondition = new Demo();
        findCondition.setLoginName(loginName);
        
        return this.demoDao.findDemo(findCondition);
    }
    
    /**
      * 根据 loginName和password查询demo实例
      * <功能详细描述>
      * @param loginName
      * @param password
      * @return [参数说明]
      * 
      * @return Demo [返回类型说明]
      * @exception throws [异常类型] [异常说明]
      * @see [类、类#方法、类#成员]
     */
    public Demo findDemoByLoginNameAndPassword(String loginName, String password) {
        if (StringUtils.isEmpty(loginName) || StringUtils.isEmpty(password)) {
            return null;
        }
        
        //组装查询条件
        Demo findCondition = new Demo();
        findCondition.setLoginName(loginName);
        findCondition.setPassword(DigestUtils.md5DigestAsHex(password.getBytes()));
        
        return this.demoDao.findDemo(findCondition);
    }
    
    /**
      * 查询demo列表
      * <功能详细描述>
      * @param id
      * @param column
      * @param login
      * @param queryColumnName
      * @param order
      * @return [参数说明]
      * 
      * @return List<Demo> [返回类型说明]
      * @exception throws [异常类型] [异常说明]
      * @see [类、类#方法、类#成员]
     */
    public List<Demo> queryDemoList(String id, String loginName, String name,
            String startCreateDate, String endCreateDate,
            String queryColumnName, boolean isAsc) {
        
        Map<String, Object> queryCondition = new HashMap<String, Object>();
        queryCondition.put("id", id);
        queryCondition.put("loginName", loginName);
        queryCondition.put("name", name);
        
        //将查询条件中的时间转换为具体的时间，这个转换的逻辑其实应该写在controller中
        try {
            if (StringUtils.isEmpty(startCreateDate)) {
                queryCondition.put("stateCreateDate",
                        DateUtils.parseDate(startCreateDate, new String[] {
                                "yyyy-MM-dd", "yyyy-MM-dd HH:mm:ss" }));
            }
            if (StringUtils.isEmpty(endCreateDate)) {
                queryCondition.put("endCreateDate",
                        DateUtils.parseDate(endCreateDate, new String[] {
                                "yyyy-MM-dd", "yyyy-MM-dd HH:mm:ss" }));
            }
        } catch (ParseException e) {
            throw new ParameterIsInvalidException(
                    "queryDemoList startCreateDate:{} endCreatDate:{} is invalid.",
                    startCreateDate, endCreateDate);
        }
        List<Order> orderList = null;
        if (!StringUtils.isEmpty(queryColumnName)) {
            orderList = new ArrayList<Order>();
            orderList.add(isAsc ? Order.asc(queryColumnName)
                    : Order.desc(queryColumnName));
        }
        
        return this.demoDao.queryDemoList(queryCondition, orderList);
    }
    
    /**
      * 根据demo的创建时间查询demo列表
      * <功能详细描述>
      * @param startCreateDate
      * @param endCreateDate
      * @return [参数说明]
      * 
      * @return PagedList<Demo> [返回类型说明]
      * @exception throws [异常类型] [异常说明]
      * @see [类、类#方法、类#成员]
     */
    public PagedList<Demo> queryDemoPagedList(Date startCreateDate,
            Date endCreateDate) {
        
        Map<String, Object> queryCondition = new HashMap<String, Object>();
        queryCondition.put("stateCreateDate", startCreateDate);
        queryCondition.put("endCreateDate", endCreateDate);
        
        return this.demoDao.queryDemoPagedList(queryCondition, null);
    }
    
    /**
      *<功能简述>
      *<功能详细描述>
      * @param code
      * @return [参数说明]
      * 
      * @return List<Demo> [返回类型说明]
      * @exception throws [异常类型] [异常说明]
      * @see [类、类#方法、类#成员]
     */
    public List<Demo> queryDemoListByCode(String code) {
        Map<String, Object> paraMap = null;
        if (!StringUtils.isEmpty(code)) {
            paraMap = new HashMap<String, Object>();
        }
        
        return this.demoDao.queryDemoList(paraMap);
    }
    
    /**
      * 根据id删除demo实例
      *<功能详细描述>
      * @param demoId
      * @return [参数说明]
      * 
      * @return boolean [返回类型说明]
      * @exception throws [异常类型] [异常说明]
      * @see [类、类#方法、类#成员]
     */
    public boolean deleteDemoById(String demoId) {
        if (StringUtils.isEmpty(demoId)) {
            throw new ParameterIsEmptyException("{} is empty",
                    "deleteDemo demoId");
        }
        
        //组装条件
        Demo condition = new Demo();
        condition.setId(demoId);
        
        return this.demoDao.deleteDemo(condition) > 0;
    }
    
    /**
      * 更新demo实例的名字
      *<功能详细描述>
      * @param demo
      * @return [参数说明]
      * 
      * @return boolean [返回类型说明]
      * @exception throws [异常类型] [异常说明]
      * @see [类、类#方法、类#成员]
     */
    @Transactional
    public boolean updateDemoName(Map<String, Object> test) {
        
        return this.demoDao.updateDemo(test) > 0;
    }
}
